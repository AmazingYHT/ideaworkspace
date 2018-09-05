package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.WeChatUserServer;
import com.cnaidun.dataservice.dboperation.mysql.model.IdInfo;
import com.cnaidun.dataservice.dboperation.mysql.model.RegInfo;
import com.cnaidun.dataservice.dboperation.mysql.model.WeixinSend;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取指定微信发送的消息信息，根据发送状态获取，并连表查询相应的idno、uid、openid等信息
 */
@Slf4j
public class WORKTYPENO_TASK0003 implements DoMessage {

    @Autowired
    private WeChatUserServer weChatUserServer;

    private String wid = "wid";

    private String idno = "idno";

    private String mobile = "mobile";

    private String startday = "startday";

    private String endday = "endday";

    private String wsend = "wsend";

    private String createtime = "createtime";

    private String dsend = "dsend";

    private String uid = "uid";

    private String openid = "openid";

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

        JSONObject jsonObject = JSONObject.parseObject(msg);
        // 无法获取 dsend 的参数，直接返回错误格式信息
        if (!jsonObject.containsKey(wsend))
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_1102, ReturnMessageUtils.ERROR_1102_MSG, "** receive param does not have ["+wsend+"]").toJSONString();

        if(null == weChatUserServer)
            weChatUserServer = SpringContextUtils.getBean(WeChatUserServer.class);

        List<WeixinSend> weixinSendList =  weChatUserServer.getSMSDataByDsend(jsonObject.getInteger(wsend));
        if(null == weixinSendList)
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_1102, ReturnMessageUtils.ERROR_1102_MSG, "can get the WeixinSend").toJSONString();

        // 获取所有的idno，形成列表
        List<String> idnoList = new ArrayList<>();

        JSONArray returnArray = new JSONArray();
        for(WeixinSend weixinSend:weixinSendList){
            idnoList.add(weixinSend.getIdno());

            JSONObject object = new JSONObject();
            object.put(wid,weixinSend.getWid());
            object.put(idno,weixinSend.getIdno());
            object.put(mobile,weixinSend.getMobile());
            object.put(startday,weixinSend.getStartday());
            object.put(endday,weixinSend.getEndday());
            object.put(wsend,weixinSend.getWsend());
            object.put(createtime,weixinSend.getCreatetime());
            object.put(dsend,weixinSend.getDsend());
            returnArray.add(object);
        }

        // 获取IdInfo队列
        List<IdInfo> idInfoList = weChatUserServer.getIdInfoByIdnoList(idnoList);
        // 准备uid 队列，查询regInfo表
        List<String> uidList = new ArrayList<>();
        for(int i=0;i<returnArray.size();i++){
            JSONObject object = returnArray.getJSONObject(i);
            String tmpIdno = object.getString(idno);
            for(IdInfo idInfo:idInfoList){
                if(idInfo.getIdno().equals(tmpIdno)){
                    object.put(this.uid,idInfo.getUid());
                    uidList.add(idInfo.getUid());
                    continue;
                }
            }
        }

        List<RegInfo> regInfoList = weChatUserServer.getRegInfoByUidList(uidList);
        for(int i=0;i<returnArray.size();i++){
            JSONObject object = returnArray.getJSONObject(i);
            String tmpUid = object.getString(this.uid);
            for(RegInfo regInfo:regInfoList){
                if(regInfo.getUid().equals(tmpUid)){
                    object.put(openid,regInfo.getOpenid());
                    continue;
                }
            }
        }

        return ReturnMessageUtils.returnSuccessObject(returnArray).toJSONString();
    }
}
