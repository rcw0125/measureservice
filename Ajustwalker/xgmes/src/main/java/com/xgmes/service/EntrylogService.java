package com.xgmes.service;

import org.springframework.dao.DataAccessException;

import com.talent.core.model.Message;
import com.xgmes.model.Applicationbill;
import com.xgmes.model.Entrylog;

public interface EntrylogService {

	/**
	 * 查询进出厂信息
	 * @param card
	 * @return
	 * @throws DataAccessException
	 */

	Message queryInList(Entrylog elog) throws DataAccessException;
	public Message queryNewInList(Entrylog elog) throws DataAccessException;
	/* 
	 * 查询出厂车辆信息    
	 */
	
	public Message queryOutList(Entrylog elog) throws DataAccessException ;
	
	public Message queryNewOutList(Entrylog elog) throws DataAccessException ;

	/**
	 * 添加进厂信息
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */

	Message insertInEntrylog(Entrylog entry) throws DataAccessException;
	/**
	 * 添加出厂信息
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public Message insertOutEntrylog(Entrylog entry) throws DataAccessException;
	
	/**
	 * 判断卡状态
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public Message queryCartype(Entrylog entry) throws DataAccessException;
	
	/**
	 * 添加线材信息
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public Message insertXCtemp(Applicationbill app) throws DataAccessException;
	
	

}