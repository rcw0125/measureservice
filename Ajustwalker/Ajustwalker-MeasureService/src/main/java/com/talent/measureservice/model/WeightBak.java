package com.talent.measureservice.model;

import org.apache.ibatis.type.Alias;

@Alias("weightbak")
public class WeightBak {
	
	private int id;
	private String boundno;
	private String datetime;
	private String weight;
	private String speed;
	private int type;
	private String trainnum;
	private String traintype;
	private String pic1;
	private String pic2;
	private String pic3;
	private String video1;
	private String video2;
	private String video3;
	private int matchflag;
	private String lietime;
	private String lieno;
	private int isuse;
	private String lcfx;
	
	
	public int getId() {
		return id;
	}
	public String getBoundno() {
		return boundno;
	}
	public String getDatetime() {
		return datetime;
	}
	public String getWeight() {
		return weight;
	}
	public String getSpeed() {
		return speed;
	}
	public int getType() {
		return type;
	}
	public String getTrainnum() {
		return trainnum;
	}
	public String getTraintype() {
		return traintype;
	}
	public String getPic1() {
		return pic1;
	}
	public String getPic2() {
		return pic2;
	}
	public String getPic3() {
		return pic3;
	}
	public String getVideo1() {
		return video1;
	}
	public String getVideo2() {
		return video2;
	}
	public String getVideo3() {
		return video3;
	}
	public int getMatchflag() {
		return matchflag;
	}
	public String getLietime() {
		return lietime;
	}
	public String getLieno() {
		return lieno;
	}
	public int getIsuse() {
		return isuse;
	}
	public String getLcfx() {
		return lcfx;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setBoundno(String boundno) {
		this.boundno = boundno;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setTrainnum(String trainnum) {
		this.trainnum = trainnum;
	}
	public void setTraintype(String traintype) {
		this.traintype = traintype;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	public void setVideo1(String video1) {
		this.video1 = video1;
	}
	public void setVideo2(String video2) {
		this.video2 = video2;
	}
	public void setVideo3(String video3) {
		this.video3 = video3;
	}
	public void setMatchflag(int matchflag) {
		this.matchflag = matchflag;
	}
	public void setLietime(String lietime) {
		this.lietime = lietime;
	}
	public void setLieno(String lieno) {
		this.lieno = lieno;
	}
	public void setIsuse(int isuse) {
		this.isuse = isuse;
	}
	public void setLcfx(String lcfx) {
		this.lcfx = lcfx;
	}

}
