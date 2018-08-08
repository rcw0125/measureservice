package com.talent.measureservice.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.talent.measureservice.model.TareLog;

public interface TareMapper {
	
	/**
	 * 通过车号查询日志
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public List<TareLog> getTarebyCarno (TareLog tlog) throws DataAccessException;
	
}
