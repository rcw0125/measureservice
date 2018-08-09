package com.talent.measure.model;

public class Operaconfig {
	private String operatype;
	private String sflag; // 是否出入库 0不限制,1出库,2入库,3出入库
	private String notstoreoutdo; // 无发货确认时，设定要发货，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private String notstoreindo; // 无发货确认时，设定要发货，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private String gflag; // 是否进出门 0不限制，1进门，2出门，3进出门
	private String notentergatedo; // 无进门的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private String notleavegatedo; // 无进门的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private String printsetgross; // 只计毛打印联，以逗号隔开
	private String printsettare;// 只计皮打印联，以逗号隔开
	private String printsetsuttle; // 出净重打印联，以逗号隔开
	private String qflag; // 取样标记0不取样，1计毛前，2收货前，3收货后，当有更详细的业务设置时，以详细的为准
	private String notslampedo; // 无取样的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private String tareoutdo; // 发货皮重超差后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private double tareoutup=0; // 发货皮重超差上限值 value=0不允许超，0<value<1百分比控制，value>=1
								// 按千克计算
	private double tareoutdown=0; // 发货皮重超差下限值 value=0不允许超，0<value<1百分比控制，value>=1
								// 按千克计算
	private String grossoutdo; // 发货毛重超差后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private double grossoutup=0; // 发货毛重超差上限值 value=0不允许超，0<value<1百分比控制，value>=1
								// 按千克计算
	private double grossoutdown=0; // 发货毛重超差下限值
									// value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private String suttleoutdo; // 发货净重超差后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private double suttleoutup=0; // 发货净重超差上限值 value=0不允许超，0<value<1百分比控制，value>=1
								// 按千克计算
	private double suttleoutdown=0; // 发货净重超差下限值
									// value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private String regrossdo; // 重复只计毛时,0：不检查，1：进行提示，2，进行选择，3禁止计量
	private String retaredo; // 重复只计皮时,0：不检查，1：进行提示，2，进行选择，3禁止计量
	private String resuttledo; // 重复计量净重时,0：不检查，1：进行提示，2，进行选择，3禁止计量
	private double tarehour=0; // 皮重有效期 -1不限制，0一车一皮，>n n小时有效
	private String grosstimeout; // 毛重有效期
	private String taretimeout; // 皮重有效期 当皮重是1一车一皮时，有效
	private String grosstimeoutdo; // 皮重超期后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private String taretimeoutdo; // 毛重超期后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private String checktaredo; // 皮重超差的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private String tarehourdo; // 皮重有效期超期处理方式： 0：不检查，1：进行提示，2，进行选择，3禁止计量
	private double sametareoutup=0; // 相似皮重超差上限值
									// value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private double sametareoutdown=0; // 相似皮重超差下限值
									// value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private double samegrossoutup=0; // 相似毛重超差上限值
									// value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private double samegrossoutdown=0; // 相似毛重超差下限值
										// value=0不允许超，0<value<1百分比控制，value>=1
										// 按千克计算
	private int samegrosstime=0; // 查询多长时间以内的相似毛重 单位是分钟
	private int sametaretime=0; // 查询多长时间以内的相似皮重 单位是分钟
	private String sameweightdo; // 相似重量处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private double deduction2; // 扣重值 扣重值从配置参数或者业务点录入 0<value<1百分比控制，value>=1 按千克计算
	private int deductionunit=0; // 扣重单位 0计量、1发货、2收货
	private int deductiontype=0; // 扣重类型 0不扣、1固定值和2录入值
	private String checkoperatimedo; // 检查前一个业务到当前业务的时间超时后的处理方式
									// ，0：不检查，1：进行提示，2，进行选择，3禁止计量 默认2
	private double checkoperatime=0; // 检查前一个业务到当前业务的时间超时间隔 分钟，默认0
	private String checkplanweighdo; // 计划重量超差的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private double planweighout=0; // 计划重量超差上限值
									// value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private String checkplancarcountdo; // 计划车数超差的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private int plancarcountout=0; // 计划车数超差上限值 value=0不允许超，，value>=1 车数
	private String checkplanmaterialcountdo; // 计划件/支超差的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	private int planmaterialcountout=0; // 计划件/支数超差上限值 value=0不允许超，，value>=1 件数
	private String checktarelast;
	
	
	
	
	public String getChecktarelast() {
		return checktarelast;
	}
	public void setChecktarelast(String checktarelast) {
		this.checktarelast = checktarelast;
	}
	public String getOperatype() {
		return operatype;
	}
	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}
	public String getSflag() {
		return sflag;
	}
	public void setSflag(String sflag) {
		this.sflag = sflag;
	}
	public String getNotstoreoutdo() {
		return notstoreoutdo;
	}
	public void setNotstoreoutdo(String notstoreoutdo) {
		this.notstoreoutdo = notstoreoutdo;
	}
	public String getNotstoreindo() {
		return notstoreindo;
	}
	public void setNotstoreindo(String notstoreindo) {
		this.notstoreindo = notstoreindo;
	}
	public String getGflag() {
		return gflag;
	}
	public void setGflag(String gflag) {
		this.gflag = gflag;
	}
	public String getNotentergatedo() {
		return notentergatedo;
	}
	public void setNotentergatedo(String notentergatedo) {
		this.notentergatedo = notentergatedo;
	}
	public String getNotleavegatedo() {
		return notleavegatedo;
	}
	public void setNotleavegatedo(String notleavegatedo) {
		this.notleavegatedo = notleavegatedo;
	}
	public String getPrintsetgross() {
		return printsetgross;
	}
	public void setPrintsetgross(String printsetgross) {
		this.printsetgross = printsetgross;
	}
	public String getPrintsettare() {
		return printsettare;
	}
	public void setPrintsettare(String printsettare) {
		this.printsettare = printsettare;
	}
	public String getPrintsetsuttle() {
		return printsetsuttle;
	}
	public void setPrintsetsuttle(String printsetsuttle) {
		this.printsetsuttle = printsetsuttle;
	}
	public String getQflag() {
		return qflag;
	}
	public void setQflag(String qflag) {
		this.qflag = qflag;
	}
	public String getNotslampedo() {
		return notslampedo;
	}
	public void setNotslampedo(String notslampedo) {
		this.notslampedo = notslampedo;
	}
	public String getTareoutdo() {
		return tareoutdo;
	}
	public void setTareoutdo(String tareoutdo) {
		this.tareoutdo = tareoutdo;
	}

	public String getGrossoutdo() {
		return grossoutdo;
	}
	public void setGrossoutdo(String grossoutdo) {
		this.grossoutdo = grossoutdo;
	}

	public String getSuttleoutdo() {
		return suttleoutdo;
	}
	public void setSuttleoutdo(String suttleoutdo) {
		this.suttleoutdo = suttleoutdo;
	}

	public String getRegrossdo() {
		return regrossdo;
	}
	public void setRegrossdo(String regrossdo) {
		this.regrossdo = regrossdo;
	}
	public String getRetaredo() {
		return retaredo;
	}
	public void setRetaredo(String retaredo) {
		this.retaredo = retaredo;
	}
	public String getResuttledo() {
		return resuttledo;
	}
	public void setResuttledo(String resuttledo) {
		this.resuttledo = resuttledo;
	}

	public String getGrosstimeout() {
		return grosstimeout;
	}
	public void setGrosstimeout(String grosstimeout) {
		this.grosstimeout = grosstimeout;
	}
	public String getTaretimeout() {
		return taretimeout;
	}
	public void setTaretimeout(String taretimeout) {
		this.taretimeout = taretimeout;
	}
	public String getGrosstimeoutdo() {
		return grosstimeoutdo;
	}
	public void setGrosstimeoutdo(String grosstimeoutdo) {
		this.grosstimeoutdo = grosstimeoutdo;
	}
	public String getTaretimeoutdo() {
		return taretimeoutdo;
	}
	public void setTaretimeoutdo(String taretimeoutdo) {
		this.taretimeoutdo = taretimeoutdo;
	}
	public String getChecktaredo() {
		return checktaredo;
	}
	public void setChecktaredo(String checktaredo) {
		this.checktaredo = checktaredo;
	}
	public String getTarehourdo() {
		return tarehourdo;
	}
	public void setTarehourdo(String tarehourdo) {
		this.tarehourdo = tarehourdo;
	}

	public String getSameweightdo() {
		return sameweightdo;
	}

	public String getCheckoperatimedo() {
		return checkoperatimedo;
	}
	public void setCheckoperatimedo(String checkoperatimedo) {
		this.checkoperatimedo = checkoperatimedo;
	}
	
	public String getCheckplanweighdo() {
		return checkplanweighdo;
	}
	public void setCheckplanweighdo(String checkplanweighdo) {
		this.checkplanweighdo = checkplanweighdo;
	}
	
	public String getCheckplancarcountdo() {
		return checkplancarcountdo;
	}
	public void setCheckplancarcountdo(String checkplancarcountdo) {
		this.checkplancarcountdo = checkplancarcountdo;
	}

	public String getCheckplanmaterialcountdo() {
		return checkplanmaterialcountdo;
	}
	public void setCheckplanmaterialcountdo(String checkplanmaterialcountdo) {
		this.checkplanmaterialcountdo = checkplanmaterialcountdo;
	}
	public double getTareoutup() {
		return tareoutup;
	}
	public void setTareoutup(double tareoutup) {
		this.tareoutup = tareoutup;
	}
	public double getTareoutdown() {
		return tareoutdown;
	}
	public void setTareoutdown(double tareoutdown) {
		this.tareoutdown = tareoutdown;
	}
	public double getGrossoutup() {
		return grossoutup;
	}
	public void setGrossoutup(double grossoutup) {
		this.grossoutup = grossoutup;
	}
	public double getGrossoutdown() {
		return grossoutdown;
	}
	public void setGrossoutdown(double grossoutdown) {
		this.grossoutdown = grossoutdown;
	}
	public double getSuttleoutup() {
		return suttleoutup;
	}
	public void setSuttleoutup(double suttleoutup) {
		this.suttleoutup = suttleoutup;
	}
	public double getSuttleoutdown() {
		return suttleoutdown;
	}
	public void setSuttleoutdown(double suttleoutdown) {
		this.suttleoutdown = suttleoutdown;
	}
	public double getTarehour() {
		return tarehour;
	}
	public void setTarehour(double tarehour) {
		this.tarehour = tarehour;
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
	public double getDeduction2() {
		return deduction2;
	}
	public void setDeduction2(double deduction2) {
		this.deduction2 = deduction2;
	}
	public int getDeductionunit() {
		return deductionunit;
	}
	public void setDeductionunit(int deductionunit) {
		this.deductionunit = deductionunit;
	}
	public int getDeductiontype() {
		return deductiontype;
	}
	public void setDeductiontype(int deductiontype) {
		this.deductiontype = deductiontype;
	}
	public double getCheckoperatime() {
		return checkoperatime;
	}
	public void setCheckoperatime(double checkoperatime) {
		this.checkoperatime = checkoperatime;
	}
	public double getPlanweighout() {
		return planweighout;
	}
	public void setPlanweighout(double planweighout) {
		this.planweighout = planweighout;
	}
	public int getPlancarcountout() {
		return plancarcountout;
	}
	public void setPlancarcountout(int plancarcountout) {
		this.plancarcountout = plancarcountout;
	}
	public int getPlanmaterialcountout() {
		return planmaterialcountout;
	}
	public void setPlanmaterialcountout(int planmaterialcountout) {
		this.planmaterialcountout = planmaterialcountout;
	}
	public void setSameweightdo(String sameweightdo) {
		this.sameweightdo = sameweightdo;
	}


	

}
