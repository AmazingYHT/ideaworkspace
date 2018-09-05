package com.cnaidun.dataservice.jsontemplates;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.client.fileUploads.FileUploadsCient;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.BaseInfoMapper;
import com.cnaidun.dataservice.utils.MessageProperties;
import com.cnaidun.dataservice.utils.SpringBeanUtils;
import com.cnaidun.messageclient.MQServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 *
 * 出入境json格式模板转换类
 */
@Slf4j
public class EntryandexitTemplate {
    /**
     *重庆市户籍居民 出国（境）证件 办理-业务提交json格式转换
     * @param jsonObject
     * @return
     */
    public JSONObject ENTRYANDEXIT0001(JSONObject jsonObject) {
        String workTypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);

        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);

        MQServer mqServer = SpringContextUtils.getBean(MQServer.class);

        String tempalateJsonStr = baseInfoMapper.findTemplateByWorkTypeCode(workTypeCode);
        String passPortFlag = null;//普通护照
        String wlgatxzFlag = null;//港澳台通行证
        String wltwtxzFlag = null;//台湾通行证

        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            if (!"uid".equals(key)) {
                if (!StringUtils.isEmpty(key) && "passPortValue".equals(key)) {
                    passPortFlag = "contains";
                } else if (!StringUtils.isEmpty(key) && "registrationCategoryHM".equals(key)) {
                    wlgatxzFlag = "contains";
                } else if (!StringUtils.isEmpty(key) && "registrationCategoryValueTW".equals(key)) {
                    wltwtxzFlag = "contains";
                } else {
                    tempalateJsonStr = tempalateJsonStr.replace(key, value);
                }
            }
        }
        JSONObject tempalteJson = (JSONObject) JSONObject.parse(tempalateJsonStr);
        if ("contains".equals(passPortFlag)) {//是否勾选护照
            JSONObject pthz = new JSONObject();
            tempalteJson.getJSONObject("args").put("pthz", pthz);
        }
        if ("contains".equals(wlgatxzFlag)) {//是否勾选港澳通行证
            JSONObject wlgatxz = new JSONObject();
            wlgatxz.put("qwd", jsonObject.getString("destinationHM"));
            wlgatxz.put("xgqzzl", jsonObject.getString("typeOfEndorsemenHM"));
            wlgatxz.put("xgqzcs", jsonObject.getString("hongkongEndorsement"));
            wlgatxz.put("amqzzl", jsonObject.getString("typeOfEndorsemenHM"));
            wlgatxz.put("amqzcs", jsonObject.getString("macauEndorsement"));
            tempalteJson.getJSONObject("args").put("wlgatxz", wlgatxz);
        }
        if ("contains".equals(wltwtxzFlag)) {//是否勾选台湾通行证
            JSONObject wltwtxz = new JSONObject();
            wltwtxz.put("qzzl", jsonObject.getString("typeOfEndorsementTW"));
            wltwtxz.put("qzcs", jsonObject.getString("typeOfEndorsementTWTimes"));
            tempalteJson.getJSONObject("args").put("wltwtxz", wltwtxz);
        }
        String returnJsonStr = mqServer.sendAndReceive("CNAIDUN", "",
                "DATAINTERCHANGE", "ENTRYANDEXIT0001DSF", tempalteJson.toJSONString());
        log.info("第三方接口请求参数####"+tempalteJson.toJSONString());
        log.info("第三方返回&&&&&&:" + returnJsonStr);
        /*     tempalteJson.put(MessageProperties.FUNCCODE,jsonObject.getString(MessageProperties.WORKTYPECODE ));*/
        return JSONObject.parseObject(returnJsonStr);
    }


    /**
     *境外人员临住登记-业务提交json格式转换
     * @param jsonObject
     * @return
     */
    public JSONObject ENTRYANDEXIT0002(JSONObject jsonObject){

        String workTypeCode=jsonObject.getString(MessageProperties.WORKTYPECODE );

        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);

        MQServer mqServer=SpringContextUtils.getBean(MQServer.class);

        String tempalateJsonStr=baseInfoMapper.findTemplateByWorkTypeCode(workTypeCode);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
        String passportInformationPageFile="";
        String visaPage="";
        String immigrationClearancePage="";
        //图片类型（1、人员照片2、护照资料页3、签证页4、入境验讫章页5、其他照片
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {//遍历出所有图片
            String key=entry.getKey();
            String value=(String) entry.getValue();
            if( "passportInformationPage".equals(key)){ //护照资料页url
                if(!StringUtils.isEmpty(value)){
                    passportInformationPageFile=(String)downloadBase64(value).get("data");
                }
            }
            if("visaPage".equals(key)){//签证页url
                if(!StringUtils.isEmpty(value)){
                    visaPage=(String)downloadBase64(value).get("data");
                }
            }
            if("immigrationClearancePage".equals(key)){//入境检讫章页url
                if(!StringUtils.isEmpty(value)){
                    immigrationClearancePage=(String)downloadBase64(value).get("data");
                }
            }
        }
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key=entry.getKey();
            String value=(String) entry.getValue();
            if( !"uid".equals(key)){
                tempalateJsonStr = tempalateJsonStr.replace(key,value);
            }
        }
        tempalateJsonStr=tempalateJsonStr.replace("systime",sdf.format(new Date()));
        tempalateJsonStr=tempalateJsonStr.replace("ucodes",(sdf2.format(new Date())+ramdomNum()));
        JSONObject tempalteJson= (JSONObject) JSONObject.parse(tempalateJsonStr);
        JSONArray  tpxx=new JSONArray();
        JSONObject temp1=new JSONObject();
        JSONObject temp2=new JSONObject();
        JSONObject temp3=new JSONObject();
        temp1.put("tpmc","护照资料页");
        temp1.put("tp","passportInformationPageFile");
        temp1.put("tplx","2");

        temp2.put("tpmc","签证页");
        temp2.put("tp","visaPage");
        temp2.put("tplx","3");

        temp3.put("tpmc","入境检讫章页");
        temp3.put("tp","immigrationClearancePage");
        temp3.put("tplx","4");
        tpxx.add(temp1);
        tpxx.add(temp2);
        tpxx.add(temp3);
        tempalteJson.getJSONObject("args").put("tpxx",tpxx);
     /*   tempalteJson.replace("systime",sdf.format(new Date()));
        tempalteJson.replace("lzryywbh",sdf2.format(new Date())+ramdomNum());*/
        String returnJsonStr= mqServer.sendAndReceive("CNAIDUN", "",
                "DATAINTERCHANGE", "ENTRYANDEXIT0002DSF", tempalteJson.toJSONString());
        log.info("调用第三方接口请求参数###"+tempalteJson.toJSONString());
        log.info("调用第三方接口返回参数###"+returnJsonStr);
        return JSONObject.parseObject(returnJsonStr);
    }
    /**
     * 获取4位随机数
     * @return
     */
    public static int ramdomNum(){
        Random ran=new Random();
        int r=0;
        m1:while(true){
            int n=ran.nextInt(10000);
            r=n;
            int[] bs=new int[4];
            for(int i=0;i<bs.length;i++){
                bs[i]=n%10;
                n/=10;
            }
            Arrays.sort(bs);
            for(int i=1;i<bs.length;i++){
                if(bs[i-1]==bs[i]){
                    continue m1;
                }
            }
            break;
        }

        return r;
    }
    /**
     * 下载文件并转换成base64
     * @param fileId 文件id
     * @return
     */
    private static Map<String, Object> downloadBase64( String fileId) {
        try {
            FileUploadsCient fileUploadsCient=SpringBeanUtils.getBeanByClass(FileUploadsCient.class);
            Map<String, Object> map = fileUploadsCient.fileDownloadBase64(fileId);
            if (!map.get("code").equals(200)) {
                throw new RuntimeException("文件下载失败!");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *台湾人json格式转换
     * @param jsonObject
     * @return
     */
    public JSONObject ENTRYANDEXIT0003(JSONObject jsonObject){

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
                "DATAINTERCHANGE", "ENTRYANDEXIT0003DSF", tempalateJsonStr);

        log.info("调用第三方接口请求参数"+tempalateJsonStr);
        log.info("调用第三方接口返回参数"+returnJsonStr);
        return JSONObject.parseObject(returnJsonStr);
    }
    /**
     *外国人申请签证证件json格式转换
     * @param jsonObject
     * @return
     */
    public JSONObject ENTRYANDEXIT0004(JSONObject jsonObject){

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
                "DATAINTERCHANGE", "ENTRYANDEXIT0004DSF", tempalateJsonStr);
        log.info("调用第三方接口请求参数"+tempalateJsonStr);
        log.info("调用第三方接口返回参数"+returnJsonStr);
        return JSONObject.parseObject(returnJsonStr);
    }
    /* *//**
     *外国人团队旅游签证  json格式转换
     * @param jsonObject
     * @return
     *//*
    public JSONObject ENTRYANDEXIT0005(JSONObject jsonObject){

        String workTypeCode=jsonObject.getString(MessageProperties.WORKTYPECODE );

        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);

        MQServer mqServer=SpringContextUtils.getBean(MQServer.class);

        String tempalateJsonStr=baseInfoMapper.findTemplateByWorkTypeCode(workTypeCode);

        //取出所有数组数据
        JSONArray  groupArray=new JSONArray();//存放全部组员信息数组

        JSONArray  surnameArray=new JSONArray();//英文姓数组
        JSONArray  givenNameArray=new JSONArray();//英文名数组
        JSONArray  nameInChineseArray=new JSONArray();//中文姓名数组
        JSONArray   sexArray=new JSONArray();//性别	数组
        JSONArray  dateOfBirthArray=new JSONArray();//出生日期数组
        JSONArray  countryArray=new JSONArray();//国家地区数组
        JSONArray passportNoArray=new JSONArray();//护照号码数组
        JSONArray occupationArray=new JSONArray();//职业数组
        JSONArray dateOfFillingArray=new JSONArray();//填表日期数组
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key=entry.getKey();
            if("surname".equals(key)){//英文姓
                surnameArray=(JSONArray) entry.getValue();
            }
            if("givenName".equals(key)){//英文名
                givenNameArray=(JSONArray) entry.getValue();
            }
            if("nameInChinese".equals(key)){//中文姓名
                nameInChineseArray=(JSONArray) entry.getValue();
            }
            if("sex".equals(key)){//性别
                sexArray=(JSONArray) entry.getValue();
            }
            if("dateOfBirth".equals(key)){//出生日期
                dateOfBirthArray=(JSONArray) entry.getValue();
            }
            if("country".equals(key)){//国家地区
                countryArray=(JSONArray) entry.getValue();
            }
            if("passportNo".equals(key)){//护照号码
                passportNoArray=(JSONArray) entry.getValue();
            }
            if("occupation".equals(key)){//职业
                occupationArray=(JSONArray) entry.getValue();
            }
            if("dateOfFilling".equals(key)){//填表日期
                dateOfFillingArray=(JSONArray) entry.getValue();
            }
        }
        for (int i = 0; i < surnameArray.size(); i++) {
           String surName = surnameArray.getString(i);
           String givenName=givenNameArray.getString(i);
           String nameInChinese=nameInChineseArray.getString(i);
           String  sex=sexArray.getString(i);
           String dateOfBirth=dateOfBirthArray.getString(i);
           String country=dateOfBirthArray.getString(i);
           String passportNo=passportNoArray.getString(i);
           String occupation=occupationArray.getString(i);
           String dateOfFilling=dateOfFillingArray.getString(i);
           JSONObject jsonObj=new JSONObject();
           jsonObj.put("tyxh",i+1);
           jsonObj.put("ywx",surName);
           jsonObj.put("ywm",givenName);
           jsonObj.put("zwxm",nameInChinese);
           jsonObj.put("xb",sex);
           jsonObj.put("csrq",dateOfBirth);
           jsonObj.put("gjdq",country);
           jsonObj.put("hzhm",passportNo);
           jsonObj.put("zy",occupation);
           jsonObj.put("tbdwbh",dateOfFilling);
            groupArray.add(jsonObj);
        }
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key=entry.getKey();
            if( !"uid".equals(key)){
                if(entry.getKey().getClass().equals(String.class)){
                    String value=(String)entry.getValue();
                    tempalateJsonStr = tempalateJsonStr.replace(key,value);
                }

            }
        }
        JSONObject tempalteJson= (JSONObject) JSONObject.parse(tempalateJsonStr);
        tempalteJson.getJSONObject("args").getJSONObject("info").put("wgrttqztyxx",groupArray);

        String returnJsonStr= mqServer.sendAndReceive("CNAIDUN", "",
                "DATAINTERCHANGE", "ENTRYANDEXIT0005DSF", tempalteJson.toJSONString());
        return JSONObject.parseObject(returnJsonStr);
    }
    *//**
     *外国人个人签证  json格式转换
     * @param jsonObject
     * @return
     *//*
    public JSONObject ENTRYANDEXIT0006(JSONObject jsonObject){

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
                "DATAINTERCHANGE", "ENTRYANDEXIT0006DSF", tempalateJsonStr);
        return JSONObject.parseObject(returnJsonStr);
    }*/
}
