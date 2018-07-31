package com.dogpro.service.dbservice;

import java.util.List;
import java.util.Map;

import com.dogpro.domain.model.Praise;
import com.dogpro.domain.model.PraiseExample;

public interface PraisedbService {
	 public List<Praise> selectByExample(PraiseExample example);

	 /**
	  * 朋友点赞
	  */
	public int clickPraise(Praise praise);
	//单条朋友圈点赞情况
	public List<Map<String, Object>> friendCirclePraise(Long friendCircleId); 
}
