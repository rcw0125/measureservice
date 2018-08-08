package com.xgmes.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.talent.core.annotation.Rule;
import com.xgmes.mapper.BusinessConfigMapper;
import com.xgmes.model.Currtemp;
import com.xgmes.model.Entrylog;
import com.xgmes.model.ReturnMessage;
import com.xgmes.model.WorklineItem;

@Component
public class BusinessConfig {
	@Autowired
	private BusinessConfigMapper bcMapper;

	// 是否重复进厂
	@Rule(name = "重复进厂判断", ctrlflag = "reentryflag", memo = "判断车辆是否重复进厂", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkReEntry(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();
		Entrylog entry = bcMapper.queryEntryinfo(curr);


		try {
			if (entry != null) {// 进厂时业务临时表添加进厂时间，进厂时间不为空，系统任务车辆已经进厂
				msg.setFunctionname("checkReEntry");
				msg.setSuccess(false);
				msg.setMsg("车辆已进厂！上次进厂时间：" + entry.getCdate());
				msg.setFlag(curr.getReentryflag());
			}
			
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("重复进厂验证失败");
		}
		return msg;
	}

	// 是否进厂
	@Rule(name = "进厂判断", ctrlflag = "entryflag", memo = "判断车辆是否进厂", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkEntry(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();
		try {
			if ("".equals(curr.getEntertime())) {// 进厂时业务临时表添加进厂时间，进厂时间不为空，系统任务车辆已经进厂
				msg.setFunctionname("checkEntry");
				msg.setSuccess(false);
				msg.setMsg("车辆未进厂！");
				// msg.setFlag(curr.getEntryflag());
				msg.setFlag(2);
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("进厂判断验证失败");
		}
		return msg;
	}

	// 查询是否计毛
	@Rule(name = "是否计量毛重", ctrlflag = "grossflag", memo = "判断车辆是否计量毛重", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkGross(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();
		
		
		try {
			if (curr.getGross() == 0) {// 进厂时业务临时表添加进厂时间，进厂时间不为空，系统任务车辆已经进厂
				msg.setFunctionname("checkGross");
				msg.setSuccess(false);
				msg.setMsg("车辆未计量毛重！");
				// msg.setFlag(curr.getGrossflag());
				msg.setFlag(2);
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("是否计量毛重验证失败");
		}
		return msg;
	}

	// 取样时判断是否计毛
	@Rule(name = "取样时判断计量毛重", ctrlflag = "sgrossflag", memo = "取样时判断计量毛重", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkSGross(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();
		
		try {
			if (curr.getGross() == 0) {// 进厂时业务临时表添加进厂时间，进厂时间不为空，系统任务车辆已经进厂
				msg.setFunctionname("checkGross");
				msg.setSuccess(false);
				msg.setMsg("车辆未计量毛重！");
				// msg.setFlag(curr.getSgrossflag());
				msg.setFlag(2);
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("取样时判断计量毛重验证失败");
		}
		return msg;
	}

	// 查询是否计皮
	@Rule(name = "是否计皮", ctrlflag = "tareflag", memo = "是否计皮", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkTare(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();

		
		try {
			if (curr.getTare() == 0) {// 进厂时业务临时表添加进厂时间，进厂时间不为空，系统任务车辆已经进厂
				msg.setFunctionname("checkTare");
				msg.setSuccess(false);
				msg.setMsg("车辆未计量皮重！");
				// msg.setFlag(curr.getTareflag());
				msg.setFlag(2);
			}
			
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("是否计皮验证失败");
		}
		return msg;
	}

	/**
	 * 查询是否入库
	 * 
	 * @param curr
	 * @return
	 */
	// 查询是否计皮
	@Rule(name = "是否收货", ctrlflag = "tareflag", memo = "是否收货", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkSourcetime(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();

		
		try {
			if ("".equals(curr.getSourcetime()) || curr.getSourcetime() == null) {// 进厂时业务临时表添加进厂时间，进厂时间不为空，系统任务车辆已经进厂
				msg.setFunctionname("checkSourcetime");
				msg.setSuccess(false);
				msg.setMsg("车辆未收货！");
				// msg.setFlag(curr.getReceiveflag());
				msg.setFlag(2);
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("是否收货验证失败");
		}
		return msg;
	}

	/**
	 * 查询是否取样
	 * 
	 * @param curr
	 * @return
	 */
	@Rule(name = "是否取样", ctrlflag = "tareflag", memo = "是否取样", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkSampletime(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();
		
		try {
			if ("".equals(curr.getSampletime()) || curr.getSampletime() == null) {// 根据取样时间判断
				msg.setFunctionname("checkSampletime");
				msg.setSuccess(false);
				msg.setMsg("车辆未取样！");
				// msg.setFlag(curr.getSampleflag());
				msg.setFlag(2);
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("是否取样验证失败");
		}
		
		return msg;
	}

	/**
	 * 查询是否强制收货
	 * 
	 * @param curr
	 * @return
	 */
	@Rule(name = "是否强制收货", ctrlflag = "tareflag", memo = "是否强制收货", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkForce(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();
		try {
			if (!curr.getUnitname().equals(curr.getTargetname())) {// 进厂时业务临时表添加进厂时间，进厂时间不为空，系统任务车辆已经进厂
				msg.setFunctionname("checkForce");
				msg.setSuccess(false);
				msg.setMsg("当前单位与收货单位不一致！");
				// msg.setFlag(curr.getForceflag());
				msg.setFlag(2);
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("强制收货方法验证失败");
		}
		return msg;
	}
	
	@Rule(name = "是否有未完成的业务信息", ctrlflag = "", memo = "判断是否有未完成的业务信息", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkOrNotInfoByCarno(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();
	    int flag = bcMapper.queryNotInfoByCarno(curr);
		try {
			if (flag >0) {// 进厂时业务临时表添加进厂时间，进厂时间不为空，系统任务车辆已经进厂
				msg.setFunctionname("queryNotInfoByCarno");
				msg.setSuccess(false);
				msg.setMsg("车辆有：" +flag+" 车未完成业务信息！");
				msg.setFlag(2);
			}
			
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("未完成业务判断失败");
		}
		return msg;
	}
	

	@Rule(name = "是否有业务信息", ctrlflag = "", memo = "判断是否有业务信息", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkInfoByCarno(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();
	    int flag = bcMapper.queryNotInfoByCarno(curr);
		try {
			if (flag ==0) {// 进厂时业务临时表添加进厂时间，进厂时间不为空，系统任务车辆已经进厂
				msg.setFunctionname("checkInfoByCarno");
				msg.setSuccess(false);
				msg.setMsg("车辆未有业务信息！");
				msg.setFlag(2);
			}
			
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("根据车号判断是否有业务信息失败");
		}
		return msg;
	}
	
	@Rule(name = "作业点验证", ctrlflag = "", memo = "作业点验证", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkWorkpoint(Currtemp curr) {
		ReturnMessage msg = new ReturnMessage();
		WorklineItem witem = new WorklineItem();
		witem.setWorklinecode(curr.getUnitcode());
		witem.setWorkpointcode(curr.getUnitname());
		witem.setNodecode(curr.getProcesslink());
		witem.setId(Long.parseLong(curr.getRouteid()));
	   
		try {
			 int flag = bcMapper.queryWorkpoint(witem);
			if (flag ==0) {// 进厂时业务临时表添加进厂时间，进厂时间不为空，系统任务车辆已经进厂
				msg.setFunctionname("checkWorkpoint");
				msg.setSuccess(false);
				msg.setMsg("车辆未在设定作业点操作！");
				msg.setFlag(2);
			}
			
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("作业点验证失败");
		}
		return msg;
	}
}
