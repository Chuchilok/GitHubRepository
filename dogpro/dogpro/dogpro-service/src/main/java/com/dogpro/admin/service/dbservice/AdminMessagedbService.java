package com.dogpro.admin.service.dbservice;

import java.util.List;

import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.MessageExample;

/**
 * 后台管理    消息模块
 * @author Administrator
 *
 */
public interface AdminMessagedbService {

	/**
	 * 查询所有消息   分页  
	 * @param pageNO    0    当前页
	 * @param pageSize  5    每页数量大小
	 * @return 消息列表
	 */
	List<Message> getAllMsg(int pageNO, int pageSize);
	/**
	 * 总记录数
	 * @param example
	 * @return
	 */
	int countMessageByExample(MessageExample example);
}
