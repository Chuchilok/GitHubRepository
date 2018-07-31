package com.imserver.service.dbservice.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.dao.PushMessageMapper;
import com.dogpro.dao.PushUserMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.PushMessage;
import com.dogpro.domain.model.PushMessageExample;
import com.dogpro.domain.model.PushUser;
import com.dogpro.domain.model.PushUserExample;
import com.dogpro.domain.model.User;
import com.dogpro.service.dbservice.FriendCircledbService;
import com.imserver.service.dbservice.IMRedisdbService;
import com.imserver.service.dbservice.IMpushdbService;
@Service("IMpushdbService")
public class IMpushdbServiceImpl implements IMpushdbService {
	@Autowired 
	private IMRedisdbService imRedisdbService;
	@Autowired
	private PushUserMapper pushUserMapper;
	@Autowired
	private PushMessageMapper pushMessageMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private FriendCircledbService friendCircledbService;
	
	
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
			imRedisdbService.pushPushMsg2Redis(pushMessage);
			model.put("flag", 1);
			model.put("msg", "发送成功");
		} catch (Exception e) {
			// TODO: handle exception
			model.put("flag",0);
			model.put("msg", "发送失败");
		}
		return model;
	}

	public void handlePushMsg(PushMessage pushMessage) {
		// TODO Auto-generated method stub
		
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
						pushUser.getType()).andStateEqualTo(1);
				//相同token,type 删除redis 集合数据
				List<PushUser> pushUsers = pushUserMapper.selectByExample(pushUserExample2);
				for(PushUser pUser:pushUsers){
					Long userId = pUser.getUserid();
					imRedisdbService.deleteUserPush(userId+"");
				}
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

	public boolean pushmsg2DB(PushMessage pushMessage) {
		// TODO Auto-generated method stub
		Long msgCode = pushMessage.getMsgcode();
		Long RevUid = pushMessage.getRevuid();
		PushMessageExample pushMessageExample = new PushMessageExample();
		PushMessageExample.Criteria pCriteria = pushMessageExample.createCriteria();
		pCriteria.andRevuidEqualTo(RevUid).andMsgcodeEqualTo(msgCode);
		List<PushMessage> pushMessages = pushMessageMapper.selectByExample(pushMessageExample);
		if(pushMessages.isEmpty()){
			Date date = new Date();
			pushMessage.setAddtimes(date);
			pushMessage.setUpdatetimes(date);
			pushMessageMapper.insert(pushMessage);
		}
		return false;
	}

//	/**
//	 * 获取朋友圈推送记录（分页）
//	 * @param userId
//	 * @param pageNO
//	 * @param pageSize
//	 * @return
//	 */
//	public List<Map<String, Object>> getFirendCirclePush(Long userId,
//			int pageNO, int pageSize) {
//		// TODO Auto-generated method stub
//		//返回 类型
//		List<Map<String, Object>> modelList = new ArrayList<Map<String,Object>>();
//		PushMessageExample pushMessageExample = new PushMessageExample();
//		PushMessageExample.Criteria pCriteria = pushMessageExample.createCriteria();
//		//朋友圈点赞评论推送类型 （朋友圈发布者收到评论3朋友圈他人回复你4点赞5）
//		List<Integer> types = new ArrayList<Integer>();
//		types.add(3);
//		types.add(4);
//		types.add(5);
//		pCriteria.andRevuidEqualTo(userId).andTypeIn(types);
//		PaginationUtil paginationUtil = new PaginationUtil(pageNO,pageSize);
//		pushMessageExample.setPagination(paginationUtil);
//		pushMessageExample.setOrderByClause("msgCode desc");
//		List<PushMessage> pushMessages = pushMessageMapper.selectByExample(pushMessageExample);
//		for(PushMessage pushMessage2:pushMessages){
//			PushMessage pushMessage = pushMessageMapper.selectByPrimaryKey(pushMessage2.getId());
//			Map<String, Object> model = new HashMap<String, Object>();
//			model.put("sendUid", pushMessage.getSenduid());
//			model.put("targetId", pushMessage.getTargetid());
//			model.put("content", pushMessage.getContent());
//			model.put("type", pushMessage.getType());
//			model.put("msgCode", pushMessage.getMsgcode());
//			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
//			String dateString = formatter.format(pushMessage.getMsgcode());
//			model.put("dateString", dateString);
//			//用户头像
//			User user = userMapper.selectByPrimaryKey(pushMessage.getSenduid());
//			model.put("headpic", user.getHeadpic());
//			model.put("sex", user.getSex());
//			model.put("nickname", user.getNickname());
//			modelList.add(model);
//			//朋友圈 缩略图
//			model.put("FCsubUrl", friendCircledbService.getFCfirstMedia(pushMessage.getTargetid()));
//		}
//		return modelList;
//	}
	
	/**
	 * 获取朋友圈推送记录（分页）
	 * @param userId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getFirendCirclePush(Long userId,
			int pageNO, int pageSize) {
		// TODO Auto-generated method stub
		//返回 类型
		List<Map<String, Object>> modelList = new ArrayList<Map<String,Object>>();
		try {
			PaginationUtil paginationUtil = new PaginationUtil(pageNO,pageSize);
			List<Map<String, Object>> pushMsgList = pushMessageMapper.getFirendCirclePush(userId, paginationUtil);
			for(Map<String, Object> pushMessage:pushMsgList){
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("sendUid", pushMessage.get("sendUid"));
				model.put("targetId", pushMessage.get("targetId"));
				model.put("content", pushMessage.get("content"));
				model.put("type", pushMessage.get("type"));
				model.put("msgCode",pushMessage.get("msgCode"));
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
				String dateString = formatter.format(pushMessage.get("msgCode"));
				model.put("dateString", dateString);
				//用户头像
				model.put("headpic", pushMessage.get("headpic"));
				model.put("sex", pushMessage.get("sex"));
				model.put("nickname", pushMessage.get("nickname"));
				modelList.add(model);
				//朋友圈 缩略图
				model.put("FCsubUrl", pushMessage.get("FCsubUrl")!=null?pushMessage.get("FCsubUrl"):"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

}
