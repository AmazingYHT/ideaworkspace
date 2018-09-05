package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.ScrapMetal;
import java.util.List;

public interface ScrapMetalMapper {
    int deleteByPrimaryKey(String workno);

    int insert(ScrapMetal record);

    ScrapMetal selectByPrimaryKey(String workno);

    List<ScrapMetal> selectAll();

    int updateByPrimaryKey(ScrapMetal record);
}