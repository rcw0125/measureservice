package com.talent.privilege.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.talent.core.model.BaseModel;

@Alias("UserMS")
@Entity
@Table(name = "P_USER_T")
public class UserMS extends BaseModel {
	
	private static final long serialVersionUID = 7020874957667495328L;

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
	
	

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
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

}
