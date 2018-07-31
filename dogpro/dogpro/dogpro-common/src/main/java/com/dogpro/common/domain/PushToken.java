package com.dogpro.common.domain;

import java.io.Serializable;

public class PushToken implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 69297813440886067L;
	private String Ptoken;
	private String senderName;
	public String getPtoken() {
		return Ptoken;
	}
	public void setPtoken(String ptoken) {
		Ptoken = ptoken;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
}
