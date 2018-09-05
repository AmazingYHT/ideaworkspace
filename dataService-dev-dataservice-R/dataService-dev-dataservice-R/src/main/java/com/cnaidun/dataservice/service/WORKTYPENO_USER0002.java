package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.IdInfoMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.UserInfoMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.IdInfo;
import com.cnaidun.dataservice.dboperation.mysql.model.UserInfo;
import com.cnaidun.dataservice.service.inter.DoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 插入id_info和user_info
 */
@Service
@Slf4j
public class WORKTYPENO_USER0002 implements DoMessage {

    private String uid = "uid";
    private String idno = "idno";
    private String name = "name";
    private String birthday = "birthday";
    private String sex = "sex";
    private String nation = "nation";
    private String citycode = "citycode";
    private String address = "address";
    private String createtime = "createtime";
    private String validdate = "validdate";
    private String authority = "authority";
    private String phone = "phone";
    private String ename = "ename";
    private String state = "state";

    private String idpositive = "idpositive";
    private String idopposite = "idopposite";

    @Override
    public void receive(String msg) {
        // 判断是否为json格式数据
        if(!JsonUtil.isJSONObject(msg)){
            log.error("** message is not JSONObject");
        }
        JSONObject jsonObject = JSONObject.parseObject(msg);
        if(!jsonObject.containsKey(uid) || jsonObject.containsKey(idno)){
            log.error("** Can not find the paramer in (uid,idno)");
        }

        IdInfo idInfo = new IdInfo();
        idInfo.setUid(jsonObject.getString("uid"));
        idInfo.setIdno(jsonObject.getString("idno"));

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(jsonObject.getString("uid"));

        if(jsonObject.containsKey(name)) {
            idInfo.setName(jsonObject.getString(name));
            userInfo.setName(jsonObject.getString(name));
        }

        if(jsonObject.containsKey(birthday))
            idInfo.setBirthday(jsonObject.getDate(birthday));

        if(jsonObject.containsKey(sex))
            idInfo.setSex(jsonObject.getString(sex));

        if(jsonObject.containsKey(idpositive))
            idInfo.setIdpositive(jsonObject.getString(idpositive));
        if(jsonObject.containsKey(idopposite))
            idInfo.setIdopposite(jsonObject.getString(idopposite));

        if(jsonObject.containsKey(nation))
            idInfo.setNation(jsonObject.getString(nation));

        if(jsonObject.containsKey(citycode)){
            idInfo.setCitycode(jsonObject.getString(citycode));
            userInfo.setCitycode(jsonObject.getString(citycode));
        }

        if(jsonObject.containsKey(address)) {
            idInfo.setAddress(jsonObject.getString(address));
            userInfo.setAddress(jsonObject.getString(address));
        }

        if(jsonObject.containsKey(createtime)) {
            idInfo.setCreatetime(jsonObject.getDate(createtime));
            userInfo.setCreatetime(jsonObject.getDate(createtime));
        }else {
            Date date = new Date();
            idInfo.setCreatetime(date);
            userInfo.setCreatetime(date);
        }

        if(jsonObject.containsKey(validdate))
            idInfo.setValiddate(jsonObject.getString(validdate));

        if(jsonObject.containsKey(authority))
            idInfo.setAuthority(jsonObject.getString(authority));

        if(jsonObject.containsKey(phone)){
            idInfo.setPhone(jsonObject.getString(phone));
            userInfo.setPhone(jsonObject.getString(phone));
        }

        if(jsonObject.containsKey(state)){
            userInfo.setState(jsonObject.getString(state));
        }

        if(jsonObject.containsKey(ename))
            userInfo.setEname(jsonObject.getString(ename));

        IdInfoMapper idInfoMapper = SpringContextUtils.getBean(IdInfoMapper.class);

        IdInfo data = idInfoMapper.selectByPrimaryKey(idInfo.getUid(),idInfo.getIdno());
        if(data==null){
            idInfoMapper.insert(idInfo);
        }else{
            log.warn("IdInfo data is exit : uid = "+idInfo+", idno="+idInfo.getIdno());
            idInfoMapper.updateByPrimaryKey(idInfo);
        }

        UserInfoMapper userInfoMapper = SpringContextUtils.getBean(UserInfoMapper.class);

        UserInfo userData = userInfoMapper.selectByPrimaryKey(userInfo.getUid());
        if(userData==null){
            userInfoMapper.insert(userInfo);
        }else{
            log.warn("UserInfo data is exit : uid = "+userInfo.getUid());
            userInfoMapper.updateByPrimaryKey(userInfo);
        }
    }

    @Override
    public String receiveAndSend(String msg) {
        return null;
    }
}
