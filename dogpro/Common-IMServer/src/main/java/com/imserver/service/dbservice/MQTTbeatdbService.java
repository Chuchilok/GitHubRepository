package com.imserver.service.dbservice;

import java.util.Set;

import javax.ws.rs.GET;

public interface MQTTbeatdbService {
	/**
	 * 获取在线用户集合(mqtt心跳)
	 * @return
	 */
	public Set<String> getOnlineUser();
}
