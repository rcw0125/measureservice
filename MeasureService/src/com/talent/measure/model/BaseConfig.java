package com.talent.measure.model;

import java.io.Serializable;

public class BaseConfig implements Serializable{
	
	private static final long serialVersionUID = -5632202468581308811L;

	private int id = 0;
	
	private String fieldName = "";
	
	private String fieldValue = "";
	
	private String memoryMointor = "禁用";
    
    private String cpuMointor = "禁用";
    
    private String weightMointor = "禁用";
    
    private String alertMointor = "禁用";
    
    private String clusters = "127.0.0.1";
	
    private String adminEmail = "talent_mail_user@126.com";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getMemoryMointor() {
		return memoryMointor;
	}

	public void setMemoryMointor(String memoryMointor) {
		this.memoryMointor = memoryMointor;
	}

	public String getCpuMointor() {
		return cpuMointor;
	}

	public void setCpuMointor(String cpuMointor) {
		this.cpuMointor = cpuMointor;
	}

	public String getAlertMointor() {
		return alertMointor;
	}

	public void setAlertMointor(String alertMointor) {
		this.alertMointor = alertMointor;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getWeightMointor() {
		return weightMointor;
	}

	public void setWeightMointor(String weightMointor) {
		this.weightMointor = weightMointor;
	}

	public String getClusters() {
		return clusters;
	}

	public void setClusters(String clusters) {
		this.clusters = clusters;
	}
}