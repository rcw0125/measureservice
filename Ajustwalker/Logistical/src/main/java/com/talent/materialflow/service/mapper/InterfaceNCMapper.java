package com.talent.materialflow.service.mapper;

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import com.talent.materialflow.model.Applicationbill;

public interface InterfaceNCMapper {
	
	public List<Map<String,Object>> queryUnuploadData() throws DataAccessException;
	
	public List<Map<String,Object>> queryMaterialInfoByPlanid(String planid) throws DataAccessException;
	
	public void updateMeasureUpflag(String matchid) throws DataAccessException;
	
	public void updateStoreinUpflag(String matchid) throws DataAccessException;
	
	public List<Applicationbill> queryInterfaceList(Applicationbill app) throws DataAccessException;
	
	public List<Map<String,Object>> queryUploadData(Applicationbill app) throws DataAccessException;
	
	public void insertUploadLog(Applicationbill app) throws DataAccessException;
	
	public void insertTrainTask(String filename) throws DataAccessException;
	
	public List<Map<String,String>> queryTrainTask() throws DataAccessException;
}