package com.dogpro.service.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.dogpro.common.tool.SpringInit;
import com.dogpro.domain.model.PushUser;
import com.dogpro.service.dbservice.PushdbService;
import com.dogpro.service.dbservice.RedisdbService;

public class PushUser2DBthread extends Thread{
	@Autowired 
	private PushdbService pushdbService;
	@Autowired
	private RedisdbService redisdbService;
	
	public void run(){
		pushdbService = (PushdbService) SpringInit.getApplicationContext().getBean("PushdbService");
		redisdbService = (RedisdbService) SpringInit.getApplicationContext().getBean("redisdbService");
		while(!this.isInterrupted()){
			try {
				PushUser pushUser = redisdbService.popUserPuser();
				if(pushUser!=null){
					pushdbService.updateUserpush(pushUser);
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
