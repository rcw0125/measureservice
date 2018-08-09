package com.talent.measure.common;

import com.talent.base.annotation.Rule;
import com.talent.measure.dao.BusinessConfigDao;
import com.talent.measure.model.Equipment;
import com.talent.measure.model.FunctionLog;
import com.talent.measure.model.Measure;
import com.talent.measure.model.Msameweight;
import com.talent.base.model.ReturnMessage;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class BusinessConfig {

	@Autowired
	private BusinessConfigDao bcDao;

	@Rule(name = "供方皮重超差对比判断", ctrlflag = "tareoutdo", memo = "发货皮重超差对比判断", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkTareoutdo(Measure measure) {
		ReturnMessage msg = new ReturnMessage();

		if (measure.getTareb() > 0) {
			msg = checkOverweight("T", measure.getTareoutdo(), measure.getTare(), measure.getTareb(),
					measure.getTareoutup(), measure.getTareoutdown());
			msg.setFunctionname("checkTareoutdo");
		} else {
			msg.setSuccess(false);
			msg.setMsg("未填写供方皮重！");
			msg.setFlag(measure.getTareoutdo());
		}

		FunctionLog logs = new FunctionLog();
		String paraminfo = "tareb：" + measure.getTareb() + "|tareoutdo：" + measure.getTareoutdo() + "|tare:"
				+ measure.getTare() + "|Tareoutup:" + measure.getTareoutup() + "|getTareoutdown"
				+ measure.getTareoutdown();

		logs.setFunctionname("checkTareoutdo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);

		return msg;
	}

	@Rule(name = "供方毛重超差对比判断", ctrlflag = "grossoutdo", memo = "发货毛重超差对比判断", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkGrossoutdo(Measure measure) {
		ReturnMessage msg = new ReturnMessage();
		if (measure.getGrossb() > 0) {
			msg = checkOverweight("G", measure.getGrossoutdo(), measure.getGross(), measure.getGrossb(),
					measure.getGrossoutup(), measure.getGrossoutdown());
			msg.setFunctionname("checkGrossoutdo");
		} else {
			msg.setSuccess(false);
			msg.setMsg("未填写供方毛重！");
			msg.setFlag(measure.getGrossoutdo());
		}

		FunctionLog logs = new FunctionLog();
		String paraminfo = "getGrossoutdo：" + measure.getGrossoutdo() + "|getGross：" + measure.getGross()
				+ "|getGrossb:" + measure.getGrossb() + "|getGrossoutup:" + measure.getGrossoutup() + "|getGrossoutdown"
				+ measure.getGrossoutdown();

		logs.setFunctionname("checkGrossoutdo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		return msg;
	}

	@Rule(name = "供方净重超差对比判断", ctrlflag = "suttleoutdo", memo = "发货净重超差对比判断", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkSuttleoutdo(Measure measure) {
		ReturnMessage msg = new ReturnMessage();
		if (measure.getSuttleb() > 0) {
			msg = checkOverweight("S", measure.getSuttleoutdo(), measure.getSuttle(), measure.getSuttleb(),
					measure.getSuttleoutup(), measure.getSuttleoutdown());
			msg.setFunctionname("checkSuttleoutdo");
		} else {
			msg.setSuccess(false);
			msg.setMsg("未填写供方净重！");
			msg.setFlag(measure.getSuttleoutdo());
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "suttle：" + measure.getSuttle() + "|suttleoutdo：" + measure.getSuttleoutdo() + "|suttleb:"
				+ measure.getSuttleb() + "|suttleoutdown:" + measure.getSuttleoutdown() + "|getSuttleoutup"
				+ measure.getSuttleoutup();

		logs.setFunctionname("checkSuttleoutdo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		return msg;
	}

	@Rule(name = "皮重和最后一次皮重对比", ctrlflag = "checktaredo", memo = "皮重和最后一次皮重对比", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkTaredo(Measure measure) {
		ReturnMessage msg = new ReturnMessage();
		double diff = 0.0;
		double up = 0.0;

		double tare = measure.getTare();
		double tarelast = measure.getChecktarelast();
		double lasttare = bcDao.queryLasttare(measure.getCarno());
		// if (measure.getTarehour() > 0) {// 皮重长期有效
		try {
			if (measure.getChecktarelast() > 1) {// 大于1时，是重量进行处理
				up = tarelast;
			} else { // 小于1时按照比例进行处理
				up = tare * tarelast;

			}

			diff = Math.abs(measure.getTare() - lasttare); // 计算出两次重量的差值
			if (diff > up) { // 如果差值大于系统配置重量系统提示
				msg.setMsg("皮重与上次皮重对比差值：" + new DecimalFormat("#.###").format(diff * 0.001) + "/t");
				msg.setSuccess(false);
				msg.setFlag(measure.getChecktaredo());// 暂定提示，计量上面皮重不予以计量，卸货后回皮
			}

		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		// }
		FunctionLog logs = new FunctionLog();
		String paraminfo = "tare：" + measure.getTare() + "|tarelast：" + measure.getChecktarelast() + "|lasttare:"
				+ lasttare + "|carno:" + measure.getCarno() + "|operatype.." + measure.getOperatype() + "type..."
				+ measure.getMeasurestate();
		logs.setFunctionname("checkTaredo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkTaredo");
		return msg;
	}

	@Rule(name = "毛重与最后一次皮重对比", ctrlflag = "checktaredo", memo = "毛重与最后一次皮重对比", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkGrossTaredo(Measure measure) {
		ReturnMessage msg = new ReturnMessage();
		double diff = 0.0;
		double gross = measure.getGross();
		double lasttare = bcDao.queryLasttare(measure.getCarno());
		try {
			if (gross > 0) {
				diff = gross - lasttare; // 计算出两次重量的差值
				if (diff < 60) { // 毛重于上次皮重差值小于60，系统提示
					msg.setMsg("毛重与上次皮重对比差值：" + new DecimalFormat("#.###").format(diff * 0.001) + "/t");
					msg.setSuccess(false);
					msg.setFlag(2);// 暂定提示，计量上面皮重不予以计量，卸货后回皮
				}
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "tare：" + measure.getTare() + "|gross：" + measure.getGross() + "|lasttare:" + lasttare
				+ "|carno:" + measure.getCarno() + "|operatype.." + measure.getOperatype() + "type..."
				+ measure.getMeasurestate();
		logs.setFunctionname("checkGrossTaredo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkGrossTaredo");
		return msg;
	}

	@Rule(name = "皮重有效期超期判断（一毛一皮）", ctrlflag = "taretimeoutdo", memo = "皮重有效期超期判断（一毛一皮）", validplace = Rule.ALL_VALID)
	public ReturnMessage checkTaretimetoutdo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();
		if (measure.getTarehour() == 0) {// 一车一皮是皮重有效期有效
			try {
				Long min = timeconpare(measure.getTaretime());
				double mins = (double) min;
				double hour = mins / 60; // 获取相差的小时
				// Long hour = min / 60;
				if (hour > measure.getTaretimeout()) { // 对比配置中的分钟数是否超期
					msg.setMsg("皮重有效期超期");
					msg.setSuccess(false);
					msg.setFlag(measure.getTaretimeoutdo());
				}

			} catch (Exception e) {
				msg.setFlag(-1);
				msg.setSuccess(false);
				msg.setMsg("操作失败");
			}
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "物流号：" + measure.getMatchid() + "taretime：" + measure.getTaretime() + "operatype.."
				+ measure.getOperatype() + "type..." + measure.getMeasurestate();

		logs.setFunctionname("checkTaretimetoutdo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkTaretimetoutdo");
		return msg;
	}

	@Rule(name = "皮重有效期超期判断（皮重长期有效）", ctrlflag = "tarehourdo", memo = "皮重有效期超期判断（皮重长期有效）", validplace = Rule.GETING_INFO)
	public ReturnMessage checkTarehourtimetoutdo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();
		if (measure.getTarehour() > 0) {// 皮重长期有效
			try {
				Long diff = timeconpare(measure.getTaretime());
				// Long hour = diff / 60; // 获取相差的小时
				double mins = (double) diff;
				double hour = mins / 60; // 获取相差的小时
				if (hour > measure.getTarehour()) { // 对比配置中的分钟数是否超期
					msg.setMsg("皮重已超期");
					msg.setSuccess(false);
					msg.setFlag(measure.getTarehourdo());// 暂定提示，计量上面皮重不予以计量，卸货后回皮
				}

			} catch (Exception e) {
				msg.setFlag(-1);
				msg.setSuccess(false);
				msg.setMsg("操作失败");
			}
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "物流号：" + measure.getMatchid() + "taretime：" + measure.getTaretime() + "|operatype.."
				+ measure.getOperatype() + "|type..." + measure.getMeasurestate();

		logs.setFunctionname("checkTarehourtimetoutdo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkTarehourtimetoutdo");
		return msg;
	}

	@Rule(name = "毛重有效期超期判断", ctrlflag = "grosstimeoutdo", memo = "毛重有效期超期判断", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkGrosstimeoutdo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();

		try {
			Long min = timeconpare(measure.getGrosstime());
			// System.out.println("hour........"+min);
			double mins = (double) min;
			double hour = mins / 60; // 获取相差的小时
			if (hour > measure.getGrosstimeout()) {

				msg.setMsg("毛重有效期超期");
				msg.setSuccess(false);
				msg.setFlag(measure.getGrosstimeoutdo());
			}

		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "物流号：" + measure.getMatchid() + "grosstime：" + measure.getGrosstime() + "|operatype.."
				+ measure.getOperatype() + "|type..." + measure.getMeasurestate();
		logs.setFunctionname("checkGrosstimeoutdo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkGrosstimeoutdo");
		return msg;
	}

	@Rule(name = "计划量超差判断", ctrlflag = "checkplanweighdo", memo = "计划量超差判断", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkPlanweightdo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();
		double planweight = 0.0;
		double overweight = 0.0;
		double planweightkg = 0.0;
		double realweight = 0.0;
		double rate = 0.0;
		planweightkg = measure.getPlanweight() * 1000;
		try {
			// Measure m = bcDao.queryRealsuttle(measure);
			// realweight = m.getSuttle() + measure.getSuttle(); //
			// 实际执行的重量，包含当前的车的重量
			realweight = measure.getSuttle();
			if (measure.getPlanweighout() >= 1) { // 值小于1是按照比例，大于1是按照重量
				planweight = planweightkg + planweightkg * measure.getPlanweighout(); // 计算允许执行的计划量

			} else {
				planweight = planweightkg + measure.getPlanweighout();

			}
			if (realweight > planweight) { //
				DecimalFormat df = new DecimalFormat("#.###");
				overweight = realweight - planweightkg; // 计算超差计划量
				rate = Double.parseDouble(df.format(overweight / realweight));
				msg.setMsg(
						"已经执行的重量超过计划量,已超:" + df.format(overweight * 0.001) + "/t;超差率：" + df.format(rate * 1000) + " ‰");
				msg.setSuccess(false);
				msg.setFlag(measure.getCheckplanweighdo());
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "realweight：" + measure.getSuttle() + "|planweight：" + measure.getPlanweight();

		logs.setFunctionname("checkPlanweightdo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkPlanweightdo");
		return msg;
	}

	@Rule(name = "计划车数超差对比", ctrlflag = "checkplancarcountdo", memo = "计划车数超差对比", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkPlanCarcountdo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();
		int plancarcount = 0;
		int overcarcount = 0;
		int realcarcount = 0;

		try {
			Measure m = bcDao.queryRealcarcount(measure);
			realcarcount = m.getPlancarcount() + 1; // 计划车数加上当前一车，计算出实际执行车数
			plancarcount = measure.getPlancarcount() + measure.getPlancarcountout(); // 计算出允许的计划车数
			if (realcarcount > plancarcount) {
				overcarcount = realcarcount - plancarcount; // 计算出超差车数
				msg.setMsg("已经执行的车数超过计划车数，已超：" + overcarcount + "/车");
				msg.setSuccess(false);
				msg.setFlag(measure.getCheckplancarcountdo());
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}

		msg.setFunctionname("checkPlanCarcountdo");
		return msg;
	}

	@Rule(name = "计划支数超差对比", ctrlflag = "checkplanmaterialcountdo", memo = "计划支数超差对比", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage checkPlanMaterilcountdo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();
		int planmaterialcount = 0;
		int overmaterialcount = 0;
		int realmaterialcount = 0;
		try {
			// Measure m = bcDao.queryRealmaterialcount(measure);
			// realmaterialcount = m.getMaterialcount() +
			// measure.getMaterialcount(); // 加上当前的支数，计算已经执行的支数

			// planmaterialcount = measure.getPlanmaterialcount() +
			// measure.getPlanmaterialcountout(); // 计算允许执行的支数
			realmaterialcount = measure.getMaterialcount();
			planmaterialcount = measure.getMaterialcount();

			if (realmaterialcount > planmaterialcount) { // 如果实际支数比计划支数数量大
				overmaterialcount = realmaterialcount - planmaterialcount;// 计算差值用作提示显示
				msg.setMsg("已经执行的支数超过计划支数,已超：" + overmaterialcount + "/支");
				msg.setSuccess(false);
				msg.setFlag(measure.getCheckplanmaterialcountdo());

			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "realmaterialcount：" + measure.getMaterialcount();

		logs.setFunctionname("checkPlanMaterilcountdo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkPlanMaterilcountdo");
		return msg;
	}

	// @Rule(name = "出入库判断", ctrlflag = "Notstoreindo,Notstoreoutdo", memo =
	// "出入库判断", validplace = Rule.ALL_VALID)
	public ReturnMessage RGcheckSflag(Measure measure) {

		ReturnMessage msg = new ReturnMessage();

		try {
			if (measure.getSflag() == 1) { // 出库
				if (measure.getSourcetime() == null || "".equals(measure.getSourcetime())) {
					msg.setSuccess(false);
					msg.setFlag(measure.getNotstoreoutdo());
					msg.setMsg("未进行出库操作");
				}

			} else if (measure.getSflag() == 2) { // 入库
				if (measure.getTargettime() == null || "".equals(measure.getTargettime())) {
					msg.setSuccess(false);
					msg.setFlag(measure.getNotstoreindo());
					msg.setMsg("未进行入库操作");
				}
			} else if (measure.getSflag() == 3) { // 出入库
				int inflag = 0;
				int outflag = 0;
				if (measure.getSourcetime() == null || "".equals(measure.getSourcetime())) {// 没有入库时间
					outflag = measure.getNotstoreoutdo();
				}
				if (measure.getTargettime() == null || "".equals(measure.getTargettime())) { // 没有收货时间
					inflag = measure.getNotstoreindo();
				}
				if (inflag > 0 && outflag > 0) {
					if (outflag > inflag) { // 出入库都检查时，那个检查等级高根据那个提示
						msg.setFlag(measure.getNotstoreoutdo());

					} else {
						msg.setFlag(measure.getNotstoreindo());
					}
					msg.setSuccess(false);
					msg.setMsg("未进行出入库操作");

				} else if (inflag == 0 && outflag > 0) {
					msg.setSuccess(false);
					msg.setMsg("未进行出库操作");
					msg.setFlag(measure.getNotstoreoutdo());
				} else if (inflag > 0 && outflag == 0) {
					msg.setSuccess(false);
					msg.setMsg("未进行入库操作");
					msg.setFlag(measure.getNotstoreindo());
				}

			}

		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		msg.setFunctionname("checkSflag");
		return msg;
	}

	// @Rule(name = "进出厂判断", ctrlflag = "notentergatedo,notleavegatedo", memo =
	// "进出厂判断", validplace = Rule.ALL_VALID)
	public ReturnMessage checkRGGflag(Measure measure) {

		ReturnMessage msg = new ReturnMessage();

		msg.setSuccess(true);
		try {
			if (measure.getGflag() == 1) { // 进厂
				if (measure.getEntertime() == null || "".equals(measure.getEntertime())) {// 没有进厂时间
					msg.setSuccess(false);
					msg.setFlag(measure.getNotentergatedo());
					msg.setMsg("未进行进厂操作");

				}
			} else if (measure.getGflag() == 2) { // 出厂
				if (measure.getLeavetime() == null || "".equals(measure.getLeavetime())) {// 没有出厂时间

					msg.setSuccess(false);
					msg.setFlag(measure.getNotleavegatedo());
					msg.setMsg("未进行行出厂操作");

				}
			} else if (measure.getGflag() == 3) { // 进出厂
				int inflag = 0;
				int outflag = 0;
				if (measure.getEntertime() == null || "".equals(measure.getEntertime())) {// 没有进厂时间
					inflag = measure.getNotentergatedo();
				}
				if (measure.getLeavetime() == null || "".equals(measure.getLeavetime())) { // 没有出厂时间
					outflag = measure.getNotleavegatedo();
				}
				if (inflag > 0 && outflag > 0) {
					if (inflag > outflag) { // 进出厂都检查时，那个检查等级高根据那个提示
						msg.setFlag(measure.getNotentergatedo());
					} else {
						msg.setFlag(measure.getNotleavegatedo());
					}

					msg.setSuccess(false);
					msg.setMsg("未进行进出厂操作");
				} else if (inflag == 0 && outflag > 0) {

					msg.setSuccess(false);
					msg.setFlag(measure.getNotentergatedo());
					msg.setMsg("未进行进厂操作");
				} else if (inflag > 0 && outflag == 0) {

					msg.setSuccess(false);
					msg.setFlag(measure.getNotleavegatedo());
					msg.setMsg("未进行出厂操作");

				}
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		msg.setFunctionname("checkGflag");
		return msg;
	}

	@Rule(name = "进厂判断", ctrlflag = "notentergatedo", memo = "进厂判断", validplace = Rule.ALL_VALID)
	public ReturnMessage checkGflag(Measure measure) {

		ReturnMessage msg = new ReturnMessage();

		msg.setSuccess(true);
		try {
			int flag = bcDao.queryNode(measure.getMatchid(), "IN");
			if (flag > 0) {
				if (measure.getEntertime() == null || "".equals(measure.getEntertime())) {// 没有进厂时间
					msg.setSuccess(false);
					msg.setFlag(measure.getNotentergatedo());
					msg.setMsg("未进行进厂操作");
				}
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		msg.setFunctionname("checkINGflag");
		return msg;
	}

	@Rule(name = "出库判断", ctrlflag = "storeoutdo", memo = "出库判断", validplace = Rule.ALL_VALID)
	public ReturnMessage checkOSflag(Measure measure) {

		ReturnMessage msg = new ReturnMessage();

		try {
			int flag = bcDao.queryNode(measure.getMatchid(), "SOUT");
			if (flag > 0) {
				if (measure.getSourcetime() == null || "".equals(measure.getSourcetime())) {
					msg.setSuccess(false);
					msg.setFlag(measure.getNotstoreoutdo());
					msg.setMsg("未进行出库操作");
				}
			}

		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		msg.setFunctionname("checkOSflag");
		return msg;
	}

	@Rule(name = "入库判断", ctrlflag = "notstoreindo", memo = "入库判断", validplace = Rule.ALL_VALID)
	public ReturnMessage checkSflag(Measure measure) {
		ReturnMessage msg = new ReturnMessage();
		try {
			int flag = bcDao.queryNode(measure.getMatchid(), "SIN");
			if (flag > 0) {
				if (measure.getTargettime() == null || "".equals(measure.getTargettime())) {
					msg.setSuccess(false);
					msg.setFlag(measure.getNotstoreindo());
					msg.setMsg("未进行入库操作");
				}
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		msg.setFunctionname("checkSflag");
		return msg;
	}

	@Rule(name = "取样判断", ctrlflag = "notslampedo", memo = "取样判断", validplace = Rule.ALL_VALID)
	public ReturnMessage checkQflag(Measure measure) {
		ReturnMessage msg = new ReturnMessage();
		try {
			Msameweight ms = bcDao.queryLastnode(measure.getMatchid(), measure.getMeasurestate());
			if (ms != null) {
				// System.out.println(".........."+ms.getNodecode().indexOf("SP"));
				if (ms.getNodecode().indexOf("SP") != -1) {
					if ("".equals(measure.getSampletime()) || null == measure.getSampletime()) {
						msg.setSuccess(false);
						msg.setFlag(measure.getNotslampedo());
						msg.setMsg("未进行取样操作");
					}
				}
			}
			FunctionLog logs = new FunctionLog();
			String paraminfo = "物流号：" + measure.getMatchid() + "计量状态：" + measure.getMeasurestate() + "包含环节："
					+ ms.getNodecode();
			logs.setFunctionname("checkQflag");
			logs.setFlag(Integer.toString(msg.getFlag()));
			logs.setParaminfo(paraminfo);
			logs.setMsg(msg.getMsg());
			// bcDao.insertfunctionlog(logs);
			logs = null;
		} catch (Exception e) {
			msg.setFlag(-1);
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		msg.setFunctionname("checkQflag");
		return msg;
	}

	// @Rule(name = "取样判断", ctrlflag = "notslampedo", memo = "取样判断", validplace
	// = Rule.ALL_VALID)
	public ReturnMessage RGcheckQflag(Measure measure) {

		ReturnMessage msg = new ReturnMessage();

		try {
			if ("".equals(measure.getSampletime()) || null == measure.getSampletime()) {
				msg.setSuccess(false);
				msg.setFlag(measure.getNotslampedo());
				msg.setMsg("未进行取样操作");
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		msg.setFunctionname("checkQflag");
		return msg;
	}

	@Rule(name = "毛重业务信息检查处理", ctrlflag = "isgrossmaterial,isgrosstaskcode,isgrossplanid,isgrossbasket,isgrosssource,isgrosstarget,isgrossship", memo = "毛重业务信息检查处理", validplace = Rule.ALL_VALID)
	public ReturnMessage checkGrossinfodo(Measure measure) {

		ReturnMessage msg = checkOpernfodo("G", measure);
		msg.setFunctionname("checkGrossinfodo");

		return msg;
	}

	@Rule(name = "皮重业务信息验证", ctrlflag = "istarematerial,istaretaskcode,istareplanid,istarebasket,istaresource,istaretarget,istareship", memo = "皮重业务信息验证", validplace = Rule.ALL_VALID)
	public ReturnMessage checkTareinfodo(Measure measure) {
		ReturnMessage msg = checkOpernfodo("T", measure);
		msg.setFunctionname("checkTareinfodo");
		return msg;
	}

	@Rule(name = "净重业务信息检查处理", ctrlflag = "issuttleship,issuttletarget,issuttlesource,issuttlebasket,issuttleplanid,issuttletaskcode,issuttlematerial", memo = "净重业务信息检查处理", validplace = Rule.ALL_VALID)
	public ReturnMessage checkSuttleinfodo(Measure measure) {
		ReturnMessage msg = checkOpernfodo("S", measure);
		msg.setFunctionname("checkSuttleinfodo");
		return msg;
	}

	@Rule(name = "重复计量毛重处理", ctrlflag = "regrossdo", memo = "重复计量毛重处理", validplace = Rule.GETING_INFO)
	public ReturnMessage checkRegrossdo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();

		try {
			if (4 == measure.getMflag()) {// 如果是毛毛皮，
				if (measure.getRgross() > 0) {
					msg.setMsg("已经计量毛重，上次毛重：" + new DecimalFormat("#.###").format(measure.getRgross() * 0.001)
							+ "/t，上次时间：" + measure.getRgrosstime());
					msg.setSuccess(false);
					msg.setFlag(measure.getRegrossdo());
				} else if (measure.getGrossb() > 0) {
					msg.setMsg("已经计量毛重，上次毛重：" + new DecimalFormat("#.###").format(measure.getGrossb() * 0.001)
							+ "/t，上次时间：" + measure.getGrosstimeb());
					msg.setSuccess(false);
					msg.setFlag(measure.getRegrossdo());
				}
			} else {
				if (measure.getRgross() > 0) {
					msg.setMsg("已经计量毛重，上次毛重：" + new DecimalFormat("#.###").format(measure.getRgross() * 0.001)
							+ "/t，上次时间：" + measure.getRgrosstime());
					msg.setSuccess(false);
					msg.setFlag(measure.getRegrossdo());
				}
			}

		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "rgross：" + measure.getRgross();

		logs.setFunctionname("checkRegrossdo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkRegrossdo");
		return msg;
	}

	@Rule(name = "重复计量皮重处理", ctrlflag = "retaredo", memo = "重复计量皮重处理", validplace = Rule.GETING_INFO)
	public ReturnMessage checkRetaredo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();
		try {
			if (measure.getRtare() > 0) {
				msg.setMsg("已经计量皮重,上次皮重：" + new DecimalFormat("#.###").format(measure.getRtare() * 0.001) + "/t，上次时间："
						+ measure.getRtaretime());
				msg.setSuccess(false);
				msg.setFlag(measure.getRetaredo());
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "rtare：" + measure.getRtare();

		logs.setFunctionname("checkRetaredo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkRetaredo");
		return msg;
	}

	@Rule(name = "重复计量净重处理", ctrlflag = "resuttledo", memo = "重复计量净重处理", validplace = Rule.GETING_INFO)
	public ReturnMessage checkResuttledo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();
		try {
			if (measure.getRgross() > 0 && measure.getRtare() > 0) {
				msg.setMsg("已经计量净重");
				msg.setSuccess(false);
				msg.setFlag(measure.getResuttledo());
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "rgross：" + measure.getRgross() + "|Rtare：" + measure.getRtare();

		logs.setFunctionname("checkResuttledo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkResuttledo");
		return msg;
	}

	@Rule(name = "进厂到计毛时间判断", ctrlflag = "checkoperatimedo", memo = "进厂到计毛时间判断", validplace = Rule.ALL_VALID)
	public ReturnMessage checkOperatimedo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();

		try {
			// System.out.println("1......."+("".equals(measure.getEntertime()))+"2...."+(null==measure.getEntertime()));
			if (("".equals(measure.getEntertime())) || (null == measure.getEntertime())) {
				msg.setMsg("请确认是否进行进厂操作");
				msg.setSuccess(false);
				msg.setFlag(measure.getCheckoperatimedo());
			} else {
				Long min = timeconpare(measure.getEntertime());
				if (min > measure.getCheckoperatime()) {
					msg.setMsg("进厂到计量毛重超过规定时间，已超：" + (min - measure.getCheckoperatime()) + "/分钟");
					msg.setSuccess(false);
					msg.setFlag(measure.getCheckoperatimedo());
				}
			}

		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "Entertime：" + measure.getEntertime();
		logs.setFunctionname("checkOperatimedo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkOperatimedo");
		return msg;
	}

	/*
	 * @Rule(name = "皮重相似重量查询", ctrlflag = "sameweightdo", memo = "皮重相似重量查询",
	 * validplace = Rule.BEFORE_SAVE)
	 */public ReturnMessage queryTareSameweightInfo(Measure measure) {

		ReturnMessage msg = new ReturnMessage();
		Measure mc = new Measure();

		try {

			mc.setWeighttype("tare");
			mc.setWeightdown(measure.getSametareoutdown());
			mc.setWeightup(measure.getSametareoutup());
			mc.setWeighttime(measure.getSametaretime());
			mc.setTimetype("taretime");
			mc.setWeight(measure.getTare());
			mc.setMaterialname(measure.getMaterialname());

			/*
			 * 如果不需要按照车号和业务类型查询在此处把业务类型中的值清空，如果需要则某一个查询屏蔽下面两行代码即可
			 */

			mc.setCarno("");
			mc.setOperatype("");
			List<Measure> list = bcDao.querySameweightInfo(mc);
			msg.setCount(list.size());
			msg.setList(list);
			if (list.size() > 0) {
				msg.setMsg("该车存在 " + list.size() + " 车皮重相似重量");
				msg.setSuccess(false);
				msg.setFlag(measure.getSameweightdo());
			}

		} catch (Exception e) {
			msg.setMsg("操作失败");
			msg.setFlag(-1);
			msg.setSuccess(false);
		}

		msg.setFunctionname("queryTareSameweightInfo");
		return msg;
	}

	@Rule(name = "相似重量查询", ctrlflag = "sameweightdo", memo = "相似重量查询", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage queryGrossSameweightInfo(Measure measure) {
		ReturnMessage msg = new ReturnMessage();

		/* Measure mc = new Measure(); */

		try {
			/*
			 * mc.setWeighttype("gross");
			 * mc.setWeightdown(measure.getSamegrossoutdown());
			 * mc.setWeightup(measure.getSamegrossoutup());
			 * mc.setWeighttime(measure.getSamegrosstime());
			 * mc.setTimetype("grosstime"); mc.setWeight(measure.getGross());
			 * mc.setMaterialname(measure.getMaterialname());
			 */
			/*
			 * 如果不需要按照车号和业务类型查询在此处把业务类型中的值清空，如果需要则某一个查询屏蔽下面两行代码即可
			 */

			/*
			 * mc.setCarno(""); mc.setOperatype(measure.getOperatype());
			 */
			List<Measure> list = bcDao.querySameweightInfo(measure);
			msg.setCount(list.size());
			msg.setList(list);
			if (list.size() > 0) {
				msg.setMsg("该车存在 " + list.size() + " 车相似重量");
				msg.setSuccess(false);
				msg.setFlag(measure.getSameweightdo());
			}

		} catch (Exception e) {
			msg.setMsg("操作失败");
			msg.setFlag(-1);
			msg.setSuccess(false);
		}

		FunctionLog logs = new FunctionLog();
		String paraminfo = "tare：" + measure.getTare() + "|sametareoutup：" + measure.getSametareoutup()
				+ "|sametareoutdown:" + measure.getSametareoutdown() + "|gross:" + measure.getGross()
				+ "|samegrossoutup:" + measure.getSamegrossoutup() + "|samegrossoutdown:"
				+ measure.getSamegrossoutdown() + "|samegrosstime" + measure.getSamegrosstime() + "|sametaretime"
				+ measure.getSametaretime();

		logs.setFunctionname("queryGrossSameweightInfo");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo + "相似集合条数：。。。" + msg.getCount());
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		msg.setFunctionname("queryGrossSameweightInfo");
		return msg;
	}

	// 毛皮净重业务信息验证方法
	public ReturnMessage checkOpernfodo(String mtype, Measure measure) {
		ReturnMessage msg = new ReturnMessage();
		String info = "";
		int flag = 0;
		int xflag = 0;
		int ismaterial = 0;
		int istaskcode = 0;
		int isplanid = 0;
		int isbasket = 0;
		int issource = 0;
		int istarget = 0;
		int isship = 0;
		if ("G".equals(mtype)) {
			ismaterial = measure.getIsgrossmaterial();
			istaskcode = measure.getIsgrosstaskcode();
			isplanid = measure.getIsgrossplanid();
			isbasket = measure.getIsgrossbasket();
			issource = measure.getIsgrosssource();
			istarget = measure.getIsgrosstarget();
			isship = measure.getIsgrossship();
		} else if ("T".equals(mtype)) {
			ismaterial = measure.getIstarematerial();
			istaskcode = measure.getIstaretaskcode();
			isplanid = measure.getIstareplanid();
			isbasket = measure.getIstarebasket();
			issource = measure.getIstaresource();
			istarget = measure.getIstaretarget();
			isship = measure.getIstareship();

		} else if ("S".equals(mtype)) {
			ismaterial = measure.getIssuttlematerial();
			istaskcode = measure.getIssuttletaskcode();
			isplanid = measure.getIssuttleplanid();
			isbasket = measure.getIssuttlebasket();
			issource = measure.getIssuttlesource();
			istarget = measure.getIssuttletarget();
			isship = measure.getIssuttleship();

		}

		try { // 查询大于0，以下标记为0时，系统配置是不验证，为1是必须填写，为2时选择
			if (ismaterial > 0) {
				if ("".equals(measure.getMaterialcode()) || measure.getMaterialcode() == null) {
					info = info + "  物料信息为空";
					if (ismaterial == 1) {
						flag = flag + 1;
					} else if (ismaterial == 2) {
						xflag = xflag + 1;
					}
				}

			}
			if (istaskcode > 0) {
				if ("".equals(measure.getTaskcode()) || measure.getTaskcode() == null) {
					info = info + "  业务号信息为空";
					if (istaskcode == 1) {
						flag = flag + 1;
					} else if (istaskcode == 2) {
						xflag = xflag + 1;
					}
				}

			}

			if (isplanid > 0) {
				if ("".equals(measure.getPlanid()) || measure.getPlanid() == null) {
					info = info + " 计划号信息为空";
					if (isplanid == 1) {
						flag = flag + 1;
					} else if (isplanid == 2) {
						xflag = xflag + 1;
					}
				}

			}
			if (isbasket > 0) {
				if ("".equals(measure.getBasket()) || measure.getBasket() == null) {
					info = info + " 料蓝信息为空";
					if (isbasket == 1) {
						flag = flag + 1;
					} else if (isbasket == 2) {
						xflag = xflag + 1;
					}
				}

			}
			if (issource > 0) {
				if ("".equals(measure.getSourcecode()) || measure.getSourcecode() == null) {

					info = info + " 供货信息为空";
					if (issource == 1) {
						flag = flag + 1;
					} else if (issource == 2) {
						xflag = xflag + 1;
					}
				}

			}
			if (istarget > 0) {
				if ("".equals(measure.getTargetcode()) || measure.getTargetcode() == null) {
					info = info + " 收货信息为空";
					if (istarget == 1) {
						flag = flag + 1;
					} else if (istarget == 2) {
						xflag = xflag + 1;
					}
				}

			}
			if (isship > 0) {
				if ("".equals(measure.getShip()) || measure.getShip() == null) {

					info = info + " 船舶信息为空";
					if (isship == 1) {
						flag = flag + 1;
					} else if (isship == 2) {
						xflag = xflag + 1;
					}
				}

			}

			if (flag > 0) { // flag大于0表示必须填写的有字段为空，程序禁止计量
				msg.setFlag(3);
				msg.setSuccess(false);
				msg.setMsg(info);
			} else {
				if (xflag > 0) { // xflag大于0表示需要填写的有字段为空，程序提示是否计量
					msg.setFlag(2);
					msg.setSuccess(false);
					msg.setMsg(info);
				}

			}

		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");

		}
		return msg;
	}

	@Rule(name = "过磅衡器验证", ctrlflag = "", memo = "过磅衡器验证", validplace = Rule.GETING_INFO)
	public ReturnMessage queryMeasureweigh(Measure measure) {
		FunctionLog logs = new FunctionLog();
		ReturnMessage msg = new ReturnMessage();
		Map<String, String> map = new HashMap<String, String>();
		String paraminfo = "";
		try {
			/*
			 * if
			 * ("G".equals(measure.getMtypes())||("计毛".equals(measure.getMtypes(
			 * )))||("回毛".equals(measure.getMtypes()))) { map.put("mtype","G");
			 * } else if ("T".equals(measure.getMtypes())) { map.put("mtype",
			 * "T"); }
			 */
			map.put("mtype", measure.getMeasurestate());
			map.put("weighid", measure.getWeightno());
			map.put("operatype", measure.getOperatype());
			map.put("materialcode", measure.getMaterialcode());

			paraminfo = "未进入判断前mtype()：" + map.get("mtype") + "|weighid：" + map.get("weighid") + "|operatype:"
					+ map.get("operatype") + "|materialcode:" + map.get("materialcode") + "|mtype" + map.get("mtype");
			int flag = bcDao.queryMeasureweigh(map);

			if (flag == 0) { // 但是物料里面没有与传递的业务物料一致时，系统认为该业务不允许在此衡器过磅
				msg.setMsg("是否允许该业务在此衡器上进行计量");
				msg.setSuccess(false);
				msg.setFlag(2);
			}

		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		msg.setFunctionname("queryMeasureweigh");

		logs.setFunctionname("queryrange");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		return msg;
	}

	@Rule(name = "衡器量程验证", ctrlflag = "", memo = "衡器量程验证", validplace = Rule.BEFORE_SAVE)
	public ReturnMessage queryrange(Measure measure) {
		FunctionLog logs = new FunctionLog();
		ReturnMessage msg = new ReturnMessage();
		double range = 0.0;
		String paraminfo = "未进入判断前getMeasurestate()：" + measure.getMeasurestate() + "|tare：" + measure.getTare()
				+ "|gross:" + measure.getGross() + "|range:" + range + "|衡器毛重id。。" + measure.getGrossweighid()
				+ "|衡器皮重id。。" + measure.getTareweighid();
		try {
			Equipment e = new Equipment();

			if ("G".equals(measure.getMeasurestate())) {
				paraminfo = "毛未查询量程判断前getMeasurestate()：" + measure.getMeasurestate() + "|tare：" + measure.getTare()
						+ "|gross:" + measure.getGross() + "|range:" + range + "|衡器id。。。。" + measure.getGrossweighid();

				e.setEqucode(measure.getGrossweighid());
				Equipment ep = bcDao.queryrange(e);
				paraminfo = "毛查询量程判断后getMeasurestate()：" + measure.getMeasurestate() + "|tare：" + measure.getTare()
						+ "|gross:" + measure.getGross() + "|range:" + range + "|衡器id。。。。" + e.getEqucode();

				if (ep != null) {
					range = ep.getRange();
					paraminfo = "查询量程不为空getMeasurestate()：" + measure.getMeasurestate() + "|tare：" + measure.getTare()
							+ "|gross:" + measure.getGross() + "|range:" + range + "ep.getRange()..." + ep.getRange()
							+ "|衡器id。。。。" + e.getEqucode();

					if (measure.getGross() > ep.getRange()) {

						paraminfo = "毛重大于量程getMeasurestate()：" + measure.getMeasurestate() + "|tare："
								+ measure.getTare() + "|gross:" + measure.getGross() + "|range:" + range
								+ "ep.getRange()..." + ep.getRange() + "|衡器id。。。。" + e.getEqucode();

						msg.setMsg("当前重量大于最大量程,最大量程/t：" + ep.getRange());
						msg.setSuccess(false);
						msg.setFlag(2);
					}
				}

			} else if ("T".equals(measure.getMeasurestate())) {
				e.setEqucode(measure.getTareweighid());
				paraminfo = "皮未查询量程判断前getMeasurestate()：" + measure.getMeasurestate() + "|tare：" + measure.getTare()
						+ "|gross:" + measure.getGross() + "|range:" + range + "|衡器id。。。。" + measure.getTareweighid();
				Equipment ep = bcDao.queryrange(e);

				paraminfo = "皮查询量程判断后getMeasurestate()：" + measure.getMeasurestate() + "|tare：" + measure.getTare()
						+ "|gross:" + measure.getGross() + "|range:" + range + "|衡器id。。。。" + e.getEqucode();

				if (ep != null) {
					range = ep.getRange();

					paraminfo = "查询量程不为空getMeasurestate()：" + measure.getMeasurestate() + "|tare：" + measure.getTare()
							+ "|gross:" + measure.getGross() + "|range:" + range + "ep.getRange()..." + ep.getRange()
							+ "|衡器id。。。。" + e.getEqucode();

					if (measure.getTare() > ep.getRange()) {

						paraminfo = "皮重大于量程getMeasurestate()：" + measure.getMeasurestate() + "|tare："
								+ measure.getTare() + "|gross:" + measure.getGross() + "|range:" + range
								+ "ep.getRange()..." + ep.getRange() + "|衡器id。。。。" + e.getEqucode();

						msg.setMsg("当前重量大于最大量程,最大量程/kg：" + ep.getRange());
						msg.setSuccess(false);
						msg.setFlag(2);
					}
				}

			}

		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		msg.setFunctionname("queryrange");

		logs.setFunctionname("queryrange");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		// bcDao.insertfunctionlog(logs);
		return msg;
	}

	@Rule(name = "Rfid验证", ctrlflag = "", memo = "Rfid验证", validplace = Rule.GETING_INFO)
	public ReturnMessage checkRfid(Measure measure) {
		ReturnMessage msg = new ReturnMessage();
		int flag = 0;
		String flags = "";
		String weigh = "";
		try {
			if(!"-1".equals(measure.getRfidid())){
				flags = bcDao.queryInfoBymateiracode(measure.getMaterialcode(), "2");
				if ("1".equals(flags)) {// 标记为1时，需要验证rfid是否正确
					if (StringUtils.isEmpty(measure.getRfidid())) {// 通过车号获取的rfid为空时系统提示
						msg.setMsg("此车未发标签,是否过磅？");
						msg.setSuccess(false);
						msg.setFlag(2);
					} else {
						int containflag = 0;
						String[] rfid = measure.getRfid().split(",");
						for (int i = 0; i < rfid.length; i++) {
							if (measure.getRfidid().equals(rfid[i])) { // 查询的rfid卡号与现场获取的rfid卡号是否一致，不一致时值加一
								containflag++;
							} else {
								flag++;
							}
						}
						if (containflag == 0 && flag > 0) {
							msg.setMsg("车辆标签与系统标签不一致，请确认是否过磅？");
							msg.setSuccess(false);
							msg.setFlag(2);
						}
					}
				}
			}
		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		FunctionLog logs = new FunctionLog();
		String paraminfo = "物料编码：" + measure.getMaterialcode() + "是否判断rfid标记：" + flags + "checkRfid："
				+ measure.getFlag() + " rfid卡号" + measure.getRfid() + " 系统获取rfidid卡号：" + measure.getRfidid() + " 车号："
				+ measure.getCarno() + " 物流号：" + measure.getMatchid() + "计量状态：" + measure.getMeasurestate() + "毛重衡器："
				+ measure.getGrossweigh() + "皮重衡器：" + measure.getTareweigh();
		if ("G".equals(measure.getMeasuretype())) {
			weigh = measure.getGrossweigh();
		} else {
			weigh = measure.getTareweigh();
		}
		logs.setFunctionname("checkRfid");
		logs.setFlag(Integer.toString(msg.getFlag()));
		logs.setParaminfo(paraminfo);
		logs.setMsg(msg.getMsg());
		logs.setSuccess(weigh);
		logs.setFlag(String.valueOf(flag));
		bcDao.insertfunctionlog(logs);
		msg.setFunctionname("checkRfid");
		logs = null;
		return msg;
	}

	/**
	 * 
	 * @param mtype
	 *            计量方式 ：G 计毛 ；T 计皮 ；S 出净
	 * @param dotype
	 *            处理方式
	 * @param weight
	 *            过磅重量
	 * @param sweight
	 *            供方重量
	 * @param up
	 *            上限
	 * @param down
	 *            下限
	 * @return
	 */
	public ReturnMessage checkOverweight(String mtype, int dotype, double weight, double sweight, double up,
			double down) {
		ReturnMessage msg = new ReturnMessage();
		double upweight = 0.0;
		double downweight = 0.0;
		// double overweight = 0.0;
		double showoverweight = 0.0;
		double rate = 0.0;
		DecimalFormat dec = new DecimalFormat("#.###");
		// DecimalFormat df = new DecimalFormat("#.###");

		try {
			if (up < 1) { // 范围小于1，按照比例计算
				upweight = sweight + sweight * up;
				downweight = sweight - sweight * down;
			} else {
				upweight = sweight + up;
				downweight = sweight - down;
			}
			if (weight < downweight || weight > upweight) {
				/*
				 * if (weight < downweight) { overweight = weight - downweight;
				 * } else if (weight > upweight) { overweight = weight -
				 * upweight; }
				 */
				showoverweight = weight - sweight;
				if ("G".equals(mtype)) {
					rate = Double.parseDouble(dec.format(Math.abs(showoverweight) / weight));
					msg.setMsg("毛重超出浮动范围，已超:" + dec.format(showoverweight * 0.001) + "/t;称差率：" + dec.format(rate * 1000)
							+ " ‰");
					// msg.setMsg("毛重超出浮动范围，已超:" + dec.format(showoverweight *
					// 0.001) + "/t");
					msg.setSuccess(false);
					msg.setFlag(dotype);
				} else if ("T".equals(mtype)) {
					msg.setMsg("皮重超出浮动范围，已超:" + dec.format(showoverweight * 0.001) + "/t");
					msg.setSuccess(false);
					msg.setFlag(dotype);
				} else if ("S".equals(mtype)) {

					/*
					 * rate = Double.parseDouble(df.format(Math.abs(overweight)
					 * / weight));
					 * 
					 * if (rate > 0.003) {
					 */
					rate = Double.parseDouble(dec.format(Math.abs(showoverweight) / weight));
					msg.setMsg("净重超出浮动范围，已超:" + dec.format(showoverweight * 0.001) + "/t;称差率：" + dec.format(rate * 1000)
							+ " ‰");
					msg.setSuccess(false);
					msg.setFlag(dotype);
					// }
				}

			} else {
				msg.setSuccess(true);
			}

		} catch (Exception e) {
			msg.setFlag(-1);
			msg.setSuccess(false);
			msg.setMsg("操作失败");
		}
		return msg;
	}
	// 时间与当前时间对比

	public Long timeconpare(String time) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currtime = new Date();
		long diff = 0;
		long min = 0;
		try {
			Date dtime1 = formatter.parse(time);
			diff = Math.abs(dtime1.getTime() - currtime.getTime()) / 1000;
			min = diff / 60;
		} catch (ParseException e) {
			// System.out.println("time3....." + e);
		}

		return min;
	}
}
