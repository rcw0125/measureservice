package com.talent.measure.model;

public class Reportunit {
	private int id;//id值	
	private String classes;//类型
	private String unitname;//单位
	private String productline;//产线
	private String flag; //标记
	private String seq; //顺序
	private String operatype;//查询的业务类型
	private String createman;
	private String createdate;
	private String updateman;
	private String updatedate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getProductline() {
		return productline;
	}
	public void setProductline(String productline) {
		this.productline = productline;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	
	
}
