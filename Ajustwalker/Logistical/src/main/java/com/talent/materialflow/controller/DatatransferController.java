package com.talent.materialflow.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.model.QueryParams;
import com.talent.materialflow.model.Datatransfer;
import com.talent.materialflow.model.DatatransferItem;
import com.talent.materialflow.service.DatatransferItemService;
import com.talent.materialflow.service.DatatransferService;
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
public class DatatransferController extends BaseController {

	@Autowired
	private DatatransferService datatransferService;
	
	@Autowired
	private DatatransferItemService datatransferItemService;
	
	@RequestMapping(value = "/datatransfer")  
	public String init(ModelMap model) {
		return "datatransfer";
	}
	
	@ResponseBody
	@RequestMapping(value = "/datatransfer/list")  
	public Message queryPage(Datatransfer datatransfer,ModelMap model,Pagination<Datatransfer> page) {
		try{
			List<String> p = new ArrayList<String>();
			p.add("datatransfercode");
			p.add("datatransfername");
			QueryParams<Datatransfer> params = new QueryParams<Datatransfer>(datatransfer);
			params.orLike(p,datatransfer.getSearchText());
			return buildGridData(datatransferService.query(params));
		}catch (Exception e){	
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/datatransfer/edit")  
	public Message addorEdit(Datatransfer datatransfer,ModelMap model) {
		Message msg = new Message();
		try{
			datatransferService.insertOrUpdate(datatransfer, true);
			msg.setTotal(datatransfer.getId());
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
	@RequestMapping(value = "/datatransfer/delete")
	public Message del(Datatransfer datatransfer,ModelMap model) {
		Message msg = new Message();
		try{
			datatransferService.deleteByIds(datatransfer);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/datatransfer/form")
	public Datatransfer loadForm(Datatransfer datatransfer,ModelMap model) {
		try{
			if(-1 != datatransfer.getId()){
				datatransfer = datatransferService.findOne(datatransfer.getId());
			}
			if(null == datatransfer){
				datatransfer = new Datatransfer();
			}
		}catch (Exception e) {	
			datatransfer = new Datatransfer();
		}
		return datatransfer; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/datatransferitem/form")
	public DatatransferItem loadItemForm(DatatransferItem datatransferItem,ModelMap model) {
		try{
			if(-1 != datatransferItem.getId()){
				datatransferItem = datatransferItemService.findOne(datatransferItem.getId());
				if(null == datatransferItem){
					datatransferItem = new DatatransferItem();
				}
			}
		}catch (Exception e) {	
			datatransferItem = new DatatransferItem();
		}
		return datatransferItem; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/datatransferitem/list")  
	public Message queryList(DatatransferItem datatransferitem,ModelMap model) {
		try{
			QueryParams<DatatransferItem> params = new QueryParams<DatatransferItem>(datatransferitem);
			params.eq("fid",false);
			params.addOrder("datatable", "asc");
			params.addOrder("dbcolumn", "asc");
			return buildGridData(datatransferItemService.query(params));
		}catch (Exception e){	
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/datatransferitem/edit")  
	public Message itemAddorEdit(DatatransferItem datatransferitem,ModelMap model) {
		Message msg = new Message();
		try{
			datatransferItemService.insertOrUpdate(datatransferitem, true);
			msg.setTotal(datatransferitem.getId());
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
	@RequestMapping(value = "/datatransferitem/delete")
	public Message del(DatatransferItem datatransferitem,ModelMap model) {
		Message msg = new Message();
		try{
			datatransferItemService.deleteByIds(datatransferitem);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
}
