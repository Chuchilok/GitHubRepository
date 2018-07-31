package com.dogpro.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WalkingDogTrack implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2113477298270345148L;

	private Long trackid;

    private Long userid;

    private Long groupid;

    private Date tracktimes;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Date addtimes;

    private Date updatetimes;

    public Long getTrackid() {
        return trackid;
    }

    public void setTrackid(Long trackid) {
        this.trackid = trackid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Date getTracktimes() {
        return tracktimes;
    }

    public void setTracktimes(Date tracktimes) {
        this.tracktimes = tracktimes;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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