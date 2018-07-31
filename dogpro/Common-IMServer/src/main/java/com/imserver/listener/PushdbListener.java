package com.imserver.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.imserver.pushtool.PushMessage2DBthread;
import com.imserver.pushtool.PushUser2DBthread;

public class PushdbListener implements ServletContextListener {

	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		//用户推送token list 入数据库
		Thread task = new PushUser2DBthread();
		task.start();
		//推送信息 list入  数据库
		Thread task2 = new PushMessage2DBthread();
		task2.start();
	}


}





