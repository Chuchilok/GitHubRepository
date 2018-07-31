package com.dogpro.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface ComplaintService {
	//用户投诉
	public ResultObject userComplaint(ParameterObject parameterObject);
}
