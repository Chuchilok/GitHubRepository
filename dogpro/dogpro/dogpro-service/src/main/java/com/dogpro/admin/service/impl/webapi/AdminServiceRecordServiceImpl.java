package com.dogpro.admin.service.impl.webapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.admin.service.dbservice.AdminServiceRecorddbService;
import com.dogpro.admin.service.webapi.AdminServiceRecordService;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

@Service("ServiceRecordService")
public class AdminServiceRecordServiceImpl implements AdminServiceRecordService {
	@Autowired
	private AdminServiceRecorddbService serviceRecorddbService;
	
	public ResultObject getUserList(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();
		try{
			int pageNo = parameterObject.getIntegerParameter("pageNo");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			Long userId=parameterObject.getLongParameter("userId");
			//返回类型
			List<Map<String, Object>> modelList = new ArrayList<Map<String,Object>>();
			//验证userId
			if(serviceRecorddbService.checkServiceID(userId)){
				modelList = serviceRecorddbService.getUserList(pageNo, pageSize);
			}
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(modelList);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	public ResultObject getUnreadMsg(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();
		try{
			int pageNo = parameterObject.getIntegerParameter("pageNo");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			Long userId=parameterObject.getLongParameter("userId");
			//返回类型
			List<Map<String, Object>> modelList = new ArrayList<Map<String,Object>>();
			//验证userId
			if(serviceRecorddbService.checkServiceID(userId)){
				modelList = serviceRecorddbService.getUnreadMsg(userId,pageNo, pageSize);
			}
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(modelList);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject getHistoryMsg(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();
		try{
			Long msgCode = parameterObject.getLongParameter("msgCode");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			Long userId=parameterObject.getLongParameter("userId");
			Long sendUid = parameterObject.getLongParameter("sendUid");
			//返回类型
			List<Map<String, Object>> modelList = new ArrayList<Map<String,Object>>();
			//验证userId
			if(serviceRecorddbService.checkServiceID(userId)){
				modelList = serviceRecorddbService.getHistoryMsg(userId, sendUid, msgCode, pageSize);
			}
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(modelList);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	public ResultObject getUserInfo(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();
		try{
			Long sendUid = parameterObject.getLongParameter("sendUid");
			Long userId = parameterObject.getLongParameter("userId");
			//返回类型
			Map<String, Object> model  = new HashMap<String, Object>();
			//验证userId
			if(serviceRecorddbService.checkServiceID(userId)){
				model = serviceRecorddbService.getUserInfo(sendUid);
			}else {
				throw new Exception() ;
			}
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
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
