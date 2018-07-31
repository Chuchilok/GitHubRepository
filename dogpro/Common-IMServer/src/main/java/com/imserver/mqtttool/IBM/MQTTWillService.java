package com.imserver.mqtttool.IBM;

import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dogpro.common.domain.IMsend;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.SpringInit;
import com.ibm.micro.client.mqttv3.MqttCallback;
import com.ibm.micro.client.mqttv3.MqttClientPersistence;
import com.ibm.micro.client.mqttv3.MqttConnectOptions;
import com.ibm.micro.client.mqttv3.MqttDeliveryToken;
import com.ibm.micro.client.mqttv3.MqttException;
import com.ibm.micro.client.mqttv3.MqttMessage;
import com.ibm.micro.client.mqttv3.MqttTopic;
import com.imserver.service.dbservice.IMGroupMemberdbService;
import com.imserver.service.dbservice.IMRedisdbService;

/**
 * Created by wuyr on 17-9-2 上午11:53.
 */
/*
 * 封装的MQTT
 */
public class MQTTWillService {
	private boolean isConnected;
	// MQTT客户端
	private MyMqttClient mMqttClient;
	// MQTT的Topic
	private MqttTopic mMqttTopic;
	private String senderId;
	private String receiverId;

	private String hostName = "";
	private String username = "";
	private String password = "";
	private String mqttserverid = "";
	private String ServerId;
	private String WillTopic;

	private BlockingQueue<IMsend> queue = new LinkedBlockingQueue<IMsend>();

	// private Object object2 = new Object();
	// private Boolean Flagi = false;

	// Integer ii=0;
	// 初始化client 和 topic
	public MQTTWillService(final String Serverid) throws MqttException {
		this.ServerId = Serverid;
		Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
		hostName = packagesMap.get("mqttprotocol")+"://" + packagesMap.get("mqttserverhost") + ":"
				+ packagesMap.get("mqttserverport");
		mqttserverid = packagesMap.get("mqttserverid").toString() + ServerId;
		WillTopic = packagesMap.get("mqttWillTopic").toString().trim();
		username = packagesMap.get("mqttusername").toString();
		password = packagesMap.get("mqttpwd").toString();
		mMqttClient = new MyMqttClient(hostName, mqttserverid, null);
		final IMRedisdbService imRedisdbService = (IMRedisdbService) SpringInit
				.getApplicationContext().getBean("IMRedisdbService");
		// mMqttTopic = mMqttClient.getTopic(senderId);
		mMqttClient.setCallback(new MqttCallback() {

			public void messageArrived(MqttTopic arg0, MqttMessage arg1)
					throws Exception {
				// 接收到信息
				// System.out.println("======================接收到信息======================");
				System.out
						.println("======================接收到遗嘱信息======================");
				System.out.println(arg0);
				System.out.println(arg1);
				try {
					String topic = arg0.toString();
					String content = arg1.toString();
					String userId = content.split("_")[1];
					Long offlineTime = (new Date()).getTime();
					// 处理在线用户集合(删除该用户id)
					imRedisdbService.SREMonlineuser(Long.parseLong(userId));
					// 记录用户mqtt离线时间
					imRedisdbService.pushUserOfflineTime(userId, offlineTime);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

			public void deliveryComplete(MqttDeliveryToken arg0) {
				// 发送信息成功
				// System.out.println("======================发送信息成功======================");
			}

			public void connectionLost(Throwable arg0) {
				// 连接断开
				System.out.println("======================连接断开======================");
			}
		});

		// 尝试连接成功
		if (Serverid.equals("AAA")) {
			tryConnect("AAAAA", "遗嘱", 2, false);
		} else if (tryConnect()) {
			System.out
					.println("======================连接成功======================"
							+ Serverid);
			// 订阅遗嘱主题
			subscribe(WillTopic, 0);

		} else {
			System.out
					.println("======================连接失败======================");
		}
	}

	// 尝试连接
	public boolean tryConnect() {
		try {
			MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
			mqttConnectOptions.setUserName(username);
			mqttConnectOptions.setPassword(password.toCharArray());
			mqttConnectOptions.setConnectionTimeout(60000);
			mMqttClient.connect(mqttConnectOptions);
			return isConnected = true;
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 尝试连接 设置遗嘱发布
	public boolean tryConnect(String willTopic, String payload, int qos,
			boolean retained) {
		try {

			MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
			mqttConnectOptions.setConnectionTimeout(60000);
			MqttTopic topic = mMqttClient.getTopic(willTopic);
			mqttConnectOptions
					.setWill(topic, payload.getBytes(), qos, retained);
			mMqttClient.connect(mqttConnectOptions);
			return isConnected = true;
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 设置接收者ID
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public void setTopic(String topic) {
		mMqttTopic = mMqttClient.getTopic(topic);
	}

	// 手动断开连接 (退出)
	public boolean disconnect() {
		if (isConnected) {
			try {
				mMqttClient.disconnect();
				mMqttClient = null;
				mMqttTopic = null;

				return !(isConnected = false);
			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// 订阅
	public boolean subscribe(String topic, int qos) {
		if (isConnected) {
			try {
				mMqttClient.subscribe(topic, qos);
				// receiverId = topic;
				return true;
			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// 取消订阅
	public boolean unSubscribe(String topic) {
		if (isConnected) {
			try {
				mMqttClient.unsubscribe(topic);
				return true;
			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// 发布消息
	public void publishMessage(String topic, String base64String) {
		// ii++;
		// System.out.println(this.ServerId+" send data index:"+ii);
		mMqttTopic = mMqttClient.getTopic(topic);
		if (isConnected && mMqttTopic != null) {
			try {
				mMqttTopic.publish(base64String.getBytes("utf-8"), 0, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 队列发送信息
	public void sendMsg(String token, String msg) {

		IMsend imsend = new IMsend();
		imsend.setToken(token);
		imsend.setContent(msg);
		try {
			queue.put(imsend);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// synchronized (queue) {
		// queue.add(imsend);
		// }
		// synchronized (object2) {
		// object2.notify();
		// }

	}
}
