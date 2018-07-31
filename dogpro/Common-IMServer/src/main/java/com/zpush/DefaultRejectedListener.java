package com.zpush;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 被拒绝的监听
 * @author Administrator
 *
 */
public class DefaultRejectedListener implements RejectedListener{
	static final Logger logger = LoggerFactory.getLogger(DefaultRejectedListener.class);
	public void handle(ErrorResponse response, Notification notification) {
		System.out.println("被拒绝的监听："+notification+"\n response:"+response);
		//logger.warn("send notification error! response:" + response + ", notification:" + notification);
	}
}
