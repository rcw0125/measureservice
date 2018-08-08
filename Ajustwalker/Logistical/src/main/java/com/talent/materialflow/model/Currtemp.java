package com.talent.materialflow.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("currtemp")
public class Currtemp implements Serializable{
	
	private static final long serialVersionUID = -6482891806504317190L;

	private String carno;// 车号
	
	private String cartype; // c汽车,t火车，i铁水车
	
	private String icid;// ic卡id号 ,卡的唯一标示号
	
	private String ictype;// ic卡类型 类型(0、1)、0--临时卡、1--固定卡
	
	private String icno;// ic卡印刷编号
	
	private String rfidid;// rfid卡号 ,卡的唯一标示号
	
	private String rfidtype;// rfid类型(0、1)、0--临时卡、1--固定卡
	
	private String rfidno;// rfid印刷编号
	
	private String matchid;// 验配id clientid+yymmdd+00000 该验配id是第一个业务点生成
	
	private String matchidb;// 发货id
	
	private String operatype;// 业务类型
	
	private String planid;// 计划id
	
	private String taskcode;// 调拨业务号
	
	private String basket;// 料篮号、包号
	
	private String materialcode;// 物料编码
	
	private String materialname;// 物料名称
	
	private String materialspeccode;// 物料规格编码
	
	private String materialspec;// 物料规格
	
	private String shipcode;// 船名编码
	
	private String ship;// 船名
	
	private String sourcecode;// 发货单位编码
	
	private String sourcename;// 发货单位
	
	private String sourceplace;// 供货地点/发站
	
	private String sourcetime;// 发货时间
	
	private String sourceoperacode;// 发货人编码
	
	private String sourceoperaname;// 发货人
	
	private String targetcode;// 收货单位编码
	
	private String targetname;// 收货单位
	
	private String targetplace;// 收货地点名称（集港的是港口，火车是到站名）
	
	private String targettime;// 收货时间
	
	private String targetoperacode;// 收货人编码
	
	private String targetoperaname;// 收货人
	
	private int materialcount;// 0 实际发货支件数
	
	private int planweight;// 0 计划发货重量
	
	private int planmaterialcount;// 0 计划发货总支数
	
	private int plancarcount;// 0 计划发货总车数
	private double gross = 0;// 0 毛重
	
	private String grosstime;// 毛重时间
	
	private String grossweighid;// 计毛衡器id
	
	private String grossweigh;// 毛重衡器名称
	
	private String grossweighgroup;// 毛重衡器组
	
	private String grossoperacode;// 计毛计量员编码
	
	private String grossoperaname;// 计毛计量员
	
	private int grossgroupno;// 0 毛重组号
	
	private int grossserial;// 0 毛重组内序号
	
	private int grossspeed;// 0 毛重速度
	
	private String grosslogid;// 毛重logid,通过毛重logid,可以从照片表中读取照片
	
	private double tare = 0;// 0 皮重
	
	private String taretime;// 皮重时间
	
	private String tareweighid;// 皮重衡器id
	
	private String tareweigh;// 皮重衡器名称
	
	private String tareweighgroup;// 皮重衡器组
	
	private String tareoperacode;// 皮重计量员编码
	
	private String tareoperaname;// 皮重计量员
	
	private int taregroupno;// 0 皮重组号
	
	private int tareserial;// 0 皮重组内序号
	
	private String tarelogid;// 皮重logid,通过皮重logid,可以从照片表中读取照片
	
	private int tarespeed;// 0 计毛速度
	
	private double deduction;// 0 扣重值
	
	private String deductiontime;// 扣重时间
	
	private String deductioncode;// 扣重单位编码
	
	private String deductionname;// 扣重单位
	
	private String deductionoperacode;// 扣重人编码
	
	private String deductionoperaname;// 扣重人
	private double suttle = 0;// 0 净重
	
	private String suttletime;// 净重时间
	
	private String suttleweighid;// 净重衡器id
	
	private String suttleweigh;// 净重衡器名称
	
	private String suttleoperacode;// 净重计量员编码
	
	private String suttleoperaname;// 净重计量员
	
	private String entertime;// to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') 进厂时间
	
	private String entergatecode;// 进厂大门编码
	
	private String entergatename;// 进门大门名称
	
	private String leavetime;// 出厂时间
	
	private String leavegatecode;// 出厂大门编码
	
	private String leavegatename;// 出厂大门名称
	
	private double grossb;// 0 发货毛重
	
	private String grosstimeb;// 发货毛重时间
	
	private String grossweighidb;// 发货计毛衡器id
	
	private String grossweighb;// 发货毛重衡器名称
	
	private String grossweighgroupb;// 发货毛重衡器组
	
	private String grossoperacodeb;// 发货计毛计量员编码
	
	private String grossoperanameb;// 发货计毛计量员
	
	private int grossgroupnob;// 0 发货毛重组号
	
	private int grossserialb;// 0 发货毛重组内序号
	
	private int grossspeedb;// 0 发货毛重速度
	
	private String grosslogidb;// 发货毛重logid,通过毛重logid,可以从照片表中读取照片
	
	private double tareb;// 0 发货皮重
	
	private String taretimeb;// 发货皮重时间
	
	private String tareweighidb;// 发货皮重衡器id
	
	private String tareweighb;// 发货皮重衡器名称
	
	private String tareweighgroupb;// 发货皮重皮重衡器组
	
	private String tareoperacodeb;// 发货皮重计量员编码
	
	private String tareoperanameb;// 发货皮重计量员
	
	private int taregroupnob;// 0 发货皮重组号
	
	private int tareserialb;// 0 发货皮重组内序号
	
	private String tarelogidb;// 皮重logid,通过皮重logid,可以从照片表中读取照片
	
	private int tarespeedb;// 0 计毛速度
	
	private String batchcode;// 批号
	
	private String sampletime;// 取样时间
	
	private String sampleunitcode;// 取样单位编码
	
	private String sampleunitname;// 取样单位
	
	private int dflag;// 0 一车多货标记0 一车一货，1一车多货，进行第二次计量
	
	private int bflag;// 0 退货标记 0 不退货，1部分退货，2全部退货
	
	private int mflag;// 0 计量流程标记厂内调拨 0不限制,1先毛后皮,2先皮后毛;跨区调拨 3皮毛毛皮、4毛毛皮、5毛毛
	
	private String usermemo;// 用户备注
	
	private String sysmemo;// 系统备注
	
	private Date createdate;// 记录的添加时间
	
	private int validflag;// 1 标记：1有效，0作废，8完成
	
	private String orderno;// 订单号
	
	private int mstate;// 0 计量状态 0没有计量，8计量完成
	
	private String motorcadename;// 物流公司
	
	private String motorcadecode;// 物流公司编码
	
	private int sflag;// 0 是否出入库 0不限制,1出库,2入库,3出入库
	
	private int mqflag;// 3 计质量标记：0不计量质检，1质检，2计量，3 计量质检
	
	private int kqflag;// 0 跨区标记 0：不跨区，1跨区
	
	private int gflag;// 3 是否进出门 0不限制，1进门，2出门，3进出门 默认3
	
	private int tarehour;// 0 皮重有效期 -1不限制，0一车一皮，>n n小时有效
	
	private int accountstype;// 1 结算方 0：发货 1：收货
	
	private String printsetgross;// '签收' 只计毛打印联，以逗号隔开
	
	private String printsetsuttle;// '计量' 出净重打印联，以逗号隔开
	
	private int minterval;// 0 计量时间间隔 分钟数 0表示不限制
	
	private double deductionunit;// 0 扣重单位 0计量、1发货、2收货
	
	private double deductiontype;// 0 扣重类型 0不扣、1固定值和2录入值
	
	private int isplan;// 0 是否执行计划0-不执行 1-执行
	
	private int isbasket;// 0 是否启用料篮0-启用 1-不启用
	
	private int forcereceive;// 2 是否强制接收 0-不强制接收、1-强制接收
	
	private int qflag;// 4 取样方式：1计毛前取样， 2收货前取样 ，3收货前质检 ，4收货后取样
	
	private int plantimeflag;// 按时间控制预报有效期：0否，1是
	
	private int plancarcountflag;// 按车数控制预报有效期：0否，1是
	
	private int planweightflag;// 按计划量控制预报有效期：0否，1是
	
	private int plancarflag;// 是否限制车号：0否，1是
	
	private int settlementmodes;// 结算方式（1理重，0实重）
	
	private int iscompare;// 是否复磅（0否，1是）
	
	private int dispatchtype;// 发货方式：1发货挑库，2发货不挑库，3不发货不挑库
	
	private double price;// 单价
	
	private int qualityprint;// 质保书打印 0 随车质保 1 不随车质保
	
	private double ovweitghtmin;// 超重重量/比例大于
	
	private double ovweitghtmax;// 超重重量/比例小于
	
	private int issendcar;// 1 是否启用派车 1 启用派车 0 不启用派车
	
	private int isloading;// 1 是否启用装车申请单 0 不启用 1 启用
	
	private int plancountflag;// 按计划数量控制有效期：0否，1是
	
	private int ovweitghttype;// 0 超重类型：0重量，1：比例
	
	private String printsettare;// 只计皮打印联，以逗号隔开
	
	private int grosstimeout;// 24 毛重有效期
	
	private int taretimeout;// 24 皮重有效期 当皮重是1一车一皮时，有效
	
	private int grosstimeoutdo;// 2 皮重超期后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int taretimeoutdo;// 2 毛重超期后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int notstoreoutdo;// 2 无发货确认时，设定要发货，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int notstoreindo;// 2 无收货确认时，设定要发货，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int tareoutdo;// 2 发货皮重超差后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int grossoutdo;// 2 发货毛重超差后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int suttleoutdo;// 2 发货净重超差后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int notentergatedo;// 2 无进门的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int notleavegatedo;// 2 无出门的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int notslampedo;// 2 无取样的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int tareoutup;// 100 发货皮重超差上限值 value=0不允许超，0<value<1百分比控制，value>=1
							// 按千克计算
	private int tareoutdown;// 100 发货皮重超差下限值 value=0不允许超，0<value<1百分比控制，value>=1
							// 按千克计算
	private int grossoutup;// 100 发货毛重超差上限值 value=0不允许超，0<value<1百分比控制，value>=1
							// 按千克计算
	private int grossoutdown;// 100 发货毛重超差下限值
								// value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private int suttleoutup;// 100 发货净重超差上限值 value=0不允许超，0<value<1百分比控制，value>=1
							// 按千克计算
	private int suttleoutdown;// 100 发货净重超差下限值
								// value=0不允许超，0<value<1百分比控制，value>=1 按千克计算
	private int checktaredo;// 2 皮重超差的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int checktarelast;// 100 检查当前皮重与最后一次皮重的差值+-，value<0不检查，value=0不允许超，0<value<1百分比控制，value>=1，按千克计算
	
	private int checktarehistory;// 100 检查当前皮重与历史5次皮重的差值 +-，value<0不检查，value=0不允许超，0<value<1百分比控制，value>=1，按千克计算
	
	private int checktarebasic;// 100 检查当前皮重与基础皮重的差值+-，value<0不检查，value=0不允许超，0<value<1百分比控制，value>=1，按千克计算
	
	private int istaskcode;// 0 是否执行业务号 0-不执行 1-执行
	
	private int maxtare;// 30 最大皮重,用于判断计毛和计皮
	
	private int grossweightout;// 1 判断毛皮时，毛重超差范围 ，高于当前重量-该值，则为毛重 ，否则为皮重 单位吨
	
	private int tareweightout;// 1 判断毛皮时，皮重超差范围 ，高于当前重量+该值，则为毛重 ，否则为皮重 单位吨
	
	private int measuretype;// 0 计量方式类型 0 远程手动 1远程自动 2 现场自助 3现场自动 4自动选择
	
	private int checkgrossinfo;// 1 只计量毛重时，是否需要验证业务信息0 不验证，1验证
	
	private int checktareinfo;// 0 只计量皮重时，是否需要验证业务信息0 不验证，1验证
	
	private int checksuttleinfo;// 1 计量净重时，是否需要验证业务信息0 不验证，1验证
	
	private int isgrosstaskcode;// 1 只计毛时录入业务号 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int isgrossplanid;// 1 只计毛时录入计划号 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int isgrossmaterial;// 1 只计毛时录入货名 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int isgrossship;// 1 只计毛时录入船名 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int isgrosssource;// 1 只计毛时录入供货 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int isgrosstarget;// 1 只计毛时录入收货 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int isgrossbasket;// 0 只计毛时录入料篮 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int istaretaskcode;// 0 只计皮时录入业务号 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int istareplanid;// 0 只计皮时录入计划号 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int istarematerial;// 0 只计皮时录入货名 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int istaresource;// 0 只计皮时录入供货 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int istareship;// 0 只计皮时录入船名 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int istaretarget;// 0 只计皮时录入收货 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int istarebasket;// 0 只计皮时录入料篮 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int issuttletaskcode;// 1 计量净重时录入业务号 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int issuttleplanid;// 1 计量净重时录入计划号 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int issuttlematerial;// 1 计量净重时录入货名 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int issuttlesource;// 1 计量净重时录入供货 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int issuttleship;// 1 计量净重时录入船名 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int issuttletarget;// 1 计量净重时录入收货 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int issuttlebasket;// 0 计量净重时录入料篮 0：不检查\不用录入，1：必须进行录入，2：进行选择
	
	private int regrossdo; // 2 重复只计毛时,0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int retaredo;// 2 重复只计皮时,0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int resuttledo;// 2 重复计量净重时,0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int checkoperatimedo;// 0 检查前一个业务到当前业务的时间超时后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量 默认2
	
	private int checkoperatime;// 0 检查前一个业务到当前业务的时间超时间隔 分钟，默认0
	
	private double deduction2;// 0 扣重值 扣重值从配置参数或者业务点录入 0<value<1百分比控制，value>=1，按千克计算
	
	private String begintime;// 计划开始时间
	
	private String endtime;// 计划结束时间
	
	private int snumber;// 0 当前车计划件/支量
	
	private double suttleapp;// 0 当前车计划重量
	
	private int checkplanweighdo;// 2 计划重量超差的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int planweighout;// 0 计划重量超差上限值 value=0不允许超，0<value<1百分比控制，value>=1，按千克计算
	
	private int checkplancarcountdo;// 3 计划车数超差的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int plancarcountout;// 0 计划车数超差上限值 value=0不允许超，value>=1 车数
	
	private int checkplanmaterialcountdo;//计划件/支超差的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private int planmaterialcountout;// 0 计划件/支数超差上限值 value=0不允许超，value>=1 件数
	
	private int checkplantimeoutdo;// 3 计划超期的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
	
	private String memo1;// 备注1
	
	private String memo2;// 备注2
	
	private String memo3;// 备注3
	
	private String memo4;// 备注4
	
	private String memo5;// 备注5 
	
	private String memo6;// 备注6 
	
	private String memo7;// 备注7 
	
	private String memo8;// 备注8 
	
	private String memo9;// 备注9
	
	private String memo10;// 备注10
	
	private String memo11;// 备注11
	
	private String memo12;// 备注12
	
	private String memo13;// 备注13
	
	private String memo14;// 备注14
	
	private String memo15;// 备注15 疏港计划号
	
	private int issure;// 1 销售部是否确认,0是未确认，1是已确认
	
	private int isinputweightb;// 0 是否允许计量员录入前次重量，0:不检查\不用录入\前边带过来的，1：计量允许录入，且必须进行录入，2：计量允许录入，可进行选择，默认0
	
	private String realsourcename;// 实际发货库房名称
	
	private String realsourcecode;// 实际发货库房编码
	
	private String createman;// 记录人
	
	private String shipplanid;// 疏港计划号
	
	private int ifphflag;// 0 是否扣除配货费 0否 1是
	
	private int reentryflag;// 是否重复进厂:0：不检查，1：进行提示，2，进行选择，3禁止
	private int entryflag;// 进厂标记 0：不检查，1：进行提示，2，进行选择，3禁止
	private int tareflag;// 皮重标记: 0：不检查，1：进行提示，2，进行选择，3禁止
	private int grossflag;// 是否计毛标记: 0：不检查，1：进行提示，2，进行选择，3禁止
	private int sampleflag;// 取样标记 0：不检查，1：进行提示，2，进行选择，3禁止
	private int forceflag;// 强制收货标记 0：不检查，1：进行提示，2，进行选择，3禁止
	private int receiveflag;// 收货标记 0：不检查，1：进行提示，2，进行选择，3禁止
	private int sgrossflag;// 取样计毛标记: 0：不检查，1：进行提示，2，进行选择，3禁止
	private String unitcode;//当前单位
	private String unitname;//当前单位
	private String Processlink;//流程环节

	private String routeid;//路线id

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

	public String getIcid() {
		return icid;
	}

	public void setIcid(String icid) {
		this.icid = icid;
	}

	public String getIctype() {
		return ictype;
	}

	public void setIctype(String ictype) {
		this.ictype = ictype;
	}

	public String getIcno() {
		return icno;
	}

	public void setIcno(String icno) {
		this.icno = icno;
	}

	public String getRfidid() {
		return rfidid;
	}

	public void setRfidid(String rfidid) {
		this.rfidid = rfidid;
	}

	public String getRfidtype() {
		return rfidtype;
	}

	public void setRfidtype(String rfidtype) {
		this.rfidtype = rfidtype;
	}

	public String getRfidno() {
		return rfidno;
	}

	public void setRfidno(String rfidno) {
		this.rfidno = rfidno;
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

	public String getBasket() {
		return basket;
	}

	public void setBasket(String basket) {
		this.basket = basket;
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

	public int getMaterialcount() {
		return materialcount;
	}

	public void setMaterialcount(int materialcount) {
		this.materialcount = materialcount;
	}

	public int getPlanweight() {
		return planweight;
	}

	public void setPlanweight(int planweight) {
		this.planweight = planweight;
	}

	public int getPlanmaterialcount() {
		return planmaterialcount;
	}

	public void setPlanmaterialcount(int planmaterialcount) {
		this.planmaterialcount = planmaterialcount;
	}

	public int getPlancarcount() {
		return plancarcount;
	}

	public void setPlancarcount(int plancarcount) {
		this.plancarcount = plancarcount;
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

	public String getGrossweighgroup() {
		return grossweighgroup;
	}

	public void setGrossweighgroup(String grossweighgroup) {
		this.grossweighgroup = grossweighgroup;
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

	public int getGrossgroupno() {
		return grossgroupno;
	}

	public void setGrossgroupno(int grossgroupno) {
		this.grossgroupno = grossgroupno;
	}

	public int getGrossserial() {
		return grossserial;
	}

	public void setGrossserial(int grossserial) {
		this.grossserial = grossserial;
	}

	public int getGrossspeed() {
		return grossspeed;
	}

	public void setGrossspeed(int grossspeed) {
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

	public String getTareweighgroup() {
		return tareweighgroup;
	}

	public void setTareweighgroup(String tareweighgroup) {
		this.tareweighgroup = tareweighgroup;
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

	public int getTaregroupno() {
		return taregroupno;
	}

	public void setTaregroupno(int taregroupno) {
		this.taregroupno = taregroupno;
	}

	public int getTareserial() {
		return tareserial;
	}

	public void setTareserial(int tareserial) {
		this.tareserial = tareserial;
	}

	public String getTarelogid() {
		return tarelogid;
	}

	public void setTarelogid(String tarelogid) {
		this.tarelogid = tarelogid;
	}

	public int getTarespeed() {
		return tarespeed;
	}

	public void setTarespeed(int tarespeed) {
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

	public String getEntertime() {
		return entertime;
	}

	public void setEntertime(String entertime) {
		this.entertime = entertime;
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

	public String getLeavetime() {
		return leavetime;
	}

	public void setLeavetime(String leavetime) {
		this.leavetime = leavetime;
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

	public String getGrossweighgroupb() {
		return grossweighgroupb;
	}

	public void setGrossweighgroupb(String grossweighgroupb) {
		this.grossweighgroupb = grossweighgroupb;
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

	public int getGrossgroupnob() {
		return grossgroupnob;
	}

	public void setGrossgroupnob(int grossgroupnob) {
		this.grossgroupnob = grossgroupnob;
	}

	public int getGrossserialb() {
		return grossserialb;
	}

	public void setGrossserialb(int grossserialb) {
		this.grossserialb = grossserialb;
	}

	public int getGrossspeedb() {
		return grossspeedb;
	}

	public void setGrossspeedb(int grossspeedb) {
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

	public String getTareweighgroupb() {
		return tareweighgroupb;
	}

	public void setTareweighgroupb(String tareweighgroupb) {
		this.tareweighgroupb = tareweighgroupb;
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

	public int getTaregroupnob() {
		return taregroupnob;
	}

	public void setTaregroupnob(int taregroupnob) {
		this.taregroupnob = taregroupnob;
	}

	public int getTareserialb() {
		return tareserialb;
	}

	public void setTareserialb(int tareserialb) {
		this.tareserialb = tareserialb;
	}

	public String getTarelogidb() {
		return tarelogidb;
	}

	public void setTarelogidb(String tarelogidb) {
		this.tarelogidb = tarelogidb;
	}

	public int getTarespeedb() {
		return tarespeedb;
	}

	public void setTarespeedb(int tarespeedb) {
		this.tarespeedb = tarespeedb;
	}

	public String getBatchcode() {
		return batchcode;
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
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

	public int getDflag() {
		return dflag;
	}

	public void setDflag(int dflag) {
		this.dflag = dflag;
	}

	public int getBflag() {
		return bflag;
	}

	public void setBflag(int bflag) {
		this.bflag = bflag;
	}

	public int getMflag() {
		return mflag;
	}

	public void setMflag(int mflag) {
		this.mflag = mflag;
	}

	public String getUsermemo() {
		return usermemo;
	}

	public void setUsermemo(String usermemo) {
		this.usermemo = usermemo;
	}

	public String getSysmemo() {
		return sysmemo;
	}

	public void setSysmemo(String sysmemo) {
		this.sysmemo = sysmemo;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public int getValidflag() {
		return validflag;
	}

	public void setValidflag(int validflag) {
		this.validflag = validflag;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public int getMstate() {
		return mstate;
	}

	public void setMstate(int mstate) {
		this.mstate = mstate;
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

	public int getMinterval() {
		return minterval;
	}

	public void setMinterval(int minterval) {
		this.minterval = minterval;
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

	public int getQflag() {
		return qflag;
	}

	public void setQflag(int qflag) {
		this.qflag = qflag;
	}

	public int getPlantimeflag() {
		return plantimeflag;
	}

	public void setPlantimeflag(int plantimeflag) {
		this.plantimeflag = plantimeflag;
	}

	public int getPlancarcountflag() {
		return plancarcountflag;
	}

	public void setPlancarcountflag(int plancarcountflag) {
		this.plancarcountflag = plancarcountflag;
	}

	public int getPlanweightflag() {
		return planweightflag;
	}

	public void setPlanweightflag(int planweightflag) {
		this.planweightflag = planweightflag;
	}

	public int getPlancarflag() {
		return plancarflag;
	}

	public void setPlancarflag(int plancarflag) {
		this.plancarflag = plancarflag;
	}

	public int getSettlementmodes() {
		return settlementmodes;
	}

	public void setSettlementmodes(int settlementmodes) {
		this.settlementmodes = settlementmodes;
	}

	public int getIscompare() {
		return iscompare;
	}

	public void setIscompare(int iscompare) {
		this.iscompare = iscompare;
	}

	public int getDispatchtype() {
		return dispatchtype;
	}

	public void setDispatchtype(int dispatchtype) {
		this.dispatchtype = dispatchtype;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQualityprint() {
		return qualityprint;
	}

	public void setQualityprint(int qualityprint) {
		this.qualityprint = qualityprint;
	}

	public double getOvweitghtmin() {
		return ovweitghtmin;
	}

	public void setOvweitghtmin(double ovweitghtmin) {
		this.ovweitghtmin = ovweitghtmin;
	}

	public double getOvweitghtmax() {
		return ovweitghtmax;
	}

	public void setOvweitghtmax(double ovweitghtmax) {
		this.ovweitghtmax = ovweitghtmax;
	}

	public int getIssendcar() {
		return issendcar;
	}

	public void setIssendcar(int issendcar) {
		this.issendcar = issendcar;
	}

	public int getIsloading() {
		return isloading;
	}

	public void setIsloading(int isloading) {
		this.isloading = isloading;
	}

	public int getPlancountflag() {
		return plancountflag;
	}

	public void setPlancountflag(int plancountflag) {
		this.plancountflag = plancountflag;
	}

	public int getOvweitghttype() {
		return ovweitghttype;
	}

	public void setOvweitghttype(int ovweitghttype) {
		this.ovweitghttype = ovweitghttype;
	}

	public String getPrintsettare() {
		return printsettare;
	}

	public void setPrintsettare(String printsettare) {
		this.printsettare = printsettare;
	}

	public int getGrosstimeout() {
		return grosstimeout;
	}

	public void setGrosstimeout(int grosstimeout) {
		this.grosstimeout = grosstimeout;
	}

	public int getTaretimeout() {
		return taretimeout;
	}

	public void setTaretimeout(int taretimeout) {
		this.taretimeout = taretimeout;
	}

	public int getGrosstimeoutdo() {
		return grosstimeoutdo;
	}

	public void setGrosstimeoutdo(int grosstimeoutdo) {
		this.grosstimeoutdo = grosstimeoutdo;
	}

	public int getTaretimeoutdo() {
		return taretimeoutdo;
	}

	public void setTaretimeoutdo(int taretimeoutdo) {
		this.taretimeoutdo = taretimeoutdo;
	}

	public int getNotstoreoutdo() {
		return notstoreoutdo;
	}

	public void setNotstoreoutdo(int notstoreoutdo) {
		this.notstoreoutdo = notstoreoutdo;
	}

	public int getNotstoreindo() {
		return notstoreindo;
	}

	public void setNotstoreindo(int notstoreindo) {
		this.notstoreindo = notstoreindo;
	}

	public int getTareoutdo() {
		return tareoutdo;
	}

	public void setTareoutdo(int tareoutdo) {
		this.tareoutdo = tareoutdo;
	}

	public int getGrossoutdo() {
		return grossoutdo;
	}

	public void setGrossoutdo(int grossoutdo) {
		this.grossoutdo = grossoutdo;
	}

	public int getSuttleoutdo() {
		return suttleoutdo;
	}

	public void setSuttleoutdo(int suttleoutdo) {
		this.suttleoutdo = suttleoutdo;
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

	public int getTareoutup() {
		return tareoutup;
	}

	public void setTareoutup(int tareoutup) {
		this.tareoutup = tareoutup;
	}

	public int getTareoutdown() {
		return tareoutdown;
	}

	public void setTareoutdown(int tareoutdown) {
		this.tareoutdown = tareoutdown;
	}

	public int getGrossoutup() {
		return grossoutup;
	}

	public void setGrossoutup(int grossoutup) {
		this.grossoutup = grossoutup;
	}

	public int getGrossoutdown() {
		return grossoutdown;
	}

	public void setGrossoutdown(int grossoutdown) {
		this.grossoutdown = grossoutdown;
	}

	public int getSuttleoutup() {
		return suttleoutup;
	}

	public void setSuttleoutup(int suttleoutup) {
		this.suttleoutup = suttleoutup;
	}

	public int getSuttleoutdown() {
		return suttleoutdown;
	}

	public void setSuttleoutdown(int suttleoutdown) {
		this.suttleoutdown = suttleoutdown;
	}

	public int getChecktaredo() {
		return checktaredo;
	}

	public void setChecktaredo(int checktaredo) {
		this.checktaredo = checktaredo;
	}

	public int getChecktarelast() {
		return checktarelast;
	}

	public void setChecktarelast(int checktarelast) {
		this.checktarelast = checktarelast;
	}

	public int getChecktarehistory() {
		return checktarehistory;
	}

	public void setChecktarehistory(int checktarehistory) {
		this.checktarehistory = checktarehistory;
	}

	public int getChecktarebasic() {
		return checktarebasic;
	}

	public void setChecktarebasic(int checktarebasic) {
		this.checktarebasic = checktarebasic;
	}

	public int getIstaskcode() {
		return istaskcode;
	}

	public void setIstaskcode(int istaskcode) {
		this.istaskcode = istaskcode;
	}

	public int getMaxtare() {
		return maxtare;
	}

	public void setMaxtare(int maxtare) {
		this.maxtare = maxtare;
	}

	public int getGrossweightout() {
		return grossweightout;
	}

	public void setGrossweightout(int grossweightout) {
		this.grossweightout = grossweightout;
	}

	public int getTareweightout() {
		return tareweightout;
	}

	public void setTareweightout(int tareweightout) {
		this.tareweightout = tareweightout;
	}

	public int getMeasuretype() {
		return measuretype;
	}

	public void setMeasuretype(int measuretype) {
		this.measuretype = measuretype;
	}

	public int getCheckgrossinfo() {
		return checkgrossinfo;
	}

	public void setCheckgrossinfo(int checkgrossinfo) {
		this.checkgrossinfo = checkgrossinfo;
	}

	public int getChecktareinfo() {
		return checktareinfo;
	}

	public void setChecktareinfo(int checktareinfo) {
		this.checktareinfo = checktareinfo;
	}

	public int getChecksuttleinfo() {
		return checksuttleinfo;
	}

	public void setChecksuttleinfo(int checksuttleinfo) {
		this.checksuttleinfo = checksuttleinfo;
	}

	public int getIsgrosstaskcode() {
		return isgrosstaskcode;
	}

	public void setIsgrosstaskcode(int isgrosstaskcode) {
		this.isgrosstaskcode = isgrosstaskcode;
	}

	public int getIsgrossplanid() {
		return isgrossplanid;
	}

	public void setIsgrossplanid(int isgrossplanid) {
		this.isgrossplanid = isgrossplanid;
	}

	public int getIsgrossmaterial() {
		return isgrossmaterial;
	}

	public void setIsgrossmaterial(int isgrossmaterial) {
		this.isgrossmaterial = isgrossmaterial;
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

	public int getIsgrossbasket() {
		return isgrossbasket;
	}

	public void setIsgrossbasket(int isgrossbasket) {
		this.isgrossbasket = isgrossbasket;
	}

	public int getIstaretaskcode() {
		return istaretaskcode;
	}

	public void setIstaretaskcode(int istaretaskcode) {
		this.istaretaskcode = istaretaskcode;
	}

	public int getIstareplanid() {
		return istareplanid;
	}

	public void setIstareplanid(int istareplanid) {
		this.istareplanid = istareplanid;
	}

	public int getIstarematerial() {
		return istarematerial;
	}

	public void setIstarematerial(int istarematerial) {
		this.istarematerial = istarematerial;
	}

	public int getIstaresource() {
		return istaresource;
	}

	public void setIstaresource(int istaresource) {
		this.istaresource = istaresource;
	}

	public int getIstareship() {
		return istareship;
	}

	public void setIstareship(int istareship) {
		this.istareship = istareship;
	}

	public int getIstaretarget() {
		return istaretarget;
	}

	public void setIstaretarget(int istaretarget) {
		this.istaretarget = istaretarget;
	}

	public int getIstarebasket() {
		return istarebasket;
	}

	public void setIstarebasket(int istarebasket) {
		this.istarebasket = istarebasket;
	}

	public int getIssuttletaskcode() {
		return issuttletaskcode;
	}

	public void setIssuttletaskcode(int issuttletaskcode) {
		this.issuttletaskcode = issuttletaskcode;
	}

	public int getIssuttleplanid() {
		return issuttleplanid;
	}

	public void setIssuttleplanid(int issuttleplanid) {
		this.issuttleplanid = issuttleplanid;
	}

	public int getIssuttlematerial() {
		return issuttlematerial;
	}

	public void setIssuttlematerial(int issuttlematerial) {
		this.issuttlematerial = issuttlematerial;
	}

	public int getIssuttlesource() {
		return issuttlesource;
	}

	public void setIssuttlesource(int issuttlesource) {
		this.issuttlesource = issuttlesource;
	}

	public int getIssuttleship() {
		return issuttleship;
	}

	public void setIssuttleship(int issuttleship) {
		this.issuttleship = issuttleship;
	}

	public int getIssuttletarget() {
		return issuttletarget;
	}

	public void setIssuttletarget(int issuttletarget) {
		this.issuttletarget = issuttletarget;
	}

	public int getIssuttlebasket() {
		return issuttlebasket;
	}

	public void setIssuttlebasket(int issuttlebasket) {
		this.issuttlebasket = issuttlebasket;
	}

	public int getRegrossdo() {
		return regrossdo;
	}

	public void setRegrossdo(int regrossdo) {
		this.regrossdo = regrossdo;
	}

	public int getRetaredo() {
		return retaredo;
	}

	public void setRetaredo(int retaredo) {
		this.retaredo = retaredo;
	}

	public int getResuttledo() {
		return resuttledo;
	}

	public void setResuttledo(int resuttledo) {
		this.resuttledo = resuttledo;
	}

	public int getCheckoperatimedo() {
		return checkoperatimedo;
	}

	public void setCheckoperatimedo(int checkoperatimedo) {
		this.checkoperatimedo = checkoperatimedo;
	}

	public int getCheckoperatime() {
		return checkoperatime;
	}

	public void setCheckoperatime(int checkoperatime) {
		this.checkoperatime = checkoperatime;
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

	public int getSnumber() {
		return snumber;
	}

	public void setSnumber(int snumber) {
		this.snumber = snumber;
	}

	public double getSuttleapp() {
		return suttleapp;
	}

	public void setSuttleapp(double suttleapp) {
		this.suttleapp = suttleapp;
	}

	public int getCheckplanweighdo() {
		return checkplanweighdo;
	}

	public void setCheckplanweighdo(int checkplanweighdo) {
		this.checkplanweighdo = checkplanweighdo;
	}

	public int getPlanweighout() {
		return planweighout;
	}

	public void setPlanweighout(int planweighout) {
		this.planweighout = planweighout;
	}

	public int getCheckplancarcountdo() {
		return checkplancarcountdo;
	}

	public void setCheckplancarcountdo(int checkplancarcountdo) {
		this.checkplancarcountdo = checkplancarcountdo;
	}

	public int getPlancarcountout() {
		return plancarcountout;
	}

	public void setPlancarcountout(int plancarcountout) {
		this.plancarcountout = plancarcountout;
	}

	public int getCheckplanmaterialcountdo() {
		return checkplanmaterialcountdo;
	}

	public void setCheckplanmaterialcountdo(int checkplanmaterialcountdo) {
		this.checkplanmaterialcountdo = checkplanmaterialcountdo;
	}

	public int getPlanmaterialcountout() {
		return planmaterialcountout;
	}

	public void setPlanmaterialcountout(int planmaterialcountout) {
		this.planmaterialcountout = planmaterialcountout;
	}

	public int getCheckplantimeoutdo() {
		return checkplantimeoutdo;
	}

	public void setCheckplantimeoutdo(int checkplantimeoutdo) {
		this.checkplantimeoutdo = checkplantimeoutdo;
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

	public int getIssure() {
		return issure;
	}

	public void setIssure(int issure) {
		this.issure = issure;
	}

	public int getIsinputweightb() {
		return isinputweightb;
	}

	public void setIsinputweightb(int isinputweightb) {
		this.isinputweightb = isinputweightb;
	}

	public String getRealsourcename() {
		return realsourcename;
	}

	public void setRealsourcename(String realsourcename) {
		this.realsourcename = realsourcename;
	}

	public String getRealsourcecode() {
		return realsourcecode;
	}

	public void setRealsourcecode(String realsourcecode) {
		this.realsourcecode = realsourcecode;
	}

	public String getCreateman() {
		return createman;
	}

	public void setCreateman(String createman) {
		this.createman = createman;
	}

	public String getShipplanid() {
		return shipplanid;
	}

	public void setShipplanid(String shipplanid) {
		this.shipplanid = shipplanid;
	}

	public int getIfphflag() {
		return ifphflag;
	}

	public void setIfphflag(int ifphflag) {
		this.ifphflag = ifphflag;
	}
	public int getReentryflag() {
		return reentryflag;
	}

	public void setReentryflag(int reentryflag) {
		this.reentryflag = reentryflag;
	}

	public int getEntryflag() {
		return entryflag;
	}

	public void setEntryflag(int entryflag) {
		this.entryflag = entryflag;
	}

	public int getTareflag() {
		return tareflag;
	}

	public void setTareflag(int tareflag) {
		this.tareflag = tareflag;
	}

	public int getGrossflag() {
		return grossflag;
	}

	public void setGrossflag(int grossflag) {
		this.grossflag = grossflag;
	}

	public int getSampleflag() {
		return sampleflag;
	}

	public void setSampleflag(int sampleflag) {
		this.sampleflag = sampleflag;
	}

	public int getForceflag() {
		return forceflag;
	}

	public void setForceflag(int forceflag) {
		this.forceflag = forceflag;
	}

	public int getReceiveflag() {
		return receiveflag;
	}

	public void setReceiveflag(int receiveflag) {
		this.receiveflag = receiveflag;
	}

	public int getSgrossflag() {
		return sgrossflag;
	}

	public void setSgrossflag(int sgrossflag) {
		this.sgrossflag = sgrossflag;
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

	public String getProcesslink() {
		return Processlink;
	}

	public void setProcesslink(String processlink) {
		Processlink = processlink;
	}

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
  
	
}
