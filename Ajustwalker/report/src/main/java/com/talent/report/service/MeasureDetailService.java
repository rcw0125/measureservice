package com.talent.report.service;

import org.springframework.dao.DataAccessException;

import com.talent.core.model.Message;
import com.talent.report.model.MeasureDetail;
import com.talent.report.model.ReportLog;

/*
 * @author:sll
 * @date :2016-09-20
 */
public interface MeasureDetailService {

	/*
	 * 记录打印日志
	 */
	Message printLog(MeasureDetail measureDetail,ReportLog reportLog) throws DataAccessException;

}