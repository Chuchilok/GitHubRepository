package com.dogpro.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import com.dogpro.common.Interfacetool.DefaultServiceHandler;
import com.dogpro.common.Interfacetool.MobileException;
import com.dogpro.common.Interfacetool.ParameterHandler;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.Interfacetool.ResultObjectUtils;
import com.dogpro.common.Interfacetool.ServiceHandler; 
import com.dogpro.domain.model.Table1;
import com.dogpro.service.dbservice.ITable1dbService;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@Controller
@RequestMapping("/test")
public class test1action {

	
	@Autowired
	private ITable1dbService itable1;
	
	@RequestMapping("/test1.do")
	@ResponseBody
	public String test1(HttpServletRequest request,
			HttpServletResponse response ) {
	//	Map<String, Object> model = new HashMap<String, Object>();
		Table1 t1=new Table1("test_1","test_2");
		itable1.insert(t1);
		System.out.println("test1 action");
		 
		return "test1 action";
	}
	
}
