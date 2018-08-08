package com.talent.materialflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.ibatis.type.Alias;

import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("folder")
@Entity
@Table(name="B_FOLDER_T",uniqueConstraints={@UniqueConstraint(columnNames={"foldercode"})})
public class Folder extends BaseModel{
	
	private static final long serialVersionUID = -1847387127274708806L;

	@Fields(name="父类编码",sn=1)
	@Column(name="REFFOLDERCODE")
	private String reffoldercode = "";
	
	@Fields(name="物料大类编码",sn=2)
	@Column(name="FOLDERCODE")
	private String foldercode = "";
	
	@Fields(name="物料大类名称",sn=3)
	@Column(name="FOLDERNAME")
	private String foldername = "";
	
	@Fields(name="拼音缩写",sn=4)
	@Column(name="QUERYWORD")
	private String queryword = "";
	
	@Fields(name="作业路线",needFormat=true,sn=5)
	@Column(name="routeid")
	private long routeid = 0;
	
	@Fields(name="是否计量",sn=4)
	@Column(name="isormeasure")
	private int isormeasure = -1;
	
	@Fields(name="是否监秤",sn=4)
	@Column(name="isormonitor")
	private int isormonitor = -1;
	
	@Fields(name="是否启用rfid",sn=4)
	@Column(name="isorrfid")
	private int isorrfid = -1;

	public String getReffoldercode() {
		return reffoldercode;
	}

	public void setReffoldercode(String reffoldercode) {
		this.reffoldercode = reffoldercode;
	}

	public String getFoldercode() {
		return foldercode;
	}

	public void setFoldercode(String foldercode) {
		this.foldercode = foldercode;
	}

	public String getFoldername() {
		return foldername;
	}

	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}

	public String getQueryword() {
		return queryword;
	}

	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}

	public long getRouteid() {
		return routeid;
	}

	public void setRouteid(long routeid) {
		this.routeid = routeid;
	}

	public int getIsormeasure() {
		return isormeasure;
	}

	public void setIsormeasure(int isormeasure) {
		this.isormeasure = isormeasure;
	}

	public int getIsormonitor() {
		return isormonitor;
	}

	public void setIsormonitor(int isormonitor) {
		this.isormonitor = isormonitor;
	}

	public int getIsorrfid() {
		return isorrfid;
	}

	public void setIsorrfid(int isorrfid) {
		this.isorrfid = isorrfid;
	}
	
}
