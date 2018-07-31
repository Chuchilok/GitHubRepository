package com.dogpro.dao;

import com.dogpro.domain.model.Ucaptcha;
import com.dogpro.domain.model.UcaptchaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UcaptchaMapper {
    int countByExample(UcaptchaExample example);

    int deleteByExample(UcaptchaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Ucaptcha record);

    int insertSelective(Ucaptcha record);

    List<Ucaptcha> selectByExample(UcaptchaExample example);

    Ucaptcha selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Ucaptcha record, @Param("example") UcaptchaExample example);

    int updateByExample(@Param("record") Ucaptcha record, @Param("example") UcaptchaExample example);

    int updateByPrimaryKeySelective(Ucaptcha record);

    int updateByPrimaryKey(Ucaptcha record);
}