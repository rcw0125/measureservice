package com.talent.materialflow.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.talent.core.model.Message;
import com.talent.materialflow.model.Material;

@Repository
public interface Material2Service{
	
	Message insertMaterial(Material material) throws DataAccessException;
}
