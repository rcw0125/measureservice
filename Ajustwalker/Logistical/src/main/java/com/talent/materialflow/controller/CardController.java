package com.talent.materialflow.controller;



import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.talent.core.util.HttpUtils;
import com.talent.materialflow.model.Applicationbill;
import com.talent.materialflow.model.Bcard;
import com.talent.materialflow.model.Blacklist;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.CardService;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.CardMapper;


@Controller
public class CardController extends BaseController {

	@Resource
	private CardMapper cardMapper;
	@Resource
	private BCommonMapper bcommonMapper;
	@Resource
	private CardService cardService;
	@Resource
	private BCommonService bcommonService;
	@Autowired
	private HttpUtils httpUtils;
	
	/**
	 * IC卡管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/card")
	public String card(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "信息中心");
		return "card";
	}
	/**
	 * 北门卡管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/cardbm")
	public String cardbm(ModelMap model) {
		model.addAttribute("unitcode", "S16080045");
		model.addAttribute("unitname", "北门制卡"); 
		return "card";
	}
	/**
	 * 北门西道
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/cardbmS16080058")
	public String cardbmS16080058(ModelMap model) {  
		model.addAttribute("unitcode", "S16080058");
		model.addAttribute("unitname", "北门西道制卡");
		return "card";
	}
	
	
	/**
	 * 北门西道
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/cardbmS16080047")
	public String cardbmS16080047(ModelMap model) {
		model.addAttribute("unitcode", "S16080047");
		model.addAttribute("unitname", "北门西道");
		return "card";
	}
	
	/**
	 * 北门临时作业点
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/cardbmS16080044")
	public String cardbmS16080044(ModelMap model) {
		model.addAttribute("unitcode", "S16080044");
		model.addAttribute("unitname", "北门临时作业点");
		return "card";
	}
	
	/**
	 * 北门东道
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/cardbmS16080046")
	public String cardbmS16080046(ModelMap model) {
		model.addAttribute("unitcode", "S16080046");
		model.addAttribute("unitname", "北门东道");
		return "card";
	}
	/**
	 * 北门卡管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/cardbmS16080040")
	public String cardbmS16080040(ModelMap model) {
		model.addAttribute("unitcode", "S16080040");
		model.addAttribute("unitname", "北门东2道");
		return "card";
	}
	
	/**
	 * 东南门卡管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/carddnm")
	public String carddnm(ModelMap model) {
		model.addAttribute("unitcode", "S16080048");
		model.addAttribute("unitname", "东南门");
		return "card";
	}
	/**
	 * 钢材市场门卡管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/carddgc")
	public String carddgc(ModelMap model) {
		model.addAttribute("unitcode", "S16080049");
		model.addAttribute("unitname", "钢材市场门");
		return "card";
	}
	/**
	 * 公司办卡管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/carddgsb")
	public String carddgsb(ModelMap model) {
		model.addAttribute("unitcode", "S16080038");
		model.addAttribute("unitname", "公司办");
		return "card";
	}
	/**
	 * ic卡管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/icmanage")
	public String carddgsb(Bcard card,ModelMap model) {
		model.addAttribute("unitcode", card.getUnitcode());
		model.addAttribute("unitname", card.getUnitname());
		model.addAttribute("flag", card.getFlag());
		return "card";
	}
	
    /**
     * 黑名单管理
     * @param model
     * @return
     */
	@RequestMapping(value = "/card/blackcard")
	public String blackcard(ModelMap model) {
		return "blackcard";
	}
	
	@ResponseBody
	@RequestMapping(value = "/card/queryPage.do")
	public Message queryPage(Bcard card, ModelMap model, Pagination<Bcard> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(cardMapper.queryList(card));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	// 查询可以修改的信息列表
	@ResponseBody
	@RequestMapping(value = "/card/queryBcard.do")
	public Bcard queryBcard(String page) {
		Bcard card = new Bcard();
		card.setBeginperiod("00:00:00");
		card.setEndperiod("23:59:59");
		if("ic".equals(page)){
			card.setCardclass("0");
		}else if("rfid".equals(page)){
			card.setCardclass("1");
		}else if("rfidfrom".equals(page)){
			card.setCardtype("1"); 
			card.setCartype("0");
		}
		return card;
	}
	/**
	 * 根据卡号查询卡信息
	 * @param card
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/queryinfoBycardid.do")
	public Bcard queryinfoBycardid(Bcard card) {
		card =cardMapper.queryinfoBycardid(card);

		return card;
	}
	
	@ResponseBody
	@RequestMapping(value = "/card/judgCarno.do")
	public Message judgCarno(Bcard card) {		
		Message msg = new  Message();
		try {
			Blacklist black = new Blacklist();
			black.setCardid(card.getCardid());
			black.setCarno(card.getCarno());
			msg = bcommonService.judgCarno(black);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 根据卡号和车号判断是否允许退卡	
		return msg;
	}
	/**
	 * 判断是否允许初始化
	 * @param card
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/judgInitCarno.do")
	public Message judgInitCarno(Bcard card) {
		Blacklist black = new Blacklist();
		black.setCardid(card.getCardid());
		Message msg = bcommonService.judgInitCarno(black);	
		return msg;
	}
	/**
	 * 是否允许发卡
	 * @param card
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/judgCarId.do")
	public Message judgCarId(Bcard card) {
		Blacklist black = new Blacklist();
		black.setCardid(card.getCardid());
		Message msg = bcommonService.judgCarId(black);
		return msg;
	}
	
	/**
	 * 根据车号查询是否允许发卡
	 * @param card
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/judgOrFromcarno.do")
	public Message judgOrFromcarno(Blacklist black) {
		Message msg = bcommonService.judgOrFromcarno(black);
		return msg;
	}
	
	
	/**
	 * 卡初始化
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/initCard.do")
	public Message initCard(Bcard card) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			card.setCreator(user.getDisplayname());
			msg=cardService.initCard(card);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 发卡
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/fromCard.do")
	public Message cancelStorein(Bcard card,Applicationbill app) {
		Message msg = new Message();
		//Blacklist bc = new Blacklist();
		//bc.setCardid(card.getCardid());
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			card.setFromman(user.getDisplayname());
			card.setFrommancode(user.getUsername());
			msg=cardService.fromCard(card);
			if(msg.isSuccess()){
				Bcard bcard = cardMapper.queryInfoByCardno(card);
				if(bcard !=null&&(bcard.getMatchid() != null || bcard.getMatchid() != ""))
				{
					List<String> list = bcommonService.getMessage(bcard.getMatchid(), "in");
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							 httpUtils.get("http://192.168.2.42:7080/MeasureService/qualityInterface/ingate.do",
									list.get(i));
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 退卡
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/backCard.do")
	public Message backCard(Bcard card) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			card.setBackman(user.getDisplayname());
			card.setBackmancode(user.getUsername());
			msg = cardService.backCard(card);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 挂失
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/cancelCard.do")
	public Message cancelCard(Bcard card) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			card.setCreator(user.getDisplayname());
			cardService.cancelCard(card);
			msg.setMsg("挂失成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 黑名单查询
	 * @param black
	 * @param model
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/queryBlackinfo.do")
	public Message queryBlackinfo(Blacklist black, ModelMap model, Pagination<Bcard> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(cardMapper.queryBlackinfo(black));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 *添加黑名单
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/insertBlack.do")
	public Message insertBlack(Blacklist black) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			black.setCreator(user.getDisplayname());
			black.setRecordtype("2");
			cardService.insertBlack(black);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 黑名单还原
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/cancelBlack.do")
	public Message cancelBlack(Blacklist black) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			black.setCanceler(user.getDisplayname());
			cardService.cancelBlack(black);
			msg.setMsg("还原成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("还原失败！");
		}
		return msg;
	}
	
	/**
	 * 修改卡有效期
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/updatetime.do")
	public Message updatetime(Bcard card,ModelMap model) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			card.setUpdateman(user.getDisplayname());
			cardMapper.updatetime(card);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 车辆预警查询
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/queryBlackInOutInfo.do")
	public Message queryBlackInOutInfo(Blacklist black, ModelMap model, Pagination<Bcard> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(cardMapper.queryBlackInOutInfo(black));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 挂失卡查询
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/queryCardinfo.do")
	public Message queryCardinfo(Blacklist black, ModelMap model, Pagination<Bcard> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(cardMapper.queryCardinfo(black));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

}
