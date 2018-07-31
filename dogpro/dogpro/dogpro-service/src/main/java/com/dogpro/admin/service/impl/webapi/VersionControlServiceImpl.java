package com.dogpro.admin.service.impl.webapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.admin.service.dbservice.VersionControldbService;
import com.dogpro.admin.service.webapi.VersionControlService;
import com.dogpro.common.Interfacetool.DataGridResult;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.tool.MyUtil;
import com.dogpro.domain.model.VersionControl;
import com.dogpro.domain.model.VersionControlExample;
@Service("VersionControlService")
public class VersionControlServiceImpl implements VersionControlService {
	@Autowired
	private VersionControldbService versionControldbService;
	
	public DataGridResult findVersionList(ParameterObject parameterObject) {
		VersionControlExample example = new VersionControlExample();
		DataGridResult retObj=new DataGridResult();//返回对象
		try{
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			String versionName = parameterObject.getStringParameter("versionName");
			VersionControlExample.Criteria cc = example.createCriteria();
			cc.andVersionnameLike("%"+versionName+"%");
			example.setPagination(new PaginationUtil(pageNO, pageSize));
			List<VersionControl> list = versionControldbService.findVersionList(example);
			List<Map<String,Object>> models = new ArrayList<Map<String,Object>>();
			for (VersionControl versionControl : list) {
				Map<String,Object> model = new HashMap();
				model.put("versionName", versionControl.getVersionname());
				model.put("deviceType", versionControl.getDevicetype());
				model.put("versionNumber", versionControl.getVersionnumber());
				model.put("downloadURL", versionControl.getDownloadurl());
				model.put("vid", versionControl.getId());
				model.put("releaseTime",MyUtil.dateToString( versionControl.getReleasetime()));
				models.add(model);
			}
 			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			int total = versionControldbService.getCountByExample(example);
			retObj.setTotal(total);
			retObj.setRows(models);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject operateVersionControl(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			int vid = parameterObject.getIntegerParameter("vid");
			int type = parameterObject.getIntegerParameter("type");
			String versionName = parameterObject.getStringParameter("versionName");
			int deviceType = parameterObject.getIntegerParameter("deviceType");
			int versionNumber = parameterObject.getIntegerParameter("versionNumber");
			String downloadURL = parameterObject.getStringParameter("downloadURL");
			VersionControl versionControl = new VersionControl();
			versionControl.setId(vid);
			versionControl.setVersionname(versionName);
			versionControl.setDevicetype(deviceType);
			versionControl.setVersionnumber(versionNumber);
			versionControl.setDownloadurl(downloadURL);
			versionControl.setState(1);
			int result = 0;
			String msg = "";
			int flag = 0;
			Date date =new Date();
			if (type == 1) {//增加
				versionControl.setId(null);
				versionControl.setReleasetime(date);
				versionControl.setAddtimes(date);
				versionControl.setUpdatetimes(date);
				result = versionControldbService.addVersionControl(versionControl);
			}else if(type == 2){//编辑
				versionControl.setUpdatetimes(date);
				result = versionControldbService.editVersionControl(versionControl);
			}else if(type == 3){//删除
				versionControl.setUpdatetimes(date);
				versionControl.setState(0);
				result = versionControldbService.delVersionControl(versionControl);
			}
			if (result >= 1 ) {
				msg = "操作成功";
				flag = 1;
			}else{
				msg = "操作失败";
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("msg",msg );
			model.put("flag",flag);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject checkVersion(ParameterObject paramterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			int deviceType = paramterObject.getIntegerParameter("deviceType");
			int versionNumber = paramterObject.getIntegerParameter("versionNumber");
			VersionControlExample example = new VersionControlExample();
			VersionControlExample.Criteria cc = example.createCriteria();
			cc.andDevicetypeEqualTo(deviceType);
			cc.andVersionnumberGreaterThan(versionNumber);//版本号判断大于当前的版本号
			example.setOrderByClause("versionnumber asc");//暂时，为了拿到最后一个版本的值
			List<VersionControl> list = versionControldbService.checkVersion(example);//selectByExample动了手脚
			String msg = "无需更新";
			int flag = 0;
			String downloadURL = "";
			for (VersionControl versionControl : list) {
				downloadURL = versionControl.getDownloadurl();
				msg = "需要更新";
				flag = 1;
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("msg", msg);
			model.put("flag", flag);
			model.put("downloadURL", downloadURL);
			String token = paramterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject IOSCheckVersion(ParameterObject paramterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flag", 1);
			String token = paramterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

}
