package com.dogpro.dao;

import com.dogpro.domain.model.VersionControl;
import com.dogpro.domain.model.VersionControlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VersionControlMapper {
    int countByExample(VersionControlExample example);

    int deleteByExample(VersionControlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VersionControl record);

    int insertSelective(VersionControl record);

    List<VersionControl> selectByExample(VersionControlExample example);

    VersionControl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VersionControl record, @Param("example") VersionControlExample example);

    int updateByExample(@Param("record") VersionControl record, @Param("example") VersionControlExample example);

    int updateByPrimaryKeySelective(VersionControl record);

    int updateByPrimaryKey(VersionControl record);
}