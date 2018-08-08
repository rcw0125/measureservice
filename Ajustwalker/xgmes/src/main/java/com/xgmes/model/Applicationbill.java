package com.xgmes.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("application")
public class Applicationbill extends BaseModel {

	private static final long serialVersionUID = -5611336762744096415L;

	@Fields(name = "物流号")
	@Column(name = "matchid")
	private String matchid = "";

	@Fields(name = "车号")
	@Column(name = "cardno")
	private String carno = "";

	@Fields(name = "卡号")
	@Column(name = "icid")
	private String icid = "";

	@Fields(name = "RFID卡号")
	@Column(name = "rfidid")
	private String rfidid = "";

	@Fields(name = "业务类型")
	@Column(name = "operatype")
	private String operatype = "";

	@Fields(name = "车辆类型")
	@Column(name = "cartype")
	private String cartype = "";

	@Fields(name = "供货单位编码")
	@Column(name = "sourcecode")
	private String sourcecode = "";

	@Fields(name = "供货单位")
	@Column(name = "sourcename")
	private String sourcename = "";

	@Fields(name = "收货单位编码")
	@Column(name = "targetcode")
	private String targetcode = "";

	@Fields(name = "收货单位")
	@Column(name = "targetname")
	private String targetname = "";

	@Fields(name = "子表id")
	@Column(name = "itemid")
	private String itemid = "";

	@Fields(name = "计划号")
	@Column(name = "planid")
	private String planid = "";

	@Fields(name = "业务号")
	@Column(name = "taskcode")
	private String taskcode = "";

	@Fields(name = "订单号")
	@Column(name = "orderno")
	private String orderno = "";

	@Fields(name = "物料编码")
	@Column(name = "materialcode")
	private String materialcode = "";

	@Fields(name = "物料名称")
	@Column(name = "materialname")
	private String materialname = "";

	@Fields(name = "发货点")
	@Column(name = "sourceplace")
	private String sourceplace = "";

	@Fields(name = "收货人")
	@Column(name = "sourceoperaname")
	private String sourceoperaname = "";

	@Fields(name = "收货点")
	@Column(name = "targetplace")
	private String targetplace = "";

	@Fields(name = "计划数量")
	@Column(name = "planmaterialcount")
	private double planmaterialcount = 0;

	@Fields(name = "计划量")
	@Column(name = "planweight")
	private double planweight = 0;

	@Fields(name = "供方毛重")
	@Column(name = "grossb")
	private double grossb = 0;

	@Fields(name = "供方皮重")
	@Column(name = "tareb")
	private double tareb = 0;

	@Fields(name = "供方净重")
	@Column(name = "suttleb")
	private double suttleb = 0;

	@Fields(name = "船名编码")
	@Column(name = "shipcode")
	private String shipcode = "";

	@Fields(name = "船名")
	@Column(name = "ship")
	private String ship = "";

	@Fields(name = "当车数量")
	@Column(name = "snumber")
	private double snumber = 0;

	@Fields(name = "当车重量")
	@Column(name = "suttleapp")
	private double suttleapp = 0;

	@Fields(name = "中间商编码")
	@Column(name = "middlemancode")
	private String middlemancode;

	@Fields(name = "中间商名称")
	@Column(name = "middlemanname")
	private String middlemanname;

	@Fields(name = "当前单位编码")
	@Column(name = "unitcode")
	private String unitcode;

	@Fields(name = "当前单位")
	@Column(name = "unitname")
	private String unitname;

	@Fields(name = "开始时间")
	private String begintime;

	@Fields(name = "结束时间")
	private String endtime;

	@Fields(name = "计量单位")
	private String measureunit;

	@Fields(name = "审核等级")
	@Column(name = "auditlevel")
	private int auditlevel = 0;

	@Fields(name = "一次审核人")
	@Column(name = "approverfirst")
	private String approverfirst;

	@Fields(name = "一次审核时间")
	@Column(name = "firsttime")
	private String firsttime;

	@Fields(name = "二次审核人")
	@Column(name = "approversecond")
	private String approversecond;

	@Fields(name = "二次审核时间")
	@Column(name = "secondtime")
	private String secondtime;

	@Fields(name = "类型")
	@Column(name = "types")
	private String types;

	@Fields(name = "弃审人")
	@Column(name = "giveupfirst")
	private String giveupfirst;

	@Fields(name = "净重")
	@Column(name = "suttle")
	private double suttle = 0;

	@Fields(name = "净重时间")
	@Column(name = "suttletime")
	private String suttletime = "";

	@Fields(name = "是否返厂")
	@Column(name = "isorin")
	private double isorin = 0;

	@Fields(name = "是否出厂")
	@Column(name = "isorout")
	private double isorout = 0;

	@Fields(name = "计划返厂时间")
	@Column(name = "returntime")
	private String returntime;

	@Fields(name = "计划出厂时间")
	@Column(name = "outtime")
	private String outtime;

	@Fields(name = "累计返厂")
	@Column(name = "returntotal")
	private double returntotal = 0;

	@Fields(name = "累计出厂")
	@Column(name = "outtotal")
	private double outtotal = 0;

	@Fields(name = "单据类型")
	@Column(name = "documentcode")
	private String documentcode;

	@Fields(name = "原始单据号")
	@Column(name = "fdocumentno")
	private String fdocumentno;

	@Fields(name = "业务名称")
	@Column(name = "businessname")
	private String businessname;

	@Fields(name = "经办人")
	@Column(name = "saleman")
	private String saleman;

	@Fields(name = "计划进厂/出厂时间")
	@Column(name = "inoutdate")
	private String inoutdate;

	@Fields(name = "是否计重")
	@Column(name = "isormeasure")
	private String isormeasure = "0";

	@Fields(name = "事由")
	@Column(name = "reason")
	private String reason;

	@Fields(name = "路线id")
	@Column(name = "routeid")
	private String routeid;

	@Fields(name = "单据是否使用")
	@Column(name = "isrelease")
	private String isrelease = "0";

	@Fields(name = "原始单据自行号")
	@Column(name = "saleitemid")
	private String saleitemid = "";

	private String createoperacode;

	@Fields(name = "计量状态")
	private String mstate = "0";

	@Fields(name = "退货状态")
	private String bflag;

	@Fields(name = "进厂时间")
	private String entrytime;

	@Fields(name = "进厂大门")
	private String entrygate;

	@Fields(name = "车队编码")
	private String motorcadecode;

	@Fields(name = "车队名称")
	private String motorcadename;

	@Fields(name = "进厂时间")
	private String entertime;

	@Fields(name = "进厂大门")
	private String entergatename;

	private int fkflag = 0;

	private String cardstate;

	private String driver; // 司机

	private double deposit = 0; // 押金

	private int secondflag = 0;// 查询二级审核标记，为0时不查询，为1时查询需要二级审核

	private List<Map<String, String>> exparams = null;

	private String memo="";

	private String memo1="";// 计量顺序

	private String memo2="";// 计量顺序

	private double materialcounts = 0.0;

	private String materialspec;

	private String steelname;

	private String pictureno;

	private String prodline;

	private String upplanidlist;

	private String upitemidlist;

	private String isoruse = "0";// 是否使用

	private String fendanflag;

	private String operaname;

	private int materialflow = 0; // 物资流向：0厂内，1进厂，2出厂

	private double gross = 0.0;

	private double tare = 0.0;
	
	private String sourcetime;
	
	private String targettime;
	
	private String sampletime;
	
	private String taretime;
	
	private String grosstime;
	
	private String createdate;
	
	private String memo3="";// 入库备注

	private String memo4="";// 出库备注
	
	private String nodecode;
	
	private int rfidflag = 0;
	
	private String mflag;// 计量顺序 1先毛后皮 2先皮后毛
	
	private String arrivetime;
	
	private String status;//当前车辆状态
	
	private int cpflag=0;//单据复制标记 0否 1是
	
	private int updatencflag = 0;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updatencdate = null; 
	
	private String documentname;
	
	private String  leavetime;
	
	private String leavegatename;
	
	private String materialcodes; //存放物料编码
	
	private String rfidbak;//备份rfid
	
	private String checkweighman;//监秤人
	
	private String checkweighid;//监秤人ID
	
	

	public List<Map<String, String>> getExparams() {
		return exparams;
	}

	public void setExparams(List<Map<String, String>> exparams) {
		this.exparams = exparams;
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

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
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

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
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

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
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

	public String getSourceplace() {
		return sourceplace;
	}

	public void setSourceplace(String sourceplace) {
		this.sourceplace = sourceplace;
	}

	public String getSourceoperaname() {
		return sourceoperaname;
	}

	public void setSourceoperaname(String sourceoperaname) {
		this.sourceoperaname = sourceoperaname;
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

	public String getMiddlemancode() {
		return middlemancode;
	}

	public void setMiddlemancode(String middlemancode) {
		this.middlemancode = middlemancode;
	}

	public String getMiddlemanname() {
		return middlemanname;
	}

	public void setMiddlemanname(String middlemanname) {
		this.middlemanname = middlemanname;
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

	public int getAuditlevel() {
		return auditlevel;
	}

	public void setAuditlevel(int auditlevel) {
		this.auditlevel = auditlevel;
	}

	public String getApproverfirst() {
		return approverfirst;
	}

	public void setApproverfirst(String approverfirst) {
		this.approverfirst = approverfirst;
	}

	public String getFirsttime() {
		return firsttime;
	}

	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}

	public String getApproversecond() {
		return approversecond;
	}

	public void setApproversecond(String approversecond) {
		this.approversecond = approversecond;
	}

	public String getSecondtime() {
		return secondtime;
	}

	public void setSecondtime(String secondtime) {
		this.secondtime = secondtime;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getGiveupfirst() {
		return giveupfirst;
	}

	public void setGiveupfirst(String giveupfirst) {
		this.giveupfirst = giveupfirst;
	}

	public String getTargetplace() {
		return targetplace;
	}

	public void setTargetplace(String targetplace) {
		this.targetplace = targetplace;
	}

	public double getPlanmaterialcount() {
		return planmaterialcount;
	}

	public void setPlanmaterialcount(double planmaterialcount) {
		this.planmaterialcount = planmaterialcount;
	}

	public double getPlanweight() {
		return planweight;
	}

	public void setPlanweight(double planweight) {
		this.planweight = planweight;
	}

	public double getGrossb() {
		return grossb;
	}

	public void setGrossb(double grossb) {
		this.grossb = grossb;
	}

	public double getTareb() {
		return tareb;
	}

	public void setTareb(double tareb) {
		this.tareb = tareb;
	}

	public double getSuttleb() {
		return suttleb;
	}

	public void setSuttleb(double suttleb) {
		this.suttleb = suttleb;
	}

	public double getSnumber() {
		return snumber;
	}

	public void setSnumber(double snumber) {
		this.snumber = snumber;
	}

	public double getSuttleapp() {
		return suttleapp;
	}

	public void setSuttleapp(double suttleapp) {
		this.suttleapp = suttleapp;
	}

	public String getMstate() {
		return mstate;
	}

	public void setMstate(String mstate) {
		this.mstate = mstate;
	}

	public String getBflag() {
		return bflag;
	}

	public void setBflag(String bflag) {
		this.bflag = bflag;
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

	public String getMeasureunit() {
		return measureunit;
	}

	public void setMeasureunit(String measureunit) {
		this.measureunit = measureunit;
	}

	public double getIsorin() {
		return isorin;
	}

	public void setIsorin(double isorin) {
		this.isorin = isorin;
	}

	public double getIsorout() {
		return isorout;
	}

	public void setIsorout(double isorout) {
		this.isorout = isorout;
	}

	public String getReturntime() {
		return returntime;
	}

	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	public double getReturntotal() {
		return returntotal;
	}

	public void setReturntotal(double returntotal) {
		this.returntotal = returntotal;
	}

	public double getOuttotal() {
		return outtotal;
	}

	public void setOuttotal(double outtotal) {
		this.outtotal = outtotal;
	}

	public String getDocumentcode() {
		return documentcode;
	}

	public void setDocumentcode(String documentcode) {
		this.documentcode = documentcode;
	}

	public String getCreateoperacode() {
		return createoperacode;
	}

	public void setCreateoperacode(String createoperacode) {
		this.createoperacode = createoperacode;
	}

	public String getFdocumentno() {
		return fdocumentno;
	}

	public void setFdocumentno(String fdocumentno) {
		this.fdocumentno = fdocumentno;
	}

	public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getSaleman() {
		return saleman;
	}

	public void setSaleman(String saleman) {
		this.saleman = saleman;
	}

	public String getInoutdate() {
		return inoutdate;
	}

	public void setInoutdate(String inoutdate) {
		this.inoutdate = inoutdate;
	}

	public String getIsormeasure() {
		return isormeasure;
	}

	public void setIsormeasure(String isormeasure) {
		this.isormeasure = isormeasure;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getEntrygate() {
		return entrygate;
	}

	public void setEntrygate(String entrygate) {
		this.entrygate = entrygate;
	}

	public String getMotorcadecode() {
		return motorcadecode;
	}

	public void setMotorcadecode(String motorcadecode) {
		this.motorcadecode = motorcadecode;
	}

	public String getMotorcadename() {
		return motorcadename;
	}

	public void setMotorcadename(String motorcadename) {
		this.motorcadename = motorcadename;
	}

	public String getEntertime() {
		return entertime;
	}

	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}

	public String getEntergatename() {
		return entergatename;
	}

	public void setEntergatename(String entergatename) {
		this.entergatename = entergatename;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public int getFkflag() {
		return fkflag;
	}

	public void setFkflag(int fkflag) {
		this.fkflag = fkflag;
	}

	public String getCardstate() {
		return cardstate;
	}

	public void setCardstate(String cardstate) {
		this.cardstate = cardstate;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public String getIsrelease() {
		return isrelease;
	}

	public void setIsrelease(String isrelease) {
		this.isrelease = isrelease;
	}

	public int getSecondflag() {
		return secondflag;
	}

	public void setSecondflag(int secondflag) {
		this.secondflag = secondflag;
	}

	public String getMemo1() {
		return memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public String getSaleitemid() {
		return saleitemid;
	}

	public void setSaleitemid(String saleitemid) {
		this.saleitemid = saleitemid;
	}

	public double getMaterialcounts() {
		return materialcounts;
	}

	public void setMaterialcounts(double materialcounts) {
		this.materialcounts = materialcounts;
	}

	public String getMaterialspec() {
		return materialspec;
	}

	public void setMaterialspec(String materialspec) {
		this.materialspec = materialspec;
	}

	public String getSteelname() {
		return steelname;
	}

	public void setSteelname(String steelname) {
		this.steelname = steelname;
	}

	public String getPictureno() {
		return pictureno;
	}

	public void setPictureno(String pictureno) {
		this.pictureno = pictureno;
	}

	public String getProdline() {
		return prodline;
	}

	public void setProdline(String prodline) {
		this.prodline = prodline;
	}

	public String getUpplanidlist() {
		return upplanidlist;
	}

	public void setUpplanidlist(String upplanidlist) {
		this.upplanidlist = upplanidlist;
	}

	public String getUpitemidlist() {
		return upitemidlist;
	}

	public void setUpitemidlist(String upitemidlist) {
		this.upitemidlist = upitemidlist;
	}

	public String getIsoruse() {
		return isoruse;
	}

	public void setIsoruse(String isoruse) {
		this.isoruse = isoruse;
	}

	public String getRfidid() {
		return rfidid;
	}

	public void setRfidid(String rfidid) {
		this.rfidid = rfidid;
	}

	public String getMemo2() {
		return memo2;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

	public String getFendanflag() {
		return fendanflag;
	}

	public void setFendanflag(String fendanflag) {
		this.fendanflag = fendanflag;
	}

	public String getOperaname() {
		return operaname;
	}

	public void setOperaname(String operaname) {
		this.operaname = operaname;
	}

	public int getMaterialflow() {
		return materialflow;
	}

	public void setMaterialflow(int materialflow) {
		this.materialflow = materialflow;
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

	public String getSourcetime() {
		return sourcetime;
	}

	public void setSourcetime(String sourcetime) {
		this.sourcetime = sourcetime;
	}

	public String getTargettime() {
		return targettime;
	}

	public void setTargettime(String targettime) {
		this.targettime = targettime;
	}

	public String getSampletime() {
		return sampletime;
	}

	public void setSampletime(String sampletime) {
		this.sampletime = sampletime;
	}

	public String getTaretime() {
		return taretime;
	}

	public void setTaretime(String taretime) {
		this.taretime = taretime;
	}

	public String getGrosstime() {
		return grosstime;
	}

	public void setGrosstime(String grosstime) {
		this.grosstime = grosstime;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
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

	public String getNodecode() {
		return nodecode;
	}

	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}

	public int getRfidflag() {
		return rfidflag;
	}

	public void setRfidflag(int rfidflag) {
		this.rfidflag = rfidflag;
	}

	public String getMflag() {
		return mflag;
	}

	public void setMflag(String mflag) {
		this.mflag = mflag;
	}

	public String getArrivetime() {
		return arrivetime;
	}

	public void setArrivetime(String arrivetime) {
		this.arrivetime = arrivetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCpflag() {
		return cpflag;
	}

	public void setCpflag(int cpflag) {
		this.cpflag = cpflag;
	}

	public int getUpdatencflag() {
		return updatencflag;
	}

	public void setUpdatencflag(int updatencflag) {
		this.updatencflag = updatencflag;
	}

	public Date getUpdatencdate() {
		return updatencdate;
	}

	public void setUpdatencdate(Date updatencdate) {
		this.updatencdate = updatencdate;
	}

	public String getDocumentname() {
		return documentname;
	}

	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}

	public String getLeavetime() {
		return leavetime;
	}

	public void setLeavetime(String leavetime) {
		this.leavetime = leavetime;
	}

	public String getLeavegatename() {
		return leavegatename;
	}

	public void setLeavegatename(String leavegatename) {
		this.leavegatename = leavegatename;
	}

	public String getMaterialcodes() {
		return materialcodes;
	}

	public void setMaterialcodes(String materialcodes) {
		this.materialcodes = materialcodes;
	}

	public String getRfidbak() {
		return rfidbak;
	}

	public void setRfidbak(String rfidbak) {
		this.rfidbak = rfidbak;
	}

	public String getCheckweighman() {
		return checkweighman;
	}

	public void setCheckweighman(String checkweighman) {
		this.checkweighman = checkweighman;
	}

	public String getCheckweighid() {
		return checkweighid;
	}

	public void setCheckweighid(String checkweighid) {
		this.checkweighid = checkweighid;
	}
	
}
