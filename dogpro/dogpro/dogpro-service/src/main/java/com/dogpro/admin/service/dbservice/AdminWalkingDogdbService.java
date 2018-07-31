package com.dogpro.admin.service.dbservice;

import java.util.List;

import com.dogpro.domain.model.DogLocationExample;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.domain.model.WalkingDogGroupExample;
import com.dogpro.domain.model.WalkingDogTrack;
import com.dogpro.domain.model.WalkingDogTrackExample;



/**
 * 后台遛狗管理
 * @author Administrator
 *
 */
public interface AdminWalkingDogdbService {
	/**
	 * 查看所有遛狗组 分页
	 * @param str 可模糊查询||
	 * @param pageNO 当前页码
	 * @param pageSize 每页的数量
	 * @return 遛狗组列表
	 */
	List<WalkingDogGroup> walkingDogGroupList(String string, int pageNO,
			int pageSize);
	/**
	 * 根据遛狗地点id获取对应遛狗组的成员
	 * @param locationId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	List<WalkingDogGroup> getDogGroupByLocationId(long locationId, int pageNO,
			int pageSize);
	
	List<WalkingDogTrack> getTrack(String string, String string2, int pageNO, int pageSize);
	//轨迹详情计数
	int countTrackDetailByExample(WalkingDogTrackExample example);
	//遛狗组详细计数
	int countWalkingDogGroupDetailByExample(WalkingDogGroupExample example);
	int countWalkingDogGroupDetailByExampleGroup(WalkingDogGroupExample example);
	
	
	
}
