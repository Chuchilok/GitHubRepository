package com.dogpro.common.domain;

import java.io.Serializable;

public class CheckUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8036071936174925544L;
	/**
	 * 
	 */
	private long userId;
	private String token;
	private	String PR;
	private String PU;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPR() {
		return PR;
	}
	public void setPR(String pR) {
		PR = pR;
	}
	public String getPU() {
		return PU;
	}
	public void setPU(String pU) {
		PU = pU;
	}
	
	
}
