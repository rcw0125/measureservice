package com.xgmes.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xgmes.model.Measure;

public interface MeasureReportMapper {
	/**
	 * 查询打印报表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Measure> queryMeasureDetail(Measure measure) throws DataAccessException;

	
}
