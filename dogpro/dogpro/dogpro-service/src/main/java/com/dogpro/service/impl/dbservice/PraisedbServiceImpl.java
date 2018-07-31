package com.dogpro.service.impl.dbservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.dao.FriendCircleMapper;
import com.dogpro.dao.PraiseMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.FriendCircle;
import com.dogpro.domain.model.Praise;
import com.dogpro.domain.model.PraiseExample;
import com.dogpro.domain.model.User;
import com.dogpro.service.dbservice.PraisedbService;

@Service("PraisedbService")
public class PraisedbServiceImpl implements PraisedbService {
	@Autowired
	private PraiseMapper praiseMapper;
	@Autowired
	private FriendCircleMapper friendCircleMapper;
	@Autowired
	private UserMapper userMapper;

	public List<Praise> selectByExample(PraiseExample example) {
		return praiseMapper.selectByExample(example);
	}

	public int clickPraise(Praise praise) {
		//在这里判断是否存在改朋友圈信息
		//如果不存在返回   -2
		//朋友圈的ID
		FriendCircle circle = friendCircleMapper.selectByPrimaryKey(praise
				.getFriendcirId());
		if (circle != null) {
			PraiseExample temp = new PraiseExample();
			PraiseExample.Criteria uCriteria = temp.createCriteria();
			uCriteria.andUserIdEqualTo(praise.getUserId());
			uCriteria.andFriendcirIdEqualTo(praise.getFriendcirId());
			List<Praise> list = praiseMapper.selectByExample(temp);
			praise.setAddtimes(new Date());
			praise.setUpdatetimes(new Date());
			praise.setPraisetime(new Date());
			if (list.size()>0 ) {
				//取消点赞或点赞
				praise.setPraiseId(list.get(0).getPraiseId());
				praise.setState(1);//-4点赞
				if (list.get(0).getState()==1) {
					praise.setState(0);//-5取消点赞
				}
				praiseMapper.updateByPrimaryKeySelective(praise);
				return praise.getState()==1?-4:-5;
			}
			praise.setState(1);
			return praiseMapper.insertSelective(praise);
		} else {
			return -2;
		}

	}

	// 单条朋友圈点赞情况
	public List<Map<String, Object>> friendCirclePraise(Long friendCircleId) {
		// 点赞列表
//		PraiseExample praiseExample = new PraiseExample();
//		PraiseExample.Criteria pCriteria = praiseExample.createCriteria();
//		pCriteria.andFriendcirIdEqualTo(friendCircleId).andStateEqualTo(1);
//		praiseExample.setOrderByClause("praiseTime asc");
//		List<Praise> praises = praiseMapper.selectByExample(praiseExample);
//		List<Map<String, Object>> praiseList = new ArrayList<Map<String, Object>>();
//		for (Praise praise : praises) {
//			Map<String, Object> praisemodel = new HashMap<String, Object>();
//			User praiseUser = userMapper.selectByPrimaryKey(praise.getUserId());
//			praisemodel.put("praiseHeadPicUrl", praiseUser.getHeadpic());
//			praisemodel.put("praiseUserId", praiseUser.getUserId());
//			praiseList.add(praisemodel);
//		}
//		return praiseList;
		
		List<Map<String, Object>> praiseList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> selectList = praiseMapper.friendCirclePraise(friendCircleId);
		for (Map<String, Object> select : selectList) {
			Map<String, Object> praisemodel = new HashMap<String, Object>();
			praisemodel.put("praiseHeadPicUrl", select.get("praiseHeadPicUrl"));
			praisemodel.put("praiseUserId", select.get("praiseUserId"));
			praiseList.add(praisemodel);
		}
		return praiseList;
	}
}
