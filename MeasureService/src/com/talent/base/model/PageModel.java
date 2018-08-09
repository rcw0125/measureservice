package com.talent.base.model;

import java.io.Serializable;
import java.util.List;

public class PageModel implements Serializable {
	
	private static final long serialVersionUID = -2001561982120836152L;

	private int start;
	
	private int rows = 20;
	
	private int limit = 0;
	
	private int offset = -1;
	
	private int page = 1;
	
	private int allpage = 0;

	private int allcount;

	private List<Object> list;
	
	private float sumweight = 0.0f;
	
	private int truerows;
	
	private Summary summary = new Summary();
	
	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public int getTruerows() {
		return truerows;
	}

	public void setTruerows(int truerows) {
		this.truerows = truerows;
	}

	public float getSumweight() {
		return sumweight;
	}

	public void setSumweight(float sumweight) {
		this.sumweight = sumweight;
	}

	public void setup() {
		if (allcount % rows == 0) {
			this.setAllpage(allcount / rows);
		} else {
			this.setAllpage(allcount / rows + 1);
		}
		this.setTruerows(rows);
		if (page > allpage)
			page = allpage;
		if (page < 1)
			page = 1;
		if (page == allpage) {
			int lastrows = allcount % rows;
			if (lastrows != 0) {
				this.setTruerows(lastrows);
			}
		}
		this.setStart((page - 1) * rows);

	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getAllcount() {
		return allcount;
	}

	public void setAllcount(int allcount) {
		this.allcount = allcount;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public int getRows() {
		return rows;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
		if(0 != limit){
			this.rows = limit;
		}
	}

	public void setRows(int rows) {
		if(0 != getLimit()){
			this.rows = getLimit();
		}else{
			this.rows = rows;
		}
	}

	public int getPage() {
		
		return page;
	}

	public void setPage(int page) {
		if(-1 != getOffset()){
			this.rows = getOffset();
		}else{
			this.page = page;
		}
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
		if(-1 != offset){
			this.page = offset/getRows() + 1;
		}
	}

	public int getAllpage() {
		return allpage;
	}

	public void setAllpage(int allpage) {
		this.allpage = allpage;
	}
}
