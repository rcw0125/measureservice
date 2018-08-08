package com.xgmes.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.core.model.Message;
import com.xgmes.model.Blacklist;
import com.xgmes.model.Currtemp;
import com.xgmes.model.Forcestop;
import com.xgmes.model.Measure;

public interface BCommonService {

	/**
	 * 根据车号判断车辆状态
	 * 
	 * @param bc
	 * @return
	 * @throws ParseException
	 */
	public Message judgCarno(Blacklist bc) throws ParseException;

	/**
	 * 根据卡号判断是否允许发卡
	 */

	public Message judgCarId(Blacklist bc);

	/**
	 * 根据车号判断是否允许发卡
	 */

	public Message judgOrFromcarno(Blacklist bc);

	/**
	 * 根据卡号是否允许初始化
	 */

	public Message judgInitCarno(Blacklist bc);

	/**
	 * 生成matchid
	 *
	 * @param operatype
	 *            业务类型
	 * @return matchid
	 */
	public String txMatchid(String operatype) throws DataAccessException;

	/**
	 * 生成单据号
	 *
	 * @param operatype
	 *            业务类型
	 * @return matchid
	 */
	public String txPlanid(String operatype) throws DataAccessException;

	/**
	 * 生成业务号
	 *
	 * @param operatype
	 *            业务类型
	 * @return matchid
	 */
	public String taskcode(String operatype) throws DataAccessException;
	/**
	 * 获取基础数据编码
	 *
	 * @param operatype
	 *            业务类型
	 * @return matchid
	 */
	public String getCode(String types) throws DataAccessException ;
	/**
	 * 生成单据编码
	 *
	 * 
	 * @return dtype
	 */
	public String getDocumenttype() throws DataAccessException;

	/**
	 * 保存前验证
	 * 
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	public Message beforeinsert(Currtemp curr, String plink) throws DataAccessException;

	public Message forcestop(Forcestop ft) throws DataAccessException;
	
	public Message rebackforcestop(Forcestop ft)  throws DataAccessException;

	public Message beforeCancel(Currtemp curr, String plink) throws DataAccessException;

	/**
	 * 通过物流号判断需要回传质检的信息
	 * 
	 * @param matchids
	 * @return
	 * @throws DataAccessException
	 */
	public List<String> getMessage(String matchids, String type) throws DataAccessException;
	/**
	 * 是否需要发放rfid
	 * 
	 * @param matchids
	 * @return
	 * @throws DataAccessException
	 */
	public String queryInfoBymateiracode(String mateiralcode, String type) throws DataAccessException; 
	
	/**
	 * 根据卡号判断卡、车辆状态
	 * 
	 * @throws ParseException
	 */

	public Message judgOutCarno(Blacklist bc) throws ParseException ;


	/**
	 * 判断上一环节是否完成
	 * 
	 * @param matchids
	 * @return
	 * @throws DataAccessException
	 */
	public Message queryUpNode(String matchid, String type) throws DataAccessException;
	/**
	 * 根据rfid卡号判断rfid状态
	 * 
	 * @throws ParseException
	 */

	public Message judgRFID(Blacklist bc) throws ParseException;
	
	/**
	 * 根据车号、业务号查询皮重及业务信息
	 * 
	 * @param bc
	 * @return
	 * @throws ParseException
	 */
	public Message getTareBYCarnoT(Measure meausre) throws ParseException;
	
	/**
	 * 根据车号判断车号是否在黑名单
	 */

	public Message judgOrBlackCarno(Blacklist bc);

}