package com.xgmes.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.core.annotation.Rule;
import com.talent.core.model.Message;
import com.talent.core.mybatis.pagehelper.StringUtil;
import com.xgmes.common.LogisticalRuleCalc;
import com.xgmes.mapper.BCommonMapper;
import com.xgmes.mapper.BusinessConfigMapper;
import com.xgmes.mapper.EntrylogMapper;
import com.xgmes.model.Applicationbill;
import com.xgmes.model.Bcard;
import com.xgmes.model.Blacklist;
import com.xgmes.model.Currtemp;
import com.xgmes.model.Forcestop;
import com.xgmes.model.Measure;
import com.xgmes.model.ReturnMessage;
import com.xgmes.model.WorklineItem;
import com.xgmes.service.BCommonService;

@Service
@Transactional
public class BCommonServiceImpl implements BCommonService {
	@Resource
	private BCommonMapper bcommonMapper;
	@Autowired
	private LogisticalRuleCalc logisticalRuleCalc;
	@Resource
	private BusinessConfigMapper bcmapper;
	@Resource
	private EntrylogMapper entryMapper;

	/**
	 * 根据卡号判断卡、车辆状态
	 * 
	 * @throws ParseException
	 */
	@Override
	public Message judgCarno(Blacklist bc) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message msg = new Message();
		List<Blacklist> ls = new ArrayList<Blacklist>();
		Blacklist bk = bcommonMapper.queryCardinfo(bc);// 根据卡号查询车辆信息
		if (bk != null) {// 根据卡号查询信息不为空
			msg.setTotal(Long.parseLong(bk.getValidflag()));
			if ("0".equals(bk.getValidflag())) {// 标记为0，提示车辆挂失
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡已挂失");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡已挂失");
				}
				ls.add(bk);
				msg.setRows(ls);// 查询的车号信息放到返回对象
			} else if ("1".equals(bk.getValidflag())) {// 标记为1，卡处于初始化状态，为发卡
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡未发卡到车辆");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡未发卡到车辆");
				}
				ls.add(bk);
				msg.setRows(ls);// 查询的车号信息放到返回对象
			} else if ("3".equals(bk.getValidflag())) {// 状态为3，处于发卡状态
				int timeflag = 0;
				int periodflag = 0;
				Date nowtime = new Date();
				if ((!"".equals(bk.getBegintime()) && bk.getBegintime() != null)
						&& (!"".equals(bk.getEndtime()) && bk.getEndtime() != null)) {// 发卡有效期不为空时，系统认为是控制有效期
					Date begintime = sdf.parse(bk.getBegintime());
					Date endtime = sdf.parse(bk.getEndtime());
					if (nowtime.before(endtime) && nowtime.after(begintime)) {
						timeflag = 0;
					} else {
						timeflag = 1;
					}
				}
				if ((!"".equals(bk.getBeginperiod()) && bk.getBeginperiod() != null)
						&& (!"".equals(bk.getEndperiod()) && bk.getEndperiod() != null)) {// 发卡有效时段不为空，系统认为是控制时段
					Date beginperiod = sdf.parse(bk.getBeginperiod());
					Date endperiod = sdf.parse(bk.getEndperiod());
					if (nowtime.before(endperiod) && nowtime.after(beginperiod)) {
						periodflag = 0;
					} else {
						periodflag = 1;
					}
				}

				if (timeflag == 0 && periodflag == 0) {
					// 测试时屏蔽
					bc.setCarno(bk.getCarno());
					bc.setRecordtype(bk.getRecordtype());
					bc.setDeposit(bk.getDeposit());
					bc.setDriver(bk.getDriver());
					bc.setMotorcadecode(bk.getMotorcadecode());
					bc.setMotorcadename(bk.getMotorcadename());
					bc.setValidflag(bk.getValidflag());
					bc.setBegintime(bk.getBegintime());
					bc.setEndtime(bk.getEndtime());
					bc.setBeginperiod(bk.getBeginperiod());
					bc.setEndperiod(bk.getEndperiod());
					// msg.setData(bc);
					Blacklist bl = bcommonMapper.queryBlackinfo(bc);// 根据车号、卡号判断是否处于黑名单
					if (bl != null) {// 如果在黑名单中存在信息判断处于黑名单原因
						if ("0".equals(bl.getRecordtype())) { // 为0时IC卡处于黑名单
							msg.setSuccess(false);
							msg.setMsg("IC卡在黑名单");
						} else if ("1".equals(bl.getRecordtype())) {// 为0时RFID卡处于黑名单
							msg.setSuccess(false);
							msg.setMsg("RFID卡在黑名单");
						} else if ("2".equals(bl.getRecordtype())) {// 车辆在黑名单
							msg.setSuccess(false);
							msg.setMsg("车号在黑名单");
						}
						ls.add(bc);
						msg.setRows(ls);
					} else {
						List<Blacklist> list = bcommonMapper.queryCarinfo(bc);// 查询该车号其他卡号信息
						if (list.size() == 0) { // 没有其他信息的时候，把当前卡号放入集合
							ls.add(bc);
						} else {
							for (int i = 0; i < list.size(); i++) {
								Blacklist ba = (Blacklist) list.get(i);
								Message me = judgOtherId(ba); // 判断卡号是否在黑名单
								if (me.isSuccess()) {// 不在黑名单卡号放入到集合
									ls.add(ba);
								} /*
									 * else {// 在黑名单中时，终止循环，返回信息
									 * msg.setSuccess(false);
									 * msg.setMsg(me.getMsg()); break; }
									 */
							}
							ls.add(bc); // 如果没有在黑名单中的信息，把当前的信息放到返回集合中
						}
						msg.setRows(ls);// 查询的车号信息放到返回对象
					}
				} else if (timeflag > 0 && periodflag == 0) {
					msg.setSuccess(false);
					msg.setMsg("卡不在有效期内，不允许使用");
					ls.add(bk);
					msg.setRows(ls);
				} else if (timeflag == 0 && periodflag == 0) {
					msg.setSuccess(false);
					msg.setMsg("卡不在有效时段内，不允许使用");
					ls.add(bk);
					msg.setRows(ls);
				} else if (timeflag > 0 && periodflag > 0) {
					msg.setSuccess(false);
					msg.setMsg("卡不在有效期内，不允许使用");
					ls.add(bk);
					msg.setRows(ls);
				}
			}
		} else {// 根据卡号查询信息为空，提示卡未初始化
			msg.setSuccess(false);
			msg.setMsg("卡未初始化");

		}
		return msg;
	}

	/**
	 * 根据卡号判断卡、车辆状态，不根据车号查询车辆的其他信息
	 * 
	 * @throws ParseException
	 */

	public Message judgOutCarno(Blacklist bc) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message msg = new Message();
		List<Blacklist> ls = new ArrayList<Blacklist>();
		Blacklist bk = bcommonMapper.queryCardinfo(bc);// 根据卡号查询车辆信息
		if (bk != null) {// 根据卡号查询信息不为空
			msg.setTotal(Long.parseLong(bk.getValidflag()));
			if ("0".equals(bk.getValidflag())) {// 标记为0，提示车辆挂失
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡已挂失");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡已挂失");
				}
				ls.add(bk);
				msg.setRows(ls);// 查询的车号信息放到返回对象
			} else if ("1".equals(bk.getValidflag())) {// 标记为1，卡处于初始化状态，为发卡
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡未发卡到车辆");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡未发卡到车辆");
				}
				ls.add(bk);
				msg.setRows(ls);// 查询的车号信息放到返回对象
			} else if ("3".equals(bk.getValidflag())) {// 状态为3，处于发卡状态
				int timeflag = 0;
				int periodflag = 0;
				Date nowtime = new Date();
				if ((!"".equals(bk.getBegintime()) && bk.getBegintime() != null)
						&& (!"".equals(bk.getEndtime()) && bk.getEndtime() != null)) {// 发卡有效期不为空时，系统认为是控制有效期
					Date begintime = sdf.parse(bk.getBegintime());
					Date endtime = sdf.parse(bk.getEndtime());
					if (nowtime.before(endtime) && nowtime.after(begintime)) {
						timeflag = 0;
					} else {
						timeflag = 1;
					}
				}
				if ((!"".equals(bk.getBeginperiod()) && bk.getBeginperiod() != null)
						&& (!"".equals(bk.getEndperiod()) && bk.getEndperiod() != null)) {// 发卡有效时段不为空，系统认为是控制时段
					Date beginperiod = sdf.parse(bk.getBeginperiod());
					Date endperiod = sdf.parse(bk.getEndperiod());
					if (nowtime.before(endperiod) && nowtime.after(beginperiod)) {
						periodflag = 0;
					} else {
						periodflag = 1;
					}
				}

				if (timeflag == 0 && periodflag == 0) {
					// 测试时屏蔽
					bc.setCarno(bk.getCarno());
					bc.setRecordtype(bk.getRecordtype());
					bc.setDeposit(bk.getDeposit());
					bc.setDriver(bk.getDriver());
					bc.setMotorcadecode(bk.getMotorcadecode());
					bc.setMotorcadename(bk.getMotorcadename());
					bc.setValidflag(bk.getValidflag());
					bc.setBegintime(bk.getBegintime());
					bc.setEndtime(bk.getEndtime());
					bc.setBeginperiod(bk.getBeginperiod());
					bc.setEndperiod(bk.getEndperiod());
					// msg.setData(bc);
					Blacklist bl = bcommonMapper.queryBlackinfo(bc);// 根据车号、卡号判断是否处于黑名单
					if (bl != null) {// 如果在黑名单中存在信息判断处于黑名单原因
						if ("0".equals(bl.getRecordtype())) { // 为0时IC卡处于黑名单
							msg.setSuccess(false);
							msg.setMsg("IC卡在黑名单");
						} else if ("1".equals(bl.getRecordtype())) {// 为0时RFID卡处于黑名单
							msg.setSuccess(false);
							msg.setMsg("RFID卡在黑名单");
						} else if ("2".equals(bl.getRecordtype())) {// 车辆在黑名单
							if("1".equals(bk.getCartype())){
								msg.setSuccess(false);
								msg.setMsg("车辆被锁定，需解除预警！");
								msg.setMore(bc.getCarno());
							} else{
								msg.setSuccess(false);
								msg.setMsg("车号在黑名单");
								msg.setMore(bc.getCarno());
							}
						}

					}
					ls.add(bc);
					msg.setRows(ls);// 查询的车号信息放到返回对象

				} else if (timeflag > 0 && periodflag == 0) {
					msg.setSuccess(false);
					msg.setMsg("卡不在有效期内，不允许使用");
					ls.add(bk);
					msg.setRows(ls);
				} else if (timeflag == 0 && periodflag == 0) {
					msg.setSuccess(false);
					msg.setMsg("卡不在有效时段内，不允许使用");
					ls.add(bk);
					msg.setRows(ls);
				} else if (timeflag > 0 && periodflag > 0) {
					msg.setSuccess(false);
					msg.setMsg("卡不在有效期内，不允许使用");
					ls.add(bk);
					msg.setRows(ls);
				}
			}
		} else {// 根据卡号查询信息为空，提示卡未初始化
			msg.setSuccess(false);
			msg.setMsg("卡未初始化");

		}
		return msg;
	}

	/**
	 * 根据rfid卡号判断rfid状态
	 * 
	 * @throws ParseException
	 */

	public Message judgRFID(Blacklist bc) throws ParseException {
		Message msg = new Message();
		List<Blacklist> ls = new ArrayList<Blacklist>();
		if (StringUtil.isNotEmpty(bc.getCardid())) {
			Blacklist bk = bcommonMapper.queryCardinfo(bc);// 根据卡号查询车辆信息
			if (bk != null) {// 根据卡号查询信息不为空
				msg.setTotal(Long.parseLong(bk.getValidflag()));
				if ("0".equals(bk.getValidflag())) {// 标记为0，提示车辆挂失
					msg.setSuccess(false);
					if ("0".equals(bk.getRecordtype())) {
						msg.setMsg("IC卡已挂失");
					} else if ("1".equals(bk.getRecordtype())) {
						msg.setMsg("RFID卡已挂失");
					}
					ls.add(bk);
					msg.setRows(ls);// 查询的车号信息放到返回对象
				} else if ("1".equals(bk.getValidflag())) {// 标记为1，卡处于初始化状态，为发卡
					msg.setSuccess(true);
					ls.add(bk);
					msg.setRows(ls);// 查询的车号信息放到返回对象
				} else if ("3".equals(bk.getValidflag())) {// 状态为3，处于发卡状态
					if (bc.getCarno().equals(bk.getCarno())) {// 判断页面车号和系统车号是否一致
						msg.setSuccess(true);
					} else {
						msg.setSuccess(false);
						msg.setMsg("RFID卡已发放到车辆");
					}
					ls.add(bc);
					msg.setRows(ls);// 查询的车号信息放到返回对象
				}
			} else {// 根据卡号查询信息为空，提示卡未初始化
				msg.setSuccess(false);
				msg.setMsg("RFID卡未初始化");
			}
			bk = null;
		}
		ls = null;
		return msg;
	}

	public Message judgOtherId(Blacklist bc) {
		Message msg = new Message();
		Blacklist bk = bcommonMapper.queryCardinfo(bc);// 根据卡号查询车辆信息
		if (bk != null) {// 根据卡号查询信息不为空
			if ("0".equals(bk.getValidflag())) {// 标记为0，提示车辆挂失
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡已挂失");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡已挂失");
				}
			} else if ("1".equals(bk.getValidflag())) {// 标记为1，卡处于初始化状态，为发卡
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡未发卡到车辆");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡未发卡到车辆");
				}
			}
		} else {// 根据卡号查询信息为空，提示卡未初始化
			msg.setSuccess(false);
			msg.setMsg("卡未初始化");
		}
		return msg;
	}

	public Message forcestop(Forcestop ft) {
		Message msg = new Message();

		Forcestop stop = bcommonMapper.queryInfoByMatchid(ft);
		if (stop != null) {
			ft.setCarno(stop.getCarno());
			ft.setMaterialname(stop.getMaterialname());
			ft.setSourcename(stop.getSourcename());
			ft.setTargetname(stop.getTargetname());
		}
		bcommonMapper.insertForcestop(ft);
		bcommonMapper.updateAppStop(ft);
		bcommonMapper.updateCurrStop(ft);

		return msg;
	}

	public Message rebackforcestop(Forcestop ft) {
		Message msg = new Message();

		Forcestop stop = bcommonMapper.queryInfoByMatchid(ft);
		if (stop != null) {
			ft.setCarno(stop.getCarno());
			ft.setMaterialname(stop.getMaterialname());
			ft.setSourcename(stop.getSourcename());
			ft.setTargetname(stop.getTargetname());
		}
		bcommonMapper.insertForcestop(ft);
		bcommonMapper.updateAppRebackStop(ft);
		bcommonMapper.updateCurrRebackStop(ft);

		return msg;
	}

	/**
	 * 根据卡号判断是否允许发卡
	 */

	public Message judgCarId(Blacklist bc) {
		Message msg = new Message();
		Blacklist bk = bcommonMapper.queryCardinfo(bc);// 根据卡号查询车辆信息
		if (bk != null) {// 根据卡号查询信息不为空
			bc.setCarno(bk.getCarno());
			msg.setData(bc); // 把车号信息放到返回对象中
			if ("0".equals(bk.getValidflag())) {// 标记为0，提示车辆挂失
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡已挂失");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡已挂失");
				}
			} else if ("3".equals(bk.getValidflag())) {// 状态为3，处于发卡状态
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡已发卡到车辆");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡已发卡到车辆");
				}
			}
		} else {// 根据卡号查询信息为空，提示卡未初始化
			msg.setSuccess(false);
			msg.setMsg("卡未初始化");
		}
		return msg;
	}

	/**
	 * 根据车号判断车号是否发卡是否在黑名单
	 */

	public Message judgOrFromcarno(Blacklist bc) {
		Message msg = new Message();
		int blackflag = bcommonMapper.queryBlackBycarno(bc);
		int carflag = bcommonMapper.queryCardBycarno(bc);
		if (blackflag > 0 && carflag == 0) {
			msg.setSuccess(false);
			msg.setMsg("车辆在黑名单");
		} else if (blackflag == 0 && carflag > 0) {
			msg.setSuccess(false);
			msg.setMsg("车辆已经发卡");
		} else if (blackflag > 0 && carflag > 0) {
			msg.setSuccess(false);
			msg.setMsg("车辆在黑名单");
		}

		return msg;
	}

	/**
	 * 根据卡号是否允许初始化
	 */
	@Override
	public Message judgInitCarno(Blacklist bc) {
		Message msg = new Message();
		Blacklist bk = bcommonMapper.queryCardinfo(bc);// 根据卡号查询车辆信息
		if (bk != null) {// 根据卡号查询信息不为空
			bc.setCarno(bk.getCarno());
			msg.setData(bc);
			if ("0".equals(bk.getValidflag())) {// 标记为0，提示车辆挂失
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡已挂失");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡已挂失");
				}
			} else if ("1".equals(bk.getValidflag())) {// 状态为3，处于发卡状态
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡已经初始化，无需重新初始化");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡已经初始化，无需重新初始化");
				}
			} else if ("3".equals(bk.getValidflag())) {// 状态为3，处于发卡状态
				msg.setSuccess(false);
				if ("0".equals(bk.getRecordtype())) {
					msg.setMsg("IC卡已经发卡，不允许初始化");
				} else if ("1".equals(bk.getRecordtype())) {
					msg.setMsg("RFID卡已经发卡，不允许初始化");
				}
			}
		}
		return msg;
	}

	/**
	 * 根据车号判断车号是否在黑名单
	 */

	public Message judgOrBlackCarno(Blacklist bc) {
		Message msg = new Message();
		int blackflag = bcommonMapper.queryBlackBycarno(bc);

		if (blackflag == 0) {
			Bcard card = bcommonMapper.queryIcidBycarno(bc.getCarno());
			if (card != null) {
				msg.setData(card.getCardid());
				msg.setFlags(card.getCardtype());
				msg.setMore(card.getCartype());
				msg.setMfunc(card.getDriver());
				msg.setMores(card.getUnitname());
			}
		} else {
			msg.setSuccess(false);
			msg.setMsg("车辆在黑名单");

		}
		return msg;
	}

	/**
	 * 生成matchid
	 *
	 * @param operatype
	 *            业务类型
	 * @return matchid
	 */
	public String txMatchid(String operatype) throws DataAccessException {
		Map<String, String> parameterMap = new HashMap<String, String>();
		String prefix = String.valueOf(operatype);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		String dateStr = dateFormat.format(new Date());
		parameterMap.put("name", "matchid" + dateStr);
		parameterMap.put("nextval", "0");
		bcommonMapper.txNextValue(parameterMap);
		int nextValue = Integer.parseInt(parameterMap.get("nextval"));
		String nextValueStr = String.format("%0" + 5 + "d", nextValue);
		return prefix + dateStr + nextValueStr;
	}

	/**
	 * 生成单据号
	 *
	 * @param operatype
	 *            业务类型
	 * @return matchid
	 */
	public String txPlanid(String operatype) throws DataAccessException {
		Map<String, String> parameterMap = new HashMap<String, String>();
		String prefix = String.valueOf(operatype);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		String dateStr = dateFormat.format(new Date());
		parameterMap.put("name", "planid" + dateStr);
		parameterMap.put("nextval", "0");
		bcommonMapper.txNextValue(parameterMap);
		int nextValue = Integer.parseInt(parameterMap.get("nextval"));
		String nextValueStr = String.format("%0" + 4 + "d", nextValue);
		return prefix + dateStr + nextValueStr;
	}

	/**
	 * 生成业务号
	 *
	 * @param operatype
	 *            业务类型
	 * @return matchid
	 */
	public String taskcode(String operatype) throws DataAccessException {
		Map<String, String> parameterMap = new HashMap<String, String>();
		String prefix = String.valueOf(operatype);
		parameterMap.put("name", "taskcode");
		parameterMap.put("nextval", "0");
		bcommonMapper.txNextValue(parameterMap);
		int nextValue = Integer.parseInt(parameterMap.get("nextval"));
		String nextValueStr = String.format("%0" + 6 + "d", nextValue);
		return prefix + nextValueStr;
	}

	/**
	 * 获取基础数据编码
	 *
	 * @param operatype
	 *            业务类型
	 * @return matchid
	 */
	public String getCode(String types) throws DataAccessException {
		Map<String, String> parameterMap = new HashMap<String, String>();
		String prefix = String.valueOf(types);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		String dateStr = dateFormat.format(new Date());
		parameterMap.put("name", types);
		parameterMap.put("nextval", "0");
		bcommonMapper.txNextValue(parameterMap);
		int nextValue = Integer.parseInt(parameterMap.get("nextval"));
		String nextValueStr = String.format("%0" + 5 + "d", nextValue);
		return prefix + dateStr + nextValueStr;
	}

	/**
	 * 生成单据编码
	 *
	 * 
	 * @return dtype
	 */
	public String getDocumenttype() throws DataAccessException {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("name", "dtype");
		parameterMap.put("nextval", "0");
		bcommonMapper.txNextValue(parameterMap);
		int nextValue = Integer.parseInt(parameterMap.get("nextval"));
		String nextValueStr = String.format("%0" + 2 + "d", nextValue);
		return "1" + nextValueStr;
	}

	/**
	 * 保存前验证
	 * 
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Message beforeinsert(Currtemp curr, String plink) throws DataAccessException {
		Currtemp c = new Currtemp();
		if ("".equals(curr.getMatchid()) || curr.getMatchid() == null) {// 如果物流号不为空，根据物流号查询业务临时表中的数据信息
			c.setOperatype(curr.getOperatype());
			c.setCarno(curr.getCarno());
		} else {
			c = bcmapper.queryCurrTemp(curr);
		}

		c.setProcesslink(plink);
		c.setUnitcode(curr.getUnitcode());
		c.setUnitname(curr.getUnitname());
		Message msg = logisticalRuleCalc.flagsCheck(c, Rule.BEFORE_SAVE);
		List<ReturnMessage> list = (List<ReturnMessage>) msg.getFlags();
		String message = "";
		if (list != null) {
			if (!"0".equals(msg.getMfunc())) {
				for (int i = 0; i < list.size(); i++) {
					ReturnMessage rm = list.get(i);
					if (!"".equals(rm.getMsg())) {
						message = message + rm.getMsg() + "<br/>";
					}
				}
			}
		}
		if (message.length() > 0) {
			msg.setMsg(message);
		}

		return msg;
	}

	/**
	 * 是否允许作废
	 * 
	 * @param q
	 * @return
	 * @throws DataAccessException
	 */
	@Override

	public Message beforeCancel(Currtemp curr, String plink) throws DataAccessException {
		Message msg = new Message();
		try {
			Currtemp ctemp = bcmapper.queryCurrTemp(curr); // 根据物流号查询信息
			if (ctemp != null) {
				/*
				 * MK 制卡 SP 取样 SIN 入库 SOUT 出库 IN 进厂 T 计皮 G 计毛 OUT 出厂
				 */
				if ("MK".equals(plink)) {
					if (!"".equals(ctemp.getEntertime()) && ctemp.getEntertime() != null) {
						msg.setSuccess(false);
						msg.setMsg("已经进厂，不允许作废信息");
					}
				} else {
					if ("83".equals(ctemp.getOperatype()) || "93".equals(ctemp.getOperatype())) {
						String nlist = entryMapper.queryNodelist(ctemp.getRouteid());
						if (StringUtil.isNotEmpty(nlist)) {
							if (nlist.contains("SIN")) {
								if ("".equals(ctemp.getTargettime()) || ctemp.getTargettime() == null) {
									msg.setSuccess(false);
									msg.setMsg("已经入库，不允许作废信息");
								}
							}
							if (nlist.contains("SOUT")) {
								if ("".equals(ctemp.getSourcetime()) || ctemp.getSourcetime() == null) {
									msg.setSuccess(false);
									msg.setMsg("已经出库，不允许作废信息");
								}
							}
						}
					} else {
						if (StringUtil.isNotEmpty(ctemp.getRouteid())) {
							WorklineItem witem = new WorklineItem();
							witem.setFid(Long.parseLong(ctemp.getRouteid()));
							witem.setNodecode(plink);
							witem = bcmapper.queryNextNode(witem);
							if (witem != null) {
								if ("IN".equals(witem.getNodecode())) {
									if (!"".equals(ctemp.getEntertime()) && ctemp.getEntertime() != null) {
										msg.setSuccess(false);
										msg.setMsg("已经进厂，不允许作废信息");
									}
								} else if ("SP".equals(witem.getNodecode())) {
									if (!"".equals(ctemp.getSampletime()) && ctemp.getSampletime() != null) {
										msg.setSuccess(false);
										msg.setMsg("已经取样，不允许作废信息");
									}
								} else if ("T".equals(witem.getNodecode())) {
									if (!"".equals(ctemp.getTaretime()) && ctemp.getTaretime() != null) {
										msg.setSuccess(false);
										msg.setMsg("已经计皮，不允许作废信息");
									}
								} else if ("G".equals(witem.getNodecode())) {
									if (!"".equals(ctemp.getGrosstime()) && ctemp.getGrosstime() != null) {
										msg.setSuccess(false);
										msg.setMsg("已经计毛，不允许作废信息");
									}
								} else if ("SIN".equals(witem.getNodecode())) {
									if (!"".equals(ctemp.getTargettime()) && ctemp.getTargettime() != null) {
										msg.setSuccess(false);
										msg.setMsg("已经入库，不允许作废信息");
									}
								} else if ("SOUT".equals(witem.getNodecode())) {
									if (!"".equals(ctemp.getSourcetime()) && ctemp.getSourcetime() != null) {
										msg.setSuccess(false);
										msg.setMsg("已经出库，不允许作废信息");
									}
								}
							}
						}

					}

				}
			}
		} catch (Exception e) {

			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		return msg;
	}

	/**
	 * 通过物流号判断需要回传质检的信息
	 * 
	 * @param matchids
	 * @return
	 * @throws DataAccessException
	 */
	public List<String> getMessage(String matchids, String type) throws DataAccessException {
		String[] mlist = matchids.split(",");
		List<String> list = new ArrayList<String>();
		String pcode = "";
		String statu = "";
		try {
			for (int i = 0; i < mlist.length; i++) {
				if ("in".equals(type)) {
					Applicationbill app = bcommonMapper.queryInfoReturnQA(mlist[i]);
					if (app != null) {
						if ("90".equals(app.getOperatype())) {
							pcode = bcommonMapper.queryOrsample(app.getRouteid());
							if (pcode != null) {
								statu = "&status=0";
							} else {
								statu = "&status=7";
							}
							String info = "noticebillid=" + app.getMatchid() + "&noticebillbodyid=" + app.getItemid()
									+ "&cph=" + app.getCarno() + "&icno=" + app.getIcid() + "&remark=" + pcode
									+ "&billid=" + app.getOrderno() + "&billbodyid=" + app.getSaleitemid() + "&djno="
									+ app.getPlanid() + "&cvendorbaseid=" + app.getSourcecode() + "&Cbaseid="
									+ app.getMaterialcode() + "&cemployeeid=a&cdeptid=b&cooperator=c&begintime="
									+ app.getBegintime() + "&endtime=" + app.getBegintime() + "&result=进门" 
									+"&cupsourcebillid="+app.getReserve1()+"&cupsourcebillrowid="+app.getReserve2()
									+"&csourcebillid="+app.getReserve3()+"&csourcebillrowid="+app.getReserve4()
									+"&vdef16="+app.getReserve5()+"&vdef17="+app.getReserve6()+"&vdef18="+app.getReserve7()
									+"&vdef19="+app.getReserve9()+"&vdef20="+app.getReserve10()+"&memo="+app.getMemo()
									+"&memo1="+app.getMemo1()+"&memo2="+app.getMemo2()+"&memo3="+app.getMemo3()+"&memo4="+app.getMemo4()	
									+ statu;
							list.add(info);
						}

					}

				} else if ("out".equals(type)) {
					Applicationbill app = bcommonMapper.queryOutInfoReturnQA(mlist[i]);
					if (app != null) {
						if ("90".equals(app.getOperatype())) {
						/*pcode = bcommonMapper.queryOrsample(app.getRouteid());
						if (pcode != null) {*/
							String info = "noticebillid=" + app.getMatchid() + "&noticebillbodyid=" + app.getItemid()
									+ "&begintime=" + app.getBegintime() + "&endtime=" + app.getBegintime()
									+ "&result=出门";
							list.add(info);
						}
					}

				}
			}

		} catch (Exception e) {
          
		}

		return list;
	}

	/**
	 * 是否需要发放rfid
	 * 
	 * @param matchids
	 * @return
	 * @throws DataAccessException
	 */
	public String queryInfoBymateiracode(String mateiralcode, String type) throws DataAccessException {
		String info = "";
		String msg = bcommonMapper.queryInfoBymateiracode(mateiralcode);
		if (msg == null) {
			info = "-1";
		} else {
			String[] array = msg.split(",");
			if ("1".equals(type)) {// 第一个路线id 默认0，第二个rfid 默认-1，第三个 是否计量
									// 默认-1，第四个是否监秤
				info = array[0];
			} else if ("2".equals(type)) {
				info = array[1];
			} else if ("3".equals(type)) {
				info = array[2];
			} else if ("4".equals(type)) {
				info = array[3];
			}
		}

		return info;

	}

	/**
	 * 库房判断上一环节是否完成
	 * 
	 * @param matchids
	 * @return
	 * @throws DataAccessException
	 */
	public Message queryUpNode(String matchid, String type) throws DataAccessException {
		Message msg = new Message();
		int flag = 0;
		Applicationbill app = bcommonMapper.queryNodeLists(matchid);
		try {
			if (app != null) {
				app.setNodecode(type);
				Applicationbill workitem = (Applicationbill) bcommonMapper.queryUpNode(app);
				if (workitem != null) {
					if ("83".equals(app.getOperatype()) || "93".equals(app.getOperatype())) {
						if ("".equals(app.getEntertime()) || app.getEntertime() == null) {
							flag++;
							msg.setMsg("车辆未进厂");
						}
					} else {
						if ("T".equals(workitem.getNodecode())) {
							if ("".equals(app.getTaretime()) || app.getTaretime() == null) {
								flag++;
								msg.setMsg("未计量皮重");
							}
						} else if ("G".equals(workitem.getNodecode())) {
							if ("".equals(app.getGrosstime()) || app.getGrosstime() == null) {
								flag++;
								msg.setMsg("车辆未计量毛重");
							}
						} else if ("IN".equals(workitem.getNodecode())) {
							if ("".equals(app.getEntertime()) || app.getEntertime() == null) {
								flag++;
								msg.setMsg("车辆未进厂");
							}
						} else if ("SP".equals(workitem.getNodecode())) {
							if ("".equals(app.getSampletime()) || app.getSampletime() == null) {
								flag++;
								msg.setMsg("车辆未取样");
							}
						} else if ("SIN".equals(workitem.getNodecode())) {
							if ("".equals(app.getTargettime()) || app.getTargettime() == null) {
								flag++;
								msg.setMsg("车辆为入库");
							}
						} else if ("SOUT".equals(workitem.getNodecode())) {
							if ("".equals(app.getSourcetime()) || app.getSourcetime() == null) {
								flag++;
								msg.setMsg("车辆为出库");
							}
						}
					}
					workitem = null; // 清空对象
				}

			}
			if (flag > 0) {
				msg.setSuccess(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		app = null;// 清空对象
		return msg;

	}

	/**
	 * 根据车号、业务号查询皮重及业务信息
	 * 
	 * @param meausre
	 * @return
	 * @throws ParseException
	 */
	public Message getTareBYCarnoT(Measure measure) throws ParseException {
		Message msg = new Message();
		// 根据车号查询业务临时表皮重及业务信息
		Measure ms = bcommonMapper.getCurrtempBYCarnoT(measure);
		// 若业务临时表无数，判断是否有皮重有效期，若有，根据车号查询皮重表
		if (ms == null && (measure.getTarehour() > 0 || measure.getTarehour() == -1)) {
			ms = bcommonMapper.getTareBYCarnoT(measure);
			// 如果有皮重且皮重不是长期有效，判断是否在有效期内
			if (ms != null && measure.getTarehour() > 0) {
				// 判断业务号维护的皮重有效期，是否小于皮重时间与当前时间差，若小于，将皮重设置为0，重新计皮
				if (measure.getTarehour() < ms.getTarediff()) {
					ms = null;
				}
			}
		}
		msg.setMore(ms);
		// 如果等于NULL，复制为FALSE
		if (ms == null)
			msg.setSuccess(false);
		return msg;
	}
	
}
