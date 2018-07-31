package com.dogpro.common.domain;

import java.io.Serializable;

public class IMreceive implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3562452302137552177L;
	private Long sendUid;
	private Long revUid;
	private String content;
	private Integer type;
	private String msgCode;
	private Long millisTime;
	
	public Long getMillisTime() {
		return millisTime;
	}
	public void setMillisTime(Long millisTime) {
		this.millisTime = millisTime;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
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
	
}
