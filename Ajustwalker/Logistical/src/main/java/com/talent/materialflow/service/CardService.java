package com.talent.materialflow.service;

import org.springframework.dao.DataAccessException;
import com.talent.core.model.Message;
import com.talent.materialflow.model.Bcard;
import com.talent.materialflow.model.Blacklist;

public interface CardService {

	/* 
	 * 卡初始化
	 */
	Message initCard(Bcard card) throws DataAccessException;

	/* 
	 * 发卡
	 */
	Message fromCard(Bcard card) throws DataAccessException;
	/* 
	 * 退卡
	 */
	Message backCard(Bcard card) throws DataAccessException;
	/* 
	 * 挂失
	 */
	int cancelCard(Bcard card) throws DataAccessException;
	/* 
	 * 添加黑名单
	 */
	int insertBlack(Blacklist black) throws DataAccessException;
	/* 
	 * 黑名单还原
	 */
	int cancelBlack(Blacklist black) throws DataAccessException;
	
}