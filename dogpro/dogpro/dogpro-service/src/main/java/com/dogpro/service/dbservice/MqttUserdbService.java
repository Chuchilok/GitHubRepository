package com.dogpro.service.dbservice;

import java.util.Map;

public interface MqttUserdbService {
	/**
	 * 添加mqtt用户 
	 * @param userId
	 * @param account
	 * @param pwds
	 */
	public Map<String, Object> addMqttUser(Long userId,String username,String pwds);
	/**
	 * 修改 mqtt用户 密码
	 * @param userId
	 * @param pwds
	 */
	public Map<String, Object> alterPwdsByUserId(Long userId,String pwds);
	/**
	 * 修改 mqtt用户  用户名
	 * @param userId
	 * @param username
	 * @return
	 */
	public Map<String, Object> alterUsernameByUserId(Long userId,String username);
	/**
	 * 根据userId 获取mqtt用户账号 密码
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getMqttUser(Long userId);
	
}
