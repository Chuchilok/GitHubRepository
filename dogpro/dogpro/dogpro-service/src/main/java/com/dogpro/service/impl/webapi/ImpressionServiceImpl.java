package com.dogpro.service.impl.webapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.Base64;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.tool.NETTools;
import com.dogpro.domain.model.Discuss;
import com.dogpro.domain.model.Impression;
import com.dogpro.service.dbservice.ImpressiondbService;
import com.dogpro.service.webapi.ImpressionService;

@Service("ImpressionService")
public class ImpressionServiceImpl implements ImpressionService {

	@Autowired
	private ImpressiondbService impressiondbService;
	
	
	public ResultObject allImpression(ParameterObject parameterObject) {
		Discuss discuss = new Discuss();
		ResultObject retObj = new ResultObject();
		try {

			long userid = Long.parseLong(parameterObject.getStringParameter("userId"));
			String token = parameterObject.getToken();
			Integer pageNo = parameterObject.getIntegerParameter("pageNO");
			Integer pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Impression> list = impressiondbService.allImpression(userid,pageNo,pageSize);
			List<Map<String,String>> maps =new ArrayList<Map<String,String>>();
			List<Map<String,Object>> model = new ArrayList<Map<String,Object>>();
			for (Impression impression : list) {
				Map<String,Object> ma = new HashMap<String, Object>();
				ma.put("impressionId", impression.getImpressionId());
				ma.put("content", impression.getContent()==null?"":impression.getContent());
				model.add(ma);
			}
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}


	/**
	 * 添加印象
	 */
	public ResultObject addImpression(ParameterObject para) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			Map<String, Object> model = new HashMap<String, Object>();
			long friendId = Long.parseLong(para.getStringParameter("friendId"));//被印象的用户
			long userId = Long.parseLong(para.getStringParameter("userId"));
			String content = para.getStringParameter("content");
			//base64解码
			content = content.replaceAll("[\\s*\t\n\r]", "");
			String base64 = new String(Base64.base64ToByteArray(content), "utf-8");
			String token = para.getToken();
			Impression impression = new Impression();
			impression.setFriendid(friendId);
			impression.setUserid(userId);
			impression.setContent(base64);
			impression.setState(1);
			int res = impressiondbService.addImpression(impression);
			String msg = "";
			int flag = 0;
			if (res>0) {
				flag = 1 ;
				msg = "添加成功";
			}else {
				msg = "添加失败";
			}
			model.put("flag", flag);
			model.put("msg", msg);
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
	


	public ResultObject deleteImpression(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try {
			Long userId = parameterObject.getLongParameter("userId");
			Long impressionId = parameterObject.getLongParameter("impressionId");
			Map<String, Object> model = new HashMap<String, Object>();
			if(impressiondbService.deleteImpression(userId, impressionId)==1){
				model.put("flag", 1);
				model.put("msg", "删除成功");
			}else{
				model.put("flag", 0);
				model.put("msg", "删除失败");
			}
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

}
