package com.dogpro.admin.service.impl.dbservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.admin.service.dbservice.AdminRedisdbService;
import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.domain.RedisDoglocation;
import com.dogpro.common.tool.JedisUtil;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.ObjectUtil;
import com.dogpro.dao.WalkingDogGroupMapper;
import com.dogpro.domain.model.AreaSpace;
import com.dogpro.domain.model.OnlineRecord;

@Service("AdminRedisdbService")
public class AdminRedisdbServiceImpl implements AdminRedisdbService {
	@Autowired
	private WalkingDogGroupMapper walkingDogGroupMapper;
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
		// onlineRecord 记录队列 key
		String onlineRecordKey = "onlineRecordList";
		
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
	
	public boolean LPUSHonlineRecord(OnlineRecord onlineRecord) {
		try {
			byte[] key = onlineRecordKey.getBytes();
			byte[] value = ObjectUtil.object2Bytes(onlineRecord);
			jedisUtil.lpush(key, value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Long LLENonlineRecord() {
		Long len = null;
		try {
			byte[] key = onlineRecordKey.getBytes();
			len = jedisUtil.llen(key);
		} catch (Exception e) {
			
		}
		return len;
	}
	public List<OnlineRecord> LRANGEonlineRecord(int start, int stop) {
		List<OnlineRecord> records = new ArrayList<OnlineRecord>();
		try {
			byte[] key = onlineRecordKey.getBytes();
			List<byte[]> values = jedisUtil.lrange(key, start, stop);
			for(byte[] value:values){
				OnlineRecord onlineRecord = (OnlineRecord) ObjectUtil.bytes2Object(value);
				records.add(onlineRecord);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return records;
	}

	public OnlineRecord RPOPonlineRecord() {
		OnlineRecord onlineRecord = null;
		try {
			byte[] key = onlineRecordKey.getBytes();
			onlineRecord = (OnlineRecord) ObjectUtil.bytes2Object(jedisUtil.rpop(key));
		} catch (Exception e) {
		}
		return onlineRecord;
	}
}
