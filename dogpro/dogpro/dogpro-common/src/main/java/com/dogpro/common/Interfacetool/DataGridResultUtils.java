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
public class DataGridResultUtils {
	/**
	 * 封装返回结果
	 * 
	 * @param condition 查询条件
	 * @param datalist 结果集
	 * @return
	 */
	public static DataGridResult resultObject(Map<String, Object> condition, Object datalist) {
		DataGridResult resultObj = DataGridResult.getSuccessResultObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("datalist", datalist);
		resultObj.setRows(map);
		return resultObj;
	}

	/**
	 * 封装返回结果
	 * 
	 * @param datalist 结果集
	 * @return
	 */
	public static DataGridResult resultObject(Object datalist) {
		DataGridResult resultObj = DataGridResult.getSuccessResultObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datalist", datalist);
		resultObj.setRows(map);
		return resultObj;
	}

	/**
	 * 封装返回结果
	 * 
	 * @param result 结果集
	 * @return
	 */
	public static DataGridResult resultObject(Map<String, Object> result) {
		DataGridResult resultObj = DataGridResult.getSuccessResultObject();
		resultObj.setRows(result);
		return resultObj;
	}

	/**
	 * 封装返回结果
	 * 
	 * @param state 状态
	 * @return
	 */
	public static DataGridResult resultObject(boolean state) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", state);
		return resultObject(map);
	}

	/**
	 * 封装空的返回结果
	 * 
	 * @return
	 */
	public static DataGridResult resultNullObject() {
		DataGridResult resultObj = DataGridResult.getSuccessResultObject();
		resultObj.setRows(null);
		return resultObj;
	}

	/**
	 * 返回失败的结果
	 * 
	 * @param mes
	 * @return
	 */
	public static DataGridResult resultFailObject(String mes) {
		DataGridResult o = new DataGridResult();
		o.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
		o.setState(ResultObject.STATE_FAIL);
		o.setMessage(mes == null ? DataGridResult.Message_STATE_FAIL : mes);
		return o;
	}

	
	
	public static  DataGridResult handle(String parameter ) {
		if (parameter != null) {
			JSONObject objectMap = JSONObject.fromObject(parameter);
		 
			DataGridResult stu=(DataGridResult) JSONObject.toBean(objectMap,DataGridResult.class);  
			return stu;
		}

		return null;
	}
	
	 
}
