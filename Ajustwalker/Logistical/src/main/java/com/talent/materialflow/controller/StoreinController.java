package com.talent.materialflow.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.talent.materialflow.model.Currtemp;
import com.talent.materialflow.model.Storein;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.StoreinService;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.StoreinMapper;

@Controller
public class StoreinController extends BaseController {

	@Resource
	private StoreinMapper storeinMapper;
	@Resource
	private StoreinService storeinService;
	@Resource
	private BCommonMapper bcommonMapper;
	@Resource
	private BCommonService bcommonService;
	
	
	/**
	 * 原料库入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/allstorein")
	public String allstorein(ModelMap model,Storein storein) {
		model.addAttribute("unitcode", storein.getUnitcode());
		model.addAttribute("unitname", storein.getUnitname());
		return "storein";
	}
	/**
	 * 原料库入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/allstoreinflk")
	public String allstoreinflk(ModelMap model) {
		model.addAttribute("unitcode", "S16080059");
		model.addAttribute("unitname", "辅料库一层");
		return "storein";
	}
	/**
	 * 原料库入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storein")
	public String storein(ModelMap model) {
		model.addAttribute("unitcode", "S16080027");
		model.addAttribute("unitname", "原料库");
		return "storein";
	}
	
	/**
	 * 入库信息查询
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/querystorein")
	public String querystorein(ModelMap model) {
		return "storein";
	}

	/**
	 * 炼铁厂焦场入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinLTJC")
	public String storeinLTJC(ModelMap model) {
		model.addAttribute("unitcode", "S16080027");
		model.addAttribute("unitname", "炼铁厂焦场");
		return "storein";
	}
	/**
	 * 炼铁厂焦场入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinLTYLJC")
	public String storeinLTYLJC(ModelMap model) {
		model.addAttribute("unitcode", "S16080024");
		model.addAttribute("unitname", "炼铁厂原料131");
		return "storein";
	}
	
	/**
	 * 炼铁厂原料监卸入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinLTYL")
	public String storeinLTYL(ModelMap model) {
		model.addAttribute("unitcode", "S16080029");
		model.addAttribute("unitname", "炼铁厂原料监卸");
		return "storein";
	}

	/**
	 * 储运耐材炼钢点入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinLGNC")
	public String storeinLGNC(ModelMap model) {
		model.addAttribute("unitcode", "S16080023");
		model.addAttribute("unitname", "储运耐材炼钢点");
		return "storein";
	}

	/**
	 * 炼钢耐材入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinliangnc")
	public String storeinliangnc(ModelMap model) {
		model.addAttribute("unitcode", "S16080012");
		model.addAttribute("unitname", "炼钢耐材");
		return "storein";
	}

	/**
	 * 储运中心合金库2入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinpshj2")
	public String storeinpshj2(ModelMap model) {
		model.addAttribute("unitcode", "S16080003");
		model.addAttribute("unitname", "储运中心合金库2");
		return "storein";
	}

	/**
	 * 焦化厂煤场入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinJHMC")
	public String storeinJHMC(ModelMap model) {
		model.addAttribute("unitcode", "S16080020");
		model.addAttribute("unitname", "焦化厂煤场");
		return "storein";
	}

	/**
	 * 焦化厂焦油入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinJHJY")
	public String storeinJHJY(ModelMap model) {
		model.addAttribute("unitcode", "S16080019");
		model.addAttribute("unitname", "焦化厂焦油");
		return "storein";
	}

	/**
	 * 储运中心检验科2入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinPSJY2")
	public String storeinPSJY2(ModelMap model) {
		model.addAttribute("unitcode", "S16080033");
		model.addAttribute("unitname", "储运中心检验科2");
		return "storein";
	}

	/**
	 * 储运中心电机土产库入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinPSTC")
	public String storeinPSTC(ModelMap model) {
		model.addAttribute("unitcode", "S16080035");
		model.addAttribute("unitname", "储运中心电机土产库");
		return "storein";
	}

	/**
	 * 辅料库四层入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinFLK4C")
	public String storeinFLK4C(ModelMap model) {
		model.addAttribute("unitcode", "S16080008");
		model.addAttribute("unitname", "辅料库四层");
		return "storein";
	}

	/**
	 * 辅料库二层入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinFLK2C")
	public String storeinFLK2C(ModelMap model) {
		model.addAttribute("unitcode", "S16080006");
		model.addAttribute("unitname", "辅料库二层");
		return "storein";
	}

	/**
	 * 辅料库三层入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinFLK3C")
	public String storeinFLK3C(ModelMap model) {
		model.addAttribute("unitcode", "S16080007");
		model.addAttribute("unitname", "辅料库三层");
		return "storein";
	}

	/**
	 * 储运中心检验科1入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinPSJY1")
	public String storeinPSJY1(ModelMap model) {
		model.addAttribute("unitcode", "S16080032");
		model.addAttribute("unitname", "储运中心检验科1");
		return "storein";
	}

	/**
	 * 储运回收科入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinfjwx")
	public String storeinfjwx(ModelMap model) {
		model.addAttribute("unitcode", "S16080005");
		model.addAttribute("unitname", "储运回收科");
		return "storein";
	}

	/**
	 * 储运中心检验科3入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinPSJY3")
	public String storeinPSJY3(ModelMap model) {
		model.addAttribute("unitcode", "S16080034");
		model.addAttribute("unitname", "储运中心检验科3");
		return "storein";
	}

	/**
	 * 储运中心验收科4入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeincyysk4")
	public String storeincyysk4(ModelMap model) {
		model.addAttribute("unitcode", "S16080009");
		model.addAttribute("unitname", "储运中心验收科4");
		return "storein";
	}

	/**
	 * 储运中心加油站入库入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinPSYK")
	public String storeinPSYK(ModelMap model) {
		model.addAttribute("unitcode", "S16080036");
		model.addAttribute("unitname", "储运中心加油站");
		return "storein";
	}

	/**
	 * 储运中心备件库入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinPSBJ")
	public String storeinPSBJ(ModelMap model) {
		model.addAttribute("unitcode", "S16080030");
		model.addAttribute("unitname", "储运中心备件库");
		return "storein";
	}

	/**
	 * 炼铁高炉耐材入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinGLNC")
	public String storeinGLNC(ModelMap model) {
		model.addAttribute("unitcode", "S16080018");
		model.addAttribute("unitname", "炼铁高炉耐材");
		return "storein";
	}

	/**
	 * 储运中心物资配送入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinCYWZP")
	public String storeinCYWZP(ModelMap model) {
		model.addAttribute("unitcode", "S16080017");
		model.addAttribute("unitname", "储运中心物资配送");
		return "storein";
	}

	/**
	 * 储运生铁废钢库入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinCYFGK")
	public String storeinCYFGK(ModelMap model) {
		model.addAttribute("unitcode", "S16080016");
		model.addAttribute("unitname", "储运生铁废钢库");
		return "storein";
	}

	/**
	 * 钢坯货场入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeingphc")
	public String storeingphc(ModelMap model) {
		model.addAttribute("unitcode", "S16080011");
		model.addAttribute("unitname", "钢坯货场");
		return "storein";
	}

	/**
	 * 不锈钢扒渣站入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinbxgbzz")
	public String storeinbxgbzz(ModelMap model) {
		model.addAttribute("unitcode", "S16080010");
		model.addAttribute("unitname", "不锈钢扒渣站");
		return "storein";
	}

	/**
	 * 炼钢废钢库入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinLGFGK")
	public String storeinLGFGK(ModelMap model) {
		model.addAttribute("unitcode", "S16080022");
		model.addAttribute("unitname", "炼钢废钢库");
		return "storein";
	}

	/**
	 * 炼铁厂白灰入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinLTBH")
	public String storeinLTBH(ModelMap model) {
		model.addAttribute("unitcode", "S16080025");
		model.addAttribute("unitname", "炼铁厂白灰");
		return "storein";
	}

	/**
	 * 炼钢厂低仓入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinLGDC")
	public String storeinLGDC(ModelMap model) {
		model.addAttribute("unitcode", "S16080021");
		model.addAttribute("unitname", "炼钢厂低仓");
		return "storein";
	}

	/**
	 * 质检化验用品入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinHYYP")
	public String storeinHYYP(ModelMap model) {
		model.addAttribute("unitcode", "S16080004");
		model.addAttribute("unitname", "质检化验用品");
		return "storein";
	}

	/**
	 * 炼铁厂煤场入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeinLTMC")
	public String storeinLTMC(ModelMap model) {
		model.addAttribute("unitcode", "S16080028");
		model.addAttribute("unitname", "炼铁厂煤场");
		return "storein";
	}

	/**
	 * 储运货场（酸洗）入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storeincysx")
	public String storeincysx(ModelMap model) {
		model.addAttribute("unitcode", "S16080013");
		model.addAttribute("unitname", "储运货场（酸洗）");
		return "storein";
	}

	/**
	 * 炼铁一烧工区配料入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storein1SPL")
	public String storein1SPL(ModelMap model) {
		model.addAttribute("unitcode", "S16080014");
		model.addAttribute("unitname", "炼铁一烧工区配料");
		return "storein";
	}

	/**
	 * 炼铁厂二烧工区配料入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storein2SPL")
	public String storein2SPL(ModelMap model) {
		model.addAttribute("unitcode", "S16080015");
		model.addAttribute("unitname", "炼铁厂二烧工区配料");
		return "storein";
	}

	/**
	 * 炼铁渣场入库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storein/storein13")
	public String storein13(ModelMap model) {
		model.addAttribute("unitcode", "S16080001");
		model.addAttribute("unitname", "炼铁渣场");
		return "storein";
	}

	@ResponseBody
	@RequestMapping(value = "/storein/queryPage.do")
	public Message queryPage(Storein storein, ModelMap model, Pagination<Storein> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(storeinMapper.queryList(storein));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	@ResponseBody
	@RequestMapping(value = "/storein/queryCount.do")
	public Message queryCount(Storein storein, ModelMap model, Pagination<Storein> page) {
		Message msg = new Message();
		try {
			Storein st = storeinMapper.queryCount(storein);
			msg.setData(st);;
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	// 查询可以修改的信息列表
	@ResponseBody
	@RequestMapping(value = "/storein/queryStorein.do")
	public Storein queryStorein(Storein sin) {
		/*
		 * Storein sin =new Storein(); sin.setStorecode(unitcode);
		 * sin.setStorename(unitname);
		 */
		return sin;
	}

	/**
	 * 通过车号查询入库信息
	 * 
	 * @param storein
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storein/queryInfoBycarno.do")
	public Message queryInfoBycarno(Storein storein, ModelMap model) {
		Message msg = new Message();
		try {
			msg.setRows(storeinMapper.queryInfoBycarno(storein));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
		// return (Message) storeinService.queryInfoBycarno(storein);
	}

	/**
	 * 通过车号获取不验证作业点
	 * 
	 * @param storein
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storein/queryInfoBycarnoNotcode.do")
	public Message queryInfoBycarnoNotcode(Storein storein, ModelMap model) {
		Message msg = new Message();
		try {
			String matchidlist = storeinMapper.queryInfoBycarnoNotcode(storein.getCarno());
			if(matchidlist!=null){
				 if(matchidlist.indexOf(storein.getStorecode())==-1){
					 String[] list = matchidlist.split(",");
			            String node="";
			            if(list.length==1){
			            	node = "'" + list[0] + "'"; ;
			            }else{
			            	for (int i = 0; i < list.length; i++) {
								if (i == 0) {
									node = "'" + list[i] + "',";
								} else if (i == list.length - 1) {
									node = node + "'" + list[i]  + "'";
								} else {
									node = node + "'" + list[i]  + "',";
								}
							}
			            }
						if (list.length > 0) {
							storein.setUnitcode(node);
							Storein  nodename = storeinMapper.querywnamebycode(storein);
							msg.setSuccess(false);
							msg.setMsg("请到以下作业点进行入库操作：</br>"+nodename.getUnitname());
						}

				  }else{
					  
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
	 * 添加入库信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storein/beforeinsertStorein.do")
	public Message beforeinsertStorein(Storein storein) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Currtemp curr = new Currtemp();
			Map<String, String> map = storein.getExparams().get(0);
			curr.setMatchid(map.get("matchid"));
			curr.setUnitcode(storein.getUnitcode());
			curr.setUnitname(storein.getUnitname());
			//msg = bcommonService.beforeinsert(curr, "SIN");
			/*if ("0".equals(msg.getMfunc())) {// 如果返回信息为0，直接保存信息
				storein.setCreator(user.getDisplayname());
				storein.setCreateoperacode(user.getUsername());
				msg = storeinService.insertStorein(storein);
			}*/
			msg = bcommonService.queryUpNode(map.get("matchid"), "SIN");
			if (msg.isSuccess()) {// 如果返回信息为0，直接保存信息
				storein.setCreator(user.getDisplayname());
				storein.setCreateoperacode(user.getUsername());
				msg = storeinService.insertStorein(storein);
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("入库失败！");
		}
		return msg;
	}

	/**
	 * 添加入库信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storein/insertStorein.do")
	public Message insertStorein(Storein storein) {
		Message msg = new Message();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			storein.setCreator(user.getDisplayname());
			storein.setCreateoperacode(user.getUsername());
			Map<String, String> map = storein.getExparams().get(0);
			msg = bcommonService.queryUpNode(map.get("matchid"), "SIN");
			if (msg.isSuccess()) {// 如果返回信息为0，直接保存信息
				msg = storeinService.insertStorein(storein);
			}
			
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 作废入库信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storein/cancelStorein.do")
	public Message cancelStorein(Storein storein) {
		Message msg = new Message();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			storein.setCanceler(user.getDisplayname());
			msg = storeinService.cancelStorein(storein);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@RequestMapping(value = "/print/workbillbeforeprint.do")
	public String workbillbeforeprint(Storein storein, ModelMap model) {
		List<Storein> printData = storeinMapper.queryPrintInfoByitemid(storein.getMatchid().split(","));
		List<String> carnos = new ArrayList<String>();
		List<Map<String, String>> printDataHead = new ArrayList<Map<String, String>>();
		for (Storein in : printData) {
			if (!carnos.contains( in.getCarno() + (in.getMaterialcode() == null ? "null" : in.getMaterialcode()).substring(0, 3))) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("matchid", in.getMatchid());
				item.put("operaname", in.getOperaname());
				item.put("recetype", in.getRecetype() == 1 ? "是" : "否");
				item.put("carno", in.getCarno());
				item.put("entrytime", in.getReserve9());
				item.put("usermemo", storein.getUsermemo());
				item.put("prematerialcode",(in.getMaterialcode() == null ? "null" : in.getMaterialcode()).substring(0, 3));
				carnos.add(in.getCarno() + (in.getMaterialcode() == null ? "null" : in.getMaterialcode()).substring(0, 3));
				printDataHead.add(item);
			}
		}
		model.addAttribute("printheaddata", printDataHead);
		model.addAttribute("printbodydata", printData);
		return "beforeworkbillprint";
	}

	@RequestMapping(value = "/print/workbillprint.do")
	public String workbillprint(Storein storein, ModelMap model) {
		List<Storein> printData = storeinMapper.queryPrintData(storein.getMatchid().split(","));
		List<String> carnos = new ArrayList<String>();
		List<Map<String, String>> printDataHead = new ArrayList<Map<String, String>>();
		for (Storein in : printData) {
			if (!carnos.contains(in.getCarno() + (in.getMaterialcode() == null ? "null" : in.getMaterialcode()).substring(0, 3))) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("matchid", in.getMatchid());
				item.put("operaname", in.getOperaname());
				item.put("recetype", in.getRecetype() == 1 ? "是" : "否");
				item.put("carno", in.getCarno());
				item.put("entrytime", in.getReserve9());
				item.put("usermemo", in.getUsermemo());
				item.put("prematerialcode",(in.getMaterialcode() == null ? "null" : in.getMaterialcode()).substring(0, 3));
				carnos.add(in.getCarno() + (in.getMaterialcode() == null ? "null" : in.getMaterialcode()).substring(0, 3));
				printDataHead.add(item);
			}
		}
		model.addAttribute("printheaddata", printDataHead);
		model.addAttribute("printbodydata", printData);
		return "workbillprint";
	}
	
}
