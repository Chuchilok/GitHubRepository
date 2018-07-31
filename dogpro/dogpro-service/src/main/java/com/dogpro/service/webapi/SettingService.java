package com.dogpro.service.webapi;


import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

public interface SettingService {
	/**
	 * 设置 setting
	 * @param userId
	 * @param notify
	 * @param sound
	 * @param shake
	 * @param handset
	 * @return
	 */
	public ResultObject alterSetting(ParameterObject parameterObject);
	/**
	 * 获取 setting 状态
	 * @param userId
	 * @return
	 */
	public ResultObject getSetting(ParameterObject parameterObject);
	/**
	 * 设置 用户 群组 消息免打扰
	 * @param parameterObject
	 * @return
	 */
	public ResultObject setGroupDisturb(ParameterObject parameterObject);
	
}
