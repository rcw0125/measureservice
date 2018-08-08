package com.xgmes.service;

import org.springframework.dao.DataAccessException;

import com.talent.core.model.Message;
import com.xgmes.model.Storein;

public interface StoreoutService {
	/**
	 * 根据车号查询出库信息
	 * @param storein
	 * @return
	 * @throws DataAccessException
	 */
	public Message queryInfoBycarno(Storein storein) throws DataAccessException;
	/**
	 * 添加出库信息
	 */

	Message insertStoreout(Storein storein) throws DataAccessException;

	/**
	 * 作废出库信息
	 */

	Message cancelStoreout(Storein storein) throws DataAccessException;

}