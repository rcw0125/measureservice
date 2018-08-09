package com.talent.measure.model;

public class PrintLog {
	
	private String matchid; //物流号
	private String equname; //打印衡器
	private String  opname; //打印人
	private String createtime; //打印时间
	private String printtype;  //打印备注
	public String getMatchid() {
		return matchid;
	}
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
	public String getEquname() {
		return equname;
	}
	public void setEquname(String equname) {
		this.equname = equname;
	}
	public String getOpname() {
		return opname;
	}
	public void setOpname(String opname) {
		this.opname = opname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getPrinttype() {
		return printtype;
	}
	public void setPrinttype(String printtype) {
		this.printtype = printtype;
	}
	
	

}
