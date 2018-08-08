package com.talent.privilege.service;

public interface PrivilegeService {

	public void processCopyRolerights(long sourceRoleId,String targetRoleIds) throws Exception;
	
	public void processCopyUserrights(long sourceUserId,String targetUserIds) throws Exception;
}