package com.dogpro.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface FeedbackService {
	/**
	 * 71.	意见反馈接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject commitFeedback(ParameterObject parameterObject);
}
