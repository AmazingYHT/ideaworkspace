package com.cnaidun.dataservice.dboperation;

import com.cnaidun.dataservice.dboperation.mysql.PretrialOperation;
import com.cnaidun.dataservice.dboperation.mysql.model.EntertainmentRecord;
import com.cnaidun.dataservice.dboperation.mysql.model.ScrapMetal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PretrialServer {

    public boolean inputEntertainmentRecord(EntertainmentRecord entertainmentRecord) {
        if(null == entertainmentRecord)
            return false;
        PretrialOperation pretrialOperation = new PretrialOperation();
        try{
            pretrialOperation.inputEntertainmentRecord(entertainmentRecord);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }


    public boolean inputScrapMetal(ScrapMetal scrapMetal) {
        if (null == scrapMetal)
            return false;
        PretrialOperation pretrialOperation = new PretrialOperation();
        try{
            pretrialOperation.inputScrapMetal(scrapMetal);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
