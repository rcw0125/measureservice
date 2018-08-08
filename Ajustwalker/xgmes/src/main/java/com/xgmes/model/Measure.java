package com.xgmes.model;

import javax.persistence.Column;
import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("measure")
public class Measure extends BaseModel {

	private static final long serialVersionUID = 2378609213182335880L;
	
	@Fields(name = "物流号")
	@Column(name = "matchid")
	private String matchid="";// 验配id clientid+yymmdd+00000 该验配id是第一个业务点生成
	
	@Fields(name = "车号")
	@Column(name = "carno")
	private String carno="";// 车号
	
	@Fields(name = "车辆类型")
	@Column(name = "cartype")
	private String cartype="";// c汽车,t火车
	
	@Fields(name = "RFID卡号")
	@Column(name = "rfidid")
	private String rfidid="";// rfid卡号 ,卡的唯一标示号
	
	@Fields(name = "IC卡号")
	@Column(name = "icid")
	private String icid="";// ic卡id号 ,卡的唯一标示号
	
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
	
	@Fields(name = "供货地点")
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
	
	@Fields(name = "料篮")
	@Column(name = "basket")
	private String basket="";// 料篮号、包号
	
	@Fields(name = "计划发货重量")
	@Column(name = "planweight")
	private double planweight=0;// 计划发货重量
	
	@Fields(name = "计划发货总支数")
	@Column(name = "planmaterialcount")
	private int planmaterialcount=0;// 计划发货总支数
	
	@Fields(name = "实际发货总支件数")
	@Column(name = "materialcount")
	private int materialcount=0;// 实际发货总支件数
	
	@Fields(name = "计划发货总车数")
	@Column(name = "plancarcount")
	private int plancarcount=0;// 计划发货总车数
	
	@Fields(name = "毛重")
	@Column(name = "gross")
	private double gross=0;// 毛重
	
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
	private double grossgroupno=0;// 毛重组号
	
	@Fields(name = "毛重组内序号")
	@Column(name = "grossserial")
	private double grossserial=0;// 毛重组内序号
	
	@Fields(name = "毛重速度")
	@Column(name = "grossspeed")
	private double grossspeed=0;// 毛重速度
	
	@Fields(name = "毛重logid")
	@Column(name = "grosslogid")
	private String grosslogid="";// 毛重logid,通过毛重logid,可以从照片表中读取照片
	
	@Fields(name = "皮重")
	@Column(name = "tare")
	private double tare=0;// 皮重
	
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
	private double taregroupno=0;// 皮重组号
	
	@Fields(name = "皮重组内序号")
	@Column(name = "tareserial")
	private double tareserial=0.0;// 皮重组内序号
	
	@Fields(name = "皮重logid")
	@Column(name = "tarelogid")
	private String tarelogid="";// 皮重logid,通过皮重logid,可以从照片表中读取照片
	
	@Fields(name = "计毛速度")
	@Column(name = "tarespeed")
	private double tarespeed=0;// 计毛速度
	
	@Fields(name = "扣重")
	@Column(name = "deduction")
	private double deduction=0;// 扣重
	
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
	private double suttle=0;// 净重
	
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
	
	@Fields(name = "发货id")
	@Column(name = "matchidb")
	private String matchidb="";// 发货id
	
	@Fields(name = "发货前净重")
	@Column(name = "suttleb")
	private double suttleb=0;// 发货前净重
	
	@Fields(name = "发货毛重")
	@Column(name = "grossb")
	private double grossb=0;// 发货毛重
	
	@Fields(name = "发货毛重时间")
	@Column(name = "grosstimeb")
	private String grosstimeb="";// 发货毛重时间
	
	@Fields(name = "发货计毛衡器id")
	@Column(name = "grossweighidb")
	private String grossweighidb="";// 发货计毛衡器id
	
	@Fields(name = "发货毛重衡器名称")
	@Column(name = "grossweighb")
	private String grossweighb="";// 发货毛重衡器名称
	
	@Fields(name = "发货计毛计量员编码")
	@Column(name = "grossoperacodeb")
	private String grossoperacodeb="";// 发货计毛计量员编码
	
	@Fields(name = "发货计毛计量员")
	@Column(name = "grossoperanameb")
	private String grossoperanameb="";// 发货计毛计量员
	
	@Fields(name = "发货毛重组号")
	@Column(name = "grossgroupnob")
	private double grossgroupnob=0;// 发货毛重组号
	
	@Fields(name = "发货毛重组内序号")
	@Column(name = "grossserialb")
	private double grossserialb=0;// 发货毛重组内序号
	
	@Fields(name = "发货毛重速度")
	@Column(name = "grossspeedb")
	private double grossspeedb=0;// 发货毛重速度
	
	@Fields(name = "发货毛重logid")
	@Column(name = "grosslogidb")
	private String grosslogidb="";// 发货毛重logid,通过毛重logid,可以从照片表中读取照片
	
	@Fields(name = "发货皮重")
	@Column(name = "tareb")
	private double tareb=0;// 发货皮重
	
	@Fields(name = "发货皮重时间")
	@Column(name = "taretimeb")
	private String taretimeb="";// 发货皮重时间
	
	@Fields(name = "发货皮重衡器id")
	@Column(name = "tareweighidb")
	private String tareweighidb="";// 发货皮重衡器id
	
	@Fields(name = "发货皮重衡器名称")
	@Column(name = "tareweighb")
	private String tareweighb="";// 发货皮重衡器名称
	
	@Fields(name = "发货皮重计量员编码")
	@Column(name = "tareoperacodeb")
	private String tareoperacodeb="";// 发货皮重计量员编码
	
	@Fields(name = "发货皮重计量员")
	@Column(name = "tareoperanameb")
	private String tareoperanameb="";// 发货皮重计量员
	
	@Fields(name = "发货皮重组号")
	@Column(name = "taregroupnob")
	private double taregroupnob=0;// 发货皮重组号
	
	@Fields(name = "发货皮重组内序号")
	@Column(name = "tareserialb")
	private double tareserialb=0;// 发货皮重组内序号
	
	@Fields(name = "皮重logid")
	@Column(name = "tarelogidb")
	private String tarelogidb="";// 皮重logid,通过皮重logid,可以从照片表中读取照片
	
	@Fields(name = "计皮速度")
	@Column(name = "tarespeedb")
	private double tarespeedb=0;// 计皮速度
	
	@Fields(name = "批号")
	@Column(name = "batchcode")
	private String batchcode="";// 批号
	
	@Fields(name = "退货标记")
	@Column(name = "bflag")
	private int bflag=0;// 退货标记 0 不退货，1部分退货，2全部退货
	
	@Fields(name = "打印票据毛重次数")
	@Column(name = "printgrossnum")
	private int printgrossnum=0;// 打印票据毛重次数
	
	@Fields(name = "打印票据净重次数")
	@Column(name = "printsuttlenum")
	private int printsuttlenum=0;// 打印票据净重次数
	
	@Fields(name = "记录类型")
	@Column(name = "recordtype")
	private int recordtype=0;// 记录类型 0 远程手动 1远程自动 2 现场自助 3现场自动 4 异常维护 3远程自动 4现场自动
	
	@Fields(name = "物流公司")
	@Column(name = "motorcadename")
	private String motorcadename="";// 物流公司
	
	@Fields(name = "物流公司编码")
	@Column(name = "motorcadecode")
	private String motorcadecode="";// 物流公司编码
	
	@Fields(name = "扣重类型")
	@Column(name = "deductiontype")
	private double deductiontype=0;// 扣重类型 0不扣、1固定值和3录入值
	
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
	
	@Fields(name = "扣重")
	@Column(name = "deduction2")
	private double deduction2=0;// 扣重值 扣重值从配置参数或者业务点录入 0<value<1百分比控制，value>=1，按千克计算
	
	private String begintime="";
	
	private String endtime="";
	
	private String mflag="";//计量顺序 1先毛后皮 2先皮后毛
	
	private int materialflow=0; //物资流向：0厂内，1进厂，2出厂
	
	private int tarehour=0;//皮重有效期-1长期 0 无有效期 >0小时
	
	private int tarediff=0;//皮重差
	
	private int mstate=0; //计量状态
	
	@Fields(name = "勾兑人")
	@Column(name = "operaman")
	private String operaman;//勾兑人
	
	@Fields(name = "勾兑时间")
	@Column(name = "createdate2")
	private String createdate2;//勾兑时间
	
	
	public String getOperaman() {
		return operaman;
	}

	public void setOperaman(String operaman) {
		this.operaman = operaman;
	}

	public String getCreatedate2() {
		return createdate2;
	}

	public void setCreatedate2(String createdate) {
		this.createdate2 = createdate;
	}

	public int getTarediff() {
		return tarediff;
	}

	public void setTarediff(int tarediff) {
		this.tarediff = tarediff;
	}

	public int getTarehour() {
		return tarehour;
	}

	public void setTarehour(int tarehour) {
		this.tarehour = tarehour;
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

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getRfidid() {
		return rfidid;
	}

	public void setRfidid(String rfidid) {
		this.rfidid = rfidid;
	}

	public String getIcid() {
		return icid;
	}

	public void setIcid(String icid) {
		this.icid = icid;
	}

	public String getOperatype() {
		return operatype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
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

	public String getMaterialspeccode() {
		return materialspeccode;
	}

	public void setMaterialspeccode(String materialspeccode) {
		this.materialspeccode = materialspeccode;
	}

	public String getMaterialspec() {
		return materialspec;
	}

	public void setMaterialspec(String materialspec) {
		this.materialspec = materialspec;
	}

	public String getShipcode() {
		return shipcode;
	}

	public void setShipcode(String shipcode) {
		this.shipcode = shipcode;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
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

	public String getSourcetime() {
		return sourcetime;
	}

	public void setSourcetime(String sourcetime) {
		this.sourcetime = sourcetime;
	}

	public String getSourceplace() {
		return sourceplace;
	}

	public void setSourceplace(String sourceplace) {
		this.sourceplace = sourceplace;
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

	public String getTargettime() {
		return targettime;
	}

	public void setTargettime(String targettime) {
		this.targettime = targettime;
	}

	public String getTargetplace() {
		return targetplace;
	}

	public void setTargetplace(String targetplace) {
		this.targetplace = targetplace;
	}

	public String getBasket() {
		return basket;
	}

	public void setBasket(String basket) {
		this.basket = basket;
	}

	public double getPlanweight() {
		return planweight;
	}

	public void setPlanweight(double planweight) {
		this.planweight = planweight;
	}

	public double getPlanmaterialcount() {
		return planmaterialcount;
	}

	public int getMaterialcount() {
		return materialcount;
	}

	public void setMaterialcount(int materialcount) {
		this.materialcount = materialcount;
	}

	public int getPlancarcount() {
		return plancarcount;
	}

	public void setPlancarcount(int plancarcount) {
		this.plancarcount = plancarcount;
	}

	public void setPlanmaterialcount(int planmaterialcount) {
		this.planmaterialcount = planmaterialcount;
	}

	public double getGross() {
		return gross;
	}

	public void setGross(double gross) {
		this.gross = gross;
	}

	public String getGrosstime() {
		return grosstime;
	}

	public void setGrosstime(String grosstime) {
		this.grosstime = grosstime;
	}

	public String getGrossweighid() {
		return grossweighid;
	}

	public void setGrossweighid(String grossweighid) {
		this.grossweighid = grossweighid;
	}

	public String getGrossweigh() {
		return grossweigh;
	}

	public void setGrossweigh(String grossweigh) {
		this.grossweigh = grossweigh;
	}

	public String getGrossoperacode() {
		return grossoperacode;
	}

	public void setGrossoperacode(String grossoperacode) {
		this.grossoperacode = grossoperacode;
	}

	public String getGrossoperaname() {
		return grossoperaname;
	}

	public void setGrossoperaname(String grossoperaname) {
		this.grossoperaname = grossoperaname;
	}

	public double getGrossgroupno() {
		return grossgroupno;
	}

	public void setGrossgroupno(double grossgroupno) {
		this.grossgroupno = grossgroupno;
	}

	public double getGrossserial() {
		return grossserial;
	}

	public void setGrossserial(double grossserial) {
		this.grossserial = grossserial;
	}

	public double getGrossspeed() {
		return grossspeed;
	}

	public void setGrossspeed(double grossspeed) {
		this.grossspeed = grossspeed;
	}

	public String getGrosslogid() {
		return grosslogid;
	}

	public void setGrosslogid(String grosslogid) {
		this.grosslogid = grosslogid;
	}

	public double getTare() {
		return tare;
	}

	public void setTare(double tare) {
		this.tare = tare;
	}

	public String getTaretime() {
		return taretime;
	}

	public void setTaretime(String taretime) {
		this.taretime = taretime;
	}

	public String getTareweighid() {
		return tareweighid;
	}

	public void setTareweighid(String tareweighid) {
		this.tareweighid = tareweighid;
	}

	public String getTareweigh() {
		return tareweigh;
	}

	public void setTareweigh(String tareweigh) {
		this.tareweigh = tareweigh;
	}

	public String getTareoperacode() {
		return tareoperacode;
	}

	public void setTareoperacode(String tareoperacode) {
		this.tareoperacode = tareoperacode;
	}

	public String getTareoperaname() {
		return tareoperaname;
	}

	public void setTareoperaname(String tareoperaname) {
		this.tareoperaname = tareoperaname;
	}

	public double getTaregroupno() {
		return taregroupno;
	}

	public void setTaregroupno(double taregroupno) {
		this.taregroupno = taregroupno;
	}

	public double getTareserial() {
		return tareserial;
	}

	public void setTareserial(double tareserial) {
		this.tareserial = tareserial;
	}

	public String getTarelogid() {
		return tarelogid;
	}

	public void setTarelogid(String tarelogid) {
		this.tarelogid = tarelogid;
	}

	public double getTarespeed() {
		return tarespeed;
	}

	public void setTarespeed(double tarespeed) {
		this.tarespeed = tarespeed;
	}

	public double getDeduction() {
		return deduction;
	}

	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}

	public String getDeductiontime() {
		return deductiontime;
	}

	public void setDeductiontime(String deductiontime) {
		this.deductiontime = deductiontime;
	}

	public String getDeductioncode() {
		return deductioncode;
	}

	public void setDeductioncode(String deductioncode) {
		this.deductioncode = deductioncode;
	}

	public String getDeductionname() {
		return deductionname;
	}

	public void setDeductionname(String deductionname) {
		this.deductionname = deductionname;
	}

	public String getDeductionoperacode() {
		return deductionoperacode;
	}

	public void setDeductionoperacode(String deductionoperacode) {
		this.deductionoperacode = deductionoperacode;
	}

	public String getDeductionoperaname() {
		return deductionoperaname;
	}

	public void setDeductionoperaname(String deductionoperaname) {
		this.deductionoperaname = deductionoperaname;
	}

	public double getSuttle() {
		return suttle;
	}

	public void setSuttle(double suttle) {
		this.suttle = suttle;
	}

	public String getSuttletime() {
		return suttletime;
	}

	public void setSuttletime(String suttletime) {
		this.suttletime = suttletime;
	}

	public String getSuttleweighid() {
		return suttleweighid;
	}

	public void setSuttleweighid(String suttleweighid) {
		this.suttleweighid = suttleweighid;
	}

	public String getSuttleweigh() {
		return suttleweigh;
	}

	public void setSuttleweigh(String suttleweigh) {
		this.suttleweigh = suttleweigh;
	}

	public String getSuttleoperacode() {
		return suttleoperacode;
	}

	public void setSuttleoperacode(String suttleoperacode) {
		this.suttleoperacode = suttleoperacode;
	}

	public String getSuttleoperaname() {
		return suttleoperaname;
	}

	public void setSuttleoperaname(String suttleoperaname) {
		this.suttleoperaname = suttleoperaname;
	}

	public String getMatchidb() {
		return matchidb;
	}

	public void setMatchidb(String matchidb) {
		this.matchidb = matchidb;
	}

	public double getSuttleb() {
		return suttleb;
	}

	public void setSuttleb(double suttleb) {
		this.suttleb = suttleb;
	}

	public double getGrossb() {
		return grossb;
	}

	public void setGrossb(double grossb) {
		this.grossb = grossb;
	}

	public String getGrosstimeb() {
		return grosstimeb;
	}

	public void setGrosstimeb(String grosstimeb) {
		this.grosstimeb = grosstimeb;
	}

	public String getGrossweighidb() {
		return grossweighidb;
	}

	public void setGrossweighidb(String grossweighidb) {
		this.grossweighidb = grossweighidb;
	}

	public String getGrossweighb() {
		return grossweighb;
	}

	public void setGrossweighb(String grossweighb) {
		this.grossweighb = grossweighb;
	}

	public String getGrossoperacodeb() {
		return grossoperacodeb;
	}

	public void setGrossoperacodeb(String grossoperacodeb) {
		this.grossoperacodeb = grossoperacodeb;
	}

	public String getGrossoperanameb() {
		return grossoperanameb;
	}

	public void setGrossoperanameb(String grossoperanameb) {
		this.grossoperanameb = grossoperanameb;
	}

	public double getGrossgroupnob() {
		return grossgroupnob;
	}

	public void setGrossgroupnob(double grossgroupnob) {
		this.grossgroupnob = grossgroupnob;
	}

	public double getGrossserialb() {
		return grossserialb;
	}

	public void setGrossserialb(double grossserialb) {
		this.grossserialb = grossserialb;
	}

	public double getGrossspeedb() {
		return grossspeedb;
	}

	public void setGrossspeedb(double grossspeedb) {
		this.grossspeedb = grossspeedb;
	}

	public String getGrosslogidb() {
		return grosslogidb;
	}

	public void setGrosslogidb(String grosslogidb) {
		this.grosslogidb = grosslogidb;
	}

	public double getTareb() {
		return tareb;
	}

	public void setTareb(double tareb) {
		this.tareb = tareb;
	}

	public String getTaretimeb() {
		return taretimeb;
	}

	public void setTaretimeb(String taretimeb) {
		this.taretimeb = taretimeb;
	}

	public String getTareweighidb() {
		return tareweighidb;
	}

	public void setTareweighidb(String tareweighidb) {
		this.tareweighidb = tareweighidb;
	}

	public String getTareweighb() {
		return tareweighb;
	}

	public void setTareweighb(String tareweighb) {
		this.tareweighb = tareweighb;
	}

	public String getTareoperacodeb() {
		return tareoperacodeb;
	}

	public void setTareoperacodeb(String tareoperacodeb) {
		this.tareoperacodeb = tareoperacodeb;
	}

	public String getTareoperanameb() {
		return tareoperanameb;
	}

	public void setTareoperanameb(String tareoperanameb) {
		this.tareoperanameb = tareoperanameb;
	}

	public double getTaregroupnob() {
		return taregroupnob;
	}

	public void setTaregroupnob(double taregroupnob) {
		this.taregroupnob = taregroupnob;
	}

	public double getTareserialb() {
		return tareserialb;
	}

	public void setTareserialb(double tareserialb) {
		this.tareserialb = tareserialb;
	}

	public String getTarelogidb() {
		return tarelogidb;
	}

	public void setTarelogidb(String tarelogidb) {
		this.tarelogidb = tarelogidb;
	}

	public double getTarespeedb() {
		return tarespeedb;
	}

	public void setTarespeedb(double tarespeedb) {
		this.tarespeedb = tarespeedb;
	}

	public String getBatchcode() {
		return batchcode;
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}

	public int getBflag() {
		return bflag;
	}

	public void setBflag(int bflag) {
		this.bflag = bflag;
	}

	public int getPrintgrossnum() {
		return printgrossnum;
	}

	public void setPrintgrossnum(int printgrossnum) {
		this.printgrossnum = printgrossnum;
	}

	public int getPrintsuttlenum() {
		return printsuttlenum;
	}

	public void setPrintsuttlenum(int printsuttlenum) {
		this.printsuttlenum = printsuttlenum;
	}

	public int getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(int recordtype) {
		this.recordtype = recordtype;
	}

	public String getMotorcadename() {
		return motorcadename;
	}

	public void setMotorcadename(String motorcadename) {
		this.motorcadename = motorcadename;
	}

	public String getMotorcadecode() {
		return motorcadecode;
	}

	public void setMotorcadecode(String motorcadecode) {
		this.motorcadecode = motorcadecode;
	}

	public double getDeductiontype() {
		return deductiontype;
	}

	public void setDeductiontype(double deductiontype) {
		this.deductiontype = deductiontype;
	}

	public String getMemo1() {
		return memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public String getMemo2() {
		return memo2;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

	public String getMemo3() {
		return memo3;
	}

	public void setMemo3(String memo3) {
		this.memo3 = memo3;
	}

	public String getMemo4() {
		return memo4;
	}

	public void setMemo4(String memo4) {
		this.memo4 = memo4;
	}

	public String getMemo5() {
		return memo5;
	}

	public void setMemo5(String memo5) {
		this.memo5 = memo5;
	}

	public String getMemo6() {
		return memo6;
	}

	public void setMemo6(String memo6) {
		this.memo6 = memo6;
	}

	public String getMemo7() {
		return memo7;
	}

	public void setMemo7(String memo7) {
		this.memo7 = memo7;
	}

	public String getMemo8() {
		return memo8;
	}

	public void setMemo8(String memo8) {
		this.memo8 = memo8;
	}

	public String getMemo9() {
		return memo9;
	}

	public void setMemo9(String memo9) {
		this.memo9 = memo9;
	}

	public String getMemo10() {
		return memo10;
	}

	public void setMemo10(String memo10) {
		this.memo10 = memo10;
	}

	public String getMemo11() {
		return memo11;
	}

	public void setMemo11(String memo11) {
		this.memo11 = memo11;
	}

	public String getMemo12() {
		return memo12;
	}

	public void setMemo12(String memo12) {
		this.memo12 = memo12;
	}

	public String getMemo13() {
		return memo13;
	}

	public void setMemo13(String memo13) {
		this.memo13 = memo13;
	}

	public String getMemo14() {
		return memo14;
	}

	public void setMemo14(String memo14) {
		this.memo14 = memo14;
	}

	public String getMemo15() {
		return memo15;
	}

	public void setMemo15(String memo15) {
		this.memo15 = memo15;
	}

	public double getDeduction2() {
		return deduction2;
	}

	public void setDeduction2(double deduction2) {
		this.deduction2 = deduction2;
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

	public String getMflag() {
		return mflag;
	}

	public void setMflag(String mflag) {
		this.mflag = mflag;
	}

	public int getMaterialflow() {
		return materialflow;
	}

	public void setMaterialflow(int materialflow) {
		this.materialflow = materialflow;
	}

	public int getMstate() {
		return mstate;
	}

	public void setMstate(int mstate) {
		this.mstate = mstate;
	}
	
	
}
