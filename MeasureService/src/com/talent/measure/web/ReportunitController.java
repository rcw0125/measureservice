package com.talent.measure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.measure.dao.ReportunitDao;
import com.talent.measure.model.Reportunit;

@Controller
public class ReportunitController {

	@Autowired
	private ReportunitDao reportunitDao;

	/**
	 * 查询单位
	 * 
	 * @param equipment
	 * @param pm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reportunit/queryReportunit.do")
	public Message queryReportunit(Reportunit re, PageModel pm) {
		Message msg = new Message();
		try {
			pm = reportunitDao.queryReportunit(re, pm);
			msg.setTotal(pm.getAllcount());
			msg.setRows(pm.getList());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/*
	 * 添加、修改单位
	 */
	@ResponseBody
	@RequestMapping(value = "/reportunit/insertEquiment.do")
	public Message insertHandover(@CookieValue(value = "username", defaultValue = "") String username, Reportunit re) {
		Message msg = new Message();
		int i = 0;
		try {
			re.setCreateman(username);
			int m = reportunitDao.queryCount(re);
			if (0==re.getId()) {
				if (m > 0) {
					msg.setSuccess(false);
					msg.setMsg("单位已经存在！");
				} else {
					i = reportunitDao.insertReportunit(re);
					if (i > 0) {
						msg.setMsg("操作成功！");
					} else {
						msg.setSuccess(false);
						msg.setMsg("操作失败！");
					}
				}
			} else {
				if (m > 1) {
					msg.setSuccess(false);
					msg.setMsg("单位已经存在！");
				} else {
					i = reportunitDao.updateReportunit(re);
					if (i > 0) {
						msg.setMsg("操作成功！");
					} else {
						msg.setSuccess(false);
						msg.setMsg("操作失败！");
					}
				}
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 作废单位
	 * 
	 * @param h
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reportunit/cancelReportunit.do")
	public Message cancelReportunit(@CookieValue(value = "username", defaultValue = "") String username, Reportunit re) {

		Message msg = new Message();
		int i = 0;
		try {
			re.setCreateman(username);
			i = reportunitDao.cancelReportunit(re);
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

	@ResponseBody
	@RequestMapping(value = "/reportunit/queryReportunitByid.do")
	public Reportunit queryInfo(Reportunit re) {
		Message msg = new Message();
		try {
			if (-1==re.getId()) {
				re = new Reportunit();
			} else {
				re = reportunitDao.queryReportunitByid(re);
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return re;
	}

}
