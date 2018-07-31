package com.dogpro.service.impl.dbservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.dao.FriendCircleMapper;
import com.dogpro.dao.FriendCircleMediaMapper;
import com.dogpro.dao.FriendsMapper;
import com.dogpro.dao.FriendsNoteMapper;
import com.dogpro.dao.ImpressionMapper;
import com.dogpro.dao.MessageMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.FriendCircle;
import com.dogpro.domain.model.FriendCircleExample;
import com.dogpro.domain.model.FriendCircleMedia;
import com.dogpro.domain.model.FriendCircleMediaExample;
import com.dogpro.domain.model.Friends;
import com.dogpro.domain.model.FriendsExample;
import com.dogpro.domain.model.FriendsNote;
import com.dogpro.domain.model.FriendsNoteExample;
import com.dogpro.domain.model.Impression;
import com.dogpro.domain.model.ImpressionExample;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.MessageExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;
import com.dogpro.domain.model.extend.FriendCircleExtend;
import com.dogpro.service.dbservice.FriendsdbService;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.ServiceRecorddbService;

/**
 * 朋友接口(IM)
 * 
 * @author Administrator
 *
 */
@Service("FriendsdbService")
public class FriendsdbServiceImpl implements FriendsdbService {

	@Autowired
	private FriendsMapper friendsMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private FriendsNoteMapper friendsNoteMapper;
	@Autowired
	private FriendCircleMapper friendCircleMapper;
	@Autowired
	private FriendCircleMediaMapper friendCircleMediaMapper;
	@Autowired
	private ImpressionMapper impressionMapper;
	@Autowired
	private ServiceRecorddbService serviceRecorddbService;
	@Autowired
	private RedisdbService redisdbService;

	public List<User> searchFriends(long userId, String keyword) {
		UserExample userExample = new UserExample();
		UserExample.Criteria cUser = userExample.createCriteria();
		cUser.andPhoneEqualTo(keyword).andStateEqualTo(1)
				.andIscompletedEqualTo(1);
		// cUser.andUserIdNotIn(strs);//本是好友不显示
		List<User> list = userMapper.selectByExample(userExample);
		return list;
	}

//	public List<Map<String, Object>> friendListByUserId(long userId) {
//		List<Friends> list = friendsMapper.selectByFriends(userId);
//		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//		// 客服账号
//		User serviceUser = serviceRecorddbService.getServiceUser();
//		if (serviceUser != null && serviceUser.getUserId() == userId) {
//			// 该账号为客服
//			// 获取7天内的有客服记录 的 用户
//			Date afterTime = new Date(new Date().getTime() - 7 * 24 * 60 * 60
//					* 1000);
//			List<User> users = serviceRecorddbService
//					.getUserListAfterTime(afterTime);
//			for (User user : users) {
//				Map<String, Object> model = new HashMap<String, Object>();
//				model.put("headpic",
//						user.getHeadpic() == null ? "" : user.getHeadpic());
//				model.put("nickname",
//						user.getNickname() == null ? "" : user.getNickname());
//				model.put("userId",
//						user.getUserId() == null ? "" : user.getUserId());
//				result.add(model);
//			}
//		} else {
//			// 加上客服
//			if (serviceUser != null) {
//				Map<String, Object> model = new HashMap<String, Object>();
//				model.put("headpic", serviceUser.getHeadpic() == null ? ""
//						: serviceUser.getHeadpic());
//				model.put("nickname", serviceUser.getNickname() == null ? ""
//						: serviceUser.getNickname());
//				model.put("userId", serviceUser.getUserId() == null ? ""
//						: serviceUser.getUserId());
//				result.add(model);
//			}
//			// 好友列表
//			for (Friends friend : list) {
//				Map<String, Object> model = new HashMap<String, Object>();
//				User user = userMapper.selectByPrimaryKey(friend
//						.getFriendUserId());
//				model.put("headpic",
//						user.getHeadpic() == null ? "" : user.getHeadpic());
//				model.put("nickname",
//						user.getNickname() == null ? "" : user.getNickname());
//				model.put("userId",
//						user.getUserId() == null ? "" : user.getUserId());
//				FriendsNoteExample friendsNoteExample = new FriendsNoteExample();
//				FriendsNoteExample.Criteria fCriteria = friendsNoteExample
//						.createCriteria();
//				fCriteria.andUseridEqualTo(userId);
//				fCriteria.andFriendidEqualTo(friend.getFriendUserId());
//				fCriteria.andStateEqualTo(1);
//				List<FriendsNote> friendsNotes = friendsNoteMapper
//						.selectByExample(friendsNoteExample);
//				if (!friendsNotes.isEmpty()) {
//					model.put("nickname", friendsNotes.get(0).getNotes());
//				}
//				result.add(model);
//			}
//		}
//
//		return result;
//	}
	public List<Map<String, Object>> friendListByUserId(long userId) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 客服账号
		User serviceUser = serviceRecorddbService.getServiceUser();
		if (serviceUser != null && serviceUser.getUserId() == userId) {
			// 该账号为客服
			// 获取7天内的有客服记录 的 用户
			Date afterTime = new Date(new Date().getTime() - 7 * 24 * 60 * 60
					* 1000);
			List<User> users = serviceRecorddbService
					.getUserListAfterTime(afterTime);
			for (User user : users) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("headpic",
						user.getHeadpic() == null ? "" : user.getHeadpic());
				model.put("nickname",
						user.getNickname() == null ? "" : user.getNickname());
				model.put("userId",
						user.getUserId() == null ? "" : user.getUserId());
				result.add(model);
			}
		} else {
			// 加上客服
			if (serviceUser != null) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("headpic", serviceUser.getHeadpic() == null ? ""
						: serviceUser.getHeadpic());
				model.put("nickname", serviceUser.getNickname() == null ? ""
						: serviceUser.getNickname());
				model.put("userId", serviceUser.getUserId() == null ? ""
						: serviceUser.getUserId());
				result.add(model);
			}
			// 好友列表
			List<Map<String, Object>> friendsNoteList = friendsMapper.selectByFriends(userId);
			for (Map<String, Object> friend : friendsNoteList) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("headpic",
						friend.get("headpic") == null ? "" : friend.get("headpic"));
				model.put("nickname",
						friend.get("nickname") == null ? "" : friend.get("nickname"));
				model.put("userId",
						friend.get("userId") == null ? "" : friend.get("userId"));
				if(friend.get("friendsNote")!=null){
					model.put("nickname", friend.get("friendsNote"));
				}
				result.add(model);
			}
		}

		return result;
	}

	/**
	 * 删除好友
	 */
	public int delFriendByUserId(long userId, long friendUserId) {
		Friends friends = friendsMapper.selectByUidFid(userId, friendUserId);
		if (friends == null) {
			return 0;
		}
		friends.setFriendState(0);
		friends.setUpdatetimes(new Date());
		return friendsMapper.updateByPrimaryKeySelective(friends);
	}

	/**
	 * 发送请求好友
	 * 
	 * 
	 */
	public int requestAddFriend(Message message, int isOpen) {
		Date date = new Date(message.getMsgcode());
		message.setUpdatetimes(date);
		message.setAddtimes(date);
		message.setSendtimes(date);

		// 同时添加Friends数据
		Friends friends = new Friends();
		friends.setUserId(message.getSenduserid());
		friends.setFriendUserId(message.getAcceptuserid());
		friends.setIsRequest(1);
		friends.setRequestTime(date);
		friends.setFriendComment(message.getContent());
		friends.setLongitude(message.getSendlongitude());
		friends.setLatitude(message.getSendlatitude());
		friends.setIsOpenfriendCirToFriend(isOpen);
		friends.setAddtimes(date);
		friends.setUpdatetimes(date);
		// 正在请求好友 2
		friends.setFriendState(2);
		friends.setState(1);
		FriendsExample uE = new FriendsExample();
		FriendsExample.Criteria cc = uE.createCriteria();
		cc.andUserIdEqualTo(friends.getUserId());
		cc.andFriendUserIdEqualTo(friends.getFriendUserId());
		cc.andStateEqualTo(1);
		// 同意时添加
		List<Friends> lfs = friendsMapper.selectByExample(uE);
		if (!lfs.isEmpty()) {
			Friends friendsdata = lfs.get(0);
			// 好友状态
			int friendState = friendsdata.getFriendState();
			if (friendState == 2) {
				// 正在请求
				// 修改 message表 修改friends表
				friends.setId(friendsdata.getId());
				friends.setAddtimes(friendsdata.getAddtimes());
				friendsMapper.updateByPrimaryKeySelective(friends);
				messageMapper.insertSelective(message);
				return 1;
			} else if (friendState == 1) {
				// 已经是好友
				// 判断对方是否已删除本人
				FriendsExample fExample = new FriendsExample();
				FriendsExample.Criteria fCriteria = fExample.createCriteria();
				fCriteria.andUserIdEqualTo(message.getAcceptuserid());
				fCriteria.andFriendUserIdEqualTo(message.getSenduserid());
				// 好友状态为1 表示 好友关系
				fCriteria.andFriendStateEqualTo(1);
				List<Friends> fList = friendsMapper.selectByExample(fExample);
				if (!fList.isEmpty()) {
					// 双方都是好友关系
					return -4;
				} else {
					// 对方删了你
					friends.setId(friendsdata.getId());
					friends.setAddtimes(friendsdata.getAddtimes());
					friendsMapper.updateByPrimaryKeySelective(friends);
					messageMapper.insertSelective(message);
					return 1;
				}
			} else if (friendState == 0) {
				// 单方面删除好友
				// 对方删了你
				friends.setId(friendsdata.getId());
				friends.setAddtimes(friendsdata.getAddtimes());
				friendsMapper.updateByPrimaryKeySelective(friends);
				messageMapper.insertSelective(message);
				return 1;
			} else {
				return 0;
			}

		} else {
			// 未有好友添加 记录
			friendsMapper.insertSelective(friends);
			messageMapper.insertSelective(message);
			return 1;
		}
	}

	/**
	 * 增加好友验证
	 */
	public int addFriendValidation(Message message) {
		Date date = new Date(message.getMsgcode());
		message.setAddtimes(date);
		message.setUpdatetimes(date);
		message.setSendtimes(date);

		// 修改对方 好友记录
		FriendsExample fExample = new FriendsExample();
		FriendsExample.Criteria fCriteria = fExample.createCriteria();
		fCriteria.andUserIdEqualTo(message.getAcceptuserid());
		fCriteria.andFriendUserIdEqualTo(message.getSenduserid());
		fCriteria.andStateEqualTo(1);
		List<Friends> friendslist = friendsMapper.selectByExample(fExample);
		if (!friendslist.isEmpty()) {
			Friends requestFriends = friendslist.get(0);
			if (requestFriends.getFriendState() != 2) {
				// 对方不是正在请求 添加好友
				return 0;
			}
			requestFriends.setFriendState(1);
			requestFriends.setUpdatetimes(date);
			friendsMapper.updateByPrimaryKeySelective(requestFriends);
		} else {
			return 0;
		}
		// 修改个人 好友记录
		FriendsExample fExample2 = new FriendsExample();
		FriendsExample.Criteria fCriteria2 = fExample2.createCriteria();
		fCriteria2.andUserIdEqualTo(message.getSenduserid());
		fCriteria2.andFriendUserIdEqualTo(message.getAcceptuserid());
		fCriteria2.andStateEqualTo(1);
		List<Friends> friendslist2 = friendsMapper.selectByExample(fExample2);
		if (!friendslist2.isEmpty()) {
			Friends requestFriends = friendslist2.get(0);
			requestFriends.setFriendState(1);
			requestFriends.setUpdatetimes(date);
			friendsMapper.updateByPrimaryKeySelective(requestFriends);
		} else {
			// 添加新数据
			Friends friends = new Friends();
			friends.setUserId(message.getSenduserid());
			friends.setFriendUserId(message.getAcceptuserid());
			friends.setIsRequest(0);
			friends.setRequestTime(date);
			friends.setFriendComment("");
			friends.setLongitude(message.getAcceptlongitude());
			friends.setLatitude(message.getAcceptlatitude());
			// 默认 开启 朋友圈
			friends.setIsOpenfriendCirToFriend(1);
			friends.setAddtimes(date);
			friends.setUpdatetimes(date);
			friends.setFriendState(1);
			friends.setState(1);
			friendsMapper.insertSelective(friends);

		}
		// 发送im信息 给B
		messageMapper.insertSelective(message);
		// 发送好友回复给B
		Map<String, Object> replyMap = new HashMap<String, Object>();
		String nickname = redisdbService.getUserNickname(message
				.getSenduserid());
		replyMap.put("nickname", nickname);
		replyMap.put("content", "我通过了你的朋友验证请求，现在我们可以开始聊天了");
		JSONObject replyMapObject = new JSONObject(replyMap);
		String content = message.getContent();
		message.setContent(replyMapObject.toJSONString());
		message.setType(2);
		messageMapper.insertSelective(message);
		// 发送im信息给A
		message.setContent(content);
		message.setType(3);
		Long userId = message.getSenduserid();
		Long friendUserId = message.getAcceptuserid();
		message.setSenduserid(friendUserId);
		message.setAcceptuserid(userId);
		return messageMapper.insertSelective(message);

	}

	public int selectUserIsFriend(Friends friends) {
		return friendsMapper.selectUserIsFriend(friends);
	}

//	/**
//	 * 好友主页详情
//	 * 
//	 * @param userid
//	 * @param friendsUserId
//	 * @return
//	 */
//	public Map<String, Object> friendHomeDetail(Long userid, Long friendsUserId) {
//		// TODO Auto-generated method stub
//		// 返回类型
//		Map<String, Object> model = new HashMap<String, Object>();
//		String nickname = "";
//		int sex = 0;
//		String headpic = "";
//		String sign = "";
//		String municipalities = "";
//		String districts = "";
//		int isFriend = 0;
//		User friend = userMapper.selectByPrimaryKey(friendsUserId);
//		if (friend.getState() == 1) {
//			nickname = friend.getNickname();
//			sex = friend.getSex();
//			headpic = friend.getHeadpic();
//			sign = friend.getSign();
//			municipalities = friend.getMunicipalities();
//			districts = friend.getDistricts();
//
//			// 判断是否是好友(分A添加B和B添加A两种情况)
//			FriendsExample friendsExample = new FriendsExample();
//			FriendsExample.Criteria fCriteria = friendsExample.createCriteria();
//			fCriteria.andUserIdEqualTo(userid)
//					.andFriendUserIdEqualTo(friendsUserId).andStateEqualTo(1);
//			List<Friends> fList1 = friendsMapper
//					.selectByExample(friendsExample);
//			fCriteria.andUserIdEqualTo(friendsUserId)
//					.andFriendUserIdEqualTo(userid).andStateEqualTo(1);
//			List<Friends> fList2 = friendsMapper
//					.selectByExample(friendsExample);
//			// 不是好友关系
//			if (fList1.isEmpty() && fList2.isEmpty()) {
//				isFriend = 0;
//			} else {
//				isFriend = 1;
//				// 检测是否有昵称
//				FriendsNoteExample friendsNoteExample = new FriendsNoteExample();
//				FriendsNoteExample.Criteria fCriteria2 = friendsNoteExample
//						.createCriteria();
//				fCriteria2.andUseridEqualTo(userid)
//						.andFriendidEqualTo(friendsUserId).andStateEqualTo(1);
//				List<FriendsNote> friendsNotes = friendsNoteMapper
//						.selectByExample(friendsNoteExample);
//				if (!friendsNotes.isEmpty()) {
//					nickname = friendsNotes.get(0).getNotes();
//				}
//			}
//
//		}
//		model.put("nickname", nickname);
//		model.put("sex", sex);
//		model.put("headpic", headpic);
//		model.put("sign", sign);
//		model.put("municipalities", municipalities);
//		model.put("districts", districts);
//		model.put("isFriend", isFriend);
//		return model;
//	}
	/**
	 * 好友主页详情
	 * 
	 * @param userid
	 * @param friendsUserId
	 * @return
	 */
	public Map<String, Object> friendHomeDetail(Long userid, Long friendsUserId) {
		// TODO Auto-generated method stub
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> friMap = friendsMapper.friendHomeDetail(userid, friendsUserId);
		model.put("nickname", friMap.get("nickname")!=null?friMap.get("nickname"):"");
		model.put("sex", friMap.get("sex")!=null?friMap.get("sex"):0);
		model.put("headpic", friMap.get("headpic")!=null?friMap.get("headpic"):"");
		model.put("sign", friMap.get("sign")!=null?friMap.get("sign"):"");
		model.put("municipalities", friMap.get("municipalities")!=null?friMap.get("municipalities"):"");
		model.put("districts", friMap.get("districts")!=null?friMap.get("districts"):"");
		model.put("isFriend", friMap.get("isFriend")!=null?friMap.get("isFriend"):0);
		if(friMap.get("fcNote")!=null){
			model.put("nickname",friMap.get("fcNote"));
		}
		return model;
	}
	/**
	 * 修改好友备注
	 * 
	 * @param userId
	 * @param friendUserId
	 * @param FriendNote
	 * @return
	 */
	public Map<String, Object> alterFriendNote(Long userId, Long friendUserId,
			String FriendNote) {
		// TODO Auto-generated method stub
		// 返回类型
		int flag = 0;
		String msg = "修改失败";
		Map<String, Object> model = new HashMap<String, Object>();
		// 判断是否是好友
		Friends friends = friendsMapper.selectByUidFid(userId, friendUserId);
		if (friends != null && friends.getFriendState() == 1) {
			// 将之前的备注信息state设为0
			FriendsNoteExample friendsNoteExample = new FriendsNoteExample();
			FriendsNoteExample.Criteria fCriteria2 = friendsNoteExample
					.createCriteria();
			fCriteria2.andUseridEqualTo(userId)
					.andFriendidEqualTo(friendUserId).andStateEqualTo(1);
			List<FriendsNote> friendsNotes = friendsNoteMapper
					.selectByExample(friendsNoteExample);
			FriendsNote friendsNote = new FriendsNote();
			friendsNote.setState(0);
			friendsNoteMapper.updateByExampleSelective(friendsNote,
					friendsNoteExample);
			// 添加新的备注信息
			friendsNote.setFriendid(friendUserId);
			friendsNote.setUserid(userId);
			friendsNote.setNotes(FriendNote);
			friendsNote.setState(1);
			Date currentTime = new Date();
			friendsNote.setAddtimes(currentTime);
			friendsNote.setUpdatetimes(currentTime);
			friendsNoteMapper.insertSelective(friendsNote);
			flag = 1;
			msg = "修改成功";
		} else {
			// 不是好友关系
			flag = 0;
			msg = "还不是好友关系";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}

	/**
	 * 好友主页-相册
	 * 
	 * @param userId
	 * @param friendUserId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> friendPhotoAlbum(Long userId,
			Long friendUserId, int pageNO, int pageSize) {
		// 返回类型
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		// 判断是否是好友
		Friends friends = friendsMapper.selectByUidFid(friendUserId, userId);
		if (friends != null && friends.getIsOpenfriendCirToFriend() == 0) {
			// 不对我开放朋友圈

		} else {
			// 返回类型
			PaginationUtil paginationUtil = new PaginationUtil(pageNO, pageSize);
			List<Map<String, Object>> friendCircleMedias = friendCircleMediaMapper
					.selectFriendCircleMediaByUid(friendUserId, paginationUtil);
			for (Map<String, Object> friendCircleMedia : friendCircleMedias) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("friendCircleId",
						friendCircleMedia.get("friendCircleId"));
				model.put("mediaType", friendCircleMedia.get("mediaType"));
				model.put("mediaSubUrl", friendCircleMedia.get("mediaSubUrl"));
				model.put("mediaUrl", friendCircleMedia.get("mediaUrl"));
				modelList.add(model);
			}
		}
		return modelList;
	}

	/**
	 * 好友主页-朋友圈
	 * 
	 * @param userId
	 * @param friendUserId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> friendFriendCircle(Long userId,
			Long friendUserId, int pageNO, int pageSize) {
		// 返回类型
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		// 判断是否是好友
		Friends friends = friendsMapper.selectByUidFid(friendUserId, userId);
		if (friends != null && friends.getIsOpenfriendCirToFriend() == 0) {
			// 不对我开放朋友圈

		} else {
			// 分页
			PaginationUtil paginationUtil = new PaginationUtil(pageNO, pageSize);
			List<FriendCircleExtend> friendCircleExtends = friendCircleMapper
					.mineFriendCircle(friendUserId, paginationUtil);
			// 查找对应图片
			for (FriendCircleExtend friendCircleExtend : friendCircleExtends) {
				Map<String, Object> model = new HashMap<String, Object>();
				// 返回数据
				Long friendCircleId = friendCircleExtend.getFriendcirId();
				int type = friendCircleExtend.getType();
				String content = friendCircleExtend.getContent();
				int mediaType = friendCircleExtend.getMediatype();
				// 时间输出格式
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M月d");
				String publishTime = simpleDateFormat.format(friendCircleExtend
						.getPublishtime());
				String mediaSubUrl = "";
				String mediaUrl = "";
				// 查找对应媒体图片
				List<FriendCircleMedia> friendCircleMedias = friendCircleExtend
						.getFriendCircleMedias();
				if (!friendCircleMedias.isEmpty()) {
					FriendCircleMedia friendCircleMedia = friendCircleMedias
							.get(0);
					if (friendCircleMedia.getMediaId() != null
							&& friendCircleMedia.getMediaId() != 0) {
						mediaSubUrl = friendCircleMedia.getMediasuburl();
						mediaUrl = friendCircleMedia.getMediaurl();
					}
				}
				model.put("friendCircleId", friendCircleId);
				model.put("type", type);
				model.put("content", content);
				model.put("mediaType", mediaType);
				model.put("publishTime", publishTime);
				model.put("mediaSubUrl", mediaSubUrl);
				model.put("mediaUrl", mediaUrl);
				modelList.add(model);
			}
		}
		return modelList;
	}

	// public List<Map<String, Object>> friendFriendCircle(Long userId,
	// Long friendUserId, int pageNO, int pageSize) {
	// // 返回类型
	// List<Map<String, Object>> modelList = new ArrayList<Map<String,
	// Object>>();
	// // 判断是否是好友
	// Friends friends = friendsMapper.selectByUidFid(friendUserId, userId);
	// if (friends != null && friends.getIsOpenfriendCirToFriend() == 0) {
	// // 不对我开放朋友圈
	//
	// } else {
	// // 查询该用户对用的朋友圈
	// FriendCircleExample friendCircleExample = new FriendCircleExample();
	// FriendCircleExample.Criteria fCriteria2 = friendCircleExample
	// .createCriteria();
	// fCriteria2.andUserIdEqualTo(friendUserId).andStateEqualTo(1);
	// // 分页
	// PaginationUtil paginationUtil = new PaginationUtil(pageNO, pageSize);
	// friendCircleExample.setPagination(paginationUtil);
	// // 按日期排序
	// friendCircleExample.setOrderByClause("publishTime desc");
	// List<FriendCircle> friendCircles = friendCircleMapper
	// .selectByExample(friendCircleExample);
	// // 查找对应图片
	// for (FriendCircle friendCircleTest : friendCircles) {
	// Map<String, Object> model = new HashMap<String, Object>();
	// // content为null 强制转一下
	// FriendCircle friendCircle = friendCircleMapper
	// .selectByPrimaryKey(friendCircleTest.getFriendcirId());
	// Long friendCircleId = friendCircle.getFriendcirId();
	// int type = friendCircle.getType();
	// String content = friendCircle.getContent();
	// int mediaType = friendCircle.getMediatype();
	// // 时间输出格式
	// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M月d");
	// String publishTime = simpleDateFormat.format(friendCircle
	// .getPublishtime());
	// String mediaSubUrl = "";
	// String mediaUrl = "";
	// // 查找对应媒体图片
	// FriendCircleMediaExample friendCircleMediaExample = new
	// FriendCircleMediaExample();
	// FriendCircleMediaExample.Criteria fCriteria3 = friendCircleMediaExample
	// .createCriteria();
	// fCriteria3.andFriendcirIdEqualTo(friendCircleId);
	// List<FriendCircleMedia> friendCircleMedias = friendCircleMediaMapper
	// .selectByExample(friendCircleMediaExample);
	// if (!friendCircleMedias.isEmpty()) {
	// FriendCircleMedia friendCircleMedia = friendCircleMedias
	// .get(0);
	// mediaSubUrl = friendCircleMedia.getMediasuburl();
	// mediaUrl = friendCircleMedia.getMediaurl();
	// }
	// model.put("friendCircleId", friendCircleId);
	// model.put("type", type);
	// model.put("content", content);
	// model.put("mediaType", mediaType);
	// model.put("publishTime", publishTime);
	// model.put("mediaSubUrl", mediaSubUrl);
	// model.put("mediaUrl", mediaUrl);
	// modelList.add(model);
	// }
	// }
	// return modelList;
	// }
	/**
	 * 好友主页-印象接口
	 * 
	 * @param userId
	 * @param friendUserId
	 * @return
	 */
	public List<Map<String, Object>> friendHomeImpression(Long userId,
			Long friendUserId, int pageNo, int pageSize) {
		// 返回类型
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		// 查询印象列表
		ImpressionExample impressionExample = new ImpressionExample();
		ImpressionExample.Criteria iCriteria = impressionExample
				.createCriteria();
		iCriteria.andFriendidEqualTo(friendUserId).andStateEqualTo(1);
		PaginationUtil paginationUtil = new PaginationUtil(pageNo, pageSize);
		impressionExample.setPagination(paginationUtil);
		List<Impression> impressions = impressionMapper
				.selectByExample(impressionExample);
		for (Impression impression : impressions) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("impressionId", impression.getImpressionId());
			model.put("content", impression.getContent() == null ? ""
					: impression.getContent());
			modelList.add(model);
		}
		return modelList;

	}

//	/**
//	 * 42. 新的好友请求列表
//	 */
//	public Map<String, Object> newFriendsList(Long userId) {
//		FriendsExample fExample = new FriendsExample();
//		FriendsExample.Criteria fCriteria = fExample.createCriteria();
//		// 请求 接受者 为 userId
//		fCriteria.andFriendUserIdEqualTo(userId);
//		// 15日内
//		Date date = new Date(new Date().getTime() - 15 * 24 * 60 * 60 * 1000);
//		fCriteria.andIsRequestEqualTo(1);
//		fCriteria.andRequestTimeGreaterThan(date);
//		// 记录 状态 为 1
//		fCriteria.andStateEqualTo(1);
//		// 排序
//		fExample.setOrderByClause("request_time desc");
//		// 返回参数
//		Map<String, Object> result = new HashMap<String, Object>();
//		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
//		List<Friends> friends = friendsMapper.selectByExample(fExample);
//		for (Friends friend : friends) {
//			Map<String, Object> model = new HashMap<String, Object>();
//			model.put("friendUserId", friend.getUserId());
//			model.put("content", friend.getFriendComment());
//			// 判断 是否已添加 好友
//			int isFriend = friend.getFriendState().compareTo(1) == 0 ? 1 : 0;
//			model.put("isFriend", isFriend);
//			User user = userMapper.selectByPrimaryKey(friend.getUserId());
//			model.put("headPic", user.getHeadpic());
//			// 若 已是好友并 已有备注 获取其备注 否则 获取其 昵称
//			model.put("nickname", user.getNickname());
//			if (isFriend == 1) {
//				FriendsNoteExample fNoteExample = new FriendsNoteExample();
//				FriendsNoteExample.Criteria fNoteCriteria = fNoteExample
//						.createCriteria();
//				fNoteCriteria.andUseridEqualTo(userId)
//						.andFriendidEqualTo(friend.getUserId())
//						.andStateEqualTo(1);
//				List<FriendsNote> friendsNotes = friendsNoteMapper
//						.selectByExample(fNoteExample);
//				if (!friendsNotes.isEmpty()) {
//					model.put("nickname", friendsNotes.get(0).getNotes());
//				}
//			}
//			modelList.add(model);
//		}
//		result.put("newFriendsList", modelList);
//		return result;
//	}
	
	/**
	 * 42. 新的好友请求列表
	 */
	public Map<String, Object> newFriendsList(Long userId) {
		// 15日内
		Date date = new Date(new Date().getTime() - 15 * 24 * 60 * 60 * 1000);
		// 返回参数
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> newfriendsList = friendsMapper.newFriendsList(userId, date);
			for (Map<String, Object> friend : newfriendsList) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("friendUserId", friend.get("friendUserId"));
				model.put("content", friend.get("content")!=null?friend.get("content"):"");
				// 判断 是否已添加 好友
				int isFriend =  Integer.valueOf(friend.get("isFriend").toString());
				model.put("isFriend",  friend.get("isFriend"));
				model.put("headPic", friend.get("headPic"));
				// 若 已是好友并 已有备注 获取其备注 否则 获取其 昵称
				model.put("nickname", friend.get("nickname"));
				if (isFriend == 1) {
					if (friend.get("fcNote")!=null) {
						model.put("nickname", friend.get("fcNote"));
					}
				}
				modelList.add(model);
			}
			result.put("newFriendsList", modelList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
