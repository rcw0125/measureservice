package com.talent.materialflow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.materialflow.model.ComboxData;
import com.talent.materialflow.model.TrainStatus;
import com.talent.materialflow.service.mapper.TrainStatusMapper;

@Controller
public class TrainStatusController extends BaseController{
	
	@Resource
	private TrainStatusMapper trainstatusMapper;
	
	@RequestMapping(value = "/trainstatus")
	public String trainstatus(ModelMap model) {
		return "trainstatus";
	}
	
	@ResponseBody
	@RequestMapping(value = "/trainstatus/list")
	public Message queryList(TrainStatus trainstatus, ModelMap model, Pagination<TrainStatus> page) {
		try {
			return buildGridData(trainstatusMapper.queryList(trainstatus));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(false, Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/trainstatus/delete")
	public Message del(TrainStatus trainstatus,ModelMap model) {
		try {
			trainstatusMapper.delete(trainstatus);
			return new Message();
		} catch (Exception e) {
			return new Message(false, Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
	
	/**
	 * 发到站信息
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/trainstatus/queryVstation")
	public Message queryVstation(ComboxData combox, Pagination<ComboxData> page) {
		return buildGridData(trainstatusMapper.queryVstation(combox));
	}

}
