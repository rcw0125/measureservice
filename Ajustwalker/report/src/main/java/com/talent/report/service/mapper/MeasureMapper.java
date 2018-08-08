package com.talent.report.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.report.model.ComboxData;


public interface MeasureMapper {
	
	public List<ComboxData> queryOperatype(ComboxData combox) throws DataAccessException;
	
	public List<ComboxData> queryEquipment(ComboxData combox) throws DataAccessException;
	
	public List<ComboxData> queryMaterial(ComboxData combox) throws DataAccessException;
	
	public List<ComboxData> queryStorename(ComboxData combox) throws DataAccessException;
}
