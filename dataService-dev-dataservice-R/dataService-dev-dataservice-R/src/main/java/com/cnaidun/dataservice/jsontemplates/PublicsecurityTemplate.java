package com.cnaidun.dataservice.jsontemplates;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.client.fileUploads.FileUploadsCient;
import com.cnaidun.dataservice.common.utils.DateConverUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.BaseInfoMapper;
import com.cnaidun.dataservice.utils.MessageProperties;
import com.cnaidun.messageclient.MQServer;

import java.util.Calendar;
import java.util.Map;

public class PublicsecurityTemplate {

    /**
     * 下载文件并转换成base64
     *
     * @param fileUploadsCient 文件下载客户端类
     * @param fileId           文件id
     * @return
     */
    private static Map<String, Object> downloadBase64(FileUploadsCient fileUploadsCient, String fileId) {
        try {
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
     * 批量下载转换为base64后将，并将原json中文件路径替换为base64
     *
     * @param fileUploadsCient 文件下载客户端类
     * @param imges            原json
     * @return
     */
    private static JSONArray batchDownloadBase64(FileUploadsCient fileUploadsCient, JSONArray imges) {
        JSONArray jsonArray = new JSONArray();
        try {
            int i = 0;
            for (Object obj : imges) {
                String fileId = obj.toString();
                Map<String, Object> map = downloadBase64(fileUploadsCient, fileId);
                obj = map.get("data");
                jsonArray.set(i, obj);
                i++;
            }
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 将第三方的文件标志，替换为原json文件下载后的base64
     *
     * @param json    原json
     * @param jsonStr 第三方json字符串
     * @return 替换后的第三方字符串
     */
    public static String replaceImages(JSONObject json, String jsonStr) {
        FileUploadsCient fileUploadsCient = SpringContextUtils.getBean(FileUploadsCient.class);
        JSONArray jsonArray = null;
        String value = null;
        for (Map.Entry<String, Object> map : json.entrySet()) {
            if (map.getValue() instanceof JSONArray) {
                jsonArray = (JSONArray) json.get(map.getKey());
                jsonArray = batchDownloadBase64(fileUploadsCient, jsonArray);
                jsonStr = jsonStr.replace("\"" + map.getKey() + "\"", jsonArray.toJSONString());
            } else {
                value = (String) map.getValue();
                String key=map.getKey();
                String replacement=downloadBase64(fileUploadsCient, value).get("data") + "";
                jsonStr = jsonStr.replace(key, replacement);
            }


        }
        return jsonStr;
    }

    /**
     * 重庆市保安员资格证考试报名办理-业务提交json格式转换
     *
     * @param jsonObject
     * @return
     */
    public static JSONObject PUBLICSECURITY0023(JSONObject jsonObject) {

        String workTypeCode = jsonObject.getString(MessageProperties.WORKTYPECODE);

        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);

        MQServer mqServer = SpringContextUtils.getBean(MQServer.class);

        String tempalateJsonStr = baseInfoMapper.findTemplateByWorkTypeCode(workTypeCode);
        tempalateJsonStr = tempalateJsonStr.replace("systime", DateConverUtil.getSbyDT(Calendar.getInstance().getTime(), "yyyyMMdd"));
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
                    if (obj.toString().lastIndexOf("/") != -1) {
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

        String returnJsonStr = mqServer.sendAndReceive("CNAIDUN", "",
                "DATAINTERCHANGE", workTypeCode + "DSF", tempalateJsonStr);
        return JSON.parseObject(returnJsonStr);
    }

    /**
     * 重庆市自行招用保安员单位-业务提交json格式转换
     *
     * @param jsonObject
     * @return
     */
    public static JSONObject PUBLICSECURITY0024(JSONObject jsonObject) {
        return PUBLICSECURITY0023(jsonObject);
    }

    /**
     * 重庆市跨区域提供保安服务备案-业务提交json格式转换
     *
     * @param jsonObject
     * @return
     */
    public static JSONObject PUBLICSECURITY0025(JSONObject jsonObject) {
        return PUBLICSECURITY0023(jsonObject);
    }

}
