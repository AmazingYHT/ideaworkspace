package com.cnaidun.dataservice.dboperation.redis.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class JedisConfig {

    private String host;

    private int port;

    private String password;

    private HashOperations hashOperations;
//
//    @Autowired
//    RedisTemplate<String,String> redisTemplate;

    public JedisConfig(){
//        HashOperations<String,String,String> hashOperations = redisTemplate.opsForHash();

//        JedisShardInfo jedisSharInfo = new JedisShardInfo("10.0.0.12",6379);
//        jedisSharInfo.setPassword("cqpolice");
//        JedisShardInfo jedisSharInfo = new JedisShardInfo(host,port);
//        jedisSharInfo.setPassword(password);
        //        jedis = new Jedis(jedisSharInfo);

//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        // 最大连接数
//        poolConfig.setMaxTotal(5);
//        // 最大空闲数
//        poolConfig.setMaxIdle(3);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
//        poolConfig.setMaxWaitMillis(1000);
//        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
//        nodes.add(new HostAndPort("10.0.0.5", 7001));
//        nodes.add(new HostAndPort("10.0.0.5", 7004));
//        nodes.add(new HostAndPort("10.0.0.6", 7002));
//        nodes.add(new HostAndPort("10.0.0.6", 7005));
//        nodes.add(new HostAndPort("10.0.0.7", 7003));
//        nodes.add(new HostAndPort("10.0.0.7", 7006));
//
//        JedisCluster jedis = new JedisCluster(nodes,3000,3000,5,"cqpolice", poolConfig);
    }

    public HashOperations getHashOperations(){
       return hashOperations;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
