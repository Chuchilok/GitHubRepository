package com.imserver.service.dbservice;

import java.util.List;
import java.util.Map;

import com.dogpro.domain.model.User;

public interface IMMessagedbService {
	/**
	 * 客户端发送信息接口
	 * @param sendUid
	 * @param revUid
	 * @param content
	 * @param type
	 * @param token
	 * @param md5
	 * @return
	 */
	public Map<String, Object> sendMsg(Long sendUid,Long revUid,String content,int type,String token,String md5,Float sendLongitude,Float sendLatitude);
	/**
	 * 客户端修改信息状态
	 * @param sendUid
	 * @param revUid
	 * @param type
	 * @param msgCode
	 * @return
	 */
	public Map<String, Object> updateMsg(Long sendUid,Long revUid,int state,int type,Long msgCode);
	/**
	 * 获取未读IM信息
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getUnread(Long userId,Long msgCode,int pageSize);
	/**
	 * 查看历史im记录
	 * @param sendUid
	 * @param isGroup
	 * @param msgCode
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getMsgList(Long revUid ,Long sendUid,int isGroup,Long msgCode,int pageSize);
	/**
	 * 	获取im用户信息（头像，昵称）
	 * @param imUserId
	 * @param isGroup
	 * @return
	 */
	public Map<String, Object> getIMInfo(Long userId,Long imUserId,int isGroup);
	
	/**
	 * 获取 后台客服用户
	 * @return
	 */
	public User getServiceUser();
	

}
