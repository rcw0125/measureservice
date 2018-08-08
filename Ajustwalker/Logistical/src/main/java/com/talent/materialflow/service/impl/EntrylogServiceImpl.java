package com.talent.materialflow.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.core.model.Message;
import com.talent.core.mybatis.pagehelper.StringUtil;
import com.talent.materialflow.model.Applicationbill;
import com.talent.materialflow.model.Bcard;
import com.talent.materialflow.model.Blacklist;
import com.talent.materialflow.model.Entrylog;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.CardService;
import com.talent.materialflow.service.EntrylogService;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.CardMapper;
import com.talent.materialflow.service.mapper.EntrylogMapper;

@Service
@Transactional
public class EntrylogServiceImpl implements EntrylogService {
	@Resource
	private EntrylogMapper entryMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private CardMapper cardApp;
	@Resource
	private CardService cardService;
	@Resource
	private BCommonMapper bcommonMapper;
	@Resource
	private CardMapper cardMapper;
	/*
	 * 查询进厂车辆信息
	 */

	@Override
	public Message queryInList(Entrylog elog) throws DataAccessException {

		Blacklist black = new Blacklist();
		Bcard card = new Bcard();
		black.setCardid(elog.getIcid());
		black.setCarno(elog.getCarno());
		Message msg = new Message();
		card.setCardid(elog.getIcid());
		try {
			msg = bcommonService.judgOutCarno(black);
			if (msg.isSuccess()) {
				card = cardApp.queryinfoBycardid(card);
				if ("1".equals(card.getCartype())) {// 卡为员工车辆判断是否有进厂未出厂的车辆如果没有直接进厂
					msg.setData("员工车辆，车辆放行");
					msg.setMfunc("1");
					Entrylog entry = new Entrylog();
					entry.setCarno(card.getCarno());
					entry.setIcid(elog.getIcid());
					entry.setGatename("北门");// 暂时这么写，后续更改为传递参数
					entry.setEntrytype("1");
					entry.setCartype("1");
					entry.setDriver(card.getDriver());
					entry.setUnitname(card.getUnitname());
					entryMapper.insertEntrylog(entry);
				} else if ("0".equals(elog.getCartype())) {// 卡为业务车辆获取卡的业务信息
					List<Applicationbill> list = entryMapper.queryList(black.getCarno());
					String info = "";
					int reflag = 0; // 是否重复进厂
					for (int i = 0; i < list.size(); i++) {
						Applicationbill app = list.get(i);
						if (StringUtil.isEmpty(app.getEntertime())
								|| (StringUtil.isNotEmpty(app.getEntertime()) && "84".equals(app.getOperatype()))) {
							info = info + "物流号：" + app.getMatchid() + " 计划号：" + app.getPlanid() + " 物料名称："
									+ app.getMaterialname() + " 供货单位：" + app.getSourcename() + " 收货单位："
									+ app.getTargetname() + "用户备注：" + app.getUsermemo() + "\r\n\r\n";
						} else {
							reflag = 1;
						}

					}

					if (info.length() == 0) {
						if (reflag == 1) {
							msg.setSuccess(false);
							info = "上次进厂未出厂";
						} else {
							card.setCardid(black.getCardid());
							card = entryMapper.queryCardclass(card);
							if ("0".equals(card.getCardtype())) {
								// msg.setMsg("临时卡无业务不允许进厂");
								msg.setSuccess(false);
								info = "临时卡无业务不允许进厂";

							} else {
								Entrylog entry = new Entrylog();
								entry.setCarno(black.getCarno());
								entry.setIcid(elog.getIcid());
								entry.setGatename("北门");// 暂时这么写，后续更改为传递参数
								entry.setEntrytype("1");
								entry.setCartype("1");
								entryMapper.insertEntrylog(entry);
								info = "固定卡无业务信息";
								msg.setMfunc("3");
								entry = null;

							}
						}

					}
					msg.setData(info);
					msg.setMsg(info);
				} else if ("3".equals(card.getCartype())) {
					msg.setMfunc("3");
					Entrylog entry = new Entrylog();
					entry.setCarno(card.getCarno());
					entry.setIcid(elog.getIcid());
					entry.setGatename("北门");// 暂时这么写，后续更改为传递参数
					entry.setEntrytype("1");
					entry.setCartype("3");
					entryMapper.insertEntrylog(entry);
					entry = null;
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 根据卡号和车号判断卡状态

		return msg;
	}

	@Override
	public Message queryNewInList(Entrylog elog) throws DataAccessException {

		Blacklist black = new Blacklist();
		Bcard card = new Bcard();
		black.setCardid(elog.getIcid());
		black.setCarno(elog.getCarno());
		Message msg = new Message();
		card.setCardid(elog.getIcid());
		try {
			msg = bcommonService.judgOutCarno(black);
			if (msg.isSuccess()) {
				card = cardApp.queryinfoBycardid(card);
				if ("1".equals(card.getCartype())) {// 卡为员工车辆判断是否有进厂未出厂的车辆如果没有直接进厂
					msg.setData("员工车辆，车辆放行"); 
					elog.setCarno(card.getCarno());
					Entrylog e = entryMapper.queryId(elog);// 根据车号查询进厂未出厂的信息id
					if (e == null) {
						Entrylog entry = new Entrylog();
						entry.setCarno(card.getCarno());
						entry.setIcid(elog.getIcid());
						entry.setGatecode(elog.getGatecode());
						entry.setGatename(elog.getGatename());
						entry.setCreator(elog.getGatename());
						entry.setUsermemo("员工车辆进厂");
						entry.setEntrytype("1");
						entry.setCartype("1");
						entry.setDriver(card.getDriver());
						entry.setUnitname(card.getUnitname());
						entryMapper.insertEntrylog(entry);
						entry = null;
						msg.setMsg("员工车辆，车辆放行");
						msg.setMfunc("3");
						msg.setMore(card.getCarno());
					} else {
						msg.setSuccess(false);
						msg.setMsg("该车上次进厂未出厂，车辆已锁定！");
						black.setCreator(elog.getGatename());
						black.setRecordtype("2");
						black.setCardid(elog.getCarno());
						black.setUsermemo("员工车辆重复进厂");
						cardApp.insertBlack(black);// 添加车号到黑名单,查明原因还原车辆 
						msg.setMfunc("1");
						msg.setMore(card.getCarno());
					}
					
				} else if ("0".equals(elog.getCartype())) {// 卡为业务车辆获取卡的业务信息
					List<Applicationbill> list = entryMapper.queryList(black.getCarno());
					String info = "";
					int reflag = 0; // 是否重复进厂
					for (int i = 0; i < list.size(); i++) {
						Applicationbill app = list.get(i);
						if (StringUtil.isEmpty(app.getEntertime())) {
							info = info + "物流号：" + app.getMatchid() + " 计划号：" + app.getPlanid() + " 物料名称："
									+ app.getMaterialname() + " 供货单位：" + app.getSourcename() + " 收货单位："
									+ app.getTargetname() + "用户备注：" + app.getUsermemo() + "\r\n\r\n";
						} else {
							reflag = 1;
						}

					}

					if (info.length() == 0) {
						if (reflag == 1) {
							msg.setSuccess(false);
							info = "上次进厂未出厂";
						} else {
							card.setCardid(black.getCardid());
							card = entryMapper.queryCardclass(card);
							if ("0".equals(card.getCardtype())) {
								// msg.setMsg("临时卡无业务不允许进厂");
								msg.setSuccess(false);
								info = "临时卡无业务不允许进厂";

							} else {
								elog.setCarno(black.getCarno());
								Entrylog e = entryMapper.queryId(elog);// 根据车号查询进厂未出厂的信息id
								if (e == null) {
									Entrylog entry = new Entrylog();
									entry.setCarno(black.getCarno());
									entry.setIcid(elog.getIcid());
									entry.setGatecode(elog.getGatecode());
									entry.setGatename(elog.getGatename());
									entry.setCreator(elog.getGatename());
									entry.setEntrytype("1");
									entry.setCartype("0");
									entry.setUsermemo("固定卡进厂");
									entryMapper.insertEntrylog(entry);
									info = "固定卡无业务信息";
									msg.setMfunc("3");
									entry = null;
								} else {
									msg.setSuccess(false);
									info = "该车上次进厂未出厂，请检查";
								}
							}
						}

					}
					msg.setData(info);
					msg.setMsg(info);
				} else if ("3".equals(card.getCartype())) {//指令员卡进厂
					msg.setMfunc("3");
					Entrylog entry = new Entrylog();
					entry.setCarno(card.getCarno());
					entry.setIcid(elog.getIcid());
					entry.setGatecode(elog.getGatecode());
					entry.setGatename(elog.getGatename());
					entry.setCreator(elog.getGatename());
					entry.setEntrytype("1");
					entry.setCartype("3");
					entry.setUsermemo("指令卡进厂");
					entryMapper.insertEntrylog(entry);
					entry = null;
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setMsg("进厂保存失败原因：" + e.getMessage());
		} // 根据卡号和车号判断卡状态

		return msg;
	}
	/*
	 * 查询出厂车辆信息
	 */

	@Override
	public Message queryOutList(Entrylog elog) throws DataAccessException {
		Blacklist black = new Blacklist();
		black.setCardid(elog.getIcid());
		black.setCarno(elog.getCarno());
		Message msg = new Message();
		Bcard card = new Bcard();
		card.setCardid(elog.getIcid());
		Entrylog entry = new Entrylog();
		try {
			msg = bcommonService.judgOutCarno(black);
			// System.out.println("输出车号.........."+black);
			if (msg.isSuccess()) {
				card = cardApp.queryinfoBycardid(card);
				if ("1".equals(card.getCartype())) {// 卡为员工车辆判断是否有进厂未出厂的车辆如果没有直接进厂
					msg.setData("员工车辆，车辆放行");
					msg.setMfunc("1");
					entry.setCarno(card.getCarno());
					entry.setIcid(elog.getIcid());
					entry.setGatename("北门");// 暂时这么写，后续更改为传递参数
					entry.setEntrytype("2");
					entry.setCartype("1");
					entry.setDriver(card.getDriver());
					entry.setUnitname(card.getUnitname());
					entryMapper.insertEntrylog(entry);
					msg.setMfunc("3");

				} else if ("0".equals(card.getCartype())) {// 卡为业务车辆获取卡的业务信息
					List<Applicationbill> list = entryMapper.queryoutList(black.getCarno());
					String info = "";
					int flag = 0;
					String msginfo = "";
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							Applicationbill app = list.get(i);
							info = info + "物流号：" + app.getMatchid() + " 计划号：" + app.getPlanid() + " 物料名称："
									+ app.getMaterialname() + " 供货单位：" + app.getSourcename() + " 收货单位："
									+ app.getTargetname() + " 净重/t：" + app.getSuttle() + " 净重时间：" + app.getSuttletime()
									+ " 用户备注：" + app.getUsermemo() + "\r\n\r\n";
							if (!"9".equals(app.getValidflag())) {// 终止业务不判断环节
								if ("83".equals(app.getOperatype()) || "93".equals(app.getOperatype())) {
									String nlist = entryMapper.queryNodelist(app.getRouteid());
									if (nlist.contains("SIN")) {
										if ("".equals(app.getTargettime()) || app.getTargettime() == null) {
											flag++;
											msginfo = msginfo + " 车辆未入库";
										}
									}
									if (nlist.contains("SOUT")) {
										if ("".equals(app.getSourcetime()) || app.getSourcetime() == null) {
											flag++;
											msginfo = msginfo + " 车辆未出库";
										}
									}
								} else {
									// String node =
									// entryMapper.querylastsecond(app.getRouteid());
									List<String> nodelist = entryMapper.queryAllNodelist(app.getRouteid());
									for (int m = 0; m < nodelist.size(); m++) {
										String node = nodelist.get(m);
										if ("T".equals(node)) {
											if ("".equals(app.getTaretime()) || app.getTaretime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未计量皮重";
											}
										} else if ("G".equals(node)) {
											if ("".equals(app.getGrosstime()) || app.getGrosstime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未计量毛重";
											}
										} else if ("IN".equals(node)) {
											if ("".equals(app.getEntertime()) || app.getEntertime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未进厂";
											}
										} else if ("SP".equals(node)) {
											if ("".equals(app.getSampletime()) || app.getSampletime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未取样";
											}
										} else if ("SIN".equals(node)) {
											if ("".equals(app.getTargettime()) || app.getTargettime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未入库";
											}
										} else if ("SOUT".equals(node)) {
											if ("".equals(app.getSourcetime()) || app.getSourcetime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未出库";
											}
										}
									}

								}
							}

						}
					} else {
						card.setCardid(black.getCardid());
						card = entryMapper.queryCardclass(card);
						if ("0".equals(card.getCardtype())) {
							// msg.setMsg("临时卡无业务不允许进厂");
							info = "临时卡无业务不允许出厂";
							flag = 1;
						} else {
							info = "固定卡无业务信息";
							msg.setMfunc("3");
							try {
								Map<String, String> parameterMap = new HashMap<String, String>();
								parameterMap.put("carno", black.getCarno());
								parameterMap.put("icid", elog.getIcid());
								parameterMap.put("gatename", "北门");
								parameterMap.put("usermemo", "固定卡出厂");
								parameterMap.put("createman", "");
								parameterMap.put("forceflag", "0");
								parameterMap.put("outflag", "0");
								parameterMap.put("outmsg", "");
								entryMapper.insertOutEntry(parameterMap);
								parameterMap.clear();
								parameterMap = null;
							} catch (Exception e) {

							}

						}

					}
					if (info.length() > 0 && flag > 0) {
						msg.setSuccess(false);
						msg.setData(info);
						msg.setMsg(msginfo);
					} else {
						msg.setData(info);

					}
				} else if ("3".equals(card.getCartype())) {
					msg.setMfunc("3");
					// Entrylog entry = new Entrylog();
					entry.setCarno(elog.getCarno());
					entry.setIcid(elog.getIcid());
					entry.setGatename("北门");// 暂时这么写，后续更改为传递参数
					entry.setEntrytype("1");
					entry.setCartype("3");
					try {
						entryMapper.insertEntrylog(entry);
					} catch (Exception e) {

					}
					entry = null;
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 根据卡号和车号判断卡状态

		return msg;
	}

	@Override
	public Message queryNewOutList(Entrylog elog) throws DataAccessException {
		Blacklist black = new Blacklist();
		black.setCardid(elog.getIcid());
		black.setCarno(elog.getCarno());
		Message msg = new Message();
		Bcard card = new Bcard();
		card.setCardid(elog.getIcid());
		Entrylog entry = new Entrylog();
		int cflag=0;
		try {
			msg = bcommonService.judgOutCarno(black);
			// System.out.println("输出车号.........."+black);
			if (msg.isSuccess()) {
				card = cardApp.queryinfoBycardid(card);
				if ("1".equals(card.getCartype())) {// 卡为员工车辆判断是否有进厂未出厂的车辆如果没有直接进厂
					elog.setCarno(card.getCarno());
					Entrylog e = entryMapper.queryId(elog);// 根据车号查询进厂未出厂的信息id
					if(e==null){
						cflag=entryMapper.queryCountBycarno(elog);
					}
					if (e == null &&cflag>0) {
						msg.setSuccess(false);
						msg.setMsg("该车未有进厂信息，车辆已锁定！");
						black.setCreator(elog.getGatename());
						black.setRecordtype("2");
						black.setCardid(elog.getCarno());
						black.setUsermemo("员工车辆重复出厂");
						cardApp.insertBlack(black);// 添加车号到黑名单,查明原因还原车辆 }
						msg.setMfunc("1");
						msg.setMore(card.getCarno());
					}else{
						Map<String, String> parameterMap = new HashMap<String, String>();
						parameterMap.put("carno", black.getCarno());
						parameterMap.put("icid", elog.getIcid());
						parameterMap.put("gatename", elog.getGatename());
						parameterMap.put("usermemo", "员工车辆出厂");
						parameterMap.put("createman", elog.getGatename());
						parameterMap.put("forceflag", "0");
						parameterMap.put("outflag", "0");
						parameterMap.put("outmsg", "");
						entryMapper.insertOutEntry(parameterMap);
						parameterMap.clear();
						parameterMap = null;
						msg.setData("员工车辆，车辆放行");
						msg.setMfunc("3");
						msg.setMore(card.getCarno());
					}
					

				} else if ("0".equals(card.getCartype())) {// 卡为业务车辆获取卡的业务信息
					List<Applicationbill> list = entryMapper.queryoutList(black.getCarno());
					String info = "";
					int flag = 0;
					String msginfo = "";
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							Applicationbill app = list.get(i);
							info = info + "物流号：" + app.getMatchid() + " 计划号：" + app.getPlanid() + " 物料名称："
									+ app.getMaterialname() + " 供货单位：" + app.getSourcename() + " 收货单位："
									+ app.getTargetname() + " 净重/t：" + app.getSuttle() + " 净重时间：" + app.getSuttletime()
									+ " 用户备注：" + app.getUsermemo() + "\r\n\r\n";
							if (!"9".equals(app.getValidflag())) {// 终止业务不判断环节
								if ("83".equals(app.getOperatype()) || "93".equals(app.getOperatype())) {
									String nlist = entryMapper.queryNodelist(app.getRouteid());
									if (nlist.contains("SIN")) {
										if ("".equals(app.getTargettime()) || app.getTargettime() == null) {
											flag++;
											msginfo = msginfo + " 车辆未入库";
										}
									}
									if (nlist.contains("SOUT")) {
										if ("".equals(app.getSourcetime()) || app.getSourcetime() == null) {
											flag++;
											msginfo = msginfo + " 车辆未出库";
										}
									}
								} else {
									// String node =
									// entryMapper.querylastsecond(app.getRouteid());
									List<String> nodelist = entryMapper.queryAllNodelist(app.getRouteid());
									for (int m = 0; m < nodelist.size(); m++) {
										String node = nodelist.get(m);
										if ("T".equals(node)) {
											if ("".equals(app.getTaretime()) || app.getTaretime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未计量皮重";
											}
										} else if ("G".equals(node)) {
											if ("".equals(app.getGrosstime()) || app.getGrosstime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未计量毛重";
											}
										} else if ("IN".equals(node)) {
											if ("".equals(app.getEntertime()) || app.getEntertime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未进厂";
											}
										} else if ("SP".equals(node)) {
											if ("".equals(app.getSampletime()) || app.getSampletime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未取样";
											}
										} else if ("SIN".equals(node)) {
											if ("".equals(app.getTargettime()) || app.getTargettime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未入库";
											}
										} else if ("SOUT".equals(node)) {
											if ("".equals(app.getSourcetime()) || app.getSourcetime() == null) {
												flag++;
												msginfo = msginfo + " 车辆未出库";
											}
										}
									}

								}
							}

						}
					} else {
						card.setCardid(black.getCardid());
						card = entryMapper.queryCardclass(card);
						if ("0".equals(card.getCardtype())) {
							// msg.setMsg("临时卡无业务不允许进厂");
							info = "临时卡无业务不允许出厂";
							msginfo="临时卡无业务不允许出厂";
							flag = 1;
						} else {

							elog.setCarno(black.getCarno());
							Entrylog e = entryMapper.queryId(elog);// 根据车号查询进厂未出厂的信息id
							if (e == null) {// 未有进厂信息
                                info = "固定卡车辆未进厂，请检查";
                                msginfo="固定卡车辆未进厂，请检查";
								flag = 1;
							} else {

								info = "固定卡无业务信息";
								msginfo="固定卡无业务信息";
								msg.setMfunc("3");
								try {
									Map<String, String> parameterMap = new HashMap<String, String>();
									parameterMap.put("carno", black.getCarno());
									parameterMap.put("icid", elog.getIcid());
									parameterMap.put("gatename", elog.getGatename());
									parameterMap.put("usermemo", "固定卡出厂");
									parameterMap.put("createman", elog.getGatename());
									parameterMap.put("forceflag", "0");
									parameterMap.put("outflag", "0");
									parameterMap.put("outmsg", "");
									entryMapper.insertOutEntry(parameterMap);
									parameterMap.clear();
									parameterMap = null;
								} catch (Exception ex) {
									ex.printStackTrace();

								}
							}
						}

					}
					if (info.length() > 0 && flag > 0) {
						msg.setSuccess(false);
						msg.setData(info);
						msg.setMsg(msginfo);
					} else {
						msg.setData(info);

					}
				} else if ("3".equals(card.getCartype())) {
					msg.setMfunc("3");
					// Entrylog entry = new Entrylog();
					entry.setCarno(elog.getCarno());
					entry.setIcid(elog.getIcid());
					entry.setGatecode(elog.getGatecode());// 暂时这么写，后续更改为传递参数
					entry.setGatename(elog.getGatename());// 暂时这么写，后续更改为传递参数
					entry.setCreator(elog.getGatename());
					entry.setEntrytype("1");
					entry.setCartype("3");
					entry.setUsermemo("指令卡出厂");
					try {
						entryMapper.insertEntrylog(entry);
					} catch (Exception e) {

					}
					entry = null;
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setMsg("出厂保存失败原因：" + e.getMessage());
		} // 根据卡号和车号判断卡状态

		return msg;
	}
	/*
	 * 添加进厂信息
	 */

	@Override
	public Message insertInEntrylog(Entrylog entry) throws DataAccessException {
		Message msg = new Message();
		 List<Applicationbill> list = entryMapper.searchMatchidinfo(entry.getCarno());
		if ( null!=list  && list.size() > 0) {
			Applicationbill app = list.get(0);
			entry.setOperatype(app.getOperatype());
			entry.setMaterialname(app.getMaterialname());
			entry.setMatchid(app.getMatchid());
			entry.setPlanid(app.getPlanid());
			entry.setSourcename(app.getSourcename());
			entry.setTargetname(app.getTargetname());
			entry.setCartype(app.getCartype());
			entry.setDriver(app.getDriver());
			entry.setUnitname(app.getUnitname());
			Entrylog e = entryMapper.queryId(entry);// 根据车号查询进厂未出厂的信息id
			if (e == null) {// 为空时，车号未有进厂未出厂的信息,添加一条新的进厂信息
				entry.setEntrytype("1");
				entryMapper.insertEntrylog(entry);
			} else {// 如果有进厂未出厂的车辆，获取id，根据id更新进厂信息
				entry.setId(e.getId());
				entryMapper.updateEntrylog(entry);
			}
			String matchids = "";
			e = entryMapper.queryId(entry);// 根据车号查询进厂未出厂的信息id
			for (int i = 0; i < list.size(); i++) {// 根据matchid更新业务临时表中的进厂信息，写入制卡表中的进厂id
				if (list.size() > 0) {
					Applicationbill appss = list.get(i);
					if (appss != null) {
						entry.setMatchid(appss.getMatchid());
						entry.setId(e.getId());
						entryMapper.updatecurrtemp(entry);
						entryMapper.updateInId(entry);
						if (matchids.length() == 0) {
							matchids = appss.getMatchid();
						} else {
							matchids = matchids + "," + appss.getMatchid();
						}
					}
				}
			}
			msg.setMores(list);
			msg.setMfunc(matchids);
			msg.setSuccess(true);
			msg.setMsg("操作成功");
			//System.out.println("操作成功。。。。。。");
			e = null;
		}else{
			msg.setSuccess(false);
			msg.setMsg("根据车号未查询到业务信息："+entry.getCarno());	
		}
		return msg;
	}

	/*
	 * 添加出厂信息
	 * 
	 */
	public Message insertOutEntrylog(Entrylog entry) throws DataAccessException {
		int i = 0;

		Message msg = new Message();
		String matchids = entryMapper.queryMatchids(entry.getCarno());
		msg.setMfunc(matchids);
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("carno", entry.getCarno());
		parameterMap.put("icid", entry.getIcid());
		parameterMap.put("rfidid", entry.getRfidid());
		parameterMap.put("gatename", entry.getGatename());
		parameterMap.put("usermemo", entry.getUsermemo());
		parameterMap.put("createman", entry.getCreator());
		parameterMap.put("forceflag", ((Integer) entry.getForceflag()).toString());
		parameterMap.put("outflag", "0");
		parameterMap.put("outmsg", "");
		entryMapper.insertOutEntry(parameterMap);
		Bcard card = new Bcard();
		card.setCardid(entry.getIcid());
		card.setBackman(entry.getCreator());
		card = cardApp.queryinfoBycardid(card);
		if ("0".equals(card.getCardtype())) { // 为临时卡，进行退卡操作
			i = cardApp.backCard(card);
		} else {// 固定卡时不提示
			i = 1;
		}
		if ("0".equals(parameterMap.get("outflag")) || i == 0) {
			msg.setSuccess(false);
			if ("0".equals(parameterMap.get("outflag")) || i > 0) {// 退卡成功，保存失败，提示保存返回信息
				msg.setMsg(parameterMap.get("outmsg"));
			} else if (!"0".equals(parameterMap.get("outflag")) || i == 0) {// 保存成功，退卡失败
				msg.setMsg(parameterMap.get("outmsg") + " , 退卡失败，请在退卡模块退卡");
			}
		} else {
			msg.setMsg(parameterMap.get("outmsg"));
		}

		return msg;
	}

	/**
	 * 判断卡状态
	 * 
	 * @param entry
	 * @return
	 * @throws DataAccessException
	 */
	public Message queryCartype(Entrylog entry) throws DataAccessException {
		Message msg = new Message();
		Entrylog e = entryMapper.queryCartypeBycarid(entry);
		if (e != null) {
			if ("0".equals(e.getValidflag())) {
				msg.setSuccess(false);
				msg.setMsg("IC卡已经挂失");
			} else if ("1".equals(e.getValidflag())) {
				msg.setSuccess(false);
				msg.setMsg("IC卡未发卡");
			} else if ("3".equals(e.getValidflag())) {
				msg.setMfunc(e.getCartype());
				msg.setMore(e.getCarno());
			}
		}

		return msg;
	}

	/**
	 * 添加线材信息
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public Message insertXCtemp(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		String matchid = bcommonService.txMatchid("84");
		app.setMatchid(matchid);

		Bcard card = new Bcard();
		card.setCardid(app.getIcid());// 卡号
		card.setCarno(app.getCarno());// 车号
		card.setCardtype("0");// 临时卡
		card.setCartype("0");// 业务车辆
		card.setFromman(app.getCreator());
		card.setCardclass("0");

		Blacklist black = new Blacklist();
		black.setCardid(card.getCardid());
		black.setCarno(card.getCarno());
		black.setRecordtype("0");// 卡类型，0：IC 、1：rfid

		Blacklist bk = bcommonMapper.queryCardinfo(black);// 根据卡号查询卡信息
		if (bk != null) {// 根据卡号查询信息不为空
			if ("0".equals(bk.getValidflag())) {// 标记为0，提示车辆挂失
				msg.setSuccess(false);
				msg.setMsg("IC卡已挂失");
			} else if ("1".equals(bk.getValidflag())) {// 状态为1，处于卡处于初始化状态
				msg = bcommonService.judgOrFromcarno(black);// 查询车号状态
				if (msg.isSuccess()) {
					cardMapper.fromCard(card); // 发卡
					entryMapper.insertXCtemp(app);// 添加线材业务信息
					msg.setMsg("操作成功");
				}
			} else if ("3".equals(bk.getValidflag())) {// 状态为3，处于发卡状态
				if (app.getCarno().equals(bk.getCarno())) {// 如果根据卡号读取的车号与当前车号一致，查询车号是否在黑名单
					int blackflag = bcommonMapper.queryBlackBycarno(black);
					if (blackflag == 0) {// 如果不在黑名单添加线材信息
						entryMapper.insertXCtemp(app);
					} else {
						msg.setSuccess(false);
						msg.setMsg("车辆在黑名单，请检查车辆");
					}
				} else {
					msg.setSuccess(false);
					msg.setMsg("IC卡已发卡到车辆,发卡车号：" + bk.getCarno());
				}

			}
		} else {// 根据卡号查询信息为空，提示卡未初始化
			msg.setSuccess(false);
			msg.setMsg("卡未初始化");
		}
		app = null;
		card = null;
		black = null;
		return msg;
	}

}
