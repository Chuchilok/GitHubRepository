package com.webpublish.service.impl.dbservice;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webpublish.common.interfacetool.ResultObject;
import com.webpublish.common.utils.MessageConsumerConfig;
import com.webpublish.service.dbservice.UploaddbService;
import com.webpublish.service.dbservice.UserTokendbService;
@Service("UploaddbService")
public class UploaddbServiceImpl implements UploaddbService {
	@Autowired
	private UserTokendbService userTokendbService;
	
	
	private Map packagesMap = MessageConsumerConfig
			.readConfig("config.properties");
	private String upload_IP = packagesMap.get("upload_IP").toString().trim();
	private Integer upload_PORT = Integer.valueOf(packagesMap
			.get("upload_PORT").toString().trim());
	private String upload_folder = packagesMap.get("upload_folder").toString()
			.trim();
	
	
	public ResultObject uploadFile(HttpServletRequest request,HttpServletResponse response,MultipartFile file,Long userId,String token) {
//		String ipPath = "http://" + upload_IP + ":" + upload_PORT + "/"
//				+ upload_PJNAME + "/";
		String ipPath = "http://" + upload_IP + ":" + upload_PORT + "/"+upload_folder+"/";
		int flag = 0;
		String msg = "";
		String downloadUrl = "";
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			if(!userTokendbService.matchUserToken(userId, token)){
				//token验证不正确
				flag = 0;
				msg ="用户id对应token不正确";
			}else{
				if (file != null) {// 判断上传的文件是否为空
					String path = null;// 文件路径
					String type = null;// 文件类型
					String fileName = file.getOriginalFilename();// 文件原名称
					System.out.println("上传的文件原名称:" + fileName);
					// 判断文件类型
					type = fileName.indexOf(".") != -1 ? fileName.substring(
							fileName.lastIndexOf(".") + 1, fileName.length())
							: null;
					if (type != null) {// 判断文件类型是否为空
						if ("WAR".equals(type.toUpperCase())) {
							// 项目在容器中实际发布运行的根路径
							String realPath = request.getSession()
									.getServletContext().getRealPath("/");

							// 自定义的文件名称
							String trueFileName = String.valueOf(System
									.currentTimeMillis())
									+ "."
									+ type.toLowerCase();
							// 设置存放图片文件的路径
							path = realPath
									+ upload_folder+"/"+trueFileName;
							System.out.println("存放图片文件的路径:" + path);
							// 转存文件到指定的路径
							file.transferTo(new File(path));
							System.out.println("文件成功上传到指定目录下");
							msg = "文件成功上传到指定目录下";
							flag = 1;
							downloadUrl = ipPath + trueFileName;
						} else {
							System.out.println("不是我们想要的文件类型,请按要求重新上传");
							msg = "不是我们想要的文件类型,请按要求重新上传";
						}
					} else {
						System.out.println("文件类型为空");
						msg = "文件类型为空";
					}
				} else {
					System.out.println("没有找到相对应的文件");
					msg = "没有找到相对应的文件";
				}
			}
			model.put("flag", flag);
			model.put("msg", msg);
			model.put("downloadUrl", downloadUrl);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			// TODO: handle exception
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

}
