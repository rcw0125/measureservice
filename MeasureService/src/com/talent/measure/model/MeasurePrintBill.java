package com.talent.measure.model;

import java.io.Serializable;

public class MeasurePrintBill implements Serializable{
	
	private static final long serialVersionUID = 56118650940396993L;

	private String equcode; //衡器编码
	
	private String equname; //衡器名称
 
	private String id; //唯一	
	
	private String opname; //操作人名称
	
	private String opcode; //操作人编码
	
	private String carno;  //车号
 
	private String matchid; //物流号
	
	private String printtype; //打印类型
	
	private String printModelType = "";
	
	public String getPrintModelType() {
		return printModelType;
	}

	public void setPrintModelType(String printModelType) {
		this.printModelType = printModelType;
	}

	public String getPrinttype() {
		return printtype;
	}

	public void setPrinttype(String printtype) {
		this.printtype = printtype;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpname() {
		return opname;
	}

	public void setOpname(String opname) {
		this.opname = opname;
	}

	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
 
	 
	
	

}
