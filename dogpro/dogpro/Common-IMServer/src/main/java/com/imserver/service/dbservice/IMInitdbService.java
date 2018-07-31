package com.imserver.service.dbservice;

import java.util.Map;

public interface IMInitdbService {
	/**
	 * 添加 在线用户集合
	 * @param userId
	 * @return
	 */
	public Map<String, Object> addOnline(Long userId);
	/**
	 * 用户 订阅当前 所在群组 topic
	 * @param userId
	 * @return
	 */
	public boolean subscribeGroup(Long userId,String client_id);
	/**
	 * 用户 退订 群组keyss
	 * @param userId
	 * @param keyss
	 * @return
	 */
	public boolean unsubscribeGroup(String client_id,String keyss);
	
	
}
