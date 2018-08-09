package com.talent.measure.model;

import java.io.Serializable;

public class PrintModel implements Serializable{
	
	private static final long serialVersionUID = 3319433828037738423L;

	private String  billcode = "";//票据编码
	
	private String  billxml = ""; //票据格式
	
    private String billscrip = "" ;// 票据描述 
    
	private String measuretype= ""; // 计量类型G毛重,T皮重,S净重  
	
    private String pageset= ""; //打印联类型如 计量、出门等 
    
    private String operationcode= "" ;//操作人编码
    
    private String operationname= "";//操作人名称
    
	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}

	public String getOperationname() {
		return operationname;
	}

	public void setOperationname(String operationname) {
		this.operationname = operationname;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public String getBillxml() {
		return billxml;
	}

	public void setBillxml(String billxml) {
		this.billxml = billxml;
	}

	public String getBillscrip() {
		return billscrip;
	}

	public void setBillscrip(String billscrip) {
		this.billscrip = billscrip;
	}

	 
	public String getMeasuretype() {
		return measuretype;
	}

	public void setMeasuretype(String measuretype) {
		this.measuretype = measuretype;
	}

	public String getPageset() {
		return pageset;
	}

	public void setPageset(String pageset) {
		this.pageset = pageset;
	}
    
 
}