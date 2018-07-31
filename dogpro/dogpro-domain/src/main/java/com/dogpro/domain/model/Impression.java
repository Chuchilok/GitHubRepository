package com.dogpro.domain.model;

import java.util.Date;

public class Impression {
    private Long impressionId;

    private Long friendid;

    private Long userid;

    private String content;

    private Date time;

    private Date addtimes;

    private Date updatetimes;

    private Integer state;

    public Long getImpressionId() {
        return impressionId;
    }

    public void setImpressionId(Long impressionId) {
        this.impressionId = impressionId;
    }

    public Long getFriendid() {
        return friendid;
    }

    public void setFriendid(Long friendid) {
        this.friendid = friendid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}