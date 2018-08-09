package com.talent.measure.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.measure.dao.HandOverDao;

import com.talent.measure.model.HandOver;



@Controller

public class HandOverController {
	
	@Autowired
	private HandOverDao hoverDao;
	
	@ResponseBody
	@RequestMapping(value = "/handover/queryHandover.do")
	public Message queryHandover(HandOver m, PageModel pm) {
		Message msg = new Message();
		try {
			pm = hoverDao.queryHandover(m, pm);
			msg.setTotal(pm.getAllcount());
			msg.setRows(pm.getList());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}
	
	/*
	 * 操作交接班台账：添加 、修改
	 */
	@ResponseBody
	@RequestMapping(value = "/handover/insertHandover.do")
	public Message insertHandover(@CookieValue(value="username",defaultValue="")String username,HandOver h) {
	
		Message msg = new Message();
		int i = 0;
		try {
			h.setCreateman(username);
			i = hoverDao.insertHandover(h);
			if (i > 0) {
				msg.setMsg("操作成功！");
			} else {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 作废交接班台账
	 * @param h
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/handover/cancelHandover.do")
	public Message cancelHandover(@CookieValue(value="username",defaultValue="")String username,HandOver h) {
	
		Message msg = new Message();
		int i = 0;
		try {
			h.setCancelman(username);
			i = hoverDao.cancelHandover(h) ;
			if (i > 0) {
				msg.setMsg("操作成功！");
			} else {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	// 根据物流号查询修改的数据信息
		@ResponseBody
		@RequestMapping(value = "/handover/queryHandoverByid.do")
		public HandOver queryInfo(HandOver h) {

			Message msg = new Message();
			
			try {
				h = hoverDao.queryHandoverByid(h);
			} catch (Exception e) {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}

			return h;
		}


}
