package com.talent.measure.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.CommonDao;
import com.talent.measure.model.Statistics;

@Service
@Component
@SuppressWarnings({ "deprecation", "unchecked" })
public class CommonDaoImpl extends BaseDaoiBatis implements CommonDao {

	public synchronized String newMatchid(String prefix) {
		int nextValue = txNextValue(prefix);
		String nextValueStr = String.format("%0" + 4 + "d", nextValue);
		return prefix + sysdate("yyMMdd") + nextValueStr;
	}

	public synchronized int txNextValue(String name) throws DataAccessException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name_var", name);
		getOracleSqlMapClientTemplate().queryForObject("common.sp_nextval_p", map);
		Object nextVal = 0;
		try {
			nextVal = map.get("nextval_var");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int) nextVal;
	}

	/**
	 * 获取oracle数据库时间
	 */
	@Override
	public String getOracleDateTime() {

		return (String) getOracleSqlMapClientTemplate().queryForObject("common.getOracleDateTime");
	}

	/**
	 * 获取oracle数据库时间
	 */
	@Override
	public Date sysdate() {

		return (Date) getOracleSqlMapClientTemplate().queryForObject("common.sysdate");
	}

	@Override
	public String sysdate(String format) {
		String result = "";
		try {
			Date currentDate = sysdate();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			result = sdf.format(currentDate);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	// 获取业务临时表所有的字段
	public List<HashMap<String, String>> queryColumnsList() throws DataAccessException {
		return (List<HashMap<String, String>>) getOracleSqlMapClientTemplate()
				.queryForList("common.getCurrTempColumns");
	}

	// 根据物流号和衡器id查询任务状态
	public Statistics selectTaskStaus(Statistics task) throws DataAccessException {
		return (Statistics) getOracleSqlMapClientTemplate().queryForObject("common.selectTaskStaus", task);
	}

	// 根据物流号和衡器id修改任务状态
	public int updateTaskStatus(Statistics task) throws DataAccessException {
		int flag = 0;
		Statistics sta = (Statistics) getOracleSqlMapClientTemplate().queryForObject("common.selectTaskStaus", task);
		if (sta != null) {
			flag = getOracleSqlMapClientTemplate().update("common.updateTaskStatus", task);
		} else {
			getOracleSqlMapClientTemplate().insert("common.insertTaskStatus", task);
			flag = 1;
		}
		return flag;
	}

}
