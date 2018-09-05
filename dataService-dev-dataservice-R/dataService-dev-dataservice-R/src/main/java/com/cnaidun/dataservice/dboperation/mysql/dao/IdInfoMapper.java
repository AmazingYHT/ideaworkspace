package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.IdInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IdInfoMapper {
    int deleteByPrimaryKey(@Param("uid") String uid, @Param("idno") String idno);

    int deleteByParamer(@Param("paramer") String paramer,@Param("paraValue") String paraValue);

    int insert(IdInfo record);

    IdInfo selectByPrimaryKey(@Param("uid") String uid, @Param("idno") String idno);

    List<IdInfo> selectAll();

    List<IdInfo> selectByParamer(@Param("paramer") String paramer,@Param("paraValue") String paraValue);

    List<IdInfo> selectByIdnoList(@Param("idnoList") List<String> idnoList);

    int updateByPrimaryKey(IdInfo record);
}