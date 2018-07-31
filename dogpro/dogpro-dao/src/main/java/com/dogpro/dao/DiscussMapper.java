package com.dogpro.dao;

import com.dogpro.domain.model.Discuss;
import com.dogpro.domain.model.DiscussExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import util.PaginationUtil;

public interface DiscussMapper {
    int countByExample(DiscussExample example);

    int deleteByExample(DiscussExample example);

    int deleteByPrimaryKey(Long discussId);

    int insert(Discuss record);

    int insertSelective(Discuss record);

    List<Discuss> selectByExample(DiscussExample example);

    Discuss selectByPrimaryKey(Long discussId);

    int updateByExampleSelective(@Param("record") Discuss record, @Param("example") DiscussExample example);

    int updateByExample(@Param("record") Discuss record, @Param("example") DiscussExample example);

    int updateByPrimaryKeySelective(Discuss record);

    int updateByPrimaryKey(Discuss record);
    
    List<Map<String, Object>> allianceDetailDiscuss(@Param("friendCirId")Long friendCirId,@Param("pagination")PaginationUtil pagination);
}