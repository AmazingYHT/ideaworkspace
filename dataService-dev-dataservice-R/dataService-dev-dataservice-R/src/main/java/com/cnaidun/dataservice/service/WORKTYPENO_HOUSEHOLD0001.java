package com.cnaidun.dataservice.service;

import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.service.inter.DoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WORKTYPENO_HOUSEHOLD0001  implements DoMessage {

    @Autowired
    private WORKTYPENO_ENTRYANDEXIT0001 worktypeno_entryandexit0001;

    @Override
    public void receive(String msg) {
        worktypeno_entryandexit0001.receive(msg);
    }

    @Override
    public String receiveAndSend(String msg) {
        return worktypeno_entryandexit0001.receiveAndSend(msg);
    }
}

