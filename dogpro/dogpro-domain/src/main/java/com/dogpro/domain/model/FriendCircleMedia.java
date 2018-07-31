package com.dogpro.domain.model;

import java.util.Date;

public class FriendCircleMedia {
    private Long mediaId;

    private Long friendcirId;

    private String mediaurl;

    private String mediasuburl;

    private String remark;

    private Date addtimes;

    private Date updatetimes;

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Long getFriendcirId() {
        return friendcirId;
    }

    public void setFriendcirId(Long friendcirId) {
        this.friendcirId = friendcirId;
    }

    public String getMediaurl() {
        return mediaurl;
    }

    public void setMediaurl(String mediaurl) {
        this.mediaurl = mediaurl == null ? null : mediaurl.trim();
    }

    public String getMediasuburl() {
        return mediasuburl;
    }

    public void setMediasuburl(String mediasuburl) {
        this.mediasuburl = mediasuburl == null ? null : mediasuburl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}