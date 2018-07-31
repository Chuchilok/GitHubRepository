package com.dogpro.dao;

import com.dogpro.domain.model.FriendsNote;
import com.dogpro.domain.model.FriendsNoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendsNoteMapper {
    int countByExample(FriendsNoteExample example);

    int deleteByExample(FriendsNoteExample example);

    int deleteByPrimaryKey(Long friendsnoteid);

    int insert(FriendsNote record);

    int insertSelective(FriendsNote record);

    List<FriendsNote> selectByExample(FriendsNoteExample example);

    FriendsNote selectByPrimaryKey(Long friendsnoteid);

    int updateByExampleSelective(@Param("record") FriendsNote record, @Param("example") FriendsNoteExample example);

    int updateByExample(@Param("record") FriendsNote record, @Param("example") FriendsNoteExample example);

    int updateByPrimaryKeySelective(FriendsNote record);

    int updateByPrimaryKey(FriendsNote record);
}