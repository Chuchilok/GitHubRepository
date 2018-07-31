package com.dogpro.admin.service.webapi;

import com.dogpro.common.Interfacetool.DataGridResult;
import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

/**
 * 后台管理
 * @author 许明诚
 *
 */
public interface AdminService {
	/**
	 * 46.管理用户获取注册验证码
	 * @param parameterObject
	 * @return
	 */
	public ResultObject requestCaptcha(ParameterObject parameterObject);	
	/**
	 * 47.管理用户注册
	 * @param parameterObject
	 * @return
	 */
	public ResultObject register(ParameterObject parameterObject);	
	/**
	 * 48.管理用户登陆
	 * @param parameterObject
	 * @return
	 */
	public ResultObject adminLogin(ParameterObject parameterObject);	
	/**
	 * 49.查看用户列表接口（AdminUser查看User）
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult userList(ParameterObject parameterObject);
	/**
	 * 50.根据手机号码查询用户
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult searchUserByPhone(ParameterObject parameterObject);
	/**
	 * 51.禁用用户
	 * @param parameterObject
	 * @return
	 */
	public ResultObject disableUser(ParameterObject parameterObject);
	/**
	 * 52.查看所有朋友圈
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult allianceList(ParameterObject parameterObject);
	/**
	 * 53.关键词搜索朋友圈
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult searchAlliance(ParameterObject parameterObject);
	/**
	 * 54.查看用户朋友圈
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult userAlliance(ParameterObject parameterObject);
	/**
	 * 55.删除朋友圈（AdminUser）
	 * @param parameterObject
	 * @return
	 */
	public ResultObject deleteAllianceByAdmin(ParameterObject parameterObject);
	/**
	 * 56.查看所有投诉（按类型分）
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult complaintByType(ParameterObject parameterObject);
	/**
	 * 57.核查投诉
	 * @param parameterObject
	 * @return
	 */
	public ResultObject checkComplant(ParameterObject parameterObject);
	/**
	 * 58.查看意见反馈
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult feedbackList(ParameterObject parameterObject);
	/**
	 * 59.查看遛狗地点
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult locationList(ParameterObject parameterObject);
	
	/**
	 * 60.新增遛狗地点接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject addLocation(ParameterObject parameterObject);
	
	/**
	 * 60.修改遛狗地点接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject alterLocation(ParameterObject parameterObject);
	/**
	 * 61.删除遛狗地点
	 * @param parameterObject
	 * @return
	 */
	public ResultObject deleteLocation(ParameterObject parameterObject);
	/**
	 * 62.查看所有遛狗组
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult walkingDogGroupList(ParameterObject parameterObject);
	/**
	 *  63.遛狗组详情信息 分页
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult dogGroupDetail(ParameterObject parameterObject);
	/**
	 * 64.踢出遛狗组成员
	 * @param parameterObject
	 * @return
	 */
	public ResultObject kickGroup(ParameterObject parameterObject);
	/**
	 * 65.查看遛狗轨迹
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult getTrack(ParameterObject parameterObject);
	/**
	 * 65.查看遛狗轨迹(详情用户轨迹) 分页  通过   userid  和  遛狗地点id   获取详情轨迹
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult getTrackDetail(ParameterObject parameterObject);
	/**
	 * 66.查看所有消息（AdminUser）
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult getAllMsg(ParameterObject parameterObject);
	/**
	 * 67.朋友圈搜索接口 分页
	 * @param parameterObject
	 * @return 
	 */
	public DataGridResult searchFriendsCir(ParameterObject parameterObject);
	/**
	 * 68.朋友圈详细评论接口 分页
	 * @param parameterObject
	 * @return 
	 */
	public DataGridResult findDisussByFriendCir(ParameterObject parameterObject);
	/**
	 * 68.朋友圈媒体资源详情详细评论接口 分页
	 * @param parameterObject
	 * @return 
	 */
	public ResultObject getFriendCirsMediaByFriendCirId(ParameterObject parameterObject);
	
	/**
	 * 获取的是遛狗地点的区域空间   
	 * @param parameterObject
	 * @return 
	 */
	public ResultObject findDogLocationAreaSpace(ParameterObject parameterObject);
	/**
	 * 添加 遛狗地点的区域空间   
	 * @param parameterObject
	 * @return
	 */
	public ResultObject addDogLocationAreaSpace(ParameterObject parameterObject);
	/**
	 * 编辑 遛狗地点的区域空间   
	 * @param parameterObject
	 * @return
	 */
	public ResultObject modifyDogLocationAreaSpace(ParameterObject parameterObject);
	/**
	 * 41.	获取当前在线人数   
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getTotalOnlineUsers(ParameterObject parameterObject);
	/**
	 * 42.	分页获取在线人数记录数据  
	 * @param parameterObject
	 * @return
	 */
	public DataGridResult getOnlineRecord(ParameterObject parameterObject);
	/**
	 * 43.	在线人数记录插入数据库
	 * @param parameterObject
	 * @return
	 */
	public ResultObject onlineRecordToDB(ParameterObject parameterObject);
}
