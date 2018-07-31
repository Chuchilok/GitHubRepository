package com.dogpro.admin.service.impl.dbservice;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.admin.service.dbservice.AdminAreaSpacedbService;
import com.dogpro.admin.service.dbservice.AdminRedisdbService;
import com.dogpro.common.tool.MapTools;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.dao.AreaSpaceMapper;
import com.dogpro.dao.DogLocationMapper;
import com.dogpro.domain.model.AreaSpace;
import com.dogpro.domain.model.AreaSpaceExample;
import com.dogpro.domain.model.DogLocation;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
@Service("AdminAreaSpacedbService")
public class AdminAreaSpacedbServiceImpl implements AdminAreaSpacedbService {

	@Autowired
	private AreaSpaceMapper areaSpaceMapper;
	@Autowired
	private DogLocationMapper dogLocationMapper;
	@Autowired
	private AdminRedisdbService redisdbService;
	
	public List<java.lang.Double[]> returnPolygonByDogLocationId(Long dogLocationId) throws ParseException {
		AreaSpace areaSpaceMap = redisdbService.getAreaSpaceMap(dogLocationId);
		String str = "";
		Polygon polygon = null;
		List<java.lang.Double[]> list2 = null;
		if (null != areaSpaceMap) {
			if (1 == areaSpaceMap.getState()) {
				
				str = (String) areaSpaceMap.getAreaRange();//Polygon((11 11,12 12,11 11))
				if(str.contains("|")&& !str.contains("(")){//判断是否后台修改数据
					str = MapTools.returnPolygon(str).toString();
				}
				polygon = MapTools.createPolygonByWKT(str);//获取一个面的对象
				list2 = MapTools.returnListStrByPolygon(polygon);
			}
		}
		return list2;
	}
	public int addDogLocationAreaSpace(AreaSpace areaSpace) {
		//加入Redis库队列
		boolean inRedis = redisdbService.setAreaSpaceMap(areaSpace);
		boolean space = redisdbService.pushAreaSpace(areaSpace);//在加入队列
		return space && inRedis ? 1 : -1;
	}
	
	public int modifyDogLocationAreaSpace(AreaSpace areaSpace) {
//		AreaSpace areaSpaceMap = redisdbService.getAreaSpaceMap(areaSpace.getDoglocationId());//
//		areaSpaceMap.setAreaRange(areaSpace.getAreaRange());
//		areaSpaceMap.setUpdatetimes(new Date());
//		boolean flag = redisdbService.setAreaSpaceMap(areaSpaceMap);
		AreaSpaceExample example=new AreaSpaceExample();
		AreaSpaceExample.Criteria cc = example.createCriteria();
		cc.andDoglocationIdEqualTo(areaSpace.getDoglocationId());
		boolean space = redisdbService.pushAreaSpace(areaSpace);
		return space ? 1 : -1;
	}
	
	public boolean isAddOrUpdate(AreaSpace areaSpace) {
		//判断是否新增的
		AreaSpaceExample example=new AreaSpaceExample();
		AreaSpaceExample.Criteria cc = example.createCriteria();
		cc.andDoglocationIdEqualTo(areaSpace.getDoglocationId());
		List<AreaSpace> list = areaSpaceMapper.selectByExample(example);
		int si = list.size();
		if (si>0) {
			return false;
		}
		return true;
	}
	public int insertData(AreaSpace areaSpace) {
		areaSpace.setAddtimes(new Date());
		String areaString = areaSpace.getAreaRange().toString();
		if (null == areaString || "".equals(areaString)) {
			DogLocation dogLocation = dogLocationMapper.selectByPrimaryKey(areaSpace.getDoglocationId());
			if (null==dogLocation) {
				return -1;
			}
			Point2D.Double point = new Point2D.Double(dogLocation
					.getLongitude().doubleValue(), dogLocation.getLatitude()
					.doubleValue());//以当前点为中心，
			HashMap map = MessageConsumerConfig.readConfig("config.properties");
			java.lang.Double d = java.lang.Double.valueOf(map.get("Range_KM").toString()) ;
			List<Double> list = MapTools.getCurrentCircle(point, d);//公里范围内的
			Polygon polygon = MapTools.getPolygonByListPoint(list);
			areaSpace.setAreaRange(polygon.toString()); //存入数据库
		}else if(areaString.contains("|") && !areaString.contains("(")){//判断是否后台修改数据
			areaSpace.setAreaRange(MapTools.returnPolygon(areaString).toString());
		}
		return areaSpaceMapper.insertSelective(areaSpace);
	}
	public int updateData(AreaSpace areaSpace) {
		AreaSpaceExample example=new AreaSpaceExample();
		AreaSpaceExample.Criteria cc = example.createCriteria();
		cc.andDoglocationIdEqualTo(areaSpace.getDoglocationId());
		String areaString = areaSpace.getAreaRange().toString();
		if (null ==areaString || "".equals(areaString)) {
			DogLocation dogLocation = dogLocationMapper.selectByPrimaryKey(areaSpace.getDoglocationId());
			if (null==dogLocation) {
				return -1;
			}
			Point2D.Double point = new Point2D.Double(dogLocation
					.getLongitude().doubleValue(), dogLocation.getLatitude()
					.doubleValue());//以当前点为中心，
			HashMap map = MessageConsumerConfig.readConfig("config.properties");
			java.lang.Double d = java.lang.Double.valueOf(map.get("Range_KM").toString()) ;
			List<Double> list = MapTools.getCurrentCircle(point, d);//公里范围内的
			Polygon polygon = MapTools.getPolygonByListPoint(list);
			areaSpace.setAreaRange(polygon.toString()); //存入数据库
		}else if(areaString.contains("|")&& !areaString.contains("(")){//判断是否后台修改数据
			areaSpace.setAreaRange(MapTools.returnPolygon(areaString).toString());
		}
		boolean b = redisdbService.setAreaSpaceMap(areaSpace);
		if(b){
			return areaSpaceMapper.updateByExampleSelective(areaSpace, example);
		}else
		{
			return -1;
		}
	}
}
