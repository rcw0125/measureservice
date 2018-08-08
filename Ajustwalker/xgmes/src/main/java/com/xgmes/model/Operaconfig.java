package com.xgmes.model;

import javax.persistence.Column;
import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("operaconfig")
public class Operaconfig extends BaseModel {

	private static final long serialVersionUID = 218056207801202759L;
	
	@Fields(name = "业务类型")
	@Column(name = "operatype")
	private String operatype;
	
	@Fields(name = "业务名称")
	@Column(name = "operaname")
	private String operaname;
	
	private String sequence;
	
	private String node;
	
	private String text;
	
	private String flag="0";
	
	private String cartype="";
	
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getOperatype() {
		return operatype;
	}
	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}
	public String getOperaname() {
		return operaname;
	}
	public void setOperaname(String operaname) {
		this.operaname = operaname;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	
}
