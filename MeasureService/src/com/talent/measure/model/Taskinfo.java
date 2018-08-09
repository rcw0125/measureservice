package com.talent.measure.model;

import java.util.List;

public class Taskinfo {
	
	private String opername;//计量员名称  
	private int carcount;//总车数
	private double avgtime;//个人平均任务计量时间
	private double avgcarcount;//总的平均计量车数
	private double avgcounttime; //总的平均时间
	private int backtask;//回退任务总数
	private int mvcardtask;//移卡终止任务总数
	private int finishtask;//终止任务总数
	private int autobacktask;//系统自动回退任务
	private String begintime;
	private String endtime;
	private List<Taskinfo> list;
	private String seatname;
	private int seatid;
	
	
	
	
	
	
	public String getSeatname() {
		return seatname;
	}
	public void setSeatname(String seatname) {
		this.seatname = seatname;
	}
	public int getSeatid() {
		return seatid;
	}
	public void setSeatid(int seatid) {
		this.seatid = seatid;
	}
	public List<Taskinfo> getList() {
		return list;
	}
	public void setList(List<Taskinfo> list) {
		this.list = list;
	}
	public int getAutobacktask() {
		return autobacktask;
	}
	public void setAutobacktask(int autobacktask) {
		this.autobacktask = autobacktask;
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
	public int getBacktask() {
		return backtask;
	}
	public void setBacktask(int backtask) {
		this.backtask = backtask;
	}
	public int getMvcardtask() {
		return mvcardtask;
	}
	public void setMvcardtask(int mvcardtask) {
		this.mvcardtask = mvcardtask;
	}
	public int getFinishtask() {
		return finishtask;
	}
	public void setFinishtask(int finishtask) {
		this.finishtask = finishtask;
	}
	public void setCarcount(int carcount) {
		this.carcount = carcount;
	}
	public String getOpername() {
		return opername;
	}
	public void setOpername(String opername) {
		this.opername = opername;
	}
	public double getCarcount() {
		return carcount;
	}

	public double getAvgtime() {
		return avgtime;
	}
	public void setAvgtime(double avgtime) {
		this.avgtime = avgtime;
	}
	public double getAvgcarcount() {
		return avgcarcount;
	}
	public void setAvgcarcount(double avgcarcount) {
		this.avgcarcount = avgcarcount;
	}
	public double getAvgcounttime() {
		return avgcounttime;
	}
	public void setAvgcounttime(double avgcounttime) {
		this.avgcounttime = avgcounttime;
	}
	
	

}
