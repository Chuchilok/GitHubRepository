package com.webpublish.service.webapi;


import com.webpublish.common.interfacetool.DataGridResult;
import com.webpublish.common.interfacetool.ParameterObject;
import com.webpublish.common.interfacetool.ResultObject;

public interface ProjectService {
	/**
	 * 添加新项目
	 * @param parameterObject
	 * @return
	 */
	public ResultObject addProject(ParameterObject parameterObject);
	/**
	 * 删除项目
	 * @param parameterObject
	 * @return
	 */
//	public ResultObject deleteProject(ParameterObject parameterObject);
	/**
	 * 获取项目列表
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult getProjectList(ParameterObject parameterObject);
	/**
	 * 获取版本列表
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult getVersionList(ParameterObject parameterObject);
	
}
