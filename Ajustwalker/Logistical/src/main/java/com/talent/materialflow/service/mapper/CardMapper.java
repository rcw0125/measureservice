package com.talent.materialflow.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.materialflow.model.Bcard;
import com.talent.materialflow.model.Blacklist;

public interface CardMapper {

	public List<Bcard> queryList(Bcard card) throws DataAccessException;

	public int initCard(Bcard card) throws DataAccessException;

	public int fromCard(Bcard card) throws DataAccessException;

	public int backCard(Bcard card) throws DataAccessException;

	public int cancelCard(Bcard card) throws DataAccessException;

	public int insertBlack(Blacklist black) throws DataAccessException;

	public int cancelBlack(Blacklist black) throws DataAccessException;

	public int updateInitCard(Bcard card) throws DataAccessException;

	public int queryBycardid(Bcard card) throws DataAccessException;

	public Bcard queryinfoBycardid(Bcard card) throws DataAccessException;

	public List<Blacklist> queryBlackinfo(Blacklist black) throws DataAccessException;

	public int blackinitCard(Bcard card) throws DataAccessException;
	
	public int updateCurrRfid(Bcard card) throws DataAccessException;
	
	public int updateappRfid(Bcard card) throws DataAccessException;
	
	public int updatetime(Bcard card) throws DataAccessException;
	
	public List<Bcard> queryBlackInOutInfo(Blacklist black) throws DataAccessException;
	
	public List<Blacklist> queryCardinfo(Blacklist black) throws DataAccessException;
	
	/**
     * 通过RFID卡号和车号查询
     * @param cardid,carno
     * @return
     * @throws DataAccessException
     */
	public int queryRfidInfo(Bcard bcard) throws DataAccessException;
	
	public Bcard queryInfoByCardno(Bcard bcard) throws DataAccessException;
	    

} 
