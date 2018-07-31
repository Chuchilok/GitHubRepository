package com.dogpro.domain.model;

import java.util.Date;

public class MessageMedia {
    private Long msgmediaid;

    private Date uploadtimes;

    private Long userid;

    private Integer mediatype;

    private String mediaurl;

    private Long messageid;

    private Date addtimes;

    private Date updatetimes;

    private Long resourcecode;

    public Long getMsgmediaid() {
        return msgmediaid;
    }

    public void setMsgmediaid(Long msgmediaid) {
        this.msgmediaid = msgmediaid;
    }

    public Date getUploadtimes() {
        return uploadtimes;
    }

    public void setUploadtimes(Date uploadtimes) {
        this.uploadtimes = uploadtimes;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getMediatype() {
        return mediatype;
    }

    public void setMediatype(Integer mediatype) {
        this.mediatype = mediatype;
    }

    public String getMediaurl() {
        return mediaurl;
    }

    public void setMediaurl(String mediaurl) {
        this.mediaurl = mediaurl == null ? null : mediaurl.trim();
    }

    public Long getMessageid() {
        return messageid;
    }

    public void setMessageid(Long messageid) {
        this.messageid = messageid;
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

    public Long getResourcecode() {
        return resourcecode;
    }

    public void setResourcecode(Long resourcecode) {
        this.resourcecode = resourcecode;
    }
}