package com.talent.measureservice.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("seatclient")
public class SeatClientId extends BaseModel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1056028801596593578L;

	private String seatid; //坐席编号
	
	private String seatstate; //坐席状态 0 空闲，1等待任务，2分配任务，3处理任务，4暂停坐席
	
	private String seattype;  //坐席类型 RC 汽车，RT火车，RI铁水 
	
	private String seatright; //坐席权限 0 无权 1 计量员 2 调度
	
	private String taskid; //坐席任务ID
	
	private String seatoperator; //计量员
	
	private String seatip;  //坐席IP
	
	private String seattime; //进入坐席时间
	
	private String seatsubject; //坐席主题
	
	private String lastworktime; //最后工作时间
	
	private String onlineindex;  //坐席在线确认码
	
	private String indexcount; //座席确认次数
	
	private String matchcount; //任务分配确认次数，用户如果不响应分配，任务自动重新分配
	
	private String seatname; //座席名称
	
	private String clientid; //控制终端ID
	
	private String validflag; //0无效 1有效
	
	private String equcode;//秤点编码
	
	private String equname;//秤点名称
	
	private String equtype;//称点类型
	
	private String isinseat;//是否在该坐席内
	
	private String versionnum;//配置文件版本号

	public String getSeatid() {
		return seatid;
	}

	public void setSeatid(String seatid) {
		this.seatid = seatid;
	}

	public String getSeatstate() {
		return seatstate;
	}

	public void setSeatstate(String seatstate) {
		this.seatstate = seatstate;
	}

	public String getSeattype() {
		return seattype;
	}

	public void setSeattype(String seattype) {
		this.seattype = seattype;
	}

	public String getSeatright() {
		return seatright;
	}

	public void setSeatright(String seatright) {
		this.seatright = seatright;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getSeatoperator() {
		return seatoperator;
	}

	public void setSeatoperator(String seatoperator) {
		this.seatoperator = seatoperator;
	}

	public String getSeatip() {
		return seatip;
	}

	public void setSeatip(String seatip) {
		this.seatip = seatip;
	}

	public String getSeattime() {
		return seattime;
	}

	public void setSeattime(String seattime) {
		this.seattime = seattime;
	}

	public String getSeatsubject() {
		return seatsubject;
	}

	public void setSeatsubject(String seatsubject) {
		this.seatsubject = seatsubject;
	}

	public String getLastworktime() {
		return lastworktime;
	}

	public void setLastworktime(String lastworktime) {
		this.lastworktime = lastworktime;
	}

	public String getOnlineindex() {
		return onlineindex;
	}

	public void setOnlineindex(String onlineindex) {
		this.onlineindex = onlineindex;
	}

	public String getIndexcount() {
		return indexcount;
	}

	public void setIndexcount(String indexcount) {
		this.indexcount = indexcount;
	}

	public String getMatchcount() {
		return matchcount;
	}

	public void setMatchcount(String matchcount) {
		this.matchcount = matchcount;
	}

	public String getSeatname() {
		return seatname;
	}

	public void setSeatname(String seatname) {
		this.seatname = seatname;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getValidflag() {
		return validflag;
	}

	public void setValidflag(String validflag) {
		this.validflag = validflag;
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

	public String getIsinseat() {
		return isinseat;
	}

	public void setIsinseat(String isinseat) {
		this.isinseat = isinseat;
	}

	public String getVersionnum() {
		return versionnum;
	}

	public void setVersionnum(String versionnum) {
		this.versionnum = versionnum;
	}

	public String getEqutype() {
		return equtype;
	}

	public void setEqutype(String equtype) {
		this.equtype = equtype;
	}
	
	
}
