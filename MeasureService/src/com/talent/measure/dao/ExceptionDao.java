package com.talent.measure.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.talent.base.model.PageModel;
import com.talent.measure.model.Exceptions;
import com.talent.measure.model.MeasurePhoto;
import com.talent.measure.model.Operaconfig;

public interface ExceptionDao {

	//查询可以修改的信息列表
	public PageModel queryException(Exceptions m, PageModel pm) throws DataAccessException;

	//根据物流号查询修改的数据信息
	public Exceptions queryInfo(String m) throws DataAccessException;
	
	//保存计量信息
	public Exceptions saveException(Exceptions exception) throws DataAccessException;
	/*
	 * 查询计量衡器配置信息
	 */

	public PageModel queryMeasureweighinfo(Exceptions m, PageModel pm) throws DataAccessException;
	/*
	 * 修改衡器配置状态
	 * */
	public int cancelMeasureweigh(Exceptions e)throws DataAccessException;
	/*
	 * 添加衡器配置
	 * */
	public int insertMeasureweigh(Exceptions e)throws DataAccessException;
	/*
	 * 修改衡器配置
	 * */
	public int updateMeasureweigh(Exceptions e)throws DataAccessException;
	/*
	 * 根据物流号查询修改的数据信息
	 */
	
	public Exceptions queryMweighinfoByid(String m) throws DataAccessException ;
	/*
	 * 查询业务类型
	 * */
	
	public List<MeasurePhoto> queryOperatype() throws DataAccessException;
	/*
	 * 查询衡器集合
	 * */

	public List<MeasurePhoto> queryEquipment() throws DataAccessException;
	/*
	 * 业务类型业务控制配置
	 * */

	public Operaconfig queryOperaconfig(String operatype) throws DataAccessException;
	/*
	 * 修改流程业务配置
	 * */
	public int updateOperaconfig(Operaconfig e)throws DataAccessException;
	/*
	 * 根据衡器编码查询衡器名称
	 */


	public List<Exceptions> queryEqumentByid(Exceptions m) throws DataAccessException;

}