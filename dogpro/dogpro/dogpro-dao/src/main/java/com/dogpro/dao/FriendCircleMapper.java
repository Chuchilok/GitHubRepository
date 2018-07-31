package com.dogpro.dao;

import com.dogpro.domain.model.FriendCircle;
import com.dogpro.domain.model.FriendCircleExample;
import com.dogpro.domain.model.extend.FriendCircleExtend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import util.PaginationUtil;

public interface FriendCircleMapper {
    int countByExample(FriendCircleExample example);

    int deleteByExample(FriendCircleExample example);

    int deleteByPrimaryKey(Long friendcirId);

    int insert(FriendCircle record);

    int insertSelective(FriendCircle record);

    List<FriendCircle> selectByExampleWithBLOBs(FriendCircleExample example);

    List<FriendCircle> selectByExample(FriendCircleExample example);

    FriendCircle selectByPrimaryKey(Long friendcirId);

    int updateByExampleSelective(@Param("record") FriendCircle record, @Param("example") FriendCircleExample example);

    int updateByExampleWithBLOBs(@Param("record") FriendCircle record, @Param("example") FriendCircleExample example);

    int updateByExample(@Param("record") FriendCircle record, @Param("example") FriendCircleExample example);

    int updateByPrimaryKeySelective(FriendCircle record);

    int updateByPrimaryKeyWithBLOBs(FriendCircle record);

    int updateByPrimaryKey(FriendCircle record);
    
    int insertAndGetId(FriendCircle record);
    
    int countAlbumByUid(@Param("userId") Long userId);
    
    List<FriendCircleExtend> selectFCextendByExample(@Param("friendCirCode")Long friendCirCode,@Param("isRefresh") int isRefresh,@Param("userId") Long userId,@Param("pagination")PaginationUtil pagination);

    FriendCircleExtend getFCdetail(@Param("friendCirId")Long friendCirId,@Param("userId") Long userId);
    
    List<FriendCircleExtend> mineFriendCircle(@Param("userId") Long userId,@Param("pagination")PaginationUtil pagination);
} 