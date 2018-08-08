package com.talent.measureservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.core.model.Message;
import com.talent.measureservice.model.EquipmentParam;
import com.talent.measureservice.model.SeatClientId;
import com.talent.measureservice.model.SeatConfigration;
import com.talent.measureservice.service.SeatConfigrationService;
//import com.talent.measureservice.service.SeatConfigrationService;
import com.talent.measureservice.service.mapper.SeatConfigrationMapper;

@Controller
public class MeasureSeatController {

	private ObjectMapper om = new ObjectMapper();

	@Resource
	private SeatConfigrationService seatService;

	@Resource
	private SeatConfigrationMapper seatmapper;

	// 根据坐席编码获取到坐席当前的秤点信息
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/MeasureSeatController/getSeatClient.do")
	public List<SeatClientId> getSeatClient(String jsonParams) {

		if (jsonParams.length() == 0) {
			return null;
		}
		SeatClientId sc = new SeatClientId();
		List<SeatClientId> scList = new ArrayList<SeatClientId>();
		try {

			BeanUtils.populate(sc, (Map<String, ? extends Object>) (om.readValue(jsonParams, List.class)).get(0));
			// sc = ((List<SeatClientId>) om.readValue(jsonParams,
			// List.class)).get(0);

			// 获取当前坐席的秤点……
			List<SeatClientId> dbSeatClient = seatmapper.getSeatClient(sc);
			List<SeatConfigration> allDbInfo = seatmapper.getSeatList(sc);
			if (dbSeatClient.size() > 0) {
				SeatClientId dbInfo = dbSeatClient.get(0);
				String clientId = dbInfo.getClientid();
				for (int i = 0; i < allDbInfo.size(); i++) {
					SeatClientId newDbInfo = new SeatClientId();
					SeatConfigration seatConfig = allDbInfo.get(i);
					String eType = seatConfig.getEqutype();
					String oldEno = seatConfig.getEqucode();
					String oldEname = seatConfig.getEquname();
					if (eType.equals("T")) {
						if (clientId.contains(oldEno + ",")) {
							newDbInfo.setIsinseat("是");
						} else {
							newDbInfo.setIsinseat("否");
						}
						// 获取最新的版本号
						newDbInfo.setVersionnum(String.valueOf(seatmapper.getLastVersionNum(oldEno)));
						newDbInfo.setSeatid(dbInfo.getSeatid());
						newDbInfo.setSeatname(dbInfo.getSeatname());
						newDbInfo.setSeatright(dbInfo.getSeatright());
						newDbInfo.setSeattype(dbInfo.getSeattype());
						newDbInfo.setEqucode(oldEno);
						newDbInfo.setEquname(oldEname);
						scList.add(newDbInfo);
					}
				}
			} else {
				SeatClientId dbInfo = new SeatClientId();
				dbInfo.setSeatid(sc.getSeatid());
				dbInfo.setSeatname(sc.getSeatname());
				dbInfo.setSeattype(sc.getSeattype());
				for (int i = 0; i < allDbInfo.size(); i++) {
					SeatClientId newDbInfo = new SeatClientId();
					SeatConfigration seatConfig = allDbInfo.get(i);
					String eType = seatConfig.getEqutype();
					String oldEno = seatConfig.getEqucode();
					String oldEname = seatConfig.getEquname();
					if (eType.equals("T")) {
						newDbInfo.setIsinseat("否");
						// 获取最新的版本号
						newDbInfo.setVersionnum(String.valueOf(seatmapper.getLastVersionNum(oldEno)));
						newDbInfo.setSeatid(dbInfo.getSeatid());
						newDbInfo.setSeatname(dbInfo.getSeatname());
						newDbInfo.setSeatright(dbInfo.getSeatright());
						newDbInfo.setSeattype(dbInfo.getSeattype());
						newDbInfo.setEqucode(oldEno);
						newDbInfo.setEquname(oldEname);
						scList.add(newDbInfo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scList;
	}

	// 根据秤点编码获取对应的所有坐席
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/MeasureSeatController/getEqucodeSeat.do")
	public List<SeatClientId> getEqucodeSeat(String jsonParams) {
		List<SeatClientId> scList = new ArrayList<SeatClientId>();
		if (jsonParams.length() == 0) {
			return null;
		}
		try {
			SeatClientId sc = new SeatClientId();
			BeanUtils.populate(sc, (Map<String, ? extends Object>) (om.readValue(jsonParams, List.class)).get(0));
			// 获取当前秤体对应的坐席
			scList = seatmapper.getEqucodeSeat(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return scList;
	}

	// 根据秤体编码获取对应的参数信息
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/MeasureSeatController/getEquParamInfo.do")
	public List<EquipmentParam> getEquParamInfo(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		List<EquipmentParam> scList = new ArrayList<EquipmentParam>();
		try {
			EquipmentParam sc = new EquipmentParam();
			BeanUtils.populate(sc, (Map<String, ? extends Object>) (om.readValue(jsonParams, List.class)).get(0));
			scList = seatmapper.getEquParamInfo(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scList;
	}

	// 保存秤体配置参数
	@ResponseBody
	@RequestMapping(value = "/MeasureSeatController/saveEquParamInfo.do", method = RequestMethod.POST)
	public Message saveEquParamInfo(ModelMap model, @RequestBody List<EquipmentParam> mtd) {
		Message msg = new Message();
		try {
			int successcount = seatService.saveEquParamInfo(mtd);
			if (successcount > 0) {
				msg.setSuccess(false);
				msg.setMsg("保存成功");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}

		return msg;
	}

	// 保存坐席与秤体的对应关系
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/MeasureSeatController/saveSeatClient.do", method = RequestMethod.POST)
	public Message saveSeatClient(@RequestBody String jsonParams) {
		Message msg = new Message();
		try {
			if (jsonParams.length() <= 2) {
				msg.setSuccess(false);
				msg.setMsg("求串为空");
			}
			List<SeatClientId> scList = ((List<SeatClientId>) om.readValue(jsonParams, List.class));
			int flag = seatService.updateSeatClient(scList);
			if (flag > 0) {
				msg.setSuccess(false);
				msg.setMsg("请求完成");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}
}
