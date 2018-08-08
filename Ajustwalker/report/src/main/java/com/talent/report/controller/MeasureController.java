package com.talent.report.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.report.model.ComboxData;
import com.talent.report.service.mapper.MeasureMapper;

@Controller 
public class MeasureController extends BaseController {

	@Resource
	private MeasureMapper measureMapper;

	/*
	 * 业务类型查询
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/queryOperatype.do")
	public List<ComboxData> queryOperatype(ComboxData combox) {
		return measureMapper.queryOperatype(combox);
	}
	/**
	 * 查询衡器信息
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/queryEquipment.do")
	public List<ComboxData> queryEquipment(ComboxData combox) {
		return measureMapper.queryEquipment(combox);
	}
	
	/*
	 * 物料查询
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/queryMaterial.do")
	public Message queryMaterial(ComboxData combox, Pagination<ComboxData> page) {
		return buildGridData(measureMapper.queryMaterial(combox));
	}
	/**
	 * 客商信息
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/queryCustomer.do")
	public Message queryCustomer(ComboxData combox, Pagination<ComboxData> page) {
		return buildGridData(measureMapper.queryStorename(combox));
	}

}
