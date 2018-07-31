package com.dogpro.dao;

import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.MessageExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import util.PaginationUtil;

public interface MessageMapper {
    int countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(Long messageid);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExampleWithBLOBs(MessageExample example);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Long messageid);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExampleWithBLOBs(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);
    
    List<Message> selectPrivateUnread(@Param("userId")Long userId, @Param("msgCode")Long msgcode,@Param("pagination")PaginationUtil pagination);
    
    List<Message> selectAllUnread(@Param("userId")Long userId, @Param("dogLocationId")Long dogLocationId,@Param("msgCode")Long msgcode,@Param("pagination")PaginationUtil pagination);

    int countUnread(@Param("userId")Long userId, @Param("dogLocationId")Long dogLocationId,@Param("msgCode")Long msgcode);
    
    List<Message> selectPrivateHistory(@Param("userId")Long userId, @Param("friendUid")Long friendUid,@Param("msgCode")Long msgcode,@Param("pagination")PaginationUtil pagination);
    
    List<Message> selectGroupHistory(@Param("userId")Long userId, @Param("dogLocationId")Long dogLocationId,@Param("msgCode")Long msgcode,@Param("pagination")PaginationUtil pagination);
}