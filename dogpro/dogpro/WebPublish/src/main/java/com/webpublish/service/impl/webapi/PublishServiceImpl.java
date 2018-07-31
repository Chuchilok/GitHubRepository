package com.webpublish.service.impl.webapi;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webpublish.common.interfacetool.ParameterObject;
import com.webpublish.common.interfacetool.ResultObject;
import com.webpublish.service.dbservice.PublishdbService;
import com.webpublish.service.webapi.PublishService;

@Service("PublishService")
public class PublishServiceImpl implements PublishService {
	@Autowired
	private PublishdbService publishdbService;

	public ResultObject publishVersion(ParameterObject parameterObject) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			Long projectId = parameterObject.getLongParameter("projectId");
			Long userId = parameterObject.getLongParameter("userId");
			String token = parameterObject.getStringParameter("token");
			String versionNO = parameterObject.getStringParameter("versionNO");
			String versionName = parameterObject
					.getStringParameter("versionName");
			String downloadUrl = parameterObject
					.getStringParameter("downloadUrl");
			int isRestart = parameterObject.getIntegerParameter("isRestart");
			HttpServletRequest request = (HttpServletRequest) parameterObject
					.getParameter("requestobj");
			Map<String, Object> model = publishdbService.publishVersion(userId,
					projectId, versionNO, versionName, downloadUrl, isRestart,request);
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
/*
	public ResultObject deleteVersion(ParameterObject parameterObject) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			Long versionId = parameterObject.getLongParameter("versionId");
			Long userId = parameterObject.getLongParameter("userId");
			String token = parameterObject.getStringParameter("token");
			Map<String, Object> model = publishdbService.deleteVersion(userId,
					versionId);
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
*/
}
