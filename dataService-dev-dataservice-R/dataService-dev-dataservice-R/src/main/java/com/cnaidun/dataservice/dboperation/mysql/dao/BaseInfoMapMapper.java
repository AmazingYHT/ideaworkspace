package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.BaseInfoMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseInfoMapMapper {
    int insert(BaseInfoMap record);

    List<BaseInfoMap> selectAll();

    List<BaseInfoMap> selectByParamers(@Param("parenttype") String parenttype, @Param("parentcode") String parentcode, @Param("infotype") String infotype);

    List<BaseInfoMap> selectByParamerType(@Param("parenttype") String parenttype, @Param("infotype") String infotype);
}