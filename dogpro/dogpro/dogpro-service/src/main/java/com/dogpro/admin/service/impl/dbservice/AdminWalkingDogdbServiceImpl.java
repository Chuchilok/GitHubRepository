package com.dogpro.admin.service.impl.dbservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.admin.service.dbservice.AdminWalkingDogdbService;
import com.dogpro.dao.WalkingDogGroupMapper;
import com.dogpro.dao.WalkingDogTrackMapper;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.domain.model.WalkingDogGroupExample;
import com.dogpro.domain.model.WalkingDogTrack;
import com.dogpro.domain.model.WalkingDogTrackExample;
@Service("adminWalkingDogdbService")
public class AdminWalkingDogdbServiceImpl implements AdminWalkingDogdbService {
	@Autowired
	private WalkingDogGroupMapper walkingDogGroupMapper;
	@Autowired
	private WalkingDogTrackMapper walkingDogTrackMapper;
	//查看所有遛狗组 分页
	public List<WalkingDogGroup> walkingDogGroupList(String str, int pageNO,
			int pageSize) {
		WalkingDogGroupExample example = new WalkingDogGroupExample();
		PaginationUtil pagination = new PaginationUtil(pageNO, pageSize);
		if (str!=null && !str.equals("")) {
			WalkingDogGroupExample.Criteria c = example.createCriteria();
			//模糊获取数据
		}
		example.setPagination(pagination);
		return walkingDogGroupMapper.selectToGroupByExample(example);
	}
	//遛狗地点对应的遛狗组信息  state=1 
	public List<WalkingDogGroup> getDogGroupByLocationId(long locationId,
			int pageNO, int pageSize) {
		WalkingDogGroupExample example = new WalkingDogGroupExample();
		PaginationUtil pagination = new PaginationUtil(pageNO,pageSize);
		example.setPagination(pagination );
		WalkingDogGroupExample.Criteria c = example.createCriteria();
		c.andDoglocationidEqualTo(locationId);
		c.andStateNotEqualTo(0);//数据要有效的
		return walkingDogGroupMapper.selectByExample(example );
	}
	/**
	 * 用户id，遛狗地点id(为空时，只查询)，加分页
	 */
	public List<WalkingDogTrack> getTrack(String userId, String groupId,
			int pageNO, int pageSize) {
		WalkingDogTrackExample example = new WalkingDogTrackExample();
		PaginationUtil pagination = new PaginationUtil(pageNO, pageSize);
		
		example.setPagination(pagination);
		WalkingDogTrackExample.Criteria cc =example.createCriteria();
		if (userId != null && !userId.equals("")){
			cc.andUseridEqualTo(Long.valueOf(userId));
		}
		if (groupId != null && !groupId.equals("")){
			cc.andGroupidEqualTo(Long.valueOf(groupId));//遛狗组记录id
		}
		if (userId!=null&& groupId!=null) {
			return walkingDogTrackMapper.selectByExample(example);
		}
		return walkingDogTrackMapper.selectUserByExample(example);
	}
	public int countTrackDetailByExample(WalkingDogTrackExample example) {
		return walkingDogTrackMapper.countByExample(example);
	}
	public int countWalkingDogGroupDetailByExample(
			WalkingDogGroupExample example) {
		return walkingDogGroupMapper.countByExample(example);
	}
	public int countWalkingDogGroupDetailByExampleGroup(
			WalkingDogGroupExample example) {
		return walkingDogGroupMapper.countByExampleGroup(example);
	}
}
