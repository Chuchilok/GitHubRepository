package com.webpublish.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import com.webpublish.common.interfacetool.DataGridResult;
import com.webpublish.common.interfacetool.DataGridResultUtils;
import com.webpublish.common.interfacetool.DefaultServiceHandler;
import com.webpublish.common.interfacetool.MobileException;
import com.webpublish.common.interfacetool.ParameterHandler;
import com.webpublish.common.interfacetool.ParameterObject;
import com.webpublish.common.interfacetool.ResultObject;
import com.webpublish.common.interfacetool.ResultObjectUtils;
import com.webpublish.common.interfacetool.ServiceHandler;
import com.webpublish.service.dbservice.UploaddbService;

@ControllerAdvice
@RequestMapping("/UsercenterApi")
public class UserCenterAPI extends AbstractJsonpResponseBodyAdvice {
	@Autowired
	private UploaddbService uploaddbService;
	
	
	private ServiceHandler serviceHandler = new DefaultServiceHandler();
	
	@RequestMapping("/Apiv1")
	@ResponseBody
	public ResultObject UserCheck(HttpServletRequest request,
			HttpServletResponse response) {

		return doExecute(request, response);

	}

	@RequestMapping(value = "/Apiv1JP", method = { RequestMethod.GET })
	@ResponseBody
	public Object UserCheckJP(HttpServletRequest request,
			HttpServletResponse response, String callback) {
		ResultObject resultObject = doExecute(request, response);
		JSONPObject jsonpObject = new JSONPObject(callback, resultObject);
		return jsonpObject;

	}
	
	//上传文件
	@RequestMapping("/Apiv2")
	@ResponseBody
	public ResultObject UploadFile(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file,Long userId,String token) {
		return uploaddbService.uploadFile(request, response, file,userId,token);
				
	}

	@RequestMapping("/Apiv3")
	@ResponseBody
	public ResultObject UploadFile2(HttpServletRequest request,
			HttpServletResponse response,  @RequestParam(value="file", required=false) MultipartFile file,Long userId,String token) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return uploaddbService.uploadFile(request, response, file,userId,token);
				
	}
	
	public UserCenterAPI() {
		// 参数包含callback的时候 使用jsonp的反馈形式
		super("callback");
	}

	/**
	 * 解析移动请求，执行并返回结果
	 * 
	 * @param request
	 * @param response
	 */
	private ResultObject doExecute(HttpServletRequest request,
			HttpServletResponse response) {
		String formatType = getFormatType(request);
		ResultObject resultObject = null;
		ParameterObject parameterObject = buildParameterObject(request,
				formatType);
		parameterObject.setParameter("requestobj", request);
		try {
			resultObject = this.serviceHandler.handle(parameterObject);
			if (resultObject == null) {
				throw new MobileException("不能返回空的结果对象");
			}
		} catch (MobileException e) {
			resultObject = ResultObjectUtils.resultFailObject(e.getMessage());
			System.out.println("不能处理请求的服务，请稍后重试");
		} catch (Exception e) {
			resultObject = ResultObjectUtils
					.resultFailObject("不能处理请求的服务，请稍后重试");
			System.out.println("不能处理请求的服务，请稍后重试");
		}
		return resultObject;
	}

	/**
	 * 解析移动请求，执行并返回结果
	 * 
	 * @param request
	 * @param response
	 */
	private DataGridResult dataGriddoExecute(HttpServletRequest request,
			HttpServletResponse response) {
		String formatType = getFormatType(request);
		DataGridResult resultObject = null;
		ParameterObject parameterObject = buildParameterObject(request,
				formatType);
		parameterObject.setParameter("requestobj", request);
		try {
			resultObject = this.serviceHandler.dataGridHandle(parameterObject);
			if (resultObject == null) {
				throw new MobileException("不能返回空的结果对象");
			}
		} catch (MobileException e) {
			resultObject = DataGridResultUtils.resultFailObject(e.getMessage());
			System.out.println("不能处理请求的服务，请稍后重试");
		} catch (Exception e) {
			resultObject = DataGridResultUtils
					.resultFailObject("不能处理请求的服务，请稍后重试");
			System.out.println("不能处理请求的服务，请稍后重试");
		}
		return resultObject;
	}

	private String getFormatType(HttpServletRequest request) {
		String contentType = request.getHeader("Content-Type");
		if ("application/xml".equals(contentType)) {
			return "xml";
		}
		return "json";
	}

	/**
	 * 构建请求参数对象
	 * 
	 * @param request
	 * @param formatType
	 * @return
	 */
	private ParameterObject buildParameterObject(HttpServletRequest request,
			String formatType) {
		String params = request.getParameter("params");
		if (params == null) {
			params = getRequestBody(request);
		}
		ParameterHandler parameterHandler = ParameterHandler
				.newInstance(formatType);
		ParameterObject parameterObject = parameterHandler.handle(params,
				request);
		if (parameterObject == null) {
			throw new MobileException("构建服务参数对象出错");
		}

		parameterObject.setFormat(formatType);

		return parameterObject;
	}

	/**
	 * 获取请求参数
	 * 
	 * @param request
	 * @return
	 */
	private String getRequestBody(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String temp;
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			System.out.println("获取请求参数失败");
			throw new MobileException("获取请求参数失败", e);
		}
	}

}
