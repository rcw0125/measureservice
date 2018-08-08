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
import com.xgmes.mapper.StoreoutMapper;
import com.xgmes.model.BCommon;
import com.xgmes.model.Blacklist;
import com.xgmes.model.Currtemp;
import com.xgmes.model.Storein;
import com.xgmes.service.BCommonService;
import com.xgmes.service.StoreoutService;

@Service
@Transactional
public class StoreoutServiceImpl implements StoreoutService {
	@Resource
	private StoreoutMapper storeoutMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private BCommonMapper bcommonMapper;

	/**
	 * 根据车号查询出库信息
	 * 
	 * @param storein
	 * @return
	 * @throws DataAccessException
	 */
	public Message queryInfoBycarno(Storein storein) throws DataAccessException {

		Blacklist black = new Blacklist();
		black.setCardid(storein.getCardid());
		black.setCarno(storein.getCarno());
		Message msg = new Message();
		try {
			msg = bcommonService.judgCarno(black);
			if (msg.isSuccess()) {
				msg.setRows(storeoutMapper.queryInfoBycarno(storein));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 根据卡号和车号判断卡状态

		return msg;
	}

	/**
	 * 添加出库信息
	 */
	@Override

	public Message insertStoreout(Storein storein) throws DataAccessException {
		String time = bcommonMapper.queryOracletime();
		Message msg = new Message();
		if(storein.getExparams().size()>0){
			Map<String, String> maps = storein.getExparams().get(0);
			storein.setMatchid(maps.get("matchid"));
			int flag = storeoutMapper.queryCountBymatchid(storein);
			if(flag>0){
				storeoutMapper.cancelStoreout(storein);//作废主子表
				storeoutMapper.cancelStoreoutitem(storein);
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
			storein.setWeight(StringUtils.isEmpty(map.get("weight")) ? 0 : Double.parseDouble(map.get("weight")));
			storein.setCounts(StringUtils.isEmpty(map.get("counts")) ? 0 : Double.parseDouble(map.get("counts")));
			storein.setMeasureunit(map.get("measureunit"));
			storein.setStorecode(storein.getUnitcode());
			storein.setStorename(storein.getUnitname());
			storein.setCreatedate(time);
			storeoutMapper.insertStoreoutitem(storein);
			
		}
		storein.setCreatedate(time);
		storeoutMapper.insertStoreout(storein);
		storein.setSysmemo("回毛");
		storein.setStoreintime(time);
		storeoutMapper.updateCurrtemp(storein);
		storeoutMapper.updateApplicationitem(storein);
		if(storein.getExceptionflag()==1){
			BCommon bcommon = new BCommon();
			bcommon.setMatchid(storein.getMatchid());
			bcommon.setUsermemo("异常出库信息");
			bcommon.setCreateman(storein.getCreateoperaname());
			bcommonMapper.insertExceptinonlog(bcommon);
			bcommon=null;
		}
		storein=null;
		return msg;
	}

	/**
	 * 作废出库信息
	 */

	@Override

	public Message cancelStoreout(Storein storein) throws DataAccessException {
		Message msg = new Message();
		Currtemp curr = new Currtemp();
		curr.setMatchid(storein.getMatchid());
		//msg = bcommonService.beforeCancel(curr, "SOUT");
		if (msg.isSuccess()) {
			storeoutMapper.cancelStoreout(storein);
			storeoutMapper.cancelStoreoutitem(storein);
			storein.setRecetype(0);
			storein.setStoreintime("");
			storein.setCreateoperacode("");
			storein.setCreateoperaname("");
			storein.setCreateoperaname("");
			storeoutMapper.updateCurrtemp(storein);
			storeoutMapper.updateApplicationitem(storein);
		}
		storein=null;
		curr=null;
		return msg;
	}

}
