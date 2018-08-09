package com.talent.measure.model;

public class HandOver {
	
    private int id;
    private String datetime;//日期
	private String workgroup="-1"; // 班别';
	private String workgroupsqe="-1"; // 班次';
	private String handoverman; // 交班人';
	private String successor; // 接班人';
	private String equipmentstatus; // 设备运行情况';
	private String handroomstatus; // 操作间卫生情况';
	private String general; // 综合情况';
	private String nextworknote; // 下班注意事项';
	private String createman; // 创建人';
	private String createdate; // 创建时间';
	private String updateman; // 修改人';
	private String updatedate; // 修改人';
	private String cancelman; // 作废人';
	private String canceldate; // 作废时间';
	private String validflag="-1"; // 状态 0作废 1正常';
	private String begintime;
	private String endtime;
	
	
	
	
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
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWorkgroup() {
		return workgroup;
	}
	public void setWorkgroup(String workgroup) {
		this.workgroup = workgroup;
	}
	public String getWorkgroupsqe() {
		return workgroupsqe;
	}
	public void setWorkgroupsqe(String workgroupsqe) {
		this.workgroupsqe = workgroupsqe;
	}
	public String getHandoverman() {
		return handoverman;
	}
	public void setHandoverman(String handoverman) {
		this.handoverman = handoverman;
	}
	public String getSuccessor() {
		return successor;
	}
	public void setSuccessor(String successor) {
		this.successor = successor;
	}
	public String getEquipmentstatus() {
		return equipmentstatus;
	}
	public void setEquipmentstatus(String equipmentstatus) {
		this.equipmentstatus = equipmentstatus;
	}
	public String getHandroomstatus() {
		return handroomstatus;
	}
	public void setHandroomstatus(String handroomstatus) {
		this.handroomstatus = handroomstatus;
	}
	public String getGeneral() {
		return general;
	}
	public void setGeneral(String general) {
		this.general = general;
	}
	public String getNextworknote() {
		return nextworknote;
	}
	public void setNextworknote(String nextworknote) {
		this.nextworknote = nextworknote;
	}
	public String getCreateman() {
		return createman;
	}
	public void setCreateman(String createman) {
		this.createman = createman;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdateman() {
		return updateman;
	}
	public void setUpdateman(String updateman) {
		this.updateman = updateman;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getCancelman() {
		return cancelman;
	}
	public void setCancelman(String cancelman) {
		this.cancelman = cancelman;
	}
	public String getCanceldate() {
		return canceldate;
	}
	public void setCanceldate(String canceldate) {
		this.canceldate = canceldate;
	}
	public String getValidflag() {
		return validflag;
	}
	public void setValidflag(String validflag) {
		this.validflag = validflag;
	}
	
	

}
