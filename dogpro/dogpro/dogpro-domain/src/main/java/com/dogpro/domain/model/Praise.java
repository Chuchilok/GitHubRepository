package com.dogpro.domain.model;

import java.util.Date;

public class Praise {
    private Long praiseId;

    private Long userId;

    private Date praisetime;

    private Long friendcirId;

    private String praisestar;

    private Date addtimes;

    private Date updatetimes;

    private Integer state;

    public Long getPraiseId() {
        return praiseId;
    }

    public void setPraiseId(Long praiseId) {
        this.praiseId = praiseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getPraisetime() {
        return praisetime;
    }

    public void setPraisetime(Date praisetime) {
        this.praisetime = praisetime;
    }

    public Long getFriendcirId() {
        return friendcirId;
    }

    public void setFriendcirId(Long friendcirId) {
        this.friendcirId = friendcirId;
    }

    public String getPraisestar() {
        return praisestar;
    }

    public void setPraisestar(String praisestar) {
        this.praisestar = praisestar == null ? null : praisestar.trim();
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