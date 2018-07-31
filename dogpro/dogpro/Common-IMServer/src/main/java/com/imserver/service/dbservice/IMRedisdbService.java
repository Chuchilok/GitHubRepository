package com.imserver.service.dbservice;


import java.util.List;
import java.util.Set;

import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.domain.IMmessage;
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.domain.RedisDoglocation;
import com.dogpro.common.domain.RedisWalkingDogGroup;
import com.dogpro.domain.model.GroupMessage;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.OnlineRecord;
import com.dogpro.domain.model.PushMessage;
import com.dogpro.domain.model.PushUser;

public interface IMRedisdbService {
	
	public Object getSignal();
	
	public boolean setUser(Long userId,CheckUser checkUser);
	public CheckUser getUser(Long userId);
	
	
	
	///在线用户 集合
	//增加在线用户
	public boolean SADDonlineuser(Long userId);
	//删除在线用户
	public boolean SREMonlineuser(Long userId);
	//返回在线用户集合
	public Set<String> SMEMBERSonlineuser();
	//返回在线用户 总数
	public Long SCARDonlineuser();
	
	
	public boolean updateOnlineuser(Set<String> userset);
	
	public boolean setGroup(Long locationId,String keyss);
	public String getGroup(Long locationId);
	/**
	 * 用户发送信息 记录到redis
	 * @param sendUID
	 * @param revUID
	 * @param content
	 * @param type
	 * @return
	 */
	public boolean setMsgSet(Long sendUID,Long revUID,String content,int type,Long msgcode,Float sendLongitude,Float sendLatitude );
	/**
	 * 修改MsgSet
	 * @param sendUID
	 * @param msgCode
	 * @return
	 */
	public boolean updateMsgSet(Message message);
	/**
	 * 删除MsgSet中的某个key
	 * @param msgkey
	 * @return
	 */
	public boolean deleteMsgSet(Long revUid,String msgkey);
	/**
	 * message记录 存入redis队列，返回msgKey
	 * @param msg
	 * @return
	 */
	public String pushMsg2Redis(IMmessage iMmessage);
	/**
	 * 从redis导出message记录
	 * @return
	 */
	public Message popMsgFromRedis();
	
	/**
	 * message更新记录 存入redis队列
	 * @param iMmessage
	 * @return
	 */
	public boolean pushMsgUpdate2Redis(Message message);
	
	public  boolean pushIMsend2Redis(IMsend iMsend);
	
	public  IMsend popIMsendfromRedis();
	
	/**
	 * 将单条群信息 拆分成 n条GroupMsg 插入list
	 * @param locationId
	 * @param iMsend
	 * @param keyss
	 * @return
	 */
	public String pushGroupMsg(IMmessage iMmessage);
	/**
	 * GroupMSg 出list
	 * @return
	 */
	public GroupMessage popGroupMsg();
	/**
	 * 更新GroupMsg状态
	 * @param revId
	 * @param keyss
	 * @param type
	 * @return
	 */
	public boolean updateGroupMsg(Long revId,String keyss,int type);
	
	
	public  int restest();
	
	public void handleMsg(String content);
	
	public void handleMsgThread(final String content);
	/**
	 * 群组添加成员
	 * @param doglocationId
	 * @param userId
	 * @return
	 */
	public boolean groupSADDmember(Long doglocationId, Long userId);
	/**
	 * 群组去除成员
	 * @param doglocationId
	 * @param userId
	 * @return
	 */
	public boolean groupSREMmember(Long doglocationId, Long userId);

	/**
	 * 返回群组内所有userId
	 * @param doglocationId
	 * @param userId
	 * @return
	 */
	public Set<String> groupSMEMBERS(Long doglocationId);
	/**
	 * 存入userId对应推送token K-V
	 * @param userId
	 * @param pushtoken
	 * @return
	 */
	public boolean setUserPush(Long userId,String pushtoken,String nickname);
	/**
	 * 获取userId对应推送token K-V
	 * @param userId
	 * @return
	 */
	public String getUserPush(String userId);
	/**
	 * 删除userId对应推送token K-V
	 * @param userId
	 * @return
	 */
	public boolean deleteUserPush(String userId);
	/**
	 * 获取user对应 昵称
	 * @param nickname
	 * @return
	 */
	public String getUserNickname(String userId);
	/**
	 * push userId对应token list
	 * @param userId
	 * @param pushtoken
	 * @return
	 */
	public boolean pushUserPush(String userId,String pushtoken);
	/**
	 * pop userId对应token list
	 * @return
	 */
	public PushUser popUserPuser();
	/**
	 * 添加userId记录 集合
	 * @param userId
	 * @return
	 */
	public boolean SADDalluser(Long userId);
	/**
	 * 去除userId记录 集合
	 * @param userId
	 * @return
	 */
	public boolean SREMalluser(Long userId);
	/**
	 * 返回所有用户userId 集合
	 * @return
	 */
	public Set<String> SMEMBERSalluser();
	
	
	/**
	 * 推送消息进队列
	 * @param pushMessage
	 * @return
	 */
	public boolean pushPushMsg2Redis(PushMessage pushMessage);
	/**
	 * 推送消息出队列
	 * @return
	 */
	public PushMessage popPushMsg();
	/**
	 * push AND推送消息入redis
	 * @param pushMessage
	 * @return
	 */
	public boolean pushANDPushMessage(IMsend iMsend);
	/**
	 * pop AND推送消息出redis
	 * @return
	 */
	public IMsend popANDPushMessage();
	
	/**
	 * push ios推送消息入redis
	 * @param pushMessage
	 * @return
	 */
	public boolean pushIOSPushMessage(IMsend iMsend);
	/**
	 * pop ios推送消息出redis
	 * @return
	 */
	public IMsend popIOSPushMessage();
	/**
	 * push 推送信息记录    list
	 * @return
	 */
	public boolean pushPushRecord(PushMessage pushMessage);
	/**
	 * pop 推送信息记录 list
	 * @return
	 */
	public PushMessage popPushRecord();
	
	public boolean SADDuserUnread(String msgCode,Long revUid);
	
	public boolean SREMuserUnread(String msgCode,Long revUid);
	
	public Set<String>  SMEMBERSuserUnread(Long revUid);
	
	public List<Message> getUserUnread(Long revUid);
	
	public Message getMsgSet(String revUid,String msgCode);
	
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

	
	public boolean pushUserOfflineTime(String userId,Long offlineTime);
	
	public String popUserOfflineTime();
	
	//记录 客户端 本地数据库 记录的最新信息的资源编码
	public boolean setUserMsgCode(Long userId,Long msgCode);

	//获取  客户端 本地数据库 记录的最新信息的资源编码
	public Long getUserMsgCode(Long userId);
	
	//记录用户昵称
	public boolean setUserNickname(Long userId,String nickname);
	
	//获取 用户 昵称
	public String getUserNickname(Long userId);
	
	//设置 用户群组消息免打扰状态 
	public boolean setUserGroupDisturb(Long userId,int isDisturb);
	
	//获取 用户群组消息免打扰状态 
	public Integer getUserGroupDisturb(Long userId);
	
	//记录 doglocation 部分信息
	public boolean setDolocation(RedisDoglocation redisDoglocation);
	
	//获取doglocation 部分信息
	public RedisDoglocation getDoglocation(Long DoglocationId);
	
	//记录 WalkingDogGroup 部分信息
	public boolean setWalkingDogGroup(RedisWalkingDogGroup redisWalkingDogGroup);
	
	//删除 WalkingDogGroup 部分信息
	public boolean deleteWalkingDogGroup(Long userId);
	
	//获取 WalkingDogGroup 部分信息
	public RedisWalkingDogGroup getWalkingDogGroup(Long userId);
	
	//在线用户记录  入队列
	public boolean LPUSHonlineRecord(OnlineRecord onlineRecord);
	
	//在线用户记录 总数量
	public Long LLENonlineRecord();
	
	//获取指定范围 在线用户记录
	public List<OnlineRecord> LRANGEonlineRecord(int start,int stop);
	
	//在线用户记录 出队列
	public OnlineRecord RPOPonlineRecord();
	
	public void testPush(String ss);
	
	public String testPop();
}
