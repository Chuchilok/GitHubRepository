package com.webpublish.service.impl.dbservice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webpublish.common.utils.UUIDGenerator;
import com.webpublish.dao.UserMapper;
import com.webpublish.domain.model.User;
import com.webpublish.domain.model.UserExample;
import com.webpublish.service.dbservice.UserTokendbService;
import com.webpublish.service.dbservice.UserdbService;

@Service("UserdbService")
public class UserdbServiceImpl implements UserdbService {
	@Autowired
	private UserMapper usermapper;
	@Autowired
	private UserTokendbService userTokendbService;
	
	public Map<String, Object> register(String username, String pswd) {
		// TODO Auto-generated method stub
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		// 判断该用户名是否已被使用
		UserExample userExample = new UserExample();
		UserExample.Criteria uCriteria = userExample.createCriteria();
		uCriteria.andUserNameEqualTo(username);
		if (usermapper.selectByExample(userExample).isEmpty()) {
			User newuser = new User();
			newuser.setUserName(username);
			newuser.setPswd(pswd);
			newuser.setState(1);
			Date date = new Date();
			newuser.setAddtimes(date);
			newuser.setUpdatetimes(date);
			usermapper.insertSelective(newuser);
			flag = 1;
			msg = "注册成功";
		} else {
			flag = 0;
			msg = "该用户名已被使用";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}

	public Map<String, Object> login(String username, String pswd) {
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		Long userId = 0l;
		String token = "";
		// 判断该用户名是否存在
		UserExample userExample = new UserExample();
		UserExample.Criteria uCriteria = userExample.createCriteria();
		uCriteria.andUserNameEqualTo(username);
		List<User> users = usermapper.selectByExample(userExample);
		if(!users.isEmpty()){
			User user = users.get(0);
			if(user.getPswd().equals(pswd)){
				flag = 1;
				msg = "登陆成功";
				userId = user.getId();
				token = UUIDGenerator.getToken64();
				//记录userid - token 
				userTokendbService.putUserToken(userId, token);
			}else{
				flag = 0;
				msg = "密码错误";
			}
		}else{
			flag = 0;
			msg = "该用户不存在";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("userId", userId);
		model.put("token", token);
		return model;
	}

}
