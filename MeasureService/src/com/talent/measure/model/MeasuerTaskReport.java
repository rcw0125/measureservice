package com.talent.measure.model;

import java.io.Serializable;

/**
 * @author asus
 *
 */
public class MeasuerTaskReport implements Serializable{
	
	private static final long serialVersionUID = 2772102351031709188L;
	
	private String equcode; //衡器编码
	
	private String equname; //衡器名称
	
	private String equtype;  //衡器类型
	
	private int tCount;  //皮重车数
	
	private int gCount;  //毛重车数 
	
	private double tSumWeight;  //皮重总数
	
	private double gSumWeight;  //毛重总数
	
	private String begintime; //查询时使用 开始日期
	
	private String endtime; //查询时使用 结束日期
	
	private String usercode;//用户编码
	
	private String username;//用户名称 
	 
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getEqutype() {
		return equtype;
	}
	public void setEqutype(String equtype) {
		this.equtype = equtype;
	}
	
	public int gettCount() {
		return tCount;
	}
	public void settCount(int tCount) {
		this.tCount = tCount;
	}
	public int getgCount() {
		return gCount;
	}
	public void setgCount(int gCount) {
		this.gCount = gCount;
	}
	public double gettSumWeight() {
		return tSumWeight;
	}
	public void settSumWeight(double tSumWeight) {
		this.tSumWeight = tSumWeight;
	}
	public double getgSumWeight() {
		return gSumWeight;
	}
	public void setgSumWeight(double gSumWeight) {
		this.gSumWeight = gSumWeight;
	}

}
