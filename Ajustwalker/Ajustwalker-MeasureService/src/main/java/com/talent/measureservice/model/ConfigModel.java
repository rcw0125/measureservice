package com.talent.measureservice.model;

import org.apache.ibatis.type.Alias;

@Alias("configmodel")
public class ConfigModel {

	private String operatype = "";

	private int orderno = 0;

	private String displayname = "";

	private String fieldname = "";

	private int isdisplay = 0;

	private int labeltype = 0;

	private String points = "";

	private int aboutweight = 0; // 标示重量相关的字段

	private int quicksuggest = 0; // 是否需要计量员录入时快速提示

	private String quicksuggesturl = ""; // 计量员录入快速提示请求地址

	private int writeable = 0; // 是否需要计量员录入时快速提示

	private String optrfield = ""; // 不存数据库

	private String optrvalue = ""; // 不存数据库

	private String direction = ""; // 不存数据库

	public String getOperatype() {
		return operatype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public int getIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(int isdisplay) {
		this.isdisplay = isdisplay;
	}

	public int getLabeltype() {
		return labeltype;
	}

	public void setLabeltype(int labeltype) {
		this.labeltype = labeltype;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public int getAboutweight() {
		return aboutweight;
	}

	public void setAboutweight(int aboutweight) {
		this.aboutweight = aboutweight;
	}

	public int getQuicksuggest() {
		return quicksuggest;
	}

	public void setQuicksuggest(int quicksuggest) {
		this.quicksuggest = quicksuggest;
	}

	public String getQuicksuggesturl() {
		return quicksuggesturl;
	}

	public void setQuicksuggesturl(String quicksuggesturl) {
		this.quicksuggesturl = quicksuggesturl;
	}

	public int getWriteable() {
		return writeable;
	}

	public void setWriteable(int writeable) {
		this.writeable = writeable;
	}

	public String getOptrfield() {
		return optrfield;
	}

	public void setOptrfield(String optrfield) {
		this.optrfield = optrfield;
	}

	public String getOptrvalue() {
		return optrvalue;
	}

	public void setOptrvalue(String optrvalue) {
		this.optrvalue = optrvalue;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
