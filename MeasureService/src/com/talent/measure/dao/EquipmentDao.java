package com.talent.measure.dao;

import org.springframework.dao.DataAccessException;

import com.talent.base.model.PageModel;
import com.talent.measure.model.Equipment;

public interface EquipmentDao {

	PageModel queryEquiment(Equipment eq, PageModel pm) throws DataAccessException;

	/**
	 * 添加衡器
	 * @param eq
	 * @return
	 * @throws DataAccessException
	 */
	int insertEquiment(Equipment eq) throws DataAccessException;

	/**
	 * 修改衡器
	 * @param eq
	 * @return
	 * @throws DataAccessException
	 */
	int updateEquiment(Equipment eq) throws DataAccessException;

	/**
	 * 作废衡器
	 * @param eq
	 * @return
	 * @throws DataAccessException
	 */
	int cancelEquiment(Equipment eq) throws DataAccessException;
	/**
	 * 根据衡器id查询衡器信息
	 * @param eq
	 * @return
	 * @throws DataAccessException
	 */
	public Equipment queryEquimentByid(Equipment eq)throws DataAccessException;
	/**
	 * 通过衡器编码查询是否存在
	 * @param eq
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCBycode(Equipment eq)throws DataAccessException;
	/**
	 * 通过衡器名称查询是否存在
	 * @param eq
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCByname(Equipment eq)throws DataAccessException;
	

}