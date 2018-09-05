package com.cnaidun.dataservice.dboperation.mysql.model;

import java.io.Serializable;
import java.util.Date;

public class Workflow implements Serializable {
    private String workflowno;

    private String name;

    private String worktypecode;

    private String content;

    private String status;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public String getWorkflowno() {
        return workflowno;
    }

    public void setWorkflowno(String workflowno) {
        this.workflowno = workflowno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorktypecode() {
        return worktypecode;
    }

    public void setWorktypecode(String worktypecode) {
        this.worktypecode = worktypecode;
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
        sb.append(", workflowno=").append(workflowno);
        sb.append(", name=").append(name);
        sb.append(", worktypecode=").append(worktypecode);
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
        Workflow other = (Workflow) that;
        return (this.getWorkflowno() == null ? other.getWorkflowno() == null : this.getWorkflowno().equals(other.getWorkflowno()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getWorktypecode() == null ? other.getWorktypecode() == null : this.getWorktypecode().equals(other.getWorktypecode()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWorkflowno() == null) ? 0 : getWorkflowno().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getWorktypecode() == null) ? 0 : getWorktypecode().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }
}