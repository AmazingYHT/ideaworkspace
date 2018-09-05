package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.mysql.dao.WorkSheetMapper;
import com.cnaidun.dataservice.dboperation.mysql.dao.WorkStatusMapper;
import com.cnaidun.dataservice.dboperation.mysql.model.WorkSheet;
import com.cnaidun.dataservice.dboperation.mysql.model.WorkStatus;
import com.cnaidun.dataservice.jsontemplates.DistributionUtil;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class WORKTYPENO_ENTRYANDEXIT0001 implements DoMessage {

    private String workNo= "workNo";

    private String uid = "uid";

    private String workTypeCode = "workTypeCode";

    private String unitcode = "unitcode";

    private String reqcontent = "reqcontent";

    private String createtime = "createtime";

    private String opusercode = "opusercode";

    private String workflowno="workflowno";

    private String taskno = "taskno";

    @Override
    public void receive(String msg) {

    }

    /**
     * 工作表单提交
     * 1、入work_sheet表单库
     * 2、入work_statu表单状态库
     * @param msg
     * @return
     */
    @Override
    public String receiveAndSend(String msg) {
        log.info("** WORKTYPENO_ENTRYANDEXIT0001 : {}",msg);
        WorkSheet workSheet=new WorkSheet();

        WorkStatus workStatus=new WorkStatus();

        JSONObject jsonObject = JSONObject.parseObject(msg);
        if(!jsonObject.containsKey(uid))
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_40035,ReturnMessageUtils.ERROR_40035_MSG,"** Can not find the paramer = uid").toJSONString();
        else
            workSheet.setUid(jsonObject.getString(uid));

        // 自动生成
        String workno=UUID.randomUUID().toString();
        workSheet.setWorkno(workno);
        workSheet.setStatus("0");

        workStatus.setWorkno(workno);
        workStatus.setStatus("0");

        if(jsonObject.containsKey(workTypeCode)){
            workSheet.setWorktypecode(jsonObject.getString(workTypeCode));
        }

        if(jsonObject.containsKey(unitcode)){
            workSheet.setUnitcode(jsonObject.getString(unitcode));
            workStatus.setUnitcode(jsonObject.getString(unitcode));
        }

        if(jsonObject.containsKey(reqcontent)){
            workSheet.setReqcontent(jsonObject.getString(reqcontent));
            workStatus.setRescontent(jsonObject.getString(reqcontent));
        }else{
            workSheet.setReqcontent(msg);
            workStatus.setRescontent(msg);
        }

        if(jsonObject.containsKey(createtime)){
            workSheet.setCreatetime(jsonObject.getDate(createtime));
            workStatus.setOptdate(jsonObject.getDate(createtime));
        }else{
            Date date = new Date();
            workSheet.setCreatetime(date);
            workStatus.setOptdate(date);
        }

        if(jsonObject.containsKey(opusercode)){
            workStatus.setOpusercode(jsonObject.getString(opusercode));
        }

        if(jsonObject.containsKey(workflowno)){
            workStatus.setWorkflowno(jsonObject.getString(workflowno));
        }

        if(jsonObject.containsKey(taskno)){
            workStatus.setTaskno(jsonObject.getString(taskno));
        }

        WorkSheetMapper workSheetMapper = SpringContextUtils.getBean(WorkSheetMapper.class);
        WorkStatusMapper workStatusMapper = SpringContextUtils.getBean(WorkStatusMapper.class);
        log.info("** DB : {}",workSheetMapper);
        int i = 0;
        try {
             i = workSheetMapper.insert(workSheet);
            log.info("** DB insert workSheet: {}", workSheet);
            i = workStatusMapper.insert(workStatus);
            log.info("** DB insert workStatus: {}", workStatus);
        } catch(Exception e) {
            e.printStackTrace();

        }
        if(i>0){
            DistributionUtil du=new DistributionUtil();//调用第三方接口分发类
            jsonObject.put("workno",workno);
            JSONObject dsfBack=DistributionUtil.distribute(jsonObject);

            JSONObject dataJson = new JSONObject();
            dataJson.put(this.workNo,workno);
            return ReturnMessageUtils.returnSuccessObject(dataJson).toJSONString();
        }else{
            return ReturnMessageUtils.returnError1102Object("** Can not insert to DB").toJSONString();
        }
    }
}
