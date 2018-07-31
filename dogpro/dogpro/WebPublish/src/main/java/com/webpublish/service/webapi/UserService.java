package com.webpublish.service.webapi;

import com.webpublish.common.interfacetool.ParameterObject;
import com.webpublish.common.interfacetool.ResultObject;

public interface UserService {
	/**
	 * 注册
	 * @param parameterObject
	 * @return
	 */
	public ResultObject register(ParameterObject parameterObject);
	/**
	 * 登陆
	 * @param parameterObject
	 * @return
	 */
	public ResultObject login(ParameterObject parameterObject);
}
