package com.dogpro.admin.service.impl.dbservice;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.admin.service.dbservice.AdminDogLocationdbService;
import com.dogpro.admin.service.dbservice.AdminRedisdbService;
import com.dogpro.common.domain.RedisDoglocation;
import com.dogpro.common.tool.MapTools;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.dao.DogLocationMapper;
import com.dogpro.domain.model.AreaSpace;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.DogLocationExample;
import com.vividsolutions.jts.geom.Polygon;
@Service("AdminDogLocationdbService")
public class AdminDogLocationdbServiceImpl implements AdminDogLocationdbService {
	@Autowired
	private DogLocationMapper dogLocationMapper;
	@Autowired
	private AdminRedisdbService adminRedisdbService;
	
	public List<DogLocation> locationList(String str, int pageNO,
			int pageSize) {
		DogLocationExample example = new DogLocationExample();
		PaginationUtil pagination = new PaginationUtil(pageNO,pageSize);
		example.setPagination(pagination);
		//暂不做排序  。。。
		if (str!=null && !str.equals("")) {
			DogLocationExample.Criteria cExample = example.createCriteria();
			//添加条件搜索
		}
		return dogLocationMapper.selectByExample(example);
	}
	//修改遛狗地点
	public DogLocation updateLocation(DogLocation dogLocation) {
		if(dogLocationMapper.updateByPrimaryKeySelective(dogLocation)==1){
			//部分信息 插入redis
			RedisDoglocation redisDoglocation = new RedisDoglocation(dogLocation.getId(), dogLocation.getAreaname(), dogLocation.getKeyss(), dogLocation.getLocationpic());
			adminRedisdbService.setDolocation(redisDoglocation);
			return dogLocationMapper.selectByPrimaryKey(dogLocation.getId());
		}
		return null;
		
	}
	//删除遛狗地点 (只是更改了state)
	public Long deleteLocation(long locationId) {
			DogLocation record = new DogLocation();
			record.setState(0);
			record.setId(locationId);
			dogLocationMapper.deleteByPrimaryKey(locationId);
//			if(dogLocationMapper.updateByPrimaryKeySelective(record)==1){
//				return locationId;
//			}
			return locationId;
	}
	public DogLocation getDogLocationById(Long doglocationid) {
		return dogLocationMapper.selectByPrimaryKey(doglocationid);
	}
	public int getCountByExample(DogLocationExample example) {
		return dogLocationMapper.countByExample(example);
	}
	
	//新增遛狗地点
	public DogLocation addLocation(DogLocation dogLocation) {
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
			adminRedisdbService.pushAreaSpace(areaSpace);
			adminRedisdbService.setAreaSpaceMap(areaSpace);//存入redis
			adminRedisdbService.setGroup(dogLocation.getId(), dogLocation.getKeyss());
			//部分信息 插入redis
			RedisDoglocation redisDoglocation = new RedisDoglocation(dogLocation.getId(), dogLocation.getAreaname(), dogLocation.getKeyss(), dogLocation.getLocationpic());
			adminRedisdbService.setDolocation(redisDoglocation);
			return dogLocation;
		}
		return null;
	}

}
