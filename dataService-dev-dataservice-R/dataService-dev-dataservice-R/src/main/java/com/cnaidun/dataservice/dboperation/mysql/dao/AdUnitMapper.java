package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.AdUnit;
import com.cnaidun.dataservice.dboperation.mysql.model.IdInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdUnitMapper {
    int deleteByPrimaryKey(String id);

    int insert(AdUnit record);

    AdUnit selectByPrimaryKey(String id);

    List<AdUnit> selectAll();

    int updateByPrimaryKey(AdUnit record);

    List<AdUnit> selectByParamer(@Param("paramer") String paramer, @Param("paraValue") String paraValue);
}