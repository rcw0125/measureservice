package com.talent.base.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.base.model.Dictionary;

public interface  DictionaryDao {
	
	public List<Dictionary> queryList(Dictionary dictionary) throws DataAccessException;
	
	public List<Dictionary> queryAllList(Dictionary dictionary) throws DataAccessException;
	
	public int delete(Dictionary dictionary) throws DataAccessException;
	
	public int deleteByID(Dictionary dictionary) throws DataAccessException;
	
	public int insert(Dictionary dictionary) throws DataAccessException;
}
