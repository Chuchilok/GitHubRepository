package com.dogpro.domain.model;

import java.io.Serializable;
import java.util.Date;

public class AreaSpace implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5149301133372981925L;

	private Integer id;

    private Long doglocationId;

    private Object areaRange;

    private Integer state;

    private Date addtimes;

    private Date updatetimes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDoglocationId() {
        return doglocationId;
    }

    public void setDoglocationId(Long doglocationId) {
        this.doglocationId = doglocationId;
    }

    public Object getAreaRange() {
        return areaRange;
    }

    public void setAreaRange(Object areaRange) {
        this.areaRange = areaRange;
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