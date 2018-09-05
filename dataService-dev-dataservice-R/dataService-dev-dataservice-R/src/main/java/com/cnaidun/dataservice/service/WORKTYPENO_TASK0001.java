package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.WeChatUserServer;
import com.cnaidun.dataservice.dboperation.mysql.model.WeixinSend;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 根据短信发送标识位查询数据，返回WeixinSend对象
 */
public class WORKTYPENO_TASK0001 implements DoMessage {

    @Autowired
    private WeChatUserServer weChatUserServer;

    private String wid = "wid";

    private String idno = "idno";

    private String mobile = "mobile";

    private String startday = "startday";

    private String endday = "endday";

    private String wsend = "wsend";

    private String sendday = "sendday";

    private String dsend = "dsend";

    @Override
    public void receive(String msg) {

    }

    @Override
    public String receiveAndSend(String msg) {
        // 判断是否为json格式数据
        if (!JsonUtil.isJSONObject(msg)) {
            return ReturnMessageUtils.returnError1102Object("** message is not JSONObject").toJSONString();
        }

        JSONObject jsonObject = JSONObject.parseObject(msg);
        // 无法获取 dsend 的参数，直接返回错误格式信息
        if (!jsonObject.containsKey(dsend))
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_1102, ReturnMessageUtils.ERROR_1102_MSG, "** receive param does not have ["+dsend+"]").toJSONString();

        if(null == weChatUserServer)
            weChatUserServer = SpringContextUtils.getBean(WeChatUserServer.class);

        List<WeixinSend> weixinSendList =  weChatUserServer.getSMSDataByDsend(jsonObject.getInteger(dsend));
        if(null == weixinSendList)
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_1102, ReturnMessageUtils.ERROR_1102_MSG, "can get the WeixinSend").toJSONString();

        JSONArray returnObject = new JSONArray();
        for(WeixinSend weixinSend:weixinSendList){
            JSONObject object = new JSONObject();
            object.put(wid,weixinSend.getWid());
            object.put(idno,weixinSend.getIdno());
            object.put(mobile,weixinSend.getMobile());
            object.put(startday,weixinSend.getStartday());
            object.put(endday,weixinSend.getEndday());
            object.put(wsend,weixinSend.getWsend());
            object.put(dsend,weixinSend.getDsend());
            returnObject.add(object);
        }
        return ReturnMessageUtils.returnSuccessObject(returnObject).toJSONString();

    }
}
