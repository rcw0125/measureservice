package com.talent.measure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.measure.dao.EquipmentDao;
import com.talent.measure.model.Equipment;

@Controller
public class EquipmentController {

	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private EquipmentDao equipmentDao;

	@ResponseBody
	@RequestMapping(value = "/equipment/getconfigbyip.do")
	public Equipment getEquipmentByIp(Equipment equipment) {
		equipment = platformDao.get(equipment);
		return equipment;
	}

	/**
	 * 查询衡器
	 * 
	 * @param equipment
	 * @param pm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/equipment/queryEquiment.do")
	public Message queryEquiment(Equipment equipment, PageModel pm) {
		Message msg = new Message();
		try {
			pm = equipmentDao.queryEquiment(equipment, pm);
			msg.setTotal(pm.getAllcount());
			msg.setRows(pm.getList());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/*
	 * 添加、修改衡器
	 */
	@ResponseBody
	@RequestMapping(value = "/equipment/insertEquiment.do")
	public Message insertHandover(@CookieValue(value = "username", defaultValue = "") String username, Equipment eq) {

		Message msg = new Message();
		int i = 0;
		try {
			eq.setCreateman(username);
			int m = equipmentDao.queryCBycode(eq);
			int f = equipmentDao.queryCByname(eq);		
			
			if (0 == eq.getId()) {
				if (m > 0||f>0) {
					if(m > 0&&f>0){
						msg.setSuccess(false);
						msg.setMsg("衡器编码、衡器名称已经存在！");	
					}else if(m>0&&f==0){
						msg.setSuccess(false);
						msg.setMsg("衡器编码已经存在！");
					}else if(f>0&&m==0){
						msg.setSuccess(false);
						msg.setMsg("衡器名称已经存在！");	
					}
					
				} else {
					i = equipmentDao.insertEquiment(eq);
					if (i > 0) {
						msg.setMsg("操作成功！");
					} else {
						msg.setSuccess(false);
						msg.setMsg("操作失败！");
					}
					
				}
				
				
			} else {
				if (m > 1||f>1) {
					if(m > 1&&f>1){
						msg.setSuccess(false);
						msg.setMsg("衡器编码、衡器名称已经存在！");	
					}else if(m>1&&f==1){
						msg.setSuccess(false);
						msg.setMsg("衡器编码已经存在！");
					}else if(f>1&&m==1){
						msg.setSuccess(false);
						msg.setMsg("衡器名称已经存在！");	
					}
					
				} else {
					i = equipmentDao.updateEquiment(eq);
					if (i > 0) {
						msg.setMsg("操作成功！");
					} else {
						msg.setSuccess(false);
						msg.setMsg("操作失败！");
					}
					
				}
				
				
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 作废衡器
	 * 
	 * @param h
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/equipment/cancelEquiment.do")
	public Message cancelEquiment(@CookieValue(value = "username", defaultValue = "") String username, Equipment eq) {

		Message msg = new Message();
		int i = 0;
		try {
			eq.setCreateman(username);
			i = equipmentDao.cancelEquiment(eq);
			if (i > 0) {
				msg.setMsg("操作成功！");
			} else {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/equipment/queryEquimentByid.do")
	public Equipment queryEquimentByid(Equipment eq) {

		Message msg = new Message();

		try {
			if (-1 == eq.getId()) {
				eq = new Equipment();
			} else {
				eq = equipmentDao.queryEquimentByid(eq);
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return eq;
	}

}
