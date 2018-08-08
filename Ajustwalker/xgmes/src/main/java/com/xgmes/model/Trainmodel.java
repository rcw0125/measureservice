package com.xgmes.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.core.model.BaseModel;

@Alias("trainmodel")
public class Trainmodel extends BaseModel{

	private static final long serialVersionUID = 8163488602822703421L;
	
	private String model;//车型
	
	private double load;//载重
	
	private double standardtare;//标准皮重
	
	private double deduction;//扣重
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createdate = null;
	
	private String createor;
	
	private String updateor;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updatedate = null;
	
	private String validflagman;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date validflagdate = null;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getLoad() {
		return load;
	}

	public void setLoad(double load) {
		this.load = load;
	}

	public double getStandardtare() {
		return standardtare;
	}

	public void setStandardtare(double standardtare) {
		this.standardtare = standardtare;
	}

	public double getDeduction() {
		return deduction;
	}

	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCreateor() {
		return createor;
	}

	public void setCreateor(String createor) {
		this.createor = createor;
	}

	public String getUpdateor() {
		return updateor;
	}

	public void setUpdateor(String updateor) {
		this.updateor = updateor;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getValidflagman() {
		return validflagman;
	}

	public void setValidflagman(String validflagman) {
		this.validflagman = validflagman;
	}

	public Date getValidflagdate() {
		return validflagdate;
	}

	public void setValidflagdate(Date validflagdate) {
		this.validflagdate = validflagdate;
	}

}
