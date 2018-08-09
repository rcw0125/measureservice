package com.talent.measure.model;

import java.io.Serializable;

public class WeighterData implements Serializable{
	
	private static final long serialVersionUID = -572462318024043132L;

	private int id = 0;

	private String carno = "";
	
	private String matchid = "";
	
	private String clientid = "";
	
	private String begintime = "";
	
	private String endtime = "";
	
	private String realdata = ""; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}
}