package com.talent.materialflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("customer")
@Entity
@Table(name="B_CUSTOMER_T",uniqueConstraints={@UniqueConstraint(columnNames={"customercode","customertype"})})
public class Customer extends BaseModel{
	
	private static final long serialVersionUID = 6404867366637412815L;

	@Fields(name="类型",sn=1)
	@Column(name="CUSTOMERTYPE")
	private String customertype = "";
	
	@Fields(name="编码",sn=2)
	@Column(name="CUSTOMERCODE")
	private String customercode = "";
	
	@Fields(name="名称",sn=3)
	@Column(name="CUSTOMERNAME")
	private String customername = "";
	
	@Fields(name="拼音缩写",sn=4)
	@Column(name="QUERYWORD")
	private String queryword = "";
	
	@Fields(name="ERP编码",sn=5)
	@Column(name="ERPCODE")
	private String erpcode = "";
	
	@Fields(name="客户区域",sn=6)
	@Column(name="CUSTOMERAREA")
	private String customerarea = "";
	
	@Fields(name="个人电话",sn=7)
	@Column(name="TELE")
	private String tele = "";
	
	@Fields(name="工作地点",sn=8)
	@Column(name="ADDRESS")
	private String address = "";
	
	@Fields(name="工作电话",sn=9)
	@Column(name="ADDOFFICE")
	private String addoffice = "";
	
	private int types;
	
	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getQueryword() {
		return queryword;
	}

	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}

	public String getErpcode() {
		return erpcode;
	}

	public void setErpcode(String erpcode) {
		this.erpcode = erpcode;
	}

	public String getCustomerarea() {
		return customerarea;
	}

	public void setCustomerarea(String customerarea) {
		this.customerarea = customerarea;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddoffice() {
		return addoffice;
	}

	public void setAddoffice(String addoffice) {
		this.addoffice = addoffice;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}
}
