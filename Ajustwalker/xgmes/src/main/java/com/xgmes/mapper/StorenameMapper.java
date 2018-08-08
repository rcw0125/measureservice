package com.xgmes.mapper;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.xgmes.model.Storename;

public interface StorenameMapper {

	/*
	 * 查询
	 */
	public List<Storename> queryList(Storename storename) throws DataAccessException;

	/*
	 * 添加时查询是否有名称相同的记录，用于判断是否允许添加
	 */
	public int queryCountAdd(Storename storename) throws DataAccessException;

	/*
	 * 添加
	 */
	public int insertStorename(Storename storename) throws DataAccessException;

	/*
	 * 修改时，查询信息
	 */
	public Storename queryInfoByStorename(Storename storename) throws DataAccessException;

	/*
	 * 修改时查询是否有名称相同的记录（除了本条记录），用于判断是否允许修改
	 */
	public int queryCountUpdate(Storename storename) throws DataAccessException;

	/*
	 * 修改
	 */
	public int updateStorename(Storename storename) throws DataAccessException;

	/*
	 * 作废
	 */
	public int cancelStorename(Storename storename) throws DataAccessException;

}
