package com.dogpro.service.impl.webapi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.common.tool.MessageConsumerConfig;
import com.dogpro.dao.DogLocationMapper;
import com.dogpro.domain.model.Friends;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.service.dbservice.FriendsdbService;
import com.dogpro.service.dbservice.WalkingDogdbService;
import com.dogpro.service.webapi.WalkingDogService;

/**
 * 遛狗接口(dogPro) 
 * @author Administrator
 */
@Service("WalkingDogService")
public class WalkingDogServiceImpl implements WalkingDogService {

	@Autowired
	private WalkingDogdbService walkingDogdbService;
	@Autowired
	private FriendsdbService friendsdbService;
	@Autowired
	private DogLocationMapper dogLocationMapper;

	// 加入遛狗组
	public ResultObject joinGroup(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			long userId = Long.parseLong(parameterObject
					.getStringParameter("userId"));
			long doglocationId = Long.parseLong(parameterObject
					.getStringParameter("doglocationId"));
			BigDecimal joinLongitude = new BigDecimal(parameterObject
					.getStringParameter("joinLongitude"));
			BigDecimal joinLatitude = new BigDecimal(parameterObject
					.getStringParameter("joinLatitude"));
			WalkingDogGroup walkingDogGroup = new WalkingDogGroup();
			walkingDogGroup.setDoglocationid(doglocationId);
			walkingDogGroup.setJoinlongitude(joinLongitude);
			walkingDogGroup.setJoinlatitude(joinLatitude);
			//设置群组信息免打扰 关闭
			walkingDogGroup.setIsdisturb(0);
			walkingDogGroup.setState(1);// 正在遛狗1
			walkingDogGroup.setUserid(userId);
			int res = walkingDogdbService.joinGroup(walkingDogGroup);
			String token = parameterObject.getToken();
			String msg = "";
			String groupPic = "";
			int flag = 0;
			Long groupId = 0l;
			if (res>0) {
				flag = 1 ;
				msg = "加入成功";
				groupId = walkingDogGroup.getGroupid();
				groupPic = dogLocationMapper.selectByPrimaryKey(walkingDogGroup.getDoglocationid()).getLocationpic();
			}else if(res==-9){//-9
				msg = "已经加入过遛狗地点了";
			}else if(res==-2){
				msg = "不在该遛狗地点范围内";
			}else{
				msg = "加入失败";
			}
			Map<String, Object> model = new HashMap();
			model.put("msg", msg);
			model.put("flag", flag);
			model.put("groupId", groupId);
			model.put("groupPic", groupPic);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	//退出遛狗组
	public ResultObject quitGroup(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject
					.getStringParameter("userId"));
			long groupId = Long.parseLong(parameterObject
					.getStringParameter("groupId"));
			long doglocationId = Long.parseLong(parameterObject
					.getStringParameter("doglocationId"));
			BigDecimal outLongitude =new BigDecimal(parameterObject.getStringParameter("outLongitude"));
			BigDecimal outLatitude =new BigDecimal(parameterObject.getStringParameter("outLatitude"));
			String token =parameterObject.getToken();
			WalkingDogGroup walkingDogGroup = new WalkingDogGroup();
			walkingDogGroup.setUserid(userId);
			walkingDogGroup.setDoglocationid(doglocationId);
			walkingDogGroup.setOutlongitude(outLongitude);
			walkingDogGroup.setOutlatitude(outLatitude);
			walkingDogGroup.setGroupid(groupId);
			walkingDogGroup.setState(0);//退出了，无效了
			int res = walkingDogdbService.quitGroup(walkingDogGroup,true);
			String msg = "";
			int flag = 0;
			if (res>0) {
				flag = 1 ;
				msg = "退出成功";
			}else {
				msg = "退出失败";
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("msg", msg);
			model.put("flag", flag);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	//读取遛狗的成员
	public ResultObject readGroupUser(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			long doglocationId = Long.parseLong(parameterObject.getStringParameter("doglocationId"));
			Integer isContain = parameterObject.getIntegerParameter("isContain"); 
			Integer pageNo = parameterObject.getIntegerParameter("pageNO");
			Integer pageSize = parameterObject.getIntegerParameter("pageSize");
			List<User> list = walkingDogdbService.readGroupUsers(userId,doglocationId,isContain,pageNo,pageSize);
			List<Map<String,Object>> model = new ArrayList<Map<String,Object>>();
			for (User user : list) {
				Map<String,Object> ma = new HashMap();
				ma.put("headpic", user.getHeadpic());
				ma.put("nickname", user.getNickname());
				ma.put("userId", user.getUserId());
				model.add(ma);
			}
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	
	//上传遛狗轨迹
	public ResultObject uploadTrack(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			long groupId = Long.parseLong(parameterObject.getStringParameter("groupId"));
			BigDecimal longitude = new BigDecimal(parameterObject.getStringParameter("longitude"));
			BigDecimal latitude = new BigDecimal(parameterObject.getStringParameter("latitude"));
			String token = parameterObject.getToken();
			Map<String, Object> model = new HashMap<String,Object>();
			int success = walkingDogdbService.uploadTrack(userId, groupId, longitude, latitude);
			if(success==1){
				model.put("flag", 1);
				model.put("msg", "上传成功");
			}else if(success==-2){
				model.put("flag", 0);
				model.put("msg", "上传失败");
			}else if(success==-1){
				model.put("flag", -1);
				model.put("msg", "你已经走出了遛狗地点范围……");
			}
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	/**
	 * 匹配接口
	 */
	public ResultObject matchGroup(ParameterObject parameterObject){
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			long doglocationId = Long.parseLong(parameterObject.getStringParameter("doglocationId"));
			BigDecimal longitude = new BigDecimal(parameterObject.getStringParameter("longitude"));
			BigDecimal latitude = new BigDecimal(parameterObject.getStringParameter("latitude"));
			String token = parameterObject.getToken();
			Map<String, Object> model = walkingDogdbService.matchGroup(userId, doglocationId, longitude, latitude);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	/**
	 * 取消匹配接口(当用户为群组第一人时)
	 */
	public ResultObject exitMatchGroup(ParameterObject parameterObject){
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			long doglocationId = Long.parseLong(parameterObject.getStringParameter("doglocationId"));
			BigDecimal longitude = new BigDecimal(parameterObject.getStringParameter("longitude"));
			BigDecimal latitude = new BigDecimal(parameterObject.getStringParameter("latitude"));
			String token = parameterObject.getToken();
			Map<String, Object> model = new HashMap<String, Object>();
			if(walkingDogdbService.exitMatchGroup(userId, doglocationId, longitude, latitude)==1){
				model.put("flag", 1);
				model.put("msg", "退出匹配成功");
			}else{
				model.put("flag", 0);
				model.put("msg", "退出匹配失败");
			}
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	/**
	 * 包括自己的遛狗组成员
	 */
	public ResultObject readGroupUserAndI(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			long doglocationId = Long.parseLong(parameterObject.getStringParameter("doglocationId"));
			List<User> list = walkingDogdbService.readGroupUsersAndI(userId,doglocationId);
			List<Map<String,Object>> model = new ArrayList<Map<String,Object>>();
			for (User user : list) {
				Map<String,Object> ma = new HashMap();
				ma.put("headpic", user.getHeadpic()==null?"": user.getHeadpic());
				ma.put("nickname", user.getNickname()==null?"": user.getNickname());
				ma.put("userId", user.getUserId());
				Friends friends = new Friends();
				friends.setUserId(userId);
				friends.setFriendUserId(user.getUserId());
				ma.put("isFriends",friendsdbService.selectUserIsFriend(friends));//返回1 是好友   0 不是好友
				model.add(ma);
			}
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	
	/**
	 * 73.	获取用户所在群组信息
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getJoinGroupKeys(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			String token = parameterObject.getToken();
			Map<String, Object> model = walkingDogdbService.getJoinGroupKeys(userId);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}

	
	public ResultObject endWalkingDog(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			String token = parameterObject.getToken();
			Long doglocationId = parameterObject.getLongParameter("doglocationId");
			BigDecimal longitude = new BigDecimal(parameterObject.getStringParameter("longitude"));
			BigDecimal latitude = new BigDecimal(parameterObject.getStringParameter("latitude"));
			Map<String, Object> model = walkingDogdbService.endWalkingDog(userId, doglocationId, longitude, latitude);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(model);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
}
