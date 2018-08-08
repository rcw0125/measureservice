package com.xgmes.controller;


import javax.annotation.Resource;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.xgmes.mapper.BillMapper;
import com.xgmes.model.Interface;
import com.xgmes.service.BCommonService;
import com.xgmes.service.BillService;
@Controller
public class BillController extends BaseController {


	@Resource
	private BillMapper billMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private BillService billService;

	@RequestMapping(value = "/bill/managebill")
	public String makecardcg(ModelMap model,Interface inter) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		model.addAttribute("typeflag",inter.getTypeflag());
		model.addAttribute("reserve1",inter.getReserve1());
		return "Bill";
	}


	@ResponseBody
	@RequestMapping(value = "/bill/queryPage.do")
	public Message queryPage(Interface inter, ModelMap model, Pagination<Interface> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(billMapper.queryBillList(inter));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		inter=null;
		return msg;
	}


	@ResponseBody
	@RequestMapping(value = "/bill/queryMainBymatchid.do")
	public Interface queryMainBymatchid(Interface inter) {
		if (!"-1".equals(inter.getPlanid())) {
			inter = billMapper.queryMainByPlanid(inter);
		}
		return inter;
	}
   
	@ResponseBody
	@RequestMapping(value = "/bill/queryItemByFid.do")
	public Message queryItemByFid(Interface inter) {
		Message msg = new Message();
		if (!"-1".equals(inter.getPlanid())) {
			msg.setRows(billMapper.queryItemByFid(inter));
		}
		inter=null;
		return msg;
	}


	/**
	 * 添加信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bill/insertBill.do", method = RequestMethod.POST)
	public Message insertBill(Interface inter) {

		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			inter.setCreator(user.getDisplayname());
			if ("-1".equals(inter.getPlanid())) {
				msg = billService.insertBill(inter);
			} else {
				msg = billService.updateBill(inter);
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		inter=null;
		return msg;
	}

	/**
	 * 作废制卡信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bill/cancelBill.do")
	public Message cancelBill(Interface inter) {
		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			inter.setCreator(user.getDisplayname());
			msg = billService.cancelBill(inter);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		inter=null;
		return msg;
	}


}
