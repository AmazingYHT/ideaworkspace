package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.BaseInfoOperation;
import com.cnaidun.dataservice.dboperation.mysql.WeChatUserOperation;
import com.cnaidun.dataservice.dboperation.mysql.dao.WorkSheetMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.WorkStatusMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.WorkTypeMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.*;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.MessageProperties;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 根据uid和status获取工单明细
 */
@Service
@Slf4j
public class WORKTYPENO_USER0003 implements DoMessage {

    private String workNo = "workNo";
    private String workTypeCode = "workTypeCode";
    private String workTime="workTime";
    private String workStatus = "workStatus";
    private String statusName = "statusName";
    private String workName = "workName";
    private String uid = "uid";
    private String unitCode = "unitCode";
    private String unitName = "unitName";
    private String remark = "remark";
    private String optDate = "optDate";
    private String name = "name";

    @Override
    public void receive(String msg) {

    }

    @Override
    public String receiveAndSend(String msg) {
        // 判断是否为json格式数据
        if(!JsonUtil.isJSONObject(msg)){
            return ReturnMessageUtils.returnError1102Object("** message is not JSONObject").toJSONString();
        }

        JSONObject jsonObject = JSONObject.parseObject(msg);
        // 无法获取statue 或 uid 的参数，直接返回错误格式信息
        if(!jsonObject.containsKey(MessageProperties.STATUS) || !jsonObject.containsKey(MessageProperties.UID ))
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_1102,ReturnMessageUtils.ERROR_1102_MSG,"** receive param does not have [uid] or [status]").toJSONString();

        // 获取statue 或 uid 的参数
        String uid = jsonObject.getString(MessageProperties.UID);
        JSONArray statusArray = jsonObject.getJSONArray(MessageProperties.STATUS);

        WeChatUserOperation weChatUserOperation = new WeChatUserOperation();
        List<IdInfo> idInfoList = weChatUserOperation.getIdInfoByParamer(this.uid,uid);

        // 根据 uid 获取工作表单
        WorkSheetMapper workSheetMapper = SpringContextUtils.getBean(WorkSheetMapper.class);
        List<WorkSheet> workSheetList = workSheetMapper.selectByUid(uid);
        if(null == workSheetList || workSheetList.size()==0){
            return  ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.SUCCESS_201,ReturnMessageUtils.SUCCESS_201_MSG,"").toJSONString();
        }



        // 根据工作表单获取获取 工作流水 和 业务编码
        Set<String> worknoSet = new HashSet<String>();
        Set<String> worktypecodeSet = new HashSet<String>();
        for(WorkSheet workSheet:workSheetList){
            // 获取结果集中的workno集合
            worknoSet.add(workSheet.getWorkno());

            // 获取结果集中的worktypecode集合
            worktypecodeSet.add(workSheet.getWorktypecode());
        }

        WorkStatusMapper workStatusMapper = SpringContextUtils.getBean(WorkStatusMapper.class);
        List<String> worknoList = new ArrayList<>(worknoSet);
        List<WorkStatus> workStatusList = workStatusMapper.selectLastByWorknos(worknoList);
        HashMap<String,WorkStatus> workStatusMap = new HashMap<>();
        if(workStatusList!=null && workStatusList.size()>0){
            for(WorkStatus oneState:workStatusList){
                workStatusMap.put(oneState.getWorkno(),oneState);
            }
        }


        WorkTypeMapper workTypeMapper = SpringContextUtils.getBean(WorkTypeMapper.class);
        List<String> worktypecodeList = new ArrayList<>(worktypecodeSet);
        List<WorkType> workTypeList = workTypeMapper.selectByWorktypecodes(worktypecodeList);
        HashMap<String,WorkType> workTypeMap = new HashMap<>();
        if(workTypeList!=null && workTypeList.size()>0){
            for(WorkType onedata:workTypeList){
                workTypeMap.put(onedata.getWorktypecode(),onedata);
            }
        }

        BaseInfoOperation baseInfoOperation = new BaseInfoOperation();
        List<BaseInfo> baseInfoList = baseInfoOperation.getBaseInfoList("policeOffice", "1");
        HashMap<String,BaseInfo> baseInfoHashMap = new HashMap<>();
        if(baseInfoList!=null && baseInfoList.size()>0){
            for(BaseInfo onedata:baseInfoList){
                baseInfoHashMap.put(onedata.getCode(),onedata);
            }
        }

        JSONArray dataJSON = new JSONArray();
        for(WorkSheet oneSheet : workSheetList){

            WorkStatus workStatus = workStatusMap.get(oneSheet.getWorkno());
            // 如果工作单没有状态，本工作单无效
            if(workStatus==null)
                continue;

            // 判断状态是否在参数内
            boolean flag = true;  // 不在
            for(int j=0;j<statusArray.size();j++) {
                if (workStatus.getStatus().equals(statusArray.get(j).toString())) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                continue;




            JSONObject newOne = new JSONObject();
            if(idInfoList!=null && idInfoList.size()>0){
                newOne.put(this.uid,idInfoList.get(0).getUid());
                newOne.put(this.name,idInfoList.get(0).getName());
            }

            newOne.put(this.workNo,oneSheet.getWorkno());
            newOne.put(this.workTypeCode,oneSheet.getWorktypecode());
            newOne.put(this.uid,oneSheet.getUid());
            newOne.put(this.workTime,oneSheet.getCreatetime());

            newOne.put(this.workStatus,workStatus.getStatus());
            String statusName = "已提交，预审中";
            if(workStatus.getStatus().equals("1"))
                statusName = "已提交，预审中";
            else if(workStatus.getStatus().equals("-1"))
                statusName = "预审驳回";
            else if(workStatus.getStatus().equals("0"))
                statusName = "预审通过";
            else if(workStatus.getStatus().equals("2"))
                statusName = "审核中";
            else if(workStatus.getStatus().equals("3"))
                statusName = "审核通过";

            newOne.put(this.statusName,statusName);
            newOne.put(this.unitCode,workStatus.getUnitcode());

            BaseInfo baseInfo = baseInfoHashMap.get(workStatus.getUnitcode());
            if(baseInfo!=null)
                newOne.put(this.unitName,baseInfo.getName());
            newOne.put(this.remark,workStatus.getRemark());
            newOne.put(this.optDate,workStatus.getOptdate());

            WorkType workType = workTypeMap.get(oneSheet.getWorktypecode());
            if(workType!=null)
                newOne.put(workName,workType.getWorkname());

            dataJSON.add(newOne);
        }

        if(dataJSON.size()==0){
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.SUCCESS_201,ReturnMessageUtils.SUCCESS_201_MSG,"").toJSONString();
        }else{
            return ReturnMessageUtils.returnSuccessObject(dataJSON).toJSONString();
        }

    }
}
