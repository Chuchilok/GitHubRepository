package com.dogpro.domain.model;

import java.util.Date;

public class Token {
    private Long id;

    private Long userId;

    private String token;

    private Date lastUpdateTime;

    private Integer userType;

    private Integer state;

    private Date updateTimes;

    private Date addTimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(Date updateTimes) {
        this.updateTimes = updateTimes;
    }

    public Date getAddTimes() {
        return addTimes;
    }

    public void setAddTimes(Date addTimes) {
        this.addTimes = addTimes;
    }
}