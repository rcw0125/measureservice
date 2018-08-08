package com.xgmes.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xgmes.model.Storein;

public interface StoreoutMapper {

	public List<Storein> queryList(Storein storein) throws DataAccessException;
    

	public Storein queryCount(Storein storein) throws DataAccessException;
	
	public List<Storein> queryInfoBycarno(Storein storein) throws DataAccessException;

	public int insertStoreout(Storein storein) throws DataAccessException;

	public int insertStoreoutitem(Storein storein) throws DataAccessException;
	
	public int updateApplicationitem(Storein storein) throws DataAccessException;
	
	public int updateCurrtemp(Storein storein) throws DataAccessException;

	public int cancelStoreout(Storein storein) throws DataAccessException;

	public int cancelStoreoutitem(Storein storein) throws DataAccessException;
	
	public int queryCountBymatchid(Storein storein) throws DataAccessException;
	

	public Storein queryInfoBymatchid(Storein storein) throws DataAccessException;
	/**
	 * 查询非本作业点的该车的作业点编码
	 * @param carno
	 * @return
	 * @throws DataAccessException
	 */
	public String queryInfoBycarnoNotcode(String carno) throws DataAccessException;	

}
