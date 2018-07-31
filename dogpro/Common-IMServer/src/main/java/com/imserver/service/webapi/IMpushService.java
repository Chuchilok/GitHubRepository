package com.imserver.service.webapi;


import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface IMpushService {
	/**
	 * 发送推送信息
	 * @param type
	 * @param content
	 * @param RevUid
	 * @return
	 */
	public ResultObject sendPushMsg(ParameterObject parameterObject);
	/**
	 * 99.	获取朋友圈推送消息记录（点赞，评论）
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getFirendCirclePush(ParameterObject parameterObject);
}
