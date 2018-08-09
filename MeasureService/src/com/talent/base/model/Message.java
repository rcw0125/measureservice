package com.talent.base.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {
	
	public static String MFUNCCONTINUE = "0";
	
	public static String MFUNCSTOP = "1";
	
	public static String MFUNCCONFIRM = "2";
	
	public static String MTYPEYCJL = "远程计量";
	
	public static String MTYPEYXZJ = "允许自助";
	
	public static String MTYPEQZYC = "强制远程";
	
	public static String MTYPEQZZZ = "强制自助";
	
	public static String MTYPEDEFAULT = MTYPEQZZZ;
	
	private boolean success = true; //没有出现问题（没有抛出非业务性异常），接口调用成功true，如果失败返回false
	
	private String msg = "操作成功！";
	
	private String mtype = MTYPEDEFAULT;  //远程计量、允许自助、强制远程、强制自助
	
	private String mfunc = "0";   //measure function  计量方法，0:允许计量，1禁止计量，2提供选择
	
	private Object data = new HashMap<String,Object>();
	
	private int total = 0;
	
	private Object rows = new ArrayList<Object>();
	
	private List<ReturnMessage> flags = new ArrayList<ReturnMessage>();
	
	private List<Map<String,Object>> hardwarectrl = new ArrayList<Map<String,Object>>(); //[{"name":"打印磅单","check":true,"roles":[{"mfunc":0,"value":"打印","optr":"等于","msg":"打印磅单","otherParams":"模板1,模板2"},{"name":"红外对射","check":true,"roles":[{"mfunc":2,"value":"遮挡","optr":"等于","msg":"红外对射遮挡","otherParams":""}]}]
	
	private Object more = new HashMap<String,String>();
	
	private Object mores = new ArrayList<Object>();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<Map<String, Object>> getHardwarectrl() {
		return hardwarectrl;
	}

	public void setHardwarectrl(List<Map<String, Object>> hardwarectrl) {
		this.hardwarectrl = hardwarectrl;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}	
	
	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getMfunc() {
		return mfunc;
	}

	public void setMfunc(String mfunc) {
		this.mfunc = mfunc;
	}

	public Object getRows() {
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(Object rows) {
		this.rows = rows;
		if(0 == getTotal()){
			if(rows instanceof List){
				setTotal(((List)rows).size());
			}else if(rows instanceof Object[]){
				setTotal(((Object[])rows).length);
			}else if(null == rows){
				setTotal(0);
			}
		}		
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ReturnMessage> getFlags() {
		return flags;
	}

	public void setFlags(List<ReturnMessage> flags) {
		this.flags = flags;
	}

	public Object getMore() {
		return more;
	}

	public void setMore(Object more) {
		this.more = more;
	}

	public Object getMores() {
		return mores;
	}

	public void setMores(Object mores) {
		this.mores = mores;
	}
}
