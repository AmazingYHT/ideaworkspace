package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.BaseInfoCacheServer;
import com.cnaidun.dataservice.dboperation.mysql.model.BaseInfo;
import com.cnaidun.dataservice.dboperation.mysql.model.BaseInfoMap;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 获取字典表类
 * 1、根据父节点类别、父节点编号、子节点类别 获取子节点列表
 * 2、根据节点类别获取节点列表
 * 3、根据节点类别、节点编码获取具体节点信息节点
 */
@Service
public class WORKTYPENO_CACHE00001 implements DoMessage {

    private String infotype = "infotype";
    private String code = "code";
    private String name = "name";
    private String nickset = "nickset";
    private String datatype = "datatype";
    private String status = "status";
    private String parenttype = "parenttype";
    private String parentcode = "parentcode";
    private String parentname = "parentname";

    @Override
    public void receive(String msg) {

    }

    /**
     * 获取字典表类
     * 1、根据父节点类别、父节点编号、子节点类别 获取子节点列表
     * 2、根据节点类别获取节点列表
     * 3、根据节点类别、节点编码获取具体节点信息节点
     */
    @Override
    public String receiveAndSend(String msg) {

        // 判断是否为json格式数据
        if (!JsonUtil.isJSONObject(msg)) {
            return ReturnMessageUtils.returnError1102Object("** message is not JSONObject").toJSONString();
        }

        BaseInfoCacheServer baseInfoCacheServer = SpringContextUtils.getBean(BaseInfoCacheServer.class);
        JSONObject jsonObject = JSONObject.parseObject(msg);
        // 根据父节点获取子节点名称
        if (jsonObject.containsKey(this.parenttype) && jsonObject.containsKey(this.parentcode) && jsonObject.containsKey(this.infotype)) {
            List<BaseInfoMap> baseInfoMapList = baseInfoCacheServer.getBaseInfoMap(jsonObject.getString(this.parenttype), jsonObject.getString(this.parentcode), jsonObject.getString(this.infotype));
            if (null != baseInfoMapList) {
                JSONArray returnArray = new JSONArray();
                for (BaseInfoMap oneBaseInfoMap : baseInfoMapList) {
                    JSONObject temp = new JSONObject();
                    temp.put(this.infotype, oneBaseInfoMap.getInfotype());
                    temp.put(this.code, oneBaseInfoMap.getInfocode());
                    temp.put(this.name, oneBaseInfoMap.getInfoname());
                    temp.put(this.parenttype, oneBaseInfoMap.getParenttype());
                    temp.put(this.parentcode, oneBaseInfoMap.getParentcode());
                    temp.put(this.parentname, oneBaseInfoMap.getParentname());
                    returnArray.add(jsonObject);
                }
                return ReturnMessageUtils.returnSuccessObject(returnArray.toJSONString()).toJSONString();
            }

            // 根据父节点获取子节点名称
            if (jsonObject.containsKey(this.parenttype) && jsonObject.containsKey(this.infotype)) {
                List<BaseInfoMap> baseInfoMapList1 = baseInfoCacheServer.getBaseInfoMap(jsonObject.getString(this.parenttype), jsonObject.getString(this.infotype));
                JSONArray returnArray = new JSONArray();
                for (BaseInfoMap oneBaseInfoMap : baseInfoMapList1) {
                    JSONObject temp = new JSONObject();
                    temp.put(this.infotype, oneBaseInfoMap.getInfotype());
                    temp.put(this.code, oneBaseInfoMap.getInfocode());
                    temp.put(this.name, oneBaseInfoMap.getInfoname());
                    temp.put(this.parenttype, oneBaseInfoMap.getParenttype());
                    temp.put(this.parentcode, oneBaseInfoMap.getParentcode());
                    temp.put(this.parentname, oneBaseInfoMap.getParentname());
                    returnArray.add(jsonObject);
                }
                return ReturnMessageUtils.returnSuccessObject(returnArray.toJSONString()).toJSONString();
            }

            // 根据字典信息类别获取信息列表
            if (jsonObject.containsKey(this.infotype) && !jsonObject.containsKey(this.code)) {
                List<BaseInfo> baseInfoList = baseInfoCacheServer.getBaseInfoListByInfoType(this.infotype);
                JSONArray returnArray = new JSONArray();
                if (baseInfoList != null) {
                    for (BaseInfo oneBaseInfo : baseInfoList) {
                        JSONObject temp = new JSONObject();
                        temp.put(this.infotype, oneBaseInfo.getInfotype());
                        temp.put(this.code, oneBaseInfo.getCode());
                        temp.put(this.name, oneBaseInfo.getName());
                        temp.put(this.nickset, oneBaseInfo.getNickset());
                        temp.put(this.datatype, oneBaseInfo.getDatatype());
                        temp.put(this.status, oneBaseInfo.getStatus());
                        returnArray.add(jsonObject);
                    }
                }
                return ReturnMessageUtils.returnSuccessObject(returnArray.toJSONString()).toJSONString();
            }

            // 根据字典信息类别、信息编码获取详细信息
            if (jsonObject.containsKey(this.infotype) && jsonObject.containsKey(this.code)) {
                BaseInfo baseInfo = baseInfoCacheServer.getBaseInfoByCode(jsonObject.getString(this.infotype), jsonObject.getString(this.code));
                JSONObject temp = new JSONObject();
                if (baseInfo != null) {
                    temp.put(this.infotype, baseInfo.getInfotype());
                    temp.put(this.code, baseInfo.getCode());
                    temp.put(this.name, baseInfo.getName());
                    temp.put(this.nickset, baseInfo.getNickset());
                    temp.put(this.datatype, baseInfo.getDatatype());
                    temp.put(this.status, baseInfo.getStatus());
                }
                return ReturnMessageUtils.returnSuccessObject(temp.toJSONString()).toJSONString();
            }
        }

        return ReturnMessageUtils.returnError1102Object("** WORKTYPENO_CACHE00001 error").toJSONString();
    }
}
