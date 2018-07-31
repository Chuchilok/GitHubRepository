package com.dogpro.service.dbservice;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.domain.RedisDoglocation;
import com.dogpro.common.domain.RedisWalkingDogGroup;
import com.dogpro.domain.model.AreaSpace;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.PushMessage;
import com.dogpro.domain.model.PushUser;
import com.dogpro.lucene.index.DoglocationIndex;

public interface RedisdbService {
	public boolean setUser(Long userId, CheckUser checkUser);

	public CheckUser getUser(Long userId);

	public boolean setGroup(Long locationId, String keyss);

	public String getGroup(Long locationId);

	/**
	 * 用户发送信息 记录到redis
	 * 
	 * @param sendUID
	 * @param revUID
	 * @param content
	 * @param type
	 * @return
	 */
	public boolean setMsgSet(Long sendUID, Long revUID, String content,
			int type, Long msgcode, Float sendLongitude, Float sendLatitude);

	/**
	 * 修改MsgSet
	 * 
	 * @param sendUID
	 * @param msgCode
	 * @return
	 */
	public boolean updateMsgSet(Message message);

	/**
	 * 删除MsgSet中的某个key
	 * 
	 * @param msgkey
	 * @return
	 */
	public boolean deleteMsgSet(Long revUid, String msgkey);

	/**
	 * 从redis导出message记录
	 * 
	 * @return
	 */
	public Message popMsgFromRedis();

	public boolean pushIMsend2Redis(IMsend iMsend);

	public IMsend popIMsendfromRedis();

	/**
	 * 群组添加成员
	 * 
	 * @param doglocationId
	 * @param userId
	 * @return
	 */
	public boolean groupSADDmember(Long doglocationId, Long userId);

	/**
	 * 群组去除成员
	 * 
	 * @param doglocationId
	 * @param userId
	 * @return
	 */
	public boolean groupSREMmember(Long doglocationId, Long userId);

	/**
	 * 返回群组内所有userId
	 * 
	 * @param doglocationId
	 * @param userId
	 * @return
	 */
	public Set<String> groupSMEMBERS(Long doglocationId);

	/**
	 * 存入userId对应推送token K-V
	 * 
	 * @param userId
	 * @param pushtoken
	 * @return
	 */
	public boolean setUserPush(Long userId, String pushtoken, String nickname);

	/**
	 * 获取userId对应推送token K-V
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserPush(String userId);

	/**
	 * 删除userId对应推送token K-V
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteUserPush(String userId);

	/**
	 * 获取user对应 昵称
	 * 
	 * @param nickname
	 * @return
	 */
	public String getUserNickname(String userId);

	/**
	 * push userId对应token list
	 * 
	 * @param userId
	 * @param pushtoken
	 * @return
	 */
	public boolean pushUserPush(String userId, String pushtoken);

	/**
	 * pop userId对应token list
	 * 
	 * @return
	 */
	public PushUser popUserPuser();

	/**
	 * 添加userId记录 集合
	 * 
	 * @param userId
	 * @return
	 */
	public boolean SADDalluser(Long userId);

	/**
	 * 去除userId记录 集合
	 * 
	 * @param userId
	 * @return
	 */
	public boolean SREMalluser(Long userId);

	/**
	 * 返回所有用户userId 集合
	 * 
	 * @return
	 */
	public Set<String> SMEMBERSalluser();

	public boolean SADDonlineuser(Long userId);

	public boolean SREMonlineuser(Long userId);

	public Set<String> SMEMBERSonlineuser();
	//返回在线用户 总数
	public Long SCARDonlineuser();

	/**
	 * push 推送信息记录 list
	 * 
	 * @return
	 */
	public boolean pushPushRecord(PushMessage pushMessage);

	/**
	 * pop 推送信息记录 list
	 * 
	 * @return
	 */
	public PushMessage popPushRecord();

	/**
	 * 推送消息进队列
	 * 
	 * @param pushMessage
	 * @return
	 */
	public boolean pushPushMsg2Redis(PushMessage pushMessage);

	public boolean SADDuserUnread(String msgCode, Long revUid);

	public boolean SREMuserUnread(String msgCode, Long revUid);

	public Set<String> SMEMBERSuserUnread(Long revUid);

	public List<Message> getUserUnread(Long revUid);

	public Message getMsgSet(String revUid, String msgCode);

	public boolean SADDvisitorPush(String pushtoken);

	public boolean SREMvisitorPush(String pushtoken);

	public Set<String> SMEMBERSvisitorPush();

	public boolean SADDexitGroup(Long userId);
	
	public boolean SREMexitGroup(Long userId);
	
	public Set<String> SMEMBERSexitGroup();
	
	public boolean SADDjoinGroup(Long userId);
	
	public boolean SREMjoinGroup(Long userId);
	
	public Set<String> SMEMBERSjoinGroup();
	
//	public boolean setWalkingDogGroupEndTime(Long walkingdoggroupId,Long endTime);
//	
//	public Long getWalkingDogGroupEndTime(Long walkingdoggroupId);
//	
//	public boolean deleteWalkingDogGroupEndTime(Long walkingdoggroupId);

	public boolean pushUserOfflineTime(String userId, Long offlineTime);

	public String popUserOfflineTime();

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

	/**
	 * 处理好友 请求 同意 IM信息
	 * 
	 * @param sendUid
	 * @param revUid
	 * @param type
	 * @param content
	 * @param msgCode
	 */
	public void handleMsg(Long sendUid, Long revUid, int type, String content,
			Long msgCode);

	/**
	 * 遛狗地点入redis
	 * 
	 * @param dogLocation
	 * @return
	 */
	public boolean pushDoglocation(DogLocation dogLocation);

	/**
	 * 遛狗地点出reids 修改 索引
	 * 
	 * @return
	 */
	public DogLocation popDoglocation();

	// 记录用户昵称
	public boolean setUserNickname(Long userId, String nickname);

	// 获取用户昵称
	public String getUserNickname(Long userId);

	// 设置 用户群组消息免打扰状态
	public boolean setUserGroupDisturb(Long userId, int isDisturb);

	// 获取 用户群组消息免打扰状态
	public Integer getUserGroupDisturb(Long userId);

	// 记录 doglocation 部分信息
	public boolean setDolocation(RedisDoglocation redisDoglocation);

	// 获取doglocation 部分信息
	public RedisDoglocation getDoglocation(Long DoglocationId);

	// 记录 WalkingDogGroup 部分信息
	public boolean setWalkingDogGroup(RedisWalkingDogGroup redisWalkingDogGroup);

	// 删除 WalkingDogGroup 部分信息
	public boolean deleteWalkingDogGroup(Long userId);

	// 获取 WalkingDogGroup 部分信息
	public RedisWalkingDogGroup getWalkingDogGroup(Long userId);
	
	// access_token - openid 进redis
	public boolean setAccessToken_Openid(String access_token,String openid,int type);
	
	// 根据access_token 获取 openid
	public String getOpenidByAccessToken(String access_token,int type);
	
}
