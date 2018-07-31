package com.imserver.mqtttool;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import redis.clients.jedis.Jedis;

import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.domain.IMmessage;
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.domain.PushMessage;
import com.dogpro.common.domain.SingletonObject;
import com.dogpro.common.tool.ObjectUtil;
import com.dogpro.common.tool.SpringInit;
import com.dogpro.service.dbservice.WalkingDogdbService;
import com.ibm.micro.client.mqttv3.MqttException;
import com.imserver.mqtttool.IBM.MQTTService;
import com.imserver.service.dbservice.IMRedisdbService;

public class MQTTRedisReadThread implements Runnable {

	private Jedis jedis = null;
	String key = "";
	// private final static int poolsize = 1000;
	// private static ExecutorService MQTTThreadPool = Executors
	// .newFixedThreadPool(poolsize);
	private MQTTService MQTTServiceList = null;
	AtomicLong counter = null;
	private IMRedisdbService imRedisdbService;
	// 这里引用了WalkingDogdbService 类的东西
	private WalkingDogdbService walkingDogdbService;

	public MQTTRedisReadThread(Jedis jedis, String key, int id,
			AtomicLong counter) {
		this.counter = counter;
		this.jedis = jedis;
		this.key = key;
		String ss = "FFF" + id;
		try {
			this.MQTTServiceList = new MQTTService(ss);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imRedisdbService = (IMRedisdbService) SpringInit
				.getApplicationContext().getBean("IMRedisdbService");
		walkingDogdbService = (WalkingDogdbService) SpringInit
				.getApplicationContext().getBean("WalkingDogdbService");
	}

	public void run() {

		while (true) {
			try {
//				final byte[] str = jedis.rpop(key.getBytes());
//				// counter.incrementAndGet();
//				if (null != str) {
//					IMsend iMsend = (IMsend) ObjectUtil.bytes2Object(str);
//					distributeMsg(iMsend);
//				} 
				//使用brpop
				final List<byte[]> result = jedis.brpop(10, key.getBytes());
				if(result!=null){
					IMsend iMsend = (IMsend) ObjectUtil.bytes2Object(result.get(1));
					distributeMsg(iMsend);
				}
				
			} catch (Exception e) {
				// 连接失败
				if (!jedis.isConnected()) {
					// 返回连接池里面
					jedis.close();
					// 重新获取连接
					jedis = MQTTjedisManager.instance().getJedis();
				}
			}

		}
	}

	public void distributeMsg(IMsend iMsend) {
		// 根据接受者id对应 token 判断是否离线 是否ios用户
		try {
			// IOS推送显示文字
			String iosPushContent = "";
			String jsonString = new String(Base64.base64ToByteArray(iMsend
					.getContent()), "utf-8");
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			IMmessage iMmessage = JSONObject.toJavaObject(jsonObject,
					IMmessage.class);
			// 接收者userId 集合
			Set<String> revUidSet = new HashSet<String>();
			// 判断消息类型
			int type = iMmessage.getType();
			// 是否群发标志
			boolean groupflag = false;
			JSONObject contentJson = null;
			Map<String, Object> map = null;
			String nickname = "";
			{
				switch (type) {
				case 1:
					// 好友请求1
					revUidSet.add(iMmessage.getRevUid() + "");
					contentJson = JSONObject
							.parseObject(iMmessage.getContent());
					map = contentJson;
					nickname = map.get("nickname").toString();
					iosPushContent = nickname + " 向你发送请求好友添加";
					break;
				case 2:
					// 好友请求回复2
					revUidSet.add(iMmessage.getRevUid() + "");
					contentJson = JSONObject
							.parseObject(iMmessage.getContent());
					map = contentJson;
					nickname = map.get("nickname").toString();
					iosPushContent = nickname + " 同意你的好友验证";
					break;
				case 3:
					// 好友文本消息3
					revUidSet.add(iMmessage.getRevUid() + "");
					contentJson = JSONObject
							.parseObject(iMmessage.getContent());
					map = contentJson;
					nickname = imRedisdbService.getUserNickname(iMmessage
							.getSendUid());
					String content = map.get("content").toString();
					content = content.length() > 20 ? content.substring(0, 20)
							+ "……" : content;
					iosPushContent = nickname + ":" + content;
					break;
				case 4:
					// 好友图片信息4
					revUidSet.add(iMmessage.getRevUid() + "");
					nickname = imRedisdbService.getUserNickname(iMmessage
							.getSendUid());
					iosPushContent = nickname + ":" + "[图片]";
					break;
				case 5:
					// 好友视频信息5
					revUidSet.add(iMmessage.getRevUid() + "");
					nickname = imRedisdbService.getUserNickname(iMmessage
							.getSendUid());
					iosPushContent = nickname + ":" + "[视频信息]";
					break;
				case 6:
					// 好友定位信息6
					revUidSet.add(iMmessage.getRevUid() + "");
					nickname = imRedisdbService.getUserNickname(iMmessage
							.getSendUid());
					iosPushContent = nickname + ":" + "[定位信息]";
					break;
				case 7:
					// 好友语音7
					revUidSet.add(iMmessage.getRevUid() + "");
					nickname = imRedisdbService.getUserNickname(iMmessage
							.getSendUid());
					iosPushContent = nickname + ":" + "[语音]";
					break;
				case 8:
					// 群信息8
					revUidSet.addAll(imRedisdbService.groupSMEMBERS(iMmessage
							.getRevUid()));
					groupflag = true;
					contentJson = JSONObject
							.parseObject(iMmessage.getContent());
					map = contentJson;
					String groupcontent = map.get("content").toString();
					nickname = imRedisdbService.getUserNickname(iMmessage
							.getSendUid());
					groupcontent = groupcontent.length() > 20 ? groupcontent
							.substring(0, 20) + "……" : groupcontent;
					iosPushContent = nickname + ":" + groupcontent;
					break;
				case 9:
					// 群视频信息 9
					revUidSet.addAll(imRedisdbService.groupSMEMBERS(iMmessage
							.getRevUid()));
					groupflag = true;
					nickname = imRedisdbService.getUserNickname(iMmessage
							.getSendUid());
					iosPushContent = nickname + ":" + "[视频信息]";
					break;
				case 10:
					// 群图片信息 10
					revUidSet.addAll(imRedisdbService.groupSMEMBERS(iMmessage
							.getRevUid()));
					groupflag = true;
					nickname = imRedisdbService.getUserNickname(iMmessage
							.getSendUid());
					iosPushContent = nickname + ":" + "[图片]";
					break;
				case 11:
					// 群语音 11
					revUidSet.addAll(imRedisdbService.groupSMEMBERS(iMmessage
							.getRevUid()));
					groupflag = true;
					nickname = imRedisdbService.getUserNickname(iMmessage
							.getSendUid());
					iosPushContent = nickname + ":" + "[语音]";
					break;
				case 12:
					// 群定位信息 12
					revUidSet.addAll(imRedisdbService.groupSMEMBERS(iMmessage
							.getRevUid()));
					groupflag = true;
					nickname = imRedisdbService.getUserNickname(iMmessage
							.getSendUid());
					iosPushContent = nickname + ":" + "[定位信息]";
					break;
				case 13:
					// 系统 个人信息
					revUidSet.add(iMmessage.getRevUid() + "");
					contentJson = JSONObject
							.parseObject(iMmessage.getContent());
					map = contentJson;
					int code13 = Integer.valueOf(map.get("code").toString());
					switch (code13) {
					case 1:
						// 退出群组
						revUidSet.remove(iMmessage.getSendUid().toString());
						iosPushContent = map.get("msg").toString();
						break;
					default:
						break;
					}
					break;
				case 14:
					// 系统群组信息
					revUidSet.addAll(imRedisdbService.groupSMEMBERS(iMmessage
							.getRevUid()));
					// 判断 code类型
					contentJson = JSONObject
							.parseObject(iMmessage.getContent());
					map = contentJson;
					int code14 = Integer.valueOf(map.get("code").toString());
					switch (code14) {
					case 1:
						// 新人 加入 群聊 踢出发送者本人
						revUidSet.remove(iMmessage.getSendUid().toString());
						iosPushContent = map.get("msg").toString();
						break;
					case 2:
						// 群组成员 退出群组
						iosPushContent = map.get("msg").toString();
						break;
					default:
						break;
					}
					groupflag = true;
					break;
				default:

					break;
				}
			}

			// 在线用户集合
			Set<String> onlineSet = imRedisdbService.SMEMBERSonlineuser();
			// ios处理集合
			Set<String> iosSet = new HashSet<String>();
			// AND处理集合
			Set<String> andSet = new HashSet<String>();
			// 接收者userId 是否在线 是否在ios
			// 若 非 群发
			if (!groupflag) {
				for (String revuid : revUidSet) {
					String pushtoken = imRedisdbService.getUserPush(revuid);
					// 判断是否在线
					if (onlineSet.contains(revuid)) {
						MQTTServiceList.sendMsg(iMsend.getToken(),
								iMsend.getContent());
					} else {
						if (pushtoken != null && pushtoken.startsWith("I")) {
							// ios 离线 推送
							iosSet.add(revuid);
						} else if (pushtoken != null
								&& pushtoken.startsWith("A")) {
							// and 离线推送
							andSet.add(revuid);
						}
					}
				}
			}
			// 若群发
			else {
				for (String revuid : revUidSet) {
					String pushtoken = imRedisdbService.getUserPush(revuid);
					if (!onlineSet.contains(revuid)) {
						// IM离线
						if (pushtoken != null && pushtoken.startsWith("I")) {
							// IOS用户
							// redis 判断是否开启消息免打扰
							int isDisturb = imRedisdbService
									.getUserGroupDisturb(Long.valueOf(revuid));
							if (isDisturb == 0) {
								// 防止自己发的信息 自己收到推送
								if (!(iMmessage.getType() < 13 && iMmessage
										.getSendUid().toString().equals(revuid))) {
									iosSet.add(revuid);
								}

							}
						} else if (pushtoken != null
								&& pushtoken.startsWith("A")) {
							// and用户
							// redis 判断是否开启消息免打扰
							int isDisturb = imRedisdbService
									.getUserGroupDisturb(Long.valueOf(revuid));
							if (isDisturb == 0) {
								// 防止自己发的信息 自己收到推送
								if (!(iMmessage.getType() < 13 && iMmessage
										.getSendUid().toString().equals(revuid))) {
									andSet.add(revuid);
								}
							}
						}
					}
				}
				MQTTServiceList.sendMsg(iMsend.getToken(), iMsend.getContent());
			}
			// 这里把iosSet 和 iMmessage 传入队列
			for (String revUid : iosSet) {
				// 推送类型 1朋友圈 2印象 3群组 4私聊 5新的好友
				int IMTYPE = 0;
				PushMessage pushMessage = new PushMessage();
				pushMessage.setSenduid(iMmessage.getSendUid());// 把id推进去
				String pushtoken = "";
				if (groupflag) {// 若为群发
					// 推送类型 1朋友圈 2印象 3群组 4私聊 5好友请求 6好友验证回复
					IMTYPE = 3;
					// 群id RevUid;
					Long userId = Long.parseLong(revUid);
					pushtoken = imRedisdbService.getUserPush(userId + "");
					pushMessage.setRevuid(userId);
					pushMessage.setTargetid(iMmessage.getRevUid());
					switch (type) {
					case 8:
						// 群信息8
						pushMessage.setContent(iosPushContent);
						break;
					case 9:
						// 群视频信息 9
						pushMessage.setContent(iosPushContent);
						break;
					case 10:
						// 群图片信息 10
						pushMessage.setContent(iosPushContent);
						break;

					case 11:
						// 群语音 11
						pushMessage.setContent(iosPushContent);
						break;
					case 12:
						// 群定位信息 12
						pushMessage.setContent(iosPushContent);
						break;
					case 14:
						// 群系统信息 14
						pushMessage.setContent(iosPushContent);
						IMTYPE = 7;
						break;
					default:
						pushMessage.setContent("信息…………");
						IMTYPE = 7;
						break;
					}
				} else {// 好友消息
					// 推送类型 1朋友圈 2印象 3群组 4私聊 5好友请求 6好友验证回复 7系统
					IMTYPE = 4;
					pushtoken = imRedisdbService.getUserPush(revUid);// 好友Token
					Long userId = Long.parseLong(revUid);
					pushMessage.setRevuid(userId);
					pushMessage.setTargetid(iMmessage.getSendUid());
					// System.out.println("666666:>>测试好友消息显示的token:" +
					// pushtoken);
					switch (type) {
					case 1:// 好友请求
						pushMessage.setContent(iosPushContent);
						IMTYPE = 5;
						break;
					case 2:// 好友请求回复
						pushMessage.setContent(iosPushContent);
						IMTYPE = 6;
						break;
					case 3:
						// 好友文本消息3
						pushMessage.setContent(iosPushContent);
						break;
					case 4:
						// 好友图片信息4
						pushMessage.setContent(iosPushContent);
						break;
					case 5:
						// 好友视频信息5
						pushMessage.setContent(iosPushContent);
						break;
					case 6:
						// 好友定位信息6
						pushMessage.setContent(iosPushContent);
						break;
					case 7:
						// 好友语音7
						pushMessage.setContent(iosPushContent);
						break;
					case 13:
						IMTYPE = 7;
						// 个人系统信息 13
						pushMessage.setContent(iosPushContent);
						break;
					default:
						pushMessage.setContent("信息…………");
						break;
					}
				}
				pushMessage.setType(IMTYPE);
				String content = JSONObject.toJSONString(pushMessage);
				iMsend.setToken(pushtoken);// IOS_xxxxxxxxxxxxxxxxxxxxxxx
				iMsend.setContent(content);
				imRedisdbService.pushIOSPushMessage(iMsend);
			}

			// 这里把andSet 推送 入Redis队列
			for (String revUid : andSet) {
				// 推送类型 1朋友圈 2印象 3群组 4私聊 5新的好友
				int IMTYPE = 0;
				PushMessage pushMessage = new PushMessage();
				pushMessage.setSenduid(iMmessage.getSendUid());// 把id推进去
				String pushtoken = "";
				if (groupflag) {// 若为群发
					// 推送类型 1朋友圈 2印象 3群组 4私聊 5好友请求 6好友验证回复
					IMTYPE = 3;
					// 群id RevUid;
					Long userId = Long.parseLong(revUid);
					pushtoken = imRedisdbService.getUserPush(userId + "");
					pushMessage.setRevuid(userId);
					pushMessage.setTargetid(iMmessage.getRevUid());
					switch (type) {
					case 8:
						// 群信息8
						pushMessage.setContent(iosPushContent);
						break;
					case 9:
						// 群视频信息 9
						pushMessage.setContent(iosPushContent);
						break;
					case 10:
						// 群图片信息 10
						pushMessage.setContent(iosPushContent);
						break;

					case 11:
						// 群语音 11
						pushMessage.setContent(iosPushContent);
						break;
					case 12:
						// 群定位信息 12
						pushMessage.setContent(iosPushContent);
						break;
					case 14:
						// 群系统信息 14
						pushMessage.setContent(iosPushContent);
						IMTYPE = 7;
						break;
					default:
						pushMessage.setContent("信息…………");
						IMTYPE = 7;
						break;
					}
				} else {// 好友消息
					// 推送类型 1朋友圈 2印象 3群组 4私聊 5好友请求 6好友验证回复 7系统
					IMTYPE = 4;
					pushtoken = imRedisdbService.getUserPush(revUid);// 好友Token
					Long userId = Long.parseLong(revUid);
					pushMessage.setRevuid(userId);
					pushMessage.setTargetid(iMmessage.getSendUid());
					switch (type) {
					case 1:// 好友请求
						pushMessage.setContent(iosPushContent);
						IMTYPE = 5;
						break;
					case 2:// 好友请求回复
						pushMessage.setContent(iosPushContent);
						IMTYPE = 6;
						break;
					case 3:
						// 好友文本消息3
						pushMessage.setContent(iosPushContent);
						break;
					case 4:
						// 好友图片信息4
						pushMessage.setContent(iosPushContent);
						break;
					case 5:
						// 好友视频信息5
						pushMessage.setContent(iosPushContent);
						break;
					case 6:
						// 好友定位信息6
						pushMessage.setContent(iosPushContent);
						break;
					case 7:
						// 好友语音7
						pushMessage.setContent(iosPushContent);
						break;
					case 13:
						IMTYPE = 7;
						// 个人系统信息 13
						pushMessage.setContent(iosPushContent);
						break;
					default:
						pushMessage.setContent("信息…………");
						break;
					}
				}
				pushMessage.setType(IMTYPE);
				String content = JSONObject.toJSONString(pushMessage);
				iMsend.setToken(pushtoken.substring(4));// AND_xxxxxxxxxxxxxxxxxxxxxxx
				iMsend.setContent(content);
				imRedisdbService.pushANDPushMessage(iMsend);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}
