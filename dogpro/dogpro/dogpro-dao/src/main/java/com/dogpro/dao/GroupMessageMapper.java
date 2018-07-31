package com.dogpro.dao;

import com.dogpro.domain.model.GroupMessage;
import com.dogpro.domain.model.GroupMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupMessageMapper {
    int countByExample(GroupMessageExample example);

    int deleteByExample(GroupMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupMessage record);

    int insertSelective(GroupMessage record);

    List<GroupMessage> selectByExampleWithBLOBs(GroupMessageExample example);

    List<GroupMessage> selectByExample(GroupMessageExample example);

    GroupMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupMessage record, @Param("example") GroupMessageExample example);

    int updateByExampleWithBLOBs(@Param("record") GroupMessage record, @Param("example") GroupMessageExample example);

    int updateByExample(@Param("record") GroupMessage record, @Param("example") GroupMessageExample example);

    int updateByPrimaryKeySelective(GroupMessage record);

    int updateByPrimaryKeyWithBLOBs(GroupMessage record);

    int updateByPrimaryKey(GroupMessage record);
}