package com.talent.measure.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

import com.talent.base.model.PageModel;
import com.talent.measure.model.ConfigModel;
import com.talent.measure.model.Measure;
import com.talent.measure.model.MeasureTareHistory;
import com.talent.measure.model.PrintModel;
import com.talent.measure.model.TaskCode;

public interface MeasureDao {
	// 获取供应商、客户、库房、厂内单位
	public List<Measure> getfieldname(Measure measure) throws DataAccessException;

	// 获取物料
	public List<Map<String, String>> getmaterialname(Measure m);

	public PageModel getmaterialname(Measure m, PageModel pm) throws DataAccessException;

	// 获取毛重记录、皮重记录、净重记录
	public PageModel getMeasurerecords(Measure m, PageModel pm) throws DataAccessException;

	// 获取上次皮重时间和重量
	public Measure getTarebyCarno(Measure measure) throws DataAccessException;

	// 获取业务类型名称
	public String getOperaname(Measure measure) throws DataAccessException;

	// 获取L_PLAN_V的信息
	public List<Measure> getlplanv(Measure m) throws DataAccessException;
	
	public List<Measure> getBeforesave(Measure m) throws DataAccessException ;

	// 获取L_PLAN_V的信息
	public Measure getlplanvbyplanid(Measure m) throws DataAccessException;

	// 添加制卡信息
	public Measure saveMeasure(Measure measure) throws DataAccessException;

	// 获取是否需要出厂确认
	public int getGFlag(Measure measure) throws DataAccessException;

	// 获取结算方
	public int getAccountstype(Measure measure) throws DataAccessException;

	// 获取打印时需要的计量数据……
	public List<Measure> getPrintDataInfos(String matchId) throws DataAccessException;

	// 获取根据业务类型获取到的所有打印模板的数据……
	public List<PrintModel> getPrintModelInfos(String op) throws DataAccessException;

	// 获取历史皮重信息……
	public List<MeasureTareHistory> getTareHistory(MeasureTareHistory mts) throws DataAccessException;

	// 获取所有打印模板的数据……
	public List<PrintModel> getAllPrintModelData() throws DataAccessException;

	public HashMap<String,String> getConfigPrintStyle(String operatype) throws DataAccessException;

	// 根据模板编码获取模板有效存在条数……
	public String getModelCount(String modelCode) throws DataAccessException;

	// 新增打印模板
	public int addPrintModel(PrintModel pm) throws DataAccessException;

	// 修改打印模板
	public int modifyPrintModel(PrintModel pm) throws DataAccessException;

	public List<Map<String, String>> getOperateType() throws DataAccessException;

	public List<Map<String, String>> getMaterialClasses(String search) throws DataAccessException;

	public List<Map<String, String>> getMaterialClassesByName() throws DataAccessException;

	// 获取计划量的和的信息
	public Measure getPlanSumInfo(String planId) throws DataAccessException;

	// 根据查询条件获取查询数据
	public List<Measure> getSearchInfo(Measure measure) throws DataAccessException;

	public HashMap<String, String> getPrintBillStyle(String matchid) throws DataAccessException;

	public HashMap<String, String> get(String matchid) throws DataAccessException;

	public List<Measure> getByCarno(String carno) throws DataAccessException;

	public Measure getByMatchid(String matchid) throws DataAccessException;

	public HashMap<String, String> get(String matchid, String columns) throws DataAccessException;
	// 查询是否有配置模板

	public int selectConfigmodel(ConfigModel configmodel) throws DataAccessException;

	// 插入配置信息
	public int insertConfigmodel(ConfigModel configmodel) throws DataAccessException;

	// 修改配置信息
	public int updateconfigmodel(ConfigModel configmodel) throws DataAccessException;

	public List<TaskCode> getAllTaskcodes() throws DataAccessException;

	// 根据matchid查询合金明细信息
	public List<Measure> queryscaledetail(Measure measure) throws DataAccessException;

	// 添加合金明细信息
	public int insertscaledetail(Measure measure) throws DataAccessException;

	/**
	 * 作废合金信息
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int cancelWeighterdata(Measure measure) throws DataAccessException;
	/**
	 * 根据业务号查询业务号信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public Measure queryTaskcode(Measure measure) throws DataAccessException;
	/**
	 * 根据车号查询最新的毛重
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public String queryMaxgrosstime(String carno) throws DataAccessException;
	/**
	 * 根据车号查询业务临时表是否有单独计皮的信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public String queryzcount(String carno) throws DataAccessException;
	/**
	 * 根据业务号查询打印次数
	 * @param matchId
	 * @return
	 * @throws DataAccessException
	 */
	public int queryPrintnum(String taskcode) throws DataAccessException;
	
	/**
	 * 
	  * @Title: getCompleteMeasure
	  * @Description:(根据车号查询此车无法过磅的数据信息)
	  * @param @param m
	  * @param @return
	  * @param @throws DataAccessException参数
	  * @return List<Measure>返回类型
	  * @throws
	 */
	public List<Measure> getCompleteMeasure(Measure m) throws DataAccessException ;
}

