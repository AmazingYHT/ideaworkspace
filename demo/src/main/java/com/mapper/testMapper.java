package com.mapper;



import com.bean.bean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface testMapper {

    @Select("select * from weixin_send where dsend= 0")
    List<bean> selectMap(Map<String, Object> map);

}
