package com.dogpro.domain.model;

import java.util.Date;

public class AdminUser {
    private Long adminuserid;

    private String phone;

    private String pwds;

    private String names;

    private String nickname;

    private String dept;

    private Integer state;

    private Date addtimes;

    private Date updatetimes;

    public Long getAdminuserid() {
        return adminuserid;
    }

    public void setAdminuserid(Long adminuserid) {
        this.adminuserid = adminuserid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPwds() {
        return pwds;
    }

    public void setPwds(String pwds) {
        this.pwds = pwds == null ? null : pwds.trim();
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names == null ? null : names.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
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