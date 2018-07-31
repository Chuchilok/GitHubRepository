package com.dogpro.dao;

import com.dogpro.domain.model.MessageMedia;
import com.dogpro.domain.model.MessageMediaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageMediaMapper {
    int countByExample(MessageMediaExample example);

    int deleteByExample(MessageMediaExample example);

    int deleteByPrimaryKey(Long msgmediaid);

    int insert(MessageMedia record);

    int insertSelective(MessageMedia record);

    List<MessageMedia> selectByExample(MessageMediaExample example);

    MessageMedia selectByPrimaryKey(Long msgmediaid);

    int updateByExampleSelective(@Param("record") MessageMedia record, @Param("example") MessageMediaExample example);

    int updateByExample(@Param("record") MessageMedia record, @Param("example") MessageMediaExample example);

    int updateByPrimaryKeySelective(MessageMedia record);

    int updateByPrimaryKey(MessageMedia record);
}