package com.xgmes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.xgmes.mapper.ApplicationbillMapper;
import com.xgmes.mapper.BCommonMapper;
import com.xgmes.model.Applicationbill;
import com.xgmes.model.BCommon;
import com.xgmes.model.Blacklist;
import com.xgmes.model.Currtemp;
import com.xgmes.model.Operaconfig;
import com.xgmes.service.ApplicationbillService;
import com.xgmes.service.BCommonService;
@Controller
public class ApplicationbillController extends BaseController {

	@Resource
	private ApplicationbillMapper appMapper;
	@Resource
	private ApplicationbillService appService;
	@Resource
	private BCommonMapper bcommonMapper;
	@Resource
	private BCommonService bcommonService;

	@RequestMapping(value = "/application/makecardcg")
	public String makecardcg(ModelMap model) {
		model.addAttribute("unitcode", "S16080045");
		model.addAttribute("unitname", "北门制卡01");
		model.addAttribute("operatype", "90,91,92,93,80,81,82,83");
		model.addAttribute("fkflag", "1");
		return "makecard";
	}

	@RequestMapping(value = "/application/makecardcgS16080045")
	public String makecardcgS16080045(ModelMap model) {
		model.addAttribute("unitcode", "S16080045");
		model.addAttribute("unitname", "北门制卡01");
		model.addAttribute("operatype", "90,91,92,93,80,81,82,83");
		model.addAttribute("fkflag", "1");
		return "makecard";
	}

	@RequestMapping(value = "/application/makecardcgBMSYDY")
	public String makecardcgBMSYDY(ModelMap model) {
		model.addAttribute("unitcode", "S16080045");
		model.addAttribute("unitname", "实业打印");
		model.addAttribute("operatype", "90,91,92,93,80,81,82,83");
		model.addAttribute("fkflag", "1");
		return "makecard";
	}

	@RequestMapping(value = "/application/makecarded")
	public String makecarded(ModelMap model) {
		model.addAttribute("unitcode", "S16080040");
		model.addAttribute("unitname", "北门东2道");
		model.addAttribute("operatype", "90,91,92,93,80,81,82,83");
		model.addAttribute("fkflag", "1");
		return "makecard";
	}
	
	

	@RequestMapping(value = "/application/makecardS16080047")
	public String makecardS16080047(ModelMap model) {
		model.addAttribute("unitcode", "S16080047");
		model.addAttribute("unitname", "北门西道");
		model.addAttribute("operatype", "90,91,92,93,80,81,82,83");
		model.addAttribute("fkflag", "1");
		return "makecard";
	}

	@RequestMapping(value = "/application/makecardcgS16080058")
	public String makecardcgS16080058(ModelMap model) {
		model.addAttribute("unitcode", "S16080058");
		model.addAttribute("unitname", "北门西道制卡");
		model.addAttribute("operatype", "90,91,92,93,80,81,82,83");
		model.addAttribute("fkflag", "1");
		return "makecard";
	}

	@RequestMapping(value = "/application/makecardnmbd")
	public String makecardnmbd(ModelMap model) {
		model.addAttribute("unitcode", "S16080039");
		model.addAttribute("unitname", "东南门北道");
		model.addAttribute("operatype", "90,91,92,93,80,81,82,83");
		model.addAttribute("fkflag", "1");
		return "makecard";
	}

	@RequestMapping(value = "/application/makecardgsedm")
	public String makecardgsedm(ModelMap model) {
		model.addAttribute("unitcode", "S16080039");
		model.addAttribute("unitname", "钢市二道门");
		model.addAttribute("operatype", "90,91,92,93,80,81,82,83");
		model.addAttribute("fkflag", "1");
		return "makecard";
	}

	@RequestMapping(value = "/application/makecardgsb")
	public String makecardxs(ModelMap model) {
		model.addAttribute("unitcode", "S16080038");
		model.addAttribute("unitname", "公司办");
		model.addAttribute("operatype", "90,91,92,93,80,81,82,83");
		model.addAttribute("fkflag", "1");
		return "makecard";
	}

	@RequestMapping(value = "/application/makecard")
	public String makecard(ModelMap model,Applicationbill app) {
		model.addAttribute("unitcode", app.getUnitcode());
		model.addAttribute("unitname", app.getUnitname());
		model.addAttribute("operatype", app.getOperatype());
		model.addAttribute("fkflag", app.getFkflag());
		return "makecard";
	}
	/**
	 * 
	  * @Title: systemdatatransfer
	  * @Description:(数据迁移)
	  * @param @param model
	  * @param @param app
	  * @param @return参数
	  * @return String返回类型
	  * @throws
	 */
	@RequestMapping(value = "/application/systemdatatransfer")
	public String systemdatatransfer(ModelMap model,Applicationbill app) {
		model.addAttribute("unitcode", app.getUnitcode());
		model.addAttribute("unitname", app.getUnitname());
		model.addAttribute("operatype", app.getOperatype());
		model.addAttribute("fkflag", app.getFkflag());
		return "systemdatatransfer";
	}
	@ResponseBody
	@RequestMapping(value = "/application/queryPage.do")
	public Message queryPage(Applicationbill app, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(appMapper.queryList(app));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 查询原始单据号
	 * @param app
	 * @param model
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/queryFdocumentinfo.do")
	public Message queryFdocument(Applicationbill app, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			
			msg = buildGridData(appMapper.queryFdocument(app));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/application/queryDocumentPage.do")
	public Message queryDocumentList(Applicationbill app, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			/*User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());*/
			msg = buildGridData(appMapper.queryDocumentList(app));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/application/queryApplicationbill.do")
	public Applicationbill queryApplicationbill(Applicationbill app, String type, String typecode) {
		String planidlist = "";
		String itemidlist = "";
		if ("-1".equals(app.getMatchid())) {
			if ("document".equals(type)) {
				app.setIsormeasure("");
			}
			app.setOperatype(typecode);
		} else {
			String unitcode = app.getUnitcode();
			String unitname = app.getUnitname();
			app.setMatchid(app.getMatchid());
			List<Applicationbill> list = new ArrayList<Applicationbill>();
			if ("document".equals(type)) {
				list = appMapper.queryDocumentBymatchid(app);
			} else {
				list = appMapper.queryInfoBymatchid(app);
				for (int i = 0; i < list.size(); i++) {
					Applicationbill al = list.get(i);
					if (i == 0) {
						planidlist = "," + al.getPlanid() + ",";
						itemidlist = "," + al.getSaleitemid() + ",";
					} else {
						planidlist = planidlist + al.getPlanid() + ",";
						itemidlist = itemidlist + al.getSaleitemid() + ",";
					}

				}
			}
			app = list.get(0);
			app.setUnitcode(unitcode);
			app.setUnitname(unitname);
			app.setUpplanidlist(planidlist);
			app.setUpitemidlist(itemidlist);
		}
		return app;
	}

	@ResponseBody
	@RequestMapping(value = "/application/queryAppInfo.do")
	public Message queryAppInfo(String matchid) {
		Message msg = new Message();
		Applicationbill app = new Applicationbill();
		if (!"-1".equals(matchid)) {
			app.setMatchid(matchid);
			msg.setRows(appMapper.queryInfoBymatchid(app));
		}
		return msg;
	}

	/**
	 * 制卡前验证
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/beforeinsertApplicationbill.do", method = RequestMethod.POST)
	public Message beforeinsertApplicationbill(Applicationbill app) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());
			app.setRfidid(app.getRfidbak());
			Currtemp curr = new Currtemp();
			curr.setCarno(app.getCarno());
			curr.setUnitname(app.getUnitname());
			curr.setOperatype(app.getOperatype());
			msg = bcommonService.beforeinsert(curr, "MK");
			if ("0".equals(msg.getMfunc())) {// 如果返回信息为0，直接保存信息
				if ("-1".equals(app.getMatchid())) {
					msg = appService.insertApplicationbill(app);
				} else {
					msg = appService.updateApplicationbill(app);
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
	 * 添加制卡信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/insertApplicationbill.do", method = RequestMethod.POST)
	public Message insertApplicationbill(Applicationbill app) {

		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());
			app.setRfidid(app.getRfidbak());
			if ("-1".equals(app.getMatchid())) {
				msg = appService.insertApplicationbill(app);
			} else {
				msg = appService.updateApplicationbill(app);
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 根据物料编码查询是否需要验证rfid
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/queryRfidBymcode.do")
	public Message queryRfidBymcode(Applicationbill app) {
		Message msg = new Message();

		try {
			Blacklist bk = new Blacklist();
			bk.setCardid(app.getRfidid());
			bk.setCarno(app.getCarno());
			msg = bcommonService.judgRFID(bk);// 判断rfid卡是否可用
			if (msg.isSuccess()) {// 如果可以判断物资是否需要发放rfid卡
				int flag = 0;
				int noflag = 0;
				int yflag=0;
				String[] mlist = app.getMaterialcode().split(",");
				if (mlist.length == 0 && (StringUtils.isNotEmpty(app.getRfidid()))&& app.getRfidflag()==0) {
					noflag++;
				} else {
					for (int i = 0; i < mlist.length; i++) {
						if (!"".equals(mlist[i]) && mlist[i] != null) {
							String isorrfid = bcommonService.queryInfoBymateiracode(mlist[i], "2");
							if ("1".equals(isorrfid) && ("".equals(app.getRfidid()) || app.getRfidid() == null)) {
								flag++;
							}else if ("0".equals(isorrfid) && (StringUtils.isNotEmpty(app.getRfidid())) && app.getRfidflag()==0) {
								noflag++;
							}else if("1".equals(isorrfid) && StringUtils.isNotEmpty(app.getRfidid())){//需要发放rfid的
								yflag++;
							}
						}
					}
				}
				if (flag == 0 && noflag == 0) {
					msg.setSuccess(true);
					msg.setData(flag);
				} else if (flag > 0 && noflag == 0) {
					msg.setSuccess(false);
					msg.setMsg("请发RFID卡");
				} else if (flag == 0 && noflag > 0 && yflag==0) {
					msg.setSuccess(false);
					msg.setMsg("物资无需发放RFID卡");
				}
				mlist = null;
			}

			bk = null;

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/application/queryRfidBycarno.do")
	public Message queryRfidBycarno(String carno) {
		Message msg = new Message();
		try {
			String rfid = appMapper.queryRfidBycarno(carno);
			msg.setData(rfid);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/application/queryUncomplete.do")
	public Message queryUncomplete(String carno) {
		Message msg = new Message();
		try {
			int flag=0;
			int notflag=0;
			List<String> list = appMapper.queryUncomplete(carno);
			if(list!=null){
				for(int i=0;i<list.size();i++){
					if("1".equals(list.get(i))){
						flag++;
					}else{
						notflag++;
					}
				}
				if(flag>0){
					msg.setSuccess(false);
					msg.setMsg("车辆有未完成的业务，不允许重新制卡");
				}else if(flag==0 && notflag>0){
					msg.setMfunc(String.valueOf(notflag));
					msg.setSuccess(true);
					msg.setMsg("车辆有未完成的业务，是否附加单据");
				}
						
			}else{
				msg.setMfunc(String.valueOf(flag));
				msg.setSuccess(true);
			}
			list.clear();
			list=null;
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}		
		return msg;
	}
	/**
	 * 作废制卡信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/cancelApplicationbill.do")
	public Message cancelApplicationbill(Applicationbill app) {
		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());
			msg = appService.cancelApplicationbill(app);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		app=null;
		return msg;
	}

	/**
	 * 不锈钢委外出厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document101")
	public String document101(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "101");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 材料领用出厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document102")
	public String document102(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "102");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 储运废旧物资出厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document103")
	public String document103(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "103");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 辅业外购进厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document104")
	public String document104(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "104");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 辅业外销物资出厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document105")
	public String document105(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "105");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 特别说明类物资进厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document106")
	public String document106(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "106");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 委外加工物资出厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document107")
	public String document107(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "107");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 维修加工物资出厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document108")
	public String document108(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "108");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 维修加工物资进厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document109")
	public String document109(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "109");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 施工物资出厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document110")
	public String document110(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "110");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 施工物资进厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document111")
	public String document111(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "111");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}

	/**
	 * 特别说明类物资出厂
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/document112")
	public String document112(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "112");
		model.addAttribute("secondflag", "0");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",user.getDisplayname());
		return "document";
	}


	/**
	 * 添加单据信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/insertDocument.do")
	public Message insertDocument(Applicationbill app) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());

			BCommon bcommon = new BCommon();
			bcommon.setDocumentcode(app.getOperatype());
			bcommon = bcommonMapper.queryAuditlevel(bcommon);
			app.setAuditlevel(bcommon.getAuditlevel());
			app.setDocumentcode(app.getOperatype());
			// materialflow等于1时进厂，2为出厂；Isormeasure等于1计量，0是不计重，系统认为计件
			if (bcommon.getMateriallflow() == 1 && ("1".equals(app.getIsormeasure()))) {
				app.setOperatype("92");// 其他采购计重
			} else if (bcommon.getMateriallflow() == 1 && ("0".equals(app.getIsormeasure()))) {
				app.setOperatype("93");// 其他采购计件
			} else if (bcommon.getMateriallflow() == 2 && ("1".equals(app.getIsormeasure()))) {
				app.setOperatype("82");// 其他销售计重
			} else if (bcommon.getMateriallflow() == 2 && ("0".equals(app.getIsormeasure()))) {
				app.setOperatype("83");// 其他销售计件
			}
			if ("-1".equals(app.getMatchid())) {
				app.setTypes("1");
			   msg = appService.insertDocument(app);
			} else {
			   msg = appService.updateDocument(app);
			}

		} catch (Exception e) {
			e.printStackTrace();     
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 作废单据信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/cancelDocument.do")
	public Message cancelDocument(Applicationbill app) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());
			msg = appService.cancelDocument(app);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 一级审核
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/updatefirst.do")
	public Message updatefirst(Applicationbill app) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setApproverfirst(user.getDisplayname());

			String[] matchids = app.getMatchid().split(",");
			for (int i = 0; i < matchids.length; i++) {
				app.setMatchid(matchids[i]);
				appMapper.updateFirst(app);
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 二级审核
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/updatesecond.do")
	public Message updatesecond(Applicationbill app) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setApproversecond(user.getDisplayname());
			String[] matchids = app.getMatchid().split(",");
			for (int i = 0; i < matchids.length; i++) {
				app.setMatchid(matchids[i]);
				appMapper.updatesecond(app);
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 一级弃审
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/giveupfirst.do")
	public Message giveupfirst(Applicationbill app) {
		Message msg = new Message();
		String str = "";
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setGiveupfirst(user.getDisplayname());
			String[] matchids = app.getMatchid().split(",");
			for (int i = 0; i < matchids.length; i++) {
				app.setMatchid(matchids[i]);
				int flag = appMapper.queryCountBymatchid(app);
				if (flag == 0) {
					appMapper.giveupfirst(app);
				} else {
					if (str.length() == 0) {
						str = matchids[i];
					} else {
						str = str + "," + matchids[i];
					}
				}

			}
			if (str.length() > 0) {
				msg.setSuccess(false);
				msg.setMsg("单据号：" + str + " 已经使用不允许弃审");
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 二级弃审
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/application/giveupsecond.do")
	public Message giveupsecond(Applicationbill app) {
		Message msg = new Message();
		String str = "";
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setGiveupfirst(user.getDisplayname());
			String[] matchids = app.getMatchid().split(",");
			for (int i = 0; i < matchids.length; i++) {
				app.setMatchid(matchids[i]);
				int flag = appMapper.queryCountBymatchid(app);
				if (flag == 0) {
					appMapper.giveupsecond(app);
				} else {
					if (str.length() == 0) {
						str = matchids[i];
					} else {
						str = str + "," + matchids[i];
					}
				}
			}
			if (str.length() > 0) {
				msg.setSuccess(false);
				msg.setMsg("单据号：" + str + " 已经使用不允许弃审");
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 根据车号查询业务类型
	 * 
	 * @param dtype
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryOperatypeBycarno.do")
	public List<Operaconfig> queryOperatypeBycarno(Operaconfig oper, ModelMap model) {
		return appMapper.queryOperatypeBycarno(oper);
	}

	@RequestMapping("/application/queryFdocument.do")
	public String fdocumentinfo(ModelMap modelMap) {
		return "/commonpage/fdocumentinfo";
	}
    
	@RequestMapping("/application/applicationinfo.do")
	public String applicationinfo(String carno, String cardstate, ModelMap modelMap) {
		modelMap.addAttribute("carno", carno);
		modelMap.addAttribute("cardstate", cardstate);
		return "/commonpage/applicationinfo";
	}

	@ResponseBody
	@RequestMapping(value = "/application/queryFdocumentBymatchid.do")
	public Message queryFdocumentBymatchid(Applicationbill app, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(appMapper.queryFdocumentBymatchid(app));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/application/querydocByitemid.do")
	public Message querydocByitemid(Applicationbill app, ModelMap model) {
		Message msg = new Message();
		try {
			msg.setRows(appMapper.queryFdocumentBymatchid(app));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/application/queryAppBypitemid.do")
	public Message queryAppBypitemid(Applicationbill app, ModelMap model) {
		Message msg = new Message();
		try {
			String saleitemids = "";
			String planids = "";
			if (app.getSaleitemid() != null) {
				String[] itemlist = app.getSaleitemid().split(",");
				if (itemlist.length == 1) {
					if (!"".equals(itemlist[0])) {
						saleitemids = "'" + itemlist[0] + "'";
					}
				} else {
					for (int i = 0; i < itemlist.length; i++) {
						if (i == 0) {
							if (!"".equals(itemlist[i])) {
								saleitemids = "'" + itemlist[i] + "',";
							}

						} else if (i == itemlist.length - 1) {
							if (!"".equals(itemlist[i])) {
								saleitemids = saleitemids + "'" + itemlist[i] + "'";
							}

						} else {
							if (!"".equals(itemlist[i])) {
								saleitemids = saleitemids + "'" + itemlist[i] + "',";
							}
						}
					}
				}

			}
			if (app.getPlanid() != null) {
				String[] planidlist = app.getPlanid().split(",");
				if (planidlist.length == 1) {
					if (!"".equals(planidlist[0])) {
						planids = "'" + planidlist[0] + "'";
					}

				} else {
					for (int i = 0; i < planidlist.length; i++) {
						if (i == 0) {
							if (!"".equals(planidlist[i])) {
								planids = "'" + planidlist[i] + "',";
							}
						} else if (i == planidlist.length - 1) {
							if (!"".equals(planidlist[i])) {
								planids = planids + "'" + planidlist[i] + "'";
							}
						} else {
							if (!"".equals(planidlist[i])) {
								planids = planids + "'" + planidlist[i] + "',";
							}

						}
					}
				}

			}
			app.setSaleitemid(saleitemids);
			app.setPlanid(planids);
			msg.setRows(appMapper.queryAppinfoBypitemid(app));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/application/queryAllAppBypitemid.do")
	public Message queryAllAppBypitemid(Applicationbill app, ModelMap model) {
		Message msg = new Message();
		try {
			String saleitemids = "";
			String planids = "";
			if (app.getSaleitemid() != null) {
				String[] itemlist = app.getSaleitemid().split(",");
				if (itemlist.length == 1) {
					if (!"".equals(itemlist[0])) {
						saleitemids = "'" + itemlist[0] + "'";
					}
				} else {
					for (int i = 0; i < itemlist.length; i++) {
						if (i == 0) {
							if (!"".equals(itemlist[i])) {
								saleitemids = "'" + itemlist[i] + "',";
							}

						} else if (i == itemlist.length - 1) {
							if (!"".equals(itemlist[i])) {
								saleitemids = saleitemids + "'" + itemlist[i] + "'";
							}

						} else {
							if (!"".equals(itemlist[i])) {
								saleitemids = saleitemids + "'" + itemlist[i] + "',";
							}
						}
					}
				}

			}
			if (app.getPlanid() != null) {
				String[] planidlist = app.getPlanid().split(",");
				if (planidlist.length == 1) {
					if (!"".equals(planidlist[0])) {
						planids = "'" + planidlist[0] + "'";
					}

				} else {
					for (int i = 0; i < planidlist.length; i++) {
						if (i == 0) {
							if (!"".equals(planidlist[i])) {
								planids = "'" + planidlist[i] + "',";
							}
						} else if (i == planidlist.length - 1) {
							if (!"".equals(planidlist[i])) {
								planids = planids + "'" + planidlist[i] + "'";
							}
						} else {
							if (!"".equals(planidlist[i])) {
								planids = planids + "'" + planidlist[i] + "',";
							}

						}
					}
				}

			}
			app.setSaleitemid(saleitemids);
			app.setPlanid(planids);
			msg.setRows(appMapper.queryAllAppBypitemid(app));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@RequestMapping(value = "/print/measurebillprint.do")
	public String measurebillprint(String matchid, ModelMap model) {
		model.addAttribute("measurelist", bcommonMapper.queryMeasureDetail(matchid));
		return "measurebillprint";
	}

	@RequestMapping(value = "/print/printqtbill.do")
	public String printqtbill(Applicationbill app, ModelMap model) {
		List<Applicationbill> printData = appMapper.queryDocumentBymatchids(app.getMatchid().split(","));
		List<String> matchids = new ArrayList<String>();
		List<Map<String, String>> printDataHead = new ArrayList<Map<String, String>>();
		for (Applicationbill ap : printData) {
			if (!matchids.contains(ap.getCarno() + ap.getMatchid())) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("reserve2", ap.getReserve2());
				item.put("matchid", ap.getMatchid());
				item.put("carno", ap.getCarno());
				item.put("saleman", ap.getSaleman());
				item.put("sourcename", ap.getSourcename());
				item.put("targetname", ap.getTargetname());
				item.put("creator", ap.getCreator());
				item.put("reserve1", ap.getReserve1());
				item.put("inoutdate", ap.getInoutdate());
				item.put("approverfirst", ap.getApproverfirst());
				item.put("firsttime", ap.getFirsttime());
				item.put("approversecond", ap.getApproversecond());
				item.put("secondtime", ap.getSecondtime());
				item.put("memo", ap.getMemo());
				matchids.add(ap.getCarno() + ap.getMatchid());
				printDataHead.add(item);
			}
		}
		model.addAttribute("printheaddata", printDataHead);
		model.addAttribute("printbodydata", printData);
		return "printqtbill";
	}
	
	/**
	 * 查询允许制卡的信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/application/queryapplicationinfo")
	public String queryapplicationinfo(ModelMap model) {
		return "queryapplicationinfo";
	}
	/**
	 * 查询循环单据原始单据号
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/application/queryFdocument")
	public String queryFdocument(String documentcode,String types,ModelMap model) {
		model.addAttribute("fdocumentno", appMapper.queryFdocumentcode(documentcode));
		model.addAttribute("types", types);
		return "/commonpage/fdocument";
	}
}
