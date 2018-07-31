package com.imserver.mqtttool;

import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;


import com.dogpro.common.tool.MessageConsumerConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MQTTjedisManager {

	private static Logger logger = Logger.getLogger(MQTTjedisManager.class);

	private static MQTTjedisManager instance = null;

	private JedisPool jedisPool = null;
	
	private String JEDIS_IP;
	private Integer JEDIS_PORT;
	private String JEDIS_PASSWORD;
	private Integer dbindex;

//	public JedisManager(int MaxTotal, int MaxIdle) {
//		if (null == instance) {
//			instance = new JedisManager();
//			instance.init(MaxTotal, MaxIdle);
//		}
//
//	}

	private MQTTjedisManager() {

	}

	 public static synchronized MQTTjedisManager instance() {
	
	 if (null == instance) {
	
	 instance = new MQTTjedisManager();
	 instance.init();
	 }
	 return instance;
	 }

	public void init() {
		Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
        JEDIS_IP=packagesMap.get("JEDIS_IP").toString().trim();
        String ss = packagesMap.get("JEDIS_PORT").toString().trim();
        JEDIS_PORT=Integer.valueOf(ss);
        JEDIS_PASSWORD=packagesMap.get("JEDIS_PASSWORD").toString().trim();
        if(JEDIS_PASSWORD.equals("null")){
        	JEDIS_PASSWORD = null;
        }
        dbindex = Integer.valueOf(packagesMap.get("redisIMsenddb").toString().trim());
		logger.info("------init redis start------");
		GenericObjectPoolConfig jedisPoolConfig = new GenericObjectPoolConfig();
		jedisPoolConfig.setMaxTotal(Integer.parseInt(packagesMap.get("MAX_ACTIVE").toString()));
		jedisPoolConfig.setMaxIdle(Integer.parseInt(packagesMap.get("redisMaxIdle").toString()));
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setMaxWaitMillis(1000);
		jedisPool = new JedisPool(jedisPoolConfig, JEDIS_IP, JEDIS_PORT,
				600, JEDIS_PASSWORD, dbindex);
		logger.info("------init redis end------");

	}

	public Jedis getJedis() {

		return jedisPool.getResource();
	}

	public void rebackPool(Jedis jedis) {

		jedis.close();
	}

	public String getString(String key) {
		Jedis jedis = getJedis();
		String result = jedis.get(key);

		jedis.close();

		return result;
	}

	public void set(String key, String value) {

		Jedis jedis = getJedis();

		jedis.set(key, value);

		jedis.close();

	}

	/**
	 * 存储REDIS队列 顺序存储
	 * 
	 * @param key
	 *            reids键名
	 * @param value
	 *            键值
	 */
	public void lpush(byte[] key, byte[] value) {

		Jedis jedis = getJedis();

		jedis.lpush(key, value);

		jedis.close();

	}

	/**
	 * 获取队列数据
	 * 
	 * @param key
	 *            键名
	 * @return
	 */
	public byte[] rpop(byte[] key) {

		byte[] bytes = null;
		Jedis jedis = getJedis();
		bytes = jedis.rpop(key);
		jedis.close();
		return bytes;
	}

	public void shutdown() {
		logger.info("---redis close--" + jedisPool.getNumActive());
		jedisPool.close();
	}
}
