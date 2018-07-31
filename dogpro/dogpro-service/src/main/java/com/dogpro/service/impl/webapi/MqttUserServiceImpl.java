package com.dogpro.service.impl.webapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.service.dbservice.MqttUserdbService;
import com.dogpro.service.webapi.MqttUserService;

@Service("MqttUserService")
public class MqttUserServiceImpl implements MqttUserService {
	@Autowired
	private MqttUserdbService mqttUserdbService;
	public ResultObject getMqttUser(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();
		try{
			Long userId=parameterObject.getLongParameter("userId");
			Map<String, Object> model = new HashMap<String, Object>();
			if(userId!=null){
				model = mqttUserdbService.getMqttUser(userId);
				retObj.setResult(model);
			}
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
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
