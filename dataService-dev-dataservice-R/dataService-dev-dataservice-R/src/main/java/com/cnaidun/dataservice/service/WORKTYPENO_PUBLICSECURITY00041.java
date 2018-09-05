package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.PretrialServer;
import com.cnaidun.dataservice.dboperation.mysql.dao.AdUnitConverMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.AdUnitMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.IdInfoMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.AdUnit;
import com.cnaidun.dataservice.dboperation.mysql.model.AdUnitConver;
import com.cnaidun.dataservice.dboperation.mysql.model.EntertainmentRecord;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;

import java.util.List;


/**
 * 娱乐场所预登记审批
 */
public class WORKTYPENO_PUBLICSECURITY00041 implements DoMessage {

    private String workNo = "workNo";

    private String workTypeCode = "workTypeCode";

    private String unitCode = "unitCode";

    private String parentCode = "parentCode";

    private String type = "type";

    private String status = "status";

    private String uid = "uid";

    private String chargeName = "chargeName";

    private String mobile = "mobile";

    private String handleOffice = "handleOffice";

    private String serviceMode = "serviceMode";

    private String serviceAdress = "serviceAdress";

    private String doorheadName = "doorheadName";

    private String unitName = "unitName";

    private String operatingAddress = "operatingAddress";

    private String unitType = "unitType";

    private String creditCode = "creditCode";

    private String corporationName = "corporationName";

    private String corporationMobile = "corporationMobile";

    private String corporationId = "corporationId";

    private String operatorName = "operatorName";

    private String operatorMobile = "operatorMobile";

    private String operatorIdno = "operatorIdno";

    private String registrationForm = "registrationForm";

    private String securitySituation = "securitySituation";

    private String businessLicence = "businessLicence";

    private String entertainmentLicence = "entertainmentLicence";

    private String fireSafetyCertificate = "fireSafetyCertificate";

    @Override
    public void receive(String msg) {

    }

    @Override
    public String receiveAndSend(String msg) {

        // 判断是否为json格式数据
        if (!JsonUtil.isJSONObject(msg)) {
            return ReturnMessageUtils.returnError1102Object("** message is not JSONObject").toJSONString();
        }

        JSONObject jsonObject = JSONObject.parseObject(msg);
        if (!jsonObject.containsKey(workNo))
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_40035, ReturnMessageUtils.ERROR_40035_MSG, "** Can not find the paramer = " + workNo).toJSONString();

        EntertainmentRecord entertainmentRecord = new EntertainmentRecord();
        entertainmentRecord.setWorkno(jsonObject.getString(workNo));

        if (jsonObject.containsKey(workTypeCode))
            entertainmentRecord.setWorktypecode(jsonObject.getString(workTypeCode));
        if (jsonObject.containsKey(type))
            entertainmentRecord.setType(jsonObject.getInteger(type));
        if (jsonObject.containsKey(status))
            entertainmentRecord.setStatus(jsonObject.getInteger(status));
        if (jsonObject.containsKey(uid))
            entertainmentRecord.setUid(jsonObject.getString(uid));
        if (jsonObject.containsKey(chargeName))
            entertainmentRecord.setChargename(jsonObject.getString(chargeName));
        if (jsonObject.containsKey(mobile))
            entertainmentRecord.setMobile(jsonObject.getString(mobile));
        if (jsonObject.containsKey(handleOffice))
            entertainmentRecord.setHandleoffice(jsonObject.getString(handleOffice));

        AdUnitConverMapper adUnitConverMapper = SpringContextUtils.getBean(AdUnitConverMapper.class);
        AdUnitMapper adUnitMapper = SpringContextUtils.getBean(AdUnitMapper.class);
        List<AdUnitConver> adUnitConverList =  adUnitConverMapper.selectByParamer("ten_code",entertainmentRecord.getHandleoffice());
        if(adUnitConverList==null || adUnitConverList.size()==0){
            entertainmentRecord.setUnitcode(entertainmentRecord.getHandleoffice());
            entertainmentRecord.setParentcode("000000000000");
        }else{
            AdUnitConver adUnitConver = adUnitConverList.get(0);
            entertainmentRecord.setUnitcode(adUnitConver.getCode());
            List<AdUnit> adUnitList = adUnitMapper.selectByParamer("code",adUnitConver.getCode());
            if(adUnitList==null || adUnitList.size()==0){
                entertainmentRecord.setParentcode("000000000000");
            }else{
                entertainmentRecord.setParentcode(adUnitList.get(0).getPcode().length()==0?"000000000000":adUnitList.get(0).getPcode());
            }
        }

        if (jsonObject.containsKey(serviceMode))
            entertainmentRecord.setServicemode(jsonObject.getString(serviceMode));
        if (jsonObject.containsKey(serviceAdress))
            entertainmentRecord.setServiceadress(jsonObject.getString(serviceAdress));
        if (jsonObject.containsKey(doorheadName))
            entertainmentRecord.setDoorheadname(jsonObject.getString(doorheadName));
        if (jsonObject.containsKey(unitName))
            entertainmentRecord.setUnitname(jsonObject.getString(unitName));
        if (jsonObject.containsKey(operatingAddress))
            entertainmentRecord.setOperatingaddress(jsonObject.getString(operatingAddress));
        if (jsonObject.containsKey(unitType))
            entertainmentRecord.setUnittype(jsonObject.getString(unitType));
        if (jsonObject.containsKey(creditCode))
            entertainmentRecord.setCreditcode(jsonObject.getString(creditCode));
        if (jsonObject.containsKey(corporationName))
            entertainmentRecord.setCorporationname(jsonObject.getString(corporationName));
        if (jsonObject.containsKey(corporationMobile))
            entertainmentRecord.setCorporationmobile(jsonObject.getString(corporationMobile));
        if (jsonObject.containsKey(corporationId))
            entertainmentRecord.setCorporationid(jsonObject.getString(corporationId));
        if (jsonObject.containsKey(operatorName))
            entertainmentRecord.setOperatorname(jsonObject.getString(operatorName));
        if (jsonObject.containsKey(operatorMobile))
            entertainmentRecord.setOperatormobile(jsonObject.getString(operatorMobile));
        if (jsonObject.containsKey(operatorIdno))
            entertainmentRecord.setOperatoridno(jsonObject.getString(operatorIdno));
        if (jsonObject.containsKey(registrationForm))
            entertainmentRecord.setRegistrationform(jsonObject.getString(registrationForm));
        if (jsonObject.containsKey(securitySituation))
            entertainmentRecord.setSecuritysituation(jsonObject.getString(securitySituation));
        if (jsonObject.containsKey(businessLicence))
            entertainmentRecord.setBusinesslicence(jsonObject.getString(businessLicence));
        if (jsonObject.containsKey(entertainmentLicence))
            entertainmentRecord.setEntertainmentlicence(jsonObject.getString(entertainmentLicence));
        if (jsonObject.containsKey(fireSafetyCertificate))
            entertainmentRecord.setFiresafetycertificate(jsonObject.getString(fireSafetyCertificate));

        PretrialServer pretrialServer = SpringContextUtils.getBean(PretrialServer.class);
        if (pretrialServer.inputEntertainmentRecord(entertainmentRecord))
            return ReturnMessageUtils.returnSuccessObject("").toJSONString();
        else
            return ReturnMessageUtils.returnError1102Object("** WORKTYPENO_PUBLICSECURITY00041 insert db error").toJSONString();
    }
}
