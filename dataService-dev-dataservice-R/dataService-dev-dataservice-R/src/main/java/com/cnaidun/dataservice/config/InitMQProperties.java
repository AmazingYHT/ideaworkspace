package com.cnaidun.dataservice.config;

import com.cnaidun.dataservice.MessageClientListener;
import com.cnaidun.messageclient.MQServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 10000)
public class InitMQProperties implements ApplicationRunner {

    @Autowired
    private MQServer mqServer ;

    @Override
    public void run(ApplicationArguments var1) throws Exception {
//        MQServer mqServer = new MQServer();
        mqServer.registerListener(new MessageClientListener());
    }
}
