package com.talent.report.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("statistics")
public class Statistics extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 747146627779842839L;
	private String operatype; //业务类型
	private String classes;//类别
	private String materialname;//物料
	private double daysuttle;//日累计
	private double monthsuttle;//月累计
	private String datetime; //当前日期
	private String monthtime;//当前月份
	private String productline;
	private String unitname;
	private String seq;
	private String flag;
	
	
	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getProductline() {
		return productline;
	}
	public void setProductline(String productline) {
		this.productline = productline;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getMonthtime() {
		return monthtime;
	}
	public void setMonthtime(String monthtime) {
		this.monthtime = monthtime;
	}
	public String getOperatype() {
		return operatype;
	}
	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public double getDaysuttle() {
		return daysuttle;
	}
	public void setDaysuttle(double daysuttle) {
		this.daysuttle = daysuttle;
	}
	public double getMonthsuttle() {
		return monthsuttle;
	}
	public void setMonthsuttle(double monthsuttle) {
		this.monthsuttle = monthsuttle;
	}
	
	
	
	

}
