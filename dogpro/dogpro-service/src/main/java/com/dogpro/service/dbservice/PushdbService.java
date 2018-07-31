package com.dogpro.service.dbservice;

import java.util.Map;

import com.dogpro.domain.model.PushUser;


public interface PushdbService {
	/**
	 * 发送推送信息
	 * @param type
	 * @param content
	 * @param RevUid
	 * @return
	 */
	public Map<String, Object> sendPushMsg(int type,String content,Long RevUid,Long sendUid,Long targetId);
	/**
	 * 更新数据库中 用户 推送token信息
	 * @param pushUser
	 * @return
	 */
	public boolean updateUserpush(PushUser pushUser);
}
