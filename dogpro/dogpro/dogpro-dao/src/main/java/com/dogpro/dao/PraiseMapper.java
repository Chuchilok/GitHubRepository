package com.dogpro.dao;

import com.dogpro.domain.model.Praise;
import com.dogpro.domain.model.PraiseExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PraiseMapper {
    int countByExample(PraiseExample example);

    int deleteByExample(PraiseExample example);

    int deleteByPrimaryKey(Long praiseId);

    int insert(Praise record);

    int insertSelective(Praise record);

    List<Praise> selectByExample(PraiseExample example);

    Praise selectByPrimaryKey(Long praiseId);

    int updateByExampleSelective(@Param("record") Praise record, @Param("example") PraiseExample example);

    int updateByExample(@Param("record") Praise record, @Param("example") PraiseExample example);

    int updateByPrimaryKeySelective(Praise record);

    int updateByPrimaryKey(Praise record);
    
    List<Map<String, Object>> friendCirclePraise(@Param("friendCirId")Long friendCirId);
}