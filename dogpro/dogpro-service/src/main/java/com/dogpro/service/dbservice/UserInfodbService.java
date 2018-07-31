package com.dogpro.service.dbservice;
 
import java.util.Map;

import com.dogpro.domain.model.User;


 

public interface UserInfodbService {
	public User  chickUserByPhone(String phone); 
	public int insertSelective(User user);
	public int updateByPrimaryKeySelective(User record);
	public User selectByPrimaryKey(Long userId);
	public Map<String, Object> getUserInfo(Long userId);
	public Map<String, Object> alterUserInfo(User user);
	public Map<String, Object> AlterUserPwds(Long userId,String pswd,String pswd1,String pswd2);
	public Map<String, Object> alterUserPhone(Long userId,String phone,String captcha);
	public boolean updateUserOfflineTime(Long userId,Long offlineTime);
	public Map<String, Object> getIMinfo(Long userId,Long friendUserId);
	//第三方完善信息
	public Map<String, Object> thirdPartyFillInfo(Long userId ,User user,String openid,int thirdPartyType,int type,String pushtoken,String phone,String captcha);
}
