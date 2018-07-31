package com.webpublish.service.impl.webapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webpublish.common.interfacetool.ParameterObject;
import com.webpublish.common.interfacetool.ResultObject;
import com.webpublish.service.dbservice.UserdbService;
import com.webpublish.service.webapi.UserService;
@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserdbService userdbService;
	
	public ResultObject register(ParameterObject parameterObject) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			String username = parameterObject.getStringParameter("username");
			String pswd = parameterObject.getStringParameter("pswd");
			Map<String, Object> model = userdbService.register(username, pswd);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject login(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			String username = parameterObject.getStringParameter("username");
			String pswd = parameterObject.getStringParameter("pswd");
			Map<String, Object> model = userdbService.login(username, pswd);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

}
