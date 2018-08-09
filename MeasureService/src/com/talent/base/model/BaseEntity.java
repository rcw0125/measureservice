package com.talent.base.model;

import java.util.ArrayList;
import java.util.List;

public class BaseEntity {
	
	private int id = 0;
	
	private int fid = 0;
	
	private String text = "";
	
	private String href = "";
	
	private List<Object> nodes = new ArrayList<Object>();
	
	private List<String> tags = new ArrayList<String>();
	
	private int page = 0;
	
	private int rows = 0;
	
	private int validflag = 1;
	
	private String memo = "";

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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getValidflag() {
		return validflag;
	}

	public void setValidflag(int validflag) {
		this.validflag = validflag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}	
	
	public List<Object> getNodes() {
		return nodes;
	}
	
	public void setNodes(List<Object> nodes) {
		this.nodes = nodes;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
