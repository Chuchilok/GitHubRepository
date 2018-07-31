package com.imserver.service.dbservice.impl;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.tool.HttpRequest;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.imserver.service.dbservice.MQTTbeatdbService;

@Service("MQTTbeatdbService")
public class MQTTbeatdbServiceImpl implements MQTTbeatdbService {
	Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
	/**
	 * 获取在线用户集合(mqtt心跳)
	 * @return
	 */
//	public Set<String> getOnlineUser() {
//		// TODO Auto-generated method stub
//		//执行查看在线用户命令
//		Set<String> results = runCommand(runcommand, -1);
//		//返回类型
//		Set<String> onlineUser = new HashSet<String>();
//		//拆分命令返回结果字符串
//		for(String line:results){
//			line = line.substring(7, line.length());
//			if(line.startsWith("U")){
//				line = line.substring(1, line.length());
//				String userId = line.split("_")[0];
//				onlineUser.add(userId);
//			}
//		}
//		return onlineUser;
//	}
	public Set<String> getOnlineUser() {
		//调用api 获取在线用户
		String mqttserverhost = packagesMap.get("mqttserverhost").toString();
		String mqtt_clientapi = packagesMap.get("mqtt_clientapi").toString();
		String mqttAdmin_username = packagesMap.get("mqttAdmin_username").toString();
		String mqttAdmin_password = packagesMap.get("mqttAdmin_password").toString();
		
		//返回类型
		Set<String> onlineUser = new HashSet<String>();
		try {
			String url = mqtt_clientapi.replaceAll("<mqttserverhost>", mqttserverhost);
			String result = HttpRequest.doGet(mqttAdmin_username, mqttAdmin_password, url);
			JSONObject jsonObject = JSONObject.parseObject(result);
			Map<String, Object> map = (Map<String, Object>)jsonObject;
			Map<String, Object> resultMap = (Map<String, Object>) map.get("result");
			List<Map<String, Object>> objectList = (List<Map<String, Object>>) resultMap.get("objects");
			for(Map<String, Object> obj:objectList){
				String client_id = obj.get("client_id").toString();
				if(client_id.startsWith("U")){
					client_id = client_id.substring(1, client_id.length());
					String userId = client_id.split("_")[0];
					onlineUser.add(userId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onlineUser;
	}
	
	
	public Set<String> runCommand(String cmd,int tp){
		Set<String> results = new HashSet<String>();
//	     StringBuffer buf = new StringBuffer(10000);
//	     String rt="-1";
	  try {
	   Process pos = Runtime.getRuntime().exec(cmd);
	   pos.waitFor();
	   if(tp==1){
	    if(pos.exitValue()==0){
//	     rt="1";
	    }
	   }else{
	    InputStreamReader ir = new InputStreamReader(pos.getInputStream());
	    LineNumberReader input = new LineNumberReader(ir);
	    String ln="";
	    while ((ln =input.readLine()) != null) {
//	        buf.append(ln+"<br>");
	    	results.add(ln);
	    }
//	    rt = buf.toString();
	    input.close();
	    ir.close();
	   }
	  } catch (java.io.IOException e) {
//	   rt=e.toString();
	  }catch (Exception e) {
//	   rt=e.toString();
	  }
//	     return rt;
	  return results;
	}
}
