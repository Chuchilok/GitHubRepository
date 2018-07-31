package com.dogpro.admin.service.dbservice;

import java.util.List;
import java.util.Map;

import com.dogpro.domain.model.AdminUser;
import com.dogpro.domain.model.User;
import com.dogpro.domain.model.UserExample;

/**
 * 后台用户管理
 * @author Administrator
 *
 */
public interface AdminUserdbService {
	/**
	 * 管理用户获取注册验证码
	 */
	public Map<String,Object> requestCaptcha(String phone,int type);
	/**
	 * 
	 * @param string 条件搜索 当为("")时，查询全部，否则模糊搜索
	 * @param pageNO 第几页
	 * @param pageSize 每页的数量
	 * @return User集合
	 */
	public List<User> userList(String string, int pageNO, int pageSize);
	/**
	 * 禁用/启用    用户
	 * @param userId
	 * @param state
	 * @return
	 */
	public int disableUser(long userId, int state);
	/**
	 * 获取一个user
	 * @param userId
	 * @return
	 */
	public User getUserByuserId(Long userId);
	/**
	 * 后台管理 登陆
	 * @param adminUser
	 * @return 对象
	 */
	public List<AdminUser> adminLogin(AdminUser adminUser);
	/**
	 * 总记录条数
	 * @param string 条件
	 * @return 总记录条数
	 */
	public int totalCount(String string);
	/**
	 * 记录总数
	 * @param example
	 * @return
	 */
	public int countUserByExample(UserExample example);
	//通过nickname查询userid
	public List<Long> searchUserIdByNickName(String content);
	//返回一个管理用户对象 通过id
	public AdminUser findAdminUser(Long checkuserid);
}