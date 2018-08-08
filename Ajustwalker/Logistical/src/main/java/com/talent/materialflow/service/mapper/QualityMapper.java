package com.talent.materialflow.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.talent.materialflow.model.Quality;

public interface QualityMapper {

	public List<Quality> queryList(Quality q) throws DataAccessException;

	public int insertQuality(Quality q) throws DataAccessException;

	public int cancelQuality(Quality q) throws DataAccessException;

	public int updateCurrtemp(Quality q) throws DataAccessException;
	
	public int cancelCurrtemp(Quality q) throws DataAccessException;

	public List<Quality> queryInfoBycarno(Quality q) throws DataAccessException;

	public Quality queryInfoBymatchid(Quality q) throws DataAccessException;
	
	

}
