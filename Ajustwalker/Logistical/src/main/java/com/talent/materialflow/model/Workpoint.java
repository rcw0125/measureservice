package com.talent.materialflow.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("workpoint")
public class Workpoint extends BaseModel {

	
	private static final long serialVersionUID = 7174221037930474885L;
	
	private String workpointcode;
	
	private String workpointname;
	
	private String queryword;
	
	private String workpointip;
	
	private String workpointmac;
	
	private String workpointtime;
	
	private String registers;
	
	private String usermemo;
	
	private String sysmemo;
	
	private String approver;
	
	private String approvertime;
	
	private String begintime;
	
	private String endtime;
	
	private String ictype; 
	
	private String  rfidtype;
	
	private String ids;//审核id集合
	
	private String linkcode;
	
	private String linkname;
	
	private String linktype;
	
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
	public String getQueryword() {
		return queryword;
	}
	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}
	public String getWorkpointip() {
		return workpointip;
	}
	public void setWorkpointip(String workpointip) {
		this.workpointip = workpointip;
	}
	public String getWorkpointmac() {
		return workpointmac;
	}
	public void setWorkpointmac(String workpointmac) {
		this.workpointmac = workpointmac;
	}
	public String getWorkpointtime() {
		return workpointtime;
	}
	public void setWorkpointtime(String workpointtime) {
		this.workpointtime = workpointtime;
	}
	public String getUsermemo() {
		return usermemo;
	}
	public void setUsermemo(String usermemo) {
		this.usermemo = usermemo;
	}
	public String getSysmemo() {
		return sysmemo;
	}
	public void setSysmemo(String sysmemo) {
		this.sysmemo = sysmemo;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprovertime() {
		return approvertime;
	}
	public void setApprovertime(String approvertime) {
		this.approvertime = approvertime;
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
	public String getRegisters() {
		return registers;
	}
	public void setRegisters(String registers) {
		this.registers = registers;
	}
	public String getIctype() {
		return ictype;
	}
	public void setIctype(String ictype) {
		this.ictype = ictype;
	}
	public String getRfidtype() {
		return rfidtype;
	}
	public void setRfidtype(String rfidtype) {
		this.rfidtype = rfidtype;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getLinkcode() {
		return linkcode;
	}
	public void setLinkcode(String linkcode) {
		this.linkcode = linkcode;
	}
	public String getLinkname() {
		return linkname;
	}
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}
	public String getLinktype() {
		return linktype;
	}
	public void setLinktype(String linktype) {
		this.linktype = linktype;
	}
}
