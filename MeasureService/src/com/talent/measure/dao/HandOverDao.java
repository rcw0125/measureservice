package com.talent.measure.dao;

import org.springframework.dao.DataAccessException;

import com.talent.base.model.PageModel;
import com.talent.measure.model.HandOver;

public interface HandOverDao {

	PageModel queryHandover(HandOver m, PageModel pm) throws DataAccessException;

	int insertHandover(HandOver h) throws DataAccessException;

	int cancelHandover(HandOver h) throws DataAccessException;
	public HandOver queryHandoverByid(HandOver h) throws DataAccessException;

}