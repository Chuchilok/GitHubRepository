package com.dogpro.service.impl.webapi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.service.dbservice.SettingdbService;
import com.dogpro.service.webapi.SettingService;
@Service("SettingService")
public class SettingServiceImpl implements SettingService {
	@Autowired
	private SettingdbService settingdbService;
	public ResultObject alterSetting(ParameterObject parameterObject) {
		long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
		int notify = parameterObject.getIntegerParameter("notify");
		int sound = parameterObject.getIntegerParameter("sound");
		int shake = parameterObject.getIntegerParameter("shake");
		int handset = parameterObject.getIntegerParameter("handset");
		ResultObject retObj = new ResultObject();
		try {
			Map<String,Object> model = settingdbService.alterSetting(userId, notify, sound, shake, handset);
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

	public ResultObject getSetting(ParameterObject parameterObject) {
		long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
		ResultObject retObj = new ResultObject();
		try {
			Map<String,Object> model = settingdbService.getSetting(userId);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;// TODO Auto-generated method stub
	}

	/**
	 * 设置 用户 群组 消息免打扰
	 * @param parameterObject
	 * @return
	 */
	public ResultObject setGroupDisturb(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			int isDisturb = parameterObject.getIntegerParameter("isDisturb");
			Map<String,Object> model = settingdbService.setGroupDisturb(userId,isDisturb);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;// TODO Auto-generated method stub
	}

}
