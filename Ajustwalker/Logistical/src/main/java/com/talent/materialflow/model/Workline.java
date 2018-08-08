package com.talent.materialflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.ibatis.type.Alias;
import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("workline")
@Entity
@Table(name="L_WORKLINE_T",uniqueConstraints={@UniqueConstraint(columnNames={"worklinecode"})})
public class Workline extends BaseModel{
	
	private static final long serialVersionUID = 1486880150121462550L;

	@Fields(name="作业路线编码",sn=1)
	@Column(name="worklinecode")
	private String worklinecode = "";
	
	@Fields(name="作业路线名称",sn=2)
	@Column(name="worklinename")
	private String worklinename = "";
	
	@Fields(name="拼音头缩写",sn=3)
	@Column(name="queryword")
	private String queryword = "";
	
	private String materialcode="";

	public String getWorklinecode() {
		return worklinecode;
	}

	public void setWorklinecode(String worklinecode) {
		this.worklinecode = worklinecode;
	}

	public String getWorklinename() {
		return worklinename;
	}

	public void setWorklinename(String worklinename) {
		this.worklinename = worklinename;
	}

	public String getQueryword() {
		return queryword;
	}

	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}

	public String getMaterialcode() {
		return materialcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}
	
	
}
