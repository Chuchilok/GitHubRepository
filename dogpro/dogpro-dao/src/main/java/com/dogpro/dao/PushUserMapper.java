package com.dogpro.dao;

import com.dogpro.domain.model.PushUser;
import com.dogpro.domain.model.PushUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushUserMapper {
    int countByExample(PushUserExample example);

    int deleteByExample(PushUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PushUser record);

    int insertSelective(PushUser record);

    List<PushUser> selectByExample(PushUserExample example);

    PushUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PushUser record, @Param("example") PushUserExample example);

    int updateByExample(@Param("record") PushUser record, @Param("example") PushUserExample example);

    int updateByPrimaryKeySelective(PushUser record);

    int updateByPrimaryKey(PushUser record);
}