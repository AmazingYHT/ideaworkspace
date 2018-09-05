package com.cnaidun.dataservice.config;

import com.cnaidun.dataservice.dboperation.BaseInfoCacheServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 2)
public class InitRedisCache implements ApplicationRunner {

    @Autowired
    private BaseInfoCacheServer baseInfoCacheServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        baseInfoCacheServer.initCacheData();
//       baseInfoCacheServer.Test();
    }
}
