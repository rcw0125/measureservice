package com.talent.materialflow.service;

import org.springframework.dao.DataAccessException;

import com.talent.core.model.Message;
import com.talent.materialflow.model.Applicationbill;

public interface ApplicationbillService {

	/**
	 * 添加制卡信息
	 */
	Message insertApplicationbill(Applicationbill app) throws DataAccessException;

	/**
	 * 修改制卡信息
	 */

	public Message updateApplicationbill(Applicationbill app) throws DataAccessException;

	/**
	 * 添加其他单据信息
	 */

	public Message insertDocument(Applicationbill app)throws DataAccessException;

	/**
	 * 修改制卡信息
	 */

	public Message updateDocument(Applicationbill app)throws DataAccessException;

	/**
	 * 作废其他单据
	 */

	public Message cancelDocument(Applicationbill app) throws DataAccessException;

	/**
	 * 作废制卡信息
	 */

	public Message cancelApplicationbill(Applicationbill app) throws DataAccessException;
	


}