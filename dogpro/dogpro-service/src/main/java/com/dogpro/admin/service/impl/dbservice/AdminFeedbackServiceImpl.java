package com.dogpro.admin.service.impl.dbservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.admin.service.dbservice.AdminFeedbackService;
import com.dogpro.dao.FeedbackMapper;
import com.dogpro.domain.model.Feedback;
import com.dogpro.domain.model.FeedbackExample;
@Service("AdminFeedbackService")
public class AdminFeedbackServiceImpl implements AdminFeedbackService {
	@Autowired
	private FeedbackMapper feedbackMapper;
	
	/**
	 * 意见反馈列表 分页
	 */
	public List<Feedback> feedbackList(String str, int pageNO, int pageSize) {
		FeedbackExample example = new FeedbackExample();
		PaginationUtil pagination = new PaginationUtil(pageNO, pageSize);
		if (str!=null && !str.equals("")) {
			FeedbackExample.Criteria cExample = example.createCriteria();
			//添加条件搜索
		}
		example.setPagination(pagination);
		example.setOrderByClause("addTimes asc");//这里把最早的排序出啦
		return feedbackMapper.selectByExample(example);
	}

	public int getCountByExample(FeedbackExample example) {
		return feedbackMapper.countByExample(example);
	}

}
