package com.talent.measureservice.service.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import com.talent.measureservice.model.ComboxData;
import com.talent.measureservice.model.Customer;
import com.talent.measureservice.model.Material;
import com.talent.measureservice.model.Trainmodel;





public interface BcommonMapper {

	

	/**
	 * 调用存储过程 t_sp_nextval 获取序列值
	 *
	 * @param name
	 *            序列名
	 * @return
	 * @throws DataAccessException
	 */
	public void txNextValue(Map<String, String> parameterMap) throws DataAccessException;
	/**
	 * 根据编码和名称查询收货单位
	 * 
	 * @param box
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> querySourceinfo(ComboxData box) throws DataAccessException;
	/**
	 * 根据编码和名称查询收货单位
	 * 
	 * @param box
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryTargetinfo(ComboxData box) throws DataAccessException;
	/**
	 * 根据编码和名称查询物料信息
	 * 
	 * @param box
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryMateril(ComboxData box) throws DataAccessException;
	/**
	 * 根据编码和名称查询站点信息
	 * 
	 * @param box
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryStation(ComboxData box) throws DataAccessException;
	
	/*
	 * 查询业务类型
	 */

	public List<ComboxData> queryOperatype(ComboxData box) throws DataAccessException;
	
	/**
	 * 保存客户信息
	 * 
	 * @param customer
	 * @return
	 * @throws DataAccessException
	 */
	public int insertCustomer(Customer  customer) throws DataAccessException;
	/**
	 * 保存物料信息
	 * 
	 * @param material
	 * @return
	 * @throws DataAccessException
	 */
	public int insertMaterial(Material  material) throws DataAccessException;
	
	/**
	 * 根据名称查询物料是否已经存在
	 * 
	 * @param material
	 * @return
	 * @throws DataAccessException
	 */
	public int queryMaterialCount(Material  material) throws DataAccessException;
	
	/**
	 * 根据名称查询客商是否已经存在
	 * 
	 * @param customer
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCustomerCount(Customer  customer) throws DataAccessException;
	
	/**
	 * 根据名称查询站点是否已经存在
	 * 
	 * @param customer
	 * @return
	 * @throws DataAccessException
	 */
	public int querySationCount(Customer  customer) throws DataAccessException;
	
	/**
	 * 保存站点信息
	 * 
	 * @param customer
	 * @return
	 * @throws DataAccessException
	 */
	public int insertSation(Customer  customer) throws DataAccessException;
	/**
	 * 根据火车车型查询是否已经存在
	 * 
	 * @param tmomel
	 * @return
	 * @throws DataAccessException
	 */
	public Trainmodel queryTrainmodel(Trainmodel  tmomel) throws DataAccessException;

	/**
	 * 保存车型信息
	 * 
	 * @param customer
	 * @return
	 * @throws DataAccessException
	 */
	public int insertTrainmodel(Trainmodel  tmomel) throws DataAccessException;
	
}
