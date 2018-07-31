package com.dogpro.admin.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import com.dogpro.admin.service.dbservice.AdminRedisdbService;
import com.dogpro.admin.service.dbservice.AdminTokendbService;
import com.dogpro.admin.service.webapi.ExcelService;
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
import com.dogpro.common.tool.MessageConsumerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
@RequestMapping("/SystemAdminApi")
public class SystemAdminAPI extends AbstractJsonpResponseBodyAdvice {

	@Autowired
	private ExcelService excelService;

	private ServiceHandler serviceHandler = new DefaultServiceHandler();
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private AdminTokendbService adminTokendbService;
	@Autowired
	private AdminRedisdbService adminRedisdbService;
	
	private Map packagesMap = MessageConsumerConfig
			.readConfig("config.properties");
	private String upload_IP = packagesMap.get("upload_IP").toString().trim();
	private Integer upload_PORT = Integer.valueOf(packagesMap
			.get("upload_PORT").toString().trim());
	private String upload_PJNAME = packagesMap.get("upload_PJNAME").toString()
			.trim();
	private String upload_PICTURE = packagesMap.get("upload_PICTURE")
			.toString();
	private String upload_VIDEO = packagesMap.get("upload_VIDEO").toString();
	private String upload_VOICE = packagesMap.get("upload_VOICE").toString();

	@RequestMapping("/uploadPic")
	@ResponseBody
	public Object uploadPic(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file, Long adminUserId,
			String token) {
		ResultObject resultObject = new ResultObject();
		// 进入token验证
		Map map = adminTokendbService.getTokenByAdminUserId(adminUserId);
		if (Integer.parseInt(map.get("isErr").toString()) > 0) {// 说明获取token时，同时更新数据库成功
			long min = Long.parseLong(map.get("minute").toString());
			// if (min > 2) {// 说明token已经过期了
			// resultObject = new ResultObject();
			// resultObject.setState(ResultObject.STATE_SUCCESS);
			// resultObject.setMessage(ResultObject.Message_STATE_SUCCESS);
			// resultObject.setCode(ResultObject.CODE_STATE_SUCCESS);
			// resultObject.setFlag(2);// token失效
			//
			// } else {
			String paraToken = token;
			String myToken = map.get("token").toString();
			if (paraToken.equals(myToken)) {
				String ipPath = "/" + upload_PJNAME + "/" + upload_PICTURE
						+ "/";
				String image = "";
				try {
					if (file != null) {// 判断上传的文件是否为空
						String path = null;// 文件路径
						String type = null;// 文件类型
						String fileName = file.getOriginalFilename();// 文件原名称
						System.out.println("上传的文件原名称:" + fileName);
						// 判断文件类型
						type = fileName.indexOf(".") != -1 ? fileName
								.substring(fileName.lastIndexOf(".") + 1,
										fileName.length()) : null;
						if (type != null) {// 判断文件类型是否为空
							// 硬改png后缀
							if ("png".equals(type.toLowerCase())) {
								type = "jpg";
							}
							if ("gif".equals(type.toLowerCase())
									|| "png".equals(type.toLowerCase())
									|| "jpg".equals(type.toLowerCase())
									|| "jpeg".equals(type.toLowerCase())) {
								// 项目在容器中实际发布运行的根路径
								String realPath = request.getSession()
										.getServletContext().getRealPath("/");

								// 自定义的文件名称
								String trueFileName = String.valueOf(System
										.currentTimeMillis())
										+ "."
										+ type.toLowerCase();
								// 设置存放图片文件的路径
								path = realPath + upload_PICTURE + "/"
										+ trueFileName;
								System.out.println("存放图片文件的路径:" + path);
								// 转存文件到指定的路径
								file.transferTo(new File(path));
								System.out.println("文件成功上传到指定目录下");
								image = ipPath + trueFileName;

							} else {
								System.out.println("不是我们想要的文件类型,请按要求重新上传");
							}
						} else {
							System.out.println("文件类型为空");
						}
					} else {
						System.out.println("没有找到相对应的文件");
					}

				} catch (Exception e) {
				}
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("image", image);
				result.put("msg", "上传成功");
				resultObject.setResult(result);
				resultObject.setFlag(1);// 成功
				// JSONPObject jsonpObject = new JSONPObject(callback,
				// resultObject);
				return resultObject;
			} else {
				resultObject = new ResultObject();
				resultObject.setState(ResultObject.STATE_SUCCESS);
				resultObject.setMessage(ResultObject.Message_STATE_SUCCESS);
				resultObject.setCode(ResultObject.CODE_STATE_SUCCESS);
				resultObject.setFlag(-1);// token错误
			}
			// }
		}
		// JSONPObject jsonpObject = new JSONPObject(callback, resultObject);
		return resultObject;
	}

	@RequestMapping(value = "/importExcel")
	@ResponseBody
	public Object importExcel(HttpServletRequest request, Long importUserId,
			String importToken) {
		String userId = request.getParameter("importUserId");
		String token = request.getParameter("importToken");
		ResultObject resultObject = new ResultObject();
		Map map = adminTokendbService.getTokenByAdminUserId(importUserId);
		if (Integer.parseInt(map.get("isErr").toString()) > 0) {// 说明获取token时，同时更新数据库成功
		// long min = Long.parseLong(map.get("minute").toString());
		// if (min > 2) {// 说明token已经过期了
		// resultObject.setState(ResultObject.STATE_SUCCESS);
		// resultObject.setMessage(ResultObject.Message_STATE_SUCCESS);
		// resultObject.setCode(ResultObject.CODE_STATE_SUCCESS);
		// resultObject.setFlag(2);// token失效
		// } else {
			String myToken = map.get("token").toString();
			if (importToken != null && importToken.equals(myToken)) {
				// 验证成功
				CommonsMultipartFile file = null;
				try {
					MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
					for (List<MultipartFile> files : multipartHttpServletRequest
							.getMultiFileMap().values()) {
						file = (CommonsMultipartFile) files.get(0);
					}
					if (file != null) {
						return excelService.importExcel(file);
					}
					resultObject.setState(ResultObject.STATE_SUCCESS);
					resultObject.setMessage(ResultObject.Message_STATE_SUCCESS);
					resultObject.setCode(ResultObject.CODE_STATE_SUCCESS);
					resultObject.setFlag(-1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				resultObject.setState(ResultObject.STATE_SUCCESS);
				resultObject.setMessage(ResultObject.Message_STATE_SUCCESS);
				resultObject.setCode(ResultObject.CODE_STATE_SUCCESS);
				resultObject.setFlag(-1);
			}
			// }
		}
		return resultObject;
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

	@RequestMapping(value = "/Apiv1")
	@ResponseBody
	public Object UserCheck(HttpServletRequest request,
			HttpServletResponse response, String callback) {
		ResultObject resultObject = doExecute(request, response);

		return resultObject;

	}

	@RequestMapping(value = "/Apiv2")
	@ResponseBody
	public Object UserCheck2(HttpServletRequest request,
			HttpServletResponse response, String callback) {
		DataGridResult resultObject = dataGriddoExecute(request, response);
		return resultObject;
	}

	@RequestMapping(value = "/Apiv1S", method = { RequestMethod.GET })
	@ResponseBody
	public Object Apiv1S(HttpServletRequest request,
			HttpServletResponse response ,String callback) {

		ResultObject resultObject =doExecuteService(request, response);
		JSONPObject jsonpObject = new JSONPObject(callback, resultObject);
		return jsonpObject;
	}

	public SystemAdminAPI() {
		// 参数包含callback的时候 使用jsonp的反馈形式
		super("callback");
	}

	/**
	 * 解析移动请求，执行并返回结果
	 * 
	 * @param request
	 * @param response
	 */
	private ResultObject doExecuteService(HttpServletRequest request,
			HttpServletResponse response) {
		String formatType = getFormatType(request);
		ResultObject resultObject = null;
		ParameterObject parameterObject = buildParameterObject(request,
				formatType);
		parameterObject.setParameter("requestobj", request);
		try {
			String module = parameterObject.getModule();
			if (!"serviceLogin".equals(module)) {// adminLogin为后台登录
				// 进入token验证
				Long userId = parameterObject.getLongParameter("userId");
				String token = parameterObject.getStringParameter("token");
				CheckUser checkUser = adminRedisdbService.getUser(userId);
				if (checkUser != null && checkUser.getToken().equals(token)) {
					resultObject = this.serviceHandler.handle(parameterObject);
					resultObject.setFlag(1);// 成功
				} else {
					resultObject = new ResultObject();
					resultObject.setState(ResultObject.STATE_SUCCESS);
					resultObject.setMessage(ResultObject.Message_STATE_SUCCESS);
					resultObject.setCode(ResultObject.CODE_STATE_SUCCESS);
					resultObject.setFlag(-1);// token错误
				}
				// }
			} else {// 登录时，
				resultObject = this.serviceHandler.handle(parameterObject);
				resultObject.setFlag(1);
			}
		} catch (MobileException e) {
			resultObject = ResultObjectUtils.resultFailObject(e.getMessage());
			resultObject.setFlag(0);
			System.out.println("不能处理请求的服务，请稍后重试");
		} catch (Exception e) {
			resultObject = ResultObjectUtils
					.resultFailObject("不能处理请求的服务，请稍后重试");
			resultObject.setFlag(0);
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
	private ResultObject doExecute(HttpServletRequest request,
			HttpServletResponse response) {
		String formatType = getFormatType(request);
		ResultObject resultObject = null;
		ParameterObject parameterObject = buildParameterObject(request,
				formatType);
		parameterObject.setParameter("requestobj", request);
		try {
			String module = parameterObject.getModule();
			if (!"adminLogin".equals(module)) {// adminLogin为后台登录
				// 进入token验证
				Long adminUserId = parameterObject
						.getLongParameter("adminUserId");
				Map map = adminTokendbService
						.getTokenByAdminUserId(adminUserId);
				if (Integer.parseInt(map.get("isErr").toString()) > 0) {// 说明获取token时，同时更新数据库成功
				// long min = Long.parseLong(map.get("minute").toString());
				// if (min > 2) {// 说明token已经过期了
				// resultObject = new ResultObject();
				// resultObject.setState(ResultObject.STATE_SUCCESS);
				// resultObject
				// .setMessage(ResultObject.Message_STATE_SUCCESS);
				// resultObject.setCode(ResultObject.CODE_STATE_SUCCESS);
				// resultObject.setFlag(2);// token失效
				// } else {
					String paraToken = parameterObject
							.getStringParameter("token");
					String myToken = map.get("token").toString();
					if (paraToken.equals(myToken)) {
						resultObject = this.serviceHandler
								.handle(parameterObject);
						resultObject.setFlag(1);// 成功
					} else {
						resultObject = new ResultObject();
						resultObject.setState(ResultObject.STATE_SUCCESS);
						resultObject
								.setMessage(ResultObject.Message_STATE_SUCCESS);
						resultObject.setCode(ResultObject.CODE_STATE_SUCCESS);
						resultObject.setFlag(-1);// token错误
					}
					// }
				}
			} else {// 登录时，
				resultObject = this.serviceHandler.handle(parameterObject);
				resultObject.setFlag(1);
			}
			if (resultObject == null) {
				throw new MobileException("不能返回空的结果对象");
			}
		} catch (MobileException e) {
			resultObject = ResultObjectUtils.resultFailObject(e.getMessage());
			resultObject.setFlag(0);
			System.out.println("不能处理请求的服务，请稍后重试");
		} catch (Exception e) {
			resultObject = ResultObjectUtils
					.resultFailObject("不能处理请求的服务，请稍后重试");
			resultObject.setFlag(0);
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
			// 进入token验证
			Long adminUserId = parameterObject.getLongParameter("adminUserId");
			Map map = adminTokendbService.getTokenByAdminUserId(adminUserId);
			if (Integer.parseInt(map.get("isErr").toString()) > 0) {// 说明获取token时，同时更新数据库成功
				long min = Long.parseLong(map.get("minute").toString());
				if (min > 2) {// 说明token已经过期了
					resultObject = new DataGridResult();
					resultObject.setState(ResultObject.STATE_SUCCESS);
					resultObject.setMessage(ResultObject.Message_STATE_SUCCESS);
					resultObject.setCode(ResultObject.CODE_STATE_SUCCESS);
					resultObject.setFlag(2);// token失效
				} else {
					String paraToken = parameterObject
							.getStringParameter("token");
					String myToken = map.get("token").toString();
					if (paraToken.equals(myToken)) {
						resultObject = this.serviceHandler
								.dataGridHandle(parameterObject);
						resultObject.setFlag(1);// 成功
					} else {
						resultObject = new DataGridResult();
						resultObject.setState(ResultObject.STATE_SUCCESS);
						resultObject
								.setMessage(ResultObject.Message_STATE_SUCCESS);
						resultObject.setCode(ResultObject.CODE_STATE_SUCCESS);
						resultObject.setFlag(-1);// token错误
					}
				}
			}
			// resultObject =
			// this.serviceHandler.dataGridHandle(parameterObject);
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
