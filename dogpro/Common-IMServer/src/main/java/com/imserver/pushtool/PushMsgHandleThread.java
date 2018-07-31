package com.imserver.pushtool;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.tool.SpringInit;
import com.dogpro.domain.model.PushMessage;
import com.imserver.service.dbservice.IMRedisdbService;

public class PushMsgHandleThread implements Runnable {

	// 线程编号
	private int id;
	// 安卓推送mqttServer 线程
	private Thread ANDthread;
	// IOS推送mqttServer 线程
	private Thread IOSthread;
	// 总的userId集合
	private Set<String> totalsets = new HashSet<String>();
	// 在线的userId集合
	private Set<String> onlinesets = new HashSet<String>();
	// 离线的userId集合
	private Set<String> offlinesets = new HashSet<String>();
	//游客的 pushtoken集合
	private Set<String> visitorSets = new HashSet<String>();
	// 安卓端pushToken集合
	private Map<String, String> ANDsets = new HashMap<String, String>();
	// ios端pushToken集合
	private Map<String, String> iOSsets = new HashMap<String, String>();

	public PushMsgHandleThread(int id) {
		this.id = id;
//		ANDthread = new Thread(new PushMsgMQTTThread(this.id));
		ANDthread = new Thread(new PushMsgUmengThread());
		IOSthread = new Thread(new IOSPushMsgThread());
	}

	public void run() {
		//启动安卓处理线程
		ANDthread.start();
		//启动IOS处理线程
		IOSthread.start();
		IMRedisdbService redisdbService = (IMRedisdbService) SpringInit
				.getApplicationContext().getBean("IMRedisdbService");
		while (true) {
			try {
				// redis库pushMsg List 出队列
				PushMessage pushMessage = redisdbService.popPushMsg();
				
				if (pushMessage != null) {
					// 清空总的userId集合
					totalsets.clear();
					// 安卓端userId集合
					ANDsets.clear();
					// ios端userId集合
					iOSsets.clear();
					// 游客的pushtoken集合
					visitorSets.clear();
					// 根据类型判断要发送的userId总集合
					int type = pushMessage.getType();
					//推送类型 1朋友圈    2印象	3群组	4私聊	5新的好友
					int pushType = 0;
					switch (type) {
					case 1:
						// 普通朋友圈(所有人)
						pushType = 1;
						// 获取在线的userId
						totalsets = redisdbService.SMEMBERSalluser();
						//去除本人
						totalsets.remove(pushMessage.getSenduid().toString());
						break;
					case 2:
						// 求助朋友圈(所有人)
						pushType = 1;
						// 获取在线的userId
						totalsets = redisdbService.SMEMBERSalluser();
						totalsets.remove(pushMessage.getSenduid().toString());
						break;
					case 3:
						// 朋友圈发布者收到评论
						pushType = 1;
						totalsets.add(pushMessage.getRevuid() + "");
						totalsets.remove(pushMessage.getSenduid().toString());
						break;
					case 4:
						// 朋友圈评论他人
						pushType = 1;
						totalsets.add(pushMessage.getRevuid() + "");
						totalsets.remove(pushMessage.getSenduid().toString());
						break;
					case 5:
						// 点赞
						pushType = 1;
						totalsets.add(pushMessage.getRevuid() + "");
						totalsets.remove(pushMessage.getSenduid().toString());
						break;
					case 6:
						// 印象提示
						pushType = 2;
						totalsets.add(pushMessage.getRevuid() + "");
						totalsets.remove(pushMessage.getSenduid().toString());
						break;
					case 7:
						// 遛狗结束提示(群组)
						pushType = 3;
						totalsets = redisdbService.groupSMEMBERS(pushMessage
								.getRevuid());
						//去除本人
						totalsets.remove(pushMessage.getSenduid().toString());
						break;
					case 8:
						// 退出遛狗组提示(群组)
						pushType = 3;
						totalsets = redisdbService.groupSMEMBERS(pushMessage
								.getRevuid());
						//出去本人
						totalsets.remove(pushMessage.getSenduid().toString());
						break;
					case 9:
						// 系统信息(所有人)
						pushType = 7;
						totalsets = redisdbService.SMEMBERSalluser();
						break;
					default:
						break;
					}
					// 在线的userId集合
					onlinesets = redisdbService.SMEMBERSonlineuser();

					// 根据userid查找redis库，获取其设备token
					for (String userId : totalsets) {
						// 如果用户在线
						if (onlinesets.contains(userId)) {
							String pushtoken = redisdbService.getUserPush(userId);
							if(pushtoken!=null){
								// 判断是and设备还是ios设备
								if (pushtoken.startsWith("A")) {
									ANDsets.put(userId,pushtoken);
								} else if (pushtoken.startsWith("I")) {
									iOSsets.put(userId,pushtoken);
								} else {
									// 用户不存在推送token
									offlinesets.add(userId);
								}
							}
						} else {
							// 用户不在线
							//iOSsets.put(userId, redisdbService.getUserPush(userId));//TODO 测试
							String pushtoken = redisdbService.getUserPush(userId);
							if(pushtoken!=null){
								// 判断是and设备还是ios设备
								if (pushtoken.startsWith("A")) {
									ANDsets.put(userId,pushtoken);
								} else if (pushtoken.startsWith("I")) {
									iOSsets.put(userId,pushtoken);
								} else {
									// 用户不存在推送token
									offlinesets.add(userId);
								}
							}
						}
					}
					if(type ==1||type==2||type==9){
					visitorSets = redisdbService.SMEMBERSvisitorPush();
					//判断 游客推送Token
					for(String pushtoken:visitorSets){
						if (pushtoken.startsWith("A")) {
							ANDsets.put("0",pushtoken);
						} else if (pushtoken.startsWith("I")) {
							iOSsets.put("0",pushtoken);
						}
					}
					}
					//安卓处理推送信息
					for(String userId:ANDsets.keySet()){
						try {
							IMsend iMsend = new IMsend();
							//每一条推送消息设置接受者id
							pushMessage.setRevuid(Long.parseLong(userId));
							//记录插入redis
							redisdbService.pushPushRecord(pushMessage);
							if(pushMessage.getType()!=5){
								//除去点赞
								pushMessage.setType(pushType);
								String content = JSONObject.toJSONString(pushMessage);
								String pushtoken = ANDsets.get(userId);
								iMsend.setToken(pushtoken.substring(4, pushtoken.length()));
//								String base64 = Base64.byteArrayToBase64(content.toString().getBytes("utf-8"));
								iMsend.setContent(content);
								redisdbService.pushANDPushMessage(iMsend);
							}
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//处理推送信息 （IOS）
					for(String userId:iOSsets.keySet()){
						IMsend iMsend = new IMsend();
						//每一条推送消息设置接受者id
						pushMessage.setRevuid(Long.parseLong(userId));
						//记录插入redis
						redisdbService.pushPushRecord(pushMessage);
						if(pushMessage.getType()!=5){
							//除去点赞
							pushMessage.setType(pushType);
							String content = JSONObject.toJSONString(pushMessage);
							//这里是IOS的发送数据：
							iMsend.setToken(iOSsets.get(userId));//IOS_xxxxxxxxxxxxxxxxxxxxxxx    
							iMsend.setContent(content);
							redisdbService.pushIOSPushMessage(iMsend);
						}
					}
					/*
					//离线 推送记录插入redis
					for(String userId:offlinesets){
						//每一条推送消息设置接受者id
						pushMessage.setRevuid(Long.parseLong(userId));
						//记录插入redis
						redisdbService.pushPushRecord(pushMessage);//TODO 
					}
					*/
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}


	}

}
