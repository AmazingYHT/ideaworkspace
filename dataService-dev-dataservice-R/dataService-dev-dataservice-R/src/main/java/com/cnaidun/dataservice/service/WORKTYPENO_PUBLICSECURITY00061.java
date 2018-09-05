package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.PretrialServer;
import com.cnaidun.dataservice.dboperation.mysql.dao.AdUnitConverMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.AdUnitMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.AdUnit;
import com.cnaidun.dataservice.dboperation.mysql.model.AdUnitConver;
import com.cnaidun.dataservice.dboperation.mysql.model.ScrapMetal;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;

import java.util.List;


/**
 * 废旧金属预登记审批
 */
public class WORKTYPENO_PUBLICSECURITY00061 implements DoMessage {

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

    private String unitName = "unitName";

    private String creditCode = "creditCode";

    private String corporationName = "corporationName";

    private String corporationMobile = "corporationMobile";

    private String corporationId = "corporationId";

    private String operatorName = "operatorName";

    private String operatorMobile = "operatorMobile";

    private String operatorIdno = "operatorIdno";

    private String businessLicence = "businessLicence";

    private String corporationIdno = "corporationIdno";

    private String scrapMetalRegistrationTable = "scrapMetalRegistrationTable";

    private String personnelRegistrationTable = "personnelRegistrationTable";

    private String shopFacadePhotos = "shopFacadePhotos";

    private String houseLease = "houseLease";

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
        if(!jsonObject.containsKey(workNo))
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_40035,ReturnMessageUtils.ERROR_40035_MSG,"** Can not find the paramer = "+workNo).toJSONString();

        ScrapMetal scrapMetal = new ScrapMetal();
        scrapMetal.setWorkno(jsonObject.getString(workNo));
        if (jsonObject.containsKey(workTypeCode))
            scrapMetal.setWorktypecode(jsonObject.getString(workTypeCode));

        if (jsonObject.containsKey(type))
            scrapMetal.setType(jsonObject.getInteger(type));
        if (jsonObject.containsKey(status))
            scrapMetal.setStatus(jsonObject.getInteger(status));
        if (jsonObject.containsKey(uid))
            scrapMetal.setUid(jsonObject.getString(uid));
        if (jsonObject.containsKey(chargeName))
            scrapMetal.setChargename(jsonObject.getString(chargeName));
        if (jsonObject.containsKey(mobile))
            scrapMetal.setMobile(jsonObject.getString(mobile));
        if (jsonObject.containsKey(handleOffice))
            scrapMetal.setHandleoffice(jsonObject.getString(handleOffice));

        AdUnitConverMapper adUnitConverMapper = SpringContextUtils.getBean(AdUnitConverMapper.class);
        AdUnitMapper adUnitMapper = SpringContextUtils.getBean(AdUnitMapper.class);
        List<AdUnitConver> adUnitConverList =  adUnitConverMapper.selectByParamer("ten_code",scrapMetal.getHandleoffice());
        if(adUnitConverList==null || adUnitConverList.size()==0){
            scrapMetal.setUnitcode(scrapMetal.getHandleoffice());
            scrapMetal.setParentcode("000000000000");
        }else{
            AdUnitConver adUnitConver = adUnitConverList.get(0);
            scrapMetal.setUnitcode(adUnitConver.getCode());
            List<AdUnit> adUnitList = adUnitMapper.selectByParamer("code",adUnitConver.getCode());
            if(adUnitList==null || adUnitList.size()==0){
                scrapMetal.setParentcode("000000000000");
            }else{
                scrapMetal.setParentcode(adUnitList.get(0).getPcode().length()==0?"000000000000":adUnitList.get(0).getPcode());
            }
        }

        if (jsonObject.containsKey(serviceMode))
            scrapMetal.setServicemode(jsonObject.getString(serviceMode));
        if (jsonObject.containsKey(serviceAdress))
            scrapMetal.setServiceadress(jsonObject.getString(serviceAdress));
        if (jsonObject.containsKey(unitName))
            scrapMetal.setUnitname(jsonObject.getString(unitName));
        if (jsonObject.containsKey(creditCode))
            scrapMetal.setCreditcode(jsonObject.getString(creditCode));
        if (jsonObject.containsKey(corporationName))
            scrapMetal.setCorporationname(jsonObject.getString(corporationName));
        if (jsonObject.containsKey(corporationMobile))
            scrapMetal.setCorporationmobile(jsonObject.getString(corporationMobile));
        if (jsonObject.containsKey(corporationId))
            scrapMetal.setCorporationid(jsonObject.getString(corporationId));
        if (jsonObject.containsKey(operatorName))
            scrapMetal.setOperatorname(jsonObject.getString(operatorName));
        if (jsonObject.containsKey(operatorMobile))
            scrapMetal.setOperatormobile(jsonObject.getString(operatorMobile));
        if (jsonObject.containsKey(operatorIdno))
            scrapMetal.setOperatoridno(jsonObject.getString(operatorIdno));

        if (jsonObject.containsKey(corporationIdno))
            scrapMetal.setCorporationidno(jsonObject.getString(corporationIdno));

        if (jsonObject.containsKey(scrapMetalRegistrationTable))
            scrapMetal.setScrapmetalregistrationtable(jsonObject.getString(scrapMetalRegistrationTable));

        if (jsonObject.containsKey(businessLicence))
            scrapMetal.setBusinesslicence(jsonObject.getString(businessLicence));

        if (jsonObject.containsKey(personnelRegistrationTable))
            scrapMetal.setPersonnelregistrationtable(jsonObject.getString(personnelRegistrationTable));
        if (jsonObject.containsKey(shopFacadePhotos))
            scrapMetal.setShopfacadephotos(jsonObject.getString(shopFacadePhotos));

        if (jsonObject.containsKey(houseLease))
            scrapMetal.setHouselease(jsonObject.getString(houseLease));

        PretrialServer pretrialServer = SpringContextUtils.getBean(PretrialServer.class);
        if(pretrialServer.inputScrapMetal(scrapMetal))
            return ReturnMessageUtils.returnSuccessObject("").toJSONString();
        else
            return ReturnMessageUtils.returnError1102Object("** WORKTYPENO_PUBLICSECURITY0005 error").toJSONString();
    }
}
