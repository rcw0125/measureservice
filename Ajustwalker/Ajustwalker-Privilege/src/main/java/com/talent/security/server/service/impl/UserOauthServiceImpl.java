package com.talent.security.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.talent.core.privilege.model.User;
import com.talent.privilege.service.ResourceService2;
import com.talent.privilege.service.RoleResourceService;
import com.talent.privilege.service.UsersService;
import com.talent.security.server.params.UserDetail;
import com.talent.security.server.service.UserOauthService;

@Service("userOauthService")
public class UserOauthServiceImpl implements UserOauthService {

	@Autowired
    private UsersService userDesignService;
	
	@Autowired
	private RoleResourceService roleResourceService;
    
    @Autowired
	private ResourceService2 resourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	String[] loginParams = username.split("@##@");
        User user = userDesignService.findByUsername(loginParams[1]);
        if (user == null) {
            throw new UsernameNotFoundException("找不到名为" + loginParams[1] + "的用户！");
        }
        user.setReserve1(loginParams[0]);
        UserDetail userDetail = new UserDetail(user,resourceService,roleResourceService);
//        try{
//        	List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        	List<Resource> allResource = resourceService.findAll();
//        	List<Long> userAsignedResource = new ArrayList<Long>();
//    		//查找用户通过角色获取的权限对象
//    		List<ResourceRole> selectedRoleResources = null;
//    		List<UserRole> urList = user.getRoleUsers();
//    		if(!urList.isEmpty()){
//    			List<Long> roleids = new ArrayList<Long>();
//    			for(UserRole item : urList){
//    				roleids.add(item.getRoleid());
//    			}
//    			ResourceRole rr = new ResourceRole();
//    			QueryParams<ResourceRole> rrparams = new QueryParams<ResourceRole>(rr);
//    			rrparams.in("roleid",roleids);
//    			selectedRoleResources = roleResourceService.query(rrparams);
//    		}
//    		
//    		for(Resource item : allResource){
//				for(ResourceUser selectedUserResource : user.getResourceUsers()){
//					if(item.getId() == selectedUserResource.getResourceid() && !userAsignedResource.contains(item.getId())){
//						userAsignedResource.add(item.getId());
//						grantedAuthorities.add(new SimpleGrantedAuthority(item.getResourcecode()));
//					}
//				}
//				for(ResourceRole selectedRoleResource : selectedRoleResources){
//					if(item.getId() == selectedRoleResource.getResourceid() && !userAsignedResource.contains(item.getId())){
//						userAsignedResource.add(item.getId());
//						grantedAuthorities.add(new SimpleGrantedAuthority(item.getResourcecode()));
//					}
//				}
//			}
//    		userDetail.setUserAuthorities(grantedAuthorities);
//        }catch(Exception e){
//        	e.printStackTrace();
//        }
        return userDetail;
    }

    @Override
    public boolean isExistedUsername(String username) {
        return null != userDesignService.findByUsername(username);
    }
}