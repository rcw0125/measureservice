package com.talent.privilege.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Organ implements Serializable {
	
	private static final long serialVersionUID = -7193903686560514229L;

	private int id = -1;
	
	private int fid = -1;
	
	private String usercode = "";
	
	private String organcode = "";
	
	private String organname = "";
	
	private String organmemo = "";
	
	@JsonIgnore
	private String createtime = "";
	
	@JsonIgnore
	private String createman = "";
	
	@JsonIgnore
	private String updatetime = "";
	
	@JsonIgnore
	private String updateman = "";
	
	private int validflag = 1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getOrgancode() {
		return organcode;
	}

	public void setOrgancode(String organcode) {
		this.organcode = organcode;
	}

	public String getOrganname() {
		return organname;
	}

	public void setOrganname(String organname) {
		this.organname = organname;
	}

	public int getValidflag() {
		return validflag;
	}

	public void setValidflag(int validflag) {
		this.validflag = validflag;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreateman() {
		return createman;
	}

	public void setCreateman(String createman) {
		this.createman = createman;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdateman() {
		return updateman;
	}

	public void setUpdateman(String updateman) {
		this.updateman = updateman;
	}

	public String getOrganmemo() {
		return organmemo;
	}

	public void setOrganmemo(String organmemo) {
		this.organmemo = organmemo;
	}
}
