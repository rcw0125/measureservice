package com.talent.materialflow.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.model.QueryParams;
import com.talent.materialflow.model.Folder;
import com.talent.materialflow.service.FolderService;
import com.talent.materialflow.service.mapper.FolderMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FolderController extends BaseController {

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private FolderMapper folderMapper;
	
	@RequestMapping(value = "/folder")  
	public String init(ModelMap model) {
		return "folder";
	}
	
	@ResponseBody
	@RequestMapping(value = "/folder/list")  
	public Message queryPage(Folder folder,String search,ModelMap model,Pagination<Folder> page) {
		try{
			List<String> p = new ArrayList<String>();
			p.add("foldercode");
			p.add("foldername");
			QueryParams<Folder> params = new QueryParams<Folder>(folder);
			params.orLike(p, search);
			return buildGridData(folderService.query(params, page));
		}catch (Exception e){	
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/folder/tree")  
	public List<Map<String,Object>> queryTree(ModelMap model) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Folder ff = new Folder();
		QueryParams<Folder> qf = new QueryParams<Folder>(ff);
		qf.addOrder("foldercode", "asc");
		qf.addOrder("sn", "asc");
		List<Folder> list = null;
		try {
			list = folderService.query(qf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Folder f : list){
			if(f.getFid() != f.getId()){
				Map<String,Object> item = new HashMap<String,Object>();
				item.put("id", f.getId());
				item.put("fid", f.getFid());
				item.put("name", f.getFoldercode() + f.getFoldername());
				item.put("foldercode", f.getFoldercode());
				result.add(item);
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/folder/edit")  
	public Message addorEdit(Folder folder,ModelMap model) {
		Message msg = new Message();
		try{
			if(!checkMeasureFlag(folder) && 1 == folder.getIsorrfid()){
				msg.setSuccess(false);
				msg.setMsg("物料大类不计量时不允许启用RFID！！");
			}else{
				folderService.insertOrUpdate(folder, true);
				msg.setTotal(folder.getId());
			}			
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
	
	private boolean checkMeasureFlag(Folder folder){
		if(1 == folder.getIsormeasure()){
			return true;
		}else{
			if(folder.getFid() > 0){
				Folder f = folderService.findOne(folder.getFid());
				return checkMeasureFlag(f);
			}else{
				return false;
			}			
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/folder/delete")
	public Message del(Folder folder,ModelMap model) {
		Message msg = new Message();
		try{
			folderService.deleteByIds(folder);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/folder/form")
	public Folder loadForm(Folder folder,ModelMap model) {
		try{
			if(-1 != folder.getId()){
				folder = folderService.findOne(folder.getId());
			}
			if(null == folder){
				folder = new Folder();
			}
		}catch (Exception e) {	
			folder = new Folder();
		}
		return folder; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/folder/downloadfolder.do")
	public Message downloadfolder() {
		Message msg = new Message();
		try{
			folderMapper.downloadfolder();
		}catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/folder/downloadmaterial.do")
	public Message downloadmaterial() {
		Message msg = new Message();
		try{
			folderMapper.downloadmaterial();
		}catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
}
