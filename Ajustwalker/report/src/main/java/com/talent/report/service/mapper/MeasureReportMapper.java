package com.talent.report.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.report.model.MeasureReport;
import com.talent.report.model.Printlog;
import com.talent.report.model.TareLog;


public interface MeasureReportMapper {
	/**
	 * 查询明细报表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<MeasureReport> queryReportDetail(MeasureReport mreport) throws DataAccessException;
	
	public List<MeasureReport> queryReportLog(MeasureReport mreport) throws DataAccessException;
	
	public List<MeasureReport> queryReportSum(MeasureReport mreport) throws DataAccessException;
	
	public MeasureReport queryCount(MeasureReport mreport) throws DataAccessException;
	
	public MeasureReport queryLogCount(MeasureReport mreport) throws DataAccessException;
	
	public MeasureReport querySumCount(MeasureReport mreport) throws DataAccessException;
	
	/**
     * 皮重日志查询
     * @param m
     * @param pm
     * @return
     * @throws DataAccessException
     */
	public List<TareLog> queryTareloginfo(TareLog tlog) throws DataAccessException;
	

    /**
     * 皮重信息查询
     * @param m
     * @param pm
     * @return
     * @throws DataAccessException
     */
	public List<TareLog> queryTareinfo(TareLog tlog) throws DataAccessException;
	
	public List<Printlog> queryPrintinfo(Printlog printlog)throws DataAccessException;
	
	public int updateMeasurePrintCount(MeasureReport mreport) throws DataAccessException;

	
}





































