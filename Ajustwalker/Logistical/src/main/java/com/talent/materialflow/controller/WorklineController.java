package com.talent.materialflow.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.model.QueryParams;
import com.talent.materialflow.model.Workline;
import com.talent.materialflow.model.WorklineItem;
import com.talent.materialflow.model.WorklineTimecost;
import com.talent.materialflow.service.WorklineItemService;
import com.talent.materialflow.service.WorklineService;
import com.talent.materialflow.service.WorklineTimecostService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorklineController extends BaseController {

	@Autowired
	private WorklineService worklineService;
	
	@Autowired
	private WorklineItemService worklineItemService;
	
	@Autowired
	private WorklineTimecostService worklineTimecostService;
	
	/**
	 * 作业路线管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workline/workline")
	public String workline(ModelMap model) {
		return "workline";
	}
	

	@ResponseBody
	@RequestMapping(value = "/workline/queryPage.do")  
	public Message queryPage(Workline workline,String searchText,ModelMap model,Pagination<Workline> page) {
		try{
			workline.setWorklinename(searchText);
			QueryParams<Workline> params = new QueryParams<Workline>(workline);
			params.like("worklinename",true);
			return buildGridData(worklineService.query(params,page));
		}catch (Exception e){
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/workline/addoredit.do")  
	public Message addorEdit(Workline workline,ModelMap model) {
		Message msg = new Message();
		try{
			worklineService.insertOrUpdate(workline);
			msg.setTotal(workline.getId());
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/workline/del.do")  
	public Message del(Workline workline,String ids,ModelMap model) {
		Message msg = new Message();
		try{
			worklineService.delete(Workline.class,ids.split(","));
		}catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/worklineitem/del.do")  
	public Message itemdel(WorklineItem worklineitem,String ids,ModelMap model) {
		Message msg = new Message();
		try{
			worklineItemService.delete(WorklineItem.class,ids.split(","));
		}catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/workline/loadform.do")  
	public Workline loadForm(Workline workline,ModelMap model) {
		try{
			if(-1 != workline.getId()){
				workline = worklineService.findOne(workline.getId());
			}
			if(null == workline){
				workline = new Workline();
			}
		}catch (Exception e) {	
			workline = new Workline();
		}
		return workline; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/worklineitem/queryPage.do")  
	public Message queryItemPage(WorklineItem worklineItem,ModelMap model) {
		try{
			QueryParams<WorklineItem> params = new QueryParams<WorklineItem>(worklineItem);
			params.eq("worklinecode",true);
			return buildGridData(worklineItemService.query(params));
		}catch (Exception e){
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/worklineitem/addoredit.do")  
	public Message addorEditItem(WorklineItem worklineItem,WorklineTimecost worklineTimecost,ModelMap model) {
		Message msg = new Message();
		try{
			if(worklineItem.getId() <= 0){
				worklineItemService.insertOrUpdate(worklineItem);
				if(1 != worklineItem.getSn()){
					worklineTimecost.setToid(worklineItem.getId());
					worklineTimecostService.insert(worklineTimecost, true);
				}
			}else{
				worklineItemService.insertOrUpdate(worklineItem);
				worklineTimecostService.updateJpql("update WorklineTimecost set timecost=" + worklineTimecost.getTimecost() + " where worklinecode='" + worklineTimecost.getWorklinecode() + "' and sn=" + worklineTimecost.getSn());
			}
			
			msg.setMore(worklineItem.getId());
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/worklineitem/loadform.do")  
	public WorklineItem loadworklineItem(WorklineItem worklineItem,ModelMap model) {
		try{
			if(-1 != worklineItem.getId()){
				worklineItem = worklineItemService.findOne(worklineItem.getId());
				WorklineTimecost worklineTimecost = new WorklineTimecost();
				QueryParams<WorklineTimecost> params = new QueryParams<WorklineTimecost>(worklineTimecost);
				params.eq("worklinecode",false,worklineItem.getWorklinecode());
				params.eq("sn",false,worklineItem.getSn());
				List<WorklineTimecost> list = worklineTimecostService.query(params);
				if(null != list && 0 != list.size()){
					worklineTimecost = list.get(0);
					worklineItem.setTimecost(worklineTimecost.getTimecost());
				}
			}else{
				worklineItem = new WorklineItem();
			}
		}catch (Exception e) {	
			worklineItem = new WorklineItem();
		}
		return worklineItem; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/worklineitem/loadtimedesign.do")  
	public Message loadTimedesign(WorklineTimecost worklineTimecost,ModelMap model) {
		QueryParams<WorklineTimecost> params = new QueryParams<WorklineTimecost>(worklineTimecost);
		params.eq("toid",true);
		try {
			return buildGridData(worklineTimecostService.query(params));
		} catch (Exception e) {
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/worklineitem/addNodeTimedesign.do")  
	public Message addNodeTimedesign(WorklineTimecost worklineTimecost,ModelMap model) {
		Message msg = new Message();
		try{
			worklineTimecostService.insert(worklineTimecost, true);
			msg.setTotal(worklineTimecost.getId());
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/worklineitem/updateNodetimecost.do")  
	public Message updateNodetimecost(WorklineTimecost worklineTimecost,long value,long pk,ModelMap model) {
		Message msg = new Message();
		try{
			QueryParams<WorklineTimecost> updateField = new QueryParams<WorklineTimecost>(worklineTimecost);
			updateField.eq("timecost",false,value);
			QueryParams<WorklineTimecost> conditions = new QueryParams<WorklineTimecost>(worklineTimecost);
			conditions.eq("id", false,pk);
			worklineTimecostService.update(updateField, conditions, true);
			msg.setTotal(worklineTimecost.getId());
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/worklinetimecost/del.do")  
	public Message worklinetimecost(String ids,ModelMap model) {
		Message msg = new Message();
		try{
			worklineTimecostService.delete(WorklineTimecost.class,ids.split(","));
		}catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/worklineitem/loadothernodes.do")  
	public List<Map<String,String>> loadothernodes(WorklineItem worklineItem,ModelMap model) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		try{
			QueryParams<WorklineItem> params = new QueryParams<WorklineItem>(worklineItem);
			params.eq("worklinecode",true);
			params.notEq("id",true);
			List<WorklineItem> queryResult = worklineItemService.query(params);
			for(WorklineItem wli : queryResult){
				Map<String,String> item = new HashMap<String,String>();
				item.put("id", wli.getId()+"");
				item.put("text", wli.getNodename());
				result.add(item);
			}
		}catch (Exception e){
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/worklineitem/reorder")  
	public Message reorder(String sns,String ids,String worklinecode,ModelMap model) {
		try{
			String[] snArry = sns.split(",");
			String[] idArry = ids.split(",");
			for(int i=0;i<snArry.length;i++){
				worklineItemService.updateJpql("update WorklineItem set sn=" + snArry[i] + " where id=" + idArry[i]);
				if(i > 0){
					worklineTimecostService.updateJpql("update WorklineTimecost set fromid=" + idArry[i-1] + ",toid=" + idArry[i] + " where worklinecode=" + worklinecode + " and sn=" + snArry[i]);
				}else{
					worklineTimecostService.updateJpql("update WorklineTimecost set fromid=0,toid=" + idArry[i] + " where worklinecode=" + worklinecode + " and sn=" + snArry[i]);
				}
			}
			return new Message(true,"操作成功！");
		}catch (Exception e) {	
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/workline/select2data")
	public List<Map<String,Object>> worklineselect(ModelMap model) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<Workline> worklineList = worklineService.findAll();
		for(Workline wl : worklineList){
			Map<String,Object> item = new HashMap<String,Object>();
			item.put("id", wl.getId());
			item.put("text", wl.getWorklinename());
			result.add(item);
		}
		return result;
	}
}
