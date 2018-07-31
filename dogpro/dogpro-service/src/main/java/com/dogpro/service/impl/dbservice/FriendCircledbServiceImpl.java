package com.dogpro.service.impl.dbservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.common.tool.NETTools;
import com.dogpro.dao.DiscussMapper;
import com.dogpro.dao.FriendCircleMapper;
import com.dogpro.dao.FriendCircleMediaMapper;
import com.dogpro.dao.FriendsMapper;
import com.dogpro.dao.PraiseMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.Discuss;
import com.dogpro.domain.model.DiscussExample;
import com.dogpro.domain.model.FriendCircle;
import com.dogpro.domain.model.FriendCircleExample;
import com.dogpro.domain.model.FriendCircleMedia;
import com.dogpro.domain.model.FriendCircleMediaExample;
import com.dogpro.domain.model.PraiseExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.extend.DiscussExtend;
import com.dogpro.domain.model.extend.FriendCircleExtend;
import com.dogpro.service.dbservice.FriendCircledbService;
import com.dogpro.service.dbservice.PushdbService;
import com.dogpro.service.dbservice.RedisdbService;

@Service("FriendCircledbService")
public class FriendCircledbServiceImpl implements FriendCircledbService {

	@Autowired
	private FriendsMapper friendsMapper;
	@Autowired
	private FriendCircleMapper friendCircleMapper;
	@Autowired
	private FriendCircleMediaMapper friendCircleMediaMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PraiseMapper praiseMapper;
	@Autowired
	private DiscussMapper discussMapper;
	@Autowired
	private PushdbService pushdbService;
	@Autowired
	private RedisdbService redisdbService;

	public List<FriendCircle> selectByExample(FriendCircleExample example) {
		// TODO Auto-generated method stub
		return friendCircleMapper.selectByExample(example);
	}

	// 获取联盟列表+更新朋友圈id列表
	public Map<String, Object> getallianceList(Long userId, int pageSize,
			Long friendCirId, int isRefresh, Long topfriendCirCode,
			Long bottomfriendCirCode, Long requestCode) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Map<String, Object>> friendCirList = new ArrayList<Map<String, Object>>();
		List<Long> updatefriendCirList = new ArrayList<Long>();
		Long requestCode2 = 0l;
		if (friendCirId == 0) {
			// 获取联盟列表
			if (isRefresh == 1) {
				// 下拉刷新
				try {

					friendCirList = getFCList(userId, pageSize,
							topfriendCirCode, isRefresh);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// 上拉加载
				friendCirList = getFCList(userId, pageSize,
						bottomfriendCirCode, isRefresh);
			}
			// 更新朋友圈id列表
			updatefriendCirList = getUpdateFCid(topfriendCirCode,
					bottomfriendCirCode, requestCode);
			// 请求时间戳
			requestCode2 = new Date().getTime();
		} else {
			// 获取单条记录
			// Map<String, Object> friendCir = getSingelFC(userId, friendCirId);
			// friendCirList.add(friendCir);
			// 获取单条记录
			friendCirList = getFCList(userId, 1, friendCirId, -1);
		}
		// 打包返回类型
		model.put("friendCirList", friendCirList);
		model.put("updatefriendCirList", updatefriendCirList);
		model.put("requestCode", requestCode2.toString());
		return model;
	}

	// 获取更新朋友圈id列表
	public List<Long> getUpdateFCid(Long topfriendCirCode,
			Long bottomfriendCirCod, Long requestCode) {
		List<Long> fCidList = new ArrayList<Long>();
		// 搜索更新时间>requestCode 资源编码 在top bottom资源编码之间的 朋友圈
		FriendCircleExample fExample = new FriendCircleExample();
		FriendCircleExample.Criteria fCriteria = fExample.createCriteria();
		fCriteria.andStateEqualTo(1)
				.andFriendcircodeGreaterThanOrEqualTo(bottomfriendCirCod)
				.andFriendcircodeLessThanOrEqualTo(topfriendCirCode)
				.andUpdatecodeGreaterThan(requestCode);
		List<FriendCircle> friendCircles = friendCircleMapper
				.selectByExample(fExample);
		for (FriendCircle friendCircle : friendCircles) {
			fCidList.add(friendCircle.getFriendcirId());
		}
		return fCidList;
	}

	// 获取首页单条朋友圈信息
	public Map<String, Object> getSingelFC(Long userId, Long friendCirId) {
		Map<String, Object> model = new HashMap<String, Object>();
		// 更改
		FriendCircle friendCircle = friendCircleMapper
				.selectByPrimaryKey(friendCirId);
		// 朋友圈发布者user
		User FCuser = userMapper.selectByPrimaryKey(friendCircle.getUserId());
		FriendCircleMediaExample fExample2 = new FriendCircleMediaExample();
		FriendCircleMediaExample.Criteria fCriteria2 = fExample2
				.createCriteria();
		fCriteria2.andFriendcirIdEqualTo(friendCircle.getFriendcirId());
		// 获取该条朋友圈的媒体信息
		List<FriendCircleMedia> friendCircleMedias = friendCircleMediaMapper
				.selectByExample(fExample2);
		List<Map<String, Object>> mediaList = new ArrayList<Map<String, Object>>();
		for (FriendCircleMedia friendCircleMedia : friendCircleMedias) {
			Map<String, Object> media = new HashMap<String, Object>();
			media.put("mediaURL", friendCircleMedia.getMediaurl());
			media.put("mediaSubURL", friendCircleMedia.getMediasuburl());
			mediaList.add(media);
		}
		PraiseExample pExample = new PraiseExample();
		PraiseExample.Criteria pCriteria = pExample.createCriteria();
		pCriteria.andFriendcirIdEqualTo(friendCircle.getFriendcirId())
				.andStateEqualTo(1);
		// 点赞人数
		int likeCount = praiseMapper.countByExample(pExample);
		// 该用户是否有点赞
		pCriteria.andUserIdEqualTo(userId);
		int islike = praiseMapper.countByExample(pExample);

		DiscussExample dExample = new DiscussExample();
		DiscussExample.Criteria dCriteria = dExample.createCriteria();
		dCriteria.andFriendcirIdEqualTo(friendCircle.getFriendcirId())
				.andStateEqualTo(1);
		// 按时间排序
		dExample.setOrderByClause("discussTime desc");
		// 评论列表
		List<Discuss> discusses = discussMapper.selectByExample(dExample);

		List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();
		// 获取前3条评论的信息
		for (int i = 0; i < discusses.size() && i < 3; i++) {
			int isReply = 0;
			String replyNickname = "";
			Long ReplyUserId = 0l;
			Map<String, Object> comment = new HashMap<String, Object>();
			Discuss discuss = discusses.get(i);
			User commentUser = userMapper.selectByPrimaryKey(discuss
					.getUserId());
			// 评论者id
			comment.put("commentUserId", commentUser.getUserId());
			// 评论者昵称
			comment.put("commentNickname", commentUser.getNickname());
			// 评论内容
			comment.put("commentContent", discusses.get(i).getDiscusscontent());
			// 判断是否回复别人的评论
			if (discuss.getPid() != 0) {
				Discuss replyDiscuss = discussMapper.selectByPrimaryKey(discuss
						.getPid());
				User replyUser = userMapper.selectByPrimaryKey(replyDiscuss
						.getUserId());
				isReply = 1;
				replyNickname = replyUser.getNickname();
				ReplyUserId = replyUser.getUserId();
			}
			comment.put("isReply", isReply);
			comment.put("replyNickname", replyNickname);
			comment.put("ReplyUserId", ReplyUserId);

			commentList.add(comment);
		}
		model.put("FCheadpicURL", FCuser.getHeadpic());
		model.put("FCnickname", FCuser.getNickname());
		model.put("content", friendCircle.getContent());
		model.put("type", friendCircle.getType());
		model.put("isLiked", islike);
		model.put("likeCount", likeCount);
		model.put("media", mediaList);
		model.put("comments", commentList);
		model.put("FCuserId", FCuser.getUserId());
		model.put("friendCirId", friendCircle.getFriendcirId());
		model.put("mediaType", friendCircle.getMediatype());
		model.put("friendCirCode", friendCircle.getFriendcircode().toString());
		return model;
	}

	// 获取首页单条朋友圈信息
	public Map<String, Object> getSingelFC(Long userId,
			FriendCircleExtend friendCircleExtend) {
		Map<String, Object> model = new HashMap<String, Object>();

		List<Map<String, Object>> mediaList = new ArrayList<Map<String, Object>>();
		for (FriendCircleMedia media : friendCircleExtend
				.getFriendCircleMedias()) {
			if (media.getMediaId() != null && media.getMediaId() != 0) {
				Map<String, Object> mediamodel = new HashMap<String, Object>();
				mediamodel.put("mediaURL", media.getMediaurl());
				mediamodel.put("mediaSubURL", media.getMediasuburl());
				mediaList.add(mediamodel);
			}
		}
		// 点赞人数
		int likeCount = friendCircleExtend.getPraiseTotal();
		// 该用户是否有点赞
		int islike = friendCircleExtend.getIsPraise();

		List<DiscussExtend> discusses = friendCircleExtend.getDiscussExtends();

		List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();
		// 获取前3条评论的信息
		for (int i = 0; i < discusses.size() && i < 3; i++) {
			Map<String, Object> comment = new HashMap<String, Object>();
			DiscussExtend discuss = discusses.get(i);
			if (discuss != null && discuss.getDiscussId() != null
					&& discuss.getDiscussId() != 0) {
				// 评论者id
				comment.put(
						"commentUserId",
						discuss.getDiscussUserId() != null ? discuss
								.getDiscussUserId() : 0);
				// 评论者昵称
				comment.put(
						"commentNickname",
						discuss.getDiscussNickname() != null ? discuss
								.getDiscussNickname() : "");
				// 评论内容
				comment.put(
						"commentContent",
						discuss.getDiscusscontent() != null ? discuss
								.getDiscusscontent() : "");
				comment.put("isReply", discuss.getIsReply());
				comment.put(
						"replyNickname",
						discuss.getReplyNickname() != null ? discuss
								.getReplyNickname() : "");
				comment.put(
						"ReplyUserId",
						discuss.getReplyUserId() != null ? discuss
								.getReplyUserId() : 0);
				commentList.add(comment);
			}

		}
		model.put("FCheadpicURL", friendCircleExtend.getUser().getHeadpic());
		model.put("FCnickname", friendCircleExtend.getUser().getNickname());
		model.put("content", friendCircleExtend.getContent());
		model.put("type", friendCircleExtend.getType());
		model.put("isLiked", islike);
		model.put("likeCount", likeCount);
		model.put("media", mediaList);
		model.put("comments", commentList);
		model.put("FCuserId", friendCircleExtend.getUserId());
		model.put("friendCirId", friendCircleExtend.getFriendcirId());
		model.put("mediaType", friendCircleExtend.getMediatype());
		model.put("friendCirCode", friendCircleExtend.getFriendcircode()
				.toString());
		return model;
	}

	// 联盟列表接口
	public List<Map<String, Object>> getFCList(Long userId, int pageSize,
			Long friendCirCode, int isRefresh) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		// 分页
		PaginationUtil paginationUtil = new PaginationUtil(0, pageSize);
		List<FriendCircleExtend> friendCircleExtends = new ArrayList<FriendCircleExtend>();
		friendCircleExtends = friendCircleMapper.selectFCextendByExample(
				friendCirCode, isRefresh, userId, paginationUtil);
		for (FriendCircleExtend friendCircleExtend : friendCircleExtends) {
			try {
				Map<String, Object> model = getSingelFC(userId,
						friendCircleExtend);
				modelList.add(model);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return modelList;
	}

	// 单条朋友圈详情
	public Map<String, Object> getFCdetail(Long friendCir_id, Long userId) {
		// TODO Auto-generated method stub
		// Map<String, Object> model = new HashMap<String, Object>();
		// // 查询对应朋友圈
		// FriendCircle friendCircle = friendCircleMapper
		// .selectByPrimaryKey(friendCir_id);
		// // 查询对应发表用户
		// User FCuser =
		// userMapper.selectByPrimaryKey(friendCircle.getUserId());
		// // 查询对应媒体内容
		// List<Map<String, Object>> mediaList = new ArrayList<Map<String,
		// Object>>();
		// FriendCircleMediaExample friendCircleMediaExample = new
		// FriendCircleMediaExample();
		// FriendCircleMediaExample.Criteria fCriteria =
		// friendCircleMediaExample
		// .createCriteria();
		// fCriteria.andFriendcirIdEqualTo(friendCir_id);
		// List<FriendCircleMedia> medias = friendCircleMediaMapper
		// .selectByExample(friendCircleMediaExample);
		// for (FriendCircleMedia media : medias) {
		// if(media.getMediaId()!=null&&media.getMediaId()!=0){
		// Map<String, Object> mediamodel = new HashMap<String, Object>();
		// mediamodel.put("Url", media.getMediaurl());
		// mediamodel.put("subUrl", media.getMediasuburl());
		// mediaList.add(mediamodel);
		// }
		// }
		// // 点赞列表
		// PraiseExample praiseExample = new PraiseExample();
		// PraiseExample.Criteria pCriteria = praiseExample.createCriteria();
		// pCriteria.andFriendcirIdEqualTo(friendCir_id).andStateEqualTo(1);
		// // 自己是否有点赞
		// pCriteria.andUserIdEqualTo(userId);
		// int isLiked = praiseMapper.selectByExample(praiseExample).isEmpty() ?
		// 0
		// : 1;
		// // 是否是求助信息
		// int isHelp = 0;
		// if (friendCircle.getType() == 2) {
		// isHelp = 1;
		// }
		// // 发布时间
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		// "yyyy年M月d日HH:mm");
		// String publishTime = simpleDateFormat.format(friendCircle
		// .getPublishtime());
		// // 是否是本人发布的
		// int isSelf = userId == friendCircle.getUserId() ? 1 : 0;
		// // 打包信息
		// model.put("headPicUrl", FCuser.getHeadpic());
		// model.put("nickname", FCuser.getNickname());
		// model.put("content", friendCircle.getContent());
		// model.put("media", mediaList);
		// // model.put("praiseList", praiseList);
		// model.put("isLiked", isLiked);
		// model.put("isHelp", isHelp);
		// model.put("FCuserId", FCuser.getUserId());
		// model.put("mediaType", friendCircle.getMediatype());
		// model.put("publishTime", publishTime);
		// model.put("isSelf", isSelf);
		// return model;
		//
		Map<String, Object> model = new HashMap<String, Object>();
		List<Map<String, Object>> mediaList = new ArrayList<Map<String, Object>>();
		FriendCircleExtend friendCircleExtend = friendCircleMapper.getFCdetail(
				friendCir_id, userId);
		for (FriendCircleMedia media : friendCircleExtend
				.getFriendCircleMedias()) {
			if (media.getMediaId() != null && media.getMediaId() != 0) {
				Map<String, Object> mediamodel = new HashMap<String, Object>();
				mediamodel.put("Url", media.getMediaurl());
				mediamodel.put("subUrl", media.getMediasuburl());
				mediaList.add(mediamodel);
			}
		}
		// 自己是否有点赞
		int isLiked = friendCircleExtend.getIsPraise();
		// 是否是求助信息
		int isHelp = 0;
		if (friendCircleExtend.getType() == 2) {
			isHelp = 1;
		}
		// 发布时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy年M月d日HH:mm");
		String publishTime = simpleDateFormat.format(friendCircleExtend
				.getPublishtime());
		// 是否是本人发布的
		int isSelf = userId == friendCircleExtend.getUserId() ? 1 : 0;
		// 打包信息
		model.put(
				"headPicUrl",
				friendCircleExtend.getUser().getHeadpic() != null ? friendCircleExtend
						.getUser().getHeadpic() : "");
		model.put(
				"nickname",
				friendCircleExtend.getUser().getNickname() != null ? friendCircleExtend
						.getUser().getNickname() : "");
		model.put("content", friendCircleExtend.getContent());
		model.put("media", mediaList);
		// model.put("praiseList", praiseList);
		model.put("isLiked", isLiked);
		model.put("isHelp", isHelp);
		model.put("FCuserId", friendCircleExtend.getUser().getUserId());
		model.put("mediaType", friendCircleExtend.getMediatype());
		model.put("publishTime", publishTime);
		model.put("isSelf", isSelf);
		return model;
	}

	// 发布朋友圈
	public int publishFC(FriendCircle friendCircle,
			List<Map<String, Object>> media, HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 判断用户
		User user = userMapper.selectByPrimaryKey(friendCircle.getUserId());
		if (user == null) {
			return 0;
		}
		if (friendCircle.getContent().trim().equals("") && media.isEmpty()) {
			// 朋友圈内容为空字符串 媒体文件为空
			return 0;
		}
		// 获取当前时间
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		// 设置发布时间，数据添加时间，数据更新时间
		friendCircle.setPublishtime(currentDate);
		friendCircle.setAddtimes(currentDate);
		friendCircle.setUpdatetimes(currentDate);
		friendCircle.setFriendcircode(currentDate.getTime());
		friendCircle.setUpdatecode(currentDate.getTime());
		friendCircle.setState(1);
		// 获取发布ip地址
		try {
			friendCircle.setPubliship(NETTools.getIpAddr(request));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 插入朋友圈数据
		if (friendCircleMapper.insertAndGetId(friendCircle) == 1) {
			try {
				// 插入朋友圈媒体数据
				for (Map<String, Object> mediamodel : media) {
					// 媒体图片、缩略图
					String mediaUrl = (String) mediamodel.get("mediaUrl");
					String mediaSubUrl = (String) mediamodel.get("mediaSubUrl");
					FriendCircleMedia friendCircleMedia = new FriendCircleMedia();
					friendCircleMedia.setFriendcirId(friendCircle
							.getFriendcirId());
					friendCircleMedia.setMediaurl(mediaUrl);
					friendCircleMedia.setMediasuburl(mediaSubUrl);
					friendCircleMedia.setAddtimes(currentDate);
					friendCircleMedia.setUpdatetimes(currentDate);
					friendCircleMediaMapper.insert(friendCircleMedia);
				}
			} catch (Exception e) {
				// TODO: handle exception
				return 0;
			}
			// 发布成功，发布推送信息
			String nickname = redisdbService.getUserNickname(friendCircle
					.getUserId());
			if (nickname == null) {
				nickname = "有人";
			}
			switch (friendCircle.getType()) {
			case 1:
				// 普通
				pushdbService
						.sendPushMsg(1, nickname + " 发布了1条朋友圈", 0l,
								friendCircle.getUserId(),
								friendCircle.getFriendcirId());
				break;
			case 2:
				// 求助
				pushdbService
						.sendPushMsg(2, nickname + " 发布了1条求助朋友圈", 0l,
								friendCircle.getUserId(),
								friendCircle.getFriendcirId());
				break;
			default:
				break;
			}
			return 1;
		}
		return 0;
	}

	// 我的详情
	public Map<String, Object> Mine(Long userId) {
		// TODO Auto-generated method stub
		// 查询对应user
		User user = userMapper.selectByPrimaryKey(userId);
		// 查询该用户对用的朋友圈
		FriendCircleExample friendCircleExample = new FriendCircleExample();
		FriendCircleExample.Criteria fCriteria = friendCircleExample
				.createCriteria();
		fCriteria.andUserIdEqualTo(userId).andStateEqualTo(1);
		int friendCircleCount = friendCircleMapper
				.countByExample(friendCircleExample);
		// 该用户的好友数(未实现)
		int friendCount = friendsMapper.countFriendsByUid(userId);
		// 该用户的相册数
		int albumCount = friendCircleMapper.countAlbumByUid(userId);
		// 打包数据
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("nickname", user.getNickname());
		model.put("headpic", user.getHeadpic());
		model.put("sign", user.getSign());
		model.put("sex", user.getSex());
		model.put("friendCount", friendCount);
		model.put("friendCircleCount", friendCircleCount);
		model.put("albumCount", albumCount);
		return model;

	}

	// 我的相册
	public List<Map<String, Object>> minePhotoAlbum(Long userId, int pageNO,
			int pageSize) {
		// 返回类型
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		PaginationUtil paginationUtil = new PaginationUtil(pageNO, pageSize);
		List<Map<String, Object>> friendCircleMedias = friendCircleMediaMapper
				.selectFriendCircleMediaByUid(userId, paginationUtil);
		for (Map<String, Object> friendCircleMedia : friendCircleMedias) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("friendCircleId", friendCircleMedia.get("friendCircleId"));
			model.put("mediaType", friendCircleMedia.get("mediaType"));
			model.put("mediaSubUrl", friendCircleMedia.get("mediaSubUrl"));
			model.put("mediaUrl", friendCircleMedia.get("mediaUrl"));
			modelList.add(model);
		}
		return modelList;
	}

	// // 我的朋友圈
	// public List<Map<String, Object>> mineFriendCircle(Long userId, int
	// pageNO,
	// int pageSize) {
	// // 查询该用户对用的朋友圈
	// FriendCircleExample friendCircleExample = new FriendCircleExample();
	// FriendCircleExample.Criteria fCriteria = friendCircleExample
	// .createCriteria();
	// fCriteria.andUserIdEqualTo(userId).andStateEqualTo(1);
	// // 分页
	// PaginationUtil paginationUtil = new PaginationUtil(pageNO, pageSize);
	// friendCircleExample.setPagination(paginationUtil);
	// // 按日期排序
	// friendCircleExample.setOrderByClause("publishTime desc");
	// List<FriendCircle> friendCircles = friendCircleMapper
	// .selectByExample(friendCircleExample);
	// // 返回类型
	// List<Map<String, Object>> modelList = new ArrayList<Map<String,
	// Object>>();
	// // 查找对应图片
	// for (FriendCircle friendCircleTest : friendCircles) {
	// Map<String, Object> model = new HashMap<String, Object>();
	// // 返回数据
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
	// FriendCircleMediaExample.Criteria fCriteria2 = friendCircleMediaExample
	// .createCriteria();
	// fCriteria2.andFriendcirIdEqualTo(friendCircleId);
	// List<FriendCircleMedia> friendCircleMedias = friendCircleMediaMapper
	// .selectByExample(friendCircleMediaExample);
	// if (!friendCircleMedias.isEmpty()) {
	// FriendCircleMedia friendCircleMedia = friendCircleMedias.get(0);
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
	// return modelList;
	// }

	// 我的朋友圈
	public List<Map<String, Object>> mineFriendCircle(Long userId, int pageNO,
			int pageSize) {
		// 返回类型
		List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
		// 分页
		PaginationUtil paginationUtil = new PaginationUtil(pageNO, pageSize);
		List<FriendCircleExtend> friendCircleExtends = friendCircleMapper
				.mineFriendCircle(userId, paginationUtil);
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
				FriendCircleMedia friendCircleMedia = friendCircleMedias.get(0);
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
		return modelList;
	}

	// 删除朋友圈
	public int delFriendCir(Long friendCirid) {
		if (friendCircleMapper.selectByPrimaryKey(friendCirid) != null) {
			FriendCircle record = new FriendCircle();
			record.setFriendcirId(friendCirid);
			record.setUpdatetimes(new Date());
			record.setState(0);// 修改状态为 0 删除
			Date date = new Date();
			setFCupdateCode(friendCirid, date.getTime());
			return friendCircleMapper.updateByPrimaryKeySelective(record);
		}
		return -2;
	}

	public String getFCfirstMedia(Long friendCircleId) {
		// TODO Auto-generated method stub
		String url = "";
		try {
			FriendCircle friendCircle = friendCircleMapper
					.selectByPrimaryKey(friendCircleId);
			FriendCircleMediaExample fExample = new FriendCircleMediaExample();
			FriendCircleMediaExample.Criteria fCriteria = fExample
					.createCriteria();
			fCriteria.andFriendcirIdEqualTo(friendCircleId);
			List<FriendCircleMedia> friendCircleMedias = friendCircleMediaMapper
					.selectByExample(fExample);
			if (!friendCircleMedias.isEmpty()) {
				url = friendCircleMedias.get(0).getMediasuburl();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return url;
	}

	public boolean setFCupdateCode(Long friendCirId, Long updateCode) {
		// TODO Auto-generated method stub
		FriendCircle friendCircle = friendCircleMapper
				.selectByPrimaryKey(friendCirId);
		friendCircle.setUpdatecode(updateCode);
		friendCircle.setUpdatetimes(new Date());
		if (friendCircleMapper.updateByPrimaryKeySelective(friendCircle) == 1) {
			return true;
		} else {
			return false;
		}
	}

}
