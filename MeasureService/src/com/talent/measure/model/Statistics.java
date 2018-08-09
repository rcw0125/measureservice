package com.talent.measure.model;

public class Statistics {
	private String operatype; // 业务类型
	private String classes;// 类别
	private String materialname;// 物料
	private String ship;// 船名
	private double daysuttle;// 日累计
	private double monthsuttle;// 月累计
	private String datetime; // 当前日期
	private String monthtime;// 当前月份
	private String productline;
	private String unitname;
	private String seq;
	private String flag;
	private String matchid;// 物流号
	private String weightno;// 衡器号
	private String taskstatus;// 任务状态
	private String isorprint;
	private String printmsg;
	private String printstatus;
	
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

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
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

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	public String getWeightno() {
		return weightno;
	}

	public void setWeightno(String weightno) {
		this.weightno = weightno;
	}

	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	public String getIsorprint() {
		return isorprint;
	}

	public void setIsorprint(String isorprint) {
		this.isorprint = isorprint;
	}

	public String getPrintmsg() {
		return printmsg;
	}

	public void setPrintmsg(String printmsg) {
		this.printmsg = printmsg;
	}

	public String getPrintstatus() {
		return printstatus;
	}

	public void setPrintstatus(String printstatus) {
		this.printstatus = printstatus;
	}
    
}
