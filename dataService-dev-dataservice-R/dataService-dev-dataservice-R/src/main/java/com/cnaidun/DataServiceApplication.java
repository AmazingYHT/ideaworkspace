package com.cnaidun;

import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.redis.JedisOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@Slf4j
@EnableFeignClients
@EnableEurekaClient
@MapperScan({"com.cnaidun.messageclient","com.cnaidun.dataservice.dboperation.mysql.dao"})
public class DataServiceApplication {

	@Autowired
	JedisOperation jedisOperation;

	public static void main(String[] args) {
		SpringApplication.run(DataServiceApplication.class, args);
	}
}
