package com.dogpro.dao;

import com.dogpro.domain.model.OnlineRecord;
import com.dogpro.domain.model.OnlineRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnlineRecordMapper {
    int countByExample(OnlineRecordExample example);

    int deleteByExample(OnlineRecordExample example);

    int deleteByPrimaryKey(Long onlineRecordId);

    int insert(OnlineRecord record);

    int insertSelective(OnlineRecord record);

    List<OnlineRecord> selectByExample(OnlineRecordExample example);

    OnlineRecord selectByPrimaryKey(Long onlineRecordId);

    int updateByExampleSelective(@Param("record") OnlineRecord record, @Param("example") OnlineRecordExample example);

    int updateByExample(@Param("record") OnlineRecord record, @Param("example") OnlineRecordExample example);

    int updateByPrimaryKeySelective(OnlineRecord record);

    int updateByPrimaryKey(OnlineRecord record);
}