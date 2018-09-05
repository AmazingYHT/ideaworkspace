package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.model.IdInfo;
import com.cnaidun.dataservice.dboperation.WeChatUserServer;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 判断微信用户的实名认证的身份证(idno)是否已经被注册过了
 * 获取id_info列表
 */
@Service
public class WORKTYPENO_USER0004 implements DoMessage {

    private String uid = "uid";

    private String idno ="idno";

    private String name = "name";

    private String birthday = "birthday";

    private String sex = "sex";

    private String nation = "nation";

    private String citycode = "citycode";

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
        if (!JsonUtil.isJSONObject(msg)) {
            returnObject = ReturnMessageUtils.returnError1102Object("** message is not JSONObject");
        }

        JSONObject jsonObject = JSONObject.parseObject(msg);
        // 无法获取statue 或 uid 的参数，直接返回错误格式信息
        if (!jsonObject.containsKey(idno))
            returnObject = ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_1102, ReturnMessageUtils.ERROR_1102_MSG, "** receive param does not have [idno]");

        String idnoValue = jsonObject.getString(idno);

        // 获取bean对象
        if (null == weChatUserServer)
            weChatUserServer = SpringContextUtils.getBean(WeChatUserServer.class);

        List<IdInfo> idInfoList = weChatUserServer.getIdInfoByIdno(idnoValue);
        if (null == idInfoList || idInfoList.size() == 0) {
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.SUCCESS_201, ReturnMessageUtils.SUCCESS_201_MSG, "").toJSONString();
        }

        JSONArray retArray = new JSONArray();
        for (IdInfo oneIdInfo : idInfoList) {
            JSONObject oneObject = new JSONObject();
            oneObject.put(uid,oneIdInfo.getUid());
            oneObject.put(idno,oneIdInfo.getUid());
            oneObject.put(name,oneIdInfo.getName());
            oneObject.put(birthday,oneIdInfo.getBirthday());
            oneObject.put(sex,oneIdInfo.getSex());
            oneObject.put(nation,oneIdInfo.getNation());
            oneObject.put(citycode,oneIdInfo.getCitycode());
            oneObject.put(address,oneIdInfo.getAddress());
            oneObject.put(createtime,oneIdInfo.getCreatetime());
            oneObject.put(idpositive,oneIdInfo.getIdpositive());
            oneObject.put(idopposite,oneIdInfo.getIdopposite());

            retArray.add(oneObject);
        }

        return ReturnMessageUtils.returnSuccessObject(retArray.toJSONString()).toJSONString();
    }
}
