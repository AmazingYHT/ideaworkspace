package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.BaseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseInfoMapper {
    int insert(BaseInfo record);

    String findTemplateByWorkTypeCode(String workTypeCode);

    List<BaseInfo> selectAll();

    List<BaseInfo> selectByInfotype(@Param("infotype") String infotype, @Param("status") String status);

    List<BaseInfo> selectByInfotypeList(@Param("infotypeList") List<String> infotypeList, @Param("status") String status);

    BaseInfo selectByCode(@Param("infotype") String infotype, @Param("code") String code, @Param("status") String status);
}