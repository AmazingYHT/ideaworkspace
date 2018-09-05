package com.cnaidun.dataservice.dboperation.redis;

import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.BaseInfoMapper;
import com.cnaidun.dataservice.dboperation.redis.config.JedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@Slf4j
public class JedisOperation {

//    @Autowired
//    private JedisConfig jedisConfig;

    private String smsDB = "USER_SMS";

    private static String code="123456";

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    /**
     * 增加短信验证码的数据
     * @param key
     * @param value
     * @return
     */
    public boolean setSmsCode(String key, String value) {
        log.info("** set SMS key : "+key + ", ");
        if(null == redisTemplate)
            redisTemplate = SpringContextUtils.getBean(RedisTemplate.class);
        log.info("** redis info {}",redisTemplate.getConnectionFactory().getClusterConnection().toString());
        HashOperations<String,String,String> opsForValue = redisTemplate.opsForHash();
//        Long l = -1L;
        try{
            opsForValue.put(this.smsDB, key, value);
            log.info("** set SMS OK : ");
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
//        if (l > -1)
//            return true;
//        else
//            return false;
    }

    /**
     * 获取短信验证码的数据
     * @param key
     * @return
     */
    public String getSmsCode(String key) {
        log.info("** get SMS key : "+key);
        try{
            if(null == redisTemplate)
                redisTemplate = SpringContextUtils.getBean(RedisTemplate.class);
            log.info("** redis info {}",redisTemplate.getConnectionFactory().getClusterConnection().toString());
            HashOperations<String,String,String> opsForValue = redisTemplate.opsForHash();
//            Jedis jedis = jedisConfig.getJedis();
//            String value = jedis.hget(this.smsDB, key);
            String value = opsForValue.get(this.smsDB, key);
            log.info("** get SMS value : "+value);
            return value;
        }catch (Exception e){
            log.error("** get SMS error : {}",e.getMessage());
        }
        return null;
    }

    /**
     * 删除短信验证码的数据
     * @param key
     * @return
     */
//    public boolean deleteSmsCode(String key) {
//        Jedis jedis = jedisConfig.getJedis();
//        Long l = jedis.del(this.smsDB, key);
//        if (l > 0)
//            return true;
//        else
//            return false;
//    }
}
