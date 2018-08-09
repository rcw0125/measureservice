package com.talent.measure.dao.impl;

import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.measure.dao.CardDao;
import com.talent.measure.model.Card;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@SuppressWarnings({ "deprecation" })
public class CardDaoImpl extends BaseDaoiBatis implements CardDao {
	
	/**
	 * 根据卡号或者车号，和类型，查询是否在黑名单中
	 */
	public int isBlack(Card card) throws DataAccessException{
		
		return (Integer)getOracleSqlMapClientTemplate().queryForObject("card.isblack", card);
	}
	/**
	 * 根据卡号，和类型，查询是否 发卡状态
	 */
	
	public Card queryCardInfo(Card card) throws DataAccessException{
		
		return (Card)getOracleSqlMapClientTemplate().queryForObject("card.querycardinfo", card);
	}
	/**
	 * 根据车号，查出IC卡或者RFID卡号
	 * @param card
	 * @return
	 * @throws DataAccessException
	 */
   public Card getCardIDbyCarno(Card card) throws DataAccessException{
		
		return (Card)getOracleSqlMapClientTemplate().queryForObject("card.getCardIDbyCarno", card);
	}
	
   /**
    * 添加监称信息
    * @param card
    * @return
    * @throws DataAccessException
    */
   public void insertmonitorweight(Card card) throws DataAccessException{
	   getOracleSqlMapClientTemplate().insert("card.insertmonitorweight", card);
	}
}
