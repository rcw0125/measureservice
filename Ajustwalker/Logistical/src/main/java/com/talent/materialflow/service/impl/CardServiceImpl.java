package com.talent.materialflow.service.impl;

import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.core.model.Message;
import com.talent.materialflow.model.Bcard;
import com.talent.materialflow.model.Blacklist;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.CardService;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.CardMapper;

@Service
@Transactional
public class CardServiceImpl implements CardService {
	@Resource
	private CardMapper cardMapper;
	@Resource
	private BCommonMapper bcommonMapper;
	@Resource
	private BCommonService bcommonService;

	/*
	 * 卡初始化
	 */
	@Override
	public Message initCard(Bcard card) throws DataAccessException {
		Message msg = new Message();
		try {
			Blacklist black = new Blacklist();
			black.setCardid(card.getCardid());
			black.setCarno(card.getCarno());
			msg = bcommonService.judgInitCarno(black);
			if (msg.isSuccess()) {
				int flag = cardMapper.queryBycardid(card);
				if (flag == 0) {
					cardMapper.initCard(card);
				} else {
					cardMapper.updateInitCard(card);
				}

				msg.setMsg("卡初始化成功");
			}
		} catch (Exception e) {
			System.out.println("w....." + e);
		}
		return msg;
	}

	/*
	 * 发卡
	 */
	@Override
	public Message fromCard(Bcard card) throws DataAccessException {
		card.setCarno(card.getCarno().trim());
		Blacklist black = new Blacklist();
		black.setCardid(card.getCardid());
		black.setCarno(card.getCarno());
		black.setRecordtype(card.getCardclass());
		Message msg = bcommonService.judgCarId(black);// 根据卡号和车号判断是否允许发卡
		if (msg.isSuccess()) {
			black.setCardid(card.getCardid());
			black.setCarno(card.getCarno());
			black.setRecordtype(card.getCardclass());
			msg = bcommonService.judgOrFromcarno(black);
			if(msg.isSuccess()){
				cardMapper.fromCard(card);
				msg.setMsg("发卡成功");	
			}
			
		}

		return msg;
	}

	/*
	 * 退卡
	 */
	@Override
	public Message backCard(Bcard card) throws DataAccessException {

		Blacklist black = new Blacklist();
		black.setCardid(card.getCardid());
		black.setCarno(card.getCarno());
		Message msg = new Message() ;
		try {
			msg = bcommonService.judgCarno(black);
			if (msg.isSuccess()) {
				cardMapper.backCard(card);// 退卡修改发卡表内容
				msg.setMsg("退卡成功");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 根据卡号和车号判断是否允许退卡
		
		return msg;
	}

	/*
	 * 挂失
	 */
	@Override
	public int cancelCard(Bcard card) throws DataAccessException {
		Blacklist black = new Blacklist();
		black.setCardid(card.getCardid());
		black.setRecordtype(card.getCardclass());
		black.setCreator(card.getCreator());
		black.setUsermemo("IC卡挂失");
		cardMapper.insertBlack(black);
		cardMapper.cancelCard(card);
		return 1;
	}

	/*
	 * 添加黑名单
	 */
	@Override
	public int insertBlack(Blacklist black) throws DataAccessException {
		cardMapper.insertBlack(black);
		return 1;
	}

	/*
	 * 黑名单还原
	 */
	@Override
	public int cancelBlack(Blacklist black) throws DataAccessException {
		Bcard card = new Bcard();
		card.setCardid(black.getCardid());
		card.setCreator(black.getCreator());		
		cardMapper.blackinitCard(card);
		cardMapper.cancelBlack(black);
		return 1;
	}

}
