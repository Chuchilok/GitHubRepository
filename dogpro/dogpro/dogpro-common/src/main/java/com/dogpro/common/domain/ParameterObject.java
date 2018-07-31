package com.dogpro.common.domain;

import java.util.HashMap;
import java.util.Map;

public class ParameterObject {
	public static final String FORMAT_JSON = "json";
	public static final String FORMAT_XML = "xml";

	private String module;
	private String action;
	private String format; 
	private Map<String, Object> parameters = new HashMap<String, Object>();

	public ParameterObject(String module, String action,  Map<String, Object> params) {
		this.module = module; 
		this.action = action;
		this.format = FORMAT_JSON;
		if (params != null && !params.isEmpty()) {
			this.parameters.putAll(params);
		}
	}

	public String getModule() {
		return module;
	}

	public String getAction() {
		return action;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Object getParameter(String key) {
		return this.parameters.get(key);
	}

 
	/**
	 * 获得存放参数的对象
	 * 
	 * @return
	 */
	public Map<String, Object> getParameterObject() {
		return this.parameters;
	}

	/**
	 * 获得字符参数
	 * 
	 * @param key
	 * @return
	 */
	public String getStringParameter(String key) {
		if (parameters.containsKey(key)) {
			return getParameter(key).toString();
		}
		return null;
	}

	/**
	 * 获得整数参数
	 */
	public Integer getIntegerParameter(String key) {
		String val = getStringParameter(key);
		if (val != null && !"".equals(val)) {
			return Integer.parseInt(val);
		}
		return null;
	}
	/**
	 * 获得Long参数
	 */
	public Long getLongParameter(String key) {
		String val = getStringParameter(key);
		if (val != null && !"".equals(val)) {
			return Long.parseLong(val);
		}
		return null;
	}

	public void setParameter(String key, Object value) {
		this.parameters.put(key, value);
	}

	public String getToken() {
		return getStringParameter("token");
	}
}
