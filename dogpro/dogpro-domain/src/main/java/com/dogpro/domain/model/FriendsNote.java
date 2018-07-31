package com.dogpro.domain.model;

import java.util.Date;

public class FriendsNote {
    private Long friendsnoteid;

    private Long userid;

    private Long friendid;

    private String notes;

    private Integer state;

    private Date addtimes;

    private Date updatetimes;

    public Long getFriendsnoteid() {
        return friendsnoteid;
    }

    public void setFriendsnoteid(Long friendsnoteid) {
        this.friendsnoteid = friendsnoteid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getFriendid() {
        return friendid;
    }

    public void setFriendid(Long friendid) {
        this.friendid = friendid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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