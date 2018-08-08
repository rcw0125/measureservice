package com.talent.report.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.report.model.Taskinfo;


public interface StatisticsReportMapper {
	
	Taskinfo queryavgtaskinfo(Taskinfo tinfo) throws DataAccessException;

	public List<Taskinfo> querytaskdata(Taskinfo tinfo) throws DataAccessException;
	
	public List<Taskinfo> querytaskinfo(Taskinfo tinfo) throws DataAccessException;

}
