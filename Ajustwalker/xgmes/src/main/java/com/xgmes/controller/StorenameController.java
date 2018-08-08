package com.xgmes.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.xgmes.mapper.StorenameMapper;
import com.xgmes.model.Storename;
import com.xgmes.service.StorenameService;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StorenameController extends BaseController {

	@Autowired
	private StorenameService storenameService;
	@Resource
	private StorenameMapper storenameMapper;
	
	@RequestMapping(value = "/storename")  
	public String init(ModelMap model) {
		return "storename";
	}
	
	@ResponseBody
	@RequestMapping(value = "/storename/list")  
	public Message queryPage(Storename storename,ModelMap model,Pagination<Storename> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(storenameMapper.queryList(storename));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/storename/edit")  
	public Message addorEdit(Storename storename,ModelMap model) {
		Message msg = new Message();
		try {
			msg = storenameService.insertStorename(storename);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
			}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/storename/delete")
	public Message del(Storename storename,ModelMap model) {
		Message msg = new Message();
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			storename.setCanceler(user.getDisplayname());
			storenameMapper.cancelStorename(storename);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/storename/form")
	public Storename loadForm(Storename storename) {
		Storename sn = new Storename();
		try {
			if (storename.getId() != -1) {// 如果前面传递过来的id为-1，这说明是添加，则直接返回新对象sg
				sn = storenameMapper.queryInfoByStorename(storename);// 如果是修改，查询要修改的数据并赋值给对象sg
			}
			return sn;
		} catch (Exception e) {
			System.out.println("异常信息为" + e.getMessage());
			return sn;
		}
	}
}
