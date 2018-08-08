package com.talent.report.model;
import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("measureDetail")
public class MeasureDetail extends BaseModel {

	private static final long serialVersionUID = 3119294725300144675L;
	
	private String matchid; // 物流号
	
	private String carno; // 车号
	
	private String taskcode; // 业务号
	
	private String planid; // 计划号
	
	private String materialname; // 物料名称
	
	private String materialspec;//规格型号
	
	private String sourcename; // 供货单位名称
	
	private String targetname; // 收货单位名称
	
	private double tare; // 皮重
	
	private String taretime; // 皮重时间
	
	private String tareweigh; // 皮重衡器
	
	private String tareweighid; // 皮重衡器id
	
	private double gross; // 毛重
	
	private String grosstime; // 毛重时间
	
	private String grossweigh; // 毛重衡器
	
	private String grossweighid; // 毛重衡器id
	
	private double suttle; // 净重衡器
	
	private String suttletime; // 净重时间
	
	private String suttleweigh; // 净重衡器
	
	private String suttleweighid;// 净重衡器id
	
	private String operatype; // 业务类型
	
	private String materialcode;// 物料编码
	
	private String grossoperaname;// 毛重计量员名称
	
	private String tareoperaname;// 皮重计量员名称
	
	private String suttleoperaname;// 净重计量员名称
	
	private String begintime;
	
	private String endtime;
	
	private String grouptype;
	
	private String selecttime;
	
	private int carcount = 0;
	
	private String ship; // 船名
	
	private String sourceplace; // 港口
	
    private double accordrate; //称差率
    
    private double suttleb;
    
    private double grossb;
    
    private double tareb;
    
    private String measurestate;
    
    private String operaman;
    
    private String memo;
    
    private String createdate;
    
    private String recordtype;
    
    private String grosstimeb;
    
    private double deduction;
    
    private double deductionsuttle;
    
    private double jsweight;//结算量
    
    private String ks;//客商
    
    private String titlename;//报表名称
    
    private String[] operatypes;
    
    private int printflag;
    
    private int printcount;
    
    private String nomatchid;
    
    private String[] nomatchids;//不打印磅单号
    
    private String reportname;//报表名头
    
    public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	public String getNomatchid() {
		return nomatchid;
	}

	public void setNomatchid(String nomatchid) {
		this.nomatchid = nomatchid;
	}

	public String[] getNomatchids() {
		return nomatchids;
	}

	public void setNomatchids(String[] nomatchids) {
		this.nomatchids = nomatchids;
	}

	public int getPrintflag() {
		return printflag;
	}

	public void setPrintflag(int printflag) {
		this.printflag = printflag;
	}

	public int getPrintcount() {
		return printcount;
	}

	public void setPrintcount(int printcount) {
		this.printcount = printcount;
	}

	public String[] getOperatypes() {
		return operatypes;
	}

	public void setOperatypes(String[] operatypes) {
		this.operatypes = operatypes;
	}

	public double getDeduction() {
		return deduction;
	}

	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}

	public String getGrosstimeb() {
		return grosstimeb;
	}

	public void setGrosstimeb(String grosstimeb) {
		this.grosstimeb = grosstimeb;
	}

	public String getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}

	public String getOperaman() {
		return operaman;
	}

	public void setOperaman(String operaman) {
		this.operaman = operaman;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getMeasurestate() {
		return measurestate;
	}

	public void setMeasurestate(String measurestate) {
		this.measurestate = measurestate;
	}

	private int printsum;  //打印次数
    

	public int getPrintsum() {
		return printsum;
	}

	public void setPrintsum(int printsum) {
		this.printsum = printsum;
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

	public double getTareb() {
		return tareb;
	}

	public void setTareb(double tareb) {
		this.tareb = tareb;
	}

	public double getAccordrate() {
		return accordrate;
	}

	public void setAccordrate(double accordrate) {
		this.accordrate = accordrate;
	}

	public String getTareweighid() {
		return tareweighid;
	}

	public void setTareweighid(String tareweighid) {
		this.tareweighid = tareweighid;
	}

	public String getGrossweighid() {
		return grossweighid;
	}

	public void setGrossweighid(String grossweighid) {
		this.grossweighid = grossweighid;
	}

	public String getSuttleweighid() {
		return suttleweighid;
	}

	public void setSuttleweighid(String suttleweighid) {
		this.suttleweighid = suttleweighid;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getSourceplace() {
		return sourceplace;
	}

	public void setSourceplace(String sourceplace) {
		this.sourceplace = sourceplace;
	}

	public String getSelecttime() {
		return selecttime;
	}

	public void setSelecttime(String selecttime) {
		this.selecttime = selecttime;
	}

	public int getCarcount() {
		return carcount;
	}

	public void setCarcount(int carcount) {
		this.carcount = carcount;
	}

	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
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

	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getTargetname() {
		return targetname;
	}

	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}

	public String getTaretime() {
		return taretime;
	}

	public void setTaretime(String taretime) {
		this.taretime = taretime;
	}

	public String getTareweigh() {
		return tareweigh;
	}

	public void setTareweigh(String tareweigh) {
		this.tareweigh = tareweigh;
	}

	public String getGrosstime() {
		return grosstime;
	}

	public void setGrosstime(String grosstime) {
		this.grosstime = grosstime;
	}

	public String getGrossweigh() {
		return grossweigh;
	}

	public void setGrossweigh(String grossweigh) {
		this.grossweigh = grossweigh;
	}

	public double getTare() {
		return tare;
	}

	public void setTare(double tare) {
		this.tare = tare;
	}

	public double getGross() {
		return gross;
	}

	public void setGross(double gross) {
		this.gross = gross;
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

	public String getSuttleweigh() {
		return suttleweigh;
	}

	public void setSuttleweigh(String suttleweigh) {
		this.suttleweigh = suttleweigh;
	}

	public String getOperatype() {
		return operatype;
	}

	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}

	public String getMaterialcode() {
		return materialcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}

	public String getGrossoperaname() {
		return grossoperaname;
	}

	public void setGrossoperaname(String grossoperaname) {
		this.grossoperaname = grossoperaname;
	}

	public String getTareoperaname() {
		return tareoperaname;
	}

	public void setTareoperaname(String tareoperaname) {
		this.tareoperaname = tareoperaname;
	}

	public String getSuttleoperaname() {
		return suttleoperaname;
	}

	public void setSuttleoperaname(String suttleoperaname) {
		this.suttleoperaname = suttleoperaname;
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

	public double getJsweight() {
		return jsweight;
	}

	public void setJsweight(double jsweight) {
		this.jsweight = jsweight;
	}

	public String getKs() {
		return ks;
	}

	public void setKs(String ks) {
		this.ks = ks;
	}

	public String getTitlename() {
		return titlename;
	}

	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}

	public String getMaterialspec() {
		return materialspec;
	}

	public void setMaterialspec(String materialspec) {
		this.materialspec = materialspec;
	}

	public double getDeductionsuttle() {
		return deductionsuttle;
	}

	public void setDeductionsuttle(double deductionsuttle) {
		this.deductionsuttle = deductionsuttle;
	}
}
