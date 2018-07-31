package com.dogpro.service.impl.dbservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.domain.IMmessage;
import com.dogpro.common.domain.IMreceive;
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.domain.PushToken;
import com.dogpro.common.domain.RedisDoglocation;
import com.dogpro.common.domain.RedisWalkingDogGroup;
import com.dogpro.common.tool.JedisUtil;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.ObjectUtil;
import com.dogpro.dao.DogLocationMapper;
import com.dogpro.dao.WalkingDogGroupMapper;
import com.dogpro.domain.model.AreaSpace;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.PushMessage;
import com.dogpro.domain.model.PushUser;
import com.dogpro.service.dbservice.RedisdbService;

@Service("redisdbService")
public class RedisdbServiceImpl implements RedisdbService {
	@Autowired
	private WalkingDogGroupMapper walkingDogGroupMapper;
	@Autowired
	private DogLocationMapper dogLocationMapper;
	// 用户
	private static JedisUtil jedisUtil;
	// 聊天群组set 聊天信息k-v 退群list
	private static JedisUtil jedisUtil2;
	// 消息List
	private static JedisUtil jedisUtil3;
	// 遛狗 记录信息
	private static JedisUtil jedisUtil4;
	// PushMsg List
	private static JedisUtil jedisUtil5;
	// AreaSpace List 遛狗轨迹 遛狗地点
	private static JedisUtil jedisUtil0;


	// 所有用户userid 集合 key
	String alluserkey = "AllUser";
	// 在线用户userid 集合 key
	String onlineuserkey = "OnlineUser";
	// 离线用户 离线时间  list key
	String userOfflineTime = "userOfflineTime";
	// im user对应token k-v key
	String imuserkey = "CheckUser";
	// im group对应keyss k-v key
	String imgroupkey = "G_";
	// im message消息体对应 list key
	String immessagekey = "Message";
	// im User未读信息 集合 key
	String imUserUnread  = "imUserUnread";
	// im 群组message信息对应 list key
	String imgroupmsgkey = "GroupMsg";
	// im IMsend消息体对应 list key
	String imimsendkey = "IMsend";
	// 遛狗群组成员locationid-userid 集合 key
	String groupuserkey = "G_U_";
	// 遛狗群组成员 结束遛狗 判断退出群组 集合 
	String exitGroupkey = "exitGroup";
	// 遛狗群组成员 加入遛狗组 判断加入时间群组 集合
	String joinGroupkey = "joinGroup";
	// 遛狗组记录 成员对应结束遛狗时间
	String userEndTimekey = "userEndTime";
	// 推送 user对应pushtoken k-v key
	String pushuserkey = "UP_";
	// 推送 游客对应pushtoken 集合 key
	String pushVisitorSet = "pushVisitorSet";
	// 推送user对应pushtoken list key
	String pushuserlist = "pushuserlist";
	// 发布 推送消息 pushmessage list key
	String pushmsgkey = "PushMsg";
	// AND推送消息列表 list key
	String ANDpushMsg = "ANDpushMsg";
	// ios推送消息列表 list key
	String IOSpushMsg = "IOSpushMsg";
	// 推送信息 记录 list key
	String pushMsgRecord = "pushRecord";
	//区域空间数据记录 
	String areaSpaceKey = "AS_";
	
	String areaSpaceList = "areaSpaceList";
	//遛狗地点 list key
	String doglocationList = "doglocationList";
	// 用户-昵称 hash表 key
	String userNicknameKey = "User-Nickname";
	// 用户群组免打扰hash表key 
	String userGroupDisturbKey = "userGroupDisturb";
	// Doglocation部分信息 key
	String redisDoglocationKey = "redisDoglocation";
	// walkingDogGroup部分信息key
	String redisWalkingDogGroupKey = "redisWalkingDogGroup";
	
	// accesstoken - openid type=0 微信
	String WeiXin_accesstoken_openid = "WeiXin_accesstoken_openid";
	// accesstoken - openid type=1 QQ
	String QQ_accesstoken_openid = "QQ_accesstoken_openid";
	
	
	static {
		Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
		jedisUtil = new JedisUtil(Integer.parseInt(packagesMap.get(
				"redisUserdb").toString()));
		jedisUtil2 = new JedisUtil(Integer.parseInt(packagesMap.get(
				"redisGroupSetdb").toString()));
		jedisUtil3 = new JedisUtil(Integer.parseInt(packagesMap.get(
				"redisMsgListdb").toString()));
		jedisUtil4 = new JedisUtil(Integer.parseInt(packagesMap.get(
				"redisIMsenddb").toString()));
		jedisUtil5 = new JedisUtil(Integer.parseInt(packagesMap.get(
				"redisPushdb").toString()));
		
		jedisUtil0 = new JedisUtil(Integer.parseInt(packagesMap.get(
				"redisAreaSpacedb").toString()));
	}

	public boolean setUser(Long userId, CheckUser checkUser) {
		// TODO Auto-generated method stub
		try {
			byte[] key = imuserkey.getBytes();
			byte[] field = userId.toString().getBytes();
			byte[] value = ObjectUtil.object2Bytes(checkUser);
			jedisUtil.hset(key,field,value);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public CheckUser getUser(Long userId) {
		// TODO Auto-generated method stub
		try {
			byte[] key = imuserkey.getBytes();
			byte[] field = userId.toString().getBytes();
			CheckUser checkUser = (CheckUser) ObjectUtil.bytes2Object(jedisUtil
					.hget(key, field));
			return checkUser;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return null;

	}

	public boolean setGroup(Long locationId, String keyss) {
		// TODO Auto-generated method stub
		byte[] key = ("G_" + locationId).getBytes();
		try {
			jedisUtil.set(key, ObjectUtil.object2Bytes(keyss));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String getGroup(Long locationId) {
		// TODO Auto-generated method stub
		byte[] key = ("G_" + locationId).getBytes();
		try {
			String keyss = ObjectUtil.bytes2Object(jedisUtil.get(key))
					.toString();
			return keyss;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 用户发送信息 记录到redis
	 * 
	 * @param sendUID
	 * @param revUID
	 * @param content
	 * @param type
	 * @return message时间戳标识keyss
	 */
	public boolean setMsgSet(Long sendUID, Long revUID, String content,
			int type, Long msgCode,Float sendLongitude,Float sendLatitude) {
		// TODO Auto-generated method stub
		Date currentTime = new Date();
		byte[] key = (revUID + "_" + msgCode).getBytes();
		try {
			// 初始化msg对象
			Message message = new Message();
			message.setSenduserid(sendUID);
			message.setAcceptuserid(revUID);
			message.setType(type);
			message.setContent(content);
			message.setMsgcode(msgCode);
			message.setSendlongitude(sendLongitude);
			message.setSendlatitude(sendLatitude);
			message.setState(0);
			message.setSendtimes(currentTime);
			message.setAddtimes(currentTime);
			message.setUpdatetimes(currentTime);
			jedisUtil2.set(key, ObjectUtil.object2Bytes(message));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	/**
	 * 修改MsgSet
	 * 
	 * @param sendUID
	 * @param msgCode
	 * @return
	 */
	public boolean updateMsgSet(Message message) {
		// TODO Auto-generated method stub
		String msgKey = message.getAcceptuserid() + "_" + message.getMsgcode();
		byte[] key = msgKey.getBytes();
		Date currentTime = new Date();
		try {
			message.setUpdatetimes(currentTime);
			if (message.getState() == 2) {
				message.setAccepttimes(currentTime);
			}
			jedisUtil2.set(key, ObjectUtil.object2Bytes(message));
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 删除MsgSet中的某个key
	 * 
	 * @param msgkey
	 * @return
	 */
	public boolean deleteMsgSet(Long revUid, String msgkey) {
		byte[] key = (revUid + "_" + msgkey).getBytes();
		try {
			jedisUtil2.del(key);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 从redis导出message记录
	 * 
	 * @return
	 */
	public Message popMsgFromRedis() {
		// TODO Auto-generated method stub
		Message message = null;
		byte[] key = (immessagekey).getBytes();
		Date date = new Date();
		try {
			// if (jedisUtil3.llen(key) > 0) {
			// 队列取出msg标识
			byte[] keyssbyte = jedisUtil3.rpop(key);

			if (keyssbyte != null) {
				String keyss = new String(keyssbyte);
				String[] keysslist = keyss.split("_");
				String rediskey = keysslist[0] + "_" + keysslist[1];
				int state = Integer.parseInt(keysslist[2]);
				// 从set中获取message对象
				byte[] bytes = jedisUtil2.get(rediskey.getBytes());
				if (bytes != null) {
					message = (Message) ObjectUtil.bytes2Object(bytes);
				}
				switch (state) {
				case 0:
//					// 发送出
//					SADDuserUnread(message.getMsgcode() + "",
//							message.getAcceptuserid());
					break;
				case 1:
					// 已读
					message.setState(state);
					;
					message.setUpdatetimes(date);
					// 修改user对应未读信息集合
//					SREMuserUnread(message.getMsgcode() + "",
//							message.getAcceptuserid());
					break;
				case 2:
					// 未处理
					message.setState(state);
					message.setUpdatetimes(date);
					message.setAccepttimes(date);
					updateMsgSet(message);
					break;
				default:
					break;
				}
				// // 从set中删除该key
				// deleteMsgSet(keyss);
				// 从msg队列中取出信息记录
				// byte[] bytes = jedisUtil3.rpop(key);
				// message = (Message) ObjectUtil.bytes2Object(bytes);
				//删除 信息记录  msgset k-v
				deleteMsgSet(message.getAcceptuserid(),
						message.getMsgcode() + "");
				return message;
			}
		} catch (Exception e) {
			
		}
		return null;
	}

	/**
	 * message更新记录 存入redis队列
	 * 
	 * @param iMmessage
	 * @return
	 */
	public boolean pushMsgUpdate2Redis(Message message) {
		try {
			String key = immessagekey;
			Long revUid = message.getAcceptuserid();
			Long msgCode = message.getMsgcode();
			int state = message.getState();
			jedisUtil3.lpush(key, revUid + "_" + msgCode + "_" + state);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public IMsend popIMsendfromRedis() {
		// TODO Auto-generated method stub
		IMsend iMsend = null;
		byte[] key = ("IMsend").getBytes();
		try {
			if (jedisUtil4.llen(key) > 0) {
				iMsend = (IMsend) ObjectUtil.bytes2Object(jedisUtil4.rpop(key));
				return iMsend;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean SADDonlineuser(Long userId) {
		// TODO Auto-generated method stub
		String key = onlineuserkey;
		try {
			jedisUtil.sadd(key, userId + "");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean SREMonlineuser(Long userId) {
		// TODO Auto-generated method stub
		String key = onlineuserkey;
		try {
			jedisUtil.srem(key, userId + "");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public Set<String> SMEMBERSonlineuser() {
		// TODO Auto-generated method stub
		String key = onlineuserkey;
		Set<String> sets = null;
		try {
			sets = jedisUtil.smembers(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sets;
	}

	public Long SCARDonlineuser(){
		String key = onlineuserkey;
		Long total  = null;
		try {
			total = jedisUtil.scard(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return total;
	}
	/**
	 * 
	 * @param doglocationId
	 * @param userId
	 * @return
	 */
	public boolean groupSADDmember(Long doglocationId, Long userId) {
		String key = (groupuserkey + doglocationId);
		try {
			jedisUtil2.sadd(key, userId + "");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 群组去除成员
	 * 
	 * @param doglocationId
	 * @param userId
	 * @return
	 */
	public boolean groupSREMmember(Long doglocationId, Long userId) {
		String key = (groupuserkey + doglocationId);
		try {
			jedisUtil2.srem(key, userId + "");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 返回群组内所有userId
	 * 
	 * @param doglocationId
	 * @param userId
	 * @return
	 */
	public Set<String> groupSMEMBERS(Long doglocationId) {
		Set<String> sets = null;
		String key = (groupuserkey + doglocationId);
		try {
			sets = jedisUtil2.smembers(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sets;
	}

	/**
	 * 存入userId对应推送token K-V
	 * 
	 * @param userId
	 * @param pushtoken
	 * @return
	 */
	public boolean setUserPush(Long userId, String pushtoken, String nickname) {
		byte[] keys = (pushuserkey + userId).getBytes();
		try {
			PushToken pushObject = new PushToken();
			pushObject.setPtoken(pushtoken);
			pushObject.setSenderName(nickname);
			jedisUtil.set(keys, ObjectUtil.object2Bytes(pushObject));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 删除userId对应推送token K-V
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteUserPush(String userId) {
		byte[] keys = (pushuserkey + userId).getBytes();
		try {
			jedisUtil.del(keys);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	/**
	 * 获取userId对应推送token K-V
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserPush(String userId) {
		byte[] keys = (pushuserkey + userId).getBytes();
		String pushtoken = null;
		try {
			PushToken pushObject = (PushToken) ObjectUtil
					.bytes2Object(jedisUtil.get(keys));
			pushtoken = pushObject.getPtoken();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pushtoken;
	}
	
	/**
	 * 获取user对应 昵称
	 * 
	 * @param nickname
	 * @return
	 */
	public String getUserNickname(String userId) {
		byte[] keys = (pushuserkey + userId).getBytes();
		String nickname = null;
		try {
			PushToken pushObject = (PushToken) ObjectUtil
					.bytes2Object(jedisUtil.get(keys));
			nickname = pushObject.getSenderName();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nickname;
	}

	/**
	 * push userId对应token list
	 * 
	 * @param userId
	 * @param pushtoken
	 * @return
	 */
	public boolean pushUserPush(String userId, String pushtoken) {
		byte[] key = pushuserlist.getBytes();
		try {
			PushUser pushUser = new PushUser();
			pushUser.setUserid(Long.parseLong(userId));
			pushUser.setToken(pushtoken);
			pushUser.setState(1);
			String ss = pushtoken.split("_")[0];
			Date date = new Date();
			pushUser.setAddtimes(date);
			pushUser.setUpdatetimes(date);
			if (ss.equals("AND")) {
				pushUser.setType(1);
			} else {
				pushUser.setType(2);
			}
			jedisUtil.lpush(key, ObjectUtil.object2Bytes(pushUser));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * pop userId对应token list
	 * 
	 * @return
	 */
	public PushUser popUserPuser() {
		byte[] key = pushuserlist.getBytes();
		PushUser pushUser = null;
		try {
			pushUser = (PushUser) ObjectUtil.bytes2Object(jedisUtil.rpop(key));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pushUser;
	}

	/**
	 * 添加userId记录 集合
	 * 
	 * @param userId
	 * @return
	 */
	public boolean SADDalluser(Long userId) {
		try {
			jedisUtil.sadd(alluserkey, userId + "");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 去除userId记录 集合
	 * 
	 * @param userId
	 * @return
	 */
	public boolean SREMalluser(Long userId) {
		try {
			jedisUtil.srem(alluserkey, userId + "");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 返回所有用户userId 集合
	 * 
	 * @return
	 */
	public Set<String> SMEMBERSalluser() {
		Set<String> sets = null;
		try {
			sets = jedisUtil.smembers(alluserkey);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sets;
	}

	public boolean pushPushMsg2Redis(PushMessage pushMessage) {
		// TODO Auto-generated method stub
		byte[] key = pushmsgkey.getBytes();
		try {
			byte[] objectBytes = ObjectUtil.object2Bytes(pushMessage);
			jedisUtil5.lpush(key, objectBytes);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return false;
	}

	/**
	 * push 推送信息记录 list
	 * 
	 * @return
	 */
	public boolean pushPushRecord(PushMessage pushMessage) {
		byte[] key = pushMsgRecord.getBytes();
		try {
			jedisUtil5.lpush(key, ObjectUtil.object2Bytes(pushMessage));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * pop 推送信息记录 list
	 * 
	 * @return
	 */
	public PushMessage popPushRecord() {
		byte[] key = pushMsgRecord.getBytes();
		PushMessage pushMessage = null;
		try {
			pushMessage = (PushMessage) ObjectUtil.bytes2Object(jedisUtil5
					.rpop(key));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pushMessage;
	}

	public boolean pushIMsend2Redis(IMsend iMsend) {
		// TODO Auto-generated method stub
		byte[] key = (imimsendkey).getBytes();
		try {
			jedisUtil4.lpush(key, ObjectUtil.object2Bytes(iMsend));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean SADDuserUnread(String msgCode, Long revUid) {
		// TODO Auto-generated method stub
		try {
			String key = imUserUnread + "_" + revUid;
			jedisUtil3.sadd(key, msgCode);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean SREMuserUnread(String msgCode, Long revUid) {
		// TODO Auto-generated method stub
		try {
			String key = imUserUnread + "_" + revUid;
			jedisUtil3.srem(key, msgCode);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public Set<String> SMEMBERSuserUnread(Long revUid) {
		// TODO Auto-generated method stub
		Set<String> unreadSet = null;
		try {
			String key = imUserUnread + "_" + revUid;
			unreadSet = jedisUtil3.smembers(key);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return unreadSet;
	}

	public List<Message> getUserUnread(Long revUid) {
		// TODO Auto-generated method stub
		List<Message> result = new ArrayList<Message>();
		try {
			Set<String> unreadSet = SMEMBERSuserUnread(revUid);
			List<Long> sortList = new ArrayList<Long>();
			// 根据时间戳排序
			for (String key : unreadSet) {
				sortList.add(Long.parseLong(key));
			}
			Collections.sort(sortList);
			// 获取对应信息体
			for (Long msgCode : sortList) {
				Message message = getMsgSet(revUid + "", msgCode + "");
				if (message != null) {
					result.add(message);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public Message getMsgSet(String revUid, String msgCode) {
		// TODO Auto-generated method stub
		byte[] key = (revUid + "_" + msgCode).getBytes();
		Message result = null;
		try {
			byte[] value = jedisUtil2.get(key);
			result = (Message) ObjectUtil.bytes2Object(value);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


	public boolean SADDvisitorPush(String pushtoken) {
		// TODO Auto-generated method stub
		String key = pushVisitorSet;
		try {
			jedisUtil.sadd(key, pushtoken);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean SREMvisitorPush(String pushtoken) {
		// TODO Auto-generated method stub
		String key = pushVisitorSet;
		try {
			jedisUtil.srem(key, pushtoken);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public Set<String> SMEMBERSvisitorPush() {
		// TODO Auto-generated method stub
		String key = pushVisitorSet;
		Set<String> result = null;
		try {
			result = jedisUtil.smembers(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean SADDexitGroup(Long userId) {
		// TODO Auto-generated method stub
		String key = exitGroupkey;
		try {
			String value = userId.toString();
			jedisUtil4.sadd(key, value);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean SREMexitGroup(Long userId) {
		// TODO Auto-generated method stub
		String key = exitGroupkey;
		try {
			String value = userId.toString();
			jedisUtil4.srem(key, value);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public Set<String> SMEMBERSexitGroup() {
		// TODO Auto-generated method stub
		String key = exitGroupkey;
		Set<String> result = null;
		try {
			result = jedisUtil4.smembers(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean SADDjoinGroup(Long userId) {
		// TODO Auto-generated method stub
		String key = joinGroupkey;
		try {
			String value = userId.toString();
			jedisUtil4.sadd(key, value);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean SREMjoinGroup(Long userId) {
		// TODO Auto-generated method stub
		String key = joinGroupkey;
		try {
			String value = userId.toString();
			jedisUtil4.srem(key, value);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public Set<String> SMEMBERSjoinGroup() {
		// TODO Auto-generated method stub
		String key = joinGroupkey;
		Set<String> result = null;
		try {
			result = jedisUtil4.smembers(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
//	public boolean setWalkingDogGroupEndTime(Long walkingdoggroupId,Long endTime){
//		try {
//			String key = userEndTimekey;
//			String field = walkingdoggroupId.toString();
//			String value = endTime.toString();
//			jedisUtil4.hset(key, field, value);
//		} catch (Exception e) {
//			return false;
//		}
//		return true;
//	}
//	
//	public Long getWalkingDogGroupEndTime(Long walkingdoggroupId){
//		Long endTime = null;
//		try {
//			String key = userEndTimekey;
//			String field = walkingdoggroupId.toString();
//			endTime = Long.parseLong(jedisUtil4.hget(key, field));
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return endTime;
//	}
//	public boolean deleteWalkingDogGroupEndTime(Long walkingdoggroupId){
//		try {
//			byte[] key = userEndTimekey.getBytes();
//			byte[]field = walkingdoggroupId.toString().getBytes();
//			jedisUtil4.hdel(key, field);
//		} catch (Exception e) {
//			return false;
//		}
//		return true;
//	}
	public boolean pushUserOfflineTime(String userId, Long offlineTime) {
		// TODO Auto-generated method stub
		String key = userOfflineTime;
		try {
			jedisUtil.lpush(key, userId+"_"+offlineTime);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public String popUserOfflineTime() {
		// TODO Auto-generated method stub
		String key = userOfflineTime;
		String value = null;
		try {
			value = jedisUtil.rpop(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
	}

	/**
	 * 区域空间  数据进入 redis 队列   jedisUtil0
	 */
	public boolean pushAreaSpace(AreaSpace areaSpace) {
		byte[] key = areaSpaceList.getBytes();
		try {
			if (null != areaSpace) {
				jedisUtil0.lpush(key, ObjectUtil.object2Bytes(areaSpace));
			}
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 从redis队列取出   区域空间
	 */
	public AreaSpace popAreaSpace() {
		byte[] key  = areaSpaceList.getBytes();
		AreaSpace value = null;
		try {
			byte[] rpop = jedisUtil0.rpop(key);
			if (null != rpop) {
				value = (AreaSpace) ObjectUtil.bytes2Object(rpop);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * set存入redis 
	 */
	public boolean setAreaSpaceMap(AreaSpace areaSpace) {
		try {
			if (null != areaSpace) {
				byte[] key = (areaSpaceKey + areaSpace.getDoglocationId()).getBytes();//AS_1
				jedisUtil0.set(key, ObjectUtil.object2Bytes(areaSpace));
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 从redis取出来
	 */
	public AreaSpace getAreaSpaceMap(Long dogLocationId) {
		AreaSpace areaSpace = null;
		try {
			byte[] key = (areaSpaceKey + dogLocationId).getBytes();//AS_1
			byte[] bs = jedisUtil0.get(key);
			if (null != bs) {
				areaSpace = (AreaSpace) ObjectUtil.bytes2Object(bs);
			}
			return areaSpace;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return areaSpace;
	}
	
	//处理好友 请求 同意 IM信息/系统群组信息
	public void handleMsg(Long sendUid, Long revUid,int type,String content,Long msgCode) {
		try {
			IMmessage iMmessage = new IMmessage();
			iMmessage.setSendUid(sendUid);
			iMmessage.setRevUid(revUid);
			iMmessage.setType(type);
			iMmessage.setContent(content);
			iMmessage.setMsgCode(msgCode);
			// 转发的发布topic
			String Retoken = null;
			{
				switch (type) {
				case 1:
					// 好友请求1
					Retoken = getUser(iMmessage.getRevUid()).getToken();
					break;
				case 2:
					// 好友请求回复2
					Retoken = getUser(iMmessage.getRevUid()).getToken();
					break;
				case 3:
					// 好友文本3
					Retoken = getUser(iMmessage.getRevUid()).getToken();
					break;
				case 13:
					//系统个人信息
					Retoken = getUser(iMmessage.getRevUid()).getToken();
				case 14:
					//系统群组信息
					Retoken = getGroup(iMmessage.getRevUid());
				default:

					break;
				}
			}
			if(Retoken!=null){
				// 包装转发信息体
				IMreceive imreceive = new IMreceive();
				imreceive.setSendUid(sendUid);
				imreceive.setRevUid(revUid);
				imreceive.setMsgCode(msgCode+"");
				imreceive.setContent(content);
				imreceive.setType(type);
				imreceive.setMillisTime(msgCode);
				String base64send = Base64.byteArrayToBase64(JSONObject.toJSON(imreceive)
						.toString().getBytes("utf-8"));
				IMsend imsend = new IMsend();
				imsend.setToken(Retoken);
				imsend.setContent(base64send);
				pushIMsend2Redis(imsend);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean pushDoglocation(DogLocation dogLocation) {
		// TODO Auto-generated method stub
		byte[] key = doglocationList.getBytes();
		try {
			jedisUtil0.lpush(key, ObjectUtil.object2Bytes(dogLocation));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public DogLocation popDoglocation() {
		// TODO Auto-generated method stub
		byte[] key = doglocationList.getBytes();
		DogLocation dogLocation = null;
		try {
			dogLocation = (DogLocation) ObjectUtil.bytes2Object(jedisUtil0.rpop(key));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dogLocation;
	}
	
	public boolean setUserNickname(Long userId, String nickname) {
		try {
			String key = userNicknameKey;
			String field = userId.toString();
			String value = nickname;
			jedisUtil.hset(key, field, value);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public String getUserNickname(Long userId) {
		String value = null;
		try {
			String key = userNicknameKey;
			String field = userId.toString();
			value = jedisUtil.hget(key, field);
		} catch (Exception e) {
			
		}
		return value;
	}
	
	public boolean setUserGroupDisturb(Long userId, int isDisturb) {
		try {
			String key = userGroupDisturbKey;
			String field = userId.toString();
			String value = isDisturb+"";
			jedisUtil.hset(key, field, value);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public Integer getUserGroupDisturb(Long userId) {
		// TODO Auto-generated method stub
		Integer isDisturb = 0;
		try {
			String key = userGroupDisturbKey;
			String field = userId.toString();
			String value = jedisUtil.hget(key, field);
			if(value!=null){
				isDisturb = Integer.valueOf(value);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isDisturb;
	}
	
	
	public boolean setDolocation(RedisDoglocation redisDoglocation) {
		try {
			byte[] key = redisDoglocationKey.getBytes();
			byte[] field = redisDoglocation.getId().toString().getBytes();
			byte[] value = ObjectUtil.object2Bytes(redisDoglocation);
			jedisUtil4.hset(key, field, value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public RedisDoglocation getDoglocation(Long DoglocationId) {
		RedisDoglocation redisDoglocation = null;
		try {
			byte[] key = redisDoglocationKey.getBytes();
			byte[] field = DoglocationId.toString().getBytes();
			redisDoglocation = (RedisDoglocation) ObjectUtil.bytes2Object(jedisUtil4.hget(key, field));
		} catch (Exception e) {
		}
		return redisDoglocation;
	}

	public boolean setWalkingDogGroup(RedisWalkingDogGroup redisWalkingDogGroup) {
		try {
			byte[] key = redisWalkingDogGroupKey.getBytes();
			byte[] field = redisWalkingDogGroup.getUserid().toString().getBytes();
			byte[] value = ObjectUtil.object2Bytes(redisWalkingDogGroup);
			jedisUtil4.hset(key, field, value);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public boolean deleteWalkingDogGroup(Long userId){
		try {
			byte[] key = redisWalkingDogGroupKey.getBytes();
			byte[] field = userId.toString().getBytes();
			jedisUtil4.hdel(key, field);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public RedisWalkingDogGroup getWalkingDogGroup(Long userId) {
		RedisWalkingDogGroup redisWalkingDogGroup = null;
		try {
			byte[] key = redisWalkingDogGroupKey.getBytes();
			byte[] field =userId.toString().getBytes();
			redisWalkingDogGroup = (RedisWalkingDogGroup) ObjectUtil.bytes2Object(jedisUtil4.hget(key, field));
		} catch (Exception e) {
		}
		return redisWalkingDogGroup;
	}

	public boolean setAccessToken_Openid(String access_token, String openid,
			int type) {
		try {
			String key;
			String field;
			String value;
			switch (type) {
			case 0:
				//微信
				key = WeiXin_accesstoken_openid;
				field = access_token;
				value = openid;
				break;
			case 1:
				// QQ
				key = QQ_accesstoken_openid;
				field = access_token;
				value = openid;
			default:
				return false;
			}
			jedisUtil.hset(key, field, value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String getOpenidByAccessToken(String access_token, int type) {
		String openid = null;
		try {
			String key;
			String field;
			switch (type) {
			case 0:
				//微信
				key = WeiXin_accesstoken_openid;
				field = access_token;
				break;
			case 1:
				// QQ
				key = QQ_accesstoken_openid;
				field = access_token;
			default:
				return null;
			}
			openid = jedisUtil.hget(key, field);
		} catch (Exception e) {
		}
		return openid;
	}
}
