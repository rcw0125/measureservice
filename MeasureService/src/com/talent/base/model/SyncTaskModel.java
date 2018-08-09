package com.talent.base.model;

 

public class SyncTaskModel  {
	
private String  id = "";//唯一
	
	private String  matchid = ""; //物流号
	
    private String carno = "" ;// 车号 
    
	private String optrtye= ""; // 业务类型  
	
    private String columns= ""; //需要处理的列 
    private int doresultcount= 0;//处理失败次数
    		  
	public int getDoresultcount() {
		return doresultcount;
	}

	public void setDoresultcount(int doresultcount) {
		this.doresultcount = doresultcount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public String getOptrtye() {
		return optrtye;
	}

	public void setOptrtye(String optrtye) {
		this.optrtye = optrtye;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}
    
	
}