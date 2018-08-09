package com.talent.measure.dao;

import org.springframework.dao.DataAccessException;

import com.talent.base.model.PageModel;
import com.talent.measure.model.Reportunit;

public interface ReportunitDao {

	
	PageModel queryReportunit(Reportunit re, PageModel pm) throws DataAccessException;

	int insertReportunit(Reportunit re) throws DataAccessException;
	
	int updateReportunit(Reportunit re) throws DataAccessException;
	
	int cancelReportunit(Reportunit re) throws DataAccessException;

	
	Reportunit queryReportunitByid(Reportunit re) throws DataAccessException;

	
	int queryCount(Reportunit re) throws DataAccessException;

}