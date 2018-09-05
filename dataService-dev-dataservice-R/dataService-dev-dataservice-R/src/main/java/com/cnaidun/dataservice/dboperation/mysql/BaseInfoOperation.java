package com.cnaidun.dataservice.dboperation.mysql;

import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.BaseInfoMapMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.BaseInfoMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.BaseInfo;
import com.cnaidun.dataservice.dboperation.mysql.model.BaseInfoMap;

import java.util.List;

public class BaseInfoOperation {

    /**
     * 按字典类型查询
     * @param infoType
     * @return
     */
    public List<BaseInfo> getBaseInfoList(String infoType,String status){
        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);
        List<BaseInfo> baseInfoList = baseInfoMapper.selectByInfotype(infoType,status);
        return baseInfoList;
    }

    /**
     * 按字典类型查询
     * @param infoTypeList
     * @return
     */
    public List<BaseInfo> getBaseInfoList(List<String> infoTypeList,String status){
        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);
        // status = "1",有效的字典
        List<BaseInfo> baseInfoList = baseInfoMapper.selectByInfotypeList(infoTypeList,status);
        return baseInfoList;
    }

    /**
     * 按字典类型查询
     * @param infoType
     * @return
     */
    public BaseInfo getBaseInfoList(String infoType,String code ,String status){
        BaseInfoMapper baseInfoMapper = SpringContextUtils.getBean(BaseInfoMapper.class);
        // status = "1",有效的字典
        BaseInfo baseInfo = baseInfoMapper.selectByCode(infoType,code,status);
        return baseInfo;
    }

    /**
     * 根据父节点类型、父节点编码、子节点类型查询 节点信息
     * @param infoType
     * @return
     */
    public List<BaseInfoMap> getBaseInfoMapList(String parenttype, String parentcode, String infoType){
        BaseInfoMapMapper baseInfoMapMapper = SpringContextUtils.getBean(BaseInfoMapMapper.class);
        // status = "1",有效的字典
        List<BaseInfoMap> baseInfoMapList = baseInfoMapMapper.selectByParamers(parenttype,parentcode,infoType);
        return baseInfoMapList;
    }

    /**
     * 根据父节点类型、子节点类型查询 节点信息
     * @param infoType
     * @return
     */
    public List<BaseInfoMap> getBaseInfoMapList(String parenttype, String infoType){
        BaseInfoMapMapper baseInfoMapMapper = SpringContextUtils.getBean(BaseInfoMapMapper.class);
        // status = "1",有效的字典
        List<BaseInfoMap> baseInfoMapList = baseInfoMapMapper.selectByParamerType(parenttype,infoType);
        return baseInfoMapList;
    }

}
