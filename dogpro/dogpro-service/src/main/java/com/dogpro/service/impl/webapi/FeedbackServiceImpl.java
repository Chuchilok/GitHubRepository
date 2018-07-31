package com.dogpro.service.impl.webapi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.service.dbservice.FeedbackdbService;
import com.dogpro.service.webapi.FeedbackService;
@Service("FeedbackService")
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackdbService feedbackdbService;
	/**
	 * 71.	意见反馈接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject commitFeedback(ParameterObject parameterObject){
		Long userId= parameterObject.getLongParameter("userId");
		String content = parameterObject.getStringParameter("content");
		String token = parameterObject.getToken();
		ResultObject retObj=new ResultObject();//返回对象
		try{
			Map<String, Object> model = feedbackdbService.commitFeedback(userId, content);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
}
