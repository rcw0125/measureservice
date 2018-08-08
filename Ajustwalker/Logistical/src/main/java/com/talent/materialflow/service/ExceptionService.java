package com.talent.materialflow.service;

import org.springframework.dao.DataAccessException;

import com.talent.core.model.Message;
import com.talent.materialflow.model.Applicationbill;
import com.talent.materialflow.model.Entrylog;
import com.talent.materialflow.model.Measure;
import com.talent.materialflow.model.WorklineItem;

public interface ExceptionService {

	/**
	 * 添加计量异常信息
	 *	 
	 */
	Message insertMeasureException(Measure measure) throws DataAccessException;
	/*
	 * 添加出厂信息
	 * 
	 */
	public Message insertExceptionOut(Entrylog entry) throws DataAccessException;

	/**
	 * 厂内调拨异常操作
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public Message insertExceptionDB(Measure measure) throws DataAccessException ;
	
	/**
	 * 虚拟制卡信息
	 */

	public Message insertVApplicationbill(Applicationbill app) throws DataAccessException;
	
	/**
	 * 作废虚拟制卡信息
	 */

	public Message cancelVApplicationbill(Applicationbill app) throws DataAccessException ;
	/**
	 * 作废厂内信息
	 */

	public Message cancelExceptionDB(Applicationbill app) throws DataAccessException;
	
	/**
	 * 作废信息
	 */

	public Message cancelAllinfo(Applicationbill app) throws DataAccessException;
	/**
	 * 作废皮重信息
	 */

	public int cancelTareException(String carno) throws DataAccessException;
	/**
	 * 修改皮重信息
	 */

	public int updateTareException(Measure measure) throws DataAccessException;
	/**
	 * 作废线材信息
	 */

	public int cancelExceptionXC(Applicationbill app) throws DataAccessException;
	/**
	 * 修改扣重信息
	 */

	public Message updateDeducation(Measure measure) throws DataAccessException;
	/**
	 * 作废计量信息
	 */

	public Message updateCurrMeasure(Applicationbill app) throws DataAccessException;
	/**
	 * 异常数据操作火车信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int insertHcException(Measure measure)throws DataAccessException;
	/**
	 * 路线临时调整
	 * 
	 * @param worklineItem
	 * @return 
	 * @throws DataAccessException
	 */
	public int insertAdjustWorkline(WorklineItem worklineItem) throws DataAccessException;
}