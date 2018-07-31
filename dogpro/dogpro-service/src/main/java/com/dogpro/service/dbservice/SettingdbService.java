package com.dogpro.service.dbservice;

import java.util.Map;

public interface SettingdbService {
	/**
	 * 设置 setting
	 * @param userId
	 * @param notify
	 * @param sound
	 * @param shake
	 * @param handset
	 * @return
	 */
	public Map<String, Object> alterSetting(Long userId,int notify,int sound,int shake,int handset);
	/**
	 * 获取 setting 状态
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getSetting(Long userId);
	/**
	 * 设置初始化
	 * @param userId
	 * @return
	 */
	public boolean initSetting(Long userId);
	/**
	 * 设置 群组消息免打扰
	 * @param userId
	 * @param isDisturb
	 * @return
	 */
	public Map<String, Object> setGroupDisturb(Long userId,int isDisturb);
	
}
