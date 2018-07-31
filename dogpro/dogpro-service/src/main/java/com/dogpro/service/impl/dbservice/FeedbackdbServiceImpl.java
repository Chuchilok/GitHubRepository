package com.dogpro.service.impl.dbservice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.dao.FeedbackMapper;
import com.dogpro.domain.model.Feedback;
import com.dogpro.service.dbservice.FeedbackdbService;
@Service("feedbackdbService")
public class FeedbackdbServiceImpl implements FeedbackdbService{
	@Autowired
	private FeedbackMapper feedbackMapper;
	
	/**
	 * 提交意见反馈
	 * @param userId
	 * @param content
	 * @return
	 */
	public Map<String, Object> commitFeedback(Long userId,String content){
		//返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag =0;
		String msg="";
		Feedback feedback = new Feedback();
		feedback.setUserid(userId);
		feedback.setContent(content);
		feedback.setState(1);
		Date currentTime = new Date();
		feedback.setAddtimes(currentTime);
		feedback.setUpdatetimes(currentTime);
		if(feedbackMapper.insert(feedback)==1){
			flag=1;
			msg="提交成功";
		}else{
			flag=0;
			msg="提交失败";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}
}
