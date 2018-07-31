package com.imserver.mqtttool;
 
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Tracer;
import org.fusesource.mqtt.codec.MQTTFrame;
import org.springframework.beans.factory.annotation.Autowired;

import com.dogpro.common.domain.IMsend;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.SpringInit;
import com.imserver.service.dbservice.IMRedisdbService;

//@Component("MQttTool")
public class MQttTool {

	private String hostName = "";
	private String mqttusername = "";
	private String mqttpwd = "";
	private MQTT client;
	private String mqttserverid = "";

	private CallbackConnection callbackConnection;

	private Queue<IMsend> queue = new LinkedList<IMsend>();
	private Object object = new Object();
	private Object object2 = new Object();

	private final static boolean CLEAN_START = true;// 连接前清空会话信息
	private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s
	public final static long RECONNECTION_ATTEMPT_MAX = 6;// 设置重新连接的次数
	public final static long RECONNECTION_DELAY = 2000;// 设置重连的间隔时间
	public final static int SEND_BUFFER_SIZE = 50 * 1024 * 1024;// 发送最大缓冲为2M
	private Boolean Flagi = false;
	private int ServerId = 0;
	@Autowired
	private IMRedisdbService redisdbService;

	public MQttTool(int i) {
		this.ServerId = i;
		redisdbService = (IMRedisdbService) SpringInit.getApplicationContext()
				.getBean("IMRedisdbService");

		Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
		hostName = "tcp://" + packagesMap.get("mqttserverhost") + ":"
				+ packagesMap.get("mqttserverport");
		mqttusername = packagesMap.get("mqttusername").toString();
		mqttpwd = packagesMap.get("mqttpwd").toString();
		mqttserverid = packagesMap.get("mqttserverid").toString() +
				+ ServerId;
		try {
			client = new MQTT();
			client.setHost(hostName);
			Date date = new Date();
			client.setClientId(date.getTime()+"");
			client.setCleanSession(CLEAN_START); 
			client.setReconnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);
			client.setReconnectDelay(RECONNECTION_DELAY);
			client.setKeepAlive(KEEP_ALIVE);
			client.setSendBufferSize(SEND_BUFFER_SIZE);
			client.setUserName(mqttusername);
			client.setPassword(mqttpwd);
			client.setVersion("3.1.1");
			// 选择消息分发队列
//			client.setDispatchQueue(Dispatch.createQueue("foo"));// 若没有调用方法setDispatchQueue，客户端将为连接新建一个队列。如果想实现多个连接使用公用的队列，显式地指定队列是一个非常方便的实现方法

			Runnable task = new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						synchronized (Flagi) {
							if(!Flagi ){
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								continue;
					
							}
						}
						IMsend msg = null;
						synchronized (queue) {
							msg = queue.poll();
						}

						if (msg != null) {
							publish(msg.getToken(), msg.getContent());
//							synchronized (object) {
//								try {
//									object.wait();
//								} catch (Exception e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//
//							}
						
						 } else {
						 synchronized (object2) {
						 try {
						 object2.wait();
						 } catch (Exception e) {
						 // TODO Auto-generated catch block
						 e.printStackTrace();
						 }
						 }
						 }

					}
				}
			};
			Thread thread = new Thread(task);
			thread.start();
			
			// 设置跟踪器
			client.setTracer(new Tracer() {
				@Override
				public void onReceive(MQTTFrame frame) {
					// System.out.println("recv: " + frame);
				}

				@Override
				public void onSend(MQTTFrame frame) {
					// System.out.println("send: " + frame);
				}

				@Override
				public void debug(String message, Object... args) {
					System.out.println(String.format("debug: " + message, args));
				}
			});
			// 使用回调式API
			callbackConnection = client.callbackConnection();

			// 连接监听
			callbackConnection.listener(new Listener() {

				// 接收订阅话题发布的消息

				public void onPublish(final UTF8Buffer topic,
						final Buffer payload, Runnable onComplete) {
					// System.out.println(new String(payload.getData()));
					// System.out.println(topic);
					// String base64Rev;
					// try {
					// base64Rev = new String(payload.toByteArray(),"utf-8");
					// String jsonString =new String(
					// Base64.base64ToByteArray(base64Rev),"utf-8");
					// System.out.println(jsonString);
					// } catch (Exception e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					//
					// 处理收到的信息
					// Runnable cb = null;
					// cb = new Runnable() {
					// public void run() {
					// handleMsg(topic, payload);
					// }
					// };
					// Thread task = new Thread(cb);
					// task.setPriority(1);
					// fixedThreadPool.execute(task);

					onComplete.run();
					// handleMsg(topic, payload);

				}

				// 连接失败

				public void onFailure(Throwable value) {
					System.out.println("连接失败");
					callbackConnection.disconnect(null);
					synchronized (Flagi) {
						Flagi = false;
					}
				}

				// 连接断开

				public void onDisconnected() {
					System.out.println("连接断开");
					synchronized (Flagi) {
						Flagi = false;
					}
				}

				// 连接成功

				public void onConnected() {
					System.out.println("连接成功");
					synchronized (Flagi) {
						Flagi = true;
					}
					// int res= redisdbService.restest();
					// if(!Flagi){
					// Runnable task = new Runnable() {
					// public void run() {
					// // TODO Auto-generated method stub
					// while (true) {
					// //RedisdbService redisdbService1 =(RedisdbService)
					// ApplicationContextHelper.getBean("redisdbService");
					// IMsend msg =redisdbService.popIMsendfromRedis();
					// if (msg != null) {
					// publish(msg.getToken(), msg.getContent());
					// // synchronized (object) {
					// // try {
					// // object.wait();
					// // } catch (Exception e) {
					// // // TODO Auto-generated catch block
					// // e.printStackTrace();
					// // }
					//
					// // }
					//
					// } else {
					//
					// }
					// }
					//
					// }
					// };
					//
					// Thread thread = new Thread(task,"Threadaaa");
					// thread.start();
					// Flagi=true;
					// }

					
				}
			});

			callbackConnection.connect(new Callback<Void>() {

				public void onFailure(Throwable throwable) {
					System.out.println("连接失败："
							+ throwable.getLocalizedMessage());
					
					
				}

				public void onSuccess(Void paramT) {
					
					
					// Topic[] topics = { new Topic(mqttserverid,
					// QoS.EXACTLY_ONCE) };
					// callbackConnection.subscribe(topics,
					// new Callback<byte[]>() {
					//
					// public void onFailure(Throwable value) {
					// System.out.println("订阅失败:"
					// + value.getLocalizedMessage());
					// callbackConnection.disconnect(null);
					// }
					//
					// public void onSuccess(byte[] arg0) {
					// System.out.println("订阅成功");
					// }
					// });

				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MQttTool failed");
		}

	}

	public void publish(final String topic, final String Msg) {
//		try {
//			callbackConnection.publish(topic, Msg.getBytes("UTF-8"),
//					QoS.AT_LEAST_ONCE, false, new Callback<Void>() {
//
//						public void onSuccess(Void v) {
//							System.out.println("===========消息发布成功============");
//
//							synchronized (object) {
//								object.notify();
//							}
//						}
//
//						public void onFailure(Throwable value) {
//							System.out.println("========消息发布失败=======");
//							IMsend ob1 = new IMsend();
//							ob1.setToken(topic);
//							ob1.setContent(Msg);
//							try {
//								queue.put(ob1);
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							synchronized (object) {
//								object.notify();
//							}
//							// callbackConnection.disconnect(null);
//						}
//					});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		Buffer buffer;
//		try {
//			buffer = new Buffer(Msg.getBytes("utf-8"));
//		//	callbackConnection.publish(UTF8Buffer.utf8(topic),buffer ,QoS.AT_MOST_ONCE, false,null);
//			
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
		 try {
			 byte[] bytes = Msg.getBytes("UTF-8");
			 
	 callbackConnection.publish(topic,bytes		 ,QoS.AT_MOST_ONCE, false,null);
		  System.out.println("===========消息发布成功============");
		 } catch (Exception e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
	}

	// 队列发送信息
	public void sendMsg(String token, String msg) {

		IMsend imsend = new IMsend();
		imsend.setToken(token);
		imsend.setContent(msg);
		synchronized (queue) {
			queue.add(imsend);
		}
		 synchronized (object2) {
		 object2.notify();
		 }

	}

//	// 接收信息处理信息
//	public void handleMsg(UTF8Buffer topic, Buffer payload) {
//		try {
//			String base64Rev = new String(payload.toByteArray(), "utf-8");
//			String jsonString = new String(Base64.base64ToByteArray(base64Rev),
//					"utf-8");
//			JSONObject jsonObject = JSONObject.parseObject(jsonString);
//			System.out.println(jsonString);
//			IMmessage iMmessage = JSONObject.toJavaObject(jsonObject,
//					IMmessage.class);
//			// 判断消息类型
//			int type = iMmessage.getType();
//			// 转发的发布topic
//			String Retoken = "";
//			{
//				switch (type) {
//				case 1:
//					// 好友请求1
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 2:
//					// 好友请求回复2
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 3:
//					// 好友文本消息3
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 4:
//					// 好友图片信息4
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 5:
//					// 好友视频信息5
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 6:
//					// 好友定位信息6
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 7:
//					// 群信息7
//					Retoken = redisdbService.getGroup(iMmessage.getRevUid());
//					break;
//				case 8:
//					// 群视频信息 8
//					Retoken = redisdbService.getGroup(iMmessage.getRevUid());
//					break;
//				case 9:
//					// 群图片信息 9
//					Retoken = redisdbService.getGroup(iMmessage.getRevUid());
//					break;
//				default:
//					break;
//				}
//			}
//			// 信息记录入redis msgSet
//			String msgKey = redisdbService.setMsgSet(iMmessage.getSendUid(),
//					iMmessage.getRevUid(), iMmessage.getContent(),
//					iMmessage.getType());
//			// 信息记录push入redis 信息队列中
//			redisdbService.pushMsg2Redis(msgKey);
//			// 包装转发信息体
//			IMreceive imreceive = new IMreceive();
//			imreceive = JSONObject.toJavaObject(jsonObject, IMreceive.class);
//			imreceive.setMsgKey(msgKey);
//			String base64send;
//			base64send = Base64.byteArrayToBase64(JSONObject.toJSON(imreceive)
//					.toString().getBytes("utf-8"));
//			sendMsg(Retoken, base64send);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	// 接收信息处理信息
//	public void handleMsg(String content) {
//		try {
//			String base64Rev = content;
//			String jsonString = new String(Base64.base64ToByteArray(base64Rev),
//					"utf-8");
//			JSONObject jsonObject = JSONObject.parseObject(jsonString);
//			System.out.println(jsonString);
//			IMmessage iMmessage = JSONObject.toJavaObject(jsonObject,
//					IMmessage.class);
//			// 判断消息类型
//			int type = iMmessage.getType();
//			// 转发的发布topic
//			String Retoken = "";
//			{
//				switch (type) {
//				case 1:
//					// 好友请求1
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 2:
//					// 好友请求回复2
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 3:
//					// 好友文本消息3
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 4:
//					// 好友图片信息4
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 5:
//					// 好友视频信息5
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 6:
//					// 好友定位信息6
//					Retoken = redisdbService.getUser(iMmessage.getRevUid())
//							.getToken();
//					break;
//				case 7:
//					// 群信息7
//					Retoken = redisdbService.getGroup(iMmessage.getRevUid());
//					break;
//				case 8:
//					// 群视频信息 8
//					Retoken = redisdbService.getGroup(iMmessage.getRevUid());
//					break;
//				case 9:
//					// 群图片信息 9
//					Retoken = redisdbService.getGroup(iMmessage.getRevUid());
//					break;
//				default:
//					break;
//				}
//			}
//			// 信息记录入redis msgSet
//			String msgKey = redisdbService.setMsgSet(iMmessage.getSendUid(),
//					iMmessage.getRevUid(), iMmessage.getContent(),
//					iMmessage.getType());
//			// 信息记录push入redis 信息队列中
//			redisdbService.pushMsg2Redis(msgKey);
//			// 包装转发信息体
//			IMreceive imreceive = new IMreceive();
//			imreceive = JSONObject.toJavaObject(jsonObject, IMreceive.class);
//			imreceive.setMsgKey(msgKey);
//			String base64send;
//			base64send = Base64.byteArrayToBase64(JSONObject.toJSON(imreceive)
//					.toString().getBytes("utf-8"));
//			sendMsg(Retoken, base64send);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
