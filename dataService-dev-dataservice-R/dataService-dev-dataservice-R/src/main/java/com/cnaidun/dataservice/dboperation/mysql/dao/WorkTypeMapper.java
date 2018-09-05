package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.WorkType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkTypeMapper {
    int deleteByPrimaryKey(String worktypecode);

    int insert(WorkType record);

    WorkType selectByPrimaryKey(String worktypecode);

    List<WorkType> selectAll();

    List<WorkType> selectByWorktypecodes(@Param("worktypecodeList")List<String> worktypecodeList);

    int updateByPrimaryKey(WorkType record);
}