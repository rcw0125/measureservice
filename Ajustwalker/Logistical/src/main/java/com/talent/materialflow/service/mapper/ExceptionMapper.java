package com.talent.materialflow.service.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import com.talent.materialflow.model.Applicationbill;
import com.talent.materialflow.model.Exceptioninfo;
import com.talent.materialflow.model.Measure;
import com.talent.materialflow.model.Storein;

public interface ExceptionMapper {
	/**
	 * 进出厂异常处理
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Applicationbill> queryList(Applicationbill app) throws DataAccessException;

	/**
	 * 调拨异常处理
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Measure> queryExceptiondbList(Measure measure) throws DataAccessException;

	/**
	 * 查询火车信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Measure> queryExceptionhcList(Measure measure) throws DataAccessException;
	/**
	 * 根据物流号查询火车计量信息
	 * 
	 * @param storein
	 * @return
	 * @throws DataAccessException
	 */
	public Measure queryExcehcBymatchid(Measure measure) throws DataAccessException;
	
	/**
	 * 根据物流号查询入库信息
	 * 
	 * @param storein
	 * @return
	 * @throws DataAccessException
	 */
	public List<Storein> querySinInfoBymatchid(Storein storein) throws DataAccessException;

	/**
	 * 根据物流号查询出库信息
	 * 
	 * @param storein
	 * @return
	 * @throws DataAccessException
	 */
	public List<Storein> querySoutInfoBymatchid(Storein storein) throws DataAccessException;

	/**
	 * 根据物流号查询计量信息
	 * 
	 * @param storein
	 * @return
	 * @throws DataAccessException
	 */
	public Measure queryMeasureBymatchid(Measure measure) throws DataAccessException;

	/**
	 * 根据计划号和子行号查询制卡信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryAppinfoBypitemid(Applicationbill app) throws DataAccessException;

	/**
	 * 添加计量信息
	 * 
	 * @param parameterMap
	 * @throws DataAccessException
	 */
	public void insertMeasureException(Map<String, String> parameterMap) throws DataAccessException;

	/**
	 * 添加调拨计量信息
	 * 
	 * @param parameterMap
	 * @throws DataAccessException
	 */
	public void insertExceptionDB(Map<String, String> parameterMap) throws DataAccessException;

	/**
	 * 添加出厂信息
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public void insertExceptionOut(Map<String, String> parameterMap) throws DataAccessException;

	/**
	 * 根据物流号查询计量信息
	 * 
	 * @param storein
	 * @return
	 * @throws DataAccessException
	 */
	public Measure queryExcedbBymatchid(Measure measure) throws DataAccessException;

	/**
	 * 皮重异常
	 * 
	 * @param storein
	 * @return
	 * @throws DataAccessException
	 */
	public List<Measure> queryTareException(Measure measure) throws DataAccessException;

	/**
	 * 查询允许虚拟信息的数据
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryVirtualInfo(Applicationbill app) throws DataAccessException;

	/**
	 * 查询虚拟数据信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryVInfoBymatchid(Applicationbill app) throws DataAccessException;

	public String querymatchidByplanid(String planid) throws DataAccessException;

	public int cancelEntrylog(Applicationbill app) throws DataAccessException;

	public int cancelMeasure(Applicationbill app) throws DataAccessException;
	
	public int deleteMeasure(Applicationbill app) throws DataAccessException;

	public int cancelStorein(Applicationbill app) throws DataAccessException;

	public int cancelStoreinitem(Applicationbill app) throws DataAccessException;

	public int cancelStoreout(Applicationbill app) throws DataAccessException;

	public int cancelStoreoutitem(Applicationbill app) throws DataAccessException;

	/**
	 * 查询打印报表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Measure> queryMeasureDetail(Measure measure) throws DataAccessException;
    /**
     * 根据车号作废
     * @param carno
     * @return
     * @throws DataAccessException
     */
	public int cancelTare(String carno) throws DataAccessException;
    /**
     * 根据车号作废业务临时表单独计皮信息
     * @param carno
     * @return
     * @throws DataAccessException
     */
	public int cancelTareCurr(String carno) throws DataAccessException;
	
	 /**
     * 根据车号
     * @param carno
     * @return
     * @throws DataAccessException
     */
	public Measure queryTareBycarno(Measure measure) throws DataAccessException;
	
	/**
	 * 更新皮重信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int  updateTare(Measure measure) throws DataAccessException;
	
	/**
	 * 更新临时皮重信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int  updateCurrTare(Measure measure) throws DataAccessException;
	/**
	 * 查询线材信息
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryXCList(Applicationbill app) throws DataAccessException;
	
	/**
	 * 更新计量表扣重信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int  updateMDeducation(Measure measure) throws DataAccessException;
	
	/**
	 * 更新业务临时扣重信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int  updateCDeducation(Measure measure) throws DataAccessException;
	
	/**
	 * 根据物流号查询异常修改信息
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Exceptioninfo> queryExceptioninfo(String matchid) throws DataAccessException;
	
	/**
	 * 根据车号查询是否有未完成的线材信息
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCountXC(String carno) throws DataAccessException;
		
	/**
	 * 作废业务临时表
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int updateCurrMeasure(Applicationbill app) throws DataAccessException;
	
	
	/**
	 * 添加业务临时表中
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int insertCurrtemp(Measure measure) throws DataAccessException;
	/**
	 * 修改业务临时表
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int updateCurrtemp(Measure measure) throws DataAccessException;
	
	/**
	 * 添加计量表
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int insertMeasure(Measure measure) throws DataAccessException;
	
	/**
	 * 修改计量表
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int updateMeasure(Measure measure) throws DataAccessException;
	
	/**
	 * 数据迁移
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public void datatransfer(Map<String, String> parameterMap) throws DataAccessException;
	
	/**
	 * 数据删除
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public void datadelete(Map<String, String> parameterMap) throws DataAccessException;
	
}
