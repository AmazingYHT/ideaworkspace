package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.AdUnitConver;
import com.cnaidun.dataservice.dboperation.mysql.model.IdInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdUnitConverMapper {
    int insert(AdUnitConver record);

    List<AdUnitConver> selectAll();

    List<AdUnitConver> selectByParamer(@Param("paramer") String paramer, @Param("paraValue") String paraValue);
}