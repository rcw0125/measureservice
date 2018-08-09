package com.talent.measure.model;

import java.io.Serializable;

public class Measure implements Serializable{
	
	private static final long serialVersionUID = -4857896997751765304L;

	private int id=0;
	
	private String shipplanid = "";
	
	private String createdate = "";
	
	private int accountstype=0;
	
	private int bflag=0;
	
	private int checkgrossinfo=0;
	
	private int checkoperatime=0;
	
	private int checkoperatimedo=0;
	
	private int checkplancarcountdo=0;
	
	private int checkplanmaterialcountdo=0;
	
	private int checkplantimeoutdo=0;
	
	private int checkplanweighdo=0;
	
	private int checksuttleinfo=0;
	
	private int checktarebasic=0;
	
	private int checktaredo=0;
	
	private int checktarehistory=0;
	
	private int checktareinfo=0;
	
	private int checktarelast=0;
	
	private int dflag=0;
	
	private int dispatchtype=0;
	
	private int forcereceive=0;
	
	private int gflag=0;
	
	private double gross=0;
	
	private double rgross=0;
	
	private int grossgroupno=0;
	
	private int grossgroupnob=0;
	
	private int grossoutdo=0;
	
	private int grossoutdown=0;
	
	private int grossoutup=0;
	
	private int grossserial=0;
	
	private int grossserialb=0;
	
	private int grossspeed=0;
	
	private int grossspeedb=0;
	
	private int grosstimeout=0;
	
	private int grosstimeoutdo=0;
	
	private int grossweightout=0;
	
	private int ifphflag=0;
	
	private int isbasket=0;
	
	private int iscompare=0;
	
	private int isgrossbasket=0;
	
	private int isgrossmaterial=0;
	
	private int isgrossplanid=0;
	
	private int isgrossship=0;
	
	private int isgrosssource=0;
	
	private int isgrosstarget=0;
	
	private int isgrosstaskcode=0;
	
	private int isinputweightb=0;
	
	private int isloading=0;
	
	private int isplan=0;
	
	private int issendcar=0;
	
	private int issure=0;
	
	private int issuttlebasket=0;
	
	private int issuttlematerial=0;
	
	private int issuttleplanid=0;
	
	private int issuttleship=0;
	
	private int issuttlesource=0;
	
	private int issuttletarget=0;
	
	private int issuttletaskcode=0;
	
	private int istarebasket=0;
	
	private int istarematerial=0;
	
	private int istareplanid=0;
	
	private int istareship=0;
	
	private int istaresource=0;
	
	private int istaretarget=0;
	
	private int istaretaskcode=0;
	
	private int istaskcode=0;
	
	private int kqflag=0;
	
	private int materialcount=0;
	
	private int maxtare=0;
	
	private int measuretype=0;
	
	private int mflag=0;
	
	private int minterval=0;
	
	private int mqflag=0;
	
	private int mstate=0;
	
	private int notentergatedo=0;
	
	private int notleavegatedo=0;
	
	private int notslampedo=0;
	
	private int notstoreindo=0;
	
	private int notstoreoutdo=0;
	
	private int ovweitghttype=0;
	
	private int plancarcount=0;
	
	private int plancarcountflag=0;
	
	private int plancarcountout=0;
	
	private int plancarflag=0;
	
	private int plancountflag=0;
	
	private int planmaterialcount=0;
	
	private int planmaterialcountout=0;
	
	private int plantimeflag=0;
	
	private int planweighout=0;
	
	private double planweight=0;
	
	private int planweightflag=0;
	
	private int qflag=0;
	
	private int qualityprint=0;
	
	private int regrossdo=0;
	
	private int resuttledo=0;
	
	private int retaredo=0;
	
	private int settlementmodes=0;
	
	private int sflag=0;
	
	private int snumber=0;
	
	private double suttle=0;
	
	private int suttleoutdo=0;
	
	private int suttleoutdown=0;
	
	private int suttleoutup=0;
	
	private double tare=0;
	
	private double rtare=0;
	
	private int taregroupno=0;
	
	private int taregroupnob=0;
	
	private int tarehour=0;
	
	private int tareoutdo=0;
	
	private int tareoutdown=0;
	
	private int tareoutup=0;
	
	private int tareserial=0;
	
	private int tareserialb=0;
	
	private int tarespeed=0;
	
	private int tarespeedb=0;
	
	private int taretimeout=0;
	
	private int taretimeoutdo=0;
	
	private int tareweightout=0;
	
	private int validflag=0;
	
	private String basket = "";
	
	private String batchcode = "";
	
	private String begintime = "";
	
	private String carno = "";
	
	private String cartype = "";
	
	private String createman = "";
	
	private String deductioncode = "";
	
	private String deductionname = "";
	
	private String deductionoperacode = "";
	
	private String deductionoperaname = "";
	
	private String deductiontime = "";
	
	private String endtime = "";
	
	private String entergatecode = "";
	
	private String entergatename = "";
	
	private String entertime = "";
	
	private String grosslogid = "";
	
	private String grosslogidb = "";
	
	private String grossoperacode = "";
	
	private String grossoperacodeb = "";
	
	private String grossoperaname = "";
	
	private String grossoperanameb = "";
	
	private String grosstime = "";
	
	private String grosstimeb = "";
	
	private String grossweigh = "";
	
	private String grossweighb = "";
	
	private String grossweighgroup = "";
	
	private String grossweighgroupb = "";
	
	private String grossweighid = "";
	
	private String grossweighidb = "";
	
	private String icid = "";
	
	private String icno = "";
	
	private String ictype = "";
	
	private String leavegatecode = "";
	
	private String leavegatename = "";
	
	private String leavetime = "";
	
	private String matchid = "";
	
	private String matchidb = "";
	
	private String materialcode = "";
	
	private String materialname = "";
	
	private String materialspec = "";
	
	private String materialspeccode = "";
	
	private String memo10 = "";
	
	private String memo11 = "";
	
	private String memo12 = "";
	
	private String memo13 = "";
	
	private String memo14 = "";
	
	private String memo15 = "";
	
	private String memo1 = "";
	
	private String memo2 = "";
	
	private String memo3 = "";
	
	private String memo4 = "";
	
	private String memo5 = "";
	
	private String memo6 = "";
	
	private String memo7 = "";
	
	private String memo8 = "";
	
	private String memo9 = "";
	
	private String motorcadecode = "";
	
	private String motorcadename = "";
	
	private String operatype = "";
	
	private String orderno = "";
	
	private String planid = "";
	
	private String printsetgross = "";
	
	private String printsetsuttle = "";
	
	private String printsettare = "";
	
	private String realsourcecode = "";
	
	private String realsourcename = "";
	
	private String rfidid = "";
	
	private String rfidno = "";
	
	private String rfidtype = "";
	
	private String sampletime = "";
	
	private String sampleunitcode = "";
	
	private String sampleunitname = "";
	
	private String ship = "";
	
	private String shipcode = "";
	
	private String sourcecode = "";
	
	private String sourcename = "";
	
	private String sourceoperacode = "";
	
	private String sourceoperaname = "";
	
	private String sourceplace = "";
	
	private String sourcetime = "";
	
	private String suttleoperacode = "";
	
	private String suttleoperaname = "";
	
	private String suttletime = "";
	
	private String suttleweigh = "";
	
	private String suttleweighid = "";
	
	private String sysmemo = "";
	
	private String tarelogid = "";
	
	private String tarelogidb = "";
	
	private String tareoperacode = "";
	
	private String tareoperacodeb = "";
	
	private String tareoperaname = "";
	
	private String tareoperanameb = "";
	
	private String taretime = "";
	
	private String taretimeb = "";
	
	private String tareweigh = "";
	
	private String tareweighb = "";
	
	private String tareweighgroup = "";
	
	private String tareweighgroupb = "";
	
	private String tareweighid = "";
	
	private String tareweighidb = "";
	
	private String targetcode = "";
	
	private String targetname = "";
	
	private String targetoperacode = "";
	
	private String targetoperaname = "";
	
	private String targetplace = "";
	
	private String targettime = "";
	
	private String taskcode = "";
	
	private String usermemo = "";
	
	private double deduction2=0;
	
	private double deduction=0;
	
	private double deductiontype=0;
	
	private double deductionunit=0;
	
	private double grossb=0;
	
	private double ovweitghtmax=0;
	
	private double ovweitghtmin=0;
	
	private double price=0;
	
	private double suttleapp=0;
	
	private double tareb=0;
	
	private int measureflag=0;
	
	private double suttleb=0;
	
	private String msg = "";
	
	private int flag=0;
	
	private String validflagoperaname = "";
	
	private String endtype = "";//客户端类型： 0 终端 1 坐席
	
	private String opstr = "";//业务类型+计皮/计毛
	
	private int cardclass=0; //卡类型，0：IC卡，1：RFID卡
	
	private String mrecord = ""; //查询计量记录标记 G 计毛、S净重  T计量皮重
	
	private String selecttime = ""; //时间选择类型
	
	private String fieldname = "";// 字段名称
	
	private String fieldcode = "";//  字段编码
	
	private String operaname = "";//业务类型名称
	
	private int aflag=0; //是否操作制卡表0否 1是
	
	private int siflag=0; //是否操作入库表 否 1是
	
	private int soflag=0; //是否操作出库表0否 1是
	
	private String logid = "";
	
	private String clientid = "";
	
	private String recordtype = "";
	
	private String measurestate = "";
	
	private String queryword = "";	
	
	private double basicweight=0.00;//基础皮重
	
	private String carflag = "";//是否需要手动输入
	
	private String caller = ""; //终端还是坐席 0终端 1坐席
	
	private String lastprintequname="";//最后一次打印票据衡器
	
	private String lastprintdate="";	//最后一次打印小票时间
	
	private String printstr;//只计毛、只计皮、出净重。
	
	private String mtypes=""; //计量方式 计量毛重还是皮重
	
	private double weight; //重量
	
	private String weighttype;//重量类型：G毛重T皮重
	
	private String timetype;//类型：G毛重T皮重
	
	private double sametareoutup;//相似皮重超差上限值 value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	
	private double sametareoutdown;	//相似皮重超差下限值 value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	
	private double samegrossoutup;	//相似毛重超差上限值 value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	
	private double samegrossoutdown;	//	相似毛重超差下限值 value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	
	private int samegrosstime;	//	查询多长时间以内的相似毛重 单位是分钟
	
	private int sametaretime;	//	查询多长时间以内的相似皮重 单位是分钟
	
	private double weightup; //重量上限
	
	private double weightdown; //重量下限
	
	private int weighttime;  //时间限制
	
	private double sumweight; //已经执行的总重量
	
	private int summaterialcount; //已经执行的总支数
	
	private int sumcarcount; //已经执行的总车数
	
	private int sameweightdo=0; //相似重量处理方式
	
	private int ruleflag; //验证标记 ;1表示验证获取时方法，2表示验证获取保存时方法
	
	private int tarehourdo=0; //皮重有效期超期处理方式
	
	private double dvalue; // 
	
	private String weightno;//衡器id
	
	private String rtaretime="";
	
	private String rgrosstime="";
	
	private int printsuttlenum = 1;  //打印次数
	
	private String printModelType = "";
	
	private String rfid="";

	public String getPrintModelType() {
		return printModelType;
	}
	public void setPrintModelType(String printModelType) {
		this.printModelType = printModelType;
	}
	public String getRtaretime() {
		return rtaretime;
	}
	public void setRtaretime(String rtaretime) {
		this.rtaretime = rtaretime;
	}
	public String getRgrosstime() {
		return rgrosstime;
	}
	public void setRgrosstime(String rgrosstime) {
		this.rgrosstime = rgrosstime;
	}
	public int getPrintsuttlenum() {
		return printsuttlenum;
	}
	public void setPrintsuttlenum(int printsuttlenum) {
		this.printsuttlenum = printsuttlenum;
	}

	public String getWeightno() {
		return weightno;
	}
	public void setWeightno(String weightno) {
		this.weightno = weightno;
	}
	public double getDvalue() {
		return dvalue;
	}
	public void setDvalue(double dvalue) {
		this.dvalue = dvalue;
	}
	public int getTarehourdo() {
		return tarehourdo;
	}
	public void setTarehourdo(int tarehourdo) {
		this.tarehourdo = tarehourdo;
	}
	public int getRuleflag() {
		return ruleflag;
	}
	public void setRuleflag(int ruleflag) {
		this.ruleflag = ruleflag;
	}
	public int getSameweightdo() {
		return sameweightdo;
	}
	public void setSameweightdo(int sameweightdo) {
		this.sameweightdo = sameweightdo;
	}
	public int getSummaterialcount() {
		return summaterialcount;
	}
	public void setSummaterialcount(int summaterialcount) {
		this.summaterialcount = summaterialcount;
	}
	public int getSumcarcount() {
		return sumcarcount;
	}
	public void setSumcarcount(int sumcarcount) {
		this.sumcarcount = sumcarcount;
	}
	public double getSumweight() {
		return sumweight;
	}
	public void setSumweight(double sumweight) {
		this.sumweight = sumweight;
	}
	public String getMtypes() {
		return mtypes;
	}
	public void setMtypes(String mtypes) {
		this.mtypes = mtypes;
	}

	public String getLastprintequname() {
		return lastprintequname;
	}
	public void setLastprintequname(String lastprintequname) {
		this.lastprintequname = lastprintequname;
	}
	public String getLastprintdate() {
		return lastprintdate;
	}
	public void setLastprintdate(String lastprintdate) {
		this.lastprintdate = lastprintdate;
	}
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	public String getCarflag() {
		return carflag;
	}
	public void setCarflag(String carflag) {
		this.carflag = carflag;
	}
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getRecordtype() {
		return recordtype;
	}
	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}
	public String getMeasurestate() {
		return measurestate;
	}
	public void setMeasurestate(String measurestate) {
		this.measurestate = measurestate;
	}
	public int getAflag() {
		return aflag;
	}
	public void setAflag(int aflag) {
		this.aflag = aflag;
	}
	public int getSiflag() {
		return siflag;
	}
	public void setSiflag(int siflag) {
		this.siflag = siflag;
	}
	public int getSoflag() {
		return soflag;
	}
	public void setSoflag(int soflag) {
		this.soflag = soflag;
	}
	public double getBasicweight() {
		return basicweight;
	}
	public void setBasicweight(double basicweight) {
		this.basicweight = basicweight;
	}
	public String getQueryword() {
		return queryword;
	}
	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}
	public String getEndtype() {
		return endtype;
	}
	public void setEndtype(String endtype) {
		this.endtype = endtype;
	}
	public String getOperaname() {
		return operaname;
	}
	public void setOperaname(String operaname) {
		this.operaname = operaname;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getFieldcode() {
		return fieldcode;
	}
	public void setFieldcode(String fieldcode) {
		this.fieldcode = fieldcode;
	}
	public String getShipplanid() {
		return shipplanid;
	}
	public void setShipplanid(String shipplanid) {
		this.shipplanid = shipplanid;
	}
	public String getMsg() {
		return msg;
	}
	public String getValidflagoperaname() {
		return validflagoperaname;
	}
	public void setValidflagoperaname(String validflagoperaname) {
		this.validflagoperaname = validflagoperaname;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public double getSuttleb() {
		return suttleb;
	}
	public void setSuttleb(double suttleb) {
		this.suttleb = suttleb;
	}
	public int getCardclass() {
		return cardclass;
	}
	public void setCardclass(int cardclass) {
		this.cardclass = cardclass;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public int getAccountstype() {
		return accountstype;
	}
	public void setAccountstype(int accountstype) {
		this.accountstype = accountstype;
	}
	public int getBflag() {
		return bflag;
	}
	public void setBflag(int bflag) {
		this.bflag = bflag;
	}
	public int getMeasureflag() {
		return measureflag;
	}
	public void setMeasureflag(int measureflag) {
		this.measureflag = measureflag;
	}
	public int getCheckgrossinfo() {
		return checkgrossinfo;
	}
	public void setCheckgrossinfo(int checkgrossinfo) {
		this.checkgrossinfo = checkgrossinfo;
	}
	public int getCheckoperatime() {
		return checkoperatime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCheckoperatime(int checkoperatime) {
		this.checkoperatime = checkoperatime;
	}
	public int getCheckoperatimedo() {
		return checkoperatimedo;
	}
	public void setCheckoperatimedo(int checkoperatimedo) {
		this.checkoperatimedo = checkoperatimedo;
	}
	public int getCheckplancarcountdo() {
		return checkplancarcountdo;
	}
	public void setCheckplancarcountdo(int checkplancarcountdo) {
		this.checkplancarcountdo = checkplancarcountdo;
	}
	public int getCheckplanmaterialcountdo() {
		return checkplanmaterialcountdo;
	}
	public void setCheckplanmaterialcountdo(int checkplanmaterialcountdo) {
		this.checkplanmaterialcountdo = checkplanmaterialcountdo;
	}
	public int getCheckplantimeoutdo() {
		return checkplantimeoutdo;
	}
	public void setCheckplantimeoutdo(int checkplantimeoutdo) {
		this.checkplantimeoutdo = checkplantimeoutdo;
	}
	public int getCheckplanweighdo() {
		return checkplanweighdo;
	}
	public void setCheckplanweighdo(int checkplanweighdo) {
		this.checkplanweighdo = checkplanweighdo;
	}
	public int getChecksuttleinfo() {
		return checksuttleinfo;
	}
	public void setChecksuttleinfo(int checksuttleinfo) {
		this.checksuttleinfo = checksuttleinfo;
	}
	public int getChecktarebasic() {
		return checktarebasic;
	}
	public void setChecktarebasic(int checktarebasic) {
		this.checktarebasic = checktarebasic;
	}
	public int getChecktaredo() {
		return checktaredo;
	}
	public void setChecktaredo(int checktaredo) {
		this.checktaredo = checktaredo;
	}
	public int getChecktarehistory() {
		return checktarehistory;
	}
	public void setChecktarehistory(int checktarehistory) {
		this.checktarehistory = checktarehistory;
	}
	public int getChecktareinfo() {
		return checktareinfo;
	}
	public void setChecktareinfo(int checktareinfo) {
		this.checktareinfo = checktareinfo;
	}
	public int getChecktarelast() {
		return checktarelast;
	}
	public void setChecktarelast(int checktarelast) {
		this.checktarelast = checktarelast;
	}
	public int getDflag() {
		return dflag;
	}
	public void setDflag(int dflag) {
		this.dflag = dflag;
	}
	public int getDispatchtype() {
		return dispatchtype;
	}
	public void setDispatchtype(int dispatchtype) {
		this.dispatchtype = dispatchtype;
	}
	public int getForcereceive() {
		return forcereceive;
	}
	public void setForcereceive(int forcereceive) {
		this.forcereceive = forcereceive;
	}
	public int getGflag() {
		return gflag;
	}
	public void setGflag(int gflag) {
		this.gflag = gflag;
	}
	public double getGross() {
		return gross;
	}
	public void setGross(double gross) {
		this.gross = gross;
	}
	public int getGrossgroupno() {
		return grossgroupno;
	}
	public void setGrossgroupno(int grossgroupno) {
		this.grossgroupno = grossgroupno;
	}
	public int getGrossgroupnob() {
		return grossgroupnob;
	}
	public void setGrossgroupnob(int grossgroupnob) {
		this.grossgroupnob = grossgroupnob;
	}
	public int getGrossoutdo() {
		return grossoutdo;
	}
	public void setGrossoutdo(int grossoutdo) {
		this.grossoutdo = grossoutdo;
	}
	public int getGrossoutdown() {
		return grossoutdown;
	}
	public void setGrossoutdown(int grossoutdown) {
		this.grossoutdown = grossoutdown;
	}
	public int getGrossoutup() {
		return grossoutup;
	}
	public void setGrossoutup(int grossoutup) {
		this.grossoutup = grossoutup;
	}
	public int getGrossserial() {
		return grossserial;
	}
	public void setGrossserial(int grossserial) {
		this.grossserial = grossserial;
	}
	public int getGrossserialb() {
		return grossserialb;
	}
	public void setGrossserialb(int grossserialb) {
		this.grossserialb = grossserialb;
	}
	public int getGrossspeed() {
		return grossspeed;
	}
	public void setGrossspeed(int grossspeed) {
		this.grossspeed = grossspeed;
	}
	public int getGrossspeedb() {
		return grossspeedb;
	}
	public void setGrossspeedb(int grossspeedb) {
		this.grossspeedb = grossspeedb;
	}
	public int getGrosstimeout() {
		return grosstimeout;
	}
	public void setGrosstimeout(int grosstimeout) {
		this.grosstimeout = grosstimeout;
	}
	public int getGrosstimeoutdo() {
		return grosstimeoutdo;
	}
	public void setGrosstimeoutdo(int grosstimeoutdo) {
		this.grosstimeoutdo = grosstimeoutdo;
	}
	public int getGrossweightout() {
		return grossweightout;
	}
	public void setGrossweightout(int grossweightout) {
		this.grossweightout = grossweightout;
	}
	public int getIfphflag() {
		return ifphflag;
	}
	public void setIfphflag(int ifphflag) {
		this.ifphflag = ifphflag;
	}
	public int getIsbasket() {
		return isbasket;
	}
	public void setIsbasket(int isbasket) {
		this.isbasket = isbasket;
	}
	public int getIscompare() {
		return iscompare;
	}
	public void setIscompare(int iscompare) {
		this.iscompare = iscompare;
	}
	public int getIsgrossbasket() {
		return isgrossbasket;
	}
	public void setIsgrossbasket(int isgrossbasket) {
		this.isgrossbasket = isgrossbasket;
	}
	public int getIsgrossmaterial() {
		return isgrossmaterial;
	}
	public void setIsgrossmaterial(int isgrossmaterial) {
		this.isgrossmaterial = isgrossmaterial;
	}
	public int getIsgrossplanid() {
		return isgrossplanid;
	}
	public void setIsgrossplanid(int isgrossplanid) {
		this.isgrossplanid = isgrossplanid;
	}
	public int getIsgrossship() {
		return isgrossship;
	}
	public void setIsgrossship(int isgrossship) {
		this.isgrossship = isgrossship;
	}
	public int getIsgrosssource() {
		return isgrosssource;
	}
	public void setIsgrosssource(int isgrosssource) {
		this.isgrosssource = isgrosssource;
	}
	public int getIsgrosstarget() {
		return isgrosstarget;
	}
	public void setIsgrosstarget(int isgrosstarget) {
		this.isgrosstarget = isgrosstarget;
	}
	public int getIsgrosstaskcode() {
		return isgrosstaskcode;
	}
	public void setIsgrosstaskcode(int isgrosstaskcode) {
		this.isgrosstaskcode = isgrosstaskcode;
	}
	public int getIsinputweightb() {
		return isinputweightb;
	}
	public void setIsinputweightb(int isinputweightb) {
		this.isinputweightb = isinputweightb;
	}
	public int getIsloading() {
		return isloading;
	}
	public void setIsloading(int isloading) {
		this.isloading = isloading;
	}
	public int getIsplan() {
		return isplan;
	}
	public void setIsplan(int isplan) {
		this.isplan = isplan;
	}
	public int getIssendcar() {
		return issendcar;
	}
	public void setIssendcar(int issendcar) {
		this.issendcar = issendcar;
	}
	public int getIssure() {
		return issure;
	}
	public void setIssure(int issure) {
		this.issure = issure;
	}
	public int getIssuttlebasket() {
		return issuttlebasket;
	}
	public void setIssuttlebasket(int issuttlebasket) {
		this.issuttlebasket = issuttlebasket;
	}
	public int getIssuttlematerial() {
		return issuttlematerial;
	}
	public void setIssuttlematerial(int issuttlematerial) {
		this.issuttlematerial = issuttlematerial;
	}
	public int getIssuttleplanid() {
		return issuttleplanid;
	}
	public void setIssuttleplanid(int issuttleplanid) {
		this.issuttleplanid = issuttleplanid;
	}
	public int getIssuttleship() {
		return issuttleship;
	}
	public void setIssuttleship(int issuttleship) {
		this.issuttleship = issuttleship;
	}
	public int getIssuttlesource() {
		return issuttlesource;
	}
	public void setIssuttlesource(int issuttlesource) {
		this.issuttlesource = issuttlesource;
	}
	public int getIssuttletarget() {
		return issuttletarget;
	}
	public void setIssuttletarget(int issuttletarget) {
		this.issuttletarget = issuttletarget;
	}
	public int getIssuttletaskcode() {
		return issuttletaskcode;
	}
	public void setIssuttletaskcode(int issuttletaskcode) {
		this.issuttletaskcode = issuttletaskcode;
	}
	public int getIstarebasket() {
		return istarebasket;
	}
	public void setIstarebasket(int istarebasket) {
		this.istarebasket = istarebasket;
	}
	public int getIstarematerial() {
		return istarematerial;
	}
	public void setIstarematerial(int istarematerial) {
		this.istarematerial = istarematerial;
	}
	public int getIstareplanid() {
		return istareplanid;
	}
	public void setIstareplanid(int istareplanid) {
		this.istareplanid = istareplanid;
	}
	public int getIstareship() {
		return istareship;
	}
	public void setIstareship(int istareship) {
		this.istareship = istareship;
	}
	public int getIstaresource() {
		return istaresource;
	}
	public void setIstaresource(int istaresource) {
		this.istaresource = istaresource;
	}
	public int getIstaretarget() {
		return istaretarget;
	}
	public void setIstaretarget(int istaretarget) {
		this.istaretarget = istaretarget;
	}
	public int getIstaretaskcode() {
		return istaretaskcode;
	}
	public void setIstaretaskcode(int istaretaskcode) {
		this.istaretaskcode = istaretaskcode;
	}
	public int getIstaskcode() {
		return istaskcode;
	}
	public void setIstaskcode(int istaskcode) {
		this.istaskcode = istaskcode;
	}
	public int getKqflag() {
		return kqflag;
	}
	public void setKqflag(int kqflag) {
		this.kqflag = kqflag;
	}
	public int getMaterialcount() {
		return materialcount;
	}
	public void setMaterialcount(int materialcount) {
		this.materialcount = materialcount;
	}
	public int getMaxtare() {
		return maxtare;
	}
	public void setMaxtare(int maxtare) {
		this.maxtare = maxtare;
	}
	public int getMeasuretype() {
		return measuretype;
	}
	public void setMeasuretype(int measuretype) {
		this.measuretype = measuretype;
	}
	public int getMflag() {
		return mflag;
	}
	public void setMflag(int mflag) {
		this.mflag = mflag;
	}
	public int getMinterval() {
		return minterval;
	}
	public void setMinterval(int minterval) {
		this.minterval = minterval;
	}
	public int getMqflag() {
		return mqflag;
	}
	public void setMqflag(int mqflag) {
		this.mqflag = mqflag;
	}
	public int getMstate() {
		return mstate;
	}
	public void setMstate(int mstate) {
		this.mstate = mstate;
	}
	public int getNotentergatedo() {
		return notentergatedo;
	}
	public void setNotentergatedo(int notentergatedo) {
		this.notentergatedo = notentergatedo;
	}
	public int getNotleavegatedo() {
		return notleavegatedo;
	}
	public void setNotleavegatedo(int notleavegatedo) {
		this.notleavegatedo = notleavegatedo;
	}
	public int getNotslampedo() {
		return notslampedo;
	}
	public void setNotslampedo(int notslampedo) {
		this.notslampedo = notslampedo;
	}
	public int getNotstoreindo() {
		return notstoreindo;
	}
	public void setNotstoreindo(int notstoreindo) {
		this.notstoreindo = notstoreindo;
	}
	public int getNotstoreoutdo() {
		return notstoreoutdo;
	}
	public void setNotstoreoutdo(int notstoreoutdo) {
		this.notstoreoutdo = notstoreoutdo;
	}
	public int getOvweitghttype() {
		return ovweitghttype;
	}
	public void setOvweitghttype(int ovweitghttype) {
		this.ovweitghttype = ovweitghttype;
	}
	public int getPlancarcount() {
		return plancarcount;
	}
	public void setPlancarcount(int plancarcount) {
		this.plancarcount = plancarcount;
	}
	public int getPlancarcountflag() {
		return plancarcountflag;
	}
	public void setPlancarcountflag(int plancarcountflag) {
		this.plancarcountflag = plancarcountflag;
	}
	public int getPlancarcountout() {
		return plancarcountout;
	}
	public void setPlancarcountout(int plancarcountout) {
		this.plancarcountout = plancarcountout;
	}
	public int getPlancarflag() {
		return plancarflag;
	}
	public void setPlancarflag(int plancarflag) {
		this.plancarflag = plancarflag;
	}
	public int getPlancountflag() {
		return plancountflag;
	}
	public void setPlancountflag(int plancountflag) {
		this.plancountflag = plancountflag;
	}
	public int getPlanmaterialcount() {
		return planmaterialcount;
	}
	public void setPlanmaterialcount(int planmaterialcount) {
		this.planmaterialcount = planmaterialcount;
	}
	public int getPlanmaterialcountout() {
		return planmaterialcountout;
	}
	public void setPlanmaterialcountout(int planmaterialcountout) {
		this.planmaterialcountout = planmaterialcountout;
	}
	public int getPlantimeflag() {
		return plantimeflag;
	}
	public void setPlantimeflag(int plantimeflag) {
		this.plantimeflag = plantimeflag;
	}
	public int getPlanweighout() {
		return planweighout;
	}
	public void setPlanweighout(int planweighout) {
		this.planweighout = planweighout;
	}
	public double getPlanweight() {
		return planweight;
	}
	public void setPlanweight(double planweight) {
		this.planweight = planweight;
	}
	public int getPlanweightflag() {
		return planweightflag;
	}
	public void setPlanweightflag(int planweightflag) {
		this.planweightflag = planweightflag;
	}
	public int getQflag() {
		return qflag;
	}
	public void setQflag(int qflag) {
		this.qflag = qflag;
	}
	public int getQualityprint() {
		return qualityprint;
	}
	public void setQualityprint(int qualityprint) {
		this.qualityprint = qualityprint;
	}
	public int getRegrossdo() {
		return regrossdo;
	}
	public void setRegrossdo(int regrossdo) {
		this.regrossdo = regrossdo;
	}
	public int getResuttledo() {
		return resuttledo;
	}
	public void setResuttledo(int resuttledo) {
		this.resuttledo = resuttledo;
	}
	public int getRetaredo() {
		return retaredo;
	}
	public void setRetaredo(int retaredo) {
		this.retaredo = retaredo;
	}
	public int getSettlementmodes() {
		return settlementmodes;
	}
	public void setSettlementmodes(int settlementmodes) {
		this.settlementmodes = settlementmodes;
	}
	public int getSflag() {
		return sflag;
	}
	public void setSflag(int sflag) {
		this.sflag = sflag;
	}
	public int getSnumber() {
		return snumber;
	}
	public void setSnumber(int snumber) {
		this.snumber = snumber;
	}
	public double getSuttle() {
		return suttle;
	}
	public void setSuttle(double suttle) {
		this.suttle = suttle;
	}
	public int getSuttleoutdo() {
		return suttleoutdo;
	}
	public void setSuttleoutdo(int suttleoutdo) {
		this.suttleoutdo = suttleoutdo;
	}
	public int getSuttleoutdown() {
		return suttleoutdown;
	}
	public void setSuttleoutdown(int suttleoutdown) {
		this.suttleoutdown = suttleoutdown;
	}
	public int getSuttleoutup() {
		return suttleoutup;
	}
	public void setSuttleoutup(int suttleoutup) {
		this.suttleoutup = suttleoutup;
	}
	public double getTare() {
		return tare;
	}
	public void setTare(double tare) {
		this.tare = tare;
	}
	public int getTaregroupno() {
		return taregroupno;
	}
	public void setTaregroupno(int taregroupno) {
		this.taregroupno = taregroupno;
	}
	public int getTaregroupnob() {
		return taregroupnob;
	}
	public void setTaregroupnob(int taregroupnob) {
		this.taregroupnob = taregroupnob;
	}
	public int getTarehour() {
		return tarehour;
	}
	public void setTarehour(int tarehour) {
		this.tarehour = tarehour;
	}
	public int getTareoutdo() {
		return tareoutdo;
	}
	public void setTareoutdo(int tareoutdo) {
		this.tareoutdo = tareoutdo;
	}
	public int getTareoutdown() {
		return tareoutdown;
	}
	public void setTareoutdown(int tareoutdown) {
		this.tareoutdown = tareoutdown;
	}
	public int getTareoutup() {
		return tareoutup;
	}
	public void setTareoutup(int tareoutup) {
		this.tareoutup = tareoutup;
	}
	public int getTareserial() {
		return tareserial;
	}
	public void setTareserial(int tareserial) {
		this.tareserial = tareserial;
	}
	public int getTareserialb() {
		return tareserialb;
	}
	public void setTareserialb(int tareserialb) {
		this.tareserialb = tareserialb;
	}
	public int getTarespeed() {
		return tarespeed;
	}
	public void setTarespeed(int tarespeed) {
		this.tarespeed = tarespeed;
	}
	public int getTarespeedb() {
		return tarespeedb;
	}
	public void setTarespeedb(int tarespeedb) {
		this.tarespeedb = tarespeedb;
	}
	public int getTaretimeout() {
		return taretimeout;
	}
	public void setTaretimeout(int taretimeout) {
		this.taretimeout = taretimeout;
	}
	public int getTaretimeoutdo() {
		return taretimeoutdo;
	}
	public void setTaretimeoutdo(int taretimeoutdo) {
		this.taretimeoutdo = taretimeoutdo;
	}
	public int getTareweightout() {
		return tareweightout;
	}
	public void setTareweightout(int tareweightout) {
		this.tareweightout = tareweightout;
	}
	public int getValidflag() {
		return validflag;
	}
	public void setValidflag(int validflag) {
		this.validflag = validflag;
	}
	public String getBasket() {
		return basket;
	}
	public void setBasket(String basket) {
		this.basket = basket;
	}
	public String getBatchcode() {
		return batchcode;
	}
	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
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
	public String getCreateman() {
		return createman;
	}
	public void setCreateman(String createman) {
		this.createman = createman;
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
	public String getDeductiontime() {
		return deductiontime;
	}
	public void setDeductiontime(String deductiontime) {
		this.deductiontime = deductiontime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getEntergatecode() {
		return entergatecode;
	}
	public void setEntergatecode(String entergatecode) {
		this.entergatecode = entergatecode;
	}
	public String getEntergatename() {
		return entergatename;
	}
	public void setEntergatename(String entergatename) {
		this.entergatename = entergatename;
	}
	public String getEntertime() {
		return entertime;
	}
	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}
	public String getGrosslogid() {
		return grosslogid;
	}
	public void setGrosslogid(String grosslogid) {
		this.grosslogid = grosslogid;
	}
	public String getGrosslogidb() {
		return grosslogidb;
	}
	public void setGrosslogidb(String grosslogidb) {
		this.grosslogidb = grosslogidb;
	}
	public String getGrossoperacode() {
		return grossoperacode;
	}
	public void setGrossoperacode(String grossoperacode) {
		this.grossoperacode = grossoperacode;
	}
	public String getGrossoperacodeb() {
		return grossoperacodeb;
	}
	public void setGrossoperacodeb(String grossoperacodeb) {
		this.grossoperacodeb = grossoperacodeb;
	}
	public String getGrossoperaname() {
		return grossoperaname;
	}
	public void setGrossoperaname(String grossoperaname) {
		this.grossoperaname = grossoperaname;
	}
	public String getGrossoperanameb() {
		return grossoperanameb;
	}
	public void setGrossoperanameb(String grossoperanameb) {
		this.grossoperanameb = grossoperanameb;
	}
	public String getGrosstime() {
		return grosstime;
	}
	public void setGrosstime(String grosstime) {
		this.grosstime = grosstime;
	}
	public String getGrosstimeb() {
		return grosstimeb;
	}
	public void setGrosstimeb(String grosstimeb) {
		this.grosstimeb = grosstimeb;
	}
	public String getGrossweigh() {
		return grossweigh;
	}
	public void setGrossweigh(String grossweigh) {
		this.grossweigh = grossweigh;
	}
	public String getGrossweighb() {
		return grossweighb;
	}
	public void setGrossweighb(String grossweighb) {
		this.grossweighb = grossweighb;
	}
	public String getGrossweighgroup() {
		return grossweighgroup;
	}
	public void setGrossweighgroup(String grossweighgroup) {
		this.grossweighgroup = grossweighgroup;
	}
	public String getGrossweighgroupb() {
		return grossweighgroupb;
	}
	public void setGrossweighgroupb(String grossweighgroupb) {
		this.grossweighgroupb = grossweighgroupb;
	}
	public String getGrossweighid() {
		return grossweighid;
	}
	public void setGrossweighid(String grossweighid) {
		this.grossweighid = grossweighid;
	}
	public String getGrossweighidb() {
		return grossweighidb;
	}
	public void setGrossweighidb(String grossweighidb) {
		this.grossweighidb = grossweighidb;
	}
	public String getIcid() {
		return icid;
	}
	public void setIcid(String icid) {
		this.icid = icid;
	}
	public String getIcno() {
		return icno;
	}
	public void setIcno(String icno) {
		this.icno = icno;
	}
	public String getIctype() {
		return ictype;
	}
	public void setIctype(String ictype) {
		this.ictype = ictype;
	}
	public String getLeavegatecode() {
		return leavegatecode;
	}
	public void setLeavegatecode(String leavegatecode) {
		this.leavegatecode = leavegatecode;
	}
	public String getLeavegatename() {
		return leavegatename;
	}
	public void setLeavegatename(String leavegatename) {
		this.leavegatename = leavegatename;
	}
	public String getLeavetime() {
		return leavetime;
	}
	public void setLeavetime(String leavetime) {
		this.leavetime = leavetime;
	}
	public String getMatchid() {
		return matchid;
	}
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
	public String getMatchidb() {
		return matchidb;
	}
	public void setMatchidb(String matchidb) {
		this.matchidb = matchidb;
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
	public String getMaterialspec() {
		return materialspec;
	}
	public void setMaterialspec(String materialspec) {
		this.materialspec = materialspec;
	}
	public String getMaterialspeccode() {
		return materialspeccode;
	}
	public void setMaterialspeccode(String materialspeccode) {
		this.materialspeccode = materialspeccode;
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
	public String getOperatype() {
		return operatype;
	}
	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getPlanid() {
		return planid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}
	public String getPrintsetgross() {
		return printsetgross;
	}
	public void setPrintsetgross(String printsetgross) {
		this.printsetgross = printsetgross;
	}
	public String getPrintsetsuttle() {
		return printsetsuttle;
	}
	public void setPrintsetsuttle(String printsetsuttle) {
		this.printsetsuttle = printsetsuttle;
	}
	public String getPrintsettare() {
		return printsettare;
	}
	public void setPrintsettare(String printsettare) {
		this.printsettare = printsettare;
	}
	public String getRealsourcecode() {
		return realsourcecode;
	}
	public void setRealsourcecode(String realsourcecode) {
		this.realsourcecode = realsourcecode;
	}
	public String getRealsourcename() {
		return realsourcename;
	}
	public void setRealsourcename(String realsourcename) {
		this.realsourcename = realsourcename;
	}
	public String getRfidid() {
		return rfidid;
	}
	public void setRfidid(String rfidid) {
		this.rfidid = rfidid;
	}
	public String getRfidno() {
		return rfidno;
	}
	public void setRfidno(String rfidno) {
		this.rfidno = rfidno;
	}
	public String getRfidtype() {
		return rfidtype;
	}
	public void setRfidtype(String rfidtype) {
		this.rfidtype = rfidtype;
	}
	public String getSampletime() {
		return sampletime;
	}
	public void setSampletime(String sampletime) {
		this.sampletime = sampletime;
	}
	public String getSampleunitcode() {
		return sampleunitcode;
	}
	public void setSampleunitcode(String sampleunitcode) {
		this.sampleunitcode = sampleunitcode;
	}
	public String getSampleunitname() {
		return sampleunitname;
	}
	public void setSampleunitname(String sampleunitname) {
		this.sampleunitname = sampleunitname;
	}
	public String getShip() {
		return ship;
	}
	public void setShip(String ship) {
		this.ship = ship;
	}
	public String getShipcode() {
		return shipcode;
	}
	public void setShipcode(String shipcode) {
		this.shipcode = shipcode;
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
	public String getSourceoperacode() {
		return sourceoperacode;
	}
	public void setSourceoperacode(String sourceoperacode) {
		this.sourceoperacode = sourceoperacode;
	}
	public String getSourceoperaname() {
		return sourceoperaname;
	}
	public void setSourceoperaname(String sourceoperaname) {
		this.sourceoperaname = sourceoperaname;
	}
	public String getSourceplace() {
		return sourceplace;
	}
	public void setSourceplace(String sourceplace) {
		this.sourceplace = sourceplace;
	}
	public String getSourcetime() {
		return sourcetime;
	}
	public void setSourcetime(String sourcetime) {
		this.sourcetime = sourcetime;
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
	public String getSuttleweighid() {
		return suttleweighid;
	}
	public void setSuttleweighid(String suttleweighid) {
		this.suttleweighid = suttleweighid;
	}
	public String getSysmemo() {
		return sysmemo;
	}
	public void setSysmemo(String sysmemo) {
		this.sysmemo = sysmemo;
	}
	public String getTarelogid() {
		return tarelogid;
	}
	public void setTarelogid(String tarelogid) {
		this.tarelogid = tarelogid;
	}
	public String getTarelogidb() {
		return tarelogidb;
	}
	public void setTarelogidb(String tarelogidb) {
		this.tarelogidb = tarelogidb;
	}
	public String getTareoperacode() {
		return tareoperacode;
	}
	public void setTareoperacode(String tareoperacode) {
		this.tareoperacode = tareoperacode;
	}
	public String getTareoperacodeb() {
		return tareoperacodeb;
	}
	public void setTareoperacodeb(String tareoperacodeb) {
		this.tareoperacodeb = tareoperacodeb;
	}
	public String getTareoperaname() {
		return tareoperaname;
	}
	public void setTareoperaname(String tareoperaname) {
		this.tareoperaname = tareoperaname;
	}
	public String getTareoperanameb() {
		return tareoperanameb;
	}
	public void setTareoperanameb(String tareoperanameb) {
		this.tareoperanameb = tareoperanameb;
	}
	public String getTaretime() {
		return taretime;
	}
	public void setTaretime(String taretime) {
		this.taretime = taretime;
	}
	public String getTaretimeb() {
		return taretimeb;
	}
	public void setTaretimeb(String taretimeb) {
		this.taretimeb = taretimeb;
	}
	public String getTareweigh() {
		return tareweigh;
	}
	public void setTareweigh(String tareweigh) {
		this.tareweigh = tareweigh;
	}
	public String getTareweighb() {
		return tareweighb;
	}
	public void setTareweighb(String tareweighb) {
		this.tareweighb = tareweighb;
	}
	public String getTareweighgroup() {
		return tareweighgroup;
	}
	public void setTareweighgroup(String tareweighgroup) {
		this.tareweighgroup = tareweighgroup;
	}
	public String getTareweighgroupb() {
		return tareweighgroupb;
	}
	public void setTareweighgroupb(String tareweighgroupb) {
		this.tareweighgroupb = tareweighgroupb;
	}
	public String getTareweighid() {
		return tareweighid;
	}
	public void setTareweighid(String tareweighid) {
		this.tareweighid = tareweighid;
	}
	public String getTareweighidb() {
		return tareweighidb;
	}
	public void setTareweighidb(String tareweighidb) {
		this.tareweighidb = tareweighidb;
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
	public String getTargetoperacode() {
		return targetoperacode;
	}
	public void setTargetoperacode(String targetoperacode) {
		this.targetoperacode = targetoperacode;
	}
	public String getTargetoperaname() {
		return targetoperaname;
	}
	public void setTargetoperaname(String targetoperaname) {
		this.targetoperaname = targetoperaname;
	}
	public String getTargetplace() {
		return targetplace;
	}
	public void setTargetplace(String targetplace) {
		this.targetplace = targetplace;
	}
	public String getTargettime() {
		return targettime;
	}
	public void setTargettime(String targettime) {
		this.targettime = targettime;
	}
	public String getTaskcode() {
		return taskcode;
	}
	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}
	public String getUsermemo() {
		return usermemo;
	}
	public void setUsermemo(String usermemo) {
		this.usermemo = usermemo;
	}
	public double getDeduction2() {
		return deduction2;
	}
	public void setDeduction2(double deduction2) {
		this.deduction2 = deduction2;
	}
	public double getDeduction() {
		return deduction;
	}
	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}
	public double getDeductiontype() {
		return deductiontype;
	}
	public void setDeductiontype(double deductiontype) {
		this.deductiontype = deductiontype;
	}
	public double getDeductionunit() {
		return deductionunit;
	}
	public void setDeductionunit(double deductionunit) {
		this.deductionunit = deductionunit;
	}
	public double getGrossb() {
		return grossb;
	}
	public void setGrossb(double grossb) {
		this.grossb = grossb;
	}
	public double getOvweitghtmax() {
		return ovweitghtmax;
	}
	public void setOvweitghtmax(double ovweitghtmax) {
		this.ovweitghtmax = ovweitghtmax;
	}
	public double getOvweitghtmin() {
		return ovweitghtmin;
	}
	public void setOvweitghtmin(double ovweitghtmin) {
		this.ovweitghtmin = ovweitghtmin;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSuttleapp() {
		return suttleapp;
	}
	public void setSuttleapp(double suttleapp) {
		this.suttleapp = suttleapp;
	}
	public double getTareb() {
		return tareb;
	}
	public void setTareb(double tareb) {
		this.tareb = tareb;
	}
	public String getOpstr() {
		return opstr;
	}
	public void setOpstr(String opstr) {
		this.opstr = opstr;
	}
	public String getMrecord() {
		return mrecord;
	}
	public void setMrecord(String mrecord) {
		this.mrecord = mrecord;
	}
	public String getSelecttime() {
		return selecttime;
	}
	public void setSelecttime(String selecttime) {
		this.selecttime = selecttime;
	}
	public String getPrintstr() {
		return printstr;
	}
	public void setPrintstr(String printstr) {
		this.printstr = printstr;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getWeighttype() {
		return weighttype;
	}
	public void setWeighttype(String weighttype) {
		this.weighttype = weighttype;
	}
	public String getTimetype() {
		return timetype;
	}
	public void setTimetype(String timetype) {
		this.timetype = timetype;
	}
	public double getSametareoutup() {
		return sametareoutup;
	}
	public void setSametareoutup(double sametareoutup) {
		this.sametareoutup = sametareoutup;
	}
	public double getSametareoutdown() {
		return sametareoutdown;
	}
	public void setSametareoutdown(double sametareoutdown) {
		this.sametareoutdown = sametareoutdown;
	}
	public double getSamegrossoutup() {
		return samegrossoutup;
	}
	public void setSamegrossoutup(double samegrossoutup) {
		this.samegrossoutup = samegrossoutup;
	}
	public double getSamegrossoutdown() {
		return samegrossoutdown;
	}
	public void setSamegrossoutdown(double samegrossoutdown) {
		this.samegrossoutdown = samegrossoutdown;
	}
	public int getSamegrosstime() {
		return samegrosstime;
	}
	public void setSamegrosstime(int samegrosstime) {
		this.samegrosstime = samegrosstime;
	}
	public int getSametaretime() {
		return sametaretime;
	}
	public void setSametaretime(int sametaretime) {
		this.sametaretime = sametaretime;
	}
	public double getWeightup() {
		return weightup;
	}
	public void setWeightup(double weightup) {
		this.weightup = weightup;
	}
	public double getWeightdown() {
		return weightdown;
	}
	public void setWeightdown(double weightdown) {
		this.weightdown = weightdown;
	}
	public int getWeighttime() {
		return weighttime;
	}
	public void setWeighttime(int weighttime) {
		this.weighttime = weighttime;
	}
	public double getRgross() {
		return rgross;
	}
	public void setRgross(double rgross) {
		this.rgross = rgross;
	}
	public double getRtare() {
		return rtare;
	}
	public void setRtare(double rtare) {
		this.rtare = rtare;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
    
    

}