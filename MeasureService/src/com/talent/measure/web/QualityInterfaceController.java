package com.talent.measure.web;

import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.measure.dao.QualityInterfaceDao;
import com.talent.measure.model.QualityInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QualityInterfaceController extends BaseController {

	@Autowired
	private QualityInterfaceDao qualityInterfaceDao;
	
	@ResponseBody
	@RequestMapping(value = "/qualityInterface/updatemeasure.do")
	public Object updatemeasure(QualityInterface qualityInterface,ModelMap model) {
		Message msg = new Message();
		try {
			qualityInterfaceDao.updateMeasureData(qualityInterface);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/qualityInterface/ingate.do")
	public Object ingate(QualityInterface qualityInterface,ModelMap model) {
		Message msg = new Message();
		try {
			qualityInterface.setCarriveorder_bid(qualityInterface.getBillbodyid());
			qualityInterfaceDao.insertInGate(qualityInterface);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/qualityInterface/outgate.do")
	public Object outgate(QualityInterface qualityInterface,ModelMap model) {
		Message msg = new Message();
		try {
			qualityInterface.setCarriveorder_bid(qualityInterface.getBillbodyid());
			qualityInterfaceDao.insertOutGate(qualityInterface);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/qualityInterface/returnsampleflag.do")
	public Object returnsampleflag(QualityInterface qualityInterface,ModelMap model) {
		Message msg = new Message();
		try {
			qualityInterfaceDao.returnSampleFlag(qualityInterface);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}
	/**
	 * 作废取样信息
	 * @param qualityInterface
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/qualityInterface/cancelSampleFlag.do")
	public Object cancelSampleFlag(QualityInterface qualityInterface,ModelMap model) {
		Message msg = new Message();
		try {
			qualityInterfaceDao.cancelSampleFlag(qualityInterface);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}
	/**
	 * 把上次取样信息修改为作废状态,
	 * @param matchid
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/qualityInterface/updateSatus.do")
	public Object updateSatus(QualityInterface qualityInterface,ModelMap model) {
		Message msg = new Message();
		try {
			qualityInterfaceDao.updateSatus(qualityInterface); 
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}
	/**
	 * 根据车号和计量时间获取线材净重
	 * @param carno 火车车皮号
	 * @param begintime：皮重时间；endtime：毛重时间
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/qualityInterface/queryWireValue.do")
	public Object queryWireValue(QualityInterface qualityInterface) {
		Message msg = new Message();
		try {
			String value=qualityInterfaceDao.queryWireValue(qualityInterface); 
			msg.setData(value);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}
}
