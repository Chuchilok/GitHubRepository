package com.dogpro.service.impl.webapi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.extend.DoglocationDistance;
import com.dogpro.service.dbservice.DogLocationdbService;
import com.dogpro.service.webapi.DogLocationService;

/**
 * 遛狗地点接口
 * 
 * @author 许明诚
 *
 */
@Service("DogLocationService")
public class DogLocationServiceImpl implements DogLocationService {

	@Autowired
	private DogLocationdbService dogLocationdbService;

	// 地点列表
	public ResultObject locationList(ParameterObject parameterObject) {
		DogLocation dogLocation = new DogLocation();
		//返回的是集合
		ResultObject retObj=new ResultObject();//返回对象
		try{
			//直接返回前五条热度高的地点
			String keyword = parameterObject.getStringParameter("keyword");
			List<DogLocation> location = dogLocationdbService.hotLocation();
			List<Map<String, Object>> model = new ArrayList<Map<String,Object>>();
			for (DogLocation d : location) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("addressalias", d.getAreaname()==null?"":d.getAreaname());
				map.put("districts",d.getDistricts()==null?"":d.getDistricts());
				map.put("townStreet",d.getTownstreet()==null?"":d.getTownstreet());
				map.put("locationId", d.getId());
				//此处等于没有判断，目前先这样
				int isHot = d.getHot()>0?1:0;
				map.put("isHot", isHot);
				model.add(map);
			}
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

	// 新增地点
	public ResultObject addLocation(ParameterObject parameterObject) {

		ResultObject retObj = new ResultObject();
		try {
			DogLocation dogLocation = new DogLocation();
//			Long pid = Long
//					.parseLong(parameterObject.getStringParameter("pid"));
			Long pid = 0l;
			String areaName = parameterObject.getStringParameter("areaName");
//			String addressAlias = parameterObject
//					.getStringParameter("addressAlias");
			String addressAlias = areaName;
			String provinces = parameterObject.getStringParameter("provinces");
			String municipalities = parameterObject
					.getStringParameter("municipalities");
			String districts = parameterObject.getStringParameter("districts");
			String townstreet = parameterObject.getStringParameter("townstreet");
			String address = parameterObject.getStringParameter("address");
			BigDecimal longitude = new BigDecimal(parameterObject
					.getStringParameter("longitude"));
			BigDecimal latitude = new BigDecimal(parameterObject
					.getStringParameter("latitude"));
//			float perimeter = Float.parseFloat(parameterObject
//					.getStringParameter("perimeter"));
			float perimeter = 2.0f;
			Long creatorUserId = parameterObject.getLongParameter("userId");
			dogLocation.setAddress(address);
			dogLocation.setAddressalias(addressAlias);
			dogLocation.setCreatoruserid(creatorUserId);
			dogLocation.setAreaname(areaName);
			dogLocation.setPid(pid);
			dogLocation.setProvinces(provinces);
			dogLocation.setMunicipalities(municipalities);
			dogLocation.setDistricts(districts);
			dogLocation.setLongitude(longitude);
			dogLocation.setLatitude(latitude);
			dogLocation.setPerimeter(perimeter);
			dogLocation.setTownstreet(townstreet);
			String token = parameterObject.getToken();// 令牌
			DogLocation result = dogLocationdbService.addLocation(dogLocation);
			int flag = 0;
			String msg = "";
			Long doglocationId = 0l;
			Map<String,Object> model = new HashMap();
			if (result!=null) {
				flag = 1;
				msg="添加位置成功";
				doglocationId = result.getId();
			}else{
				flag = 0;
				msg="添加位置失败";
			}
			model.put("flag", flag);
			model.put("msg", msg);
			model.put("doglocationId", doglocationId);
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
 * 修改地点
 */
	public ResultObject alterLocation(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj = new ResultObject();
		try {
			String token = parameterObject.getToken();
			Long id = Long.parseLong(parameterObject.getStringParameter("locationId"));
			JSONObject jsonObject = new JSONObject(parameterObject.getParameterObject());
			DogLocation dogLocation = JSONObject.toJavaObject(jsonObject, DogLocation.class);
			dogLocation.setId(id);
			Map<String, Object> model = new HashMap<String, Object>();
			DogLocation dLocation = dogLocationdbService.alterLocation(dogLocation);
			if(dLocation!=null){
				model.put("flag", 1);
				model.put("msg", "修改成功");
			}else{
				model.put("flag", 0);
				model.put("msg", "修改失败");
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
	 * 删除地点
	 */
	public ResultObject deleteLocation(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj = new ResultObject();
		try {
			String token = parameterObject.getToken();
			Long locationId = Long.parseLong(parameterObject.getStringParameter("locationId"));
			Map<String, Object> model = new HashMap<String, Object>();
			if(dogLocationdbService.deleteLocation(locationId)>0){
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
	/**
	 * 搜索地点
	 */
	public ResultObject searchLocation(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj = new ResultObject();
		try {
			String token = parameterObject.getToken();
			String keyword  = parameterObject.getStringParameter("keyword");
			int pageNo = parameterObject.getIntegerParameter("pageNo");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Map<String, Object>> modelList = dogLocationdbService.searchLocation(keyword,pageNo,pageSize);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(modelList);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	
	/**
	 * 3条热门同地区遛狗地点     +  同地区地点列表接口   按经纬度距离 近到远
	 */
	public ResultObject dogLocationDistanceList(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			//获取传入参数
			String districts = parameterObject.getStringParameter("districts");
			double latitude = Double.valueOf(parameterObject.getStringParameter("latitude"));
			double longitude = Double.valueOf(parameterObject.getStringParameter("longitude"));
			Integer pageNo = parameterObject.getIntegerParameter("pageNo");
			Integer pageSize = parameterObject.getIntegerParameter("pageSize");
			Integer getHotList = parameterObject.getIntegerParameter("getHotList");
			//返回参数
			List<Map<String, Object>> modelList = new ArrayList<Map<String,Object>>();
			//判断是否需要前3条 热门遛狗点
			if(getHotList!=null&&getHotList==1){
				List<DoglocationDistance> dogLocations = dogLocationdbService.getHotLocationByDistricts(latitude,longitude,districts, 3);
				for(DoglocationDistance distance:dogLocations){
					//只取 30公里以内
					if(distance.getDistance()>30000){
						break;
					}
					Map<String, Object>	model = new HashMap<String, Object>();
					model.put("locationId", distance.getId());
					model.put("addressalias", distance.getAreaname());
					model.put("townStreet", distance.getTownstreet());
					model.put("districts", distance.getDistricts());
					int isHot = distance.getHot()>0?1:0;
					model.put("isHot", isHot);
					model.put("distance", distance.getDistance());
					modelList.add(model);
					//保持分页大小 为 3条热门地点 + 距离地点
					pageSize--;
				}
			}
			List<DoglocationDistance> distances = dogLocationdbService.dogLocationDistanceList(latitude, longitude, pageNo, pageSize);
			for(DoglocationDistance distance:distances){
				//只取 30公里以内
				if(distance.getDistance()>30000){
					break;
				}
				Map<String, Object>	model = new HashMap<String, Object>();
				model.put("locationId", distance.getId());
				model.put("addressalias", distance.getAreaname());
				model.put("townStreet", distance.getTownstreet());
				model.put("districts", distance.getDistricts());
				int isHot = distance.getHot()>0?1:0;
				model.put("isHot", isHot);
				model.put("distance", distance.getDistance());
				modelList.add(model);
			}
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(modelList);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

}
