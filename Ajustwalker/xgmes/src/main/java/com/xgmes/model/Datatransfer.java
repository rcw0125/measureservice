package com.xgmes.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.ibatis.type.Alias;
import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("datatransfer")
@Entity
@Table(name="P_DATATRANSFER_T",uniqueConstraints={@UniqueConstraint(columnNames={"iname","icode"})})
public class Datatransfer extends BaseModel{
	
	private static final long serialVersionUID = 119304809970147726L;

	@Fields(name="接口名称",sn=1)
	@Column(name="iname")
	private String iname = "";
	
	@Fields(name="接口编码",sn=2)
	@Column(name="icode")
	private String icode = "";
	
	@Fields(name="接口类型",sn=3)
	@Column(name="itype")
	private String itype = "xml";
	
	@Fields(name="主表地址",sn=4)
	@Column(name="maintabledata")
	private String maintabledata = "";
	
	@Fields(name="主表名称",sn=5)
	@Column(name="maintable")
	private String maintable = "";
	
	@Fields(name="子表地址",sn=6)
	@Column(name="itemtabledata")
	private String itemtabledata = "";
	
	@Fields(name="子表名称",sn=7)
	@Column(name="itemtable")
	private String itemtable = "";
	
	@Fields(name="操作类型",sn=8)
	@Column(name="inorout")
	private String inorout = "in";
	
	@Fields(name="主键字段",sn=9)
	@Column(name="idfield")
	private String idfield = "";
	
	@Fields(name="主表主键路径",sn=10)
	@Column(name="idpath")
	private String idpath = "";
	
	@Fields(name="子表主键",sn=11)
	@Column(name="subidfield")
	private String subidfield = "";
	
	@Fields(name="子表主键路径",sn=12)
	@Column(name="subidpath")
	private String subidpath = "";
	
	@Fields(name="是否启用",sn=13,needFormat=true)
	@Column(name="serviceing")
	private String serviceing = "1";
	
	@OneToMany(cascade={CascadeType.ALL},targetEntity=DatatransferItem.class,fetch=FetchType.EAGER)
	@JoinColumn(name="fid",insertable=false,updatable=false)
	private List<DatatransferItem> datatransferItems = new ArrayList<DatatransferItem>();

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getIcode() {
		return icode;
	}

	public void setIcode(String icode) {
		this.icode = icode;
	}

	public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}

	public String getMaintabledata() {
		return maintabledata;
	}

	public void setMaintabledata(String maintabledata) {
		this.maintabledata = maintabledata;
	}

	public String getMaintable() {
		return maintable;
	}

	public void setMaintable(String maintable) {
		this.maintable = maintable;
	}

	public String getItemtabledata() {
		return itemtabledata;
	}

	public void setItemtabledata(String itemtabledata) {
		this.itemtabledata = itemtabledata;
	}

	public String getItemtable() {
		return itemtable;
	}

	public void setItemtable(String itemtable) {
		this.itemtable = itemtable;
	}

	public String getInorout() {
		return inorout;
	}

	public void setInorout(String inorout) {
		this.inorout = inorout;
	}

	public String getServiceing() {
		return serviceing;
	}

	public void setServiceing(String serviceing) {
		this.serviceing = serviceing;
	}

	public List<DatatransferItem> getDatatransferItems() {
		return datatransferItems;
	}

	public void setDatatransferItems(List<DatatransferItem> datatransferItems) {
		this.datatransferItems = datatransferItems;
	}

	public String getIdfield() {
		return idfield;
	}

	public void setIdfield(String idfield) {
		this.idfield = idfield;
	}

	public String getIdpath() {
		return idpath;
	}

	public void setIdpath(String idpath) {
		this.idpath = idpath;
	}

	public String getSubidfield() {
		return subidfield;
	}

	public void setSubidfield(String subidfield) {
		this.subidfield = subidfield;
	}

	public String getSubidpath() {
		return subidpath;
	}

	public void setSubidpath(String subidpath) {
		this.subidpath = subidpath;
	}
}
