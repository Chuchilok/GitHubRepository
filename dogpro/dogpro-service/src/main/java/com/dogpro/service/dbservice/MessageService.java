package com.dogpro.service.dbservice;

import com.dogpro.common.Interfacetool.ParameterObject;
import com.dogpro.common.Interfacetool.ResultObject;

/**
 * 消息接口
 * @author Administrator
 *
 */
public interface MessageService {
	
	//聊天上传的媒体资源
	public ResultObject uploadMedia(ParameterObject parameterObject);
	
	//发送媒体资源消息接口(好友或遛狗组)
	public ResultObject publishMessage(ParameterObject parameterObject);
}
