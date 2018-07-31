package com.dogpro.common.tool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.dogpro.common.tool.MessageConsumerConfig;
/**
 * Created by Kinglf on 2016/10/17.
 */
public class JedisUtil {
    private  String JEDIS_IP;
    private  Integer JEDIS_PORT;
    private  String JEDIS_PASSWORD;
    private JedisPool jedisPool;
    
   
    public JedisUtil(int dbindex){
    	Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
        JEDIS_IP=packagesMap.get("JEDIS_IP").toString().trim();
        String ss = packagesMap.get("JEDIS_PORT").toString().trim();
        JEDIS_PORT=Integer.valueOf(ss);
        JEDIS_PASSWORD=packagesMap.get("JEDIS_PASSWORD").toString().trim();
        if(JEDIS_PASSWORD.equals("null")){
        	JEDIS_PASSWORD = null;
        }
//        JedisPoolConfig config=new JedisPoolConfig();
//        config.setMaxIdle(Integer.parseInt(packagesMap.get("redisMaxIdle").toString()));
//        config.setMaxTotal(Integer.parseInt(packagesMap.get("MAX_ACTIVE").toString()));
//        config.setMaxWaitMillis(Long.parseLong(packagesMap.get("redisMaxWaitMillis").toString()));
//        config.setTestOnBorrow(Boolean.parseBoolean(packagesMap.get("redisTestOnBorrow").toString()));
//        config.setTestOnReturn(Boolean.parseBoolean(packagesMap.get("redisTestOnReturn").toString()));
//        config.setTestWhileIdle(Boolean.parseBoolean(packagesMap.get("redisTestWhileIdle").toString()));
//        config.setMinEvictableIdleTimeMillis(Long.parseLong(packagesMap.get("redisMinEvictableIdleTimeMillis").toString()));
//        config.setTimeBetweenEvictionRunsMillis(Long.parseLong(packagesMap.get("redisTimeBetweenEvictionRunsMillis").toString()));
//        config.setNumTestsPerEvictionRun(Integer.parseInt(packagesMap.get("redisNumTestsPerEvictionRun").toString()));
//        jedisPool = new JedisPool(config,JEDIS_IP,JEDIS_PORT,60000,JEDIS_PASSWORD,dbindex);
        GenericObjectPoolConfig jedisPoolConfig = new GenericObjectPoolConfig();
		jedisPoolConfig.setMaxTotal(Integer.parseInt(packagesMap.get("MAX_ACTIVE").toString()));
		jedisPoolConfig.setMaxIdle(Integer.parseInt(packagesMap.get("redisMaxIdle").toString()));
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setMaxWaitMillis(Long.parseLong(packagesMap.get("redisMaxWaitMillis").toString()));
        jedisPool=new JedisPool(jedisPoolConfig,JEDIS_IP,JEDIS_PORT,60000,JEDIS_PASSWORD,dbindex);
    }
    
    /**
     * 获取数据
     * @param key
     * @return
     */
    public  String get(String key){
        String value=null;
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            value=jedis.get(key);
        }catch (Exception e){
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        }finally {
            close(jedis);
        }
        return value;
    }

    private  void close(Jedis jedis) {
        try{
            jedisPool.returnResource(jedis);
        }catch (Exception e){
            if(jedis.isConnected()){
                jedis.quit();
                jedis.disconnect();
            }
        }
    }
    public  byte[] get(byte[] key){
        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }

        return value;
    }

    public  void set(byte[] key, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    public  void set(byte[] key, byte[] value, int time) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            jedis.expire(key, time);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    public  void hset(byte[] key, byte[] field, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    public  void hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public  String hget(String key, String field) {

        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }

        return value;
    }
    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public  byte[] hget(byte[] key, byte[] field) {

        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }

        return value;
    }
    public  void hdel(byte[] key, byte[] field) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hdel(key, field);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }
    /**
     * 存储REDIS队列 顺序存储
     * @param  key reids键名
     * @param  value 键值
     */
    public  void lpush(byte[] key, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(key, value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }
    public  void lpush(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(key, value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }
    
    /**
     * 存储REDIS队列 反向存储
     * @param  key reids键名
     * @param  value 键值
     */
    public  void rpush(byte[] key, byte[] value) {

        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            jedis.rpush(key, value);

        } catch (Exception e) {

            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {

            //返还到连接池
            close(jedis);

        }
    }
    
    
    
    /**
     * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端
     * @param  key reids键名
     * @param  destination 键值
     */
    public  void rpoplpush(byte[] key, byte[] destination) {

        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            jedis.rpoplpush(key, destination);

        } catch (Exception e) {

            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {

            //返还到连接池
            close(jedis);

        }
    }

    /**
     * 获取队列数据
     * @param  key 键名
     * @return
     */
    public  List lpopList(byte[] key) {

        List list = null;
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            list = jedis.lrange(key, 0, -1);

        } catch (Exception e) {

            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {

            //返还到连接池
            close(jedis);

        }
        return list;
    }
    /**
     * 获取队列数据
     * @param  key 键名
     * @return
     */
    public  byte[] rpop(byte[] key) {

        byte[] bytes = null;
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            bytes = jedis.rpop(key);
        } catch (Exception e) {

            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {

            //返还到连接池
            close(jedis);

        }
        return bytes;
    }
    
    public  String rpop(String key) {

        String bytes = null;
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            bytes = jedis.rpop(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return bytes;
    }
    
    public  void hmset(Object key, Map hash) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hmset(key.toString(), hash);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {
            //返还到连接池
            close(jedis);

        }
    }
    public  void hmset(Object key, Map hash, int time) {
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            jedis.hmset(key.toString(), hash);
            jedis.expire(key.toString(), time);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {
            //返还到连接池
            close(jedis);

        }
    }
    public  List hmget(Object key, String... fields) {
        List result = null;
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            result = jedis.hmget(key.toString(), fields);

        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {
            //返还到连接池
            close(jedis);

        }
        return result;
    }

    public  Set hkeys(String key) {
        Set result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hkeys(key);

        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {
            //返还到连接池
            close(jedis);

        }
        return result;
    }
    public  List lrange(byte[] key, int from, int to) {
        List result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.lrange(key, from, to);

        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {
            //返还到连接池
            close(jedis);

        }
        return result;
    }
    public  Map hgetAll(byte[] key) {
        Map result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hgetAll(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();

        } finally {
            //返还到连接池
            close(jedis);
        }
        return result;
    }

    public  void del(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    public  long llen(byte[] key) {

        long len = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            len = jedis.llen(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return len;
    }
    /**
     * 往集合set内添加一个元素
     * @param key
     * @param members
     */
    public void sadd(byte[] key,byte[] members){
    	Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.sadd(key, members);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }
    /**
     * 往集合set内添加一个元素
     * @param key
     * @param members
     */
    public void sadd(String key,String members){
    	Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.sadd(key, members);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }
    
    /**
     * 从集合set内移除一个元素
     * @param key
     * @param member
     */
    public void srem(String key,String member){
    	Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.srem(key, member);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }
    
    /**
     * 从集合set内移除一个元素
     * @param key
     * @param member
     */
    public void srem(byte[] key,byte[] member){
    	Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.srem(key, member);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }
    
    
    /**
     * 输出集合set中所有元素
     * @param key
     * @param members
     */
    public Set<byte[]> smembers(byte[] key){
    	Jedis jedis = null;
    	Set<byte[]> sets = null;
        try {
            jedis = jedisPool.getResource();
            sets = jedis.smembers(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return sets;
    }
    
    /**
     * 输出集合set中所有元素
     * @param key
     * @param members
     */
    public Set<String> smembers(String key){
    	Jedis jedis = null;
    	Set<String> sets = null;
        try {
            jedis = jedisPool.getResource();
            sets = jedis.smembers(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return sets;
    }
    
    /**
     * 获取集合 set中 元素总数
     * @param key
     * @param member
     */
    public Long scard(byte[] key){
    	Jedis jedis = null;
    	Long total = null;
        try {
            jedis = jedisPool.getResource();
            total =  jedis.scard(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return total;
    }
    
    /**
     * 获取集合 set中 元素总数
     * @param key
     * @param member
     */
    public Long scard(String key){
    	Jedis jedis = null;
    	Long total = null;
        try {
            jedis = jedisPool.getResource();
            total =  jedis.scard(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return total;
    }
    
    public List<byte[]> brpop(byte[] key,int timeout){
    	Jedis jedis = null;
    	List<byte[]> value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.brpop(timeout,key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return value;
    }
    
    
}