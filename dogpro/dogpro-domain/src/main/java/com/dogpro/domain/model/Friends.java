package com.dogpro.domain.model;

import java.util.Date;

public class Friends {
    private Long id;

    private Long userId;

    private Long friendUserId;

    private Integer isRequest;

    private Date requestTime;

    private String friendComment;

    private Float longitude;

    private Float latitude;

    private Integer isOpenfriendCirToFriend;

    private Date addtimes;

    private Date updatetimes;

    private Integer friendState;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Long friendUserId) {
        this.friendUserId = friendUserId;
    }

    public Integer getIsRequest() {
        return isRequest;
    }

    public void setIsRequest(Integer isRequest) {
        this.isRequest = isRequest;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getFriendComment() {
        return friendComment;
    }

    public void setFriendComment(String friendComment) {
        this.friendComment = friendComment == null ? null : friendComment.trim();
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Integer getIsOpenfriendCirToFriend() {
        return isOpenfriendCirToFriend;
    }

    public void setIsOpenfriendCirToFriend(Integer isOpenfriendCirToFriend) {
        this.isOpenfriendCirToFriend = isOpenfriendCirToFriend;
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

    public Integer getFriendState() {
        return friendState;
    }

    public void setFriendState(Integer friendState) {
        this.friendState = friendState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}