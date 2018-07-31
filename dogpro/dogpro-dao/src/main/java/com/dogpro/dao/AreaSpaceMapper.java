package com.dogpro.dao;

import com.dogpro.domain.model.AreaSpace;
import com.dogpro.domain.model.AreaSpaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaSpaceMapper {
    int countByExample(AreaSpaceExample example);

    int deleteByExample(AreaSpaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AreaSpace record);

    int insertSelective(AreaSpace record);

    List<AreaSpace> selectByExample(AreaSpaceExample example);

    AreaSpace selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AreaSpace record, @Param("example") AreaSpaceExample example);

    int updateByExample(@Param("record") AreaSpace record, @Param("example") AreaSpaceExample example);

    int updateByPrimaryKeySelective(AreaSpace record);

    int updateByPrimaryKey(AreaSpace record);
}