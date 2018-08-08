package com.talent.measureservice.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.talent.measureservice.service.BcommonService;
import com.talent.measureservice.service.mapper.BcommonMapper;


@Service
@Transactional
public class BcommonServiceImpl implements  BcommonService {
	@Resource
	private BcommonMapper bcommonMapper;

    /**
     * 获取matchid
     */
	@Override
	public String getMatchid(String operatype) throws DataAccessException {
		Map<String, String> parameterMap = new HashMap<String, String>();
		String prefix = String.valueOf(operatype);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		String dateStr = dateFormat.format(new Date());
		parameterMap.put("name", "matchid" + dateStr);
		parameterMap.put("nextval", "0");
		bcommonMapper.txNextValue(parameterMap);
		int nextValue = Integer.parseInt(parameterMap.get("nextval"));
		String nextValueStr = String.format("%0" + 5 + "d", nextValue);
		return prefix + dateStr + nextValueStr;
	}

	
	 /**
     * 获取火车批次号
     */
	@Override
	public String getBatchcode() throws DataAccessException {
		Map<String, String> parameterMap = new HashMap<String, String>();
		//String prefix = String.valueOf("T");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		String dateStr = dateFormat.format(new Date());
		parameterMap.put("name", "cartype" + dateStr);
		parameterMap.put("nextval", "0");
		bcommonMapper.txNextValue(parameterMap);
		int nextValue = Integer.parseInt(parameterMap.get("nextval"));
		String nextValueStr = String.format("%0" + 3 + "d", nextValue);
		return  dateStr + nextValueStr;
	}

	 /**
     * 获取编码
     */
	@Override
	public String getCode(String types) throws DataAccessException {
		Map<String, String> parameterMap = new HashMap<String, String>();
		String prefix = String.valueOf(types);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		String dateStr = dateFormat.format(new Date());
		parameterMap.put("name", types + dateStr);
		parameterMap.put("nextval", "0");
		bcommonMapper.txNextValue(parameterMap);
		int nextValue = Integer.parseInt(parameterMap.get("nextval"));
		String nextValueStr = String.format("%0" + 5 + "d", nextValue);
		return prefix + dateStr + nextValueStr;
	}
}
