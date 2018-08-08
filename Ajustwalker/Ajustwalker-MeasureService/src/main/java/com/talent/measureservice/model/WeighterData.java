package com.talent.measureservice.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("weighterdata")
public class WeighterData extends BaseModel {

	/**
    * 
    */
	private static final long serialVersionUID = -7985723305626568946L;

	private String carno = "";

	private String matchid = "";

	private String clientid = "";

	private String begintime = "";

	private String endtime = "";

	private String realdata = "";

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

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
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

	public String getRealdata() {
		return realdata;
	}

	public void setRealdata(String realdata) {
		this.realdata = realdata;
	}

}
