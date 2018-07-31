package com.dogpro.service.impl.webapi;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.dao.FriendCircleMapper;
import com.dogpro.domain.model.Praise;
import com.dogpro.service.dbservice.FriendCircledbService;
import com.dogpro.service.dbservice.PraisedbService;
import com.dogpro.service.dbservice.PushdbService;
import com.dogpro.service.dbservice.RedisdbService;
import com.dogpro.service.webapi.PraiseService;

@Service("PraiseService")
public class PraiseServiceImpl implements PraiseService {

	@Autowired
	private PraisedbService praisedbService;
	@Autowired
	private FriendCircleMapper friendCircleMapper;
	@Autowired
	private PushdbService pushdbService;
	@Autowired
	private RedisdbService redisdbService;
	@Autowired
	private FriendCircledbService friendCircledbService;
	
	
	public ResultObject clickPraise(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			long friendcirId = Long.parseLong(parameterObject.getStringParameter("friendCirId"));
			long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
			String praisestar = parameterObject.getStringParameter("praiseStar");
			String token = parameterObject.getToken();
			//朋友点赞
			Praise praise = new Praise();
			praise.setFriendcirId(friendcirId);
			praise.setPraisestar(praisestar);
			praise.setUserId(userId);
			praise.setState(1);
			int result = praisedbService.clickPraise(praise);
			int flag = 0;
			String msg = "";
			//如果不存在返回   -2
			//如果已经点赞返回   -3
			//朋友圈的ID
			if (result>0) {//点赞成功
				flag = 1;
				msg = "点赞成功";
				//修改朋友圈更新code
				friendCircledbService.setFCupdateCode(friendcirId, new Date().getTime());
				//发布推送消息
				Long revUid = (friendCircleMapper.selectByPrimaryKey(friendcirId)).getUserId();
				String nickname = redisdbService.getUserNickname(userId);
				if(nickname==null){
					nickname = "有人";
				}
				pushdbService.sendPushMsg(5, nickname+" 给你的朋友圈点了赞", revUid,userId,praise.getFriendcirId());
			}else if(result==-2){
				flag = 0;
				msg = "该朋友圈已被删除";
			}else if(result==-4){
				flag = 1;
				msg = "点赞成功";
				//修改朋友圈更新code
				friendCircledbService.setFCupdateCode(friendcirId, new Date().getTime());
				//发布推送消息
				Long revUid = (friendCircleMapper.selectByPrimaryKey(friendcirId)).getUserId();
				String nickname = redisdbService.getUserNickname(userId+"");
				if(nickname==null){
					nickname = "有人";
				}
				pushdbService.sendPushMsg(5, nickname+" 给你的朋友圈点了赞", revUid,userId,friendcirId);
			}else if(result==-5){
				flag = 1;
				msg = "取消点赞成功";
				//修改朋友圈更新code
				friendCircledbService.setFCupdateCode(friendcirId, new Date().getTime());
			}
			Map<String,Object> model = new HashMap<String, Object>();
			model.put("flag", flag);
			model.put("msg", msg);
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
	//查看单条朋友圈点赞情况
	public ResultObject friendCirclePraise(ParameterObject parameterObject){
		long friendCircleId = Long.parseLong(parameterObject.getStringParameter("friendCirId"));
		long userId = Long.parseLong(parameterObject.getStringParameter("userId"));
		String token = parameterObject.getToken();
		ResultObject retObj = new ResultObject();
		try {
			List<Map<String, Object>> modelList = praisedbService.friendCirclePraise(friendCircleId);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setResult(modelList);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
		
	}
}
