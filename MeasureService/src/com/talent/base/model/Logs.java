package com.talent.base.model;

import java.io.Serializable;

public class Logs implements Serializable {

	private static final long serialVersionUID = 4805998462240919468L;

	private String operator = ""; // ip地址

	private String inParams = ""; // 模块传入参数

	private String outResults = ""; // 输出结果

	private String createTime = ""; // 操作时间

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getInParams() {
		return inParams;
	}

	public void setInParams(String inParams) {
		this.inParams = inParams;
	}

	public String getOutResults() {
		return outResults;
	}

	public void setOutResults(String outResults) {
		this.outResults = outResults;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}