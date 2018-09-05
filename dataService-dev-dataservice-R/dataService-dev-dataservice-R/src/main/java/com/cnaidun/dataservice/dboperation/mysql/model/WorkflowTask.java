package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;
import java.util.Date;

public class WorkflowTask implements Serializable {
    private String taskno;

    private String taskname;

    private String workflowno;

    private String tasktype;

    private String orderno;

    private String content;

    private String status;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public String getTaskno() {
        return taskno;
    }

    public void setTaskno(String taskno) {
        this.taskno = taskno;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getWorkflowno() {
        return workflowno;
    }

    public void setWorkflowno(String workflowno) {
        this.workflowno = workflowno;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskno=").append(taskno);
        sb.append(", taskname=").append(taskname);
        sb.append(", workflowno=").append(workflowno);
        sb.append(", tasktype=").append(tasktype);
        sb.append(", orderno=").append(orderno);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
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
        WorkflowTask other = (WorkflowTask) that;
        return (this.getTaskno() == null ? other.getTaskno() == null : this.getTaskno().equals(other.getTaskno()))
            && (this.getTaskname() == null ? other.getTaskname() == null : this.getTaskname().equals(other.getTaskname()))
            && (this.getWorkflowno() == null ? other.getWorkflowno() == null : this.getWorkflowno().equals(other.getWorkflowno()))
            && (this.getTasktype() == null ? other.getTasktype() == null : this.getTasktype().equals(other.getTasktype()))
            && (this.getOrderno() == null ? other.getOrderno() == null : this.getOrderno().equals(other.getOrderno()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskno() == null) ? 0 : getTaskno().hashCode());
        result = prime * result + ((getTaskname() == null) ? 0 : getTaskname().hashCode());
        result = prime * result + ((getWorkflowno() == null) ? 0 : getWorkflowno().hashCode());
        result = prime * result + ((getTasktype() == null) ? 0 : getTasktype().hashCode());
        result = prime * result + ((getOrderno() == null) ? 0 : getOrderno().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }
}