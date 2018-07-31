package com.imserver.domain;

import java.io.Serializable;
import java.util.Date;

public class IMgroupMessage implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5346501387363090088L;

	private Long id;

    private Long senduserid;

    private Long acceptuserid;

    private Long locationid;

    private Date accepttimes;

    private Float acceptlongitude;

    private Float acceptlatitude;

    private Integer state;

    private Integer type;

    private Long keyss;

    private Date addtimes;

    private Date updatetimes;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(Long senduserid) {
        this.senduserid = senduserid;
    }

    public Long getAcceptuserid() {
        return acceptuserid;
    }

    public void setAcceptuserid(Long acceptuserid) {
        this.acceptuserid = acceptuserid;
    }

    public Long getLocationid() {
        return locationid;
    }

    public void setLocationid(Long locationid) {
        this.locationid = locationid;
    }

    public Date getAccepttimes() {
        return accepttimes;
    }

    public void setAccepttimes(Date accepttimes) {
        this.accepttimes = accepttimes;
    }

    public Float getAcceptlongitude() {
        return acceptlongitude;
    }

    public void setAcceptlongitude(Float acceptlongitude) {
        this.acceptlongitude = acceptlongitude;
    }

    public Float getAcceptlatitude() {
        return acceptlatitude;
    }

    public void setAcceptlatitude(Float acceptlatitude) {
        this.acceptlatitude = acceptlatitude;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getKeyss() {
        return keyss;
    }

    public void setKeyss(Long keyss) {
        this.keyss = keyss;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}