package com.dogpro.service.impl.dbservice;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogpro.dao.MessageMapper;
import com.dogpro.dao.MessageMediaMapper;
import com.dogpro.dao.WalkingDogGroupMapper;
import com.dogpro.domain.model.Message;
import com.dogpro.domain.model.MessageExample;
import com.dogpro.domain.model.MessageMedia;
import com.dogpro.domain.model.WalkingDogGroup;
import com.dogpro.domain.model.WalkingDogGroupExample;
import com.dogpro.service.dbservice.MessagedbService;

/**
 * 消息接口
 * @author Administrator
 *
 */

@Service("messagedbService")
public class MessagedbServiceImpl implements MessagedbService {

	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private WalkingDogGroupMapper walkingDogGroupMapper;
	@Autowired
	private MessageMediaMapper messageMediaMapper;
	//为好友视频（图片）发送
	public int publishMessage(Message message,String mediaUrl,String mediaSubUrl) {
		messageMapper.insertSelective(message);
		MessageMedia record = new MessageMedia();
		if(message.getType()== 4) 
			record.setMediatype(1);
		else 
			record.setMediatype(2);//图片 1  视频 2
		record.setUserid(message.getSenduserid());
		record.setMessageid(message.getAcceptuserid());
		record.setResourcecode(new Date().getTime()); 
		record.setAddtimes(new Date());
		record.setUpdatetimes(new Date());
		record.setMediaurl(mediaUrl);
		record.setUploadtimes(new Date());
		messageMediaMapper.insertSelective(record);
		return 1;
	}

	//为群视频（图片）发送
	public int publishMessage(Message message, long dogLocationid,String mediaUrl,String mediaSubUrl) {
		//WalkingDogGroup dogGroups = walkingDogGroupMapper.selectByPrimaryKey(groupid);
		//Long doglocationid = dogGroups.getDoglocationid();//拿到遛狗地点
		WalkingDogGroupExample walkExample = new WalkingDogGroupExample();
		WalkingDogGroupExample.Criteria cWalk = walkExample.createCriteria();
		cWalk.andStateEqualTo(1);//信息要有效状态
		cWalk.andDoglocationidEqualTo(dogLocationid);//属于在同一个遛狗组
		cWalk.andUseridNotEqualTo(message.getSenduserid());//不等于本身
		List<WalkingDogGroup> lw = walkingDogGroupMapper.selectByExample(walkExample );
		MessageMedia record = new MessageMedia();
		if(message.getType()== 8) 
			record.setMediatype(2);
		else 
			record.setMediatype(1);//图片 1  视频 2
		for (WalkingDogGroup walkingDogGroup : lw) {//相当于群发信息
			message.setMessageid(null);
			message.setAcceptuserid(walkingDogGroup.getUserid());
			messageMapper.insertSelective(message);
		}
		record.setUserid(message.getSenduserid());
		record.setMessageid(dogLocationid);
		record.setResourcecode(new Date().getTime());
		record.setAddtimes(new Date());
		record.setUpdatetimes(new Date());
		record.setMediaurl(mediaUrl);
		record.setUploadtimes(new Date());
		messageMediaMapper.insertSelective(record);
		return 1;
	}
	/**
	 * 插入message数据到数据库
	 * @param message
	 * @return
	 */
	public int insertMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			messageMapper.insert(message);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	public boolean redis2DB(Message message) {
		// TODO Auto-generated method stub
		try {
			switch (message.getState()) {
			case 0:
				//未处理
				messageMapper.insertSelective(message);
				break;
			case 1:
				//已读
				MessageExample messageExample = new MessageExample();
				MessageExample.Criteria mCriteria = messageExample.createCriteria();
				mCriteria.andAcceptuseridEqualTo(message.getAcceptuserid()).andMsgcodeEqualTo(message.getMsgcode());
				List<Message> messages = messageMapper.selectByExample(messageExample);
				message.setMessageid(messages.get(0).getMessageid());
				messageMapper.updateByPrimaryKeySelective(message);
				break;
			case 2:
				//已处理
				MessageExample messageExample2 = new MessageExample();
				MessageExample.Criteria mCriteria2 = messageExample2.createCriteria();
				mCriteria2.andAcceptuseridEqualTo(message.getAcceptuserid()).andMsgcodeEqualTo(message.getMsgcode());
				List<Message> messages2 = messageMapper.selectByExample(messageExample2);
				message.setMessageid(messages2.get(0).getMessageid());
				messageMapper.updateByPrimaryKeySelective(message);
				break;
			default:
				break;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public int insertIMMessage(Long sendUid, Long revUid, int type,
			String content, Long msgCode) {
		// TODO Auto-generated method stub
		Message message = new Message();
		message.setSenduserid(sendUid);
		message.setAcceptuserid(revUid);
		message.setType(type);
		message.setContent(content);
		message.setMsgcode(msgCode);
		
		message.setSendlongitude(-1f);
		message.setSendlatitude(-1f);
		message.setState(0);
		
		Date date = new Date(msgCode);
		message.setSendtimes(date);
		message.setAddtimes(date);
		message.setUpdatetimes(date);
		return messageMapper.insertSelective(message);
	}
	
}
