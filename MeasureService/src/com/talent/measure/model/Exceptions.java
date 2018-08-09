package com.talent.measure.model;

import java.util.Date;

public class Exceptions {

	private String carno;
	private String icid;
	private String rfidid;
	private String applicationno;
	private String operatype;
	private String planid;
	private String orderno;
	private String taskcode;
	private String materialcode;
	private String materialname;
	private String materialspeccode;
	private String materialspec;
	private String shipcode;
	private String ship;
	private String sourcecode;
	private String sourcename;
	private String sourceplace;
	private String sourcetime;
	private String targetcode;
	private String targetname;
	private String targetplace;
	private String targettime;
	private String motorcadecode;
	private String motorcadename;
	private String deduction="0";
	private String deductiontime;
	private String deductioncode;
	private String deductionname;
	private String deductionoperacode;
	private String deductionoperaname;
	private int materialcount=0;
	private double planweight=0.0;
	private int planmaterialcount=0;
	private double grossb=0.0;
	private double tareb=0.0;
	private double suttleb=0.0;
	private String entertime;
	private String leavetime;
	private String sampletime;
	private int mflag;
	private double tarehour=0;
	private double measuretype;
	private double snumber;
	private double suttleapp;
	private int flag;
	private String msg;
	private String mtype;
	private double gross=0;
	private String grosstime;
	private String grossweighid;
	private String grossweigh;
	private String grossoperacode;
	private String grossoperaname;
	private double tare=0;
	private String taretime;
	private String tareweighid;
	private String tareweigh;
	private String tareoperacode;
	private String tareoperaname;
	private String tarelogid;
	private double suttle=0;
	private String suttletime;
	private String suttleweighid;
	private String suttleweigh;
	private String suttleoperacode;
	private String suttleoperaname;
	private String totype;
	private String usermemo;
	private String createman;
	private String matchid;
	private Date createdate;
	private String begintime;
	private String endtime;
	private int id;
	private String sql;
	private String mtypes;
	
	
	
	
	
	
	
	

    
	public String getMtypes() {
		return mtypes;
	}

	public void setMtypes(String mtypes) {
		this.mtypes = mtypes;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTarelogid() {
		return tarelogid;
	}

	public void setTarelogid(String tarelogid) {
		this.tarelogid = tarelogid;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getBeginttime() {
		return begintime;
	}

	public void setBeginttime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public double getSnumber() {
		return snumber;
	}

	public void setSnumber(double snumber) {
		this.snumber = snumber;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	public String getUsermemo() {
		return usermemo;
	}

	public void setUsermemo(String usermemo) {
		this.usermemo = usermemo;
	}

	public String getCreateman() {
		return createman;
	}

	public void setCreateman(String createman) {
		this.createman = createman;
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

	public String getApplicationno() {
		return applicationno;
	}

	public void setApplicationno(String applicationno) {
		this.applicationno = applicationno;
	}

	public String getOperatype() {
		return operatype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	public String getMaterialcode() {
		return materialcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public String getMaterialspeccode() {
		return materialspeccode;
	}

	public void setMaterialspeccode(String materialspeccode) {
		this.materialspeccode = materialspeccode;
	}

	public String getMaterialspec() {
		return materialspec;
	}

	public void setMaterialspec(String materialspec) {
		this.materialspec = materialspec;
	}

	public String getShipcode() {
		return shipcode;
	}

	public void setShipcode(String shipcode) {
		this.shipcode = shipcode;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getSourcecode() {
		return sourcecode;
	}

	public void setSourcecode(String sourcecode) {
		this.sourcecode = sourcecode;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getSourceplace() {
		return sourceplace;
	}

	public void setSourceplace(String sourceplace) {
		this.sourceplace = sourceplace;
	}

	public String getSourcetime() {
		return sourcetime;
	}

	public void setSourcetime(String sourcetime) {
		this.sourcetime = sourcetime;
	}

	public String getTargetcode() {
		return targetcode;
	}

	public void setTargetcode(String targetcode) {
		this.targetcode = targetcode;
	}

	public String getTargetname() {
		return targetname;
	}

	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}

	public String getTargetplace() {
		return targetplace;
	}

	public void setTargetplace(String targetplace) {
		this.targetplace = targetplace;
	}

	public String getTargettime() {
		return targettime;
	}

	public void setTargettime(String targettime) {
		this.targettime = targettime;
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

	public String getDeduction() {
		return deduction;
	}

	public void setDeduction(String deduction) {
		this.deduction = deduction;
	}

	public String getDeductiontime() {
		return deductiontime;
	}

	public void setDeductiontime(String deductiontime) {
		this.deductiontime = deductiontime;
	}

	public String getDeductioncode() {
		return deductioncode;
	}

	public void setDeductioncode(String deductioncode) {
		this.deductioncode = deductioncode;
	}

	public String getDeductionname() {
		return deductionname;
	}

	public void setDeductionname(String deductionname) {
		this.deductionname = deductionname;
	}

	public String getDeductionoperacode() {
		return deductionoperacode;
	}

	public void setDeductionoperacode(String deductionoperacode) {
		this.deductionoperacode = deductionoperacode;
	}

	public String getDeductionoperaname() {
		return deductionoperaname;
	}

	public void setDeductionoperaname(String deductionoperaname) {
		this.deductionoperaname = deductionoperaname;
	}

	public int getMaterialcount() {
		return materialcount;
	}

	public void setMaterialcount(int materialcount) {
		this.materialcount = materialcount;
	}

	public double getPlanweight() {
		return planweight;
	}

	public void setPlanweight(double planweight) {
		this.planweight = planweight;
	}

	public int getPlanmaterialcount() {
		return planmaterialcount;
	}

	public void setPlanmaterialcount(int planmaterialcount) {
		this.planmaterialcount = planmaterialcount;
	}

	public double getGrossb() {
		return grossb;
	}

	public void setGrossb(double grossb) {
		this.grossb = grossb;
	}

	public double getTareb() {
		return tareb;
	}

	public void setTareb(double tareb) {
		this.tareb = tareb;
	}

	public double getSuttleb() {
		return suttleb;
	}

	public void setSuttleb(double suttleb) {
		this.suttleb = suttleb;
	}

	public String getEntertime() {
		return entertime;
	}

	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}

	public String getLeavetime() {
		return leavetime;
	}

	public void setLeavetime(String leavetime) {
		this.leavetime = leavetime;
	}

	public String getSampletime() {
		return sampletime;
	}

	public void setSampletime(String sampletime) {
		this.sampletime = sampletime;
	}

	public int getMflag() {
		return mflag;
	}

	public void setMflag(int mflag) {
		this.mflag = mflag;
	}

	public double getTarehour() {
		return tarehour;
	}

	public void setTarehour(double tarehour) {
		this.tarehour = tarehour;
	}

	public double getMeasuretype() {
		return measuretype;
	}

	public void setMeasuretype(double measuretype) {
		this.measuretype = measuretype;
	}

	public double getSuttleapp() {
		return suttleapp;
	}

	public void setSuttleapp(double suttleapp) {
		this.suttleapp = suttleapp;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public double getGross() {
		return gross;
	}

	public void setGross(double gross) {
		this.gross = gross;
	}

	public String getGrosstime() {
		return grosstime;
	}

	public void setGrosstime(String grosstime) {
		this.grosstime = grosstime;
	}

	public String getGrossweighid() {
		return grossweighid;
	}

	public void setGrossweighid(String grossweighid) {
		this.grossweighid = grossweighid;
	}

	public String getGrossweigh() {
		return grossweigh;
	}

	public void setGrossweigh(String grossweigh) {
		this.grossweigh = grossweigh;
	}

	public String getGrossoperacode() {
		return grossoperacode;
	}

	public void setGrossoperacode(String grossoperacode) {
		this.grossoperacode = grossoperacode;
	}

	public String getGrossoperaname() {
		return grossoperaname;
	}

	public void setGrossoperaname(String grossoperaname) {
		this.grossoperaname = grossoperaname;
	}

	public double getTare() {
		return tare;
	}

	public void setTare(double tare) {
		this.tare = tare;
	}

	public String getTaretime() {
		return taretime;
	}

	public void setTaretime(String taretime) {
		this.taretime = taretime;
	}

	public String getTareweighid() {
		return tareweighid;
	}

	public void setTareweighid(String tareweighid) {
		this.tareweighid = tareweighid;
	}

	public String getTareweigh() {
		return tareweigh;
	}

	public void setTareweigh(String tareweigh) {
		this.tareweigh = tareweigh;
	}

	public String getTareoperacode() {
		return tareoperacode;
	}

	public void setTareoperacode(String tareoperacode) {
		this.tareoperacode = tareoperacode;
	}

	public String getTareoperaname() {
		return tareoperaname;
	}

	public void setTareoperaname(String tareoperaname) {
		this.tareoperaname = tareoperaname;
	}

	public double getSuttle() {
		return suttle;
	}

	public void setSuttle(double suttle) {
		this.suttle = suttle;
	}

	public String getSuttletime() {
		return suttletime;
	}

	public void setSuttletime(String suttletime) {
		this.suttletime = suttletime;
	}

	public String getSuttleweighid() {
		return suttleweighid;
	}

	public void setSuttleweighid(String suttleweighid) {
		this.suttleweighid = suttleweighid;
	}

	public String getSuttleweigh() {
		return suttleweigh;
	}

	public void setSuttleweigh(String suttleweigh) {
		this.suttleweigh = suttleweigh;
	}

	public String getSuttleoperacode() {
		return suttleoperacode;
	}

	public void setSuttleoperacode(String suttleoperacode) {
		this.suttleoperacode = suttleoperacode;
	}

	public String getSuttleoperaname() {
		return suttleoperaname;
	}

	public void setSuttleoperaname(String suttleoperaname) {
		this.suttleoperaname = suttleoperaname;
	}

	public String getTotype() {
		return totype;
	}

	public void setTotype(String totype) {
		this.totype = totype;
	}

	public int getValidflag() {
		return validflag;
	}

	public void setValidflag(int validflag) {
		this.validflag = validflag;
	}

	private int validflag;

}
