package com.talent.measure.model;

import java.io.Serializable;

public class FlowInParams implements Serializable{
	
	private static final long serialVersionUID = -493771048118041199L;

	private String matchid = "";    
    
    private String optr = ""; //
    
    private String caller = "";//调用者 1坐席还是0终端 
	
    private double tare = 0.00;
    
    private double gross = 0.00;
    
    private double suttle = 0.00;

	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	public String getOptr() {
		return optr;
	}

	public void setOptr(String optr) {
		this.optr = optr;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public double getTare() {
		return tare;
	}

	public void setTare(double tare) {
		this.tare = tare;
	}

	public double getGross() {
		return gross;
	}

	public void setGross(double gross) {
		this.gross = gross;
	}

	public double getSuttle() {
		return suttle;
	}

	public void setSuttle(double suttle) {
		this.suttle = suttle;
	}
}