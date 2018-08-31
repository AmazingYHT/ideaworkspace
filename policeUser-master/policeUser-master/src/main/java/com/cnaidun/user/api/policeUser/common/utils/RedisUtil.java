package com.cnaidun.user.api.policeUser.common.utils;

import com.cnaidun.user.api.policeUser.common.redis.JedisOperation;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    @Autowired
    private JedisOperation jedisOperation;

    private JedisPool pool = null;

    /**
     * <p>传入ip和端口号构建redis 连接池</p>
     *
     * @param ip   ip
     * @param prot 端口
     */
    public RedisUtil(String ip, int prot) {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
         //   config.setMaxActive(500);
            config.setMaxTotal(500);
            // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
          //  config.setMaxWait(1000 * 100);
            config.setMaxWaitMillis(1000 * 100);
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            // pool = new JedisPool(config, "192.168.0.121", 6379, 100000);
            pool = new JedisPool(config, ip, prot, 100000);
        }
    }

    /**
     * <p>通过配置对象 ip 端口 构建连接池</p>
     *
     * @param config 配置对象
     * @param ip     ip
     * @param prot   端口
     */
    public RedisUtil(JedisPoolConfig config, String ip, int prot) {
        if (pool == null) {
            pool = new JedisPool(config, ip, prot, 10000);
        }
    }

    /**
     * <p>通过配置对象 ip 端口 超时时间 构建连接池</p>
     *
     * @param config  配置对象
     * @param ip      ip
     * @param prot    端口
     * @param timeout 超时时间
     */
    public RedisUtil(JedisPoolConfig config, String ip, int prot, int timeout) {
        if (pool == null) {
            pool = new JedisPool(config, ip, prot, timeout);
        }
    }

    /**
     * <p>通过连接池对象 构建一个连接池</p>
     *
     * @param pool 连接池对象
     */
    public RedisUtil(JedisPool pool) {
        if (this.pool == null) {
            this.pool = pool;
        }
    }

    /**
     * <p>通过key获取储存在redis中的value</p>
     * <p>并释放连接</p>
     *
     * @param key
     * @return 成功返回value 失败返回null
     */
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //returnResource(pool, jedis);
        }
        return value;
    }

    /**
     * <p>向redis存入key和value,并释放连接资源</p>
     * <p>如果key已经存在 则覆盖</p>
     *
     * @param key
     * @param value
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return "0";
        } finally {
           // returnResource(pool, jedis);
        }
    }


    /**
     * <p>删除指定的key,也可以传入一个包含key的数组</p>
     *
     * @param keys 一个key  也可以使 string 数组
     * @return 返回删除成功的个数
     */
    public Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.del(keys);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return 0L;
        } finally {
          //  returnResource(pool, jedis);
        }
    }

}
