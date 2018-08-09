package com.talent.measure.model;

import java.io.Serializable;

public class MeasureTaskDoResult implements Serializable{
	
	private static final long serialVersionUID = -4928811982657855006L;
	
	private String equcode; //衡器编码
	
	private String equname; //衡器名称
	
	private String equtype;  //衡器类型
	
	private String id; //唯一	
	
	private String opname; //操作人名称
	
	private String opcode; //操作人编码
	
	private String carno;  //车号
	
	private double timecount; //耗时
	
	private long totalcount; //车数和统计
	
	private String taskdoresult; //任务处理结果
	
	private String memo; //其它备注信息
	
	private String taskbegintime;  //任务开始时间
	
	private String taskendtime; //任务结束时间
	private String seatid ;
	private String seatname;
	
	
	
	public String getSeatid() {
		return seatid;
	}
	public void setSeatid(String seatid) {
		this.seatid = seatid;
	}
	public String getSeatname() {
		return seatname;
	}
	public void setSeatname(String seatname) {
		this.seatname = seatname;
	}
	public long getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(long totalcount) {
		this.totalcount = totalcount;
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
	public double getTimecount() {
		return timecount;
	}
	public void setTimecount(double timecount) {
		this.timecount = timecount;
	}
	public String getTaskdoresult() {
		return taskdoresult;
	}
	public void setTaskdoresult(String taskdoresult) {
		this.taskdoresult = taskdoresult;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTaskbegintime() {
		return taskbegintime;
	}
	public void setTaskbegintime(String taskbegintime) {
		this.taskbegintime = taskbegintime;
	}
	public String getTaskendtime() {
		return taskendtime;
	}
	public void setTaskendtime(String taskendtime) {
		this.taskendtime = taskendtime;
	}
	 
	
	

}
