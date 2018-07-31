package com.dogpro.service.impl.dbservice;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.common.domain.RedisDoglocation;
import com.dogpro.common.tool.MapTools;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.UUIDGenerator;
import com.dogpro.dao.DogLocationMapper;
import com.dogpro.domain.model.AreaSpace;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.DogLocationExample;
import com.dogpro.domain.model.extend.DoglocationDistance;
import com.dogpro.lucene.index.DoglocationIndex;
import com.dogpro.service.dbservice.DogLocationdbService;
import com.dogpro.service.dbservice.RedisdbService;
import com.vividsolutions.jts.geom.Polygon;

/**
 * 遛狗地点接口
 * 
 * @author 许明诚
 *
 */
@Service("DogLocationdbService")
public class DogLocationdbServiceImpl implements DogLocationdbService {

	@Autowired
	private DogLocationMapper dogLocationMapper;
	@Autowired
	private RedisdbService redisdbService;  
	  
	  
	
	//参数配置文件
	private Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
	//遛狗地点初始化 默认参数 头像
	private String default_groupPic = packagesMap.get("default_groupPic").toString().trim();
	
	public DogLocation addLocation(DogLocation dogLocation) {
		dogLocation.setAddtimes(new Date());
		dogLocation.setUpdatetimes(new Date());
		dogLocation.setOrders(1);
		dogLocation.setHot(0);
		dogLocation.setState(1);
		dogLocation.setKeyss(UUIDGenerator.getToken64());
		dogLocation.setLocationpic(default_groupPic);
		int r = dogLocationMapper.insertSelective(dogLocation);
		if (r > 0) {
			AreaSpace areaSpace = new AreaSpace();
			Point2D.Double point = new Point2D.Double(dogLocation
					.getLongitude().doubleValue(), dogLocation.getLatitude()
					.doubleValue());// 以当前点为中心，
			HashMap map = MessageConsumerConfig.readConfig("config.properties");
			java.lang.Double d = java.lang.Double.valueOf(map.get("Range_KM")
					.toString());
			List<Double> list = MapTools.getCurrentCircle(point, d);// 两公里范围内的
			Polygon polygon = MapTools.getPolygonByListPoint(list);
			areaSpace.setAreaRange(polygon.toString()); // 存入数据库
			areaSpace.setDoglocationId(dogLocation.getId());
			Date date = new Date();
			areaSpace.setState(1);
			areaSpace.setUpdatetimes(date);
			areaSpace.setAddtimes(date);
			// 插入redis库
			redisdbService.pushAreaSpace(areaSpace);
			redisdbService.setAreaSpaceMap(areaSpace);
			redisdbService.setGroup(dogLocation.getId(), dogLocation.getKeyss());
			//部分信息 插入redis
			RedisDoglocation redisDoglocation = new RedisDoglocation(dogLocation.getId(), dogLocation.getAreaname(), dogLocation.getKeyss(), dogLocation.getLocationpic());
			redisdbService.setDolocation(redisDoglocation);
			return dogLocation;
		}
		return null;
	}

	// 修改地点
	public DogLocation alterLocation(DogLocation dogLocation) {
		// TODO Auto-generated method stub
		dogLocation.setUpdatetimes(new Date());
		if (dogLocationMapper.updateByPrimaryKeySelective(dogLocation) == 1) {
			//部分信息 插入redis
			RedisDoglocation redisDoglocation = new RedisDoglocation(dogLocation.getId(), dogLocation.getAreaname(), dogLocation.getKeyss(), dogLocation.getLocationpic());
			redisdbService.setDolocation(redisDoglocation);
			return dogLocationMapper.selectByPrimaryKey(dogLocation.getId());
		}
		return null;
	}

	// 删除地点
	public Long deleteLocation(Long dogLocationId) {
		// TODO Auto-generated method stub
		DogLocation dogLocation = dogLocationMapper
				.selectByPrimaryKey(dogLocationId);
		if (dogLocation != null) {
			dogLocation.setState(0);
			alterLocation(dogLocation);
			return dogLocationId;
		} else {
			return -1L;
		}
	}

	// 搜索地点
	public List<Map<String, Object>> searchLocation(String keyword,int pageNo,int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		DoglocationIndex doglocationIndex = new DoglocationIndex();
		// 判断搜索词是否为空
		List<DogLocation> dogLocations = null;
		String searchword = keyword.replaceAll(" ", "");
		if (searchword.equals("")) {
			dogLocations = hotLocation();
		} else {
			dogLocations = doglocationIndex.testSearchIndex(searchword,pageNo,pageSize);
		}
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		for (DogLocation dogLocation : dogLocations) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("locationId", dogLocation.getId());
			model.put("districts", dogLocation.getDistricts());
			model.put("addressalias", dogLocation.getAreaname());
			model.put("townStreet", dogLocation.getTownstreet());
			int isHot = dogLocation.getHot() > 0 ? 1 : 0;
			model.put("isHot", isHot);
			modelList.add(model);
		}
		return modelList;
	}

	/**
	 * 返回热门的五条记录
	 */
	public List<DogLocation> hotLocation() {
		DogLocationExample example = new DogLocationExample();
		example.setOrderByClause("hot desc,orders desc");
		PaginationUtil pagination = new PaginationUtil(0, 5);
		example.setPagination(pagination);
		return dogLocationMapper.selectByExample(example);
	}

	/**
	 * 通过dogLocationId返回对象
	 */
	public DogLocation findDogLocationById(Long dogLocationId) {
		return dogLocationMapper.selectByPrimaryKey(dogLocationId);
	}
 
	/**
	 * 根据 地区名 经纬度 返回 从近到远 遛狗地点数据
	 */
	public List<DoglocationDistance> dogLocationDistanceList(double latitude, double longitude, int pageNo, int pageSize) {
		PaginationUtil pagination = new PaginationUtil(pageNo,pageSize);
		return dogLocationMapper.selectByDistance(latitude, longitude, pagination);
	}

	/**
	 * 根据 地区名 获取指定数目 热门遛狗点
	 */
	public List<DoglocationDistance> getHotLocationByDistricts(double latitude, double longitude,String districts,
			int size) {
		PaginationUtil pagination = new PaginationUtil(0, size);
		return dogLocationMapper.selectHotLocation(latitude, longitude, districts, pagination);
	}

}
