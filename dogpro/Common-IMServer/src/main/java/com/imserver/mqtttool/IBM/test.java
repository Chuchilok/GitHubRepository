package com.imserver.mqtttool.IBM;

import java.util.List;

import redis.clients.jedis.Jedis;

import com.dogpro.common.tool.JedisUtil;
import com.ibm.micro.client.mqttv3.MqttException;

public class test {

	public static void main(String[] args) throws Exception, Exception {
		try {
			TestMQTTService testMQTTService = new TestMQTTService(123l,"200");
//			while(true){
//				testMQTTService.publishMessage("200", "110->200");
//				Thread.sleep(3000);
//			}
			
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		Socket s = new Socket("127.0.0.1",8888);
//		//构建IO
//        InputStream is = s.getInputStream();
//        OutputStream os = s.getOutputStream();
//        
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
//        //向服务器端发送一条消息
//        bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
//        bw.flush();
//        while(true){
//            //向服务器端发送一条消息
//            bw.write(new Date().toString()+"\n");
//            bw.flush();
//            Thread.sleep(5000);
//        }
        
        
	}

}
