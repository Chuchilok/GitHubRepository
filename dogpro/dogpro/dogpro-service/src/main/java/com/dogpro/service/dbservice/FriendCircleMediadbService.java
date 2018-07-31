package com.dogpro.service.dbservice;

import java.util.List;

import com.dogpro.domain.model.FriendCircleMedia;
import com.dogpro.domain.model.FriendCircleMediaExample;

public interface FriendCircleMediadbService {
	public List<FriendCircleMedia> selectByExample(FriendCircleMediaExample example);
}
