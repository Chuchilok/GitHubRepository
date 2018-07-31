package com.dogpro.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface ImpressionService {
	/**
	 * 返回用户对用的所有印象
	 * @param parameterObject 参数
	 * @return 结果
	 */
	public ResultObject allImpression(ParameterObject parameterObject);
	//添加印象
	public ResultObject addImpression(ParameterObject parameterObject);
	//删除印象
	public ResultObject deleteImpression(ParameterObject parameterObject);
	
}
