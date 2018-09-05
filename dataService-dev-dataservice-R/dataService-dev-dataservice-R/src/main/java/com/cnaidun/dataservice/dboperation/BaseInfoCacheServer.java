package com.cnaidun.dataservice.dboperation;

import com.cnaidun.dataservice.dboperation.mysql.BaseInfoOperation;
import com.cnaidun.dataservice.dboperation.mysql.model.BaseInfo;
import com.cnaidun.dataservice.dboperation.mysql.model.BaseInfoMap;
import com.cnaidun.dataservice.dboperation.redis.JedisOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BaseInfoCacheServer {

    @Autowired
    private JedisOperation jedisOperation;

    /**
     * 增加 用户短信验证码，key： [uid].[phone]
     *
     * @param uid
     * @param phone
     * @param msg
     * @return
     */
    public boolean setUserSMS(String uid, String phone, String msg) {
        return jedisOperation.setSmsCode(uid + "." + phone, msg);
    }

    /**
     * 获取 用户短信验证码，key： [uid].[phone]
     *
     * @param uid
     * @param phone
     * @return
     */
    public String getUserSMS(String uid, String phone) {
        String msg = jedisOperation.getSmsCode(uid + "." + phone);

//        if(msg!=null ){
//            jedisOperation.deleteSmsCode(uid+"."+phone);
//        }
        return msg;
    }

    /**
     * 根据字典类型获取字典列表
     *
     * @param infoType
     * @return 字典列表的JSONArray格式，
     */
    public List<BaseInfo> getBaseInfoListByInfoType(String infoType) {
        BaseInfoOperation baseInfoOperation = new BaseInfoOperation();
        // status = "1",有效的字典
        return baseInfoOperation.getBaseInfoList(infoType, "1");

//        JSONArray returnArray = new JSONArray();
//        if (baseInfoList != null) {
//            for (BaseInfo oneBaseInfo : baseInfoList) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put(this.infotype, oneBaseInfo.getInfotype());
//                jsonObject.put(this.code, oneBaseInfo.getCode());
//                jsonObject.put(this.name, oneBaseInfo.getName());
//                jsonObject.put(this.nickset, oneBaseInfo.getNickset());
//                jsonObject.put(this.datatype, oneBaseInfo.getDatatype());
//                jsonObject.put(this.status, oneBaseInfo.getStatus());
//                returnArray.add(jsonObject);
//            }
//        }
//        return returnArray;
    }

    /**
     * 获取字典对象
     *
     * @param infoType  字典信息类型
     * @param inputCode 字典信息编码
     * @return
     */
    public BaseInfo getBaseInfoByCode(String infoType, String inputCode) {
        BaseInfoOperation baseInfoOperation = new BaseInfoOperation();
        return baseInfoOperation.getBaseInfoList(infoType, inputCode, "1");

//        JSONObject jsonObject = new JSONObject();
//        if (baseInfo != null) {
//            jsonObject.put(this.infotype, baseInfo.getInfotype());
//            jsonObject.put(this.code, baseInfo.getCode());
//            jsonObject.put(this.name, baseInfo.getName());
//            jsonObject.put(this.nickset, baseInfo.getNickset());
//            jsonObject.put(this.datatype, baseInfo.getDatatype());
//            jsonObject.put(this.status, baseInfo.getStatus());
//        }
//        return jsonObject;
    }

    /**
     * 获取字典对象
     *
     * @param parentType
     * @param parentCode
     * @param infoType
     * @return
     */
    public List<BaseInfoMap> getBaseInfoMap(String parentType, String parentCode, String infoType) {
        BaseInfoOperation baseInfoOperation = new BaseInfoOperation();
        List<BaseInfoMap> baseInfoMapList = null;
        if (parentCode == null) {
            baseInfoMapList = baseInfoOperation.getBaseInfoMapList(parentType, infoType);
        } else {
            baseInfoMapList = baseInfoOperation.getBaseInfoMapList(parentType, parentCode, infoType);
        }
        return baseInfoMapList;
//        JSONArray returnArray = new JSONArray();
//        for (BaseInfoMap oneBaseInfoMap : baseInfoMapList) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put(this.infotype, oneBaseInfoMap.getInfotype());
//            jsonObject.put(this.code, oneBaseInfoMap.getInfocode());
//            jsonObject.put(this.name, oneBaseInfoMap.getInfoname());
//            jsonObject.put(this.parenttype, oneBaseInfoMap.getParenttype());
//            jsonObject.put(this.parentcode, oneBaseInfoMap.getParentcode());
//            jsonObject.put(this.parentname, oneBaseInfoMap.getParentname());
//            returnArray.add(jsonObject);
//        }
//        return returnArray;
    }

    /**
     * 获取字典对象
     *
     * @param parentType
     * @param infoType
     * @return
     */
    public List<BaseInfoMap> getBaseInfoMap(String parentType, String infoType) {
        BaseInfoOperation baseInfoOperation = new BaseInfoOperation();
        return baseInfoOperation.getBaseInfoMapList(parentType, infoType);

//        JSONArray returnArray = new JSONArray();
//        for (BaseInfoMap oneBaseInfoMap : baseInfoMapList) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put(this.infotype, oneBaseInfoMap.getInfotype());
//            jsonObject.put(this.code, oneBaseInfoMap.getInfocode());
//            jsonObject.put(this.name, oneBaseInfoMap.getInfoname());
//            jsonObject.put(this.parenttype, oneBaseInfoMap.getParenttype());
//            jsonObject.put(this.parentcode, oneBaseInfoMap.getParentcode());
//            jsonObject.put(this.parentname, oneBaseInfoMap.getParentname());
//            returnArray.add(jsonObject);
//        }
//        return returnArray;
    }


    /**
     * 根据多个infotype获取 字典信息
     */
    public List<BaseInfo> getBaseInfoListByInfoTypeList(List<String> infoTypeList) {
        BaseInfoOperation baseInfoOperation = new BaseInfoOperation();
        return baseInfoOperation.getBaseInfoList(infoTypeList, "1");

//        // 存储需要的字典信息类型
//        HashSet<String> infoTypeSet = new HashSet<String>();
//        for (int i = 0; i < inputJson.size(); i++) {
//            JSONObject temp = (JSONObject) inputJson.get(i);
//            infoTypeSet.add(temp.getString(infotype));
//        }
//        List<String> infoTypeList = new ArrayList<String>(infoTypeSet);
//
//
//
//        JSONArray returnArray = new JSONArray();
//        for (BaseInfo oneBaseInfo : baseInfoList) {
//            String infotypeTmp = oneBaseInfo.getInfotype();
//            String codeTmp = oneBaseInfo.getCode();
//
//            for (int i = 0; i < inputJson.size(); i++) {
//                JSONObject temp = (JSONObject) inputJson.get(i);
//                if (temp.getString(infotype).equals(infotypeTmp) && temp.getString(code).equals(codeTmp)) {
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put(infotype, infotypeTmp);
//                    jsonObject.put(code, codeTmp);
//                    jsonObject.put(name, oneBaseInfo.getName());
//                    jsonObject.put(nickset, oneBaseInfo.getNickset());
//                    jsonObject.put(datatype, oneBaseInfo.getDatatype());
//                    jsonObject.put(status, oneBaseInfo.getStatus());
//                    returnArray.add(jsonObject);
//                    continue;
//                }
//            }
//        }
//        return returnArray;
    }

//    public String inputData(WorkSheet workSheet) throws Exception {
//        try {
//
//            HashOperations opsForHash = redisTemplate.opsForHash();
//
//            RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//            //key序列化方式
//            redisTemplate.setKeySerializer(redisSerializer);
//            //value序列化
//            redisTemplate.setValueSerializer(redisSerializer);
//            //value hashmap序列化
//            redisTemplate.setHashValueSerializer(redisSerializer);
//            //key haspmap序列化
//            redisTemplate.setHashKeySerializer(redisSerializer);
//
//            Map arg1 = new HashMap();
//            arg1.put("workno", workSheet.getWorkno());
//            arg1.put("workTypeCode", workSheet.getWorkno());
//            arg1.put("unitCode", workSheet.getWorkno());
//            arg1.put("uid", workSheet.getWorkno());
//            arg1.put("reqContent", workSheet.getWorkno());
//            arg1.put("createTime", "2018-05-02");
//            opsForHash.putAll(workSheet.getWorkno(), arg1);
//            return "200";
//
//        } catch (Exception e) {
//            throw new Exception("存入Redis失败");
//        }
//
//
//    }
}
