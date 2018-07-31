package com.imserver.mqtttool;

import org.springframework.beans.factory.annotation.Autowired;

import com.dogpro.common.tool.SpringInit;
import com.dogpro.domain.model.Message;
import com.dogpro.service.dbservice.MessagedbService;
import com.dogpro.service.dbservice.RedisdbService;

public class Message2DBthread extends Thread{
	@Autowired 
	private MessagedbService messagedbService;
	@Autowired
	private RedisdbService redisdbService;
	
	public void run(){
		messagedbService = (MessagedbService) SpringInit.getApplicationContext().getBean("messagedbService");
		redisdbService = (RedisdbService) SpringInit.getApplicationContext().getBean("redisdbService");
		while(!this.isInterrupted()){
			try {
				Message message = redisdbService.popMsgFromRedis();
				if(message!=null){
					//判断是否修改数据库
					messagedbService.redis2DB(message);
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
