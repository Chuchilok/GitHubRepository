package com.imserver.pushtool;

import java.util.Map;

import com.dogpro.common.domain.IMsend;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.SpringInit;
import com.dogpro.common.tool.UmengPush;
import com.imserver.service.dbservice.IMRedisdbService;

public class PushMsgUmengThread implements Runnable{
	private UmengPush umengPush;
	private String Umeng_appkey;
	private String Umeng_appMasterSecret;
	private String Umeng_title;
	
	
	public PushMsgUmengThread(){
		Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
		Umeng_appkey = packagesMap.get("Umeng_appkey").toString();
		Umeng_appMasterSecret = packagesMap.get("Umeng_appMasterSecret").toString();
		Umeng_title = packagesMap.get("Umeng_title").toString(); 
		umengPush = new UmengPush(Umeng_appkey, Umeng_appMasterSecret);
	}
	
	public void run(){
		IMRedisdbService imRedisdbService =(IMRedisdbService) SpringInit.getApplicationContext().getBean("IMRedisdbService");
		try {
			while(true){
				IMsend iMsend = imRedisdbService.popANDPushMessage();
				if(iMsend!=null){
					umengPush.sendAndroidUnicast(Umeng_title,Umeng_title,iMsend);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
