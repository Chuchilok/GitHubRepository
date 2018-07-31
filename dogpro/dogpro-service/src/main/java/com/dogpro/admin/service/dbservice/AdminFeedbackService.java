package com.dogpro.admin.service.dbservice;

import java.util.List;

import com.dogpro.domain.model.Feedback;
import com.dogpro.domain.model.FeedbackExample;

/**
 * 后台   意见反馈
 * @author Administrator
 *
 */
public interface AdminFeedbackService {
	/***
	 * 查看 所有反馈意见 分页
	 * @param str   || 可以模糊查询
	 * @param pageNO
	 * @param pageSize
	 * @return  反馈意见集合
	 */
	List<Feedback> feedbackList(String str, int pageNO, int pageSize);

	int getCountByExample(FeedbackExample example);

}
