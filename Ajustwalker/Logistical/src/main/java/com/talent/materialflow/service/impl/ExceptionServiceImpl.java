package com.talent.materialflow.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.talent.core.model.Message;
import com.talent.core.mybatis.pagehelper.StringUtil;
import com.talent.core.privilege.model.User;
import com.talent.materialflow.model.Applicationbill;
import com.talent.materialflow.model.BCommon;
import com.talent.materialflow.model.Bcard;
import com.talent.materialflow.model.Currtemp;
import com.talent.materialflow.model.Entrylog;
import com.talent.materialflow.model.Measure;
import com.talent.materialflow.model.Workline;
import com.talent.materialflow.model.WorklineItem;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.ExceptionService;
import com.talent.materialflow.service.mapper.AdjustworklineMapper;
import com.talent.materialflow.service.mapper.ApplicationbillMapper;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.CardMapper;
import com.talent.materialflow.service.mapper.EntrylogMapper;
import com.talent.materialflow.service.mapper.ExceptionMapper;

@Service
@Transactional
public class ExceptionServiceImpl implements ExceptionService {
	@Resource
	private ExceptionMapper exceptionMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private ApplicationbillMapper appMapper;
	@Resource
	private EntrylogMapper entryMapper;
	@Resource
	private CardMapper cardApp;

	@Resource
	private BCommonMapper bcommonMapper;
	@Resource
	private AdjustworklineMapper adjustMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.talent.materialflow.service.impl.ExceptionService#
	 * insertMeasureException(com.talent.materialflow.model.Measure)
	 */
	@Override
	public Message insertMeasureException(Measure measure) throws DataAccessException {
		Message msg = new Message();
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("v_matchid", measure.getMatchid());
		parameterMap.put("v_gross", String.valueOf(measure.getGross() * 1000));
		parameterMap.put("v_grosstime", measure.getGrosstime());
		parameterMap.put("v_grossweighid", measure.getGrossweighid());
		parameterMap.put("v_grossweigh", measure.getGrossweigh());
		parameterMap.put("v_grossoperacode", measure.getGrossoperacode());
		parameterMap.put("v_grossoperaname", measure.getGrossoperaname());
		parameterMap.put("v_tare", String.valueOf(measure.getTare() * 1000));
		parameterMap.put("v_taretime", measure.getTaretime());
		parameterMap.put("v_tareweighid", measure.getTareweighid());
		parameterMap.put("v_tareweigh", measure.getTareweigh());
		parameterMap.put("v_tareoperacode", measure.getTareoperacode());
		parameterMap.put("v_tareoperaname", measure.getTareoperaname());
		parameterMap.put("v_usermemo", measure.getUsermemo());
		parameterMap.put("v_createman", measure.getCreator());
		parameterMap.put("v_flag", "0");
		parameterMap.put("v_msg", "");
		exceptionMapper.insertMeasureException(parameterMap);
		String flag = parameterMap.get("v_flag");
		if ("0".equals(flag)) {
			msg.setSuccess(false);
		} else {
			msg.setSuccess(true);
		}
		msg.setMsg(parameterMap.get("outmsg"));
		return msg;
	}

	/*
	 * 添加出厂信息
	 * 
	 */
	public Message insertExceptionOut(Entrylog entry) throws DataAccessException {
		int i = 0;
		Message msg = new Message();
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("matchid", entry.getMatchid());
		parameterMap.put("carno", entry.getCarno());
		parameterMap.put("icid", entry.getIcid());
		parameterMap.put("rfidid", entry.getRfidid());
		parameterMap.put("gatename", entry.getGatename());
		parameterMap.put("usermemo", entry.getUsermemo());
		parameterMap.put("createman", entry.getCreator());
		parameterMap.put("forceflag", ((Integer) entry.getForceflag()).toString());
		parameterMap.put("outflag", "0");
		parameterMap.put("outmsg", "");
		exceptionMapper.insertExceptionOut(parameterMap);
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
		msg.setMsg(parameterMap.get("outmsg"));
		return msg;
	}

	/**
	 * 厂内调拨异常操作
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public Message insertExceptionDB(Measure measure) throws DataAccessException {
		Message msg = new Message();
		Map<String, String> parameterMap = new HashMap<String, String>();
		if ("-1".equals(measure.getMatchid()) || StringUtil.isEmpty(measure.getMatchid())) {
			String matchid = bcommonService.txMatchid(measure.getOperatype());
			measure.setMatchid(matchid);
		}
		parameterMap.put("v_matchid", measure.getMatchid());
		parameterMap.put("v_carno", measure.getCarno());
		parameterMap.put("v_icid", measure.getIcid());
		parameterMap.put("v_taskcode", measure.getTaskcode());
		parameterMap.put("v_gross", String.valueOf(measure.getGross() * 1000));
		parameterMap.put("v_grosstime", measure.getGrosstime());
		parameterMap.put("v_grossweighid", measure.getGrossweighid());
		parameterMap.put("v_grossweigh", measure.getGrossweigh());
		parameterMap.put("v_grossoperacode", measure.getGrossoperacode());
		parameterMap.put("v_grossoperaname", measure.getGrossoperaname());
		parameterMap.put("v_tare", String.valueOf(measure.getTare() * 1000));
		parameterMap.put("v_taretime", measure.getTaretime());
		parameterMap.put("v_tareweighid", measure.getTareweighid());
		parameterMap.put("v_tareweigh", measure.getTareweigh());
		parameterMap.put("v_tareoperacode", measure.getTareoperacode());
		parameterMap.put("v_tareoperaname", measure.getTareoperaname());
		parameterMap.put("v_usermemo", measure.getUsermemo());
		parameterMap.put("v_createman", measure.getCreator());
		parameterMap.put("v_flag", "0");
		parameterMap.put("v_msg", "");
		exceptionMapper.insertExceptionDB(parameterMap);
		String flag = parameterMap.get("v_flag");
		if ("0".equals(flag)) {
			msg.setSuccess(false);
		} else {
			msg.setSuccess(true);
		}
		msg.setMsg(parameterMap.get("v_msg"));
		parameterMap.clear();
		parameterMap = null;
		return msg;
	}

	/**
	 * 虚拟制卡信息
	 */

	public Message insertVApplicationbill(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar c = Calendar.getInstance();
		String entrytime = dateFormat.format(c.getTime());// 开始时间
		Entrylog entry = new Entrylog();
		entry.setCarno(app.getCarno());
		entry.setCreator(app.getCreator());
		entry.setGatename("虚拟进厂");
		entry.setUsermemo("虚拟进厂");
		entry.setOperatype(app.getOperatype());
		entry.setMatchid(app.getMatchid());
		Entrylog e = entryMapper.queryId(entry);// 根据车号查询进厂未出厂的信息id
		if (e == null) {// 为空时，车号未有进厂未出厂的信息,添加一条新的进厂信息
			entry.setEntrytype("1");
			entryMapper.insertEntrylog(entry);
		} else {// 如果有进厂未出厂的车辆，获取id，根据id更新进厂信息
			entry.setId(e.getId());
			entryMapper.updateEntrylog(entry);
		}
		app.setId(entry.getId());
		app.setEntrytime(entrytime);
		app.setEntergatename(entry.getGatename());
		app.setTypes("0");
		String usermemo = "";
		Applicationbill config = appMapper.queryConfig(app);
		if ("0".equals(config.getIsormeasure())) {
			app.setMatchid(bcommonService.txMatchid(app.getOperatype()));
		}
		for (int i = 0; i < app.getExparams().size(); i++) {
			if ("1".equals(config.getIsormeasure())) {
				app.setMatchid(bcommonService.txMatchid(app.getOperatype()));
			}
			Map<String, String> map = app.getExparams().get(i);
			app.setItemid(map.get("itemid"));
			app.setSaleitemid(map.get("saleitemid"));
			app.setPlanid(map.get("planid"));
			app.setMaterialcode(map.get("materialcode"));
			app.setMaterialname(map.get("materialname"));
			app.setSourcecode(map.get("sourcecode"));
			app.setSourcename(map.get("sourcename"));
			app.setTargetcode(map.get("targetcode"));
			app.setTargetname(map.get("targetname"));
			app.setGrossb(StringUtils.isEmpty(map.get("grossb")) ? 0 : Double.parseDouble(map.get("grossb")));
			app.setTareb(StringUtils.isEmpty(map.get("tareb")) ? 0 : Double.parseDouble(map.get("tareb")));
			app.setSuttleb(StringUtils.isEmpty(map.get("suttleb")) ? 0 : Double.parseDouble(map.get("suttleb")));
			app.setSnumber(StringUtils.isEmpty(map.get("snumber")) ? 0 : Double.parseDouble(map.get("snumber")));
			app.setSuttleapp(StringUtils.isEmpty(map.get("suttleapp")) ? 0 : Double.parseDouble(map.get("suttleapp")));
			app.setMeasureunit(map.get("measureunit"));
			app.setMaterialspec(map.get("materialspec"));
			app.setProdline(map.get("prodline"));
			app.setPictureno(map.get("pictureno"));
			app.setSteelname(map.get("steelname"));
			app.setMemo2(map.get("saleitemid"));
			app.setRouteid(map.get("routeid"));
			app.setDocumentcode(map.get("documentcode"));
			app.setUsermemo(map.get("usermemo"));
			app.setIsoruse("1");
			app.setFendanflag("1");
			appMapper.updateVIsoruse(app);
			appMapper.insertApplicationbillitem(app);
			if (!usermemo.contains(map.get("usermemo"))) {
				usermemo = usermemo + " " + map.get("usermemo");
			}
			if ("1".equals(config.getIsormeasure())) {
				app.setUsermemo(usermemo);
				appMapper.insertApplicationbill(app);
				if ("1".equals(config.getMflag())) {// 先毛后皮
					app.setMemo1("计毛");
				} else if ("2".equals(config.getMflag())) {
					app.setMemo1("计皮");
				}
				appMapper.insertCurrtemp(app);
			}
		}

		if ("0".equals(config.getIsormeasure())) {
			app.setUsermemo(usermemo);
			appMapper.insertApplicationbill(app);
			if ("1".equals(config.getMflag())) {// 先毛后皮
				app.setMemo1("计毛");
			} else if ("2".equals(config.getMflag())) {
				app.setMemo1("计皮");
			}
			appMapper.insertCurrtemp(app);
		}
		BCommon bcommon = new BCommon();
		bcommon.setMatchid(app.getMatchid());
		bcommon.setUsermemo("虚拟制卡信息");
		bcommon.setCreateman(app.getCreator());
		bcommonMapper.insertExceptinonlog(bcommon);
		bcommon = null;
		entry = null;
		e = null;
		config = null;
		app = null;
		return msg;
	}

	/**
	 * 作废虚拟制卡信息
	 */

	public Message cancelVApplicationbill(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		Currtemp curr = new Currtemp();
		String matchid = exceptionMapper.querymatchidByplanid(app.getMatchid());
		curr.setMatchid(matchid);
		app.setMatchid(matchid);
		msg = bcommonService.beforeCancel(curr, "IN");
		if (msg.isSuccess()) {
			List<Applicationbill> list = appMapper.queryPlanid(app);
			for (int i = 0; i < list.size(); i++) { // 根据物流号查询改单据使用的其他单据，还原未使用状态
				Applicationbill al = list.get(i);
				al.setIsoruse("0");
				al.setFendanflag("0");
				appMapper.updateVIsoruse(al);
				app.setId(al.getId());
			}
			appMapper.cancelApplicationbill(app);
			appMapper.cancelapplicationbillitem(app);
			appMapper.cancelCurrtemp(app);
			exceptionMapper.cancelEntrylog(app);
			BCommon bcommon = new BCommon();
			bcommon.setMatchid(app.getMatchid());
			bcommon.setUsermemo("虚拟制卡信息作废");
			bcommon.setCreateman(app.getCreator());
			bcommonMapper.insertExceptinonlog(bcommon);
			bcommon = null;
			list.clear();
			list = null;
			app = null;
		}
		return msg;
	}

	/**
	 * 作废厂内信息
	 */

	public Message cancelExceptionDB(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		appMapper.cancelCurrtemp(app);
		exceptionMapper.deleteMeasure(app);
		BCommon bcommon = new BCommon();
		bcommon.setMatchid(app.getMatchid());
		bcommon.setUsermemo("厂内信息作废");
		bcommon.setCreateman(app.getCreator());
		bcommonMapper.insertExceptinonlog(bcommon);
		bcommon = null;
		app = null;
		return msg;
	}

	/**
	 * 作废信息
	 */

	public Message cancelAllinfo(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		Currtemp curr = new Currtemp();
		curr.setMatchid(app.getMatchid());
		List<Applicationbill> list = appMapper.queryPlanid(app);
		for (int i = 0; i < list.size(); i++) { // 根据物流号查询改单据使用的其他单据，还原未使用状态
			Applicationbill al = list.get(i);
			if ("0".equals(al.getDocumentcode())) {
				app.setIsoruse("0");
				app.setSaleitemid(al.getSaleitemid());
				appMapper.updateIsoruseInter(app);
			} else {
				al.setIsoruse("0");
				appMapper.updateIsoruse(al);
			}

		}
		app.setId(list.get(0).getId());
		appMapper.cancelApplicationbill(app);
		appMapper.cancelapplicationbillitem(app);
		appMapper.cancelCurrtemp(app);
		exceptionMapper.cancelEntrylog(app);
		if (app.getMaterialflow() == 1) {
			exceptionMapper.cancelStorein(app);
			exceptionMapper.cancelStoreinitem(app);
		} else if (app.getMaterialflow() == 2) {
			exceptionMapper.cancelStoreout(app);
			exceptionMapper.cancelStoreoutitem(app);
		}
		BCommon bcommon = new BCommon();
		bcommon.setMatchid(app.getMatchid());
		bcommon.setUsermemo("异常作废整条数据信息");
		bcommon.setCreateman(app.getCreator());
		bcommonMapper.insertExceptinonlog(bcommon);
		bcommon = null;
		app = null;
		return msg;
	}

	/**
	 * 作废皮重信息
	 */

	public int cancelTareException(String carno) throws DataAccessException {
		exceptionMapper.cancelTareCurr(carno);
		int i = exceptionMapper.cancelTare(carno);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BCommon bcommon = new BCommon();
		bcommon.setMatchid(carno);
		bcommon.setUsermemo("作废皮重信息");
		bcommon.setCreateman(user.getDisplayname());
		bcommonMapper.insertExceptinonlog(bcommon);
		bcommon = null;
		user = null;
		return i;
	}

	/**
	 * 修改皮重信息
	 */

	public int updateTareException(Measure measure) throws DataAccessException {
		measure.setTare(measure.getTare() * 1000);
		exceptionMapper.updateCurrTare(measure);// 修改业务临时表皮重信息
		int i = exceptionMapper.updateTare(measure);// 修改皮重表信息
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BCommon bcommon = new BCommon();
		bcommon.setMatchid(measure.getCarno());
		bcommon.setUsermemo(measure.getUsermemo() + "皮重异常修改皮重信息");
		bcommon.setCreateman(user.getDisplayname());
		bcommonMapper.insertExceptinonlog(bcommon);
		bcommon = null;
		return i;
	}

	/**
	 * 作废线材信息
	 */

	public int cancelExceptionXC(Applicationbill app) throws DataAccessException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int i = appMapper.cancelCurrtemp(app);
		int counts = exceptionMapper.queryCountXC(app.getCarno());
		if (counts == 0) {
			Bcard card = new Bcard();
			card.setCardid(app.getIcid());
			card.setBackman(user.getDisplayname());
			card = cardApp.queryinfoBycardid(card);
			if (card != null) {
				if ("0".equals(card.getCardtype())) { // 为临时卡，进行退卡操作
					cardApp.backCard(card);
				}
			}
			card = null;
		}
		BCommon bcommon = new BCommon();
		bcommon.setMatchid(app.getMatchid());
		bcommon.setUsermemo("作废线材信息");
		bcommon.setCreateman(user.getDisplayname());
		bcommonMapper.insertExceptinonlog(bcommon);
		bcommon = null;
		user = null;
		app = null;
		return i;
	}

	/**
	 * 修改扣重信息
	 */

	public Message updateDeducation(Measure measure) throws DataAccessException {
		Message msg = new Message();
		String usermemo = "作废扣重信息";
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (measure.getDeduction() > 0) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar c = Calendar.getInstance();
			String deductiontime = dateFormat.format(c.getTime());// 开始时间
			measure.setDeductiontime(deductiontime);
			measure.setDeductioncode(user.getUsername());
			measure.setDeductionname(user.getDisplayname());
			usermemo = "操作扣重信息";
		}
		measure.setDeduction(measure.getDeduction() * 1000);
		exceptionMapper.updateCDeducation(measure);
		exceptionMapper.updateMDeducation(measure);
		BCommon bcommon = new BCommon();
		bcommon.setMatchid(measure.getMatchid());
		bcommon.setUsermemo(usermemo);
		bcommon.setCreateman(user.getDisplayname());
		bcommonMapper.insertExceptinonlog(bcommon);
		bcommon = null;
		measure = null;
		return msg;
	}

	/**
	 * 作废计量信息
	 */

	public Message updateCurrMeasure(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		exceptionMapper.cancelMeasure(app);
		if (app.getMaterialflow() == 1) {
			app.setMemo1("计毛");
		} else if (app.getMaterialflow() == 2) {
			app.setMemo1("计皮");
		}
		exceptionMapper.updateCurrMeasure(app);
		BCommon bcommon = new BCommon();
		bcommon.setMatchid(app.getMatchid());
		bcommon.setUsermemo("异常作废计量数据信息");
		bcommon.setCreateman(app.getCreator());
		bcommonMapper.insertExceptinonlog(bcommon);
		bcommon = null;
		app = null;
		return msg;
	}

	/**
	 * 异常数据操作火车信息
	 * 
	 * @param measure
	 * @return 2016-11-29 10:14:18 grosstime 2016-11-28 10:14:27
	 * @throws DataAccessException
	 */
	public int insertHcException(Measure measure) throws DataAccessException {
		int flag = 0;
		// DecimalFormat fnum = new DecimalFormat("##0.000");
		double gross = measure.getGross() * 1000;
		double tare = measure.getTare() * 1000;
		measure.setGross(gross);
		measure.setTare(tare);
		measure.setPlanweight(measure.getPlanweight() * 1000);
		measure.setDeduction(measure.getDeduction() * 1000);
		if (gross > 0 && tare > 0) {
			double suttle = gross - tare;
			measure.setSuttle(suttle);
			if (measure.getGrosstime().compareTo(measure.getTaretime()) > 0) {// 毛重时间晚于皮重时间
				measure.setSuttletime(measure.getGrosstime());
				measure.setSuttleoperacode(measure.getGrossoperacode());
				measure.setSuttleoperaname(measure.getGrossoperaname());
				measure.setSuttleweigh(measure.getGrossweigh());
				measure.setSuttleweighid(measure.getGrossweighid());
			} else {
				measure.setSuttletime(measure.getTaretime());
				measure.setSuttleoperacode(measure.getTareoperacode());
				measure.setSuttleoperaname(measure.getTareoperaname());
				measure.setSuttleweigh(measure.getTareweigh());
				measure.setSuttleweighid(measure.getTareweighid());
			}
			measure.setMstate(8);
		} else {
			if ("95".equals(measure.getOperatype())) {// 火运进厂
				measure.setMemo1("回皮");
			} else {// 火运出厂
				measure.setMemo1("回毛");
			}
			measure.setMstate(0);
		}
		Measure m = exceptionMapper.queryExcehcBymatchid(measure);
		if (m != null) {
			exceptionMapper.updateCurrtemp(measure);
			exceptionMapper.updateMeasure(measure);
			flag = 1;
		} else {
			String matchid = bcommonService.txMatchid(measure.getOperatype());
			measure.setMatchid(matchid);
			exceptionMapper.insertCurrtemp(measure);
			exceptionMapper.insertMeasure(measure);
			flag = 1;
		}
		BCommon bcommon = new BCommon();
		bcommon.setMatchid(measure.getMatchid());
		bcommon.setUsermemo("火运异常数据操作 " + measure.getUsermemo());
		bcommon.setCreateman(measure.getCreator());
		bcommonMapper.insertExceptinonlog(bcommon);
		bcommon = null;
		m = null;
		measure = null;
		return flag;
	}

	/**
	 * 路线临时调整
	 * 
	 * @param worklineItem
	 * @return 
	 * @throws DataAccessException
	 */
	public int insertAdjustWorkline(WorklineItem worklineItem) throws DataAccessException {
		int flag = 0;
		Workline workline = new Workline();
		workline.setFid(worklineItem.getFid());
		workline = adjustMapper.queryWorkline(workline);
		long id = adjustMapper.queryId().getId();
		workline.setId(id);
		adjustMapper.insertWorkline(workline);
		List<WorklineItem> list = adjustMapper.queryWorklineItem(worklineItem);
		for (int i = 0; i < list.size(); i++) {
			WorklineItem wi = list.get(i);
			if (worklineItem.getNodecode().equals(wi.getNodecode())) {
				worklineItem.setFid(id);
				adjustMapper.insertWorklineItem(worklineItem);
			} else {
				wi.setFid(id);
				adjustMapper.insertWorklineItem(wi);
			}
		}
		adjustMapper.updateAppRouteid(worklineItem);//修改制卡表路线
		adjustMapper.updateCurrRouteid(worklineItem);//修改临时表路线
		
		BCommon bcommon = new BCommon();
		bcommon.setMatchid(String.valueOf(worklineItem.getFid()));
		bcommon.setUsermemo("作业路线调整 ");
		bcommon.setCreateman(worklineItem.getCreator());
		bcommonMapper.insertExceptinonlog(bcommon);
		bcommon = null;
		return flag;
	}
}
