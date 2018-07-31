package com.dogpro.upload.service.dbservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UploaddbService {
	//上传图片(单张)
	public Map<String, Object> uploadPic(HttpServletRequest request, MultipartFile file, Long userId,String token);
	//上传图片(多张)
	public Map<String, Object> uploadPics(HttpServletRequest request,MultipartFile[] file, Long userId,String token);
	//上传视频(单个视频+截图)
	public Map<String, Object> uploadVideo(HttpServletRequest request,MultipartFile videofile,MultipartFile subimage, Long userId, String token);
	//上传语音(语音文件)
	public Map<String, Object> uploadVoice(HttpServletRequest request,MultipartFile voicefile,Long userId, String token);
	//上传图片(单张)
	public Map<String, Object> uploadThirdPartyPic(HttpServletRequest request, MultipartFile file, String access_token,String openid,int thirdPartyType);
	//上传视频(单个视频)
	public Map<String, Object> uploadSingleVideo(HttpServletRequest request,MultipartFile videofile, Long userId, String token);
}