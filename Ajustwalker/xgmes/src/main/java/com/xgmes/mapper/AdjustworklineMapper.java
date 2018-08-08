package com.xgmes.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xgmes.model.Applicationbill;
import com.xgmes.model.Workline;
import com.xgmes.model.WorklineItem;

public interface AdjustworklineMapper {
	/**
	 * 查询制卡表主子表
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */

	public List<Applicationbill> queryList(Applicationbill app) throws DataAccessException;
    /**
     * 根据路线id查询路线环节
     * @param workitem
     * @return
     * @throws DataAccessException
     */
	public List<WorklineItem> queryNodename(WorklineItem workitem) throws DataAccessException;
	
	/**
     * 根据路线id查询主表
     * @param workitem
     * @return
     * @throws DataAccessException
     */
	public Workline queryWorkline(Workline workline) throws DataAccessException;
	/**
     * 根据路线id查询路线环节
     * @param workitem
     * @return
     * @throws DataAccessException
     */
	public List<WorklineItem> queryWorklineItem(WorklineItem workitem) throws DataAccessException;
	/**
     * 查询id
     * @param workitem
     * @return
     * @throws DataAccessException
     */
	public Workline queryId() throws DataAccessException;
	/**
     * 添加路线主表
     * @param workline
     * @return
     * @throws DataAccessException
     */
	public int insertWorkline(Workline workline) throws DataAccessException;
	
	/**
     * 添加路线子表
     * @param workitem
     * @return
     * @throws DataAccessException
     */
	public int insertWorklineItem(WorklineItem workitem) throws DataAccessException;
	
	/**
     * 修改制卡主表路线
     * @param workitem
     * @return
     * @throws DataAccessException
     */
	public int updateAppRouteid(WorklineItem workitem) throws DataAccessException;
	
	/**
     * 修改临时表路线
     * @param workitem
     * @return
     * @throws DataAccessException
     */
	public int updateCurrRouteid(WorklineItem workitem) throws DataAccessException;
	 
	
}
