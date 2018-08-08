package com.talent.materialflow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.materialflow.model.Cheatlog;
import com.talent.materialflow.service.mapper.CheatlogMapper;

@Controller
public class CheatlogController extends BaseController {
	
	@Resource
	private CheatlogMapper cheatlogMapper;
	
	@RequestMapping(value = "/cheatlog")  
	public String init(ModelMap model) {
		return "cheatlog";
	}
	
	@ResponseBody
	@RequestMapping(value = "/cheatlog/list")
	public Message queryList(Cheatlog cheatlog,ModelMap model,Pagination<Cheatlog> page) {
		try {
			return buildGridData(cheatlogMapper.queryList(cheatlog));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(false, Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/unauth/cheatlog/insert")
	public Message insertCheatlog(Cheatlog cheatlog) {
		Message msg = new Message();
		try {
			cheatlogMapper.insertCheatlog(cheatlog);					
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

}
