package com.talent.report.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.model.Message;
import com.talent.core.controller.BaseController;
import com.talent.report.model.Taskinfo;
import com.talent.report.service.mapper.StatisticsReportMapper;

@Controller
public class StatisticsReportController extends BaseController {
	
	@Resource
	private StatisticsReportMapper statisticsReportMapper;
	
	@ResponseBody
	@RequestMapping(value = "/StatisticsReport/queryavgtaskinfo.do")
	public Taskinfo queryavgtaskinfo(Taskinfo tinfo) {

		Taskinfo taskinfo = null;
		List<Taskinfo> list = null;
		try {
			taskinfo = statisticsReportMapper.queryavgtaskinfo(tinfo);
			list = statisticsReportMapper.querytaskinfo(tinfo);
			taskinfo.setList(list);

		} catch (Exception e) {
			taskinfo = new Taskinfo();
		}

		return taskinfo;
	}

	@ResponseBody
	@RequestMapping(value = "/StatisticsReport/querytaskdata.do")
	public Message querytaskdata(Taskinfo tinfo, ModelMap model) {
		try {
			return buildGridData(statisticsReportMapper.querytaskdata(tinfo));
		} catch (Exception e) {
			return new Message(false, Message.FAILURE_MESSAGE + e.getMessage());
		}

	}
}
