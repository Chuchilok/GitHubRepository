package com.dogpro.redis;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	private Jedis jedis;
    public RedisUtil() {
        // 连接redis服务器
        jedis = new Jedis("192.168.199.200", 6379);
        System.out.println("Connection to server sucessfully");
        // 增加一个Key检测是否连接成功
        // jedis.set("foo", "bar");
        // String value = jedis.get("foo");
        // System.out.println("foo-->"+value);
        // 查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());//输出PONG则连接成功
        System.out.println("--------------------------------------------");
    }
}
