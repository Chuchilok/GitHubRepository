package com.dogpro.domain.model;

import java.io.Serializable;
import java.util.Date;

public class PushMessage implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4759433090137303820L;

	private Long id;

    private Long revuid;

    private Long senduid;

    private Integer type;

    private Long msgcode;

    private Integer state;

    private Long targetid;

    private Date addtimes;

    private Date updatetimes;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRevuid() {
        return revuid;
    }

    public void setRevuid(Long revuid) {
        this.revuid = revuid;
    }

    public Long getSenduid() {
        return senduid;
    }

    public void setSenduid(Long senduid) {
        this.senduid = senduid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(Long msgcode) {
        this.msgcode = msgcode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getTargetid() {
        return targetid;
    }

    public void setTargetid(Long targetid) {
        this.targetid = targetid;
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