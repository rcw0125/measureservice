package com.talent.measure.dao.impl;

import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.base.model.PageModel;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.MeasureReportDao;
import com.talent.measure.model.MeasureReport;
import com.talent.measure.model.PrintLog;
import com.talent.base.model.Summary;
import com.talent.measure.model.TareLog;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Service
@Component
@SuppressWarnings({ "deprecation", "unchecked" })
public class MeasureReportDaoImpl extends BaseDaoiBatis implements MeasureReportDao {

	/*
	 * 明细报表查询
	 */

	@Override
	public PageModel queryReportDetail(MeasureReport m, PageModel pm) throws DataAccessException {
		try {
			Summary summary = (Summary)getOracleSqlMapClientTemplate().queryForObject("measureReport.queryReportDetail_summary", m);
			pm.setSummary(summary);
			pm.setAllcount(summary.getCount());
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("measureReport.queryReportDetail", m, pm.getStart(),
					pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	/**
	 * 查询异常修改信息
	 * @param m
	 * @param pm
	 * @return
	 * @throws DataAccessException
	 */
	public PageModel queryReportException(MeasureReport m, PageModel pm) throws DataAccessException {
		try {
			Summary summary = (Summary)getOracleSqlMapClientTemplate().queryForObject("measureReport.queryReportException_summary", m);
			pm.setSummary(summary);
			pm.setAllcount(summary.getCount());
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("measureReport.queryReportException", m, pm.getStart(),
					pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
   /**
    * 汇总报表查询
    */
	public PageModel queryReportSum(MeasureReport m, PageModel pm) throws DataAccessException {
		try {
			Summary summary = (Summary)getOracleSqlMapClientTemplate().queryForObject("measureReport.queryReportSum_summary", m);
			pm.setSummary(summary);
			pm.setAllcount(summary.getCount());
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("measureReport.queryReportSum", m, pm.getStart(),pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	/**
	 * 根据物流号查询打印日志
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public List<PrintLog> queryPrintinfo(String matchid)throws DataAccessException {
		return (List<PrintLog>) getOracleSqlMapClientTemplate().queryForList("measureReport.queryPrintinfo",matchid);
	}
	
	/*
	 * 日志报表查询
	 */

	@Override
	public PageModel queryReportLog(MeasureReport m, PageModel pm) throws DataAccessException {
		try {
			Summary summary = (Summary)getOracleSqlMapClientTemplate().queryForObject("measureReport.queryReportLog_summary", m);
			pm.setSummary(summary);
			pm.setAllcount(summary.getCount());
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("measureReport.queryReportLog", m, pm.getStart(),
					pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}


    /**
     * 皮重日志查询
     * @param m
     * @param pm
     * @return
     * @throws DataAccessException
     */
	public PageModel queryTareloginfo(TareLog tlog, PageModel pm) throws DataAccessException {
		try {
			Summary summary = (Summary)getOracleSqlMapClientTemplate().queryForObject("measureReport.queryTareloginfo_count", tlog);

			pm.setAllcount(summary.getCount());
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("measureReport.queryTareloginfo", tlog, pm.getStart(),
					pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	

    /**
     * 皮重信息查询
     * @param m
     * @param pm
     * @return
     * @throws DataAccessException
     */
	public PageModel queryTareinfo(TareLog tlog, PageModel pm) throws DataAccessException {
		try {
			Summary summary = (Summary)getOracleSqlMapClientTemplate().queryForObject("measureReport.queryTareinfo_count", tlog);

			pm.setAllcount(summary.getCount());
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("measureReport.queryTareinfo", tlog, pm.getStart(),
					pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
}
