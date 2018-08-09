package com.talent.measure.model;

import java.io.Serializable;

public class DeviceConfig implements Serializable{
	
	private static final long serialVersionUID = -4069156930685040060L;

	private int id = 0;
	
	private String deviceName = ""; //红外对射、打印机、终端机
	
	private String ctrlType = "";   //业务类型、物料大类、物料名称
	
	private String ctrlGoal = "";   //红外对射：启用检查，不启用；打印机：打印（模板）、不打印；终端机：自助计量、远程计量、强制远程、强制自助
	
	private String configDetail = ""; //配置详情 
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getCtrlType() {
		return ctrlType;
	}

	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}

	public String getCtrlGoal() {
		return ctrlGoal;
	}

	public void setCtrlGoal(String ctrlGoal) {
		this.ctrlGoal = ctrlGoal;
	}

	public String getConfigDetail() {
		return configDetail;
	}

	public void setConfigDetail(String configDetail) {
		this.configDetail = configDetail;
	}
}