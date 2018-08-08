package com.talent.materialflow.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang.StringUtils;
import com.talent.core.model.Message;
import com.talent.core.util.HttpUtils;
import com.talent.materialflow.model.Applicationbill;
import com.talent.materialflow.model.Bcard;
import com.talent.materialflow.model.Blacklist;
import com.talent.materialflow.model.Currtemp;
import com.talent.materialflow.model.Entrylog;
import com.talent.materialflow.service.ApplicationbillService;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.CardService;
import com.talent.materialflow.service.mapper.ApplicationbillMapper;
import com.talent.materialflow.service.mapper.BCommonMapper;
import com.talent.materialflow.service.mapper.CardMapper;
import com.talent.materialflow.service.mapper.EntrylogMapper;

@Service
@Transactional
public class ApplicationbillServiceImpl implements ApplicationbillService {
	@Resource
	private ApplicationbillMapper appMapper;
	@Resource
	private BCommonService bcommonService;
	@Resource
	private CardService cardService;
	@Autowired
	private HttpUtils httpUtils;
	@Resource
	private BCommonMapper bcommonMapper;
	@Resource
	private CardMapper cardMapper;
	@Resource
	private EntrylogMapper entryMapper;

	/**
	 * 添加制卡信息
	 */

	public Message insertApplicationbill(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		Bcard card = new Bcard();
		if (app.getFkflag() == 1 && "1".equals(app.getCardstate())) { // 允许发卡并且卡状态为初始化状态时进行发卡操作
			card.setCardid(app.getIcid());// 卡号
			card.setCarno(app.getCarno());// 车号
			card.setDeposit(String.valueOf(app.getDeposit()));// 押金
			card.setDriver(app.getDriver());// 司机
			card.setCardtype("0");// 临时卡
			card.setCartype("0");// 业务车辆
			card.setFromman(app.getCreator());
			card.setMotorcadecode(app.getMotorcadecode());
			card.setMotorcadename(app.getMotorcadename());
			card.setCardclass("0");
			cardMapper.fromCard(card);
			//msg = cardService.fromCard(card);
		}
		int rfidflag=0;//是否需要验证rfid
		if(StringUtils.isNotEmpty(app.getMaterialcodes())){
			String[] mlist =  app.getMaterialcodes().split(",");
			for (int i = 0; i < mlist.length; i++) {
				if (!"".equals(mlist[i]) && mlist[i] != null) {
					String isorrfid = bcommonService.queryInfoBymateiracode(mlist[i], "2");
					if ("1".equals(isorrfid)) {//等于1的时候标记需要发放rfid
						rfidflag++;
					}
				}
			}
		}
		if(rfidflag>0){//需要发放rfid卡
			if (StringUtils.isNotEmpty(app.getRfidid().trim())) {//rfid卡号不为空
				card.setCardid(app.getRfidid().trim());// 卡号
				card.setCardtype("1");// 临时卡
				card.setCartype("0");
				card.setCardclass("1");
				card.setCarno(app.getCarno().trim());
				card.setFromman(app.getCreator());
				card.setMotorcadecode(app.getMotorcadecode());
				card.setMotorcadename(app.getMotorcadename());
				Blacklist black = new Blacklist();
				black.setCardid(card.getCardid());
				black.setCarno(card.getCarno());
				black.setRecordtype(card.getCardclass());
				Message me = bcommonService.judgCarId(black);// 根据卡号和车号判断是否允许发卡
				if (me.isSuccess()) {
					black.setCardid(card.getCardid());
					black.setCarno(card.getCarno());
					black.setRecordtype(card.getCardclass());
					me = bcommonService.judgOrFromcarno(black);
					if(me.isSuccess()){
						int mflag = cardMapper.fromCard(card);
						if(mflag==0){
							msg.setSuccess(false);
							msg.setMsg("射频卡发卡失败，请单独发放射频卡后制卡");
						}
					}else{
						msg=me;
					}
				}
			}else{
				msg.setSuccess(false);
				msg.setMsg("本车需要发放射频卡，未获取到RFID卡");
			}
		}
		Entrylog emsg = new Entrylog();
		emsg.setEntrytype("制卡记录发卡查询");
		emsg.setCardmemo("物料编码："+app.getMaterialcodes()+" rfid标记："+rfidflag+" rfid卡号："+app.getRfidid()+" 返回内容："+msg.getMsg());//返回内容
		entryMapper.insertEntrylogmsg(emsg);
		
		
			if(msg.isSuccess()){
				app.setCarno(app.getCarno().trim());
				Applicationbill config = appMapper.queryConfig(app);
				app.setTypes("0");
				String usermemo = "";
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
					app.setDocumentcode(map.get("documentcode"));
					app.setMemo2(map.get("saleitemid"));
					app.setRouteid(map.get("routeid"));
					app.setUsermemo(map.get("usermemo"));
					app.setMiddlemanname(map.get("middlemanname"));
					if (!"0".equals(map.get("documentcode"))) {
						app.setIsoruse("1");
						appMapper.updateIsoruse(app);
					} else {
						app.setIsoruse("1");
						appMapper.updateIsoruseInter(app);
					}
					appMapper.insertApplicationbillitem(app);
					if(!usermemo.contains(map.get("usermemo"))){
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
			}
		return msg;
	}

	/**
	 * 修改制卡信息
	 */

	public Message updateApplicationbill(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		String document = app.getDocumentcode();
		app.setCarno(app.getCarno().trim());
		appMapper.cancelapplicationbillitem(app);
		if (!"0".equals(document)) {// 单据类型为0时，代表数据为erp单据
			List<Applicationbill> list = appMapper.queryPlanid(app);
			for (int i = 0; i < list.size(); i++) { // 根据物流号查询改单据使用的其他单据，还原未使用状态
				Applicationbill al = list.get(i);
				al.setIsoruse("0");
				appMapper.updateIsoruse(al);
			}
		}
		for (int i = 0; i < app.getExparams().size(); i++) {
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
			app.setDocumentcode(map.get("documentcode"));
			app.setUsermemo(map.get("usermemo"));
			if (!"0".equals(map.get("documentcode"))) {
				app.setIsoruse("1");
				appMapper.updateIsoruse(app);
			} else {
				app.setIsoruse("1");
				appMapper.updateIsoruseInter(app);

			}
			if(app.getItemid().indexOf("add")!=-1){
				appMapper.insertApplicationbillitem(app);
			}else{
				int flag = appMapper.queryCountByitemid(app);
				if (flag == 0) {
					appMapper.insertApplicationbillitem(app);
				} else {
					appMapper.updateApplicationbillitem(app);
				}	
			}
		}
		appMapper.updateApplicationbill(app);
		appMapper.updateCurrtemp(app);
		appMapper.updateMeasure(app);
		return msg;
	}

	/**
	 * 添加其他单据信息
	 */

	public Message insertDocument(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		String matchid = bcommonService.txPlanid(app.getOperatype());
		app.setMatchid(matchid);
		app.setCarno(app.getCarno().trim());
		appMapper.insertApplicationbill(app);
		for (int i = 0; i < app.getExparams().size(); i++) {
			Map<String, String> map = app.getExparams().get(i);
			// app.setMaterialcode(map.get("materialcode"));
			app.setMaterialname(map.get("materialname"));
			app.setMeasureunit(map.get("measureunit"));
			app.setSnumber(StringUtils.isEmpty(map.get("snumber")) ? 0 : Double.parseDouble(map.get("snumber")));
			app.setIsorin(StringUtils.isEmpty(map.get("isorin")) ? 0 : Integer.parseInt(map.get("isorin")));
			app.setReturntime(map.get("returntime"));
			app.setReturntotal(
					StringUtils.isEmpty(map.get("returntotal")) ? 0 : Double.parseDouble(map.get("returntotal")));
			app.setIsorout(StringUtils.isEmpty(map.get("isorout")) ? 0 : Double.parseDouble(map.get("isorout")));
			app.setOuttime(map.get("outtime"));
			app.setOuttotal(StringUtils.isEmpty(map.get("outtotal")) ? 0 : Double.parseDouble(map.get("outtotal")));
			app.setUsermemo(map.get("usermemo"));
			app.setSaleitemid(map.get("saleitemid"));
			appMapper.insertApplicationbillitem(app);
		}
		return msg;
	}

	/**
	 * 修改其他单据信息
	 */

	public Message updateDocument(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		app.setCarno(app.getCarno().trim());
		appMapper.cancelapplicationbillitem(app);
		appMapper.updateApplicationbill(app);
		for (int i = 0; i < app.getExparams().size(); i++) {
			Map<String, String> map = app.getExparams().get(i);
			app.setMaterialname(map.get("materialname"));
			app.setMeasureunit(map.get("measureunit"));
			app.setSnumber(StringUtils.isEmpty(map.get("snumber")) ? 0 : Double.parseDouble(map.get("snumber")));
			app.setIsorin(StringUtils.isEmpty(map.get("isorin")) ? 0 : Integer.parseInt(map.get("isorin")));
			app.setReturntime(map.get("returntime"));
			app.setReturntotal(StringUtils.isEmpty(map.get("returntotal")) ? 0 : Double.parseDouble(map.get("returntotal")));
			app.setIsorout(StringUtils.isEmpty(map.get("isorout")) ? 0 : Double.parseDouble(map.get("isorout")));
			app.setOuttime(map.get("outtime"));
			app.setOuttotal(StringUtils.isEmpty(map.get("outtotal")) ? 0 : Double.parseDouble(map.get("outtotal")));
			app.setUsermemo(map.get("usermemo"));
			app.setSaleitemid(map.get("saleitemid"));
			app.setItemid(map.get("itemid"));
			if (map.get("itemid").indexOf("add") != -1) {
				appMapper.insertApplicationbillitem(app);
			} else {
				appMapper.updateApplicationbillitem(app);
			}
		}
		return msg;
	}

	/**
	 * 作废其他单据
	 */

	public Message cancelDocument(Applicationbill app) throws DataAccessException {
		Message msg = new Message();
		appMapper.cancelApplicationbill(app);
		appMapper.cancelapplicationbillitem(app);
		return msg;
	}

	/**
	 * 作废制卡信息
	 */

	public Message cancelApplicationbill(Applicationbill app) throws DataAccessException {
		Message msg = new Message();

		Currtemp curr = new Currtemp();
		curr.setMatchid(app.getMatchid());
		msg = bcommonService.beforeCancel(curr, "IN");
		if (msg.isSuccess()) {
			List<Applicationbill> list = appMapper.queryPlanid(app);
			for (int i = 0; i < list.size(); i++) { // 根据物流号查询改单据使用的其他单据，还原未使用状态
				Applicationbill al = list.get(i);
				al.setIsoruse("0");
				if ("0".equals(al.getDocumentcode())) {// 单据类型为0时，代表数据为erp单据
					appMapper.updateIsoruseInter(al);
				} else {
					appMapper.updateIsoruse(al);
				}
				app.setId(al.getId());
				app.setRouteid(al.getRouteid());
			}
			appMapper.cancelApplicationbill(app);//作废制卡主表信息
			appMapper.cancelapplicationbillitem(app);//作废制卡子表信息
			appMapper.cancelCurrtemp(app);//作废业务临时表信息
			int idflag = appMapper.queryIdcount(app.getId());
			if(idflag==0){
				appMapper.cancelEntrylog(app);//作废进出厂日志	
			}
			try {
				String pcode = bcommonMapper.queryOrsample(app.getRouteid());
				if (pcode != null) {
					String message = httpUtils.get("http://192.168.2.42:7080/MeasureService/qualityInterface/updateSatus.do",
							"matchid=" + app.getMatchid());
					System.out.println("输出作废信息........." + message);
				}
			} catch (Exception e) {

			}

		}
        app=null;
        curr=null;
		return msg;
	}

}
