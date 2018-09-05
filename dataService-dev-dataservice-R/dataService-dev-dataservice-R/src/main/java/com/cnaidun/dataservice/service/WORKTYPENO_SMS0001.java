package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.BaseInfoCacheServer;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WORKTYPENO_SMS0001 implements DoMessage {

    @Autowired
    private BaseInfoCacheServer baseInfoCacheServer;

    private String uid = "uid";

    private String phone = "phone";

    private String msgCode = "msgCode";

    @Override
    public void receive(String msg) {
    }

    @Override
    public String receiveAndSend(String msg) {
        JSONObject returnObject = new JSONObject();
        // 判断是否为json格式数据
        if (!JsonUtil.isJSONObject(msg)) {
            returnObject = ReturnMessageUtils.returnError1102Object("** message is not JSONObject");
        }

        JSONObject jsonObject = JSONObject.parseObject(msg);
        // 无法获取statue 或 uid 的参数，直接返回错误格式信息
        if (!jsonObject.containsKey(uid) || !jsonObject.containsKey(phone) || !jsonObject.containsKey(msgCode))
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_1102, ReturnMessageUtils.ERROR_1102_MSG, "** receive param does not have ["+uid+"] or ["+phone+"] or ["+msgCode+"]").toJSONString();

        if(baseInfoCacheServer==null){
            baseInfoCacheServer = SpringContextUtils.getBean(BaseInfoCacheServer.class);
        }
        if(baseInfoCacheServer.setUserSMS(jsonObject.getString(uid),jsonObject.getString(phone),jsonObject.getString(msgCode))){
            return ReturnMessageUtils.returnSuccessObject(null).toJSONString();
        }else{
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_1102, ReturnMessageUtils.ERROR_1102_MSG, "** receive param does not have ["+uid+"] or ["+phone+"] or ["+msgCode+"]").toJSONString();
        }
    }
}
