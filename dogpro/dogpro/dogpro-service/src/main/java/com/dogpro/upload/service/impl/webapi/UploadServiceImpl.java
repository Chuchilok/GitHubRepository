package com.dogpro.upload.service.impl.webapi;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.upload.service.dbservice.UploaddbService;
import com.dogpro.upload.service.webapi.UploadService;

@Service("UploadService")
public class UploadServiceImpl implements UploadService {
	@Autowired
	private UploaddbService uploaddbService;

	public ResultObject uploadPic(HttpServletRequest request,
			MultipartFile file, Long userId, String token) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			Map<String, Object> model = uploaddbService.uploadPic(request,
					file, userId, token);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject uploadPics(HttpServletRequest request,
			MultipartFile[] file, Long userId, String token) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			Map<String, Object> model = uploaddbService.uploadPics(request,
					file, userId, token);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject uploadVideo(HttpServletRequest request,
			MultipartFile file, MultipartFile subimage, Long userId,
			String token) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			Map<String, Object> model = uploaddbService.uploadVideo(request,
					file, subimage, userId, token);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject uploadVoice(HttpServletRequest request,
			MultipartFile file, Long userId, String token) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			Map<String, Object> model = uploaddbService.uploadVoice(request,
					file, userId, token);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject uploadThirdPartyPic(HttpServletRequest request,
			MultipartFile file, String access_token, String openid,
			int thirdPartyType) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			Map<String, Object> model = uploaddbService.uploadThirdPartyPic(
					request, file, access_token, openid, thirdPartyType);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	public ResultObject uploadSingleVideo(HttpServletRequest request,
			MultipartFile file, Long userId,
			String token) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			Map<String, Object> model = uploaddbService.uploadSingleVideo(request,
					file, userId, token);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
}
