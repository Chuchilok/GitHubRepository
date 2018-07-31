package com.dogpro.common.tool;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class MQTTapi {
	private Map packagesMap = MessageConsumerConfig
			.readConfig("config.properties");

	public boolean subscribeGroup(String client_id, String keyss) {
		try {
			// 调用api 订阅群组keyss
			String mqttserverhost = packagesMap.get("mqttserverhost")
					.toString();
			String mqtt_subscribeapi = packagesMap.get("mqtt_subscribeapi")
					.toString();
			String mqttAdmin_username = packagesMap.get("mqttAdmin_username")
					.toString();
			String mqttAdmin_password = packagesMap.get("mqttAdmin_password")
					.toString();
			String url = mqtt_subscribeapi.replaceAll("<mqttserverhost>",
					mqttserverhost);
			// post参数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("topic", keyss);
			map.put("qos", 2);
			map.put("client_id", client_id);
			JSONObject jsonObject = new JSONObject(map);
			String para = jsonObject.toJSONString();
			try {
				// http请求
				String result = HttpRequest.doPost(mqttAdmin_username,
						mqttAdmin_password, url, para);
				JSONObject jsonObject2 = JSONObject.parseObject(result);
				Map map2 = (Map) jsonObject2;
				String code = map2.get("code").toString();
				if (code.equals("0")) {
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean unsubscribeGroup(String client_id, String keyss) {
		try {
			// 调用api 订阅群组keyss
			String mqttserverhost = packagesMap.get("mqttserverhost")
					.toString();
			String mqtt_unsubscribeapi = packagesMap.get("mqtt_unsubscribeapi")
					.toString();
			String mqttAdmin_username = packagesMap.get("mqttAdmin_username")
					.toString();
			String mqttAdmin_password = packagesMap.get("mqttAdmin_password")
					.toString();
			String url = mqtt_unsubscribeapi.replaceAll("<mqttserverhost>",
					mqttserverhost);
			// post参数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("topic", keyss);
			map.put("client_id", client_id);
			JSONObject jsonObject = new JSONObject(map);
			String para = jsonObject.toJSONString();

			// http请求
			String result = HttpRequest.doPost(mqttAdmin_username,
					mqttAdmin_password, url, para);
			JSONObject jsonObject2 = JSONObject.parseObject(result);
			Map map2 = (Map) jsonObject2;
			String code = map2.get("code").toString();
			if (code.equals("0")) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean deleteClient(String client_id) {
		try {
			// 调用api 订阅群组keyss
			String mqttserverhost = packagesMap.get("mqttserverhost")
					.toString();
			String mqtt_deleteapi = packagesMap.get("mqtt_deleteapi")
					.toString();
			String mqttAdmin_username = packagesMap.get("mqttAdmin_username")
					.toString();
			String mqttAdmin_password = packagesMap.get("mqttAdmin_password")
					.toString();
			String url = mqtt_deleteapi.replaceAll("<mqttserverhost>",
					mqttserverhost).replaceAll("<clientid>", client_id);
			// delete http请求
//			String result = HttpRequest.DELETE(mqttAdmin_username,mqttAdmin_password,url, new HashMap<String, Object>());
			String result = HttpRequest.basicDelete(mqttAdmin_username,mqttAdmin_password,url, "");
			JSONObject jsonObject2 = JSONObject.parseObject(result);
			Map map2 = (Map) jsonObject2;
			String code = map2.get("code").toString();
			if(code!=null&&code.equals("0")){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
