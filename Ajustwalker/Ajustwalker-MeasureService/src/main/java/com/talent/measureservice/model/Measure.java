package com.talent.measureservice.model;

import org.apache.ibatis.type.Alias;

import com.talent.core.model.BaseModel;

@Alias("measure")
public class Measure extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7948138128864079252L;
	private String carno="";
	private String cartype="";
	private String icid="";
	private String ictype="";
	private String icno="";
	private String rfidid="";
	private String rfidtype="";
	private String rfidno="";
	private String matchid="";
	private String matchidb="";
	private String operatype="";
	private String planid="";
	private String taskcode="";
	private String basket="";
	private String materialcode="";
	private String materialname="";
	private String materialspeccode="";
	private String materialspec="";
	private String shipcode="";
	private String ship="";
	private String sourcecode="";
	private String sourcename="";
	private String sourceplace="";
	private String sourcetime="";
	private String sourceoperacode="";
	private String sourceoperaname="";
	private String targetcode="";
	private String targetname="";
	private String targetplace="";
	private String targettime="";
	private String targetoperacode="";
	private String targetoperaname="";
	private int materialcount=0;
	private double planweight=0;
	private double planmaterialcount=0;
	private int plancarcount=0;
	private double gross=0;
	private String grosstime="";
	private String grossweighid="";
	private String grossweigh="";
	private String grossweighgroup="";
	private String grossoperacode="";
	private String grossoperaname="";
	private int grossgroupno=0;
	private int grossserial=0;
	private double grossspeed=0;
	private String grosslogid="";
	private double tare=0;
	private String taretime="";
	private String tareweighid="";
	private String tareweigh="";
	private String tareweighgroup="";
	private String tareoperacode="";
	private String tareoperaname="";
	private int taregroupno=0;
	private int tareserial=0;
	private String tarelogid="";
	private double tarespeed=0;
	private double deduction=0;
	private String deductiontime="";
	private String deductioncode="";
	private String deductionname="";
	private String deductionoperacode="";
	private String deductionoperaname="";
	private double suttle=0;
	private String suttletime="";
	private String suttleweighid="";
	private String suttleweigh="";
	private String suttleoperacode="";
	private String suttleoperaname="";
	private String entertime="";
	private String entergatecode="";
	private String entergatename="";
	private String leavetime="";
	private String leavegatecode="";
	private String leavegatename="";
	private double grossb=0;
	private String grosstimeb="";
	private String grossweighidb="";
	private String grossweighb="";
	private String grossweighgroupb="";
	private String grossoperacodeb="";
	private String grossoperanameb="";
	private int grossgroupnob=0;
	private int grossserialb=0;
	private int grossspeedb=0;
	private String grosslogidb="";
	private double tareb=0;
	private String taretimeb="";
	private String tareweighidb="";
	private String tareweighb="";
	private String tareweighgroupb="";
	private String tareoperacodeb="";
	private String tareoperanameb="";
	private int taregroupnob=0;
	private int tareserialb=0;
	private String tarelogidb="";
	private int tarespeedb=0;
	private String batchcode="";
	private String sampletime="";
	private String sampleunitcode="";
	private String sampleunitname="";
	private int dflag=0;
	private int bflag=0;
	private int mflag=0;
	private String usermemo="";
	private String sysmemo="";
	private String createdate="";
	private String orderno="";
	private int mstate=0;
	private String motorcadename="";
	private String motorcadecode="";
	private int sflag=0;
	private int mqflag=0;
	private int kqflag=0;
	private int gflag=0;
	private int tarehour=0;
	private int accountstype=0;
	private String printsetgross="";
	private String printsetsuttle="";
	private int minterval=0;
	private double deductionunit=0;
	private double deductiontype=0;
	private int isplan=0;
	private int isbasket=0;
	private int forcereceive=0;
	private int qflag=0;
	private int plantimeflag=0;
	private int plancarcountflag=0;
	private int planweightflag=0;
	private int plancarflag=0;
	private int settlementmodes=0;
	private int iscompare=0;
	private int dispatchtype=0;
	private double price=0;
	private int qualityprint=0;
	private double ovweitghtmin=0;
	private double ovweitghtmax=0;
	private int issendcar=0;
	private int isloading=0;
	private int plancountflag=0;
	private int ovweitghttype=0;
	private String printsettare="";
	private int grosstimeout=0;
	private int taretimeout=0;
	private int grosstimeoutdo=0;
	private int taretimeoutdo=0;
	private int notstoreoutdo=0;
	private int notstoreindo=0;
	private int tareoutdo=0;
	private int grossoutdo=0;
	private int suttleoutdo=0;
	private int notentergatedo=0;
	private int notleavegatedo=0;
	private int notslampedo=0;
	private int tareoutup=0;
	private int tareoutdown=0;
	private int grossoutup=0;
	private int grossoutdown=0;
	private int suttleoutup=0;
	private int suttleoutdown=0;
	private int checktaredo=0;
	private int checktarelast=0;
	private int checktarehistory=0;
	private int checktarebasic=0;
	private int istaskcode=0;
	private int maxtare=0;
	private int grossweightout=0;
	private int tareweightout=0;
	private int measuretype=0;
	private int checkgrossinfo=0;
	private int checktareinfo=0;
	private int checksuttleinfo=0;
	private int isgrosstaskcode=0;
	private int isgrossplanid=0;
	private int isgrossmaterial=0;
	private int isgrossship=0;
	private int isgrosssource=0;
	private int isgrosstarget=0;
	private int isgrossbasket=0;
	private int istaretaskcode=0;
	private int istareplanid=0;
	private int istarematerial=0;
	private int istaresource=0;
	private int istareship=0;
	private int istaretarget=0;
	private int istarebasket=0;
	private int issuttletaskcode=0;
	private int issuttleplanid=0;
	private int issuttlematerial=0;
	private int issuttlesource=0;
	private int issuttleship=0;
	private int issuttletarget=0;
	private int issuttlebasket=0;
	private int regrossdo=0;
	private int retaredo=0;
	private int resuttledo=0;
	private int checkoperatimedo=0;
	private int checkoperatime=0;
	private double deduction2=0;
	private String begintime="";
	private String endtime="";
	private double snumber=0;
	private double suttleapp=0;
	private int checkplanweighdo=0;
	private int planweighout=0;
	private int checkplancarcountdo=0;
	private int plancarcountout=0;
	private int checkplanmaterialcountdo=0;
	private int planmaterialcountout=0;
	private int checkplantimeoutdo=0;
	private String memo1="";
	private String memo2="";
	private String memo3="";
	private String memo4="";
	private String memo5="";
	private String memo6="";
	private String memo7="";
	private String memo8="";
	private String memo9="";
	private String memo10="";
	private String memo11="";
	private String memo12="";
	private String memo13="";
	private String memo14="";
	private String memo15="";
	private int issure=0;
	private int isinputweightb=0;
	private String realsourcename="";
	private String realsourcecode="";
	private String createman="";
	private String shipplanid="";
	private int ifphflag=0;
	private int routeid=0;
	private String measurestate="";
	
	private double suttleb;
	
	private String recordtype="";
	
	private String clientid="";
	public String getCarno() {
		return carno;
	}
	public String getCartype() {
		return cartype;
	}
	public String getIcid() {
		return icid;
	}
	public String getIctype() {
		return ictype;
	}
	public String getIcno() {
		return icno;
	}
	public String getRfidid() {
		return rfidid;
	}
	public String getRfidtype() {
		return rfidtype;
	}
	public String getRfidno() {
		return rfidno;
	}
	public String getMatchid() {
		return matchid;
	}
	public String getMatchidb() {
		return matchidb;
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
	public String getBasket() {
		return basket;
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
	public String getSourceplace() {
		return sourceplace;
	}
	public String getSourcetime() {
		return sourcetime;
	}
	public String getSourceoperacode() {
		return sourceoperacode;
	}
	public String getSourceoperaname() {
		return sourceoperaname;
	}
	public String getTargetcode() {
		return targetcode;
	}
	public String getTargetname() {
		return targetname;
	}
	public String getTargetplace() {
		return targetplace;
	}
	public String getTargettime() {
		return targettime;
	}
	public String getTargetoperacode() {
		return targetoperacode;
	}
	public String getTargetoperaname() {
		return targetoperaname;
	}

	public double getPlanweight() {
		return planweight;
	}
	public double getPlanmaterialcount() {
		return planmaterialcount;
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
	public String getGrossweighgroup() {
		return grossweighgroup;
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
	public String getTareweighgroup() {
		return tareweighgroup;
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
	public String getEntertime() {
		return entertime;
	}
	public String getEntergatecode() {
		return entergatecode;
	}
	public String getEntergatename() {
		return entergatename;
	}
	public String getLeavetime() {
		return leavetime;
	}
	public String getLeavegatecode() {
		return leavegatecode;
	}
	public String getLeavegatename() {
		return leavegatename;
	}
	public double getGrossb() {
		return grossb;
	}
	public String getGrosstimeb() {
		return grosstimeb;
	}
	public String getGrossweighidb() {
		return grossweighidb;
	}
	public String getGrossweighb() {
		return grossweighb;
	}
	public String getGrossweighgroupb() {
		return grossweighgroupb;
	}
	public String getGrossoperacodeb() {
		return grossoperacodeb;
	}
	public String getGrossoperanameb() {
		return grossoperanameb;
	}
	public int getGrossgroupnob() {
		return grossgroupnob;
	}
	public int getGrossserialb() {
		return grossserialb;
	}
	public int getGrossspeedb() {
		return grossspeedb;
	}
	public String getGrosslogidb() {
		return grosslogidb;
	}
	public double getTareb() {
		return tareb;
	}
	public String getTaretimeb() {
		return taretimeb;
	}
	public String getTareweighidb() {
		return tareweighidb;
	}
	public String getTareweighb() {
		return tareweighb;
	}
	public String getTareweighgroupb() {
		return tareweighgroupb;
	}
	public String getTareoperacodeb() {
		return tareoperacodeb;
	}
	public String getTareoperanameb() {
		return tareoperanameb;
	}
	public int getTaregroupnob() {
		return taregroupnob;
	}
	public int getTareserialb() {
		return tareserialb;
	}
	public String getTarelogidb() {
		return tarelogidb;
	}
	public int getTarespeedb() {
		return tarespeedb;
	}
	public String getBatchcode() {
		return batchcode;
	}
	public String getSampletime() {
		return sampletime;
	}
	public String getSampleunitcode() {
		return sampleunitcode;
	}
	public String getSampleunitname() {
		return sampleunitname;
	}
	public int getDflag() {
		return dflag;
	}
	public int getBflag() {
		return bflag;
	}
	public int getMflag() {
		return mflag;
	}
	public String getUsermemo() {
		return usermemo;
	}
	public String getSysmemo() {
		return sysmemo;
	}
	public String getCreatedate() {
		return createdate;
	}

	public String getOrderno() {
		return orderno;
	}
	public int getMstate() {
		return mstate;
	}
	public String getMotorcadename() {
		return motorcadename;
	}
	public String getMotorcadecode() {
		return motorcadecode;
	}
	public int getSflag() {
		return sflag;
	}
	public int getMqflag() {
		return mqflag;
	}
	public int getKqflag() {
		return kqflag;
	}
	public int getGflag() {
		return gflag;
	}
	public int getTarehour() {
		return tarehour;
	}
	public int getAccountstype() {
		return accountstype;
	}
	public String getPrintsetgross() {
		return printsetgross;
	}
	public String getPrintsetsuttle() {
		return printsetsuttle;
	}
	public int getMinterval() {
		return minterval;
	}
	public double getDeductionunit() {
		return deductionunit;
	}
	public double getDeductiontype() {
		return deductiontype;
	}
	public int getIsplan() {
		return isplan;
	}
	public int getIsbasket() {
		return isbasket;
	}
	public int getForcereceive() {
		return forcereceive;
	}
	public int getQflag() {
		return qflag;
	}
	public int getPlantimeflag() {
		return plantimeflag;
	}
	public int getPlancarcountflag() {
		return plancarcountflag;
	}
	public int getPlanweightflag() {
		return planweightflag;
	}
	public int getPlancarflag() {
		return plancarflag;
	}
	public int getSettlementmodes() {
		return settlementmodes;
	}
	public int getIscompare() {
		return iscompare;
	}
	public int getDispatchtype() {
		return dispatchtype;
	}
	public double getPrice() {
		return price;
	}
	public int getQualityprint() {
		return qualityprint;
	}
	public double getOvweitghtmin() {
		return ovweitghtmin;
	}
	public double getOvweitghtmax() {
		return ovweitghtmax;
	}
	public int getIssendcar() {
		return issendcar;
	}
	public int getIsloading() {
		return isloading;
	}
	public int getPlancountflag() {
		return plancountflag;
	}
	public int getOvweitghttype() {
		return ovweitghttype;
	}
	public String getPrintsettare() {
		return printsettare;
	}
	public int getGrosstimeout() {
		return grosstimeout;
	}
	public int getTaretimeout() {
		return taretimeout;
	}
	public int getGrosstimeoutdo() {
		return grosstimeoutdo;
	}
	public int getTaretimeoutdo() {
		return taretimeoutdo;
	}
	public int getNotstoreoutdo() {
		return notstoreoutdo;
	}
	public int getNotstoreindo() {
		return notstoreindo;
	}
	public int getTareoutdo() {
		return tareoutdo;
	}
	public int getGrossoutdo() {
		return grossoutdo;
	}
	public int getSuttleoutdo() {
		return suttleoutdo;
	}
	public int getNotentergatedo() {
		return notentergatedo;
	}
	public int getNotleavegatedo() {
		return notleavegatedo;
	}
	public int getNotslampedo() {
		return notslampedo;
	}
	public int getTareoutup() {
		return tareoutup;
	}
	public int getTareoutdown() {
		return tareoutdown;
	}
	public int getGrossoutup() {
		return grossoutup;
	}
	public int getGrossoutdown() {
		return grossoutdown;
	}
	public int getSuttleoutup() {
		return suttleoutup;
	}
	public int getSuttleoutdown() {
		return suttleoutdown;
	}
	public int getChecktaredo() {
		return checktaredo;
	}
	public int getChecktarelast() {
		return checktarelast;
	}
	public int getChecktarehistory() {
		return checktarehistory;
	}
	public int getChecktarebasic() {
		return checktarebasic;
	}
	public int getIstaskcode() {
		return istaskcode;
	}
	public int getMaxtare() {
		return maxtare;
	}
	public int getGrossweightout() {
		return grossweightout;
	}
	public int getTareweightout() {
		return tareweightout;
	}
	public int getMeasuretype() {
		return measuretype;
	}
	public int getCheckgrossinfo() {
		return checkgrossinfo;
	}
	public int getChecktareinfo() {
		return checktareinfo;
	}
	public int getChecksuttleinfo() {
		return checksuttleinfo;
	}
	public int getIsgrosstaskcode() {
		return isgrosstaskcode;
	}
	public int getIsgrossplanid() {
		return isgrossplanid;
	}
	public int getIsgrossmaterial() {
		return isgrossmaterial;
	}
	public int getIsgrossship() {
		return isgrossship;
	}
	public int getIsgrosssource() {
		return isgrosssource;
	}
	public int getIsgrosstarget() {
		return isgrosstarget;
	}
	public int getIsgrossbasket() {
		return isgrossbasket;
	}
	public int getIstaretaskcode() {
		return istaretaskcode;
	}
	public int getIstareplanid() {
		return istareplanid;
	}
	public int getIstarematerial() {
		return istarematerial;
	}
	public int getIstaresource() {
		return istaresource;
	}
	public int getIstareship() {
		return istareship;
	}
	public int getIstaretarget() {
		return istaretarget;
	}
	public int getIstarebasket() {
		return istarebasket;
	}
	public int getIssuttletaskcode() {
		return issuttletaskcode;
	}
	public int getIssuttleplanid() {
		return issuttleplanid;
	}
	public int getIssuttlematerial() {
		return issuttlematerial;
	}
	public int getIssuttlesource() {
		return issuttlesource;
	}
	public int getIssuttleship() {
		return issuttleship;
	}
	public int getIssuttletarget() {
		return issuttletarget;
	}
	public int getIssuttlebasket() {
		return issuttlebasket;
	}
	public int getRegrossdo() {
		return regrossdo;
	}
	public int getRetaredo() {
		return retaredo;
	}
	public int getResuttledo() {
		return resuttledo;
	}
	public int getCheckoperatimedo() {
		return checkoperatimedo;
	}
	public int getCheckoperatime() {
		return checkoperatime;
	}
	public double getDeduction2() {
		return deduction2;
	}
	public String getBegintime() {
		return begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public double getSnumber() {
		return snumber;
	}
	public double getSuttleapp() {
		return suttleapp;
	}
	public int getCheckplanweighdo() {
		return checkplanweighdo;
	}
	public int getPlanweighout() {
		return planweighout;
	}
	public int getCheckplancarcountdo() {
		return checkplancarcountdo;
	}
	public int getPlancarcountout() {
		return plancarcountout;
	}
	public int getCheckplanmaterialcountdo() {
		return checkplanmaterialcountdo;
	}
	public int getPlanmaterialcountout() {
		return planmaterialcountout;
	}
	public int getCheckplantimeoutdo() {
		return checkplantimeoutdo;
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
	public int getIssure() {
		return issure;
	}
	public int getIsinputweightb() {
		return isinputweightb;
	}
	public String getRealsourcename() {
		return realsourcename;
	}
	public String getRealsourcecode() {
		return realsourcecode;
	}
	public String getCreateman() {
		return createman;
	}
	public String getShipplanid() {
		return shipplanid;
	}
	public int getIfphflag() {
		return ifphflag;
	}
	public int getRouteid() {
		return routeid;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	public void setIcid(String icid) {
		this.icid = icid;
	}
	public void setIctype(String ictype) {
		this.ictype = ictype;
	}
	public void setIcno(String icno) {
		this.icno = icno;
	}
	public void setRfidid(String rfidid) {
		this.rfidid = rfidid;
	}
	public void setRfidtype(String rfidtype) {
		this.rfidtype = rfidtype;
	}
	public void setRfidno(String rfidno) {
		this.rfidno = rfidno;
	}
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
	public void setMatchidb(String matchidb) {
		this.matchidb = matchidb;
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
	public void setBasket(String basket) {
		this.basket = basket;
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
	public void setSourceplace(String sourceplace) {
		this.sourceplace = sourceplace;
	}
	public void setSourcetime(String sourcetime) {
		this.sourcetime = sourcetime;
	}
	public void setSourceoperacode(String sourceoperacode) {
		this.sourceoperacode = sourceoperacode;
	}
	public void setSourceoperaname(String sourceoperaname) {
		this.sourceoperaname = sourceoperaname;
	}
	public void setTargetcode(String targetcode) {
		this.targetcode = targetcode;
	}
	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}
	public void setTargetplace(String targetplace) {
		this.targetplace = targetplace;
	}
	public void setTargettime(String targettime) {
		this.targettime = targettime;
	}
	public void setTargetoperacode(String targetoperacode) {
		this.targetoperacode = targetoperacode;
	}
	public void setTargetoperaname(String targetoperaname) {
		this.targetoperaname = targetoperaname;
	}
	public void setPlanweight(double planweight) {
		this.planweight = planweight;
	}
	public void setPlanmaterialcount(double planmaterialcount) {
		this.planmaterialcount = planmaterialcount;
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
	public void setGrossweighgroup(String grossweighgroup) {
		this.grossweighgroup = grossweighgroup;
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
	public void setTareweighgroup(String tareweighgroup) {
		this.tareweighgroup = tareweighgroup;
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
	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}
	public void setEntergatecode(String entergatecode) {
		this.entergatecode = entergatecode;
	}
	public void setEntergatename(String entergatename) {
		this.entergatename = entergatename;
	}
	public void setLeavetime(String leavetime) {
		this.leavetime = leavetime;
	}
	public void setLeavegatecode(String leavegatecode) {
		this.leavegatecode = leavegatecode;
	}
	public void setLeavegatename(String leavegatename) {
		this.leavegatename = leavegatename;
	}
	public void setGrossb(double grossb) {
		this.grossb = grossb;
	}
	public void setGrosstimeb(String grosstimeb) {
		this.grosstimeb = grosstimeb;
	}
	public void setGrossweighidb(String grossweighidb) {
		this.grossweighidb = grossweighidb;
	}
	public void setGrossweighb(String grossweighb) {
		this.grossweighb = grossweighb;
	}
	public void setGrossweighgroupb(String grossweighgroupb) {
		this.grossweighgroupb = grossweighgroupb;
	}
	public void setGrossoperacodeb(String grossoperacodeb) {
		this.grossoperacodeb = grossoperacodeb;
	}
	public void setGrossoperanameb(String grossoperanameb) {
		this.grossoperanameb = grossoperanameb;
	}
	public void setGrossgroupnob(int grossgroupnob) {
		this.grossgroupnob = grossgroupnob;
	}
	public void setGrossserialb(int grossserialb) {
		this.grossserialb = grossserialb;
	}
	public void setGrossspeedb(int grossspeedb) {
		this.grossspeedb = grossspeedb;
	}
	public void setGrosslogidb(String grosslogidb) {
		this.grosslogidb = grosslogidb;
	}
	public void setTareb(double tareb) {
		this.tareb = tareb;
	}
	public void setTaretimeb(String taretimeb) {
		this.taretimeb = taretimeb;
	}
	public void setTareweighidb(String tareweighidb) {
		this.tareweighidb = tareweighidb;
	}
	public void setTareweighb(String tareweighb) {
		this.tareweighb = tareweighb;
	}
	public void setTareweighgroupb(String tareweighgroupb) {
		this.tareweighgroupb = tareweighgroupb;
	}
	public void setTareoperacodeb(String tareoperacodeb) {
		this.tareoperacodeb = tareoperacodeb;
	}
	public void setTareoperanameb(String tareoperanameb) {
		this.tareoperanameb = tareoperanameb;
	}
	public void setTaregroupnob(int taregroupnob) {
		this.taregroupnob = taregroupnob;
	}
	public void setTareserialb(int tareserialb) {
		this.tareserialb = tareserialb;
	}
	public void setTarelogidb(String tarelogidb) {
		this.tarelogidb = tarelogidb;
	}
	public void setTarespeedb(int tarespeedb) {
		this.tarespeedb = tarespeedb;
	}
	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}
	public void setSampletime(String sampletime) {
		this.sampletime = sampletime;
	}
	public void setSampleunitcode(String sampleunitcode) {
		this.sampleunitcode = sampleunitcode;
	}
	public void setSampleunitname(String sampleunitname) {
		this.sampleunitname = sampleunitname;
	}
	public void setDflag(int dflag) {
		this.dflag = dflag;
	}
	public void setBflag(int bflag) {
		this.bflag = bflag;
	}
	public void setMflag(int mflag) {
		this.mflag = mflag;
	}
	public void setUsermemo(String usermemo) {
		this.usermemo = usermemo;
	}
	public void setSysmemo(String sysmemo) {
		this.sysmemo = sysmemo;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public void setMstate(int mstate) {
		this.mstate = mstate;
	}
	public void setMotorcadename(String motorcadename) {
		this.motorcadename = motorcadename;
	}
	public void setMotorcadecode(String motorcadecode) {
		this.motorcadecode = motorcadecode;
	}
	public void setSflag(int sflag) {
		this.sflag = sflag;
	}
	public void setMqflag(int mqflag) {
		this.mqflag = mqflag;
	}
	public void setKqflag(int kqflag) {
		this.kqflag = kqflag;
	}
	public void setGflag(int gflag) {
		this.gflag = gflag;
	}
	public void setTarehour(int tarehour) {
		this.tarehour = tarehour;
	}
	public void setAccountstype(int accountstype) {
		this.accountstype = accountstype;
	}
	public void setPrintsetgross(String printsetgross) {
		this.printsetgross = printsetgross;
	}
	public void setPrintsetsuttle(String printsetsuttle) {
		this.printsetsuttle = printsetsuttle;
	}
	public void setMinterval(int minterval) {
		this.minterval = minterval;
	}
	public void setDeductionunit(double deductionunit) {
		this.deductionunit = deductionunit;
	}
	public void setDeductiontype(double deductiontype) {
		this.deductiontype = deductiontype;
	}
	public void setIsplan(int isplan) {
		this.isplan = isplan;
	}
	public void setIsbasket(int isbasket) {
		this.isbasket = isbasket;
	}
	public void setForcereceive(int forcereceive) {
		this.forcereceive = forcereceive;
	}
	public void setQflag(int qflag) {
		this.qflag = qflag;
	}
	public void setPlantimeflag(int plantimeflag) {
		this.plantimeflag = plantimeflag;
	}
	public void setPlancarcountflag(int plancarcountflag) {
		this.plancarcountflag = plancarcountflag;
	}
	public void setPlanweightflag(int planweightflag) {
		this.planweightflag = planweightflag;
	}
	public void setPlancarflag(int plancarflag) {
		this.plancarflag = plancarflag;
	}
	public void setSettlementmodes(int settlementmodes) {
		this.settlementmodes = settlementmodes;
	}
	public void setIscompare(int iscompare) {
		this.iscompare = iscompare;
	}
	public void setDispatchtype(int dispatchtype) {
		this.dispatchtype = dispatchtype;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQualityprint(int qualityprint) {
		this.qualityprint = qualityprint;
	}
	public void setOvweitghtmin(double ovweitghtmin) {
		this.ovweitghtmin = ovweitghtmin;
	}
	public void setOvweitghtmax(double ovweitghtmax) {
		this.ovweitghtmax = ovweitghtmax;
	}
	public void setIssendcar(int issendcar) {
		this.issendcar = issendcar;
	}
	public void setIsloading(int isloading) {
		this.isloading = isloading;
	}
	public void setPlancountflag(int plancountflag) {
		this.plancountflag = plancountflag;
	}
	public void setOvweitghttype(int ovweitghttype) {
		this.ovweitghttype = ovweitghttype;
	}
	public void setPrintsettare(String printsettare) {
		this.printsettare = printsettare;
	}
	public void setGrosstimeout(int grosstimeout) {
		this.grosstimeout = grosstimeout;
	}
	public void setTaretimeout(int taretimeout) {
		this.taretimeout = taretimeout;
	}
	public void setGrosstimeoutdo(int grosstimeoutdo) {
		this.grosstimeoutdo = grosstimeoutdo;
	}
	public void setTaretimeoutdo(int taretimeoutdo) {
		this.taretimeoutdo = taretimeoutdo;
	}
	public void setNotstoreoutdo(int notstoreoutdo) {
		this.notstoreoutdo = notstoreoutdo;
	}
	public void setNotstoreindo(int notstoreindo) {
		this.notstoreindo = notstoreindo;
	}
	public void setTareoutdo(int tareoutdo) {
		this.tareoutdo = tareoutdo;
	}
	public void setGrossoutdo(int grossoutdo) {
		this.grossoutdo = grossoutdo;
	}
	public void setSuttleoutdo(int suttleoutdo) {
		this.suttleoutdo = suttleoutdo;
	}
	public void setNotentergatedo(int notentergatedo) {
		this.notentergatedo = notentergatedo;
	}
	public void setNotleavegatedo(int notleavegatedo) {
		this.notleavegatedo = notleavegatedo;
	}
	public void setNotslampedo(int notslampedo) {
		this.notslampedo = notslampedo;
	}
	public void setTareoutup(int tareoutup) {
		this.tareoutup = tareoutup;
	}
	public void setTareoutdown(int tareoutdown) {
		this.tareoutdown = tareoutdown;
	}
	public void setGrossoutup(int grossoutup) {
		this.grossoutup = grossoutup;
	}
	public void setGrossoutdown(int grossoutdown) {
		this.grossoutdown = grossoutdown;
	}
	public void setSuttleoutup(int suttleoutup) {
		this.suttleoutup = suttleoutup;
	}
	public void setSuttleoutdown(int suttleoutdown) {
		this.suttleoutdown = suttleoutdown;
	}
	public void setChecktaredo(int checktaredo) {
		this.checktaredo = checktaredo;
	}
	public void setChecktarelast(int checktarelast) {
		this.checktarelast = checktarelast;
	}
	public void setChecktarehistory(int checktarehistory) {
		this.checktarehistory = checktarehistory;
	}
	public void setChecktarebasic(int checktarebasic) {
		this.checktarebasic = checktarebasic;
	}
	public void setIstaskcode(int istaskcode) {
		this.istaskcode = istaskcode;
	}
	public void setMaxtare(int maxtare) {
		this.maxtare = maxtare;
	}
	public void setGrossweightout(int grossweightout) {
		this.grossweightout = grossweightout;
	}
	public void setTareweightout(int tareweightout) {
		this.tareweightout = tareweightout;
	}
	public void setMeasuretype(int measuretype) {
		this.measuretype = measuretype;
	}
	public void setCheckgrossinfo(int checkgrossinfo) {
		this.checkgrossinfo = checkgrossinfo;
	}
	public void setChecktareinfo(int checktareinfo) {
		this.checktareinfo = checktareinfo;
	}
	public void setChecksuttleinfo(int checksuttleinfo) {
		this.checksuttleinfo = checksuttleinfo;
	}
	public void setIsgrosstaskcode(int isgrosstaskcode) {
		this.isgrosstaskcode = isgrosstaskcode;
	}
	public void setIsgrossplanid(int isgrossplanid) {
		this.isgrossplanid = isgrossplanid;
	}
	public void setIsgrossmaterial(int isgrossmaterial) {
		this.isgrossmaterial = isgrossmaterial;
	}
	public void setIsgrossship(int isgrossship) {
		this.isgrossship = isgrossship;
	}
	public void setIsgrosssource(int isgrosssource) {
		this.isgrosssource = isgrosssource;
	}
	public void setIsgrosstarget(int isgrosstarget) {
		this.isgrosstarget = isgrosstarget;
	}
	public void setIsgrossbasket(int isgrossbasket) {
		this.isgrossbasket = isgrossbasket;
	}
	public void setIstaretaskcode(int istaretaskcode) {
		this.istaretaskcode = istaretaskcode;
	}
	public void setIstareplanid(int istareplanid) {
		this.istareplanid = istareplanid;
	}
	public void setIstarematerial(int istarematerial) {
		this.istarematerial = istarematerial;
	}
	public void setIstaresource(int istaresource) {
		this.istaresource = istaresource;
	}
	public void setIstareship(int istareship) {
		this.istareship = istareship;
	}
	public void setIstaretarget(int istaretarget) {
		this.istaretarget = istaretarget;
	}
	public void setIstarebasket(int istarebasket) {
		this.istarebasket = istarebasket;
	}
	public void setIssuttletaskcode(int issuttletaskcode) {
		this.issuttletaskcode = issuttletaskcode;
	}
	public void setIssuttleplanid(int issuttleplanid) {
		this.issuttleplanid = issuttleplanid;
	}
	public void setIssuttlematerial(int issuttlematerial) {
		this.issuttlematerial = issuttlematerial;
	}
	public void setIssuttlesource(int issuttlesource) {
		this.issuttlesource = issuttlesource;
	}
	public void setIssuttleship(int issuttleship) {
		this.issuttleship = issuttleship;
	}
	public void setIssuttletarget(int issuttletarget) {
		this.issuttletarget = issuttletarget;
	}
	public void setIssuttlebasket(int issuttlebasket) {
		this.issuttlebasket = issuttlebasket;
	}
	public void setRegrossdo(int regrossdo) {
		this.regrossdo = regrossdo;
	}
	public void setRetaredo(int retaredo) {
		this.retaredo = retaredo;
	}
	public void setResuttledo(int resuttledo) {
		this.resuttledo = resuttledo;
	}
	public void setCheckoperatimedo(int checkoperatimedo) {
		this.checkoperatimedo = checkoperatimedo;
	}
	public void setCheckoperatime(int checkoperatime) {
		this.checkoperatime = checkoperatime;
	}
	public void setDeduction2(double deduction2) {
		this.deduction2 = deduction2;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public void setSnumber(double snumber) {
		this.snumber = snumber;
	}
	public void setSuttleapp(double suttleapp) {
		this.suttleapp = suttleapp;
	}
	public void setCheckplanweighdo(int checkplanweighdo) {
		this.checkplanweighdo = checkplanweighdo;
	}
	public void setPlanweighout(int planweighout) {
		this.planweighout = planweighout;
	}
	public void setCheckplancarcountdo(int checkplancarcountdo) {
		this.checkplancarcountdo = checkplancarcountdo;
	}
	public void setPlancarcountout(int plancarcountout) {
		this.plancarcountout = plancarcountout;
	}
	public void setCheckplanmaterialcountdo(int checkplanmaterialcountdo) {
		this.checkplanmaterialcountdo = checkplanmaterialcountdo;
	}
	public void setPlanmaterialcountout(int planmaterialcountout) {
		this.planmaterialcountout = planmaterialcountout;
	}
	public void setCheckplantimeoutdo(int checkplantimeoutdo) {
		this.checkplantimeoutdo = checkplantimeoutdo;
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
	public void setIssure(int issure) {
		this.issure = issure;
	}
	public void setIsinputweightb(int isinputweightb) {
		this.isinputweightb = isinputweightb;
	}
	public void setRealsourcename(String realsourcename) {
		this.realsourcename = realsourcename;
	}
	public void setRealsourcecode(String realsourcecode) {
		this.realsourcecode = realsourcecode;
	}
	public void setCreateman(String createman) {
		this.createman = createman;
	}
	public void setShipplanid(String shipplanid) {
		this.shipplanid = shipplanid;
	}
	public void setIfphflag(int ifphflag) {
		this.ifphflag = ifphflag;
	}
	public void setRouteid(int routeid) {
		this.routeid = routeid;
	}
	public String getMeasurestate() {
		return measurestate;
	}
	public void setMeasurestate(String measurestate) {
		this.measurestate = measurestate;
	}
	public int getMaterialcount() {
		return materialcount;
	}
	public int getPlancarcount() {
		return plancarcount;
	}
	public void setMaterialcount(int materialcount) {
		this.materialcount = materialcount;
	}
	public void setPlancarcount(int plancarcount) {
		this.plancarcount = plancarcount;
	}
	public double getSuttleb() {
		return suttleb;
	}
	public void setSuttleb(double suttleb) {
		this.suttleb = suttleb;
	}
	public String getRecordtype() {
		return recordtype;
	}
	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
}
