package com.xgmes.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.xgmes.mapper.BCommonMapper;
import com.xgmes.mapper.WorkpointMapper;
import com.xgmes.model.ComboxData;
import com.xgmes.model.Workpoint;
import com.xgmes.service.BCommonService;

@Controller
public class WorkpointController extends BaseController {

	@Resource
	private WorkpointMapper workpointMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private BCommonMapper bcommonMapper;
    
	
	/**
	 * 作业点管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workpoint/workpoint")
	public String workpoint(ModelMap model) {
		return "workpoint";
	}
	
	@ResponseBody
	@RequestMapping(value = "/workpoint/queryPage.do")
	public Message queryPage(Workpoint workpoint, ModelMap model, Pagination<Workpoint> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(workpointMapper.queryList(workpoint));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/workpoint/queryInfoByid.do")
	public Workpoint queryInfoByid(Workpoint workpoint) {
		Workpoint wk= new Workpoint();
		if (-1!=workpoint.getId()) {
			wk = workpointMapper.queryInfoByid(workpoint);
		}
		return wk;
	}
	/**
	 * 根据IP地址查询信息
	 * @param workpoint
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/workpoint/queryInfoByIp.do")
	public Workpoint queryInfoByIP(Workpoint workpoint) {
		Workpoint wk= workpointMapper.queryInfoByIp(workpoint);
		if(wk==null){
			wk=new Workpoint();
		}
		return wk;
	}

	/**
	 * 根据编码查询ip、mac
	 * @param workpoint
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/workpoint/queryInfoBycode.do")
	public Message queryInfoBycode(Workpoint workpoint) {
		
		Message msg = new Message();
		try {
			String mac = workpoint.getWorkpointmac();
			workpoint = workpointMapper.queryInfoBycode(workpoint);
			msg.setData(workpoint);
			if(!mac.equals(workpoint.getWorkpointmac())){
				msg.setSuccess(false);
				msg.setMsg("此作业点未注册");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 添加业务号信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/workpoint/insertWorkpoint.do")
	public Message insertTaskcode(Workpoint workpoint) {
		Message msg = new Message();
		
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int i = workpointMapper.queryCount(workpoint);
			if (workpoint.getId()==-1) {
				if (i == 0) {
					workpoint.setCreator(user.getDisplayname());
					workpointMapper.insertWorkpoint(workpoint);
				} else {
					msg.setSuccess(false);
					msg.setMsg("此IP地址已经添加作业点！");
				}
			} else {
				if (i > 1) {
					msg.setSuccess(false);
					msg.setMsg("此IP地址已经添加作业点！");
				} else {
					workpoint.setUpdater(user.getDisplayname());
					workpointMapper.updateWorkpoint(workpoint);
				}
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 作废作业点信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/workpoint/cancelWorkpoint.do")
	public Message cancelWorkpoint(Workpoint workpoint) {
		Message msg = new Message();
		
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			workpoint.setCanceler(user.getDisplayname());
			workpointMapper.cancelWorkpoint(workpoint);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 审核作业点
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/workpoint/approveWorkpoint.do")
	public Message approveWorkpoint(Workpoint workpoint) {
		Message msg = new Message();
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			workpoint.setApprover(user.getDisplayname());
			String[] ids = workpoint.getIds().split(",");
			for(int i=0;i<ids.length;i++){
				workpoint.setId(Long.parseLong(ids[i]));
			   workpointMapper.approveWorkpoint(workpoint);
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 弃审作业点
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/workpoint/giveupWorkpoint.do")
	public Message giveupWorkpoint(Workpoint workpoint) {
		Message msg = new Message();
		try {
			String[] ids = workpoint.getIds().split(",");
			for(int i=0;i<ids.length;i++){
			   workpoint.setId(Long.parseLong(ids[i]));
			   workpointMapper.giveupWorkpoint(workpoint);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/workpoint/querybytype.do")
	public List<Workpoint> querybytype(Workpoint workpoint) {
		return workpointMapper.queryWorkpointsByType(workpoint);
	}
	
	/**
	 * 注册作业点信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/workpoint/registeWorkpoint.do")
	public Message registeWorkpoint(Workpoint workpoint) {
		Message msg = new Message();
		try {
			workpoint.setRegisters(workpoint.getCreator());
			int i = workpointMapper.registeWorkpoint(workpoint);
			if(i == 0){
				msg.setSuccess(false);
				msg.setMsg("作业点没有维护,请联系管理员！");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/unauth/workpoint/select2.do")
	public List<ComboxData> workpointComboxData(ComboxData combox) {
		return bcommonMapper.queryWorkpoints(combox);
	}

	@RequestMapping(value = "/unauth/registeworkpoint.do")
	public String showentryreg(){
		return "workpointreg";
	}
}
