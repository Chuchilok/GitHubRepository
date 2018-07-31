package com.imserver.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.imserver.mqtttool.Message2DBthread;
import com.imserver.mqtttool.OfflineTime2DBthread;
import com.imserver.pushtool.PushMessage2DBthread;
import com.imserver.pushtool.PushMsgHandleThread;
import com.imserver.pushtool.PushUser2DBthread;

public class MQTTdbListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// 信息redis入DB线程
		Thread message2DBthread = new Message2DBthread();
		message2DBthread.start();
		// 离线时间 redis入DB线程
		Thread offlineTime2DBthread = new OfflineTime2DBthread();
		offlineTime2DBthread.start();
	}

}
