package com.talent.measureservice.model;

import javax.persistence.Column;

import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;

@Alias("trainmeasure")
public class TrainMeasure  {


	@Fields(name = "物流号")
	@Column(name = "matchid")
	private String matchid="";// 验配id clientid+yymmdd+00000 该验配id是第一个业务点生成

	@Fields(name = "车号")
	@Column(name = "carno")
	private String carno="";// 车号

	@Fields(name = "车辆类型")
	@Column(name = "cartype")
	private String cartype="";// c汽车,t火车

	@Fields(name = "业务类型")
	@Column(name = "operatype")
	private String operatype="";// 业务类型

	@Fields(name = "计划号")
	@Column(name = "planid")
	private String planid="";// 计划id

	@Fields(name = "业务类型")
	@Column(name = "taskcode")
	private String taskcode="";// 调拨业务号

	@Fields(name = "物料编码")
	@Column(name = "materialcode")
	private String materialcode="";// 物料编码

	@Fields(name = "物料名称")
	@Column(name = "materialname")
	private String materialname="";// 物料名称

	@Fields(name = "规格编码")
	@Column(name = "materialspeccode")
	private String materialspeccode="";// 物料规格编码

	@Fields(name = "物料规格")
	@Column(name = "materialspec")
	private String materialspec="";// 物料规格

	@Fields(name = "船名编码")
	@Column(name = "shipcode")
	private String shipcode="";// 船名编码

	@Fields(name = "船名")
	@Column(name = "ship")
	private String ship="";// 船名

	@Fields(name = "发货单位编码")
	@Column(name = "sourcecode")
	private String sourcecode="";// 发货单位编码

	@Fields(name = "发货单位")
	@Column(name = "sourcename")
	private String sourcename="";// 发货单位

	@Fields(name = "发货时间")
	@Column(name = "sourcetime")
	private String sourcetime="";// 发货时间

	@Fields(name = "发货地点")
	@Column(name = "sourceplace")
	private String sourceplace="";// 供货地点/发站

	@Fields(name = "收货单位编码")
	@Column(name = "targetcode")
	private String targetcode="";// 收货单位编码

	@Fields(name = "收货单位")
	@Column(name = "targetname")
	private String targetname="";// 收货单位

	@Fields(name = "收货时间")
	@Column(name = "targettime")
	private String targettime="";// 收货时间

	@Fields(name = "收货地点名称")
	@Column(name = "targetplace")
	private String targetplace="";// 收货地点名称（集港的是港口，火车是到站名）

	@Fields(name = "计划发货重量")
	@Column(name = "planweight")
	private double planweight=0.0;// 计划发货重量

	@Fields(name = "计划发货总支数")
	@Column(name = "planmaterialcount")
	private int planmaterialcount=0;// 计划发货总支数

	@Fields(name = "实际发货总支件数")
	@Column(name = "materialcount")
	private int materialcount=0;// 实际发货总支件数

	@Fields(name = "毛重")
	@Column(name = "gross")
	private double gross=0.0;// 毛重

	@Fields(name = "毛重时间")
	@Column(name = "grosstime")
	private String grosstime="";// 毛重时间

	@Fields(name = "计毛衡器id")
	@Column(name = "grossweighid")
	private String grossweighid="";// 计毛衡器id

	@Fields(name = "毛重衡器名称")
	@Column(name = "grossweigh")
	private String grossweigh="";// 毛重衡器名称

	@Fields(name = "计毛计量员编码")
	@Column(name = "grossoperacode")
	private String grossoperacode="";// 计毛计量员编码

	@Fields(name = "计毛计量员")
	@Column(name = "grossoperaname")
	private String grossoperaname="";// 计毛计量员

	@Fields(name = "毛重组号")
	@Column(name = "grossgroupno")
	private int grossgroupno=0;// 毛重组号

	@Fields(name = "毛重组内序号")
	@Column(name = "grossserial")
	private int grossserial=0;// 毛重组内序号

	@Fields(name = "毛重速度")
	@Column(name = "grossspeed")
	private double grossspeed=0;// 毛重速度

	@Fields(name = "毛重logid")
	@Column(name = "grosslogid")
	private String grosslogid="";// 毛重logid,通过毛重logid,可以从照片表中读取照片

	@Fields(name = "皮重")
	@Column(name = "tare")
	private double tare=0.0;// 皮重

	@Fields(name = "皮重时间")
	@Column(name = "taretime")
	private String taretime="";// 皮重时间

	@Fields(name = "皮重衡器id")
	@Column(name = "tareweighid")
	private String tareweighid="";// 皮重衡器id

	@Fields(name = "皮重衡器名称")
	@Column(name = "tareweigh")
	private String tareweigh="";// 皮重衡器名称

	@Fields(name = "皮重计量员编码")
	@Column(name = "tareoperacode")
	private String tareoperacode="";// 皮重计量员编码

	@Fields(name = "皮重计量员")
	@Column(name = "tareoperaname")
	private String tareoperaname="";// 皮重计量员

	@Fields(name = "皮重组号")
	@Column(name = "taregroupno")
	private int taregroupno=0;// 皮重组号

	@Fields(name = "皮重组内序号")
	@Column(name = "tareserial")
	private int tareserial=0;// 皮重组内序号

	@Fields(name = "皮重logid")
	@Column(name = "tarelogid")
	private String tarelogid="";// 皮重logid,通过皮重logid,可以从照片表中读取照片

	@Fields(name = "计毛速度")
	@Column(name = "tarespeed")
	private double tarespeed=0;// 计毛速度

	@Fields(name = "扣重")
	@Column(name = "deduction")
	private double deduction=0.0;// 扣重

	@Fields(name = "扣重时间")
	@Column(name = "deductiontime")
	private String deductiontime="";// 扣重时间

	@Fields(name = "扣重单位编码")
	@Column(name = "deductioncode")
	private String deductioncode="";// 扣重单位编码

	@Fields(name = "扣重单位")
	@Column(name = "deductionname")
	private String deductionname="";// 扣重单位

	@Fields(name = "扣重人编码")
	@Column(name = "deductionoperacode")
	private String deductionoperacode="";// 扣重人编码

	@Fields(name = "扣重人")
	@Column(name = "deductionoperaname")
	private String deductionoperaname="";// 扣重人

	@Fields(name = "净重")
	@Column(name = "suttle")
	private double suttle=0.0;// 净重

	@Fields(name = "净重时间")
	@Column(name = "suttletime")
	private String suttletime="";// 净重时间

	@Fields(name = "净重衡器id")
	@Column(name = "suttleweighid")
	private String suttleweighid="";// 净重衡器id

	@Fields(name = "净重衡器名称")
	@Column(name = "suttleweigh")
	private String suttleweigh="";// 净重衡器名称

	@Fields(name = "净重计量员编码")
	@Column(name = "suttleoperacode")
	private String suttleoperacode="";// 净重计量员编码

	@Fields(name = "净重计量员")
	@Column(name = "suttleoperaname")
	private String suttleoperaname="";// 净重计量员

	@Fields(name = "备注1")
	@Column(name = "memo1")
	private String memo1="";// 计量申请单号

	@Fields(name = "备注2")
	@Column(name = "memo2")
	private String memo2="";// 异常修改人

	@Fields(name = "备注3")
	@Column(name = "memo3")
	private String memo3="";// 异常修改时间

	@Fields(name = "备注4")
	@Column(name = "memo4")
	private String memo4="";// 备注4

	@Fields(name = "备注5")
	@Column(name = "memo5")
	private String memo5="";// 备注5

	@Fields(name = "备注6")
	@Column(name = "memo6")
	private String memo6="";// 备注6

	@Fields(name = "备注7")
	@Column(name = "memo7")
	private String memo7="";// 备注7

	@Fields(name = "备注8")
	@Column(name = "memo8")
	private String memo8="";// 备注8

	@Fields(name = "备注9")
	@Column(name = "memo9")
	private String memo9="";// 备注9

	@Fields(name = "备注10")
	@Column(name = "memo10")
	private String memo10="";// 备注10

	@Fields(name = "备注11")
	@Column(name = "memo11")
	private String memo11="";// 备注11

	@Fields(name = "备注13")
	@Column(name = "memo12")
	private String memo12="";// 备注12

	@Fields(name = "备注13")
	@Column(name = "memo13")
	private String memo13="";// 备注13

	@Fields(name = "备注14")
	@Column(name = "memo14")
	private String memo14="";// 备注14

	@Fields(name = "备注15")
	@Column(name = "memo15")
	private String memo15="";// 备注15

	private String begintime="";

	private String endtime="";

	private String mflag="";// 计量顺序 1先毛后皮 2先皮后毛

	private int materialflow = 0; // 物资流向：0厂内，1进厂，2出厂

	private String[] carnos={};// 车号

	private String measurestate="";

	private String usermemo="";
	public String getMatchid() {
		return matchid;
	}

	public String getCarno() {
		return carno;
	}

	public String getCartype() {
		return cartype;
	}

	public String getOperatype() {
		return operatype;
	}

	public String getPlanid() {
		return planid;
	}

	public String getTaskcode() {
		return taskcode;
	}

	public String getMaterialcode() {
		return materialcode;
	}

	public String getMaterialname() {
		return materialname;
	}

	public String getMaterialspeccode() {
		return materialspeccode;
	}

	public String getMaterialspec() {
		return materialspec;
	}

	public String getShipcode() {
		return shipcode;
	}

	public String getShip() {
		return ship;
	}

	public String getSourcecode() {
		return sourcecode;
	}

	public String getSourcename() {
		return sourcename;
	}

	public String getSourcetime() {
		return sourcetime;
	}

	public String getSourceplace() {
		return sourceplace;
	}

	public String getTargetcode() {
		return targetcode;
	}

	public String getTargetname() {
		return targetname;
	}

	public String getTargettime() {
		return targettime;
	}

	public String getTargetplace() {
		return targetplace;
	}

	public double getPlanweight() {
		return planweight;
	}

	public int getPlanmaterialcount() {
		return planmaterialcount;
	}

	public int getMaterialcount() {
		return materialcount;
	}

	public double getGross() {
		return gross;
	}

	public String getGrosstime() {
		return grosstime;
	}

	public String getGrossweighid() {
		return grossweighid;
	}

	public String getGrossweigh() {
		return grossweigh;
	}

	public String getGrossoperacode() {
		return grossoperacode;
	}

	public String getGrossoperaname() {
		return grossoperaname;
	}

	public int getGrossgroupno() {
		return grossgroupno;
	}

	public int getGrossserial() {
		return grossserial;
	}

	public double getGrossspeed() {
		return grossspeed;
	}

	public String getGrosslogid() {
		return grosslogid;
	}

	public double getTare() {
		return tare;
	}

	public String getTaretime() {
		return taretime;
	}

	public String getTareweighid() {
		return tareweighid;
	}

	public String getTareweigh() {
		return tareweigh;
	}

	public String getTareoperacode() {
		return tareoperacode;
	}

	public String getTareoperaname() {
		return tareoperaname;
	}

	public int getTaregroupno() {
		return taregroupno;
	}

	public int getTareserial() {
		return tareserial;
	}

	public String getTarelogid() {
		return tarelogid;
	}

	public double getTarespeed() {
		return tarespeed;
	}

	public double getDeduction() {
		return deduction;
	}

	public String getDeductiontime() {
		return deductiontime;
	}

	public String getDeductioncode() {
		return deductioncode;
	}

	public String getDeductionname() {
		return deductionname;
	}

	public String getDeductionoperacode() {
		return deductionoperacode;
	}

	public String getDeductionoperaname() {
		return deductionoperaname;
	}

	public double getSuttle() {
		return suttle;
	}

	public String getSuttletime() {
		return suttletime;
	}

	public String getSuttleweighid() {
		return suttleweighid;
	}

	public String getSuttleweigh() {
		return suttleweigh;
	}

	public String getSuttleoperacode() {
		return suttleoperacode;
	}

	public String getSuttleoperaname() {
		return suttleoperaname;
	}

	public String getMemo1() {
		return memo1;
	}

	public String getMemo2() {
		return memo2;
	}

	public String getMemo3() {
		return memo3;
	}

	public String getMemo4() {
		return memo4;
	}

	public String getMemo5() {
		return memo5;
	}

	public String getMemo6() {
		return memo6;
	}

	public String getMemo7() {
		return memo7;
	}

	public String getMemo8() {
		return memo8;
	}

	public String getMemo9() {
		return memo9;
	}

	public String getMemo10() {
		return memo10;
	}

	public String getMemo11() {
		return memo11;
	}

	public String getMemo12() {
		return memo12;
	}

	public String getMemo13() {
		return memo13;
	}

	public String getMemo14() {
		return memo14;
	}

	public String getMemo15() {
		return memo15;
	}

	public String getBegintime() {
		return begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public String getMflag() {
		return mflag;
	}

	public int getMaterialflow() {
		return materialflow;
	}

	public String[] getCarnos() {
		return carnos;
	}

	public String getMeasurestate() {
		return measurestate;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public void setMaterialspeccode(String materialspeccode) {
		this.materialspeccode = materialspeccode;
	}

	public void setMaterialspec(String materialspec) {
		this.materialspec = materialspec;
	}

	public void setShipcode(String shipcode) {
		this.shipcode = shipcode;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public void setSourcecode(String sourcecode) {
		this.sourcecode = sourcecode;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public void setSourcetime(String sourcetime) {
		this.sourcetime = sourcetime;
	}

	public void setSourceplace(String sourceplace) {
		this.sourceplace = sourceplace;
	}

	public void setTargetcode(String targetcode) {
		this.targetcode = targetcode;
	}

	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}

	public void setTargettime(String targettime) {
		this.targettime = targettime;
	}

	public void setTargetplace(String targetplace) {
		this.targetplace = targetplace;
	}

	public void setPlanweight(double planweight) {
		this.planweight = planweight;
	}

	public void setPlanmaterialcount(int planmaterialcount) {
		this.planmaterialcount = planmaterialcount;
	}

	public void setMaterialcount(int materialcount) {
		this.materialcount = materialcount;
	}

	public void setGross(double gross) {
		this.gross = gross;
	}

	public void setGrosstime(String grosstime) {
		this.grosstime = grosstime;
	}

	public void setGrossweighid(String grossweighid) {
		this.grossweighid = grossweighid;
	}

	public void setGrossweigh(String grossweigh) {
		this.grossweigh = grossweigh;
	}

	public void setGrossoperacode(String grossoperacode) {
		this.grossoperacode = grossoperacode;
	}

	public void setGrossoperaname(String grossoperaname) {
		this.grossoperaname = grossoperaname;
	}

	public void setGrossgroupno(int grossgroupno) {
		this.grossgroupno = grossgroupno;
	}

	public void setGrossserial(int grossserial) {
		this.grossserial = grossserial;
	}

	public void setGrossspeed(double grossspeed) {
		this.grossspeed = grossspeed;
	}

	public void setGrosslogid(String grosslogid) {
		this.grosslogid = grosslogid;
	}

	public void setTare(double tare) {
		this.tare = tare;
	}

	public void setTaretime(String taretime) {
		this.taretime = taretime;
	}

	public void setTareweighid(String tareweighid) {
		this.tareweighid = tareweighid;
	}

	public void setTareweigh(String tareweigh) {
		this.tareweigh = tareweigh;
	}

	public void setTareoperacode(String tareoperacode) {
		this.tareoperacode = tareoperacode;
	}

	public void setTareoperaname(String tareoperaname) {
		this.tareoperaname = tareoperaname;
	}

	public void setTaregroupno(int taregroupno) {
		this.taregroupno = taregroupno;
	}

	public void setTareserial(int tareserial) {
		this.tareserial = tareserial;
	}

	public void setTarelogid(String tarelogid) {
		this.tarelogid = tarelogid;
	}

	public void setTarespeed(double tarespeed) {
		this.tarespeed = tarespeed;
	}

	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}

	public void setDeductiontime(String deductiontime) {
		this.deductiontime = deductiontime;
	}

	public void setDeductioncode(String deductioncode) {
		this.deductioncode = deductioncode;
	}

	public void setDeductionname(String deductionname) {
		this.deductionname = deductionname;
	}

	public void setDeductionoperacode(String deductionoperacode) {
		this.deductionoperacode = deductionoperacode;
	}

	public void setDeductionoperaname(String deductionoperaname) {
		this.deductionoperaname = deductionoperaname;
	}

	public void setSuttle(double suttle) {
		this.suttle = suttle;
	}

	public void setSuttletime(String suttletime) {
		this.suttletime = suttletime;
	}

	public void setSuttleweighid(String suttleweighid) {
		this.suttleweighid = suttleweighid;
	}

	public void setSuttleweigh(String suttleweigh) {
		this.suttleweigh = suttleweigh;
	}

	public void setSuttleoperacode(String suttleoperacode) {
		this.suttleoperacode = suttleoperacode;
	}

	public void setSuttleoperaname(String suttleoperaname) {
		this.suttleoperaname = suttleoperaname;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

	public void setMemo3(String memo3) {
		this.memo3 = memo3;
	}

	public void setMemo4(String memo4) {
		this.memo4 = memo4;
	}

	public void setMemo5(String memo5) {
		this.memo5 = memo5;
	}

	public void setMemo6(String memo6) {
		this.memo6 = memo6;
	}

	public void setMemo7(String memo7) {
		this.memo7 = memo7;
	}

	public void setMemo8(String memo8) {
		this.memo8 = memo8;
	}

	public void setMemo9(String memo9) {
		this.memo9 = memo9;
	}

	public void setMemo10(String memo10) {
		this.memo10 = memo10;
	}

	public void setMemo11(String memo11) {
		this.memo11 = memo11;
	}

	public void setMemo12(String memo12) {
		this.memo12 = memo12;
	}

	public void setMemo13(String memo13) {
		this.memo13 = memo13;
	}

	public void setMemo14(String memo14) {
		this.memo14 = memo14;
	}

	public void setMemo15(String memo15) {
		this.memo15 = memo15;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setMflag(String mflag) {
		this.mflag = mflag;
	}

	public void setMaterialflow(int materialflow) {
		this.materialflow = materialflow;
	}

	public void setCarnos(String[] carnos) {
		this.carnos = carnos;
	}

	public void setMeasurestate(String measurestate) {
		this.measurestate = measurestate;
	}

	public String getUsermemo() {
		return usermemo;
	}

	public void setUsermemo(String usermemo) {
		this.usermemo = usermemo;
	}
    


	
}
