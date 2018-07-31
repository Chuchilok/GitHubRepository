package com.dogpro.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class RedisDoglocation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5338353694199401123L;

	private Long id;

    private String areaname;

    private String keyss;

    private String locationpic;
    
    
	public RedisDoglocation(Long id, String areaname, 
			String keyss, String locationpic) {
		this.id = id;
		this.areaname = areaname;
		this.keyss = keyss;
		this.locationpic = locationpic;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAreaname() {
		return areaname;
	}


	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}


	public String getKeyss() {
		return keyss;
	}


	public void setKeyss(String keyss) {
		this.keyss = keyss;
	}


	public String getLocationpic() {
		return locationpic;
	}


	public void setLocationpic(String locationpic) {
		this.locationpic = locationpic;
	}

	
    
}
