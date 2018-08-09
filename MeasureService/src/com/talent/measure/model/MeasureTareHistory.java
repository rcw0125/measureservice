package com.talent.measure.model;

import java.io.Serializable;

public class MeasureTareHistory implements Serializable{
	
	private static final long serialVersionUID = 8156739629541296191L;

	private String carno; //车号
	
	private double tare; //皮重 
	
	private int selectcount;  //要查询的次数 
	
	private String taretime; //皮重日期
 
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public double getTare() {
		return tare;
	}
	public void setTare(double tare) {
		this.tare = tare;
	}
	public int getSelectcount() {
		return selectcount;
	}
	public void setSelectcount(int selectcount) {
		this.selectcount = selectcount;
	}
	public String getTaretime() {
		return taretime;
	}
	public void setTaretime(String taretime) {
		this.taretime = taretime;
	}
	 
	 

}
