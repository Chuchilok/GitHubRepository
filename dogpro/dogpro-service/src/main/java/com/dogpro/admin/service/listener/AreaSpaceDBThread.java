package com.dogpro.admin.service.listener;

import com.dogpro.admin.service.dbservice.AdminAreaSpacedbService;
import com.dogpro.admin.service.dbservice.AdminRedisdbService;
import com.dogpro.common.tool.SpringInit;
import com.dogpro.domain.model.AreaSpace;

public class AreaSpaceDBThread extends Thread {
	private AdminAreaSpacedbService areaSpacedbService;
	private AdminRedisdbService redisdbService;
	public void run(){
		//redis键名
		byte[] redisKey = "AreaSpace".getBytes();
		areaSpacedbService = (AdminAreaSpacedbService) SpringInit.getApplicationContext().getBean("AdminAreaSpacedbService");
		redisdbService = (AdminRedisdbService) SpringInit.getApplicationContext().getBean("AdminRedisdbService");
		AreaSpace areaSpace = null;
		while(!this.isInterrupted()){
			try {
					areaSpace = redisdbService.popAreaSpace();
					if (areaSpace!=null) {
						boolean addOrUpdate = areaSpacedbService.isAddOrUpdate(areaSpace);
						if (addOrUpdate) {
							areaSpacedbService.insertData(areaSpace);
						}else{
							areaSpacedbService.updateData(areaSpace);
						}
					}else{
						sleep(100);
					}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
