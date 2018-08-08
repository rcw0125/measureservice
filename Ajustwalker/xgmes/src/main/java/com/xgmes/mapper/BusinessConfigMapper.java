package com.xgmes.mapper;




import org.springframework.dao.DataAccessException;

import com.xgmes.model.Currtemp;
import com.xgmes.model.Entrylog;
import com.xgmes.model.WorklineItem;


public interface BusinessConfigMapper {

	/**
	 * 查询进厂id
	 * 
	 * @param curr
	 * @return
	 * @throws DataAccessException
	 */
	public Entrylog queryEntryinfo(Currtemp curr) throws DataAccessException;
	
	/**
	 * 根据物流号查询业务临时表信息
	 * 
	 * @param curr
	 * @return
	 * @throws DataAccessException
	 */
	public Currtemp queryCurrTemp(Currtemp curr) throws DataAccessException;
	/**
	 * 查询是否有未完成的业务信息
	 * @param curr
	 * @return
	 * @throws DataAccessException
	 */
	
	public int queryNotInfoByCarno(Currtemp curr) throws DataAccessException;
	
	/**
	 * 根据路线id、作业环节、作业点编码查询
	 * 
	 * @param curr
	 * @return
	 * @throws DataAccessException
	 */
	public int queryWorkpoint(WorklineItem witem) throws DataAccessException;
	
	public WorklineItem queryNextNode(WorklineItem witem) throws DataAccessException;
	
	

}
