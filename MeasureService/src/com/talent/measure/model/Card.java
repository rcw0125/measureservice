package com.talent.measure.model;

import java.io.Serializable;

public class Card implements Serializable {
	
	private static final long serialVersionUID = 3445720074889491589L;

	private String  icid = "";//IC卡号
	
	private String  rfidid = ""; //RFID卡号
	
    private String carno = "" ;// 车号
	
	private int  flag = 0;//状态：0 错误，1成功
	
	private String msg =""; // 提示信息 
	
	private int  icflag;// 0表示只用IC卡，1表示只用RFID卡，2 表示双卡确认
	
	private int validflag = 0;//是否有效0作废，1初始化，2发给单位，3发给司机。挂失后变为作废，退卡后变为初始化
    
    private String cardid = ""; //卡号id,卡的唯一标示号
    
    private String  recordtype =""; //  0 IC卡号; 1RFID;2 车号
    
    private String weightno=""; //衡器编码
    
    private String cartype="0";

	public String getIcid() {
		return icid;
	}

	public void setIcid(String icid) {
		this.icid = icid;
	}

	public String getRfidid() {
		return rfidid;
	}

	public void setRfidid(String rfidid) {
		this.rfidid = rfidid;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getIcflag() {
		return icflag;
	}

	public void setIcflag(int icflag) {
		this.icflag = icflag;
	}

	public int getValidflag() {
		return validflag;
	}

	public void setValidflag(int validflag) {
		this.validflag = validflag;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}

	public String getWeightno() {
		return weightno;
	}

	public void setWeightno(String weightno) {
		this.weightno = weightno;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
     

}