package com.webpublish.common.interfacetool;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.web.multipart.MultipartHttpServletRequest;
 

public class JsonParameterHandler extends ParameterHandler {
	public ParameterObject handle(String parameter, HttpServletRequest request) {
		if (parameter != null) {
			JSONObject objectMap = JSONObject.fromObject(parameter);
	//		String callbkfun = objectMap.getString("callbkfun");
			String module = objectMap.getString("module");
			String action = objectMap.getString("action");
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			@SuppressWarnings("unchecked")
			Map<String, Object> params = objectMap.getJSONObject("paras");
			if (params != null) {
				paramsMap.putAll(params);
			}
			if ((request instanceof MultipartHttpServletRequest)) {
				buildMultipartFiles(paramsMap, (MultipartHttpServletRequest) request);
			}
			return new ParameterObject(module, action, paramsMap);
		}

		return null;
	}
}