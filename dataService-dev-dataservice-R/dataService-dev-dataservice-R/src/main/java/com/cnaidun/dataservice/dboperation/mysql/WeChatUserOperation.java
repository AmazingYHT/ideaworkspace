package com.cnaidun.dataservice.dboperation.mysql;

import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.BaseInfoMapMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.IdInfoMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.RegInfoMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.WeixinSendMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.IdInfo;
import com.cnaidun.dataservice.dboperation.mysql.model.RegInfo;
import com.cnaidun.dataservice.dboperation.mysql.model.WeixinSend;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * 微信用户数据库操作类
 */
@Slf4j
public class WeChatUserOperation {

    public List<RegInfo> getRegInfoByUidList(List<String> uidList){
        RegInfoMapper regInfoMapper = SpringContextUtils.getBean(RegInfoMapper.class);
        try{
            return regInfoMapper.selectByUidList(uidList);
        }catch (Exception e){
            log.error("** getDataByUidList error : {}"+e.getMessage());
            return null;
        }
    }

    /**
     * 根据idno列表获取IdInfo信息
     * @param idnoList
     * @return
     */
    public List<IdInfo> getIdInfoByIdnoList(List<String> idnoList){
        IdInfoMapper idInfoMapper = SpringContextUtils.getBean(IdInfoMapper.class);
        try{
            return idInfoMapper.selectByIdnoList(idnoList);
        }catch (Exception e){
            log.error("** getDataByUidList error : {}"+e.getMessage());
            return null;
        }
    }

    /**
     * 根据一组 参数 及 参数值 获取 用户实名认证信息
     * @param paramer
     * @param value
     * @return
     */
    public List<IdInfo> getIdInfoByParamer(String paramer, String value){
        IdInfoMapper idInfoMapper = SpringContextUtils.getBean(IdInfoMapper.class);
        return idInfoMapper.selectByParamer(paramer,value);
    }

    public boolean delIdInfoByParamer(String paramer,String value){
        IdInfoMapper idInfoMapper = SpringContextUtils.getBean(IdInfoMapper.class);
        try{
            idInfoMapper.deleteByParamer(paramer,value);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

}
