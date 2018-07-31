package com.dogpro.admin.service.dbservice;

import java.util.List;

import com.dogpro.domain.model.Discuss;
import com.dogpro.domain.model.DiscussExample;
import com.dogpro.domain.model.FriendCircle;
import com.dogpro.domain.model.FriendCircleExample;
import com.dogpro.domain.model.FriendCircleMedia;
import com.dogpro.domain.model.FriendCircleMediaExample;

/**
 * 后台管理朋友圈 ()
 * @author Administrator
 *
 */
public interface AdminFriendsCircledbService {
	//获取一个朋友圈对象，id
	FriendCircle getFriendCirById(long friendCirId);
	/**
	 * 所有朋友圈， 分页、
	 * str 如果为空，所有，，，，否则，按str(号码)查询朋友圈
	 * @param pageNO   第几页   (0)
	 * @param pageSize  每页显示多少 (5)
	 * @return 朋友圈集合
	 */
	List<FriendCircle> allianceList(String str,int pageNO, int pageSize);

	/**
	 * 获取对应朋友圈的媒体资源
	 * @param friendcirId  朋友圈id
	 * @return 媒体资源集合
	 */
	List<FriendCircleMedia> getFriendCirsMediaByFriendCirId(Long friendcirId);
	/**
	 *  通过用户id 获取对应朋友圈
	 * @param userId 用户id
	 * @param pageNO   第几页   (0)
	 * @param pageSize  每页显示多少 (5)
	 * @return 朋友圈集合
	 */
	List<FriendCircle> allianceList(long userId, int pageNO, int pageSize);
	/**
	 * 删除朋友圈  通过朋友圈id
	 * @param friendCirId 朋友圈id
	 * @return 1 删除成功    0 删除失败     -1  未知错误，请联系管理员
	 */
	int deleteAllianceByAdmin(long friendCirId);

	int getCountByExample(FriendCircleExample d);
	/**
	 * 朋友圈搜索集合
	 * @param example
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	List<FriendCircle> searchFriendCir(FriendCircleExample example, int pageNO, int pageSize);
	/**
	 * 详情朋友圈媒体
	 * @param example
	 * @param pageNO
	 * @param pageSize
	 * @return
	 */
	List<FriendCircleMedia> FriendMediaDetail(FriendCircleMediaExample example,
			int pageNO, int pageSize);
	//朋友圈详细评论
	List<Discuss> findDisussByFriendCir(DiscussExample example, int pageNO,
			int pageSize);
	//详细评论计数
	int countDiscussByFriendCir(DiscussExample example);
	//获取评论对象 通过id
	Discuss getDiscussById(Long id);
	
}
