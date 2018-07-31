package com.dogpro.service.impl.dbservice;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.tool.MQTTapi;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.ThirdPartyCheck;
import com.dogpro.common.tool.UUIDGenerator;
import com.dogpro.dao.MessageMapper;
import com.dogpro.dao.ThirdPartyMapper;
import com.dogpro.dao.UcaptchaMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.MqttUser;
import com.dogpro.domain.model.MqttUserExample;
import com.dogpro.domain.model.ThirdParty;
import com.dogpro.domain.model.ThirdPartyExample;
import com.dogpro.domain.model.Ucaptcha;
import com.dogpro.domain.model.UcaptchaExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;
import com.dogpro.service.dbservice.MqttUserdbService;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.ServiceRecorddbService;
import com.dogpro.service.dbservice.SettingdbService;
import com.dogpro.service.dbservice.UserCheckdbService;
import com.dogpro.service.dbservice.UserInfodbService;
@Service("UserCheckdbService")
public class UserCheckdbServiceImpl implements UserCheckdbService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UcaptchaMapper ucaptchaMapper;
	@Autowired
	private RedisdbService redisdbService;
	@Autowired
	private SettingdbService settingdbService;
	@Autowired
	private MqttUserdbService mqttUserdbService;
	@Autowired
	private ThirdPartyMapper thirdPartyMapper;
	@Autowired
	private ServiceRecorddbService serviceRecorddbService;
	@Autowired
	private MessageMapper messageMapper;
	
	
	//参数配置文件
	private Map packagesMap = MessageConsumerConfig
			.readConfig("config.properties");
	//用户初始化 默认参数 头像
	private String default_headPic = packagesMap.get("default_headPic").toString().trim();
	
	
	//注册
	public Map<String, Object> regUser(String phone, String pswd, String captcha,int type,String pushtoken) {
		Map<String, Object> model = new HashMap<String, Object>();
		int flag=0;
		String msg="";
		Long userId = (long) 0;
		String token = "";
		String mqttusername = "";
		String mqttpassword = "";
		String UUID = "";
		//判断手机号码能否使用
		UserExample userExample = new UserExample();
		UserExample.Criteria uCriteria = userExample.createCriteria();
		uCriteria.andPhoneEqualTo(phone);
		List<User> users = userMapper.selectByExample(userExample);
		if(users.isEmpty()){
			flag = 1;
		}else{
			flag = 0;
		}
		if(flag == 1){
			UcaptchaExample uExample = new UcaptchaExample();
			UcaptchaExample.Criteria ucCriteria = uExample.createCriteria();
			ucCriteria.andPhoneEqualTo(phone).andStateEqualTo(1).andTypeEqualTo(1);
			List<Ucaptcha> ucaptchas = ucaptchaMapper.selectByExample(uExample);
			if(ucaptchas.isEmpty()){
				flag = 0;
				msg  = "手机未验证";
			}else{
				Ucaptcha ucaptcha = ucaptchas.get(0);
				Calendar cal=Calendar.getInstance();
				if(cal.getTime().getTime()>ucaptcha.getDeadtime().getTime()){
					flag = 0;
					msg  = "验证码已过3分钟有效期";
				}
				else if(!ucaptcha.getCaptcha().equals(captcha.toLowerCase())){
					flag = 0;
					msg  = "验证码错误";
				}else{
					//更新验证码表
					ucaptcha.setCaptcha(captcha);
					ucaptcha.setState(0);
					Date time = new java.sql.Date(new java.util.Date().getTime());
					ucaptcha.setUpdatetimes(time);
					ucaptchaMapper.updateByPrimaryKeySelective(ucaptcha);
					//更新user表
					User user = new User();
					user.setPhone(phone);
					user.setPswd(pswd);
					UUID = UUIDGenerator.getUUID32();
					user.setUuid(UUID);
					Calendar calendar = Calendar.getInstance();
					user.setAddtimes(calendar.getTime());
					user.setUpdatetimes(calendar.getTime());
					user.setHeadpic(default_headPic);
					user.setState(1);
					user.setIscompleted(0);
					userMapper.insertSelective(user);
					flag = 1;
					msg  = "注册成功";
					userId = user.getUserId();
					token = UUIDGenerator.getToken64();
					//im 插入redis库 
					CheckUser checkUser = new CheckUser();
					checkUser.setUserId(userId);
					checkUser.setToken(token);
					checkUser.setPR("1");
					checkUser.setPU("2");
					redisdbService.setUser(userId, checkUser);
					//总userId 集合 插入 redis库
					redisdbService.SADDalluser(userId);
					//提送 userId-token 插入redis库
					String pushtokens = "";
					switch (type) {
					case 1:
						//安卓
						pushtokens = "AND_"+pushtoken;
						break;
					case 2:
						//IOS
						pushtokens = "IOS_"+pushtoken;
						break;
					default:
						break;
					}
					//获取用户昵称
//					String nickname = userMapper.selectByPrimaryKey(userId).getNickname();
					//vistor pushtoken set
//					redisdbService.SREMvisitorPush(pushtokens);
					//user pushtoken k-v
					redisdbService.setUserPush(userId, pushtokens,"");
					//user pushtoken list
					redisdbService.pushUserPush(userId+"", pushtokens);
					//设置初始化
					settingdbService.initSetting(userId);
					//设置mqtt 账号
					mqttusername = phone;
					mqttpassword = UUID;
					mqttUserdbService.addMqttUser(userId, mqttusername, mqttpassword);
					//发送客服mqtt im私聊信息
					User serviceUser = serviceRecorddbService.getServiceUser();
					if(serviceUser!=null){
						Message message = new Message();
						message.setType(3);//im 私聊
						message.setState(0);//未处理状态
						Map<String, Object> contentMap = new HashMap<String, Object>();
						contentMap.put("content", "注册成功，有任何问题都可向铲屎侠客服反馈哦~>_<~");
						JSONObject jsonObject = new JSONObject(contentMap);
						message.setContent(jsonObject.toJSONString());
						message.setSenduserid(serviceUser.getUserId());
						message.setAcceptuserid(userId);
						message.setSendlongitude(0f);
						message.setSendlatitude(0f);
						Date currentTime = new Date();
						message.setMsgcode(currentTime.getTime());
						message.setAddtimes(currentTime);
						message.setUpdatetimes(currentTime);
						message.setSendtimes(currentTime);
						messageMapper.insertSelective(message);
						redisdbService.handleMsg(message.getSenduserid(), message.getAcceptuserid(), 1, message.getContent(), currentTime.getTime());
					}
				}
			}
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("userId", userId);
		model.put("token", token);
		model.put("mqttusername", mqttusername);
		model.put("mqttpassword", mqttpassword);
		
		return model;
	}
	
	
	//用户登录
	public Map<String, Object> loginUser(String phone, String pswd,int type,String pushtoken) {
		Map<String, Object> model = new HashMap<String, Object>();
		int flag=0;
		String msg="";
		long userId=0;
		int skipflag = 0;
		String token = "";
		String mqttusername = "";
		String mqttpassword = "";
		String _headurl=""; 
		String _nickname="";
		
		

		UserExample userExample = new UserExample();
		UserExample.Criteria uCriteria = userExample.createCriteria();
		uCriteria.andPhoneEqualTo(phone).andStateEqualTo(1);
		List<User> users = userMapper.selectByExample(userExample);
		if(!users.isEmpty()){
			User user=users.get(0);
			String userpswd = user.getPswd();
			if(userpswd!=null&&userpswd.equals(pswd)){
				
				flag=1;
				userId=user.getUserId();
				msg="登录成功";
				//上一手MQTT踢下线
				DisconnectClient(userId);
				token= UUIDGenerator.getToken64();
				if(user.getIscompleted()==1){
					skipflag=1;
				}
				//im 插入redis库 
				CheckUser checkUser = new CheckUser();
				checkUser.setUserId(userId);
				checkUser.setToken(token);
				checkUser.setPR("1");
				checkUser.setPU("2");
				redisdbService.setUser(userId, checkUser);
				//总userId 集合 插入 redis库
				redisdbService.SADDalluser(userId);
				//在线 uerId 集合 插入redis库
//				redisdbService.SADDonlineuser(userId);
				//提送 userId-token 插入redis库
				String pushtokens = "";
				switch (type) {
				case 1:
					//安卓
					pushtokens = "AND_"+pushtoken;
					break;
				case 2:
					//IOS
					pushtokens = "IOS_"+pushtoken;
					break;
				default:
					break;
				}
				//获取用户昵称
				String nickname = userMapper.selectByPrimaryKey(userId).getNickname();
				if(nickname==null){
					nickname = "某人";
				}
				_nickname=nickname;
				//vistor pushtoken set
				redisdbService.SREMvisitorPush(pushtokens);
				//user pushtoken k-v
				redisdbService.setUserPush(userId, pushtokens,nickname);
				//user pushtoken list
				redisdbService.pushUserPush(userId+"", pushtokens);
				//设置初始化
				settingdbService.initSetting(userId);
				//获取mqtt 账号密码
				mqttusername = user.getPhone();
				mqttpassword = user.getUuid();
				_headurl=user.getHeadpic();
			}else{
				msg="密码错误";
			}
			
		}else{
			flag=0;
			msg="用户不存在";
		}
		if(flag==1){
			System.out.println("userId:"+userId+" 登陆成功");
		}
		
		model.put("flag", flag);
		model.put("userId", userId);
		model.put("msg", msg);
		model.put("token", token);
		model.put("skipflag", skipflag);
		model.put("mqttusername", mqttusername);
		model.put("mqttpassword", mqttpassword);
		model.put("headurl", _headurl);
		model.put("nickname", _nickname);
	
		return model;
	}

	
	//app启动发送 pushtoken
	public Map<String, Object> commitPushtoken(String pushtoken, int type) {
		//返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		try {
			//提送 userId-token 插入redis库
			String pushtokens = "";
			switch (type) {
			case 1:
				//安卓
				pushtokens = "AND_"+pushtoken;
				break;
			case 2:
				//IOS
				pushtokens = "IOS_"+pushtoken;
				break;
			default:
				break;
			}
			//vistors pushtoken set
			redisdbService.SADDvisitorPush(pushtokens);
			//user pushtoken list
			redisdbService.pushUserPush(0+"", pushtokens);
			flag = 1;
			msg = "成功";
		} catch (Exception e) {
			flag = 0;
			msg = "失败";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}

	//第三方登陆
	public Map<String, Object> thirdPartyLogin(int thirdPartyType,int type,String pushtoken,String access_token,String openid) {
		//返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		//登陆标志
		int flag=0;
		String msg="";
		long userId=0;
		String token = "";
		String mqttusername = "";
		String mqttpassword = "";
		String _headurl=""; 
		String _nickname="";
		//跳转标志
		int skipflag = 0;
		//验证第三方登陆 access_token 和 openid
		if(thirdPartyCheck(thirdPartyType, access_token, openid)){
			//搜索有没有该 第三方记录
			ThirdPartyExample thirdPartyExample = new ThirdPartyExample();
			ThirdPartyExample.Criteria tCriteria = thirdPartyExample.createCriteria();
			tCriteria.andOpenidEqualTo(openid).andStateEqualTo(1);
			List<ThirdParty> thirdParties = thirdPartyMapper.selectByExample(thirdPartyExample);
			if(thirdParties.isEmpty()){
				//未第三方登陆绑定用户
				flag = 1;
				skipflag = 0;
				msg = "未绑定用户";
			}else{
				//登陆成功
				flag = 1;
				skipflag = 1;
				msg = "登陆成功";
				ThirdParty thirdParty = thirdParties.get(0);
				userId = thirdParty.getUserid();
				token= UUIDGenerator.getToken64();
				User user = userMapper.selectByPrimaryKey(userId);
				//im 插入redis库 
				CheckUser checkUser = new CheckUser();
				checkUser.setUserId(userId);
				checkUser.setToken(token);
				checkUser.setPR("1");
				checkUser.setPU("2");
				redisdbService.setUser(userId, checkUser);
				//总userId 集合 插入 redis库
				redisdbService.SADDalluser(userId);
				//提送 userId-token 插入redis库
				String pushtokens = "";
				switch (type) {
				case 1:
					//安卓
					pushtokens = "AND_"+pushtoken;
					break;
				case 2:
					//IOS
					pushtokens = "IOS_"+pushtoken;
					break;
				default:
					break;
				}
				//获取用户昵称
				String nickname = userMapper.selectByPrimaryKey(userId).getNickname();
				if(nickname==null){
					nickname = "某人";
				}
				_nickname=nickname;
				//vistor pushtoken set
				redisdbService.SREMvisitorPush(pushtokens);
				//user pushtoken k-v
				redisdbService.setUserPush(userId, pushtokens,nickname);
				//user pushtoken list
				redisdbService.pushUserPush(userId+"", pushtokens);
				//设置初始化
				settingdbService.initSetting(userId);
				mqttusername = user.getPhone();
				mqttpassword = user.getUuid();
				_headurl=user.getHeadpic();
			}
		}else{
			//验证第三方登陆 access_token 和 openid 错误
			flag = 0;
			msg = "第三方验证失败";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("userId", userId);
		model.put("token", token);
		model.put("skipflag", skipflag);
		model.put("mqttusername", mqttusername);
		model.put("mqttpassword", mqttpassword); 
		model.put("headurl", _headurl);
		model.put("nickname", _nickname);
		return model;
	}
	
	public boolean vevifyPhone(String phone) {
		//判断手机号码能否使用
		UserExample userExample = new UserExample();
		UserExample.Criteria uCriteria = userExample.createCriteria();
		uCriteria.andPhoneEqualTo(phone);
		List<User> users = userMapper.selectByExample(userExample);
		if(users.isEmpty()){
			return true;
		}else{
			return false;
		}
	}


	public Map<String, Object> vevifyCaptcha(String phone, String captcha,int type,Boolean updateCaptchaData) {
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		UcaptchaExample uExample = new UcaptchaExample();
		UcaptchaExample.Criteria ucCriteria = uExample.createCriteria();
		ucCriteria.andPhoneEqualTo(phone).andStateEqualTo(1).andTypeEqualTo(type);
		List<Ucaptcha> ucaptchas = ucaptchaMapper.selectByExample(uExample);
		if(ucaptchas.isEmpty()){
			flag = 0;
			msg  = "手机未验证";
		}else{
			Ucaptcha ucaptcha = ucaptchas.get(0);
			Calendar cal=Calendar.getInstance();
			if(cal.getTime().getTime()>ucaptcha.getDeadtime().getTime()){
				flag = 0;
				msg  = "验证码已过3分钟有效期";
			}
			else if(!ucaptcha.getCaptcha().equals(captcha.toLowerCase())){
				flag = 0;
				msg  = "验证码错误";
			}else{
				flag = 1;
				msg = "验证成功";
				if(updateCaptchaData){
					//更新验证码表  验证码失效
					ucaptcha.setCaptcha(captcha);
					ucaptcha.setState(0);
					Date time = new java.sql.Date(new java.util.Date().getTime());
					ucaptcha.setUpdatetimes(time);
					ucaptchaMapper.updateByPrimaryKeySelective(ucaptcha);
				}
			}
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}
	public Map<String, Object> resetUserPwds(String phone, String pswd1,String pswd2,
			String captcha) {
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		User user = null;
		UserExample userExample = new UserExample();
		UserExample.Criteria userCriteria = userExample.createCriteria();
		userCriteria.andPhoneEqualTo(phone).andStateEqualTo(1);
		List<User> users = userMapper.selectByExample(userExample);
		if(!users.isEmpty()){
			user = users.get(0);
		}
		if(user!=null){
			UcaptchaExample uExample = new UcaptchaExample();
			UcaptchaExample.Criteria uCriteria = uExample.createCriteria();
			uCriteria.andPhoneEqualTo(phone).andStateEqualTo(1).andTypeEqualTo(2);
			List<Ucaptcha> ucaptchas = ucaptchaMapper.selectByExample(uExample);
			if(ucaptchas.isEmpty()){
				flag = 0;
				msg = "该手机号码未验证";
			}else{
				if(pswd1.equals(pswd2)){
					Calendar calendar = Calendar.getInstance();
					if(calendar.getTime().getTime()<ucaptchas.get(0).getDeadtime().getTime()){
						if((captcha.toLowerCase()).equals(ucaptchas.get(0).getCaptcha())){
							//更新验证码数据
							Ucaptcha ucaptcha = ucaptchas.get(0);
							ucaptcha.setState(0);
							ucaptcha.setUpdatetimes(calendar.getTime());
							ucaptchaMapper.updateByPrimaryKeySelective(ucaptcha);
							//更新user表数据
							user.setPswd(pswd1);
							user.setUpdatetimes(calendar.getTime());
							userMapper.updateByPrimaryKeySelective(user);
							
							flag = 1;
							msg = "修改成功";
						}else{
							flag = 0;
							msg = "验证码错误";
						}
					}else{
						flag = 0;
						msg = "该手机号码验证已超时";
					}
				}else{
					flag = 0;
					msg = "两次输入密码不一致";
				}
			}
		}else{
			flag = 0;
			msg = "该用户不存在";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}


	public boolean thirdPartyCheck(int type, String accesstoken, String openid) {
		try {
			if(type==0){
				//微信登陆
				String result = ThirdPartyCheck.WeixinCheck(accesstoken, openid);
				if(result!=null){
					JSONObject jsonObject = JSONObject.parseObject(result);
					Integer errcode = jsonObject.getInteger("errcode");
					if(errcode.compareTo(0)==0){
						//access_token-openid 入 redis
						redisdbService.setAccessToken_Openid(accesstoken, openid, type);
						return true;
					}
				}
			}else if(type==1){
				//qq登陆
				String result = ThirdPartyCheck.QQCheck(accesstoken);
				if(result!=null){
					JSONObject jsonObject = JSONObject.parseObject(result);
					String QQopenid = jsonObject.getString("openid");
					if(QQopenid!=null&&QQopenid.equals(openid)){
						//access_token-openid 入 redis
						redisdbService.setAccessToken_Openid(accesstoken, openid, type);
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	//第三方验证手机号码
	public Map<String, Object> thirdPartyCheckPhone(String phone,String captcha){
		//返回参数
		Map<String , Object> result = new HashMap<String, Object>();
		int flag = 0;
		String msg = "验证失败";
		int isexist = 0;
		int isCompleted = 0;
		String headPic = "";
		String nickname = "";
		int sex = 0;
		String sign = "";
		String provinces = "";
		String municipalities = "";
		String districts = "";
		String address = "";
		//不更新验证码数据  验证验证码
		Map<String, Object> map = vevifyCaptcha(phone, captcha,3, false);
		if(map.get("flag").toString().equals("1")){
			//验证成功
			flag = 1;
			msg = "验证成功";
			//判断是否存在该手机号码用户
			User user = userMapper.selectByPhone(phone);
			if(user!=null){
				isexist = 1;
				if(user.getIscompleted()==1)
				isCompleted = 1;
				headPic = user.getHeadpic();
				nickname = user.getNickname();
				sex = user.getSex();
				sign = user.getSign();
				provinces = user.getProvinces();
				municipalities = user.getMunicipalities();
				districts = user.getDistricts();
				address = user.getAddress();
			}
		}else{
			flag = 0;
			msg = map.get("msg").toString();
		}
		result.put("flag", flag);
		result.put("msg", msg);
		result.put("isexist",isexist);
		result.put("isCompleted", isCompleted);
		result.put("headPic", headPic);
		result.put("nickname", nickname);
		result.put("sex", sex);
		result.put("sign", sign);
		result.put("provinces", provinces);
		result.put("municipalities", municipalities);
		result.put("districts", districts);
		result.put("address", address);
		return result;
	}

	// 第三方绑定 用户
	public Map<String, Object> thirdPartyBindUser(String access_token,
			String openid, int thirdPartyType, String pushtoken, int type,
			String phone, String captcha,User user) {
		//返回参数
		Map<String,Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "绑定失败";
		Long userId = 0l;
		String token = "";
		String mqttusername = "";
		String mqttpassword = "";

		//更新验证码表 验证
		Map<String, Object> map = vevifyCaptcha(phone, captcha,3, true);
		if(map.get("flag").toString().equals("1")){
			//验证成功
			User olduser = userMapper.selectByPhone(phone);
			if(olduser!=null){
				//存在该用户
				ThirdParty thirdParty = thirdPartyMapper.selectByUidAndType(olduser.getUserId(), thirdPartyType);
				if(thirdParty!=null){
					//已绑定过了 不能绑定了
					flag = 0;
					msg = "该用户已绑定其他第三方账号";
				}else{
					//未绑定过其他账号
					Date currentTime = new Date();
					ThirdParty newThirdParty = new ThirdParty();
					newThirdParty.setUserid(olduser.getUserId());
					newThirdParty.setAccessToken(access_token);
					newThirdParty.setOpenid(openid);
					newThirdParty.setType(thirdPartyType);
					newThirdParty.setState(1);
					newThirdParty.setAddtimes(currentTime);
					newThirdParty.setUpdatetimes(currentTime);
					thirdPartyMapper.insertSelective(newThirdParty);
					userId = olduser.getUserId();
					mqttusername = olduser.getPhone();
					mqttpassword = olduser.getUuid();
					if (olduser.getIscompleted()==0) {
						//未完善信息
						flag = 1;
						msg = "绑定成功";
						olduser.setNickname(user.getNickname());
						olduser.setHeadpic(user.getHeadpic());
						olduser.setSign(user.getSign());
						olduser.setSex(user.getSex());
						olduser.setAddress(user.getAddress());
						olduser.setProvinces(user.getProvinces());
						olduser.setMunicipalities(user.getMunicipalities());
						olduser.setDistricts(user.getDistricts());
						olduser.setIscompleted(1);
						olduser.setUpdatetimes(currentTime);
						userMapper.updateByPrimaryKeySelective(olduser);
					}else{
						//已完善信息
						flag = 1;
						msg = "绑定成功";
						olduser.setNickname(user.getNickname());
						olduser.setHeadpic(user.getHeadpic());
						olduser.setSign(user.getSign());
						olduser.setSex(user.getSex());
						olduser.setAddress(user.getAddress());
						olduser.setProvinces(user.getProvinces());
						olduser.setMunicipalities(user.getMunicipalities());
						olduser.setDistricts(user.getDistricts());
						olduser.setIscompleted(1);
						olduser.setUpdatetimes(currentTime);
						userMapper.updateByPrimaryKeySelective(olduser);
					}
				}
			}else{
				//不存在用户
				flag = 1;
				msg = "绑定成功";
				Date currentTime = new Date();
				User newUser = new User();
				newUser.setPhone(phone);
				String UUID = UUIDGenerator.getUUID32();
				newUser.setUuid(UUID);
				newUser.setNickname(user.getNickname());
				newUser.setHeadpic(user.getHeadpic());
				newUser.setSign(user.getSign());
				newUser.setSex(user.getSex());
				newUser.setAddress(user.getAddress());
				newUser.setProvinces(user.getProvinces());
				newUser.setMunicipalities(user.getMunicipalities());
				newUser.setDistricts(user.getDistricts());
				newUser.setIscompleted(1);
				newUser.setAddtimes(currentTime);
				newUser.setUpdatetimes(currentTime);
				userMapper.insertSelective(newUser);
				//插入第三方记录
				ThirdParty newThirdParty = new ThirdParty();
				newThirdParty.setUserid(newUser.getUserId());
				newThirdParty.setAccessToken(access_token);
				newThirdParty.setOpenid(openid);
				newThirdParty.setType(thirdPartyType);
				newThirdParty.setState(1);
				newThirdParty.setAddtimes(currentTime);
				newThirdParty.setUpdatetimes(currentTime);
				thirdPartyMapper.insertSelective(newThirdParty);
				userId = newUser.getUserId();
				mqttusername = newUser.getPhone();
				mqttpassword = newUser.getUuid();
				//发送客服mqtt im私聊信息
				User serviceUser = serviceRecorddbService.getServiceUser();
				if(serviceUser!=null){
					Message message = new Message();
					message.setType(3);//im 私聊
					message.setState(0);//未处理状态
					Map<String, Object> contentMap = new HashMap<String, Object>();
					contentMap.put("content", "注册成功，有任何问题都可向铲屎侠客服反馈哦~>_<~");
					JSONObject jsonObject = new JSONObject(contentMap);
					message.setContent(jsonObject.toJSONString());
					message.setSenduserid(serviceUser.getUserId());
					message.setAcceptuserid(userId);
					message.setSendlongitude(0f);
					message.setSendlatitude(0f);
					Date Time = new Date();
					message.setMsgcode(Time.getTime());
					message.setAddtimes(Time);
					message.setUpdatetimes(Time);
					message.setSendtimes(Time);
					messageMapper.insertSelective(message);
					redisdbService.handleMsg(message.getSenduserid(), message.getAcceptuserid(), 1, message.getContent(), Time.getTime());
				}
			}
		}else{
			//验证失败
			flag = 0;
			msg = "手机验证失败";
		}
		
		if(flag==1){
			token= UUIDGenerator.getToken64();
			//上一手MQTT踢下线
			DisconnectClient(userId);
			//im 插入redis库 
			CheckUser checkUser = new CheckUser();
			checkUser.setUserId(userId);
			checkUser.setToken(token);
			checkUser.setPR("1");
			checkUser.setPU("2");
			redisdbService.setUser(userId, checkUser);
			//总userId 集合 插入 redis库
			redisdbService.SADDalluser(userId);
			//推送 userId-token 插入redis库
			String pushtokens = "";
			switch (type) {
			case 1:
				//安卓
				pushtokens = "AND_"+pushtoken;
				break;
			case 2:
				//IOS
				pushtokens = "IOS_"+pushtoken;
				break;
			default:
				break;
			}
			//获取用户昵称
			String nickname = userMapper.selectByPrimaryKey(userId).getNickname();
			if(nickname==null){
				nickname = "某人";
			}
			//vistor pushtoken set
			redisdbService.SREMvisitorPush(pushtokens);
			//user pushtoken k-v
			redisdbService.setUserPush(userId, pushtokens,nickname);
			//user pushtoken list
			redisdbService.pushUserPush(userId+"", pushtokens);
			//设置初始化
			settingdbService.initSetting(userId);
			//设置mqtt 账号
			mqttUserdbService.addMqttUser(userId, mqttusername, mqttpassword);
			//修改 redis 用户 nickname
			redisdbService.setUserNickname(userId,nickname);
			// 修改redis库 PushToken
			redisdbService.setUserPush(userId, pushtokens,nickname);
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("userId", userId);
		model.put("token", token);
		model.put("mqttusername", mqttusername);
		model.put("mqttpassword", mqttpassword);
		return model;
	}


	public boolean DisconnectClient(Long userId) {
		CheckUser checkUser = redisdbService.getUser(userId);
		if(checkUser!=null){
			String token = checkUser.getToken();
			String client_id = "U"+userId+"_"+token;
			MQTTapi mqtTapi = new MQTTapi();
			return mqtTapi.deleteClient(client_id);
		}
		return true;
	}
	
	
}
