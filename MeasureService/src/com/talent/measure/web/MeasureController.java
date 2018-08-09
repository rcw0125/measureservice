package com.talent.measure.web;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.base.annotation.Rule;
import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.base.model.ReturnMessage;
import com.talent.base.util.BaseController;
import com.talent.base.util.CacheUtil;
import com.talent.measure.common.BusinessConfig;
import com.talent.measure.common.HardwareCtrl;
import com.talent.measure.common.MeasureRuleCalc;
import com.talent.measure.dao.BusinessConfigDao;
import com.talent.measure.dao.CommonDao;
import com.talent.measure.dao.MeasureDao;
import com.talent.measure.dao.QualityInterfaceDao;
import com.talent.measure.model.ConfigModel;
import com.talent.measure.model.Equipment;
import com.talent.measure.model.FlowInParams;
import com.talent.measure.model.Measure;
import com.talent.measure.model.MeasureTareHistory;
import com.talent.measure.model.QualityInterface;
import com.talent.measure.model.Statistics;
import com.talent.measure.model.TareLog;
import com.talent.measure.model.TaskCode;
import com.talent.measure.model.WeighterData;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeasureController extends BaseController {

	@Autowired
	private MeasureDao measureDao;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private MeasureRuleCalc measureRuleCalc;

	@Autowired
	private HardwareCtrl hardwareCtrl;

	@Autowired
	private PlatformDao platformDao;

	@Autowired
	private CacheUtil cacheUtil;

	@Autowired
	private BusinessConfigDao bcDao;

	@Autowired
	private BusinessConfig businessConfig;

	@Autowired
	private QualityInterfaceDao qualityInterfaceDao;

	// 打印接口 （当前计量类型 、matchid）

	// 获取终端和坐席的关系

	// 保存终端和秤点的关系

	// 查询库房、供应商
	@ResponseBody
	@RequestMapping(value = "/measure/getfieldnames.do")
	public List<Measure> getfieldnames(Measure m) {
		List<Measure> fs = new ArrayList<Measure>();
		if (m != null && m.getMrecord() != null) {
			fs = (List<Measure>) measureDao.getfieldname(m);
		}
		return fs;
	}

	// 查询物料
	@ResponseBody
	@RequestMapping(value = "/measure/getmaterialname.do")
	public Message getmaterialname(Measure m, PageModel pm, String search, int limit) {
		Message msg = new Message();
		try {
			m.setMaterialname(search);
			pm = measureDao.getmaterialname(m, pm);
			msg.setTotal(pm.getAllcount());
			msg.setRows(pm.getList());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}

	// 查询业务号
	@ResponseBody
	@RequestMapping(value = "/measure/getoperatename.do")
	public List<Map<String, String>> getOperateType() {

		List<Map<String, String>> list = measureDao.getOperateType();
		return list;
	}

	// 查询物料大类
	@ResponseBody
	@RequestMapping(value = "/measure/getmaterialclass.do")
	public List<Map<String, String>> getMaterialClasses(String search) {

		List<Map<String, String>> list = measureDao.getMaterialClasses(search);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/measure/queryHistorytare.do")
	public Message queryHistorytare(Measure measure) {
		Message msg = new Message();
		System.out.println("输出信息......." + measure.getTare());
		try {
			List<TareLog> list = bcDao.queryHistorytare(measure);
			msg.setTotal(list.size());
			msg.setRows(list);
		} catch (Exception e) {
			msg.setMsg("操作失败");
			msg.setTotal(-1);
			msg.setSuccess(false);

		}
		return msg;
	}

	// 获取计量信息 毛重记录、皮重记录、净重记录
	@ResponseBody
	@RequestMapping(value = "/measure/getMeasurerecord.do")
	public Object getMeasurerecord(Measure m, PageModel pagemodel) {

		pagemodel = measureDao.getMeasurerecords(m, pagemodel);

		return pagemodel;
	}

	/**
	 * 根据车号获取 业务信息：1、无业务信息、2、有多条业务信息、3、有一条业务信息
	 * 
	 * @param m
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/measure/getMeasureInfobyCarno.do")
	public Object getMeasureInfo(Measure m, FlowInParams params) {
		// System.out.println("获取信息方法开始。。。。。。。。");
		String[] matchids = null;
		Message msg = new Message();
		Message checkmsg = new Message();
		String printstr = "";
		List<Measure> ms = new ArrayList<Measure>();
		try {
			if ("1".equals(m.getCarflag())) { // carflag为1的时候表示是通过车号查询业务信息
				if (m != null) {
					if ((!"".equals(m.getCarno()) && m.getCarno() != null)) {// 车号不为空的时候
						ms = measureDao.getlplanv(m); // .getByCarno(m.getCarno());
						if (ms != null && ms.size() > 0) {
							if (ms.size() == 1) {// 业务信息是一条,调用业务判断函数
								msg.setRows(ms);
								Measure measure = ms.get(0);
								measure.setCaller(m.getCaller());
								measure.setWeight(m.getWeight());
								measure.setWeightno(m.getWeightno());
								measure.setRfid(m.getRfid());
								matchids = new String[] { measure.getMatchid() };
								m = getmeasureflag(measure);// 判断当前是计毛还是计量皮重
								m.setRgross(measure.getGross()); // 用于重复毛重的判断
								m.setRtare(measure.getTare()); // 用于重复皮重的判断
								m.setRtaretime(measure.getTaretime());
								m.setRgrosstime(measure.getGrosstime());
								try {
									params.setOptr(m.getOpstr());
									printstr = m.getPrintstr();
									params.setMatchid(m.getMatchid());
									checkmsg = measureRuleCalc.flagsCheck(m, Rule.GETING_INFO);
									msg.setMfunc(checkmsg.getMfunc());
									msg.setFlags(checkmsg.getFlags());
								} catch (Exception e) {
									msg.setSuccess(false);
									msg.setMsg("业务判断操作失败！");
								}

							} else {
								// matchids = new String[ms.size()];
								List<Measure> list = new ArrayList<Measure>(); // 存储判断过的计量信息对象
								List<Measure> li = new ArrayList<Measure>();// 存储查询对象，对象中业务类型信息已经经过处理
								for (int i = 0; i < ms.size(); i++) {
									Measure sure = ms.get(i);
									sure = getmeasureflag(sure);
									if (sure.getGross() > 0 && sure.getTare() == 0) {
										if (sure.getTargettime() != null && (!"".equals(sure.getTargettime()))) {//
											list.add(sure);
										}
									} else if (sure.getGross() == 0 && sure.getTare() > 0) {
										if (sure.getSourcetime() != null && (!"".equals(sure.getSourcetime()))) {//
											list.add(sure);
										}
									} else if (sure.getGross() == 0 && sure.getTare() == 0) {
										list.add(sure);
									}
									li.add(sure);
								}
								if (list.size() == 0) {
									msg.setRows(li);
									matchids = new String[] { li.get(0).getMatchid() };
								} else {
									msg.setRows(list);
									matchids = new String[] { list.get(0).getMatchid() };
								}

							}
						} else{//根据车号未查询到可以过磅的信息
							List<Measure> infolist =measureDao.getCompleteMeasure(m);//根据车号查询本次不允许过磅的车辆
						    if(null !=infolist && infolist.size()>0){
						    	
						    	List<ReturnMessage> r = new ArrayList<ReturnMessage>();
						    	ReturnMessage rm = new ReturnMessage();
						    	rm.setFlag(3);
					    		rm.setSuccess(false);
					    		
						    	Measure measure = infolist.get(0);
						    	if("91".equals(measure.getOperatype())||"93".equals(measure.getOperatype())
						    	  ||"81".equals(measure.getOperatype())||"83".equals(measure.getOperatype())){ //计件提示
						    		rm.setMsg("计件车辆无需过磅");
						    		r.add(rm);
						    		msg.setFlags(r);
						    		msg.setMfunc("3");
						    	}else{
						    		if(measure.getMstate()==8){//已过磅提示
						    			rm.setMsg("车辆已经过磅，过磅时间："+measure.getSuttletime());
						    			r.add(rm);
							    		msg.setFlags(r);
							    		msg.setMfunc("3");
						    		}
						    	}
						    }
						}
					} else {
						msg.setSuccess(false);
						msg.setMsg("无法获取车号信息，操作失败！");
					}
				}
			} else if ("2".equals(m.getCarflag())) {// 通过计划号或者业务号查询业务信息
				if (m.getPlanid() != null && !m.getPlanid().equals("")) {// 如果计划号不为空
					// 计划号
					// ms = measureDao.queryTaskcode(m);
					msg.setRows(ms);
				} else {
					msg.setSuccess(false);
					msg.setMsg("计划号或业务号为空，读取信息失败！");
				}
			} else {
				msg.setRows(null);
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("getMeasureInfobyCarno.do 操作失败！"+e.getMessage());
		}

		if (matchids != null && 1 == matchids.length) {
			// 获取计量方式，远程、自动
			Measure mes = ((List<Measure>) msg.getRows()).get(0);
			if (mes != null) {
				String measuretyps = "";
				if ("10".equals(mes.getOperatype())) {
					if (1 == mes.getMeasuretype()) {
						measuretyps = "远程自动";
					} else if (2 == mes.getMeasuretype()) {
						measuretyps = "自助计量";
					} else if (3 == mes.getMeasuretype()) {
						measuretyps = "现场自动";
					} else {
						measuretyps = "远程手动";
					}
					msg.setMtype(measuretyps);
				} else {
					msg.setMtype(hardwareCtrl.getMeasureStyle((mes)));
				}
			}

			// 获取硬件控制信息，车停位检测、红外对射
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.add(hardwareCtrl.getCarLoactionCtrlStyle(mes));
			params.setOptr(printstr);
			list.add(hardwareCtrl.getPrinterStyle(params));
			msg.setHardwarectrl(list);

			// 获取页面上显示的字段
			ConfigModel configmodel = new ConfigModel();
			configmodel.setOperatype(mes.getOperatype());
			configmodel.setPoints(params.getCaller());
			msg.setMores(platformDao.queryList(configmodel));
		}
		// System.out.println("获取信息方法结束。。。。。。。。");
		return msg;
	}

	/**
	 * 与重量相关的判断
	 * 
	 * @param m
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/judgeWeight.do")
	public Object judgeWeight(Measure m, FlowInParams params) {
		Message msg = new Message();
		try {

			m = (Measure) measureDao.getByMatchid(m.getMatchid());
			m = getmeasureflag(m);// 判断当前是计毛还是计量皮重

			String opstr = "";
			if (m.getMeasureflag() == 0) {
				opstr = "计毛重";
				m.setMeasurestate("G");
			} else {
				opstr = "计皮重";
				m.setMeasurestate("T");
			}
			try {
				params.setOptr(m.getOpstr() + opstr);
				params.setMatchid(m.getMatchid());
				msg = measureRuleCalc.flagsCheck(m, Rule.GETING_INFO);

			} catch (Exception e) {
				msg.setSuccess(false);
				msg.setMsg("业务判断操作失败！");
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 通过计量流程、已有计量信息，判断当前是计毛、计皮
	 * 
	 * @param measure
	 * @return
	 */
	private Measure getmeasureflag(Measure measure) {
		String measureOrder = "";
		// measure.setFlag(0);//
		String printstr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 判断是计毛\还是计量皮重
		if (measure.getMflag() == 1) { // 先毛后皮
			measureOrder = "先毛后皮";
			if ("10".equals(measure.getOperatype()) || "11".equals(measure.getOperatype())) {
				if (StringUtils.isEmpty(measure.getGrosstime())) {
					if (measure.getTarehour() > 0) {
						Measure m1 = measureDao.getTarebyCarno(measure);
						if (m1 != null) {
							measure.setMtypes("回毛");
						} else {
							measure.setMtypes("计毛");
						}
					} else {
						/*
						 * String matchid =
						 * measureDao.queryzcount(measure.getCarno());//
						 * 根据车号查询是否单独计皮 if (null == matchid ||
						 * "".equals(matchid)) {
						 */
						measure.setMtypes("计毛");
						/*
						 * } else { measure.setMtypes("回毛"); }
						 */

					}
				} else {
					measure.setMtypes("回皮");
				}

			}

		} else if (measure.getMflag() == 2) {// 先皮后毛
			measureOrder = "先皮后毛";
			if ("10".equals(measure.getOperatype()) || "11".equals(measure.getOperatype())) {
				if (StringUtils.isEmpty(measure.getTaretime())) {
					if (measure.getTarehour() > 0) {
						Measure m1 = measureDao.getTarebyCarno(measure);
						if (m1 != null) {
							measure.setMtypes("回毛");
						} else {
							measure.setMtypes("计皮");
						}
					} else {
						String matchid = measureDao.queryzcount(measure.getCarno());// 根据车号查询是否单独计皮
						if (StringUtils.isEmpty(matchid)) {
							measure.setMtypes("计皮");
						} else {
							measure.setMtypes("回毛");
						}
					}
				} else {
					measure.setMtypes("回毛");
				}
			}

		} else if (measure.getMflag() == 3) {// 3皮毛毛皮
			measureOrder = "皮毛毛皮";
			if ("11".equals(measure.getOperatype())) {
				measure.setMtypes("计皮");
			}

		} else if (measure.getMflag() == 4) {// 4 毛毛皮
			measureOrder = "毛毛皮";
			if ("11".equals(measure.getOperatype())) {
				measure.setMtypes("计毛");
			}

		} else if (measure.getMflag() == 5) {// 5 毛毛 
			measureOrder = "毛毛";
			if ("11".equals(measure.getOperatype())) {
				measure.setMtypes("计毛");
			}
		} else {
			measureOrder = "不限制";
		}

		// 获取业务类型+计毛/计量皮重
		String opstr = measureDao.getOperaname(measure);
		opstr = opstr + measureOrder;

		if ("回毛".equals(measure.getMtypes())) {
			if (measure.getTarehour() > 0) {// 获取上次皮重时间和重量
				Measure m1 = measureDao.getTarebyCarno(measure);

				if (m1 != null) {

					Date time1 = commonDao.sysdate();
					Date time2 = null;
					try {
						time2 = sdf.parse(m1.getTaretime());
					} catch (Exception e) {
						time2 = time1;
					}
					long t = time1.getTime() - time2.getTime();// 当前时间与上次皮重相比
					measure.setTare(m1.getTare());
					measure.setTaretime(m1.getTaretime());
					measure.setTareweighid(m1.getTareweighid());
					measure.setTareweigh(m1.getTareweigh());
					measure.setTareoperacode(m1.getTareoperacode());
					measure.setTareoperaname(m1.getTareoperaname());
					measure.setTaregroupno(m1.getTaregroupno());
					measure.setTareserial(m1.getTareserial());
					measure.setTarespeed(m1.getTarespeed());
					measure.setTarelogid(m1.getTarelogid());

					if (t > measure.getTarehour() * 1000 * 60 * 60) {// 当前时间-皮重时间>皮重有效期时

						Date grosstime2 = null;
						try {
							String maxgrosstime = measureDao.queryMaxgrosstime(measure.getCarno());
							grosstime2 = sdf.parse(maxgrosstime);
						} catch (Exception e) {
							grosstime2 = time1;
						}
						long dvalue = grosstime2.getTime() - time2.getTime();// 最新的毛重与上次皮重做对比
						if (dvalue > measure.getTarehour() * 1000 * 60 * 60) {// 皮重超期已经使用一次，禁止计量
							measure.setFlag(3);// 坐席选择
							// 提示皮重超期，禁止计量。
							measure.setMsg("皮重超期");
							measure.setMeasureflag(1);   
							printstr = "只计毛";
						} else {
							// 返回1计皮
							measure.setFlag(2);// 坐席选择
							// 提示皮重超期，选择计量。
							measure.setMsg("皮重已超期");//计量上通过这个五个字判断，需修改时需要和计量沟通同时修改
							measure.setMeasureflag(1);
							printstr = "只计毛";
						}

					} else {// 当前时间在皮重有效期内，获取皮重相关信息
						measure.setMeasureflag(0);// 当前时间-皮重时间<=皮重有效期,计量类型返回0计毛时
						printstr = "出净重";
					}

				} else {
					opstr = opstr + "计毛";
					printstr = "出净重";

				}
			} else {
				opstr = opstr + "计毛";
				printstr = "出净重";
			}
			measure.setMeasurestate("G");
		} else if ("回皮".equals(measure.getMtypes())) {
			opstr = opstr + "计皮";
			printstr = "出净重";
			measure.setMeasurestate("T");
		} else if ("计皮".equals(measure.getMtypes())) {
			opstr = opstr + "计皮";
			printstr = "只计皮";
			measure.setMeasurestate("T");

		} else if ("计毛".equals(measure.getMtypes())) {
			opstr = opstr + "计毛";
			printstr = "只计毛";
			measure.setMeasurestate("G");
		}
		measure.setPrintstr(printstr);
		measure.setOpstr(opstr);
		return measure;
	}

	@ResponseBody
	@RequestMapping(value = "/measure/queryMeasureInfoReturnQA.do")
	public Object updatemeasure(QualityInterface qualityInterface, ModelMap model) {
		Message msg = new Message();
		try {
			QualityInterface q = qualityInterfaceDao.queryMeasureInfoReturnQA("9016081800001");
			if (q.getCounts() > 0) {
				qualityInterfaceDao.updateMeasureData(q);// 插入到检化验系统计量信息
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}

	/**
	 * 计量信息保存接口
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws DataAccessException
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/saveMeasureInfo.do", method = RequestMethod.POST)
	public Message saveMeasureInfo(@RequestBody Measure measure) throws DataAccessException {
		Message msg = new Message();
		//List<Measure> mlist = new ArrayList<Measure>();
		List<Measure> ml = new ArrayList<Measure>();
		try {
			Measure meas = new Measure();
			if ("0".equals(measure.getOperatype())) {
				String matchid = measureDao.queryzcount(measure.getCarno());// 根据车号查询改车号是否有单独计皮的信息
				if (null == matchid || "".equals(matchid)) {
					matchid = commonDao.newMatchid("10");
				}
				measure.setMatchid(matchid);
				msg.setMore(matchid);//单独计量皮重时把物流号放在more里面
			} else {
				ml = measureDao.getBeforesave(measure);
			}

			if (ml.size() > 0) {// 如果可以根据matchid查询出信息，部分数据一数据库为准
				meas = ml.get(0);
				meas.setBasket(measure.getBasket());
				meas.setBatchcode(measure.getBatchcode());
				meas.setBflag(measure.getBflag());
				meas.setCarno(measure.getCarno());
				meas.setClientid(measure.getClientid());
				if(measure.getDeduction() > 0)
				{
					meas.setDeduction(measure.getDeduction());
				}
				if(measure.getDeduction2() > 0)
				{
					meas.setDeduction2(measure.getDeduction2());
				}
				meas.setDeductioncode(measure.getDeductioncode());
				meas.setDeductionname(measure.getDeductionname());
				meas.setDeductionoperacode(measure.getDeductionoperacode());
				meas.setDeductionoperaname(measure.getDeductionoperaname());
				meas.setDeductiontime(measure.getDeductiontime());
				meas.setDflag(measure.getDflag());
				meas.setDvalue(measure.getDvalue());
				meas.setFlag(measure.getFlag());
				meas.setGflag(measure.getGflag());
				meas.setGross(measure.getGross());
				meas.setGrossb(measure.getGrossb());
				meas.setGrossgroupno(measure.getGrossgroupno());
				meas.setGrosslogid(measure.getGrosslogid());
				meas.setGrosslogidb(measure.getGrosslogidb());
				meas.setGrossoperacode(measure.getGrossoperacode());
				meas.setGrossoperacodeb(measure.getGrossoperacodeb());
				meas.setGrossoperaname(measure.getGrossoperaname());
				meas.setGrossoperanameb(measure.getGrossoperanameb());
				meas.setGrossserial(measure.getGrossserial());
				meas.setGrosstime(measure.getGrosstime());
				meas.setGrosstimeb(measure.getGrosstimeb());
				meas.setGrossweigh(measure.getGrossweigh());
				meas.setGrossweighb(measure.getGrossweighb());
				meas.setGrossweighgroup(measure.getGrossweighgroup());
				meas.setGrossweighid(measure.getGrossweighid());
				meas.setGrossweighidb(measure.getGrossweighidb());
				meas.setIcid(measure.getIcid());
				/* meas.setRfidid(measure.getRfidid()); */
				meas.setMatchid(measure.getMatchid());
				meas.setMatchidb(measure.getMatchidb());
				meas.setMaterialcode(measure.getMaterialcode());
				meas.setMaterialname(measure.getMaterialname());
				meas.setMaterialcount(measure.getMaterialcount());
				meas.setMaterialspec(measure.getMaterialspec());
				meas.setMaterialspeccode(measure.getMaterialspeccode());
				meas.setMeasurestate(measure.getMeasurestate());
				meas.setMflag(measure.getMflag());
				meas.setMtypes(measure.getMtypes());
				meas.setOperaname(measure.getOperaname());
				meas.setOperatype(measure.getOperatype());
				meas.setPlanid(measure.getPlanid());
				meas.setPlanweight(measure.getPlanweight());
				meas.setRecordtype(measure.getRecordtype());
				meas.setRfidid(measure.getRfidid());
				meas.setRfidtype(measure.getRfidtype());
				meas.setRuleflag(measure.getRuleflag());
				meas.setSflag(measure.getSflag());
				meas.setShip(measure.getShip());
				meas.setShipcode(measure.getShipcode());
				meas.setSnumber(measure.getSnumber());
				meas.setSourcecode(measure.getSourcecode());
				meas.setSourcename(measure.getSourcename());
				meas.setSourceplace(measure.getSourceplace());
				meas.setSuttle(measure.getSuttle());
				meas.setSuttleapp(measure.getSuttleapp());
				meas.setSuttleb(measure.getSuttleb());
				meas.setSuttleoperacode(measure.getSuttleoperacode());
				meas.setSuttleoperaname(measure.getSuttleoperaname());
				meas.setSuttletime(measure.getSuttletime());
				meas.setSuttleweigh(measure.getSuttleweigh());
				meas.setSuttletime(measure.getSuttletime());
				meas.setSuttleweigh(measure.getSuttleweigh());
				meas.setSuttleweighid(measure.getSuttleweighid());
				meas.setSysmemo(measure.getSysmemo());
				meas.setTare(measure.getTare());
				meas.setTareb(measure.getTareb());
				meas.setTaregroupno(measure.getTaregroupno());
				meas.setTarelogid(measure.getTarelogid());
				meas.setTarelogidb(measure.getTarelogidb());
				meas.setTareoperacode(measure.getTareoperacode());
				meas.setTareoperacodeb(measure.getTareoperacodeb());
				meas.setTareoperaname(measure.getTareoperaname());
				meas.setTareoperanameb(measure.getTareoperanameb());
				meas.setTareserial(measure.getTareserial());
				meas.setTarespeed(measure.getTarespeed());
				meas.setTaretime(measure.getTaretime());
				meas.setTaretimeb(measure.getTaretimeb());
				meas.setTareweigh(measure.getTareweigh());
				meas.setTareweighb(measure.getTareweighb());
				meas.setTareweighgroup(measure.getTareweighgroup());
				meas.setTareweighid(measure.getTareweighid());
				meas.setTareweighidb(measure.getTareweighidb());
				meas.setTargetcode(measure.getTargetcode());
				meas.setTargetname(measure.getTargetname());
				meas.setTargetplace(measure.getTargetplace());
				meas.setTaskcode(measure.getTaskcode());
				meas.setUsermemo(measure.getUsermemo());
			} else {// 如果数据库没有数据信息，数据以计量程序传递为主
				measure.setCartype("C");
				meas = measure;
			}

			measure = measureDao.saveMeasure(meas);
			if (meas.getGross() > 0 && meas.getTare() > 0) { // 出净重时，判断是否回传检化验系统
				if ("90".equals(meas.getOperatype())) {
					try {
						QualityInterface q = qualityInterfaceDao.queryMeasureInfoReturnQA(meas.getMatchid());
						qualityInterfaceDao.updateMeasureData(q);// 插入到检化验系统计量信息
					} catch (Exception e) {
						System.out.println("回传检化验计量信息失败!###############################");
						e.printStackTrace();
					}
				}
				/*
				 * try {//从NC下载的单据调用回传方法回传信息 if
				 * ("90".equals(meas.getOperatype()) ||
				 * "80".equals(meas.getOperatype())) { String message =
				 * WebUtils.get(
				 * "http://192.168.2.42:6080/Logistical/unauth/interface/upload",
				 * "matchid=" + meas.getMatchid());
				 * System.out.println("输出查询。。。。。" + message); }
				 * 
				 * } catch (Exception e) { e.printStackTrace();
				 * msg.setMsg("调用操作失败！"); }
				 */

				// WebUtils.get("http://localhost:8080/Logistical/unauth/interface/upload",
				// "matchid="+meas.getMatchid());

			}
			/**
			 * 一车多货情况： 根据车号、业务类型、matchid查询除本次选择计量的业务外的其他业务信息保存本次计量重量
			 */
		/*	try {
				Measure me = new Measure();
				me.setCarno(measure.getCarno());
				me.setMatchid(measure.getMatchid());
				me.setOperatype(measure.getOperatype());
				mlist = measureDao.getlplanv(me);
				if (null != mlist && mlist.size() > 0) {
					Measure m = new Measure();
					for (int i = 0; i < mlist.size(); i++) {
						m = mlist.get(i);
						if (measure.getGross() > 0 && measure.getTare() > 0) {// 如果本次计量出净重
							
							 * 一车多货，上一次出净重时，如果是计毛时出净重，把净重作为下一车皮重
							
							if ("G".equals(measure.getMeasurestate())) {
								m.setMeasurestate("T");
								m.setTare(measure.getGross());
								m.setTaretime(measure.getGrosstime());
								m.setTareoperacode(measure.getGrossoperacode());
								m.setTareoperaname(measure.getGrossoperaname());
								m.setTareweighid(measure.getGrossweighid());
								m.setTareweigh(measure.getGrossweigh());
								m.setTarelogid(measure.getGrosslogid());
							} else if ("T".equals(measure.getMeasurestate())) {
								m.setMeasurestate("G");
								m.setGross(measure.getTare());
								m.setGrosstime(measure.getTaretime());
								m.setGrossoperacode(measure.getTareoperacode());
								m.setGrossoperaname(measure.getTareoperaname());
								m.setGrossweighid(measure.getTareweighid());
								m.setGrossweigh(measure.getTareweigh());
								m.setGrosslogid(measure.getTarelogid());
							}
						} else { // 不出净重时
							if ("G".equals(measure.getMeasurestate())) {
								m.setMeasurestate("G");
								m.setGross(measure.getGross());
								m.setGrosstime(measure.getGrosstime());
								m.setGrossoperacode(measure.getGrossoperacode());
								m.setGrossoperaname(measure.getGrossoperaname());
								m.setGrossweighid(measure.getGrossweighid());
								m.setGrossweigh(measure.getGrossweigh());
								m.setGrosslogid(measure.getGrosslogid());
							} else if ("T".equals(measure.getMeasurestate())) {
								m.setMeasurestate("T");
								m.setTare(measure.getTare());
								m.setTaretime(measure.getTaretime());
								m.setTareoperacode(measure.getTareoperacode());
								m.setTareoperaname(measure.getTareoperaname());
								m.setTareweighid(measure.getTareweighid());
								m.setTareweigh(measure.getTareweigh());
								m.setTarelogid(measure.getTarelogid());
							}
						}

						measureDao.saveMeasure(m);// 针对一车多货情况添加业务信息
					}
				}
			} catch (Exception e) {
				System.out.println("一车多货保存数据失败！");
				e.printStackTrace();
			}*/
			if (measure.getFlag() == 0) {
				msg.setSuccess(false);
				msg.setMsg("保存数据失败");
			} else {
				msg.setSuccess(true);
				msg.setMsg("保存数据成功");
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("saveMeasureInfo.do 操作失败！"+e.getMessage());
			e.printStackTrace();
		}
		ml.clear();
		ml=null;
		return msg;
	}

	/**
	 * 获取输入计划号的信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws DataAccessException
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/inputPlanidInfo.do", method = RequestMethod.POST)
	public Message inputPlanidInfo(@RequestBody Measure measure, FlowInParams params) throws DataAccessException {
		Message msg = new Message();
		Message checkmsg = new Message();
		List<Measure> mlist = new ArrayList<Measure>();
		try {
			measure.setPlanid(measure.getTaskcode());
			Measure m = measureDao.queryTaskcode(measure);
			if (m != null) {
				// if("0".equals(measure.getCaller())){
				measure.setMaterialcode(m.getMaterialcode());
				measure.setMaterialname(m.getMaterialname());
				measure.setSourcecode(m.getSourcecode());
				measure.setSourcename(m.getSourcename());
				measure.setTargetcode(m.getTargetcode());
				measure.setTargetname(m.getTargetname());
				measure.setOperatype(m.getOperatype());
				measure.setCartype("C");
				// }
				measure.setMflag(m.getMflag());
				measure.setMqflag(m.getMqflag());
				measure.setAccountstype(m.getAccountstype());
				measure.setKqflag(m.getKqflag());
				measure.setGflag(m.getGflag());
				measure.setTarehour(m.getTarehour());
				measure.setPrintsetgross(m.getPrintsetgross());
				measure.setPrintsettare(m.getPrintsettare());
				measure.setPrintsetsuttle(m.getPrintsetsuttle());
				measure.setDeduction2(m.getDeduction2());
				measure.setDeductionunit(m.getDeduction2());
				measure.setDeductiontype(m.getDeductiontype());
				measure.setIsplan(m.getIsplan());
				measure.setIsbasket(m.getIsbasket());
				measure.setForcereceive(m.getForcereceive());
				measure.setMeasuretype(m.getMeasuretype());
				measure.setOperaname(m.getOperaname());
			}
			String matchid = measureDao.queryzcount(measure.getCarno());
			if (null == matchid || "".equals(matchid)) {
				matchid = commonDao.newMatchid(measure.getOperatype());
			}
			measure.setMatchid(matchid);
			mlist.add(measure);
			// 计量方式类型 0 远程手动 1远程自动 2 现场自助 3现场自动 4自动选择
			String measuretyps = "";
			if (1==measure.getMeasuretype()) {
				measuretyps = "远程自动";
			} else if (2==measure.getMeasuretype()) {
				measuretyps = "自助计量";
			} else if (3==measure.getMeasuretype()) {
				measuretyps = "现场自动";
			} else {
				measuretyps = "远程计量";
			}
			msg.setMtype(measuretyps);
			measure = getmeasureflag(measure);
			// 获取硬件控制信息，车停位检测、红外对射
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.add(hardwareCtrl.getCarLoactionCtrlStyle(measure));
			params.setMatchid(measure.getMatchid());
			params.setOptr(measure.getPrintstr());
			//list.add(hardwareCtrl.getConfigPrintStyle(params));
			list.add(hardwareCtrl.getPrinterStyle(params));
			msg.setHardwarectrl(list);
			
			// 获取页面上显示的字段
			ConfigModel configmodel = new ConfigModel();
			configmodel.setOperatype(measure.getOperatype());
			configmodel.setPoints(measure.getCaller()); // 测试坐席是否可以提供
			msg.setMores(platformDao.queryList(configmodel));
			if (measure.getFlag() != 0) {
				if (measure.getFlag() == 2 && "0".equals(measure.getCaller())) {
					msg.setMtype("远程计量");
				}
				checkmsg = measureRuleCalc.flagsCheck(measure, Rule.GETING_INFO);
				msg.setMfunc(String.valueOf(measure.getFlag()));
				List<ReturnMessage> li = new ArrayList<ReturnMessage>();
				ReturnMessage re = new ReturnMessage();
				re.setFlag(measure.getFlag());
				re.setMsg(measure.getMsg());
				li.add(re);   
				msg.setFlags(li);

			} else {
				checkmsg = measureRuleCalc.flagsCheck(measure, Rule.GETING_INFO);
				msg.setMfunc(checkmsg.getMfunc());
				msg.setFlags(checkmsg.getFlags());
			}
			msg.setRows(mlist);

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("inputPlanidInfo操作失败！"+e.getMessage());
		}
		return msg;
	}

	/**
	 * 多条数据选择信息后验证信息获取相应的验证显示信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws DataAccessException
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/moreMeasureInfo.do", method = RequestMethod.POST)
	public Message moreMeasureInfo(@RequestBody Measure measure, FlowInParams params) throws DataAccessException {
		Message msg = new Message();
		Message checkmsg = new Message();
		List<Measure> mlist = new ArrayList<Measure>();
		try {
			mlist.add(measure);
			msg.setMtype(hardwareCtrl.getMeasureStyle(measure));
			// 获取硬件控制信息，车停位检测、红外对射
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.add(hardwareCtrl.getCarLoactionCtrlStyle(measure));
			params.setMatchid(measure.getMatchid());
			measure = getmeasureflag(measure);
			params.setOptr(measure.getPrintstr());
			list.add(hardwareCtrl.getPrinterStyle(params));
			msg.setHardwarectrl(list);
			// 获取页面上显示的字段
			ConfigModel configmodel = new ConfigModel();
			configmodel.setOperatype(measure.getOperatype());
			configmodel.setPoints("1");
			msg.setMores(platformDao.queryList(configmodel));
			checkmsg = measureRuleCalc.flagsCheck(measure, Rule.GETING_INFO);
			msg.setMfunc(checkmsg.getMfunc());
			msg.setFlags(checkmsg.getFlags());
			msg.setRows(mlist);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 计量信息保存前验证
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws DataAccessException
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/beforesaveMeasureInfo.do", method = RequestMethod.POST)
	public Message beforesaveMeasureInfo(@RequestBody Measure measure) throws DataAccessException {
		Message msg = new Message();
		Measure m = new Measure();
		List<Measure> ms = new ArrayList<Measure>();
		try {
			ms = measureDao.getBeforesave(measure); // .getByCarno(m.getCarno());
			if (ms.size() > 0) {
				m = ms.get(0);
				m.setRgross(m.getGross());
				m.setRtare(m.getTare());
				m.setGross(measure.getGross());
				m.setGrosstime(measure.getGrosstime());
				m.setGrossweighid(measure.getGrossweighid());
				m.setTare(measure.getTare());
				m.setTaretime(measure.getTaretime());
				m.setTareweighid(measure.getTareweighid());
				m.setSuttle(measure.getSuttle());
				m.setSuttletime(measure.getSuttletime());
				m.setMeasurestate(measure.getMeasurestate());
				m.setMaterialcode(measure.getMaterialcode());
				m.setMaterialname(measure.getMaterialname());
				m.setTaskcode(measure.getTaskcode());
				m.setPlanid(measure.getPlanid());
				m.setSourcecode(measure.getSourcecode());
				m.setTargetcode(measure.getTargetcode());
				m.setSourcename(measure.getSourcename());
				m.setTargetname(measure.getTargetname());
				m.setShip(measure.getShip());
				m.setBasket(measure.getBasket());
			} else {
				m = measure;
			}
			if (1 == measure.getRuleflag()) { // 1表示验证获取时方法，2表示验证获取保存时方法
				msg = measureRuleCalc.flagsCheck(m, Rule.GETING_INFO);
			} else {
				msg = measureRuleCalc.flagsCheck(m, Rule.BEFORE_SAVE);
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("beforesaveMeasureInfo 操作失败！！！"+e.getMessage());
		}
		return msg;
	}

	// 根据车号时间 衡器 matchid 获取计量信息
	@ResponseBody
	@RequestMapping(value = "/measure/getSearchInfo.do")
	public List<Measure> getSearchInfo(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		Measure mth = GetSearchCdMeasure(jsonParams);
		if (mth.getBegintime().length() == 0)// 开始时间为空不查询
		{
			return null;
		}
		if (mth.getEndtime().length() == 0)// 结束时间为空不查询
		{
			return null;
		}
		// mth.getMatchid();//过磅单号
		// mth.getCarno();//车号
		// mth.getMrecord();//mrecord; //查询计量记录标记 G 计毛、S净重 T 计量皮重
		// mth.getTareweigh();// 衡器编码(“” 空代表全部 )
		List<Measure> fs = measureDao.getSearchInfo(mth);
		return fs;
	}

	// 根据车号获取哪几次的历史皮重
	@ResponseBody
	@RequestMapping(value = "/measure/getTareHistory.do")
	public List<MeasureTareHistory> getTareHistory(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		MeasureTareHistory mth = GetMeasureTareHistory(jsonParams);
		if (mth.getCarno().length() == 0)// 车号为空不查询
		{
			return null;
		}
		List<MeasureTareHistory> fs = measureDao.getTareHistory(mth);
		return fs;
	}

	public MeasureTareHistory GetMeasureTareHistory(String jsonParams) {
		ObjectMapper om = new ObjectMapper();
		MeasureTareHistory mtr = new MeasureTareHistory();
		try {
			mtr = om.readValue(jsonParams, MeasureTareHistory.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mtr;
	}

	public Measure GetSearchCdMeasure(String jsonParams) {
		ObjectMapper om = new ObjectMapper();
		Measure mtr = new Measure();
		try {
			mtr = om.readValue(jsonParams, Measure.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mtr;
	}

	// 获取计量页面快速补全的信息，快速提示下拉框
	@ResponseBody
	@RequestMapping(value = "/measure/quicksuggest.do")
	public List<HashMap<String, String>> quickSuggest(String fieldname, String inputvalue) {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, String> m = new HashMap<String, String>();
			m.put("item", fieldname + i);
			result.add(m);
		}
		return result;
	}

	// 获取业务号快速提示下拉框
	@ResponseBody
	@RequestMapping(value = "/measure/quicksuggest_taskcode.do")
	public List<TaskCode> quicksuggest_taskcode(String inputvalue) {
		TaskCode tc = new TaskCode();
		List<TaskCode> result = cacheUtil.getCache("taskcodesCache").lList(tc, new String[] { "taskcode" },
				new String[] { inputvalue });
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/equipment/weighterdata.do", method = RequestMethod.POST)
	public Object weighterdata(ModelMap model, @RequestBody WeighterData weighterData) {

		Message msg = new Message();
		try {
			platformDao.getNewID(weighterData);
			platformDao.insert(weighterData);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/equipment/weighterdata/query.do")
	public Object queryWeighterdata(ModelMap model, WeighterData weighterData) {

		Message msg = new Message();
		try {
			List<WeighterData> weighterDataList = platformDao.queryList(weighterData);
			msg.setRows(weighterDataList);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 获取页面显示信息
	 * 
	 * @param m
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/measure/queryshowinfo.do")
	public Object queryshowinfo(Measure m) {
		Message msg = new Message();
		ConfigModel configmodel = new ConfigModel();
		configmodel.setOperatype(m.getOperatype());
		configmodel.setPoints(m.getCaller());
		msg.setMores(platformDao.queryList(configmodel));

		return msg;
	}

	/**
	 * 根据物流号查询开始时间和结束时间
	 * 
	 * @param m
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/querytimeBymatchid.do")
	public Message querytimeBymatchid(WeighterData ex) {
		Message msg = new Message();
		List<WeighterData> list = bcDao.querytimeBymatchid(ex);
		msg.setRows(list);
		return msg;
	}

	/*
	 * 根据衡器编码查询量程
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/queryrange.do")
	public Message queryrange(Equipment e) {
		Message msg = new Message();
		Equipment ep = bcDao.queryrange(e);
		msg.setMore(ep.getRange());
		return msg;
	}

	/**
	 * 与重量相关的判断
	 * 
	 * @param m
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/void.do")
	public void test(Measure m) {
		businessConfig.queryrange(m);
	}

	/**
	 * 添加合金信息
	 * 
	 * @param jsonParams
	 * @return
	 * @throws DataAccessException
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/insertscaledetail.do", method = RequestMethod.POST)
	public Message insertscaledetail(@RequestBody String jsonParams) throws DataAccessException {
		Message msg = new Message();
		try {

			List<Measure> rtList = getObjectsFromJson(jsonParams, Measure.class);
			Measure measure = rtList.get(0);
			Measure mdetail = rtList.get(1);
			Measure f = measureDao.saveMeasure(measure);
			if (f.getFlag() == 0) {
				msg.setSuccess(false);
				msg.setMsg("保存数据失败");
			} else {
				int i = measureDao.insertscaledetail(mdetail);
				if (i > 0) {
					msg.setSuccess(true);
					msg.setMsg("保存成功");
				} else {
					msg.setSuccess(false);
					msg.setMsg("合金明细保存失败");
				}
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	public static <T> List<T> getObjectsFromJson(String in, Class<T> clsT) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("deprecation")
		JsonParser parser = objectMapper.getJsonFactory().createJsonParser(in);

		JsonNode nodes = parser.readValueAsTree();
		List<T> list = new ArrayList<T>(nodes.size());
		for (JsonNode node : nodes) {
			list.add(objectMapper.readValue(node.toString(), clsT));
		}
		return list;
	}

	/**
	 * 根据物流号查询合金计量信息
	 * 
	 * @param measure
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/queryscaledetail.do")
	public Object queryWeighterdata(Measure measure) {
		Message msg = new Message();
		try {
			msg.setRows(measureDao.queryscaledetail(measure));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 作废合金计量信息
	 * 
	 * @param measure
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/cancelWeighterdata.do")
	public Object cancelWeighterdata(Measure measure) {
		Message msg = new Message();
		try {
			int i = measureDao.cancelWeighterdata(measure);
			if (i > 0) {
				msg.setMsg("操作成功！");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 计量信息回传取样系统
	 * 
	 * @param measure 上传的物流号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measurees/queryMeasureInfoReturnQA.do")
	public Message queryMeasureInfoReturnQA(Measure measure) {
		Message msg = new Message();
		try {
			QualityInterface q = qualityInterfaceDao.queryMeasureInfoReturnQA(measure.getMatchid());
			if(q==null){
				msg.setSuccess(false);
				msg.setMsg("信息不需要取样");
			}else{
				if ("90".equals(q.getOperatype())) {
					try {
						qualityInterfaceDao.updateMeasureData(q);// 插入到检化验系统计量信息
					} catch (Exception e) {
						System.out.println("回传检化验计量信息失败!###############################");
						e.printStackTrace();
					}
				}else{
					msg.setSuccess(false);
					msg.setMsg("信息不需要取样");
				}
			}
			q=null;
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	/**
	 * 根据物流号和衡器id查询任务状态
	 * 
	 * @param measure
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/selectTaskStaus.do")
	public Message selectTaskStaus(Statistics task) {
		Message msg = new Message();
		try {
			msg.setData(commonDao.selectTaskStaus(task));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		task=null;
		return msg ;
	}
	/**
	 * 根据物流号和衡器id查询任务状态
	 * 
	 * @param measure
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/updateTaskStatus.do")
	public Message updateTaskStatus(Statistics task) {
		Message msg = new Message();
		try {
			int flag = commonDao.updateTaskStatus(task);
			if(flag==0){
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		task=null;
		return msg ;
	}
}
