package com.talent.materialflow.controller;

import java.util.Map;

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
import com.talent.materialflow.model.Applicationbill;
import com.talent.materialflow.model.BCommon;
import com.talent.materialflow.model.Measure;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.TrainBlendMapper;

@Controller
public class TrainBlendController extends BaseController {

	@Resource
	private TrainBlendMapper trainblendMapper;
	
	@Autowired
	private HttpUtils httpUtils;
	@Resource
	private BCommonMapper bcommonMapper;

	@RequestMapping(value = "/trainblend/trainblend")
	public String card(ModelMap model) {
		return "trainblend";
	}

	/**
	 * 
	 * @param measure
	 * @param model
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/trainblend/queryTrainBlendList.do")
	public Message queryPage(Measure measure, ModelMap model, Pagination<Measure> page) {

		try {
			return buildGridData(trainblendMapper.queryTrainBlendList(measure));
		} catch (Exception e) {
			Message msg = new Message();
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
			return msg;
		}

	}

	/**
	 * 查询接口火车单据信息
	 * 
	 * @param app
	 * @param model
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/trainblend/queryBlendInterList.do")
	public Message queryPage(Applicationbill app, ModelMap model, Pagination<Applicationbill> page) {

		try {
			return buildGridData(trainblendMapper.queryBlendInterList(app));
		} catch (Exception e) {
			Message msg = new Message();
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
			return msg;
		}

	}

	/**
	 * 勾兑数据信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/trainblend/updateTrainBlend.do")
	public Message updateTrainBlend(Applicationbill app) {
		Message msg = new Message();
		try {
			int flag = 0;
			 String	message="";
			BCommon bcommon = new BCommon();
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			for (int i = 0; i < app.getExparams().size(); i++) {
				Map<String, String> map = app.getExparams().get(i);
				app.setMatchid(map.get("matchid"));
				flag = trainblendMapper.updateTrainBlend(app);// 查询输入的业务号是否已经存在
				if(flag>0){
					//http://192.168.2.42:6080/Logistical/unauth/interface/upload?matchid=9016111800828
					 message = httpUtils.get("http://192.168.2.42:6080/Logistical/unauth/interface/upload",
								"matchid="+app.getMatchid());
					 //System.out.println("勾兑上传返回信息..........."+message);
				
				}
				
				bcommon.setMatchid(app.getMatchid());
				bcommon.setUsermemo("勾兑信息记录,上传返回信息："+flag+".."+message);
				bcommon.setCreateman(app.getCreator());
				bcommonMapper.insertExceptinonlog(bcommon);
			}

			if (flag == 0) {// 不存在保存。存在提示不允许使用
				msg.setSuccess(false);
				msg.setMsg("勾兑失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("勾兑失败！" + e.getMessage());
		}
		return msg;
	}

	/**
	 * 作废勾兑数据信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/trainblend/cancelTrainBlend.do")
	public Message cancelTrainBlend(Applicationbill app) {
		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setPlanid("");
			app.setSaleitemid("");
			int flag = trainblendMapper.updateTrainBlend(app);// 作废勾兑信息
			if (flag == 0) {// 不存在保存。存在提示不允许使用
				msg.setSuccess(false);
				msg.setMsg("勾兑失败！");
			}
			BCommon bcommon = new BCommon();
			bcommon.setMatchid(app.getMatchid());
			bcommon.setUsermemo("作废勾兑信息记录");
			bcommon.setCreateman(app.getCreator());
			bcommonMapper.insertExceptinonlog(bcommon);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("勾兑失败！" + e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 完成火运状态
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/trainblend/updatePlanStatus.do")
	public Message updatePlanStatus(Applicationbill app) {
		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			int flag = trainblendMapper.updatePlanStatus(app);// 作废勾兑信息
			if (flag == 0) {// 不存在保存。存在提示不允许使用
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}
}
