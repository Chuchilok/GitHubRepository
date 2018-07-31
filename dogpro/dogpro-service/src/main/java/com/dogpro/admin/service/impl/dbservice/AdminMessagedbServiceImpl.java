package com.dogpro.admin.service.impl.dbservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.PaginationUtil;

import com.dogpro.admin.service.dbservice.AdminMessagedbService;
import com.dogpro.dao.MessageMapper;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.MessageExample;
@Service("adminMessagedbService")
public class AdminMessagedbServiceImpl implements AdminMessagedbService {
	@Autowired
	private MessageMapper messageMapper;
	/**
	 * 把最新的消息先查出来  
	 */
	public List<Message> getAllMsg(int pageNO, int pageSize) {
		MessageExample example = new MessageExample();
		PaginationUtil pagination = new PaginationUtil(pageNO, pageSize);
		example.setPagination(pagination);
		example.setOrderByClause("addtimes desc");//把最新的消息先查出来  
		return messageMapper.selectByExampleWithBLOBs(example);
	}
	public int countMessageByExample(MessageExample example) {
		return messageMapper.countByExample(example);
	}
}
