package com.talent.materialflow.model;

import java.io.Serializable;


import org.apache.ibatis.type.Alias;
@Alias("logisticalRule")

public class LogisticalRule implements Serializable {

	private static final long serialVersionUID = 2670403399643295271L;

	private int id = -1;

	private String opertype = "";

	private String logisticaltype = "";

	private String materialcode = "全部";

	private String operaname = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpertype() {
		return opertype;
	}

	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}

	public String getLogisticaltype() {
		return logisticaltype;
	}

	public void setLogisticaltype(String logisticaltype) {
		this.logisticaltype = logisticaltype;
	}

	public String getMaterialcode() {
		return materialcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}

	public String getOperaname() {
		return operaname;
	}

	public void setOperaname(String operaname) {
		this.operaname = operaname;
	}
}
