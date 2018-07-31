package com.dogpro.common.tool;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class WXtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		String ss = ThirdPartyCheck.WeixinCheck("123", "123");
//		System.out.println(ss);
//		JSONObject jsonObject = JSONObject.parseObject(ss);
//		Integer sss = jsonObject.getInteger("errcode");
//		System.out.println(sss);
//		String INDEX_DIR = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"index_dir";  
//		System.out.println(INDEX_DIR);
//		String openid = "o0_fx0aVju-nxtNz-hGpt5KlF0pg";
//		String access_token = "mBQJ-R1WyFWI6grxAhW88qL3fZk5LLeBKOPU2yXYSVIIY0c69TNtPR9a63o_H3ci-oJ0gLWm_-N1D4MMk27cpQ";
//		System.out.println(ThirdPartyCheck.WeixinCheck(access_token,openid));
		
		
//		String url = "http://192.168.1.200:8080/api/v2/nodes/emq@127.0.0.1/clients";
//		String param = "curr_page=1&page_size=20";
//		System.out.println(HttpRequest.sendGet(url, param));
//		String url = "http://192.168.1.200:8080/api/v2/users";
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("username", "username");
//		map.put("password", "password");
//		map.put("tag", "user");
//		System.out.println(HttpRequest.sendPost(url, HttpRequest.getUrlParamsByMap(map)));
		
		for(int i =0;i<30;i++){
			
		}
		
		
		String url = "http://192.168.1.200:8080/api/v2/nodes/emq@127.0.0.1/clients";
		String username = "admin";
		String pswdString = "public";
		try {
			String result = HttpRequest.doGet(username, pswdString, url);
			JSONObject jsonObject = JSONObject.parseObject(result);
			Map<String, Object> map = (Map<String, Object>)jsonObject;
			Map<String, Object> resultMap = (Map<String, Object>) map.get("result");
			System.out.println(resultMap.get("total_num").toString());
			List<Map<String, Object>> objectList = (List<Map<String, Object>>) resultMap.get("objects");
			for(Map<String, Object> obj:objectList){
				System.out.println(obj.get("client_id"));
			}
			//			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
