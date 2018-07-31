package com.dogpro.service.dbservice;

import java.util.List;
import java.util.Map;

import com.dogpro.domain.model.Discuss;
import com.dogpro.domain.model.DiscussExample;

public interface DiscussdbService {
	public List<Discuss> selectByExample(DiscussExample example);

	/**
	 * 发布评论
	 * @param disuss
	 * @return
	 */
	public int publishDisuss(Discuss disuss);
	//根据朋友圈id，userid，分页获取朋友圈评论
	public List<Map<String, Object>> allianceDetailDiscuss(Long friendCirId,Long userId,int pageNO,int pageSize);
	//删除评论
	public Map<String, Object> deleteDisuss(Long userId,Long discussId);
}
