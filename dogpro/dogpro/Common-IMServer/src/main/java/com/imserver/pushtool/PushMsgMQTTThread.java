package com.imserver.pushtool;

import com.dogpro.common.domain.IMsend;
import com.dogpro.common.tool.SpringInit;
import com.ibm.micro.client.mqttv3.MqttException;
import com.imserver.mqtttool.IBM.PushMQTTService;
import com.imserver.service.dbservice.IMRedisdbService;

public class PushMsgMQTTThread implements Runnable{
	PushMQTTService pushMQTTserver;
	public PushMsgMQTTThread(int pushMQttid){
		try {
			pushMQTTserver = new PushMQTTService("PS"+pushMQttid);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		IMRedisdbService imRedisdbService =(IMRedisdbService) SpringInit.getApplicationContext().getBean("IMRedisdbService");
		try {
			while(true){
				IMsend iMsend = imRedisdbService.popANDPushMessage();
				if(iMsend!=null){
					pushMQTTserver.sendMsg(iMsend.getToken(), iMsend.getContent());
				}else {
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 
}
