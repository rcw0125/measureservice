package com.talent.materialflow.model;

import javax.persistence.Column;
import org.apache.ibatis.type.Alias;
import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("document")
public class Documenttype extends BaseModel {

	private static final long serialVersionUID = 6429975877711552377L;
	
	@Fields(name = "单据编码")
	@Column(name = "documentcode")
	private String documentcode;
	
	@Fields(name = "单据名称")
	@Column(name = "documentname")
	private String documentname;
	
	@Fields(name = "车辆流向")
	@Column(name = "materiallflow")
	private String materiallflow;
	
	@Fields(name = "作业路线编码")
	@Column(name = "worklinecode")
	private String worklinecode;
	
	@Fields(name = "作业路线名称")
	@Column(name = "worklinename")
	private String worklinename;
	
	@Fields(name = "原始单据编码")
	@Column(name = "fdocumentcode")
	private String fdocumentcode;
	
	@Fields(name = "原始单据名称")
	@Column(name = "fdocumentname")
	private String fdocumentname;
	
	@Fields(name = "审核等级")
	@Column(name = "auditlevel")
	private String auditlevel;
	
	@Fields(name = "创建人编码")
	@Column(name = "createoperacode")
	private String createoperacode;
	
	@Column(name = "text")
	private String text;

	public String getDocumentcode() {
		return documentcode;
	}
	
	public void setDocumentcode(String documentcode) {
		this.documentcode = documentcode;
	}
	
	public String getDocumentname() {
		return documentname;
	}
	
	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}
	
	public String getMateriallflow() {
		return materiallflow;
	}
	
	public void setMateriallflow(String materiallflow) {
		this.materiallflow = materiallflow;
	}
	
	public String getWorklinecode() {
		return worklinecode;
	}
	
	public void setWorklinecode(String worklinecode) {
		this.worklinecode = worklinecode;
	}
	
	public String getWorklinename() {
		return worklinename;
	}
	
	public void setWorklinename(String worklinename) {
		this.worklinename = worklinename;
	}
	
	public String getFdocumentcode() {
		return fdocumentcode;
	}
	
	public void setFdocumentcode(String fdocumentcode) {
		this.fdocumentcode = fdocumentcode;
	}
	
	public String getFdocumentname() {
		return fdocumentname;
	}
	
	public void setFdocumentname(String fdocumentname) {
		this.fdocumentname = fdocumentname;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public String getAuditlevel() {
		return auditlevel;
	}

	public void setAuditlevel(String auditlevel) {
		this.auditlevel = auditlevel;
	}

	public String getCreateoperacode() {
		return createoperacode;
	}

	public void setCreateoperacode(String createoperacode) {
		this.createoperacode = createoperacode;
	}
	
	
	
}
