package com.dogpro.admin.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface AdminServiceRecordService {
	/**
	 * 1.	返回客服 用户列表
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getUserList(ParameterObject parameterObject);
	/**
	 * 2.	获取未读信息（下线后收到的信息）
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getUnreadMsg(ParameterObject parameterObject);
	/**
	 * 3.	获取客户 历史聊天记录（包括自己发送的信息）
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getHistoryMsg(ParameterObject parameterObject);
	/**
	 * 6.	获取用户客服基本信息
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getUserInfo(ParameterObject parameterObject);
	
	/**
	 * 客服 登陆
	 * @param parameterObject
	 * @return
	 */
	public ResultObject serviceLogin(ParameterObject parameterObject);
}
