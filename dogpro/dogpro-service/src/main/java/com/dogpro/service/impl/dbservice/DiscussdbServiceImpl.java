package com.dogpro.service.impl.dbservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.dao.DiscussMapper;
import com.dogpro.dao.FriendCircleMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.Discuss;
import com.dogpro.domain.model.DiscussExample;
import com.dogpro.domain.model.User;
import com.dogpro.service.dbservice.DiscussdbService;
import com.dogpro.service.dbservice.FriendCircledbService;
import com.dogpro.service.dbservice.PushdbService;
import com.dogpro.service.dbservice.RedisdbService;
@Service("DiscussdbService")
public class DiscussdbServiceImpl implements DiscussdbService{
	@Autowired
	private DiscussMapper discussMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PushdbService pushdbService;
	@Autowired
	private FriendCircleMapper friendCircleMapper;
	@Autowired
	private RedisdbService	redisdbService;
	@Autowired
	private FriendCircledbService friendCircledbService;
	
	
	public List<Discuss> selectByExample(DiscussExample example) {
		// TODO Auto-generated method stub
		return discussMapper.selectByExample(example);
	}
	public int publishDisuss(Discuss discuss) {
		if(discuss.getPid()!=0&&discussMapper.selectByPrimaryKey(discuss.getPid())==null){
			return 0;
		}
		discuss.setAddtimes(new Date());
		discuss.setDiscusstime(new Date());
		discuss.setUpdatetimes(new Date());
		if(discussMapper.insertSelective(discuss)==1){
			String nickname = redisdbService.getUserNickname(discuss.getUserId());
			if(nickname==null){
				nickname = "有人";
			}
			//发布推送消息
			if(discuss.getPid()==0){
				//普通评论
				Long revUid = friendCircleMapper.selectByPrimaryKey(discuss.getFriendcirId()).getUserId();
				pushdbService.sendPushMsg(3, nickname+" 评论了:"+discuss.getDiscusscontent(), revUid,discuss.getUserId(),discuss.getFriendcirId());
			}else{
				//评论他人的评论
				Long revUid1 = discussMapper.selectByPrimaryKey(discuss.getPid()).getUserId();
				pushdbService.sendPushMsg(4, nickname+" 回复了:"+discuss.getDiscusscontent(), revUid1,discuss.getUserId(),discuss.getFriendcirId());
				Long revUid2 = friendCircleMapper.selectByPrimaryKey(discuss.getFriendcirId()).getUserId();
				if(!revUid2.equals(revUid1)){
				pushdbService.sendPushMsg(3, nickname+" 评论了:"+discuss.getDiscusscontent(), revUid2,discuss.getUserId(),discuss.getFriendcirId());
				}
			}
			return 1;
		}
		return 0;
		
	}
	//根据朋友圈id，userid，分页获取朋友圈评论
	public List<Map<String, Object>> allianceDetailDiscuss(Long friendCirId, Long userId, int pageNO,
			int pageSize) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> discussList = new ArrayList<Map<String,Object>>();
		PaginationUtil paginationUtil = new PaginationUtil(pageNO, pageSize);
		List<Map<String, Object>> lists = discussMapper.allianceDetailDiscuss(friendCirId, paginationUtil);
		for(Map<String, Object> disMap:lists){
			Map<String, Object> discussmodel = new HashMap<String, Object>();
			//发布时间
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M月d日 HH:mm");
			String discussTime = simpleDateFormat.format((Date)disMap.get("discussTime"));
			discussmodel.put("discussId", disMap.get("discuss_id"));
			discussmodel.put("nickname", disMap.get("nickname"));
			discussmodel.put("discussUserId", disMap.get("user_id"));
			discussmodel.put("content", disMap.get("discussContent"));
			discussmodel.put("discussHeadPicUrl", disMap.get("headPic"));
			discussmodel.put("discussTime", discussTime);
			discussmodel.put("isReply",disMap.get("isReply"));
			discussmodel.put("replyNickname",disMap.get("replyname")==null?"":disMap.get("replyname"));
			discussmodel.put("ReplyUserId",disMap.get("replyUserId")==null?0:disMap.get("replyUserId"));
			discussList.add(discussmodel);
		}
//		//评论列表
//		DiscussExample discussExample = new DiscussExample();
//		DiscussExample.Criteria dCriteria = discussExample.createCriteria();
//		dCriteria.andFriendcirIdEqualTo(friendCirId).andStateEqualTo(1);
//		//评论分页
//		discussExample.setPagination(paginationUtil);
//
//		//按时间排序
//		discussExample.setOrderByClause("discussTime desc");
//		List<Discuss> discusses = discussMapper.selectByExample(discussExample);
//		List<Map<String, Object>> discussList = new ArrayList<Map<String,Object>>();
//		for(Discuss discuss:discusses){
//			int isReply = 0;
//			String replyNickname = "";
//			Long ReplyUserId = 0l;
//			Map<String, Object> discussmodel = new HashMap<String, Object>();
//			User discussUser = userMapper.selectByPrimaryKey(discuss.getUserId());
//			//发布时间
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M月d日 HH:mm");
//			String discussTime = simpleDateFormat.format(discuss.getDiscusstime());
//			discussmodel.put("discussId", discuss.getDiscussId());
//			discussmodel.put("nickname", discussUser.getNickname());
//			discussmodel.put("discussUserId", discuss.getUserId());
//			discussmodel.put("content", discuss.getDiscusscontent());
//			discussmodel.put("discussHeadPicUrl", discussUser.getHeadpic());
//			discussmodel.put("discussTime", discussTime);
//			if(discuss.getPid()!=0){
//				Discuss replyDiscuss = discussMapper.selectByPrimaryKey(discuss.getPid());
//				User replyUser = userMapper.selectByPrimaryKey(replyDiscuss.getUserId());
//				isReply = 1;
//				replyNickname = replyUser.getNickname();
//				ReplyUserId = replyUser.getUserId();
//			}
//			discussmodel.put("isReply",isReply);
//			discussmodel.put("replyNickname",replyNickname);
//			discussmodel.put("ReplyUserId",ReplyUserId);
//			discussList.add(discussmodel);
//		}
		return discussList;
	}
	public Map<String, Object> deleteDisuss(Long userId, Long discussId) {
		int flag = 0;
		String msg = "删除失败";
		Map<String, Object> model = new HashMap<String, Object>();
		Discuss discuss = discussMapper.selectByPrimaryKey(discussId);
		if(discuss!=null&&discuss.getUserId().equals(userId)){
			discuss.setState(0);
			discuss.setUpdatetimes(new Date());
			discussMapper.updateByPrimaryKeySelective(discuss);
			friendCircledbService.setFCupdateCode(discuss.getFriendcirId(), new Date().getTime());
			flag = 1;
			msg = "删除成功";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}

}
