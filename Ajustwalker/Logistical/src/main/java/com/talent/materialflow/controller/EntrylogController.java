package com.talent.materialflow.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent.core.annotation.Rule;
//import com.talent.core.annotation.Rule;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.talent.core.util.HttpUtils;
import com.talent.materialflow.common.LogisticalRuleCalc;
import com.talent.materialflow.model.Applicationbill;
import com.talent.materialflow.model.BCommon;
import com.talent.materialflow.model.ComboxData;
//import com.talent.materialflow.common.MeasureRuleCalc;
import com.talent.materialflow.model.Currtemp;
import com.talent.materialflow.model.Entrylog;
import com.talent.materialflow.model.Gateparam;
import com.talent.materialflow.model.Storein;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.EntrylogService;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.EntrylogMapper;

@Controller
public class EntrylogController extends BaseController {

	@Resource
	private EntrylogMapper entryMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private EntrylogService entryService;
	@Autowired
	private LogisticalRuleCalc logisticalRuleCalc;
	@Resource
	private BCommonMapper bcommonMapper;
	@Autowired
	private HttpUtils httpUtils;

	@RequestMapping(value = "/entry/entrylog")
	public String entrylog(Entrylog elog, ModelMap model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("unitcode", elog.getUnitcode());
		model.addAttribute("unitname", elog.getUnitname());
		model.addAttribute("creator", user.getDisplayname());
		return "entrylog";
	}

	@ResponseBody
	@RequestMapping(value = "/entry/queryPage.do")
	public Message queryPage(Entrylog elog, ModelMap model, Pagination<Entrylog> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(entryMapper.queryEntrylog(elog));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 通过卡号验证卡和车辆、获取业务信息
	 * 
	 * @param elog
	 * @param model
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/entry/queryInList.do")
	public Message queryInList(Entrylog elog, ModelMap model) {
		Message msg = new Message();
		try {
			msg = entryService.queryCartype(elog);
			if (msg.isSuccess()) {
				if ("3".equals(msg.getMfunc())) {// 指令卡不判断业务，直接抬杠放行
					msg.setMfunc(msg.getMfunc());
					Entrylog entry = new Entrylog();
					entry.setCarno(String.valueOf(msg.getMore()));
					entry.setIcid(elog.getIcid());
					entry.setGatename("北门");// 暂时这么写，后续更改为传递参数
					entry.setEntrytype("1");
					entry.setCartype("3");
					entryMapper.insertEntrylog(entry);
					List<Entrylog> list = new ArrayList<Entrylog>();
					list.add(entry);
					msg.setRows(list);
				} else if ("2".equals(msg.getMfunc())) {// 指令卡不判断业务，直接抬杠放行
					msg.setSuccess(false);
					msg.setMsg("此卡为操作员卡");
					msg.setMfunc(msg.getMfunc());
				} else {

					elog.setCartype(msg.getMfunc());// 卡类型放入对象
					Currtemp curr = new Currtemp();
					curr.setOperatype("90");
					curr.setProcesslink("IN");
					msg = logisticalRuleCalc.flagsCheck(curr, Rule.GETING_INFO);
					if (msg.isSuccess()) {
						msg = entryService.queryInList(elog);// 根据卡号、车号判断卡和车辆状态、读取业务信息
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("进厂服务查询操作失败！");
		}
		return msg;
	}

	/**
	 * 通过卡号验证卡和车辆、获取业务信息
	 * 
	 * @param elog
	 * @param model
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/entry/queryNewInList.do")
	public Message queryNewInList(Entrylog elog, ModelMap model) {
		Message msg = new Message();
		try {
			msg = entryService.queryCartype(elog);
			if (msg.isSuccess()) {
				if ("3".equals(msg.getMfunc())) {// 指令卡不判断业务，直接抬杠放行
					if ((msg.getMore().toString().indexOf("东南门") != -1 && elog.getGatename().indexOf("东南门") != -1)
						|| (msg.getMore().toString().indexOf("北门") != -1&& elog.getGatename().indexOf("北门") != -1)
						|| (msg.getMore().toString().indexOf("钢材市场门") != -1&& elog.getGatename().indexOf("钢材市场门") != -1)
						|| (msg.getMore().toString().indexOf("钢市二道门") != -1&& elog.getGatename().indexOf("钢市二道门") != -1)
						|| (msg.getMore().toString().indexOf("正门二道门") != -1&& elog.getGatename().indexOf("正门二道门") != -1)
						|| (msg.getMore().toString().indexOf("焦化二道门") != -1&& elog.getGatename().indexOf("焦化二道门") != -1)
					  ) {
						msg.setMfunc(msg.getMfunc());
						Entrylog entry = new Entrylog();
						entry.setCarno(String.valueOf(msg.getMore()));
						entry.setIcid(elog.getIcid());
						entry.setGatecode(elog.getGatecode());
						entry.setGatename(elog.getGatename());// 暂时这么写，后续更改为传递参数
						entry.setEntrytype("1");
						entry.setCartype("3");
						entryMapper.insertEntrylog(entry);
						List<Entrylog> list = new ArrayList<Entrylog>();
						list.add(entry);
						msg.setRows(list);
 
					}else{
						msg.setSuccess(false);
						msg.setMsg("此卡为："+msg.getMore()+"，无法进行抬杠 ");
						msg.setMfunc(msg.getMfunc());
					}
					
				} else if ("2".equals(msg.getMfunc())) {// 指令卡不判断业务，直接抬杠放行
					msg.setSuccess(false);
					msg.setMsg("此卡为操作员卡");
					msg.setMfunc(msg.getMfunc());
				} else {

					elog.setCartype(msg.getMfunc());// 卡类型放入对象
					Currtemp curr = new Currtemp();
					curr.setOperatype("90");
					curr.setProcesslink("IN");
					msg = logisticalRuleCalc.flagsCheck(curr, Rule.GETING_INFO);
					if (msg.isSuccess()) {
						msg = entryService.queryNewInList(elog);// 根据卡号、车号判断卡和车辆状态、读取业务信息
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("进厂服务查询操作失败！"+e.getMessage());
		}
		/**
		 * 记录返回信息
		 */
		Entrylog emsg = entryMapper.queryCardList(elog);
		emsg.setEntrytype("进厂");
		emsg.setCardmemo(emsg.getUsermemo() + "　" + msg.getMsg());//返回内容
		emsg.setTypes(((Boolean)msg.isSuccess()).toString());//返回标记
		emsg.setGatename(elog.getGatename());
		entryMapper.insertEntrylogmsg(emsg);
		emsg=null;
		return msg;
	}

	/**
	 * 通过车号查询入库信息
	 * 
	 * @param storeout
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/entry/queryInfoBycarno.do")
	public Message queryInfoBycarno(Storein storein, ModelMap model) {
		Message msg = new Message();
		try {
			// msg.setRows(storeoutMapper.queryInfoBycarno(storein));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
		// return (Message)storeoutService.queryInfoBycarno(storein);
	}

	/**
	 * 通过卡号验证卡和车辆、获取业务信息
	 * 
	 * @param elog
	 * @param model
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/entry/queryCartype.do")
	public Message queryCartype(Entrylog elog, ModelMap model) {
		Message msg = new Message();
		try {
			msg = entryService.queryCartype(elog);
			if (!"2".equals(msg.getMfunc())) {
				msg.setSuccess(false);
				msg.setMsg("请刷操作员");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("进厂cartype服务查询操作失败！");
		}
		return msg;
	}

	/**
	 * 通过卡号验证卡和车辆、获取业务信息
	 * 
	 * @param elog
	 * @param model
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/entry/queryOutList.do")
	public Message queryOutList(Entrylog elog, ModelMap model) {
		Message msg = new Message();
		try {
			msg = entryService.queryCartype(elog);
			if (msg.isSuccess()) {
				if ("3".equals(msg.getMfunc())) {// 指令卡不判断业务，直接抬杠放行
					msg.setMfunc(msg.getMfunc());
					msg.setMfunc(msg.getMfunc());
					Entrylog entry = new Entrylog();
					entry.setCarno(String.valueOf(msg.getMore()));
					entry.setIcid(elog.getIcid());
					entry.setGatename("北门");// 暂时这么写，后续更改为传递参数
					entry.setEntrytype("2");
					entry.setCartype("3");
					entryMapper.insertEntrylog(entry);
					msg.setRows(new ArrayList<Object>());
				} else if ("2".equals(msg.getMfunc())) {
					msg.setSuccess(false);
					msg.setMfunc(msg.getMfunc());
					msg.setMsg("此卡为操作员卡");
				} else {
					msg = entryService.queryOutList(elog);// 根据卡号、车号判断卡和车辆状态、读取业务信息
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("出厂业务查询操作失败！");
		}
		return msg;
	}

	/**
	 * 通过卡号验证卡和车辆、获取业务信息
	 * 
	 * @param elog
	 * @param model
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/entry/queryNewOutList.do")
	public Message queryNewOutList(Entrylog elog, ModelMap model) {
		Message msg = new Message();
		try {
			msg = entryService.queryCartype(elog);
			if (msg.isSuccess()) {
				if ("3".equals(msg.getMfunc())) {// 指令卡不判断业务，直接抬杠放行
					if ((msg.getMore().toString().indexOf("东南门") != -1 && elog.getGatename().indexOf("东南门") != -1)
							|| (msg.getMore().toString().indexOf("北门") != -1&& elog.getGatename().indexOf("北门") != -1)
							|| (msg.getMore().toString().indexOf("钢材市场门") != -1&& elog.getGatename().indexOf("钢材市场门") != -1)
							|| (msg.getMore().toString().indexOf("钢市二道门") != -1&& elog.getGatename().indexOf("钢市二道门") != -1)
							|| (msg.getMore().toString().indexOf("正门二道门") != -1&& elog.getGatename().indexOf("正门二道门") != -1)
							|| (msg.getMore().toString().indexOf("焦化二道门") != -1&& elog.getGatename().indexOf("焦化二道门") != -1)
						  ) {
						msg.setMfunc(msg.getMfunc());
						msg.setMfunc(msg.getMfunc());
						Entrylog entry = new Entrylog();
						entry.setCarno(String.valueOf(msg.getMore()));
						entry.setIcid(elog.getIcid());
						entry.setGatename(elog.getGatecode());
						entry.setGatename(elog.getGatename());
						entry.setEntrytype("2");
						entry.setCartype("3"); // 车辆类型
						entryMapper.insertEntrylog(entry);
						msg.setRows(new ArrayList<Object>());
						}else{
							msg.setSuccess(false);
							msg.setMsg("此卡为："+msg.getMore()+"，无法进行抬杠 ");
							msg.setMfunc(msg.getMfunc());
						}
				} else if ("2".equals(msg.getMfunc())) {
					msg.setSuccess(false);
					msg.setMfunc(msg.getMfunc());
					msg.setMsg("此卡为操作员卡");
				} else {
					msg = entryService.queryNewOutList(elog);// 根据卡号、车号判断卡和车辆状态、读取业务信息
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("出厂业务查询操作失败！"+e.getMessage());
		}
		/**
		 * 记录返回信息
		 */
		Entrylog emsg = entryMapper.queryCardList(elog);
		emsg.setEntrytype("出厂");
		emsg.setCardmemo(emsg.getUsermemo() + "　" + msg.getMsg());//返回内容
		emsg.setTypes(((Boolean)msg.isSuccess()).toString());//返回标记
		emsg.setGatename(elog.getGatename());
		entryMapper.insertEntrylogmsg(emsg);
		emsg=null;
		return msg;
	}

	/**
	 * 添加异常进厂信息
	 * 
	 * @param elog
	 * @param model
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/entry/insertInEX.do")
	public Message insertInEX(Entrylog elog, ModelMap model) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			elog.setCreator(user.getDisplayname());
			entryMapper.insertEntrylog(elog);
			BCommon bcommon = new BCommon();
			bcommon.setMatchid(elog.getCarno());
			bcommon.setUsermemo(elog.getUsermemo() + " 异常进出厂");
			bcommon.setCreateman(user.getDisplayname());
			bcommonMapper.insertExceptinonlog(bcommon);
			user = null;
			elog = null;
			bcommon = null;
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("异常进厂操作失败" + e.getMessage());
		}
		return msg;
	}

	/**
	 * 添加异常进厂信息
	 * 
	 * @param elog
	 * @param model
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/entry/insertOutEX.do")
	public Message insertOutEX(Entrylog elog, ModelMap model) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Map<String, String> parameterMap = new HashMap<String, String>();
			parameterMap.put("carno", elog.getCarno());
			parameterMap.put("icid", elog.getIcid());
			parameterMap.put("gatename", elog.getGatename());
			parameterMap.put("usermemo", elog.getUsermemo());
			parameterMap.put("createman", user.getDisplayname());
			parameterMap.put("forceflag", "0");
			parameterMap.put("outflag", "0");
			parameterMap.put("outmsg", "");
			entryMapper.insertOutEntry(parameterMap);
			BCommon bcommon = new BCommon();
			bcommon.setMatchid(elog.getCarno());
			bcommon.setUsermemo(elog.getUsermemo() + " 异常进出厂");
			bcommon.setCreateman(user.getDisplayname());
			bcommonMapper.insertExceptinonlog(bcommon);
			user = null;
			elog = null;
			bcommon = null;
			parameterMap.clear();
			parameterMap = null;
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("异常进厂操作失败" + e.getMessage());
		}
		return msg;
	}

	/**
	 * 添加进厂信息
	 * 
	 * @param app
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/unauth/entry/insertInEntrylog.do")
	public Message insertInEntrylog(Entrylog entry) {
		Message msg = new Message();
		Entrylog emsg = entryMapper.queryCardList(entry);
		try {
			msg = entryService.insertInEntrylog(entry);
			String message = "";
			try {
				List<String> list = bcommonService.getMessage(msg.getMfunc(), "in");
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						message = httpUtils.get("http://192.168.2.42:7080/MeasureService/qualityInterface/ingate.do",
								list.get(i));
					}
				}
				System.out.println("message......." + message);
			} catch (Exception e) {

			}
			emsg.setEntrytype("进厂添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("进厂控制层操作失败！"+e.getMessage());
			emsg.setEntrytype("进厂添加异常"+e.getMessage());
		}
		emsg.setIcid(entry.getIcid());
		emsg.setCarno(entry.getCarno());
		emsg.setCardmemo(msg.getMsg());//返回内容
		emsg.setTypes(((Boolean)msg.isSuccess()).toString());//返回标记
		emsg.setGatename(entry.getGatename());
		entryMapper.insertEntrylogmsg(emsg);
		return msg;
	}

	/**
	 * 添加出厂信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/entry/insertOutEntrylog.do")
	public Message insertOutEntrylog(Entrylog entry) {
		Message msg = new Message();
		try {
			msg = entryService.insertOutEntrylog(entry);
			try {
				if (!"".equals(msg.getMfunc()) && msg.getMfunc() != null) {
					List<String> list = bcommonService.getMessage(msg.getMfunc(), "out");
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							httpUtils.get("http://192.168.2.42:7080/MeasureService/qualityInterface/outgate.do",
									list.get(i));
						}
					}
				}
			} catch (Exception e) {

			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("出厂控制层操作失败！" + e.getMessage());
		}
		/**
		 * 记录返回信息
		 */
		Entrylog emsg = new Entrylog();
		emsg.setIcid(entry.getIcid());
		emsg.setCarno(entry.getCarno());
		emsg.setCardmemo(msg.getMsg());//返回内容
		emsg.setEntrytype("出厂");
		emsg.setTypes(((Boolean)msg.isSuccess()).toString());//返回标记
		emsg.setGatename(entry.getGatename());
		entryMapper.insertEntrylogmsg(emsg);
		emsg=null;
		return msg;
	}

	/**
	 * 查询道闸控制信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/entry/queryGateparam.do")
	public Message queryGateparam(Gateparam gp) {
		Message msg = new Message();
		try {
			msg.setRows(entryMapper.queryGateparam(gp));

		} catch (Exception e) {
			System.out.print("异常........." + e);
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 添加线材信息
	 * 
	 * @param app
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/unauth/entry/insertXCtemp.do")
	public Message insertXCtemp(Applicationbill app) {
		Message msg = new Message();
		try {
			msg = entryService.insertXCtemp(app);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！"+e.getMessage());
		}
		/**
		 * 记录返回信息
		 */
		Entrylog emsg = new Entrylog();
		emsg.setIcid(app.getIcid());
		emsg.setCarno(app.getCarno());
		emsg.setCardmemo(msg.getMsg());//返回内容
		emsg.setTypes(((Boolean)msg.isSuccess()).toString());//返回标记
		entryMapper.insertEntrylogmsg(emsg);
		emsg=null;
		return msg;
	}

	/**
	 * 查询道闸控制信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/entry/sleep.do")
	public Message sleep() {
		Message msg = new Message();
		System.out.print("方法开始！！！！");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("方法结束！！！！");
		msg.setMsg("休息2秒钟~~~~");
		return msg;
	}

	/**
	 * 通过车号查询进出厂信息
	 * 
	 * @param storeout
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/entry/queryEXList.do")
	public Message queryEXList(String carno, ModelMap model) {
		Message msg = new Message();
		try {
			msg.setRows(entryMapper.queryEXList(carno));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
		// return (Message)storeoutService.queryInfoBycarno(storein);
	}

	/**
	 * 通过车号查询是否进厂
	 * 
	 * @param storeout
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/entry/queryOrIn.do")
	public Message queryInfoBycarno(Entrylog elog, ModelMap model) {
		Message msg = new Message();
		try {
			Entrylog e = entryMapper.queryId(elog);
			if (e == null) {
				msg.setTotal(0);
				msg.setMsg("车辆未有进厂信息不允许出厂");
			} else {
				msg.setTotal(1);
				msg.setMsg("车辆上次进厂未出厂");
			}
			e = null;
			elog = null;
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 查询大门信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/bcommon/queryInOutGatename.do")
	public List<ComboxData> queryInOutGatename(ComboxData o) {
		return bcommonMapper.queryInOutGatename(o);
	}
	
	/**
	 * 作废小车进出厂记录
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/entrylog/delete")
	public Message cancelEntrylog(Entrylog elog, ModelMap model) {
		Message msg = new Message();
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			elog.setValidflagoperaname(user.getDisplayname());
			entryMapper.cancelEntrylog(elog);
			entryMapper.cancelEntrylog2(elog);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 门岗查询进出厂记录
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unauth/entry/gatequreylist.do")
	public Message gatequreylist(Entrylog elog) {
			Message msg = new Message();
			try {
				msg.setRows(entryMapper.gatequreylist(elog));
			} catch (Exception e) {
				e.printStackTrace();
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
			return msg;
	}
}
