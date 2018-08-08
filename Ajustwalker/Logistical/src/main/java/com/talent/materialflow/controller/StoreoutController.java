package com.talent.materialflow.controller;

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

import com.talent.materialflow.model.Storein;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.StoreoutService;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.StoreinMapper;
import com.talent.materialflow.service.mapper.StoreoutMapper;

@Controller
public class StoreoutController extends BaseController {

	@Resource
	private StoreoutMapper storeoutMapper;
	@Resource
	private StoreoutService storeoutService;
	@Resource
	private BCommonMapper bcommonMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private StoreinMapper storeinMapper;

	/**
	 * 废副产品出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeout")
	public String storeout(ModelMap model) {
		model.addAttribute("unitcode", "2SPL");
		model.addAttribute("unitname", "废副产品库");
		return "storeout";
	}

	/**
	 * 出库信息查询
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/querystoreout")
	public String querystoreout(ModelMap model) {
		return "storeout";
	}

	/**
	 * 炼铁厂焦场出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutLTJC")
	public String storeoutLTJC(ModelMap model) {
		model.addAttribute("unitcode", "S16080027");
		model.addAttribute("unitname", "炼铁厂焦场");
		return "storeout";
	}

	/**
	 * 炼铁厂原料监卸出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutLTYL")
	public String storeoutLTYL(ModelMap model) {
		model.addAttribute("unitcode", "S16080029");
		model.addAttribute("unitname", "炼铁厂原料监卸");
		return "storeout";
	}

	/**
	 * 储运耐材炼钢点出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeinLGNC")
	public String storeinLGNC(ModelMap model) {
		model.addAttribute("unitcode", "S16080023");
		model.addAttribute("unitname", "储运耐材炼钢点");
		return "storeout";
	}

	/**
	 * 炼钢耐材出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutliangnc")
	public String storeoutliangnc(ModelMap model) {
		model.addAttribute("unitcode", "S16080012");
		model.addAttribute("unitname", "炼钢耐材");
		return "storeout";
	}

	/**
	 * 储运中心合金库2出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutpshj2")
	public String storeoutpshj2(ModelMap model) {
		model.addAttribute("unitcode", "S16080003");
		model.addAttribute("unitname", "储运中心合金库2");
		return "storeout";
	}

	/**
	 * 焦化厂煤场出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutJHMC")
	public String storeoutJHMC(ModelMap model) {
		model.addAttribute("unitcode", "S16080020");
		model.addAttribute("unitname", "焦化厂煤场");
		return "storeout";
	}

	/**
	 * 焦化厂焦油出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutJHJY")
	public String storeoutJHJY(ModelMap model) {
		model.addAttribute("unitcode", "S16080019");
		model.addAttribute("unitname", "焦化厂焦油");
		return "storeout";
	}

	/**
	 * 储运中心检验科2出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutPSJY2")
	public String storeoutPSJY2(ModelMap model) {
		model.addAttribute("unitcode", "S16080033");
		model.addAttribute("unitname", "储运中心检验科2");
		return "storeout";
	}

	/**
	 * 储运中心电机土产库出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutPSTC")
	public String storeoutPSTC(ModelMap model) {
		model.addAttribute("unitcode", "S16080035");
		model.addAttribute("unitname", "储运中心电机土产库");
		return "storeout";
	}

	/**
	 * 辅料库四层出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutFLK4C")
	public String storeoutFLK4C(ModelMap model) {
		model.addAttribute("unitcode", "S16080008");
		model.addAttribute("unitname", "辅料库四层");
		return "storeout";
	}

	/**
	 * 辅料库二层出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutFLK2C")
	public String storeoutFLK2C(ModelMap model) {
		model.addAttribute("unitcode", "S16080006");
		model.addAttribute("unitname", "辅料库二层");
		return "storeout";
	}

	/**
	 * 辅料库三层出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutFLK3C")
	public String storeoutFLK3C(ModelMap model) {
		model.addAttribute("unitcode", "S16080007");
		model.addAttribute("unitname", "辅料库三层");
		return "storeout";
	}

	/**
	 * 储运中心检验科1出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutPSJY1")
	public String storeoutPSJY1(ModelMap model) {
		model.addAttribute("unitcode", "S16080032");
		model.addAttribute("unitname", "储运中心检验科1");
		return "storeout";
	}

	/**
	 * 储运回收科出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutfjwx")
	public String storeoutfjwx(ModelMap model) {
		model.addAttribute("unitcode", "S16080005");
		model.addAttribute("unitname", "储运回收科");
		return "storeout";
	}

	/**
	 * 储运中心检验科3出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutPSJY3")
	public String storeoutPSJY3(ModelMap model) {
		model.addAttribute("unitcode", "S16080034");
		model.addAttribute("unitname", "储运中心检验科3");
		return "storeout";
	}

	/**
	 * 储运中心验收科4出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutcyysk4")
	public String storeoutcyysk4(ModelMap model) {
		model.addAttribute("unitcode", "S16080009");
		model.addAttribute("unitname", "储运中心验收科4");
		return "storeout";
	}

	/**
	 * 储运中心加油站出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutPSYK")
	public String storeoutPSYK(ModelMap model) {
		model.addAttribute("unitcode", "S16080036");
		model.addAttribute("unitname", "储运中心加油站");
		return "storeout";
	}

	/**
	 * 储运中心备件库出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutPSBJ")
	public String storeoutPSBJ(ModelMap model) {
		model.addAttribute("unitcode", "S16080030");
		model.addAttribute("unitname", "储运中心备件库");
		return "storeout";
	}

	/**
	 * 炼铁高炉耐材出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutGLNC")
	public String storeoutGLNC(ModelMap model) {
		model.addAttribute("unitcode", "S16080018");
		model.addAttribute("unitname", "炼铁高炉耐材");
		return "storeout";
	}

	/**
	 * 储运中心物资配送出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutCYWZP")
	public String storeoutCYWZP(ModelMap model) {
		model.addAttribute("unitcode", "S16080017");
		model.addAttribute("unitname", "储运中心物资配送");
		return "storeout";
	}

	/**
	 * 储运生铁废钢库出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutCYFGK")
	public String storeoutCYFGK(ModelMap model) {
		model.addAttribute("unitcode", "S16080016");
		model.addAttribute("unitname", "储运生铁废钢库");
		return "storeout";
	}

	/**
	 * 钢坯货场出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutgphc")
	public String storeoutgphc(ModelMap model) {
		model.addAttribute("unitcode", "S16080011");
		model.addAttribute("unitname", "钢坯货场");
		return "storeout";
	}

	/**
	 * 不锈钢扒渣站出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutbxgbzz")
	public String storeoutbxgbzz(ModelMap model) {
		model.addAttribute("unitcode", "S16080010");
		model.addAttribute("unitname", "不锈钢扒渣站");
		return "storeout";
	}

	/**
	 * 炼钢废钢库出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutLGFGK")
	public String storeoutLGFGK(ModelMap model) {
		model.addAttribute("unitcode", "S16080022");
		model.addAttribute("unitname", "炼钢废钢库");
		return "storeout";
	}

	/**
	 * 炼铁厂白灰出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutLTBH")
	public String storeoutLTBH(ModelMap model) {
		model.addAttribute("unitcode", "S16080025");
		model.addAttribute("unitname", "炼铁厂白灰");
		return "storeout";
	}

	/**
	 * 炼钢厂低仓出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutLGDC")
	public String storeoutLGDC(ModelMap model) {
		model.addAttribute("unitcode", "S16080021");
		model.addAttribute("unitname", "炼钢厂低仓");
		return "storeout";
	}

	/**
	 * 质检化验用品出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutHYYP")
	public String storeoutHYYP(ModelMap model) {
		model.addAttribute("unitcode", "S16080004");
		model.addAttribute("unitname", "质检化验用品");
		return "storeout";
	}

	/**
	 * 炼铁厂煤场出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutLTMC")
	public String storeoutLTMC(ModelMap model) {
		model.addAttribute("unitcode", "S16080028");
		model.addAttribute("unitname", "炼铁厂煤场");
		return "storeout";
	}

	/**
	 * 储运货场（酸洗）出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeoutcysx")
	public String storeoutcysx(ModelMap model) {
		model.addAttribute("unitcode", "S16080013");
		model.addAttribute("unitname", "储运货场（酸洗）");
		return "storeout";
	}

	/**
	 * 炼铁一烧工区配料出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeout1SPL")
	public String storeout1SPL(ModelMap model) {
		model.addAttribute("unitcode", "S16080014");
		model.addAttribute("unitname", "炼铁一烧工区配料");
		return "storeout";
	}

	/**
	 * 炼铁厂二烧工区配料出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeout2SPL")
	public String storeout2SPL(ModelMap model) {
		model.addAttribute("unitcode", "S16080015");
		model.addAttribute("unitname", "炼铁厂二烧工区配料");
		return "storeout";
	}

	/**
	 * 炼铁渣场出库
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/storeout/storeout13")
	public String storeout13(ModelMap model) {
		model.addAttribute("unitcode", "S16080001");
		model.addAttribute("unitname", "炼铁渣场");
		return "storeout";
	}

	@ResponseBody
	@RequestMapping(value = "/storeout/queryPage.do")
	public Message queryPage(Storein storein, ModelMap model, Pagination<Storein> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(storeoutMapper.queryList(storein));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/storeout/queryCount.do")
	public Message queryCount(Storein storein, ModelMap model, Pagination<Storein> page) {
		Message msg = new Message();
		try {
			Storein st = storeoutMapper.queryCount(storein);
			msg.setData(st);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	// 查询可以修改的信息列表
	@ResponseBody
	@RequestMapping(value = "/storeout/queryStoreout.do")
	public Storein queryStoreout(Storein sin) {
		return sin;
	}

	/**
	 * 通过车号查询入库信息
	 * 
	 * @param storeout
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storeout/queryInfoBycarno.do")
	public Message queryInfoBycarno(Storein storein, ModelMap model) {
		Message msg = new Message();
		try {
			msg.setRows(storeoutMapper.queryInfoBycarno(storein));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
		// return (Message)storeoutService.queryInfoBycarno(storein);
	}

	/**
	 * 保存信息前验证
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storeout/beforeinsertStoreout.do")
	public Message beforeinsertStorein(Storein storein) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			storein.setCreateoperacode(user.getUsername());
			storein.setCreateoperaname(user.getDisplayname());
			// Currtemp curr = new Currtemp();
			Map<String, String> map = storein.getExparams().get(0);
			/*
			 * curr.setMatchid(map.get("matchid"));
			 * curr.setUnitcode(storein.getUnitcode());
			 * curr.setUnitname(storein.getUnitname()); msg =
			 * bcommonService.beforeinsert(curr, "SOUT"); if
			 * ("0".equals(msg.getMfunc())) {// 如果返回信息为0，直接保存信息
			 * storeoutService.insertStoreout(storein);
			 * 
			 * }
			 */
			msg = bcommonService.queryUpNode(map.get("matchid"), "SOUT");
			if (msg.isSuccess()) {// 如果返回信息为0，直接保存信息
				storeoutService.insertStoreout(storein);
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 添加出库信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storeout/insertStoreout.do")
	public Message insertStorein(Storein storein) {
		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			storein.setCreateoperacode(user.getUsername());
			storein.setCreateoperaname(user.getDisplayname());
			Map<String, String> map = storein.getExparams().get(0);
			msg = bcommonService.queryUpNode(map.get("matchid"), "SOUT");
			if (msg.isSuccess()) {// 如果返回信息为0，直接保存信息
				storeoutService.insertStoreout(storein);
			}
			// storeoutService.insertStoreout(storein);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 作废出库信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storeout/cancelStoreout.do")
	public Message cancelStorein(Storein storein) {
		Message msg = new Message();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			storein.setCreateoperacode(user.getUsername());
			storein.setCreateoperaname(user.getDisplayname());
			msg = storeoutService.cancelStoreout(storein);
		} catch (Exception e) {
			
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 通过车号获取不验证作业点
	 * 
	 * @param storein
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storeout/queryInfoBycarnoNotcode.do")
	public Message queryInfoBycarnoNotcode(Storein storein, ModelMap model) {
		Message msg = new Message();
		try {
			String matchidlist = storeoutMapper.queryInfoBycarnoNotcode(storein.getCarno());
			if (matchidlist != null) {
				if (matchidlist.indexOf(storein.getStorecode()) == -1) {
					String[] list = matchidlist.split(",");
					String node = "";
					if (list.length == 1) {
						node = "'" + list[0] + "'";
					} else {
						for (int i = 0; i < list.length; i++) {
							if (i == 0) {
								node = "'" + list[i] + "',";
							} else if (i == list.length - 1) {
								node = node + "'" + list[i] + "'";
							} else {
								node = node + "'" + list[i] + "',";
							}
						}
					}
					if (list.length > 0) {
						storein.setUnitcode(node);
						Storein nodename = storeinMapper.querywnamebycode(storein);
						msg.setSuccess(false);
						msg.setMsg("请到以下作业点进行出库操作：</br>" + nodename.getUnitname());
					}
				} else {

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

}
