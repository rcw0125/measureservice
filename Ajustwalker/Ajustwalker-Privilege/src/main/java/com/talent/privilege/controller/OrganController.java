package com.talent.privilege.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.model.QueryParams;
import com.talent.core.privilege.model.Organ;
import com.talent.privilege.service.OrganService;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrganController extends BaseController {

	@Autowired
	private OrganService organService;
	
	@RequestMapping(value = "/organ")  
	public String init(ModelMap model) {
		return "organ";
	}
	
	@ResponseBody
	@RequestMapping(value = "/organ/list")  
	public Message queryPage(Organ organ,String searchText,ModelMap model,Pagination<Organ> page) {
		try{
			QueryParams<Organ> params = new QueryParams<Organ>(organ);
			if(null == searchText || searchText.length() == 0){
				params.like("organcode", true);
				params.like("organname", true);
			}else{
				List<String> p = new ArrayList<String>();
				p.add("organcode");
				p.add("organname");
				params.orLike(p, searchText);
			}
			return buildGridData(organService.query(params,page));
		}catch (Exception e){	
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/organ/edit")  
	public Message addorEdit(Organ organ,ModelMap model) {
		Message msg = new Message();
		try{
			organService.insertOrUpdate(organ, true);
			msg.setTotal(organ.getId());
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
	@RequestMapping(value = "/organ/delete")
	public Message del(Organ organ,ModelMap model) {
		Message msg = new Message();
		try{
			organService.deleteByIds(organ);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/organ/form")
	public Organ loadForm(Organ organ,ModelMap model) {
		try{
			if(-1 != organ.getId()){
				organ = organService.findOne(organ.getId());
			}
			if(null == organ){
				organ = new Organ();
			}
		}catch (Exception e) {	
			organ = new Organ();
		}
		return organ; 
	}
}
