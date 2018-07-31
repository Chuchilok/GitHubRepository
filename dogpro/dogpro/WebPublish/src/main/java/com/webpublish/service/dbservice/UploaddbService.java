package com.webpublish.service.dbservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.webpublish.common.interfacetool.ResultObject;

public interface UploaddbService {
	public ResultObject uploadFile(HttpServletRequest request,HttpServletResponse response ,MultipartFile file,Long userId,String token);
}
