package com.talent.measure.model;

import java.io.Serializable;

public class SeatConfigration  implements Serializable{
	
	private static final long serialVersionUID = 3573881625085783147L;
	
	private String equcode; //衡器编码
	
	private String equname; //衡器名称
	
	private String equtype;  //衡器类型
	
	private String id; //唯一
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEqucode() {
		return equcode;
	}
	public void setEqucode(String equcode) {
		this.equcode = equcode;
	}
	public String getEquname() {
		return equname;
	}
	public void setEquname(String equname) {
		this.equname = equname;
	}
	public String getEqutype() {
		return equtype;
	}
	public void setEqutype(String equtype) {
		this.equtype = equtype;
	}
	
	

}
