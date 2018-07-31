package com.imserver.mqtttool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.domain.IMmessage;
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.tool.ObjectUtil;

public class RedisReadTaskSchuder {

	private static Logger logger = Logger.getLogger(RedisReadTaskSchuder.class);

	// 消息计数器
	AtomicLong counter = new AtomicLong(0);

	// 定时器
	ScheduledExecutorService executorService = Executors
			.newScheduledThreadPool(1);

	volatile boolean runState = true;
	// 开启时间
	long startTime = 0;

	// 工作线程
	Thread[] workers = null;

	// 线程数量
	int threadNumber = 0;
	String readthreadname = "redis_thread_reader_";

	private IMmessage iMmessage;
	private IMsend iMsend;
	private int MaxTotal;
	private int MaxIdle;
	
	public RedisReadTaskSchuder(int threadNumber, int flag, int MaxTotal,
			int MaxIdle) {
		this.MaxTotal = MaxTotal;
		this.MaxIdle = MaxIdle;
		iMmessage = new IMmessage();
		iMmessage.setSendUid(20l);
		iMmessage.setRevUid(32l);
		String sb = "test中国";
		iMmessage.setContent(sb);
		iMmessage.setToken("606f8249d18c47398a478904305087fdfbcf76b3026343c0af3eba9bfb154bad");
		iMmessage.setMd5("a");
		iMmessage.setType(7);
		String ss = Base64.byteArrayToBase64(JSONObject.toJSON(iMmessage)
				.toString().getBytes());
		IMsend iMsend = new IMsend();
		iMsend.setToken("3eba2885246d4d66adfeeb70dc3809abf873d78ebb3440609d4ba377307d705c");
		iMsend.setContent(ss);

		// 默认线程数量为硬件内核数的2倍
		this.threadNumber = threadNumber;

		workers = new Thread[threadNumber];

		for (int i = 0; i < threadNumber; i++) {
			workers[i] = new Thread(new RedisReadTask(JedisManager.instance(MaxTotal, MaxIdle).getJedis(), flag));
			workers[i].setDaemon(true);
			workers[i].setName(readthreadname + "i");
		}

		executorService.scheduleAtFixedRate(new PrintTimer(), 2, 15,
				TimeUnit.SECONDS);

	}

	/**
	 * 启动工作线程
	 */
	public void start() {

		for (int i = 0; i < threadNumber; i++) {
			workers[i].start();
		}

		startTime = System.currentTimeMillis();
	}

	/**
	 * 计数器重置
	 * 
	 */
	public void resetCount() {
		this.counter.set(0);
		startTime = System.currentTimeMillis();
	}

	public long getCount() {
		return this.counter.get();
	}

	/**
	 * 关闭线程
	 */
	public void shutdown() {
		runState = false;
		executorService.shutdown();
	}

	public void printReuslt() {
		logger.info("---获取到数据数量:--" + counter.get());
	}

	class RedisReadTask implements Runnable {

		private Jedis jedis = null;
		private int flag;

		RedisReadTask(Jedis jedis, int flag) {

			this.jedis = jedis;
			this.flag = flag;
		}

		public void run() {

			while (runState) {
				byte[] str = null;
				try {
					// jedis.set(Main.testKey.getBytes(),
					// Main.testStr.getBytes());
					// byte[] str = jedis.get(Main.testKey.getBytes());
					switch (flag) {
					case 1:
						jedis.lpush("redislisttest".getBytes(),
								ObjectUtil.object2Bytes(iMsend));
						counter.incrementAndGet();
						break;
					case 2:
						str = jedis.rpop("redislisttest".getBytes());
						if (null != str) {
							counter.incrementAndGet();
						}
						break;
					case 3:
						jedis.lpush("redislisttest".getBytes(),
								ObjectUtil.object2Bytes(iMsend));
						str = jedis.rpop("redislisttest".getBytes());
						if (null != str) {
							counter.incrementAndGet();
						}
						break;

					default:
						break;
					}

				} catch (Throwable t) {

					logger.error("", t);

					// 连接失败
					if (!jedis.isConnected()) {

						// 返回连接池里面
						jedis.close();
						// 重新获取连接
						jedis = JedisManager.instance(MaxTotal, MaxIdle)
								.getJedis();
					}
				}
			}

			// 返回去jedis pool 里面
			jedis.close();
		}
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
				sb.append(threadNumber);
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
