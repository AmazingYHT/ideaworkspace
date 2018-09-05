package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.RegInfoMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.RegInfo;
import com.cnaidun.dataservice.service.inter.DoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 插入reg_info表
 */
@Service
@Slf4j
public class WORKTYPENO_USER0001 implements DoMessage {

    private String uid = "uid";

    private String openid = "openid";

    private String unitcode = "unitcode";

    private String createTime = "createTime";

    @Override
    public void receive(String msg) {
//        JSONObject returnObject = new JSONObject();
        // 判断是否为json格式数据
        if(!JsonUtil.isJSONObject(msg)){
            log.error("** message is not JSONObject");
        }

        JSONObject jsonObject = JSONObject.parseObject(msg);

        RegInfoMapper regInfoMapper = SpringContextUtils.getBean(RegInfoMapper.class);

        RegInfo regInfo = new RegInfo();
        if(jsonObject.containsKey(uid)){
            regInfo.setUid(jsonObject.getString(uid));
        }
        if(jsonObject.containsKey(openid)){
            regInfo.setOpenid(jsonObject.getString(openid));
        }
        if(jsonObject.containsKey(unitcode)){
            regInfo.setUnitcode(jsonObject.getString(unitcode));
        }
        if(jsonObject.containsKey(createTime)){
            regInfo.setCreatetime(jsonObject.getDate(createTime));
        }else{
            regInfo.setCreatetime(new Date());
        }

        regInfoMapper.insert(regInfo);
    }

    @Override
    public String receiveAndSend(String msg) {
        return null;
    }
}
