package com.dogpro.service.impl.dbservice;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.UUIDGenerator;
import com.dogpro.dao.MessageMapper;
import com.dogpro.dao.ServiceRecordMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.ServiceRecord;
import com.dogpro.domain.model.ServiceRecordExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.extend.ServiceRecord_User;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.ServiceRecorddbService;

@Service("ServiceRecorddbService")
public class ServiceRecorddbServiceImpl implements ServiceRecorddbService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ServiceRecordMapper serviceRecordMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private RedisdbService redisdbService;
	
	// 客服用户
	private User serviceUser = null;

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

	public List<Map<String, Object>> getUserList(int pageNo, int pageSize) {
		// 返回参数
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 搜索 客服 记录
		PaginationUtil paginationUtil = new PaginationUtil(pageNo, pageSize);
		List<ServiceRecord_User> serviceRecord_Users = serviceRecordMapper
				.getUserList(paginationUtil);
		for (ServiceRecord_User sRecord : serviceRecord_Users) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("userId", sRecord.getUserId());
			model.put("last_chat_time", sRecord.getLastChatTime());
			User user;
			String nickname = "";
			String headPic = "";
			if ((user = sRecord.getUser()) != null) {
				nickname = user.getNickname();
				headPic = user.getHeadpic();
			}
			model.put("nickname", nickname);
			model.put("headPic", headPic);
			result.add(model);
		}
		return result;
	}

	public List<Map<String, Object>> getUnreadMsg(Long userId, int pageNo,
			int pageSize) {
		// 返回参数
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		User service = getServiceUser();
		if (service != null) {
			//重新获取上次离线时间
//			service = userMapper.selectByPrimaryKey(service.getUserId());
			Long offlinetime = service.getOfflinetimes() == null ? 0 : service
					.getOfflinetimes();
			PaginationUtil pagination = new PaginationUtil(pageNo, pageSize);
			List<Message> messages = messageMapper.selectPrivateUnread(userId,
					offlinetime, pagination);
			for (Message message : messages) {
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("content", message.getContent());
					model.put("msgCode", message.getMsgcode());
					model.put("sendUid", message.getSenduserid());
					model.put("millisTime", message.getSendtimes());
					model.put("type", message.getType());
					result.add(model);
			}
		}
		return result;
	}

	public List<Map<String, Object>> getHistoryMsg(Long userId, Long sendUid,
			Long msgCode, int pageSize) {
		// 返回参数
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		User service = getServiceUser();
		if (service != null) {
			PaginationUtil pagination = new PaginationUtil(0, pageSize);
			if (msgCode == null || msgCode <= 0) {
				msgCode = new Date().getTime();
			}
			try {
				messageMapper.selectPrivateHistory(userId,
						sendUid, msgCode, pagination);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Message> messages = messageMapper.selectPrivateHistory(userId,
					sendUid, msgCode, pagination);
			for (Message message : messages) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("content", message.getContent());
				model.put("msgCode", message.getMsgcode());
				model.put("sendUid", message.getSenduserid());
				model.put("millisTime", message.getSendtimes());
				model.put("type", message.getType());
				result.add(model);
			}
		}
		return result;
	}

	public boolean checkServiceID(Long userId) {
		if(getServiceUser()!=null&&getServiceUser().getUserId()==userId){
			return true;
		}
		return false;
	}

	public boolean updateServiceRecordData(ServiceRecord serviceRecord) {
		ServiceRecord old = serviceRecordMapper.getServiceRecordrByUid(serviceRecord.getUserId());
		if(old!=null){
			Date currentTime = new Date();
			old.setChatCount(old.getChatCount()+1);
			old.setLastChatTime(currentTime);
			old.setUpdatetimes(currentTime);
			serviceRecordMapper.updateByPrimaryKeySelective(old);
			return true;
		}else{
			Date currentTime = new Date();
			serviceRecord.setChatCount(1);
			serviceRecord.setLastChatTime(currentTime);
			serviceRecord.setAddtimes(currentTime);
			serviceRecord.setUpdatetimes(currentTime);
			serviceRecord.setState(1);
			serviceRecordMapper.insertSelective(serviceRecord);
			return true;
		}
	}

	public Map<String, Object> serviceLogin(String phone, String pswd) {
		//返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "登陆失败";
		Long userId = 0l;
		String token = "";
		String mqttusername = "";
		String mqttpassword = "";
		User Service = getServiceUser();
		if(Service!=null){
			if(Service.getPhone().equals(phone)&&Service.getPswd().equals(pswd)){
				flag = 1;
				msg = "登陆成功";
				userId = Service.getUserId();
				token = UUIDGenerator.getToken64();
				mqttusername = Service.getPhone();
				mqttpassword = Service.getUuid();
				//im 插入redis库 
				CheckUser checkUser = new CheckUser();
				checkUser.setUserId(userId);
				checkUser.setToken(token);
				checkUser.setPR("1");
				checkUser.setPU("2");
				redisdbService.setUser(userId, checkUser);
				//总userId 集合 插入 redis库
				redisdbService.SADDalluser(userId);
				//获取用户昵称
				String nickname = userMapper.selectByPrimaryKey(userId).getNickname();
				if(nickname==null){
					nickname = "某人";
				}
				//修改 redis 用户 nickname
				redisdbService.setUserNickname(userId, nickname);
			}else{
				flag = 0;
				msg = "账号或密码不正确";
			}
		}else{
			flag = 0;
			msg = "客服账号不存在";
		}
		model.put("flag", flag);
		model.put("userId", userId);
		model.put("msg", msg);
		model.put("token", token);
		model.put("mqttusername", mqttusername);
		model.put("mqttpassword", mqttpassword);
		return model;
	}

	public Map<String, Object> getUserInfo(Long sendUid) {
		Map<String, Object> model = new HashMap<String, Object>();
		User user = userMapper.selectByPrimaryKey(sendUid);
		if(user!=null&&user.getState()==1&&user.getIscompleted()==1){
			model.put("sex", user.getSex());
			model.put("headpic", user.getHeadpic());
			model.put("nickname", user.getNickname());
			model.put("phone", user.getPhone());
			model.put("provinces", user.getProvinces());
			model.put("municipalities", user.getMunicipalities());
			model.put("districts", user.getDistricts());
			model.put("townstreet", user.getTownstreet());
			model.put("address", user.getAddress());
		}

		return model;
	}

	public List<User> getUserListAfterTime(Date afterTime) {
		ServiceRecordExample sExample = new ServiceRecordExample();
		ServiceRecordExample.Criteria sCriteria = sExample.createCriteria();
		//判断时间
		sCriteria.andLastChatTimeGreaterThan(afterTime).andStateEqualTo(1);
		List<ServiceRecord> serviceRecords = serviceRecordMapper.selectByExample(sExample);
		List<User> result = new ArrayList<User>();
		for(ServiceRecord serviceRecord:serviceRecords){
			User user = userMapper.selectByPrimaryKey(serviceRecord.getUserId());
			if(user!=null){
				result.add(user);
			}
		}
		return result;
	}

}
