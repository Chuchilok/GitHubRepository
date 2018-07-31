package com.webpublish.service.dbservice;

public interface UserTokendbService {
	/**
	 * 存入 userid - token
	 * @param userId
	 * @param token
	 * @return
	 */
	public boolean putUserToken(Long userId,String token);
	/**
	 * 获取 userid 对应token
	 * @param userId
	 * @return
	 */
	public String getUserToken(Long userId);
	/**
	 * 判断 userId 对应 token
	 * @param userId
	 * @param token
	 * @return
	 */
	public boolean matchUserToken(Long userId,String token);
	/**
	 * 遍历 map
	 */
	public void traverseMap();
}
