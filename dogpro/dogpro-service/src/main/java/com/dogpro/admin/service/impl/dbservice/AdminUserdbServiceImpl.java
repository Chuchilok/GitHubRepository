package com.dogpro.admin.service.impl.dbservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.admin.service.dbservice.AdminUserdbService;
import com.dogpro.common.tool.UUIDGenerator;
import com.dogpro.dao.AdminUserMapper;
import com.dogpro.dao.UcaptchaMapper;
import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.AdminUser;
import com.dogpro.domain.model.AdminUserExample;
import com.dogpro.domain.model.Ucaptcha;
import com.dogpro.domain.model.UcaptchaExample;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;

@Service("adminUserdbService")
public class AdminUserdbServiceImpl implements AdminUserdbService {

	@Autowired
	private UcaptchaMapper ucaptchaMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AdminUserMapper adminUserMapper;

	// 管理用户获取注册验证码
	public Map<String, Object> requestCaptcha(String phone, int type) {
		Map<String, Object> model = new HashMap<String, Object>();
		int flag = 0;
		String captcha = "";
		// 检测手机号码可不可用
		UserExample userExample = new UserExample();
		UserExample.Criteria uCriteria = userExample.createCriteria();
		uCriteria.andPhoneEqualTo(phone);
		List<User> users = userMapper.selectByExample(userExample);
		if (!users.isEmpty()) {
			switch (type) {
			case 3:
				flag = 0;
				break;
			case 4:
				flag = 1;
				break;
			default:
				flag = -1;
				break;
			}
		} else {
			switch (type) {
			case 3:
				flag = 1;
				break;
			case 4:
				flag = 0;
				break;
			default:
				flag = -1;
				break;
			}
		}
		// 若手机号码可用，发送验证码，更新验证码数据
		if (flag == 1) {
			captcha = UUIDGenerator.getCaptcha4();
			UcaptchaExample ucaptchaExample = new UcaptchaExample();
			UcaptchaExample.Criteria criteria = ucaptchaExample
					.createCriteria();
			criteria.andPhoneEqualTo(phone);
			List<Ucaptcha> ucaptchas = ucaptchaMapper
					.selectByExample(ucaptchaExample);
			if (ucaptchas.isEmpty()) {
				Ucaptcha ucaptcha = new Ucaptcha();
				ucaptcha.setPhone(phone);
				ucaptcha.setCaptcha(captcha);
				ucaptcha.setState(1);
				ucaptcha.setType(type);
				Calendar cal = Calendar.getInstance();
				ucaptcha.setRequesttime(cal.getTime());
				ucaptcha.setAddtimes(cal.getTime());
				ucaptcha.setUpdatetimes(cal.getTime());
				cal.add(Calendar.MINUTE, 3);
				ucaptcha.setDeadtime(cal.getTime());
				ucaptchaMapper.insert(ucaptcha);
			} else {
				Ucaptcha ucaptcha = ucaptchas.get(0);
				ucaptcha.setCaptcha(captcha);
				ucaptcha.setState(1);
				ucaptcha.setType(type);
				Calendar cal = Calendar.getInstance();
				ucaptcha.setRequesttime(cal.getTime());
				ucaptcha.setUpdatetimes(cal.getTime());
				cal.add(Calendar.MINUTE, 3);
				ucaptcha.setDeadtime(cal.getTime());
				ucaptchaMapper.updateByPrimaryKeySelective(ucaptcha);
			}

			// 向对应手机号码发送验证码短信
			/*
			 * 
			 * String sms = ""; switch (type) { case 3: sms =
			 * SMS.RegisterCaptcha(phone, captcha); break; case 4: sms =
			 * SMS.AlterPwdCaptcha(phone, captcha); default: break; }
			 */
		}
		model.put("flag", flag);
		model.put("captcha", captcha);
		return model;
	}
	/**
	 * phone 条件搜索 当为("")时，查询全部，否则模糊搜索
		pageNO 第几页
		pageSize 每页的数量
		Returns:
			User集合
	 */
	public List<User> userList(String phone, int pageNO, int pageSize) {
		UserExample uExample = new UserExample();
		//分页
		PaginationUtil pagination = new PaginationUtil(pageNO, pageSize);
		if (phone!=null && !phone.equals("")) {
			UserExample.Criteria createCriteria = uExample.createCriteria();
			createCriteria.andPhoneLike("%"+phone+"%");
			uExample.or(createCriteria);
			UserExample.Criteria createCriteria1 = uExample.createCriteria();
			createCriteria1.andNicknameLike("%"+phone+"%");//
			uExample.or(createCriteria1);
		}
		uExample.setPagination(pagination);
		return userMapper.selectByExample(uExample);
	}
	//禁用/启用    用户
	public int disableUser(long userId, int state) {
		User record = new User();
		record.setUserId(userId);
		int flag = 0;
		if (state==1) {//禁用用户
			record.setState(0);
			flag = 1;
		}else{ //启用用户
			record.setState(1);
			flag = 2;
		}
		record.setUpdatetimes(new Date());
		int r = userMapper.updateByPrimaryKeySelective(record);
		if (r<=0) {
			return -1;
		}else {
			return flag;
		}
	}
	public User getUserByuserId(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}
	//登陆 1 成功   0 失败  -1  未知错误，请找管理员        未处理的密码md5
	public List<AdminUser> adminLogin(AdminUser adminUser) {
		AdminUserExample example = new AdminUserExample();
		AdminUserExample.Criteria cc = example.createCriteria();
		cc.andPhoneEqualTo(adminUser.getPhone());//通过phone 查询 是否有该对象
		return adminUserMapper.selectByExample(example);
//		if (list.size()>=0) {
//			return list.get(0).getPwds().equals(adminUser.getPwds())?1:0;//到时可能涉及md5
//		}else
//			return -1;
	}
	public int totalCount(String string) {
		UserExample example = new UserExample();
		return userMapper.countByExample(example);
	}
	public int countUserByExample(UserExample example) {
		return userMapper.countByExample(example);
	}
	public List<Long> searchUserIdByNickName(String content) {
		UserExample example = new UserExample();
		UserExample.Criteria cc = example.createCriteria();
		cc.andNicknameLike("%"+content+"%");
		List<User> selectByExample = userMapper.selectByExample(example);
		List<Long> values = new ArrayList<Long>();
		for (User user : selectByExample) {
			values.add(user.getUserId());
		}
		return values;
	}
	public AdminUser findAdminUser(Long checkuserid) {
		return adminUserMapper.selectByPrimaryKey(checkuserid);
	}
}
