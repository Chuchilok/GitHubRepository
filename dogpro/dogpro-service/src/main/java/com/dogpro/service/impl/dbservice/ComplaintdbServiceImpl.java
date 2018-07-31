package com.dogpro.service.impl.dbservice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.dao.ComplaintMapper;
import com.dogpro.domain.model.Complaint;
import com.dogpro.service.dbservice.ComplaintdbService;

@Service("complaintdbService")
public class ComplaintdbServiceImpl implements ComplaintdbService {

	@Autowired
	private ComplaintMapper complaintMapper;
	/**
	 * 投诉
	 */
	public int userComplaint(Complaint complaint) {
		complaint.setComplainttime(new Date());
		complaint.setAddtimes(new Date());
		complaint.setUpdatetimes(new Date());
		return complaintMapper.insertSelective(complaint);
	}

}
