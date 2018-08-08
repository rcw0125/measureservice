package com.talent.measureservice.service.mapper;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.talent.measureservice.model.ConfigModel;





public interface ConfigcommonMapper {

	

	
	/**
	 * 根据编码和名称查询收货单位
	 * 
	 * @param box
	 * @return
	 * @throws DataAccessException
	 */
	public List<ConfigModel> queryConfigmodel(ConfigModel cmodel) throws DataAccessException;
	
	/**
	 * 获取数据库当前时间
	 * 
	 * @param box
	 * @return
	 * @throws DataAccessException
	 */
	public String queryCurrtime() throws DataAccessException;
	
	
}
