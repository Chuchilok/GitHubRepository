package com.dogpro.admin.service.dbservice;

import java.util.Map;

public interface AdminTokendbService {

	//更新token，并返回
	public String updateToken(long adminUserId);

	//通过管理用户id返回一个 .token 和  是否大于两分钟
	public Map<String,Object> getTokenByAdminUserId(Long adminUserId);
	
}
