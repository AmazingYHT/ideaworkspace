package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.WorkSheet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSheetMapper {
    int deleteByPrimaryKey(String workno);

    int insert(WorkSheet record);

    WorkSheet selectByPrimaryKey(String workno);

    List<WorkSheet> selectAll();

    List<WorkSheet> selectByUid(String uid);

    int updateByPrimaryKey(WorkSheet record);
}