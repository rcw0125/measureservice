package com.talent.materialflow.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.materialflow.model.ComboxData;
import com.talent.materialflow.model.TrainStatus;

public interface TrainStatusMapper {
	
	/**
	 * 查询
	 */
	public List<TrainStatus> queryList(TrainStatus ts) throws DataAccessException;
	
	/**
	 * 删除
	 */
	public int delete(TrainStatus ts) throws DataAccessException;
	
	public List<ComboxData> queryVstation(ComboxData combox) throws DataAccessException;

}
