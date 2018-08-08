package com.talent.materialflow.controller;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.talent.materialflow.model.Trainmodel;
import com.talent.materialflow.service.mapper.TrainmodelMapper;

@Controller
public class TrainmodelController extends BaseController{
	
	@Resource
	private TrainmodelMapper trainmodelMapper;
	
	@RequestMapping(value = "/trainmodel")
	public String trainmodel(ModelMap model) {
		return "trainmodel";
	}
	
	@ResponseBody
	@RequestMapping(value = "/trainmodel/list")
	public Message queryList(Trainmodel trainmodel, ModelMap model, Pagination<Trainmodel> page) {
		try {
			return buildGridData(trainmodelMapper.queryList(trainmodel));
		} catch (Exception e) {
			return new Message(false, Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/trainmodel/edit")  
	public Message insertTrainmodel(Trainmodel trainmodel) throws DataAccessException {
		Message msg = new Message();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			if (-1 == trainmodel.getId()) {
					trainmodel.setCreateor(user.getDisplayname());
					trainmodelMapper.insertTrainmodel(trainmodel);
			} else {
					trainmodel.setUpdateor(user.getDisplayname());
					trainmodelMapper.updateTrainmodel(trainmodel);
				}

		}
		catch (DuplicateKeyException de) {
			msg.setSuccess(false);
			msg.setMsg("相同的车型已经存在~!");
		}
		catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/trainmodel/cancel")
	public Message del(Trainmodel trainmodel,ModelMap model) {
		try {
			trainmodelMapper.cancelTrainmodel(trainmodel);
			return new Message();
		} catch (Exception e) {
			return new Message(false, Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/trainmodel/form")
	public Trainmodel loadForm(Trainmodel trainmodel) {
		try {
			if (trainmodel.getId() != -1) {// 如果前面传递过来的id为-1，这说明是添加，则直接返回新对象sg
				trainmodel = trainmodelMapper.queryInfoByID(trainmodel);// 如果是修改，查询要修改的数据并赋值给对象sg
			}
		} catch (Exception e) {
			System.out.println("异常信息为" + e.getMessage());
		}
		return trainmodel;
	}

}
