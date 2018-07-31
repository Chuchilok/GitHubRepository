package com.dogpro.service.dbservice;

import java.util.List;

import com.dogpro.domain.model.Impression;

public interface ImpressiondbService {
	//返回对应印象
	public List allImpression(long userid,int pageNo,int pageSize);

	public int addImpression(Impression impression);
	
	//删除自己发的 或 别人给自己的印象
	public int deleteImpression(Long userId,Long impressionId);
}
