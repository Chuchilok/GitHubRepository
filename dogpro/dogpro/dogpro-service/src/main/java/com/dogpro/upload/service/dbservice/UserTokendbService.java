package com.dogpro.upload.service.dbservice;

public interface UserTokendbService {
	public boolean matchToken(Long userId,String token);
	
	public boolean matchOpenid(String access_token,String openid,int type);
}
