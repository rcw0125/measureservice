package com.talent.measureservice.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("equipentparam")
public class EquipmentParam  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5211544396859984793L;

	private String equcode; // 衡器编码

	private String equname; // 衡器名称

	private String paraminfos; // 参数类型 clob

	private long versionnum;// 版本号

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
	
	
}
