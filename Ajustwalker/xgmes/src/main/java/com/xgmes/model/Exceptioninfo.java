package com.xgmes.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("exceptioninfo")
public class Exceptioninfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1214729913277680272L;
	private String operaname;
	private String usermemo;
	private String createdate;
	private String matchid;
	public String getOperaname() {
		return operaname;
	}
	public void setOperaname(String operaname) {
		this.operaname = operaname;
	}
	public String getUsermemo() {
		return usermemo;
	}
	public void setUsermemo(String usermemo) {
		this.usermemo = usermemo;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getMatchid() {
		return matchid;
	}
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

    


	

}
