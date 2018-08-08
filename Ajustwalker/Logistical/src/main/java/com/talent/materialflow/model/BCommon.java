package com.talent.materialflow.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("bcommon")
public class BCommon implements Serializable {

	private static final long serialVersionUID = -9071980057636460965L;

	private String planid;
	
	private String matchid;

	private String saleitemid;

	private String orderno;

	private String taskcode;

	private String carno;

	private String materialcode;

	private String materialname;

	private String sourcecode;

	private String sourcename;

	private String targetcode;

	private String targetname;

	private String shipcode;

	private String ship;

	private String storecode;

	private String storename;

	private String searchText;

	private String documentcode;

	private String routeid;

	private int auditlevel;

	private int materiallflow;

	private String operatype;

	private String operaname;

	private String materialspec;
	
	private String measureunit;
	
	private String steelname;
	
	private String pictureno;
	
	private String prodline;
	
	private String usermemo;
	
	private double snumber=0;
	
	private String arrivetime;
	
	private String createman; //异常操作人
	
	private String documentname;//业务名称

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getSaleitemid() {
		return saleitemid;
	}

	public void setSaleitemid(String saleitemid) {
		this.saleitemid = saleitemid;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public String getMaterialcode() {
		return materialcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public String getSourcecode() {
		return sourcecode;
	}

	public void setSourcecode(String sourcecode) {
		this.sourcecode = sourcecode;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getTargetcode() {
		return targetcode;
	}

	public void setTargetcode(String targetcode) {
		this.targetcode = targetcode;
	}

	public String getTargetname() {
		return targetname;
	}

	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}

	public String getShipcode() {
		return shipcode;
	}

	public void setShipcode(String shipcode) {
		this.shipcode = shipcode;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getDocumentcode() {
		return documentcode;
	}

	public void setDocumentcode(String documentcode) {
		this.documentcode = documentcode;
	}

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public int getAuditlevel() {
		return auditlevel;
	}

	public void setAuditlevel(int auditlevel) {
		this.auditlevel = auditlevel;
	}

	public int getMateriallflow() {
		return materiallflow;
	}

	public void setMateriallflow(int materiallflow) {
		this.materiallflow = materiallflow;
	}

	public String getOperatype() {
		return operatype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

	public String getOperaname() {
		return operaname;
	}

	public void setOperaname(String operaname) {
		this.operaname = operaname;
	}

	public String getMaterialspec() {
		return materialspec;
	}

	public void setMaterialspec(String materialspec) {
		this.materialspec = materialspec;
	}

	public String getMeasureunit() {
		return measureunit;
	}

	public void setMeasureunit(String measureunit) {
		this.measureunit = measureunit;
	}

	public String getSteelname() {
		return steelname;
	}

	public void setSteelname(String steelname) {
		this.steelname = steelname;
	}

	public String getPictureno() {
		return pictureno;
	}

	public void setPictureno(String pictureno) {
		this.pictureno = pictureno;
	}

	public String getProdline() {
		return prodline;
	}

	public void setProdline(String prodline) {
		this.prodline = prodline;
	}

	public String getUsermemo() {
		return usermemo;
	}

	public void setUsermemo(String usermemo) {
		this.usermemo = usermemo;
	}

	public double getSnumber() {
		return snumber;
	}

	public void setSnumber(double snumber) {
		this.snumber = snumber;
	}

	public String getArrivetime() {
		return arrivetime;
	}

	public void setArrivetime(String arrivetime) {
		this.arrivetime = arrivetime;
	}



	public String getCreateman() {
		return createman;
	}

	public void setCreateman(String createman) {
		this.createman = createman;
	}

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	public String getDocumentname() {
		return documentname;
	}

	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}
	
	

}
