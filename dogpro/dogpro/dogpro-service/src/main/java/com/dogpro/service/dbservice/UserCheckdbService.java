package com.dogpro.service.dbservice;

import java.util.Map;

import com.dogpro.domain.model.User;

public interface UserCheckdbService {
	// 注册
	public Map<String, Object> regUser(String phone, String pswd,
			String captcha, int type, String pushtoken);

	// 登陆
	public Map<String, Object> loginUser(String phone, String pswd, int type,
			String pushtoken);

	// app启动发送 pushtoken
	public Map<String, Object> commitPushtoken(String pushtoken, int type);

	// 第三方登陆
	public Map<String, Object> thirdPartyLogin(int thirdPartyType, int type,
			String pushtoken, String access_token, String openid);

	// 验证手机 是否可用
	public boolean vevifyPhone(String phone);

	// 验证验证码
	public Map<String, Object> vevifyCaptcha(String phone, String captcha,int type,
			Boolean updateCaptchaData);

	// 忘记密码重置密码
	public Map<String, Object> resetUserPwds(String phone, String pswd1,
			String pswd2, String captcha);

	// 检验第三方登陆
	public boolean thirdPartyCheck(int type, String accesstoken, String openid);

	// 第三方验证手机号码
	public Map<String, Object> thirdPartyCheckPhone(String phone, String captcha);

	// 第三方绑定 用户
	public Map<String, Object> thirdPartyBindUser(String access_token,
			String openid, int thirdPartyType, String pushtoken, int type,String phone, String captcha,User user);
	
	//MQTT用户踢下线
	public boolean DisconnectClient(Long userId);

}
