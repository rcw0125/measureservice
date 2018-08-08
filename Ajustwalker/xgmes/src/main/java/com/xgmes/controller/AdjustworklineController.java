package com.xgmes.controller;




import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.xgmes.mapper.AdjustworklineMapper;
import com.xgmes.mapper.BCommonMapper;
import com.xgmes.model.Applicationbill;
import com.xgmes.model.WorklineItem;
import com.xgmes.service.ApplicationbillService;
import com.xgmes.service.BCommonService;
import com.xgmes.service.ExceptionService;
@Controller
public class AdjustworklineController extends BaseController {

	@Resource
	private AdjustworklineMapper adjustMapper;
	@Resource
	private ApplicationbillService appService;
	@Resource
	private BCommonMapper bcommonMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private ExceptionService exceptionService;

	@RequestMapping(value = "/application/adjustworkline")
	public String makecardcg(Applicationbill app,ModelMap model) {
		model.addAttribute("operatype", app.getOperatype());
		return "adjustworkline";
	}

	/**
	 * 查询正在进行的业务信息
	 * @param app
	 * @param model
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/adjustworkline/queryPage.do")
	public Message queryPage(Applicationbill app, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(adjustMapper.queryList(app));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/adjustworkline/queryNodename.do")
	public Message queryNodename(WorklineItem app, ModelMap model) {
		Message msg = new Message();
		try {
			msg.setRows(adjustMapper.queryNodename(app));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/adjustworkline/insertWorkline.do")  
	public Message addorEditItem(WorklineItem worklineItem,ModelMap model) {
		Message msg = new Message();
		try{
			exceptionService.insertAdjustWorkline(worklineItem);
		}catch (Exception e) {	
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！"+e.getMessage());
		}
		return msg; 
	}

}
