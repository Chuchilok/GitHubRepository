package com.webpublish.service.webapi;

import com.webpublish.common.interfacetool.ParameterObject;
import com.webpublish.common.interfacetool.ResultObject;

public interface PublishService {
	/**
	 * 发布版本
	 * @param parameterObject
	 * @return
	 */
	public ResultObject publishVersion(ParameterObject parameterObject);
	/**
	 * 删除版本
	 * @param parameterObject
	 * @return
	 */
//	public ResultObject deleteVersion(ParameterObject parameterObject);

}
