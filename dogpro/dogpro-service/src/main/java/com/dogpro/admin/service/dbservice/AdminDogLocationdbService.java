package com.dogpro.admin.service.dbservice;

import java.util.List;

import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.DogLocationExample;

/**
 * 后台 管理遛狗地点
 * @author Administrator
 *
 */
public interface AdminDogLocationdbService {
	/**
	 * 返回所有遛狗地点 分页 
	 * @param string 可通过模糊查询
	 * @param pageNO 0 当前页数 
	 * @param pageSize 5  每页多少条
	 * @return 遛狗地点集合
	 */
	List<DogLocation> locationList(String string, int pageNO, int pageSize);
	/**
	 * 新增遛狗地点
	 * @param dogLocation
	 * @return  1 修改成功， 0 修改失败   -1 出现错误
	 */
	DogLocation addLocation(DogLocation dogLocation);
	/**
	 * 修改遛狗地点   通过遛狗地点id 
	 * @param dogLocation
	 * @return  1 修改成功， 0 修改失败   -1 出现错误
	 */
	DogLocation updateLocation(DogLocation dogLocation);
	/**
	 * 删除遛狗地点   通过遛狗地点id
	 * @param locationId
	 * @return  1 删除成功， 0 删除失败   -1 出现错误
	 */
	Long deleteLocation(long locationId);
	/**
	 * 获取一个遛狗地点对象 遛狗id
	 * @param doglocationid
	 * @return
	 */
	DogLocation getDogLocationById(Long doglocationid);
	int getCountByExample(DogLocationExample example);
	
}
