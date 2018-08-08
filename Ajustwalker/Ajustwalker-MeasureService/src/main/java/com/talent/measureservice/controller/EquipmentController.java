package com.talent.measureservice.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent.core.model.Message;
import com.talent.measureservice.model.WeighterData;
import com.talent.measureservice.service.mapper.WeighterDataMapper;

@Controller
public class EquipmentController {
	@Resource
	private WeighterDataMapper weighterMapper;
	
	/**
	 * 查询重量信息
	 * @param weight
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/equipment/queryWeighterlist.do")
	public Message queryWeighterlist(WeighterData weight) {
		Message msg = new Message();
		try {
			List<WeighterData> list =weighterMapper.queryWeighterlist(weight);
			msg.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("获取信息失败！"+e.getMessage());
		}
		weight=null;
		return msg;
	}
	/**
	 * 保存重量信息
	 * @param weight
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/equipment/insertWeightdata.do")
	public Message insertWeightdata(WeighterData weight) {
		Message msg = new Message();
		try {
			weighterMapper.insertWeightdata(weight);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("保存失败！"+e.getMessage());
		}
		weight=null;
		return msg;
	}

}
