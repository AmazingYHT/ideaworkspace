package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.BaseInfotype;
import java.util.List;

public interface BaseInfotypeMapper {
    int deleteByPrimaryKey(String infotype);

    int insert(BaseInfotype record);

    BaseInfotype selectByPrimaryKey(String infotype);

    List<BaseInfotype> selectAll();

    int updateByPrimaryKey(BaseInfotype record);
}