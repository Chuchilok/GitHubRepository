package com.webpublish.common.interfacetool;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;
 

public abstract class ParameterHandler {
	private static ParameterHandler jsonParameterHandler = new JsonParameterHandler();
	private static ParameterHandler xmlParameterHandler = new XmlParameterHandler();

	public static ParameterHandler newInstance(String format) {
		if ("xml".equals(format)) {
			return xmlParameterHandler;
		}
		return jsonParameterHandler;
	}

	public static void buildMultipartFiles(Map<String, Object> params, MultipartHttpServletRequest request) {
		Iterator<?> iter = request.getFileNames();
		while (iter.hasNext()) {
			String fileName = (String) iter.next();
			List<?> files = request.getFiles(fileName);
			params.put(fileName, files);
		}
	}

	public abstract ParameterObject handle(String paramString, HttpServletRequest paramHttpServletRequest);
}