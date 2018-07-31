package com.dogpro.common.domain;

import java.io.Serializable;
import java.util.Date;

public class RedisWalkingDogGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5243174412187645748L;

	private Long groupid;

	private Long userid;

	private Long doglocationid;

	private Integer state;

	private Date endtimes;

	private Date jointimes;
	
	public RedisWalkingDogGroup(Long groupid, Long userid, Long doglocationid,
			Integer state, Date endtimes,Date jointimes) {
		this.groupid = groupid;
		this.userid = userid;
		this.doglocationid = doglocationid;
		this.state = state;
		this.endtimes = endtimes;
		this.jointimes = jointimes;
	}

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getDoglocationid() {
		return doglocationid;
	}

	public void setDoglocationid(Long doglocationid) {
		this.doglocationid = doglocationid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getEndtimes() {
		return endtimes;
	}

	public void setEndtimes(Date endtimes) {
		this.endtimes = endtimes;
	}

	public Date getJointimes() {
		return jointimes;
	}

	public void setJointimes(Date jointimes) {
		this.jointimes = jointimes;
	}

	
}
