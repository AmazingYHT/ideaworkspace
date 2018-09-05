package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.WorkStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkStatusMapper {
    int insert(WorkStatus record);


    List<WorkStatus> selectLastByWorknos(@Param("worknoList")List<String> worknoList);

    List<WorkStatus> selectAll();
}