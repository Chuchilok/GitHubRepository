package com.webpublish.mqtttool;

import com.webpublish.domain.model.IMsend;

public interface MQTTServiceInt {

	//尝试连接
	public abstract boolean tryConnect();

	//尝试连接  设置遗嘱发布 
	public abstract boolean tryConnect(String willTopic, String payload,
			int qos, boolean retained);

	//设置接收者ID
	public abstract void setReceiverId(String receiverId);

	public abstract void setTopic(String topic);

	//手动断开连接 (退出)
	public abstract boolean disconnect();

	//订阅
	public abstract boolean subscribe(String topic, int qos);

	//取消订阅
	public abstract boolean unSubscribe(String topic);

	//发布消息
	public abstract void publishMessage(String topic, String base64String);

	// 队列发送信息
	public abstract void sendMsg(String token, String msg);

	// 队列发送信息
	public abstract void sendMsg(IMsend iMsend);

}