package com.talent.privilege.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Role implements Serializable {
	
	private static final long serialVersionUID = 6997479971013627090L;

	private int id = -1;
	
	private String usercode = "";
	
	private String rolecode = "";
	
	private String rolename = "";
	
	private String rolememo = "";
	
	@JsonIgnore
	private String createtime = "";
	
	@JsonIgnore
	private String createman = "";
	
	@JsonIgnore
	private String updatetime = "";
	
	@JsonIgnore
	private String updateman = "";
	
	private int validflag = 1;
	
	private int selected = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRolememo() {
		return rolememo;
	}

	public void setRolememo(String rolememo) {
		this.rolememo = rolememo;
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

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}
}
