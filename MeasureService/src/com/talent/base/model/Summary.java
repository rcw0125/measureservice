package com.talent.base.model;

import java.io.Serializable;

public class Summary implements Serializable {
	
	private static final long serialVersionUID = 1854120819044251397L;

	private int count = 0;
	
	private int sumcarcount = 0;
	
	private double sumgross = 0.00;
	
	private double sumtare = 0.00;
	
	private double sumsuttle = 0.00;
	
	private double sumgrossb = 0.00;
	
	private double sumtareb = 0.00;
	
	private double sumsuttleb = 0.00;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSumcarcount() {
		return sumcarcount;
	}

	public void setSumcarcount(int sumcarcount) {
		this.sumcarcount = sumcarcount;
	}

	public double getSumgross() {
		return sumgross;
	}

	public void setSumgross(double sumgross) {
		this.sumgross = sumgross;
	}

	public double getSumtare() {
		return sumtare;
	}

	public void setSumtare(double sumtare) {
		this.sumtare = sumtare;
	}

	public double getSumsuttle() {
		return sumsuttle;
	}

	public void setSumsuttle(double sumsuttle) {
		this.sumsuttle = sumsuttle;
	}

	public double getSumgrossb() {
		return sumgrossb;
	}

	public void setSumgrossb(double sumgrossb) {
		this.sumgrossb = sumgrossb;
	}

	public double getSumtareb() {
		return sumtareb;
	}

	public void setSumtareb(double sumtareb) {
		this.sumtareb = sumtareb;
	}

	public double getSumsuttleb() {
		return sumsuttleb;
	}

	public void setSumsuttleb(double sumsuttleb) {
		this.sumsuttleb = sumsuttleb;
	}

	
}
