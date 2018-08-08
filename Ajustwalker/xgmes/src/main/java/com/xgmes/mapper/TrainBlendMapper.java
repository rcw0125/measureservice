package com.xgmes.mapper;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.xgmes.model.Applicationbill;
import com.xgmes.model.Measure;

public interface TrainBlendMapper {


	/**
	 * 查询火车信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Measure> queryTrainBlendList(Measure measure) throws DataAccessException;


	/**
	 * 根据物流号查询勾兑信息
	 * 
	 * @param storein
	 * @return
	 * @throws DataAccessException
	 */
	public Measure queryTrainBlendBymatchid(Measure measure) throws DataAccessException;

	
	/**
	 * 修改勾兑信息
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int updateTrainBlend(Applicationbill app) throws DataAccessException;
	
	/**
	 * 查询火车信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryBlendInterList(Applicationbill app) throws DataAccessException;

	/**
	 * 修改火运单据状态
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int updatePlanStatus(Applicationbill app) throws DataAccessException;
	
}
