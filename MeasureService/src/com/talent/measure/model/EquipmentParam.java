package com.talent.measure.model;

import java.io.Serializable;

/**
 * @author asus
 *
 */
public class EquipmentParam implements Serializable {
	
	private static final long serialVersionUID = 2156605261498462565L;
	
	private String equcode; //衡器编码
	
	private String equname; //衡器名称
	
	private String id; //唯一
	
	private String paraminfos ; //参数类型 clob
	
	private long versionnum;//版本号 
	
	public String getParaminfos() {
		return paraminfos;
	}
	public void setParaminfos(String paraminfos) {
		this.paraminfos = paraminfos;
	}
	public long getVersionnum() {
		return versionnum;
	}
	public void setVersionnum(long versionnum) {
		this.versionnum = versionnum;
	}
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
	 
	

}
