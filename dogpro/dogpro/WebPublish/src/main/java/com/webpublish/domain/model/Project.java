package com.webpublish.domain.model;

import java.util.Date;

public class Project {
    private Long id;

    private String projectName;

    private Integer state;

    private Date buildtimes;

    private Date addtimes;

    private Date updatetimes;

    private Long builduserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getBuildtimes() {
        return buildtimes;
    }

    public void setBuildtimes(Date buildtimes) {
        this.buildtimes = buildtimes;
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

    public Long getBuilduserId() {
        return builduserId;
    }

    public void setBuilduserId(Long builduserId) {
        this.builduserId = builduserId;
    }
}