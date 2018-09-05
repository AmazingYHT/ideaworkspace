package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.WeixinSend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeixinSendMapper {
    int deleteByPrimaryKey(String wid);

    int insert(WeixinSend record);

    WeixinSend selectByPrimaryKey(String wid);

    List<WeixinSend> selectAll();

    int updateByPrimaryKey(WeixinSend record);

    List<WeixinSend> selectByParamer(@Param("paramer") String paramer, @Param("paraValue") String paraValue);

    int updateParamerByPrimaryKey(@Param("wid") String wid,@Param("paramer") String paramer, @Param("paraValue") String paraValue);
}