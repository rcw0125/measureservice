package com.talent.measure.web;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.base.model.Message;
import com.talent.measure.dao.SeatConfigrationDao;
import com.talent.measure.model.EquipmentParam;
import com.talent.measure.model.SeatClientId;
import com.talent.measure.model.SeatConfigration;

@Controller
public class MeasureSeatController {

	@Autowired
	private SeatConfigrationDao seatDao;

	// 根据坐席编码获取到坐席当前的秤点信息
	@ResponseBody
	@RequestMapping(value = "/MeasureSeatController/getSeatClient.do")
	public List<SeatClientId> getSeatClient(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		SeatClientId sc = null;
		try {
			sc = getObjectsFromJson(jsonParams, SeatClientId.class).get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<SeatClientId> scList = new ArrayList<SeatClientId>();
		// 获取当前坐席的秤点……
		List<SeatClientId> dbSeatClient = seatDao.getSeatClient(sc);
		List<SeatConfigration> allDbInfo = seatDao.getSeatList("");
		if (dbSeatClient.size() > 0) {

			SeatClientId dbInfo = dbSeatClient.get(0);
			String clientId = dbInfo.getClientid();
			for (int i = 0; i < allDbInfo.size(); i++) {
				SeatClientId newDbInfo = new SeatClientId();
				// newDbInfo=dbInfo;
				SeatConfigration seatConfig = allDbInfo.get(i);
				String eType = seatConfig.getEqutype();
				String oldEno = seatConfig.getEqucode();
				String oldEname = seatConfig.getEquname();
				if (eType.equals("C")) {
					if (clientId.contains(oldEno + ",")) {
						newDbInfo.setIsinseat("是");
					} else {
						newDbInfo.setIsinseat("否");
					}
					// 获取最新的版本号
					newDbInfo.setVersionnum(String.valueOf(seatDao.getLastVersionNum(oldEno)));
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
				// newDbInfo=dbInfo;
				SeatConfigration seatConfig = allDbInfo.get(i);
				String eType = seatConfig.getEqutype();
				String oldEno = seatConfig.getEqucode();
				String oldEname = seatConfig.getEquname();
				if (eType.equals("C")) {

					newDbInfo.setIsinseat("否");

					// 获取最新的版本号
					newDbInfo.setVersionnum(String.valueOf(seatDao.getLastVersionNum(oldEno)));
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

		return scList;
	}

	// 根据秤点编码获取对应的所有坐席
	@ResponseBody
	@RequestMapping(value = "/MeasureSeatController/getEqucodeSeat.do")
	public List<SeatClientId> getEqucodeSeat(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		SeatClientId sc = null;
		try {
			sc = getObjectsFromJson(jsonParams, SeatClientId.class).get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.setClientid(sc.getClientid() + ",");
		List<SeatClientId> scList = new ArrayList<SeatClientId>();
		// 获取当前秤体对应的坐席
		scList = seatDao.getEqucodeSeat(sc);
		return scList;
	}

	// 根据秤体编码获取对应的参数信息
	@ResponseBody
	@RequestMapping(value = "/MeasureSeatController/getEquParamInfo.do")
	public List<EquipmentParam> getEquParamInfo(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		EquipmentParam sc = null;
		try {
			sc = getObjectsFromJson(jsonParams, EquipmentParam.class).get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<EquipmentParam> scList = seatDao.getEquParamInfo(sc);
		return scList;
	}

	// 保存秤体配置参数
	@ResponseBody
	@RequestMapping(value = "/MeasureSeatController/saveEquParamInfo.do", method = RequestMethod.POST)
	public Message saveEquParamInfo(ModelMap model, @RequestBody List<EquipmentParam> mtd) {
		Message msg = new Message();
		int successcount = 0;
		// if(jsonParams.length()==0)
		// {
		// msg=setMessage(successcount,"请求串为空");
		// return msg;
		// }
		// List<EquipmentParam> mtd = new ArrayList<EquipmentParam>();
		// try {
		// mtd = getObjectsFromJson(jsonParams,
		// EquipmentParam.class);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		for (int i = 0; i < mtd.size(); i++) {
			EquipmentParam oneMod = mtd.get(i);
			if (oneMod.getEqucode().length() == 0 || oneMod.getParaminfos().length() == 0) {
				continue;
			}
			// oneMod.setVersionnum(seatDao.getLastVersionNum(oneMod.getEqucode())+1);
			successcount = successcount + seatDao.saveEquParamInfo(oneMod);
		}

		// msg="{"+"\"successcount\""+":"+successcount+"}";
		msg = setMessage(successcount, "请求完成");
		return msg;
	}

	// 保存坐席与秤体的对应关系
	@ResponseBody
	@RequestMapping(value = "/MeasureSeatController/saveSeatClient.do", method = RequestMethod.POST)
	public Message saveSeatClient(@RequestBody String jsonParams) {
		Message m = new Message();
		int successcount = 0;
		if (jsonParams.length() <= 2) {
			m = setMessage(successcount, "求串为空");
			return m;
		}
		SeatClientId mtd = new SeatClientId();
		List<SeatClientId> scList = new ArrayList<SeatClientId>();
		try {
			scList = getObjectsFromJson(jsonParams, SeatClientId.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String clientId = "";
		for (int i = 0; i < scList.size(); i++) {
			SeatClientId sc = scList.get(i);
			if (i == 0) {
				// mtd=sc;
				mtd.setSeatid(sc.getSeatid());
				mtd.setSeatname(sc.getSeatname());
				mtd.setSeattype(sc.getSeattype());
				mtd.setSeatright(sc.getSeatright());
				mtd.setValidflag("1");// 默认都是1
			}
			clientId = sc.getClientid() + "," + clientId;
		}
		mtd.setClientid(clientId);

		String seatid = mtd.getSeatid();
		// 判断是修改还是新增
		int isAdd = seatDao.checkSeatClientIsAdd(seatid);
		if (isAdd > 0)// 修改
		{
			successcount = seatDao.updateSeatClient(mtd);
		} else// 新增
		{
			successcount = seatDao.insertSeatClient(mtd);
		}
		m = setMessage(successcount, "请求完成");
		return m;
	}

	public static Message setMessage(int successcount, String msg) {
		Message rtm = new Message();
		rtm.setTotal(successcount);
		if (successcount > 0) {
			rtm.setSuccess(true);
		} else {
			rtm.setSuccess(false);
		}
		rtm.setMsg(msg);
		rtm.setData("");
		return rtm;

	}

	private <T> List<T> getObjectsFromJson(String in, Class<T> clsT) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("deprecation")
		JsonParser parser = objectMapper.getJsonFactory().createJsonParser(in);

		JsonNode nodes = parser.readValueAsTree();
		List<T> list = new ArrayList<T>(nodes.size());
		for (JsonNode node : nodes) {
			list.add(objectMapper.readValue(node.toString(), clsT));
		}
		return list;
	}
}
