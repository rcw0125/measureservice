package com.talent.materialflow.service.mapper;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.talent.materialflow.model.Material;

/*
 * @author:chengyanli
 * @date :2016-08-02
 */
public interface MaterialMapper {
	/*
	 * 查询
	 */
	public List<Material> queryList(Material material) throws DataAccessException;

	/*
	 * 添加时查询是否有名称相同的记录，用于判断是否允许添加
	 */
	public int queryCountAdd(Material material) throws DataAccessException;

	/*
	 * 添加
	 */
	public int insertMaterial(Material material) throws DataAccessException;

	/*
	 * 修改时，查询信息
	 */
	public Material queryInfoByMaterial(Material material) throws DataAccessException;

	/*
	 * 修改时查询是否有名称相同的记录（除了本条记录），用于判断是否允许修改
	 */
	public int queryCountUpdate(Material material) throws DataAccessException;

	/*
	 * 修改
	 */
	public int updateMaterial(Material material) throws DataAccessException;

	/*
	 * 作废
	 */
	public int cancelMaterial(Material material) throws DataAccessException;

}
