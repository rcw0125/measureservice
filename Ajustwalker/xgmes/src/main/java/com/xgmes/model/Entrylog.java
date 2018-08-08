package com.xgmes.model;

import java.util.Date;

import javax.persistence.Column;
import org.apache.ibatis.type.Alias;
import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("entrylog")
public class Entrylog extends BaseModel{
	
	private static final long serialVersionUID = 6866527361166842381L;
	
	@Fields(name = "物流号")
	@Column(name = "matchid")
	private String matchid;
	
	@Fields(name = "车号")
	@Column(name = "carno")
	private String carno;
	
	@Fields(name = "IC卡号")
	@Column(name = "icid")
	private String icid;
	
	@Fields(name = "RFID卡号")
	@Column(name = "rfidid")
	private String rfidid;
	
	@Fields(name = "业务类型")
	@Column(name = "operatype")
	private String operatype;
	
	@Fields(name = "货名")
	@Column(name = "materialname")
	private String materialname;
	
	@Fields(name = "供货")
	@Column(name = "sourcename")
	private String sourcename;
	
	@Fields(name = "收货")
	@Column(name = "targetname")
	private String targetname;
	
	@Fields(name = "进厂类型")
	@Column(name = "entrytype")
	private String entrytype;
	
	@Fields(name = "大门")
	@Column(name = "gatename")
	private String gatename;
	
	@Fields(name = "大门编码")
	@Column(name = "gatecode")
	private String gatecode;

	@Fields(name = "进出id")
	@Column(name = "eid")
	private String eid;
	
	@Fields(name = "厂内耗时")
	@Column(name = "statetime")
	private String statetime;
	
	@Fields(name = "卡类型")
	@Column(name = "ictype")
	private String ictype;
	
	private String matchids;
	
	private int forceflag;//是否强制进出厂	
	
	private Entrylog entry;
	
	private String createdate;
	
	private String begintime;
	
	private String endtime;
	
	private String entrytime;
	
	private String cartype;//卡类型
	
	private String planid;//计划号
	
	private String types;//进出厂类型
	
	@Fields(name = "当前单位")
	private String unitname;
	
	@Fields(name = "当前单位")
	private String unitcode;
	
	private String stateminute;
	
	private String driver;//司机
	
	private String cardmemo;//卡信息备注
	
	private String validflagoperaname;
	
	private Date validflagtime;
	
	private String msg;
	
	private String cardid;
	
	public String getMatchid() {
		return matchid;
	}
	
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
	
	public String getCarno() {
		return carno;
	}
	
	public void setCarno(String carno) {
		this.carno = carno;
	}
	
	public String getIcid() {
		return icid;
	}
	
	public void setIcid(String icid) {
		this.icid = icid;
	}
	
	public String getRfidid() {
		return rfidid;
	}
	
	public void setRfidid(String rfidid) {
		this.rfidid = rfidid;
	}
	
	public String getOperatype() {
		return operatype;
	}
	
	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}
	
	public String getMaterialname() {
		return materialname;
	}
	
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	
	public String getSourcename() {
		return sourcename;
	}
	
	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
	
	public String getTargetname() {
		return targetname;
	}
	
	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}
	
	public String getEntrytype() {
		return entrytype;
	}
	public void setEntrytype(String entrytype) {
		this.entrytype = entrytype;
	}
	
	public String getGatename() {
		return gatename;
	}
	
	public void setGatename(String gatename) {
		this.gatename = gatename;
	}

	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getStatetime() {
		return statetime;
	}
	
	public void setStatetime(String statetime) {
		this.statetime = statetime;
	}
	
	public String getIctype() {
		return ictype;
	}
	
	public void setIctype(String ictype) {
		this.ictype = ictype;
	}
	
	public String getMatchids() {
		return matchids;
	}
	
	public void setMatchids(String matchids) {
		this.matchids = matchids;
	}
	
	public int getForceflag() {
		return forceflag;
	}
	
	public void setForceflag(int forceflag) {
		this.forceflag = forceflag;
	}
	
	public String getGatecode() {
		return gatecode;
	}
	
	public void setGatecode(String gatecode) {
		this.gatecode = gatecode;
	}
	
	public Entrylog getEntry() {
		return entry;
	}
	
	public void setEntry(Entrylog entry) {
		this.entry = entry;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}


	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public String getStateminute() {
		return stateminute;
	}

	public void setStateminute(String stateminute) {
		this.stateminute = stateminute;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getCardmemo() {
		return cardmemo;
	}

	public void setCardmemo(String cardmemo) {
		this.cardmemo = cardmemo;
	}

	public String getValidflagoperaname() {
		return validflagoperaname;
	}

	public void setValidflagoperaname(String validflagoperaname) {
		this.validflagoperaname = validflagoperaname;
	}

	public Date getValidflagtime() {
		return validflagtime;
	}

	public void setValidflagtime(Date validflagtime) {
		this.validflagtime = validflagtime;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	
	
}
