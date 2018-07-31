package com.dogpro.service.listener;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dogpro.common.tool.JedisUtil;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.ObjectUtil;
import com.dogpro.common.tool.SpringInit;
import com.dogpro.domain.model.WalkingDogTrack;
import com.dogpro.service.dbservice.WalkingDogdbService;
public class Track2DBthread extends Thread{
	@Autowired
	private WalkingDogdbService walkingDogdbService;

	public void run(){
		//redis键名
		byte[] redisKey = "WalkingDogTrack".getBytes();
		walkingDogdbService = (WalkingDogdbService) SpringInit.getApplicationContext().getBean("WalkingDogdbService");
		Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
		JedisUtil jedisUtil = new JedisUtil(Integer.parseInt(packagesMap.get("redisTrackdb").toString()));
		while(!this.isInterrupted()){
			try {
				byte[] bytes = jedisUtil.rpop(redisKey);
				if(bytes!=null){
					try {
						WalkingDogTrack walkingDogTrack = (WalkingDogTrack)ObjectUtil.bytes2Object(bytes);
						walkingDogTrack.setTrackid(null);
						walkingDogdbService.redis2Mysql(walkingDogTrack);
					} catch (Exception e) {
						// TODO: handle exception
					}
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
