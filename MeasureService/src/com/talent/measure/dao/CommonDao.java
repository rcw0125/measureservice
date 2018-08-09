package com.talent.measure.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.measure.model.Statistics;

public interface CommonDao {

	public String newMatchid(String prefix);

	public String getOracleDateTime();

	public String sysdate(String format);

	/**
	 * 获取oracle数据库时间
	 */

	public Date sysdate();

	// 获取业务临时表所有的字段
	public List<HashMap<String, String>> queryColumnsList() throws DataAccessException;

	// 根据物流号和衡器id查询任务状态
	public Statistics selectTaskStaus(Statistics task) throws DataAccessException;

	// 根据物流号和衡器id修改任务状态
	public int updateTaskStatus(Statistics task) throws DataAccessException;
}
