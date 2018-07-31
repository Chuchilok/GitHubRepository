package com.dogpro.service.impl.dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.domain.CheckUser;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.UserTokendbService;

@Service("UserTokendbService")
public class UserTokendbServiceImpl implements UserTokendbService{

	@Autowired
	private RedisdbService redisdbService;
	public boolean matchToken(Long userId, String token) {
		CheckUser checkUser = redisdbService.getUser(userId);
		if(checkUser!=null&&checkUser.getToken().equals(token)){
			return true;
		}
		return false;
		
	}

	public String getUserToken(Long userId) {
		String token = null;
		CheckUser checkUser = redisdbService.getUser(userId);
		if(checkUser!=null){
			token = checkUser.getToken();
		}
		return token;
	}

}
