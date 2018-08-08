package com.talent.materialflow.model;

import javax.persistence.Column;
import org.apache.ibatis.type.Alias;
import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("quality")
public class Quality extends BaseModel {

	private static final long serialVersionUID = 776739993293773957L;

	@Fields(name = "车号")
	@Column(name = "carno")
	private String carno;
    
	@Fields(name = "卡号")
	@Column(name = "cardid")
	private String cardid;
	
	@Fields(name = "物流号")
	@Column(name = "matchid")
	private String matchid;

	@Fields(name = "计划号")
	@Column(name = "planid")
	private String planid;
	
	@Fields(name = "订单号")
	@Column(name = "orderno")
	private String orderno;
	
	@Fields(name = "中间商")
	@Column(name = "middlemanname")
	private String middlemanname;
	
	@Fields(name = "港口")
	@Column(name = "sourceplace")
	private String sourceplace;

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

	@Fields(name = "收货单位")
	@Column(name = "targetname")
	private String targetname;

	@Fields(name = "取样地点编码")
	@Column(name = "qmpostioncode")
	private String qmpostioncode;

	@Fields(name = "取样地点")
	@Column(name = "qmpostion")
	private String qmpostion;

	@Fields(name = "添加单位编码")
	@Column(name = "unitcode")
	private String unitcode;

	@Fields(name = "添加单位")
	@Column(name = "unitname")
	private String unitname;

	@Fields(name = "添加时间")
	@Column(name = "createdate")
	private String createdate;

	@Fields(name = "预留字段1")
	@Column(name = "memo1")
	private String memo1;

	@Fields(name = "预留字段2")
	@Column(name = "memo2")
	private String memo2;

	@Fields(name = "预留字段3")
	@Column(name = "memo3")
	private String memo3;

	@Fields(name = "预留字段4")
	@Column(name = "memo4")
	private String memo4;

	@Fields(name = "预留字段5")
	@Column(name = "memo5")
	private String memo5;
	
	@Fields(name = "取样时间")
	@Column(name = "sampletime")
	private String sampletime;
	
	@Fields(name = "开始时间")
	@Column(name = "begintime")
	private String begintime;
	
	@Fields(name = "结束时间")
	@Column(name = "endtime")
	private String endtime;
	
	@Fields(name = "用户编码")
	private String createoperacode;
	
	
	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getMiddlemanname() {
		return middlemanname;
	}

	public void setMiddlemanname(String middlemanname) {
		this.middlemanname = middlemanname;
	}

	public String getSourceplace() {
		return sourceplace;
	}

	public void setSourceplace(String sourceplace) {
		this.sourceplace = sourceplace;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	public String getQmpostioncode() {
		return qmpostioncode;
	}

	public void setQmpostioncode(String qmpostioncode) {
		this.qmpostioncode = qmpostioncode;
	}

	public String getQmpostion() {
		return qmpostion;
	}

	public void setQmpostion(String qmpostion) {
		this.qmpostion = qmpostion;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
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

	public String getSampletime() {
		return sampletime;
	}

	public void setSampletime(String sampletime) {
		this.sampletime = sampletime;
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

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCreateoperacode() {
		return createoperacode;
	}

	public void setCreateoperacode(String createoperacode) {
		this.createoperacode = createoperacode;
	}
	
}
