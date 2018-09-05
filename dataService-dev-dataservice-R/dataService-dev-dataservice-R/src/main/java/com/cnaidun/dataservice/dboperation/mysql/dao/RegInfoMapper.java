package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.RegInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegInfoMapper {
    int insert(RegInfo record);

    List<RegInfo> selectAll();

    List<RegInfo> selectByUidList(@Param("uidList") List<String> uidList);
}