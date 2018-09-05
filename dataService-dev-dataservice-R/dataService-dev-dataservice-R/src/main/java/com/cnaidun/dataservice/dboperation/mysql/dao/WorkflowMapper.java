package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.Workflow;
import java.util.List;

public interface WorkflowMapper {
    int deleteByPrimaryKey(String workflowno);

    int insert(Workflow record);

    Workflow selectByPrimaryKey(String workflowno);

    List<Workflow> selectAll();

    int updateByPrimaryKey(Workflow record);
}