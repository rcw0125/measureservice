package com.talent.measure.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.talent.base.model.PageModel;
import com.talent.measure.model.BusinessConfig;
import com.talent.measure.model.Card;
import com.talent.measure.model.Equipment;
import com.talent.measure.model.FunctionLog;
import com.talent.measure.model.Measure;
import com.talent.measure.model.Mointor;
import com.talent.measure.model.Msameweight;
import com.talent.measure.model.TareLog;
import com.talent.measure.model.WeighterData;

public interface BusinessConfigDao {
	/*
	 * 根据业务类型查询重量相似的相关配置
	 */

	BusinessConfig querySameweightconfig(BusinessConfig bc) throws DataAccessException;

	/*
	 * 获取相似的计量信息
	 */
	List<Measure> querySameweightInfo(Measure bc) throws DataAccessException;

	/*
	 * 查询最后一车皮重
	 */
	public Double queryLasttare(String carno) throws DataAccessException;
	/*
	 * 查询已经执行的计划量
	 */

	public Measure queryRealsuttle(Measure bc) throws DataAccessException;
	/*
	 * *查询已经执行的支数
	 */

	public Measure queryRealmaterialcount(Measure bc) throws DataAccessException;
	/*
	 * 查询已经执行的车数
	 */

	public Measure queryRealcarcount(Measure bc) throws DataAccessException;
	/*
	 * 查询历史皮重
	 * 
	 */

	public List<TareLog> queryHistorytare(Measure measure) throws DataAccessException;
	/*
	 * 获取计量衡器
	 */

	public int queryMeasureweigh(Map<String, String> map) throws DataAccessException;

	public int insertfunctionlog(FunctionLog flog);

	public List<WeighterData> querytimeBymatchid(WeighterData ex) throws DataAccessException;

	public Equipment queryrange(Equipment e) throws DataAccessException;

	public PageModel queryMointorinfo(Mointor m, PageModel pm) throws DataAccessException;

	public Card queryRfidBycarno(Card card) throws DataAccessException;

	/**
	 * 是否需要发放rfid
	 * 
	 * @param matchids
	 * @return
	 * @throws DataAccessException
	 */
	public String queryInfoBymateiracode(String mateiralcode, String type) throws DataAccessException;

	/**
	 * 查询路线环节
	 * 
	 * @param bc
	 * @return
	 * @throws DataAccessException
	 */
	public int queryNode(String matchid,String nodecode)throws DataAccessException;
	/**
	 * 查询当前点的上一个环节
	 * @param matchid
	 * @param nodes
	 * @return
	 * @throws DataAccessException
	 */
	public Msameweight queryLastnode(String matchid,String nodes)throws DataAccessException;
	
	/**
	 * 根据编码查询作业点名称	
	 * @param ms
	 * @return
	 * @throws DataAccessException
	 */
	public String queryworkpointname(String node) throws DataAccessException;

}