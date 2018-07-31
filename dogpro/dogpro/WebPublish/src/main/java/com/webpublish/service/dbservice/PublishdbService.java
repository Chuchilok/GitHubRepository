package com.webpublish.service.dbservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface PublishdbService {
	/**
	 * 发布新版本
	 * @param userId
	 * @param projectId
	 * @param versionNO
	 * @param versionName
	 * @param downloadUrl
	 * @param request
	 * @return
	 */
	public Map<String, Object> publishVersion(Long userId,Long projectId,String versionNO,String versionName,String downloadUrl,int isRestart,HttpServletRequest request); 
	/**
	 * 删除版本
	 * @param userId
	 * @param versionId
	 * @return
	 */
//	public Map<String, Object> deleteVersion(Long userId,Long versionId);
}
