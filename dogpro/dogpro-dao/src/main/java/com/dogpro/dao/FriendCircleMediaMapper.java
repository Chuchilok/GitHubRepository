package com.dogpro.dao;

import com.dogpro.domain.model.FriendCircleMedia;
import com.dogpro.domain.model.FriendCircleMediaExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import util.PaginationUtil;

public interface FriendCircleMediaMapper {
    int countByExample(FriendCircleMediaExample example);

    int deleteByExample(FriendCircleMediaExample example);

    int deleteByPrimaryKey(Long mediaId);

    int insert(FriendCircleMedia record);

    int insertSelective(FriendCircleMedia record);

    List<FriendCircleMedia> selectByExample(FriendCircleMediaExample example);

    FriendCircleMedia selectByPrimaryKey(Long mediaId);

    int updateByExampleSelective(@Param("record") FriendCircleMedia record, @Param("example") FriendCircleMediaExample example);

    int updateByExample(@Param("record") FriendCircleMedia record, @Param("example") FriendCircleMediaExample example);

    int updateByPrimaryKeySelective(FriendCircleMedia record);

    int updateByPrimaryKey(FriendCircleMedia record);
    
    List<Map<String, Object>> selectFriendCircleMediaByUid(@Param("userId")Long userId,@Param("pagination")PaginationUtil pagination);

    
}