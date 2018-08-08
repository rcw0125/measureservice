package com.xgmes.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.xgmes.model.Applicationbill;
import com.xgmes.model.Bcard;
import com.xgmes.model.Entrylog;
import com.xgmes.model.Gateparam;

public interface EntrylogMapper {
	/**
	 * 根据车号查询业务信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryList(String carno) throws DataAccessException;
	
	
	
	/**
	 * 根据车号查询业务信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryoutList(String carno) throws DataAccessException;
	
	/**
	 * 根据车号查询业务信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public String queryMatchids(String carno) throws DataAccessException;
	
	
	/**
	 * 根据车号查询业务信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> searchMatchidinfo(String carno) throws DataAccessException;
	
	
	/**
	 * 添加进厂信息
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public int insertEntrylog(Entrylog entry) throws DataAccessException;
	/**
	 * 记录进出厂刷卡返回信息
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public int insertEntrylogmsg(Entrylog entry) throws DataAccessException;
	
	/**
	 * 添加出厂信息
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public void insertOutEntry(Map<String, String> parameterMap) throws DataAccessException;
	
	/**
	 * 根据车号修改进出厂表中的进厂信息
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public int updateEntrylog(Entrylog entry) throws DataAccessException;
	/**
	 * 查询进厂id
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public Entrylog queryId(Entrylog entry) throws DataAccessException;
	
	/**
	 * 根据车号查询车辆是否在系统运行
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCountBycarno(Entrylog entry) throws DataAccessException;
	

	/**
	 * 根据物流号更新制卡表进厂id
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */

	public int updateInId(Entrylog entry) throws DataAccessException;

	/**
	 * 根据物流号更新业务临时表中进厂信息
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public int updatecurrtemp(Entrylog entry) throws DataAccessException;
	
	/**
	 * 查询进出厂日志信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Entrylog> queryEntrylog(Entrylog entry) throws DataAccessException;
	/**
	 * 根据卡号查询卡类型
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public Entrylog queryCartypeBycarid(Entrylog entry) throws DataAccessException;
	
	/**
	 * 查询道闸配置
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Gateparam> queryGateparam(Gateparam gp) throws DataAccessException;
	
	/**
	 * 添加线材业务
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public int insertXCtemp(Applicationbill app) throws DataAccessException;
	
	
	/**
	 * 根据路线id查询倒数第二个环节
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public String querylastsecond(String routeid) throws DataAccessException;
	
	/**
	 * 根据作业路线查询路线环节
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public String queryNodelist(String routeid) throws DataAccessException;
	
	public Bcard queryCardclass(Bcard card) throws DataAccessException;
	
	/**
	 * 根据路线查询环节
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<String> queryAllNodelist(String routeid) throws DataAccessException;
	
	/**
	 * 根据车号查询异常进出厂业务信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<Applicationbill> queryEXList(String carno) throws DataAccessException;
	
	/**
	 * 作废小车进出厂记录
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public int cancelEntrylog(Entrylog entry) throws DataAccessException;	
	
	/**
	 * 作废小车进出厂记录
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public int cancelEntrylog2(Entrylog entry) throws DataAccessException;
	
	/**
	 * 门岗查询进出厂
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public List<Entrylog> gatequreylist(Entrylog entry) throws DataAccessException;
	
	/**
	 * 查询进厂id
	 * 
	 * @param string
	 * @return
	 * @throws DataAccessException
	 */
	public Entrylog queryCardList(Entrylog entry) throws DataAccessException;
}
