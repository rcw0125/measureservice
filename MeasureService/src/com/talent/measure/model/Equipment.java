package com.talent.measure.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Equipment implements Serializable {
	
	private static final long serialVersionUID = -5907118791826144313L;

	private int id = 0;
	
	private String equcode = ""; //衡器编码
	
	private String equname = ""; //衡器名称
	
	private int validflag = 1;
	
	private String equclass = ""; //1：模版  2：设备
	
	private String createman = "";
	
	private String equunit = "";
	
	private String equpostion = "";
	
	private String equtype = "";
	
	private Date createdate = Calendar.getInstance().getTime();
	
	private Date uptime = Calendar.getInstance().getTime();
	
	private String paramodel = "";
	
	private String ip = "";  //唯一
	private Double range;//量程
	private String createtime;
	private String updateman;
	private String updatedate;
	
	

	public Double getRange() {
		return range;
	}

	public void setRange(Double range) {
		this.range = range;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEqucode() {
		return equcode;
	}

	public void setEqucode(String equcode) {
		this.equcode = equcode;
	}

	public String getEquname() {
		return equname;
	}

	public void setEquname(String equname) {
		this.equname = equname;
	}

	public int getValidflag() {
		return validflag;
	}

	public void setValidflag(int validflag) {
		this.validflag = validflag;
	}

	public String getEquclass() {
		return equclass;
	}

	public void setEquclass(String equclass) {
		this.equclass = equclass;
	}

	public String getCreateman() {
		return createman;
	}

	public void setCreateman(String createman) {
		this.createman = createman;
	}

	public String getEquunit() {
		return equunit;
	}

	public void setEquunit(String equunit) {
		this.equunit = equunit;
	}

	public String getEqupostion() {
		return equpostion;
	}

	public void setEqupostion(String equpostion) {
		this.equpostion = equpostion;
	}

	public String getEqutype() {
		return equtype;
	}

	public void setEqutype(String equtype) {
		this.equtype = equtype;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public String getParamodel() {
		return paramodel;
	}

	public void setParamodel(String paramodel) {
		this.paramodel = paramodel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdateman() {
		return updateman;
	}

	public void setUpdateman(String updateman) {
		this.updateman = updateman;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	
}
