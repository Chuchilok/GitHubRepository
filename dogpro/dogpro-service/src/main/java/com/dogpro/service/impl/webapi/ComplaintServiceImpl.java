package com.dogpro.service.impl.webapi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.tool.NETTools;
import com.dogpro.domain.model.Complaint;
import com.dogpro.service.dbservice.ComplaintdbService;
import com.dogpro.service.webapi.ComplaintService;

@Service("ComplaintService")
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	private ComplaintdbService complaintdbService;
	
	public ResultObject userComplaint(ParameterObject parameterObject) {
		// complaintdbService.userComplaint(complaint);
		ResultObject retObj = new ResultObject();
		try {
			long userId = Long.parseLong(parameterObject.getStringParameter("userId")) ;
			Long friendCir_id = Long.parseLong( parameterObject
					.getStringParameter("friendCirId"));
			String complaintContent = parameterObject
					.getStringParameter("complaintContent");
			String complaintType = parameterObject
					.getStringParameter("complaintType");
			String token = parameterObject.getToken();// 暂时不判断
			Complaint complaint = new Complaint();
			complaint.setUserId(userId);
			complaint.setFriendcirId(friendCir_id);
			complaint.setComplaintcontent(complaintContent);
			complaint.setComplainttype(complaintType);
			//获取客户端IP
			HttpServletRequest request = (HttpServletRequest) parameterObject.getParameter("requestobj");
			complaint.setComplaintip(NETTools.getIpAddr(request ));
			int result = complaintdbService.userComplaint(complaint);// 插入投诉记录
			int flag = 0;
			String msg = "";
			if (result>0) {//投诉成功
				flag = 1;
				msg = "投诉成功，请等待审批结果";
			}else{
				flag = 0;
				msg = "投诉失败";
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
	
}
