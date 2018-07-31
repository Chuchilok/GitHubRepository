package com.dogpro.service.dbservice;

public interface UserTokendbService {
	public boolean matchToken(Long userId,String token);
	
	public String getUserToken(Long userId);
}
