package com.webpublish.service.dbservice;

import java.util.Map;

public interface UserdbService {
	/**
	 * 注册
	 * @param username
	 * @param pswd
	 * @return
	 */
	public Map<String, Object> register(String username,String pswd);
	/**
	 * 登陆
	 * @param username
	 * @param pswd
	 * @return
	 */
	public Map<String, Object> login(String username,String pswd);
}
