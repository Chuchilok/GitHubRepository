package com.webpublish.service.impl.dbservice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.webpublish.common.utils.NETTools;
import com.webpublish.dao.ProjectMapper;
import com.webpublish.dao.PublishRecordMapper;
import com.webpublish.dao.VersionMapper;
import com.webpublish.domain.model.IMsend;
import com.webpublish.domain.model.Project;
import com.webpublish.domain.model.PublishRecord;
import com.webpublish.domain.model.PublishRecordExample;
import com.webpublish.domain.model.Version;
import com.webpublish.mqtttool.MQTTServiceInt;
import com.webpublish.service.dbservice.PublishdbService;

@Service("PublishdbService")
public class PublishdbServiceImpl implements PublishdbService {
	@Autowired
	private VersionMapper versionmapper;
	@Autowired
	private PublishRecordMapper publishRecordMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private MQTTServiceInt mqttService;
	
	
	public Map<String, Object> publishVersion(Long userId, Long projectId,
			String versionNO, String versionName, String downloadUrl,int isRestart,
			HttpServletRequest request) {
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "发布失败";
		try {
			// 新增版本记录
			Date date = new Date();
			Version version = new Version();
			version.setProjectId(projectId);
			version.setVersionNo(versionNO);
			version.setVersionName(versionName);
			version.setDownloadUrl(downloadUrl);
			version.setState(1);
			version.setAddtimes(date);
			version.setUpdatetimes(date);
			versionmapper.insertSelective(version);
			// 新增发布记录
			PublishRecord publishRecord = new PublishRecord();
			publishRecord.setUserId(userId);
			publishRecord.setVersionId(version.getId());
			try {
				publishRecord.setPublishIp(NETTools.getIpAddr(request));
				publishRecord.setPublishPort(NETTools.getRemotePort(request));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			publishRecord.setPublishtimes(date);
			publishRecord.setAddtimes(date);
			publishRecord.setUpdatetimes(date);
			publishRecordMapper.insertSelective(publishRecord);
			//发送im信息
			Project project = projectMapper.selectByPrimaryKey(projectId);
			handleMsg(project.getProjectName(), versionNO, downloadUrl,isRestart);
			flag = 1;
			msg = "发布成功";
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}
	/*
	public Map<String, Object> deleteVersion(Long userId, Long versionId) {
		// TODO Auto-generated method stub
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "删除失败";
		// 查找对应 发布记录
		PublishRecordExample publishRecordExample = new PublishRecordExample();
		PublishRecordExample.Criteria pCriteria = publishRecordExample
				.createCriteria();
		pCriteria.andVersionIdEqualTo(versionId);
		List<PublishRecord> publishRecords = publishRecordMapper
				.selectByExample(publishRecordExample);
		if (!publishRecords.isEmpty()) {
			PublishRecord publishRecord = publishRecords.get(0);
			if (publishRecord.getUserId().equals(userId)) {
				// 更新版本数据
				Version version = versionmapper.selectByPrimaryKey(versionId);
				Date date = new Date();
				version.setState(0);
				version.setUpdatetimes(date);
				versionmapper.updateByPrimaryKeySelective(version);
				flag = 1;
				msg = "删除成功";
			} else {
				flag = 0;
				msg = "用户id与发布者id不一致";
			}
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}
	*/
	public void handleMsg(String projectName,String versionNO,String downloadUrl,int isRestart){
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("versionNO", versionNO);
		msg.put("downloadUrl", downloadUrl);
		msg.put("isRestart", isRestart);
		JSONObject jsonObject = new JSONObject(msg);
		IMsend iMsend = new IMsend();
		iMsend.setToken(projectName);
		iMsend.setContent(jsonObject.toJSONString());
		mqttService.sendMsg(iMsend);
	}

}
