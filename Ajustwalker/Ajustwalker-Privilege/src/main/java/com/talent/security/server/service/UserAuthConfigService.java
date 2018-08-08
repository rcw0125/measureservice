package com.talent.security.server.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.talent.core.model.QueryParams;
import com.talent.core.privilege.model.Resource;
import com.talent.core.privilege.model.ResourceRole;
import com.talent.core.privilege.model.ResourceUser;
import com.talent.core.privilege.model.User;
import com.talent.core.privilege.model.UserRole;
import com.talent.core.privilege.service.ResourceRoleService;
import com.talent.core.privilege.service.ResourceService;
import com.talent.privilege.service.UsersService;

@Service
public class UserAuthConfigService {

    @Autowired
    private UsersService userDesignService;
	
	@Autowired
	private ResourceRoleService resourceRoleService;
    
    @Autowired
	private ResourceService resourceService;

    public User getUser(String username) {
        try {
        	List<User> userList = userDesignService.queryByJpql("from User t where t.username = '" + username + "'");
        	if(null != userList && !userList.isEmpty()){
        		return userList.get(0);
        	}else{
        		return null;
        	}
		} catch (Exception e) {
			return null;
		}
    }

    public List<GrantedAuthority> getRights(User user) {
    	
        List<GrantedAuthority> grantedAuthority = new LinkedList<>();
        try{
        	List<Resource> allResource = resourceService.findAll();
        	
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
    			selectedRoleResources = resourceRoleService.query(rrparams);
    		}
    		
    		for(Resource item : allResource){
				for(ResourceUser selectedUserResource : user.getResourceUsers()){
					if(item.getId() == selectedUserResource.getResourceid()){
						grantedAuthority.add(new SimpleGrantedAuthority(item.getResourcecode()));
					}
				}
				for(ResourceRole selectedRoleResource : selectedRoleResources){
					if(item.getId() == selectedRoleResource.getResourceid()){
						grantedAuthority.add(new SimpleGrantedAuthority(item.getResourcecode()));
					}
				}
			}
        }catch(Exception e){
        	e.printStackTrace();
        }
        return grantedAuthority;
    }
}