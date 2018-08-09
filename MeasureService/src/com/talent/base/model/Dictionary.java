package com.talent.base.model;

import java.io.Serializable;

public class Dictionary extends Message implements Serializable{
	
	private static final long serialVersionUID = -8298012576406761745L;
	
	private int id = 0;

	private String dname = "";
    
    private String dcode = "";
    
    private String dvalue = "";
    
    private String ddesc = "";

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDcode() {
		return dcode;
	}

	public void setDcode(String dcode) {
		this.dcode = dcode;
	}

	public String getDdesc() {
		return ddesc;
	}

	public void setDdesc(String ddesc) {
		this.ddesc = ddesc;
	}

	public String getDvalue() {
		return dvalue;
	}

	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}
}
