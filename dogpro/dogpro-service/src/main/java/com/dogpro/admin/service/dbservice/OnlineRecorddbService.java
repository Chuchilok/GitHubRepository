package com.dogpro.admin.service.dbservice;

import java.util.List;
import java.util.Map;

import com.dogpro.domain.model.OnlineRecord;

public interface OnlineRecorddbService {
	/**
	 * 41.	获取当前在线人数
	 * @return
	 */
	public Map<String, Object> getTotalOnlineUsers();
	/**
	 * 42.	分页获取在线人数记录数据
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getOnlineRecord(int pageNO,int pageSize);
	/**
	 * 43.	在线人数记录插入数据库
	 * @param recordNO
	 * @return
	 */
	public Map<String, Object> onlineRecordToDB(int recordNO);
	/**
	 * 获取用户在线数记录 总数
	 * @return
	 */
	public Long getTotalOnlineRecord();
	/**
	 * 获取数据库 在线用户数记录 总数
	 * @return
	 */
	public int getTotalDBonlineRecord();
	/**
	 * 获取数据库 在线用户数记录 总数
	 * @return
	 */
	public List<OnlineRecord> getDBonlineRecords(int pageNO,int pageSize);
}
