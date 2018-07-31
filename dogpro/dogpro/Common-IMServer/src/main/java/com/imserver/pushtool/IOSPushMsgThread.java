package com.imserver.pushtool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.domain.PushMessage;
import com.dogpro.common.tool.SpringInit;
import com.imserver.service.dbservice.IMRedisdbService;
import com.zpush.Notification;
import com.zpush.NotificationBuilder;
import com.zpush.PushManager;
import com.zpush.PushManagerImpl;
import com.zpush.Statistic;
import com.zpush.util.DebugFlag;

public class IOSPushMsgThread implements Runnable {
	PushManager IOSpushManager;
	Thread r;

	public IOSPushMsgThread() {
		try {
			IOSpushManager = SpringInit.getApplicationContext().getBean(
					PushManagerImpl.class);
			// 从队列里拿 出
			r = new Thread(new Runnable() {
				public void run() {
					while (true) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Statistic statistic = IOSpushManager.getStatistic();
						if (statistic != null && statistic.getQueueSize() > 0) {
							if (DebugFlag.debug) {
								System.out.println("得到统计信息>>>>>>>>>>>>>"
										+ statistic.toString());
							}
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void run() {
		// r.start();//统计信息线程
		IMRedisdbService imRedisdbService = (IMRedisdbService) SpringInit
				.getApplicationContext().getBean("IMRedisdbService");
		while (true) {
			try {

				IMsend iMsend = imRedisdbService.popIOSPushMessage();// IOS拿数据
				if (iMsend != null) {
					JSONObject json = JSONObject.parseObject(iMsend
							.getContent());
					PushMessage obj = JSONObject.toJavaObject(json,
							PushMessage.class);
					//暂时放这里，后面封装一下
					// UserInfodbService 获取对应的用户昵称
					// DogLocationdbService 获取对应群的别名或者地区名显示出来
					Map<String, Object> para = new HashMap<String, Object>();
					para.put("type", obj.getType());
					para.put("targetId", obj.getTargetid());
					Notification notification = new NotificationBuilder()
							.setToken(iMsend.getToken().substring(4))
							.setSound("default")
							.setBadge(1)
							.setAlert(obj.getContent())
							.setSendTitleName(obj.getSenduid()+">>用户id"+obj.getRevuid())
							.setAlert(obj.getContent())
							.setUserProperty("params", para)
							.build();
					// 用来判断用户是否在线，如不在线，把数据加回redis
//					Set<String> onlinesets = imRedisdbService
//							.SMEMBERSonlineuser();
//					boolean flag = true;
//					for (String userId : onlinesets) {
//						if (userId.equals(obj.getRevUid().toString())) {// 判断是否还在线
//							flag = false;
//						}
//					}
					IOSpushManager.push(notification);// 加入推送队列
//					if (flag) {
//						imRedisdbService.pushPushRecord(obj);//把信息加入到未发送的队列
//						System.out.println("对方可能下线，未能发送："+notification.payloadJSONString());
//					}
					// IOSpushManager.push(notification);//加入推送队列
					Statistic statistic = IOSpushManager.getStatistic();
					System.out.println("得到统计信息>>>"
							+ statistic.toString());
				}
				else{
					Thread.sleep(500);
				}
				
			} catch (Exception e) {
				System.out.println("加入推送队列 >>出现错误！");
				e.printStackTrace();
			}
		}
	}

}
