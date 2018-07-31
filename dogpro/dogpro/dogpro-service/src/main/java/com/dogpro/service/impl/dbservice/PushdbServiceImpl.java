package com.dogpro.service.impl.dbservice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.dao.PushUserMapper;
import com.dogpro.domain.model.PushMessage;
import com.dogpro.domain.model.PushUser;
import com.dogpro.domain.model.PushUserExample;
import com.dogpro.service.dbservice.PushdbService;
import com.dogpro.service.dbservice.RedisdbService;

@Service("PushdbService")
public class PushdbServiceImpl implements PushdbService {
	@Autowired
	private RedisdbService redisdbService;
	@Autowired
	private PushUserMapper pushUserMapper;

	public Map<String, Object> sendPushMsg(int type, String content, Long RevUid ,Long sendUid,Long targetId) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			PushMessage pushMessage = new PushMessage();
			pushMessage.setType(type);
			pushMessage.setContent(content);
			pushMessage.setRevuid(RevUid);
			pushMessage.setMsgcode(System.currentTimeMillis());
			pushMessage.setState(1);
			pushMessage.setSenduid(sendUid);
			pushMessage.setTargetid(targetId);
			redisdbService.pushPushMsg2Redis(pushMessage);
			model.put("flag", 1);
			model.put("msg", "发送成功");
		} catch (Exception e) {
			// TODO: handle exception
			model.put("flag",0);
			model.put("msg", "发送失败");
		}
		return model;
	}

	/**
	 * 更新数据库中 用户 推送token信息
	 * 
	 * @param pushUser
	 * @return
	 */
	public boolean updateUserpush(PushUser pushUser) {
		try {
			//游客记录
			if(pushUser.getUserid()==0){
				PushUser updateRecord = new PushUser();
				updateRecord.setState(0);
				updateRecord.setUpdatetimes(new Date());
				// 搜索是否存在相同token,type 数据 更新
				PushUserExample pushUserExample = new PushUserExample();
				PushUserExample.Criteria pCriteria = pushUserExample
						.createCriteria();
				pCriteria.andTokenEqualTo(pushUser.getToken()).andTypeEqualTo(
						pushUser.getType());
				pushUserMapper.updateByExampleSelective(updateRecord,
						pushUserExample);
				//插入游客记录
				updateRecord.setAddtimes(new Date());
				updateRecord.setToken(pushUser.getToken());
				updateRecord.setType(pushUser.getType());
				updateRecord.setUserid(pushUser.getUserid());
				updateRecord.setState(1);
				pushUserMapper.insertSelective(updateRecord);
			}else {
				//非游客记录
				PushUser updateRecord = new PushUser();
				updateRecord.setState(0);
				updateRecord.setUpdatetimes(new Date());
				// 搜索是否存在相同userId 数据 更新
				PushUserExample pushUserExample = new PushUserExample();
				PushUserExample.Criteria pCriteria = pushUserExample
						.createCriteria();
				pCriteria.andUseridEqualTo(pushUser.getUserid());
				pushUserMapper.updateByExampleSelective(updateRecord,
						pushUserExample);
				// 搜索是否存在相同token,type 数据 更新
				PushUserExample pushUserExample2 = new PushUserExample();
				PushUserExample.Criteria pCriteria2 = pushUserExample2
						.createCriteria();
				pCriteria2.andTokenEqualTo(pushUser.getToken()).andTypeEqualTo(
						pushUser.getType());
				pushUserMapper.updateByExampleSelective(updateRecord,
						pushUserExample2);
				// 插入新数据
				pushUser.setAddtimes(new Date());
				pushUser.setState(1);
				pushUserMapper.insert(pushUser);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
