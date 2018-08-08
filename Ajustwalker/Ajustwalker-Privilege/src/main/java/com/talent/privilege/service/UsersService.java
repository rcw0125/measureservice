package com.talent.privilege.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.talent.core.privilege.model.User;
import com.talent.core.service.BaseService;

@Repository
public interface UsersService extends BaseService<User, Long>{
	
	@Query("from User t where t.username = ?1 and t.password = ?2")
	public User checkUser(String username,String password);
	
	@Query("from User t where t.username = ?1")
	public User findByUsername(String username);
	
	@Transactional
	@Modifying
	@Query("update User set password = ?2 where username = ?1")
	public void repassword(String username,String password);
}
