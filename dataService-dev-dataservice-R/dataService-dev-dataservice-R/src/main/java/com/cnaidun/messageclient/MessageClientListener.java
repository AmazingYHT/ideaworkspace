package com.cnaidun.messageclient;

import com.cnaidun.messageclient.service.listener.MessageReceiveListener;
import com.cnaidun.messageclient.utils.ReturnMessageUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageClientListener implements MessageReceiveListener {

    @Override
    public void receive(String msg) {
        log.info("** DataService receive msg = "+msg);
    }

    @Override
    public String receiveAndSend(String msg) {
        log.info("** DataService receive msg = "+msg);
        return ReturnMessageUtils.returnSuccessObject("** This is test").toJSONString();
    }
}
