package com.dogpro.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import com.dogpro.common.Interfacetool.DataGridResult;
import com.dogpro.common.Interfacetool.DataGridResultUtils;
import com.dogpro.common.Interfacetool.DefaultServiceHandler;
import com.dogpro.common.Interfacetool.MobileException;
import com.dogpro.common.Interfacetool.ParameterHandler;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.Interfacetool.ResultObjectUtils;
import com.dogpro.common.Interfacetool.ServiceHandler;
import com.dogpro.common.domain.CheckUser;
import com.dogpro.common.tool.JedisUtil;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.ObjectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
@RequestMapping("/UsercenterApi")
public class UserCenterAPI extends AbstractJsonpResponseBodyAdvice {

	private ServiceHandler serviceHandler = new DefaultServiceHandler();
	private ObjectMapper objectMapper = new ObjectMapper();

	@RequestMapping("/Apiv1")
	@ResponseBody
	public ResultObject UserCheck(HttpServletRequest request,
			HttpServletResponse response) {

		return doExecute(request, response);

	}

	@RequestMapping("/ApivDataGrid")
	@ResponseBody
	public DataGridResult a(HttpServletRequest request,
			HttpServletResponse response) {
		return dataGriddoExecute(request, response);

	}

	@RequestMapping(value = "/ApivDataGridP", method = { RequestMethod.GET })
	@ResponseBody
	public Object ApivDataGridP(HttpServletRequest request,
			HttpServletResponse response, String callback) {
		DataGridResult resultObject = dataGriddoExecute(request, response);
		JSONPObject jsonpObject = new JSONPObject(callback, resultObject);
		return jsonpObject;
	}

	@RequestMapping(value = "/Apiv1JP", method = { RequestMethod.GET })
	@ResponseBody
	public Object UserCheckJP(HttpServletRequest request,
			HttpServletResponse response, String callback) {
		ResultObject resultObject = doExecute(request, response);
		JSONPObject jsonpObject = new JSONPObject(callback, resultObject);
		return jsonpObject;

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

		// 判断是否传入token
		String ActionName = parameterObject.getAction();
		// 若不是检测用户Service
		if (!ActionName.equals("UserCheckService")) {
			try {
				Long userId = parameterObject.getLongParameter("userId");
				String token = parameterObject.getToken();
				Map packagesMap = MessageConsumerConfig
						.readConfig("config.properties");
				int dbindex = Integer.parseInt(packagesMap.get("redisUserdb")
						.toString());
				JedisUtil jedisUtil = new JedisUtil(dbindex);
				byte[] key = ("U_" + userId).getBytes();
				byte[] value = jedisUtil.get(key);
				CheckUser checkUser = (CheckUser) ObjectUtil
						.bytes2Object(value);
				if (checkUser.getUserId() != userId
						|| !checkUser.getToken().equals(token)) {
					resultObject = new ResultObject();
					resultObject.setState(ResultObject.STATE_FAIL);
					resultObject.setMessage("用户ID对应token不正确");
					resultObject.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
					return resultObject;
				}
			} catch (Exception e) {
				resultObject = new ResultObject();
				resultObject.setState(ResultObject.STATE_FAIL);
				resultObject.setMessage("用户ID对应token不正确");
				resultObject.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
				return resultObject;
			}
		}
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
