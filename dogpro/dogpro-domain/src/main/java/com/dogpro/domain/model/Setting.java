package com.dogpro.domain.model;

import java.util.Date;

public class Setting {
    private Long id;

    private Long userid;

    private Integer notify;

    private Integer sound;

    private Integer shake;

    private Integer handset;

    private Integer state;

    private Date addtimes;

    private Date updatetimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getNotify() {
        return notify;
    }

    public void setNotify(Integer notify) {
        this.notify = notify;
    }

    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    public Integer getShake() {
        return shake;
    }

    public void setShake(Integer shake) {
        this.shake = shake;
    }

    public Integer getHandset() {
        return handset;
    }

    public void setHandset(Integer handset) {
        this.handset = handset;
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