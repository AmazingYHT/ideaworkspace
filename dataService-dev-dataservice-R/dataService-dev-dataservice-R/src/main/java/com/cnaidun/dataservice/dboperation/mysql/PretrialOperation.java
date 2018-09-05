package com.cnaidun.dataservice.dboperation.mysql;

import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.EntertainmentRecordMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.ScrapMetalMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.EntertainmentRecord;
import com.cnaidun.dataservice.dboperation.mysql.model.ScrapMetal;


/**
 * 娱乐场所登记备案、废旧金属收购业备案 表操作
 */
public class PretrialOperation {

    /**
     * 娱乐场所登记备案 审批表插入
     * @param entertainmentRecord
     * @return
     */
    public int inputEntertainmentRecord(EntertainmentRecord entertainmentRecord){
        int inputCount = 0;
        if(null == entertainmentRecord)
            return inputCount;

        EntertainmentRecordMapper entertainmentRecordMapper = SpringContextUtils.getBean(EntertainmentRecordMapper.class);
        EntertainmentRecord tempRecord = entertainmentRecordMapper.selectByPrimaryKey(entertainmentRecord.getWorkno());
        if(null == tempRecord){
            inputCount = entertainmentRecordMapper.insert(entertainmentRecord);
        }else{
            inputCount = entertainmentRecordMapper.updateByPrimaryKey(entertainmentRecord);
        }
        return inputCount;
    }

    /**
     * 废旧金属收购业备案 审批表插入
     * @param scrapMetal
     * @return
     */
    public int inputScrapMetal(ScrapMetal scrapMetal){
        int inputCount = 0;
        if(null == scrapMetal)
            return inputCount;

        ScrapMetalMapper scrapMetalMapper = SpringContextUtils.getBean(ScrapMetalMapper.class);
        ScrapMetal tempRecord = scrapMetalMapper.selectByPrimaryKey(scrapMetal.getWorkno());
        if(null == tempRecord){
            inputCount = scrapMetalMapper.insert(scrapMetal);
        }else{
            inputCount = scrapMetalMapper.updateByPrimaryKey(scrapMetal);
        }
        return inputCount;
    }

}
