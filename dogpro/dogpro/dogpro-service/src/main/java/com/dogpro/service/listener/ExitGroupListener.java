package com.dogpro.service.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ExitGroupListener implements ServletContextListener {

	
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		//判断 用户 是否 退出遛狗组
		Thread task2 = new ExitGroupThread();
		task2.start();
	}


}





