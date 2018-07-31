package com.dogpro.domain.model;

import java.util.Date;

public class Feedback {
    private Long feedbackid;

    private Long userid;

    private String content;

    private Integer state;

    private Date addtimes;

    private Date updatetimes;

    public Long getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(Long feedbackid) {
        this.feedbackid = feedbackid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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