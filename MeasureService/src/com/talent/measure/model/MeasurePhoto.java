package com.talent.measure.model;

import java.io.Serializable;

public class MeasurePhoto implements Serializable{
	
	private static final long serialVersionUID = 607341989201059208L;
	
	private String equcode; //衡器编码
	
	private String equname; //衡器名称
	
	private String id; //唯一	
	
	private String measuretype; //计量类型 G T
	
	private String matchid=""; // 过磅单号
	
	private String photo;  //照片路径  
	
	private String createtime; //创建时间
	
	private String text;
	
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getEqucode() {
		return equcode;
	}
	public void setEqucode(String equcode) {
		this.equcode = equcode;
	}
	public String getEquname() {
		return equname;
	}
	public void setEquname(String equname) {
		this.equname = equname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMeasuretype() {
		return measuretype;
	}
	public void setMeasuretype(String measuretype) {
		this.measuretype = measuretype;
	}
	public String getMatchid() {
		return matchid;
	}
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	 
	 
}
