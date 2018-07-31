package com.imserver.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface IMMessageService {
	/**
	 * 发送信息
	 * @param parameterObject
	 * @return
	 */
	public ResultObject sendMsg(ParameterObject parameterObject);
	/**
	 * 修改信息状态
	 * @param parameterObject
	 * @return
	 */
	public ResultObject updateMsg(ParameterObject parameterObject);
	/**
	 * 获取个人 未读取消息记录
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getUnread(ParameterObject parameterObject);
	/**
	 * 获取im 聊天记录
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getMsgList(ParameterObject parameterObject);
	/**
	 * 获取im用户信息（头像，昵称）
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getIMInfo(ParameterObject parameterObject);
	/**
	 * im mqtt 连接成功
	 * @param parameterObject
	 * @return
	 */
	public ResultObject addOnline(ParameterObject parameterObject);
}
