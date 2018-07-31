package com.dogpro.service.impl.dbservice;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;





import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.tool.SMS;
import com.dogpro.common.tool.UUIDGenerator;
import com.dogpro.dao.UcaptchaMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.Ucaptcha;
import com.dogpro.domain.model.UcaptchaExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;
import com.dogpro.service.dbservice.UcaptchadbService;
@Service("UcaptchadbService")
public class UcaptchadbServiceImpl implements UcaptchadbService{
	@Autowired
	private UcaptchaMapper ucaptchaMapper;
	@Autowired
	private UserMapper userMapper;
	
	
	public List<Ucaptcha> selectByExample(UcaptchaExample example) {
		// TODO Auto-generated method stub
		return ucaptchaMapper.selectByExample(example);
	}

	public int insertSelective(Ucaptcha record) {
		// TODO Auto-generated method stub
		return ucaptchaMapper.insertSelective(record);
	}
	public int updateByExampleSelective(Ucaptcha record,  UcaptchaExample example){
		return ucaptchaMapper.updateByExampleSelective(record, example);
	}

	public int updateByPrimaryKeySelective(Ucaptcha record) {
		// TODO Auto-generated method stub
		return ucaptchaMapper.updateByPrimaryKeySelective(record);
	}
	//获取验证码
	public Map<String, Object> getCaptchaByType(String phone, int type) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		int flag=0;
		String msg = "发送失败";
		String captcha=UUIDGenerator.getCaptcha4Num();
		//检测手机号码可不可用
		UserExample userExample = new UserExample();
		UserExample.Criteria uCriteria = userExample.createCriteria();
		uCriteria.andPhoneEqualTo(phone);
		List<User> users = userMapper.selectByExample(userExample);
		if(!users.isEmpty()){
			switch (type) {
			case 1:
				//手机号注册
				flag = 0;
				msg = "该账号已注册";
				break;
			case 2:
				//找回密码
				flag = 1;
				break;
			case 3:
				//第三方验证
				flag = 1;
				break;
			default:
				flag = 0;
				break;
			}
		}else{
			switch (type) {
			case 1:
				//手机号注册
				flag = 1;
				break;
			case 2:
				//找回密码
				flag = 0;
				msg = "该账号不存在";
				break;
			case 3:
				//第三方验证
				flag = 1;
				break;
			default:
				flag = 0;
				break;
			}
		}
		//若手机号码可用，发送验证码，更新验证码数据
		if(flag == 1){
			UcaptchaExample ucaptchaExample=new UcaptchaExample();
			UcaptchaExample.Criteria criteria=ucaptchaExample.createCriteria();
			criteria.andPhoneEqualTo(phone);
			List<Ucaptcha> ucaptchas = ucaptchaMapper.selectByExample(ucaptchaExample);
			if(ucaptchas.isEmpty()){
				Ucaptcha ucaptcha = new Ucaptcha();
				ucaptcha.setPhone(phone);
				ucaptcha.setCaptcha(captcha);
				ucaptcha.setState(1);
				ucaptcha.setType(type);
				
				Calendar cal=Calendar.getInstance();
				ucaptcha.setRequesttime(cal.getTime());
				ucaptcha.setAddtimes(cal.getTime());
				ucaptcha.setUpdatetimes(cal.getTime());
				cal.add(Calendar.MINUTE, 3);
				ucaptcha.setDeadtime(cal.getTime());
				ucaptchaMapper.insert(ucaptcha);
			}else{
				Ucaptcha ucaptcha = ucaptchas.get(0);
				ucaptcha.setCaptcha(captcha);
				ucaptcha.setState(1);
				ucaptcha.setType(type);
				Calendar cal=Calendar.getInstance();
				ucaptcha.setRequesttime(cal.getTime());
				ucaptcha.setUpdatetimes(cal.getTime());
				cal.add(Calendar.MINUTE, 3);
				ucaptcha.setDeadtime(cal.getTime());
				ucaptchaMapper.updateByPrimaryKeySelective(ucaptcha);
			}
			
			//向对应手机号码发送验证码短信

			String sms = "";
			switch (type) {
			case 1:
				sms = SMS.RegisterCaptcha(phone, captcha);
				msg = "发送成功";
				break;
			case 2:
				sms = SMS.AlterPwdCaptcha(phone, captcha);
				msg = "发送成功";
				break;
			case 3:
				sms = SMS.ThirdPartyCaptcha(phone, captcha);
				msg = "发送成功";
				break;
			default:
				break;
			}
			
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("captcha", "");
		return model;
	}
	//登陆
	public Map<String, Object> loginUser(String phone,String pswd){
		Map<String, Object> model = new HashMap<String, Object>();
		int flag=0;
		String msg="";
		long userId=0;
		String token=UUIDGenerator.getToken64();
		
		UserExample userExample = new UserExample();
		UserExample.Criteria uCriteria = userExample.createCriteria();
		uCriteria.andPhoneEqualTo(phone);
		List<User> users = userMapper.selectByExample(userExample);
		if(!users.isEmpty()){
			User user=users.get(0);
			if(user.getPswd().equals(pswd)){
				flag=1;
				userId=user.getUserId();
				msg="登录成功";
				
			}else{
				msg="密码错误";
			}
			
		}else{
			flag=0;
			msg="用户不存在";
		}
		model.put("flag", flag);
		model.put("userId", userId);
		model.put("msg", msg);
		model.put("token", token);
		return model;
	}
}
