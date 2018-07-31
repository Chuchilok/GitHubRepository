package com.dogpro.upload.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
import com.dogpro.upload.service.webapi.UploadService;

@ControllerAdvice
@RequestMapping("/UsercenterApi")
public class UserCenterAPI extends AbstractJsonpResponseBodyAdvice {

	private ServiceHandler serviceHandler = new DefaultServiceHandler();

	@Autowired
	private UploadService uploadService;

	// 上传图片(单张)
	@RequestMapping("/Apiv1")
	@ResponseBody
	public ResultObject UploadPicture(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file, Long userId,
			String token) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return uploadService.uploadPic(request, file, userId, token);

	}

	// 上传图片(多张)
	@RequestMapping("/Apiv2")
	@ResponseBody
	public ResultObject UploadPictures(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") MultipartFile[] file, Long userId,
			String token) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return uploadService.uploadPics(request, file, userId, token);
	}

	// 上传视频以及缩略图
	@RequestMapping("/Apiv3")
	@ResponseBody
	public ResultObject UploadVideo(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file,
			MultipartFile subimage, Long userId, String token) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return uploadService
				.uploadVideo(request, file, subimage, userId, token);
	}

	// 上传语音
	@RequestMapping("/Apiv4")
	@ResponseBody
	public ResultObject UploadVoice(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file, Long userId,
			String token) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return uploadService.uploadVoice(request, file, userId, token);
	}

	// 第三方上传图片
	@RequestMapping("/Apiv5")
	@ResponseBody
	public ResultObject uploadThirdPartyPic(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file,
			String access_token, String openid, Integer thirdPartyType) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return uploadService.uploadThirdPartyPic(request, file, access_token,
				openid, thirdPartyType);
	}

	// 上传视频(单个视频，主要提供给网页端客服)
	@RequestMapping("/Apiv6")
	@ResponseBody
	public ResultObject UploadSingleVideo(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file, Long userId,
			String token) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return uploadService.uploadSingleVideo(request, file, userId, token);
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
