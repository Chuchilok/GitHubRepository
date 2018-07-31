package com.imserver.mqtttool.IBM;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.net.ssl.SSLSocketFactory;

import com.alibaba.druid.util.Base64;
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.ibm.micro.client.mqttv3.MqttCallback;
import com.ibm.micro.client.mqttv3.MqttConnectOptions;
import com.ibm.micro.client.mqttv3.MqttDeliveryToken;
import com.ibm.micro.client.mqttv3.MqttException;
import com.ibm.micro.client.mqttv3.MqttMessage;
import com.ibm.micro.client.mqttv3.MqttTopic;
import com.ibm.micro.internal.security.SSLSocketFactoryFactory;


/**
 * Created by wuyr on 17-9-2 上午11:53.
 */
/*
 * 封装的MQTT
 * */
public class TestMQTTService {
    private boolean isConnected;
    //MQTT客户端
    private MyMqttClient mMqttClient;
    //MQTT的Topic(订阅主题)
    private MqttTopic mMqttTopic;
    //连接地址
    private String hostName = "";
    //mqtt账号名
    private String username;
    //mqtt账号密码
    private String password;
	//mqtt clientid(唯一)
    private String mqttserverid = "";
	private String ServerId;
	private String receiverId; 
	private BlockingQueue<IMsend> queue = new LinkedBlockingQueue<IMsend>();
//	private Object object2 = new Object();
//	private Boolean Flagi = false;
	private Long userId;
	private String token;
	//Integer ii=0;
    //初始化client 和 topic
    public TestMQTTService(Long userId,String token) throws MqttException {
    	this.userId = userId;
    	this.token = token;
    	hostName = "ssl://" + "192.168.1.200"+ ":"
				+ "8883";
//    	hostName = "tcp://192.168.1.200:1883";
//    	hostName = "ssl://" + "appim.petman666.com"+ ":"
//				+ "8883";
    	mqttserverid = "U"+userId+"_"+ token;
    	username = "admin";
    	password ="123456789a";
        mMqttClient = new MyMqttClient(hostName, mqttserverid, null);
        //mMqttTopic = mMqttClient.getTopic(senderId);
        mMqttClient.setCallback(new MqttCallback() {
			
			public void messageArrived(MqttTopic arg0, MqttMessage arg1)
					throws Exception {
				//接收到信息
				System.out.println("======================接收到信息======================");
				System.out.println(arg1);
//				System.out.println(arg0+"  :  "+new String(Base64.base64ToByteArray(arg1.toString()),"utf-8"));
			}
			
			public void deliveryComplete(MqttDeliveryToken arg0) {
				//发送信息成功
//				System.out.println("======================发送信息成功======================");
			}
			
			public void connectionLost(Throwable arg0) {
				//连接断开
				System.out.println("======================连接断开======================");
//				synchronized (Flagi){
//					Flagi = false;
//				}
			}
		});
        
        //尝试连接成功
        if(userId!=0){
        	String willTopic = "200";
        	String payload = "遗嘱信息";
        	int qos = 2;
        	boolean retained = false;
        	if(tryConnect(willTopic, payload, qos, retained)){
        	System.out.println("======================连接成功======================"+mqttserverid);
        	subscribe(token, 2);
        	String onlineTopic = "mqttOnline";
        	String onlinepayload = "UserId_"+userId;
        	//发布上线时间
        	publishMessage(onlineTopic,onlinepayload);
        	
        	}
        }
        else if(tryConnect()){
        	System.out.println("======================连接成功======================"+mqttserverid);
        	subscribe(token, 2);
        	String willTopic = "mqttOnline";
        	String payload = "UserId_"+userId;
        	int qos = 2;
        	boolean retained = false;
        	//发布上线时间
        	publishMessage("mqttOnline",payload);
        }else {
        	System.out.println("======================连接失败======================");
		}
    }

   

  //尝试连接
    public boolean tryConnect() {
        try {
            
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setUserName(username);
            mqttConnectOptions.setPassword(password.toCharArray());
            mqttConnectOptions.setConnectionTimeout(60000);
            mqttConnectOptions.setKeepAliveInterval(20);
            
            mMqttClient.connect(mqttConnectOptions);
            return isConnected = true;
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return false;
    }

  //尝试连接  设置遗嘱发布 
    public boolean tryConnect(String willTopic,String payload,int qos,boolean retained) {
        try {
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setConnectionTimeout(60000);
            MqttTopic topic = mMqttClient.getTopic(willTopic);
            mqttConnectOptions.setWill(topic, payload.getBytes(), qos, retained);
            mqttConnectOptions.setUserName(username);
            mqttConnectOptions.setPassword(password.toCharArray());
            mqttConnectOptions.setKeepAliveInterval(20);
            mMqttClient.connect(mqttConnectOptions);
            return isConnected = true;
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //设置接收者ID
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setTopic(String topic){
        mMqttTopic = mMqttClient.getTopic(topic);
    }

    //手动断开连接 (退出)
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

    //订阅
    public boolean subscribe(String topic, int qos) {
        if (isConnected) {
            try {
                mMqttClient.subscribe(topic, qos);
                //receiverId = topic;
                return true;
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //取消订阅
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
    //发布消息
    public void publishMessage(String topic,String msg) {
    	mMqttTopic = mMqttClient.getTopic(topic);
        if (isConnected && mMqttTopic != null) {
            try {
                mMqttTopic.publish(msg.getBytes(), 2, false);
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
// 		synchronized (queue) {
// 			queue.add(imsend);
// 		}
// 		 synchronized (object2) {
// 		 object2.notify();
// 		 }

 	}
}
