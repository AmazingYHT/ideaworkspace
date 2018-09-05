package com.cnaidun.dataservice.dboperation;


import com.cnaidun.dataservice.dboperation.mysql.WeChatUserOperation;
import com.cnaidun.dataservice.dboperation.mysql.WeixinSendOperation;
import com.cnaidun.dataservice.dboperation.mysql.model.IdInfo;
import com.cnaidun.dataservice.dboperation.mysql.model.RegInfo;
import com.cnaidun.dataservice.dboperation.mysql.model.WeixinSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WeChatUserServer {

    private String uid = "uid";

    private String idno = "idno";

    private String dsend = "dsend";

    private String wsend = "wsend";

    private WeixinSendOperation weixinSendOperation;

    /**
     *
     * @param wid 微信注册编号
     * @param wSend 微信发送状态
     * @return
     */
    public boolean updateSMSDataWsendByWid(String wid,int wSend){
        try{
            if(null == weixinSendOperation)
                weixinSendOperation = new WeixinSendOperation();
            return weixinSendOperation.updateSMSDataByWid(wid,this.wsend,String.valueOf(wSend));
        }catch (Exception e){
            log.info("** updateSMSDataDsendByWid error : {}",e.getMessage());
            return false;
        }
    }

    /**
     * 更新短信发送状态
     * @param wid 微信注册编号
     * @param dSend 短信发送状态
     * @return
     */
    public boolean updateSMSDataDsendByWid(String wid,int dSend){
        try{
            if(null == weixinSendOperation)
               weixinSendOperation = new WeixinSendOperation();
            return weixinSendOperation.updateSMSDataByWid(wid,this.dsend,String.valueOf(dSend));
        }catch (Exception e){
            log.error("** updateSMSDataDsendByWid error : {}",e.getMessage());
            return false;
        }
    }

    /**
     * 根据 短信发送状态 获取数据
     * @param dSend
     * @return
     */
    public List<WeixinSend> getSMSDataByDsend(int dSend){
        try{
            WeixinSendOperation weixinSendOperation = new WeixinSendOperation();
            return weixinSendOperation.selectSMSData(this.dsend,String.valueOf(dSend));
        }catch (Exception e){
            log.error("** getSMSDataByDsend error : {}",e.getMessage());
            return null;
        }
    }

    /**
     * 根据 短信发送状态 获取数据
     * @param wSend
     * @return
     */
    public List<WeixinSend> getSMSDataByWsend(int wSend){
        try{
            WeixinSendOperation weixinSendOperation = new WeixinSendOperation();
            return weixinSendOperation.selectSMSData(this.wsend,String.valueOf(wSend));
        }catch (Exception e){
            log.error("** getSMSDataByDsend error : {}",e.getMessage());
            return null;
        }
    }

    /**
     * 根据 身份证号 获取注册信息
     * @param idno
     * @return
     */
    public List<IdInfo> getIdInfoByIdno(String idno){
        WeChatUserOperation weChatUserOperation = new WeChatUserOperation();
        return weChatUserOperation.getIdInfoByParamer(this.idno,idno);
    }

    /**
     * 根据 微信小程序编码 获取注册信息
     * @param uid
     * @return
     */
    public List<IdInfo> getIdInfoByUid(String uid){
        WeChatUserOperation weChatUserOperation = new WeChatUserOperation();
        return weChatUserOperation.getIdInfoByParamer(this.uid,uid);
    }

    /**
     * 根据 微信小程序编码列表 获取注册信息
     * @param idnoList
     * @return
     */
    public List<IdInfo> getIdInfoByIdnoList(List<String> idnoList){
        WeChatUserOperation weChatUserOperation = new WeChatUserOperation();
        return weChatUserOperation.getIdInfoByIdnoList(idnoList);
    }

    /**
     * 根据 微信小程序编码列表 获取注册信息
     * @param uidList
     * @return
     */
    public List<RegInfo> getRegInfoByUidList(List<String> uidList){
        WeChatUserOperation weChatUserOperation = new WeChatUserOperation();
        return weChatUserOperation.getRegInfoByUidList(uidList);
    }

    /**
     * 根据 微信小程序编码 删除注册信息
     * @param uid
     * @return
     */
    public boolean delIdInfoByUid(String uid){
        WeChatUserOperation weChatUserOperation = new WeChatUserOperation();
        return weChatUserOperation.delIdInfoByParamer(this.uid,uid);
    }
}
