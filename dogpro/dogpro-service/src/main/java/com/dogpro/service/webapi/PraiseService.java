package com.dogpro.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface PraiseService {
	//点赞功能
	public  ResultObject clickPraise(ParameterObject paramterObject); 
	
	//查看单条朋友圈点赞情况
	public ResultObject friendCirclePraise(ParameterObject parameterObject); 
	
}
