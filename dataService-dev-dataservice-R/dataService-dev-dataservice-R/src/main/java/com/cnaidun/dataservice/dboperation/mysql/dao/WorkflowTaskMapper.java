package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.WorkflowTask;
import java.util.List;

public interface WorkflowTaskMapper {
    int deleteByPrimaryKey(String taskno);

    int insert(WorkflowTask record);

    WorkflowTask selectByPrimaryKey(String taskno);

    List<WorkflowTask> selectAll();

    int updateByPrimaryKey(WorkflowTask record);
}