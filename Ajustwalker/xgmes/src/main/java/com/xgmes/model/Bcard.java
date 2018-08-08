package com.xgmes.model;



import javax.persistence.Column;
import org.apache.ibatis.type.Alias;
import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("bcard")
public class Bcard extends BaseModel{
	
	private static final long serialVersionUID = 5745055994794401597L;

	@Fields(name = "卡号")
	@Column(name = "cardid")
	private String cardid; 
	
	@Fields(name = "类型")
	@Column(name = "cardtype")
	private String cardtype;//类型(0、1)、0--临时卡、1--固定卡
	
	@Fields(name = "印刷编号")
	@Column(name = "cardno")
	private String cardno;     
	
	@Fields(name = "卡类型")
	@Column(name = "cardclass")
	private String cardclass;//卡类型，0：ic卡，1：rfid卡
	
	@Fields(name = "车号")
	@Column(name = "carno")
	private String carno;  
	
	@Fields(name = "司机编号")
	@Column(name = "drivercode")
	private String drivercode;//领卡人司机编码 ,如车辆档案没有建立,可自动建立车辆档案,为车主信息
	
	@Fields(name = "司机")
	@Column(name = "driver")
	private String driver;  
	
	@Fields(name = "发卡人编码")
	@Column(name = "frommancode")
	private String frommancode;   
	
	@Fields(name = "发卡人")
	@Column(name = "fromman")
	private String fromman;   
	
	@Fields(name = "发卡时间")
	@Column(name = "fromdate")
	private String fromdate;     
	
	@Fields(name = "押金")
	@Column(name = "deposit")
	private String deposit;   
	
	@Fields(name = "退卡人")
	@Column(name = "backman")
	private String backman;   
	
	@Fields(name = "退卡人编码")
	@Column(name = "backmancode")
	private String backmancode;     
	
	@Fields(name = "退卡时间")
	@Column(name = "backdate")
	private String backdate;   
	
	@Fields(name = " 领卡单位编码")
	@Column(name = "unitcode")
	private String unitcode;    
	
	@Fields(name = "领卡单位")
	@Column(name = "unitname")
	private String unitname;    
	
	@Fields(name = "单位领卡时间")
	@Column(name = "unittime")
	private String unittime;   
	
	@Fields(name = "发卡人")
	@Column(name = "unitman")
	private String unitman;     // 单位领卡发卡人
	
	@Fields(name = "车队编码")
	@Column(name = "motorcadecode")
	private String motorcadecode;
	
	@Fields(name = "车队名称")
	@Column(name = "motorcadename")
	private String motorcadename;
	
	@Fields(name = "载重量")
	@Column(name = "capacity")
	private String capacity;    
	
	@Fields(name = "车辆类型")
	@Column(name = "cartype")
	private String cartype;    //车辆类型 1业务车号 0社会车辆
	
	@Fields(name = "RFID卡号")
	private String rfidno;
	
	private String begintime;
	
	private String endtime;
	
	private String beginperiod;//开始时段
	
	private String endperiod;//结束时段
	
	private String flag="0"; //0 门岗只允许发放临时卡，业务车辆
	
	private String updatedate;
	
	private String updateman;
	
	private String matchid;
	
	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getUpdateman() {
		return updateman;
	}

	public void setUpdateman(String updateman) {
		this.updateman = updateman;
	}

	public String getCardid() {
		return cardid;
	}
	
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	
	public String getCardtype() {
		return cardtype;
	}
	
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	
	public String getCardno() {
		return cardno;
	}
	
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	
	public String getCardclass() {
		return cardclass;
	}
	
	public void setCardclass(String cardclass) {
		this.cardclass = cardclass;
	}
	
	public String getCarno() {
		return carno;
	}
	
	public void setCarno(String carno) {
		this.carno = carno;
	}
	
	public String getDrivercode() {
		return drivercode;
	}
	
	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}
	
	public String getDriver() {
		return driver;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	public String getFrommancode() {
		return frommancode;
	}
	
	public void setFrommancode(String frommancode) {
		this.frommancode = frommancode;
	}
	
	public String getFromman() {
		return fromman;
	}
	
	public void setFromman(String fromman) {
		this.fromman = fromman;
	}
	
	public String getFromdate() {
		return fromdate;
	}
	
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	
	public String getDeposit() {
		return deposit;
	}
	
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	
	public String getBackman() {
		return backman;
	}
	
	public void setBackman(String backman) {
		this.backman = backman;
	}
	
	public String getBackmancode() {
		return backmancode;
	}
	
	public void setBackmancode(String backmancode) {
		this.backmancode = backmancode;
	}
	
	public String getBackdate() {
		return backdate;
	}
	
	public void setBackdate(String backdate) {
		this.backdate = backdate;
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
	
	public String getUnittime() {
		return unittime;
	}
	
	public void setUnittime(String unittime) {
		this.unittime = unittime;
	}
	
	public String getUnitman() {
		return unitman;
	}
	
	public void setUnitman(String unitman) {
		this.unitman = unitman;
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
	
	public String getCapacity() {
		return capacity;
	}
	
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	public String getCartype() {
		return cartype;
	}
	
	public void setCartype(String cartype) {
		this.cartype = cartype;
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

	public String getBeginperiod() {
		return beginperiod;
	}

	public void setBeginperiod(String beginperiod) {
		this.beginperiod = beginperiod;
	}

	public String getEndperiod() {
		return endperiod;
	}

	public void setEndperiod(String endperiod) {
		this.endperiod = endperiod;
	}

	public String getRfidno() {
		return rfidno;
	}

	public void setRfidno(String rfidno) {
		this.rfidno = rfidno;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
	
	
}
