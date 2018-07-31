package com.dogpro.dao;

import com.dogpro.domain.model.ServiceRecord;
import com.dogpro.domain.model.ServiceRecordExample;
import com.dogpro.domain.model.extend.ServiceRecord_User;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import util.PaginationUtil;

public interface ServiceRecordMapper {
    int countByExample(ServiceRecordExample example);

    int deleteByExample(ServiceRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ServiceRecord record);

    int insertSelective(ServiceRecord record);

    List<ServiceRecord> selectByExample(ServiceRecordExample example);

    ServiceRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ServiceRecord record, @Param("example") ServiceRecordExample example);

    int updateByExample(@Param("record") ServiceRecord record, @Param("example") ServiceRecordExample example);

    int updateByPrimaryKeySelective(ServiceRecord record);

    int updateByPrimaryKey(ServiceRecord record);
    
    List<ServiceRecord_User> getUserList(@Param("pagination") PaginationUtil paginationUtil);
    
    ServiceRecord getServiceRecordrByUid(@Param("user_id") Long userId);
}