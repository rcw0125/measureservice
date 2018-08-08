package com.talent.privilege.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;
import com.talent.core.privilege.model.ResourceUser;
import com.talent.core.privilege.model.UserOrgan;
import com.talent.core.privilege.model.UserRole;

@Entity
public class UserJSP extends BaseModel{

	private static final long serialVersionUID = 8397657449242617762L;

	@Fields(name="用户编码",sn=1)
	@Column(name="USERNAME")
	private String username;
	
	@Fields(name="用户名称",sn=2)
	@Column(name="DISPLAYNAME")
	private String displayname = "";
	
	private String password;
	
	@Transient
	private String repassword = "";
	
	@Transient
	private String prepassword = "";
	
	@Fields(name="性别",sn=3)
	@Column(name="SEX")
	private String sex = "男";
	
	@Fields(name="民族",sn=4)
	@Column(name="NATION")
	private String nation = "汉族";
	
	@Fields(name="是否禁用",sn=5)
	@Column(name="SERVICING")
	private String servicing = "1";
	
	@Fields(name="年龄",sn=6)
	@Column(name="AGE")
	private int age = 30;

	@Fields(name="联系电话",sn=7)
	@Column(name="PHONE")
	private String phone;
	
	@Fields(name="电子邮箱",sn=8)
	@Column(name="EMAIL")
	private String email;

	@Fields(name="最后登录时间",sn=8)
	@Column(name="LASTLOGINTIME")
	private Date lastLoginTime = null;
	
	@Column(name="userimage")
	private String userimage = "";
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},targetEntity=UserRole.class,fetch=FetchType.EAGER)
	@JoinColumn(name="userid",updatable=false,insertable=false)
    private List<UserRole> roleUsers = new ArrayList<UserRole>();
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},targetEntity=UserOrgan.class,fetch=FetchType.EAGER)
	@JoinColumn(name="userid",updatable=false,insertable=false)
	private List<UserOrgan> organUsers = new ArrayList<UserOrgan>();
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},targetEntity=ResourceUser.class,fetch=FetchType.EAGER)
	@JoinColumn(name="userid",updatable=false,insertable=false)
    private List<ResourceUser> resourceUsers = new ArrayList<ResourceUser>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getPrepassword() {
		return prepassword;
	}

	public void setPrepassword(String prepassword) {
		this.prepassword = prepassword;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getUserimage() {
		return userimage;
	}

	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}

	public List<UserRole> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(List<UserRole> roleUsers) {
		this.roleUsers = roleUsers;
	}

	public List<UserOrgan> getOrganUsers() {
		return organUsers;
	}

	public void setOrganUsers(List<UserOrgan> organUsers) {
		this.organUsers = organUsers;
	}

	public List<ResourceUser> getResourceUsers() {
		return resourceUsers;
	}

	public void setResourceUsers(List<ResourceUser> resourceUsers) {
		this.resourceUsers = resourceUsers;
	}
}