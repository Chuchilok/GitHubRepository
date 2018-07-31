package com.dogpro.domain.model;

import java.io.Serializable;
import java.util.Date;

public class OnlineRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8591189136650127710L;

	private Long onlineRecordId;

    private Long totalOnlineUser;

    private Date recordTime;

    private Integer state;

    private Date addtimes;

    private Date updatetimes;

    public Long getOnlineRecordId() {
        return onlineRecordId;
    }

    public void setOnlineRecordId(Long onlineRecordId) {
        this.onlineRecordId = onlineRecordId;
    }

    public Long getTotalOnlineUser() {
        return totalOnlineUser;
    }

    public void setTotalOnlineUser(Long totalOnlineUser) {
        this.totalOnlineUser = totalOnlineUser;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
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