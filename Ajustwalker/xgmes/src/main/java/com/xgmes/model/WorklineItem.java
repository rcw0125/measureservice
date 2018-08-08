package com.xgmes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.apache.ibatis.type.Alias;
import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("worklineItem")
@Entity
@Table(name="L_WORKLINE_ITEM_T",uniqueConstraints={@UniqueConstraint(columnNames={"workpointcode"})})
public class WorklineItem extends BaseModel{
	
	private static final long serialVersionUID = 2755877945536635136L;

	@Fields(name="作业路线编码")
	@Column(name="WORKLINECODE")
	private String worklinecode = "";
	
	@Fields(name="节点编码")
	@Column(name="NODECODE")
	private String nodecode = "";
	
	@Fields(name="节点名称")
	@Column(name="NODENAME")
	private String nodename = "";
	
	@Fields(name="作业点编码")
	@Column(name="WORKPOINTCODE")
	private String workpointcode = "";
	
	@Fields(name="作业点名称")
	@Column(name="WORKPOINTNAME")
	private String workpointname = "";
	
	@Transient
	private int fromid = -1;
	
	@Transient
	private int presn = 0;
	
	@Transient
	private int timecost = 0;

	public String getWorklinecode() {
		return worklinecode;
	}

	public void setWorklinecode(String worklinecode) {
		this.worklinecode = worklinecode;
	}

	public String getNodecode() {
		return nodecode;
	}

	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public String getWorkpointcode() {
		return workpointcode;
	}

	public void setWorkpointcode(String workpointcode) {
		this.workpointcode = workpointcode;
	}

	public String getWorkpointname() {
		return workpointname;
	}

	public void setWorkpointname(String workpointname) {
		this.workpointname = workpointname;
	}

	public int getFromid() {
		return fromid;
	}

	public void setFromid(int fromid) {
		this.fromid = fromid;
	}

	public int getPresn() {
		return presn;
	}

	public void setPresn(int presn) {
		this.presn = presn;
	}

	public int getTimecost() {
		return timecost;
	}

	public void setTimecost(int timecost) {
		this.timecost = timecost;
	}
}
