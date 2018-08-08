package com.talent.materialflow.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.materialflow.model.Storein;

public interface StoreinMapper {

	public List<Storein> queryList(Storein storein) throws DataAccessException;
	
	
	public Storein queryCount(Storein storein) throws DataAccessException;
    /**
     * 根据车号查询信息
     * @param storein
     * @return
     * @throws DataAccessException
     */
	public List<Storein> queryInfoBycarno(Storein storein) throws DataAccessException;
	
	/**
     * 根据选择的入库信息号打印信息
     * @param storein
     * @return
     * @throws DataAccessException
     */
	public List<Storein> queryPrintInfoByitemid(String[] itemid) throws DataAccessException;
	
	
	
	/**
	 * 查询非本作业点的该车的作业点编码
	 * @param carno
	 * @return
	 * @throws DataAccessException
	 */
	public String queryInfoBycarnoNotcode(String carno) throws DataAccessException;	
	/**
	 * 根据编码查询作业点
	 * @param carno
	 * @return
	 * @throws DataAccessException
	 */
	public Storein querywnamebycode(Storein storein) throws DataAccessException;	

	public int insertStorein(Storein storein) throws DataAccessException;

	public int insertStoreinitem(Storein storein) throws DataAccessException;

	public int updateCurrtemp(Storein storein) throws DataAccessException;
	
	public int updateApplicationitem(Storein storein) throws DataAccessException;
	
	public int cancelStorein(Storein storein) throws DataAccessException;

	public int cancelStoreinitem(Storein storein) throws DataAccessException;

	public Storein queryInfoBymatchid(Storein storein) throws DataAccessException;
	
	public int queryCountBymatchid(Storein storein) throws DataAccessException;
	
	
	public List<Storein> queryPrintData(String[] matchids) throws DataAccessException;
}
