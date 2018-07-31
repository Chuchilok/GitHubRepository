package com.imserver.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.imserver.pushtool.PushMessage2DBthread;
import com.imserver.pushtool.PushMsgHandleThread;
import com.imserver.pushtool.PushUser2DBthread;

public class PushListener implements ServletContextListener {

	final int PushThreadSize = 1;
	private Thread[] pushThreads = new Thread[PushThreadSize];
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		//推送消息处理线程初始化
		for(int i =0;i<PushThreadSize;i++){
			pushThreads[i] = new Thread(new PushMsgHandleThread(i));
		}
		//推送消息处理线程启动
		for(int i =0;i<PushThreadSize;i++){
			pushThreads[i].start();
		}
		
	}


}





