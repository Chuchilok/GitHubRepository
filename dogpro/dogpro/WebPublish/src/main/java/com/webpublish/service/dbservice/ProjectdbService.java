package com.webpublish.service.dbservice;

import java.util.List;
import java.util.Map;

public interface ProjectdbService {
	/**
	 * 添加新项目
	 * @param userId
	 * @param projectName
	 * @return
	 */
	public Map<String,Object> addProject(Long userId,String projectName);
	/**
	 * 删除项目
	 * @param userId
	 * @param projectId
	 * @return
	 */
//	public Map<String, Object> deleteProject(Long userId,Long projectId);
	/**
	 * 获取项目列表
	 * @return
	 */
	public List<Map<String,Object>> getProjectList(int pageNO,int pageSize);
	/**
	 * 获取项目总数
	 * @return
	 */
	public int getProjectListTotal();
	/**
	 * 获取版本列表
	 * @param projectId
	 * @return
	 */
	public List<Map<String,Object>> getVersionList(Long projectId,int pageNO,int pageSize);
	/**
	 * 获取项目对应版本总数
	 * @return
	 */
	public int getVersionListTotal(Long projectId);
}
