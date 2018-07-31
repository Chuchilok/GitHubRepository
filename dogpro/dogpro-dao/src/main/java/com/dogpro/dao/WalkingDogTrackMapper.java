package com.dogpro.dao;

import com.dogpro.domain.model.WalkingDogTrack;
import com.dogpro.domain.model.WalkingDogTrackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalkingDogTrackMapper {
    int countByExample(WalkingDogTrackExample example);

    int deleteByExample(WalkingDogTrackExample example);

    int deleteByPrimaryKey(Long trackid);

    int insert(WalkingDogTrack record);

    int insertSelective(WalkingDogTrack record);

    List<WalkingDogTrack> selectByExample(WalkingDogTrackExample example);

    WalkingDogTrack selectByPrimaryKey(Long trackid);

    int updateByExampleSelective(@Param("record") WalkingDogTrack record, @Param("example") WalkingDogTrackExample example);

    int updateByExample(@Param("record") WalkingDogTrack record, @Param("example") WalkingDogTrackExample example);

    int updateByPrimaryKeySelective(WalkingDogTrack record);

    int updateByPrimaryKey(WalkingDogTrack record);

	List<WalkingDogTrack> selectUserByExample(WalkingDogTrackExample example);
}