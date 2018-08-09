package com.talent.privilege.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
public class Resource implements Serializable {
	
	private static final long serialVersionUID = -932156568855169388L;

	private int id = -1;
	
	private int fid = -1;
	
	private String rolecode = "";
	
	private String usercode = "";
	
	private String resourcetype = "";
	
	private String resourcecode = "";
	
	private String resourcename = "";
	
	private String icon = "";
	
	private String link = "";
	
	private String resourcememo = "";
	
	@JsonIgnore
	private String createtime = "";
	
	@JsonIgnore
	private String createman = "";
	
	@JsonIgnore
	private String updatetime = "";
	
	@JsonIgnore
	private String updateman = "";
	
	private String ctrltype = "全部";
	
	private int isdisplay = -1;
	
	@JsonIgnore
	private int validflag = 1;
	
	private List<Resource> children = new ArrayList<Resource>();

	public int getIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(int isdisplay) {
		this.isdisplay = isdisplay;
	}

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

	public String getResourcetype() {
		return resourcetype;
	}

	public void setResourcetype(String resourcetype) {
		this.resourcetype = resourcetype;
	}

	public String getResourcecode() {
		return resourcecode;
	}

	public void setResourcecode(String resourcecode) {
		this.resourcecode = resourcecode;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public String getResourcememo() {
		return resourcememo;
	}

	public void setResourcememo(String resourcememo) {
		this.resourcememo = resourcememo;
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

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCtrltype() {
		return ctrltype;
	}

	public void setCtrltype(String ctrltype) {
		this.ctrltype = ctrltype;
	}
}