package com.talent.materialflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.talent.core.util.HttpUtils;
import com.talent.materialflow.model.Applicationbill;
import com.talent.materialflow.model.Entrylog;
import com.talent.materialflow.model.Measure;
import com.talent.materialflow.model.Storein;
import com.talent.materialflow.service.ApplicationbillService;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.ExceptionService;
import com.talent.materialflow.service.mapper.ApplicationbillMapper;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.EntrylogMapper;
import com.talent.materialflow.service.mapper.ExceptionMapper;

@Controller
public class ExceptionController extends BaseController {

	@Resource
	private ApplicationbillMapper appMapper;
	@Resource
	private ApplicationbillService appService;
	@Resource
	private ExceptionMapper exceptionMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private ExceptionService exceptionService;
	@Resource
	private EntrylogMapper entryMapper;
	@Autowired
	private HttpUtils httpUtils;
	@Resource
	private BCommonMapper bcommonMapper;
	

	@RequestMapping(value = "/exception/exception")
	public String makecardcg(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "90,91,92,93,80,81,82,83");
		model.addAttribute("fkflag", "1");
		return "exception";
	}
	@RequestMapping(value = "/measureReport/measurereportdetail")
	public String card(ModelMap model) {
		return "report/measurereportdetail";
	}
	@ResponseBody
	@RequestMapping(value = "/exception/queryPage.do")
	public Message queryPage(Applicationbill app, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(exceptionMapper.queryList(app));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	@ResponseBody
	@RequestMapping(value = "/measureReport/queryReportDetail.do")
	public Message queryPage(Measure measure, ModelMap model, Pagination<Measure> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(exceptionMapper.queryMeasureDetail(measure));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 通过物流号查询入库信息
	 * 
	 * @param storein
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/querySinInfoBymatchid.do")
	public Message querySinInfoBymatchid(Storein storein, ModelMap model) {
		Message msg = new Message();
		try {
			msg.setRows(exceptionMapper.querySinInfoBymatchid(storein));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 通过物流号查询出库信息
	 * 
	 * @param storein
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/querySoutInfoBymatchid.do")
	public Message querySoutInfoBymatchid(Storein storein, ModelMap model) {
		Message msg = new Message();
		try {
			msg.setRows(exceptionMapper.querySoutInfoBymatchid(storein));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 通过物流号查询计量信息
	 * 
	 * @param storein
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/queryMeasureBymatchid.do")
	public Measure queryMeasureBymatchid(Measure measure, ModelMap model) {
		Message msg = new Message();
		try {
			int materialflow = measure.getMaterialflow();
			String matchid = measure.getMatchid();
			measure = exceptionMapper.queryMeasureBymatchid(measure);
			if(measure==null){
				measure = new Measure();
				measure.setMatchid(matchid);
			}
			measure.setMaterialflow(materialflow);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return measure;
	}

	/**
	 * 添加制卡信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/insertApplicationbill.do", method = RequestMethod.POST)
	public Message insertApplicationbill(Applicationbill app) {

		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());
			if ("-1".equals(app.getMatchid())) {
				app.setFkflag(0);
				msg = appService.insertApplicationbill(app);
			} else {
				msg = appService.updateApplicationbill(app);
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 添加计量信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/insertMeasureException.do")
	public Message insertMeasureException(Measure measure) {

		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			measure.setCreator(user.getDisplayname());
			msg = exceptionService.insertMeasureException(measure);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/exception/insertInEntrylog.do")
	public Message insertInEntrylog(Entrylog entry) throws DataAccessException {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			entry.setCreator(user.getDisplayname());
			entry.setEntrytype("1");
			entry.setUsermemo("异常进厂");
			entryMapper.insertEntrylog(entry);
			entryMapper.updatecurrtemp(entry);
			entryMapper.updateInId(entry);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}

	/**
	 * 添加出厂信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/insertExceptionOut.do")
	public Message insertExceptionOut(Entrylog entry) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			entry.setCreator(user.getDisplayname());
			entry.setUsermemo("异常出厂");
			entry.setEntrytype("2");
			msg = exceptionService.insertExceptionOut(entry);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
    /**
     * 厂内异常
     * @param model
     * @return
     */
	@RequestMapping(value = "/exception/exceptiondb")
	public String exceptiondb(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "10,11,12");
		return "exceptiondb";
	}
    /**
     * 火车异常
     * @param model
     * @return
     */
	@RequestMapping(value = "/exception/exceptionhc")
	public String exceptionhc(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "1#门");
		model.addAttribute("operatype", "10,11,12");
		return "exceptionhc";
	}
	@ResponseBody
	@RequestMapping(value = "/exception/queryExceptiondbList.do")
	public Message queryExceptiondbList(Measure measure , Pagination<Measure> page) {
		Message msg = new Message();
		try {
			 msg =buildGridData(exceptionMapper.queryExceptiondbList(measure));
			//msg.setRows(exceptionMapper.queryExceptiondbList(measure));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 通过物流号查询调拨计量信息
	 * 
	 * @param storein
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/queryExcedbBymatchid.do")
	public Measure queryExcedbBymatchid(Measure measure, ModelMap model) {
		Message msg = new Message();
		try {
			if ("-1".equals(measure.getMatchid())) {
				measure = new Measure();
				measure.setMatchid("-1");
			} else {
				measure = exceptionMapper.queryExcedbBymatchid(measure);
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return measure;
	}

	/**
	 * 厂内调拨操作
	 * 
	 * @param measure
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/insertExceptionDB.do")
	public Message insertExceptionDB(Measure measure) {

		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			measure.setCreator(user.getDisplayname());
			msg = exceptionService.insertExceptionDB(measure);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 虚拟数据信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/exception/virtualInfo")
	public String virtualInfo(ModelMap model) {
		model.addAttribute("operatype", "101,102,103,104,105,106,107,108,109,110,111");
		model.addAttribute("secondflag", "0");
		return "virtualInfo";
	}

	@ResponseBody
	@RequestMapping(value = "/exception/queryVirtualInfo.do")
	public Message queryVirtualInfo(Applicationbill app, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(exceptionMapper.queryVirtualInfo(app));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/exception/queryOneVirtualbymatchid.do")
	public Applicationbill queryOneVirtualbymatchid(Applicationbill app) {
		List<Applicationbill> list = exceptionMapper.queryVInfoBymatchid(app);
		return list.get(0);
	}

	@ResponseBody
	@RequestMapping(value = "/exception/queryVirtualbymatchid.do")
	public Message queryApplicationbill(Applicationbill app) {
		Message msg = new Message();
		msg.setRows(exceptionMapper.queryVInfoBymatchid(app));
		return msg;
	}

	/**
	 * 虚拟制卡信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/insertVApplicationbill.do", method = RequestMethod.POST)
	public Message insertVApplicationbill(Applicationbill app) {
		Message msg = new Message();
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());
			exceptionService.insertVApplicationbill(app);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 作废虚拟信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/cancelVApplicationbill.do")
	public Message cancelVApplicationbill(Applicationbill app) {
		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());
			msg = exceptionService.cancelVApplicationbill(app);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	/**
	 * 作废厂内信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/cancelExceptionDB.do")
	public Message cancelExceptionDB(Applicationbill app) {
		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());
			msg = exceptionService.cancelExceptionDB(app);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/exception/cancelAllinfo.do")
	public Message cancelAllinfo(Applicationbill app) {
		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());
			msg=exceptionService.cancelAllinfo(app);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@RequestMapping(value = "/exception/tareException")
	public String tareException(ModelMap model) {
		return "tareexception";
	}
	/**
	 * 作废皮重信息
	 * @param carno
	 * @param model
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/cancelTareException.do")
	public Message cancelTareException(String carno, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			int i = exceptionService.cancelTareException(carno);
			if(i>0){
				msg.setSuccess(true);
				msg.setMsg("皮重作废成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/exception/queryTareException.do")
	public Message queryTareException(Measure measure, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(exceptionMapper.queryTareException(measure));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/exception/queryTareBycarno.do")
	public Measure queryApplicationbill(Measure measure) {
		return (Measure)exceptionMapper.queryTareBycarno(measure);
	}
	
	/**
	 * 修改皮重信息
	 * @param carno
	 * @param model
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/updateTareException.do")
	public Message updateTareException(Measure measure) {
		Message msg = new Message();
		try {
			int i = exceptionService.updateTareException(measure);
			if(i>0){
				msg.setSuccess(true);
				msg.setMsg("皮重修改成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@RequestMapping(value = "/exception/exceptionxc")
	public String Exceptionxc(ModelMap model) {
		return "exceptionxc";
	}
	@ResponseBody
	@RequestMapping(value = "/exception/queryXCList.do")
	public Message queryXCList(Applicationbill app, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(exceptionMapper.queryXCList(app));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 作废线材信息
	 * @param carno
	 * @param model
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/cancelExceptionXC.do")
	public Message cancelExceptionXC(Applicationbill app) {
		Message msg = new Message();
		try {
			int i = exceptionService.cancelExceptionXC(app) ;
			if(i>0){
				msg.setSuccess(true);
				msg.setMsg("线材信息作废成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 修改调拨扣重信息
	 * 
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/updateDeducation.do")
	public Message updateDeducation(Measure measure) {
		Message msg = new Message();
		try {
			
			msg = exceptionService.updateDeducation(measure);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	@ResponseBody
	@RequestMapping(value = "/exception/queryExceptioninfo.do")
	public Message queryExceptioninfo(String matchid) {
		Message msg = new Message();
		msg.setRows(exceptionMapper.queryExceptioninfo(matchid));
		return msg;
	}
	
	/**
	 * 取样重量信息回传
	 * 
	 * @param matchid
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/exception/queryMeasureInfoReturnQA.do")
	public Message queryMeasureInfoReturnQA(String matchid) {
		Message msg = new Message();
		try {
			//System.out.println("message......." + matchid);
			String message = httpUtils.get("http://192.168.2.42:7080/MeasureService/measurees/queryMeasureInfoReturnQA.do","matchid="+matchid);
			//System.out.println("message......." + message);
			if(message.contains("信息不需要取样")){
				 msg.setSuccess(false);
				 msg.setMsg("信息不需要取样");
			}		   
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("取样回传操作失败！"+e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 取样信息回传
	 * 
	 * @param matchid
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/exception/uploadSampleInfo.do")
	public Message uploadSampleInfo(String matchid) {
		Message msg = new Message();
		try {
			String message = "";
			try {
				List<String> list = bcommonService.getMessage(matchid, "in");
				if (list != null) {
					if(list.size()==0){
						msg.setSuccess(false);
						msg.setMsg("信息不需要取样");
					}else{
						for (int i = 0; i < list.size(); i++) {
							message = httpUtils.get("http://192.168.2.42:7080/MeasureService/qualityInterface/ingate.do",list.get(i));
						}
					}
					
				}
				System.out.println("message......." + message);
			} catch (Exception e) {

			}	   
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("取样信息回传操作失败！"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 作废计量信息
	 * @param app
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/updateCurrMeasure.do")
	public Message updateCurrMeasure(Applicationbill app) {
		Message msg = new Message();

		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setCreator(user.getDisplayname());
			app.setCreateoperacode(user.getUsername());
			msg=exceptionService.updateCurrMeasure(app);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 查询火车信息
	 * @param measure
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exception/queryExceptionhcList.do")
	public Message queryExceptionhcList(Measure measure , Pagination<Measure> page) {
		Message msg = new Message();
		try {
			 msg =buildGridData(exceptionMapper.queryExceptionhcList(measure));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	  
	  /**
		 * 通过物流号查询调拨计量信息
		 * 
		 * @param storein
		 * @param model
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/exception/queryExcehcBymatchid.do")
		public Measure queryExcehcBymatchid(Measure measure, ModelMap model) {
			Message msg = new Message();
			try {
				if ("-1".equals(measure.getMatchid())) {
					measure = new Measure();
					measure.setMatchid("-1");
				} else {
					measure = exceptionMapper.queryExcehcBymatchid(measure);
					if("85".equals(measure.getOperatype())/*&& measure.getMaterialname().indexOf("线材")!=-1*/){
						String message = httpUtils.get(
								"http://192.168.2.42:7080/MeasureService/qualityInterface/queryWireValue.do",
								"cph=" + measure.getCarno() + "&begintime=" + measure.getTaretime() + "&endtime="
										+ measure.getGrosstime());
						Map<?, ?> map = JSON.parseObject(message, Map.class);
						if(map.get("data")!=null){
							String data = map.get("data").toString();
							measure.setPlanweight(Double.parseDouble(data));
						}
					}
					
				}
			} catch (Exception e) {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
			return measure;
		}
		
		/**
		 * 火车信息操作
		 * 
		 * @param measure
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/exception/beforInsertHc.do")
		public Message beforInsertHc(Measure measure) {

			Message msg = new Message();
			try {
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				measure.setCreator(user.getDisplayname());
				if("85".equals(measure.getOperatype())&& measure.getMaterialname().indexOf("线材")!=-1 && measure.getPlanweight()==0 && measure.getGross()>0 && measure.getTare()>0){
					String message = httpUtils.get(
							"http://192.168.2.42:7080/MeasureService/qualityInterface/queryWireValue.do",
							"cph=" + measure.getCarno() + "&begintime=" + measure.getTaretime() + "&endtime="
									+ measure.getGrosstime());
					Map<?, ?> map = JSON.parseObject(message, Map.class);
					if(map.get("data")!=null){
						String data = map.get("data").toString();
						measure.setPlanweight(Double.parseDouble(data));
					}
				}
				int f=0;
				double suttle=0;
				double dvalue=0;
				if(measure.getPlanweight()>0 && "85".equals(measure.getOperatype())&& measure.getGross()>0 && measure.getTare()>0){
					suttle = measure.getGross()*1000- measure.getTare()*1000;
					dvalue = measure.getPlanweight()*1000-suttle;
					if(dvalue>500){
						f++;
					}
				}
				if(f==0){
					int flag =exceptionService.insertHcException(measure);
					if(flag==0){
						msg.setSuccess(false);
						msg.setMsg("保存失败！");
					}
				}else{
					msg.setMfunc("2");
					msg.setSuccess(false);
					msg.setMsg("车厢重量与线材比对超过500kg;差值："+dvalue+"车厢重量/kg：" + suttle + " 对比重量/kg:"+ measure.getPlanweight());
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg.setSuccess(false);
				msg.setMsg("保存失败！");
			}
			return msg;
		}
		/**
		 * 火车信息操作
		 * 
		 * @param measure
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/exception/insertHcException.do")
		public Message insertHcException(Measure measure) {

			Message msg = new Message();
			try {
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				measure.setCreator(user.getDisplayname());
			    int flag = exceptionService.insertHcException(measure);
				if(flag==0){
					msg.setSuccess(false);
					msg.setMsg("保存失败！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg.setSuccess(false);
				msg.setMsg("保存失败！");
			}
			return msg;
		}
		/**
		 * 
		  * @Title: datadelete
		  * @Description:(数据转移信息)
		  * @param @param measure
		  * @param @return参数
		  * @return Message返回类型
		  * @throws
		 */
		@ResponseBody
		@RequestMapping(value = "/exception/datatransfer.do")
		public Message datatransfer(Measure measure) {
			Message msg = new Message();
			try {
				//User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("v_begintime", measure.getBegintime());
				parameterMap.put("v_endtime", measure.getEndtime());
				//parameterMap.put("v_createman", user.getDisplayname());
				parameterMap.put("v_message", "");
			    exceptionMapper.datatransfer(parameterMap);
			} catch (Exception e) {
				e.printStackTrace();
				msg.setSuccess(false);
				msg.setMsg("保存失败！");
			}
			return msg;
		}
		/**
		 * 
		  * @Title: datadelete
		  * @Description:(删除数据信息)
		  * @param @param measure
		  * @param @return参数
		  * @return Message返回类型
		  * @throws
		 */
		@ResponseBody
		@RequestMapping(value = "/exception/datadelete.do")
		public Message datadelete(Measure measure) {
			Message msg = new Message();
			try {
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("v_begintime", measure.getBegintime());
				parameterMap.put("v_endtime", measure.getEndtime());
				parameterMap.put("v_createman", user.getDisplayname());
				parameterMap.put("v_message", "");
			    exceptionMapper.datadelete(parameterMap);
			} catch (Exception e) {
				e.printStackTrace();
				msg.setSuccess(false);
				msg.setMsg("保存失败！");
			}
			return msg;
		}
}
