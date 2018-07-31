package com.dogpro.service.listener;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.dogpro.common.domain.RedisDoglocation;
import com.dogpro.common.domain.RedisWalkingDogGroup;
import com.dogpro.common.tool.SpringInit;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.WalkingDogdbService;

public class ExitGroupThread extends Thread {
	@Autowired 
	private RedisdbService redisdbService;
	@Autowired
	private WalkingDogdbService	walkingDogdbService;
	//判定结束遛狗时间 3小时
	private int endjudgehour = -3;
	//判定加入遛狗组 12小时
	private int joinjudgehour = -12;
	//休眠时间 60秒
	private long sleeptime = 6000;
	public void run(){
		redisdbService = (RedisdbService) SpringInit.getApplicationContext().getBean("redisdbService");
		walkingDogdbService = (WalkingDogdbService) SpringInit.getApplicationContext().getBean("WalkingDogdbService");
		while(true){
			Set<String> joinGroupsSet = redisdbService.SMEMBERSjoinGroup();
			Set<String> exitGroupsSet = redisdbService.SMEMBERSexitGroup();
			if(joinGroupsSet!=null||exitGroupsSet!=null){
				//加入时间    判定
				Calendar joinCalendar = Calendar.getInstance();
				joinCalendar.add(Calendar.HOUR_OF_DAY, joinjudgehour);
				Date joindate = joinCalendar.getTime();
				//结束时间  判定
				Calendar endCalendar = Calendar.getInstance();
				endCalendar.add(Calendar.HOUR_OF_DAY, endjudgehour);
				Date enddate = endCalendar.getTime();
				// 处理
				handleExitGroup(exitGroupsSet, enddate, joinGroupsSet, joindate);;
			}
			try {
				sleep(sleeptime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void handleExitGroup(Set<String> exitGroupsSet,Date endjudgeDate,Set<String> joinGroupsSet, Date joinjudeDate){
//		Map<Long,Long> map = new HashMap<Long, Long>();
//		//获取 结束遛狗 的用户 的结束时间
//		for(String userIdString:exitGroupsSet){
//			Long userId = Long.parseLong(userIdString);
//			RedisWalkingDogGroup redisWalkingDogGroup = redisdbService.getWalkingDogGroup(userId);
//			if(redisWalkingDogGroup!=null&&redisWalkingDogGroup.getEndtimes()!=null){
//				map.put(userId,redisWalkingDogGroup.getEndtimes().getTime());
//			}
//		}
//		//判断 结束遛狗 的用户 的结束时间 是否超过 既定时间
//		WalkingDogGroup walkingDogGroup = new WalkingDogGroup();
//		for(Long code:map.keySet()){
//			if(code<endjudgeDate.getTime()){
//				//在评判时间之前
//				walkingDogGroup.setGroupid(map.get(code));
//				//踢出遛狗组
//				walkingDogdbService.quitGroup(walkingDogGroup,false);
//			}
//		}
		
		//判断 结束遛狗 的用户 的结束时间 是否超过 既定时间
		WalkingDogGroup walkingDogGroup = new WalkingDogGroup();
		for(String userIdString:exitGroupsSet){
			Long userId = Long.parseLong(userIdString);
			RedisWalkingDogGroup redisWalkingDogGroup = redisdbService.getWalkingDogGroup(userId);
			if(redisWalkingDogGroup!=null&&redisWalkingDogGroup.getEndtimes()!=null){
				Long endTimeCode = redisWalkingDogGroup.getEndtimes().getTime();
				if(endTimeCode<endjudgeDate.getTime()){
					//在评判时间之前
					walkingDogGroup.setGroupid(redisWalkingDogGroup.getGroupid());
					//踢出遛狗组
					walkingDogdbService.quitGroup(walkingDogGroup,false);
				}
			}
		}
		//判断 加入遛狗 的用户 的结束时间 是否超过 既定时间
		for(String userIdString:joinGroupsSet){
			Long userId = Long.parseLong(userIdString);
			RedisWalkingDogGroup redisWalkingDogGroup = redisdbService.getWalkingDogGroup(userId);
			if(redisWalkingDogGroup!=null&&redisWalkingDogGroup.getJointimes()!=null){
				Long joinTimeCode = redisWalkingDogGroup.getJointimes().getTime();
				if(joinTimeCode<joinjudeDate.getTime()){
					//在评判时间之前
					walkingDogGroup.setGroupid(redisWalkingDogGroup.getGroupid());
					//被动 结束遛狗
					walkingDogdbService.endWalkingDog(userId, redisWalkingDogGroup.getDoglocationid(), new BigDecimal(0), new BigDecimal(0));
					//被动 踢出遛狗组
					walkingDogdbService.quitGroup(walkingDogGroup,false);
				}
			}
		}
	}
}
