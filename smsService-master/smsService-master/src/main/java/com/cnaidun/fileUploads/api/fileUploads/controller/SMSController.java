package com.cnaidun.fileUploads.api.fileUploads.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.fileUploads.api.fileUploads.bean.SMSResponse;
import com.cnaidun.fileUploads.api.fileUploads.bean.SmsBean;
import com.cnaidun.fileUploads.api.fileUploads.utils.CodeUtils;
import com.cnaidun.fileUploads.api.fileUploads.utils.SMSService;
import com.cnaidun.messageclient.MQServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sms")
@Slf4j
public class SMSController {

    @Autowired
    private MQServer mqServer;

//    @Autowired
//    private SmsServiceImpl smsServiceimpl;

    /**
     * @param
     * @param
     * @return
     */
    @RequestMapping("/sendMesSing")
    @ResponseBody
    public SMSResponse sendMesSing(@RequestHeader(value="uid")  String uid, @RequestBody SmsBean smsBean) {

        SMSResponse smsResponse=new SMSResponse();
        String mobile=smsBean.getMobile();
        String formid=smsBean.getFormid();
        log.info("uid:{},mobile:{},formid:{}",uid,mobile,formid);
        try {
            String code = CodeUtils.getSix();
            String[] params = {code, "5"};
            log.info("start call smsService");
            String result = SMSService.sendMeModel(mobile, params);
            log.info("end call smsService,result:",result);
            if ("OK".equals(result)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("workTypeCode","SMS0001");
                jsonObject.put("phone",mobile);
                jsonObject.put("msgCode",code);
                jsonObject.put("uid",uid);

                log.info("params:{}",jsonObject.toJSONString());

                String receive = mqServer.sendAndReceive("CNAIDUN", "BACKBISINESSGROUP",
                        "SMS", "SMS0001",
                        jsonObject.toJSONString());
                log.info("mq returnMsg:{}",receive);
                if(receive != null){
                    JSONObject receiveObj = JSON.parseObject(receive);
                    String objString = receiveObj.getString("code");
                    if("200".equals(objString)){
                        smsResponse.setCode("200");
                        smsResponse.setMessage("请求成功");
                    }else{
                        smsResponse.setCode(objString);
                        smsResponse.setMessage(receiveObj.getString("msg"));
                    }

                }else{
                    smsResponse.setCode("500");
                    smsResponse.setMessage("请求超时");
                    smsResponse.setData(new JSONObject());
                }
                return smsResponse;
            } else {
                smsResponse.setCode(result);
                smsResponse.setMessage("请求失败");
                return smsResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("e:{}",e);
            smsResponse.setCode("500");
            smsResponse.setMessage(e.getMessage());
        }


        return smsResponse;
    }

    /**
     * @param phone 手机号
     * @param param 第一个参数领取日期，第二个参数到期日期
     * @return
     */
    @RequestMapping("/sendMesGroup")
    public Map sendMesGroup(String phone, String param) {
        log.info("进入短信群发");
        Map map = new HashMap();
        if(StringUtils.isBlank(phone)|| StringUtils.isBlank(param)){
            map.put("code", "请确认参数是否正确");
           return map;
        }
        String[] phones = phone.split(",");
        String[] params = param.split(",");
        try {
            String result = SMSService.sendMesModel(phones, params);

            if (result.equals("OK")) {
                //insertRecord(phone,params,1);
                map.put("code", "200");
                map.put("msg", "请求成功");
                log.info("请求成功");
            } else {
                //insertRecord(phone,params,2);
                map.put("errCode", result);
                map.put("msg", "请求失败");
                log.info(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public Map checkSmsCode(String code, String mobile) {
        Map map = new HashMap();
        if (StringUtils.isBlank(code) || StringUtils.isBlank(mobile)) {
            map.put("msg", "请确认请求参数");
        }
        return map;
    }



    /**
     * 发送验证码
     * @param mobile 手机号
     * @param code 验证码
     * @return
     */
    @RequestMapping("/sendMesSingCode")
    public Map sendMesSingCode(String mobile, String uid,String code) {
        Map map = new HashMap();
        if(mobile==null){
            map.put("msg", "请确认手机号是否正确");
        }
        try {
            //String code = CodeUtils.getSix();
            String[] params = {code, "5"};
            String result = SMSService.sendMeModel(mobile, params);
            if (result.equals("OK")) {
                //redis先注释
                // smsService.set(CodeUtils.hashCode(code, mobile, uid, formid)+"",new SmsBean(code, mobile, uid, formid));
                map.put("code", "200");
                map.put("msg", "请求成功");
            } else {
                map.put("code", result);
                map.put("msg", "请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

//    @RequestMapping("/insertRecord")
//    private void insertRecord(String phones,String[] params,int dsend){
//        SmsBean smsBean = new SmsBean();
//        smsBean.setWid(UUID.randomUUID().toString());
//        smsBean.setMobile(phones);
//        smsBean.setEndday(params[1]);
//        smsBean.setDsend(dsend);
//        smsBean.setStartday(params[0]);
//        smsBean.setIdno("1432");
//        smsBean.setWsend("1");
//        smsBean.setSendday(new Date());
//        smsServiceimpl.insertRecord(smsBean);
//    }
}
