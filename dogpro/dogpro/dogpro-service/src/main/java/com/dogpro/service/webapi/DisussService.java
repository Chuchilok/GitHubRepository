package com.dogpro.service.webapi;

import java.util.Map;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface DisussService {
	//发布评论
	public ResultObject publishDisuss(ParameterObject parameterObject);
	//查看朋友圈详情评论列表
	public ResultObject friendCircleDisuss(ParameterObject parameterObject);
	//删除评论
	public ResultObject deleteDisuss(ParameterObject parameterObject);
}
