package com.imserver.service.dbservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.tool.HttpRequest;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.service.dbservice.DogLocationdbService;
import com.dogpro.service.dbservice.WalkingDogdbService;
import com.imserver.service.dbservice.IMInitdbService;
import com.imserver.service.dbservice.IMRedisdbService;


@Service("IMInitdbService")
public class IMInitdbServiceImpl implements IMInitdbService{
	@Autowired
	private IMRedisdbService imRedisdbService;
	@Autowired
	private WalkingDogdbService walkingDogdbService;
	
	private Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
	
	
	public Map<String, Object> addOnline(Long userId) {
		Map<String, Object> model = new HashMap<String, Object>();
		int flag =0;
		String msg = "";
		try {
			imRedisdbService.SADDonlineuser(userId);
			flag = 1;
			msg = "成功";
		} catch (Exception e) {
			// TODO: handle exception
		}
		flag = 0;
		msg = "失败";
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}

	@Override
	public boolean subscribeGroup(Long userId,String client_id) {
		try {
			Map<String, Object> model = walkingDogdbService.getJoinGroupKeys(userId);
			String dogloactionId = model.get("locationId").toString();
			String keyss  = model.get("keyss").toString();
			if(!dogloactionId.equals("0")){
				//该用户存在群组
				//调用api 订阅群组keyss
				String mqttserverhost = packagesMap.get("mqttserverhost").toString();
				String mqtt_subscribeapi = packagesMap.get("mqtt_subscribeapi").toString();
				String mqttAdmin_username = packagesMap.get("mqttAdmin_username").toString();
				String mqttAdmin_password = packagesMap.get("mqttAdmin_password").toString();
				String url = mqtt_subscribeapi.replaceAll("<mqttserverhost>", mqttserverhost);
				//post参数
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("topic", keyss);
				map.put("qos", 2);
				map.put("client_id", client_id);
				JSONObject jsonObject = new JSONObject(map);
				String para = jsonObject.toJSONString();
				try {
					//http请求
					String result = HttpRequest.doPost(mqttAdmin_username, mqttAdmin_password, url, para);
					JSONObject jsonObject2 = JSONObject.parseObject(result);
					Map map2 = (Map)jsonObject2;
					String code = map2.get("code").toString();
					if(code.equals("0")){
						return true;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}


	@Override
	public boolean unsubscribeGroup(String client_id, String keyss) {
		//调用api 订阅群组keyss
		String mqttserverhost = packagesMap.get("mqttserverhost").toString();
		String mqtt_unsubscribeapi = packagesMap.get("mqtt_unsubscribeapi").toString();
		String mqttAdmin_username = packagesMap.get("mqttAdmin_username").toString();
		String mqttAdmin_password = packagesMap.get("mqttAdmin_password").toString();
		String url = mqtt_unsubscribeapi.replaceAll("<mqttserverhost>", mqttserverhost);
		//post参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("topic", keyss);
		map.put("client_id", client_id);
		JSONObject jsonObject = new JSONObject(map);
		String para = jsonObject.toJSONString();
		try {
			//http请求
			String result = HttpRequest.doPost(mqttAdmin_username, mqttAdmin_password, url, para);
			JSONObject jsonObject2 = JSONObject.parseObject(result);
			Map map2 = (Map)jsonObject2;
			String code = map2.get("code").toString();
			if(code.equals("0")){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return true;
	}
}
