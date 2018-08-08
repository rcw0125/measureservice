package com.talent.materialflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.ibatis.type.Alias;
import com.talent.core.annotation.Fields;
import com.talent.core.model.BaseModel;

@Alias("worklineTimecost")
@Entity
@Table(name="L_WORKLINE_TIMECOST_T")
public class WorklineTimecost extends BaseModel{
	
	private static final long serialVersionUID = -727625780407845677L;
	
	@Fields(name="作业路线编码")
	@Column(name="WORKLINECODE")
	private String worklinecode = "";

	@Fields(name="来源节点ID")
	@Column(name="FROMID")
	private long fromid = -1;
	
	@Fields(name="去向节点ID")
	@Column(name="TOID")
	private long toid = -1;
	
	@Fields(name="时间消耗")
	@Column(name="TIMECOST")
	private int timecost = 0;

	public long getFromid() {
		return fromid;
	}

	public void setFromid(long fromid) {
		this.fromid = fromid;
	}

	public long getToid() {
		return toid;
	}

	public void setToid(long toid) {
		this.toid = toid;
	}

	public int getTimecost() {
		return timecost;
	}

	public void setTimecost(int timecost) {
		this.timecost = timecost;
	}

	public String getWorklinecode() {
		return worklinecode;
	}

	public void setWorklinecode(String worklinecode) {
		this.worklinecode = worklinecode;
	}
}
