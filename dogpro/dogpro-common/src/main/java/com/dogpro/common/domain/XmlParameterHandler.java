package com.dogpro.common.domain;

import javax.servlet.http.HttpServletRequest;
 

public class XmlParameterHandler extends ParameterHandler {
	public ParameterObject handle(String parameter, HttpServletRequest request) {
		throw new RuntimeException("no implementation");
	}
}