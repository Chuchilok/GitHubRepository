package com.dogpro.dao;

import com.dogpro.domain.model.ThirdParty;
import com.dogpro.domain.model.ThirdPartyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ThirdPartyMapper {
    int countByExample(ThirdPartyExample example);

    int deleteByExample(ThirdPartyExample example);

    int deleteByPrimaryKey(Long thirdpartyId);

    int insert(ThirdParty record);

    int insertSelective(ThirdParty record);

    List<ThirdParty> selectByExample(ThirdPartyExample example);

    ThirdParty selectByPrimaryKey(Long thirdpartyId);

    int updateByExampleSelective(@Param("record") ThirdParty record, @Param("example") ThirdPartyExample example);

    int updateByExample(@Param("record") ThirdParty record, @Param("example") ThirdPartyExample example);

    int updateByPrimaryKeySelective(ThirdParty record);

    int updateByPrimaryKey(ThirdParty record);
    
    ThirdParty selectByUidAndType(@Param("userId") Long userId, @Param("type") int type);
}