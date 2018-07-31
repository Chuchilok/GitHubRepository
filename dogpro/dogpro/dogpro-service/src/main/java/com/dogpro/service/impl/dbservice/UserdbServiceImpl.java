package com.dogpro.service.impl.dbservice;

import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Flags.Flag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.tool.UUIDGenerator;
import com.dogpro.dao.UcaptchaMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.Ucaptcha;
import com.dogpro.domain.model.UcaptchaExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;
import com.dogpro.service.dbservice.MqttUserdbService;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.SettingdbService;
import com.dogpro.service.dbservice.UserInfodbService;

@Service("UserInfodbService")
public class UserdbServiceImpl implements UserInfodbService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UcaptchaMapper ucaptchaMapper;
	@Autowired
	private RedisdbService redisdbService;
	@Autowired
	private MqttUserdbService mqttUserdbService;
	@Autowired
	private SettingdbService settingdbService;

	public User chickUserByPhone(String phone) {
		// TODO Auto-generated method stub
		UserExample uExample = new UserExample();
		UserExample.Criteria uCriteria = uExample.createCriteria();
		uCriteria.andPhoneEqualTo(phone);
		List<User> users = userMapper.selectByExample(uExample);
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	public int insertSelective(User user) {
		return userMapper.insertSelective(user);
	}

	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

	public User selectByPrimaryKey(Long userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}

	// 获取用户个人信息
	public Map<String, Object> getUserInfo(Long userId) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		User user = userMapper.selectByPrimaryKey(userId);
		if (user != null) {
			model.put("sex", user.getSex());
			model.put("headPic", user.getHeadpic());
			model.put("nickname", user.getNickname());
			model.put("sign", user.getSign());
			model.put("provinces", user.getProvinces());
			model.put("municipalities", user.getMunicipalities());
			model.put("districts", user.getDistricts());
			// model.put("townStreet", user.getTownstreet());
			model.put("address", user.getAddress());
		}
		return model;
	}

	// 修改个人信息
	public Map<String, Object> alterUserInfo(User user) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "修改失败";
		String _headurl=""; 
		String _nickname="";
		try {
			User newUser = new User();
			newUser.setUserId(user.getUserId());
			newUser.setNickname(user.getNickname());
			newUser.setHeadpic(user.getHeadpic());
			newUser.setSign(user.getSign());
			newUser.setSex(user.getSex());
			newUser.setAddress(user.getAddress());
			newUser.setProvinces(user.getProvinces());
			newUser.setMunicipalities(user.getMunicipalities());
			newUser.setDistricts(user.getDistricts());
			newUser.setIscompleted(1);
			newUser.setUpdatetimes(new java.sql.Date(new java.util.Date()
					.getTime()));
			if (userMapper.updateByPrimaryKeySelective(newUser) != 0) {
				flag = 1;
				msg = "修改成功";
				//修改 redis 用户 nickname
				redisdbService.setUserNickname(newUser.getUserId(), newUser.getNickname());
				// 修改redis库 PushToken
				String pushtoken = redisdbService.getUserPush(newUser
						.getUserId() + "");
				redisdbService.setUserPush(newUser.getUserId(), pushtoken,
						user.getNickname());
				_nickname=user.getNickname();
				_headurl=newUser.getHeadpic();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("headurl", _headurl);
		model.put("nickname", _nickname);
		return model;
	}

	// 修改密码
	public Map<String, Object> AlterUserPwds(Long userId, String pswd,
			String pswd1, String pswd2) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		User user = userMapper.selectByPrimaryKey(userId);
		if (user != null) {
			if (user.getPswd().equals(pswd)) {
				if (pswd1.equals(pswd2)) {
					user.setPswd(pswd1);
					user.setUpdatetimes(new java.sql.Date(new java.util.Date()
							.getTime()));
					userMapper.updateByPrimaryKeySelective(user);
					flag = 1;
					msg = "修改成功";

				} else {
					flag = 0;
					msg = "两次输入新密码不一致";
				}
			} else {
				flag = 0;
				msg = "原密码不正确";
			}
		} else {
			flag = 0;
			msg = "该用户不存在";
		}

		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}

	// 修改手机号码
	public Map<String, Object> alterUserPhone(Long userId, String phone,
			String captcha) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		User user = userMapper.selectByPrimaryKey(userId);
		if (user != null) {
			UcaptchaExample uExample = new UcaptchaExample();
			UcaptchaExample.Criteria uCriteria = uExample.createCriteria();
			uCriteria.andPhoneEqualTo(phone).andStateEqualTo(1)
					.andTypeEqualTo(1);
			List<Ucaptcha> ucaptchas = ucaptchaMapper.selectByExample(uExample);
			if (ucaptchas.isEmpty()) {
				flag = 0;
				msg = "该手机号码未验证";
			} else {
				Calendar calendar = Calendar.getInstance();
				if (calendar.getTime().getTime() < ucaptchas.get(0)
						.getDeadtime().getTime()) {
					if ((captcha.toLowerCase()).equals(ucaptchas.get(0)
							.getCaptcha())) {
						// 更新验证码数据
						Ucaptcha ucaptcha = ucaptchas.get(0);
						ucaptcha.setState(0);
						ucaptcha.setUpdatetimes(calendar.getTime());
						ucaptchaMapper.updateByPrimaryKeySelective(ucaptcha);
						// 更新user表数据
						user.setPhone(phone);
						user.setUpdatetimes(calendar.getTime());
						userMapper.updateByPrimaryKeySelective(user);
						// 更新 mqtt用户表
						mqttUserdbService.alterUsernameByUserId(userId, phone);
						flag = 1;
						msg = "修改成功";
					} else {
						flag = 0;
						msg = "验证码错误";
					}
				} else {
					flag = 0;
					msg = "该手机号码验证已超时";
				}
			}
		} else {
			flag = 1;
			msg = "该用户不存在";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}

	public boolean updateUserOfflineTime(Long userId, Long offlineTime) {
		// TODO Auto-generated method stub
		try {
			User user = userMapper.selectByPrimaryKey(userId);
			user.setOfflinetimes(offlineTime);
			userMapper.updateByPrimaryKeySelective(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public Map<String, Object> getIMinfo(Long userId, Long friendUserId) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 查找 朋友 id 对应 头像 昵称
			User user = userMapper.selectByPrimaryKey(friendUserId);
			String nickname = user.getNickname();
			String headpic = user.getHeadpic();
			result.put("nickname", nickname);
			result.put("headpic", headpic);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	// 第三方完善信息
	public Map<String, Object> thirdPartyFillInfo(Long userId, User user,String openid,int thirdPartyType,int type, String pushtoken,
			String phone, String captcha) {
		// TODO Auto-generated method stub
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "完善失败";
		String mqttusername = "";
		String mqttpassword = "";
		String _headurl=""; 
	String _nickname="";
		// 判断手机是否可用
		if (vevifyPhone(phone)) {
			// 判断验证码是否正确
			Map<String, Object> map = vevifyCaptcha(phone, captcha);
			if (map.get("flag").toString().equals("1")) {
				// 验证成功
				Date date = new Date();
				User newUser = userMapper.selectByPrimaryKey(userId);
				newUser.setPhone(phone);
				newUser.setNickname(user.getNickname());
				newUser.setHeadpic(user.getHeadpic());
				newUser.setSign(user.getSign());
				newUser.setSex(user.getSex());
				newUser.setAddress(user.getAddress());
				newUser.setState(1);
				newUser.setIscompleted(1);
				newUser.setUpdatetimes(date);
				String UUID = UUIDGenerator.getUUID32();
				newUser.setUuid(UUID);
				// 插入user表 数据库
				userMapper.updateByPrimaryKeySelective(newUser);
				// 总userId 集合 插入 redis库
				redisdbService.SADDalluser(userId);
				// 在线 uerId 集合 插入redis库
				redisdbService.SADDonlineuser(userId);
				// 推送 userId-token 插入redis库
				String pushtokens = "";
				switch (type) {
				case 1:
					// 安卓
					pushtokens = "AND_" + pushtoken;
					break;
				case 2:
					// IOS
					pushtokens = "IOS_" + pushtoken;
					break;
				default:
					break;
				}
				// 获取用户昵称
				String nickname = user.getNickname();
				if (nickname == null) {
					nickname = "某人";
				}
				_nickname=nickname;
				// vistor pushtoken set
				redisdbService.SREMvisitorPush(pushtokens);
				// user pushtoken k-v
				redisdbService.setUserPush(userId, pushtokens, nickname);
				// user pushtoken list
				redisdbService.pushUserPush(userId + "", pushtokens);
				// 设置初始化
				settingdbService.initSetting(userId);
				// 添加 mqtt用户
				mqttusername = phone;
				mqttpassword = UUID;
				mqttUserdbService.addMqttUser(userId, mqttusername,
						mqttpassword);

				flag = 1;
				msg = "完善成功";
				_headurl=user.getHeadpic();
			} else {
				// 验证失败
				flag = 0;
				msg = map.get("msg").toString();
			}
		} else {
			// 手机不可用
			flag = 0;
			msg = "手机号码不可用";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("mqttusername", mqttusername);
		model.put("mqttpassword", mqttpassword);
		model.put("headurl", _headurl);
		model.put("nickname", _nickname);
		return model;
	}

	public boolean vevifyPhone(String phone) {
		// TODO Auto-generated method stub
		// 判断手机号码能否使用
		UserExample userExample = new UserExample();
		UserExample.Criteria uCriteria = userExample.createCriteria();
		uCriteria.andPhoneEqualTo(phone);
		List<User> users = userMapper.selectByExample(userExample);
		if (users.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public Map<String, Object> vevifyCaptcha(String phone, String captcha) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		UcaptchaExample uExample = new UcaptchaExample();
		UcaptchaExample.Criteria ucCriteria = uExample.createCriteria();
		ucCriteria.andPhoneEqualTo(phone).andStateEqualTo(1).andTypeEqualTo(1);
		List<Ucaptcha> ucaptchas = ucaptchaMapper.selectByExample(uExample);
		if (ucaptchas.isEmpty()) {
			flag = 0;
			msg = "手机未验证";
		} else {
			Ucaptcha ucaptcha = ucaptchas.get(0);
			Calendar cal = Calendar.getInstance();
			if (cal.getTime().getTime() > ucaptcha.getDeadtime().getTime()) {
				flag = 0;
				msg = "验证码已过3分钟有效期";
			} else if (!ucaptcha.getCaptcha().equals(captcha.toLowerCase())) {
				flag = 0;
				msg = "验证码错误";
			} else {
				// 更新验证码表
				ucaptcha.setCaptcha(captcha);
				ucaptcha.setState(0);
				Date time = new java.sql.Date(new java.util.Date().getTime());
				ucaptcha.setUpdatetimes(time);
				ucaptchaMapper.updateByPrimaryKeySelective(ucaptcha);
				flag = 1;
				msg = "验证成功";
			}
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}
}
