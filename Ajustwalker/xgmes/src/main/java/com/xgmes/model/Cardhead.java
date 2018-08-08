package com.xgmes.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("cardhead")
public class Cardhead extends BaseModel {

	
	private static final long serialVersionUID = 5968399117720494718L;
	private String briefname;
	private String validflag;
	private String orderindex;
	private String queryword;
	private String createdate;
	private String createman;
	private String updatedate;
	private String updateman;
	private String validman;
	private String validtime;
	private String sysmemo;

	public String getBriefname() {
		return briefname;
	}

	public void setBriefname(String briefname) {
		this.briefname = briefname;
	}

	public String getValidflag() {
		return validflag;
	}

	public void setValidflag(String validflag) {
		this.validflag = validflag;
	}

	public String getOrderindex() {
		return orderindex;
	}

	public void setOrderindex(String orderindex) {
		this.orderindex = orderindex;
	}

	public String getQueryword() {
		return queryword;
	}

	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCreateman() {
		return createman;
	}

	public void setCreateman(String createman) {
		this.createman = createman;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getUpdateman() {
		return updateman;
	}

	public void setUpdateman(String updateman) {
		this.updateman = updateman;
	}

	public String getValidman() {
		return validman;
	}

	public void setValidman(String validman) {
		this.validman = validman;
	}

	public String getValidtime() {
		return validtime;
	}

	public void setValidtime(String validtime) {
		this.validtime = validtime;
	}

	public String getSysmemo() {
		return sysmemo;
	}

	public void setSysmemo(String sysmemo) {
		this.sysmemo = sysmemo;
	}

}
