package com.dogpro.service.impl.dbservice;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.domain.RedisDoglocation;
import com.dogpro.common.domain.RedisWalkingDogGroup;
import com.dogpro.common.tool.JedisUtil;
import com.dogpro.common.tool.MQTTapi;
import com.dogpro.common.tool.MapTools;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.common.tool.ObjectUtil;
import com.dogpro.dao.AreaSpaceMapper;
import com.dogpro.dao.DogLocationMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.dao.WalkingDogGroupMapper;
import com.dogpro.dao.WalkingDogTrackMapper;
import com.dogpro.domain.model.AreaSpace;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.DogLocationExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.domain.model.WalkingDogGroupExample;
import com.dogpro.domain.model.WalkingDogTrack;
import com.dogpro.service.dbservice.MessagedbService;
import com.dogpro.service.dbservice.PushdbService;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.UserTokendbService;
import com.dogpro.service.dbservice.WalkingDogdbService;
import com.vividsolutions.jts.geom.Polygon;

/**
 * 遛狗接口(dogpro)
 * 
 * @author Administrator
 *
 */
@Service("WalkingDogdbService")
public class WalkingDogdbServiceImpl implements WalkingDogdbService {

	@Autowired
	private WalkingDogGroupMapper walkingDogGroupMapper;
	@Autowired
	private DogLocationMapper dogLocationMapper;
	@Autowired
	private AreaSpaceMapper areaSpaceMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private WalkingDogTrackMapper walkingDogTrackMapper;
	@Autowired
	private RedisdbService redisdbService;
	@Autowired
	private PushdbService pushdbService;
	@Autowired
	private UserTokendbService userTokendbService;
	@Autowired
	private MessagedbService messagedbService;

	private static JedisUtil jedisUtil;
	static {
		Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
		jedisUtil = new JedisUtil(Integer.parseInt(packagesMap.get(
				"redisTrackdb").toString()));
	}

	// 加入遛狗组
	public int joinGroup(WalkingDogGroup walkingDogGroup) {
		Date currentTime = new Date();
		walkingDogGroup.setAddtimes(currentTime);
		walkingDogGroup.setUpdatetimes(currentTime);
		walkingDogGroup.setJointimes(currentTime);
		DogLocationExample example = new DogLocationExample();
		DogLocationExample.Criteria cDog = example.createCriteria();
		cDog.andIdEqualTo(walkingDogGroup.getDoglocationid());

		DogLocation dogLocation = dogLocationMapper
				.selectByPrimaryKey(walkingDogGroup.getDoglocationid());
		if (dogLocation.getPid() != 0) {
			dogLocation = dogLocationMapper.selectByPrimaryKey(dogLocation
					.getPid());
			walkingDogGroup.setDoglocationid(dogLocation.getId());
		}
		if (dogLocation != null) {
			// record.setHot(dogLocation.getHot() + 1);
			WalkingDogGroupExample walkExample = new WalkingDogGroupExample();
			WalkingDogGroupExample.Criteria cWalk = walkExample
					.createCriteria();
			cWalk.andStateNotEqualTo(0);
			cWalk.andUseridEqualTo(walkingDogGroup.getUserid());
			// 在这里进行判断次人是否再次来到次地方
			if (walkingDogGroupMapper.selectByExample(walkExample).size() > 0) {
				// System.out.println("在这里进行判断此人是否再次来到此地方");
				return -9;
			}
			// 区域范围判断标志
			boolean flag = false;
			try {
				AreaSpace areaSpace = redisdbService
						.getAreaSpaceMap(walkingDogGroup.getDoglocationid());
				String str = areaSpace.getAreaRange().toString();// 查询出区域范围
				Point2D.Double point = new Point2D.Double(walkingDogGroup
						.getJoinlongitude().doubleValue(), walkingDogGroup
						.getJoinlatitude().doubleValue());
				Polygon polygon = MapTools.createPolygonByWKT(str);
				List<Point2D.Double> pointList = MapTools
						.getPointListByPolygon(polygon);
				flag = MapTools.isInPolygon(point, pointList);
			} catch (Exception e) {
			}
			if (!flag) {
				// 超出遛狗区域范围
				return -2;
			}
			if (walkingDogGroupMapper.insertSelective(walkingDogGroup) == 1) {
				// 修改redis库中的Gourp-user集合
				redisdbService.groupSADDmember(dogLocation.getId(),
						walkingDogGroup.getUserid());
				// 插入 Redis 加入遛狗组成员 集合
				redisdbService.SADDjoinGroup(walkingDogGroup.getUserid());
				// 插入 redis walkingdogGroup 部分信息
				RedisWalkingDogGroup redisWalkingDogGroup = new RedisWalkingDogGroup(
						walkingDogGroup.getGroupid(),
						walkingDogGroup.getUserid(),
						walkingDogGroup.getDoglocationid(),
						walkingDogGroup.getState(),
						walkingDogGroup.getEndtimes(),
						walkingDogGroup.getJointimes());
				redisdbService.setWalkingDogGroup(redisWalkingDogGroup);
			}
			// 给该群组内其他成员 发送 im信息
			String nickname = redisdbService.getUserNickname(walkingDogGroup
					.getUserid());
			String areaname = dogLocation.getAreaname();
			int code = 1;
			String msg = nickname + " 加入了  " + areaname + " 遛狗组";
			Map<String, Object> contentMap = new HashMap<String, Object>();
			contentMap.put("code", code);
			contentMap.put("msg", msg);
			JSONObject jsonObject = new JSONObject(contentMap);
			// 设置 群组信息免打扰 关闭
			redisdbService.setUserGroupDisturb(walkingDogGroup.getUserid(), 0);
			// 发送IM mqtt信息
			redisdbService.handleMsg(walkingDogGroup.getUserid(),
					walkingDogGroup.getDoglocationid(), 14,
					jsonObject.toJSONString(), currentTime.getTime());
			messagedbService.insertIMMessage(walkingDogGroup.getUserid(),
					walkingDogGroup.getDoglocationid(), 14,
					jsonObject.toJSONString(), currentTime.getTime());
			
			// 给该群组内其他成员 发送 开场白
			
			String content = "你们在哪，带上我一起遛狗吧~";
			Map<String, Object> conMap = new HashMap<String, Object>();
			conMap.put("content", content);
			jsonObject = new JSONObject(conMap);
			// 发送IM mqtt信息
			redisdbService.handleMsg(walkingDogGroup.getUserid(),
					walkingDogGroup.getDoglocationid(), 8,
					jsonObject.toJSONString(), currentTime.getTime());
			messagedbService.insertIMMessage(walkingDogGroup.getUserid(),
					walkingDogGroup.getDoglocationid(), 8,
					jsonObject.toJSONString(), currentTime.getTime());
			
			// 调用 订阅群组 api
			return subscribeGroupTopic(walkingDogGroup.getUserid(),
					walkingDogGroup.getDoglocationid()) ? 1 : -1;
		} else
			// 插入一条遛狗组记录
			return -1;
	}

	// 退出遛狗组
	public int quitGroup(WalkingDogGroup walkingDogGroup, boolean isinitiactive) {
		// 判断用户是否已结束遛狗
		WalkingDogGroup key = walkingDogGroupMapper
				.selectByPrimaryKey(walkingDogGroup.getGroupid());
		RedisWalkingDogGroup re = redisdbService
				.getWalkingDogGroup(key.getUserid());
		if (re == null
				|| !key.getDoglocationid().equals(re.getDoglocationid())
				|| re.getState() != 2) {
			return -2;
		}

		
		if (key != null) {
			key.setState(0);
			key.setUpdatetimes(new Date());
			key.setOuttimes(new Date());
			if (walkingDogGroupMapper.updateByPrimaryKeySelective(key) == 1) {
				// 修改redis库中的Gourp-user集合
				redisdbService.groupSREMmember(key.getDoglocationid(),
						key.getUserid());
				// 修改redis库中 的 exitgroup 集合
				Long userId = key.getUserid();
				redisdbService.SREMexitGroup(userId);
				// 修改Redis库中 的 joingroup 集合
				redisdbService.SREMjoinGroup(userId);
				// 删除 redis walkingdogGroup 部分信息
				redisdbService.deleteWalkingDogGroup(key.getUserid());
				String nickname = redisdbService.getUserNickname(key
						.getUserid());
				if (nickname == null) {
					nickname = "有人";
				}
				// 调用 mqtt api 退订群组topic
				String token = userTokendbService.getUserToken(key.getUserid());
				if (token != null) {
					String client_id = "U" + key.getUserid() + "_" + token;
					DogLocation dogLocation = dogLocationMapper
							.selectByPrimaryKey(key.getDoglocationid());
					String keyss = dogLocation.getKeyss();
					MQTTapi mqttapi = new MQTTapi();
					mqttapi.unsubscribeGroup(client_id, keyss);
				}
				// 设置 群组信息免打扰 关闭
				redisdbService.setUserGroupDisturb(key.getUserid(), 0);
				// 给该用户 发送 im信息 （主要是被系统3小时踢出）
				int code = 1;
				String msg = "你已退出遛狗组";
				Map<String, Object> contentMap = new HashMap<String, Object>();
				contentMap.put("code", code);
				contentMap.put("msg", msg);
				JSONObject jsonObject = new JSONObject(contentMap);
				Date currentTime = new Date();
				if (!isinitiactive) {
					// 若被系统踢出遛狗组 发送 IM mqtt信息
					redisdbService.handleMsg(key.getDoglocationid(),
							key.getUserid(), 13, jsonObject.toJSONString(),
							currentTime.getTime());
					messagedbService.insertIMMessage(key.getDoglocationid(),
							key.getUserid(), 13, jsonObject.toJSONString(),
							currentTime.getTime());
				}
				// 给其他用户 发送IM信息 （更新群组成员列表）
				code = 2;
				msg = nickname + " 退出遛狗组";
				contentMap.clear();
				contentMap.put("code", code);
				contentMap.put("msg", msg);
				jsonObject = new JSONObject(contentMap);
				redisdbService.handleMsg(key.getUserid(),
						key.getDoglocationid(), 14, jsonObject.toJSONString(),
						currentTime.getTime());
				messagedbService.insertIMMessage(key.getUserid(),
						key.getDoglocationid(), 14, jsonObject.toJSONString(),
						currentTime.getTime());
				// 发布推行信息
				// pushdbService.sendPushMsg(8, nickname + " 退出了遛狗组",
				// key.getDoglocationid(), key.getUserid(),
				// key.getDoglocationid());
			}
			return 1;
		}
		return -2;
	}

	// 遛狗组成员
	// public List<User> readGroupUsers(long userId, long doglocationId,Integer
	// isContain,int pageNo,int pageSize) {
	//
	// WalkingDogGroupExample example1 = new WalkingDogGroupExample();
	// WalkingDogGroupExample.Criteria c1 = example1.createCriteria();
	// c1.andDoglocationidEqualTo(doglocationId);
	// c1.andStateNotEqualTo(0);// 正在遛狗 或 结束遛狗
	// PaginationUtil paginationUtil = new PaginationUtil(pageNo, pageSize);
	// example1.setPagination(paginationUtil);
	// if(isContain!=null&&isContain==0){
	// c1.andUseridNotEqualTo(userId);
	// }
	// List<WalkingDogGroup> list = walkingDogGroupMapper
	// .selectByExample(example1);
	// List<Long> strs = new ArrayList<Long>();
	// for (WalkingDogGroup wd : list) {
	// strs.add(wd.getUserid());
	// }
	// UserExample uexample = new UserExample();
	// UserExample.Criteria uc = uexample.createCriteria();
	// uc.andUserIdIn(strs);
	// if (strs.size() <= 0) {
	// return new ArrayList<User>();
	// }
	// List<User> lus = userMapper.selectByExample(uexample);
	// return lus;
	// }

	// 遛狗组成员
	public List<User> readGroupUsers(long userId, long doglocationId,
			Integer isContain, int pageNo, int pageSize) {

		PaginationUtil paginationUtil = new PaginationUtil(pageNo, pageSize);
		List<User> lus = new ArrayList<User>();
		lus = walkingDogGroupMapper.readGroupUser(userId, doglocationId,
				isContain, paginationUtil);
		return lus;
	}

	/**
	 * 上传遛狗轨迹
	 */
	public int uploadTrack(Long userId, Long groupId, BigDecimal longitude,
			BigDecimal latitude) {
		try {
			// redis键名
			byte[] redisKey = "WalkingDogTrack".getBytes();
			// 打包轨迹信息
			WalkingDogTrack walkingDogTrack = new WalkingDogTrack();
			walkingDogTrack.setUserid(userId);
			walkingDogTrack.setGroupid(groupId);
			walkingDogTrack.setLongitude(longitude);
			walkingDogTrack.setLatitude(latitude);
			Date currentTime = new Date();
			walkingDogTrack.setTracktimes(currentTime);
			walkingDogTrack.setAddtimes(currentTime);
			walkingDogTrack.setUpdatetimes(currentTime);

			// 遛狗记录 
			RedisWalkingDogGroup redisWalkingDogGroup = redisdbService.getWalkingDogGroup(userId);
			if (redisWalkingDogGroup == null || redisWalkingDogGroup.getState() != 1) {
				return -2;
			}

			String str = "";
			boolean b = false;
			try {
				AreaSpace areaSpace = redisdbService
						.getAreaSpaceMap(redisWalkingDogGroup.getDoglocationid());
				str = areaSpace.getAreaRange().toString();// 查询出区域范围
				Point2D.Double point = new Point2D.Double(
						longitude.doubleValue(), latitude.doubleValue());
				Polygon polygon = MapTools.createPolygonByWKT(str);
				List<Point2D.Double> pointList = MapTools
						.getPointListByPolygon(polygon);
				b = MapTools.isInPolygon(point, pointList);
			} catch (Exception e) {
			}
			if (b) {
				// 如果在区域范围内 上传轨迹
				jedisUtil.lpush(redisKey,
						ObjectUtil.object2Bytes(walkingDogTrack));
			} else {
				// 超出遛狗范围 后台帮该用户结束遛狗
				endWalkingDog(userId, redisWalkingDogGroup.getDoglocationid(),
						longitude, latitude);
			}
			return b ? 1 : -1;
		} catch (Exception e) {
			return -2;
		}
	}

	/**
	 * 遛狗轨迹插入数据库
	 */
	public int redis2Mysql(WalkingDogTrack walkingDogTrack) {

		return walkingDogTrackMapper.insertSelective(walkingDogTrack);
	}

	/**
	 * 匹配遛狗组
	 */
	public Map<String, Object> matchGroup(long userId, long doglocationId,
			BigDecimal longitude, BigDecimal latitude) {
		// TODO Auto-generated method stub
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		int isFirst = 0;
		// 搜索该地点state=1的用户数
		WalkingDogGroupExample walkingDogGroupExample = new WalkingDogGroupExample();
		WalkingDogGroupExample.Criteria wCriteria = walkingDogGroupExample
				.createCriteria();
		wCriteria.andDoglocationidEqualTo(doglocationId).andStateNotEqualTo(0);
		// 在线人数
		int totalusers = walkingDogGroupMapper
				.countByExample(walkingDogGroupExample);
		// 在线用户列表(根据joinTime排序，时间早的在前面)
		walkingDogGroupExample.setOrderByClause("joinTimes asc");
		List<WalkingDogGroup> walkingDogGroups = walkingDogGroupMapper
				.selectByExample(walkingDogGroupExample);
		// 若该地点人数为0，则先插入自己加入该群组的数据，等待他人加入，匹配失败
		if (totalusers == 0) {
			// 插入数据
			WalkingDogGroup walkingDogGroup = new WalkingDogGroup();
			walkingDogGroup.setUserid(userId);
			walkingDogGroup.setDoglocationid(doglocationId);
			walkingDogGroup.setJoinlongitude(longitude);
			walkingDogGroup.setJoinlatitude(latitude);
			Date currentTime = new Date();
			walkingDogGroup.setJointimes(currentTime);
			walkingDogGroup.setAddtimes(currentTime);
			walkingDogGroup.setUpdatetimes(currentTime);
			// state=1 表示在线
			walkingDogGroup.setState(1);
			walkingDogGroupMapper.insert(walkingDogGroup);
			flag = 0;
			msg = "匹配失败";
			isFirst = 1;

		}
		// 若人数为1，判断是否是用户本人
		else if (totalusers == 1) {
			// 是用户本人
			if (walkingDogGroups.get(0).getUserid() == userId) {
				flag = 0;
				msg = "匹配失败";
				isFirst = 1;
			}
			// 非用户本人
			else {
				flag = 1;
				msg = "匹配成功";
				isFirst = 0;
			}
		}
		// 若该地点人数大于1匹配成功
		else {
			flag = 1;
			msg = "匹配成功";
			// 是用户本人
			if (walkingDogGroups.get(0).getUserid() == userId) {
				isFirst = 1;
			}
			// 非用户本人
			else {
				isFirst = 0;
			}
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("isFirst", isFirst);
		return model;
	}

	/**
	 * 如果为第一人，退出匹配
	 */
	public int exitMatchGroup(long userId, long doglocationId,
			BigDecimal longitude, BigDecimal latitude) {
		// TODO Auto-generated method stub
		// 搜索该用户该地点state=1的数据
		WalkingDogGroupExample walkingDogGroupExample = new WalkingDogGroupExample();
		WalkingDogGroupExample.Criteria wCriteria = walkingDogGroupExample
				.createCriteria();
		wCriteria.andDoglocationidEqualTo(doglocationId)
				.andUseridEqualTo(userId).andStateEqualTo(1);
		List<WalkingDogGroup> walkingDogGroups = walkingDogGroupMapper
				.selectByExample(walkingDogGroupExample);
		if (!walkingDogGroups.isEmpty()) {
			// 获取对应数据
			WalkingDogGroup walkingDogGroup = walkingDogGroups.get(0);
			Date currentTime = new Date();
			walkingDogGroup.setEndtimes(currentTime);
			walkingDogGroup.setEndlongitude(longitude);
			walkingDogGroup.setEndlatitude(latitude);
			walkingDogGroup.setOuttimes(currentTime);
			walkingDogGroup.setOutlongitude(longitude);
			walkingDogGroup.setOutlatitude(latitude);
			walkingDogGroup.setUpdatetimes(currentTime);
			walkingDogGroup.setState(0);
			walkingDogGroupMapper.updateByPrimaryKeySelective(walkingDogGroup);
			return 1;
		}
		return 0;
	}

	/**
	 * 包括自己的遛狗组成员<去掉>
	 */
	public List<User> readGroupUsersAndI(long userId, long doglocationId) {
		WalkingDogGroupExample example = new WalkingDogGroupExample();
		WalkingDogGroupExample.Criteria c = example.createCriteria();
		c.andUseridEqualTo(userId);
		c.andStateNotEqualTo(0);// 有效
		c.andDoglocationidEqualTo(doglocationId);
		List<WalkingDogGroup> my = walkingDogGroupMapper
				.selectByExample(example);
		if (my.size() <= 0) {
			return new ArrayList<User>();
		}
		WalkingDogGroupExample example1 = new WalkingDogGroupExample();
		WalkingDogGroupExample.Criteria c1 = example1.createCriteria();
		c1.andDoglocationidEqualTo(doglocationId);
		c1.andStateNotEqualTo(0);// 有效
		List<WalkingDogGroup> list = walkingDogGroupMapper
				.selectByExample(example1);
		List<Long> strs = new ArrayList<Long>();
		for (WalkingDogGroup wd : list) {
			strs.add(wd.getUserid());
		}
		UserExample uexample = new UserExample();
		UserExample.Criteria uc = uexample.createCriteria();
		uc.andUserIdIn(strs);
		if (strs.size() <= 0) {
			return new ArrayList<User>();
		}
		List<User> lus = userMapper.selectByExample(uexample);
		return lus;
	}

	/**
	 * 根据userId获取订阅的群组keys
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getJoinGroupKeys(Long userId) {
		// TODO Auto-generated method stub
		// 返回类型
		Map<String, Object> result = new HashMap<String, Object>();
		Long locationId = 0l;
		String areaName = "";
		Long groupId = 0l;
		int state = 0;
		String groupPic = "";
		Long exitTime = 0l;
		int isDisturb = 0;
		// 搜索该用户在线的参加群组记录
		RedisWalkingDogGroup redisWalkingDogGroup = redisdbService
				.getWalkingDogGroup(userId);
		if (redisWalkingDogGroup != null) {
			groupId = redisWalkingDogGroup.getGroupid();
			locationId = redisWalkingDogGroup.getDoglocationid();
			state = redisWalkingDogGroup.getState();
			if (state == 2) {
				// 已结束遛狗
				Long kickTime = redisWalkingDogGroup.getEndtimes().getTime()
						+ 3 * 60 * 60 * 1000;
				exitTime = kickTime - new Date().getTime();
			}
			RedisDoglocation redisDoglocation = redisdbService
					.getDoglocation(locationId);
			if (redisDoglocation != null) {
				groupPic = redisDoglocation.getLocationpic();
				areaName = redisDoglocation.getAreaname();
			}
			isDisturb = redisdbService.getUserGroupDisturb(userId);

		}
		result.put("locationId", locationId);
		result.put("areaName", areaName);
		result.put("groupId", groupId);
		result.put("state", state);
		result.put("groupPic", groupPic);
		result.put("exitTime", exitTime);
		result.put("isDisturb", isDisturb);

		return result;
	}

	// 通过遛狗地点id（相当于群id）查找对应的UserId
	public List<Long> findUserIdByDogLocation(Long dogLocation) {
		WalkingDogGroupExample example = new WalkingDogGroupExample();
		WalkingDogGroupExample.Criteria cc = example.createCriteria();
		cc.andStateNotEqualTo(0);// 消息有用状态
		List<Long> ls = new ArrayList<Long>();
		List<WalkingDogGroup> selectByExample = walkingDogGroupMapper
				.selectByExample(example);
		for (WalkingDogGroup walkingDogGroup : selectByExample) {
			ls.add(walkingDogGroup.getUserid());
		}
		return ls;
	}

	public Map<String, Object> endWalkingDog(long userId, long doglocationId,
			BigDecimal longitude, BigDecimal latitude) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "结束失败";
		try {
			WalkingDogGroupExample walkingDogGroupExample = new WalkingDogGroupExample();
			WalkingDogGroupExample.Criteria wCriteria = walkingDogGroupExample
					.createCriteria();
			wCriteria.andUseridEqualTo(userId)
					.andDoglocationidEqualTo(doglocationId).andStateEqualTo(1);
			List<WalkingDogGroup> walkingDogGroups = walkingDogGroupMapper
					.selectByExample(walkingDogGroupExample);
			if (!walkingDogGroups.isEmpty()) {
				WalkingDogGroup walkingDogGroup = walkingDogGroups.get(0);
				Date currentTime = new Date();
				walkingDogGroup.setEndlatitude(latitude);
				walkingDogGroup.setEndlongitude(longitude);
				walkingDogGroup.setEndtimes(currentTime);
				walkingDogGroup.setUpdatetimes(currentTime);
				// 设置状态为2 已结束遛狗
				walkingDogGroup.setState(2);
				if (walkingDogGroupMapper
						.updateByPrimaryKeySelective(walkingDogGroup) == 1) {
					// 发布推送信息
					String nickname = redisdbService.getUserNickname(userId);
					if (nickname == null) {
						nickname = "有人";
					}
					pushdbService.sendPushMsg(7, nickname + " 结束了遛狗",
							walkingDogGroup.getDoglocationid(),
							walkingDogGroup.getUserid(),
							walkingDogGroup.getDoglocationid());
					flag = 1;
					msg = "结束成功";
					// 调用 mqtt api 退订群组topic
					String token = userTokendbService.getUserToken(userId);
					if (token != null) {
						String client_id = "U" + userId + "_" + token;
						DogLocation dogLocation = dogLocationMapper
								.selectByPrimaryKey(walkingDogGroup
										.getDoglocationid());
						String keyss = dogLocation.getKeyss();
						MQTTapi mqttapi = new MQTTapi();
						mqttapi.unsubscribeGroup(client_id, keyss);
					}
					// 修改redis库中的Gourp-user集合
					redisdbService.groupSREMmember(
							walkingDogGroup.getDoglocationid(), userId);
					// 插入redis exitGroup 集合
//					redisdbService
//							.setWalkingDogGroupEndTime(
//									walkingDogGroup.getGroupid(),
//									currentTime.getTime());
					// 插入 redis walkingdogGroup 部分信息
					RedisWalkingDogGroup redisWalkingDogGroup = new RedisWalkingDogGroup(
							walkingDogGroup.getGroupid(),
							walkingDogGroup.getUserid(),
							walkingDogGroup.getDoglocationid(),
							walkingDogGroup.getState(),
							walkingDogGroup.getEndtimes(),
							walkingDogGroup.getJointimes());
					redisdbService.setWalkingDogGroup(redisWalkingDogGroup);
					//插入 redis 结束遛狗 用户 集合
					redisdbService.SADDexitGroup(walkingDogGroup.getUserid());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}

	public boolean subscribeGroupTopic(Long userId, Long doglocationId) {
		// 调用 mqtt api 退订群组topic
		String token = userTokendbService.getUserToken(userId);
		if (token != null) {
			String client_id = "U" + userId + "_" + token;
			DogLocation dogLocation = dogLocationMapper
					.selectByPrimaryKey(doglocationId);
			String keyss = dogLocation.getKeyss();
			MQTTapi mqttapi = new MQTTapi();
			mqttapi.subscribeGroup(client_id, keyss);
			return true;
		}
		return false;
	}

	public Long getDogLocationIdByUid(Long userId) {
		Map<String, Object> model = getJoinGroupKeys(userId);
		Long locationId = Long.valueOf(model.get("locationId").toString());
		return locationId;
	}
}
