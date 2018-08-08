package com.xgmes.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.core.annotation.Rule;
import com.talent.core.model.Message;
import com.xgmes.common.LogisticalRuleCalc;
import com.xgmes.mapper.BusinessConfigMapper;
import com.xgmes.mapper.QualityMapper;
import com.xgmes.model.Blacklist;
import com.xgmes.model.Currtemp;
import com.xgmes.model.Quality;
import com.xgmes.model.ReturnMessage;
import com.xgmes.service.BCommonService;
import com.xgmes.service.QualityService;

@Service
@Transactional
public class QualityServiceImpl implements QualityService {
	@Resource
	private QualityMapper qualitMapper;
	@Resource
	private BCommonService bcommonService;
	@Autowired
	private LogisticalRuleCalc logisticalRuleCalc;
	@Resource
	private BusinessConfigMapper bcmapper;

	/**
	 * 根据车号查询取样信息
	 * 
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	public Message queryInfoBycarno(Quality q) throws DataAccessException {

		Blacklist black = new Blacklist();
		black.setCardid(q.getCardid());
		black.setCarno(q.getCarno());
		Message msg = new Message();
		try {
			msg = bcommonService.judgCarno(black);
			if (msg.isSuccess()) {
				msg.setRows(qualitMapper.queryInfoBycarno(q));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 根据卡号和车号判断卡状态
		
		return msg;
	}

	/**
	 * 保存前验证
	 * 
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Message beforeinsertquality(Quality q) throws DataAccessException {
		Currtemp curr = new Currtemp();
		curr.setMatchid(q.getMatchid());
		curr = bcmapper.queryCurrTemp(curr);
		curr.setProcesslink("SP");
		Message msg = logisticalRuleCalc.flagsCheck(curr, Rule.BEFORE_SAVE);

		List<ReturnMessage> list = (List<ReturnMessage>) msg.getFlags();
		String message="";
		if (list != null) {
			if (!"0".equals(msg.getMfunc())) {
				for (int i = 0; i < list.size(); i++) {
					ReturnMessage rm = list.get(i);
					if(!"".equals(rm.getMsg())){
						message=message+rm.getMsg()+ "<br/>";
					}
					
				}
			}

		}
		msg.setMsg(message);
		return msg;
	}

	/**
	 * 添加取样信息
	 * 
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public int insertquality(Quality q) throws DataAccessException {
		q.setQmpostion(q.getUnitname());
		q.setQmpostioncode(q.getUnitcode());
		qualitMapper.updateCurrtemp(q);
		qualitMapper.insertQuality(q);
		return 1;
	}

	/**
	 * 作废取样信息
	 * 
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public int cancelquality(Quality q) throws DataAccessException {
		qualitMapper.cancelCurrtemp(q);
		return qualitMapper.cancelQuality(q);
	}

}
