package com.dogpro.common.tool;

import java.util.HashMap;
import java.util.Map;

public class ThirdPartyCheck {
	public static String WeixinCheck(String access_token,String openid){
		String result = null;
		try {
			String url = "https://api.weixin.qq.com/sns/auth";
			Map<String, Object> map  = new HashMap<String, Object>();
			map.put("access_token", access_token);
			map.put("openid", openid);
			result = HttpRequest.sendGet(url, HttpRequest.getUrlParamsByMap(map));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static String QQCheck(String access_token){
		String result = null;
		try {
			String url = "https://graph.qq.com/oauth2.0/me";
			Map<String, Object> map  = new HashMap<String, Object>();
			map.put("access_token", access_token);
			result = HttpRequest.sendGet(url, HttpRequest.getUrlParamsByMap(map));
			result = result.substring(10);
			result = result.substring(0, result.length()-3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
