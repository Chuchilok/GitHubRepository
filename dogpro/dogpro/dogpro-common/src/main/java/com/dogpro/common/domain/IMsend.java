package com.dogpro.common.domain;

import java.io.Serializable;

public class IMsend implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6842056486930605885L;
	private String token;
	private String content;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
