package com.talent.measureservice.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent.core.model.Message;
import com.talent.measureservice.model.ComboxData;
import com.talent.measureservice.service.mapper.BcommonMapper;
@Controller
public class BCommonController {
	
	@Resource
	private BcommonMapper bcommonMapper;
	
	/**
	 * 参数物料编码或者名称  searchText
	 * @param box
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommonController/queryMateril")
	public Message queryMateril(ComboxData box) {

		Message msg = new Message();
		try {
			List<ComboxData> list =bcommonMapper.queryMateril(box);
			msg.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("查询物料信息失败！"+e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 参数供货编码或者名称  searchText
	 * @param box
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommonController/querySourceinfo")
	public Message querySourceinfo(ComboxData box) {

		Message msg = new Message();
		try {
			List<ComboxData> list =bcommonMapper.querySourceinfo(box);
			msg.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("查询供货信息失败！"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 参数收货编码或者名称  searchText
	 * @param box
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommonController/queryTargetinfo")
	public Message queryTargetinfo(ComboxData box) {

		Message msg = new Message();
		try {
			List<ComboxData> list =bcommonMapper.queryTargetinfo(box);
			msg.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("查询收货信息失败！"+e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 参数站点编码或者名称  searchText
	 * @param box
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommonController/queryStation")
	public Message queryStation(ComboxData box) {

		Message msg = new Message();
		try {
			List<ComboxData> list =bcommonMapper.queryStation(box);
			msg.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("查询物料信息失败！"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 参数站点编码或者名称  searchText
	 * @param box
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommonController/queryOperatype")
	public Message queryOperatype(ComboxData box) {

		Message msg = new Message();
		try {
			List<ComboxData> list =bcommonMapper.queryOperatype(box);
			msg.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("查询物料信息失败！"+e.getMessage());
		}
		return msg;
	}
	
}
