package com.xgmes.service;

import org.springframework.dao.DataAccessException;

import com.talent.core.model.Message;
import com.xgmes.model.Quality;

public interface QualityService {
	

	/**
	 * 根据车号查询取样信息
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	public Message queryInfoBycarno(Quality q) throws DataAccessException;
	/**
	 * 保存前验证
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	
	public Message beforeinsertquality(Quality q) throws DataAccessException;
	/**
	 * 添加取样信息
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	public int  insertquality(Quality q) throws DataAccessException;

	/**
	 * 作废取样信息
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	int cancelquality(Quality q) throws DataAccessException;

}