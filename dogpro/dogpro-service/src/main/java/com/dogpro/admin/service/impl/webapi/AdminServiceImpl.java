package com.dogpro.admin.service.impl.webapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.admin.service.dbservice.AdminAreaSpacedbService;
import com.dogpro.admin.service.dbservice.AdminComplaintdbService;
import com.dogpro.admin.service.dbservice.AdminDogLocationdbService;
import com.dogpro.admin.service.dbservice.AdminFeedbackService;
import com.dogpro.admin.service.dbservice.AdminFriendsCircledbService;
import com.dogpro.admin.service.dbservice.AdminMessagedbService;
import com.dogpro.admin.service.dbservice.AdminTokendbService;
import com.dogpro.admin.service.dbservice.AdminUserdbService;
import com.dogpro.admin.service.dbservice.AdminWalkingDogdbService;
import com.dogpro.admin.service.dbservice.OnlineRecorddbService;
import com.dogpro.admin.service.webapi.AdminService;
import com.dogpro.common.Interfacetool.DataGridResult;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.tool.MapTools;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.MyUtil;
import com.dogpro.common.tool.UUIDGenerator;
import com.dogpro.domain.model.AdminUser;
import com.dogpro.domain.model.AreaSpace;
import com.dogpro.domain.model.Complaint;
import com.dogpro.domain.model.ComplaintExample;
import com.dogpro.domain.model.Discuss;
import com.dogpro.domain.model.DiscussExample;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.DogLocationExample;
import com.dogpro.domain.model.Feedback;
import com.dogpro.domain.model.FeedbackExample;
import com.dogpro.domain.model.FriendCircle;
import com.dogpro.domain.model.FriendCircleExample;
import com.dogpro.domain.model.FriendCircleMedia;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.MessageExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.domain.model.WalkingDogGroupExample;
import com.dogpro.domain.model.WalkingDogTrack;
import com.dogpro.domain.model.WalkingDogTrackExample;

@SuppressWarnings("unused")
@Service("AdminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminUserdbService adminUserdbService;
	@Autowired
	private AdminFriendsCircledbService adminFriendsCircledbService;
	@Autowired
	private AdminComplaintdbService adminComplaintdbService;
	@Autowired
	private AdminFeedbackService adminFeedbackService;
	@Autowired
	private AdminDogLocationdbService adminDogLocationdbService;
	@Autowired
	private AdminWalkingDogdbService adminWalkingDogdbService;
	@Autowired
	private AdminMessagedbService adminMessagedbService;
	@Autowired
	private AdminAreaSpacedbService adminAreaSpacedbService;
	@Autowired
	private AdminTokendbService adminTokendbService;
	@Autowired
	private OnlineRecorddbService onlineRecorddbService;

	// 参数配置文件
	private Map packagesMap = MessageConsumerConfig
			.readConfig("config.properties");
	// 遛狗地点初始化 默认参数 头像
	private String default_groupPic = packagesMap.get("default_groupPic")
			.toString().trim();

	// 46.管理用户获取注册验证码（暂时也不用）
	public ResultObject requestCaptcha(ParameterObject parameterObject) {

		ResultObject retObj = new ResultObject();// 返回对象
		try {
			int type = parameterObject.getIntegerParameter("type");
			String phone = parameterObject.getStringParameter("phone");
			// 通过手机号码，和类型返回验证码和提示消息
			Map<String, Object> map = adminUserdbService.requestCaptcha(phone,
					type);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(map);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 47.管理用户注册 (暂时没有这)
	public ResultObject register(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 48.管理用户登陆
	public ResultObject adminLogin(ParameterObject parameterObject) {

		ResultObject retObj = new ResultObject();// 返回对象
		try {
			AdminUser adminUser = new AdminUser();
			String phone = parameterObject.getStringParameter("phone");
			String pwds = parameterObject.getStringParameter("pwds");
			adminUser.setPhone(phone);
			adminUser.setPwds(pwds);
			List<AdminUser> ladmins = adminUserdbService.adminLogin(adminUser);
			int res = 0;
			long adminUserId = 0;
			if (!ladmins.isEmpty()) {
				AdminUser u = ladmins.get(0);
				if(u.getPwds().equals(adminUser.getPwds())){
					res = 1;
					adminUserId = u.getAdminuserid();
				}
			} else
				res = -1;
			// 登陆 1 成功 0 失败 -1 未知错误，请找管理员 未处理的密码md5
			int flag = 0;
			String msg = "";
			Map<String, Object> model = new HashMap<String, Object>();
			if (res == 1) {
				// 登录成功之后，获取token，更新表中
				String token = adminTokendbService.updateToken(adminUserId);
				model.put("token", token);
				msg = "登陆成功";
				flag = 1;
			} else if (res == 0) {
				msg = "密码出错";
			} else {
				msg = "未知错误，请找管理员  ";
			}

			model.put("flag", flag);
			model.put("msg", msg);
			model.put("adminUserId", adminUserId);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public Map getAdminUserId(Long adminUserId) {
		Map token = adminTokendbService.getTokenByAdminUserId(adminUserId);
		return token;
	}

	// 49.查看用户列表接口（AdminUser查看User）(已)
	public DataGridResult userList(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		// 分页数据
		try {
			String token = parameterObject.getToken();
			Long adminUserId = parameterObject.getLongParameter("adminUserId");

			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			{
				int pageNO = parameterObject.getIntegerParameter("pageNO");
				int pageSize = parameterObject.getIntegerParameter("pageSize");
				List<User> userList = adminUserdbService.userList("", pageNO,
						pageSize);// 查询所有

				for (User user : userList) {
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("nickname", user.getNickname() == null ? ""
							: user.getNickname());
					model.put("phone", user.getPhone());
					model.put("state", user.getState());
					model.put("userId", user.getUserId());
					models.add(model);
				}
			}
			retObj.setTotal(adminUserdbService.totalCount(""));// 获取总数
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			// retObj.setFlag(1);// 标识是否可以访问
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
			// retObj.setFlag(1);
		}
		return retObj;
	}

	// 50.根据手机号码查询用户 (已)
	public DataGridResult searchUserByPhone(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			String phone = parameterObject.getStringParameter("phone");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<User> userList = adminUserdbService.userList(phone, pageNO,
					pageSize);// 查询所有
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			for (User user : userList) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("nickname", user.getNickname());
				model.put("phone", user.getPhone());
				model.put("state", user.getState());
				model.put("userId", user.getUserId());
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			UserExample example = new UserExample();
			UserExample.Criteria createCriteria = example.createCriteria();
			if (phone != null && !phone.equals("")) {
				createCriteria.andPhoneLike("%" + phone + "%");
				example.or(createCriteria);
				UserExample.Criteria createCriteria1 = example.createCriteria();
				createCriteria1.andNicknameLike("%" + phone + "%");//
				example.or(createCriteria1);
			}
			retObj.setTotal(adminUserdbService.countUserByExample(example));
			retObj.setRows(models);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 51.禁用/启用 用户 (已)
	public ResultObject disableUser(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			long userId = parameterObject.getLongParameter("userId");
			int state = parameterObject.getIntegerParameter("state");
			int res = adminUserdbService.disableUser(userId, state);
			int flag = 0;
			String msg = "";
			if (res == 1) {
				// 禁用成功
				flag = 1;
				msg = "禁用成功";
			} else if (res == 2) {
				// 启用成功
				flag = 1;
				msg = "启用成功";
			} else {
				flag = 0;
				msg = "操作失败";
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 52.查看所有朋友圈 分页 (已)
	public DataGridResult allianceList(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			List<FriendCircle> list = adminFriendsCircledbService.allianceList(
					"", pageNO, pageSize);
			// 返回参数可添加
			for (FriendCircle friendCircle : list) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("friendCirId", friendCircle.getFriendcirId());
				model.put("userId", friendCircle.getUserId());
				User u = adminUserdbService.getUserByuserId(friendCircle
						.getUserId());
				if (u != null) {
					model.put("userNickName", u.getNickname());// 这里要获取用户对象
				} else {
					model.put("userNickName", "");
				}
				model.put("publishTime",
						MyUtil.dateToString(friendCircle.getPublishtime()));
				model.put("content", friendCircle.getContent() == null ? ""
						: friendCircle.getContent());
				model.put("publishIp", friendCircle.getPubliship() == null ? ""
						: friendCircle.getPubliship());
				model.put("mediaType", friendCircle.getMediatype());
				model.put("type", friendCircle.getType());
				model.put("state", friendCircle.getState());
				// 朋友圈对应的媒体列表
				List<FriendCircleMedia> friendCirs = adminFriendsCircledbService
						.getFriendCirsMediaByFriendCirId(friendCircle
								.getFriendcirId());
				List<Map<String, Object>> mapMedias = new ArrayList<Map<String, Object>>();
				for (FriendCircleMedia friendCircleMedia : friendCirs) {
					Map<String, Object> mapMedia = new HashMap<String, Object>();
					mapMedia.put("mediaUrl",
							friendCircleMedia.getMediaurl() == null ? ""
									: friendCircleMedia.getMediaurl());
					mapMedia.put("mediaSubUrl",
							friendCircleMedia.getMediasuburl() == null ? ""
									: friendCircleMedia.getMediasuburl());
					mapMedias.add(mapMedia);
				}
				model.put("media", mapMedias);
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			FriendCircleExample example = new FriendCircleExample();// 模糊查询时用到
			retObj.setTotal(adminFriendsCircledbService
					.getCountByExample(example));
		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 53.关键词搜索朋友圈分页暂时按电话号码 分页(已)
	public DataGridResult searchAlliance(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			String phone = parameterObject.getStringParameter("phone");
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			List<FriendCircle> list = adminFriendsCircledbService.allianceList(
					phone, pageNO, pageSize);
			// 返回参数可添加
			for (FriendCircle friendCircle : list) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("friendCirId", friendCircle.getFriendcirId());
				model.put("userId", friendCircle.getUserId());
				model.put("publishTime", friendCircle.getPublishtime());
				model.put("content", friendCircle.getContent() == null ? ""
						: friendCircle.getContent());
				model.put("publishIp", friendCircle.getPubliship() == null ? ""
						: friendCircle.getPubliship());
				model.put("mediaType", friendCircle.getMediatype());
				model.put("type", friendCircle.getType());
				model.put("state", friendCircle.getState());
				// 朋友圈对应的媒体列表
				List<FriendCircleMedia> friendCirs = adminFriendsCircledbService
						.getFriendCirsMediaByFriendCirId(friendCircle
								.getFriendcirId());
				List<Map<String, Object>> mapMedias = new ArrayList<Map<String, Object>>();
				for (FriendCircleMedia friendCircleMedia : friendCirs) {
					Map<String, Object> mapMedia = new HashMap<String, Object>();
					mapMedia.put("mediaUrl",
							friendCircleMedia.getMediaurl() == null ? ""
									: friendCircleMedia.getMediaurl());
					mapMedia.put("mediaSubUrl",
							friendCircleMedia.getMediasuburl() == null ? ""
									: friendCircleMedia.getMediasuburl());
					mapMedias.add(mapMedia);
				}
				model.put("media", mapMedias);
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 54.查看用户朋友圈 分页 (已)
	public DataGridResult userAlliance(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			long userId = parameterObject.getLongParameter("userId");
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			List<FriendCircle> list = adminFriendsCircledbService.allianceList(
					userId, pageNO, pageSize);
			// 返回参数可添加
			for (FriendCircle friendCircle : list) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("friendCirId", friendCircle.getFriendcirId());
				model.put("userId", friendCircle.getUserId());
				model.put("publishTime", friendCircle.getPublishtime());
				model.put("content", friendCircle.getContent() == null ? ""
						: friendCircle.getContent());
				model.put("publishIp", friendCircle.getPubliship() == null ? ""
						: friendCircle.getPubliship());
				model.put("mediaType", friendCircle.getMediatype());
				model.put("type", friendCircle.getType());
				model.put("state", friendCircle.getState());
				// 朋友圈对应的媒体列表
				List<FriendCircleMedia> friendCirs = adminFriendsCircledbService
						.getFriendCirsMediaByFriendCirId(friendCircle
								.getFriendcirId());
				List<Map<String, Object>> mapMedias = new ArrayList<Map<String, Object>>();
				for (FriendCircleMedia friendCircleMedia : friendCirs) {
					Map<String, Object> mapMedia = new HashMap<String, Object>();
					mapMedia.put("mediaUrl",
							friendCircleMedia.getMediaurl() == null ? ""
									: friendCircleMedia.getMediaurl());
					mapMedia.put("mediaSubUrl",
							friendCircleMedia.getMediasuburl() == null ? ""
									: friendCircleMedia.getMediasuburl());
					mapMedias.add(mapMedia);
				}
				model.put("media", mapMedias);
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			FriendCircleExample example = new FriendCircleExample();
			FriendCircleExample.Criteria fCriteria = example.createCriteria();
			fCriteria.andUserIdEqualTo(userId);
			int total = adminFriendsCircledbService.getCountByExample(example);
			retObj.setTotal(total);
		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 67.朋友圈搜索接口 分页 (已)
	public DataGridResult searchFriendsCir(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			String content = parameterObject.getStringParameter("content");
			FriendCircleExample example = new FriendCircleExample();
			FriendCircleExample.Criteria cc = example.createCriteria();
			FriendCircleExample.Criteria cc1 = example.createCriteria();
			// 可能是
			cc.andContentLike("%" + content + "%");
			example.or(cc);
			List<Long> values = adminUserdbService
					.searchUserIdByNickName(content);
			if (values.size() > 0) {
				cc1.andUserIdIn(values);
				example.or(cc1);
			}
			// type 求助 1 普通 2
			if (content.equals("求助")) {
				FriendCircleExample.Criteria cc4 = example.createCriteria();
				cc4.andTypeEqualTo(2);
				example.or(cc4);
			} else if (content.equals("普通")) {
				FriendCircleExample.Criteria cc4 = example.createCriteria();
				cc4.andTypeEqualTo(1);
				example.or(cc4);
			}
			List<FriendCircle> list = adminFriendsCircledbService
					.searchFriendCir(example, pageNO, pageSize);
			for (FriendCircle friendCircle : list) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("friendCirId", friendCircle.getFriendcirId());
				model.put("userId", friendCircle.getUserId());
				User u = adminUserdbService.getUserByuserId(friendCircle
						.getUserId());
				if (u != null) {
					model.put("userNickName", u.getNickname());// 这里要获取用户对象
				} else {
					model.put("userNickName", "");
				}
				model.put("publishTime",
						MyUtil.dateToString(friendCircle.getPublishtime()));
				model.put("content", friendCircle.getContent() == null ? ""
						: friendCircle.getContent());
				model.put("publishIp", friendCircle.getPubliship() == null ? ""
						: friendCircle.getPubliship());
				model.put("mediaType", friendCircle.getMediatype());
				model.put("type", friendCircle.getType());
				model.put("state", friendCircle.getState());
				// 朋友圈对应的媒体列表
				List<FriendCircleMedia> friendCirs = adminFriendsCircledbService
						.getFriendCirsMediaByFriendCirId(friendCircle
								.getFriendcirId());
				List<Map<String, Object>> mapMedias = new ArrayList<Map<String, Object>>();
				for (FriendCircleMedia friendCircleMedia : friendCirs) {
					Map<String, Object> mapMedia = new HashMap<String, Object>();
					mapMedia.put("mediaUrl",
							friendCircleMedia.getMediaurl() == null ? ""
									: friendCircleMedia.getMediaurl());
					mapMedia.put("mediaSubUrl",
							friendCircleMedia.getMediasuburl() == null ? ""
									: friendCircleMedia.getMediasuburl());
					mapMedias.add(mapMedia);
				}
				model.put("media", mapMedias);
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			int total = adminFriendsCircledbService.getCountByExample(example);
			retObj.setTotal(total);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 55.删除朋友圈（AdminUser） (一半，差FriendCir的state)
	public ResultObject deleteAllianceByAdmin(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			long friendCirId = parameterObject.getLongParameter("friendCirId");
			// 等待中
			int res = adminFriendsCircledbService
					.deleteAllianceByAdmin(friendCirId);
			String msg = "";
			int flag = 0;
			if (res > 0) {
				flag = 1;
				msg = "删除成功";
			} else if (res == 0) {
				flag = 0;
				msg = "删除失败";
			} else {
				flag = 0;
				msg = "未知错误，请联系管理员";
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 56.查看所有投诉（按类型分） (已)
	public DataGridResult complaintByType(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			int type = parameterObject.getIntegerParameter("type");
			List<Complaint> lc = adminComplaintdbService.complaintByType(type,
					pageNO, pageSize);
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			for (Complaint complaint : lc) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("complaintId", complaint.getComplaintId());
				model.put("friendCirId", complaint.getFriendcirId());
				model.put("userId", complaint.getUserId());
				User u = adminUserdbService.getUserByuserId(complaint
						.getUserId());
				if (u != null) {
					model.put("userNickname", u.getNickname());// 这里要获取用户对象
				} else {
					model.put("userNickname", "");
				}
				model.put("complaintIp", complaint.getComplaintip());
				model.put("complaintContent", complaint.getComplaintcontent());
				model.put("check", complaint.getCheck());
				model.put("checkTime", "");
				model.put("checkUserId", complaint.getCheckuserid());
				if (complaint.getCheckuserid() != null) {
					AdminUser adminUser = adminUserdbService
							.findAdminUser(complaint.getCheckuserid());
					if (adminUser != null) {
						model.put("adminNickname", adminUser.getNickname());
						model.put("checkTime",
								MyUtil.dateToString(complaint.getChecktime()));
					} else {
						model.put("adminNickname", "");
					}
				}
				model.put("handleProcess", complaint.getHandleprocess());
				model.put("handleResult", complaint.getHandleresult());
				models.add(model);
			}

			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			ComplaintExample example = new ComplaintExample();
			ComplaintExample.Criteria cc = example.createCriteria();
			if (type != 0) {
				cc.andComplainttypeEqualTo(String.valueOf(type));
			}
			int total = adminComplaintdbService
					.countComplaintByExample(example);
			retObj.setTotal(total);

		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 57.核查投诉 (已)
	public ResultObject checkComplant(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			long adminUserId = parameterObject.getLongParameter("adminUserId");
			long complaintId = parameterObject.getLongParameter("complaintId");
			int check = parameterObject.getIntegerParameter("check");// 核查结果（属实1虚假2）
			String handleProcess = parameterObject
					.getStringParameter("handleProcess");
			String handleResult = parameterObject
					.getStringParameter("handleResult");
			Complaint complant = new Complaint();
			complant.setComplaintId(complaintId);
			complant.setCheckuserid(adminUserId);
			complant.setHandleprocess(handleProcess);
			complant.setHandleresult(handleResult);
			complant.setCheck(String.valueOf(check));
			complant.setChecktime(new Date());
			int res = adminComplaintdbService.checkComplant(complant);
			String msg = "";
			int flag = 0;
			if (res >= 1) {
				msg = "核查成功";
				flag = 1;
			} else if (res == 0) {
				msg = "核查失败";
			} else if (res == -1) {
				msg = "没这个投诉";
			} else {
				msg = "系统出错";
			}
			// 1 核查成功，0 核查失败 -1 没这个投诉 -2 系统出错
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 58.查看意见反馈 分页 (已)
	public DataGridResult feedbackList(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Feedback> lf = adminFeedbackService.feedbackList("", pageNO,
					pageSize);// 可支持模糊查询
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			for (Feedback feedback : lf) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("userId", feedback.getUserid());
				User u = adminUserdbService.getUserByuserId(feedback
						.getUserid());
				if (u != null) {
					model.put("nickname", u.getNickname());// 这里要获取用户对象
				} else {
					model.put("nickname", "");
				}
				model.put("feedbackId", feedback.getFeedbackid());
				model.put("content", feedback.getContent());
				model.put("addTimes",
						MyUtil.dateToString(feedback.getAddtimes()));
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			FeedbackExample example = new FeedbackExample();
			int total = adminFeedbackService.getCountByExample(example);
			retObj.setTotal(total);
		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 59.查看遛狗地点 分页 (已)
	public DataGridResult locationList(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<DogLocation> ld = adminDogLocationdbService.locationList("",
					pageNO, pageSize);
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			for (DogLocation dogLocation : ld) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("dogLocationId", dogLocation.getId());
				model.put("areaname", dogLocation.getAreaname());
				model.put("addressalias", dogLocation.getAddressalias());
				model.put("provinces", dogLocation.getProvinces());
				model.put("municipalities", dogLocation.getMunicipalities());
				model.put("districts", dogLocation.getDistricts());
				model.put("townstreet", dogLocation.getTownstreet());
				model.put("longitude", dogLocation.getLongitude());
				model.put("latitude", dogLocation.getLatitude());
				model.put("perimeter", dogLocation.getPerimeter());
				model.put("creatoruserid", dogLocation.getCreatoruserid());
				model.put("hot", dogLocation.getHot());
				model.put("orders", dogLocation.getOrders());
				model.put("state", dogLocation.getState());
				model.put("locationpic", dogLocation.getLocationpic());
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			DogLocationExample example = new DogLocationExample();
			int total = adminDogLocationdbService.getCountByExample(example);
			retObj.setTotal(total);
		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 新增遛狗地点接口（改）
	public ResultObject addLocation(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			JSONObject jsonObject = new JSONObject(
					parameterObject.getParameterObject());
			DogLocation dogLocation = JSONObject.toJavaObject(jsonObject,
					DogLocation.class);
			Date currentTime = new Date();
			// 管理员 创建的 地点
			dogLocation.setCreatoruserid(0l);
			dogLocation.setPid(0l);
			dogLocation.setState(1);
			dogLocation.setKeyss(UUIDGenerator.getToken64());
			String address = dogLocation.getProvinces()
					+ dogLocation.getMunicipalities()
					+ dogLocation.getDistricts() + dogLocation.getTownstreet();
			dogLocation.setAddress(address);
			if (dogLocation.getLocationpic() == null
					|| dogLocation.getLocationpic().equals("")) {
				dogLocation.setLocationpic(default_groupPic);
			}
			dogLocation.setAddtimes(currentTime);
			dogLocation.setUpdatetimes(currentTime);
			DogLocation DogLocation2 = adminDogLocationdbService
					.addLocation(dogLocation);// 修改遛狗地点
			// 1 修改成功， 0 修改失败 -1 出现错误
			String msg = "";
			int flag = 0;
			if (DogLocation2 != null) {
				flag = 1;
				msg = "添加成功";
			} else {
				flag = 0;
				msg = "添加失败";
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 60.修改遛狗地点接口 通过遛狗地点id 修改updateTimes (已)
	public ResultObject alterLocation(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			long dogLocationId = parameterObject
					.getLongParameter("dogLocationId");
			JSONObject jsonObject = new JSONObject(
					parameterObject.getParameterObject());
			DogLocation dogLocation = JSONObject.toJavaObject(jsonObject,
					DogLocation.class);
			if(dogLocation.getLocationpic()!=null&&dogLocation.getLocationpic().equals("")){
				dogLocation.setLocationpic(null);
			}
			dogLocation.setId(dogLocationId);
			dogLocation.setUpdatetimes(new Date());
			DogLocation DogLocation2 = adminDogLocationdbService
					.updateLocation(dogLocation);// 修改遛狗地点
			// 1 修改成功， 0 修改失败 -1 出现错误
			String msg = "";
			int flag = 0;
			if (DogLocation2 != null) {
				flag = 1;
				msg = "修改成功";
			} else {
				flag = 0;
				msg = "修改失败";
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 61.删除遛狗地点 更改了state 0 (已)
	public ResultObject deleteLocation(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			long locationId = parameterObject.getLongParameter("locationId");
			Long locationId2 = adminDogLocationdbService
					.deleteLocation(locationId);
			String msg = "";
			int flag = 0;
			if (locationId2 > 0) {
				flag = 1;
				msg = "删除成功";
			} else if (locationId2 == 0) {
				flag = 0;
				msg = "删除失败";
			} else {
				flag = 0;
				msg = "未知错误，请联系管理员";
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 62.查看所有遛狗组 以遛狗地点分组 分页 (已)
	public DataGridResult walkingDogGroupList(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<WalkingDogGroup> dogGroups = adminWalkingDogdbService
					.walkingDogGroupList("", pageNO, pageSize);
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			for (WalkingDogGroup walkingDogGroup : dogGroups) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("groupId", walkingDogGroup.getGroupid());
				// 获取遛狗地点名。(遛狗组名称)
				model.put("locationId", walkingDogGroup.getDoglocationid());
				DogLocation dogLocation = adminDogLocationdbService
						.getDogLocationById(walkingDogGroup.getDoglocationid());
				if (dogLocation != null) {
					model.put("areaname", dogLocation.getAreaname());
				} else {
					model.put("areaname", "");
				}
				model.put("state", walkingDogGroup.getState());
				model.put("addTimes",
						MyUtil.dateToString(walkingDogGroup.getAddtimes()));// 遛狗组创建时间
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			WalkingDogGroupExample example = new WalkingDogGroupExample();

			int total = adminWalkingDogdbService
					.countWalkingDogGroupDetailByExampleGroup(example);
			retObj.setTotal(total);
		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 63.遛狗组详情信息 分页 (已)
	public DataGridResult dogGroupDetail(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			long locationId = parameterObject.getLongParameter("locationId");
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			List<WalkingDogGroup> ls = adminWalkingDogdbService
					.getDogGroupByLocationId(locationId, pageNO, pageSize);
			for (WalkingDogGroup walkingDogGroup : ls) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("userId", walkingDogGroup.getUserid());
				User u = adminUserdbService.getUserByuserId(walkingDogGroup
						.getUserid());
				if (u != null) {
					model.put("nickName", u.getNickname());
				} else {
					model.put("nickName", "");
				}
				model.put("joinTimes",
						MyUtil.dateToString(walkingDogGroup.getJointimes()));
				model.put("state", walkingDogGroup.getState());
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			WalkingDogGroupExample example = new WalkingDogGroupExample();
			WalkingDogGroupExample.Criteria cc = example.createCriteria();
			cc.andGroupidEqualTo(locationId);
			cc.andStateNotEqualTo(0);//数据要有效的
			int total = adminWalkingDogdbService
					.countWalkingDogGroupDetailByExample(example);
			retObj.setTotal(total);
		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// .踢出遛狗组成员 （取消，不该有）
	public ResultObject kickGroup(ParameterObject parameterObject) {
		// TODO 省略
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 64.查看遛狗轨迹 (已)
	public DataGridResult getTrack(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			// 用户， 遛狗地点id (可空) ，当前页(0)，
			List<WalkingDogTrack> tracks = adminWalkingDogdbService.getTrack(
					"", "", pageNO, pageSize);//
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			for (WalkingDogTrack walkingDogTrack : tracks) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("groupId", walkingDogTrack.getGroupid());//
				model.put("addTimes",
						MyUtil.dateToString(walkingDogTrack.getAddtimes()));// 轨迹添加时间
				model.put("userId", walkingDogTrack.getUserid());
				model.put("trackId", walkingDogTrack.getTrackid());
				// 获取用户对象
				User u = adminUserdbService.getUserByuserId(walkingDogTrack
						.getUserid());
				if (u != null) {
					model.put("nickName",
							u.getNickname() == null ? "" : u.getNickname());
				} else
					model.put("nickName", "");
				models.add(model);
			}

			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			WalkingDogTrackExample example = new WalkingDogTrackExample();
			int total = adminWalkingDogdbService
					.countTrackDetailByExample(example);
			retObj.setTotal(total);
		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 65.查看遛狗轨迹(详情用户轨迹) 分页 通过 userid 和 遛狗组记录id 获取详情轨迹 (已)
	public DataGridResult getTrackDetail(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			String userId = parameterObject.getStringParameter("userId");
			String locationId = parameterObject.getStringParameter("groupId");
			// 用户， 遛狗地点id (可空) ，当前页(0)，
			List<WalkingDogTrack> tracks = adminWalkingDogdbService.getTrack(
					userId, locationId, pageNO, pageSize);//
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			for (WalkingDogTrack walkingDogTrack : tracks) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("locationId", walkingDogTrack.getGroupid());// 遛狗地点id
				DogLocation dogLocation2 = adminDogLocationdbService
						.getDogLocationById(walkingDogTrack.getGroupid());
				model.put("districts", dogLocation2.getDistricts());
				model.put("townstreet", dogLocation2.getTownstreet());
				model.put("addTimes", walkingDogTrack.getAddtimes());// 轨迹添加时间
				model.put("longitude", walkingDogTrack.getLongitude());
				model.put("latitude", walkingDogTrack.getLatitude());

				// 通过遛狗地点id获取 遛狗地点对象
				DogLocation dogLocation = adminDogLocationdbService
						.getDogLocationById(walkingDogTrack.getGroupid());
				if (dogLocation != null) {
					model.put("locationName", dogLocation.getAreaname());
				} else {
					model.put("locationName", "");
				}
				models.add(model);
			}

			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			WalkingDogTrackExample example = new WalkingDogTrackExample();
			WalkingDogTrackExample.Criteria cc = example.createCriteria();
			cc.andGroupidEqualTo(Long.parseLong(locationId));
			cc.andUseridEqualTo(Long.parseLong(userId));
			int total = adminWalkingDogdbService
					.countTrackDetailByExample(example);
			retObj.setTotal(total);
		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 66.查看所有消息（AdminUser） (已) 后期可加条件
	public DataGridResult getAllMsg(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Message> lms = adminMessagedbService.getAllMsg(pageNO,
					pageSize);
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			for (Message message : lms) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("messageId", message.getMessageid());
				model.put("senduserId", message.getSenduserid());
				// 获取用户对象
				User u = adminUserdbService.getUserByuserId(message
						.getSenduserid());
				if (u != null) {
					model.put("sendNickname",
							u.getNickname() == null ? "" : u.getNickname());
				} else
					model.put("sendNickname", "");
				model.put("acceptuserId", message.getAcceptuserid());
				// 获取用户对象
				User u1 = adminUserdbService.getUserByuserId(message
						.getAcceptuserid());
				if (u1 != null) {
					model.put("acceptNickName", u1.getNickname() == null ? ""
							: u1.getNickname());
				} else
					model.put("acceptNickName", "");
				model.put("type", message.getType());
				model.put("acceptTimes", message.getAccepttimes() == null ? ""
						: MyUtil.dateToString(message.getAccepttimes()));
				model.put("state", message.getState());
				model.put("source",
						message.getSource() == null ? "" : message.getSource());
				model.put("addTimes",
						MyUtil.dateToString(message.getAddtimes()));
				model.put("content", message.getContent() == null ? ""
						: message.getContent());
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(DataGridResult.STATE_SUCCESS);
			retObj.setMessage(DataGridResult.Message_STATE_SUCCESS);
			retObj.setCode(DataGridResult.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			MessageExample example = new MessageExample();
			int total = adminMessagedbService.countMessageByExample(example);
			retObj.setTotal(total);
		} catch (Exception e) {
			retObj.setCode(DataGridResult.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(DataGridResult.STATE_FAIL);
			retObj.setMessage(DataGridResult.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 68.朋友圈详细评论接口 分页
	public DataGridResult findDisussByFriendCir(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			Long friendCirId = parameterObject.getLongParameter("friendCirId");
			DiscussExample example = new DiscussExample();
			DiscussExample.Criteria cc = example.createCriteria();
			cc.andFriendcirIdEqualTo(friendCirId);
			List<Discuss> lds = adminFriendsCircledbService
					.findDisussByFriendCir(example, pageNO, pageSize);
			for (Discuss discuss : lds) {
				Map<String, Object> model = new HashMap<String, Object>();
				User u = adminUserdbService
						.getUserByuserId(discuss.getUserId());
				if (u != null) {
					model.put("nickName", u.getNickname());// 这里要获取用户对象
				} else {
					model.put("nickName", "");
				}
				model.put("content", discuss.getDiscusscontent());
				if (discuss.getPid() == 0) {

					model.put("takeComment", "朋友圈");
				} else {
					Discuss d = adminFriendsCircledbService
							.getDiscussById(discuss.getPid());
					u = adminUserdbService.getUserByuserId(d.getUserId());
					if (u != null) {
						model.put("takeComment", u.getNickname());// 这里要获取用户对象
					} else {
						model.put("takeComment", "");
					}
				}
				model.put("disussTime",
						MyUtil.dateToString(discuss.getDiscusstime()));
				model.put("state", discuss.getState() == 1 ? "未删除" : "已删除");
				models.add(model);
			}
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			int total = adminFriendsCircledbService
					.countDiscussByFriendCir(example);
			retObj.setTotal(total);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	// 朋友圈媒体资源详情接口
	public ResultObject getFriendCirsMediaByFriendCirId(
			ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			Long friendCirId = parameterObject.getLongParameter("friendCirId");
			List<FriendCircleMedia> friendCirs = adminFriendsCircledbService
					.getFriendCirsMediaByFriendCirId(friendCirId);
			List<Map<String, Object>> mapMedias = new ArrayList<Map<String, Object>>();
			for (FriendCircleMedia friendCircleMedia : friendCirs) {
				Map<String, Object> mapMedia = new HashMap<String, Object>();
				mapMedia.put("mediaUrl",
						friendCircleMedia.getMediaurl() == null ? ""
								: friendCircleMedia.getMediaurl());
				mapMedia.put("mediaSubUrl",
						friendCircleMedia.getMediasuburl() == null ? ""
								: friendCircleMedia.getMediasuburl());
				mapMedias.add(mapMedia);
			}
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(mapMedias);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;

	}

	public ResultObject findDogLocationAreaSpace(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			Long dogLocationId = parameterObject
					.getLongParameter("dogLocationId");
			List<Double[]> coordinates = adminAreaSpacedbService
					.returnPolygonByDogLocationId(dogLocationId);
			model.put("coordinates", coordinates);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(coordinates);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject addDogLocationAreaSpace(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			Long dogLocationId = parameterObject
					.getLongParameter("dogLocationId");
			String ploygonStr = parameterObject
					.getStringParameter("ploygonStr");
			AreaSpace areaSpace = new AreaSpace();
			areaSpace.setAreaRange(MapTools.returnPolygon(ploygonStr)
					.toString());
			Date date = new Date();
			areaSpace.setUpdatetimes(date);
			areaSpace.setState(1);
			areaSpace.setDoglocationId(dogLocationId);
			String msg = "添加失败";
			int flag = 0;
			int res = adminAreaSpacedbService
					.addDogLocationAreaSpace(areaSpace);
			if (res >= 1) {
				flag = 1;
				msg = "添加成功";
			}
			model.put("flag", flag);
			model.put("msg", msg);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject modifyDogLocationAreaSpace(
			ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			Long dogLocationId = parameterObject
					.getLongParameter("dogLocationId");
			String ploygonStr = parameterObject
					.getStringParameter("ploygonStr");
			AreaSpace areaSpace = new AreaSpace();
			areaSpace.setAreaRange(ploygonStr);
			Date date = new Date();
			areaSpace.setUpdatetimes(date);
			areaSpace.setState(1);
			areaSpace.setDoglocationId(dogLocationId);
			String msg = "修改失败";
			int flag = 0;
			int res = adminAreaSpacedbService
					.modifyDogLocationAreaSpace(areaSpace);
			if (res >= 1) {
				flag = 1;
				msg = "修改成功";
			}
			model.put("flag", flag);
			model.put("msg", msg);
			String token = parameterObject.getToken();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject getTotalOnlineUsers(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			Map<String, Object> model = onlineRecorddbService.getTotalOnlineUsers();
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public DataGridResult getOnlineRecord(ParameterObject parameterObject) {
		DataGridResult retObj = new DataGridResult();// 返回对象
		try {
			int pageNO = parameterObject.getIntegerParameter("pageNO");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			List<Map<String, Object>> models = onlineRecorddbService.getOnlineRecord(pageNO, pageSize);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setRows(models);
			Long total = onlineRecorddbService.getTotalOnlineRecord()+onlineRecorddbService.getTotalDBonlineRecord();
			retObj.setTotal(total.intValue());
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	public ResultObject onlineRecordToDB(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();// 返回对象
		try {
			int recordNO = parameterObject.getIntegerParameter("recordNO");
			Map<String, Object> model = onlineRecorddbService.onlineRecordToDB(recordNO);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

}
