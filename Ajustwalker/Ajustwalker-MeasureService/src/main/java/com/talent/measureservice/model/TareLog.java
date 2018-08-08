package com.talent.measureservice.model;



import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;

@Alias("tarelog")
public class TareLog {
	
	@Fields(name = "车号")
	private String carno;
	
	@Fields(name = "物流号")
	private String matchid;
	
	@Fields(name = "皮重")
	private double tare;
	
	@Fields(name = "皮重时间")
	private String taretime;
	
	@Fields(name = "皮重衡器id")
	private String tareweighid;
	
	@Fields(name = "皮重衡器")
	private String tareweigh;
	
	@Fields(name = "皮重操作员编码")
	private String tareoperacode;
	
	@Fields(name = "皮重操作员")
	private String tareoperaname;
	
	@Fields(name = "皮重日志id")
	private String tarelogid;
	
	@Fields(name = "差值")
	private double dvalue;
	
	@Fields(name = "类型")
	private String cartype;
	
	@Fields(name = "开始时间")
	private String begintime;
	
	@Fields(name = "结束时间")
	private String endtime;

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
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

	public String getTarelogid() {
		return tarelogid;
	}

	public void setTarelogid(String tarelogid) {
		this.tarelogid = tarelogid;
	}

	public double getDvalue() {
		return dvalue;
	}

	public void setDvalue(double dvalue) {
		this.dvalue = dvalue;
	}

	
	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
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

}
