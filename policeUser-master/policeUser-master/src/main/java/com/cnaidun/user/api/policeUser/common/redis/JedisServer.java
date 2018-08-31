package com.cnaidun.user.api.policeUser.common.redis;

import com.cnaidun.user.api.policeUser.PoliceUserApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;

import java.util.HashMap;
import java.util.Map;

@Component
public class JedisServer {


    @Autowired
    JedisOperation jedisOperation;

    public void getInstance() {
        String aa = jedisOperation.getRedis("88");

        Boolean flag = jedisOperation.set("88", "FBI");

        String a = jedisOperation.getRedis("88");
    }
}
