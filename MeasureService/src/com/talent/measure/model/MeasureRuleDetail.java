package com.talent.measure.model;

import java.io.Serializable;

public class MeasureRuleDetail implements Serializable {

	private static final long serialVersionUID = -8834967023457813571L;

	private int id = 0;

	private int pid = 0;

	private String functionname = "";

	private String ctrlmemo = "";
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

	public String getCtrlmemo() {
		return ctrlmemo;
	}

	public void setCtrlmemo(String ctrlmemo) {
		this.ctrlmemo = ctrlmemo;
	}
}