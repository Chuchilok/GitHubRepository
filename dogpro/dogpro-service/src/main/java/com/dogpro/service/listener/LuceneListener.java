package com.dogpro.service.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LuceneListener implements ServletContextListener {

	
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		//判断 遛狗地点修改 lucene
		Thread task2 = new LuceneThread();
		task2.start();
	}


}





