package com.talent.measureservice.service;

import org.springframework.dao.DataAccessException;

public interface BcommonService {

	/**
	 * 生成matchid
	 *
	 * @param operatype
	 *            业务类型
	 * @return matchid
	 */
	public String getMatchid(String operatype) throws DataAccessException;

	/**
	 * 获取火车批次号
	 */
	public String getBatchcode() throws DataAccessException;

	/**
	 * 获取编码
	 */
	public String getCode(String types) throws DataAccessException;
}