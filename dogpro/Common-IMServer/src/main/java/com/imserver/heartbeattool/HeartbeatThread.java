package com.imserver.heartbeattool;

import java.util.Set;

import com.dogpro.common.tool.SpringInit;
import com.imserver.service.dbservice.IMRedisdbService;
import com.imserver.service.dbservice.MQTTbeatdbService;

public class HeartbeatThread implements Runnable{

	private IMRedisdbService imRedisdbService;
	private  MQTTbeatdbService mqttbeatdbService;
	
	public HeartbeatThread(){
		imRedisdbService = (IMRedisdbService) SpringInit.getApplicationContext().getBean("IMRedisdbService");
		mqttbeatdbService = (MQTTbeatdbService) SpringInit.getApplicationContext().getBean("MQTTbeatdbService");
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			Set<String> userSet = mqttbeatdbService.getOnlineUser();
			imRedisdbService.updateOnlineuser(userSet);
			//休眠
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
