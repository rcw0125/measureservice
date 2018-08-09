package com.talent.measure.model;

import java.io.Serializable;

public class Mointor implements Serializable {
	
	private static final long serialVersionUID = -7933132779969769335L;

	private int id;
	
	private String weightname = "";
	
	private String weightcode = "";
	
	private int weightcount = 0;
	
	private int timecount = 0;

	private String errormsg = "";
	
	private String stacktrace = "";
	private String begintime;
	private String endtime;
	private String createdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTimecount() {
		return timecount;
	}

	public void setTimecount(int timecount) {
		this.timecount = timecount;
	}

	public int getWeightcount() {
		return weightcount;
	}

	public void setWeightcount(int weightcount) {
		this.weightcount = weightcount;
	}

	public String getWeightname() {
		return weightname;
	}

	public void setWeightname(String weightname) {
		this.weightname = weightname;
	}

	public String getWeightcode() {
		return weightcode;
	}

	public void setWeightcode(String weightcode) {
		this.weightcode = weightcode;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
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

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
}