package com.imserver.listener;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.ejb.Schedule;

import com.dogpro.common.tool.SpringInit;
import com.dogpro.domain.model.OnlineRecord;
import com.dogpro.service.dbservice.WalkingDogdbService;
import com.imserver.service.dbservice.IMRedisdbService;

public class OnlineRecordThread extends Thread{
	//每隔1分钟 记录一次
	private Long sleeptime = 60*1000l;
	private IMRedisdbService iMRedisdbService = (IMRedisdbService) SpringInit.getApplicationContext().getBean("IMRedisdbService");
	private Long totalOnlineUsers = 0l;
	public void run(){
		while(true){
			try {
				//休眠 
				sleep(sleeptime);
				//在线用户总数
				totalOnlineUsers = iMRedisdbService.SCARDonlineuser();
				setOnlineRecord(totalOnlineUsers);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    
	public void setOnlineRecord(Long totalOnlineUsers){
		//打包 在线用户记录 实体
		OnlineRecord onlineRecord = new OnlineRecord();
		onlineRecord.setTotalOnlineUser(totalOnlineUsers);
		Date currentTime = new Date();
		onlineRecord.setRecordTime(currentTime);
		onlineRecord.setAddtimes(currentTime);
		onlineRecord.setUpdatetimes(currentTime);
		onlineRecord.setState(1);
		//存入 redis 队列
		iMRedisdbService.LPUSHonlineRecord(onlineRecord);
	}
}
