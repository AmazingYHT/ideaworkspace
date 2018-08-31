package com.cnaidun.user.api.policeUser.common;

import com.cnaidun.user.api.policeUser.common.redis.JedisServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class control {

    @Autowired
    private JedisServer jedisServer;

    @RequestMapping("/index.action")
    public void test() {
        jedisServer.getInstance();
        String a="1";
    }

}
