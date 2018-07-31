package com.dogpro.service.dbservice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dogpro.domain.model.Ucaptcha;
import com.dogpro.domain.model.UcaptchaExample;
import com.dogpro.domain.model.User;

public interface UcaptchadbService {
	public List<Ucaptcha> selectByExample(UcaptchaExample example);
	public int insertSelective(Ucaptcha record);
	public int updateByExampleSelective(Ucaptcha record,UcaptchaExample example);
	public int updateByPrimaryKeySelective(Ucaptcha record);
	//获取验证码
	public Map<String, Object> getCaptchaByType(String phone,int type);
}
