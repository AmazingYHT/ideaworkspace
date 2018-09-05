package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.WeChatUserServer;
import com.cnaidun.dataservice.service.inter.DoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 删除 id_info 信息
 */
@Service
@Slf4j
public class WORKTYPENO_USER0005 implements DoMessage {

    private String uid = "uid";

    @Autowired
    private WeChatUserServer weChatUserServer;

    @Override
    public void receive(String msg) {
        JSONObject returnObject = new JSONObject();
        // 判断是否为json格式数据
        if (!JsonUtil.isJSONObject(msg)) {
            log.error("** message is not JSONObject");
        }

        JSONObject jsonObject = JSONObject.parseObject(msg);
        // 无法获取statue 或 uid 的参数，直接返回错误格式信息
        if (!jsonObject.containsKey(this.uid))
            log.error("** receive param does not have ["+this.uid+"]");

        // 获取bean对象
        if (null == weChatUserServer)
            weChatUserServer = SpringContextUtils.getBean(WeChatUserServer.class);

        weChatUserServer.delIdInfoByUid(jsonObject.getString(this.uid));

        log.info("** del id_info data,uid="+uid);

    }

    @Override
    public String receiveAndSend(String msg) {
        return null;
    }
}
