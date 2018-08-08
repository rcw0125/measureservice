package com.xgmes.model;


import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.core.model.BaseModel;

@Alias("trainstatus")
public class TrainStatus extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 625780745865458334L;
	
	
	private String linecode;//线别编码
	private String linename;//线别名称
	private String vreceivecode;//接车单号
	private String vtraincode;//车号
	private String vtraintype;//车型
	private String istatus;//车皮状态
	private String vinvname;//物料名称
	private String vstation;//发到站
	private String measstatus;//计量状态
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createdate = null;
	private String begintime;
	private String endtime;
	
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
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
	public String getLinecode() {
		return linecode;
	}
	public void setLinecode(String linecode) {
		this.linecode = linecode;
	}
	public String getLinename() {
		return linename;
	}
	public void setLinename(String linename) {
		this.linename = linename;
	}
	public String getVreceivecode() {
		return vreceivecode;
	}
	public void setVreceivecode(String vreceivecode) {
		this.vreceivecode = vreceivecode;
	}
	public String getVtraincode() {
		return vtraincode;
	}
	public void setVtraincode(String vtraincode) {
		this.vtraincode = vtraincode;
	}
	public String getVtraintype() {
		return vtraintype;
	}
	public void setVtraintype(String vtraintype) {
		this.vtraintype = vtraintype;
	}
	public String getIstatus() {
		return istatus;
	}
	public void setIstatus(String istatus) {
		this.istatus = istatus;
	}
	public String getVinvname() {
		return vinvname;
	}
	public void setVinvname(String vinvname) {
		this.vinvname = vinvname;
	}
	public String getVstation() {
		return vstation;
	}
	public void setVstation(String vstation) {
		this.vstation = vstation;
	}
	public String getMeasstatus() {
		return measstatus;
	}
	public void setMeasstatus(String measstatus) {
		this.measstatus = measstatus;
	}


}
