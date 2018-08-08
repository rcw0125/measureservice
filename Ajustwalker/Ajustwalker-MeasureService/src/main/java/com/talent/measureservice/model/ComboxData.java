package com.talent.measureservice.model;


import org.apache.ibatis.type.Alias;

@Alias("combox")
public class ComboxData {
	
	private String code;
	
	private String name;
	
	private String searchText;
	
	
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
    
}
