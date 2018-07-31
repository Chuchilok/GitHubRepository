package com.dogpro.upload.service.impl.dbservice;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.tool.JedisUtil;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.ObjectUtil;
import com.dogpro.upload.service.dbservice.UserTokendbService;

@Service("UserTokendbService")
public class UserTokendbServiceImpl implements UserTokendbService{

	Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
	int dbindex = Integer.parseInt(packagesMap.get("redisUserdb").toString());
	private JedisUtil jedisUtil = new JedisUtil(dbindex);
	// im user对应token k-v key
	String imuserkey = "CheckUser";
	// accesstoken - openid type=0 微信
	String WeiXin_accesstoken_openid = "WeiXin_accesstoken_openid";
	// accesstoken - openid type=1 QQ
	String QQ_accesstoken_openid = "QQ_accesstoken_openid";
	
	
	
	public boolean matchToken(Long userId, String token) {
		// TODO Auto-generated method stub
		try {
			byte[] key = imuserkey.getBytes();
			byte[] field = userId.toString().getBytes();
			CheckUser checkUser = (CheckUser) ObjectUtil.bytes2Object(jedisUtil
					.hget(key, field));
			if(checkUser.getUserId()!=userId||!checkUser.getToken().equals(token)){
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	public boolean matchOpenid(String access_token, String openid,int type) {
		String key = null;
		switch (type) {
		case 0:
			//微信
			key = WeiXin_accesstoken_openid;
			break;
			case 1:
			//QQ
			key = QQ_accesstoken_openid;
		default:
			return false;
		}
		try {
			String trueopenid = jedisUtil.hget(key, access_token);
			if(trueopenid==null||!trueopenid.equals(openid)){
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
