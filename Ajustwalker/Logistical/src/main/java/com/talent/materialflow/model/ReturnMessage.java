package com.talent.materialflow.model;

import java.io.Serializable;

public class ReturnMessage implements Serializable{

	private static final long serialVersionUID = 8192973549153810546L;

	private boolean success = true;

	private String msg="";

	private int flag;

	private int count;

	private String functionname="";

	public boolean isSuccess() {
		return success;
	}

	public String getMsg() {
		return msg;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}
}
