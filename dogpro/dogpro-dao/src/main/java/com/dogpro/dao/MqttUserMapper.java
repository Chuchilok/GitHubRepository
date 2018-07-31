package com.dogpro.dao;

import com.dogpro.domain.model.MqttUser;
import com.dogpro.domain.model.MqttUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MqttUserMapper {
    int countByExample(MqttUserExample example);

    int deleteByExample(MqttUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MqttUser record);

    int insertSelective(MqttUser record);

    List<MqttUser> selectByExample(MqttUserExample example);

    MqttUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MqttUser record, @Param("example") MqttUserExample example);

    int updateByExample(@Param("record") MqttUser record, @Param("example") MqttUserExample example);

    int updateByPrimaryKeySelective(MqttUser record);

    int updateByPrimaryKey(MqttUser record);
}