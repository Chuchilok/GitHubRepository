package com.dogpro.common.Interfacetool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 返回结果的工具类
 * 
 * @author hx
 * @time 2016年3月10日
 */
public class ResultObjectUtils {
	/**
	 * 封装返回结果
	 * 
	 * @param condition 查询条件
	 * @param datalist 结果集
	 * @return
	 */
	public static ResultObject resultObject(Map<String, Object> condition, Object datalist) {
		ResultObject resultObj = ResultObject.getSuccessResultObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("datalist", datalist);
		resultObj.setResult(map);
		return resultObj;
	}

	/**
	 * 封装返回结果
	 * 
	 * @param datalist 结果集
	 * @return
	 */
	public static ResultObject resultObject(Object datalist) {
		ResultObject resultObj = ResultObject.getSuccessResultObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datalist", datalist);
		resultObj.setResult(map);
		return resultObj;
	}

	/**
	 * 封装返回结果
	 * 
	 * @param result 结果集
	 * @return
	 */
	public static ResultObject resultObject(Map<String, Object> result) {
		ResultObject resultObj = ResultObject.getSuccessResultObject();
		resultObj.setResult(result);
		return resultObj;
	}

	/**
	 * 封装返回结果
	 * 
	 * @param state 状态
	 * @return
	 */
	public static ResultObject resultObject(boolean state) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", state);
		return resultObject(map);
	}

	/**
	 * 封装空的返回结果
	 * 
	 * @return
	 */
	public static ResultObject resultNullObject() {
		ResultObject resultObj = ResultObject.getSuccessResultObject();
		resultObj.setResult(null);
		return resultObj;
	}

	/**
	 * 返回失败的结果
	 * 
	 * @param mes
	 * @return
	 */
	public static ResultObject resultFailObject(String mes) {
		ResultObject o = new ResultObject();
		o.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
		o.setState(ResultObject.STATE_FAIL);
		o.setMessage(mes == null ? ResultObject.Message_STATE_FAIL : mes);
		return o;
	}

	
	
	public static  ResultObject handle(String parameter ) {
		if (parameter != null) {
			JSONObject objectMap = JSONObject.fromObject(parameter);
		 
			ResultObject stu=(ResultObject) JSONObject.toBean(objectMap,ResultObject.class);  
			return stu;
		}

		return null;
	}
	
	 
}
