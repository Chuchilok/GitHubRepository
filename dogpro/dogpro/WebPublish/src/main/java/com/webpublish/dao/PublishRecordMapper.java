package com.webpublish.dao;

import com.webpublish.domain.model.PublishRecord;
import com.webpublish.domain.model.PublishRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PublishRecordMapper {
    int countByExample(PublishRecordExample example);

    int deleteByExample(PublishRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PublishRecord record);

    int insertSelective(PublishRecord record);

    List<PublishRecord> selectByExample(PublishRecordExample example);

    PublishRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PublishRecord record, @Param("example") PublishRecordExample example);

    int updateByExample(@Param("record") PublishRecord record, @Param("example") PublishRecordExample example);

    int updateByPrimaryKeySelective(PublishRecord record);

    int updateByPrimaryKey(PublishRecord record);
}