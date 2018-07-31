/**
 * 
 */
package com.dogpro.common.domain;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class IMmessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2985042061547666428L;
	/**
	 * 
	 */
	private Long sendUid;
	private Long revUid;
	private String content;
	private Integer type;
	private String token;
	private String md5;
	private Float sendLongitude;
	private Float sendLatitude;
	private Long msgCode;
	public Long getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(Long msgCode) {
		this.msgCode = msgCode;
	}
	public Long getSendUid() {
		return sendUid;
	}
	public void setSendUid(Long sendUid) {
		this.sendUid = sendUid;
	}
	public Long getRevUid() {
		return revUid;
	}
	public void setRevUid(Long revUid) {
		this.revUid = revUid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public Float getSendLongitude() {
		return sendLongitude;
	}
	public void setSendLongitude(Float sendLongitude) {
		this.sendLongitude = sendLongitude;
	}
	public Float getSendLatitude() {
		return sendLatitude;
	}
	public void setSendLatitude(Float sendLatitude) {
		this.sendLatitude = sendLatitude;
	}

	

}
