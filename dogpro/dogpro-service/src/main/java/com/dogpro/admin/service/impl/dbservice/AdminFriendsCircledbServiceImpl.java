package com.dogpro.admin.service.impl.dbservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.admin.service.dbservice.AdminFriendsCircledbService;
import com.dogpro.dao.DiscussMapper;
import com.dogpro.dao.FriendCircleMapper;
import com.dogpro.dao.FriendCircleMediaMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.Discuss;
import com.dogpro.domain.model.DiscussExample;
import com.dogpro.domain.model.FriendCircle;
import com.dogpro.domain.model.FriendCircleExample;
import com.dogpro.domain.model.FriendCircleMedia;
import com.dogpro.domain.model.FriendCircleMediaExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;

/**
 * 里面有 allianceList 里面有userMapper 用到
 * 
 * @author Administrator
 *
 */
@Service("adminFriendsCircledbService")
public class AdminFriendsCircledbServiceImpl implements
		AdminFriendsCircledbService {

	@Autowired
	private FriendCircleMapper friendCircleMapper;
	@Autowired
	private DiscussMapper discussMapper;
	@Autowired
	private FriendCircleMediaMapper friendCircleMediaMapper;
	@Autowired
	private UserMapper userMapper;

	/**
	 *  所有朋友圈集合
	 */
	public List<FriendCircle> allianceList(String str, int pageNO, int pageSize) {
		FriendCircleExample example = new FriendCircleExample();
		PaginationUtil pagination = new PaginationUtil(pageNO, pageSize);
		example.setPagination(pagination);
		if (str != null && !str.equals("")) {
			UserExample userE = new UserExample();
			UserExample.Criteria cUser = userE.createCriteria();
			cUser.andPhoneEqualTo(str);
			List<User> lus = userMapper.selectByExample(userE);
			if (lus.size() > 0) {
				List<Long> lid = new ArrayList<Long>();
				for (User user : lus) {
					lid.add(user.getUserId());
				}
				FriendCircleExample.Criteria cU = example.createCriteria();
				cU.andUserIdIn(lid);
			}
		}
		return friendCircleMapper.selectByExampleWithBLOBs(example);
	}
	/**
	 * 通过 用户id   查找朋友圈
	 */
	public List<FriendCircle> allianceList(long userId, int pageNO, int pageSize) {
		FriendCircleExample example = new FriendCircleExample();
		PaginationUtil pagination = new PaginationUtil(pageNO, pageSize);
		example.setPagination(pagination);
		if (userId != 0) {
			FriendCircleExample.Criteria cU = example.createCriteria();
			cU.andUserIdEqualTo(userId);
		}
		return friendCircleMapper.selectByExampleWithBLOBs(example);
	}

	/**
	 * 通过朋友圈id查询出来媒体资源
	 */
	public List<FriendCircleMedia> getFriendCirsMediaByFriendCirId(Long friendcirId) {
		FriendCircleMediaExample example = new FriendCircleMediaExample();
		FriendCircleMediaExample.Criteria cExample = example.createCriteria();
		cExample.andFriendcirIdEqualTo(friendcirId);
		return friendCircleMediaMapper.selectByExample(example);
	}
	/**
	 * 删除朋友圈  通过 朋友圈id
 		friendCirId 朋友圈id
		Returns:
		1 删除成功 0 删除失败 -1 未知错误，请联系管理员
	 */
	public int deleteAllianceByAdmin(long friendCirId) {
		try {
			FriendCircleMediaExample example = new FriendCircleMediaExample();
			//record.setState(0);//暂时更改为0
			//等待FriendCirCleMapper的更新 state
			//先删除媒体资源
			FriendCircleMediaExample.Criteria cc = example.createCriteria();
			cc.andFriendcirIdEqualTo(friendCirId);
			friendCircleMediaMapper.deleteByExample(example);
			return friendCircleMapper.deleteByPrimaryKey(friendCirId);//直接删除
			//return friendCircleMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			return -1;
		}
	}
	
	public int getCountByExample(FriendCircleExample example) {
		return friendCircleMapper.countByExample(example);
	}
	public List<FriendCircle> searchFriendCir(FriendCircleExample example,
			int pageNO, int pageSize) {
		PaginationUtil pagination = new PaginationUtil(pageNO, pageSize);
		example.setPagination(pagination);
		return friendCircleMapper.selectByExampleWithBLOBs(example);
	}
	public List<FriendCircleMedia> FriendMediaDetail(
			FriendCircleMediaExample example, int pageNO, int pageSize) {
		PaginationUtil pagination = new PaginationUtil(pageNO, pageSize);
		example.setPagination(pagination);
		return friendCircleMediaMapper.selectByExample(example);
	}
	public List<Discuss> findDisussByFriendCir(DiscussExample example,
			int pageNO, int pageSize) {
		PaginationUtil pagination = new PaginationUtil(pageNO,pageSize);
		example.setPagination(pagination);
		return discussMapper.selectByExample(example);
	}
	public int countDiscussByFriendCir(DiscussExample example) {
		return discussMapper.countByExample(example);
	}
	public FriendCircle getFriendCirById(long friendCirId) {
		return friendCircleMapper.selectByPrimaryKey(friendCirId);
	}
	public Discuss getDiscussById(Long id) {
		return discussMapper.selectByPrimaryKey(id);
	}
}
