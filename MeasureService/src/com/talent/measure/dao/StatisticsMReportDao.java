package com.talent.measure.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.talent.measure.model.Statistics;
import com.talent.measure.model.Taskinfo;

public interface StatisticsMReportDao {

	/**
	 * 查询任务统计图数据
	 * @param tinfo
	 * @return
	 * @throws DataAccessException
	 */
	List<Taskinfo> querytaskinfo(Taskinfo tinfo) throws DataAccessException;

	Taskinfo queryavgtaskinfo(Taskinfo tinfo) throws DataAccessException;

	List<Taskinfo> querytaskdata(Taskinfo tinfo) throws DataAccessException;

	public List<Statistics> queryMeasureByOperatype(Statistics tinfo)throws DataAccessException;
    
	public List<Statistics> queryunitweight(Statistics tinfo)throws DataAccessException;
	
	public Statistics createunitweight(Statistics sta) throws DataAccessException; 
}