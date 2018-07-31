package com.dogpro.admin.service.dbservice;

import java.util.List;

import com.dogpro.domain.model.Complaint;
import com.dogpro.domain.model.ComplaintExample;

/**
 * 后台   投诉管理
 * @author Administrator
 *
 */
public interface AdminComplaintdbService {
	/**
	 * 查看投诉   按分类   分页
	 * @param type  分类    0 为查找全部
	 * @param pageNO  0  当前页
	 * @param pageSize  5 每页的数量
	 * @return
	 */
	List<Complaint> complaintByType(int type, int pageNO, int pageSize);

	/**
	 * 核查投诉  
	 * @param complant 对象
	 * @return  1 核查成功，0 核查失败  -1 没这个投诉  -2 系统出错
	 */
	int checkComplant(Complaint complant);
	/**
	 * 计数
	 * @param example
	 * @return
	 */
	int countComplaintByExample(ComplaintExample example);
	
}
