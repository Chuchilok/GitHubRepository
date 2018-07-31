package com.webpublish.service.impl.dbservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webpublish.common.utils.PaginationUtil;
import com.webpublish.dao.ProjectMapper;
import com.webpublish.dao.PublishRecordMapper;
import com.webpublish.dao.UserMapper;
import com.webpublish.dao.VersionMapper;
import com.webpublish.domain.model.Project;
import com.webpublish.domain.model.ProjectExample;
import com.webpublish.domain.model.PublishRecord;
import com.webpublish.domain.model.PublishRecordExample;
import com.webpublish.domain.model.User;
import com.webpublish.domain.model.Version;
import com.webpublish.domain.model.VersionExample;
import com.webpublish.service.dbservice.ProjectdbService;

@Service("ProjectdbService")
public class ProjectdbServiceImpl implements ProjectdbService {
	@Autowired
	private UserMapper usermapper;
	@Autowired
	private ProjectMapper projectmapper;
	@Autowired
	private VersionMapper versionmapper;
	@Autowired
	private PublishRecordMapper publishRecordMapper;

	public Map<String, Object> addProject(Long userId, String projectName) {
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		Project project = new Project();
		project.setBuilduserId(userId);
		project.setProjectName(projectName);
		project.setState(1);
		Date date = new Date();
		project.setBuildtimes(date);
		project.setAddtimes(date);
		project.setUpdatetimes(date);
		if (projectmapper.insertSelective(project) == 1) {
			flag = 1;
			msg = "添加成功";
		} else {
			flag = 0;
			msg = "添加失败";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}
/*
	public Map<String, Object> deleteProject(Long userId, Long projectId) {
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		Project project = projectmapper.selectByPrimaryKey(projectId);
		if (project.getBuilduserId().equals(userId)) {
			project.setState(0);
			Date date = new Date();
			project.setUpdatetimes(date);
			projectmapper.updateByPrimaryKeySelective(project);
			flag = 1;
			msg = "删除成功";
		} else {
			flag = 0;
			msg = "非项目创建者本人";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}
*/
	public List<Map<String, Object>> getProjectList(int pageNO,int pageSize) {
		// 返回类型
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		try {
			ProjectExample projectExample = new ProjectExample();
			ProjectExample.Criteria pCriteria = projectExample.createCriteria();
			pCriteria.andStateEqualTo(1);
			PaginationUtil paginationUtil = new PaginationUtil(pageNO,pageSize);
			projectExample.setPagination(paginationUtil);
			projectExample.setOrderByClause("buildTimes desc");
			List<Project> projects = projectmapper
					.selectByExample(projectExample);
			for (Project project : projects) {
				Map<String, Object> model = new HashMap<String, Object>();
				// 创建者
				User user = usermapper.selectByPrimaryKey(project
						.getBuilduserId());
				// 发布时间
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String buildTimes = formatter.format(project.getBuildtimes());
				model.put("projectId", project.getId());
				model.put("projectName", project.getProjectName());
				model.put("buildTimes", buildTimes);
				model.put("builderName", user.getUserName());
				modelList.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return modelList;
	}

	public List<Map<String, Object>> getVersionList(Long projectId,int pageNO,int pageSize) {
		// 返回类型
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		try {
			VersionExample versionExample = new VersionExample();
			VersionExample.Criteria vCriteria = versionExample.createCriteria();
			vCriteria.andProjectIdEqualTo(projectId).andStateEqualTo(1);
			PaginationUtil paginationUtil = new PaginationUtil(pageNO,pageSize);
			versionExample.setPagination(paginationUtil);
			versionExample.setOrderByClause("addTimes desc");
			List<Version> versions = versionmapper.selectByExample(versionExample);
			for (Version version:versions) {
				Map<String, Object> model = new HashMap<String, Object>();
				//发布记录
				PublishRecordExample publishRecordExample = new PublishRecordExample();
				PublishRecordExample.Criteria pCriteria = publishRecordExample.createCriteria();
				pCriteria.andVersionIdEqualTo(version.getId());
				List<PublishRecord> publishRecords = publishRecordMapper.selectByExample(publishRecordExample);
				if(!publishRecords.isEmpty()){
					PublishRecord publishRecord = publishRecords.get(0);
					// 创建者
					User user = usermapper.selectByPrimaryKey(publishRecord.getUserId());
					// 发布时间
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String publishTime = formatter.format(publishRecord.getPublishtimes());
					model.put("versionId", version.getId());
					model.put("versionNO", version.getVersionNo());
					model.put("versionName", version.getVersionName());
					model.put("downloadUrl", version.getDownloadUrl());
					model.put("publishTime", publishTime);
					model.put("publisherName", user.getUserName());
					modelList.add(model);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return modelList;
	}

	@Override
	public int getProjectListTotal() {
		// TODO Auto-generated method stub
		ProjectExample projectExample = new ProjectExample();
		ProjectExample.Criteria pCriteria = projectExample.createCriteria();
		pCriteria.andStateEqualTo(1);
		
		return projectmapper.countByExample(projectExample);
	}

	@Override
	public int getVersionListTotal(Long projectId) {
		// TODO Auto-generated method stub
		VersionExample versionExample = new VersionExample();
		VersionExample.Criteria vCriteria = versionExample.createCriteria();
		vCriteria.andProjectIdEqualTo(projectId).andStateEqualTo(1);
		return versionmapper.countByExample(versionExample);
	}

}
