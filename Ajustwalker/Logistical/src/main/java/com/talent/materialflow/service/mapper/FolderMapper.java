package com.talent.materialflow.service.mapper;


import org.springframework.dao.DataAccessException;

public interface FolderMapper {
	
	public void downloadfolder() throws DataAccessException;
	
	public void downloadmaterial() throws DataAccessException;

}
