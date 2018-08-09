package com.talent.measure.dao;



import org.springframework.dao.DataAccessException;

import com.talent.measure.model.QualityInterface;

public interface QualityInterfaceDao {

	public void insertNoticeQualityData(QualityInterface qualityInterface);
	
	public void updateMeasureData(QualityInterface qualityInterface);
	
	public void insertInGate(QualityInterface qualityInterface);
	
	public void insertOutGate(QualityInterface qualityInterface);
	
	public void returnSampleFlag(QualityInterface qualityInterface);
	
	public QualityInterface queryMeasureInfoReturnQA(String matchid);
	
	public void cancelSampleFlag(QualityInterface qualityInterface);
	/**
	 * 撤销取样信息
	 * @param matchid
	 */
	public void updateSatus(QualityInterface qualityInterface);
	/**
	 * 根据车号和计量时间获取线材净重
	 * @param qualityInterface
	 * @return
	 */
	public String queryWireValue(QualityInterface qualityInterface) throws DataAccessException;
}