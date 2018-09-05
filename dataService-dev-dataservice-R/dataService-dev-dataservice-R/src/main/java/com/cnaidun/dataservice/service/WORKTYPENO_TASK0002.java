package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.WeChatUserServer;
import com.cnaidun.dataservice.dboperation.mysql.model.WeixinSend;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 修改 短信、微信的发送状态
 * 短信状态：入参wid、dsend
 * 微信状态：入参wid、wsend
 */
@Slf4j
public class WORKTYPENO_TASK0002 implements DoMessage {

    @Autowired
    private WeChatUserServer weChatUserServer;

    private String wid = "wid";

    private String data = "data";

    private String idno = "idno";

    private String mobile = "mobile";

    private String startday = "startday";

    private String endday = "endday";

    private String wsend = "wsend";

    private String sendday = "sendday";

    private String dsend = "dsend";

    @Override
    public void receive(String msg) {
        receiveAndSend(msg);
    }

    @Override
    public String receiveAndSend(String msg) {
        // 判断是否为json格式数据
        if (!JsonUtil.isJSONObject(msg)) {
            return ReturnMessageUtils.returnError1102Object("** message is not JSONObject").toJSONString();
        }

        JSONObject getJson = JSONObject.parseObject(msg);

        if(!getJson.containsKey(this.data)){
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_1102, ReturnMessageUtils.ERROR_1102_MSG, "** receive param does not have ["+this.data+"]").toJSONString();
        }

        // 获取数据区
        JSONArray jsonArray=getJson.getJSONArray(this.data);
        if(null == weChatUserServer)
            weChatUserServer = SpringContextUtils.getBean(WeChatUserServer.class);

        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            // 无法获取 dsend 的参数，直接返回错误格式信息
            if (!jsonObject.containsKey(this.wid)){
                log.warn("** receive param does not have {}",this.wid);
                continue;
            }

            String widStr = jsonObject.getString(this.wid);

            if(jsonObject.containsKey(this.dsend)){
                int dsendInt = jsonObject.getInteger(this.dsend);
                if(weChatUserServer.updateSMSDataDsendByWid(widStr,dsendInt))
                    log.debug("** updateSMSDataDsendByWid success : {},{}",widStr,dsendInt);
                else
                    log.error("** updateSMSDataDsendByWid error : {},{}",widStr,dsendInt);
            }else if(jsonObject.containsKey(this.wsend)){
                int wsendInt = jsonObject.getInteger(this.wsend);
                if(weChatUserServer.updateSMSDataWsendByWid(widStr,wsendInt))
                    log.debug("** updateSMSDataWsendByWid success : {},{}",widStr,wsendInt);
                else
                    log.error("** updateSMSDataWsendByWid error : {},{}",widStr,wsendInt);
            }else{
                log.warn("** receive param does not have {}",this.wid);
                continue;
            }
        }

        return ReturnMessageUtils.returnSuccessObject("It's OK").toJSONString();
    }
}
