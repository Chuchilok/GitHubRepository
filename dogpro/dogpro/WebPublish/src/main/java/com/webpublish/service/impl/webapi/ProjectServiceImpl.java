package com.webpublish.service.impl.webapi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webpublish.common.interfacetool.DataGridResult;
import com.webpublish.common.interfacetool.ParameterObject;
import com.webpublish.common.interfacetool.ResultObject;
import com.webpublish.service.dbservice.ProjectdbService;
import com.webpublish.service.webapi.ProjectService;

@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectdbService projectdbService;

	public ResultObject addProject(ParameterObject parameterObject) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			String projectName = parameterObject
					.getStringParameter("projectName");
			Long userId = parameterObject.getLongParameter("userId");
			String token = parameterObject.getStringParameter("token");
			Map<String, Object> model = projectdbService.addProject(userId,
					projectName);
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
/*
	public ResultObject deleteProject(ParameterObject parameterObject) {
		// 返回类型
		ResultObject retObj = new ResultObject();
		try {
			Long projectId = parameterObject.getLongParameter("projectId");
			Long userId = parameterObject.getLongParameter("userId");
			String token = parameterObject.getStringParameter("token");
			Map<String, Object> model = projectdbService.deleteProject(userId,
					projectId);
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
*/
	public DataGridResult getProjectList(ParameterObject parameterObject) {
		// 返回类型
		DataGridResult retObj = new DataGridResult();
		try {
			Long userId = parameterObject.getLongParameter("userId");
			String token = parameterObject.getStringParameter("token");
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Map<String, Object>> model = projectdbService.getProjectList(
					pageNO, pageSize);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setRows(model);
			retObj.setTotal(projectdbService.getProjectListTotal());
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public DataGridResult getVersionList(ParameterObject parameterObject) {
		// 返回类型
		DataGridResult retObj = new DataGridResult();
		try {
			Long userId = parameterObject.getLongParameter("userId");
			String token = parameterObject.getStringParameter("token");
			Long projectId = parameterObject.getLongParameter("projectId");
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Map<String, Object>> model = projectdbService.getVersionList(
					projectId, pageNO, pageSize);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setRows(model);
			retObj.setTotal(projectdbService.getVersionListTotal(projectId));
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

}
