package com.dogpro.service.webapi;

import java.util.Map;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.domain.model.User;

public interface UserCheckService {
	// 检测手机号
	public ResultObject checkPhone(ParameterObject parameterObject);

	// 请求验证码
	public ResultObject requestCaptcha(ParameterObject parameterObject);

	// 用户登录
	public ResultObject userLogin(ParameterObject parameterObject);

	// 用户注册
	public ResultObject userReg(ParameterObject parameterObject);

	// app启动发送 pushtoken
	public ResultObject commitPushtoken(ParameterObject parameterObject);

	//忘记密码，重置密码
	public ResultObject resetUserPwds(ParameterObject parameterObject);
	
	// 第三方登陆
	public ResultObject thirdPartyLogin(ParameterObject parameterObject);
	
	//第三方验证手机号码
	public ResultObject thirdPartyCheckPhone(ParameterObject parameterObject);
	
	//第三方确认信息
	public ResultObject thirdPartyBindUser(ParameterObject parameterObject);
	
	//客服 登陆
	public ResultObject serviceLogin(ParameterObject parameterObject);
	// 忘记密码，短信验证
	/* public ResultObject commitCaptcha(ParameterObject parameterObject); */
}
