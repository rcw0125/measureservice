package com.talent.materialflow.model;

import javax.persistence.Column;
import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("blacklist")
public class Blacklist extends BaseModel {

	private static final long serialVersionUID = -7376672642100054626L;

	@Fields(name = "车号/卡号")
	@Column(name = "cardid")
	private String cardid;

	@Fields(name = "车号")
	@Column(name = "carno")
	private String carno;

	@Fields(name = "类型")
	@Column(name = "recordtype")
	private String recordtype;

	private String begintime;

	private String endtime;

	private String driver;

	private double deposit;

	private String motorcadecode;

	private String motorcadename;

	private String beginperiod;

	private String endperiod;

	private String createdate;
	private String createman;
	private String validman;
	private String validtime;
	
	private String unitname;
	
	private String cartype;

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public String getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public String getMotorcadecode() {
		return motorcadecode;
	}

	public void setMotorcadecode(String motorcadecode) {
		this.motorcadecode = motorcadecode;
	}

	public String getMotorcadename() {
		return motorcadename;
	}

	public void setMotorcadename(String motorcadename) {
		this.motorcadename = motorcadename;
	}

	public String getEndperiod() {
		return endperiod;
	}

	public void setEndperiod(String endperiod) {
		this.endperiod = endperiod;
	}

	public String getBeginperiod() {
		return beginperiod;
	}

	public void setBeginperiod(String beginperiod) {
		this.beginperiod = beginperiod;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCreateman() {
		return createman;
	}

	public void setCreateman(String createman) {
		this.createman = createman;
	}

	public String getValidman() {
		return validman;
	}

	public void setValidman(String validman) {
		this.validman = validman;
	}

	public String getValidtime() {
		return validtime;
	}

	public void setValidtime(String validtime) {
		this.validtime = validtime;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}


}
