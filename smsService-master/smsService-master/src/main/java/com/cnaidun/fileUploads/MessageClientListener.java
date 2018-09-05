package com.cnaidun.fileUploads;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.fileUploads.api.fileUploads.utils.SMSService;
import com.cnaidun.messageclient.MQServer;
import com.cnaidun.messageclient.service.listener.MessageReceiveListener;
import com.cnaidun.messageclient.utils.ReturnMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MessageClientListener implements MessageReceiveListener {


    @Override
    public void receive(String msg) {

        log.info("** DataService receive msg = "+msg);
    }

    @Override
    public String receiveAndSend(String msg) {
        log.info("** DataService receive msg = "+msg);
        Map mapType = JSON.parseObject(msg,Map.class);
        Map map = new HashMap();
        for (Object obj : mapType.keySet()){
            log.info("key为："+obj+"值为："+mapType.get(obj));
            map.put(obj,mapType.get(obj));
        }
        if (map.get("workTypeCode")!=null&&map.get("workTypeCode").equals("SMS0003")){
            if (map.get("phone")==null||map.get("code")==null){
                log.info("phone:"+map.get("phone")+"   code:"+map.get("code"));
                ReturnMessageUtils.returnSuccessObject("40035").toJSONString();
            }
            String[] params = {(String)map.get("code"),"5"};
            String result =  SMSService.sendMeModel((String) map.get("phone"),params);
            if(!result.equals("OK")){
                log.info(result);
                ReturnMessageUtils.returnSuccessObject(result).toJSONString();
            }
        }
        else if(map.get("workTypeCode")!=null&&map.get("workTypeCode").equals("SMS0004")){
            if (map.get("phone") == null ){
                ReturnMessageUtils.returnSuccessObject("40035").toJSONString();
            }
            if (map.get("param") == null){
                ReturnMessageUtils.returnSuccessObject("40035").toJSONString();
            }
            if (map.get("templateId") == null){
                ReturnMessageUtils.returnSuccessObject("40035").toJSONString();
            }
            JSONArray  arrayPhone = (JSONArray)map.get("phone");
            String[] phones = null;
            if(arrayPhone.size()>0){
                phones = new String[arrayPhone.size() ];
                for(int i=0;i<arrayPhone.size();i++) {
                    phones[i] = (String)arrayPhone.get(i);
                }
            }else{
                phones = new String[0];
            }

            JSONArray arrayParam = (JSONArray) map.get("param");
            String[] params = null;
            if(arrayParam.size()>0){
                params = new String[arrayParam.size() ];
                for(int i=0;i<arrayParam.size();i++) {
                    params[i] = (String)arrayParam.get(i);
                }
            }else{
                params = new String[0];
            }
            int templateid = Integer.parseInt((String)map.get("templateId"));
            String result = SMSService.sendMesModels(phones,params,templateid);
            if(!result.equals("OK")){
                log.info(result);
                ReturnMessageUtils.returnSuccessObject(result).toJSONString();
            }
        }

        return ReturnMessageUtils.returnSuccessObject("200").toJSONString();
    }

}
