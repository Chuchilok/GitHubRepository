package com.dogpro.admin.service.dbservice;

import java.util.List;

import com.dogpro.domain.model.AreaSpace;
import com.vividsolutions.jts.io.ParseException;

public interface AdminAreaSpacedbService {
	/**
	 * 通过遛狗地点id 返回一个面的所有点 从reids查
	 * @param dogLocationId
	 * @return
	 */
	public List<Double[]> returnPolygonByDogLocationId(Long dogLocationId)throws ParseException;

	/**
	 * 添加一个遛狗地点面的信息 入redis
	 * @param areaSpace
	 * @return 1 -1 
	 */
	public int addDogLocationAreaSpace(AreaSpace areaSpace);
	/**
	 * 编辑一个遛狗地点面的信息 入redis
	 * @param areaSpace
	 * @return 1 -1 
	 */
	public int modifyDogLocationAreaSpace(AreaSpace areaSpace);
	/**
	 * 添加数据到数据库
	 * @return
	 */
	public int insertData(AreaSpace areaSpace);
	/**
	 * 判断
	 * @param areaSpace
	 * @return  true 为新增    false为修改
	 */
	public boolean isAddOrUpdate(AreaSpace areaSpace);

	public int updateData(AreaSpace areaSpace);
}
