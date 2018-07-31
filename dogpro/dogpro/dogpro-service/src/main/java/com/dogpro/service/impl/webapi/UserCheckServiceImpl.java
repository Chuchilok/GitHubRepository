package com.dogpro.service.impl.webapi;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.Ucaptcha;
import com.dogpro.domain.model.UcaptchaExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;
import com.dogpro.service.dbservice.ServiceRecorddbService;
import com.dogpro.service.dbservice.UcaptchadbService;
import com.dogpro.service.dbservice.UserCheckdbService;
import com.dogpro.service.dbservice.UserInfodbService;
import com.dogpro.service.webapi.UserCheckService;

@Service("UserCheckService")
public class UserCheckServiceImpl implements UserCheckService {
	@Autowired
	private UserInfodbService userserviceImpl;
	@Autowired
	private UcaptchadbService ucaptchadbService;
	@Autowired
	private UserCheckdbService userCheckdbService;
	@Autowired
	private ServiceRecorddbService serviceRecorddbService;

	// 验证手机号
	public ResultObject checkPhone(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			String phone = parameterObject.getStringParameter("phone");
			Map<String, Object> model = new HashMap<String, Object>();
			int flag = 0;
			if (userserviceImpl.chickUserByPhone(phone) != null) {
				flag = 0;
			} else {
				flag = 1;
			}
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			model.put("flag", flag);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	/* 获取验证码 */
	public ResultObject requestCaptcha(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		// 注册
		try {
			String phone = parameterObject.getStringParameter("phone");
			int type = parameterObject.getIntegerParameter("type");
			Map<String, Object> model = ucaptchadbService.getCaptchaByType(
					phone, type);
			retObj.setResult(model);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
		} catch (Exception e) {
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 用户登录
	public ResultObject userLogin(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();

		try {
			String phone = parameterObject.getStringParameter("phone");
			String pswd = parameterObject.getStringParameter("pswd");
			int type = parameterObject.getIntegerParameter("type");
			String pushtoken = parameterObject.getStringParameter("pushtoken");
			Map<String, Object> model = userCheckdbService.loginUser(phone,
					pswd, type, pushtoken);
			retObj.setResult(model);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 用户注册
	public ResultObject userReg(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();

		try {
			String phone = parameterObject.getStringParameter("phone");
			String pswd = parameterObject.getStringParameter("pswd");
			String captcha = parameterObject.getStringParameter("captcha");
			int type = parameterObject.getIntegerParameter("type");
			String pushtoken = parameterObject.getStringParameter("pushtoken");
			Map<String, Object> model = userCheckdbService.regUser(phone, pswd,
					captcha, type, pushtoken);
			retObj.setResult(model);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject commitPushtoken(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			int type = parameterObject.getIntegerParameter("type");
			String pushtoken = parameterObject.getStringParameter("pushtoken");
			Map<String, Object> model = userCheckdbService.commitPushtoken(
					pushtoken, type);
			retObj.setResult(model);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject thirdPartyLogin(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			String pushtoken = parameterObject.getStringParameter("pushtoken");
			int thirdPartyType = parameterObject
					.getIntegerParameter("thirdPartyType");
			int type = parameterObject.getIntegerParameter("type");
			String access_token = parameterObject
					.getStringParameter("access_token");
			String openid = parameterObject.getStringParameter("openid");
			Map<String, Object> model = userCheckdbService.thirdPartyLogin(
					thirdPartyType, type, pushtoken, access_token, openid);
			retObj.setResult(model);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	
	public ResultObject thirdPartyCheckPhone(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			String phone = parameterObject.getStringParameter("phone");
			String captcha = parameterObject.getStringParameter("captcha");
			Map<String, Object> model = userCheckdbService.thirdPartyCheckPhone(phone, captcha);
			retObj.setResult(model);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject thirdPartyBindUser(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			String phone = parameterObject.getStringParameter("phone");
			String captcha = parameterObject.getStringParameter("captcha");
			String access_token = parameterObject.getStringParameter("access_token");
			String openid = parameterObject.getStringParameter("openid");
			Integer thirdPartyType = parameterObject.getIntegerParameter("thirdPartyType");
			Integer type = parameterObject.getIntegerParameter("type");
			String pushtoken = parameterObject.getStringParameter("pushtoken");
			JSONObject jsonObject = new JSONObject(parameterObject.getParameterObject());
			User user = JSONObject.toJavaObject(jsonObject, User.class);
			Map<String, Object> model = userCheckdbService.thirdPartyBindUser(access_token, openid, thirdPartyType, pushtoken, type, phone, captcha, user);
			retObj.setResult(model);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	
	// 忘记密码重置密码
	public ResultObject resetUserPwds(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		String phone = parameterObject.getStringParameter("phone");
		String pswd1 = parameterObject.getStringParameter("pswd1");
		String pswd2 = parameterObject.getStringParameter("pswd2");
		String captcha = parameterObject.getStringParameter("captcha");
		try {
			Map<String, Object> model = userCheckdbService.resetUserPwds(phone,
					pswd1, pswd2, captcha);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject serviceLogin(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		String phone = parameterObject.getStringParameter("phone");
		String pswd = parameterObject.getStringParameter("pswd");
		try {
			Map<String, Object> model = serviceRecorddbService.serviceLogin(phone, pswd);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
}