package com.cnaidun.dataservice.dboperation.mysql;

import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.WorkStatusMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.WorkStatus;

public class WorkSheetOperation {

    /**
     * 插入工作单表
     * @param record
     * @return
     */
    public int insertWorkStatus(WorkStatus record){
        int inputCount = 0;
        if(null == record)
            return inputCount;
        WorkStatusMapper workStatusMapper = SpringContextUtils.getBean(WorkStatusMapper.class);
        inputCount = workStatusMapper.insert(record);
        return inputCount;
    }
}
