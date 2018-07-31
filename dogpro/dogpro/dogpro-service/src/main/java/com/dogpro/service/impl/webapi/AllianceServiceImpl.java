package com.dogpro.service.impl.webapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;
import com.dogpro.domain.model.Discuss;
import com.dogpro.domain.model.DiscussExample;
import com.dogpro.domain.model.FriendCircle;
import com.dogpro.domain.model.FriendCircleExample;
import com.dogpro.domain.model.FriendCircleExample.Criteria;
import com.dogpro.domain.model.FriendCircleMedia;
import com.dogpro.domain.model.FriendCircleMediaExample;
import com.dogpro.domain.model.Praise;
import com.dogpro.domain.model.PraiseExample;
import com.dogpro.domain.model.User;
import com.dogpro.service.dbservice.DiscussdbService;
import com.dogpro.service.dbservice.FriendCircleMediadbService;
import com.dogpro.service.dbservice.FriendCircledbService;
import com.dogpro.service.dbservice.PraisedbService;
import com.dogpro.service.dbservice.UserInfodbService;
import com.dogpro.service.webapi.AllianceService;

@Service("AllianceService")
public class AllianceServiceImpl implements AllianceService {
	@Autowired
	private UserInfodbService userserviceImpl;
	@Autowired
	private FriendCircledbService friendCircledbService;
	@Autowired
	private PraisedbService praiseService;
	@Autowired
	private DiscussdbService discussService;
	@Autowired
	private FriendCircleMediadbService friendCircleMediaService;

	// 联盟列表
	public ResultObject allianceList(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		try {
			long userId = parameterObject.getLongParameter("userId");
			int pageSize = parameterObject.getIntegerParameter("pageSize");
			Long friendCirId = parameterObject.getLongParameter("friendCirId");
			Long topfriendCirCode = parameterObject
					.getLongParameter("topfriendCirCode");
			Long bottomfriendCirCode = parameterObject
					.getLongParameter("bottomfriendCirCode");
			Long requestCode = parameterObject.getLongParameter("requestCode");
			int isRefresh = parameterObject.getIntegerParameter("isRefresh");
			Map<String, Object> modelList = friendCircledbService
					.getallianceList(userId, pageSize, friendCirId, isRefresh,
							topfriendCirCode, bottomfriendCirCode, requestCode);
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

	// 获取联盟详情
	public ResultObject allianceDetail(ParameterObject parameterObject) {
		ResultObject retObj = new ResultObject();
		Long userId = Long.parseLong(parameterObject
				.getStringParameter("userId"));
		Long friendCirId = Long.parseLong(parameterObject
				.getStringParameter("friendCirId"));

		String token = parameterObject.getToken();
		try {
			Map<String, Object> model = friendCircledbService.getFCdetail(
					friendCirId, userId);
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

	// 发布朋友圈
	public ResultObject publishFriendCir(ParameterObject parameterObject) {
		// 返回对象
		ResultObject retObj = new ResultObject();
		try {
			String token = parameterObject.getToken();
			JSONObject jsonObject = new JSONObject(
					parameterObject.getParameterObject());
			FriendCircle friendCircle = JSONObject.toJavaObject(jsonObject,
					FriendCircle.class);
			List<Map<String, Object>> friendCircleMedia = (List<Map<String, Object>>) parameterObject
					.getParameter("friendCircleMedia");
			HttpServletRequest request = (HttpServletRequest) parameterObject
					.getParameter("requestobj");
			Map<String, Object> model = new HashMap<String, Object>();
			if (friendCircledbService.publishFC(friendCircle,
					friendCircleMedia, request) == 1) {
				model.put("flag", 1);
				model.put("msg", "发布成功");
			} else {
				model.put("flag", 0);
				model.put("msg", "发布失败");
			}
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

	// 我的 详情接口
	public ResultObject mine(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		Long userId = Long.parseLong(parameterObject
				.getStringParameter("userId"));
		String token = parameterObject.getStringParameter("token");
		// 返回对象
		ResultObject retObj = new ResultObject();
		try {
			Map<String, Object> model = friendCircledbService.Mine(userId);
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

	// 我的相册接口
	public ResultObject minePhotoAlbum(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		Long userId = Long.parseLong(parameterObject
				.getStringParameter("userId"));
		String token = parameterObject.getStringParameter("token");
		int pageNO = parameterObject.getIntegerParameter("pageNO");
		int pageSize = parameterObject.getIntegerParameter("pageSize");
		// 返回对象
		ResultObject retObj = new ResultObject();
		try {
			List<Map<String, Object>> modelList = friendCircledbService
					.minePhotoAlbum(userId, pageNO, pageSize);
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

	// 我的朋友圈接口
	public ResultObject mineFriendCircle(ParameterObject parameterObject) {
		// TODO Auto-generated method stub
		Long userId = Long.parseLong(parameterObject
				.getStringParameter("userId"));
		String token = parameterObject.getStringParameter("token");
		int pageNO = parameterObject.getIntegerParameter("pageNO");
		int pageSize = parameterObject.getIntegerParameter("pageSize");
		// 返回对象
		ResultObject retObj = new ResultObject();
		try {
			List<Map<String, Object>> modelList = friendCircledbService
					.mineFriendCircle(userId, pageNO, pageSize);
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

	/**
	 * 删除朋友圈
	 */
	public ResultObject delFriendCir(ParameterObject parameterObject) {

		Long friendCirId = Long.parseLong(parameterObject
				.getStringParameter("friendCirId"));
		String token = parameterObject.getToken();
		// 返回对象
		ResultObject retObj = new ResultObject();
		try {
			Map<String, Object> model = new HashMap();
			// state修改状态为 0 删除
			int i = friendCircledbService.delFriendCir(friendCirId);
			String msg = "";
			int flag = 0;
			if (i > 0) {
				flag = 1;
				msg = "删除成功";
			} else if (i == -2) {
				flag = 0;
				msg = "该朋友圈不存在";
			} else {
				flag = 0;
				msg = "删除失败";
			}
			retObj.setState(ResultObject.STATE_SUCCESS);
			retObj.setMessage(ResultObject.Message_STATE_SUCCESS);
			retObj.setCode(ResultObject.CODE_STATE_SUCCESS);
			model.put("flag", flag);
			model.put("msg", msg);
			retObj.setResult(model);
		} catch (Exception e) {
			retObj.setCode(ResultObject.CODE_STATE_FAIL_DEFAULT);
			retObj.setState(ResultObject.STATE_FAIL);
			retObj.setMessage(ResultObject.Message_STATE_FAIL);
		}
		return retObj;
	}
}
