package com.imserver.service.dbservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.tool.SpringInit;
import com.dogpro.dao.DogLocationMapper;
import com.dogpro.dao.WalkingDogGroupMapper;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.DogLocationExample;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.domain.model.WalkingDogGroupExample;
import com.dogpro.lucene.index.DoglocationIndex;
import com.imserver.service.dbservice.IMGroupMemberdbService;
import com.imserver.service.dbservice.IMRedisdbService;
@Service("IMGroupMemberdbService")
public class IMGroupMemberdbServiceImpl implements IMGroupMemberdbService {
	@Autowired
	private IMRedisdbService imRedisdbService;
	@Autowired
	private DogLocationMapper dogLocationMapper;
	@Autowired
	private WalkingDogGroupMapper walkingDogGroupMapper;
	
	public void SearchGroupMember() {
		// TODO Auto-generated method stub
		//搜索所有有效地点
		DogLocationExample dogLocationExample = new DogLocationExample();
		DogLocationExample.Criteria dCriteria = dogLocationExample.createCriteria();
		dCriteria.andStateEqualTo(1);
		List<DogLocation> dogLocations = dogLocationMapper.selectByExample(dogLocationExample);
		for(DogLocation dogLocation:dogLocations){
			//设置遛狗地点 mqtt keys
			imRedisdbService.setGroup(dogLocation.getId(), dogLocation.getKeyss());
			//搜索 群组 成员
			WalkingDogGroupExample walkingDogGroupExample = new WalkingDogGroupExample();
			WalkingDogGroupExample.Criteria wCriteria = walkingDogGroupExample.createCriteria();
			wCriteria.andStateNotEqualTo(0).andDoglocationidEqualTo(dogLocation.getId());
			List<WalkingDogGroup> walkingDogGroups = walkingDogGroupMapper.selectByExample(walkingDogGroupExample);
			//reids 修改群组成员集合
			for(WalkingDogGroup walkingDogGroup:walkingDogGroups){
				imRedisdbService.groupSADDmember(dogLocation.getId(), walkingDogGroup.getUserid());
			}
		}
	}
	
	public void initGroupMember(){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SearchGroupMember();
			}
		});
		thread.start();
	}
}
