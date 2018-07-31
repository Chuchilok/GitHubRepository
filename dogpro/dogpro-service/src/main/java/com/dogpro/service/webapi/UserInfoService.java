package com.dogpro.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface UserInfoService {
	//修改用户信息
	public ResultObject alterUserInfo(ParameterObject parameterObject);
	//修改密码
	public ResultObject alterUserPwds(ParameterObject parameterObject);
	//修改手机号
	public ResultObject alterUserPhone(ParameterObject parameterObject);
	//查看用户信息
	public ResultObject getUserInfo(ParameterObject parameterObject);
	// 第三方完善信息
	public ResultObject thirdPartyFillInfo(ParameterObject parameterObject);
}
