package com.webpublish.mqtttool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ibm.micro.client.mqttv3.MqttCallback;
import com.ibm.micro.client.mqttv3.MqttConnectOptions;
import com.ibm.micro.client.mqttv3.MqttDeliveryToken;
import com.ibm.micro.client.mqttv3.MqttException;
import com.ibm.micro.client.mqttv3.MqttMessage;
import com.ibm.micro.client.mqttv3.MqttTopic;
import com.webpublish.common.utils.DownloadTool;
import com.webpublish.common.utils.ProcessTool;
import com.webpublish.common.utils.PropertiesReader;
import com.webpublish.common.utils.VersionTool;
import com.webpublish.common.utils.WarUtils;

/**
 * Created by wuyr on 17-9-2 上午11:53.
 */
/*
 * 封装的MQTT
 */
public class MQTTService {
	private boolean isConnected = false;
	// MQTT客户端
	private MyMqttClient mMqttClient;
	MqttConnectOptions  mqttConnectOptions=null;
	// MQTT的Topic
	private MqttTopic mMqttTopic;
	private String senderId;
	private String receiverId;

	private String hostName = "";
	private String mqttusername = "";
	private String mqttpwd = "";
	private String mqttserverid = "";
	private String ServerId;
	private String subscribeTopic;
	private static Map packagesMap;
	private static String projectName;
	private static String jbossPath;
	private static String clientId;
	
	private String username;
    private String password;
	
	
	public MQTTService(Map<String, String> infoMap) throws MqttException {
		this.projectName = infoMap.get("projectName");
		this.jbossPath = infoMap.get("jbossPath");
		this.clientId = infoMap.get("clientId");
		
		
		packagesMap = new PropertiesReader().getProperties("config.properties");
		hostName = "tcp://" + packagesMap.get("mqttserverhost") + ":"
				+ packagesMap.get("mqttserverport");
		mqttserverid = packagesMap.get("mqttclientid").toString()+clientId;
//		subscribeTopic = packagesMap.get("projectname").toString().trim();
		subscribeTopic = projectName;
		username = packagesMap.get("mqttusername").toString();
    	password = packagesMap.get("mqttpwd").toString();
		mMqttClient = new MyMqttClient(hostName, mqttserverid, null);
		mMqttClient.setCallback(new MqttCallback() {
			public void messageArrived(MqttTopic arg0, MqttMessage arg1)
					throws Exception {
				// 接收到信息
				System.out
						.println("======================接收到信息======================"+mqttserverid);
				System.out.println(arg0 + "==============" + arg1);
				handleReceive(arg1.toString());

			}

			public void deliveryComplete(MqttDeliveryToken arg0) {
				// 发送信息成功
				System.out
						.println("======================发送信息成功======================"+mqttserverid);
			}

			public void connectionLost(Throwable arg0) {
				// 连接断开
				
				System.out.println("======================连接断开======================"+mqttserverid);
				//开启新进程  关闭本进程
				handleConectionLost();
				
				
//				isConnected = false;
//				while(!isConnected)
//				{
//					//重连
//					try {
//						Thread.sleep(3000);
//						if(tryConnect()){
//							 // 订阅项目 topic
//							subscribe(subscribeTopic, 2);
//							System.out
//							.println("======================连接成功======================"
//									);
//						}else{
//							System.out
//							.println("======================连接失败======================");
//						}
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
				

			}
		});
		 while(!isConnected){
			 try {
				 if (tryConnect()) {
					 // 订阅项目 topic
						subscribe(subscribeTopic, 2);
					System.out
							.println("======================连接成功======================"+mqttserverid
									);
				} else {
					System.out.println("======================连接失败======================"+mqttserverid);
					Thread.sleep(3000);
				}
			} catch (Exception e) {
			}
		 }
	}

	// 尝试连接
	public boolean tryConnect() {
//		if(isConnected){
//			return isConnected;
//		}
		try {
			System.out.println("======================尝试连接======================"+mqttserverid);
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

	// 尝试连接 设置遗嘱发布
	public boolean tryConnect(String willTopic, String payload, int qos,
			boolean retained) {
		try {

			MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
			mqttConnectOptions.setConnectionTimeout(60000);
			MqttTopic topic = mMqttClient.getTopic(willTopic);
			 mqttConnectOptions.setKeepAliveInterval(20); 
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
		mMqttTopic = mMqttClient.getTopic(topic);
		if (isConnected && mMqttTopic != null) {
			try {
				mMqttTopic.publish(base64String.getBytes("utf-8"), 2, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 处理接收到的mqtt信息
	public void handleReceive(String content) {
		Map<String, Object> result = JSONObject.parseObject(content);
		try {
			//版本号
			String versionNO = result.get("versionNO").toString();
			//下载url
			String downloadUrl = result.get("downloadUrl").toString();
			//是否重启jboss
			int isRestart = Integer.parseInt(result.get("isRestart").toString());
			// 判断版本 新旧(未实现)
			if (isupdate(versionNO)) {
				if(isRestart==1){
					//echo
					echo();
					// 下载文件
					String filePath = downloadVersion(downloadUrl, versionNO);
					//新建解压文件夹
					mkdirUnzipPath();
					// 解压
					unzipWar(versionNO,filePath);
					// 清除tmp目录下文件
					deletetmp();
					// 重启jboss
					restart();
					// 写下目前版本号
					setLocalVersion(versionNO);
					//删除 下载 war包
					deleteWAR(filePath);
					
				}else{
					//echo
					echo();
					// 下载文件
					String filePath = downloadVersion(downloadUrl, versionNO);
					//新建解压文件夹
					mkdirUnzipPath();
					// 解压文件
					unzipWar(versionNO,filePath);
//					// 清除tmp目录下文件
//					deletetmp();
					// 写下目前版本号
					setLocalVersion(versionNO);
					//删除 下载 war包
					deleteWAR(filePath);
					//touch命令
					touch();
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 下载 版本文件
		public String downloadVersion(String url) {
			// String filePath =
			// packagesMap.get("downloadPath").toString()+subscribeTopic+version+".war";
			String downloadPath = packagesMap.get("downloadPath").toString();
			String filePath = downloadPath.replaceAll("<jbossPath>", jbossPath).replaceAll("<projectName>", projectName)+projectName+".war";
			try {
				DownloadTool.downloadFile(url, filePath);
				return filePath;
			} catch (Exception e) {
				// TODO: handle exception	
				e.printStackTrace();
				return null;
			}
		}

	// 下载 版本文件
	public String downloadVersion(String url, String version) {
		// String filePath =
		// packagesMap.get("downloadPath").toString()+subscribeTopic+version+".war";
		String downloadPath = packagesMap.get("downloadPath").toString();
		String filePath = downloadPath.replaceAll("<jbossPath>", jbossPath).replaceAll("<projectName>", projectName)+projectName+version+".war";
		try {
			DownloadTool.downloadFile(url, filePath);
			return filePath;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	//解压前创建 解压文件夹
	public void mkdirUnzipPath(){
		String targetPath = packagesMap.get("unzipPath").toString().replaceAll("<jbossPath>", jbossPath).replaceAll("<projectName>", projectName);
		String mkdircmd = packagesMap.get("mkdircmd").toString().replaceAll("<unzipPath>", targetPath);
		try {
			ProcessTool.exeCmd(mkdircmd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 解压
	public void unzipWar(String version,String downloadPath) {
		String filePath = downloadPath;
		String targetPath = packagesMap.get("unzipPath").toString().replaceAll("<jbossPath>", jbossPath).replaceAll("<projectName>", projectName);
		String unzipcmd = packagesMap.get("unzipcmd").toString().replaceAll("<filePath>", filePath).replaceAll("<unzipPath>", targetPath);
//		WarUtils.unzip(filePath, targetPath);
		try {
			ProcessTool.exeCmd(unzipcmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

	// 关闭tomcat 重启tomcat
	public void restart() {
		String pscmd = packagesMap.get("pscmd").toString();
		String stopcmd = packagesMap.get("stopcmd").toString();
		String startcmd = packagesMap.get("startcmd").toString();
		startcmd = startcmd.replaceAll("<jbossPath>", jbossPath);
		
		// RunCommand runCommand = new RunCommand();
		// System.out.println(runCommand.runCommand(pscmd, -1));
		// System.out.println("====================================================");
		// runCommand.runCommand(stopcmd, -1);
		// System.out.println(runCommand.runCommand(pscmd, -1));
		// RunCommand.exeCmd(startcmd);
		try {
			ProcessTool.exeCmd(pscmd);
			ProcessTool.exeCmd(stopcmd);
			ProcessTool.exeCmd(pscmd);
			ProcessTool.exeCmd(startcmd);
			ProcessTool.exeCmd(pscmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//删除war包
	public void deleteWAR(String filePath){
		String rmcmd = packagesMap.get("rmcmd").toString() + filePath;
		try {
			ProcessTool.exeCmd(rmcmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//删除 tmp目录下 文件
	public void deletetmp(){
		String rmtmpcmd = packagesMap.get("rmtmpcmd").toString();
		String cmd = rmtmpcmd.replaceAll("<jbossPath>", jbossPath);
		try {
			ProcessTool.exeCmd(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//touch命令
	public void touch(){
		String touchcmd = packagesMap.get("touchcmd").toString();
		String touch = touchcmd.replaceAll("<jbossPath>", jbossPath).replaceAll("<projectName>", projectName);
		try {
			ProcessTool.exeCmd(touch);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 判断接收版本更新信息 是否更新
	public boolean isupdate(String versionNO) {
		String localVersion = getLocalVersion();
		if (localVersion != null) {
			try {
				if (VersionTool.compareVersion(localVersion, versionNO) >= 0) {
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return true;
	}

	public String getLocalVersion() {
		String result = null;
		String iofilename = packagesMap.get("iofilename").toString();
		FileReader reader;
		try {
			reader = new FileReader(iofilename);
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				result = line;
			}
			br.close();
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean setLocalVersion(String version) {
		String iofilename = packagesMap.get("iofilename").toString();
		FileWriter writer;
		BufferedWriter bw;
		try {
			File file = new File(iofilename);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			writer = new FileWriter(file);
			bw = new BufferedWriter(writer);
			bw.write(version + "\n");
			bw.close();
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean echo(){
		String echocmd = packagesMap.get("echocmd").toString();
		String echo = echocmd.replaceAll("<projectName>", projectName).replaceAll("<jbossPath>", jbossPath);
		try {
			ProcessTool.exeCmd(echo);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public static void handleConectionLost(){
		try {
			//休眠3秒
			Thread.sleep(3000);
			//进程号
			String pid = ProcessTool.getPID();
			// 路径
			String pwdcmd = packagesMap.get("pwdcmd").toString();
			String pwd = ProcessTool.exeCmd2(pwdcmd).replaceAll("\n", "");
			// 启动新进程
			String startprojectcmd = packagesMap.get("startprojectcmd").toString().replaceAll("<pwd>", pwd).replaceAll("<projectName>", projectName).replaceAll("<jbossPath>", jbossPath).replaceAll("<clientId>", clientId);
			System.out.println(startprojectcmd);
//			startprojectcmd = startprojectcmd.replaceAll("<pwd>", pwd);
//			startprojectcmd = startprojectcmd.replaceAll("<projectName>", projectName);
//			startprojectcmd = startprojectcmd.replaceAll("<jbossPath>", jbossPath);
//			startprojectcmd = startprojectcmd.replaceAll("<clientId>", clientId);
			ProcessTool.exeCmd(startprojectcmd);
			//杀死本进程
			String killcmd = packagesMap.get("killcmd").toString().replaceAll("<PID>", pid);
			System.out.println(killcmd);
			ProcessTool.exeCmd(killcmd);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
