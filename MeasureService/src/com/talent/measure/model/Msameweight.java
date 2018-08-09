package com.talent.measure.model;

public class Msameweight {
	private String matchid; // 相似重量的物流号
	private String sametype; // 相似重量的计量类型
	private String fmatchid; // 计量时物流号
	private String memo; // 处理结果
	private String mtype; // 查询相似重量时计量的类型
	private String matchidlist; // 相似重量物流号集合
	private String carno; // 车号
	private String taskcode; // 业务号
	private String planid; // 计划号
	private String materialname; // 物料名称
	private String sourcename; // 供货单位名称
	private String targetname; // 收货单位名称
	private String tare; // 皮重
	private String taretime; // 皮重时间
	private String tareweigh; // 皮重衡器
	private String tareweighid; // 皮重衡器id
	private String gross; // 毛重
	private String grosstime; // 毛重时间
	private String grossweigh; // 毛重衡器
	private String grossweighid; // 毛重衡器id
	private String suttle; // 净重衡器
	private String suttletime; // 净重时间
	private String suttleweigh; // 净重衡器
	private String suttleweighid;// 净重衡器id
	private String operatype; // 业务类型
	private String materialcode;// 物料编码
	private String grossoperaname;// 毛重计量员名称
	private String tareoperaname;// 皮重计量员名称
	private String suttleoperaname;// 净重计量员名称
	private String begintime;
	private String endtime;
	private String selecttime;

	private String routeid;
	private String nodecode;
	private String nodename;
	private String types;
	private String workpointcode; // 作业点编码
	private String workpointname; // 作业点名称

	public String getSelecttime() {
		return selecttime;
	}

	public void setSelecttime(String selecttime) {
		this.selecttime = selecttime;
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

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getTargetname() {
		return targetname;
	}

	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}

	public String getTare() {
		return tare;
	}

	public void setTare(String tare) {
		this.tare = tare;
	}

	public String getTaretime() {
		return taretime;
	}

	public void setTaretime(String taretime) {
		this.taretime = taretime;
	}

	public String getTareweigh() {
		return tareweigh;
	}

	public void setTareweigh(String tareweigh) {
		this.tareweigh = tareweigh;
	}

	public String getTareweighid() {
		return tareweighid;
	}

	public void setTareweighid(String tareweighid) {
		this.tareweighid = tareweighid;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getGrosstime() {
		return grosstime;
	}

	public void setGrosstime(String grosstime) {
		this.grosstime = grosstime;
	}

	public String getGrossweigh() {
		return grossweigh;
	}

	public void setGrossweigh(String grossweigh) {
		this.grossweigh = grossweigh;
	}

	public String getGrossweighid() {
		return grossweighid;
	}

	public void setGrossweighid(String grossweighid) {
		this.grossweighid = grossweighid;
	}

	public String getSuttle() {
		return suttle;
	}

	public void setSuttle(String suttle) {
		this.suttle = suttle;
	}

	public String getSuttletime() {
		return suttletime;
	}

	public void setSuttletime(String suttletime) {
		this.suttletime = suttletime;
	}

	public String getSuttleweigh() {
		return suttleweigh;
	}

	public void setSuttleweigh(String suttleweigh) {
		this.suttleweigh = suttleweigh;
	}

	public String getSuttleweighid() {
		return suttleweighid;
	}

	public void setSuttleweighid(String suttleweighid) {
		this.suttleweighid = suttleweighid;
	}

	public String getOperatype() {
		return operatype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

	public String getMaterialcode() {
		return materialcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}

	public String getGrossoperaname() {
		return grossoperaname;
	}

	public void setGrossoperaname(String grossoperaname) {
		this.grossoperaname = grossoperaname;
	}

	public String getTareoperaname() {
		return tareoperaname;
	}

	public void setTareoperaname(String tareoperaname) {
		this.tareoperaname = tareoperaname;
	}

	public String getSuttleoperaname() {
		return suttleoperaname;
	}

	public void setSuttleoperaname(String suttleoperaname) {
		this.suttleoperaname = suttleoperaname;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getMatchidlist() {
		return matchidlist;
	}

	public void setMatchidlist(String matchidlist) {
		this.matchidlist = matchidlist;
	}

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	public String getSametype() {
		return sametype;
	}

	public void setSametype(String sametype) {
		this.sametype = sametype;
	}

	public String getFmatchid() {
		return fmatchid;
	}

	public void setFmatchid(String fmatchid) {
		this.fmatchid = fmatchid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getNodecode() {
		return nodecode;
	}

	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getWorkpointcode() {
		return workpointcode;
	}

	public void setWorkpointcode(String workpointcode) {
		this.workpointcode = workpointcode;
	}

	public String getWorkpointname() {
		return workpointname;
	}

	public void setWorkpointname(String workpointname) {
		this.workpointname = workpointname;
	}
    
}
