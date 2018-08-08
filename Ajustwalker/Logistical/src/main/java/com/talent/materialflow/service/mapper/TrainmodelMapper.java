package com.talent.materialflow.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.materialflow.model.Trainmodel;

public interface TrainmodelMapper {
	
	public List<Trainmodel> queryList(Trainmodel tm) throws DataAccessException;

	/**
	 * 添加
	 */
	public int insertTrainmodel(Trainmodel trainmodel) throws DataAccessException;

	/**
	 * 修改时，查询信息
	 */
	public Trainmodel queryInfoByID(Trainmodel trainmodel) throws DataAccessException;

	/**
	 * 修改
	 */
	public int updateTrainmodel(Trainmodel trainmodel) throws DataAccessException;

	/**
	 * 作废
	 */
	public int cancelTrainmodel(Trainmodel trainmodel) throws DataAccessException;


}
