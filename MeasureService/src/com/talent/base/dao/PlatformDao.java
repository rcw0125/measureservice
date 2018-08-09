package com.talent.base.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.talent.base.model.BaseEntity;
import com.talent.base.model.PageModel;

public interface PlatformDao {
	
	public <T> PageModel queryPage(T t,PageModel pm) throws DataAccessException;
	
	public <T> PageModel queryPage2(T t,PageModel pm) throws DataAccessException;
	
	public <T> List<T> queryList(T t) throws DataAccessException;
	
	public <T> List<T> queryAllList(T t) throws DataAccessException;
	
	public <T> int remove(T t,String ids) throws DataAccessException;
	
	public <T> int update(T t) throws DataAccessException;
	
	public <T> void insert(T t) throws DataAccessException;
	
	public <T> int getNewID(T t) throws DataAccessException;
	
	public <T> T get(T t) throws DataAccessException;
	
	public <T> List<T> queryTree(T t,String state,String sel,List<T> list) throws DataAccessException;
	
	public <T> List<Object> queryTree(T t,int id,List<BaseEntity> allList,List<BaseEntity> list) throws DataAccessException;
	
	public String dynamicSQL(String sql) throws DataAccessException;
	
	public <T> void order(T t,String orderfield,String direction) throws DataAccessException;
}
