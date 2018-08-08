package com.xgmes.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.xgmes.mapper.MaterialMapper;
import com.xgmes.model.Material;
import com.xgmes.service.Material2Service;
import com.talent.core.privilege.model.User;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Material2Controller extends BaseController {

	@Autowired
	private Material2Service material2Service;
	@Resource
	private MaterialMapper materialMapper;
	
	@RequestMapping(value = "/material2")  
	public String init(ModelMap model) {
		return "material";
	}
	
	@ResponseBody
	@RequestMapping(value = "/material2/list")  
	public Message queryPage(Material material,ModelMap model,Pagination<Material> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(materialMapper.queryList(material));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/material2/edit")  
	public Message addorEdit(Material material,ModelMap model) {
		Message msg = new Message();
		try {
			msg = material2Service.insertMaterial(material);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
			}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/material2/delete")
	public Message del(Material material) {
		Message msg = new Message();
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			material.setCanceler(user.getDisplayname());
			materialMapper.cancelMaterial(material);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/material2/form")
	public Material queryInfoByMaterial(Material material,HttpServletRequest requst) {
		Material material2 = new Material();
		try {
			if (material.getId() != -1) {// 如果前面传递过来的id为-1，这说明是添加，则直接返回新对象sg
				material2 = materialMapper.queryInfoByMaterial(material);// 如果是修改，查询要修改的数据并赋值给对象sg
				if(material2.getMaterialcode().contains("TM"))
				{
					material2.setTypes(2);
				} else
				{
					material2.setTypes(1);
				}
			}
			return material2;
		} catch (Exception e) {
			System.out.println("异常信息为" + e.getMessage());
			return material2;
		}
	}
}
