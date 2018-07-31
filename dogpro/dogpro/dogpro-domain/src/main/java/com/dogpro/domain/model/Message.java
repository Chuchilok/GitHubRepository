package com.dogpro.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3969209800213425127L;

	private Long messageid;

    private Date accepttimes;

    private Float acceptlongitude;

    private Float acceptlatitude;

    private Long senduserid;

    private Long acceptuserid;

    private Long msgcode;

    private Float sendlongitude;

    private Float sendlatitude;

    private Date sendtimes;

    private Integer state;

    private Integer type;

    private Integer source;

    private Date addtimes;

    private Date updatetimes;

    private String content;

    public Long getMessageid() {
        return messageid;
    }

    public void setMessageid(Long messageid) {
        this.messageid = messageid;
    }

    public Date getAccepttimes() {
        return accepttimes;
    }

    public void setAccepttimes(Date accepttimes) {
        this.accepttimes = accepttimes;
    }

    public Float getAcceptlongitude() {
        return acceptlongitude;
    }

    public void setAcceptlongitude(Float acceptlongitude) {
        this.acceptlongitude = acceptlongitude;
    }

    public Float getAcceptlatitude() {
        return acceptlatitude;
    }

    public void setAcceptlatitude(Float acceptlatitude) {
        this.acceptlatitude = acceptlatitude;
    }

    public Long getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(Long senduserid) {
        this.senduserid = senduserid;
    }

    public Long getAcceptuserid() {
        return acceptuserid;
    }

    public void setAcceptuserid(Long acceptuserid) {
        this.acceptuserid = acceptuserid;
    }

    public Long getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(Long msgcode) {
        this.msgcode = msgcode;
    }

    public Float getSendlongitude() {
        return sendlongitude;
    }

    public void setSendlongitude(Float sendlongitude) {
        this.sendlongitude = sendlongitude;
    }

    public Float getSendlatitude() {
        return sendlatitude;
    }

    public void setSendlatitude(Float sendlatitude) {
        this.sendlatitude = sendlatitude;
    }

    public Date getSendtimes() {
        return sendtimes;
    }

    public void setSendtimes(Date sendtimes) {
        this.sendtimes = sendtimes;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Date getAddtimes() {
        return addtimes;
    }

    public void setAddtimes(Date addtimes) {
        this.addtimes = addtimes;
    }

    public Date getUpdatetimes() {
        return updatetimes;
    }

    public void setUpdatetimes(Date updatetimes) {
        this.updatetimes = updatetimes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}