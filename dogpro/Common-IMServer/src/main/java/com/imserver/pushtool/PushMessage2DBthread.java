package com.imserver.pushtool;

import org.springframework.beans.factory.annotation.Autowired;

import com.dogpro.common.tool.SpringInit;
import com.dogpro.domain.model.PushMessage;
import com.dogpro.service.dbservice.RedisdbService;
import com.imserver.service.dbservice.IMRedisdbService;
import com.imserver.service.dbservice.IMpushdbService;

public class PushMessage2DBthread extends Thread{
	@Autowired 
	private IMpushdbService pushdbService;
	@Autowired
	private IMRedisdbService redisdbService;
	
	public void run(){
		pushdbService = (IMpushdbService) SpringInit.getApplicationContext().getBean("IMpushdbService");
		redisdbService = (IMRedisdbService) SpringInit.getApplicationContext().getBean("IMRedisdbService");
		while(!this.isInterrupted()){
			try {
				PushMessage pushMessage = redisdbService.popPushRecord();
				if(pushMessage!=null){
					pushdbService.pushmsg2DB(pushMessage);
				}else{
					sleep(500);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
