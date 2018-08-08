package com.xgmes.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.xgmes.model.Applicationbill;
import com.xgmes.model.BCommon;
import com.xgmes.model.Bcard;
import com.xgmes.model.Blacklist;
import com.xgmes.model.Cardhead;
import com.xgmes.model.ComboxData;
import com.xgmes.model.Entrylog;
import com.xgmes.model.Forcestop;
import com.xgmes.model.LogisticalRule;
import com.xgmes.model.LogisticalRuleDetail;
import com.xgmes.model.Measure;
import com.xgmes.model.Operaconfig;
import com.xgmes.model.Quality;
import com.xgmes.model.Storein;
import com.xgmes.model.Taskcode;
import com.xgmes.model.Workpoint;

public interface BCommonMapper {

	/**
	 * 添加黑名单
	 * 
	 * @param bc
	 * @return
	 * @throws DataAccessException
	 */
	public int insertBlacklist(Blacklist bc) throws DataAccessException;

	/**
	 * 添加终止信息
	 * 
	 * @param bc
	 * @return
	 * @throws DataAccessException
	 */
	public int insertForcestop(Forcestop ft) throws DataAccessException;

	/**
	 * 数据信息修改为禁止状态
	 * 
	 * @param ft
	 * @return
	 * @throws DataAccessException
	 */
	public int updateCurrStop(Forcestop ft) throws DataAccessException;

	/**
	 * 数据信息修改为禁止状态
	 * 
	 * @param ft
	 * @return
	 * @throws DataAccessException
	 */
	public int updateAppStop(Forcestop ft) throws DataAccessException;
	
	/**
	 * 数据信息还原禁止状态
	 * 
	 * @param ft
	 * @return
	 * @throws DataAccessException
	 */
	public int updateCurrRebackStop(Forcestop ft) throws DataAccessException;

	/**
	 * 数据信息还原禁止状态
	 * 
	 * @param ft
	 * @return
	 * @throws DataAccessException
	 */
	public int updateAppRebackStop(Forcestop ft) throws DataAccessException;

	/**
	 * 根据车号查询车卡信息
	 * 
	 * @param bc
	 * @return
	 * @throws DataAccessException
	 */
	public Blacklist queryCardinfo(Blacklist bc) throws DataAccessException;

	/**
	 * 根据车号查询车卡信息
	 * 
	 * @param bc
	 * @return
	 * @throws DataAccessException
	 */
	public String queryRfidBycarno(String carno) throws DataAccessException;

	/**
	 * 根据车号查询车辆黑名单状态
	 * 
	 * @param bc
	 * @return
	 * @throws DataAccessException
	 */
	public Blacklist queryBlackinfo(Blacklist bc) throws DataAccessException;
	/*
	 * 查询业务类型
	 */

	public List<Operaconfig> queryOperatype(Operaconfig oper) throws DataAccessException;

	/**
	 * 查询单据类型
	 * 
	 * @param dtype
	 * @return
	 * @throws DataAccessException
	 */
	public List<Operaconfig> queryDtype(Operaconfig oper) throws DataAccessException;

	/*
	 * 查询业务类型
	 */

	public List<Blacklist> queryCarinfo(Blacklist bc) throws DataAccessException;

	/**
	 * 调用存储过程 t_sp_nextval 获取序列值
	 *
	 * @param name
	 *            序列名
	 * @return
	 * @throws DataAccessException
	 */
	public void txNextValue(Map<String, String> parameterMap) throws DataAccessException;

	public List<Operaconfig> queryNode(String oper) throws DataAccessException;

	/**
	 * 
	 * @param oper
	 * @return
	 * @throws DataAccessException
	 */
	public String queryModelcontent(Operaconfig oper) throws DataAccessException;

	/**
	 * 查询进出厂详情
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public List<Entrylog> queryEntryDetail(String matchid) throws DataAccessException;

	/**
	 * 查询取样详情
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public List<Quality> queryQualityDetail(String matchid) throws DataAccessException;

	/**
	 * 查询制卡详情
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryMakecardDetail(String matchid) throws DataAccessException;

	/**
	 * 查询计量详情
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public List<Measure> queryMeasureDetail(String matchid) throws DataAccessException;

	public List<Map<String, Object>> queryStoreDetail(String matchid) throws DataAccessException;

	/**
	 * 查询入库详情
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public List<Storein> queryStoreinDetail(String matchid) throws DataAccessException;

	/**
	 * 查询出库详情
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public List<Storein> queryStoreoutDetail(String matchid) throws DataAccessException;

	/**
	 * 根据数据的供货单位编码或者名称查询供应商信息
	 * 
	 * @param sourcename
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> querySourceinfo(ComboxData box) throws DataAccessException;

	/**
	 * 根据数据的供货单位编码或者名称查询库房作业点
	 * 
	 * @param sourcename
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> querySworkpoint(ComboxData box) throws DataAccessException;

	/**
	 * 根据收货单位编码或者收货单位名称查询收货单位信息
	 * 
	 * @param targetname
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryTargetinfo(ComboxData box) throws DataAccessException;

	/**
	 * 根据厂内单位编码或者厂内单位名称查询厂内信息
	 * 
	 * @param storename
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryStoreinfo(ComboxData box) throws DataAccessException;

	/**
	 * 查询大门
	 * 
	 * @param storename
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryGatename(ComboxData box) throws DataAccessException;
	
	/**
	 * 查询大门
	 * 
	 * @param storename
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryInOutGatename(ComboxData box) throws DataAccessException;
	

	/**
	 * 根据物料编码或者物料名称查询物流信息
	 * 
	 * @param materialname
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryMaterilinfo(ComboxData box) throws DataAccessException;
    
	/**
	 * 根据物料编码或者物料名称查询物流信息
	 * 
	 * @param materialname
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryBMaterilinfo(ComboxData box) throws DataAccessException;

	/**
	 * 根据路线id和路线名称查询路线
	 * 
	 * @param materialname
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryWorkline(ComboxData box) throws DataAccessException;

	/**
	 * 查询用户信息
	 * 
	 * @param box
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryUsername(ComboxData box) throws DataAccessException;
	
	/**
	 * 查询站点信息
	 * 
	 * @param box
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryStation(ComboxData box) throws DataAccessException;
	

	public List<BCommon> selectMateril(BCommon bc) throws DataAccessException;

	/**
	 * 根据车队编号或者车队名称查询车队信息
	 * 
	 * @param materialname
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryMotorcadeinfo(ComboxData box) throws DataAccessException;

	/**
	 * 查询环节信息
	 * 
	 * @param materialname
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryLinkinfo(ComboxData box) throws DataAccessException;

	/**
	 * 查询环节信息
	 * 
	 * @param materialname
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryEqunameinfo(ComboxData box) throws DataAccessException;
	
	/**
	 * 根据类型查询衡器
	 * 
	 * @param materialname
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryEqunameinfoByType(ComboxData box) throws DataAccessException;
	

	/**
	 * 查询读卡器类型
	 * 
	 * @param o
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryReaderType(ComboxData box) throws DataAccessException;

	/**
	 * 查询作业点
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryWorkpoints(ComboxData box) throws DataAccessException;
	
	/**
	 * 查询厂内单位
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryStorenames(ComboxData box) throws DataAccessException;


	/**
	 * 查询火车物料信息
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryHcMateril(ComboxData box) throws DataAccessException;
	
	/**
	 * 查询火车客商信息
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryHcCustomer(ComboxData box) throws DataAccessException;
	 
	/**
	 * 查询计量单位
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<ComboxData> queryMeasureunit() throws DataAccessException;

	/**
	 * 根据计划查询条件查询计划信息
	 * 
	 * @param bcommon
	 * @return
	 * @throws DataAccessException
	 */
	public List<BCommon> queryPlaninfo(BCommon bcommon) throws DataAccessException;

	public List<LogisticalRule> queryFunction() throws DataAccessException;

	public List<LogisticalRuleDetail> queryFunctionDetail() throws DataAccessException;

	public BCommon queryAuditlevel(BCommon bcommon) throws DataAccessException;

	/**
	 * 查询车号是否在发卡
	 * 
	 * @param card
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCardBycarno(Blacklist bc) throws DataAccessException;

	/**
	 * 查询车号是否在黑名单
	 * 
	 * @param card
	 * @return
	 * @throws DataAccessException
	 */
	public int queryBlackBycarno(Blacklist bc) throws DataAccessException;

	/**
	 * 查询车号是业务临时表信息
	 * 
	 * @param card
	 * @return
	 * @throws DataAccessException
	 */
	public Forcestop queryInfoByMatchid(Forcestop ft) throws DataAccessException;

	/**
	 * 查询车牌
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Cardhead> queryCardhead() throws DataAccessException;

	/**
	 * 查询根据作业点编码查询读卡器类型
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public Workpoint queryRTypeBycode(Workpoint point) throws DataAccessException;

	/**
	 * 查询可以制卡的车号
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, String>> queryCarno(BCommon bc) throws DataAccessException;

	/**
	 * 查询业务号信息
	 * 
	 * @param taskcode
	 * @return
	 * @throws DataAccessException
	 */
	public List<Taskcode> queryTaskcode(Taskcode taskcode) throws DataAccessException;

	/**
	 * 查询返回质检系统进厂业务信息
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public Applicationbill queryInfoReturnQA(String matchid) throws DataAccessException;

	/**
	 * 查询返回质检系统出厂业务信息
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public Applicationbill queryOutInfoReturnQA(String matchid) throws DataAccessException;

	public String queryOrsample(String routeid) throws DataAccessException;

	public String queryInfoBymateiracode(String routeid) throws DataAccessException;

	public List<String> selectQAmatchid() throws DataAccessException;

	public List<String> insertAutolog(ComboxData box) throws DataAccessException;

	public Applicationbill queryUpNode(Applicationbill application) throws DataAccessException;

	/**
	 * 根据物流号获取节点信息
	 * 
	 * @param matchids
	 * @return
	 * @throws DataAccessException
	 */
	public Applicationbill queryNodeLists(String matchid) throws DataAccessException;

	/**
	 * 通过saleitemid查询物流号
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public String querymatchidbyitemid(String saleitemid) throws DataAccessException;

	/**
	 * 通过进厂id查询物流号
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public String querymatchidbyid(String id) throws DataAccessException;

	/**
	 * 通过出厂id查询物流号
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public String querymatchidbyeid(String eid) throws DataAccessException;
	
	/**
	 * 通过车号查询卡号
	 * 
	 * @param matchid
	 * @return
	 * @throws DataAccessException
	 */
	public Bcard queryIcidBycarno(String carno) throws DataAccessException;
	
	
	/**
	 * 根据车号、业务号查询皮重信息
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public Measure getTareBYCarnoT(Measure measure) throws DataAccessException;
	
	/**
	 * 根据车号、业务号查询皮重及业务信息
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public Measure getCurrtempBYCarnoT(Measure measure) throws DataAccessException;
	
	
	public int insertExceptinonlog(BCommon bcommon) throws DataAccessException;
	
	/**
	 * 查询可以制卡的车号
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, String>> queryStoreinCarno(BCommon bc) throws DataAccessException;
	
	/**
	 * 查询可以制卡的车号
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, String>> queryStoreoutCarno(BCommon bc) throws DataAccessException;

	/**
	 * 查询可以进出厂的车号
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, String>> queryInOutCarno(BCommon bc) throws DataAccessException;
	/**
	 * 获取数据库时间
	 * @return
	 * @throws DataAccessException
	 */
	public String queryOracletime() throws DataAccessException;

	
}
