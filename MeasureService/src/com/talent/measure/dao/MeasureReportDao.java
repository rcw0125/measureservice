package com.talent.measure.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.base.model.PageModel;
import com.talent.measure.model.MeasureReport;
import com.talent.measure.model.PrintLog;
import com.talent.measure.model.TareLog;


public interface MeasureReportDao {

	/*
	 * 明细报表查询
	 */
	PageModel queryReportDetail(MeasureReport m, PageModel pm) throws DataAccessException;
	/**
	 * 汇总报表查询
	 * @param m
	 * @param pm
	 * @return
	 * @throws DataAccessException
	 */
	PageModel queryReportSum(MeasureReport m, PageModel pm) throws DataAccessException;
	/**
	 * 根据物流号查询打印日志
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public List<PrintLog> queryPrintinfo(String matchid)throws DataAccessException;
	/*
	 * 日志报表查询
	 */

	public PageModel queryReportLog(MeasureReport m, PageModel pm) throws DataAccessException;
	
	
	 /**
     * 皮重日志查询
     * @param m
     * @param pm
     * @return
     * @throws DataAccessException
     */
	public PageModel queryTareloginfo(TareLog tlog, PageModel pm) throws DataAccessException ;
	

    /**
     * 皮重信息查询
     * @param m
     * @param pm
     * @return
     * @throws DataAccessException
     */
	public PageModel queryTareinfo(TareLog tlog, PageModel pm) throws DataAccessException;
	/**
	 * 查询异常修改信息
	 * @param m
	 * @param pm
	 * @return
	 * @throws DataAccessException
	 */
	public PageModel queryReportException(MeasureReport m, PageModel pm) throws DataAccessException;
}