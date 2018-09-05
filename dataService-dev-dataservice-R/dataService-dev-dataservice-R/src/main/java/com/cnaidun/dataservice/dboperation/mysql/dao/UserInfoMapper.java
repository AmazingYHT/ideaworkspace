package com.cnaidun.dataservice.dboperation.mysql.dao;

import com.cnaidun.dataservice.dboperation.mysql.model.UserInfo;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String uid);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(String uid);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}