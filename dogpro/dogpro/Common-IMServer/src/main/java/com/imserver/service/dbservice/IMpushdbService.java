package com.imserver.service.dbservice;

import java.util.List;
import java.util.Map;

import com.dogpro.domain.model.PushMessage;
import com.dogpro.domain.model.PushUser;


public interface IMpushdbService {
	/**
	 * 发送推送信息
	 * @param type
	 * @param content
	 * @param RevUid
	 * @return
	 */
	public Map<String, Object> sendPushMsg(int type,String content,Long RevUid,Long sendUid,Long targetId);
	public void handlePushMsg(PushMessage pushMessage);
	/**
	 * 更新数据库中 用户 推送token信息
	 * @param pushUser
	 * @return
	 */
	public boolean updateUserpush(PushUser pushUser);
	/**
	 * 推送信息记录 从reids 入 数据库
	 * @param pushMessage
	 * @return
	 */
	public boolean pushmsg2DB(PushMessage pushMessage);
	/**
	 * 获取朋友圈推送记录（分页）
	 * @param userId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getFirendCirclePush(Long userId,int pageNO,int pageSize);
}
