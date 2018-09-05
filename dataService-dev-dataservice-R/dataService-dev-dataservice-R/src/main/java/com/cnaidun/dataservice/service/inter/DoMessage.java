package com.cnaidun.dataservice.service.inter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public interface DoMessage {
    public void receive(String msg);
    public String receiveAndSend(String msg);

}
