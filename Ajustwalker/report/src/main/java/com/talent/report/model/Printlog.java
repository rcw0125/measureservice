package com.talent.report.model;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

public class Printlog extends BaseModel {
	
	private static final long serialVersionUID = -205896863631387L;
	
	private String begintime = "";
	
	private String endtime = "";

	@Fields(name="操作人",sn=2)
	private String exportman; //导出人
	
	@Fields(name="导出时间",sn=1)
	private String exporttime; //导出时间
	
	private String exportparam = ""; //导出条件
	
	@Fields(name="文件名",sn=3)
	private String logfilename; //日志文件名称
	
	private String matchid;
	private String opname;
	private String equname;
	private String createtime;
	private String printtype;

	public String getOpname() {
		return opname;
	}

	public void setOpname(String opname) {
		this.opname = opname;
	}

	public String getEquname() {
		return equname;
	}

	public void setEquname(String equname) {
		this.equname = equname;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPrinttype() {
		return printtype;
	}

	public void setPrinttype(String printtype) {
		this.printtype = printtype;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getExportman() {
		return exportman;
	}

	public void setExportman(String exportman) {
		this.exportman = exportman;
	}

	public String getExporttime() {
		return exporttime;
	}

	public void setExporttime(String exporttime) {
		this.exporttime = exporttime;
	}

	public String getExportparam() {
		return exportparam;
	}

	public void setExportparam(String exportparam) {
		this.exportparam = exportparam;
	}

	public String getLogfilename() {
		return logfilename;
	}

	public void setLogfilename(String logfilename) {
		this.logfilename = logfilename;
	}

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
}
