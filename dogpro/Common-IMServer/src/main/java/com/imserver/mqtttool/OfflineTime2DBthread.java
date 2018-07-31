package com.imserver.mqtttool;

import org.springframework.beans.factory.annotation.Autowired;

import com.dogpro.common.tool.SpringInit;
import com.dogpro.domain.model.Message;
import com.dogpro.service.dbservice.MessagedbService;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.UserInfodbService;
import com.imserver.service.dbservice.IMRedisdbService;

public class OfflineTime2DBthread extends Thread{
	@Autowired 
	private UserInfodbService userInfodbService;
	@Autowired
	private IMRedisdbService imRedisdbService;
	
	public void run(){
		userInfodbService = (UserInfodbService) SpringInit.getApplicationContext().getBean("UserInfodbService");
		imRedisdbService = (IMRedisdbService) SpringInit.getApplicationContext().getBean("IMRedisdbService");
		while(!this.isInterrupted()){
			try {
				String value = imRedisdbService.popUserOfflineTime();
				if(value!=null){
					String[] values = value.split("_");
					Long userId = Long.parseLong(values[0]);
					Long offlineTime = Long.parseLong(values[1]);
					//修改数据库
					userInfodbService.updateUserOfflineTime(userId, offlineTime);
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
