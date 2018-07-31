package com.dogpro.dao;

import com.dogpro.domain.model.User;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.domain.model.WalkingDogGroupExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import util.PaginationUtil;

public interface WalkingDogGroupMapper {
    int countByExample(WalkingDogGroupExample example);
    int countByExampleGroup(WalkingDogGroupExample example);
    int deleteByExample(WalkingDogGroupExample example);

    int deleteByPrimaryKey(Long groupid);

    int insert(WalkingDogGroup record);

    int insertSelective(WalkingDogGroup record);

    List<WalkingDogGroup> selectByExample(WalkingDogGroupExample example);

    WalkingDogGroup selectByPrimaryKey(Long groupid);

    int updateByExampleSelective(@Param("record") WalkingDogGroup record, @Param("example") WalkingDogGroupExample example);

    int updateByExample(@Param("record") WalkingDogGroup record, @Param("example") WalkingDogGroupExample example);

    int updateByPrimaryKeySelective(WalkingDogGroup record);

    int updateByPrimaryKey(WalkingDogGroup record);

	List<WalkingDogGroup> selectToGroupByExample(WalkingDogGroupExample example);
	
	WalkingDogGroup selectByUidLid(@Param("userId") Long userId,@Param("dogLocationId") Long dogLocationId);
	
	WalkingDogGroup selectByUid(@Param("userId") Long userId);
	
	List<User> readGroupUser(@Param("userId") Long userId,@Param("dogLocationId")Long doglocationId,@Param("isContain") Integer isContain,@Param("pagination")PaginationUtil pagination);
}