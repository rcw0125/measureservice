package com.talent.materialflow.model;


import org.apache.ibatis.type.Alias;

@Alias("combox")
public class ComboxData {
	private String id="";
	
	private String text="";
	
	private String searchText="";
	
	private String operatype="";
	
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getOperatype() {
		return operatype;
	}
	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}
    
}
