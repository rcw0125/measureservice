package com.talent.measure.model;

import java.io.Serializable;

public class MeasureRule implements Serializable{
	
	private static final long serialVersionUID = 3406507573332728721L;

	private int id = -1;
	
	private String opertype = "";
    
    private String measuretype = "";
    
    private String materialcode = "全部";
    private String operaname="";
    
    
    
	public String getOperaname() {
		return operaname;
	}

	public void setOperaname(String operaname) {
		this.operaname = operaname;
	}

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

	public String getMeasuretype() {
		return measuretype;
	}

	public void setMeasuretype(String measuretype) {
		this.measuretype = measuretype;
	}

	public String getMaterialcode() {
		return materialcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}
}