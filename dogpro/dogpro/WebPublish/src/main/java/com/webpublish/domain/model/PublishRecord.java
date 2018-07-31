package com.webpublish.domain.model;

import java.util.Date;

public class PublishRecord {
    private Long id;

    private Long versionId;

    private Long userId;

    private String publishIp;

    private Integer publishPort;

    private Date publishtimes;

    private Date addtimes;

    private Date updatetimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPublishIp() {
        return publishIp;
    }

    public void setPublishIp(String publishIp) {
        this.publishIp = publishIp == null ? null : publishIp.trim();
    }

    public Integer getPublishPort() {
        return publishPort;
    }

    public void setPublishPort(Integer publishPort) {
        this.publishPort = publishPort;
    }

    public Date getPublishtimes() {
        return publishtimes;
    }

    public void setPublishtimes(Date publishtimes) {
        this.publishtimes = publishtimes;
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