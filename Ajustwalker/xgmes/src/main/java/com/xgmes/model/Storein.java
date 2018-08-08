package com.xgmes.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("storein")
public class Storein extends BaseModel {

	private static final long serialVersionUID = 9121974553900814179L;

	@Fields(name = "物流号")
	@Column(name = "matchid")
	private String matchid;
	
	@Fields(name = "申请单id")
	@Column(name = "itemid")
	private String itemid;
	
	@Fields(name = "业务类型")
	@Column(name = "operatype")
	private String operatype;
	
	@Fields(name = "运输类型")
	@Column(name = "cartype")
	private String cartype;
	
	@Fields(name = "车号")
	@Column(name = "carno")
	private String carno;
	
	@Fields(name = "卡号")
	@Column(name = "cardid")
	private String cardid;
	
	@Fields(name = "计划号")
	@Column(name = "planid")
	private String planid;
	
	@Fields(name = "业务号")
	@Column(name = "taskcode")
	private String taskcode;
	
	@Fields(name = "物料编码")
	@Column(name = "materialcode")
	private String materialcode;
	
	@Fields(name = "物料名称")
	@Column(name = "materialname")
	private String materialname;
	
	@Fields(name = "供货编码")
	@Column(name = "sourcecode")
	private String sourcecode;
	
	@Fields(name = "供货名称")
	@Column(name = "sourcename")
	private String sourcename;
	
	@Fields(name = "收货编码")
	@Column(name = "targetcode")
	private String targetcode;
	
	@Fields(name = "收货名称")
	@Column(name = "targetname")
	private String targetname;
	
	@Fields(name = "入库编码")
	@Column(name = "storecode")
	private String storecode;
	
	@Fields(name = "入库名称")
	@Column(name = "storename")
	private String storename;
	
	@Fields(name = "船名编码")
	@Column(name = "shipcode")
	private String shipcode;
	
	@Fields(name = "船名")
	@Column(name = "ship")
	private String ship;
	
	@Fields(name = "数量")
	@Column(name = "counts")
	private double counts;
	
	private double outcounts;
	
	@Fields(name = "入库重量")
	@Column(name = "weight")
	private double weight;
	
	@Fields(name = "净重")
	@Column(name = "suttle")
	private double suttle;
	
	@Fields(name = "净重时间")
	@Column(name = "suttletime")
	private String suttletime;
	
	@Fields(name = "扣重")
	@Column(name = "deduction")
	private double deduction;
	
	@Fields(name = "扣重时间")
	@Column(name = "deductiontime")
	private String deductiontime;
	
	@Fields(name = "扣重单位编码")
	@Column(name = "deductioncode")
	private String deductioncode;
	
	@Fields(name = "扣重单位名称")
	@Column(name = "deductionname")
	private String deductionname;
	
	@Fields(name = "扣重人编码")
	@Column(name = "deductionoperacode")
	private String deductionoperacode;
	
	@Fields(name = "扣重人")
	@Column(name = "deductionoperaname")
	private String deductionoperaname;
	
	@Fields(name = "收货方式")
	@Column(name = "recetype")
	private int recetype;
	
	@Fields(name = "用户名称")
	@Column(name = "createoperaname")
	private String createoperaname;
	
	@Fields(name = "开始时间")
	@Column(name = "begintime")
	private String begintime;	
	
	@Fields(name = "结束时间")
	@Column(name = "endtime")
	private String endtime;	
	
	@Fields(name = "扣重类型")
	@Column(name = "deductiontype")
	private String deductiontype;
	
	@Fields(name = "库位编码")
	@Column(name = "postioncode")
	private String postioncode;
	
	@Fields(name = "库位")
	@Column(name = "storepos")
	private String storepos;
	
	@Fields(name = "入库时间")
	@Column(name = "storeintime")
	private String storeintime;
	
	@Fields(name = "班别")
	@Column(name = "workgroup")
	private String workgroup;
	
	@Fields(name = "班次")
	@Column(name = "workgroupindex")
	private String workgroupindex;
	
	
	
	@Fields(name = "用户编码")
	@Column(name = "createoperacode")
	private String createoperacode;
	
	@Fields(name = "当前单位")
	private String unitname;
	
	@Fields(name = "当前单位")
	private String unitcode;
	
	@Fields(name = "毛重")
	private double gross;
	
	@Fields(name = "皮重")
	private double tare;
	
	
	private String materialspec;
	
	private String  modelno;
	
	private String  measureunit;
	
	private String  pictureno;
	
	private String  prodlinename;
	
	private String isormeasure="0";
	
	private String operaname;
	
	private String createdate;
	
	private String middlemanname;
	
	private double plancounts = 0;
	
	private int carcounts=0; //总车数
	
	private List<Map<String, String>> exparams = null;
	
	private int exceptionflag=0; //出入库异常标记 0正常数据；1异常数据
	
	public String getMatchid() {
		return matchid;
	}
	
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
	
	public String getItemid() {
		return itemid;
	}
	
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	
	public String getOperatype() {
		return operatype;
	}
	
	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}
	
	public String getCartype() {
		return cartype;
	}
	
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	
	public String getCarno() {
		return carno;
	}
	
	public void setCarno(String carno) {
		this.carno = carno;
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
	
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
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
	
	public int getRecetype() {
		return recetype;
	}
	
	public void setRecetype(int recetype) {
		this.recetype = recetype;
	}

	public String getCreateoperaname() {
		return createoperaname;
	}
	
	public void setCreateoperaname(String createoperaname) {
		this.createoperaname = createoperaname;
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
	
	public String getDeductiontype() {
		return deductiontype;
	}
	
	public void setDeductiontype(String deductiontype) {
		this.deductiontype = deductiontype;
	}
	
	public String getPostioncode() {
		return postioncode;
	}
	
	public void setPostioncode(String postioncode) {
		this.postioncode = postioncode;
	}
	
	public String getStorepos() {
		return storepos;
	}
	
	public void setStorepos(String storepos) {
		this.storepos = storepos;
	}
	
	public String getStoreintime() {
		return storeintime;
	}
	
	public void setStoreintime(String storeintime) {
		this.storeintime = storeintime;
	}
	
	public String getCardid() {
		return cardid;
	}
	
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(String workgroup) {
		this.workgroup = workgroup;
	}

	public String getWorkgroupindex() {
		return workgroupindex;
	}

	public void setWorkgroupindex(String workgroupindex) {
		this.workgroupindex = workgroupindex;
	}

	public String getCreateoperacode() {
		return createoperacode;
	}

	public void setCreateoperacode(String createoperacode) {
		this.createoperacode = createoperacode;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public double getGross() {
		return gross;
	}

	public void setGross(double gross) {
		this.gross = gross;
	}

	public double getTare() {
		return tare;
	}

	public void setTare(double tare) {
		this.tare = tare;
	}

	public String getMaterialspec() {
		return materialspec;
	}

	public void setMaterialspec(String materialspec) {
		this.materialspec = materialspec;
	}

	public String getModelno() {
		return modelno;
	}

	public void setModelno(String modelno) {
		this.modelno = modelno;
	}

	public String getMeasureunit() {
		return measureunit;
	}

	public void setMeasureunit(String measureunit) {
		this.measureunit = measureunit;
	}

	public String getPictureno() {
		return pictureno;
	}

	public void setPictureno(String pictureno) {
		this.pictureno = pictureno;
	}

	public String getProdlinename() {
		return prodlinename;
	}

	public void setProdlinename(String prodlinename) {
		this.prodlinename = prodlinename;
	}

	

	public List<Map<String, String>> getExparams() {
		return exparams;
	}

	public void setExparams(List<Map<String, String>> exparams) {
		this.exparams = exparams;
	}

	public double getCounts() {
		return counts;
	}

	public void setCounts(double counts) {
		this.counts = counts;
	}

	public double getOutcounts() {
		return outcounts;
	}

	public void setOutcounts(double outcounts) {
		this.outcounts = outcounts;
	}

	public String getIsormeasure() {
		return isormeasure;
	}

	public void setIsormeasure(String isormeasure) {
		this.isormeasure = isormeasure;
	}

	public String getOperaname() {
		return operaname;
	}

	public void setOperaname(String operaname) {
		this.operaname = operaname;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getMiddlemanname() {
		return middlemanname;
	}

	public void setMiddlemanname(String middlemanname) {
		this.middlemanname = middlemanname;
	}

	public double getPlancounts() {
		return plancounts;
	}

	public void setPlancounts(double plancounts) {
		this.plancounts = plancounts;
	}

	public int getCarcounts() {
		return carcounts;
	}

	public void setCarcounts(int carcounts) {
		this.carcounts = carcounts;
	}

	public int getExceptionflag() {
		return exceptionflag;
	}

	public void setExceptionflag(int exceptionflag) {
		this.exceptionflag = exceptionflag;
	}
	
	
}
