package com.talent.privilege.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.talent.privilege.model.Resource;
import com.talent.privilege.model.Role;
import com.talent.privilege.model.User;

public interface PrivilegeDao {
	
    public User getLoginUser(User user) throws DataAccessException;
    
    public List<Resource> queryResourcesByUsercode(User user) throws DataAccessException;
    
    public List<Resource> queryMeasureMenuByUsercode(User user,String contextName) throws DataAccessException;
    
    public String queryMeasureMenuStrByUsercode(User user,String contextName) throws DataAccessException;
    
    public List<User> getAllUser() throws DataAccessException;
    
    public void insertUserRole(User user) throws DataAccessException;
    
    public void deleteUserRole(User user) throws DataAccessException;
    
    public List<Role> selectUserRole(User user) throws DataAccessException;
    
    public void insertResourceRole(Resource resource) throws DataAccessException;
    
    public void deleteResourceRole(Resource resource) throws DataAccessException;
    
    public List<Role> selectResourceRole(Resource resource) throws DataAccessException;
    
    public User queryPassword(User user) throws DataAccessException;
    
    public int updatePassword(User user) throws DataAccessException;
}
