package com.talent.measureservice.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.measureservice.model.Measure;
import com.talent.measureservice.model.TrainMeasure;
import com.talent.measureservice.model.WeightBak;

public interface MeasureMapper {
	
	/**
	 * 通过车号查询火车业务信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public List<TrainMeasure> queryTraininfo (TrainMeasure measure) throws DataAccessException;
	
	/**
	 * 通过车号查询火车计划信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public List<TrainMeasure> queryTraininfoPlan (TrainMeasure measure) throws DataAccessException;
	
	/**
	 * 添加业务临时表中
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertCurrtemp(Measure measure) throws DataAccessException;

	/**
	 * 根据车号查询临时表是否存在数据
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int queryMeasureTempCount(Measure measure) throws DataAccessException;
	
	/**
	 * 修改业务临时表中
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateMeasureTemp(Measure measure) throws DataAccessException;

	/**
	 * 作废业务临时表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int cancelCurrtemp(Measure measure) throws DataAccessException;

	/**
	 * 修改业务临时表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateCurrtemp(Measure measure) throws DataAccessException;
	
	/**
	 * 添加计量表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertMeasure(Measure measure) throws DataAccessException;
	
	/**
	 * 修改计量表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateMeasure(Measure measure) throws DataAccessException;
	
	/**
	 * 添加计量日志表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertMeasureLog(Measure measure) throws DataAccessException;
	
	/**
	 * 通过车号查询在皮重表中是否存在
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int selectTareBycarno(Measure measure) throws DataAccessException;
	/**
	 * 添加皮重表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertTare(Measure measure) throws DataAccessException;
	
	/**
	 * 修改皮重表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateTare(Measure measure) throws DataAccessException;
	
	/**
	 * 添加皮重日志表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertTareLog(Measure measure) throws DataAccessException;
	
	/**
	 * 保存计量信息临时表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertMeasureTemp(Measure measure) throws DataAccessException;
	
	/**
	 * 通过车号查询临时表中的数据信息  通过车号查询的原因是在临时表中可能存在其他车辆的信息，可否使用衡器id查询
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public List<Measure> queryMeasureTemp(Measure measure) throws DataAccessException;
	
	/**
	 * 作废火车计量信息临时表，通过衡器id作废临时信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int deleteMeasureTemp(Measure measure) throws DataAccessException;
	
	/**
	 * 作废火车计量信息临时表，通过车号作废临时信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int deleteMeasureTempBycarno(Measure measure) throws DataAccessException;
	
	/**
	 * 
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertWeightbak(WeightBak weightbak) throws DataAccessException;
	/**
	 * 查询计量是否存在
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int queryMeasureCount(Measure measure) throws DataAccessException;
	
	/**
	 * 查询计量是否存在
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCurrCount(Measure measure) throws DataAccessException;
	
}
