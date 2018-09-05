package com.cnaidun.dataservice.utils;

import com.alibaba.fastjson.JSONObject;

public class ReturnMessageUtils {

    public static String code = "code";
    public static String msg = "msg";
    public static String data ="data";

    public static String SUCCESS_200 = "200";
    public static String SUCCESS_200_MSG = "success";

    public static String SUCCESS_201 = "201";
    public static String SUCCESS_201_MSG = "无数据";

    public static String ERROR_1102 = "1102";
    public static String ERROR_1102_MSG = "系统错误";

    public static String ERROR_1104 = "1104";
    public static String ERROR_1104_MSG = "请求超时";

    public static String ERROR_40035 = "40035";
    public static String ERROR_40035_MSG = "不合法的参数";

    /**
     * 返回信息封装
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static JSONObject returnMsgObject(String code,String msg,Object data){
        JSONObject returnObject = new JSONObject();
        returnObject.put(ReturnMessageUtils.code,code);
        returnObject.put(ReturnMessageUtils.msg,msg);
        returnObject.put(ReturnMessageUtils.data,data);
        return returnObject;
    }

    public static JSONObject returnError1102Object(Object data){
        JSONObject returnObject = new JSONObject();
        returnObject.put(ReturnMessageUtils.code,ReturnMessageUtils.ERROR_1102);
        returnObject.put(ReturnMessageUtils.msg,ReturnMessageUtils.ERROR_1102_MSG);
        returnObject.put(ReturnMessageUtils.data,data);
        return returnObject;
    }

    /**
     * 不合法的参数
     * @param data
     * @return
     */
    public static JSONObject returnError40035Object(Object data){
        JSONObject returnObject = new JSONObject();
        returnObject.put(ReturnMessageUtils.code,ReturnMessageUtils.ERROR_40035);
        returnObject.put(ReturnMessageUtils.msg,ReturnMessageUtils.ERROR_40035_MSG);
        returnObject.put(ReturnMessageUtils.data,data);
        return returnObject;
    }

    /**
     * 返回正确信息
     * @return
     */
    public static JSONObject returnSuccessObject(Object data){
        JSONObject returnObject = new JSONObject();
        returnObject.put(ReturnMessageUtils.code,ReturnMessageUtils.SUCCESS_200);
        returnObject.put(ReturnMessageUtils.msg,ReturnMessageUtils.SUCCESS_200_MSG);
        returnObject.put(ReturnMessageUtils.data,data);
        return returnObject;
    }

}
