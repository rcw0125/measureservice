package com.talent.report.service.mapper;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.talent.report.model.ComboxData;
import com.talent.report.model.MeasureDetail;
import com.talent.report.model.ReportLog;

public interface MeasureDetailMapper {
	
	/**
	 * 查询计量明细报表
	 * 
	 * @param mdetail
	 * @return
	 * @throws DataAccessException
	 */
	public List<MeasureDetail> queryList(MeasureDetail mdetail) throws DataAccessException;
	
	public MeasureDetail queryCount(MeasureDetail mdetail) throws DataAccessException;
	/**
	 * 明细汇总
	 * 
	 * @param mdetail
	 * @return
	 * @throws DataAccessException
	 */
	public List<MeasureDetail> queryReportSum(MeasureDetail mdetail) throws DataAccessException;
	
	/*
	 * 查询衡器集合
	 * 
	 */

	public List<ComboxData> queryEquipment(ComboxData combox) throws DataAccessException;
	
	/*
	 * 记录打印日志
	 * 
	 */
	public int printlog(ReportLog reportLog) throws DataAccessException;
	/*
	 * 更新计量表打印次数及打印序列
	 */
	public int updateMeasurePrintCount(MeasureDetail mdetail) throws DataAccessException;
}





































