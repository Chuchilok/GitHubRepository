package com.dogpro.service.dbservice;


import java.util.List;
import java.util.Map;

import com.dogpro.domain.model.Friends;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.User; 

/**
 * 朋友接口(dogpro) 注释的在IM
 * @author Administrator
 *
 */
public interface FriendsdbService {

	List<User> searchFriends(long userId, String keyword);

	//好友列表
	List<Map<String, Object>> friendListByUserId(long userId);

	 int delFriendByUserId(long userId, long friendUserId);

	 int requestAddFriend(Message message,int isOpen);
	
	 int selectUserIsFriend(Friends friends);

	 int addFriendValidation(Message message);

	/**
	 * 好友主页详情
	 * @param userid
	 * @param friendsUserId
	 * @return
	 */
	public Map<String, Object> friendHomeDetail(Long userid,Long friendsUserId);
	/**
	 * 修改好友备注
	 * @param userId
	 * @param friendUserId
	 * @param FriendNote
	 * @return
	 */
	public Map<String, Object> alterFriendNote(Long userId,Long friendUserId,String FriendNote);
	/**
	 * 好友主页-相册
	 * @param userId
	 * @param friendUserId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> friendPhotoAlbum(Long userId,Long friendUserId,int pageNO,int pageSize);
	/**
	 * 好友主页-朋友圈
	 * @param userId
	 * @param friendUserId
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> friendFriendCircle(Long userId,Long friendUserId,int pageNO,int pageSize);
	/**
	 * 好友主页-印象接口
	 * @param userId
	 * @param friendUserId
	 * @return
	 */
	public List<Map<String, Object>> friendHomeImpression(Long userId,Long friendUserId,int pageNo,int pageSize);
	/**
	 * 新的好友请求列表
	 * @param userId
	 * @return
	 */
	public Map<String, Object> newFriendsList(Long userId);
}
