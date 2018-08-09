package com.talent.measure.dao.impl;

import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.StatisticsMReportDao;
import com.talent.measure.model.Statistics;
import com.talent.measure.model.Taskinfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Service
@Component
@SuppressWarnings({ "deprecation", "unchecked" })
public class StatisticsMReportDaoImpl extends BaseDaoiBatis implements StatisticsMReportDao  {

	
	
	@Override
	public List<Taskinfo> querytaskinfo(Taskinfo tinfo )throws DataAccessException {
		return (List<Taskinfo>) getOracleSqlMapClientTemplate().queryForList("statisReport.querytaskinfo",tinfo);
	}
	
	 
	
	@Override
	public Taskinfo queryavgtaskinfo(Taskinfo tinfo )throws DataAccessException {
		return (Taskinfo) getOracleSqlMapClientTemplate().queryForObject("statisReport.queryavgtaskinfo",tinfo);
	}
	
	
	@Override
	public List<Taskinfo> querytaskdata(Taskinfo tinfo )throws DataAccessException {
		return (List<Taskinfo>) getOracleSqlMapClientTemplate().queryForList("statisReport.querytaskdata",tinfo);
	}
	
	public List<Statistics> queryMeasureByOperatype(Statistics tinfo)throws DataAccessException {
		
		List<Statistics> result = null;
		try {
			result = getOracleSqlMapClientTemplate().queryForList("statisReport.queryMeasureByOperatype", tinfo);
		} catch (Exception e) {
			result = new ArrayList<Statistics>();
		}
		return result;
	}
	public List<Statistics> queryunitweight(Statistics tinfo)throws DataAccessException {
		return getOracleSqlMapClientTemplate().queryForList("statisReport.queryunitweight", tinfo);
	}
	
	@SuppressWarnings("rawtypes")
	public Statistics createunitweight(Statistics sta) throws DataAccessException {
		Map map = new HashMap();
		map.put("v_datetime",sta.getDatetime() );	
		getOracleSqlMapClientTemplate().insert("statisReport.createunitweight",map);
		String flag  = (String)map.get("v_flag");
		sta.setFlag(flag);
		return sta;
	}
	

}
