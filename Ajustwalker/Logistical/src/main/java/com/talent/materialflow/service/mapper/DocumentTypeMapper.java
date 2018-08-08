package com.talent.materialflow.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.materialflow.model.Documenttype;

public interface DocumentTypeMapper {

	public List<Documenttype> queryList(Documenttype dtype) throws DataAccessException;

	public Documenttype queryInfoByid(Documenttype dtype) throws DataAccessException;

	public int insertDocumenttype(Documenttype dtype) throws DataAccessException;

	public int updateDocumenttype(Documenttype dtype) throws DataAccessException;
	
	public int cancelDocumenttype(Documenttype dtype) throws DataAccessException;

	public int queryCount(Documenttype dtype) throws DataAccessException;
	
	public int queryCountById(Documenttype dtype) throws DataAccessException;

}
