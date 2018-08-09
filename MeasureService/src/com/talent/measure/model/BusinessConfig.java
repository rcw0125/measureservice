package com.talent.measure.model;

public class BusinessConfig {
	private double weight; //重量
	private String weighttype;//重量类型：G毛重T皮重
	private String timetype;//重量类型：G毛重T皮重
	private String carno;//车号
	private String operatype;//业务类型
	private double sametareoutup;//相似皮重超差上限值 value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private double sametareoutdown;	//相似皮重超差下限值 value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private double samegrossoutup;	//相似毛重超差上限值 value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private double samegrossoutdown;	//	相似毛重超差下限值 value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private int samegrosstime;	//	查询多长时间以内的相似毛重 单位是分钟
	private int sametaretime;	//	查询多长时间以内的相似皮重 单位是分钟
	private double weightup; //重量上限
	private double weightdown; //重量下限
	private int weighttime;  //时间限制
	
	
	
	
	
	
	public String getTimetype() {
		return timetype;
	}
	public void setTimetype(String timetype) {
		this.timetype = timetype;
	}
	public double getWeightup() {
		return weightup;
	}
	public void setWeightup(double weightup) {
		this.weightup = weightup;
	}
	public double getWeightdown() {
		return weightdown;
	}
	public void setWeightdown(double weightdown) {
		this.weightdown = weightdown;
	}
	public int getWeighttime() {
		return weighttime;
	}
	public void setWeighttime(int weighttime) {
		this.weighttime = weighttime;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getWeighttype() {
		return weighttype;
	}
	public void setWeighttype(String weighttype) {
		this.weighttype = weighttype;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public String getOperatype() {
		return operatype;
	}
	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}
	public double getSametareoutup() {
		return sametareoutup;
	}
	public void setSametareoutup(double sametareoutup) {
		this.sametareoutup = sametareoutup;
	}
	public double getSametareoutdown() {
		return sametareoutdown;
	}
	public void setSametareoutdown(double sametareoutdown) {
		this.sametareoutdown = sametareoutdown;
	}
	public double getSamegrossoutup() {
		return samegrossoutup;
	}
	public void setSamegrossoutup(double samegrossoutup) {
		this.samegrossoutup = samegrossoutup;
	}
	public double getSamegrossoutdown() {
		return samegrossoutdown;
	}
	public void setSamegrossoutdown(double samegrossoutdown) {
		this.samegrossoutdown = samegrossoutdown;
	}
	public int getSamegrosstime() {
		return samegrosstime;
	}
	public void setSamegrosstime(int samegrosstime) {
		this.samegrosstime = samegrosstime;
	}
	public int getSametaretime() {
		return sametaretime;
	}
	public void setSametaretime(int sametaretime) {
		this.sametaretime = sametaretime;
	}

	
}
