package com.dogpro.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class WalkingDogGroup {
    private Long groupid;

    private Long userid;

    private Long doglocationid;

    private Integer isdisturb;

    private Date jointimes;

    private BigDecimal joinlongitude;

    private BigDecimal joinlatitude;

    private Integer state;

    private Date endtimes;

    private BigDecimal endlongitude;

    private BigDecimal endlatitude;

    private Date outtimes;

    private BigDecimal outlongitude;

    private BigDecimal outlatitude;

    private Date addtimes;

    private Date updatetimes;

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getDoglocationid() {
        return doglocationid;
    }

    public void setDoglocationid(Long doglocationid) {
        this.doglocationid = doglocationid;
    }

    public Integer getIsdisturb() {
        return isdisturb;
    }

    public void setIsdisturb(Integer isdisturb) {
        this.isdisturb = isdisturb;
    }

    public Date getJointimes() {
        return jointimes;
    }

    public void setJointimes(Date jointimes) {
        this.jointimes = jointimes;
    }

    public BigDecimal getJoinlongitude() {
        return joinlongitude;
    }

    public void setJoinlongitude(BigDecimal joinlongitude) {
        this.joinlongitude = joinlongitude;
    }

    public BigDecimal getJoinlatitude() {
        return joinlatitude;
    }

    public void setJoinlatitude(BigDecimal joinlatitude) {
        this.joinlatitude = joinlatitude;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getEndtimes() {
        return endtimes;
    }

    public void setEndtimes(Date endtimes) {
        this.endtimes = endtimes;
    }

    public BigDecimal getEndlongitude() {
        return endlongitude;
    }

    public void setEndlongitude(BigDecimal endlongitude) {
        this.endlongitude = endlongitude;
    }

    public BigDecimal getEndlatitude() {
        return endlatitude;
    }

    public void setEndlatitude(BigDecimal endlatitude) {
        this.endlatitude = endlatitude;
    }

    public Date getOuttimes() {
        return outtimes;
    }

    public void setOuttimes(Date outtimes) {
        this.outtimes = outtimes;
    }

    public BigDecimal getOutlongitude() {
        return outlongitude;
    }

    public void setOutlongitude(BigDecimal outlongitude) {
        this.outlongitude = outlongitude;
    }

    public BigDecimal getOutlatitude() {
        return outlatitude;
    }

    public void setOutlatitude(BigDecimal outlatitude) {
        this.outlatitude = outlatitude;
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