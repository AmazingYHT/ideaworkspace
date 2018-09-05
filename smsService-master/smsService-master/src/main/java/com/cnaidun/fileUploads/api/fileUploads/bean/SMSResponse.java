package com.cnaidun.fileUploads.api.fileUploads.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class SMSResponse {
    private String code;
    private String message;
    private JSONObject Data;
}
