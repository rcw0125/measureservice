package com.talent.privilege.service.mapper;

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

import com.talent.core.privilege.model.User;
import com.talent.privilege.model.UserMS;

public interface DashboardMapper {
	
	public List<Map<String,Object>> getReminders(String displayname) throws DataAccessException;
	
	public List<Map<String,String>> getMeasureUserPrivileges(String username) throws DataAccessException;
	
	public UserMS getMeasureUserByName(String username);

	public int queryCount() throws DataAccessException;
	
	public String isparameter(int id) throws DataAccessException;
	
	public List<UserMS> getAllMeasureUser();
	
	public UserMS queryPassword(UserMS user) throws DataAccessException;
	
	public int updatePassword(UserMS user) throws DataAccessException;
}