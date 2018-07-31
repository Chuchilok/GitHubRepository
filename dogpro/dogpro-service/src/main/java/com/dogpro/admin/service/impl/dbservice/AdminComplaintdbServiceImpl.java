package com.dogpro.admin.service.impl.dbservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.admin.service.dbservice.AdminComplaintdbService;
import com.dogpro.dao.ComplaintMapper;
import com.dogpro.domain.model.Complaint;
import com.dogpro.domain.model.ComplaintExample;
import com.dogpro.domain.model.User;
@Service("adminComplaintdbService")
public class AdminComplaintdbServiceImpl implements AdminComplaintdbService {
	@Autowired
	private ComplaintMapper complaintMapper;
	//查看投诉 按分类 分页
	public List<Complaint> complaintByType(int type, int pageNO, int pageSize) {
		ComplaintExample example = new ComplaintExample();
		PaginationUtil pagination = new PaginationUtil(pageNO, pageSize);
		example.setPagination(pagination);
		example.setOrderByClause("complaintTime asc");//把最早的查出来先
		if (type!=0) {//全部类型查找
			ComplaintExample.Criteria cExample = example.createCriteria();
			cExample.andComplainttypeEqualTo(String.valueOf(type));
		}
		return complaintMapper.selectByExample(example);
	}
	//核查投诉 
	public int checkComplant(Complaint complant) {
		try {
			if (complaintMapper.selectByPrimaryKey(complant.getComplaintId()) != null) {
				complant.setUpdatetimes(new Date());//更改修改时间
				return complaintMapper.updateByPrimaryKeySelective(complant);
			} else {
				return -1;
			}
		} catch (Exception e) {
			return -1;
		}
	}
	public int countComplaintByExample(ComplaintExample example) {
		return complaintMapper.countByExample(example);
	}

}
