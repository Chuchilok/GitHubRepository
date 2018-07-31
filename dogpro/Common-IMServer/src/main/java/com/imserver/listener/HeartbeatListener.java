package com.imserver.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.imserver.heartbeattool.HeartbeatThread;

public class HeartbeatListener implements ServletContextListener {

	private Thread heartbeatThread;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		//mqtt心跳线程初始化
		heartbeatThread = new Thread(new HeartbeatThread());
		//mqtt心跳线程启动
		heartbeatThread.start();
	}


}





