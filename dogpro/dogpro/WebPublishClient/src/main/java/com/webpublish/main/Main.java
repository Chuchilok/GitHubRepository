package com.webpublish.main;

import java.util.HashMap;
import java.util.Map;

import com.webpublish.mqtttool.MQTTService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//项目名
		String projectName = args[0];
		//jboss 路径
		String jbossPath = args[1];
		//clientId
		String clientId = args[2];
//		String projectName ="dogpro-Webapi";
//		String jbossPath  ="/opt/linuxsir/web/jboss-as-7.1.1.Final";
		Map<String,String> infoMap = new HashMap<String, String>();
		infoMap.put("projectName", projectName);
		infoMap.put("jbossPath", jbossPath);
		infoMap.put("clientId", clientId);
		for(String key:infoMap.keySet()){
			System.out.println(key+" : "+infoMap.get(key));
		}
		try {
			Thread.sleep(3000);
			MQTTService mqttService = new MQTTService(infoMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
