package com.dogpro.service.impl.webapi;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.tool.NETTools;
import com.dogpro.domain.model.Complaint;
import com.dogpro.domain.model.Discuss;
import com.dogpro.service.dbservice.DiscussdbService;
import com.dogpro.service.dbservice.FriendCircledbService;
import com.dogpro.service.webapi.DisussService;
@Service("DisussService")
public class DisussServiceImpl implements DisussService {

	@Autowired
	private DiscussdbService discussdbService;
	@Autowired
	private FriendCircledbService friendCircledbService;
	/**
	 * 发布评论
	 */
	public ResultObject publishDisuss(ParameterObject parameterObject) {
		Discuss discuss = new Discuss();
		ResultObject retObj = new ResultObject();
		try {
			long friendCir_id = Long.parseLong(parameterObject.getStringParameter("friendCirId"));
			long PId = Long.parseLong(parameterObject.getStringParameter("PId"));
			long user_id = Long.parseLong(parameterObject.getStringParameter("userId"));
			String discussContent = parameterObject.getStringParameter("discussContent");
			int flag = 0;
			String msg = "";
			if(discussContent!=null&&!discussContent.trim().equals("")){
			discuss.setPid(PId);//如果 0 为回复朋友圈内容
			discuss.setUserId(user_id);
			discuss.setDiscusscontent(discussContent);
			discuss.setFriendcirId(friendCir_id);
			discuss.setState(1);
			//客户端ip地址
			HttpServletRequest request = (HttpServletRequest) parameterObject.getParameter("requestobj");
			discuss.setDiscussip(NETTools.getIpAddr(request));
			//发布
			int result = discussdbService.publishDisuss(discuss);
			if (result>0) {//评价成功
				flag = 1;
				msg = "评价成功";
				//修改朋友圈更新code
				friendCircledbService.setFCupdateCode(friendCir_id, new Date().getTime());
			}else{
				flag = 0;
				msg = "评价失败";
			}
			}else{
				flag = 0;
				msg = "评论不能为空";
			}
			Map<String,Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
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
	
	//查看朋友圈详情评论列表
	public ResultObject friendCircleDisuss(ParameterObject parameterObject){
		Long friendCirId = Long.parseLong(parameterObject.getStringParameter("friendCirId"));
		Long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
		String token = parameterObject.getToken();
		int pageNO = parameterObject.getIntegerParameter("pageNO");
		int pageSize = parameterObject.getIntegerParameter("pageSize");
		
		ResultObject retObj = new ResultObject();
		try {
			List<Map<String, Object>> model = discussdbService.allianceDetailDiscuss(friendCirId, userId, pageNO, pageSize);
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

	public ResultObject deleteDisuss(ParameterObject parameterObject) {
		Long discussId = Long.parseLong(parameterObject.getStringParameter("discussId"));
		Long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
		ResultObject retObj = new ResultObject();
		try {
			Map<String, Object> model = discussdbService.deleteDisuss(userId, discussId);
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
