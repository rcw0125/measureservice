package com.talent.measure.dao;

import org.springframework.dao.DataAccessException;
import com.talent.measure.model.Card;

public interface CardDao {
	// 获取卡号是否在黑名单中，0表示不在黑名单中，反之在。
	public int isBlack(Card card) throws DataAccessException;

	// 通过卡号、卡号类型，获取发卡表中的信息。
	public Card queryCardInfo(Card card) throws DataAccessException;

	// 通过车号、卡号类型，查询相应卡号。
	public Card getCardIDbyCarno(Card card) throws DataAccessException;

	/**
	 * 添加监称信息
	 * 
	 * @param card
	 * @return
	 * @throws DataAccessException
	 */
	public void insertmonitorweight(Card card) throws DataAccessException;
}
