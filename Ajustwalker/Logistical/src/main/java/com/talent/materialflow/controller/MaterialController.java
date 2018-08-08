package com.talent.materialflow.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.model.QueryParams;
import com.talent.materialflow.model.Material;
import com.talent.materialflow.service.MaterialService;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MaterialController extends BaseController {

	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value = "/material")  
	public String init(ModelMap model) {
		return "material";
	}
	
	@ResponseBody
	@RequestMapping(value = "/material/list")  
	public Message queryPage(Material material,ModelMap model,Pagination<Material> page) {
		try{
			QueryParams<Material> params = new QueryParams<Material>(material);
			params.eq("foldercode", false);
			params.like("materialcode", false);
			params.like("materialname", false);
			params.like("queryword", false);
			params.addOrder("materialcode", "asc");
			return buildGridData(materialService.query(params,page));
		}catch (Exception e){	
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/material/edit")  
	public Message addorEdit(Material material,ModelMap model) {
		Message msg = new Message();
		try{
			materialService.insertOrUpdate(material, true);
			msg.setTotal(material.getId());
		}catch(PersistenceException ex){
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}catch(DuplicateKeyException e){
			msg.setSuccess(false);
			msg.setMsg("试图添加重复数据，操作失败！");
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/material/delete")
	public Message del(Material material,ModelMap model) {
		Message msg = new Message();
		try{
			materialService.deleteByIds(material);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/material/form")
	public Material loadForm(Material material,ModelMap model) {
		try{
			if(-1 != material.getId()){
				material = materialService.findOne(material.getId());
			}
			if(null == material){
				material = new Material();
			}
		}catch (Exception e) {	
			material = new Material();
		}
		return material; 
	}
}
