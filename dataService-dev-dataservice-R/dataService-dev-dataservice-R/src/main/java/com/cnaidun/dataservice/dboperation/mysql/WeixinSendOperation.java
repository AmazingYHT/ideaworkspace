package com.cnaidun.dataservice.dboperation.mysql;

import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.WeixinSendMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.WeixinSend;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class WeixinSendOperation {

    private static WeixinSendMapper weixinSendMapper;
    /**
     * 根据参数获取数据列表
     * @param paramer 支持dsend、wsend
     * @param value
     * @return
     */
    public List<WeixinSend> selectSMSData(String paramer, String value){
        if(null == weixinSendMapper)
            weixinSendMapper = SpringContextUtils.getBean(WeixinSendMapper.class);
        return weixinSendMapper.selectByParamer(paramer,value);
    }

    public boolean updateSMSDataByWid(String wid,String paramer, String value){
        if(null == weixinSendMapper)
             weixinSendMapper = SpringContextUtils.getBean(WeixinSendMapper.class);
        weixinSendMapper.updateParamerByPrimaryKey(wid,paramer,value);
        return true;
    }
}
