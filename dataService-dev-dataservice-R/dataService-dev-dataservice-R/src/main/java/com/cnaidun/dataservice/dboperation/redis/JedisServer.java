package com.cnaidun.dataservice.dboperation.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;

import java.util.HashMap;
import java.util.Map;
@Slf4j
public class JedisServer {
////    @Autowired
////    JedisPool jedisPool;
//
//    @Autowired
//    JedisProperties redisProperties;
//
//    public JedisCluster getInstance(){
////        HostAndPort hostAndPort = new HostAndPort("118.24.224.186",6379);
//
////        JedisCluster jedis = new JedisCluster(hostAndPort,3000,3000,5,"cqpolice",new JedisPoolConfig());
//        JedisShardInfo jedisSharInfo = new JedisShardInfo("118.24.224.186",6379);
//        jedisSharInfo.setPassword("cqpolice");
//        Jedis jedis = new Jedis(jedisSharInfo);
//        jedis.set("test","test");
//        jedis.hset("wo","wo","wo");
//        log.info("tests");
////        HashMap test = new HashMap();
////        test.put("1","1");
////        test.put("2","2");
////        test.put("3","3");
////        test.put("4","4");
////        jedis.hmset("tst",test);
////        jedis.hset("111","workno","CN22AIDUN");
//
//        return null;
//    }
}
