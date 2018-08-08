package com.talent.materialflow.model;

import javax.persistence.Column;

import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("taskcode")
public class Taskcode extends BaseModel {

	private static final long serialVersionUID = 1977422756861426143L;

	@Fields(name = "业务号")
	@Column(name = "taskcode")
	private String taskcode;

	@Fields(name = "供货单位编码")
	@Column(name = "sourcecode")
	private String sourcecode;

	@Fields(name = "供货单位")
	@Column(name = "sourcename")
	private String sourcename;

	@Fields(name = "物料编码")
	@Column(name = "materialcode")
	private String materialcode;

	@Fields(name = "物料名称")
	@Column(name = "materialname")
	private String materialname;

	@Fields(name = "收货单位编码")
	@Column(name = "targetcode")
	private String targetcode;

	@Fields(name = "收货单位")
	@Column(name = "targetname")
	private String targetname;

	@Fields(name = "运输方式")
	@Column(name = "transitway")
	private String transitway;//运输方式  S--船运  T--火运  C--汽运

	@Fields(name = "业务类型")
	@Column(name = "operatype")
	private String operatype;//业务类型 10-厂内调拨  ,11-跨区调拨 12-港口调拨  13-车站调拨

	@Fields(name = "计量顺序")
	@Column(name = "mflag")
	private int mflag;//计量流程标记厂内调拨 0不限制,1先毛后皮,2先皮后毛;跨区调拨 3皮毛毛皮、4毛毛皮、5毛毛

	@Fields(name = "是否出入库")
	@Column(name = "sflag")
	private int sflag; //是否出入库 0不限制,1出库,2入库,3出入库

	@Fields(name = "计质量标记")
	@Column(name = "mqflag")
	private int mqflag;//计质量标记0只计量不质检、1只质检不计量和2既计量也质检，默认选择只计量不质检

	@Fields(name = "是否跨区")
	@Column(name = "kqflag")
	private int kqflag;

	@Fields(name = "是否进出门 ")
	@Column(name = "gflag")
	private int gflag; //是否进出门 0不限制，1进门，2出门，3进出门  默认3

	@Fields(name = "皮重有效期")
	@Column(name = "tarehour")
	private int tarehour;

	@Fields(name = "结算方")
	@Column(name = "accountstype")
	private int accountstype; //结算方  0发货方重量、1收货方重量
	

	@Fields(name = "扣重值")
	@Column(name = "deduction2")
	private String deduction2;//扣重值 扣重值从配置参数或者业务点录入 0<value<1百分比控制，value>=1 按千克计算

	@Fields(name = "扣重单位")
	@Column(name = "deductionunit")
	private double deductionunit;//扣重单位  0计量、1发货、2收货

	@Fields(name = "扣重类型")
	@Column(name = "deductiontype")
	private double deductiontype;//扣重类型 0不扣、1固定值和2录入值

	@Fields(name = "是否执行计划")
	@Column(name = "isplan")
	private int isplan;//是否执行计划0-不执行 1-执行

	@Fields(name = "是否启用料蓝")
	@Column(name = "isbasket")
	private int isbasket;//是否启用料篮0-不启用 1-启用

	@Fields(name = "是否强制接收")
	@Column(name = "forcereceive")
	private int forcereceive;//是否强制接收 0-不强制接收、1-强制接收

	@Fields(name = "计量方式类型")
	@Column(name = "measuretype")
	private int measuretype;//计量方式类型 0 自动计量  1自助计量  2 远程计量 3远程手动计量  4自动选择

	@Fields(name = "生成类型")
	@Column(name = "recordtype")
	private int recordtype;  //0=自动生成，1=手工维护

	@Fields(name = "扣重系数")
	@Column(name = "weightcoefficient")
	private double weightcoefficient;
	
	@Fields(name = "开始时间")
	private String begintime;
	
	@Fields(name = "结束时间")
	private String endtime;

	private String measurement;//计量顺序
	
	private int printcount;//打印次数
	
	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	public String getSourcecode() {
		return sourcecode;
	}

	public void setSourcecode(String sourcecode) {
		this.sourcecode = sourcecode;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

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

	public String getTargetcode() {
		return targetcode;
	}

	public void setTargetcode(String targetcode) {
		this.targetcode = targetcode;
	}

	public String getTargetname() {
		return targetname;
	}

	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}

	public String getTransitway() {
		return transitway;
	}

	public void setTransitway(String transitway) {
		this.transitway = transitway;
	}

	public String getOperatype() {
		return operatype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

	public int getMflag() {
		return mflag;
	}

	public void setMflag(int mflag) {
		this.mflag = mflag;
	}

	public int getSflag() {
		return sflag;
	}

	public void setSflag(int sflag) {
		this.sflag = sflag;
	}

	public int getMqflag() {
		return mqflag;
	}

	public void setMqflag(int mqflag) {
		this.mqflag = mqflag;
	}

	public int getKqflag() {
		return kqflag;
	}

	public void setKqflag(int kqflag) {
		this.kqflag = kqflag;
	}

	public int getGflag() {
		return gflag;
	}

	public void setGflag(int gflag) {
		this.gflag = gflag;
	}

	public int getTarehour() {
		return tarehour;
	}

	public void setTarehour(int tarehour) {
		this.tarehour = tarehour;
	}

	public int getAccountstype() {
		return accountstype;
	}

	public void setAccountstype(int accountstype) {
		this.accountstype = accountstype;
	}


	public String getDeduction2() {
		return deduction2;
	}

	public void setDeduction2(String deduction2) {
		this.deduction2 = deduction2;
	}

	public double getDeductionunit() {
		return deductionunit;
	}

	public void setDeductionunit(double deductionunit) {
		this.deductionunit = deductionunit;
	}

	public double getDeductiontype() {
		return deductiontype;
	}

	public void setDeductiontype(double deductiontype) {
		this.deductiontype = deductiontype;
	}

	public int getIsplan() {
		return isplan;
	}

	public void setIsplan(int isplan) {
		this.isplan = isplan;
	}

	public int getIsbasket() {
		return isbasket;
	}

	public void setIsbasket(int isbasket) {
		this.isbasket = isbasket;
	}

	public int getForcereceive() {
		return forcereceive;
	}

	public void setForcereceive(int forcereceive) {
		this.forcereceive = forcereceive;
	}

	public int getMeasuretype() {
		return measuretype;
	}

	public void setMeasuretype(int measuretype) {
		this.measuretype = measuretype;
	}

	public int getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(int recordtype) {
		this.recordtype = recordtype;
	}

	public double getWeightcoefficient() {
		return weightcoefficient;
	}

	public void setWeightcoefficient(double weightcoefficient) {
		this.weightcoefficient = weightcoefficient;
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

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public int getPrintcount() {
		return printcount;
	}

	public void setPrintcount(int printcount) {
		this.printcount = printcount;
	}
	
}
