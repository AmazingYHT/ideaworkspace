package com.cnaidun.dataservice.jsontemplates;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.BaseInfoMapper;
import com.cnaidun.dataservice.utils.MessageProperties;
import com.cnaidun.messageclient.MQServer;

import java.util.Map;

public class IntersectionTemplate {
    /**
     *机动车驾驶人联系方式变更-提交业务json格式转换
     * @param jsonObject
     * @return`
     */
    public JSONObject INTERSECTION0001(JSONObject jsonObject){
        String workTypeCode=jsonObject.getString(MessageProperties.WORKTYPECODE );
        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);

        MQServer mqServer=SpringContextUtils.getBean(MQServer.class);

        String tempalateJsonStr=baseInfoMapper.findTemplateByWorkTypeCode(workTypeCode);

        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            if (!"uid".equals(key)) {
                tempalateJsonStr = tempalateJsonStr.replace(key, value);
            }
        }
        JSONObject tempalteJson= (JSONObject) JSONObject.parse(tempalateJsonStr);

        String returnJsonStr= mqServer.sendAndReceive("CNAIDUN", "",
                "DATAINTERCHANGE", "INTERSECTION0001DSF", tempalateJsonStr);
/*
        tempalteJson.put(MessageProperties.FUNCCODE,jsonObject.getString(MessageProperties.WORKTYPECODE ));
*/
        return JSONObject.parseObject(returnJsonStr);
    }
    /**
     *机动车车辆联系方式变更-提交业务json格式转换
     * @param jsonObject
     * @return
     */
    public JSONObject INTERSECTION0003(JSONObject jsonObject){

        String workTypeCode=jsonObject.getString(MessageProperties.WORKTYPECODE );

        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);

        MQServer mqServer=SpringContextUtils.getBean(MQServer.class);

        String tempalateJsonStr=baseInfoMapper.findTemplateByWorkTypeCode(workTypeCode);

        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key=entry.getKey();
            String value=(String) entry.getValue();
            if( !"uid".equals(key)){
                tempalateJsonStr = tempalateJsonStr.replace(key,value);
            }
        }
        JSONObject tempalteJson= (JSONObject) JSONObject.parse(tempalateJsonStr);

        String returnJsonStr= mqServer.sendAndReceive("CNAIDUN", "",
                "DATAINTERCHANGE", "INTERSECTION0003DSF", tempalateJsonStr);
/*
        tempalteJson.put(MessageProperties.FUNCCODE,jsonObject.getString(MessageProperties.WORKTYPECODE ));
*/
        return JSONObject.parseObject(returnJsonStr);
    }
    /**
     *货车通行证换发办理-提交申请 json格式转换
     * @param jsonObject
     * @return
     */
    public JSONObject INTERSECTION0005(JSONObject jsonObject){

        String workTypeCode=jsonObject.getString(MessageProperties.WORKTYPECODE );

        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);

        MQServer mqServer=SpringContextUtils.getBean(MQServer.class);

        String tempalateJsonStr=baseInfoMapper.findTemplateByWorkTypeCode(workTypeCode);

        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key=entry.getKey();
            String value=(String) entry.getValue();
            if( !"uid".equals(key)){
                tempalateJsonStr = tempalateJsonStr.replace(key,value);
            }
        }
        JSONObject tempalteJson= (JSONObject) JSONObject.parse(tempalateJsonStr);

        String returnJsonStr= mqServer.sendAndReceive("CNAIDUN", "",
                "DATAINTERCHANGE", "ENTRYANDEXIT0005DSF", tempalateJsonStr);
/*
        tempalteJson.put(MessageProperties.FUNCCODE,jsonObject.getString(MessageProperties.WORKTYPECODE ));
*/
        return JSONObject.parseObject(returnJsonStr);
    }
}
