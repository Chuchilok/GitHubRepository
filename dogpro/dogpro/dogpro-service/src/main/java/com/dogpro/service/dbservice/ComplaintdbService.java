package com.dogpro.service.dbservice;

import com.dogpro.domain.model.Complaint;

public interface ComplaintdbService {
	/**
	 * 用户投诉
	 * @param complaint
	 * @return 投诉成功和失败
	 */
	public int userComplaint(Complaint complaint);
}
