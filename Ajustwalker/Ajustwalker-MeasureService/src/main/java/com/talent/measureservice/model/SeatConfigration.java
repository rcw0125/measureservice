package com.talent.measureservice.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;
@Alias("seatconfigraton")
public class SeatConfigration extends BaseModel {
	
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -7525132730760701589L;

	private String equcode; //衡器编码
	
	private String equname; //衡器名称
	
	private String equtype;  //衡器类型
	

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
