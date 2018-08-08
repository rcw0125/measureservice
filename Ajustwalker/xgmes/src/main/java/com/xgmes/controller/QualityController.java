package com.xgmes.controller;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.xgmes.mapper.BCommonMapper;
import com.xgmes.mapper.QualityMapper;
import com.xgmes.model.Quality;
import com.xgmes.service.BCommonService;
import com.xgmes.service.QualityService;

@Controller
public class QualityController extends BaseController {

	@Resource
	private QualityMapper qualitMapper;
	@Resource
	private QualityService qservice;
	@Resource
	private BCommonMapper bcommonMapper;
	@Resource
	private BCommonService bcommonService;
    
	/**
	 * 取样
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/quality/quality")
	public String storeout(ModelMap model,Quality q) {
		model.addAttribute("unitcode", q.getUnitcode());
		model.addAttribute("unitname", q.getUnitname());
		return "quality";
	}
	
	@ResponseBody
	@RequestMapping(value = "/quality/queryPage.do")
	public Message queryPage(Quality q, ModelMap model, Pagination<Quality> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(qualitMapper.queryList(q));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	// 查询可以修改的信息列表
	@ResponseBody
	@RequestMapping(value = "/quality/queryQuality.do")
	public Quality queryQuality(Quality qa) {
		/*Quality qa = new Quality();
		qa.setQmpostion(unitname);
		qa.setQmpostioncode(unitcode);*/
		return qa;
	}

	/**
	 * 通过车号查询取样信息，暂时查询所有的信息，需要根据作业路线上是否有取样点，查询信息
	 * 
	 * @param q
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/quality/queryInfoBycarno.do")
	public Message queryInfoBycarno(Quality q, ModelMap model) {
		Message msg = new Message();
		try {
		   msg.setRows(qualitMapper.queryInfoBycarno(q)); 
		
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 取样信息保存前验证
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/quality/beforeinsertquality.do")
	public Message beforeinsertquality(Quality q) {
		Message msg = new Message();
		
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			/*Currtemp curr = new Currtemp();
			curr.setMatchid(q.getMatchid());
			curr.setUnitcode(q.getUnitcode());
			curr.setUnitname(q.getUnitname());
			msg = bcommonService.beforeinsert(curr, "SP");//qservice.beforeinsertquality(q);
			if ("0".equals(msg.getMfunc())) {// 如果返回信息为0，直接保存信息
				q.setCreateoperacode(user.getUsername());
				q.setCreator(user.getDisplayname());
				qservice.insertquality(q);
			}*/
			msg = bcommonService.queryUpNode(q.getMatchid(), "SP");
			if (msg.isSuccess()) {// 如果返回信息为0，直接保存信息
				q.setCreateoperacode(user.getUsername());
				q.setCreator(user.getDisplayname());
				qservice.insertquality(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 添加取样信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/quality/insertquality.do")
	public Message insertquality(Quality q) {
		Message msg = new Message();
		
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			q.setCreateoperacode(user.getUsername());
			q.setCreator(user.getDisplayname());
			qservice.insertquality(q);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 作废取样信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/quality/cancelquality.do")
	public Message cancelquality(Quality q) {
		Message msg = new Message();
		
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			q.setCreateoperacode(user.getUsername());
			q.setCreator(user.getDisplayname());
			qservice.cancelquality(q);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

}
