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

public class OnlineRecordListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		// 处理在线人数 记录
		OnlineRecordThread oRecordThread = new OnlineRecordThread();
		oRecordThread.start();
	}

}
