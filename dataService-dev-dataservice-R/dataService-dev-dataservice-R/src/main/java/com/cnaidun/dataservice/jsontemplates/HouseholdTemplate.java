package com.cnaidun.dataservice.jsontemplates;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.cnaidun.dataservice.client.fileUploads.FileUploadsCient;
import com.cnaidun.dataservice.common.utils.DateConverUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.BaseInfoMapper;
import com.cnaidun.dataservice.utils.MessageProperties;
import com.cnaidun.dataservice.utils.ZipUtil;
import com.cnaidun.messageclient.MQServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class HouseholdTemplate {
    public static final Logger log = LogManager.getLogger(HouseholdTemplate.class);
    /**
     * 下载文件并转换成base64
     * @param fileUploadsCient 文件下载客户端类
     * @param fileId 文件id
     * @return
     */
    private static Map<String, Object> downloadBase64(FileUploadsCient fileUploadsCient, String fileId) {
        log.info("下载文件并转换成base64开始！");
        try {
            Map<String, Object> map = fileUploadsCient.fileDownloadBase64(fileId);
            if (!map.get("code").equals(200)) {
                log.error("下载失败");
                throw new RuntimeException("文件下载失败!");
            }
            log.info("下载文件并转换成base64成功！");
            return map;
        } catch (Exception e) {
            log.info("下载文件并转换成base64失败！");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 批量下载转换为base64后将，并将原json中文件路径替换为base64
     * @param fileUploadsCient 文件下载客户端类
     * @param imges 原json
     * @return
     */
    private static JSONArray batchDownloadBase64(FileUploadsCient fileUploadsCient, JSONArray imges)  {
        JSONArray jsonArray = new JSONArray();
        try{
            int i=0;
            for (Object obj : imges) {
                String fileId = obj.toString();
                Map<String, Object> map = downloadBase64(fileUploadsCient, fileId);
                obj = ZipUtil.CompressToBase64((String) map.get("data"));
                jsonArray.set(i,obj);
                i++;
            }
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e.getMessage());
        }
    }
    /**
     *将第三方的文件标志，替换为原json文件下载后的base64
     * @param json 原json
     * @param jsonStr 第三方json字符串
     * @return 替换后的第三方字符串
     */
    public static String replaceImages(JSONObject json, String jsonStr) {
        log.info("开始替换图片！");
        FileUploadsCient fileUploadsCient = SpringContextUtils.getBean(FileUploadsCient.class);
        JSONArray jsonArray = null;
        String value = null;
        for(Map.Entry<String,Object> map : json.entrySet()){
            if(map.getValue() instanceof  JSONArray){
                if(jsonArray.isEmpty()){
                    continue;
                }
                String field =  (String) (((JSONArray)map.getValue() ).get(0));
                if(field==null){
                    jsonStr = jsonStr.replace(map.getKey(),null);
                    continue;
                }
                Map<String, Object> map1 = downloadBase64(fileUploadsCient,field);
               // String value1 = ZipUtil.CompressToBase64((String) map1.get("data"));
                jsonStr = jsonStr.replace(map.getKey(),(String)map1.get("data"));
            }else{
                value= (String) map.getValue();
                value = (String) downloadBase64(fileUploadsCient,value).get("data");
                value = ZipUtil.CompressToBase64(value) ;
                jsonStr = jsonStr.replace(map.getKey(),value);
            }
            log.info("替换图片结束！");

        }
//        if (jsonArray != null) {
//            jsonArray = batchDownloadBase64(fileUploadsCient,jsonArray);
//            jsonStr = jsonStr.replace("\"" + "图像类型名称-出生医学证明" + "\"", jsonArray.toJSONString());
//        }
//        jsonArray = (JSONArray) json.get("parentPermanentBooks");
//        if (jsonArray != null) {
//            jsonArray = batchDownloadBase64(fileUploadsCient,jsonArray);
//            jsonStr = jsonStr.replace("\"" + "图像类型名称-父母亲户口簿" + "\"", jsonArray.toJSONString());
//        }
//        jsonArray = (JSONArray) json.get("parentPermanentBooks" + "\"");
//        if (jsonArray != null) {
//            jsonArray = batchDownloadBase64(fileUploadsCient,jsonArray);
//            jsonStr = jsonStr.replace("\"" + "图像类型名称-父母亲身份证正反面", jsonArray.toJSONString());
//        }
//        jsonArray = (JSONArray) json.get("fosterProve" + "\"");
//        if (jsonArray != null) {
//            jsonArray = batchDownloadBase64(fileUploadsCient,jsonArray);
//            jsonStr = jsonStr.replace("\"" + "图像类型名称-证明材料", jsonArray.toJSONString());
//        }
//        jsonArray = (JSONArray) json.get("nameFamilyComposition");
//        if (jsonArray != null){
//            jsonArray = batchDownloadBase64(fileUploadsCient,jsonArray);
//            jsonStr = jsonStr.replace("\"" + "图像类型名称-民族成份填报申请书" + "\"", jsonArray.toJSONString());
//        }
//        jsonArray = (JSONArray) json.get("signature");
//        if(jsonArray!=null){
//            jsonArray = batchDownloadBase64(fileUploadsCient,jsonArray);
//            jsonStr = jsonStr.replace("\"" +"图像类型名称-电子签名"+"\"" ,jsonArray.toJSONString());
//        }
        return jsonStr;
    }

    /**
     *
     * @param jsonObject 前端传入的json字符串
     * @return 生成的模板字符串
     */
    private static String commonMethod(String workTypeCode,JSONObject jsonObject) {
        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);
        String tempalateJsonStr = baseInfoMapper.findTemplateByWorkTypeCode(workTypeCode);
        tempalateJsonStr = tempalateJsonStr.replace("sysdate",DateConverUtil.getSbyDT(Calendar.getInstance().getTime(),"yyyyMMdd"));
        tempalateJsonStr = tempalateJsonStr.replace("officeNumber","an"+getNBitRandomDigit(24));
        tempalateJsonStr = tempalateJsonStr.replace("pscode",jsonObject.get("pscode")+"");
        tempalateJsonStr = tempalateJsonStr.replace("messageuuid",jsonObject.get("workno")+"");
        JSONObject images = new JSONObject();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            boolean isImage = false;
            if (value.toString().indexOf("/") != -1) {
                isImage = true;
                images.put(key, value);
                continue;
            }
            if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) entry.getValue();
                for (Object obj : jsonArray) {
                    if (obj !=null && obj.toString().lastIndexOf("/") != -1) {
                        isImage = true;
                    }
                }
                if (isImage) {
                    images.put(key, jsonArray);
                } else {
                    tempalateJsonStr = tempalateJsonStr.replace("\"" + key + "\"", jsonArray.toJSONString());
                }
                continue;
            }
            value = entry.getValue();
            if (!"uid".equals(key)) {
                tempalateJsonStr = tempalateJsonStr.replace(key, value + "");
            }
        }
        JSONObject tempalteJson = null;
        tempalateJsonStr = replaceImages(images, tempalateJsonStr);
        try {
            tempalteJson = (JSONObject) JSONObject.parse(tempalateJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String returnJsonStr= mqServer.sendAndReceive("CNAIDUN", "",
//                "DATAINTERCHANGE", workTypeCode+"DSF", tempalateJsonStr);
////        String returnJsonStr= mqServer.sendAndReceive("CNAIDUN", "",
////                "DATAINTERCHANGE", workTypeCode+"DSF", "{}");
       return tempalateJsonStr;
    }
    private static JSONObject sendInfo(String workTypeCode,String message){
        MQServer mqServer = SpringContextUtils.getBean(MQServer.class);
        String returnJsonStr= mqServer.sendAndReceive("CNAIDUN", "",
                "DATAINTERCHANGE", workTypeCode+"DSF", message);
        log.info("第三方返回&&&&&&:"+returnJsonStr);
        return JSONObject.parseObject(returnJsonStr);
    }
    /**
     * 户口申报-新生儿户口申报-业务办理
     *
     * @param jsonObject
     * @return 第三方json
     */
    public static JSONObject HOUSEHOLD0001(JSONObject jsonObject) {
        String workTypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);
        String message = commonMethod(workTypeCode,jsonObject);
        JSONObject templateJson = JSONObject.parseObject(message);
        JSONArray babySexs = templateJson.getJSONObject("args").getJSONObject("jcxx").getJSONArray("babySex");
        JSONArray babyBirthCertificateNos = templateJson.getJSONObject("args").getJSONObject("jcxx").getJSONArray("babyBirthCertificateNo");
        JSONArray babyBirthdays = templateJson.getJSONObject("args").getJSONObject("jcxx").getJSONArray("babyBirthday");
        int i=0;
        JSONObject resultJson = null;
        for(Object babySex : babySexs){
            JSONObject tempObject = (JSONObject)templateJson.clone();
            tempObject.getJSONObject("args").getJSONObject("jcxx").put("BJBH","an"+getNBitRandomDigit(24));
            tempObject.put("babySex",babySex);
            tempObject.put("babyBirthCertificateNo",babyBirthCertificateNos.get(i));
            tempObject.put("babyBirthday",babyBirthdays.get(i));
            message = tempObject.toJSONString();
            i++;
            resultJson =  sendInfo(workTypeCode,message);
            if(!resultJson.getString("code").equals("200")){
                return resultJson;
            }
        }
        return resultJson;
    }
    private static String getNBitRandomDigit(long n){
         java.util.Random random = new java.util.Random();
        //  存储中间生成的数串
        StringBuffer randomDigitStr = new StringBuffer();
        //  生成的随机数
        long randomDigit;

        if(n < 18){
            //  生成 n位随机数（-0.5避免生成的前 n均为 9最终四舍五入时（可能）超出一位数字）
            randomDigit = Math.round((random.nextDouble() * Math.pow(10, n)) - 0.5);
            randomDigitStr.append(randomDigit);
        } else {
            //  n超过 18，则通过生成多个 18位随机数连接成更长串
            for(int i = 0; i < Math.floor(n / 18); ++i){
                //  生成 18位随机数（long最长为 19位且非所有位均为 9，所以取 18位）
                randomDigit =
                        Math.round((random.nextDouble() * Math.pow(10, 18)) - 0.5);
                randomDigitStr.append(randomDigit);
            }
        }
        //  生成的随机数串位数
        int randomDigitStrLength = randomDigitStr.length();

        //  生成的随机数串位不足但是也不达 18位时，循环随机插入随机生成的[0, 9]间任意一位数
        if(randomDigitStrLength < n){
            for(int i = 0; i < n - randomDigitStrLength; ++i){
                //  生成可插入的下标位置（若位置为 randomDigitStr.length()，则是在末尾添加）
                int offset = random.nextInt(randomDigitStr.length() + 1);
                if(offset < randomDigitStr.length()){
                    //  在 offset位置（下标）插入随机生成的[0, 9]间一位数
                    randomDigitStr.insert(offset, random.nextInt(10));
                } else {
                    //  在数串末尾添加[0, 9]间一位数
                    randomDigitStr.append(random.nextInt(10));
                }
            }
        }
        return randomDigitStr.toString();
    }
    /**
     * 户口注销-死亡注销-业务办理
     *
     * @param jsonObject
     * @return 第三方json
     */
    public static JSONObject HOUSEHOLD0002(JSONObject jsonObject) {
        String workTypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);
        String message = commonMethod(workTypeCode,jsonObject);
        log.info("生成json开始");
        log.info(message);
        log.info("生成json结束");
        return sendInfo(workTypeCode,message);
    }
    /**
     * 户口迁入-夫妻投靠-业务办理
     *
     * @param jsonObject
     * @return 第三方json
     */
    public static JSONObject HOUSEHOLD0003(JSONObject jsonObject) {
        String workTypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);
        String message = commonMethod(workTypeCode,jsonObject);
        JSONObject templateJson = JSONObject.parseObject(message);
        JSONArray relyNames = templateJson.getJSONObject("args").getJSONObject("jcxx").getJSONArray("relyName");
        JSONArray relyIdnos = templateJson.getJSONObject("args").getJSONObject("jcxx").getJSONArray("relyIdno");
        JSONArray relyMobiles = templateJson.getJSONObject("args").getJSONObject("jcxx").getJSONArray("relyMobile");
        JSONArray relyAndApplicantRelationships = templateJson.getJSONObject("args").getJSONObject("jcxx").getJSONArray("relyAndApplicantRelationship");
        int i=0;
        JSONObject resultJson = null;
        for(Object realName : relyNames){
            JSONObject tempObject = (JSONObject)templateJson.clone();
            tempObject.getJSONObject("args").getJSONObject("jcxx").put("BJBH","an"+getNBitRandomDigit(24));
            tempObject.put("relyName",realName);
            tempObject.put("relyIdno",relyIdnos.get(i));
            tempObject.put("relyMobile",relyMobiles.get(i));
            tempObject.put("relyAndApplicantRelationship",relyAndApplicantRelationships.get(i));
            message = tempObject.toJSONString();
            log.info("生成json开始");
            log.info(message);
            log.info("生成json结束");
            i++;
            resultJson =  sendInfo(workTypeCode,message);
            if(!resultJson.getString("code").equals("200")){
                return resultJson;
            }
        }
        return resultJson;
    }
    /**
     * 户口迁入-购买商品房落户-业务办理
     *
     * @param jsonObject
     * @return 第三方json
     */
    public static JSONObject HOUSEHOLD0004(JSONObject jsonObject) {
        String workTypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);
        String message = commonMethod(workTypeCode,jsonObject);
        return sendInfo(workTypeCode,message);
    }
    /**
     * 户口迁入-未成年子女投靠父母-业务办理
     *
     * @param jsonObject
     * @return 第三方json
     */
    public static JSONObject HOUSEHOLD0005(JSONObject jsonObject) {
        String workTypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);
        String message = commonMethod(workTypeCode,jsonObject);
        return sendInfo(workTypeCode,message);
    }

    /**
     * 户口申报-新生儿户口申报-业务办理
     *
     * @param jsonObject
     * @return 第三方json
     */
    public static JSONObject HOUSEHOLD0006(JSONObject jsonObject) {
        String workTypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);
        String message = commonMethod(workTypeCode,jsonObject);
        return sendInfo(workTypeCode,message);
    }
    /**
     * 出租房屋登记-业务办理
     *
     * @param jsonObject
     * @return 第三方json
     */
    public static JSONObject HOUSEHOLD0007(JSONObject jsonObject) {
        String workTypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);
        String message = commonMethod(workTypeCode,jsonObject);
        return sendInfo(workTypeCode,message);
    }
    /**
     * 流动人口居住登记-业务办理
     *
     * @param jsonObject
     * @return 第三方json
     */
    public static JSONObject HOUSEHOLD0008(JSONObject jsonObject) {
        String workTypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);
        String message = commonMethod(workTypeCode,jsonObject);
        return sendInfo(workTypeCode,message);
    }
}
