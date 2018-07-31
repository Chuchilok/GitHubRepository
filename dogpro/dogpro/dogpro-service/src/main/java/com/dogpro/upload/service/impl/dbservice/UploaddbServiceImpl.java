package com.dogpro.upload.service.impl.dbservice;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.upload.service.dbservice.UploaddbService;
import com.dogpro.upload.service.dbservice.UserTokendbService;

@Service("UploaddbService")
public class UploaddbServiceImpl implements UploaddbService {
	@Autowired
	private UserTokendbService userTokendbService;

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

	public Map<String, Object> uploadPic(HttpServletRequest request,
			MultipartFile file, Long userId, String token) {
		// TODO Auto-generated method stub
		String ipPath =  "/"+upload_PJNAME+"/"+ upload_PICTURE + "/";
		int flag = 0;
		String msg = "";
		String image = "";
		String subimage = "";// 缩略图路径
		Map<String, Object> model = new HashMap<String, Object>();
		// 权限检测
		if (!userTokendbService.matchToken(userId, token)) {
			flag = 0;
			msg = "userId与token匹配不正确";
		} else {
			try {
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
						//硬改png后缀
						if("png".equals(type.toLowerCase())){
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
							msg = "文件成功上传到指定目录下";
							flag = 1;
							image = ipPath + trueFileName;
							/*
							 * 生成压缩图
							 */
							String subName = trueFileName.replace(".", "_sub.");
							String subPath = realPath + upload_PICTURE + "/"
									+ subName;
							
							Thumbnails.of(path).scale(1.0f).outputQuality(0.4f).outputFormat(type.toLowerCase()).toFile(subPath);
							subimage = ipPath + subName;
							/*
							 * 
							 * 生成压缩图
							 */

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

			} catch (Exception e) {
				flag = 0;
				msg = "上传失败";
			}
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("image", image);
		model.put("subimage", subimage);
		return model;
	}

	public Map<String, Object> uploadPics(HttpServletRequest request,
			MultipartFile[] file, Long userId, String token) {
		String ipPath =  "/"+upload_PJNAME+"/"+ upload_PICTURE + "/";
		int flag = 0;
		String msg = "";
		String image = "";
		String subimage = "";// 缩略图路径
		Map<String, Object> model = new HashMap<String, Object>();
		List<Map<String, Object>> url = new ArrayList<Map<String, Object>>();
		// 权限检测
		if (!userTokendbService.matchToken(userId, token)) {
			flag = 0;
			msg = "userId与token匹配不正确";
		} else {
			try {
				for (MultipartFile imageFile : file) {
					Map<String, Object> imageMap = new HashMap<String, Object>();
					if (imageFile != null) {// 判断上传的文件是否为空
						String path = null;// 文件路径
						String type = null;// 文件类型
						String fileName = imageFile.getOriginalFilename();// 文件原名称
						System.out.println("上传的文件原名称:" + fileName);
						// 判断文件类型
						type = fileName.indexOf(".") != -1 ? fileName
								.substring(fileName.lastIndexOf(".") + 1,
										fileName.length()) : null;
						if (type != null) {// 判断文件类型是否为空
							//硬改png后缀
							if("png".equals(type.toLowerCase())){
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
								imageFile.transferTo(new File(path));
								System.out.println("文件成功上传到指定目录下");
								msg = "文件成功上传到指定目录下";
								image = ipPath + trueFileName;
								String subName = trueFileName.replace(".", "_sub.");
								String subPath = realPath + upload_PICTURE + "/"
										+ subName;
								Thumbnails.of(path).scale(1.0f).outputQuality(0.4f).outputFormat(type.toLowerCase()).toFile(subPath);
								subimage = ipPath + subName;
								flag = 1;
							} else {
								System.out.println("不是我们想要的文件类型,请按要求重新上传");
								flag = 0;
								msg = "不是我们想要的文件类型,请按要求重新上传";
							}
						} else {
							System.out.println("文件类型为空");
							flag = 0;
							msg = "文件类型为空";
						}
					} else {
						System.out.println("没有找到相对应的文件");
						flag = 0;
						msg = "没有找到相对应的文件";
					}
					imageMap.put("image", image);
					imageMap.put("subimage", subimage);
					url.add(imageMap);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("url", url);
		return model;
	}

	public Map<String, Object> uploadVideo(HttpServletRequest request,
			MultipartFile videofile, MultipartFile subimage, Long userId,
			String token) {
		String ipPath = "/"+upload_PJNAME+"/"+ upload_VIDEO + "/";
		int videoflag = 0;
		String msg = "";
		String videoUrl = "";
		String imageUrl = "";
		Map<String, Object> model = new HashMap<String, Object>();
		// 权限检测
		if (!userTokendbService.matchToken(userId, token)) {
			videoflag = 0;
			msg = "userId与token匹配不正确";
		} else {
			try {
				if (videofile != null) {// 判断上传的文件是否为空
					String path = null;// 文件路径
					String type = null;// 文件类型
					String fileName = videofile.getOriginalFilename();// 文件原名称
					System.out.println("上传的文件原名称:" + fileName);
					// 判断文件类型
					type = fileName.indexOf(".") != -1 ? fileName.substring(
							fileName.lastIndexOf(".") + 1, fileName.length())
							: null;
					if (type != null) {// 判断文件类型是否为空mp4|flv|avi|rm|rmvb
						if ("avi".equals(type.toLowerCase())
								|| "mp4".equals(type.toLowerCase())
								|| "wmv".equals(type.toLowerCase())
								|| "flv".equals(type.toLowerCase())
								|| "rmvb".equals(type.toLowerCase())
								|| "3gp".equals(type.toLowerCase())
								) {
							// 项目在容器中实际发布运行的根路径
							String realPath = request.getSession()
									.getServletContext().getRealPath("/");
							// 自定义的文件名称
							String trueFileName = String.valueOf(System
									.currentTimeMillis())
									+ "."
									+ type.toLowerCase();
							// 设置存放图片文件的路径
							path = realPath + upload_VIDEO + "/" + trueFileName;
							System.out.println("存放视频文件的路径:" + path);
							// 转存文件到指定的路径
							videofile.transferTo(new File(path));
							System.out.println("文件成功上传到指定目录下");
							msg = "文件成功上传到指定目录下";
							videoflag = 1;
							videoUrl = ipPath + trueFileName;
							// 上传缩略图图片
							fileName = subimage.getOriginalFilename();// 缩略图文件原名称
							// 判断缩略图文件类型
							type = fileName.indexOf(".") != -1 ? fileName
									.substring(fileName.lastIndexOf(".") + 1,
											fileName.length()) : null;
							if (type != null) {// 判断缩略图文件类型是否为空
								//硬改png后缀
//								if("png".equals(type.toLowerCase())){
//									type = "jpg";
//								}
								if ("gif".equals(type.toLowerCase())
										|| "png".equals(type.toLowerCase())
										|| "jpg".equals(type.toLowerCase())
										|| "jpeg".equals(type.toLowerCase())) {
									// 项目在容器中实际发布运行的根路径
									realPath = request.getSession()
											.getServletContext()
											.getRealPath("/");

									// 自定义的文件名称
									trueFileName = String.valueOf(System
											.currentTimeMillis())
											+ "."
											+ type.toLowerCase();
									// 设置存放图片文件的路径
									path = realPath + upload_VIDEO + "/"
											+ trueFileName;
									System.out.println("存放图片文件的路径:" + path);
									// 转存文件到指定的路径
									subimage.transferTo(new File(path));
									System.out.println("文件成功上传到指定目录下");
									msg = "文件成功上传到指定目录下";
									imageUrl = ipPath + trueFileName;
								}
							} else {
								System.out.println("不是我们想要的文件类型,请按要求重新上传");
								videoflag = 0;
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
			} catch (Exception e) {
				// TODO: handle exception
				videoflag = 0;
			}
		}
		model.put("flag", videoflag);
		model.put("msg", msg);
		model.put("videoUrl", videoUrl);
		model.put("imageUrl", imageUrl);
		return model;
	}

	public Map<String, Object> uploadVoice(HttpServletRequest request,
			MultipartFile voicefile, Long userId, String token) {
		// TODO Auto-generated method stub
		String ipPath ="/"+upload_PJNAME+"/" + upload_VOICE + "/";
		int flag = 0;
		String msg = "";
		String voiceUrl = "";
		Map<String, Object> model = new HashMap<String, Object>();
		// 权限检测
		if (!userTokendbService.matchToken(userId, token)) {
			flag = 0;
			msg = "userId与token匹配不正确";
		} else {
			try {

				if (voicefile != null) {// 判断上传的文件是否为空
					String path = null;// 文件路径
					String type = null;// 文件类型
					String fileName = voicefile.getOriginalFilename();// 文件原名称
					System.out.println("上传的文件原名称:" + fileName);
					// 判断文件类型
					type = fileName.indexOf(".") != -1 ? fileName.substring(
							fileName.lastIndexOf(".") + 1, fileName.length())
							: null;
					if (type != null) {// 判断文件类型
						if ("mp3".equals(type.toLowerCase())||
								"wav".equals(type.toLowerCase())||
								"acc".equals(type.toLowerCase())) {
							// 项目在容器中实际发布运行的根路径
							String realPath = request.getSession()
									.getServletContext().getRealPath("/");
							// 自定义的文件名称
							String trueFileName = String.valueOf(System
									.currentTimeMillis())
									+ "."
									+ type.toLowerCase();
							// 设置存放图片文件的路径
							path = realPath + upload_VOICE + "/" + trueFileName;
							System.out.println("存放视频文件的路径:" + path);
							// 转存文件到指定的路径
							voicefile.transferTo(new File(path));
							System.out.println("文件成功上传到指定目录下");
							msg = "文件成功上传到指定目录下";
							flag = 1;
							voiceUrl = ipPath + trueFileName;

							msg = "文件成功上传到指定目录下";
							flag = 1;
						}

					} else {
						System.out.println("文件类型为空");
						msg = "文件类型为空";
					}
				} else {
					System.out.println("没有找到相对应的文件");
					msg = "没有找到相对应的文件";
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("voiceUrl", voiceUrl);
		return model;
	}
	
	public Map<String, Object> uploadThirdPartyPic(HttpServletRequest request,
			MultipartFile file, String access_token, String openid,int thirdPartyType) {
		// TODO Auto-generated method stub
		String ipPath =  "/"+upload_PJNAME+"/"+ upload_PICTURE + "/";
		int flag = 0;
		String msg = "";
		String image = "";
		String subimage = "";// 缩略图路径
		Map<String, Object> model = new HashMap<String, Object>();
		// 权限检测
		if (!userTokendbService.matchOpenid(access_token, openid, thirdPartyType)) {
			flag = 0;
			msg = "access_token与Openid匹配不正确";
		}  else {
			try {
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
						//硬改png后缀
						if("png".equals(type.toLowerCase())){
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
							msg = "文件成功上传到指定目录下";
							flag = 1;
							image = ipPath + trueFileName;
							/*
							 * 生成压缩图
							 */
							String subName = trueFileName.replace(".", "_sub.");
							String subPath = realPath + upload_PICTURE + "/"
									+ subName;
							
							Thumbnails.of(path).scale(1.0f).outputQuality(0.4f).outputFormat(type.toLowerCase()).toFile(subPath);
							subimage = ipPath + subName;
							/*
							 * 
							 * 生成压缩图
							 */

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

			} catch (Exception e) {
				flag = 0;
				msg = "上传失败";
			}
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("image", image);
		model.put("subimage", subimage);
		return model;
	}

	
	public Map<String, Object> uploadSingleVideo(HttpServletRequest request,
			MultipartFile videofile, Long userId,	String token) {
		String ipPath = "/"+upload_PJNAME+"/"+ upload_VIDEO + "/";
		int videoflag = 0;
		String msg = "";
		String videoUrl = "";
		Map<String, Object> model = new HashMap<String, Object>();
		// 权限检测
		if (!userTokendbService.matchToken(userId, token)) {
			videoflag = 0;
			msg = "userId与token匹配不正确";
		} else {
			try {
				if (videofile != null) {// 判断上传的文件是否为空
					String path = null;// 文件路径
					String type = null;// 文件类型
					String fileName = videofile.getOriginalFilename();// 文件原名称
					System.out.println("上传的文件原名称:" + fileName);
					// 判断文件类型
					type = fileName.indexOf(".") != -1 ? fileName.substring(
							fileName.lastIndexOf(".") + 1, fileName.length())
							: null;
					if (type != null) {// 判断文件类型是否为空mp4|flv|avi|rm|rmvb
						if ("avi".equals(type.toLowerCase())
								|| "mp4".equals(type.toLowerCase())
								|| "wmv".equals(type.toLowerCase())
								|| "flv".equals(type.toLowerCase())
								|| "rmvb".equals(type.toLowerCase())
								|| "3gp".equals(type.toLowerCase())
								) {
							// 项目在容器中实际发布运行的根路径
							String realPath = request.getSession()
									.getServletContext().getRealPath("/");
							// 自定义的文件名称
							String trueFileName = String.valueOf(System
									.currentTimeMillis())
									+ "."
									+ type.toLowerCase();
							// 设置存放图片文件的路径
							path = realPath + upload_VIDEO + "/" + trueFileName;
							System.out.println("存放视频文件的路径:" + path);
							// 转存文件到指定的路径
							videofile.transferTo(new File(path));
							System.out.println("文件成功上传到指定目录下");
							msg = "文件成功上传到指定目录下";
							videoflag = 1;
							videoUrl = ipPath + trueFileName;
						} else {
							System.out.println("文件类型不正确");
							msg = "文件类型不正确";
						}
					} else {
						System.out.println("文件类型为空");
						msg = "文件类型为空";
					}
				}else {
						System.out.println("没有找到相对应的文件");
						msg = "没有找到相对应的文件";
					}
			} catch (Exception e) {
				// TODO: handle exception
				videoflag = 0;
			}
		}
		model.put("flag", videoflag);
		model.put("msg", msg);
		model.put("videoUrl", videoUrl);
		return model;
	}
}
