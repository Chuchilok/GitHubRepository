package com.dogpro.service.impl.dbservice;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Flags.Flag;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.dao.MqttUserMapper;
import com.dogpro.domain.model.MqttUser;
import com.dogpro.domain.model.MqttUserExample;
import com.dogpro.service.dbservice.MqttUserdbService;

@Service("MqttUserdbService")
public class MqttUserdbServiceImpl implements MqttUserdbService {
	@Autowired
	private MqttUserMapper mqttUserMapper;

	public Map<String, Object> addMqttUser(Long userId, String username,
			String pwds) {
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		try {
			// 判断该userId 是否存在mqtt用户
			MqttUserExample mExample = new MqttUserExample();
			MqttUserExample.Criteria mCriteria = mExample.createCriteria();
			mCriteria.andUseridEqualTo(userId);
			List<MqttUser> mqttUsers = mqttUserMapper.selectByExample(mExample);
			if (mqttUsers.isEmpty()) {
				MqttUser mqttUser = new MqttUser();
				Date time = new Date();
				mqttUser.setUserid(userId);
				mqttUser.setUsername(username);
				//密码 sha256编码
				mqttUser.setPassword(getSHA256Str(pwds));
				mqttUser.setState(1);
				mqttUser.setIsSuperuser(false);
				mqttUser.setSalt("1");
				mqttUser.setCreated(time);
				mqttUser.setAddtimes(time);
				mqttUser.setUpdatetimes(time);
				// 插入数据
				if (mqttUserMapper.insertSelective(mqttUser) == 1) {
					flag = 1;
					msg = "添加成功";
				} else {
					flag = 0;
					msg = "插入数据失败";
				}
			} else {
				// 存在该 mqtt用户 修改 其密码
				MqttUser mqttUser = mqttUsers.get(0);
				mqttUser.setPassword(getSHA256Str(pwds));
				Date time = new Date();
				mqttUser.setUpdatetimes(time);
				// 修改数据
				if (mqttUserMapper.updateByPrimaryKeySelective(mqttUser) == 1) {
					flag = 1;
					msg = "修改成功";
				} else {
					flag = 0;
					msg = "修改数据失败";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			flag = 0;
			msg = "异常错误";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}

	public Map<String, Object> alterPwdsByUserId(Long userId, String pwds) {
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		try {
			// 判断该userId 是否存在mqtt用户
			MqttUserExample mExample = new MqttUserExample();
			MqttUserExample.Criteria mCriteria = mExample.createCriteria();
			mCriteria.andUseridEqualTo(userId);
			List<MqttUser> mqttUsers = mqttUserMapper.selectByExample(mExample);
			if (!mqttUsers.isEmpty()) {
				// 修改用户 mqtt 密码
				MqttUser mqttUser = mqttUsers.get(0);
				//密码 sha256编码
				mqttUser.setPassword(getSHA256Str(pwds));
				Date time = new Date();
				mqttUser.setUpdatetimes(time);
				// 修改数据
				if (mqttUserMapper.updateByPrimaryKeySelective(mqttUser) == 1) {
					flag = 1;
					msg = "修改成功";
				} else {
					flag = 0;
					msg = "修改数据失败";
				}
			} else {
				flag = 0;
				msg = "该mqtt用户不存在";
			}
		} catch (Exception e) {
			flag = 0;
			msg = "异常错误";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}

	public Map<String, Object> getMqttUser(Long userId) {
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		String mqttusername = "";
		String mqttpassword = "";
		try {
			// 判断该userId 是否存在mqtt用户
			MqttUserExample mExample = new MqttUserExample();
			MqttUserExample.Criteria mCriteria = mExample.createCriteria();
			mCriteria.andUseridEqualTo(userId);
			List<MqttUser> mqttUsers = mqttUserMapper.selectByExample(mExample);
			if (!mqttUsers.isEmpty()) {
				MqttUser mqttUser = mqttUsers.get(0);
				mqttusername = mqttUser.getUsername();
				mqttpassword = mqttUser.getPassword();
				flag = 1;
				msg = "获取成功";
			} else {
				flag = 0;
				msg = "该mqtt用户不存在";
			}
		} catch (Exception e) {
			flag = 0;
			msg = "异常错误";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		model.put("mqttusername", mqttusername);
		model.put("mqttpassword", mqttpassword);
		return model;

	}

	public Map<String, Object> alterUsernameByUserId(Long userId,
			String username) {
		// 返回类型
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String msg = "";
		try {
			// 判断该userId 是否存在mqtt用户
			MqttUserExample mExample = new MqttUserExample();
			MqttUserExample.Criteria mCriteria = mExample.createCriteria();
			mCriteria.andUseridEqualTo(userId);
			List<MqttUser> mqttUsers = mqttUserMapper.selectByExample(mExample);
			if (!mqttUsers.isEmpty()) {
				// 修改用户 mqtt 密码
				MqttUser mqttUser = mqttUsers.get(0);
				mqttUser.setUsername(username);
				Date time = new Date();
				mqttUser.setUpdatetimes(time);
				// 修改数据
				if (mqttUserMapper.updateByPrimaryKeySelective(mqttUser) == 1) {
					flag = 1;
					msg = "修改成功";
				} else {
					flag = 0;
					msg = "修改数据失败";
				}
			} else {
				flag = 0;
				msg = "该mqtt用户不存在";
			}
		} catch (Exception e) {
			flag = 0;
			msg = "异常错误";
		}
		model.put("flag", flag);
		model.put("msg", msg);
		return model;
	}
	
	public static String getSHA256Str(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }
	
	

}
