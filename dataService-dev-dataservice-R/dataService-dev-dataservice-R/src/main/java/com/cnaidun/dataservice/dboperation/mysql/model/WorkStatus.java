package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;
import java.util.Date;

public class WorkStatus implements Serializable {
    private String workno;

    private String unitcode;

    private String opusercode;

    private String workflowno;

    private String taskno;

    private String status;

    private String rescontent;

    private Date optdate;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getOpusercode() {
        return opusercode;
    }

    public void setOpusercode(String opusercode) {
        this.opusercode = opusercode;
    }

    public String getWorkflowno() {
        return workflowno;
    }

    public void setWorkflowno(String workflowno) {
        this.workflowno = workflowno;
    }

    public String getTaskno() {
        return taskno;
    }

    public void setTaskno(String taskno) {
        this.taskno = taskno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRescontent() {
        return rescontent;
    }

    public void setRescontent(String rescontent) {
        this.rescontent = rescontent;
    }

    public Date getOptdate() {
        return optdate;
    }

    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", workno=").append(workno);
        sb.append(", unitcode=").append(unitcode);
        sb.append(", opusercode=").append(opusercode);
        sb.append(", workflowno=").append(workflowno);
        sb.append(", taskno=").append(taskno);
        sb.append(", status=").append(status);
        sb.append(", rescontent=").append(rescontent);
        sb.append(", optdate=").append(optdate);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WorkStatus other = (WorkStatus) that;
        return (this.getWorkno() == null ? other.getWorkno() == null : this.getWorkno().equals(other.getWorkno()))
            && (this.getUnitcode() == null ? other.getUnitcode() == null : this.getUnitcode().equals(other.getUnitcode()))
            && (this.getOpusercode() == null ? other.getOpusercode() == null : this.getOpusercode().equals(other.getOpusercode()))
            && (this.getWorkflowno() == null ? other.getWorkflowno() == null : this.getWorkflowno().equals(other.getWorkflowno()))
            && (this.getTaskno() == null ? other.getTaskno() == null : this.getTaskno().equals(other.getTaskno()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRescontent() == null ? other.getRescontent() == null : this.getRescontent().equals(other.getRescontent()))
            && (this.getOptdate() == null ? other.getOptdate() == null : this.getOptdate().equals(other.getOptdate()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWorkno() == null) ? 0 : getWorkno().hashCode());
        result = prime * result + ((getUnitcode() == null) ? 0 : getUnitcode().hashCode());
        result = prime * result + ((getOpusercode() == null) ? 0 : getOpusercode().hashCode());
        result = prime * result + ((getWorkflowno() == null) ? 0 : getWorkflowno().hashCode());
        result = prime * result + ((getTaskno() == null) ? 0 : getTaskno().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRescontent() == null) ? 0 : getRescontent().hashCode());
        result = prime * result + ((getOptdate() == null) ? 0 : getOptdate().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}