package com.dogpro.upload.service.webapi;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.dogpro.common.Interfacetool.ResultObject;

public interface UploadService {
	//上传图片(单张)
	public ResultObject uploadPic(HttpServletRequest request, MultipartFile file, Long userId,String token);
	//上传图片(多张)
	public ResultObject uploadPics(HttpServletRequest request,MultipartFile[] file, Long userId,String token);
	//上传视频(视频+截图)
	public ResultObject uploadVideo(HttpServletRequest request,MultipartFile videofile,MultipartFile subimage, Long userId, String token);
	//上传音频文件(语音文件)
	public ResultObject uploadVoice(HttpServletRequest request,MultipartFile voicefile,Long userId, String token);
	//第三方登陆上传图片(单张)
	public ResultObject uploadThirdPartyPic(HttpServletRequest request, MultipartFile file, String access_token,String openid,int thirdPartyType);
	//上传视频(视频+截图)
	public ResultObject uploadSingleVideo(HttpServletRequest request,MultipartFile videofile, Long userId, String token);
}
