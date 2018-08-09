package com.talent.privilege.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable {
	
	private static final long serialVersionUID = 7020874957667495328L;

	private int id = -1;
	
	private String rolecode = "";
	
	private String usercode = "";
	
	private String username = "";
	
	private String organcode = "";
	
	private String password = ""; 
	
	private String repassword = "";
	
	private String prepassword = "";
	
	private String sex = "";
	
	private String nation = "";
	
	private String servicing = "";
	
	private int age = 0;
	
	private int validflag = 1;
	
	@JsonIgnore
	private String createtime = "";
	
	@JsonIgnore
	private String createman = "";
	
	@JsonIgnore
	private String updatetime = "";
	
	@JsonIgnore
	private String updateman = "";
	
	private String usermemo = "";
	
	@JsonIgnore
	private int isdisplay = -1; //对应resource里面的isdisplay，用于查询资源是否在本系统或类似在计量坐席的外部系统
	
	public int getIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(int isdisplay) {
		this.isdisplay = isdisplay;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

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

	public String getOrgancode() {
		return organcode;
	}

	public void setOrgancode(String organcode) {
		this.organcode = organcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrepassword() {
		return prepassword;
	}

	public void setPrepassword(String prepassword) {
		this.prepassword = prepassword;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public int getValidflag() {
		return validflag;
	}

	public void setValidflag(int validflag) {
		this.validflag = validflag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getServicing() {
		return servicing;
	}

	public void setServicing(String servicing) {
		this.servicing = servicing;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getCreateman() {
		return createman;
	}

	public void setCreateman(String createman) {
		this.createman = createman;
	}

	public String getUpdateman() {
		return updateman;
	}

	public void setUpdateman(String updateman) {
		this.updateman = updateman;
	}

	public String getUsermemo() {
		return usermemo;
	}

	public void setUsermemo(String usermemo) {
		this.usermemo = usermemo;
	}
}
