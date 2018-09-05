package com.cnaidun.dataservice.dboperation;

import com.cnaidun.dataservice.dboperation.mysql.WorkSheetOperation;
import com.cnaidun.dataservice.dboperation.mysql.model.WorkStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WorkSheetServer {

    /**
     * 插入工作单审批单
     * @param record
     * @return
     */
    public boolean insertWorkStatus(WorkStatus record){
        if(null == record)
            return false;
        WorkSheetOperation workSheetOperation = new WorkSheetOperation();
        try{
            workSheetOperation.insertWorkStatus(record);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

}
