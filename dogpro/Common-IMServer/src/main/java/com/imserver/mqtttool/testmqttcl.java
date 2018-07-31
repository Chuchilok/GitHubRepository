package com.imserver.mqtttool;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.hawtdispatch.Dispatch;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.fusesource.mqtt.client.Tracer;
import org.fusesource.mqtt.codec.MQTTFrame;

import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.tool.MessageConsumerConfig;
//@Component
public class testmqttcl {
	
	
	private String hostName = "";
	private String mqttusername = "";
	private String mqttpwd = "";
	private String mqttserverid = "";

	private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private Object object = new Object();
	private Object object2 = new Object();
	
	private CallbackConnection callbackConnection;

	private final static boolean CLEAN_START = true;// 连接前清空会话信息
	private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s
	public final static long RECONNECTION_ATTEMPT_MAX = 6;// 设置重新连接的次数
	public final static long RECONNECTION_DELAY = 2000;// 设置重连的间隔时间
	public final static int SEND_BUFFER_SIZE = 2 * 1024 * 1024;// 发送最大缓冲为2M
	public static String mqttServer = "";
	
	public testmqttcl(String dyid) {
		Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
		hostName = "tcp://" + packagesMap.get("mqttserverhost") + ":"
				+ packagesMap.get("mqttserverport");
		mqttusername = packagesMap.get("mqttusername").toString();
		mqttServer = packagesMap.get("mqttserverid").toString();
		mqttpwd = packagesMap.get("mqttpwd").toString();
		mqttserverid = dyid;// "tokenid1001";
		try {
			MQTT mqtt = new MQTT();

			// MQTT设置说明
			mqtt.setHost(hostName);
			mqtt.setClientId(mqttserverid); // 用于设置客户端会话的ID。在setCleanSession(false);被调用时，MQTT服务器利用该ID获得相应的会话。此ID应少于23个字符，默认根据本机地址、端口和时间自动生成
			mqtt.setCleanSession(false); // 若设为false，MQTT服务器将持久化客户端会话的主体订阅和ACK位置，默认为true
			mqtt.setKeepAlive(KEEP_ALIVE);// 定义客户端传来消息的最大时间间隔秒数，服务器可以据此判断与客户端的连接是否已经断开，从而避免TCP/IP超时的长时间等待
			mqtt.setUserName(mqttusername);// 服务器认证用户名
			mqtt.setPassword(mqttpwd);// 服务器认证密码

			mqtt.setWillTopic("willTopic");// 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息
			mqtt.setWillMessage("willMessage");// 设置“遗嘱”消息的内容，默认是长度为零的消息
			mqtt.setWillQos(QoS.EXACTLY_ONCE);// 设置“遗嘱”消息的QoS，默认为QoS.ATMOSTONCE
			mqtt.setWillRetain(true);// 若想要在发布“遗嘱”消息时拥有retain选项，则为true
			mqtt.setVersion("3.1.1");

			// 失败重连接设置说明
			/*
			mqtt.setConnectAttemptsMax(10L);// 客户端首次连接到服务器时，连接的最大重试次数，超出该次数客户端将返回错误。-1意为无重试上限，默认为-1
			mqtt.setReconnectAttemptsMax(3L);// 客户端已经连接到服务器，但因某种原因连接断开时的最大重试次数，超出该次数客户端将返回错误。-1意为无重试上限，默认为-1
			mqtt.setReconnectDelay(10L);// 首次重连接间隔毫秒数，默认为10ms
			mqtt.setReconnectDelayMax(30000L);// 重连接间隔毫秒数，默认为30000ms
			mqtt.setReconnectBackOffMultiplier(2);// 设置重连接指数回归。设置为1则停用指数回归，默认为2
*/
			// Socket设置说明
			mqtt.setReceiveBufferSize(65536);// 设置socket接收缓冲区大小，默认为65536（64k）
			mqtt.setSendBufferSize(65536);// 设置socket发送缓冲区大小，默认为65536（64k）
			mqtt.setTrafficClass(8);// 设置发送数据包头的流量类型或服务类型字段，默认为8，意为吞吐量最大化传输

			// 带宽限制设置说明
			mqtt.setMaxReadRate(0);// 设置连接的最大接收速率，单位为bytes/s。默认为0，即无限制
			mqtt.setMaxWriteRate(0);// 设置连接的最大发送速率，单位为bytes/s。默认为0，即无限制

			// 选择消息分发队列
			mqtt.setDispatchQueue(Dispatch.createQueue(mqttserverid));// 若没有调用方法setDispatchQueue，客户端将为连接新建一个队列。如果想实现多个连接使用公用的队列，显式地指定队列是一个非常方便的实现方法
			
			//发送队列线程
			Runnable task = new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						 
						String msg = null;
							try {
								msg = queue.take();
								publish(mqttServer, msg);
							} catch ( Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					  
						

					}
				}
			};
			Thread thread = new Thread(task);
			thread.start();
			
			// 设置跟踪器
			mqtt.setTracer(new Tracer() {
				@Override
				public void onReceive(MQTTFrame frame) {
					System.out.println("recv: " + frame);
				}

				@Override
				public void onSend(MQTTFrame frame) {
					System.out.println("send: " + frame);
				}

				@Override
				public void debug(String message, Object... args) {
					System.out.println(String.format("debug: " + message, args));
				}
			});

			// 使用回调式API
			callbackConnection = mqtt.callbackConnection();

			// 连接监听
			callbackConnection.listener(new Listener() {

				// 接收订阅话题发布的消息
				public void onPublish(UTF8Buffer topic, Buffer payload,
						Runnable onComplete) {
//					System.out
//							.println("=============receive msg================"
//									+ new String(payload.toByteArray()));
					String  base64Rev= new String(payload.toByteArray());
					byte[] tes;
					try {
						tes = Base64.base64ToByteArray(new String(base64Rev.getBytes("UTF-8")));
						String jsonString =new String  (tes);
					    JSONObject jsonObject = JSONObject.parseObject(jsonString);
					    System.out.println(jsonString);
						onComplete.run();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
				}

				// 连接失败
				public void onFailure(Throwable value) {
					System.out.println("===========connect failure===========");
					callbackConnection.disconnect(null);
				}

				// 连接断开
				public void onDisconnected() {
					System.out.println("====mqtt disconnected=====");

				}

				// 连接成功
				public void onConnected() {
					System.out.println("====mqtt connected=====");
					
				}
			});

			callbackConnection.connect(new Callback<Void>() {

				public void onFailure(Throwable throwable) {
					// System.out.println("连接失败："
					// + throwable.getLocalizedMessage());
				}

				public void onSuccess(Void paramT) {
					//连接成功 订阅消息
					Topic[] topics = { new Topic(mqttserverid, QoS.EXACTLY_ONCE) };
					callbackConnection.subscribe(topics,
							new Callback<byte[]>() {

								public void onFailure(Throwable value) {
									// System.out.println("订阅失败:"
									// + value.getLocalizedMessage());
									callbackConnection.disconnect(null);
								}

								public void onSuccess(byte[] arg0) {
									// System.out.println("订阅成功");
								}
							});
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		callbackConnection.disconnect(new Callback<Void>() {  
	        public void onSuccess(Void arg0) {  
	            //与服务器断开连接成功  
	        }  
	        public void onFailure(Throwable arg0) {  
	            //与服务器断开连接失败  
	        }  
	});  
	}

	// 发布消息
	public void publish(String topic, final String msg) {
		try {
			callbackConnection.publish(topic, (msg).getBytes("UTF-8"),
					QoS.AT_LEAST_ONCE, false, new Callback<Void>() {
						public void onSuccess(Void v) {
							System.out.println("===========消息发布成功============");
							
						}

						public void onFailure(Throwable value) {
							System.out.println("========消息发布失败=======");
							try {
								queue.put(msg);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					});

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 队列发送信息
	public void sendMsg(String msg) {
		

			try {
				queue.put(msg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	//订阅 群组
	public void subscribeGroup(String keyss){
		Topic[] topics= { new Topic(keyss, QoS.EXACTLY_ONCE) };
		callbackConnection.subscribe(topics,
				new Callback<byte[]>() {
					public void onFailure(Throwable value) {
						// System.out.println("订阅失败:"
						// + value.getLocalizedMessage());
						callbackConnection.disconnect(null);
					}
					public void onSuccess(byte[] arg0) {
						// System.out.println("订阅成功");
					}
				});
		 
	}
	//退订
	public void unsubscribeGroup(String keyss){
		UTF8Buffer[] topics= {new UTF8Buffer(keyss)};
		callbackConnection.unsubscribe(topics,new Callback<Void>() {

			public void onSuccess(Void value) {
				// TODO Auto-generated method stub
				
			}

			public void onFailure(Throwable value) {
				// TODO Auto-generated method stub
				
			}
		});
		 
	}
}