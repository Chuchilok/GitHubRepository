package com.dogpro.service.webapi;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

/**
 * 朋友接口(IM)注释的在 dogpro
 * @author Administrator
 *
 */
public interface FriendsService {
	
	//好友列表
	public ResultObject friendList(ParameterObject parameterObject);
	//删除好友
	public ResultObject delFriends(ParameterObject parameterObject);
	
	//请求加好友
	public ResultObject requestAddFriend(ParameterObject parameterObject);
	
	//增加好友验证
	public ResultObject addFriendValidation(ParameterObject parameterObject);
	
	//增加好友验证
	public ResultObject searchFriends(ParameterObject parameterObject);

	/**
	 * 67.	好友主页详情接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject friendHomeDetail(ParameterObject parameterObject);
	/**
	 * 68.	修改好友备注接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject alterFriendNote(ParameterObject parameterObject);
	/**
	 * 69.	好友主页-相册接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject friendPhotoAlbum(ParameterObject parameterObject);
	/**
	 * 69.	好友主页-朋友圈接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject friendFriendCircle(ParameterObject parameterObject);
	/**
	 * 70.	好友主页-印象接口
	 * @param parameterObject
	 * @return
	 */
	public ResultObject friendHomeImpression(ParameterObject parameterObject);
	/**
	 * 42.	新的好友请求列表
	 * @param parameterObject
	 * @return
	 */
	public ResultObject newFriendsList(ParameterObject parameterObject);
}
