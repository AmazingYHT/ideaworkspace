package com.cnaidun.dataservice;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.config.InitWorktypenoConfig;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.MessageProperties;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;
import com.cnaidun.messageclient.service.listener.MessageReceiveListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageClientListener implements MessageReceiveListener {

    @Override
    public void receive(String msg) {
        log.info("** DataService receive msg = "+msg);
        if(null == msg){
            log.warn("** recevied msg is null !!!!!!!!");
            return;
        }

        if(JsonUtil.isJSONObject(msg)){
            JSONObject jsonObject = JSONObject.parseObject(msg);
            if(!jsonObject.containsKey(MessageProperties.WORKTYPECODE)){
                log.warn("** Can find msg paramerName = workTypeCode !");
            }
            String worktypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);
            String classPath = InitWorktypenoConfig.workTypeNoMap.get(worktypeCode);
            try {
                Class<?> clazz = Class.forName(classPath);
                DoMessage doMessage = (DoMessage)clazz.newInstance();
                doMessage.receive(msg);
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }else{

        }
    }

    @Override
    public String receiveAndSend(String msg) {
        log.info("** DataService receive msg = "+msg);
        if(null == msg){
            log.warn("** recevied msg is null !");
            return ReturnMessageUtils.returnError1102Object("** dataService receiveAndSend recevied msg is null!").toJSONString();
        }

        if(JsonUtil.isJSONObject(msg)){
            JSONObject jsonObject = JSONObject.parseObject(msg);
            if(!jsonObject.containsKey(MessageProperties.WORKTYPECODE)){
                log.warn("** Can find msg paramerName = workTypeCode!!!!!!!!");
                return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_40035,ReturnMessageUtils.ERROR_40035_MSG,"** Can find msg paramerName = workTypeCode!!!!!!!!").toJSONString();
            }
            String worktypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);
            try {
                log.info("The workTypeNoMap {}", worktypeCode);

                Class<?> clazz = Class.forName(InitWorktypenoConfig.workTypeNoMap.get(worktypeCode));
                if(null == clazz){
                    return ReturnMessageUtils.returnError1102Object("** the worktypeCode="+worktypeCode+" is not register").toJSONString();
                }
                DoMessage doMessage = (DoMessage)clazz.newInstance();
                return doMessage.receiveAndSend(msg);
            }catch (Exception e){
                e.printStackTrace();
                log.error("** Can find the worktypeCode="+worktypeCode+" 's doclass!");
            }
        }else{
            //return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.SUCCESS_200,ReturnMessageUtils.SUCCESS_200_MSG,"").toJSONString();
        }
        return ReturnMessageUtils.returnError1102Object("** dataService receiveAndSend do nothing").toJSONString();
    }
}
