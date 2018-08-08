package com.talent.report.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("tareLog")
public class TareLog extends BaseModel{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 2418232087961834186L;
	private String carno;
	 private String matchid;
	 private double tare             ;
	 private String  taretime        ;
	 private String tareweighid       ;
	 private String tareweigh         ;
	 private String tareoperacode    ;
	 private String tareoperaname      ;
	 private String tarelogid         ;
	 private double dvalue;
	 private String types;
	 private String begintime;
	 private String endtime;
	 private String cartype;
	 
	 
	 
	 
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
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public double getDvalue() {
		return dvalue;
	}
	public void setDvalue(double dvalue) {
		this.dvalue = dvalue;
	}
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
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	 

}
