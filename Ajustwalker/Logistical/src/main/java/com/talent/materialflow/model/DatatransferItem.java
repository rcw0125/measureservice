package com.talent.materialflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("datatransferitem")
@Entity
@Table(name="P_DATATRANSFERITEM_T",uniqueConstraints={@UniqueConstraint(columnNames={"fid","dbcolumn","datatable"})})
public class DatatransferItem extends BaseModel{
	
	private static final long serialVersionUID = -5975557183219474508L;

	@Fields(name="数据字段",sn=1)
	@Column(name="dbcolumn")
	private String dbcolumn = "";
	
	@Fields(name="接口字段",sn=2)
	@Column(name="icolumn")
	private String icolumn = "";
	
	@Fields(name="接口字段",sn=3)
	@Column(name="icolumndesc")
	private String icolumndesc = "";
	
	@Fields(name="数据库表",sn=4)
	@Column(name="datatable")
	private String datatable = "main";

	public String getDbcolumn() {
		return dbcolumn;
	}

	public void setDbcolumn(String dbcolumn) {
		this.dbcolumn = dbcolumn;
	}

	public String getIcolumn() {
		return icolumn;
	}

	public void setIcolumn(String icolumn) {
		this.icolumn = icolumn;
	}

	public String getDatatable() {
		return datatable;
	}

	public void setDatatable(String datatable) {
		this.datatable = datatable;
	}

	public String getIcolumndesc() {
		return icolumndesc;
	}

	public void setIcolumndesc(String icolumndesc) {
		this.icolumndesc = icolumndesc;
	}
}
