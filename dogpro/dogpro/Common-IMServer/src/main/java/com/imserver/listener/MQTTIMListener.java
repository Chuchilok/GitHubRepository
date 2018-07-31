package com.imserver.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.dogpro.common.tool.SpringInit;
import com.ibm.micro.client.mqttv3.MqttException;
import com.imserver.mqtttool.MQTTRedisReadThread;
import com.imserver.mqtttool.MQTTjedisManager;
import com.imserver.mqtttool.Message2DBthread;
import com.imserver.mqtttool.OfflineTime2DBthread;
import com.imserver.mqtttool.RedisReadTaskSchuder;
import com.imserver.mqtttool.IBM.MQTTOnlineService;
import com.imserver.mqtttool.IBM.MQTTWillService;
import com.imserver.service.dbservice.IMGroupMemberdbService;

public class MQTTIMListener implements ServletContextListener {

	final int MQTTServerSize = 550;
	final static int RedisThreadSize = 2;

	//MQttTool[] MQttToolList = new MQttTool[MQTTServerSize];
	//MQTTService[] MQTTServiceList = new MQTTService[MQTTServerSize];
	private final static int poolsize = 1;
	private static ExecutorService MQTTThreadPool = Executors
			.newFixedThreadPool(poolsize);
	private static ExecutorService RedisThreadPool = Executors
			.newFixedThreadPool(RedisThreadSize);
	private static int index = 0;
	private String key = "IMsend";
	Thread[] mqttRedisReadThreads = new Thread[RedisThreadSize];

	
	
	private static Logger logger = Logger.getLogger(RedisReadTaskSchuder.class);

	// 消息计数器
	AtomicLong counter = null;//new AtomicLong(0);

	// 定时器
	ScheduledExecutorService executorService = Executors
			.newScheduledThreadPool(1);

	volatile boolean runState = true;
	// 开启时间
	long startTime = 0;

	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		for (int i = 0; i < RedisThreadSize; i++) {
			mqttRedisReadThreads[i] = new Thread(new MQTTRedisReadThread(MQTTjedisManager
					.instance().getJedis(), key,i,counter));
			
		}
		
		for (int i = 0; i < RedisThreadSize; i++) {
			
			mqttRedisReadThreads[i].start();
		}
		
		//群组成员信息 初始化
//		IMGroupMemberdbService imGroupMemberdbService = (IMGroupMemberdbService) SpringInit.getApplicationContext().getBean("IMGroupMemberdbService");
//		imGroupMemberdbService.initGroupMember();
	}


class PrintTimer implements Runnable {

	public void run() {

		try {
			StringBuilder sb = new StringBuilder();

			SimpleDateFormat format = new SimpleDateFormat(
					"YYYY-MM-dd HH:mm:ss");
			long _count = counter.get();
			long _endTime = System.currentTimeMillis();

			long throughput_s = (_count * 1000) / (_endTime - startTime);
			long minTime = (_endTime - startTime) / (1000 * 60);
			long hourTime = (_endTime - startTime) / (1000 * 60 * 60);

			long throughput_m = (minTime != 0) ? _count / minTime : 0;
			long throughput_h = (hourTime != 0) ? _count / hourTime : 0;

			sb.append("\n======================================================\n");
			sb.append("---------开始时间--------------结束时间-------------获取条数-----每秒吞吐量-----分钟吞吐量-----小时吞吐量-----测试运行线程数量----每个消息的大小\n");

			sb.append("-");
			sb.append(format.format(new Date(startTime)));
			sb.append("---");
			sb.append(format.format(new Date()));
			sb.append("------");
			sb.append(counter.get());
			sb.append("------");
			sb.append(throughput_s);
			sb.append("---------");
			sb.append(throughput_m);
			sb.append("---------");
			sb.append(throughput_h);
			sb.append("-----------");
			sb.append(RedisThreadSize);
			sb.append("-----------");
			sb.append("672byte");
			sb.append("-----");

			logger.error(sb.toString());
			logger.error("\n");

		} catch (Throwable t) {

			logger.error("", t);
		}

	}

}
}





