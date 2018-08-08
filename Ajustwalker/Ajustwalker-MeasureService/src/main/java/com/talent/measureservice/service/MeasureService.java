package com.talent.measureservice.service;

import org.springframework.dao.DataAccessException;

import com.talent.measureservice.model.Measure;


public interface MeasureService {

	/**
	 * 保存计量信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	int insertMeasure(Measure measure) throws DataAccessException;

}