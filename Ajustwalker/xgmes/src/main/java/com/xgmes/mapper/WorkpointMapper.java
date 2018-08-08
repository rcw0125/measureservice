package com.xgmes.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xgmes.model.Workpoint;

public interface WorkpointMapper {

	public List<Workpoint> queryList(Workpoint workpoint) throws DataAccessException;

	public Workpoint queryInfoByid(Workpoint workpoint) throws DataAccessException;
	
	public Workpoint queryInfoByIp(Workpoint workpoint) throws DataAccessException;
	
	public int insertWorkpoint(Workpoint workpoint) throws DataAccessException;

	public int updateWorkpoint(Workpoint workpoint) throws DataAccessException;
	
	public int approveWorkpoint(Workpoint workpoint) throws DataAccessException;
	
	public int giveupWorkpoint(Workpoint workpoint) throws DataAccessException;
	
	public int registeWorkpoint(Workpoint workpoint) throws DataAccessException;
	
	public int cancelWorkpoint(Workpoint workpoint) throws DataAccessException;

	public int queryCount(Workpoint workpoint) throws DataAccessException;
	
	public List<Workpoint> queryWorkpointsByType(Workpoint workpoint) throws DataAccessException;
	
	public Workpoint queryInfoBycode(Workpoint workpoint) throws DataAccessException;
	
}
