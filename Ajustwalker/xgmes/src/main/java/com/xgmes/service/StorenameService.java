package com.xgmes.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.talent.core.model.Message;
import com.xgmes.model.Storename;

@Repository
public interface StorenameService{
	Message insertStorename(Storename storename) throws DataAccessException;
	
}
