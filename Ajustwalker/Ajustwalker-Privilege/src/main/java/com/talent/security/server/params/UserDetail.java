package com.talent.security.server.params;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.talent.core.model.QueryParams;
import com.talent.core.privilege.model.Resource;
import com.talent.core.privilege.model.ResourceRole;
import com.talent.core.privilege.model.User;
import com.talent.core.privilege.model.ResourceUser;
import com.talent.core.privilege.model.UserRole;
import com.talent.privilege.service.ResourceService2;
import com.talent.privilege.service.RoleResourceService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {

	private static final long serialVersionUID = 3957586021470480642L;

	protected static final String ROLE_PREFIX = "ROLE_";

	protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.USER.name());

	protected User user;

	protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	
	private ResourceService2 resourceService;
	
	private RoleResourceService roleResourceService;

	public UserDetail() {
		
	}

	public UserDetail(User user,ResourceService2 resourceService,RoleResourceService roleResourceService) {
		this.user = user;
		this.resourceService = resourceService;
		this.roleResourceService = roleResourceService;
		initialAuthorities();
	}

	private void initialAuthorities() {
		this.grantedAuthorities.add(DEFAULT_USER_ROLE);
        try{
        	List<Resource> allResource = resourceService.findAll();
        	List<Long> userAsignedResource = new ArrayList<Long>();
    		//查找用户通过角色获取的权限对象
    		List<ResourceRole> selectedRoleResources = null;
    		List<UserRole> urList = user.getRoleUsers();
    		if(!urList.isEmpty()){
    			List<Long> roleids = new ArrayList<Long>();
    			for(UserRole item : urList){
    				roleids.add(item.getRoleid());
    			}
    			ResourceRole rr = new ResourceRole();
    			QueryParams<ResourceRole> rrparams = new QueryParams<ResourceRole>(rr);
    			rrparams.in("roleid",roleids);
    			selectedRoleResources = roleResourceService.query(rrparams);
    		}
    		
    		for(Resource item : allResource){
				for(ResourceUser selectedUserResource : user.getResourceUsers()){
					if(item.getId() == selectedUserResource.getResourceid() && !userAsignedResource.contains(item.getId())){
						userAsignedResource.add(item.getId());
						this.grantedAuthorities.add(new SimpleGrantedAuthority(item.getResourcecode()));
					}
				}
				for(ResourceRole selectedRoleResource : selectedRoleResources){
					if(item.getId() == selectedRoleResource.getResourceid() && !userAsignedResource.contains(item.getId())){
						userAsignedResource.add(item.getId());
						this.grantedAuthorities.add(new SimpleGrantedAuthority(item.getResourcecode()));
					}
				}
			}
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}