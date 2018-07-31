package com.imserver.mqtttool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;

  
public class MQTTthreadpool {
	private static int poolsize = 10;
	private static ExecutorService fixedThreadPool ;
	static{
		fixedThreadPool = Executors.newFixedThreadPool(poolsize);
	}
//	public void handle(MQttTool mqttTool,UTF8Buffer topic,Buffer payload){
//		MQTThandleMsgThread task = new MQTThandleMsgThread(mqttTool,topic,payload);
//		fixedThreadPool.execute(task);
//	}
	public void execute(Runnable task){
		fixedThreadPool.execute(task);
	}
}
