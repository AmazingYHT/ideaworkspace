package com.cnaidun.dataservice.service;

import com.cnaidun.dataservice.service.inter.DoMessage;
import org.springframework.stereotype.Service;

/**
 *
 * 1、根据父节点类别、父节点编号、子节点类别 获取子节点列表
 * 2、根据节点类别获取节点列表
 * 3、根据节点类别、节点编码获取具体节点信息节点
 */
@Service
public class WORKTYPENO_CACHE00002 implements DoMessage {

    private String infotypeStr = "infotype";
    private String codeStr = "code";

    @Override
    public void receive(String msg) {

    }

    /**
     * 根据指定的[节点类别、节点编码]列表，获取这些节点的具体信息
     * 入参：JSONArray，[{infotype:xxx,code:xxxx},{{infotype:xxx,code:xxxx},...]
     * 返回：JSONArray，[{infotype:xxx,code:xxxx,name:xxxxx,nickset:xxxxxx,datatype:xxxxxx,status},{infotype:xxx,code:xxxx,name:xxxxx,nickset:xxxxxx,datatype:xxxxxx,status},...]
     */
    @Override
    public String receiveAndSend(String msg) {

//        // 判断是否为json格式数据
//        if (!JsonUtil.isJSONObject(msg)) {
//            return ReturnMessageUtils.returnError1102Object("** message is not JSONObject").toJSONString();
//        }
//        JSONObject inputObject = JSONObject.parseObject(msg);
//        BaseInfoCacheServer baseInfoCacheServer = SpringContextUtils.getBean(BaseInfoCacheServer.class);
//
//        JSONArray jsonArray = (JSONArray)inputObject.get("data");
//
//        baseInfoCacheServer.getBaseInfoListByInfoTypeList()
//        return baseInfoCacheServer.getCacheData(jsonArray).toJSONString();
         return null;
    }
}
