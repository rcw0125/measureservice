package com.talent.materialflow.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.materialflow.model.Interface;
public interface BillMapper {
	/**
	 * 查询添加单据信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Interface> queryBillList(Interface inter) throws DataAccessException;
    
	/**
	 * 根据id查询主表信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public Interface queryMainByPlanid(Interface inter) throws DataAccessException;
	/**
	 * 根据id查询子表信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Interface> queryItemByFid(Interface inter) throws DataAccessException;
	/**
	 * 添加接口主表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertInterfaceinfo(Interface inter) throws DataAccessException;
	
	/**
	 * 添加接口子表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertInterfaceinfoitem(Interface inter) throws DataAccessException;
	/**
	 * 修改接口主表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateInterfaceinfo(Interface inter) throws DataAccessException;
	
	/**
	 * 修改接口子表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateInterfaceinfoitem(Interface inter) throws DataAccessException;
	
	/**
	 * 作废接口主表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int cancelInterfaceinfo(Interface inter) throws DataAccessException;
	
	/**
	 * 作废接口子表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int cancelInterfaceinfoitem(Interface inter) throws DataAccessException;

}
