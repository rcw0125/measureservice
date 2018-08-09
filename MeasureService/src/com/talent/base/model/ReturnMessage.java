package com.talent.base.model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class ReturnMessage {
	
	private boolean success = true; //没有出现问题（没有抛出非业务性异常），接口调用成功true，如果失败返回false
	private String msg = "操作成功！";
	private int flag = 0;
	private int count = 0;
	private List list = new ArrayList();
	private String functionname="";//方法名称
	private String clientmsg = "";//终端显示内容
	private String rfid="";
	
	
	
	public String getClientmsg() {
		return clientmsg;
	}
	public void setClientmsg(String clientmsg) {
		this.clientmsg = clientmsg;
	}
	public String getFunctionname() {
		return functionname;
	}
	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
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
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	

	

}
