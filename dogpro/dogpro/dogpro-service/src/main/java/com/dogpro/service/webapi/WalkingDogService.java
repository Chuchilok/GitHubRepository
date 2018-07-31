package com.dogpro.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

/**
 * 遛狗接口
 * @author Administrator
 *
 */
public interface WalkingDogService {
	//加入遛狗组
	public ResultObject joinGroup(ParameterObject parameterObject);
	
	//退出遛狗组
	public ResultObject quitGroup(ParameterObject parameterObject);
	
	//读取遛狗组的成员
	public ResultObject readGroupUser(ParameterObject parameterObject);
	
	//上传遛狗轨迹
	public ResultObject uploadTrack(ParameterObject parameterObject);
	/**
	 * 匹配接口
	 */
	public ResultObject matchGroup(ParameterObject parameterObject);
	/**
	 * 取消匹配接口(当用户为群组第一人时)
	 */
	public ResultObject exitMatchGroup(ParameterObject parameterObject);

	//好友列表(包括自己)
	public ResultObject readGroupUserAndI(ParameterObject parameterObject);
	/**
	 * 73.	获取用户所在群组信息
	 * @param parameterObject
	 * @return
	 */
	public ResultObject getJoinGroupKeys(ParameterObject parameterObject);
	
	public ResultObject endWalkingDog(ParameterObject parameterObject);
}
