package com.talent.base.dao.impl;

import java.util.List;
import com.talent.base.dao.DictionaryDao;
import com.talent.base.model.Dictionary;
import com.talent.dubbo.config.api.annotation.Service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Service
@Component
public class DictionaryDaoImpl extends BaseDaoiBatis implements DictionaryDao {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Dictionary> queryList(Dictionary dictionary) throws DataAccessException {
		return getOracleSqlMapClientTemplate().queryForList("Dictionary.query_list",dictionary);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Cacheable(value = "baseCache",key="'dictionary'")
	public List<Dictionary> queryAllList(Dictionary dictionary) throws DataAccessException {
		return getOracleSqlMapClientTemplate().queryForList("Dictionary.query_list",dictionary);
	}

	@Override
	public int delete(Dictionary dictionary) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByID(Dictionary dictionary) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Dictionary dictionary) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
