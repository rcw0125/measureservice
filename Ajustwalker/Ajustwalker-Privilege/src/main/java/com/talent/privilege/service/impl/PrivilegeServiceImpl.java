package com.talent.privilege.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.talent.core.privilege.model.ResourceRole;
import com.talent.core.privilege.model.ResourceUser;
import com.talent.core.privilege.model.UserRole;
import com.talent.core.privilege.service.UserRoleService;
import com.talent.privilege.service.PrivilegeService;
import com.talent.privilege.service.RoleResourceService;
import com.talent.privilege.service.UserResourceService;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService{
	
	@Autowired
	private RoleResourceService roleResourceService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserResourceService userResourceService;

	@Override
	public void processCopyRolerights(long sourceRoleId, String targetRoleIds) throws Exception {
		String[] idsArry = targetRoleIds.split(",");
		roleResourceService.updateJpql("delete from ResourceRole where roleid in (" + targetRoleIds + ")");
		List<ResourceRole> roleResourceList = roleResourceService.queryByJpql("from ResourceRole t where t.roleid = " + sourceRoleId);
		for(String id : idsArry){
			for(ResourceRole rr : roleResourceList){
				ResourceRole temp = new ResourceRole();
				temp.setRoleid(Long.parseLong(id));
				temp.setResourceid(rr.getResourceid());
				roleResourceService.insertOrUpdate(temp);
			}
		}
	}

	@Override
	public void processCopyUserrights(long sourceUserId, String targetUserIds) throws Exception {
		String[] idsArry = targetUserIds.split(",");
		userRoleService.updateJpql("delete from UserRole where userid in (" + targetUserIds + ")");
		userResourceService.updateJpql("delete from ResourceUser where userid in (" + targetUserIds + ")");
		List<UserRole> userRoleList = userRoleService.queryByJpql("from UserRole t where t.userid=" + sourceUserId);
		List<ResourceUser> userResourceList = userResourceService.queryByJpql("from ResourceUser t where t.userid=" + sourceUserId);
		for(String id : idsArry){
			for(UserRole ur : userRoleList){
				UserRole temp = new UserRole();
				temp.setUserid(Long.parseLong(id));
				temp.setRoleid(ur.getRoleid());;
				userRoleService.insertOrUpdate(temp);
			}
			for(ResourceUser ur : userResourceList){
				ResourceUser temp = new ResourceUser();
				temp.setUserid(Long.parseLong(id));
				temp.setResourceid(ur.getResourceid());
				userResourceService.insertOrUpdate(temp);
			}
		}
	}
}
