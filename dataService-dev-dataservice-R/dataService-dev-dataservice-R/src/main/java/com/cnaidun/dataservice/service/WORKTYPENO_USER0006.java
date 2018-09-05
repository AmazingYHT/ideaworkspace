package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.model.IdInfo;
import com.cnaidun.dataservice.dboperation.WeChatUserServer;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 根据uid获取 微信用户的实名认证 的信息
 */
@Service
@Slf4j
public class WORKTYPENO_USER0006 implements DoMessage {

    private String uid = "uid";

    private String idno ="idno";

    private String name = "name";

    private String birthday = "birthday";

    private String sex = "sex";

    private String phone = "phone";

    private String nation = "nation";

    private String citycode = "citycode";

    private String validdate = "validdate";

    private String authority = "authority";

    private String address = "address";

    private String createtime = "createtime";

    private String idpositive = "idpositive";
    private String idopposite = "idopposite";

    @Autowired
    private WeChatUserServer weChatUserServer;

    @Override
    public void receive(String msg) {

    }

    @Override
    public String receiveAndSend(String msg) {
        JSONObject returnObject = new JSONObject();
        // 判断是否为json格式数据
        if(!JsonUtil.isJSONObject(msg)){
            returnObject=ReturnMessageUtils.returnError1102Object("** message is not JSONObject");
        }

        JSONObject jsonObject = JSONObject.parseObject(msg);
        // 无法获取statue 或 uid 的参数，直接返回错误格式信息
        if(!jsonObject.containsKey(uid))
            returnObject = ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_1102,ReturnMessageUtils.ERROR_1102_MSG,"** receive param does not have [uid]");

        String uidValue = jsonObject.getString(uid);

        // 获取bean对象
        if(null == weChatUserServer)
            weChatUserServer= SpringContextUtils.getBean(WeChatUserServer.class);

        List<IdInfo>  idInfoList = weChatUserServer.getIdInfoByUid(uidValue);
        if(null == idInfoList || idInfoList.size()==0 ){
            return  ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.SUCCESS_201,ReturnMessageUtils.SUCCESS_201_MSG,"").toJSONString();
        }
        JSONArray retArray = new JSONArray();
        for(IdInfo oneIdInfo:idInfoList){
            JSONObject oneObject = new JSONObject();
            oneObject.put(uid,oneIdInfo.getUid());
            oneObject.put(idno,oneIdInfo.getIdno());
            oneObject.put(name,oneIdInfo.getName());
            oneObject.put(birthday,oneIdInfo.getBirthday());
            oneObject.put(sex,oneIdInfo.getSex());
            oneObject.put(phone, oneIdInfo.getPhone());
            oneObject.put(nation,oneIdInfo.getNation());
            oneObject.put(citycode,oneIdInfo.getCitycode());
            oneObject.put(address,oneIdInfo.getAddress());
            oneObject.put(createtime,oneIdInfo.getCreatetime());
            oneObject.put(idpositive,oneIdInfo.getIdpositive());
            oneObject.put(idopposite,oneIdInfo.getIdopposite());
            oneObject.put(validdate,oneIdInfo.getValiddate());
            oneObject.put(authority,oneIdInfo.getAuthority());

            retArray.add(oneObject);
        }

        return ReturnMessageUtils.returnSuccessObject(retArray.toJSONString()).toJSONString();
    }
}
