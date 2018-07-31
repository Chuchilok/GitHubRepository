package com.dogpro.domain.model.extend;


import java.util.List;

import com.dogpro.domain.model.Impression;
import com.dogpro.domain.model.MqttUser;
import com.dogpro.domain.model.Setting;
import com.dogpro.domain.model.ThirdParty;
import com.dogpro.domain.model.Ucaptcha;
import com.dogpro.domain.model.User;

public class UserExtend extends User{
	//验证码
	private Ucaptcha ucaptcha;
	//设置
	private Setting setting;
	//mqtt 用户
	private MqttUser mqttUser;
	//第三方登陆 list
	private List<ThirdParty> thirdPartys;
	//印象 list
	private List<Impression> impressions;
	public Ucaptcha getUcaptcha() {
		return ucaptcha;
	}
	public void setUcaptcha(Ucaptcha ucaptcha) {
		this.ucaptcha = ucaptcha;
	}
	public Setting getSetting() {
		return setting;
	}
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	public MqttUser getMqttUser() {
		return mqttUser;
	}
	public void setMqttUser(MqttUser mqttUser) {
		this.mqttUser = mqttUser;
	}
	public List<ThirdParty> getThirdPartys() {
		return thirdPartys;
	}
	public void setThirdPartys(List<ThirdParty> thirdPartys) {
		this.thirdPartys = thirdPartys;
	}
	public List<Impression> getImpressions() {
		return impressions;
	}
	public void setImpressions(List<Impression> impressions) {
		this.impressions = impressions;
	}
	
	
}
