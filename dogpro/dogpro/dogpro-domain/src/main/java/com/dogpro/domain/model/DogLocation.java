package com.dogpro.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DogLocation implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4300902406159162493L;

	private Long id;

    private Long pid;

    private String areaname;

    private String addressalias;

    private String provinces;

    private String municipalities;

    private String districts;

    private String townstreet;

    private String address;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Float perimeter;

    private Long creatoruserid;

    private Date addtimes;

    private Date updatetimes;

    private Integer orders;

    private Integer hot;

    private Integer state;

    private String keyss;

    private String locationpic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getAddressalias() {
        return addressalias;
    }

    public void setAddressalias(String addressalias) {
        this.addressalias = addressalias == null ? null : addressalias.trim();
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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Float getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Float perimeter) {
        this.perimeter = perimeter;
    }

    public Long getCreatoruserid() {
        return creatoruserid;
    }

    public void setCreatoruserid(Long creatoruserid) {
        this.creatoruserid = creatoruserid;
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

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getKeyss() {
        return keyss;
    }

    public void setKeyss(String keyss) {
        this.keyss = keyss == null ? null : keyss.trim();
    }

    public String getLocationpic() {
        return locationpic;
    }

    public void setLocationpic(String locationpic) {
        this.locationpic = locationpic == null ? null : locationpic.trim();
    }
}