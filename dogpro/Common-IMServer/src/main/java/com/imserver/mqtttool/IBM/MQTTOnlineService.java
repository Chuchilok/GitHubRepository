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
import com.dogpro.service.dbservice.WalkingDogdbService;
import com.ibm.micro.client.mqttv3.MqttCallback;
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
 * */
public class MQTTOnlineService {
    private boolean isConnected;
    //MQTT客户端
    private MyMqttClient mMqttClient;
    //MQTT的Topic
    private MqttTopic mMqttTopic;
    private String senderId;
    private String receiverId;
    private String username;
    private String password;
    private String hostName = "";
	private String mqttserverid = "";
	private String ServerId;
	private String OnlineTopic;
	
	
	
	
	private BlockingQueue<IMsend> queue = new LinkedBlockingQueue<IMsend>();
	
    public MQTTOnlineService(final String Serverid) throws MqttException {
    	this.ServerId = Serverid;
    	Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
    	hostName =  packagesMap.get("mqttprotocol")+"://" + packagesMap.get("mqttserverhost") + ":"
				+ packagesMap.get("mqttserverport");
    	username = packagesMap.get("mqttusername").toString();
		password = packagesMap.get("mqttpwd").toString();
    	mqttserverid = packagesMap.get("mqttserverid").toString()+ ServerId;
    	OnlineTopic = packagesMap.get("mqttOnlineTopic").toString().trim();
        mMqttClient = new MyMqttClient(hostName, mqttserverid, null);
        final IMRedisdbService imRedisdbService =  (IMRedisdbService) SpringInit.getApplicationContext().getBean("IMRedisdbService");
        final WalkingDogdbService walkingDogdbService =  (WalkingDogdbService) SpringInit.getApplicationContext().getBean("WalkingDogdbService");
        //mMqttTopic = mMqttClient.getTopic(senderId);
        mMqttClient.setCallback(new MqttCallback() {
			
			public void messageArrived(MqttTopic arg0, MqttMessage arg1)
					throws Exception {
				//接收到信息
				System.out.println("======================接收到上线信息======================");
				System.out.println(arg0);
				System.out.println(arg1);
				try {
					String topic = arg0.toString();
					String content = arg1.toString();
					String userId = content.split("_")[1];
					//判断是否存在群组，存在则调用api订阅群组
					Long doglocationId = walkingDogdbService.getDogLocationIdByUid(Long.parseLong(userId));
					if(doglocationId>0){
						walkingDogdbService.subscribeGroupTopic(Long.parseLong(userId), doglocationId);
					}
					//处理在线用户 集合(增加用户)
					imRedisdbService.SADDonlineuser(Long.parseLong(userId));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
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
        if(Serverid.equals("AAA")){
        	tryConnect("AAAAA", "遗嘱", 2, false);
        }
        else if(tryConnect()){
        	System.out.println("======================连接成功======================"+Serverid);
        	//订阅online主题
        	subscribe(OnlineTopic, 2);
        	
        }else {
        	System.out.println("======================连接失败======================");
		}
    }

   

    //尝试连接
    public boolean tryConnect() {
        try {
            
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setConnectionTimeout(60000);
            mqttConnectOptions.setUserName(username);
			mqttConnectOptions.setPassword(password.toCharArray());
            mMqttClient.connect(mqttConnectOptions);
//            synchronized (Flagi){
//				Flagi = true;
//			}
            
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
            mqttConnectOptions.setUserName(username);
			mqttConnectOptions.setPassword(password.toCharArray());
            MqttTopic topic = mMqttClient.getTopic(willTopic);
            mqttConnectOptions.setWill(topic, payload.getBytes(), qos, retained);
            mMqttClient.connect(mqttConnectOptions);
//            synchronized (Flagi){
//				Flagi = true;
//			}
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
    public void publishMessage(String topic,String base64String) {
    //	ii++;
    //	System.out.println(this.ServerId+" send data index:"+ii);
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
// 		synchronized (queue) {
// 			queue.add(imsend);
// 		}
// 		 synchronized (object2) {
// 		 object2.notify();
// 		 }

 	}
 	
}
