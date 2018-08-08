package com.xgmes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("material")
@Entity
@Table(name="B_MATERIAL_T",uniqueConstraints={@UniqueConstraint(columnNames={"materialcode"})})
public class Material extends BaseModel{
	
	private static final long serialVersionUID = -5533665120023991988L;

	@Fields(name="物料编码",sn=2)
	@Column(name="MATERIALCODE")
	private String materialcode = "";
	
	@Fields(name="物料名称",sn=3)
	@Column(name="MATERIALNAME")
	private String materialname = "";
	
	@Fields(name="拼音缩写",sn=4)
	@Column(name="QUERYWORD")
	private String queryword = "";
	
	@Fields(name="ERP编码",sn=5)
	@Column(name="ERPCODE")
	private String erpcode = "";
	
	@Fields(name="计质量标记",sn=6)
	@Column(name="MQFLAG")
	private String mqflag = "";
	
	@Fields(name="跟踪标记",sn=7)
	@Column(name="TRACKFLAG")
	private int trackflag = 0;
	
	@Fields(name="理重系数",sn=8)
	@Column(name="THEORYCOEFFICIENT")
	private double theorycoefficient = 0.000;
	
	@Fields(name="单支量",sn=9)
	@Column(name="EACHWEIGHT")
	private double eachweight = 0.000;
	
	@Fields(name="理重、实重",sn=10)
	@Column(name="ISTHEORY")
	private int istheory = 0;
	
	@Fields(name="大类编码",sn=1)
	@Column(name="FOLDERCODE")
	private String foldercode = "";
	
	@Fields(name="计量单位",sn=11)
	@Column(name="MATERIALUNIT")
	private String materialunit = "";
	
	@Fields(name="规格",sn=12)
	@Column(name="SIZERANGE")
	private String sizerange = "";
	
	@Fields(name="取样方式",sn=13)
	@Column(name="QYFS")
	private String qyfs = "";
	
	@Fields(name="作业路线",needFormat=true,sn=14)
	@Column(name="routeid")
	private long routeid = 0;
	
	@Fields(name="是否计量",sn=4,needFormat=true)
	@Column(name="isormeasure")
	private int isormeasure = -1;
	
	@Fields(name="是否监秤",sn=4,needFormat=true)
	@Column(name="isormonitor")
	private int isormonitor = -1;
	
	@Fields(name="是否启用rfid",sn=4,needFormat=true)
	@Column(name="isorrfid")
	private int isorrfid = -1;
	
	private int types;

	public String getMaterialcode() {
		return materialcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public String getQueryword() {
		return queryword;
	}

	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}

	public String getErpcode() {
		return erpcode;
	}

	public void setErpcode(String erpcode) {
		this.erpcode = erpcode;
	}

	public String getMqflag() {
		return mqflag;
	}

	public void setMqflag(String mqflag) {
		this.mqflag = mqflag;
	}

	public int getTrackflag() {
		return trackflag;
	}

	public void setTrackflag(int trackflag) {
		this.trackflag = trackflag;
	}

	public double getTheorycoefficient() {
		return theorycoefficient;
	}

	public void setTheorycoefficient(double theorycoefficient) {
		this.theorycoefficient = theorycoefficient;
	}

	public double getEachweight() {
		return eachweight;
	}

	public void setEachweight(double eachweight) {
		this.eachweight = eachweight;
	}

	public int getIstheory() {
		return istheory;
	}

	public void setIstheory(int istheory) {
		this.istheory = istheory;
	}

	public String getFoldercode() {
		return foldercode;
	}

	public void setFoldercode(String foldercode) {
		this.foldercode = foldercode;
	}

	public String getMaterialunit() {
		return materialunit;
	}

	public void setMaterialunit(String materialunit) {
		this.materialunit = materialunit;
	}

	public String getSizerange() {
		return sizerange;
	}

	public void setSizerange(String sizerange) {
		this.sizerange = sizerange;
	}

	public String getQyfs() {
		return qyfs;
	}

	public void setQyfs(String qyfs) {
		this.qyfs = qyfs;
	}

	public long getRouteid() {
		return routeid;
	}

	public void setRouteid(long routeid) {
		this.routeid = routeid;
	}

	public int getIsormeasure() {
		return isormeasure;
	}

	public void setIsormeasure(int isormeasure) {
		this.isormeasure = isormeasure;
	}

	public int getIsormonitor() {
		return isormonitor;
	}

	public void setIsormonitor(int isormonitor) {
		this.isormonitor = isormonitor;
	}

	public int getIsorrfid() {
		return isorrfid;
	}

	public void setIsorrfid(int isorrfid) {
		this.isorrfid = isorrfid;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	
	
}
