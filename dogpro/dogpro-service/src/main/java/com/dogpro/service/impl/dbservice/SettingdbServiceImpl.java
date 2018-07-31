package com.dogpro.service.impl.dbservice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.dao.SettingMapper;
import com.dogpro.dao.WalkingDogGroupMapper;
import com.dogpro.domain.model.Setting;
import com.dogpro.domain.model.SettingExample;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.SettingdbService;
@Service("SettingdbService")
public class SettingdbServiceImpl implements SettingdbService {
	@Autowired
	private SettingMapper settingMapper;
	@Autowired
	private RedisdbService redisdbService;
	@Autowired
	private WalkingDogGroupMapper walkingDogGroupMapper;
	/**
	 * 设置 setting
	 * @param userId
	 * @param notify
	 * @param sound
	 * @param shake
	 * @param handset
	 * @return
	 */
	public Map<String, Object> alterSetting(Long userId,int notify,int sound,int shake,int handset){
		//返回类型
		Map<String,Object> model  = new HashMap<String, Object>();
		model.put("flag", 0);
		model.put("msg", "修改失败");
		Date currentTime = new Date();
		Setting record = new Setting();
		record.setNotify(notify);
		record.setSound(sound);
		record.setShake(shake);
		record.setHandset(handset);
		record.setUserid(userId);
		record.setState(1);
		record.setUpdatetimes(currentTime);
		record.setAddtimes(currentTime);
		//查找是否有 对应userId 记录
		SettingExample settingExample = new SettingExample();
		SettingExample.Criteria sCriteria = settingExample.createCriteria();
		sCriteria.andUseridEqualTo(userId);
		List<Setting> sList = settingMapper.selectByExample(settingExample);
		if(!sList.isEmpty()){
			record.setId(sList.get(0).getId());
			record.setAddtimes(sList.get(0).getAddtimes());
			//更新数据
			if(settingMapper.updateByPrimaryKeySelective(record)==1){
				model.put("flag", 1);
				model.put("msg", "修改成功");
			}
		}else {
			//插入数据
			if(settingMapper.insertSelective(record)==1){
				model.put("flag", 1);
				model.put("msg", "修改成功");
			}
		}
		//修改redis库 中推送token
		String pushtoken = redisdbService.getUserPush(userId+"");
		String nickname = redisdbService.getUserNickname(userId+"");
		if(pushtoken.startsWith("N")){
			//取消推送消息通知
			pushtoken = pushtoken.substring(1, pushtoken.length());
		}
		if(notify==0){
			pushtoken = "N"+pushtoken;
		}
		redisdbService.setUserPush(userId, pushtoken, nickname);
		return model;
	}
	/**
	 * 获取 setting 状态
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getSetting(Long userId){
		Map<String, Object> model = new HashMap<String, Object>();
		int notify = 1;
		int sound = 1;
		int shake = 1;
		int handset = 0;
		//查找是否有 对应userId 记录
		SettingExample settingExample = new SettingExample();
		SettingExample.Criteria sCriteria = settingExample.createCriteria();
		sCriteria.andUseridEqualTo(userId);
		List<Setting> sList = settingMapper.selectByExample(settingExample);
		if(!sList.isEmpty()){
			notify = sList.get(0).getNotify();
			sound = sList.get(0).getSound();
			shake = sList.get(0).getShake();
			handset = sList.get(0).getHandset();
		}
		model.put("notify", notify);
		model.put("sound", sound);
		model.put("shake", shake);
		model.put("handset", handset);
		return model;
	}
	
	
	
	public boolean initSetting(Long userId) {
		// TODO Auto-generated method stub
		try {
			int notify = 1;
			int sound = 1;
			int shake = 1;
			int handset = 0;
			//查找是否有 对应userId 记录
			SettingExample settingExample = new SettingExample();
			SettingExample.Criteria sCriteria = settingExample.createCriteria();
			sCriteria.andUseridEqualTo(userId);
			List<Setting> sList = settingMapper.selectByExample(settingExample);
			if(!sList.isEmpty()){
				notify = sList.get(0).getNotify();
				//修改redis库 中推送token
				String pushtoken = redisdbService.getUserPush(userId+"");
				String nickname = redisdbService.getUserNickname(userId+"");
				if(pushtoken.startsWith("N")){
					//取消推送消息通知
					pushtoken = pushtoken.substring(1, pushtoken.length());
				}
				if(notify==0){
					pushtoken = "N"+pushtoken;
				}
				redisdbService.setUserPush(userId, pushtoken, nickname);
			}else {
				//插入setting数据
				Date currentTime = new Date();
				Setting record = new Setting();
				record.setNotify(notify);
				record.setSound(sound);
				record.setShake(shake);
				record.setHandset(handset);
				record.setUserid(userId);
				record.setState(1);
				record.setUpdatetimes(currentTime);
				record.setAddtimes(currentTime);
				settingMapper.insertSelective(record);
				return true;
			}
		} catch (Exception e) {
			
		}
		return false;
	}

	/**
	 * 设置 群组消息免打扰
	 * @param userId
	 * @param isDisturb
	 * @return
	 */
	public Map<String, Object> setGroupDisturb(Long userId, int isDisturb) {
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "修改失败";
		//修改数据库
		WalkingDogGroup walkingDogGroup = walkingDogGroupMapper.selectByUid(userId);
		if(walkingDogGroup!=null){
			walkingDogGroup.setIsdisturb(isDisturb);
			walkingDogGroup.setUpdatetimes(new Date());
			walkingDogGroupMapper.updateByPrimaryKeySelective(walkingDogGroup);
			//修改redis库
			redisdbService.setUserGroupDisturb(userId, isDisturb);
			flag = 1;
			msg = "修改成功";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}
}
