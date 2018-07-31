package com.imserver.service.webapi.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.tool.NETTools;
import com.dogpro.domain.model.Complaint;
import com.imserver.service.dbservice.IMInitdbService;
import com.imserver.service.dbservice.IMMessagedbService;
import com.imserver.service.webapi.IMMessageService;
@Service("IMMessageService")
public class IMMessageServiceImpl implements IMMessageService {
	@Autowired
	private IMMessagedbService imMessagedbService;
	@Autowired
	private IMInitdbService imInitdbService;
	
	/**
	 * 发送信息
	 * @param parameterObject
	 * @return
	 */
	public ResultObject sendMsg(ParameterObject parameterObject) {
		// complaintdbService.userComplaint(complaint);
		ResultObject retObj = new ResultObject();
		try {
			Long userId = parameterObject.getLongParameter("userId");
			long sendUid = userId;
			long revUid = parameterObject.getLongParameter("revUid");
			String content = parameterObject.getStringParameter("content");
			int type = parameterObject.getIntegerParameter("type");
			String token = parameterObject.getStringParameter("token");
			String md5 = parameterObject.getStringParameter("md5");
			String sendLongitude = parameterObject.getStringParameter("sendLongitude");
			String sendLatitude = parameterObject.getStringParameter("sendLatitude");
			Float Longitude;
			Float Latitude;
			if(sendLongitude==null||sendLongitude.trim().equals("")){
				Longitude = 0f ;
			}else{
				Longitude = Float.parseFloat(sendLongitude);
			}
			if(sendLatitude==null||sendLatitude.trim().equals("")){
				Latitude = 0f ;
			}else{
				Latitude = Float.parseFloat(sendLatitude);
			}
			Map<String,Object> model = imMessagedbService.sendMsg(sendUid, revUid, content, type, token, md5,Longitude,Latitude);
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
	/**
	 * 修改信息状态
	 * @param parameterObject
	 * @return
	 */
	public ResultObject updateMsg(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj = new ResultObject();
		try {
			long sendUid = parameterObject.getLongParameter("sendUid");
			long revUid = parameterObject.getLongParameter("revUid");
			int state = parameterObject.getIntegerParameter("state");
			int type = parameterObject.getIntegerParameter("type");
			String token = parameterObject.getStringParameter("token");
			Long msgCode = parameterObject.getLongParameter("msgCode");
			Map<String,Object> model = imMessagedbService.updateMsg(sendUid, revUid, state, type, msgCode);
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
	/**
	 * 获取个人 未读取消息记录
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getUnread(ParameterObject parameterObject){
		ResultObject retObj = new ResultObject();
		try {
			Long userId = parameterObject.getLongParameter("userId");
			Long msgCode = parameterObject.getLongParameter("msgCode");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			Map<String, Object> model = imMessagedbService.getUnread(userId,msgCode,pageSize);
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

	public ResultObject getMsgList(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj = new ResultObject();
		try {
			Long userId = parameterObject.getLongParameter("userId");
			Long sendUid = parameterObject.getLongParameter("sendUid");
			int isGroup = parameterObject.getIntegerParameter("isGroup");
			Long msgCode = parameterObject.getLongParameter("msgCode");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Map<String, Object>> modelList = imMessagedbService.getMsgList(userId, sendUid, isGroup, msgCode, pageSize);
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
	/**
	 * 获取im用户信息（头像，昵称）
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getIMInfo(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj = new ResultObject();
		try {
			Long userId = parameterObject.getLongParameter("userId");
			Long imUserId = parameterObject.getLongParameter("imUserId");
			int isGroup = parameterObject.getIntegerParameter("isGroup");
			Map<String, Object> model = imMessagedbService.getIMInfo(userId, imUserId, isGroup);
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

	public ResultObject addOnline(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			Long userId = parameterObject.getLongParameter("userId");
			//记录在线用户
			Map<String, Object> model = imInitdbService.addOnline(userId);
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
