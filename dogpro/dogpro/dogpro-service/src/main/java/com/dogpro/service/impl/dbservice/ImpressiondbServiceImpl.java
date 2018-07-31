package com.dogpro.service.impl.dbservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.dao.ImpressionMapper;
import com.dogpro.domain.model.Impression;
import com.dogpro.domain.model.ImpressionExample;
import com.dogpro.service.dbservice.ImpressiondbService;
import com.dogpro.service.dbservice.PushdbService;
import com.dogpro.service.dbservice.RedisdbService;
@Service("ImpressiondbService")
public class ImpressiondbServiceImpl implements ImpressiondbService {

	@Autowired
	private ImpressionMapper impressionMapper;
	@Autowired
	private PushdbService pushdbService;
	@Autowired
	private RedisdbService redisdbService;
	
	public List allImpression(long userid,int pageNo,int pageSize) {
		ImpressionExample example = new ImpressionExample();
		ImpressionExample.Criteria uCriteria = example.createCriteria();
		//获取的是别人给的印象 
		uCriteria.andFriendidEqualTo(userid);
		uCriteria.andStateEqualTo(1);
		PaginationUtil paginationUtil = new PaginationUtil(pageNo,pageSize);
		example.setPagination(paginationUtil);
		return impressionMapper.selectByExample(example);
	}

	public int addImpression(Impression impression) {
		impression.setAddtimes(new Date());
		impression.setUpdatetimes(new Date());
		impression.setTime(new Date());
		if(!impression.getUserid().equals(impression.getFriendid())){
			//不能给自己添加印象
			//发布推送信息
			Long revUid = impression.getFriendid();
			String nickname = redisdbService.getUserNickname(impression.getUserid());
			if(nickname==null){
				nickname = "有人";
			}
			pushdbService.sendPushMsg(6, nickname+" 给你添加了一个印象", revUid,impression.getUserid(),revUid);
			return impressionMapper.insertSelective(impression);
		}else{
			return 0;
		}
		
	}

	public int deleteImpression(Long userId, Long impressionId) {
		Impression impression = impressionMapper.selectByPrimaryKey(impressionId);
		if(impression!=null){
			if(impression.getFriendid()==userId||impression.getUserid()==userId){
				impression.setState(0);
				impression.setUpdatetimes(new Date());
				return impressionMapper.updateByPrimaryKeySelective(impression);
			}
		}
		return 0;
	}

}
