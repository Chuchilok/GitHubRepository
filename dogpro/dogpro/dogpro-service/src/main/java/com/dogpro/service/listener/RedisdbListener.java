package com.dogpro.service.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class RedisdbListener implements ServletContextListener{
	
	private Track2DBthread track2MysqlThread;
	private PushUser2DBthread pushUser2DBthread;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		//遛狗轨迹
		if (track2MysqlThread != null && track2MysqlThread.isInterrupted()) {
			track2MysqlThread.interrupt();
		}
		//用户推送token 入数据库
//		if (pushUser2DBthread != null && pushUser2DBthread.isInterrupted()) {
//			pushUser2DBthread.interrupt();
//		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		if (track2MysqlThread == null) {
			track2MysqlThread = new Track2DBthread();
			track2MysqlThread.start(); 
		}
		//用户推送token 入数据库
//		if (pushUser2DBthread == null) {
//			pushUser2DBthread = new PushUser2DBthread();
//			pushUser2DBthread.start();
//		}
	}

	
}
