package com.talent.base.model;

public abstract class DynamicBean {
	
	protected String beanName;  
	  
    public DynamicBean(String beanName) {  
        this.beanName = beanName;  
    }  
  
    public String getBeanName() {  
        return beanName;  
    }  
  
    public void setBeanName(String beanName) {  
        this.beanName = beanName;  
    }  
}
