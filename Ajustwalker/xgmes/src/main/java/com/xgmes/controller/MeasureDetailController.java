package com.xgmes.controller;




import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.xgmes.mapper.MeasureReportMapper;
import com.xgmes.model.Measure;


@Controller
public class MeasureDetailController extends BaseController {

	@Resource
	private MeasureReportMapper measureMapper;

	
	/**
	 * 明细查询
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/measureReporttext/measurereportdetail")
	public String card(ModelMap model) {
		return "report/measurereportdetail";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/measureReport1234/queryReportDetail.do")
	public Message queryPage(Measure measure, ModelMap model, Pagination<Measure> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(measureMapper.queryMeasureDetail(measure));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
}
