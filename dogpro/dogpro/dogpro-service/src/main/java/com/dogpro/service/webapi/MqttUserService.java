package com.dogpro.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface MqttUserService {
	/**
	 * 获取用户 mqtt账号信息
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getMqttUser(ParameterObject parameterObject);
}
