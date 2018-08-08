package com.xgmes.service;

import org.springframework.dao.DataAccessException;

import com.talent.core.model.Message;
import com.xgmes.model.Storein;

public interface StoreinService {
	Message queryInfoBycarno(Storein storein) throws DataAccessException;

	/**
	 * 添加入库信息
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */

	Message insertStorein(Storein storein) throws DataAccessException;

	/**
	 * 作废入库信息
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */

	Message cancelStorein(Storein storein) throws DataAccessException;

}