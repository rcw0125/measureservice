package com.talent.base.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.aladdin.dao.ibatis.AbstractMSSQLBaseDao;
import com.aladdin.dao.ibatis.AbstractMSSQLBaseDao2;
import com.aladdin.dao.ibatis.AbstractOracleBaseDao;

@SuppressWarnings("deprecation")
public class BaseDaoiBatis{
	
	@Resource(name="abstractOracleBaseDao")
	private AbstractOracleBaseDao abstractOracleBaseDao;
	
	@Resource(name="abstractMSSQLBaseDao")
	private AbstractMSSQLBaseDao abstractMSSQLBaseDao;
	
	@Resource(name="abstractMSSQLBaseDao2")
	private AbstractMSSQLBaseDao2 abstractMSSQLBaseDao2;
	
    public SqlMapClientTemplate  getOracleSqlMapClientTemplate(){
    	return abstractOracleBaseDao.getSqlMapClientTemplate();
    }
    
    public SqlMapClientTemplate  getMSSQLSqlMapClientTemplate(){
    	return abstractMSSQLBaseDao.getSqlMapClientTemplate();
    }
    
    public SqlMapClientTemplate  getMSSQLSqlMapClientTemplate2(){
    	return abstractMSSQLBaseDao2.getSqlMapClientTemplate();
    }
}