package com.dogpro.common.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class SMS {
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	// 注册验证码
	public static String RegisterCaptcha(String mobile, String captcha) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ac", "send");
		model.put("uid", "pajisi126");
		model.put("pwd", "ea6043048e2d6242f902ad6fb7aa7f35");
		model.put("template", "407628");
		model.put("mobile", mobile);
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("code", captcha);
		model.put("content", JSONObject.toJSON(content));

		String url = "http://api.sms.cn/sms/";

		return HttpRequest.sendPost(url, HttpRequest.getUrlParamsByMap(model));
	}

	// 修改密码验证码
	public static String AlterPwdCaptcha(String mobile, String captcha) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ac", "send");
		model.put("uid", "pajisi126");
		model.put("pwd", "ea6043048e2d6242f902ad6fb7aa7f35");
		model.put("template", "407629");
		model.put("mobile", mobile);
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("code", captcha);
		model.put("content", JSONObject.toJSON(content));

		String url = "http://api.sms.cn/sms/";

		return HttpRequest.sendPost(url, HttpRequest.getUrlParamsByMap(model));
	}

	// 第三方手机号绑定
	public static String ThirdPartyCaptcha(String mobile, String captcha) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ac", "send");
		model.put("uid", "pajisi126");
		model.put("pwd", "ea6043048e2d6242f902ad6fb7aa7f35");
		model.put("template", "407628");
		model.put("mobile", mobile);
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("code", captcha);
		model.put("content", JSONObject.toJSON(content));

		String url = "http://api.sms.cn/sms/";

		return HttpRequest.sendPost(url, HttpRequest.getUrlParamsByMap(model));
	}

}