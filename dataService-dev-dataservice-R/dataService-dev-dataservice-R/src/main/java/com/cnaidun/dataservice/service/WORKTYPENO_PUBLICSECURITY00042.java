package com.cnaidun.dataservice.service;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.dataservice.common.utils.JsonUtil;
import com.cnaidun.dataservice.common.utils.SpringContextUtils;
import com.cnaidun.dataservice.dboperation.PretrialServer;
import com.cnaidun.dataservice.dboperation.WorkSheetServer;
import com.cnaidun.dataservice.dboperation.mysql.model.WorkStatus;
import com.cnaidun.dataservice.service.inter.DoMessage;
import com.cnaidun.dataservice.utils.ReturnMessageUtils;


/**
 * 娱乐场所预登记审批
 */
public class WORKTYPENO_PUBLICSECURITY00042 implements DoMessage {

    private String workno = "workno";

    private String unitcode = "unitcode";

    private String opusercode = "opusercode";

    private String workflowno = "workflowno";

    private String taskno = "taskno";

    private String status = "status";

    private String rescontent = "rescontent";

    private String optdate= "optdate";

    private String remark = "remark";

    @Override
    public void receive(String msg) {

    }

    @Override
    public String receiveAndSend(String msg) {
        // 判断是否为json格式数据
        if (!JsonUtil.isJSONObject(msg)) {
            return ReturnMessageUtils.returnError1102Object("** message is not JSONObject").toJSONString();
        }

        JSONObject jsonObject = JSONObject.parseObject(msg);
        if(!jsonObject.containsKey(workno))
            return ReturnMessageUtils.returnMsgObject(ReturnMessageUtils.ERROR_40035,ReturnMessageUtils.ERROR_40035_MSG,"** Can not find the paramer = "+workno).toJSONString();

        WorkStatus workStatus = new WorkStatus();
        workStatus.setWorkno(jsonObject.getString(workno));

        if(jsonObject.containsKey(unitcode))
            workStatus.setUnitcode(jsonObject.getString(unitcode));
        if(jsonObject.containsKey(opusercode))
            workStatus.setOpusercode(jsonObject.getString(opusercode));
        if(jsonObject.containsKey(workflowno))
            workStatus.setWorkflowno(jsonObject.getString(workflowno));
        if(jsonObject.containsKey(taskno))
            workStatus.setTaskno(jsonObject.getString(taskno));
        if(jsonObject.containsKey(status))
            workStatus.setStatus(jsonObject.getString(status));
        if(jsonObject.containsKey(optdate))
            workStatus.setOptdate(jsonObject.getDate(optdate));
        if(jsonObject.containsKey(remark))
            workStatus.setRemark(jsonObject.getString(remark));
        if(jsonObject.containsKey(rescontent))
            workStatus.setRescontent(jsonObject.getString(rescontent));

        WorkSheetServer workSheetServer = SpringContextUtils.getBean(WorkSheetServer.class);
        if (workSheetServer.insertWorkStatus(workStatus))
            return ReturnMessageUtils.returnSuccessObject("").toJSONString();
        else
            return ReturnMessageUtils.returnError1102Object("** WORKTYPENO_PUBLICSECURITY00042 insert db error").toJSONString();
    }
}
