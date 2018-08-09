package com.talent.measure.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class QualityInterface implements Serializable {

	private static final long serialVersionUID = -6421544160141754856L;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private int id = 0;

	private String cph = "";

	private String noticebillid = "";

	private String icno = "";

	private String noticebillbodyid = "";

	private String billid = "";

	private String billbodyid = "";

	private String remark = "";

	private String cvendorbaseid = "";

	private String djno = "";

	private String carriveorder_bid = "";

	private double sjsl = 0.00;

	private String cbaseid = "";

	private double w1 = 0.00;

	private double w2 = 0.00;

	private String t1 = "";

	private String t2 = "";

	private String begintime = "";

	private String endtime = "";

	private String result = "";

	private String matchid = "";

	private String sampleunitcode = "";

	private String sampleunitname = "";

	private String dreceivedate = sdf.format(Calendar.getInstance().getTime());

	private String cemployeeid = "";

	private String cdeptid = "";

	private String cooperator = "";

	private String samplercode = "";

	private String samplername = "";

	private int counts;

	private String status;

	private String cupsourcebillid;
	private String cupsourcebillrowid;
	private String csourcebillid;
	private String csourcebillrowid;
	private String vdef16;
	private String vdef17;
	private String vdef18;
	private String vdef19;
	private String vdef20;

	private String memo;
	private String memo1;
	private String memo2;
	private String memo3;
	private String memo4;
	
	private double deduction;
	
	private double deduction2;
	
	private String operatype;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCph() {
		return cph;
	}

	public void setCph(String cph) {
		this.cph = cph;
	}

	public String getNoticebillid() {
		return noticebillid;
	}

	public void setNoticebillid(String noticebillid) {
		this.noticebillid = noticebillid;
	}

	public String getIcno() {
		return icno;
	}

	public void setIcno(String icno) {
		this.icno = icno;
	}

	public String getNoticebillbodyid() {
		return noticebillbodyid;
	}

	public void setNoticebillbodyid(String noticebillbodyid) {
		this.noticebillbodyid = noticebillbodyid;
	}

	public String getBillbodyid() {
		return billbodyid;
	}

	public void setBillbodyid(String billbodyid) {
		this.billbodyid = billbodyid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCvendorbaseid() {
		return cvendorbaseid;
	}

	public void setCvendorbaseid(String cvendorbaseid) {
		this.cvendorbaseid = cvendorbaseid;
	}

	public String getDjno() {
		return djno;
	}

	public void setDjno(String djno) {
		this.djno = djno;
	}

	public String getCarriveorder_bid() {
		return carriveorder_bid;
	}

	public void setCarriveorder_bid(String carriveorder_bid) {
		this.carriveorder_bid = carriveorder_bid;
	}

	public double getSjsl() {
		return sjsl;
	}

	public void setSjsl(double sjsl) {
		this.sjsl = sjsl;
	}

	public String getCbaseid() {
		return cbaseid;
	}

	public void setCbaseid(String cbaseid) {
		this.cbaseid = cbaseid;
	}

	public double getW1() {
		return w1;
	}

	public void setW1(double w1) {
		this.w1 = w1;
	}

	public double getW2() {
		return w2;
	}

	public void setW2(double w2) {
		this.w2 = w2;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	public String getSampleunitcode() {
		return sampleunitcode;
	}

	public void setSampleunitcode(String sampleunitcode) {
		this.sampleunitcode = sampleunitcode;
	}

	public String getSampleunitname() {
		return sampleunitname;
	}

	public void setSampleunitname(String sampleunitname) {
		this.sampleunitname = sampleunitname;
	}

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public String getDreceivedate() {
		return dreceivedate;
	}

	public void setDreceivedate(String dreceivedate) {
		this.dreceivedate = dreceivedate;
	}

	public String getCemployeeid() {
		return cemployeeid;
	}

	public void setCemployeeid(String cemployeeid) {
		this.cemployeeid = cemployeeid;
	}

	public String getCdeptid() {
		return cdeptid;
	}

	public void setCdeptid(String cdeptid) {
		this.cdeptid = cdeptid;
	}

	public String getCooperator() {
		return cooperator;
	}

	public void setCooperator(String cooperator) {
		this.cooperator = cooperator;
	}

	public String getSamplercode() {
		return samplercode;
	}

	public void setSamplercode(String samplercode) {
		this.samplercode = samplercode;
	}

	public String getSamplername() {
		return samplername;
	}

	public void setSamplername(String samplername) {
		this.samplername = samplername;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCupsourcebillid() {
		return cupsourcebillid;
	}

	public String getCupsourcebillrowid() {
		return cupsourcebillrowid;
	}

	public String getCsourcebillid() {
		return csourcebillid;
	}

	public String getCsourcebillrowid() {
		return csourcebillrowid;
	}

	public String getVdef16() {
		return vdef16;
	}

	public String getVdef17() {
		return vdef17;
	}

	public String getVdef18() {
		return vdef18;
	}

	public String getVdef19() {
		return vdef19;
	}

	public String getVdef20() {
		return vdef20;
	}

	public String getMemo() {
		return memo;
	}

	public String getMemo1() {
		return memo1;
	}

	public String getMemo2() {
		return memo2;
	}

	public String getMemo3() {
		return memo3;
	}

	public String getMemo4() {
		return memo4;
	}

	public void setCupsourcebillid(String cupsourcebillid) {
		this.cupsourcebillid = cupsourcebillid;
	}

	public void setCupsourcebillrowid(String cupsourcebillrowid) {
		this.cupsourcebillrowid = cupsourcebillrowid;
	}

	public void setCsourcebillid(String csourcebillid) {
		this.csourcebillid = csourcebillid;
	}

	public void setCsourcebillrowid(String csourcebillrowid) {
		this.csourcebillrowid = csourcebillrowid;
	}

	public void setVdef16(String vdef16) {
		this.vdef16 = vdef16;
	}

	public void setVdef17(String vdef17) {
		this.vdef17 = vdef17;
	}

	public void setVdef18(String vdef18) {
		this.vdef18 = vdef18;
	}

	public void setVdef19(String vdef19) {
		this.vdef19 = vdef19;
	}

	public void setVdef20(String vdef20) {
		this.vdef20 = vdef20;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

	public void setMemo3(String memo3) {
		this.memo3 = memo3;
	}

	public void setMemo4(String memo4) {
		this.memo4 = memo4;
	}

	public double getDeduction() {
		return deduction;
	}

	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}

	public double getDeduction2() {
		return deduction2;
	}

	public void setDeduction2(double deduction2) {
		this.deduction2 = deduction2;
	}

	public String getOperatype() {
		return operatype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

}
