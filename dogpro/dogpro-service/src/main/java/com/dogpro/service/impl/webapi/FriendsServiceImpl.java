package com.dogpro.service.impl.webapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.domain.model.Friends;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.User;
import com.dogpro.service.dbservice.FriendsdbService;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.dbservice.UserInfodbService;
import com.dogpro.service.webapi.FriendsService;

/**
 * 朋友接口(dogpro)
 * @author Administrator
 *
 */
@Service("FriendsService")
public class FriendsServiceImpl implements FriendsService	{

	@Autowired
	private FriendsdbService friendsdbService;
	@Autowired 
	private RedisdbService redisdbService;
	/**
	 * 好友列表
	 */
	public ResultObject friendList(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			String token = parameterObject.getToken();
			List<Map<String, Object>> model =  friendsdbService.friendListByUserId(userId);
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
	 * 删除好友
	 */ 
	public ResultObject delFriends(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			long friendUserId = Long.parseLong(parameterObject.getStringParameter("friendUserId"));
			int res = friendsdbService.delFriendByUserId(userId,friendUserId);
			String token = parameterObject.getToken();
			String msg = "";
			int flag = 0;
			if (res>0) {
				flag = 1 ;
				msg = "删除成功";
			}else {
				msg = "删除失败";
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
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
	 * 请求加好友
	 */ 
	public ResultObject requestAddFriend(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			String content = parameterObject.getStringParameter("content");
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			long sendUserId = userId;
			long acceptUserId = Long.parseLong(parameterObject.getStringParameter("acceptUserId"));
			float sendLongitude	= Float.parseFloat(parameterObject.getStringParameter("sendLongitude"));
			float sendLatitude = Float.parseFloat(parameterObject.getStringParameter("sendLatitude"));
			int isOpen= parameterObject.getIntegerParameter("isOpen");//是否开放朋友圈
			Message message = new Message();
			message.setType(1);//添加好友请求
			message.setState(0);//未处理状态
			message.setContent(content);
			message.setSenduserid(sendUserId);
			message.setAcceptuserid(acceptUserId);
			message.setSendlongitude(sendLongitude);
			message.setSendlatitude(sendLatitude);
			Long currentTime = new Date().getTime();
			message.setMsgcode(currentTime);
			String token = parameterObject.getToken();
			int res = friendsdbService.requestAddFriend(message,isOpen);
			String msg = "";
			int flag = 0;
			if (res==1) {
				//昵称信息
				Map<String, Object> contentMap = new HashMap<String, Object>();
				String nickname = redisdbService.getUserNickname(userId);
				contentMap.put("nickname", nickname);
				contentMap.put("friendcontent", content);
				JSONObject jsonObject = new JSONObject(contentMap);
				//发送好友请求 IM mqtt信息
				redisdbService.handleMsg(sendUserId, acceptUserId, 1, jsonObject.toJSONString(), currentTime);
				flag = 1 ;
				msg = "发送成功";
			}else if(res == -4){
				msg = "已是好友";
			}else if(res == 2){
				flag = 1 ;
				msg = "添加成功";
			}else {
				msg = "发送失败";
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
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
	 * 增加好友验证
	 */ 
	public ResultObject addFriendValidation(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			//此处修改请求过来的信息(Message)和Friends数据
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			long friendUserId = Long.parseLong(parameterObject.getStringParameter("friendUserId"));
//			String friendComment = parameterObject.getStringParameter("friendComment");
//			long isOpen = Long.parseLong(parameterObject.getStringParameter("isOpen"));//是否开放朋友圈给好友
			float acceptLongitude = Float.parseFloat(parameterObject.getStringParameter("acceptLongitude"));
			float acceptLatitude = Float.parseFloat(parameterObject.getStringParameter("acceptLatitude"));
			Message message = new Message();
			message.setAcceptuserid(friendUserId);//接收者用户
			message.setSenduserid(userId);//发送者用户
			message.setAcceptlongitude(acceptLongitude);
			message.setAcceptlatitude(acceptLatitude);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", "我通过了你的朋友验证请求，现在我们可以开始聊天了");
			JSONObject jsonObject = new JSONObject(map);
			message.setContent(jsonObject.toJSONString());
			Long msgCode = new Date().getTime();
			message.setMsgcode(msgCode);
			message.setState(0);//表示已经处理状态
			message.setType(3);//类型
			int res = friendsdbService.addFriendValidation(message);
			String msg = "";
			int flag = 0;
			if (res>0) {
				Map<String, Object> replyMap = new HashMap<String, Object>();
				String nickname = redisdbService.getUserNickname(userId);
				replyMap.put("nickname", nickname);
				replyMap.put("content", "我通过了你的朋友验证请求，现在我们可以开始聊天了");
				JSONObject replyMapObject = new JSONObject(replyMap);
				//发送好友回复给B
				redisdbService.handleMsg(userId, friendUserId, 2, replyMapObject.toJSONString(), msgCode);
				//发送im信息 给B
				redisdbService.handleMsg(userId, friendUserId, 3, message.getContent(), msgCode);
				//发送好友回复给A
//				redisdbService.handleMsg(friendUserId,userId , 2, message.getContent(), msgCode);
				//发送im信息给A
				redisdbService.handleMsg(friendUserId,userId , 3, message.getContent(), msgCode);
				flag = 1 ;
				msg = "添加成功";
			}else {
				msg = "添加失败";
			}
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
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
	 * 搜索好友添加
	 */
	
	public ResultObject searchFriends(ParameterObject parameterObject) {
		ResultObject retObj=new ResultObject();//返回对象
		try{
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			String keyword = parameterObject.getStringParameter("keyword");
			String token = parameterObject.getToken();
			
			List<Map<String, Object>> models = new ArrayList<Map<String, Object>>();
			List<User> lus = friendsdbService.searchFriends(userId,keyword);
			for (User user : lus) {
				Map<String, Object> map = new HashMap();
				map.put("headpic", user.getHeadpic()==null?"":user.getHeadpic());
				map.put("nickname", user.getNickname()==null?"":user.getNickname());
				map.put("userId", user.getUserId());
				//是否好友 
				models.add(map);
			}
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(models);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	/**
	 * 67.	好友主页详情接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject friendHomeDetail(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		Long userid = parameterObject.getLongParameter("userId");
		Long friendsUserId = parameterObject.getLongParameter("friendsUserId");
		String token = parameterObject.getToken();
		ResultObject retObj=new ResultObject();//返回对象
		try{
			Map<String, Object> model = friendsdbService.friendHomeDetail(userid, friendsUserId);
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
	 * 68.	修改好友备注接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject alterFriendNote(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		String friendnote = parameterObject.getStringParameter("friendnote");
		Long userId = parameterObject.getLongParameter("userId");
		Long friendsUserId = parameterObject.getLongParameter("friendsUserId");
		String token = parameterObject.getToken();
		ResultObject retObj=new ResultObject();//返回对象
		try{
			Map<String, Object> model = friendsdbService.alterFriendNote(userId, friendsUserId, friendnote);
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
	 * 69.	好友主页-相册接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject friendPhotoAlbum(ParameterObject parameterObject){
		Long userId= parameterObject.getLongParameter("userId");
		Long friendsUserId = parameterObject.getLongParameter("friendsUserId");
		String token = parameterObject.getToken();
		int pageNO = parameterObject.getIntegerParameter("pageNO");
		int pageSize = parameterObject.getIntegerParameter("pageSize");
		ResultObject retObj=new ResultObject();//返回对象
		try{
			List<Map<String, Object>> modelList = friendsdbService.friendPhotoAlbum(userId, friendsUserId, pageNO, pageSize);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(modelList);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	
	/**
	 * 69.	好友主页-朋友圈接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject friendFriendCircle(ParameterObject parameterObject){
		Long userId= parameterObject.getLongParameter("userId");
		Long friendsUserId = parameterObject.getLongParameter("friendsUserId");
		String token = parameterObject.getToken();
		int pageNO = parameterObject.getIntegerParameter("pageNO");
		int pageSize = parameterObject.getIntegerParameter("pageSize");
		ResultObject retObj=new ResultObject();//返回对象
		try{
			List<Map<String, Object>> modelList = friendsdbService.friendFriendCircle(userId, friendsUserId, pageNO, pageSize);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(modelList);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	/**
	 * 70.	好友主页-印象接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject friendHomeImpression(ParameterObject parameterObject){
		Long userId= parameterObject.getLongParameter("userId");
		Long friendsUserId = parameterObject.getLongParameter("friendsUserId");
		Integer pageNo = parameterObject.getIntegerParameter("pageNO");
		Integer pageSize = parameterObject.getIntegerParameter("pageSize");
		String token = parameterObject.getToken();
		ResultObject retObj=new ResultObject();//返回对象
		try{
			List<Map<String, Object>> modelList = friendsdbService.friendHomeImpression(userId, friendsUserId,pageNo,pageSize);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setResult(modelList);
		}catch(Exception e){
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
	/**
	 * 42.	新的好友请求列表
	 * @param parameterObject
	 * @return
	 */
	public ResultObject newFriendsList(ParameterObject parameterObject) {
		Long userId= parameterObject.getLongParameter("userId");
		ResultObject retObj=new ResultObject();//返回对象
		try{
			Map<String, Object> model = friendsdbService.newFriendsList(userId);
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
