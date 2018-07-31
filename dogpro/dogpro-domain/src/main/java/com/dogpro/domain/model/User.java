package com.dogpro.domain.model;

import java.util.Date;

public class User {
    private Long userId;

    private Integer sex;

    private String headpic;

    private String nickname;

    private String sign;

    private String phone;

    private String pswd;

    private String provinces;

    private String municipalities;

    private String districts;

    private String townstreet;

    private String address;

    private Date addtimes;

    private Date updatetimes;

    private Integer state;

    private Long offlinetimes;

    private Integer iscompleted;

    private String uuid;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadpic() {
    	if(headpic!=null){
    		if(!headpic.contains("_sub")){
    			return headpic.replaceAll("\\.", "_sub\\.");
    		}
    	}
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic == null ? null : headpic.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd == null ? null : pswd.trim();
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getOfflinetimes() {
        return offlinetimes;
    }

    public void setOfflinetimes(Long offlinetimes) {
        this.offlinetimes = offlinetimes;
    }

    public Integer getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(Integer iscompleted) {
        this.iscompleted = iscompleted;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }
}