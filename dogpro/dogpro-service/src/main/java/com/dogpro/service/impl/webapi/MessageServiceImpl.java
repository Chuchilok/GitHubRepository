package com.dogpro.service.impl.webapi;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.domain.model.Message;
import com.dogpro.service.dbservice.MessagedbService;
import com.dogpro.service.webapi.MessageService;

/**
 * 消息接口
 * 
 * @author Administrator
 *
 */
@Service("MessageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessagedbService messagedbService;

	public ResultObject uploadMedia(ParameterObject parameterObject) {
		// 聊天上传的视频资源，先添加消息（Message）

		Message message = new Message();

		return null;
	}

	public ResultObject publishMessage(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			long sendUserId = Long.parseLong(parameterObject
					.getStringParameter("sendUserId"));
			long acceptUserId = Long.parseLong(parameterObject
					.getStringParameter("acceptUserId"));
			float sendLongitude = Float.parseFloat(parameterObject
					.getStringParameter("sendLongitude"));
			float sendLatitude = Float.parseFloat(parameterObject
					.getStringParameter("sendLatitude"));
			int type = parameterObject.getIntegerParameter("type");
			String mediaUrl = parameterObject.getStringParameter("mediaUrl");
			String mediaSubUrl = parameterObject
					.getStringParameter("mediaSubUrl");
			String token = parameterObject.getToken();
			Message message = new Message();
			message.setSenduserid(sendUserId);
			message.setSendlongitude(sendLongitude);
			message.setSendlatitude(sendLatitude);
			message.setState(0);// 刚发送出去的，未处理状态
			message.setSendtimes(new Date());// 发送信息时间
			message.setAddtimes(new Date());
			message.setUpdatetimes(new Date());
			int res = 0;
			//TODO 缩略图没用到 mediaSubUrl
			if (type == 4) {// 为好友图片信息
				message.setType(4);
				message.setAcceptuserid(acceptUserId);
				res = messagedbService.publishMessage(message,mediaUrl,mediaSubUrl);
			} else if (type == 5) {// 为好友视频信息
				message.setType(5);
				message.setAcceptuserid(acceptUserId);
				res = messagedbService.publishMessage(message,mediaUrl,mediaSubUrl);
			} else if (type == 8) {// 为群视频信息
				message.setType(8);
				long groupid = acceptUserId;
				res = messagedbService.publishMessage(message, groupid,mediaUrl,mediaSubUrl);
			}else if (type == 9) {// 为群图片信息
				message.setType(9);
				long dogLocationid = acceptUserId;//遛狗地点id
				res = messagedbService.publishMessage(message, dogLocationid,mediaUrl,mediaSubUrl);
			}
			Map<String, Object> model = new HashMap<String, Object>();
			String msg = "";
			int flag = 0;
			if (res>0) {
				flag = 1 ;
				msg = "上传成功";
			}else {
				msg = "上传失败";
			}
			model.put("flag", flag);
			model.put("msg", msg);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
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
