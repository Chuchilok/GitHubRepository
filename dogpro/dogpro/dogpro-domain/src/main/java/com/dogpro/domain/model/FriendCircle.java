package com.dogpro.domain.model;

import java.util.Date;

public class FriendCircle {
    private Long friendcirId;

    private Long userId;

    private Date publishtime;

    private Integer mediatype;

    private Integer type;

    private String publiship;

    private String remark;

    private String sort;

    private Date addtimes;

    private Date updatetimes;

    private String provinces;

    private String municipalities;

    private String districts;

    private String townstreet;

    private String address;

    private Float longitude;

    private Float latitude;

    private Integer state;

    private Long friendcircode;

    private Long updatecode;

    private String content;

    public Long getFriendcirId() {
        return friendcirId;
    }

    public void setFriendcirId(Long friendcirId) {
        this.friendcirId = friendcirId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getMediatype() {
        return mediatype;
    }

    public void setMediatype(Integer mediatype) {
        this.mediatype = mediatype;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPubliship() {
        return publiship;
    }

    public void setPubliship(String publiship) {
        this.publiship = publiship == null ? null : publiship.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
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

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces == null ? null : provinces.trim();
    }

    public String getMunicipalities() {
        return municipalities;
    }

    public void setMunicipalities(String municipalities) {
        this.municipalities = municipalities == null ? null : municipalities.trim();
    }

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts == null ? null : districts.trim();
    }

    public String getTownstreet() {
        return townstreet;
    }

    public void setTownstreet(String townstreet) {
        this.townstreet = townstreet == null ? null : townstreet.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getFriendcircode() {
        return friendcircode;
    }

    public void setFriendcircode(Long friendcircode) {
        this.friendcircode = friendcircode;
    }

    public Long getUpdatecode() {
        return updatecode;
    }

    public void setUpdatecode(Long updatecode) {
        this.updatecode = updatecode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}