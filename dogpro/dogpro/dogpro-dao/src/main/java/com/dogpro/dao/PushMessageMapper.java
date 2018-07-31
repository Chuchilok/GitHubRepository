package com.dogpro.dao;

import com.dogpro.domain.model.PushMessage;
import com.dogpro.domain.model.PushMessageExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import util.PaginationUtil;

public interface PushMessageMapper {
    int countByExample(PushMessageExample example);

    int deleteByExample(PushMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PushMessage record);

    int insertSelective(PushMessage record);

    List<PushMessage> selectByExampleWithBLOBs(PushMessageExample example);

    List<PushMessage> selectByExample(PushMessageExample example);

    PushMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PushMessage record, @Param("example") PushMessageExample example);

    int updateByExampleWithBLOBs(@Param("record") PushMessage record, @Param("example") PushMessageExample example);

    int updateByExample(@Param("record") PushMessage record, @Param("example") PushMessageExample example);

    int updateByPrimaryKeySelective(PushMessage record);

    int updateByPrimaryKeyWithBLOBs(PushMessage record);

    int updateByPrimaryKey(PushMessage record);
    
    List<Map<String, Object>> getFirendCirclePush(@Param("userId") Long userId, @Param("pagination")PaginationUtil paginationUtil );
}