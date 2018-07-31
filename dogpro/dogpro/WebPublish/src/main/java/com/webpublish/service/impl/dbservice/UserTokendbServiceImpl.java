package com.webpublish.service.impl.dbservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.webpublish.service.dbservice.UserTokendbService;

@Service("UserTokendbService")
public class UserTokendbServiceImpl implements UserTokendbService {
	private Map<Long,String> map = new HashMap<Long,String>();
	
	@Override
	public boolean putUserToken(Long userId, String token) {
		// TODO Auto-generated method stub
		try {
			map.put(userId, token);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	@Override
	public String getUserToken(Long userId) {
		// TODO Auto-generated method stub
		String token = null;
		try {
			token = map.get(userId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return token;
	}

	@Override
	public boolean matchUserToken(Long userId, String token) {
		// TODO Auto-generated method stub
//		traverseMap();
		try {
			String token2 = getUserToken(userId);
			if(token2!=null&&token2.equals(token)){
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public void traverseMap() {
		// TODO Auto-generated method stub
		for(Long userId:map.keySet()){
			System.err.println(userId +" : "+map.get(userId));
		}
	}

}
