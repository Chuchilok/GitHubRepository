package com.imserver.service.dbservice.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.domain.IMmessage;
import com.dogpro.common.domain.RedisWalkingDogGroup;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.dao.DogLocationMapper;
import com.dogpro.dao.FriendsMapper;
import com.dogpro.dao.MessageMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.dao.WalkingDogGroupMapper;
import com.dogpro.domain.model.Friends;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.MessageExample;
import com.dogpro.domain.model.ServiceRecord;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.service.dbservice.FriendsdbService;
import com.dogpro.service.dbservice.ServiceRecorddbService;
import com.imserver.service.dbservice.IMMessagedbService;
import com.imserver.service.dbservice.IMRedisdbService;

@Service("IMMessagedbService")
public class IMMessagedbServiceImpl implements IMMessagedbService {
	@Autowired
	private IMRedisdbService imRedisdbService;
	@Autowired
	private FriendsMapper friendsMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private FriendsdbService friendsdbService;
	@Autowired
	private DogLocationMapper dogLocationMapper;
	@Autowired
	private WalkingDogGroupMapper walkingDogGroupMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ServiceRecorddbService serviceRecorddbService;
	// 客服用户
	private User serviceUser = null;

	/**
	 * 客户端发送信息接口
	 * 
	 * @param sendUid
	 * @param revUid
	 * @param content
	 * @param type
	 * @param token
	 * @param md5
	 * @return
	 */
	public Map<String, Object> sendMsg(Long sendUid, Long revUid,
			String content, int type, String token, String md5,
			Float sendLongitude, Float sendLatitude) {
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		// 根据type判断是 私聊 还是 群组
		if (type < 8) {
			// 判断是否发给客服的
			if (getServiceUser() != null
					&& getServiceUser().getUserId() == revUid) {
				//发送给客服   更新客服记录表 
				ServiceRecord serviceRecord = new ServiceRecord();
				serviceRecord.setUserId(sendUid);
				serviceRecorddbService.updateServiceRecordData(serviceRecord);
			} else if (getServiceUser() != null
					&& getServiceUser().getUserId() == sendUid) {
				//客服发送
				
			}else {
				// 好友私聊 判断是否仍是好友关系
				Friends me = null;
				Friends you = null;
				try {
					me = friendsMapper.selectByUidFid(sendUid, revUid);
					you = friendsMapper.selectByUidFid(revUid, sendUid);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (me == null || you == null) {
					model.put("msgCode", 0 + "");
					model.put("flag", -1);
					model.put("msg", "不是好友关系");
					return model;
				}
			}
		} else {
			// 群聊 判断是否仍存在群组
			RedisWalkingDogGroup redisWalkingDogGroup = imRedisdbService
					.getWalkingDogGroup(sendUid);
			if (redisWalkingDogGroup == null) {
				model.put("msgCode", 0 + "");
				model.put("flag", -1);
				model.put("msg", "不存在该遛狗组");
				return model;
			}else if(redisWalkingDogGroup!=null&&redisWalkingDogGroup.getState()!=1){
				model.put("msgCode", 0 + "");
				model.put("flag", -1);
				model.put("msg", "你已结束遛狗，发送消息失败");
				return model;
			}
		}

		IMmessage iMmessage = new IMmessage();
		iMmessage.setSendUid(sendUid);
		iMmessage.setRevUid(revUid);
		iMmessage.setContent(content);
		iMmessage.setToken(token);
		iMmessage.setMd5(md5);
		iMmessage.setType(type);
		iMmessage.setSendLongitude(sendLongitude);
		iMmessage.setSendLatitude(sendLatitude);
		Date date = new Date();
		Long msgCode = date.getTime();
		iMmessage.setMsgCode(msgCode);
		String ss = Base64.byteArrayToBase64(JSONObject.toJSON(iMmessage)
				.toString().getBytes());
		ss = ss.replaceAll("[\\s*\t\n\r]", "");
		try {
			imRedisdbService.handleMsgThread(ss);
			model.put("msgCode", msgCode + "");
			model.put("flag", 1);
			model.put("msg", "发送成功");
		} catch (Exception e) {
			// TODO: handle exception
			model.put("msgCode", 0 + "");
			model.put("flag", 0);
			model.put("msg", "发送失败");
		}
		return model;
	}

	/**
	 * 客户端修改信息状态
	 * 
	 * @param sendUid
	 * @param revUid
	 * @param type
	 * @param msgCode
	 * @return
	 */
	public Map<String, Object> updateMsg(Long sendUid, Long revUid, int state,
			int type, Long msgCode) {
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			// 包装message信息体
			Message message = new Message();
			message.setState(state);
			message.setSenduserid(sendUid);
			message.setAcceptuserid(revUid);
			message.setType(type);
			message.setMsgcode(msgCode);
			if (imRedisdbService.pushMsgUpdate2Redis(message)) {
				model.put("flag", 1);
				model.put("msg", "修改成功");
			} else {
				model.put("flag", 0);
				model.put("msg", "修改失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return model;
	}

	/**
	 * 获取未读IM信息
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getUnread(Long userId, Long msgCode, int pageSize) {
		// 返回类型
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		PaginationUtil paginationUtil = new PaginationUtil(0, pageSize);
		Long oldmsgCode = imRedisdbService.getUserMsgCode(userId);
		if (oldmsgCode != null) {
			if (oldmsgCode > msgCode) {
				msgCode = oldmsgCode;
			} else {
				// 记录 user对应msgcode
				imRedisdbService.setUserMsgCode(userId, msgCode);
			}
		} else {
			// 记录 user对应msgcode
			imRedisdbService.setUserMsgCode(userId, msgCode);
		}
		List<Message> messages = new ArrayList<Message>();
		int total = 0;
		try {
			// 所在遛狗组
			WalkingDogGroup walkingDogGroup = walkingDogGroupMapper
					.selectByUid(userId);
			if (walkingDogGroup != null) {
				// 存在群组
				messages = messageMapper.selectAllUnread(userId,
						walkingDogGroup.getDoglocationid(), msgCode,
						paginationUtil);
				total = messageMapper.countUnread(userId,
						walkingDogGroup.getDoglocationid(), msgCode);
			} else {
				// 不存在群组,还有
				messages = messageMapper.selectPrivateUnread(userId, msgCode,
						paginationUtil);
				total = messageMapper.countUnread(userId, 0l, msgCode);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		for (Message message : messages) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("sendUid", message.getSenduserid());
			model.put("revUid", message.getAcceptuserid());
			model.put("millisTime", message.getSendtimes().getTime()+"");
			model.put("state", message.getState());
			model.put("type", message.getType());
			model.put("msgCode", message.getMsgcode()+"");
			model.put("content", message.getContent());
			modelList.add(model);
		}
		result.put("total", total);
		result.put("message", modelList);
		return result;
	}

	/**
	 * 查看历史im记录
	 * 
	 * @param sendUid
	 * @param isGroup
	 * @param msgCode
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getMsgList(Long revUid, Long sendUid,
			int isGroup, Long msgCode, int pageSize) {
		// 返回类型
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		if (msgCode == null || msgCode <= 0) {
			msgCode = new Date().getTime();
		}
		// 分页
		PaginationUtil paginationUtil = new PaginationUtil(0, pageSize);
		List<Message> messages = new ArrayList<Message>();
		if (isGroup == 1) {
			messages = messageMapper.selectGroupHistory(revUid, sendUid,
					msgCode, paginationUtil);
		} else {
			messages = messageMapper.selectPrivateHistory(revUid, sendUid,
					msgCode, paginationUtil);
		}
		for (Message message1 : messages) {
			Map<String, Object> model = new HashMap<String, Object>();
			Message message = messageMapper.selectByPrimaryKey(message1
					.getMessageid());
			model.put("sendUid", message.getSenduserid());
			model.put("content", message.getContent());
			model.put("type", message.getType());
			model.put("millisTime", message.getSendtimes().getTime()+"");
			model.put("msgCode", message.getMsgcode()+"");
			modelList.add(model);
		}
		return modelList;
	}

	public Map<String, Object> getIMInfo(Long userId, Long imUserId, int isGroup) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		String url = "";
		String nickname = "";
		try {
			if (isGroup == 0) {
				// 私聊
				Map<String, Object> obj = friendsdbService.friendHomeDetail(
						userId, imUserId);
				url = obj.get("headpic").toString();
				nickname = obj.get("nickname").toString();
			} else if (isGroup == 1) {
				nickname = dogLocationMapper.selectByPrimaryKey(imUserId)
						.getAreaname();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.put("url", url);
		model.put("nickname", nickname);
		return model;
	}

	/**
	 * 获取 后台客服用户
	 * 
	 * @return
	 */
	public User getServiceUser() {
		try {
			if (serviceUser == null) {
				Map packagesMap = MessageConsumerConfig
						.readConfig("config.properties");
				String service_phone = packagesMap.get("service_phone")
						.toString().trim();
				serviceUser = userMapper.selectByPhone(service_phone);
			}
		} catch (Exception e) {
		}

		return serviceUser;
	}

}
