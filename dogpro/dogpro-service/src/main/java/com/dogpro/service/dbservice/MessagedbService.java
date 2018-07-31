package com.dogpro.service.dbservice;

import com.dogpro.domain.model.Message;


/**
 * 消息接口
 * @author Administrator
 *
 */
public interface MessagedbService {

	int publishMessage(Message message,String mediaUrl,String mediaSubUrl);

	int publishMessage(Message message, long groupid,String mediaUrl,String mediaSubUrl);
	/**
	 * 插入message数据到数据库
	 * @param message
	 * @return
	 */
	int insertMessage(Message message);
	
	int insertIMMessage(Long sendUid, Long revUid, int type, String content, Long msgCode);
	
	public boolean redis2DB(Message message);
}
