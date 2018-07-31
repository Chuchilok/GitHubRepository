package com.dogpro.service.dbservice;

import java.util.Map;

public interface FeedbackdbService {
	/**
	 * 提交意见反馈
	 * @param userId
	 * @param content
	 * @return
	 */
	public Map<String, Object> commitFeedback(Long userId,String content);
}
