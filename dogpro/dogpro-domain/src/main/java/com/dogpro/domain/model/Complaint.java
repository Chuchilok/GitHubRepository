package com.dogpro.domain.model;

import java.util.Date;

public class Complaint {
    private Long complaintId;

    private Long friendcirId;

    private Date complainttime;

    private String complaintcontent;

    private String complainttype;

    private Long userId;

    private String complaintip;

    private String check;

    private Date checktime;

    private Long checkuserid;

    private String handleprocess;

    private String handleresult;

    private Date addtimes;

    private Date updatetimes;

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public Long getFriendcirId() {
        return friendcirId;
    }

    public void setFriendcirId(Long friendcirId) {
        this.friendcirId = friendcirId;
    }

    public Date getComplainttime() {
        return complainttime;
    }

    public void setComplainttime(Date complainttime) {
        this.complainttime = complainttime;
    }

    public String getComplaintcontent() {
        return complaintcontent;
    }

    public void setComplaintcontent(String complaintcontent) {
        this.complaintcontent = complaintcontent == null ? null : complaintcontent.trim();
    }

    public String getComplainttype() {
        return complainttype;
    }

    public void setComplainttype(String complainttype) {
        this.complainttype = complainttype == null ? null : complainttype.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComplaintip() {
        return complaintip;
    }

    public void setComplaintip(String complaintip) {
        this.complaintip = complaintip == null ? null : complaintip.trim();
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check == null ? null : check.trim();
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public Long getCheckuserid() {
        return checkuserid;
    }

    public void setCheckuserid(Long checkuserid) {
        this.checkuserid = checkuserid;
    }

    public String getHandleprocess() {
        return handleprocess;
    }

    public void setHandleprocess(String handleprocess) {
        this.handleprocess = handleprocess == null ? null : handleprocess.trim();
    }

    public String getHandleresult() {
        return handleresult;
    }

    public void setHandleresult(String handleresult) {
        this.handleresult = handleresult == null ? null : handleresult.trim();
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