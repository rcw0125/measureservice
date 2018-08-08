package com.talent.report.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.core.model.Message;
import com.talent.report.model.MeasureDetail;
import com.talent.report.model.ReportLog;
import com.talent.report.service.MeasureDetailService;
import com.talent.report.service.mapper.MeasureDetailMapper;

/*
 * @author:sll
 * @date :2016-09-20
 */
@Service
@Transactional
public class MeasureDetailServiceImpl implements MeasureDetailService {
	@Resource
	private MeasureDetailMapper mDeatialMapper;	

	/*
	 * 记录打印日志
	 */
	@Override
	public Message printLog(MeasureDetail measureDetail,ReportLog reportLog) {
		Message msg = new Message();
		try {
			//记录打印日志
			mDeatialMapper.printlog(reportLog);
		    if(reportLog.getTypes().equals("3")){
			 measureDetail.setMemo("P"+reportLog.getId());
			 //回写打印次数
			 mDeatialMapper.updateMeasurePrintCount(measureDetail);
		    }
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
}