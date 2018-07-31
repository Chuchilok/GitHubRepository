package com.dogpro.admin.service.dbservice;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.domain.RedisDoglocation;
import com.dogpro.domain.model.AreaSpace;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.OnlineRecord;
import com.dogpro.domain.model.PushMessage;
import com.dogpro.domain.model.PushUser;
import com.dogpro.lucene.index.DoglocationIndex;

public interface AdminRedisdbService {
	
	public boolean setUser(Long userId, CheckUser checkUser);

	public CheckUser getUser(Long userId);
	
	public boolean SADDalluser(Long userId);
	
	// 记录用户昵称
	public boolean setUserNickname(Long userId, String nickname);
	
	/**
	 * push 区域空间进队列
	 * 
	 * @param pushMessage
	 * @return
	 */
	public boolean pushAreaSpace(AreaSpace AreaSpace);

	/**
	 * pop 区域空间 取出来
	 * 
	 * @return
	 */
	public AreaSpace popAreaSpace();

	/**
	 * 获取userId对应推送token K-V
	 * 
	 * @param userId
	 * @return
	 */
	/**
	 * hmset存入redis
	 * 
	 * @param areaSpace
	 * @return
	 */
	public boolean setAreaSpaceMap(AreaSpace areaSpace);

	/**
	 * 从redis取出来
	 * 
	 * @param dogLocationId
	 * @return
	 */
	public AreaSpace getAreaSpaceMap(Long dogLocationId);

	// 记录 doglocation 部分信息
	public boolean setDolocation(RedisDoglocation redisDoglocation);

	// 获取doglocation 部分信息
	public RedisDoglocation getDoglocation(Long DoglocationId);

	public boolean setGroup(Long locationId, String keyss);

	public String getGroup(Long locationId);

	// 返回在线用户 总数
	public Long SCARDonlineuser();

	// 在线用户记录 入队列
	public boolean LPUSHonlineRecord(OnlineRecord onlineRecord);

	// 在线用户记录 总数量
	public Long LLENonlineRecord();

	// 获取指定范围 在线用户记录
	public List<OnlineRecord> LRANGEonlineRecord(int start, int stop);

	// 在线用户记录 出队列
	public OnlineRecord RPOPonlineRecord();
}
