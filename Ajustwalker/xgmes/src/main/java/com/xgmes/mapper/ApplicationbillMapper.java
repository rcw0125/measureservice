package com.xgmes.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xgmes.model.Applicationbill;
import com.xgmes.model.Operaconfig;
import com.xgmes.model.Workline;

public interface ApplicationbillMapper {
	/**
	 * 查询制卡表主子表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Applicationbill> queryList(Applicationbill app) throws DataAccessException;

	/**
	 * 查询其他单据表主子表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Applicationbill> queryDocumentList(Applicationbill app) throws DataAccessException;

	/**
	 * 根据物流号查询信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Applicationbill> queryInfoBymatchid(Applicationbill app) throws DataAccessException;

	/**
	 * 根据物流号查询单据
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Applicationbill> queryDocumentBymatchid(Applicationbill app) throws DataAccessException;

	/**
	 * 根据物流号查询使用的单据类型
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Applicationbill> queryPlanid(Applicationbill app) throws DataAccessException;

	/**
	 * 添加制卡主表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public int insertApplicationbill(Applicationbill app) throws DataAccessException;

	/**
	 * 添加制卡子表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertApplicationbillitem(Applicationbill app) throws DataAccessException;

	/**
	 * 添加业务临时表中
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertCurrtemp(Applicationbill app) throws DataAccessException;

	/**
	 * 作废制卡主表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int cancelApplicationbill(Applicationbill app) throws DataAccessException;

	/**
	 * 修改单据状态
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateIsoruse(Applicationbill app) throws DataAccessException;

	/**
	 * 修改虚拟单据状态
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateVIsoruse(Applicationbill app) throws DataAccessException;

	/**
	 * 修改接口单据状态
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateIsoruseInter(Applicationbill app) throws DataAccessException;

	/**
	 * 作废制卡子表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int cancelapplicationbillitem(Applicationbill app) throws DataAccessException;

	/**
	 * 作废业务临时表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int cancelCurrtemp(Applicationbill app) throws DataAccessException;

	/**
	 * 修改制卡主表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateApplicationbill(Applicationbill app) throws DataAccessException;

	/**
	 * 修改制卡子表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateApplicationbillitem(Applicationbill app) throws DataAccessException;

	/**
	 * 修改业务临时表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateCurrtemp(Applicationbill app) throws DataAccessException;

	/**
	 * 作废进出厂日志信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int cancelEntrylog(Applicationbill app) throws DataAccessException;

	/**
	 * 一级审核
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateFirst(Applicationbill app) throws DataAccessException;

	/**
	 * 二级审核
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updatesecond(Applicationbill app) throws DataAccessException;

	/**
	 * 一级弃审
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int giveupfirst(Applicationbill app) throws DataAccessException;

	/**
	 * 二级弃审
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int giveupsecond(Applicationbill app) throws DataAccessException;

	/**
	 * 查询单据是否制卡
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCountBymatchid(Applicationbill app) throws DataAccessException;

	/**
	 * 查询是否制卡
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCountByitemid(Applicationbill app) throws DataAccessException;

	/**
	 * 查询路线id通过业务类型和物流编码
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public Workline queryRouteidBymateiral(Workline work) throws DataAccessException;

	/**
	 * 查询路线id通过业务类型和大类编码
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public Workline queryRouteidByfolder(Workline work) throws DataAccessException;

	/**
	 * 根据业务类型查询计量顺序
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public Applicationbill queryConfig(Applicationbill app) throws DataAccessException;

	/*
	 * 根据车号查询业务类型
	 */

	public List<Operaconfig> queryOperatypeBycarno(Operaconfig oper) throws DataAccessException;

	/**
	 * 查询单据信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryFdocumentBymatchid(Applicationbill app) throws DataAccessException;

	/**
	 * 根据计划号和子行号查询制卡信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryAppinfoBypitemid(Applicationbill app) throws DataAccessException;

	public List<Applicationbill> queryAllAppBypitemid(Applicationbill app) throws DataAccessException;

	public List<Applicationbill> queryDocumentBymatchids(String[] matchids) throws DataAccessException;

	public String queryRfidBycarno(String carno) throws DataAccessException;

	public List<String> queryUncomplete(String carno) throws DataAccessException;
    /**
     * 通过id查询
     * @param id
     * @return
     * @throws DataAccessException
     */
	public int queryIdcount(Long id) throws DataAccessException;
    /**
     * 查询原始单据号
     * @param matchids
     * @return
     * @throws DataAccessException
     */
	public List<Applicationbill> queryFdocument(Applicationbill app) throws DataAccessException;
	
	  /**
     * 查询原始单据号类型
     * @param matchids
     * @return
     * @throws DataAccessException
     */
	public String queryFdocumentcode(String documentcode) throws DataAccessException;
	
	/**
	 * 修改计量表业务信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int updateMeasure(Applicationbill app) throws DataAccessException;
	
}
