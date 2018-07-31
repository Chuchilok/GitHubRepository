package com.dogpro.service.impl.webapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.domain.model.Ucaptcha;
import com.dogpro.domain.model.UcaptchaExample;
import com.dogpro.domain.model.User;
import com.dogpro.service.dbservice.UcaptchadbService;
import com.dogpro.service.dbservice.UserInfodbService;
import com.dogpro.service.webapi.UserInfoService;
@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService{
	//个人信息
	@Autowired
	private UserInfodbService userserviceImpl;
	@Autowired
	private UcaptchadbService ucaptchaServiceImpl;
	
	//获取用户信息
	public ResultObject getUserInfo(ParameterObject parameterObject) {
		Long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
		String token = parameterObject.getToken();
		ResultObject retObj=new ResultObject();
		try{
			Map<String, Object> model = userserviceImpl.getUserInfo(userId);
			retObj.setResult(model);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	
	//修改个人信息
	public ResultObject alterUserInfo(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		String token = parameterObject.getToken();
		JSONObject jsonObject = new JSONObject(parameterObject.getParameterObject());
		User user = JSONObject.toJavaObject(jsonObject, User.class);
		ResultObject retObj=new ResultObject();
		try{
			Map<String,Object> model = userserviceImpl.alterUserInfo(user);
			retObj.setResult(model);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	//第三方完善信息
	public ResultObject thirdPartyFillInfo(ParameterObject parameterObject) {
		
		ResultObject retObj=new ResultObject();
		try{
			JSONObject jsonObject = new JSONObject(parameterObject.getParameterObject());
			User user = JSONObject.toJavaObject(jsonObject, User.class);
			Long userId = parameterObject.getLongParameter("userId");
			String openid = parameterObject.getStringParameter("openid");
			int thirdPartyType = parameterObject.getIntegerParameter("thirdPartyType");
			int type = parameterObject.getIntegerParameter("type");
			String pushtoken = parameterObject.getStringParameter("pushtoken");
			String phone = parameterObject.getStringParameter("phone");
			String captcha = parameterObject.getStringParameter("captcha");
			Map<String,Object> model = userserviceImpl.thirdPartyFillInfo(userId,user, openid, thirdPartyType, type, pushtoken, phone, captcha);
			retObj.setResult(model);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	//修改密码
	public ResultObject alterUserPwds(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj=new ResultObject();
		long userId=(long)parameterObject.getIntegerParameter("userId");
		String pswd=parameterObject.getStringParameter("pswd");
		String pswd1=parameterObject.getStringParameter("pswd1");
		String pswd2=parameterObject.getStringParameter("pswd2");
		try{
			Map<String, Object> model = userserviceImpl.AlterUserPwds(userId, pswd, pswd1,pswd2);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	
	//修改手机号
	public ResultObject alterUserPhone(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj=new ResultObject();
		long userId=(Long) parameterObject.getParameter("userId");
		String phone=parameterObject.getStringParameter("phone");
		String captcha=parameterObject.getStringParameter("captcha");
		try{
			Map<String, Object> model = userserviceImpl.alterUserPhone(userId, phone, captcha);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

}
