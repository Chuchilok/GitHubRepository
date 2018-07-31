package com.imserver.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import util.PaginationUtil;

import com.alibaba.fastjson.JSONObject;
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
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.domain.PushMessage;
import com.dogpro.common.domain.RedisDoglocation;
import com.dogpro.common.domain.RedisWalkingDogGroup;
import com.dogpro.common.tool.JedisUtil;
import com.dogpro.common.tool.MD5Generator;
import com.dogpro.common.tool.MQTTapi;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.ObjectUtil;
import com.dogpro.common.tool.UmengPush;
import com.dogpro.dao.DogLocationMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.dao.WalkingDogGroupMapper;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.DogLocationExample;
import com.dogpro.domain.model.OnlineRecord;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.domain.model.WalkingDogGroupExample;
import com.dogpro.domain.model.extend.DoglocationDistance;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.ServiceRecorddbService;
import com.dogpro.service.dbservice.WalkingDogdbService;
import com.dogpro.service.impl.dbservice.WalkingDogdbServiceImpl;
import com.imserver.service.dbservice.IMRedisdbService;

@ControllerAdvice
@RequestMapping("/UsercenterApi")
public class UserCenterAPI extends AbstractJsonpResponseBodyAdvice {

	private ServiceHandler serviceHandler = new DefaultServiceHandler();

	@Autowired
	private IMRedisdbService redisdbservice;
	
	
	
	@RequestMapping("/test")
	@ResponseBody
	public String testdelete(HttpServletRequest request,
			HttpServletResponse response,String key) {
		JedisUtil jedisUtil = new JedisUtil(1);
		List<byte[]> ss = jedisUtil.brpop(key.getBytes(), 10);
		try {
//			OnlineRecord onlineRecord = (OnlineRecord) ObjectUtil.bytes2Object(ss.get(0));
			OnlineRecord onlineRecord1 = (OnlineRecord) ObjectUtil.bytes2Object(ss.get(1));
			System.out.println(onlineRecord1.getOnlineRecordId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(new String(ss.get(0)));
		System.out.println(new String(ss.get(1)));
		return "ok";

	}
	
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
	
	@RequestMapping(value = "/Apiv1JPShare", method = { RequestMethod.GET })
	@ResponseBody
	public Object Apiv1JPShare(HttpServletRequest request,
			HttpServletResponse response, String callback) {
		String formatType = getFormatType(request);
		ResultObject resultObject = null;
		ParameterObject parameterObject = buildParameterObject(request,
				formatType);
		parameterObject.setParameter("requestobj", request);
		String ActionName = parameterObject.getAction();
		String module = parameterObject.getModule();
		if((ActionName.equals("AllianceService")&&module.equals("allianceDetail"))||(ActionName.equals("DisussService")&&module.equals("friendCircleDisuss"))||(ActionName.equals("PraiseService")&&module.endsWith("friendCirclePraise"))){
				resultObject = doExecuteWithoutToken(request, response);
		}
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
				CheckUser checkUser = redisdbservice.getUser(userId);
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
				resultObject = ResultObjectUtils.resultFailObject(e
						.getMessage());
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
	private ResultObject doExecuteWithoutToken(HttpServletRequest request,
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
				resultObject = ResultObjectUtils.resultFailObject(e
						.getMessage());
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
