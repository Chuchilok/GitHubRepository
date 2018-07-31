package com.webpublish.mqtttool;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import com.ibm.micro.client.mqttv3.MqttCallback;
import com.ibm.micro.client.mqttv3.MqttConnectOptions;
import com.ibm.micro.client.mqttv3.MqttDeliveryToken;
import com.ibm.micro.client.mqttv3.MqttException;
import com.ibm.micro.client.mqttv3.MqttMessage;
import com.ibm.micro.client.mqttv3.MqttTopic;
import com.webpublish.common.utils.MessageConsumerConfig;
import com.webpublish.domain.model.IMsend;


/**
 * Created by wuyr on 17-9-2 上午11:53.
 */
/*
 * 封装的MQTT
 * */
@Component("MQTTService01222")
public class MQTTService implements MQTTServiceInt {
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
	private BlockingQueue<IMsend> queue = new LinkedBlockingQueue<IMsend>();

	public MQTTService() throws MqttException {
    	this.ServerId = "0";
    	Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
    	hostName = "tcp://" + packagesMap.get("mqttserverhost") + ":"
				+ packagesMap.get("mqttserverport");
    	mqttserverid = packagesMap.get("mqttserverid").toString()+ ServerId;
    	username = packagesMap.get("mqttusername").toString();
    	password = packagesMap.get("mqttpwd").toString();
        mMqttClient = new MyMqttClient(hostName, mqttserverid, null);
        mMqttClient.setCallback(new MqttCallback() {
			public void messageArrived(MqttTopic arg0, MqttMessage arg1)
					throws Exception {
				//接收到信息
//				System.out.println("======================接收到信息======================");
				
				
			}
			
			public void deliveryComplete(MqttDeliveryToken arg0) {
				//发送信息成功
				System.out.println("======================发送信息成功======================");
			}
			
			public void connectionLost(Throwable arg0) {
				//连接断开
				System.out.println("======================连接断开======================");
				//重连
				try {
					Thread.sleep(3000);
					tryConnect();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        //尝试连接成功
        if(ServerId.startsWith("m")){
        	String[] s = ServerId.split("_");
        	tryConnect(s[0], "UserId_"+s[1], 2, false);
        }
        else if(tryConnect()){
        	System.out.println("======================连接成功======================"+ServerId);
        	Runnable task = new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						IMsend msg = null;
						try {
							msg = queue.take();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (msg != null) {
							publishMessage(msg.getToken(), msg.getContent());
							System.err.println("topic"+msg.getToken());
							System.err.println("content"+msg.getContent());
						 }

					}
				}
			};
			Thread thread = new Thread(task,"Thread MQTTServer"+ServerId);
			thread.start();
        }else {
        	System.out.println("======================连接失败======================");
		}
    }

   

    //尝试连接
    /* (non-Javadoc)
	 * @see com.webpublish.mqtttool.MQTTServiceInt#tryConnect()
	 */
    @Override
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

  //尝试连接  设置遗嘱发布 
    /* (non-Javadoc)
	 * @see com.webpublish.mqtttool.MQTTServiceInt#tryConnect(java.lang.String, java.lang.String, int, boolean)
	 */
    @Override
	public boolean tryConnect(String willTopic,String payload,int qos,boolean retained) {
        try {
            
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setConnectionTimeout(60000);
            MqttTopic topic = mMqttClient.getTopic(willTopic);
            mqttConnectOptions.setWill(topic, payload.getBytes(), qos, retained);
            mqttConnectOptions.setUserName(username);
            mqttConnectOptions.setPassword(password.toCharArray());
            mMqttClient.connect(mqttConnectOptions);
            return isConnected = true;
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //设置接收者ID
    /* (non-Javadoc)
	 * @see com.webpublish.mqtttool.MQTTServiceInt#setReceiverId(java.lang.String)
	 */
    @Override
	public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /* (non-Javadoc)
	 * @see com.webpublish.mqtttool.MQTTServiceInt#setTopic(java.lang.String)
	 */
    @Override
	public void setTopic(String topic){
        mMqttTopic = mMqttClient.getTopic(topic);
    }

    //手动断开连接 (退出)
    /* (non-Javadoc)
	 * @see com.webpublish.mqtttool.MQTTServiceInt#disconnect()
	 */
    @Override
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
    /* (non-Javadoc)
	 * @see com.webpublish.mqtttool.MQTTServiceInt#subscribe(java.lang.String, int)
	 */
    @Override
	public boolean subscribe(String topic, int qos) {
        if (isConnected) {
            try {
                mMqttClient.subscribe(topic, qos);
                return true;
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //取消订阅
    /* (non-Javadoc)
	 * @see com.webpublish.mqtttool.MQTTServiceInt#unSubscribe(java.lang.String)
	 */
    @Override
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
    /* (non-Javadoc)
	 * @see com.webpublish.mqtttool.MQTTServiceInt#publishMessage(java.lang.String, java.lang.String)
	 */
    @Override
	public void publishMessage(String topic,String base64String) {
    	mMqttTopic = mMqttClient.getTopic(topic);
        if (isConnected && mMqttTopic != null) {
            try {
                mMqttTopic.publish(base64String.getBytes("utf-8"), 2, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    // 队列发送信息
 	/* (non-Javadoc)
	 * @see com.webpublish.mqtttool.MQTTServiceInt#sendMsg(java.lang.String, java.lang.String)
	 */
 	@Override
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

 	}
 	// 队列发送信息
  	/* (non-Javadoc)
	 * @see com.webpublish.mqtttool.MQTTServiceInt#sendMsg(com.webpublish.domain.model.IMsend)
	 */
  	@Override
	public void sendMsg(IMsend iMsend) {
  		try {
 			queue.put(iMsend);
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}

  	}

}
