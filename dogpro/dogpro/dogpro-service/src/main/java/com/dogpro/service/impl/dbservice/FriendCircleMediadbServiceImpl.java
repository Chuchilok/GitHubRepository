package com.dogpro.service.impl.dbservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.dao.FriendCircleMediaMapper;
import com.dogpro.domain.model.FriendCircleMedia;
import com.dogpro.domain.model.FriendCircleMediaExample;
import com.dogpro.service.dbservice.FriendCircleMediadbService;

@Service("FriendCircleMediadbService")
public class FriendCircleMediadbServiceImpl implements FriendCircleMediadbService{
	@Autowired
	private FriendCircleMediaMapper friendCircleMediaMapper;
	public List<FriendCircleMedia> selectByExample(
			FriendCircleMediaExample example) {
		// TODO Auto-generated method stub
		return friendCircleMediaMapper.selectByExample(example);
	}

}
