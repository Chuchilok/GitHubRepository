package com.dogpro.service.dbservice;

import java.util.List;
import java.util.Map;

import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.extend.DoglocationDistance;

/**
 * 遛狗地点接口
 * @author 许明诚
 *
 */
public interface DogLocationdbService {
	//增加地点
	public DogLocation addLocation(DogLocation dogLocation);
	//修改地点
	public DogLocation alterLocation(DogLocation dogLocation);
	//删除地点
	public Long deleteLocation(Long dogLocationId);
	//搜索地点
	public List<Map<String, Object>> searchLocation(String keyword,int pageNo,int pageSize) throws Exception;
	//获取热门地点
	public List<DogLocation> hotLocation();
	public DogLocation findDogLocationById(Long dogLocationId);
	/**
	 * 根据 地区名 经纬度 返回 从近到远 遛狗地点数据
	 * @param districts
	 * @param latitude
	 * @param longitude
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<DoglocationDistance> dogLocationDistanceList(double latitude,double longitude,int pageNo,int pageSize);
	/**
	 * 根据 地区名 获取指定数目 热门遛狗点
	 * @param districts
	 * @param size
	 * @return
	 */
	public List<DoglocationDistance> getHotLocationByDistricts(double latitude, double longitude,String districts,int size);
}
