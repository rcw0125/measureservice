package com.xgmes.service.impl;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.talent.core.model.Message;
import com.xgmes.mapper.BCommonMapper;
import com.xgmes.mapper.StoreinMapper;
import com.xgmes.model.BCommon;
import com.xgmes.model.Blacklist;
import com.xgmes.model.Currtemp;
import com.xgmes.model.Storein;
import com.xgmes.service.BCommonService;
import com.xgmes.service.StoreinService;

@Service
@Transactional
public class StoreinServiceImpl implements StoreinService {
	@Resource
	private StoreinMapper storeinMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private BCommonMapper bcommonMapper;

	public Message queryInfoBycarno(Storein storein) throws DataAccessException {

		Blacklist black = new Blacklist();
		black.setCardid(storein.getCardid());
		black.setCarno(storein.getCarno());
		Message msg = new Message();
		try {
			msg = bcommonService.judgCarno(black);
			if (msg.isSuccess()) {
				msg.setRows(storeinMapper.queryInfoBycarno(storein));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 根据卡号和车号判断卡状态

		return msg;
	}

	/**
	 * 添加入库信息
	 */

	@Override
	public Message insertStorein(Storein storein) throws DataAccessException {
		String time = bcommonMapper.queryOracletime();
		Message msg = new Message();
		if (storein.getExparams().size() > 0) {
			Map<String, String> maps = storein.getExparams().get(0);
			storein.setMatchid(maps.get("matchid"));
			int flag = storeinMapper.queryCountBymatchid(storein);
			if (flag > 0) {
				storeinMapper.cancelStorein(storein);
				storeinMapper.cancelStoreinitem(storein);
			}
		}
		for (int i = 0; i < storein.getExparams().size(); i++) {
			Map<String, String> map = storein.getExparams().get(i);
			storein.setMatchid(map.get("matchid"));
			storein.setOperatype(map.get("operatype"));
			storein.setCartype("C");
			storein.setItemid(map.get("itemid"));
			storein.setPlanid(map.get("planid"));
			storein.setTaskcode(map.get("taskcode"));
			storein.setMaterialcode(map.get("materialcode"));
			storein.setMaterialname(map.get("materialname"));
			storein.setSourcecode(map.get("sourcecode"));
			storein.setSourcename(map.get("sourcename"));
			storein.setTargetcode(map.get("targetcode"));
			storein.setTargetname(map.get("targetname"));
			storein.setShipcode(map.get("shipcode"));
			storein.setShip(map.get("ship"));
			storein.setWeight(StringUtils.isEmpty(map.get("weight")) ? 0 : Double.parseDouble(map.get("weight")));
			storein.setCounts(StringUtils.isEmpty(map.get("counts")) ? 0 : Double.parseDouble(map.get("counts")));
			storein.setOutcounts(StringUtils.isEmpty(map.get("outcounts")) ? 0 : Double.parseDouble(map.get("outcounts")));
			storein.setMeasureunit(map.get("measureunit"));
			storein.setModelno(map.get("modelno"));
			storein.setPictureno(map.get("pictureno"));
			storein.setMaterialspec(map.get("materialspec"));
			storein.setProdlinename(map.get("prodlinename"));
			storein.setStorecode(storein.getUnitcode());
			storein.setStorename(storein.getUnitname());
			storein.setMiddlemanname(map.get("middlemanname"));
			storein.setCreatedate(time);
			storeinMapper.insertStoreinitem(storein);
		}
		storeinMapper.insertStorein(storein);
		storein.setSysmemo("回皮");
		storein.setStoreintime(time);
		storeinMapper.updateCurrtemp(storein);
		storeinMapper.updateApplicationitem(storein);
		msg.setMsg("入库成功");
		/*
		 * try { if ("91".equals(storein.getOperatype())) { httpUtils.get(
		 * "http://192.168.2.42:6080/Logistical/unauth/interface/upload",
		 * "matchid=" + storein.getMatchid() + "&isormeasure=0"); }
		 * 
		 * } catch (Exception e) {
		 * 
		 * }
		 */
		if(storein.getExceptionflag()==1){
			BCommon bcommon = new BCommon();
			bcommon.setMatchid(storein.getMatchid());
			bcommon.setUsermemo("异常入库信息");
			bcommon.setCreateman(storein.getCreator());
			bcommonMapper.insertExceptinonlog(bcommon);
			bcommon=null;
		}
		storein=null;
		return msg;
	}

	/**
	 * 作废入库信息
	 */

	@Override
	public Message cancelStorein(Storein storein) throws DataAccessException {
		Message msg = new Message();
		Currtemp curr = new Currtemp();
		curr.setMatchid(storein.getMatchid());
		 msg = bcommonService.beforeCancel(curr, "SIN");
		if (msg.isSuccess()) {
			storeinMapper.cancelStorein(storein);
			storeinMapper.cancelStoreinitem(storein);
			storein.setRecetype(0);
			storein.setStoreintime("");
			storein.setCreator("");
			storein.setCreateoperacode("");
			storein.setUsermemo("");
			storeinMapper.updateCurrtemp(storein);
			storeinMapper.updateApplicationitem(storein);
		}
		return msg;
	}

}
