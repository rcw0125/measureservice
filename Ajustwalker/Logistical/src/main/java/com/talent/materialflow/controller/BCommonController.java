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

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.model.QueryParams;
import com.talent.core.privilege.model.User;
import com.talent.core.util.HttpUtils;
import com.talent.materialflow.model.Applicationbill;
import com.talent.materialflow.model.BCommon;
import com.talent.materialflow.model.Blacklist;
import com.talent.materialflow.model.Cardhead;
import com.talent.materialflow.model.ComboxData;
import com.talent.materialflow.model.Customer;
import com.talent.materialflow.model.Documenttype;
import com.talent.materialflow.model.Entrylog;
import com.talent.materialflow.model.Forcestop;
import com.talent.materialflow.model.LogisticalRuleDetail;
import com.talent.materialflow.model.Material;
import com.talent.materialflow.model.Measure;
import com.talent.materialflow.model.Operaconfig;
import com.talent.materialflow.model.Quality;
import com.talent.materialflow.model.Storein;
import com.talent.materialflow.model.Taskcode;
import com.talent.materialflow.model.Workline;
import com.talent.materialflow.model.WorklineItem;
import com.talent.materialflow.model.Workpoint;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.WorklineItemService;
import com.talent.materialflow.service.WorklineService;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.WorkpointMapper;

@Controller
@SuppressWarnings("unused")
public class BCommonController extends BaseController {

	@Resource
	private BCommonService bcommonService;

	@Resource
	private BCommonMapper bcommonMapper;

	@Autowired
	private WorklineItemService worklineItemService;

	@Autowired
	private WorklineService worklineService;

	@Resource
	private WorkpointMapper workpointMapper;
	@Autowired
	private HttpUtils httpUtils;

	/**
	 * 根据卡号判断卡、车辆状态
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/judgCarno.do")
	public Message judgCarno(Blacklist q) {
		Message msg = new Message();
		try {
			msg = bcommonService.judgCarno(q);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 通过车号读取rfid
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryRfidBycarno.do")
	public Message judgCarno(String carno) {
		Message msg = new Message();
		try {
			String carnos = bcommonMapper.queryRfidBycarno(carno);
			msg.setMfunc(carnos);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 判断rfid卡状态
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/judgRFID.do")
	public Message judgRFID(Blacklist q) {
		Message msg = new Message();
		try {
			msg = bcommonService.judgRFID(q);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 根据车号判断车号是否发卡是否在黑名单
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/judgOrFromcarno.do")
	public Message judgOrFromcarno(Blacklist q) {
		Message msg = new Message();
		try {
			msg = bcommonService.judgOrFromcarno(q);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 根据车号判断车号卡是否在黑名单
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/judgOrBlackCarno.do")
	public Message judgOrBlackCarno(Blacklist q) {
		Message msg = new Message();
		try {
			msg = bcommonService.judgOrBlackCarno(q);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 添加强制终止信息
	 * 
	 * @param carno
	 * @param sysmemo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/forcestop.do")
	public Message forcestop(Forcestop ft, String sysmemo) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ft.setCreator(user.getDisplayname());
			ft.setUsermemo(sysmemo);
			msg = bcommonService.forcestop(ft);
			String message = httpUtils.get("http://192.168.2.42:7080/MeasureService/qualityInterface/updateSatus.do",
					"matchid=" + ft.getMatchid());
			System.out.print("message......"+message);

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 添加强制终止信息
	 * 
	 * @param carno
	 * @param sysmemo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/rebackforcestop.do")
	public Message rebackforcestop(Forcestop ft, String sysmemo) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ft.setCreator(user.getDisplayname());
			ft.setUsermemo(sysmemo+" 终止业务还原");
			msg = bcommonService.rebackforcestop(ft);

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/*
	 * 业务类型查询
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryOperatype.do")
	public List<Operaconfig> queryOperatype(Operaconfig oper, ModelMap model) {
		String ops = "";
		if (oper.getOperatype() != null) {
			String[] operlist = oper.getOperatype().split(",");
			if (operlist.length == 1) {
				ops = "'" + operlist[0] + "'";
			} else {
				for (int i = 0; i < operlist.length; i++) {
					if (i == 0) {
						ops = "'" + operlist[i] + "',";
					} else if (i == operlist.length - 1) {
						ops = ops + "'" + operlist[i] + "'";
					} else {
						ops = ops + "'" + operlist[i] + "',";
					}
				}
			}

		}
		oper.setOperatype(ops);
		return bcommonMapper.queryOperatype(oper);
	}

	/**
	 * 查询其他单据类型
	 * 
	 * @param dtype
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryDtype.do")
	public List<Operaconfig> queryDtype(Operaconfig oper, ModelMap model) {
		return bcommonMapper.queryDtype(oper);
	}

	/**
	 * 查询动态列表
	 * 
	 * @param oper
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryModelcontent.do")
	public Message queryModelcontent(Operaconfig oper) {
		return new Message(true, bcommonMapper.queryModelcontent(oper));
	}

	@RequestMapping("/bcommon/showdetail.do")
	public String showdetail(String matchid, ModelMap modelMap) {

		List<Applicationbill> makecardlist = bcommonMapper.queryMakecardDetail(matchid);
		if (makecardlist != null && makecardlist.size() > 0) {
			Applicationbill app = (Applicationbill) makecardlist.get(0);
			List<WorklineItem> wliList = null;
			List<WorklineItem> finalWliList = null;
			Workline wl = null;
			try {
				WorklineItem wli = new WorklineItem();
				wli.setFid(Long.parseLong(app.getRouteid()));
				QueryParams<WorklineItem> worklineItemQuery = new QueryParams<WorklineItem>(wli);
				worklineItemQuery.eq("fid", false);
				wliList = worklineItemService.query(worklineItemQuery);
				finalWliList = new ArrayList<WorklineItem>(wliList.size());
				Workpoint workpoint = new Workpoint();
				workpoint.setValidflag("3");
				List<Workpoint> workpointList = workpointMapper.queryList(workpoint);

				for (WorklineItem temp : wliList) {
					String points = "," + temp.getWorkpointcode() + ",";
					for (Workpoint wp : workpointList) {
						points = points.replace("," + wp.getWorkpointcode() + ",", "," + wp.getWorkpointname() + ",");
					}
					points = points.substring(1).substring(0, points.length() - 2);
					temp.setWorkpointname(points);
					finalWliList.add(temp);
				}
				wl = worklineService.findOne(Long.parseLong(app.getRouteid()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Quality> qualitylist = bcommonMapper.queryQualityDetail(matchid);
			List<Measure> measurelist = bcommonMapper.queryMeasureDetail(matchid);
			List<Entrylog> entrylist = bcommonMapper.queryEntryDetail(matchid);
			List<Storein> storeinlist = bcommonMapper.queryStoreinDetail(matchid);
			List<Storein> storeoutlist = bcommonMapper.queryStoreoutDetail(matchid);

			modelMap.addAttribute("entrylist", entrylist);
			modelMap.addAttribute("qualitylist", qualitylist);
			modelMap.addAttribute("makecardlist", makecardlist);
			modelMap.addAttribute("measurelist", measurelist);
			modelMap.addAttribute("storeinlist", storeinlist);
			modelMap.addAttribute("storeoutlist", storeoutlist);
			modelMap.addAttribute("nodelist", finalWliList);
			modelMap.addAttribute("operatype", app.getOperatype());
			
			String wlname = null != wl ? wl.getWorklinename().trim() : "无作业路线";
			modelMap.addAttribute("worklinename", wlname.endsWith("路线") ? wlname : wlname + "路线");
		}
		return "showdetail";
	}
	@RequestMapping("/bcommon/showOtherDetail.do")
	public String showOtherDetail(String ids,String type,String entrytype, ModelMap modelMap) {
		String matchid="";
        if("1".equals(type)){//通过接口saleitemid查询物流号
        	matchid =bcommonMapper.querymatchidbyitemid(ids); 
        }else{//通过进出厂id获取物流号
        	if("1".equals(entrytype)){//进厂
        		matchid=bcommonMapper.querymatchidbyid(ids);
        	}else{//出厂
        		matchid=bcommonMapper.querymatchidbyeid(ids);
        	}
        }
		List<Applicationbill> makecardlist = bcommonMapper.queryMakecardDetail(matchid);
		if (makecardlist != null && makecardlist.size() > 0) {
			Applicationbill app = (Applicationbill) makecardlist.get(0);
			List<WorklineItem> wliList = null;
			List<WorklineItem> finalWliList = null;
			Workline wl = null;
			try {
				WorklineItem wli = new WorklineItem();
				wli.setFid(Long.parseLong(app.getRouteid()));
				QueryParams<WorklineItem> worklineItemQuery = new QueryParams<WorklineItem>(wli);
				worklineItemQuery.eq("fid", false);
				wliList = worklineItemService.query(worklineItemQuery);
				finalWliList = new ArrayList<WorklineItem>(wliList.size());
				Workpoint workpoint = new Workpoint();
				workpoint.setValidflag("3");
				List<Workpoint> workpointList = workpointMapper.queryList(workpoint);

				for (WorklineItem temp : wliList) {
					String points = "," + temp.getWorkpointcode() + ",";
					for (Workpoint wp : workpointList) {
						points = points.replace("," + wp.getWorkpointcode() + ",", "," + wp.getWorkpointname() + ",");
					}
					points = points.substring(1).substring(0, points.length() - 2);
					temp.setWorkpointname(points);
					finalWliList.add(temp);
				}
				wl = worklineService.findOne(Long.parseLong(app.getRouteid()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Quality> qualitylist = bcommonMapper.queryQualityDetail(matchid);
			List<Measure> measurelist = bcommonMapper.queryMeasureDetail(matchid);
			List<Entrylog> entrylist = bcommonMapper.queryEntryDetail(matchid);
			List<Storein> storeinlist = bcommonMapper.queryStoreinDetail(matchid);
			List<Storein> storeoutlist = bcommonMapper.queryStoreoutDetail(matchid);

			modelMap.addAttribute("entrylist", entrylist);
			modelMap.addAttribute("qualitylist", qualitylist);
			modelMap.addAttribute("makecardlist", makecardlist);
			modelMap.addAttribute("measurelist", measurelist);
			modelMap.addAttribute("storeinlist", storeinlist);
			modelMap.addAttribute("storeoutlist", storeoutlist);
			modelMap.addAttribute("nodelist", finalWliList);
			String wlname = null != wl ? wl.getWorklinename().trim() : "无作业路线";
			modelMap.addAttribute("worklinename", wlname.endsWith("路线") ? wlname : wlname + "路线");
		}
		return "showdetail";
	}

	/**
	 * 查询供货单位信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/querySourceinfo.do")
	public Message querySourceinfo(ComboxData o, Pagination<Customer> page) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.querySourceinfo(o));
		return msg;
	}
	/**
	 * 查询火车物料
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryHcMateril.do")
	public Message queryHcMateril(ComboxData o, Pagination<Customer> page) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryHcMateril(o));
		return msg;
	}
	/**
	 * 查询火车客商信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryHcCustomer.do")
	public Message queryHcCustomer(ComboxData o, Pagination<Customer> page) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryHcCustomer(o));
		return msg;
	}
	 
	/**
	 * 查询站点信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryStation.do")
	public Message queryStation(ComboxData o, Pagination<Customer> page) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryStation(o));
		return msg;
	}
	
	
	/**
	 * 查询库房作业点
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/querySworkpoint.do")
	public List<ComboxData> querySworkpoint(ComboxData o) {
		return bcommonMapper.querySworkpoint(o);
	}
	/**
	 * 查询大门信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryGatename.do")
	public List<ComboxData> queryGatename(ComboxData o) {
		return bcommonMapper.queryGatename(o);
	}
	/**
	 * 查询大门信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryInOutGatename.do")
	public List<ComboxData> queryInOutGatename(ComboxData o) {
		return bcommonMapper.queryInOutGatename(o);
	}
	

	/**
	 * 查询收货单位信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryTargetinfo.do")
	public Message queryTargetinfo(ComboxData o, Pagination<Customer> page) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryTargetinfo(o));
		return msg;
	}

	/**
	 * 查询厂内单位信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryStoreinfo.do")
	public Message queryStoreinfo(ComboxData o, Pagination<Customer> page) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryStoreinfo(o));
		return msg;
	}

	/**
	 * 查询物料信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryMaterilinfo.do")
	public Message queryMaterilinfo(ComboxData o) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryMaterilinfo(o));
		return msg;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryUsername.do")
	public Message queryUsername(ComboxData o, Pagination<Customer> page) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryUsername(o));
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/bcommon/queryMaterilinfoSelect.do")
	public Message queryMaterilinfoSelect(ComboxData o, Pagination<Customer> page) {
		return buildGridData(bcommonMapper.queryMaterilinfo(o));
	}
	/**
	 * 查询物流计量系统物料
	 * @param o
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryBMaterilinfo.do")
	public Message queryMaterilinfo(ComboxData o, Pagination<Customer> page) {
		return buildGridData(bcommonMapper.queryBMaterilinfo(o));
	}

	@ResponseBody
	@RequestMapping(value = "/bcommon/queryMateril.do")
	public Message queryMateril(BCommon bcommon, Pagination<Material> page) {
		/*Message msg = new Message();
		msg.setRows(bcommonMapper.selectMateril(bcommon));*/
		return buildGridData(bcommonMapper.selectMateril(bcommon));
	}

	/**
	 * 查询车队信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryMotorcadeinfo.do")
	public List<ComboxData> queryMotorcadeinfo(ComboxData o) {
		return bcommonMapper.queryMotorcadeinfo(o);
	}

	/**
	 * 查询车队信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/selectMotorcadeinfo.do")
	public List<ComboxData> selectMotorcadeinfo(ComboxData o) {
		return bcommonMapper.queryMotorcadeinfo(o);
	}

	/**
	 * 查询读卡器类型
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryReaderType.do")
	public List<ComboxData> queryReaderType(ComboxData o) {
		return bcommonMapper.queryReaderType(o);
	}

	/**
	 * 查询作业路线
	 * 
	 * @param dtype
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryWorkline.do")
	public Message queryWorkline(ComboxData oper, ModelMap model) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryWorkline(oper));
		return msg;
	}

	/**
	 * 查询物流环节
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryLinkinfo.do")
	public List<ComboxData> queryLinkinfo(ComboxData o) {
		return bcommonMapper.queryLinkinfo(o);
	}

	/**
	 * 查询衡器信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryEqunameinfo.do")
	public List<ComboxData> queryEqunameinfo(ComboxData o) {
		return bcommonMapper.queryEqunameinfo(o);
	}

	/**
	 * 根据类型查询衡器
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryEqunameinfoByType.do")
	public List<ComboxData> queryEqunameinfoByType(ComboxData o) {
		return bcommonMapper.queryEqunameinfoByType(o);
	}
	
	/**
	 * 查询计划信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryPlaninfo.do")
	public Message queryPlaninfo(BCommon bcommon, Pagination<BCommon> page) {
		Message msg = new Message();
		msg = buildGridData(bcommonMapper.queryPlaninfo(bcommon));
		return msg;
	}

	/**
	 * 查询业务号信息
	 * 
	 * @param sourcename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryTaskcode.do")
	public Message queryTaskcode(Taskcode taskcode, Pagination<BCommon> page) {
		Message msg = new Message();
		msg = buildGridData(bcommonMapper.queryTaskcode(taskcode));
		return msg;
	}

	/**
	 * 查询作业点
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryWorkpoints.do")
	public List<ComboxData> workpointComboxData(ComboxData combox) {
		return bcommonMapper.queryWorkpoints(combox);
	}
	
	/**
	 * 查询厂内单位
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryStorenames.do")
	public List<ComboxData> storenameComboxData(ComboxData combox) {
		return bcommonMapper.queryStorenames(combox);
	}

	/**
	 * 查询计量单位
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryMeasureunit")
	public List<ComboxData> queryMeasureunit(ModelMap model) {
		return bcommonMapper.queryMeasureunit();
	}

	/**
	 * 查询计量单位
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryYesOrNo")
	public List<Map<String, String>> queryYesOrNo(ModelMap model) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> item = new HashMap<String, String>();
		item.put("id", "0");
		item.put("text", "否");
		result.add(item);
		item = new HashMap<String, String>();
		item.put("id", "1");
		item.put("text", "是");
		result.add(item);
		return result;
	}

	@RequestMapping("/bcommon/queryCardhead.do")
	public String queryCardhead(ModelMap modelMap) {
		List<Cardhead> cardlist = bcommonMapper.queryCardhead();
		int num = cardlist.size() % 8;
		for (int i = 0; i < num; i++) {
			cardlist.add(new Cardhead());
		}
		modelMap.addAttribute("cardlist", cardlist);
		return "/commonpage/CarNoKeyDialog";
	}

	/**
	 * 根据作业点编码查询读卡器型号
	 * 
	 * @param dtype
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryRTypeBycode.do")
	public Message queryRTypeBycode(Workpoint pt, ModelMap model) {
		Message msg = new Message();
		pt = bcommonMapper.queryRTypeBycode(pt);
		msg.setMore(pt);
		return msg;
	}

	/**
	 * 查询可以制卡车号
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryCarno.do")
	public Message queryCarno(BCommon bc) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryCarno(bc));
		return msg;
	}
	
	/**
	 * 查询入库车号
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryStoreinCarno.do")
	public Message queryStoreinCarno(BCommon bc) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryStoreinCarno(bc));
		return msg;
	}
	
	/**
	 * 查询出库车号
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryStoreoutCarno.do")
	public Message queryStoreoutCarno(BCommon bc) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryStoreoutCarno(bc));
		return msg;
	}
	/**
	 * 查询进出厂车号
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/queryInOutCarno.do")
	public Message queryInOutCarno(BCommon bc) {
		Message msg = new Message();
		msg.setRows(bcommonMapper.queryInOutCarno(bc));
		return msg;
	}
	
	/**
	 * 查询业务号信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bcommon/taskcodeinfo.do")
	public String exceptiondb(ModelMap model) {
		return "/commonpage/taskcodeinfo";
	}
	
	/**
	 * 根据车号、业务号查询皮重及业务信息
	 * 
	 * @param measure
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bcommon/getTareBYCarnoT.do")
	public Message getTareBYCarnoT(Measure measure) {
		Message msg = new Message();
		try {
			msg = bcommonService.getTareBYCarnoT(measure);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	

}
