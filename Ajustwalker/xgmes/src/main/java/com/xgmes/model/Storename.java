package com.xgmes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("storename")
@Entity
@Table(name="B_STORENAME_T",uniqueConstraints={@UniqueConstraint(columnNames={"ids"})})
public class Storename extends BaseModel{
	
	private static final long serialVersionUID = 839239027915891301L;

	@Fields(name="编码",sn=2)
	@Column(name="storecode")
	private String storecode = "";
	
	@Fields(name="名称",sn=3)
	@Column(name="storename")
	private String storename = "";
	
	@Fields(name="统计单位1",sn=7)
	@Column(name="tjstorename")
	private String tjstorename = "";
	
	@Fields(name="统计单位2",sn=8)
	@Column(name="tjstorename2")
	private String tjstorename2 = "";
	
	@Fields(name="统计单位3",sn=9)
	@Column(name="tjstorename3")
	private String tjstorename3 = "";
	
	@Fields(name="统计单位4",sn=10)
	@Column(name="tjstorename4")
	private String tjstorename4 = "";
	
	@Fields(name="拼音缩写",sn=4)
	@Column(name="queryword")
	private String queryword = "";
	
	@Fields(name="类型编码",sn=1)
	@Column(name="typecode")
	private String typecode = "";
	
	@Fields(name="库房位置",sn=5)
	@Column(name="position")
	private String position = "";
	
	@Fields(name="最大限制",sn=6)
	@Column(name="uplimit")
	private double uplimit = 0.000;
	
	@Fields(name="库房控制",sn=11)
	@Column(name="storecontrol")
	private int storecontrol = 0;
	
	private String types;

	public String getStorecode() {
		return storecode;
	}

	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getTjstorename() {
		return tjstorename;
	}

	public void setTjstorename(String tjstorename) {
		this.tjstorename = tjstorename;
	}

	public String getTjstorename2() {
		return tjstorename2;
	}

	public void setTjstorename2(String tjstorename2) {
		this.tjstorename2 = tjstorename2;
	}

	public String getTjstorename3() {
		return tjstorename3;
	}

	public void setTjstorename3(String tjstorename3) {
		this.tjstorename3 = tjstorename3;
	}

	public String getTjstorename4() {
		return tjstorename4;
	}

	public void setTjstorename4(String tjstorename4) {
		this.tjstorename4 = tjstorename4;
	}

	public String getQueryword() {
		return queryword;
	}

	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getUplimit() {
		return uplimit;
	}

	public void setUplimit(double uplimit) {
		this.uplimit = uplimit;
	}

	public int getStorecontrol() {
		return storecontrol;
	}

	public void setStorecontrol(int storecontrol) {
		this.storecontrol = storecontrol;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}
