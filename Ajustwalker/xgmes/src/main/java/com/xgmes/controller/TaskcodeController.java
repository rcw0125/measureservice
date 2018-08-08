package com.xgmes.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.talent.core.util.HttpUtils;
import com.xgmes.mapper.TaskcodeMapper;
import com.xgmes.model.Taskcode;
import com.xgmes.service.BCommonService;

@Controller
public class TaskcodeController extends BaseController {

	@Resource
	private TaskcodeMapper taskcodeMapper;
	
	@Resource
	private BCommonService bcommonService;
    
	@Autowired
	private HttpUtils httpUtils;
	/**
	 * 业务号管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/taskcode/taskcode")
	public String taskcode(ModelMap model) {
		return "taskcode";
	}
	
	@ResponseBody
	@RequestMapping(value = "/taskcode/queryPage.do")
	public Message queryPage(Taskcode taskcode, ModelMap model, Pagination<Taskcode> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(taskcodeMapper.queryList(taskcode));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/taskcode/queryInfoBytaskcode.do")
	public Taskcode queryApplicationbill(Taskcode taskcode) {
		Taskcode tc = new Taskcode();
		if (-1!=taskcode.getId()) {
			tc = taskcodeMapper.queryInfoBytaskcode(taskcode);
		}else{
			tc.setOperatype("10");
		}
		return tc;
	}

	/**
	 * 添加业务号信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/taskcode/insertTaskcode.do")
	public Message insertTaskcode(Taskcode taskcode) {
		Message msg = new Message();
		
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int i = taskcodeMapper.queryCount(taskcode);
			taskcode.setTransitway("C");
			taskcode.setMeasuretype(2);//计量类型为强制自助
			
			if (taskcode.getId()==-1) {//添加
				taskcode.setCreator(user.getDisplayname());
				if (i == 0) {
					int flag = taskcodeMapper.queryCountbyTaskcode(taskcode);//查询输入的业务号是否已经存在
					if(flag==0){//不存在保存。存在提示不允许使用
						taskcodeMapper.insertTaskcode(taskcode);
					}else{
						msg.setSuccess(false);
						msg.setMsg("输入业务号已经存在！");	
					}
					
				} else {
					msg.setSuccess(false);
					msg.setMsg("相同货名、供货、收货业务号已经存在！");
				}
			} else {//修改
				taskcode.setUpdater(user.getDisplayname());
				if (i > 1) {
					msg.setSuccess(false);
					msg.setMsg("相同货名、供货、收货业务号已经存在！");
				} else {
					taskcodeMapper.updateTaskcode(taskcode);
				}
			}
			
			/**
			 * TODO 刷新计量缓存，以后去掉
			 */
			try{
				httpUtils.get("http://192.168.2.42:7080/MeasureService/tools/refreshbasedata.do", "");
			}catch(Exception ee){
				System.out.println("刷新计量基础信息失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 *查询输入业务号是否存在
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/taskcode/queryCountbyTaskcode.do")
	public Message queryCountbyTaskcode(Taskcode taskcode) {
		Message msg = new Message();
		
		try {
			int flag = taskcodeMapper.queryCountbyTaskcode(taskcode);//查询输入的业务号是否已经存在
			if(flag>0){//不存在保存。存在提示不允许使用
				msg.setSuccess(false);
				msg.setMsg("输入业务号已经存在！");	
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 作废业务号信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/taskcode/cancelTaskcode.do")
	public Message updateTaskcode(Taskcode taskcode) {
		Message msg = new Message();
		
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			taskcode.setCanceler(user.getDisplayname());
			taskcodeMapper.cancelTaskcode(taskcode);
			
			/**
			 * TODO 刷新计量缓存，以后去掉
			 */
			try{
				httpUtils.get("http://192.168.2.42:7080/MeasureService/tools/refreshbasedata.do", "");
			}catch(Exception ee){
				System.out.println("刷新计量基础信息失败！");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

}
