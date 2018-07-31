package com.dogpro.domain.model;

import java.util.Date;

public class Discuss {
    private Long discussId;

    private Long pid;

    private Long userId;

    private Date discusstime;

    private String discussip;

    private Long friendcirId;

    private String discusscontent;

    private Date addtimes;

    private Date updatetimes;

    private Integer state;

    public Long getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Long discussId) {
        this.discussId = discussId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDiscusstime() {
        return discusstime;
    }

    public void setDiscusstime(Date discusstime) {
        this.discusstime = discusstime;
    }

    public String getDiscussip() {
        return discussip;
    }

    public void setDiscussip(String discussip) {
        this.discussip = discussip == null ? null : discussip.trim();
    }

    public Long getFriendcirId() {
        return friendcirId;
    }

    public void setFriendcirId(Long friendcirId) {
        this.friendcirId = friendcirId;
    }

    public String getDiscusscontent() {
        return discusscontent;
    }

    public void setDiscusscontent(String discusscontent) {
        this.discusscontent = discusscontent == null ? null : discusscontent.trim();
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