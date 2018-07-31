package com.dogpro.dao;

import com.dogpro.domain.model.Friends;
import com.dogpro.domain.model.FriendsExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface FriendsMapper {
    int countByExample(FriendsExample example);

    int deleteByExample(FriendsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Friends record);

    int insertSelective(Friends record);

    List<Friends> selectByExample(FriendsExample example);

    Friends selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Friends record, @Param("example") FriendsExample example);

    int updateByExample(@Param("record") Friends record, @Param("example") FriendsExample example);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);

	List<Map<String, Object>> selectByFriends(Long userId);

	Integer selectUserIsFriend(Friends friends);
	
	Friends selectByUidFid(@Param("userId") Long userId, @Param("friendUserId") Long friendUserId);
	
	int countFriendsByUid(Long userId);
	
	List<Map<String, Object>> newFriendsList(@Param("userId") Long userId,@Param("date") Date date);
	
	Map<String, Object> friendHomeDetail(@Param("userId") Long userId,@Param("friendsUserId") Long friendsUserId);
}