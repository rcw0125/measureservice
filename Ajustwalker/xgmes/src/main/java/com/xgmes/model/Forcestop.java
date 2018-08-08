package com.xgmes.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("forcestop")
public class Forcestop extends BaseModel {
	
	private static final long serialVersionUID = -3662557795245331199L;
	
	private String matchid;
	
	private String carno;
	
	private String stoplink;
	
	private String unitcode;
	
	private String unitname;
	
	private String materialname;
	
	private String sourcename;
	
	private String targetname;
	
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

	public String getStoplink() {
		return stoplink;
	}
	public void setStoplink(String stoplink) {
		this.stoplink = stoplink;
	}

	public String getUnitcode() {
		return unitcode;
	}
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
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
	
	
}
