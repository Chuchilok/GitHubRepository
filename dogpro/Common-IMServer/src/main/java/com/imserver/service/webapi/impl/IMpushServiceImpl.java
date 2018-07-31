package com.imserver.service.webapi.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.imserver.service.dbservice.IMpushdbService;
import com.imserver.service.webapi.IMpushService;

@Service("IMpushService")
public class IMpushServiceImpl implements IMpushService {
	@Autowired
	private IMpushdbService iMpushdbService;
	
	public ResultObject sendPushMsg(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			long revUid = parameterObject.getLongParameter("revUid");
			String content = parameterObject.getStringParameter("content");
			int type = parameterObject.getIntegerParameter("type");
			String token = parameterObject.getStringParameter("token");
			Long sendUid = parameterObject.getLongParameter("sendUid");
			Long targetId = parameterObject.getLongParameter("targetId");
			Map<String,Object> model = iMpushdbService.sendPushMsg(type, content, revUid,sendUid,targetId);
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

	public ResultObject getFirendCirclePush(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			long userId = parameterObject.getLongParameter("userId");
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			String token = parameterObject.getStringParameter("token");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Map<String,Object>> modelList = iMpushdbService.getFirendCirclePush(userId, pageNO, pageSize);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(modelList);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	
}
