package com.dogpro.admin.service.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AreaSpaceListener implements ServletContextListener{
	
	private AreaSpaceDBThread areaSpace2MysqlThread;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		if (areaSpace2MysqlThread != null && areaSpace2MysqlThread.isInterrupted()) {
			areaSpace2MysqlThread.interrupt();
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		if (areaSpace2MysqlThread == null) {
			areaSpace2MysqlThread = new AreaSpaceDBThread();
			areaSpace2MysqlThread.start(); 
		}
		
	}

	
}
