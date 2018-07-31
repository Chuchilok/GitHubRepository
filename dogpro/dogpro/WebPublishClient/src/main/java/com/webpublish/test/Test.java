package com.webpublish.test;

import java.util.HashMap;
import java.util.Map;

import com.webpublish.mqtttool.MQTTService;

public class Test {
	public static void main(String[] args){
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("projectName", "projectName");
		infoMap.put("jbossPath", "jbossPath");
		infoMap.put("clientId", "clientId");
		
		try {
			Thread.sleep(3000);
			MQTTService mqttService = new MQTTService(infoMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
