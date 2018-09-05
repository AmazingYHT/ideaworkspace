package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.EntertainmentRecord;
import java.util.List;

public interface EntertainmentRecordMapper {
    int deleteByPrimaryKey(String workno);

    int insert(EntertainmentRecord record);

    EntertainmentRecord selectByPrimaryKey(String workno);

    List<EntertainmentRecord> selectAll();

    int updateByPrimaryKey(EntertainmentRecord record);
}