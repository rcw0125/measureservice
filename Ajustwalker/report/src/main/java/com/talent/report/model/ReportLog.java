package com.talent.report.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("reportLog")
public class ReportLog  extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String matchid;//过磅单号
	
	private String usermemo;//日志内容
	
	private String operaman;//操作人
	
	private String createdate;//操作时间
	
	private String types;//操作类型 3 打印4 PDF

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	public String getUsermemo() {
		return usermemo;
	}

	public void setUsermemo(String usermemo) {
		this.usermemo = usermemo;
	}

	public String getOperaman() {
		return operaman;
	}

	public void setOperaman(String operaman) {
		this.operaman = operaman;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
    
}
