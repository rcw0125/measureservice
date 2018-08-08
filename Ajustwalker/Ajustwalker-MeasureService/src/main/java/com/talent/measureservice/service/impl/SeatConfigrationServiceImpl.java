package com.talent.measureservice.service.impl;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.measureservice.model.EquipmentParam;
import com.talent.measureservice.model.SeatClientId;
import com.talent.measureservice.service.SeatConfigrationService;
import com.talent.measureservice.service.mapper.SeatConfigrationMapper;
@Service
@Transactional
public class SeatConfigrationServiceImpl implements SeatConfigrationService {

	@Resource
	private SeatConfigrationMapper seatmapper;
	
	

	// 保存秤体对应的配置参数信息

	@Override
	public int saveEquParamInfo(List<EquipmentParam> mtd) throws DataAccessException {
		int successcount = 0;
		for (int i = 0; i < mtd.size(); i++) {
			EquipmentParam oneMod = mtd.get(i);
			if (oneMod.getEqucode().length() == 0 || oneMod.getParaminfos().length() == 0) {
				continue;
			}
			successcount = successcount + seatmapper.saveEquParamInfo(oneMod);
		}
		mtd.clear();
		mtd=null;
		return successcount;
	}


	// 修改坐席与秤点对应关系


	@SuppressWarnings("unchecked")
	@Override
	public int updateSeatClient(List<SeatClientId> scList) throws DataAccessException {
		int successcount = 0;
		SeatClientId mtd = new SeatClientId();
		String clientId = "";
		SeatClientId sc = new  SeatClientId();
		for (int i = 0; i < scList.size(); i++) {
			try {
				BeanUtils.populate(sc, (Map<String, ? extends Object>)scList.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			} 
			if (i == 0) {
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
		int isAdd = seatmapper.checkSeatClientIsAdd(seatid);
		if (isAdd > 0)// 修改
		{
			successcount = seatmapper.updateSeatClient(mtd);
		} else// 新增
		{
			successcount = seatmapper.insertSeatClient(mtd);
		}
		/**
		 * 清空对象
		 */
		scList.clear();
		scList=null;
		mtd=null;
		clientId=null;
		seatid=null;
		return successcount;
	}

}
