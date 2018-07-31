package com.dogpro.dao;

import com.dogpro.domain.model.Impression;
import com.dogpro.domain.model.ImpressionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImpressionMapper {
    int countByExample(ImpressionExample example);

    int deleteByExample(ImpressionExample example);

    int deleteByPrimaryKey(Long impressionId);

    int insert(Impression record);

    int insertSelective(Impression record);

    List<Impression> selectByExample(ImpressionExample example);

    Impression selectByPrimaryKey(Long impressionId);

    int updateByExampleSelective(@Param("record") Impression record, @Param("example") ImpressionExample example);

    int updateByExample(@Param("record") Impression record, @Param("example") ImpressionExample example);

    int updateByPrimaryKeySelective(Impression record);

    int updateByPrimaryKey(Impression record);
}