package com.cnaidun.fileUploads.api.fileUploads.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SmsBean implements Serializable {
    private String code;
    private String mobile;
    private String uid;
    private String formid;
    private String wid;
    private  String idno;   //身份证号码
    private String startday;//身份证开始时间
    private String endday;    //身份证失效时间
    private  String wsend;
    private Date sendday;   //发送消息的时间
    private int dsend;  //短信发送是否成功 0代表未发送 1代表发送成功 2发送失败

    public SmsBean(String code, String phone, String uid, String formid) {
        this.code = code;
        this.mobile = phone;
        this.uid = uid;
        this.formid = formid;
    }

    public SmsBean() {
    }
}
