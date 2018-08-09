package com.talent.measure.dao;

import org.springframework.dao.DataAccessException;

import com.talent.base.model.PageModel;

import com.talent.measure.model.Msameweight;

public interface MsameweightDao {

	int insertConfigmodel(Msameweight ms) throws DataAccessException;
	PageModel querySameweight(Msameweight m, PageModel pm) throws DataAccessException ;

}