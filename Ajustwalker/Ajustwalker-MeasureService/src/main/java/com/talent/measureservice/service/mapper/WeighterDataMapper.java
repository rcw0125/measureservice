package com.talent.measureservice.service.mapper;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.talent.measureservice.model.WeighterData;

public interface WeighterDataMapper {

	/**
	 * 查询重量信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<WeighterData> queryWeighterlist(WeighterData weight) throws DataAccessException;


	/**
	 * 保存重量信息
	 * @param weight
	 * @return
	 * @throws DataAccessException
	 */

	public int insertWeightdata(WeighterData weight) throws DataAccessException;

}
