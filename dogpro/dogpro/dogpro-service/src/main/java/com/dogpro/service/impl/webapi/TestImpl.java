package com.dogpro.service.impl.webapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.service.webapi.TestService;

 
@Service("Testservice") 
public class TestImpl implements TestService {

	public ResultObject test1fun(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		
		ResultObject retObj=new ResultObject();//返回对象
		try{
			Map<String, Object> model = new HashMap<String, Object>();
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

}
