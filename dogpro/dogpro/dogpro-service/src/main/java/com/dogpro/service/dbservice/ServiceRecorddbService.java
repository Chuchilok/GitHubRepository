package com.dogpro.service.dbservice;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Update;

import com.dogpro.domain.model.ServiceRecord;
import com.dogpro.domain.model.User;

public interface ServiceRecorddbService {
	/**
	 * 获取客服 用户
	 * @return 
	 */
	public User getServiceUser();
	/**
	 * 获取 咨询过的 用户列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getUserList(int pageNo,int pageSize);
	/**
	 * 获取未读信息（下线后收到的信息）
	 * @param sendUid
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getUnreadMsg(Long userId,int pageNo,int pageSize);
	/**
	 * 获取客户 历史聊天记录（包括自己发送的信息）
	 * @param sendUid
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getHistoryMsg(Long userId,Long sendUid,Long msgCode , int pageSize);
	/**
	 * 检测 用户id 是否是 客服用户
	 * @param userId
	 * @return
	 */
	public boolean checkServiceID(Long userId);
	/**
	 * 插入 或 更新  serviceRecord 记录
	 * @param serviceRecord
	 * @return
	 */
	public boolean updateServiceRecordData(ServiceRecord serviceRecord);
	/**
	 * 
	 * @param phone
	 * @param pswd
	 * @return
	 */
	public Map<String, Object> serviceLogin(String phone,String pswd);
	/**
	 * 6.	获取用户客服基本信息
	 * @param sendUids
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> getUserInfo(Long sendUid);
	
	public List<User> getUserListAfterTime(Date afterTime);
}
