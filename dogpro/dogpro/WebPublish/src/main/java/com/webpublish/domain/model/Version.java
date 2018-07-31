package com.webpublish.domain.model;

import java.util.Date;

public class Version {
    private Long id;

    private Long projectId;

    private String versionNo;

    private String versionName;

    private String downloadUrl;

    private Integer state;

    private Date addtimes;

    private Date updatetimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo == null ? null : versionNo.trim();
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
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